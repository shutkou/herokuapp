package com.herokuapp.qa.order.processor;

import org.openqa.selenium.WebDriver;

import com.herokuapp.qa.driverFactory.DriverUtil;
import com.herokuapp.qa.page.WelcomePage;
import com.herokuapp.qa.specs.Order;

public class OrderProcessor {

	private WelcomePage welcomePage;
	private DriverUtil driverUtil;
	
	public OrderProcessor(WebDriver driver) {
		driverUtil = new DriverUtil(driver);
		welcomePage = new WelcomePage(driver);
	}
	public void submitOrder(Order order) {
		order.getItems().forEach(welcomePage :: orderItem);
		welcomePage.selectState(order.getState());
		driverUtil.waitAndClick(welcomePage.getCheckoutBtn());
	}
}
		