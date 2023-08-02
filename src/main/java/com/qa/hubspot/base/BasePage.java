 package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This method is used to initialize the browser on the basis of given browser...
 * @param browser
 * @return This return web driver driver
 */
public class BasePage {

	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browser) {
		System.out.println("browser value is :" +browser);
		
		if(browser.equalsIgnoreCase("chrome")) {
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(co);
		}
		else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("safari")){
			driver = new SafariDriver();
	}else {
		System.out.println("Please pass the correct browser value : "+ browser);
	}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	/**
	 * This method is used to load the properties from config.properties file
	 * @return it returns Properties prop reference
	 */
	public Properties init_prop() {
		
		 prop= new Properties();
		 try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/hubspot/config/config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;
		
	}
}
