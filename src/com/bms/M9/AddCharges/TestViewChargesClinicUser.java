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
import Utils.HandlingCalendars;

public class TestViewChargesClinicUser extends TestSetUp {
	// CHG.ADD.212,213,214,215
	@Test(enabled = true, priority = 1)
	public void ValidateViewChargeUserClinical() throws InterruptedException {
		try {
			test = extent.startTest("To Verify VIEW CHARGE with and without billing/Payments as Clinic user",
					"To Verify VIEW CHARGE with and without billing/Payments as Clinic user"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Login as Clinic User");
			// Commons.logout(driver);
			// Commons.logintoRevflow(driver,
			// Commons.readPropertyValue("userCustomer"),Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "Add patient with complete Case");
			AddChargeUtils.AddPatientCaseCompleted(driver, "DummyAddcharge");
			test.log(LogStatus.INFO, ActionUtils.getText(AddPatientPage.patientID(driver)));
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			// VIEW CHARGE without payments as Clinic user
			// Adding multiple Charges to current Patient on Same DOS
			test.log(LogStatus.INFO, "Adding Charges to current Patient on Same DOS");
			// charge-1
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			/*
			 * AddChargeUtils.AddCPTCode(driver,"97110",2);
			 * AddChargeUtils.AddCPTCode(driver,"97010",3);
			 */
			String charges = ActionUtils
					.getText(driver.findElement(By.xpath("//*[@id='tbl_proc']/table/tbody/tr[1]/td[9]")));
			test.log(LogStatus.INFO, "Total Amount On Add charge page" + charges);
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			// Navigate to View Charge Screen
			AddChargeUtils.NavigateToViewChargePage(driver);
			test.log(LogStatus.INFO, "Navigated to View charges");
			test.log(LogStatus.INFO,
					"Verifying Edit Charge,Edit all CHarges For Visit,Delete button appears on View charge");
			Assert.assertTrue(Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Edit Charge')]"), 30));
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath("//button[contains(text(),'Edit all Charges for Visit')]"), 30));
			Assert.assertTrue(
					Commons.waitforElement(driver, By.xpath("//button//span[contains(text(),'Delete')]"), 30));
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='viewChargeGrid']/table/tbody/tr/td[21]")).getText()
					.trim().contains(charges));
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
			// VIEW CHARGE with payments as Clinic user
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
			// Navigate to View Charge Screen
			AddChargeUtils.NavigateToViewChargePage(driver);
			test.log(LogStatus.INFO, "Navigated to View charges");
			test.log(LogStatus.INFO,
					"Verifying Edit Charge,Edit all CHarges For Visit,Delete button appears on View charge");
			Assert.assertTrue(Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Edit Charge')]"), 30));
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath("//button[contains(text(),'Edit all Charges for Visit')]"), 30));
			Assert.assertTrue(
					Commons.waitforElement(driver, By.xpath("//button//span[contains(text(),'Delete')]"), 30));
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='viewChargeGrid']/table/tbody/tr/td[21]")).getText()
					.trim().contains(charges));
		} catch (Exception e) {
			Assert.assertFalse(true, "Test View Charge Clinic User Fail" + Throwables.getStackTraceAsString(e));
		}
	}
}
