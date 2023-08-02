package com.qa.hubspot.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class LoginPage extends BasePage{

	private WebDriver driver;
	
	//By locators : OR
	private By emailId = By.name("username");
	private By password = By.name("password");
	private By loginLinkBtn=By.xpath("//a[contains(text(), 'Log in')]");
	private By loginButton = By.xpath("//button[@type = 'submit']");
	private By forgotLinkButton = By.xpath("//*[@id=\"password-description\"]/a/i18n-string");
	private By SignUplink = By.linkText("Sign up");

	//2. Constructor of the page class:
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	//3. page actions: features of the page in the form methods:

	public String getLoginPageTitle(){
		return driver.getTitle();
	}

	public boolean isSignUpLinkExist() {
		return driver.findElement(SignUplink).isDisplayed();
		
	}
	
	public boolean isForgotPasswordBtnExist() {
		return driver.findElement(forgotLinkButton).isDisplayed();
	}
	
	/**
	 * this method will enter username and password and will click on the login button 
	**/
	public void doLogin(String un, String pwd)  {
		System.out.println("Login with: " +un +" and " +pwd);
		driver.findElement(loginLinkBtn).click();
		driver.findElement(emailId).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginButton).click();		
	}
}
