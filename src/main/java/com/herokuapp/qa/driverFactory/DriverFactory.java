package com.herokuapp.qa.driverFactory;

import org.openqa.selenium.WebDriver;

import com.herokuapp.qa.driverBuilder.ChromeDriverBuilder;
import com.herokuapp.qa.driverBuilder.FirefoxDriverBuilder;
import com.herokuapp.qa.driverBuilder.WebDriverBuilder;


public class DriverFactory {
	
	public static final String CHROME = "chrome";
	public static final String FIREFOX = "firefox";
	private WebDriverBuilder webdriverBuilder;
	private WebDriver driver;
	
	public void setUpDriver (String browser) {
		if (browser.equalsIgnoreCase(CHROME)){
			webdriverBuilder = new ChromeDriverBuilder();
		}else if(browser.equalsIgnoreCase(FIREFOX)){
			webdriverBuilder = new FirefoxDriverBuilder();
		}
		driver = webdriverBuilder.buildDriver();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
