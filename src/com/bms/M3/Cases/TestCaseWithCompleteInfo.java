package com.bms.M3.Cases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestCaseWithCompleteInfo extends TestSetUp {
	@Test(priority = 3, enabled = true)
	public void TestShellCase() throws InterruptedException {
		test = extent.startTest("Test Shell Case", "Test Case Information" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");// Add
																				// Patient
																				// all
																				// Details
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertTrue(AddCaseUtils.GetFirstCaseStatus(driver).contains("complete"));// To
																							// check
																							// there
																							// is
																							// one
																							// default
																							// case
																							// with
																							// status
																							// as
																							// active
			test.log(LogStatus.INFO, "Test Shell Case successfully.");
			test.log(LogStatus.INFO, "Test:::: TestShellCase() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: TestShellCase() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 2, enabled = true)
	public void CaseAsCompleted() throws InterruptedException {
		test = extent.startTest("Add complete case with All fields", "Test Case Information"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");// Add
																				// Patient
																				// all
																				// Details
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			AddCaseUtils.AddDXCode(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertEquals(AddCaseUtils.GetFirstCaseStatus(driver), "Complete");
			String expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			Assert.assertTrue(expectedMessage.contains("AutoPri Blue Shield"));
			test.log(LogStatus.INFO, "Verified Primary Insurance as AutoPri Blue Shield");
			test.log(LogStatus.INFO, "Case Completed successfully.");
			test.log(LogStatus.INFO, "Test:::: CaseAsCompleted() Completed as Pass");
		
		
		
		
		
		
		
		
		
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: CaseAsCompleted() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 1, enabled = true)
	public void Samereferingphysicians() throws InterruptedException {
		test = extent.startTest("Add both refering physicians as same",
				"To Validate Primary and Secondary Physicians are not allowed to be the same physician"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.QuickAddpatient(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			ActionUtils.click(CasePage.ReferringPhysicianMagnifier(driver));
			ActionUtils.sendKeys(CasePage.MDCodeReferingphysician(driver), "100");
			ActionUtils.click(CasePage.ReferringPhysicianSearchButton(driver));
			ActionUtils.doubleClick(driver, CasePage.ReferringPhysicianList_MDcode(driver));
			System.out.println("Primary Refering physician added successfully");
			test.log(LogStatus.INFO, "Primary Refering physician added successfully");
			ActionUtils.click(CasePage.SecondReferringPhysicianMagnifier(driver));
			Thread.sleep(5000);
			ActionUtils.sendKeys(CasePage.MDCode_SecondryReferingphysician(driver), "100");
			ActionUtils.click(CasePage.SecondReferringPhysicianSearchButton(driver));
			Commons.ExistandDisplayElement(driver, By.xpath("//p[contains(.,'No Second Referring Physician Found')]"),
					60);
			String stractual = ActionUtils
					.getText(driver.findElement(By.xpath("//p[contains(.,'No Second Referring Physician Found')]")));
			Assert.assertEquals(stractual, "No Second Referring Physician Found",
					"Assertion error for Test Same refering physican");
			ActionUtils.click(
					driver.findElement(By.xpath("//div[@id='SecondReferringPhysician']//input[@value='Cancel']")));
			System.out.println("Closed refering physician window");
		} catch (Exception e) {
			test.log(LogStatus.INFO,
					"Test Failed for Primary and Secondary Physicians are not allowed to be the same physician");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 4, enabled = true)
	public void AddRefPhysician() throws InterruptedException {
		test = extent.startTest("to test add physician will not save without any data ", "AddRefPhysician"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");// Add
																				// Patient
																				// all
																				// Details
			AddCaseUtils.GoToAddNewCase(driver);
			ActionUtils.click(CasePage.ReferringPhysicianMagnifier(driver));
			Commons.waitforElement(driver, By.xpath("//input[@value='Add Referring Physician']"), 120);
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Add Referring Physician']")));
			Commons.waitforElement(driver, By.name("btnReferringPhySave"), 120);
			ActionUtils.click(driver.findElement(By.name("btnReferringPhySave")));
			Assert.assertTrue(
					Commons.waitForElementToBeVisible(driver, driver.findElement(By.id("toast-container")), 90));
			String message = driver.findElement(By.xpath("//div[@id='toast-container']//div[@class='toast-message']"))
					.getText();
			System.out.println("message on toaster is " + message);
			test.log(LogStatus.INFO, "message on toaster is " + message);
			Assert.assertEquals(message, "Please fill all the mandatory fields or check highlighted field values.");
			test.log(LogStatus.INFO, "got toaster message hence test case Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: AddRefPhysician() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}