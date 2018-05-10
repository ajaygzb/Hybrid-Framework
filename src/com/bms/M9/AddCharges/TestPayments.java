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
import Utils.FinancialUtils;
import Utils.HandlingCalendars;

public class TestPayments extends TestSetUp {
	public String actual = null;

	@Test(enabled = true, priority = 1)
	public void Add_Delete_Update_Payments() throws InterruptedException {
		// TC-1
		test = extent.startTest("Add Payments to a Patient", "Add Payments to a Patient" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Search patient with complete Case");
		AddPatientUtils.QuickAddpatient(driver);
		AddChargeUtils.NavigateToPaymentPage(driver);
		test.log(LogStatus.INFO, "Navigate to Payments page");
		Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
		try {
			// Case-1
			// Add Payments to Patient
			HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"), "01/01/2017");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "100");
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_CCnum']")), "1234");
			test.log(LogStatus.INFO, "Add Location And Billing Provider fields");
			ActionUtils.click(CasePage.LocationMagnifier(driver));
			ActionUtils.click(CasePage.LocationSearchButton(driver));
			ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
			Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
			Commons.waitforElement(driver, By.xpath("//div[@id='patientCredits']//table/tbody/tr[1]"), 50);
			System.out.println("Added credits to Patient");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			Commons.waitForLoad(driver);
			ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientID']")));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Financial']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Financial']")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//h2[contains(.,'Account Summary')]"), 60)) {
				System.out.println("Got Patient's Account Summary");
			} else {
				ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Financial']")));
				Commons.ExistandDisplayElement(driver, By.xpath("//h2[contains(.,'Account Summary')]"), 60);
			}
			Commons.waitforElement(driver, By.xpath("//div[@id='gridAccountSummaryNotes']//tbody//tr[3]//td[3]"), 30);
			ActionUtils
					.getText(driver.findElement(By.xpath("//div[@id='gridAccountSummaryNotes']//tbody//tr[3]//td[3]")));
			Assert.assertEquals(
					ActionUtils.getText(
							driver.findElement(By.xpath("//div[@id='gridAccountSummaryNotes']//tbody//tr[3]//td[3]"))),
					"$100.00");
			test.log(LogStatus.INFO, "Validate Payments in Account summary");
			Commons.screenshot();
			extent.endTest(test);
			// Verify the functionality of Patient Payment Log
			test = extent.startTest("Verify Payment Amount in Patient Payment Log",
					"Verify Payment Amount in Patient Payment Log" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// FinancialUtils.NavigatetoAccountSummary(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='PaymentLog']"), 30);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='PaymentLog']")));
			// Assert Patient Payment Log
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Patient Payment Log ')]"), 190);
			Commons.waitforElement(driver, By.xpath("//*[@id='viewRunReport']//a[contains(.,'Deposit Date')]"), 30);
			Commons.waitforElement(driver, By.xpath("//*[@id='viewRunReport']//td[contains(.,'01/01/2017')]"),
					30);
			Commons.waitforElement(driver, By.xpath("//*[@id='viewRunReport']//a[contains(.,'Payment Amount')]"), 30);
			Commons.waitforElement(driver,
					By.xpath("//*[@id='viewRunReport']/div[4]/div/table/tbody//div[contains(.,'$100.00')]"), 60);
			Commons.waitforElement(driver, By.xpath("//*[@id='viewRunReport']//a[contains(.,'Payment Type')]"), 30);
			Commons.waitforElement(driver, By.xpath("//*[@id='viewRunReport']//tr[1]//div[contains(.,'Check1234')]"),
					30);
			// Commons.waitforElement(driver,
			// By.xpath("//*[@id='viewRunReport']//a[contains(.,'Payment
			// Type')]"),30);
			Commons.screenshot();
			extent.endTest(test);
			// Verify the functionality of Demand Statement
			test = extent.startTest("To Verify the functionality of Demand Statement",
					"To Verify the functionality of Demand Statement" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			FinancialUtils.NavigatetoAccountSummary(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(@id,'DemandStatement')]"), 30);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'DemandStatement')]")));
			// Assert Patient Demand Statement
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Demand Statement')]"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='createReport']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//td[contains(.,'($100.00)')]"), 30);
			Assert.assertEquals(driver.findElements(By.xpath("//td[contains(.,'($100.00)')]")).size(), 5);
			Commons.waitforElement(driver, By.xpath("//td[contains(.,'Check #1234')]"), 30);
			Commons.waitforElement(driver, By.xpath("//a[contains(.,'Payment Type')]"), 30);
			Assert.assertEquals(driver.findElements(By.xpath("//tr[1]//td[contains(.,'01/01/2017')]")).size(), 2);
			Assert.assertEquals(driver.findElements(By.xpath("//a[contains(.,'Payment Amount')]")).size(), 2);
			Commons.screenshot();
			extent.endTest(test);
			// Verify the functionality of Print Patient Forms
			test = extent.startTest("To Verify the functionality of Print Patient Forms",
					"To Verify the functionality of Print Patient Forms" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			FinancialUtils.NavigatetoAccountSummary(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(@href,'#PrintPatientForm')]"), 30);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'#PrintPatientForm')]")));
			// Assert Print Patient Forms
			Commons.waitforElement(driver, By.xpath("//a[@id='printPatientForms']"), 90);
			Thread.sleep(3000);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnPrintPatientForms']")));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Please select a record"));
			System.out.println("Click on first available check box");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='gridPrintPatientForms']//tbody//tr[1]//input[1]")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnPrintPatientForms']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//h4[contains(.,'Print Preview')]"), 90);
			Assert.assertEquals(driver.findElements(By.xpath("//*[@id='nextPreviousBtn']//button")).size(), 2);
			Commons.waitforElement(driver, By.xpath("//*[@id='printPatientForm']"), 30);
			Commons.waitforElement(driver, By.xpath("//*[@id='closePatientForm']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='closePatientForm']")));
			Commons.screenshot();
			extent.endTest(test);
			// Case-2
			// Update Payments
			test = extent.startTest("Update Payments to a Patient",
					"Update Payments to a Patient" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient having existing Payments");
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page");
			Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
			Commons.screenshot();
			String beforeupdate = ActionUtils.getText(driver
					.findElement(By.xpath("//select[contains(@id,'sel_tblTransType')]//option[@selected='selected']")));
			test.log(LogStatus.INFO, "Select Payment COde to CASH DOS and click Update");
			ActionUtils.click(driver.findElement(
					By.xpath("//select[contains(@id,'sel_tblTransType')]//option[contains(text(),'Cash DOS')]")));
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_UpdateCredit']")));
			actual = Commons.capturemessage(driver, 60);
			if (actual != null) {
				Assert.assertEquals(actual, "Credit updated.");
			}
			String afterupdate = ActionUtils.getText(driver
					.findElement(By.xpath("//select[contains(@id,'sel_tblTransType')]//option[@selected='selected']")));
			Assert.assertNotEquals(beforeupdate, afterupdate);
			extent.endTest(test);
			// Case-3
			// Delete Payments
			test = extent.startTest("Delete Payments to a Patient",
					"Delete Payments to a Patient" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient having existing Payments");
			Commons.screenshot();
			test.log(LogStatus.INFO, "Select Checkbox to delete Payment");
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='selectAll']")));
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_DeleteCredit']")));
			Commons.waitforElement(driver,
					By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]"), 15);
			test.log(LogStatus.INFO, "Got Confirmation Pop up");
			Commons.screenshot();
			ActionUtils.click(driver
					.findElement(By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]")));
			actual = Commons.capturemessage(driver, 60);
			if (actual != null) {
				Assert.assertEquals(actual, "Credit Deleted.");
			}
		} catch (Exception e) {
			Assert.assertFalse(true, "Payments Add Failed" + Throwables.getStackTraceAsString(e));
		}
	}
}
