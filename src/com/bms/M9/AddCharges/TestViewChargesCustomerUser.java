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

public class TestViewChargesCustomerUser extends TestSetUp {
	public String actual = null;

	@Test(enabled = true, priority = 1)
	public void ValidateAddChargesUserCustomer() throws InterruptedException {
		try {
			test = extent.startTest("To Verify Add charges and Add payments functionality in Customer User",
					"To Verify Add charges and Add payments functionality in Customer User"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Login as user Customer");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userCustomer"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "Add patient with complete Case");
			AddChargeUtils.AddPatientCaseCompleted(driver, "AddchargeCustomer");
			test.log(LogStatus.INFO, ActionUtils.getText(AddPatientPage.patientID(driver)));
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Adding multiple Charges to current Patient on different DOS");
			// Add charge-1
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary.contains("$45.00"));
			System.out.println("***********************Assertion-1 Pass**************");
			// Add Payments
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page");
			Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
			Commons.waitforElement(driver, By.xpath("//*[@id='sel_TransType']"), 40);
			Assert.assertTrue(
					ActionUtils.getText(driver.findElements(By.xpath("//*[@id='sel_TransType']/option[@label]")).get(0))
							.contains("Copay"));
			Assert.assertTrue(
					ActionUtils.getText(driver.findElements(By.xpath("//*[@id='sel_TransType']/option[@label]")).get(1))
							.contains("SUPPLY RECORD"));
			System.out.println("***********************Assertion-2 Pass**************");
			HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"), "01/01/2017");
			ActionUtils.click(driver.findElement(By.xpath("//option[@label='PATIENT PD IN OFFICE']")));
			ActionUtils.click(driver.findElement(By.xpath("//option[@label='Cash']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
			Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
			Commons.waitforElement(driver, By.xpath("//div[@id='patientCredits']//table/tbody/tr[1]"), 50);
			System.out.println("Added credits to Patient");
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary_payment = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary_payment.contains("$30.00"));
			System.out.println("***********************Assertion-3 Pass**************");
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Add Charge and payments Customer User Fail" + Throwables.getStackTraceAsString(e));
		}
	}
}
