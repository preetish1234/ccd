
package com.Grid_Exection;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilitys.DataProviderFactory;

public class Parallel_Test_Grid {
	
	
public static RemoteWebDriver driver;
	

    @BeforeTest
	@Parameters({"platform","browserName","remoteurl"})

public void Setup_Grid(String platform,String browserName, String remoteurl)throws MalformedURLException{
		
		DesiredCapabilities capabilities = null;
		
		if(browserName.equalsIgnoreCase("firefox")){
			   capabilities =  DesiredCapabilities.firefox();
			    
			   capabilities.setCapability("marionette", false);
			   capabilities.setBrowserName("firefox");
			   capabilities.setCapability(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,System.setProperty("webdriver.firefox.marionette",
			    		                        DataProviderFactory.getConfig().getFireFoxGekoPath()));
				
			    ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("preetish");
		          driver =new FirefoxDriver(myprofile);
		}
		
		else if(browserName.equalsIgnoreCase("chrome"))
		{
		    capabilities =  DesiredCapabilities.chrome();
			capabilities.setBrowserName("chrome");
		    capabilities.setCapability(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,System.setProperty("webdriver.chrome.driver", 
		    		DataProviderFactory.getConfig().getChromePath()));
		
		    driver =  new ChromeDriver(capabilities);
		}
		
		else if(browserName.equalsIgnoreCase("ie"))
		{
			
			
			capabilities =  DesiredCapabilities.internetExplorer();
			capabilities.setBrowserName("ie");
			capabilities.setCapability(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,System.setProperty("webdriver.ie.driver",
                    DataProviderFactory.getConfig().getIEPath()));
               driver = new InternetExplorerDriver(capabilities);
			}
		
		capabilities.setPlatform(Platform.WINDOWS);
	  driver = new RemoteWebDriver(new URL(remoteurl),capabilities);	
	  driver.get("http://www.google.com");
	}

@Test
public void GoogleSearch() throws InterruptedException{
	
	driver.findElementByName("q").sendKeys("preetish");
	driver.findElementByName("btnG").click();
    driver.close();	
	
	}


}
