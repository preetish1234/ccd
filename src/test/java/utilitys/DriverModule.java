package utilitys;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.interactions.Actions;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class DriverModule extends AbstractModule {
	 
    @Override
    protected void configure() {
 
        //DriverManager config
        bind(DriverManager.class)
            .in(Scopes.SINGLETON);
 
        //My test input properties
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("uat.properties"));
            Names.bindProperties(binder(), props);
        } catch (IOException e) {
            //skip
        }
 
    }
 
    @SuppressWarnings("static-access")
	@Provides
    public Enumeration<Driver> getDriver(DriverManager driverManager) {
        return driverManager.getDrivers();
    }
 
    @Provides
    public Actions getActions(WebDriver driver) {
        return new Actions(driver);
    }
 
    @Provides
    public JavascriptExecutor getExecutor(WebDriver driver) {
        return (JavascriptExecutor)(driver);
    }
}