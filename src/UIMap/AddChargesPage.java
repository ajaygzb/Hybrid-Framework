package UIMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import TestBase.TestSetUp;
import Utils.ActionUtils;
import Utils.Commons;
import com.google.common.base.Throwables;

public class AddChargesPage {
	public static WebElement webelement;

	public static WebElement TransactionOnMenu(WebDriver driver) {
		try {
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("tabletchrome")) {
				Commons.Explicitwait();
				Commons.waitforElement(driver, By.xpath("//*[@id='menu-toggle']/i"), 90);
				if (!driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,'Transactions')]"))
						.isDisplayed()) {
					driver.findElement(By.xpath("//*[@id='menu-toggle']/i")).click();
				}
				Commons.Explicitwait();
				Commons.Explicitwait();
			}
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,'Transactions')]"), 80);
			webelement = driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,'Transactions')]"));
			System.out.println("Got Transactions on Menu webelement");
		} catch (Exception e) {
			System.out.println("Did not get Transactions on Menu webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AddChargesButton(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,'Add Charges')]"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,'Add Charges')]"));
			System.out.println("Got Add Charges on Sub Menu");
		} catch (Exception e) {
			System.out.println("Did not get Add Charges on Sub Menu Retry...");
			ActionUtils.click(AddChargesPage.TransactionOnMenu(driver));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,'Add Charges')]"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,'Add Charges')]"));
			System.out.println("Got Add Charges on Sub Menu");
			//Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ViewChargesButton(WebDriver driver) {
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='sidemenu']//span[contains(text(),'View Charges')]"), 40);
			webelement = driver.findElement(By.xpath("//*[@id='sidemenu']//span[contains(text(),'View Charges')]"));
			System.out.println("Got  View Charges on Sub Menu");
		} catch (Exception e) {
			System.out.println("Did not get View Charges on Sub Menu retrying..");
			ActionUtils.click(AddChargesPage.TransactionOnMenu(driver));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='sidemenu']//span[contains(text(),'View Charges')]"), 40);
			webelement = driver.findElement(By.xpath("//*[@id='sidemenu']//span[contains(text(),'View Charges')]"));
			//Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PaymentButton(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='sidemenu']//span[contains(text(),'Payments')]"), 40);
			webelement = driver.findElement(By.xpath("//*[@id='sidemenu']//span[contains(text(),'Payments')]"));
			System.out.println("Got Payments on Sub Menu");
		} catch (Exception e) {
			System.out.println("Did not get Payments on Sub Menu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ChargeAudit(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='sidemenu']//span[contains(text(),'Audit Charge')]"), 40);
			webelement = driver.findElement(By.xpath("//*[@id='sidemenu']//span[contains(text(),'Audit Charge')]"));
			System.out.println("Got Audit Charge on Sub Menu");
		} catch (Exception e) {
			System.out.println("Did not get Audit Charge on Sub Menu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SaveChargeButton(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@id='btnSaveCharge']"), 40);
			webelement = driver.findElement(By.xpath("//button[@id='btnSaveCharge']"));
			System.out.println("Got Save Charge Button");
		} catch (Exception e) {
			System.out.println("Did not get Save Charge Button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SaveChargeandPaymentButton(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@id='btnSaveChargeandPayment']"), 40);
			webelement = driver.findElement(By.xpath("//button[@id='btnSaveChargeandPayment']"));
			System.out.println("Got Save Charge and Payment Button");
		} catch (Exception e) {
			System.out.println("Did not get Save Charge and Payment Button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}


	
	public static WebElement BillingProviderMagnifier(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='btnBPSearch']"), 40);
			webelement = driver.findElement(By.xpath("//*[@id='btnBPSearch']"));
			System.out.println("Got Billing Provider Magnifier  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Billing Provider Magnifier webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "BillingProviderMagnifier");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}



















}













