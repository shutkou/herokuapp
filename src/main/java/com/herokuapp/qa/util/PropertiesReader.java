package com.herokuapp.qa.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import com.herokuapp.qa.config.TestConfig;

public class PropertiesReader {

	private static final Properties config;
	private static Logger logger = LoggerFactory.getLogger(PropertiesReader.class);
	
	static {
	  config = new Properties();
	  try {
	    InputStream stream = new DefaultResourceLoader().getResource(
	    		TestConfig.TEST_PROPERTIES).getInputStream();
	    try {
	      config.load(stream);
	    }
	    finally {
	      stream.close();
	    }
	  }
	  catch (Exception ex) {
		  logger.warn("Properties with path {} will not be loaded",
				  TestConfig.TEST_PROPERTIES);
	  }
	}
	
	public static String getPropertyValue(String key){
        return config.getProperty(key);
    }

	public static Properties getProperties() {
		return config;
	}
	

}
