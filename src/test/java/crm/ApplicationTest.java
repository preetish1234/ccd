package crm;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.mortbay.log.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import page.Product;
import page.Shop;
import utilitys.Baseclass;
import utilitys.BrowserFactory;
import utilitys.DataProviderFactory;
import utilitys.Getscreenshot;
import utilitys.log;
import utilitys.util;




@Listeners(utilitys.Baseclass.class)
public class ApplicationTest extends Baseclass{
	
 WebDriver driver;	

	@Test(alwaysRun=true,priority=0)
	public void BrowserEngin() throws Exception{

	         try {
	        	 
	        	 
	    //   test = extent.createTest("BrowserEngin","This will start the browser");	 
	        	//util.captureScreenShot(driver, "adad");
	              driver = BrowserFactory.startBrowser("chrome");
	              test.log(Status.INFO, "Chrome Strated");
	             
	           Getscreenshot.captureScreenShot(driver);        
	              test.log(Status.INFO, "initializing the url");
	         } catch (Exception e) {
					
	 			System.out.println(e);
	 				}
	         }
	               
	
	@Test(alwaysRun=true,priority =1,description="Verify the Home Page",dependsOnMethods="BrowserEngin")           	
public void verifyHomePage() throws IOException{       
	           
	            	   try {      
	      //     test = extent.createTest("verifyHomePage","Title verification");  		
	            		   Shop.verifyPage(driver);
	            		   test.log(Status.INFO, "verify page status");
	           
	         } catch (IOException e) {
					
			System.out.println("failed"+e);
		
				}
		}
	@Test(alwaysRun=true,priority =2,description="Verify the Click on shop menu",dependsOnMethods="verifyHomePage")
	public void Click_on_Shop()throws IOException{
		try {
			
	//		test = extent.createTest("click_on_ShopTab","Click on Shop_tab successful");
			        Shop.click_on_ShopTab(driver);
                
			        Product.click_on_Coffer_Plunger(driver);     
			        Log.info("Click on Coffer_Plunger");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	@AfterTest(alwaysRun=true)
	public void close(){
		try {
			extent.flush();
			driver.quit();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}


}