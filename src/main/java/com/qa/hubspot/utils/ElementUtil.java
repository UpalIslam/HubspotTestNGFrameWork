package com.qa.hubspot.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
	}

	public By getBy(String locatorType, String locatorValue) {
		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssselecter":
			locator = By.cssSelector(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		default:
			System.out.println("pass correct locator");
			break;
		}
		return locator;
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}

	public String doGetText(By locator) {
		return driver.findElement(locator).getText();
	}

//******* This method will print attribute value *******
	public String getAttributeValue(By locator, String attName) {
		String attVal = getElement(locator).getAttribute(attName);
		System.out.println("Attribute value is " + attVal);
		return attVal;
	}

//******** This method is display if an Element is present or not************
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	/*
	 * ******** This method will verify if Element is present with find Elements
	 * ******** Have to use driver.findElements otherwise if you use driver.find
	 * element it will generate error no locator found exception.
	 */

	public boolean verifyElementPresent(By locator) {
		int elementCount = getElementsCount(locator);
		System.out.println("Total elements found :" + elementCount);
		if (elementCount <= 1) {
			System.out.println("Element Found " + locator);
			return true;
		} else {
			System.out.println("Element not found :" + locator);
		}
		return false;
	}

//******* To get all the WebElements and add them to ArrayList
	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String eleText = e.getText();
			if (!eleText.isEmpty()) {
				eleTextList.add(eleText);
			}

		}
		return eleTextList;
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

//********* To find the WebElements *********
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doDropDownSelectByIndex(By locator, int index) {

		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doDropDownSelectByValue(By locator, String value) {

		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	/******************************** WaitUtils **********************************/
	public void doSendKeys(By locator, String value, int timeOut) {
// doPresenceOfElementLocated(locator, timeOut).sendKeys(value);
		waitForElementToBeVisible(locator, timeOut).sendKeys(value);
	}

	/*
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 */
// Creating WebElement of using explicit wait
	public WebElement doPresenceOfElementLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/*
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 */
	public WebElement waitForElementToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementToBeVisible(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public List<String> getElementsTextListWithWait(By locator, int timeOut) {
		List<WebElement> elelist = waitForElementsToBeVisible(locator, timeOut);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : elelist) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}

	public void waitForFrameByNameOrId(String nameOrId, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
	}

	public void waitForFrameBylocator(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
}
