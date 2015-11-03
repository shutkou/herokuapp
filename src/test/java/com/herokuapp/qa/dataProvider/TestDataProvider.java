package com.herokuapp.qa.dataProvider;

import org.testng.annotations.DataProvider;

import com.herokuapp.qa.BaseTest;
import com.herokuapp.qa.util.TaxesReader;

public class TestDataProvider extends BaseTest{
	
	@DataProvider(name = "taxes", parallel = false)
	public static Object[][] taxes() {
		
		TaxesReader taxesReader = new TaxesReader(PRICE_LIST_PATH, SHEET_TAXES);
		return taxesReader.getTaxes();
	}
	
}
