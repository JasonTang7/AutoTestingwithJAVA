package com.listener;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebListener implements WebDriverEventListener 
{
	private Logger log = LogManager.getLogger("Weblistener");
    
	
	//Constructor
	public WebListener(){}		 
 
    public void beforeNavigateTo(String url, WebDriver driver)
    {
    	log.info("Before navigating to url printing the browser associated capabilities :"+url);
    }
    
    public void afterNavigateTo(String url, WebDriver driver)
    {
    	log.info("WebDriver has navigated to:'"+url+"'");    		
    }
    
    public void beforeChangeValueOf(WebElement element, WebDriver driver)
    {
    	log.info("Value of the:"+ElementValue(element)+" before any changes made");
    }
 
    public void afterChangeValueOf(WebElement element, WebDriver driver)
    {
    	log.info("Value of element after change: "+ElementValue(element));
    }
    
    public void beforeClickOn(WebElement element, WebDriver driver)
    {
    	log.info("Trying to click on: "+ElementValue(element));
 
    }
    
    public void afterClickOn(WebElement element, WebDriver driver)
    {
    	log.info("Clicked on: "+ElementValue(element));    	    	
    }
 
    public void beforeNavigateBack(WebDriver driver)
    {
    	log.info("Navigating back to previous page");
    }
    
    public void afterNavigateBack(WebDriver driver)
    {
    	log.info("Navigated back to previous page");
    }
    
    public void beforeNavigateForward(WebDriver driver)
    {
    	log.info("Navigating forward to next page");
    }
    
    public void afterNavigateForward(WebDriver driver)
    {	
    	log.info("Navigated forward to next page");
    }
    
    
    public void onException(Throwable error, WebDriver driver)
    {
        if (error.getClass().equals(NoSuchElementException.class))
            System.out.print("...");  
        else
        	log.error("WebDriver error:", error);
    }    
 
    /*
     * non overridden methods of WebListener class
     */
    public void beforeScript(String script, WebDriver driver){}

    public void afterScript(String script, WebDriver driver){}
    
    public void beforeFindBy(By by, WebElement element, WebDriver driver){}

    public void afterFindBy(By by, WebElement element, WebDriver driver){}
 
    
	public static String ElementValue(WebElement element)
	{
		String sValue = element.toString();
		int iStartindex = sValue.indexOf(">");
		int iEndindex = sValue.lastIndexOf("]");
		String sElementValue = sValue.substring(iStartindex+1,iEndindex);		
		return sElementValue;
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}
         
 
}

