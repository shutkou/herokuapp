package com.herokuapp.qa.page;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.herokuapp.qa.driver.DriverUtil;

public abstract class BasePage {

	protected WebDriver driver;
	protected DriverUtil driverUtil;
	protected FluentWait<WebDriver> wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.driverUtil = new DriverUtil(driver);
		this.wait = driverUtil.wait(DriverUtil.TIMEOUT);
	}
	
	protected By row = By.cssSelector(".line_item");
	protected By name = By.cssSelector("td:nth-child(1)");
	protected By price = By.cssSelector("td:nth-child(2)");
	protected By quantity = By.cssSelector("td:nth-child(3)");
	
	public List<? extends WebElement> getRows() {
		driverUtil.waitForAll(row, DriverUtil.TIMEOUT);
		return driver.findElements(row);
	};

	public <T> Stream<T> mapRows(Function<WebElement, T> mapper) {
		driverUtil.waitForAll(row, DriverUtil.TIMEOUT);
		return getRows().stream().map(mapper);
	}

	public Stream<? extends WebElement> filterRows(Predicate<WebElement> matcher) {
		driverUtil.waitForAll(row, DriverUtil.TIMEOUT);
		return getRows().stream().filter(matcher);
	}
	
	public HashMap<String, BigDecimal> getActualPrices() {
		waitForPageToLoad();
		HashMap<String, BigDecimal> actualPrices = new HashMap<String, BigDecimal>();
		getRows().forEach(row -> actualPrices.put(nameMapper(row), priceMapper(row)));
		return actualPrices;
	}
	
	public HashMap<String, Integer> getActualQuantity() {
		waitForPageToLoad();
		HashMap<String, Integer> actualQuantity = new HashMap<String, Integer>();
		getRows().forEach(row -> actualQuantity.put(nameMapper(row), quantityMapper(row)));
		return actualQuantity;
	}
	
	public Function<String, Predicate<WebElement>> hasName = text ->
	row -> nameMapper(row).equalsIgnoreCase(text);
	
	public String nameMapper(WebElement row) {
		return row.findElement(name).getText();
	}
	
	public BigDecimal priceMapper(WebElement row) {
		String priceAsString = row.findElement(price).getText();
		return new BigDecimal(priceAsString).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public int quantityMapper(WebElement row) {
		String quantityAsString = row.findElement(quantity).getText();
		return Integer.parseInt(quantityAsString);
	}
	
	public void waitForPageToLoad() {
		driverUtil.waitForAll(row);
		driverUtil.waitForAll(name);
		driverUtil.waitForAll(price);
		driverUtil.waitForAll(quantity);
	}
	
				
}
