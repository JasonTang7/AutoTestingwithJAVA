package com.testsuite.pc;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseSetup;
import com.pages.HomePage;



public class HomePageTest extends BaseSetup{
	
	Logger log =LogManager.getLogger("HomePageTest");
	  private HomePage homepage;

	  @BeforeMethod
	  public void initPageObjects() {
	    homepage = PageFactory.initElements(driver, HomePage.class);
	  }

	  @Test
	   public void loadYamiURL()
	   {
		  driver.get(baseUrl);
		  String h1Text = homepage.header.getAttribute("textContent");
		  log.info("h1Text:" + h1Text);
	    
		  Assert.assertTrue("".equals(homepage.header.getText()));
	   }
		  
	  @Test(dependsOnMethods = {"loadYamiURL"})
	  public void testHomePageHasAHeader() {
	
		   
	    String h1Text = homepage.header.getAttribute("textContent");
	    log.info("h1Text:" + h1Text);
	    

	    Set<Cookie> cookiesList =  driver.manage().getCookies();
	    for(Cookie getcookies :cookiesList) {
	    	
	    	//System.out.println(getcookies);
	    	
	    	if(getcookies.getName().equalsIgnoreCase("YMB_TK"))
	    	{
	    		//System.out.println(getcookies.getValue());
	    		
//	    		driver.manage().deleteCookie(getcookies);
//	    		 
//	    		Cookie cname = new Cookie("myCookie", "12345678999");
//	    	    driver.manage().addCookie(cname);
	    	        
	    		//break;
	    	}
	        
	    }
	    
	    
	    Assert.assertTrue("".equals(homepage.header.getText()));
	  }

}
