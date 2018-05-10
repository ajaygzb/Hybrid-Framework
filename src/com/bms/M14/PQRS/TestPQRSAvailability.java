package com.bms.M14.PQRS;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.CreateNotePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.Commons;
import Utils.EMRUtils;
import Utils.SPageUtils;
import Utils.SearchPatientUtils;

public class TestPQRSAvailability extends TestSetUp {
	// TC PQRS availability for RT provider
	// TC PQRS availability for PT provider
	// TC PQRS availability for SLP provider
	// TC PQRS availability for OT provider
	public String strActualtext = null;
	public List<WebElement> actual = null;

	@Test(enabled = true, priority = 1)
	public void PQRSMeasures() throws InterruptedException {
		test = extent.startTest("Validate PQRS Measures with RT provider while the patient age is over 65 ",
				"Validate PQRS Measures with RT provider while the patient age is over 65 "
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// TC PQRS availability for RT provider
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userRT"), Commons.readPropertyValue("password"));
		test.log(LogStatus.INFO, "Login as RT provider");
		try {
			strActualtext = EMRUtils.AddPatientPQRS(driver, "PQRS65" + new Random().nextInt(9999999), "14/05/1952", ""); // Added
																															// a
																															// Patient
																															// with
																															// all
																															// fields
																															// and
																															// Complete
																															// case
																															// with
																															// insurance
																															// Medicare
																															// FLR
			System.out.println("Added a Patient with all fields and Complete case with insurance flagged for PQRS");
			test.log(LogStatus.INFO,
					"Added a Patient with all fields and Complete case with insurance flagged for PQRS");
			test.log(LogStatus.INFO,
					"To Validate Alert Message when Do NOT enter any minutes and click Charge Capture");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
			System.out.println("Navigated to P page");
			test.log(LogStatus.INFO, "Navigated to P page");
			Commons.screenshot();
			// ********************CASE-1: Validate PQRS tab available with ONLY
			// the following measures displayed**************
			// 128,130,131,154,155,182
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"),
					60);
			actual = driver.findElements(By.xpath(
					"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"));
			Assert.assertEquals(actual.size(), 6);
			System.out.println("PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "Validating PQRS Measures Codes");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected1 = { "128", "130", "131", "154", "155", "182" };
			for (int i = 0; i < 6; i++) {
				Assert.assertTrue(actual.get(i).getText().trim().contains(expected1[i]));
			}
			System.out.println("************Assertion-1 Pass*********");
			Commons.screenshot();
			extent.endTest(test);
			// ********************CASE-2: Validate PQRS tab available with ONLY
			// the following measures displayed**************
			// 126,127,128,130,131,154,155,182
			test = extent.startTest("Validate PQRS Measures with PT provider while the patient age is over 65 ",
					"Validate PQRS Measures with PT provider while the patient age is over 65 "
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// TC PQRS availability for PT provider
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPT"), Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "Login as PT provider");
			SearchPatientUtils.QuickpatientSearch(driver, strActualtext);
			APageUtils.GotoNoteList(driver, 1);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
			System.out.println("Navigated to P page");
			test.log(LogStatus.INFO, "Navigated to P page");
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"),
					60);
			actual = driver.findElements(By.xpath(
					"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"));
			Assert.assertEquals(actual.size(), 8);
			System.out.println("PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "Validating PQRS Measures Codes");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected2 = { "126", "127", "128", "130", "131", "154", "155", "182" };
			for (int i = 0; i < 8; i++) {
				Assert.assertTrue(actual.get(i).getText().trim().contains(expected2[i]));
			}
			System.out.println("************Assertion-2 Pass*********");
			Commons.screenshot();
			extent.endTest(test);
			// ********************CASE-3: Validate PQRS tab available with ONLY
			// the following measures displayed**************
			// 128,130,131,154,155,182
			test = extent.startTest("Validate PQRS Measures with SLP provider while the patient age is over 65 ",
					"Validate PQRS Measures with SLP provider while the patient age is over 65 "
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// TC PQRS availability for SLP provider
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userSLP"), Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "Login as SLP provider");
			SearchPatientUtils.QuickpatientSearch(driver, strActualtext);
			APageUtils.GotoNoteList(driver, 1);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
			System.out.println("Navigated to P page");
			test.log(LogStatus.INFO, "Navigated to P page");
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"),
					60);
			actual = driver.findElements(By.xpath(
					"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"));
			Assert.assertEquals(actual.size(), 3);
			System.out.println("PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "Validating PQRS Measures Codes");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected3 = { "130", "131", "226" };
			for (int i = 0; i < 3; i++) {
				Assert.assertTrue(actual.get(i).getText().trim().contains(expected3[i]));
			}
			System.out.println("************Assertion-3 Pass*********");
			Commons.screenshot();
			extent.endTest(test);
			// ********************CASE-4: Validate PQRS tab available with ONLY
			// the following measures displayed**************
			// 128,130,131,134,154,155,181,182,226,431
			test = extent.startTest("Validate PQRS Measures with OT provider while the patient age is over 65 ",
					"Validate PQRS Measures with OT provider while the patient age is over 65 "
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// TC PQRS availability for OT provider
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userOT"), Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "Login as OT provider");
			SearchPatientUtils.QuickpatientSearch(driver, strActualtext);
			APageUtils.GotoNoteList(driver, 1);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
			System.out.println("Navigated to P page");
			test.log(LogStatus.INFO, "Navigated to P page");
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"),
					60);
			actual = driver.findElements(By.xpath(
					"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"));
			Assert.assertEquals(actual.size(), 10);
			System.out.println("PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "Validating PQRS Measures Codes");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected4 = { "128", "130", "131", "134", "154", "155", "181", "182", "226", "431" };
			for (int i = 0; i < 10; i++) {
				Assert.assertTrue(actual.get(i).getText().trim().contains(expected4[i]));
			}
			System.out.println("************Assertion-4 Pass*********");
			Commons.screenshot();
			extent.endTest(test);
			System.out.println("Test PQRS availablility is Completed as Pass");
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e) + "Test Sign and Finalize failed");
		}
	}
}
