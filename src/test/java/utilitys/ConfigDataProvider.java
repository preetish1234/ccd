package utilitys;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider 
{
	Properties pro;
	
	
	public ConfigDataProvider()
	{
		
		File src=new File("C:\\Users/Preetish Kumar/Desktop/cafecoffe/src/test/java/configutration/config.properties");
		
		try 
		{
			FileInputStream fis=new FileInputStream(src);
			
			 pro=new Properties();
			
			 pro.load(fis);
			
		} catch (Exception e) 
		{
			System.out.println("Exception is "+e.getMessage());
		}
		
	}
	
	public String getIEPath()
	{
		String url=pro.getProperty("IEPath");
		return url;
	}
	
	
	public String getChromePath()
	{
		String url=pro.getProperty("chromePath");
		return url;
	}
	
	public String getFireFoxGekoPath()
	{
		String url=pro.getProperty("FireFoxGekoPath");
		return url;
	}
	
	
	public String getApplicationUrl()
	{
		String url=pro.getProperty("url");
		return url;
	}
	
    public String getLogPath()
    {
    	String url=pro.getProperty("logPath");
    	return url;
    }
}