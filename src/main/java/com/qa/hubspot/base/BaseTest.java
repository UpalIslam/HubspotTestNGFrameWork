package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.hubspot.pages.LoginPage;

public class BaseTest {

	public BasePage basePage;
	public LoginPage loginPage;
	public Properties prop;
	public WebDriver driver;
	
		@BeforeTest
		public void setup() {
			basePage = new BasePage();	
			prop= basePage.init_prop();
			String browser = prop.getProperty("browser");
			driver = basePage.init_driver(browser);
			String url = prop.getProperty("url");
			driver.get(url);
			loginPage = new LoginPage(driver);
		}
		
		@AfterTest
		public void tearDown() {
			driver.quit();
		}
}
