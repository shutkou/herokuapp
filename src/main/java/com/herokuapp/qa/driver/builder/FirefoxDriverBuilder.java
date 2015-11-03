package com.herokuapp.qa.driver.builder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FirefoxDriverBuilder implements WebDriverBuilder{

	@Override
	public WebDriver buildDriver() {
		return new FirefoxDriver();
	}

}
