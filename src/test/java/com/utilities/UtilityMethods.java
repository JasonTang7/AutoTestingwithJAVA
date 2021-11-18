package com.utilities;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.apache.commons.io.FileUtils;

import nl.flotsam.xeger.Xeger;

public class UtilityMethods {

	static ConfigManager sys = new ConfigManager();
	static Logger log =LogManager.getLogger("UtilityMethods");
	
	
	static Thread thread;

	/**
	 * Purpose - to get the system name
	 * @return - String (returns system name)
	 */
    public static String machineName()
    {
    	String sComputername = null;
    	try 
    	{
			sComputername=InetAddress.getLocalHost().getHostName();
		} 
    	catch (UnknownHostException e) 
    	{
			log.error("Unable to get the hostname..."+ e.getCause());
		}
		return sComputername;
    }
    
    /**
     * TODO To get the entire exception stack trace
     * 
     * @return returns the stack trace
     */
    public static String getStackTrace()
    {
		String trace = "";
		int i;
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for(i=2;i<stackTrace.length;i++)
		{
			trace = trace+"\n"+stackTrace[i];
		}
		return trace;
    }

    

    /**
     * Purpose - to get current date and time
     * @return - String (returns date and time)
     */
    public static String getCurrentDateTime()
    {
    	Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
        String dateNow = formatter.format(currentDate.getTime());
        return dateNow;    	
    }
    
    /**
	 * Purpose - To convert given time in "yyyy-MM-dd-HH:mm:ss" to IST time
	 * @returns date in String format 
	 * @throws Exception
	 */
	
    public static String convertToISTTime(String origTime) 
	{

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        TimeZone obj = TimeZone.getTimeZone("GMT");
        formatter.setTimeZone(obj);
        try 
        {
			Date date = formatter.parse(origTime);
			formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
			//System.out.println(date);
			return formatter.format(date);
		} catch (ParseException e) {
			log.error("Cannot parse given date .." + origTime);
			log.info("returning current date and time .." + origTime);
		}
		return getCurrentDateTime();
    }    

    /**
     * Purpose - to display message box
     * @param infoMessage
     * @param location
     */
    public static void infoBox(String infoMessage, String location)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + location, JOptionPane.INFORMATION_MESSAGE);
    }

    
    /**
     * Purpose - to stop the execution
     * @param suspendRunImagePath
     * @throws Exception
     */
    public static void suspendRun(String suspendRunImagePath) throws Exception
    {
    	Assert.fail("TEST RUN HAS BEEN SUSPENDED");
    }
    
    

    /**
     * Purpose - to generate the random number which will be used while saving a screenshot
     * @return - returns a random number
     */
 	public static int getRandomNumber()
 	{
 		Random rand = new Random();
 		int numNoRange = rand.nextInt();
 		return numNoRange;
     }
 	
 	/**
 	 * TODO Typecasts the wait time values from String to integer
 	 *
 	 * @param WaitTime
 	 * @return returns value of wait time in integer
 	 * @throws Exception
 	 */
 	public static int getWaitTime(String WaitType)
 	{
 		int iSecondsToWait = 15;
 		String wait;
 		if(WaitType!=null&&!WaitType.equalsIgnoreCase(""))
 		{
 			wait = sys.getProperty(WaitType);
 		}
 		else
 		{
 			log.error("WaitType cannot be empty...defaulting to MEDIUM WAIT");
 			wait = sys.getProperty("WAIT.MEDIUM");
 		}
 		try
 		{
 			iSecondsToWait = Integer.parseInt(wait);
 		}
 		catch(NumberFormatException e)
 		{
 			log.error("Please check the config file. Wait values must be a number...defaulting to 15 seconds");
 		} 		
 		return iSecondsToWait;		
 	}
 	
 	



	/**
	 * Method: To get caller method and class names
	 */
