package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class BaseClass {

	public static Properties prop = null;
	public static Logger APP_LOGS = null;

	public BaseClass() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "//src//main//java//com//qa//config//config.properties");

			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void intialize() {

		APP_LOGS = LogManager.getLogger(BaseClass.class);
	}

}
