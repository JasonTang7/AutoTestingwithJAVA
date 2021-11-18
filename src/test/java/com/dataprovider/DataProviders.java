package com.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	@DataProvider(name = "Login")
	public static Object[][] LoginRequired(){

		return new Object[][]{new String[]{"NO"}};
	}

}
