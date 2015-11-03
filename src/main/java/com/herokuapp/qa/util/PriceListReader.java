package com.herokuapp.qa.util;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;

public class PriceListReader {
	
	private static final int NAME_COLOMN = 0;
	private static final int PRICE_COLOMN = 1;
	private static final int QUANTITY_COLOMN = 2;
	
	private ExcelReader excelReader;
	private HSSFSheet sheet;
	private HashMap<String, BigDecimal> prices;
	private HashMap<String, Integer> stockQuantity;
	
	public PriceListReader(String exlPath, String sheetName) {
		excelReader = new ExcelReader(exlPath);
		sheet = excelReader.getSheet(sheetName);
	}
	
	public HashMap<String, BigDecimal> getPrices(){
		prices = new HashMap<String, BigDecimal>();
		sheet.forEach(this :: putPrice);
		return prices;
	}
	
	public HashMap<String, Integer> getInventoryQuantity(){
		stockQuantity = new HashMap<String, Integer>();
		sheet.forEach(this :: putStockQuantity);
		return stockQuantity;
	}
	
	private void putPrice(Row row){
		String name = row.getCell(NAME_COLOMN).getStringCellValue();
		String priceAsString = row.getCell(PRICE_COLOMN).getStringCellValue();
		prices.put(name, new BigDecimal(priceAsString));
	}
	
	private void putStockQuantity(Row row){
		String name = row.getCell(NAME_COLOMN).getStringCellValue();
		String priceAsString = row.getCell(QUANTITY_COLOMN).getStringCellValue();
		stockQuantity.put(name, Integer.parseInt(priceAsString));
	}
}
