package com.bms.M9.AddCharges;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import UIMap.AddPatientPage;
import Utils.ActionUtils;
import Utils.AddChargeUtils;
import Utils.Commons;
import Utils.FinancialUtils;
import Utils.HandlingCalendars;

public class TestViewChargesBMSUser extends TestSetUp {
	// CHG.ADD.100,206,207,003
	@Test(enabled = true, priority = 1)
	public void ValidateViewChargesUserBMS() throws InterruptedException {
		try {
			test = extent.startTest("To Verify VIEW CHARGE with and without billing/Payments as BMS user",
					"To Verify VIEW CHARGE with and without billing/Payments as BMS user" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Login as user BMS");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userBMS"), Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "Add patient with complete Case");
			AddChargeUtils.AddPatientCaseCompleted(driver, "testAutomationAddcharge");
			test.log(LogStatus.INFO, ActionUtils.getText(AddPatientPage.patientID(driver)));
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			// VIEW CHARGE without payments as BMS user
			// Adding multiple Charges to current Patient on different DOS
			test.log(LogStatus.INFO, "Adding multiple Charges to current Patient on different DOS");
			// charge-1
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			// charge-2
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "15/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "15/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97110", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			// charge-3
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "16/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "16/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97010", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			// Navigate to View Charge Screen
			AddChargeUtils.NavigateToViewChargePage(driver);
			test.log(LogStatus.INFO, "Navigated to View charges");
			try {
				// Verify Correct amount of charges added
				test.log(LogStatus.INFO, "Verifying Total Charge Added as :==  $150 ");
				Assert.assertTrue(Commons.waitforElement(driver,
						By.xpath("//*[@id='viewChargeGrid']/table/tfoot/tr/td[21]"), 80));
				Assert.assertTrue(ActionUtils
						.getText(driver.findElement(By.xpath("//*[@id='viewChargeGrid']/table/tfoot/tr/td[21]")))
						.contains("$126"));
				test.log(LogStatus.INFO,
						"Verifying Edit Charge,Edit all CHarges For Visit,Delete button appears on View charge");
				Assert.assertTrue(
						Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Edit Charge')]"), 30));
				Assert.assertTrue(Commons.waitforElement(driver,
						By.xpath("//button[contains(text(),'Edit all Charges for Visit')]"), 30));
				Assert.assertTrue(
						Commons.waitforElement(driver, By.xpath("//button//span[contains(text(),'Delete')]"), 30));
				test.log(LogStatus.INFO,
						"Verifying Charges display in date order with the most recent date of Service at the top.");
				int chargerowcount = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//tr")).size();
				Assert.assertEquals(chargerowcount, 3);
				test.log(LogStatus.INFO, "Total 3 rows added for charge");
				String date1 = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]")).get(0)
						.getText().toString().trim();
				String date2 = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]")).get(1)
						.getText().toString().trim();
				String date3 = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]")).get(2)
						.getText().toString().trim();
				Assert.assertEquals(date1, "06/16/2016");
				Assert.assertEquals(date2, "06/15/2016");
				Assert.assertEquals(date3, "06/14/2016");
				test.log(LogStatus.INFO, "Verifying Checkbox are unchecked");
				int checkboxcount = driver
						.findElements(By
								.xpath("//*[@id='viewChargeGrid']/table/tbody//input[contains(@type,'checkbox')][contains(@class,'ng-not-modified')]"))
						.size();
				Assert.assertEquals(checkboxcount, 3);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Assertion Failed" + Throwables.getStackTraceAsString(e));
				test.log(LogStatus.FAIL, "Test fail");
			}
			test.log(LogStatus.INFO,
					"To Verify Checkboxes convert into Lock symbol when user makes payment and charges are not editable after Payment is Done ");
			// Deleting all existing charges on current patient
			test.log(LogStatus.INFO, "Deleting Existing charges on Patient");
			ActionUtils.click(driver
					.findElement(By.xpath("//*[@id='viewChargeGrid']/table//th//input[contains(@type,'checkbox')]")));
			ActionUtils.click(driver.findElement(By.xpath("//button//span[contains(text(),'Delete')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='btnDeleteWarning']"), 20);
			driver.findElement(By.xpath("//*[@id='btnDeleteWarning']")).click();
			Commons.capturemessage(driver, 40);
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'No Charges Available')]"), 30);
			test.log(LogStatus.INFO, "Adding Charges to current user with Full Payment");
			AddChargeUtils.NavigateToAddChargePage(driver);
			// VIEW CHARGE with payments as BMS user
			// charge-1
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save charge and payment Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeandPaymentButton(driver));
			Commons.capturemessage(driver, 90);
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlPayCode']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlPayCode']//option[4]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='ddlMoneyType']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlMoneyType']//option[contains(text(),'Cash')]")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnSavePayment']")));
			Commons.waitForLoad(driver);
			Commons.capturemessage(driver, 60);
			// charge-2
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "15/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "15/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97110", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save charge and payment Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeandPaymentButton(driver));
			Commons.capturemessage(driver, 90);
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlPayCode']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlPayCode']//option[4]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='ddlMoneyType']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlMoneyType']//option[contains(text(),'Cash')]")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnSavePayment']")));
			Commons.waitForLoad(driver);
			Commons.capturemessage(driver, 60);
			AddChargeUtils.NavigateToViewChargePage(driver);
			test.log(LogStatus.INFO, "Navigated to View charges");
			test.log(LogStatus.INFO,
					"To verify Checkboxes changed to Lock Symbol and charges cannot be changed after Payment is Done");
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(".//*[@id='viewChargeGrid']/table/tbody//i[contains(@class,'lock')]"), 30));
			System.out.println("Assertion Pass");
			// Verify the functionality of Visit Logs
			test = extent.startTest("To Verify the functionality of Visit Logs",
					"To Verify the functionality of Visit Logs" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			FinancialUtils.NavigatetoAccountSummary(driver);
			Commons.waitforElement(driver, By.xpath("//button[@id='VisitLog']"), 30);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='VisitLog']")));
			// Assert Patient Visit Logs
			Commons.waitforElement(driver, By.xpath("//*[@id='collapseSelectReport']"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='collapseSelectReport']")));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@id='StartDate']")).sendKeys("06/14/2016");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='EndDate']")).sendKeys("06/14/2016");
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='createReport']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Patient Visit Log ')]"), 60);
			Commons.screenshot();
			Commons.waitforElement(driver, By.xpath("//*[@id='viewRunReport']//tbody//td[2]//div[contains(.,'1')]"),
					30);
			Commons.waitforElement(driver,
					By.xpath("//*[@id='viewRunReport']//tbody//td[3]//div[contains(.,'06/14/2016')]"), 30);
			Commons.screenshot();
			extent.endTest(test);
		} catch (Exception e) {
			Assert.assertFalse(true, "Test View Charge BMS User Fail" + Throwables.getStackTraceAsString(e));
		}
	}
}
