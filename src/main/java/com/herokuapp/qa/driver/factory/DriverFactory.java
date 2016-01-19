package com.herokuapp.qa.driver.factory;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.herokuapp.qa.driver.builder.ChromeDriverBuilder;
import com.herokuapp.qa.driver.builder.FirefoxDriverBuilder;
import com.herokuapp.qa.driver.builder.WebDriverBuilder;

@Component
public class DriverFactory {
	
	public static final String CHROME = "chrome";
	public static final String FIREFOX = "firefox";
	private WebDriverBuilder webdriverBuilder;
	@Autowired
	private ChromeDriverBuilder chromeDriverBuilder;
	@Autowired
	private FirefoxDriverBuilder firefoxDriverBuilder;
	private static InheritableThreadLocal<WebDriver> driver = new InheritableThreadLocal<>();
	
	public void setUpDriver (String browser) {
		if (browser.equalsIgnoreCase(CHROME)){
			webdriverBuilder = chromeDriverBuilder;
		}else if(browser.equalsIgnoreCase(FIREFOX)){
			webdriverBuilder = firefoxDriverBuilder;
		}
		driver.set(webdriverBuilder.buildDriver());
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
}
