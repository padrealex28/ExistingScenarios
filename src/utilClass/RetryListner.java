package utilClass;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListner implements IRetryAnalyzer {
		int failedcount=0;
	String retrylimits;
	FileReader reader;    
	Properties props;
	    
	@Override    
	public boolean retry(ITestResult iTestResult) {        
	baseProperties();        
	retrylimits=props.getProperty("retryLimit");        
	int retrylimit = Integer.parseInt(retrylimits);  
	      
	if (failedcount<retrylimit){            
	failedcount++;            
	return  true;
	}        
	return false;    
	}   
	 public void baseProperties()
	 {        
	try
	 {            
	reader = new FileReader("config.properties");        
	} catch (FileNotFoundException e) 
	{            
	e.printStackTrace();       
	 }        
	props = new Properties();        
	try {            
	props.load(reader);        
	} 
	catch (IOException e) 
	{           
	 e.printStackTrace();        
	}    
	}
}
