package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.base.BaseClass;

public class TestUtils extends BaseClass{

	
		
		
		public static String testDataSheetPath = System.getProperty("user.dir")+prop.getProperty("testDataSheet") ;
		
		static Workbook book;
		static Sheet sheet ;
		
		
		/**
		 *  Reads xls file, to get test data
		 * @param tabName
		 * @return 2D array of data 
		 */
		
	  public static Object[][] getData(String tabName) {
			
			
			FileInputStream file=null;
			try {
				file= new FileInputStream(testDataSheetPath);
			} catch (FileNotFoundException e) {
				
				APP_LOGS.error("Couldn't find the specified file :: "+e);
			}
			
			try {
				book = WorkbookFactory.create(file);
			} catch (EncryptedDocumentException e) {
				
				APP_LOGS.error("Encrypted Document Exception ::"+ e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				
				APP_LOGS.error("IOException found ::"+e.getMessage());
				e.printStackTrace();
			}
			sheet = book.getSheet(tabName);
			
			Object [][]data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			   for(int i=0;i<sheet.getLastRowNum();i++) {
				   for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					   
					   data[i][j]=sheet.getRow(i+1).getCell(j).toString();
					   }
			   }
			return data;
		}
	  

		
	} 

