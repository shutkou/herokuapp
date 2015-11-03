package com.herokuapp.qa.order.builder;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.herokuapp.qa.page.WelcomePage;
import com.herokuapp.qa.spec.Order;

public class OrderBuilder {
	
	private WelcomePage welcomePage;
	private Order order;
	
	public OrderBuilder(WebDriver driver) {
		HashMap<String, String> items = new HashMap<String, String>();
		welcomePage = new WelcomePage(driver);
		order = new Order(items);
	}
	
	public OrderBuilder addItem(String item, String quantity) {
		order.getItems().put(item, quantity);
		return this;
	}
	
	public OrderBuilder addItem(String item, int quantity) {
		return addItem(item, Integer.toString(quantity));
	}
	
	public OrderBuilder setState (String state) {
		order.setState(state);
		return this;
	}
	
	public void submitOrder() {
		welcomePage.waitForPageToLoad();
		order.getItems().forEach(welcomePage :: orderItem);
		welcomePage.selectState(order.getState());
		welcomePage.getCheckoutBtn().click();
	}

}
