package com.herokuapp.qa.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReader {

	private HSSFWorkbook workbook;	
	
	public ExcelReader(String excelPath) {
		try {
		File src = new File(CommonUtil.getClasspathRoot() + excelPath);	
		FileInputStream fileInputStream = new FileInputStream(src);
		workbook = new HSSFWorkbook(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
			//TODO LOGGER
		}
	}
	
	public HSSFSheet getSheet(String sheetName){
		return workbook.getSheet(sheetName);
	}

}
