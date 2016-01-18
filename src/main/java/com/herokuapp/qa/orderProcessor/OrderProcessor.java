package com.herokuapp.qa.orderProcessor;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.herokuapp.qa.page.WelcomePage;
import com.herokuapp.qa.spec.Order;

@Component
@Scope("prototype")
public class OrderProcessor {
	
	@Autowired
	private WelcomePage welcomePage;	
	private Order order;
	
	//@Autowired
	public OrderProcessor() {
		HashMap<String, String> items = new HashMap<String, String>();
		order = new Order(items);
	}
	
	public OrderProcessor addItem(String item, String quantity) {
		order.getItems().put(item, quantity);
		return this;
	}
	
	public OrderProcessor addItem(String item, int quantity) {
		return addItem(item, Integer.toString(quantity));
	}
	
	public OrderProcessor setState (String state) {
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
