package com.bms.M9.AddCharges;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import ReportUtils.ExtentManager;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddChargeUtils;
import Utils.Commons;
import Utils.HandlingCalendars;

public class TestValidateErrorMessageAddCharges extends TestSetUp {
	public String strActualtext;

	// CHG.ADD.009,
	@Test(enabled = true, priority = 1)
	public void ValidateMandatoryFieldsAddCharges() throws InterruptedException {
		try {
			test = extent.startTest("To Verify Mandatory Fields On Add Charge Page",
					"To Verify Mandatory Fields On Add Charge Page" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Add patient with complete Case");
			// AddChargeUtils.QuickSearchPatient(driver, "CaseCompleted");
			AddChargeUtils.AddPatientCaseCompleted(driver, "AddCharges");
			test.log(LogStatus.INFO, ActionUtils.getText(AddPatientPage.patientID(driver)));
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			// Clicked on save without entering any field
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.screenshot();
			Assert.assertEquals(Commons.capturemessage(driver, 20),
					"Please fill all the mandatory fields or check highlighted field values.");
			test.log(LogStatus.INFO, "*******************Got Error Message1 Assertion Pass****************");
			// Entered Date Of Service and Click on Save
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 20), "There are no charges to save.");
			test.log(LogStatus.INFO, "*******************Got Error Message2 Assertion Pass****************");
			// Removed Location and Billing provider and Click on Save
			test.log(LogStatus.INFO, "Remove location and billing provider");
			Commons.waitforElement(driver, By.xpath("//button[contains(@id,'btnClrLocation')]"), 10);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'btnClrLocation')]")));
			// removing plan of care if any
			driver.findElement(By.xpath("//input[@id='txtPlanOfCare']")).clear();
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 20),
					"Please fill all the mandatory fields or check highlighted field values.");
			test.log(LogStatus.INFO, "*******************Got Error Message3 Assertion Pass****************");
			// Add location And Billing Provider Fields
			test.log(LogStatus.INFO, "Add Location And Billing Provider fields");
			ActionUtils.click(CasePage.LocationMagnifier(driver));
			ActionUtils.click(CasePage.LocationSearchButton(driver));
			ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
			// Thread.sleep(2000);
			ActionUtils.click(AddChargesPage.BillingProviderMagnifier(driver));
			ActionUtils.click(CasePage.BillingProviderSearchButton(driver));
			ActionUtils.click(CasePage.BillingProviderList(driver));
			ActionUtils.doubleClick(driver, CasePage.BillingProviderList(driver));
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			Commons.screenshot();
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 20), "There are no charges to save.");
			test.log(LogStatus.INFO, "*******************Got Error Message4 Assertion Pass****************");
			// Add CPT code and click save
			String ChargebeforeCPT = driver.findElement(By.xpath("//*[@id='tbl_proc']/table/tbody/tr[1]/td[9]"))
					.getText().trim();
			test.log(LogStatus.INFO, "Adding CPT Code 97140");
			/*
			 * Commons.waitforElement(driver,
			 * By.xpath("//div[@id='tbl_proc']//tr[1]//input[@type='search']"),
			 * 20); ActionUtils.sendKeys(driver.findElement(By.xpath(
			 * "//div[@id='tbl_proc']//tr[1]//input[@type='search']")),"97140");
			 * Commons.ExistandDisplayElement(driver,
			 * By.xpath("//span[contains(@md-highlight-text,'searchText')]"),10)
			 * ; Commons.waitForElementToBeVisible(driver,
			 * driver.findElement(By.xpath("//li[@class='ng-scope selected']"
			 * )),20); ActionUtils.click(driver.findElement(By.xpath(
			 * "//li[@class='ng-scope selected']")));
			 */
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			String ChargeAfterCPT = driver.findElement(By.xpath("//*[@id='tbl_proc']/table/tbody/tr[1]/td[9]"))
					.getText().trim();
			Assert.assertFalse(ChargebeforeCPT.contains(ChargeAfterCPT));
			test.log(LogStatus.INFO, "CHarges Added to patient " + ChargeAfterCPT);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			test.log(LogStatus.INFO, "*******************Got  Message5 Assertion Pass****************");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "***************Test Validate Mandatory Fields Add Charges Fail*************");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		test.log(LogStatus.INFO, "***************Test Validate Mandatory Fields Add Charges Pass*************");
	}
}