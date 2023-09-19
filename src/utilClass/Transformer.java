package utilClass;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class Transformer extends CommonFunctions implements IAnnotationTransformer {    	

	@Override   
	 public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) 
	{        
	IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);        
	annotation.setRetryAnalyzer(RetryListner.class);    
	
	}
	
	
	}
