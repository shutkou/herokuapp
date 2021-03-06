package com.herokuapp.qa.spec;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Order {
	
	private HashMap <String, String> items;
	private String state;
	
	public Order(HashMap <String, String> items, String state) {
		this.items = items;
		this.state = state;
	}

	public Order(HashMap<String, String> items) {
		this.items = items;
	}

	public HashMap<String, String> getItems() {
		return items;
	}

	public void setItems(HashMap<String, String> items) {
		this.items = items;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	

}
