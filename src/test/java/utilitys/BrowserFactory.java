package utilitys;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Parameters;





public class BrowserFactory
      {
 	
 static WebDriver driver;
@BeforeMethod(alwaysRun=true)
@Parameters("Browser")

public static WebDriver startBrowser(String browserName)throws MalformedURLException
		{
			try {
				if(browserName.equalsIgnoreCase("firefox")){
					
						DesiredCapabilities capabilities = DesiredCapabilities.firefox();
						   capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
						   capabilities.setCapability("marionette", false);
						   capabilities.setPlatform(Platform.WIN8_1);
						   capabilities.setBrowserName("firefox");
						   capabilities.setCapability(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,System.setProperty("webdriver.firefox.marionette",
						    		                        DataProviderFactory.getConfig().getFireFoxGekoPath()));
							
						    ProfilesIni profile = new ProfilesIni();
					FirefoxProfile myprofile = profile.getProfile("preetish");
					          driver =new FirefoxDriver(myprofile);
					}
					
					else if(browserName.equalsIgnoreCase("chrome"))
					{
						DesiredCapabilities capabilities = DesiredCapabilities.chrome();
						capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
						capabilities.setPlatform(Platform.WIN8_1);
						capabilities.setBrowserName("chrome");
					    capabilities.setCapability(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,System.setProperty("webdriver.chrome.driver", 
					    		 DataProviderFactory.getConfig().getChromePath()));
					
					    driver =  new ChromeDriver(capabilities);
					}
					
					else if(browserName.equalsIgnoreCase("ie"))
					{
						
						
						DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
						capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
						capabilities.setPlatform(Platform.WIN8_1);
						capabilities.setBrowserName("ie");
						capabilities.setCapability(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,System.setProperty("webdriver.ie.driver",
				                DataProviderFactory.getConfig().getIEPath()));
				           driver = new InternetExplorerDriver(capabilities);
						
						
					}
					
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
				      JavascriptExecutor js = (JavascriptExecutor)driver;
				driver.get(DataProviderFactory.getConfig().getApplicationUrl());
				   log.Log.info("Take the url");

		
				   
				   String DomainName = js.executeScript("return document.domain;").toString();			
			        System.out.println("Domain name of the site = "+DomainName);					
			          		
			        //Fetching the URL of the site. Tostring() change object to name		
			        String url = js.executeScript("return document.URL;").toString();			
			        System.out.println("URL of the site = "+url);					
			          		
			       //Method document.title fetch the Title name of the site. Tostring() change object to name		
			       String TitleName = js.executeScript("return document.title;").toString();			
			       System.out.println("Title of the page = "+TitleName);					

			        		
			      //Navigate to new Page i.e to generate access page. (launch new url)		
			      js.executeScript("window.location = 'https://www.cafecoffeeday.com/'");
			      System.out.println(js);
				   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return driver;
            }
	
		
}