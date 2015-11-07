package com.herokuapp.qa.config;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
		
		@Autowired
		DriverFactory driverFactory;

		@Bean(name="propertyConfigurer")
		public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
			PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
			propertyConfigurer.setLocation(new ClassPathResource(TEST_PROPERTIES));
			propertyConfigurer.setIgnoreUnresolvablePlaceholders(true);
		    return propertyConfigurer;
		}
		
		@Bean
		public WebDriver driver() {
			driverFactory.setUpDriver(System.getProperty(SPRING_PROFILES_ACTIVE));
			return driverFactory.getDriver();
		}
	
		public static File getClasspathRoot() {
			return new File(System.getProperty(USER_DIR));
		}
}
