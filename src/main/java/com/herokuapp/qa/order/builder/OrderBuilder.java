package com.herokuapp.qa.order.builder;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.herokuapp.qa.order.processor.OrderProcessor;
import com.herokuapp.qa.specs.Order;

public class OrderBuilder {
	
	private OrderProcessor orderProcessor;
	private Order order;
	
	public OrderBuilder(WebDriver driver) {
		orderProcessor = new OrderProcessor(driver);
		HashMap<String, String> items = new HashMap<String, String>();
		order = new Order(items);
	}
	
	public OrderBuilder add(String item, String quantity) {
		order.getItems().put(item, quantity);
		return this;
	}
	
	public OrderBuilder selectState (String state) {
		order.setState(state);
		return this;
	}
	
	public void submit() {
		orderProcessor.submitOrder(order);
	}

}
