package com.qa.utils;

import io.restassured.RestAssured;
import static org.hamcrest.CoreMatchers.equalTo;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.qa.base.BaseClass;

public class RestUtils extends BaseClass {
	/**
	 * This method is use to validate the value of a "key" which is returned by
	 * "GET" method, the value of the key is expected to be String type.
	 * 
	 * @param URL, the URL of the application which needs to be tested.
	 * @param key, the name of the key which needs to be validated.
	 * @param expectedVal,the expected value of the key,which is String type
	 */

	public static void GETAndvalidate(String URL, String key, String expectedVal) {
		try {
			RestAssured.given().get(URL).then().assertThat().body(key, equalTo(expectedVal));
		} catch (Exception e) {
		    APP_LOGS.error("Exception occurred,could not validate :" +e.getMessage());
			e.printStackTrace();
		}

	}
	/**
	 * Overloaded method
     * This method is use to validate the value of a "key" which is returned by
	 * "GET" method, the value of the key is expected to be String type.
	 * 
	 * @param URL, the URL of the application which needs to be tested.
	 * @param key, the name of the key which needs to be validated.
	 * @param expectedVal: boolean
	 */
	
	public static void GETAndvalidate(String URL, String key, Boolean expectedVal) {
		try {
			RestAssured.given().get(URL).then().assertThat().body(key, equalTo(expectedVal));
		} catch (Exception e) {
			APP_LOGS.error(e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * This method is use to validate the value of a "key" which is returned by
	 * "GET" method, the value of the key is expected to be Boolean type.
	 * 
	 * @param URL,the URL of the application which needs to be tested.
	 * @param key,the name of the key which needs to be validated
	 * @param expectedVal,the expected value of the key,which is Boolean type
	 */

	public static void validateGET(String URL, String key, Boolean expectedVal) {

		try {
			RestAssured.given().get(URL).then().assertThat().body(key, equalTo(expectedVal));
		} catch (Exception e) {
			APP_LOGS.error(e.getMessage());
			e.printStackTrace();
		}
	}

}
