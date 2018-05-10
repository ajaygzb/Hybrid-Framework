package com.bms.M3.Cases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.HandlingCalendars;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestCaseInactive extends TestSetUp {
	@Test(priority = 1, enabled = true)
	public void InActivateCaseNoCharges() throws InterruptedException {
		test = extent.startTest("Inactivate case with no charges", "Inactivate case with no charges"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "CaseInactiveNoCharges");// Add
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
			test.log(LogStatus.INFO, "Inactivate the case");
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'AutoPri Blue Shield')]")));
			ActionUtils.click(CasePage.CaseEditButton(driver));
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#verificationForm']")));
			Commons.scrollElementinotView(driver.findElement(By.xpath("//input[@name='isCaseActive']")), driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='isCaseActive']"), 30);
			Thread.sleep(5000);
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='isCaseActive']")));
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//input[@name='isCaseActive'][contains(@class,'not-empty')]"), 5)) {
				ActionUtils.click(driver.findElement(By.xpath("//input[@name='isCaseActive']")));
			}
			Commons.screenshot();
			Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]"), 60);
			ActionUtils
					.click(driver.findElement(By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]")));
			Commons.capturemessage(driver, 40);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//span[@id='patientID']"), 60);
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertEquals(AddCaseUtils.GetFirstCaseStatus(driver), "Incomplete");
			test.log(LogStatus.INFO, "Inactivate case with no charges Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Inactivate case with no charges Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 2, enabled = true)
	public void InActivateCaseWithCharges() throws InterruptedException {
		test = extent.startTest("Inactivate case with charges", "Inactivate case with charges"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "CaseInactiveWithCharges");// Add
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
			// Add Charge to Case
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "01/01/2017");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			AddChargeUtils.AddCPTCode(driver, "97110", 2);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "01/01/2017");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.waitForLoad(driver);
			Commons.capturemessage(driver, 90);
			test.log(LogStatus.INFO, "Inactivate the case");
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertEquals(AddCaseUtils.GetFirstCaseStatus(driver), "Complete");
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'AutoPri Blue Shield')]")));
			ActionUtils.click(CasePage.CaseEditButton(driver));
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#verificationForm']")));
			Commons.scrollElementinotView(driver.findElement(By.xpath("//input[@name='isCaseActive']")), driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='isCaseActive']"), 30);
			Thread.sleep(5000);
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='isCaseActive']")));
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//input[@name='isCaseActive'][contains(@class,'not-empty')]"), 5)) {
				ActionUtils.click(driver.findElement(By.xpath("//input[@name='isCaseActive']")));
			}
			Commons.screenshot();
			Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]"), 60);
			ActionUtils
					.click(driver.findElement(By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='rebillPopup']//button[contains(.,'Yes')]"),
					12)) {
				System.out.println("Rebill pop up appeared");
				Commons.screenshot();
				driver.findElement(By.xpath("//*[@id='rebillPopup']//button[contains(.,'Yes')]")).click();
			}
			Commons.capturemessage(driver, 150);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//span[@id='patientID']"), 60);
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertEquals(AddCaseUtils.GetFirstCaseStatus(driver), "Complete");
			test.log(LogStatus.INFO, "Inactivate case with  charges Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Inactivate case with  charges Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}