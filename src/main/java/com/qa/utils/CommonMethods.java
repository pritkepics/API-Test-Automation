package com.qa.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class CommonMethods extends BaseClass {

	/**
	 * Converts raw data to xml
	 * @param res, the http response
	 * @return returns xml data
	 */
	
	public static XmlPath rawToXml(Response res) {
		XmlPath xml = null;
		try {
			String resp=res.asString();
		    xml=new XmlPath(resp);
		    return xml;
		}
       catch(Exception e) {
    	   APP_LOGS.error(e.getMessage());
    	   e.printStackTrace();
       }
		
	  return xml ;
	}
	
	/**
	 * Converts raw data to json format
	 * @param res, the http response
	 * @return , returns json data
	 */
	
   public static JsonPath rawToJson(Response res) {
	   JsonPath json=null;
	   
	   try {
	    String resp=res.asString();
	    json=new JsonPath(resp);
	    return json;
	    }
	   catch(Exception e) {
		   APP_LOGS.error(e.getMessage());
		   e.printStackTrace();
	   }
	   return json;
   }
   
   /**
	 * This method is used for parsing the response.
	 * 
	 * @param URL ,this is the url of the application for which the response needs
	 *            to be parsed.
	 * @param     element, this is the element in response which contains the
	 *            <key,value> pairs of data.
	 * @return, the method returns a list of Maps of type <String,Object>
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> extractData(String URL, String element) {
		List<Map<String, Object>> storedVal = new ArrayList<>();

		try {
			Response res = RestAssured.given().get(URL);

			ObjectMapper objectMApper = new ObjectMapper();
			Object resObj = objectMApper.readValue(res.getBody().asString(), Object.class);

			List<Map<String, Object>> resList = new ArrayList<>();
			resList.add((Map<String, Object>) resObj);

			for (int i = 0; i < resList.size(); i++) {

				storedVal.addAll((Collection<? extends Map<String, Object>>) resList.get(i).get(element));

			}

		}

		catch (JsonParseException e) {
			APP_LOGS.error(e.getMessage());
			e.printStackTrace();

		} catch (JsonMappingException e) {
			APP_LOGS.error(e.getMessage());
			e.printStackTrace();

		} catch (IOException e) {
			APP_LOGS.error(e.getMessage());
			e.printStackTrace();
		}
		return storedVal;
	}
	
}

