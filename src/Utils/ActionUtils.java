package Utils;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import com.google.common.base.Throwables;
import TestBase.TestSetUp;

public class ActionUtils {
	public static void click(WebElement webelement) {
		try {
			Commons.waitForElementToBeVisible(TestSetUp.driver, webelement,10);
			Commons.waitForLoad(TestSetUp.driver);
			if (!webelement.isDisplayed()) {
				Commons.scrollElementinotView(webelement, TestSetUp.driver);
			}
			// To manage rendering and sync issues in app
			Commons.waitForElementToBeClickable(TestSetUp.driver, webelement, 30);
			webelement.click();
			System.out.println("Clicked on above webelement");
		} catch (WebDriverException wde) {
			Commons.scrollElementinotView(webelement, TestSetUp.driver);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				webelement.click();
			} catch (Exception e) {
				JavaScriptclick(webelement);
			}
		} catch (Exception e) {
			System.out.println("Unable to click on above webelement");
			Assert.assertFalse(true, "Unable to click on above webelement" + Throwables.getStackTraceAsString(e));
		}
	}

	public static void JavaScriptclick(WebElement webelement) {
		try {
			Commons.waitForElementToBeClickable(TestSetUp.driver, webelement, 20);
			if (!webelement.isDisplayed()) {
				Commons.scrollElementinotView(webelement, TestSetUp.driver);
			}
			Commons.waitForLoad(TestSetUp.driver);
			JavascriptExecutor executor = (JavascriptExecutor) TestSetUp.driver;
			executor.executeScript("arguments[0].click();", webelement);
			System.out.println("Clicked on above webelement");
		} catch (Exception e) {
			System.out.println("Unable to click on above webelement");
			e.printStackTrace();
		}
	}

	public static void sendKeys(WebElement webelement, String strdata) {
		try {
			Commons.waitForLoad(TestSetUp.driver);
			webelement.sendKeys(strdata);
			System.out.println("Typed in element");
			/*
			 * try { //Thread.sleep(500); } catch (InterruptedException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */// wait used just to slow down execution speed to avoid http
				// console errors and smooth rendering of pages.
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
	}

	public static void clear(WebElement webelement) {
		try {
			webelement.clear();
			System.out.println("Cleared the webelement");
			;
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
	}

	public static String getText(WebElement webelement) {
		String strText = null;
		try {
			int i = 0;
			do {
				// Thread.sleep(2000); //ID takes time to load on HEADER
				strText = webelement.getText().trim();
				System.out.println("Fetching the text of webelement" + strText);
				i++;
			} while (strText.isEmpty() && i < 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strText;
	}

	public static void doubleClick(WebDriver driver, WebElement element) {
		try {
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
				System.out.println("$$In safari block$$");
				// element.click();
				ActionUtils.click(element);
				element.sendKeys(Keys.ENTER);
			} else if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("tabletchrome")) {
				Commons.scrollElementinotView(element, driver);
				Actions action = new Actions(driver);
				action.doubleClick(element).build().perform();
				// Thread.sleep(3000);
				// action.sendKeys(element,Keys.ENTER).build().perform();
			} else {
				Commons.scrollElementinotView(element, driver);
				Commons.waitForElementToBeClickable(driver, element, 80);
				new Actions(driver).moveToElement(element).doubleClick(element).build().perform();
				// Thread.sleep(3000);
				System.out.println("Double Clicked on WebElement");
			}
		} catch (WebDriverException e) {
			try {
				System.out.println("Could not able to Double Clicked on WebElement.... retrying...");
				Commons.scrollElementinotView(element, driver);
				Commons.waitForElementToBeClickable(driver, element, 80);
				Thread.sleep(8000);
				new Actions(driver).moveToElement(element).doubleClick(element).build().perform();
			} catch (Exception e1) {
				Assert.assertFalse(true, "Could not Double click on webelement" + Throwables.getStackTraceAsString(e));
			}
			// Thread.sleep(3000);
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
	}

	/**
	 * Function to performing keyboard actions
	 * 
	 * @param webelement
	 * @param keys
	 */
	public static void sendKeys(WebElement webelement, Keys keys) {
		{
			try {
				webelement.sendKeys(keys);
				System.out.println("passed Keyboard action");
			} catch (ElementNotVisibleException e) {
				System.out.println("Unable to perform keys action");
				e.printStackTrace();
			}
		}
	}
}
