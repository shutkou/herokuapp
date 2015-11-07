package com.herokuapp.qa.driver.builder;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.herokuapp.qa.config.TestConfig;

@Component
@Scope("prototype")
public class ChromeDriverBuilder implements WebDriverBuilder{

	@Value("${chromeDriver.path}")
	private String chromeDriverPath;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

	@Override
	public WebDriver buildDriver() {
		setChromeDriverPath();
		setExecutable();
		return new ChromeDriver();
	}
	
	private void setChromeDriverPath(){
		String driverPath = System.getProperty(CHROME_DRIVER_PROPERTY);
		if (!StringUtils.isBlank(driverPath))
			return;
		System.setProperty(CHROME_DRIVER_PROPERTY, TestConfig.getClasspathRoot() + chromeDriverPath);
	}
	
	private void setExecutable() {
		File executable = new File(TestConfig.getClasspathRoot() + chromeDriverPath);
		if (!executable.canExecute()) {
	         executable.setExecutable(true);
	      }
	}

}
