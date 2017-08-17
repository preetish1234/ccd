package utilitys;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class Getscreenshot {
	
	

	public static String captureScreenShot(WebDriver driver){
	
	        String path = "./ErrorScreenShots/" + util.getCurrentDateTime()+ ".png";
	        try {
	            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	            FileUtils.copyFile(screenshotFile, new File(path));
	            System.out.println("ScreenshotTaken");
	        } catch (Exception e) {
	            System.out.println("Exception will takeing screenshot " + e);
	        }
	        return path;
	    }
	}

