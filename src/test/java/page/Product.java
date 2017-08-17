package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import utilitys.Baseclass;
import utilitys.util;



public class Product extends Baseclass{

	WebDriver driver;
	
	
	public Product(WebDriver driver){
		
		
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(how=How.XPATH,using=".//*[@class='product-image']//following::*//*[contains(text(),'Coffee Plunger (French Press)')]")
	public static  WebElement Coffee_Plunger;
	
	public static void click_on_Coffer_Plunger(WebDriver driver){
		
		
		for(int i=0;i<=1;i++){
		try {
			
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
			
			List<WebElement> radio = driver.findElements(By.xpath(".//*[@class='img-responsive']//following::*//*[contains(text(),'Coffee Plunger (French Press)')]")); 
			int count=radio.size();
		
			System.out.println("total element "+count); 	
			
			for(int i1 =0; i1< count;i1++){
		     			
		     			WebElement localclick = radio.get(i1);
		     			
		     	     String value = localclick.getText();
		     			System.out.println("values for product - " + value);
		     		
		     			if(value.contains("Coffee Plunger (French Press)")){  
		     				
		     				try {
								localclick.click();
		    System.out.println("click on >>>"+value);
		    
		    //switch to parent window
		    test.log(Status.INFO, "Switch to priveous window");
		    driver.switchTo().window(Shop.mainWindowHandle);
		    System.out.println(Shop.mainWindowHandle);
		    
		     		} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}				
		    		  }
		        }
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
	  }
	}
  }
	
}
