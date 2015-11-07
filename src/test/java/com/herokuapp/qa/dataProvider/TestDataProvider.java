package com.herokuapp.qa.dataProvider;

import java.util.Properties;

import org.testng.annotations.DataProvider;

import com.herokuapp.qa.BaseTest;
import com.herokuapp.qa.util.PropertiesReader;
import com.herokuapp.qa.util.TaxesReader;

public class TestDataProvider extends BaseTest{
		
	private static Properties properties;
	
	@DataProvider(name = "taxes", parallel = false)
	public static Object[][] taxes() {
		properties = PropertiesReader.getProperties();
		TaxesReader taxesReader = new TaxesReader(
				properties.getProperty("priceList.path"), 
				properties.getProperty("sheet.taxes"));
		return taxesReader.getTaxes();
	}
	
}
