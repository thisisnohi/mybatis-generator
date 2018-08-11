package nohi.tools.mybatis.plugin.table;


import java.text.MessageFormat;
import java.util.List;

import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * Created by nohi on 2018/8/11.
 */
public class TkMyBatis3Impl  extends IntrospectedTableMyBatis3Impl {

	@Override
	protected void calculateJavaModelGenerators(List<String> warnings, ProgressCallback progressCallback) {
		super.calculateJavaModelGenerators( warnings, progressCallback );
	}

	@Override
	protected String calculateMyBatis3XmlMapperFileName() {
		StringBuilder sb = new StringBuilder();
		if (stringHasValue(tableConfiguration.getMapperName())) {
			String mapperName = tableConfiguration.getMapperName();
			int ind = mapperName.lastIndexOf('.');
			if (ind != -1) {
				mapperName = mapperName.substring(ind + 1);
			}
			//支持mapperName = "{0}Dao" 等用法
			sb.append(MessageFormat.format(mapperName, fullyQualifiedTable.getDomainObjectName()));
			sb.append("Mapper.xml"); //$NON-NLS-1$
		} else {
			sb.append(fullyQualifiedTable.getDomainObjectName());
			sb.append("Mapper.xml"); //$NON-NLS-1$
		}
		return sb.toString();
	}

	@Override
	protected void calculateJavaClientAttributes() {
		if (context.getJavaClientGeneratorConfiguration() == null) {
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(calculateJavaClientImplementationPackage());
		sb.append('.');
		sb.append(fullyQualifiedTable.getDomainObjectName());
		sb.append("DAOImpl"); //$NON-NLS-1$
		setDAOImplementationType(sb.toString());

		sb.setLength(0);
		sb.append(calculateJavaClientInterfacePackage());
		sb.append('.');
		sb.append(fullyQualifiedTable.getDomainObjectName());
		sb.append("DAO"); //$NON-NLS-1$
		setDAOInterfaceType(sb.toString());

		sb.setLength(0);
		sb.append(calculateJavaClientInterfacePackage());
		sb.append('.');
		if (stringHasValue(tableConfiguration.getMapperName())) {
			//支持mapperName = "{0}Dao" 等用法
			sb.append(MessageFormat.format(tableConfiguration.getMapperName(), fullyQualifiedTable.getDomainObjectName()));
			sb.append("Mapper");
		} else {
			sb.append(fullyQualifiedTable.getDomainObjectName());
			sb.append("Mapper"); //$NON-NLS-1$
		}
		setMyBatis3JavaMapperType(sb.toString());

		sb.setLength(0);
		sb.append(calculateJavaClientInterfacePackage());
		sb.append('.');
		if (stringHasValue(tableConfiguration.getSqlProviderName())) {
			//支持mapperName = "{0}SqlProvider" 等用法
			sb.append(MessageFormat.format(tableConfiguration.getSqlProviderName(), fullyQualifiedTable.getDomainObjectName()));
			sb.append("Mapper");
		} else {
			sb.append(fullyQualifiedTable.getDomainObjectName());
			sb.append("SqlProvider"); //$NON-NLS-1$
		}
		setMyBatis3SqlProviderType(sb.toString());
	}




}
