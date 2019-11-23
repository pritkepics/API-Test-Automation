package com.qa.testCases;



import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.qa.base.BaseClass;
import com.qa.utils.CommonMethods;
import com.qa.utils.RestUtils;
import com.qa.utils.TestUtils;

public class AcceptanceTest extends BaseClass {

	
	public static final String promotions="Promotions";
	public static final String sheetName = "AcceptanceTest";
	public static final String name = "Name";
	public static final String gallery = "Gallery";
	public static final String description= "Description";

    BaseClass baseClass;
	/**
	 * Setup block to initialize variables 
	 * and logger.
	 */
	
	@BeforeMethod
	public void setUp() {

		intialize();
		APP_LOGS.info("Setup job completed.");
	}
	
	/**
	 * Fetches the data from excel sheet.
	 * @return 2D array of data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	@DataProvider
	public Object[][] AcceptanceTestDataPro() throws EncryptedDocumentException, IOException {

		Object data[][] = TestUtils.getData(sheetName);
		//APP_LOGS.info("Completed reading excel shet reading operation.");
		return data;
	}

	
	/**
	 * Test to check if the response contains Name = "Carbon credits"
	 * 
	 */

	@Test(dataProvider = "AcceptanceTestDataPro", priority = 1)

	public void validateName(String URL, String k1,String v1,String k2,String v2,String imageValue) {
      
	 	RestUtils.GETAndvalidate(URL,k1,v1);
	 	APP_LOGS.info("validateName completed");
	}

	/**
	 * Test to check if the response contains CanRelist = true
	 */
	
	@Test(dataProvider = "AcceptanceTestDataPro", priority = 2)
	public void validateCanRelist(String URL, String k1,String v1,String k2,String v2,String imageValue) {
		Boolean boolTrue=true;
		if(v2.equalsIgnoreCase("true")) {
			boolTrue =Boolean.parseBoolean(v2);
		}
		
		RestUtils.GETAndvalidate(URL, k2, boolTrue);
        APP_LOGS.info("validateCanRelist completed");
	}

	/**
	 * Test to Check The Promotions element with Name = "Gallery" has a Description
	 * that contains the text "2x larger image"
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */

	@Test(dataProvider = "AcceptanceTestDataPro", priority = 3)
	public void validatePromotions(String URL, String k1,String v1,String k2,String v2, String imageValue) throws JsonParseException, JsonMappingException, IOException {

		List<Map<String, Object>> storedVal = CommonMethods.extractData(URL, promotions);

		for (int i = 0; i < storedVal.size(); i++) {

			if (storedVal.get(i).get(name).equals(gallery)) {

				Assert.assertTrue((storedVal.get(i).get(description).toString().contains(imageValue)));

			}
		}
	   APP_LOGS.info("validatePromotions completed");
	}

}
