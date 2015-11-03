package com.herokuapp.qa;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.herokuapp.qa.driver.DriverUtil;
import com.herokuapp.qa.driver.factory.DriverFactory;

public abstract class BaseTest {

	protected static final String PRICE_LIST_PATH = "/testData/priceList.xls";
	protected static final String SHEET_PRICES = "priceList";
	protected static final String SHEET_TAXES = "stateTaxes";
	protected WebDriver driver;
	protected DriverUtil driverUtil;
	protected FluentWait<WebDriver> wait;
	private DriverFactory driverFactory;
	private String browser = DriverFactory.CHROME;

	@BeforeSuite
	@Parameters("browser")
	public void setBrowser(@Optional String browser) {
		if( !StringUtils.isBlank(browser))
			this.browser = browser;
	}

	protected void setUpDriver() {
		driverFactory = new DriverFactory();
		driverFactory.setUpDriver(browser);
		driver = driverFactory.getDriver();
		postDriverInit();
	}

	protected void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	protected void postDriverInit() {
		driverUtil = new DriverUtil(driver);
		wait = driverUtil.wait(DriverUtil.TIMEOUT);
	}
}
