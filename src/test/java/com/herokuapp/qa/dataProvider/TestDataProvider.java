package com.herokuapp.qa.dataProvider;

import org.testng.annotations.DataProvider;

import com.herokuapp.qa.util.TaxesReader;

public class TestDataProvider {
	
	@DataProvider(name = "taxes")
	public static Object[][] taxes() {
		
		TaxesReader taxesReader = new TaxesReader();
		return taxesReader.getTaxes();
	}
	
}
