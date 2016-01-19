package com.herokuapp.qa;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.herokuapp.qa.config.TestConfig;
import com.herokuapp.qa.driver.DriverUtil;
import com.herokuapp.qa.driver.factory.DriverFactory;

@ContextConfiguration(classes = { TestConfig.class })
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
		
	@Autowired
	protected DriverUtil driverUtil;
	@Autowired
	protected DriverFactory driverFactory;
	protected FluentWait<WebDriver> wait;
	private String browser = DriverFactory.CHROME;
	private String profile = "qa1";
	
	@BeforeSuite
	@Parameters("profile")
	public void setProfile(@Optional String profile) {
		if( !StringUtils.isBlank(profile)) {
			this.profile = profile;
			System.setProperty(TestConfig.SPRING_PROFILES_ACTIVE, this.profile);
		}
	}
	
	@BeforeClass
	@Parameters({"browser"})
	public void setBrowser(@Optional String browser) {
		if( !StringUtils.isBlank(browser))
			this.browser = browser;
		setUp();
	}

	protected void setUp() {
		driverFactory.setUpDriver(browser);
		wait = driverUtil.wait(DriverUtil.TIMEOUT);	}

	protected void quitDriver() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}
	
	protected WebDriver getDriver() {
		return DriverFactory.getDriver();
	}
	
}
