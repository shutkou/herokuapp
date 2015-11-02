package com.herokuapp.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.herokuapp.qa.order.builder.OrderBuilder;

public class WelcomePage extends BasePage{

	public WelcomePage(WebDriver driver) {
		super(driver);
	}
	
	public static final String BASE_URL = "https://jungle-socks.herokuapp.com/";
	
	private By orderField = By.cssSelector("td>input");
	private By select = By.cssSelector("select");
	private By checkoutBtn = By.name("commit");
	
	public OrderBuilder placeOrder(String item, String quantity) {
		OrderBuilder orderBuilder = new OrderBuilder(driver);
		return orderBuilder.add(item, quantity);
	}
	
	public void orderItem(String name, String amount) {
		filterRows(hasName.apply(name))
		.map(row -> row.findElement(orderField))
		.findFirst()
		.ifPresent(field -> driverUtil.type(field, amount));
	}
	
	public void orderItem(String name, int amount) {
		orderItem(name, Integer.toString(amount));
	}
	
	public void selectState(String state) {
		Select dropdown = new Select(driver.findElement(select));
		dropdown.selectByValue(state.toUpperCase());
	}

	public By getCheckoutBtn() {
		return checkoutBtn;
	}
	
	public void goTo() {
		if( !isAt() ) {
		driver.navigate().to(BASE_URL);
		}
		waitForPageToLoad();
	}
	
	private boolean isAt() {
		return driverUtil.isElementPresentBy.test(checkoutBtn);
	}

}
