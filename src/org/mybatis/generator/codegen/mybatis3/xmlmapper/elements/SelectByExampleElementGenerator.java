package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

/**
 * 根据字段生成select by example
 * 
 * @author nohi
 */
public class SelectByExampleElementGenerator extends AbstractXmlElementGenerator {

	public SelectByExampleElementGenerator() {
		super();
	}

	@Override
	public void addElements(XmlElement parentElement) {
		XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

		answer.addAttribute(new Attribute("id", "selectByExample")); //$NON-NLS-1$
		if (introspectedTable.getRules().generateResultMapWithBLOBs()) {
			answer.addAttribute(new Attribute("resultMap",introspectedTable.getResultMapWithBLOBsId()));
		} else {
			answer.addAttribute(new Attribute("resultMap",introspectedTable.getBaseResultMapId()));
		}

		FullyQualifiedJavaType parameterType = introspectedTable.getRules().calculateAllFieldsClass();
		answer.addAttribute(new Attribute("parameterType",parameterType.getFullyQualifiedName()));

		context.getCommentGenerator().addComment(answer);

		StringBuilder sb = new StringBuilder();
		sb.append("select "); //$NON-NLS-1$

		if (stringHasValue(introspectedTable.getSelectByPrimaryKeyQueryId())) {
			sb.append('\'');
			sb.append(introspectedTable.getSelectByPrimaryKeyQueryId());
			sb.append("' as QUERYID,"); //$NON-NLS-1$
		}
		answer.addElement(new TextElement(sb.toString()));
		answer.addElement(getBaseColumnListElement());
		if (introspectedTable.hasBLOBColumns()) {
			answer.addElement(new TextElement(",")); //$NON-NLS-1$
			answer.addElement(getBlobColumnListElement());
		}

		sb.setLength(0);
		sb.append("from "); //$NON-NLS-1$
//		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
		sb.append(introspectedTable.getFullyQualifiedTable().getIntrospectedTableName());
		answer.addElement(new TextElement(sb.toString()));

		answer.addElement(new TextElement("<where>"));
		
		for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
			XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
			sb.setLength(0);
			sb.append(introspectedColumn.getJavaProperty());
			sb.append(" != null"); //$NON-NLS-1$
			
			if ("VARCHAR".equalsIgnoreCase(introspectedColumn.getJdbcTypeName())) {
				sb.append(" and ");
				sb.append(introspectedColumn.getJavaProperty());
				sb.append(" != '' ");
			}
			isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
			answer.addElement(isNotNullElement);

			sb.setLength(0);
			sb.append(" AND ");
			sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
			sb.append(" = "); //$NON-NLS-1$
			sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
			
			isNotNullElement.addElement(new TextElement(sb.toString()));
		}
		answer.addElement(new TextElement("</where>"));
		
		if (context.getPlugins().sqlMapSelectByPrimaryKeyElementGenerated(answer, introspectedTable)) {
			parentElement.addElement(answer);
		}
	}
}
