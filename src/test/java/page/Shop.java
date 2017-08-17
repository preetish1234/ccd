package page;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;



import utilitys.Baseclass;
import utilitys.util;

public class Shop extends Baseclass {
	
	static WebDriver  driver;
	static Set<String> s;
	static String mainWindowHandle;
	public  Shop(WebDriver driver){
		
		PageFactory.initElements(driver, this);
		
		
	}
	
public static void verifyPage(WebDriver driver) throws IOException{       
        
 	   try {      
 	
 		    		  
//test = extent.createTest("verifyPage","Verify the home page");
 		  	   
String title = driver.getTitle();
       System.out.println("Home page title- "+title);
      String Expected = "Café Coffee Day | A Lot Can Happen Over Coffe";
        System.out.println(""+Expected);  
  Assert.assertEquals(Expected, title);

} catch (Exception e) {
	
System.out.println(e);

	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	@FindBy(how=How.XPATH,using=".//img[@title='Café Coffee Day Logo'][1]//following::*[contains(text(),'Shop')][1]")
	public static WebElement Shop_Tab;
	
	public static void click_on_ShopTab(WebDriver driver){
		
		 try {
			
			 
			   util.isElementPresnt(driver, ".//img[@title='Café Coffee Day Logo'][1]//following::*[contains(text(),'Shop')][1]", 40);

  List<WebElement> addChart = driver.findElements(By.xpath(".//img[@title='Café Coffee Day Logo'][1]//following::*[contains(text(),'Shop')][1]")); 
				int count=addChart.size();
				System.out.println("total element "+count); 	
				
				
				for(int i =0; i< count;i++){
	             			
	             			WebElement localclick = addChart.get(i);
	             			
	             	     String value = localclick.getAttribute("innerHTML");
	             			System.out.println("values are - " + value);
	             		
	             			WebElement ele = addChart.get(i);
	            			int x=ele.getLocation().getX();
	            			
	            			if (x!=0){
	            				ele.click();
	            	System.out.println("Click on on tab menu");		
	            				break;			   
	            			}
	       }
			    	       
				
			mainWindowHandle=driver.getWindowHandle();
			               
				 s = driver.getWindowHandles();
	            Iterator<String> ite = s.iterator();
	            System.out.println(s);
	            while(ite.hasNext())
	            {
	               String popupHandle=ite.next().toString();
	                  if(!popupHandle.contains(mainWindowHandle))
	                 {
	                    driver.switchTo().window(popupHandle);
	                       System.out.println(popupHandle);
	                 }
	              }
	          }
			    catch (Exception e) {
					 
			    	e.printStackTrace();
				   
			      }
		 }		
  }
	
	
