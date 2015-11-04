package com.herokuapp.qa.config;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.herokuapp.qa.driver.factory.DriverFactory;

	@Configuration
	@ComponentScan(basePackages = { "com.herokuapp.*" })
	@PropertySource("classpath:test.properties")
	public class TestConfig {
		
		public static final String USER_DIR = "user.dir";
		public static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
		
		@Autowired
		DriverFactory driverFactory;
		
		@Autowired
		private ApplicationContext applicationContext;
		
		@Value("${priceList.path}")
		private String priceListPath;
		
		@Value("${chromeDriver.path}")
		private String chromeDriverPath;

		@Bean
		public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
			return new PropertySourcesPlaceholderConfigurer();
		}
		
		@Bean
		public ApplicationContext applicationContext() {
			return this.applicationContext;
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
