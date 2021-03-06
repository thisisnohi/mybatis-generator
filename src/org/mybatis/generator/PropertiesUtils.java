package org.mybatis.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.mybatis.generator.logging.Log;
import org.mybatis.generator.logging.LogFactory;

public class PropertiesUtils {
	private Log log = LogFactory.getLog(getClass());
	private static final String configfile = "config.properties";

	private Properties properties = new Properties();
	private static PropertiesUtils instance;

	private PropertiesUtils() {
	}

	public static PropertiesUtils getInstance() {
		if (null == instance) {
			instance = new PropertiesUtils();
			instance.init();
		}
		return instance;
	}

	public void init() {
		try {
		  InputStream rs = null;
		  String configFileName = System.getProperty("user.dir")+ File.separator+ configfile;
		  File file = new File(configFileName);
		  if (file.exists()){
			System.out.println("加载:" + file.getAbsolutePath() );
			rs = new FileInputStream(new File(configFileName));
		  }else{
			rs = Thread.currentThread().getContextClassLoader().getResourceAsStream(configfile);
		  }
		  properties.load(rs);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public String getConfigValue(String key) {
		return properties.getProperty(key);
	}

	public String getDefaultValue(String key, String defaultValue) {
		String rs = getConfigValue(key);
		if (null == rs || "".equals(rs.trim())) {
			return defaultValue;
		}
		return rs;
	}
}
