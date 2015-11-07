package com.herokuapp.qa.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;

public class TaxesReader {
	
	private static final int NAME_COLOMN = 0;
	private static final int TAX_COLOMN = 1;
	
	private ExcelReader excelReader;
	private HSSFSheet sheet;
	private Object[][] taxes;
	
	public TaxesReader(String exlPath, String sheetName) {
		excelReader = new ExcelReader(exlPath);
		sheet = excelReader.getSheet(sheetName);
	}
	
	public Object[][] getTaxes() {
		int rowsCount = sheet.getLastRowNum();
		taxes = new Object[rowsCount + 1 ][2];
		sheet.forEach(this :: putTax);
		return taxes;
	}
	
	private void putTax(Row row){
		String name = row.getCell(NAME_COLOMN).getStringCellValue();
		String taxAsString = row.getCell(TAX_COLOMN).getStringCellValue();
		taxes[row.getRowNum()][0] = name;
		taxes[row.getRowNum()][1] = taxAsString;
	}
}
