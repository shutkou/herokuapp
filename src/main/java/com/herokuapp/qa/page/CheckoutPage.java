package com.herokuapp.qa.page;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage{

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	private By subtotal = By.id("subtotal");
	private By taxes = By.id("taxes");
	private By total = By.id("total");
	
	public BigDecimal calculateSubTotal() {
		waitForPageToLoad();
		return getRows()
				.stream()
				.map(this :: getRowTotal)
				.reduce(new BigDecimal(0), (a, b) -> a.add(b))
				.setScale(2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getTaxes() {
		return getValueAsBigDecimal(taxes);
	}
	
	public BigDecimal getTotal() {
		return getValueAsBigDecimal(total);
	}
	
	public BigDecimal getSubTotal() {
		return getValueAsBigDecimal(subtotal);
	}
	
	public void waitForPageToLoad() {
		super.waitForPageToLoad();
		driverUtil.waitForAll(total);
		driverUtil.waitForAll(taxes);
		driverUtil.waitForAll(subtotal);
	}
	
	private BigDecimal getRowTotal(WebElement row) {
		BigDecimal price = priceMapper(row);
		return price.multiply(new BigDecimal(quantityMapper(row)))
				.setScale(2, RoundingMode.HALF_UP);
	}
	
	private BigDecimal getValueAsBigDecimal(By by) {
		String totalAsString = driver.findElement(by).getText()
				.replace("$", "");
		return new BigDecimal(totalAsString).setScale(2);
	}

}
