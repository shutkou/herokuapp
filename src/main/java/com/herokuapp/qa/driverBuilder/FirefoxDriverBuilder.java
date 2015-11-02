package com.herokuapp.qa.driverBuilder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverBuilder implements WebDriverBuilder{

	@Override
	public WebDriver buildDriver() {
		return new FirefoxDriver();
	}

}
