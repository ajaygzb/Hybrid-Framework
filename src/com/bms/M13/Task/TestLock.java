package com.bms.M13.Task;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.EMRUtils;
import Utils.HandlingCalendars;

public class TestLock extends TestSetUp {
	public String actual = null;

	@Test(enabled = true, priority = 1)
	public void TestLockFunctionality() throws InterruptedException {
		try {
			// TC-1
			test = extent.startTest("To verify Lock functionality on Header",
					"To verify Lock functionality on Header" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Click On Lock Icon On Header");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='navbar']//a[contains(.,'Lock')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='lockPopup']//h4[contains(.,'Locked')]"), 30);
			test.log(LogStatus.INFO, "Application Locked and Locked Window Appeared");
			Commons.screenshot();
			System.out.println(driver.findElement(By.xpath("//*[@id='lockPopup']//label")).getText());
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='lockPopup']//label")).getText().trim()
					.contains("The screen has been locked by " + Commons.readPropertyValue("username").trim()
							+ ". Please enter password to continue."));
			System.out.println("Assertion PAss");
			Thread.sleep(5000);
			ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='lockPassword']")),
					Commons.readPropertyValue("password"));
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='lockOkBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='navbar']//a[contains(.,'Lock')]"), 30);
			Thread.sleep(5000);
		} catch (AssertionError e) {
			System.out.println("Assertion-1 failed");
			test.log(LogStatus.FAIL, "Assertion-1 failed" + Throwables.getStackTraceAsString(e));
			Commons.logintoRevflow(driver, Commons.readPropertyValue("username"),
					Commons.readPropertyValue("password"));
		}
		extent.endTest(test);
		Commons.screenshot();
		try {
			// TC-2
			test = extent.startTest("To verify error messages on Lock window",
					"To verify error messages on Lock window" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Click On Lock Icon On Header");
			Commons.waitforElement(driver, By.xpath("//*[@id='navbar']//a[contains(.,'Lock')]"), 15);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='navbar']//a[contains(.,'Lock')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='lockPopup']//h4[contains(.,'Locked')]"), 30);
			test.log(LogStatus.INFO, "Application Locked and Locked Window Appeared");
			Thread.sleep(5000);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='lockOkBtn']")));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Password cannot be left blank."));
			System.out.println("Assertion-2 Pass");
			ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='lockPassword']")),
					Commons.readPropertyValue("invalidpassword"));
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='lockOkBtn']")));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Incorrect Password."));
			System.out.println("Assertion-3 Pass");
			driver.findElement(By.xpath("//*[@id='lockPassword']")).clear();
			Thread.sleep(5000);
			ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='lockPassword']")),
					Commons.readPropertyValue("password"));
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='lockOkBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='navbar']//a[contains(.,'Lock')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='dropDownMenu']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='dropDownMenu']//a[contains(.,'New Tab')]")));
			Commons.waitForLoad(driver);
			Thread.sleep(9000);
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
			System.out.println("Open a New tab....");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='navbar']//a[contains(.,'Lock')]"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='navbar']//a[contains(.,'Lock')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='lockPopup']//h4[contains(.,'Locked')]"), 30);
			test.log(LogStatus.INFO, "Application Locked and Locked Window Appeared in new tab");
			Commons.screenshot();
			driver.close();
			driver.switchTo().window(tabs2.get(0));
			Commons.screenshot();
			Thread.sleep(5000);
			Commons.waitforElement(driver, By.xpath("//*[@id='lockPopup']//h4[contains(.,'Locked')]"), 30);
			ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='lockPassword']")),
					Commons.readPropertyValue("password"));
			Commons.waitforElement(driver, By.xpath("//input[@id='lockOkBtn']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='lockOkBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='navbar']//a[contains(.,'Lock')]"), 30);
		} catch (AssertionError e) {
			System.out.println("Assertion-2 failed");
			test.log(LogStatus.FAIL, "Assertion-2 failed" + Throwables.getStackTraceAsString(e));
			Commons.logintoRevflow(driver, Commons.readPropertyValue("username"),
					Commons.readPropertyValue("password"));
		}
		extent.endTest(test);
		Commons.screenshot();
	}
}
