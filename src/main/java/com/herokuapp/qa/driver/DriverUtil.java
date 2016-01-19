package com.herokuapp.qa.driver;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.herokuapp.qa.driver.factory.DriverFactory;

@Component
@Scope("prototype")
public class DriverUtil {

	public static final int TIMEOUT = 20;
	public static final int IMPL_TIMEOUT = 50;
	private final static Logger LOGGER = LoggerFactory.getLogger(DriverUtil.class);

	public DriverUtil() {
		//wait = wait(TIMEOUT);
	}

	public void setImpliciteWait(int timeout) {
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	/**
	 * Wait will ignore instances of NotFoundException that are encountered (thrown) by default in the 'until' condition, and immediately propagate all others. You can add more to the ignore list by
	 * calling ignoring(exceptions to add).
	 *
	 * @param DriverFactory.getDriver()
	 *            The WebDriverFactory.getDriver() instance to pass to the expected conditions
	 * @param timeOutInSeconds
	 *            The timeout in seconds when an expectation is called
	 */
	public FluentWait<WebDriver> wait(int timeOutInSeconds) {
		return new WebDriverWait(DriverFactory.getDriver(), timeOutInSeconds).ignoring(
				NoSuchElementException.class,
				StaleElementReferenceException.class);
	}

	/**
	 * Causes the currently executing thread to sleep (temporarily cease execution) for the specified number of milliseconds Does not throw InterruptedException
	 *
	 * @param millis
	 *            the length of time to sleep in milliseconds
	 */
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible. Visibility means that the elements are not only displayed but also have a height and
	 * width that is greater than 0.
	 * <p>
	 * Ignores NoSuchElementException, StaleElementReferenceException.
	 * <p>
	 * Wait will cease after <code>TIMEOUT</code> without throwing an exception
	 *
	 * @param by
	 *            used to find the element
	 */
	public void waitForAll(By by) {
		waitForAll(by, TIMEOUT);
	}

	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible. Visibility means that the elements are not only displayed but also have a height and
	 * width that is greater than 0.
	 * <p>
	 * Ignores NoSuchElementException, StaleElementReferenceException.
	 * <p>
	 * Wait will cease after <code>TIMEOUT</code> without throwing an exception
	 *
	 * @param locator
	 *            used to find the element
	 * @param timeOutInSeconds
	 */
	public void waitForAll(By locator, int timeOutInSeconds) {
		try {
			wait(timeOutInSeconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			LOGGER.warn(String.format("Wait for all elements located by ('%s') timed out after %s / " + e, locator, timeOutInSeconds));
		}
	}

	public void waitAndClick(By locator) {
		try {
			wait(TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			LOGGER.warn(String.format("Wait for element located by ('%s') timed out after %s / " + e, locator, TIMEOUT));
		}
		DriverFactory.getDriver().findElement(locator).click();
	}

	public void waitAndClick(WebElement element) {
		try {
			wait(TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			LOGGER.warn(String.format("Wait for element located by ('%s') timed out after %s / " + e, element, TIMEOUT));
		}
		element.click();
	}

	/**
	 * waits for element to be displayed without throwing an exception.
	 * <p>
	 * clicks, clears, sends keys
	 *
	 * @param locator
	 *            used to find the element
	 * @param text
	 *            to send
	 */
	public void type(By locator, String text) {
		wait(TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
		WebElement element = DriverFactory.getDriver().findElement(locator);
		element.click();
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * waits for element to be displayed without throwing an exception.
	 * <p>
	 * clicks, clears, sends keys
	 *
	 * @param element
	 *            WebElement
	 * @param text
	 *            to send
	 */
	public void type(WebElement element, String text) {
		waitForElementToBeVisible(element);
		element.click();
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * An expectation for checking that an element, known to be present on the DOM of a page, is visible. Visibility means that the element is not only displayed but also has a height and width that
	 * is greater than 0. Ignores NoSuchElementException, StaleElementReferenceException.
	 * <p>
	 * Wait will cease after <code>TIMEOUT</code> without throwing an exception
	 *
	 * @param element
	 *            the WebElement
	 */
	public void waitForElementToBeVisible(WebElement element) {
		waitForElementToBeVisible(element, TIMEOUT);
	}

	/**
	 * An expectation for checking that an element, known to be present on the DOM of a page, is visible. Visibility means that the element is not only displayed but also has a height and width that
	 * is greater than 0. Ignores NoSuchElementException, StaleElementReferenceException.
	 * <p>
	 * Wait will cease after <code>timeOutInSeconds</code> without throwing an exception
	 *
	 * @param element
	 *            the WebElement
	 * @param timeOutInSeconds
	 *            to wait for. Does not throw an exception on timeout.
	 */
	public void waitForElementToBeVisible(WebElement element, int timeOutInSeconds) {
		try {
			wait(timeOutInSeconds).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			LOGGER.warn(String.format("Wait for element ('%s') timed out after %s / " + e, element, timeOutInSeconds));
		}
	}
	
	public Predicate<By> isElementPresentBy = 
			by -> DriverFactory.getDriver().findElements(by).size() > 0;
}