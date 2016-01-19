package com.herokuapp.qa.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.herokuapp.qa.driver.factory.DriverFactory;

	@Configuration
	@ComponentScan(basePackages = { "com.herokuapp.*" })
	@PropertySource("classpath:" + TestConfig.TEST_PROPERTIES)
	public class TestConfig {
		
		public static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
		public static final String TEST_PROPERTIES = "test.properties";
		public static final String USER_DIR = "user.dir";
		
		@Value("${base.url.qa1}")
		private String baseUrlQA1;
		
		@Value("${base.url.dev}")
		private String baseUrlDev;
		
		@Value("${base.url.prod}")
		private String baseUrlProd;
		
		@Autowired
		DriverFactory driverFactory;

		@Bean(name="propertyConfigurer")
		public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
			PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
			propertyConfigurer.setLocation(new ClassPathResource(TEST_PROPERTIES));
			propertyConfigurer.setIgnoreUnresolvablePlaceholders(true);
		    return propertyConfigurer;
		}
		
		@Bean(name="baseUrl")
		@Profile("qa1")
		public String getUrlQA1() {
			return baseUrlQA1;
		}
		
		@Bean(name="baseUrl")
		@Profile("dev")
		public String getUrlDev() {
			return baseUrlDev;
		}
		
		@Bean(name="baseUrl")
		@Profile("prod")
		public String getUrlProd() {
			return baseUrlProd;
		}
	
		public static File getClasspathRoot() {
			return new File(System.getProperty(USER_DIR));
		}
}
