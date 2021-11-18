package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import autotesting.pages.Page;

public class HomePage extends Page {

	  @FindBy(how = How.XPATH, using = "/html/body/h1")
	  @CacheLookup
	  public WebElement header;

	  
	  //public WebElement header = driver.findElement(By.xpath("/html/body/h1"));
	  
	  public HomePage(WebDriver webDriver) {
	    super(webDriver);
	  }
	}
