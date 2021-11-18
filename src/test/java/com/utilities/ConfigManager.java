package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.convert.ListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ConfigManager {
	private Properties properties = new Properties();	
	private String configFileName;
	Logger log =LogManager.getLogger("ConfigManager");
	private String fileSeperator = System.getProperty("file.separator");
	
	//The default constructor initializes and reads the key values from sys.properties file
	public ConfigManager()
	{
		configFileName = "sys";
	}
	
	//The parameterized constructor when supplied with "app" as configFile name initializes and 
	//reads the key values from app.properties 
	public ConfigManager(String configname)
    {
		configFileName = configname;
    }
	
	/**
	 * Returns the value of given property from either sys.properties or app.properties file  
	 * @param key - ConfigParamvalue that requires to be returned from Config.properties file
	 * @return - return ConfigValue
	 */
	public String getProperty(String key)
	{
		String value ="";
        if(key!="")
        {
			loadProperties();
			try
			{
				if(!properties.getProperty(key).trim().isEmpty())
					value = properties.getProperty(key).trim();
			}
			catch(NullPointerException e)
			{
				Assert.fail("Key - '"+key+"' does not exist or not given a value in "+configFileName+".properties file \n"+UtilityMethods.getStackTrace());
			}
        }
        else
        {
            log.error("key cannot be null.. ");
            Assert.fail("key cannot be null.. ");                      
        }
        return value;
    }
	
	/**
	 * 
	 * This method is sued to load properties file that has to be accessed
	 *
	 */
	private void loadProperties()
	{
		FileInputStream fis;
		try 
		{
			fis = new FileInputStream(getConfigFilePath(configFileName));
			properties.load(fis);
			fis.close();
		} 
		catch (FileNotFoundException e) 
		{
			log.error("Cannot find configuration file - "+configFileName+".properties"+" at "+getConfigFilePath(configFileName));
			Assert.fail("Cannot find configuration file - "+configFileName+".properties"+" at "+getConfigFilePath(configFileName));
		} 
		catch (IOException e) 
		{
			log.error("Cannot read configuration file - "+" at "+getConfigFilePath(configFileName));
			Assert.fail("Cannot read configuration file - "+" at "+getConfigFilePath(configFileName));
		}
	}
	
	/**
	 * This method helps to write any new key value pairs to app.properties file. 
	 * @param sKey
	 * @param sData
	 * @throws Exception
	 */
//	public void writeProperty(String sKey,String sData)
//	{	
//		try 
//		{
//			PropertiesConfiguration config = null;
//			config = new PropertiesConfiguration(getConfigFilePath(configFileName));
//
//			config.setProperty(sKey, sData);
//			config.getLayout();
//			config.save();
//		} 
//		catch (ConfigurationException e)
//		{
//        	log.error("cannot write to properties file..."+e.getMessage()+e.getStackTrace());
//        	Assert.fail("cannot write to properties file..."+e.getMessage()+e.getStackTrace());
//		}
//
//	}
	
	
	public void writeProperty(String sKey,String sData)
	{	
		
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.properties()
		        .setFileName(getConfigFilePath(configFileName))
		        .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));

		try {
			Configuration config = builder.getConfiguration();
			config.setProperty(sKey, sData);
			//config.getLayout();
			builder.save();
			log.info("----Write new property success!");
		} catch (ConfigurationException e)
		{
        	log.error("cannot write to properties file..."+e.getMessage()+e.getStackTrace());
        	Assert.fail("cannot write to properties file..."+e.getMessage()+e.getStackTrace());
		}
		
		
		
		/*
		File file = new File(getConfigFilePath(configFileName));
		ListDelimiterHandler delimiter = new DefaultListDelimiterHandler(',');

		PropertiesBuilderParameters propertyParameters = new Parameters().properties();
		
		propertyParameters.setFile(file);
		propertyParameters.setThrowExceptionOnMissing(true);
		propertyParameters.setListDelimiterHandler(delimiter);

		FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
				PropertiesConfiguration.class);

		builder.configure(propertyParameters);
		
		try 
		{
			PropertiesConfiguration config = null;
			config = builder.getConfiguration();
			config.setProperty(sKey, sData);
			config.getLayout();
		} 
		catch (ConfigurationException e)
		{
        	log.error("cannot write to properties file..."+e.getMessage()+e.getStackTrace());
        	Assert.fail("cannot write to properties file..."+e.getMessage()+e.getStackTrace());
		}*/

	}
	
	/**
	 * 
	 * This method is used to get the path of specified properties file name
	 *
	 * @param File , Need to pass the name of properties file
	 * @return , returns the file path of the specified properties file
	 */
	public String getConfigFilePath(String File)
	{
		String configFilePath;
		configFilePath = getConfigFolderPath()+fileSeperator+File.toLowerCase()+".properties";
		return configFilePath;	
	}
	
	/**
	 * 
	 * This method is used to get the location of 'ConfigFiles' folder
	 *
	 * @return, Returns the path of 'ConfigFiles' folder
	 */
	public String getConfigFolderPath()
	{
		return System.getProperty("user.dir")+fileSeperator+"ConfigFiles";
	}

}