/*	public static void preserveMethodName()
	{
		Throwable t = new Throwable(); 
		StackTraceElement[] elements = t.getStackTrace();
		String callerClassName = elements[1].getClassName();
		String callerMethodName = elements[1].getMethodName(); 
		String sMethod = "\"" + callerMethodName + "\"" + " method from " + callerClassName + " class";
		ReportHelper.setsMethodName(sMethod);		
	}*/
	
	/**
	 * 
	 * TODO method to run balloon popup process/method in a separate thread.
	 *
	 */
	public static void currentRunningTestCaseBalloonPopUp(final String sTestName)
	{
		thread = new Thread() 
		{
			 public void run()
			 {
				BalloonPopUp.currentRunningTestCase(sTestName);
			 }			
		};
		thread.start();
	}
	
	/**
	 * 
	 * TODO method to verify if the balloon pop process/method is ended or not.
	 */
	public static void verifyPopUp()
	{
		try 
		{
			thread.join();
		} catch (InterruptedException e) {
			log.error("balloon popup thread Interrupted"+e.getStackTrace());
		} 
	}

	
	/**
	 * 
	 * TODO Gives the name of operating system your are currently working on
	 * 
	 * @return returns the OS name
	 */
	public static String getOSName()
	{
		return System.getProperty("os.name");
	
	}	
	
	/**
	 * 
	 * TODO Gives the seperator value according to Operation System
	 * 
	 * @return returns the seperator with respect to Operation System
	 */
	public static String getFileSeperator()
	{		
		return System.getProperty("file.separator");
	}

	public static String generateStringFromRegEx(String regEx)
	{
		Xeger generator = new Xeger(regEx);
		return generator.generate();
	}
	
	/**
	 * 
	 * This method is used to delete all files and folders from specified directory path
	 *
	 * @param sFolderPath , Need to pass the fodler.directory path
	 */
	public void deleteAllExistingFilesOrFoldersFromSpecifiedDirectory(String sFolderPath)
	{
		File folder = new File(sFolderPath);
		try
		{
			FileUtils.cleanDirectory(folder);
			log.info("All files/Folders from specified folder is deleted successfully:"+sFolderPath);
		} 
		catch (IOException exception)
		{
			log.error("Unable to delete files/Folders from specified folder:"+sFolderPath);
			Assert.fail("IO Exception occured while trying to delete files/Fodlers from specified folder, Please close the files if they are opened: "+exception.getMessage());
		}
		catch (Exception exception)
		{
			log.error("Unable to delete files/Folders from specified folder:"+sFolderPath);
			Assert.fail("Some Exception occured while trying to delete files/Fodlers from specified folder, Please close the files if they are opened: "+exception.getMessage());
		}
	}
	
	public static enum Mode {
	    ALPHA, ALPHANUMERIC, NUMERIC 
	}
	
	public static String generateRandomString(int length, Mode mode) {

		StringBuffer buffer = new StringBuffer();
		String characters = "";

		switch(mode){
		
		case ALPHA:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;
		
		case ALPHANUMERIC:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;
	
		case NUMERIC:
			characters = "1234567890";
		    break;
		}
		
		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

	public static void keysPress(String keys) {
		for(int i=0;i<keys.length();i++)
		{
			//System.out.println(keys.charAt(i));
			
			if(keys.charAt(i) == '@')
			{
				keyPress('@');				
			}
			else
			{
				keyPress((int) keys.charAt(i));				
			}
			
		}
	}

	public static void keyPress(int key) {
		
		if(key>96 && key < 123)
		{
			key = key - 32;
		}
		//System.out.println(key);
		
		try {
			Robot robot;
			robot = new Robot();
			robot.setAutoDelay(100);
			robot.keyPress(key);  // press arrow down key of keyboard to 
		    robot.keyRelease(key);
		    robot.waitForIdle();	
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // Robot class throws AWT Exception	
	}
	
	public static void keyPress(char characterKey){

	    switch (characterKey){
	        case '!': ShiftnumShift('1'); break;
	        case '@': ShiftnumShift('2'); break;
	        case '#': ShiftnumShift('3'); break;
	        case '$': ShiftnumShift('4'); break;
	        case '%': ShiftnumShift('5'); break;
	        case '^': ShiftnumShift('6'); break;
	        case '&': ShiftnumShift('7'); break;
	        case '*': ShiftnumShift('8'); break;
	        case '(': ShiftnumShift('9'); break;
	        case ')': ShiftnumShift('0'); break;
	        default: return;
	    }

	}


	private static void ShiftnumShift(char numShiftCode){
		
	    if (numShiftCode == '\0'){
	        return;
	    } 
	    
	    Robot robot;
		try {
			robot = new Robot();
			robot.setAutoDelay(100);
		    robot.keyPress(KeyEvent.VK_SHIFT);

		    int numShift_KEY = getnumShift(numShiftCode);

	        if (numShift_KEY != -1){
	            robot.keyPress(numShift_KEY);
	            robot.keyRelease(numShift_KEY);
	        }
		    robot.keyRelease(KeyEvent.VK_SHIFT);       
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	}

	private static int getnumShift(char numberChar){
	    switch (numberChar){
	        case '0' : return KeyEvent.VK_SHIFT;
	        case '1' : return KeyEvent.VK_1;
	        case '2' : return KeyEvent.VK_2;
	        case '3' : return KeyEvent.VK_3;
	        case '4' : return KeyEvent.VK_4;
	        case '5' : return KeyEvent.VK_5;
	        case '6' : return KeyEvent.VK_6;
	        case '7' : return KeyEvent.VK_7;
	        case '8' : return KeyEvent.VK_8;
	        case '9' : return KeyEvent.VK_9;
	        default: return -1;
	    }
	}
}