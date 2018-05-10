package com.bms.M9.AddCharges;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import ReportUtils.ExtentManager;
import TestBase.TestSetUp;
import Utils.ActionUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.Commons;

public class TestDeafults extends TestSetUp {
	public String strActualtext;

	// CHG.ADD.005,6,7,8
	@Test(enabled = true, priority = 1)
	public void DefaultSelectionWithCase() throws InterruptedException {
		try {
			// TC-1
			test = extent.startTest("Add Charge page availability Default Selection With Incomplete Case",
					"Add Charge page availability Default Selection With Incomplete Case" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient with Incomplete Case");
			// AddChargeUtils.QuickSearchPatient(driver, "Automation");
			AddPatientUtils.QuickAddpatient(driver);
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'No Cases Available')]"), 60);
			try {
				String expectedMessage = driver.findElement(By.xpath("//span[contains(.,'No Cases Available')]"))
						.getText();
				Assert.assertEquals(expectedMessage, "No Cases Available");
				test.log(LogStatus.INFO, "***************Assertion Pass **********************" + expectedMessage);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail in Default Selection With Incomplete Case*************"
								+ Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "Test Default Selection With Incomplete Case Pass");
			extent.flush();
			extent.endTest(test);
			// TC-2
			test = extent.startTest("Add Charge page availability Default Selection With complete Case",
					"Add Charge page availability Default Selection With complete Case" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Add patient with complete Case");
			String Patient_ID = AddChargeUtils.QuickSearchPatient(driver, "EMRMedicare");
			test.log(LogStatus.INFO, "Added patient with complete Case" + Patient_ID);
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']//option[2]"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			try {
				Assert.assertTrue(
						Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']//option[2]"), 60));
				test.log(LogStatus.INFO, "***************Assertion Pass **********************"
						+ "Charges page displays case drop down.");
				Assert.assertTrue(
						driver.findElement(By.xpath("//*[@id='mainContent']//label[contains(.,'Primary Insurance')]"))
								.isDisplayed(),
						"Could not found Primary insurance field");
				test.log(LogStatus.INFO, "*********Found Primary Insurance***************");
				Assert.assertTrue(
						driver.findElement(By.xpath("//*[@id='mainContent']//label[contains(.,'Secondary Insurance')]"))
								.isDisplayed());
				test.log(LogStatus.INFO, "*********Found Secondary Insurance***************");
				Assert.assertTrue(
						driver.findElement(By.xpath("//*[@id='mainContent']//label[contains(.,'Tertiary Insurance')]"))
								.isDisplayed());
				test.log(LogStatus.INFO, "*********Found Tertiary Insurance***************");
				Assert.assertTrue(
						driver.findElement(By.xpath("//*[@id='mainContent']//label[contains(.,'Location (required)')]"))
								.isDisplayed());
				test.log(LogStatus.INFO, "*********Found Location/POS***************");
				Assert.assertTrue(driver
						.findElement(
								By.xpath("//*[@id='mainContent']//label[contains(.,'Billing Provider (required)')]"))
						.isDisplayed());
				test.log(LogStatus.INFO, "*********Found Billing Provider***************");
				Assert.assertEquals(driver.findElements(By.xpath("//*[@id='tbl_proc']/table/tbody/tr")).size(), 5); // =5
																													// //five
																													// row
																													// in
																													// charge
																													// tabel
				test.log(LogStatus.INFO, "*********Found Charge Details Tabel with 5 row***************");
				Assert.assertEquals(driver
						.findElements(By
								.xpath("//*[@id='tbl_proc']/table/tbody//input[@type='checkbox'][contains(@class,'ng-empty')]"))
						.size(), 5); // =5 //five checkbox empty
				test.log(LogStatus.INFO, "*********Verified 5 checkboxes in Tabel are Empty***************");
				Assert.assertEquals(driver
						.findElements(By.xpath("//*[@id='tbl_proc']/table/tbody//span[contains(.,'NEW')]")).size(), 5);
				test.log(LogStatus.INFO, "*********Verified Status as NEW***************");
				Assert.assertEquals(
						driver.findElements(By.xpath("//*[@id='tbl_proc']//input[contains(@type,'search')]")).size(),
						5);
				test.log(LogStatus.INFO, "*********Verified CPT Code Fields***************");
				Assert.assertEquals(
						driver.findElements(By.xpath("//*[@id='tbl_proc']//input[contains(@ng-model,'charge.units')]"))
								.size(),
						5);
				test.log(LogStatus.INFO, "*********Verified Units Fields***************");
				Assert.assertEquals(
						driver.findElements(By.xpath("//*[@id='tbl_proc']//input[contains(@id,'Amount')]")).size(), 5);
				test.log(LogStatus.INFO, "*********Verified Amount Fields***************");
				Assert.assertEquals(
						driver.findElements(By.xpath("//select[contains(@ng-model,'charge.procMod1')]")).size(), 5);
				test.log(LogStatus.INFO, "*********Verified Modifier 1 Fields***************");
				Assert.assertEquals(
						driver.findElements(By.xpath("//select[contains(@ng-model,'charge.procMod2')]")).size(), 5);
				test.log(LogStatus.INFO, "*********Verified Modifier 2 Fields***************");
				Assert.assertEquals(driver.findElements(By.xpath("//input[contains(@ng-model,'charge.dx')]")).size(),
						5);
				test.log(LogStatus.INFO, "*********Verified DX Code Fields***************");
				Assert.assertEquals(driver.findElements(By.xpath("//*[@id='tbl_proc']//td[9]")).size(), 5);
				test.log(LogStatus.INFO, "*********Verified Total Amount Fields***************");
				Commons.scrollElementinotView(driver.findElement(By.xpath("//div[@id='gridDiagnosis']")), driver);
				Commons.waitforElement(driver, By.xpath("//div[@id='gridDiagnosis']"), 20);
				Assert.assertTrue(driver.findElement(By.xpath("//div[@id='gridDiagnosis']")).isDisplayed());
				test.log(LogStatus.INFO, "*********Verified DX code Fields***************");
				test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@id='gridDiagnosis']")).getText());
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Assertion Fail in Charge details tabel*************"
						+ Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "Test Default Selection With complete Case Pass");
			extent.flush();
			extent.endTest(test);
			// finally
		} catch (Exception e) {
			test.log(LogStatus.INFO,
					"***************Test Defaults is fail*************" + Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		test.log(LogStatus.INFO, "***************Test Defaults is Pass*************");
	}
}
