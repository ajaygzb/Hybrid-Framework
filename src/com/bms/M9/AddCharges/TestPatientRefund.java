package com.bms.M9.AddCharges;

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
import Utils.HandlingCalendars;

public class TestPatientRefund extends TestSetUp {
	public String actual = null;

	@Test(enabled = true, priority = 3)
	public void PatientRefund() throws InterruptedException {
		// TC-1
		test = extent.startTest("Refund Payments to a Patient", "Refund Payments to a Patient"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Quick Add patient");
		AddPatientUtils.QuickAddpatient(driver);
		AddChargeUtils.NavigateToPaymentPage(driver);
		test.log(LogStatus.INFO, "Navigate to Payments page");
		Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
		try {
			// Case-1
			// Adding COPAY $1 Payments to Patient
			HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"), "01/01/2017");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "1");
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_CCnum']")), "1234");
			test.log(LogStatus.INFO, "Add Location And Billing Provider fields");
			ActionUtils.click(CasePage.LocationMagnifier(driver));
			ActionUtils.click(CasePage.LocationSearchButton(driver));
			ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
			Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
			Commons.waitforElement(driver, By.xpath("//div[@id='patientCredits']//table/tbody/tr[1]"), 50);
			System.out.println("Added credits to Patient");
			test.log(LogStatus.INFO, "Payment of $100 is Done to patient with COPAY");
			Commons.screenshot();
			// REFUND Amount $1
			HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"), "01/01/2017");
			driver.findElement(By.xpath("//input[@id='txt_Amount']")).clear();
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "1");
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_CCnum']")), "1234");
			ActionUtils.click(driver
					.findElement(By.xpath("//select[@id='sel_TransType']//option[contains(text(),'PATIENT REFUND')]")));
			ActionUtils.click(CasePage.LocationMagnifier(driver));
			ActionUtils.click(CasePage.LocationSearchButton(driver));
			ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
			Assert.assertEquals(Commons.capturemessage(driver, 30), "Amount must be negative for refund.");
			driver.findElement(By.xpath("//input[@id='txt_Amount']")).clear();
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "-5");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
			Assert.assertEquals(Commons.capturemessage(driver, 30), "The refund cannot be larger than copay total.");
			driver.findElement(By.xpath("//input[@id='txt_Amount']")).clear();
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "-1");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
			Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
			Assert.assertTrue(
					driver.findElement(By.xpath("//div[@id='patientCredits']//tbody/tr[contains(.,'PATIENT REFUND')]"))
							.getText().contains("PATIENT REFUND"),
					"Credit row not added");
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Payments Add Failed" + Throwables.getStackTraceAsString(e));
		}
	}
}
