package com.base;

import java.io.IOException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.testsuite.ecapi.SuiteConfiguration;

public class ApiBaseSetup {
	
	Logger log =LogManager.getLogger("ApiBaseSetup");
	
	protected static URL gridHubUrl = null;
	protected static String apiBaseUrl;
	
	@BeforeSuite
	public void initTestSuite() throws IOException {
		SuiteConfiguration config = new SuiteConfiguration();
		apiBaseUrl = config.getProperty("ecapi.url");
		log.info("apiBaseUrl:" + apiBaseUrl);
		if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
			gridHubUrl = new URL(config.getProperty("grid.url"));
		}
	}
	
	@BeforeMethod
	public void initAPI() {
	    
		log.info("Initiated api test..." + apiBaseUrl);
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		
	}

}
