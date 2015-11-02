package com.herokuapp.qa.driverBuilder;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.herokuapp.qa.util.CommonUtil;

public class ChromeDriverBuilder implements WebDriverBuilder{

	private static final String CHROME_DRIVER_PATH = "/drivers/chromedriver";
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
		System.setProperty(CHROME_DRIVER_PROPERTY, CommonUtil.getClasspathRoot() + CHROME_DRIVER_PATH);
	}
	
	private void setExecutable() {
		File executable = new File(CommonUtil.getClasspathRoot() + CHROME_DRIVER_PATH);
		if (!executable.canExecute()) {
	         executable.setExecutable(true);
	      }
	}

}
