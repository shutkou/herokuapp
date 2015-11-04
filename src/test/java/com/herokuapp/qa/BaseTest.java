package com.herokuapp.qa;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.herokuapp.qa.config.TestConfig;
import com.herokuapp.qa.driver.DriverUtil;
import com.herokuapp.qa.driver.factory.DriverFactory;

@ContextConfiguration(classes = { TestConfig.class })
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

	protected static final String PRICE_LIST_PATH = "/testData/priceList.xls";
	protected static final String SHEET_PRICES = "priceList";
	protected static final String SHEET_TAXES = "stateTaxes";
	
	public static ApplicationContext _applicationContext;
	
	@Autowired
	protected WebDriver driver;
	
	@Autowired
	protected DriverUtil driverUtil;
	protected FluentWait<WebDriver> wait;
	private String browser = DriverFactory.CHROME;

	@BeforeSuite
	@Parameters("browser")
	public void setBrowser(@Optional String browser) {
		if( !StringUtils.isBlank(browser))
			this.browser = browser;
		System.setProperty(TestConfig.SPRING_PROFILES_ACTIVE, this.browser);
	}

	protected void setUp() {
		_applicationContext = this.applicationContext;
		wait = driverUtil.wait(DriverUtil.TIMEOUT);	}

	protected void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
	
}
