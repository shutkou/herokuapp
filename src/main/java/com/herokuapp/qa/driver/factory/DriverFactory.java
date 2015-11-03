package com.herokuapp.qa.driver.factory;

import org.openqa.selenium.WebDriver;

import com.herokuapp.qa.driver.builder.ChromeDriverBuilder;
import com.herokuapp.qa.driver.builder.FirefoxDriverBuilder;
import com.herokuapp.qa.driver.builder.WebDriverBuilder;


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
