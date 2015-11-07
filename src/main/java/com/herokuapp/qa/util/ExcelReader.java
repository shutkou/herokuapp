package com.herokuapp.qa.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.herokuapp.qa.config.TestConfig;

public class ExcelReader {
		
	private static Logger logger = LoggerFactory.getLogger(ExcelReader.class);
	private HSSFWorkbook workbook;	
	
	public ExcelReader(String excelPath) {
		try {
		File src = new File(TestConfig.getClasspathRoot() + excelPath);	
		FileInputStream fileInputStream = new FileInputStream(src);
		workbook = new HSSFWorkbook(fileInputStream);
		} catch (Exception e) {
			logger.warn("Excel reader exeption: ", e);
		}
	}
	
	public HSSFSheet getSheet(String sheetName){
		return workbook.getSheet(sheetName);
	}
}
