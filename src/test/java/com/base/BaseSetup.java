/**************************************** PURPOSE **********************************

 - This class contains the code related to Basic setup of TestSuites such as Instantiating Browser,
 - Launching Browser from selected Configuration, perform Clean Up etc

USAGE
 - Inherit this BaseClass for any TestSuite class. You don't have to write any @Beforeclass and @AfterClass
 - actions in your TestSuite Classes
 
 - Example: 
 --import Com.Base
 --- public class <TestSuiteClassName> extends BaseClass
*/

package com.base;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.testsuite.pc.SuiteConfiguration;

import ru.stqa.selenium.factory.WebDriverPool;


/**
 * Base class for TestNG-based test classes
 */
public class BaseSetup {
	
	Logger log =LogManager.getLogger("BaseSetup");
	
	protected static URL gridHubUrl = null;
	protected static String baseUrl;
	protected static Capabilities capabilities;
	
	protected WebDriver driver;
	
	/**
	 * Getter method for WebDriver
	 * @return driver
	*/
    public WebDriver getDriver() 
    {
        return driver;
    }

    /**
     * 
     * Setter method for WebDriver
     *
     * @param driver
     */
    public void setDriver(WebDriver driver) 
    {
        this.driver = driver;
    }
	
	@BeforeSuite
	public void initTestSuite() throws IOException {
		SuiteConfiguration config = new SuiteConfiguration();
		baseUrl = config.getProperty("site.url");
		if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
			gridHubUrl = new URL(config.getProperty("grid.url"));
		}
		    capabilities = config.getCapabilities();
	}
	
	@BeforeMethod
	public void initWebDriver() {
	    
		log.info("Initiated Webdriver...");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		log.info("Chrome driver location is:" + System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
	    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
	   
	    driver.manage().window().maximize();
	    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		WebDriverPool.DEFAULT.dismissAll();
	}

}
