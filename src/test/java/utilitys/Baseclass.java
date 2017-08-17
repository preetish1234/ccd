package utilitys;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;




public class Baseclass implements ITestListener{
	
      
	  static ExtentHtmlReporter htmlReport;	
      protected static ExtentReports extent;
	  protected static ExtentTest test;
	  static WebDriver driver;
	
	
	@BeforeMethod(alwaysRun=true)
	public void Logconfig(Method method)throws ClassNotFoundException, SQLException
	{
	PropertyConfigurator.configure("C:\\Users\\Preetish Kumar\\Desktop\\cafecoffe\\log.properties");
	    test = extent.createTest(method.getName());
	}
	

	@BeforeSuite(alwaysRun=true)
	public void setUp(){
					
			try   {	
				extent = new ExtentReports();
				extent = ExtentManager.createInstance("MyOwnReport.html");
				ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("MyOwnReport.html");
				extent.attachReporter(htmlReporter);
		  }
		  catch (Exception e) {
				
				e.printStackTrace();
		  }
			  }
	
	

	@AfterMethod(alwaysRun=true)
	
	public void getResult(ITestResult result) throws IOException
	{
		try {
			System.out.println("------ITestResult start-----");
			
			if(ITestResult.FAILURE==result.getStatus()){
				WebDriver driver = null;
				
				String  screenshotPath = Getscreenshot.captureScreenShot(driver);
				test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ "****Test case FAILED due to below issue****", ExtentColor.RED));
	
			//    test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			    test.fail("SnapShot below"+test.addScreenCaptureFromPath(screenshotPath));
			}
		else if(result.getStatus()==ITestResult.SUCCESS)
			{
				test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"****Test Case PASSED****", ExtentColor.GREEN));
			}
			else{
				test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"****Test case Skipped****", ExtentColor.ORANGE));
			}
			
			        

		} catch (Exception e) {
			
			System.out.println(e);
		}
	}
	
	

	
	public static class ExtentManager {
	    
	    
	    
	    public static ExtentReports getInstance() {
	    	if (extent == null)
	    		createInstance("MyOwnRport.html");
	    	
	        return extent;
	    }
	    
	    public static ExtentReports createInstance(String fileName) {
	      
	    	htmlReport = new ExtentHtmlReporter(fileName);  
	    	extent = new ExtentReports(); 
		    extent.attachReporter(htmlReport);
		
			 extent.setSystemInfo("OS", "Win 8.1");
			 extent.setSystemInfo("Host Name", "Preetish");
			 extent.setSystemInfo("Envirment", "QA");
			 extent.setSystemInfo("User Name", "Preetish Kumar");
	
	htmlReport.loadXMLConfig(new File(System.getProperty("user.dir")+"./extent-config.xml"));
	     	htmlReport.config().setChartVisibilityOnOpen(true);
			 htmlReport.config().setDocumentTitle("Banking Report");
		 htmlReport.config().setReportName("Preetish");
		 htmlReport.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	        return extent;
	    }
	}
	
	

	

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(">>>>>Test started<<<<<");
		
	}


	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(">>>>>Test completed<<<<<");
		
	}


	@Override
	public void onTestFailure(ITestResult result) {
		
		Getscreenshot.captureScreenShot(driver);
	}


	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped");
		
	}


	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}


}