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

public class TestRemovingRequiredFeildsInCase extends TestSetUp {
	@Test(priority = 1)
	public void RemovingCaseEffectiveDate() throws InterruptedException {
		try {
			test = extent.startTest("Removing CaseEffective Date",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.addpatient_withallfields(driver, "RemoveRequiredFieldCAse");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.ClickCaseEdit(driver);
			ActionUtils.click(CasePage.CaseEffectiveDateRemove(driver));
			ActionUtils.clear(CasePage.CaseEffectiveDateRemove(driver));
			String Message = AddCaseUtils.ClickCaseSave(driver);
			Commons.Explicitwait();
			Assert.assertTrue(Message.contains("You can edit these fields, but you cannot leave them blank."));
			test.log(LogStatus.INFO, "Removing Case Effective Date Successfully");
			test.log(LogStatus.INFO, "Test:::: RemovingCaseEffectiveDate() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Assertion Error!!!" + e);
			test.log(LogStatus.FAIL, "Test:::: RemovingCaseEffectiveDate() Completed as Fail");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
		extent.endTest(test);
		RemovingAssingmentDate();
	}

	// @Test(priority=1,enabled=true)
	public void RemovingAssingmentDate() throws InterruptedException {
		try {
			test = extent.startTest("Removing Assingment Date", "Test Case Information" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(@class,'patientHeaderNameLink')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='caseUnSaved']//input[@value='No']"), 5)) {
				System.out.println("Case Unsaved pop up appeared");
				Commons.screenshot();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseUnSaved']//input[@value='No']")));
				Commons.waitForLoad(driver);
			}
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.ClickCaseEdit(driver);
			ActionUtils.click(CasePage.CaseAssignmentDateRemove(driver));
			ActionUtils.clear(CasePage.CaseAssignmentDateRemove(driver));
			Commons.Explicitwait();
			String Message = AddCaseUtils.ClickCaseSave(driver);
			Assert.assertTrue(Message.contains("You can edit these fields, but you cannot leave them blank."));
			test.log(LogStatus.INFO, "Removing Assingment Date Successfully");
			test.log(LogStatus.INFO, "Test:::: RemovingAssingmentDate() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Assertion Error!!!");
			test.log(LogStatus.FAIL, "Test:::: RemovingAssingmentDate() Completed as Fail");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
		extent.endTest(test);
		RemovingReferringPhysician();
	}

	// @Test(priority=4)
	public void RemovingReferringPhysician() throws InterruptedException {
		try {
			test = extent.startTest("Removing Referring Physician",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(@class,'patientHeaderNameLink')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='caseUnSaved']//input[@value='No']"), 5)) {
				System.out.println("Case Unsaved pop up appeared");
				Commons.screenshot();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseUnSaved']//input[@value='No']")));
				Commons.waitForLoad(driver);
			}
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.ClickCaseEdit(driver);
			ActionUtils.click(CasePage.CaseReferringPhysicianRemoveButton(driver));
			String Message = AddCaseUtils.ClickCaseSave(driver);
			Commons.Explicitwait();
			Assert.assertTrue(Message.contains("You can edit these fields, but you cannot leave them blank."));
			test.log(LogStatus.INFO, "Removing Referring Physician Successfully");
			test.log(LogStatus.INFO, "Test:::: RemovingReferringPhysician() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Assertion Error!!!");
			test.log(LogStatus.FAIL, "Test:::: RemovingReferringPhysician() Completed as Fail");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
		extent.endTest(test);
		RemovingLocation();
	}

	// @Test(priority=3)
	public void RemovingLocation() throws InterruptedException {
		try {
			test = extent.startTest("Removing Location", "Test Case Information" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(@class,'patientHeaderNameLink')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='caseUnSaved']//input[@value='No']"), 5)) {
				System.out.println("Case Unsaved pop up appeared");
				Commons.screenshot();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseUnSaved']//input[@value='No']")));
				Commons.waitForLoad(driver);
			}
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.ClickCaseEdit(driver);
			ActionUtils.click(CasePage.CaseLocationRemoveButton(driver));
			Commons.Explicitwait();
			String Message = AddCaseUtils.ClickCaseSave(driver);
			Assert.assertTrue(Message.contains("You can edit these fields, but you cannot leave them blank."));
			test.log(LogStatus.INFO, "Removing Location Successfully");
			test.log(LogStatus.INFO, "Test:::: RemovingLocation() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Assertion Error!!!");
			test.log(LogStatus.FAIL, "Test:::: RemovingLocation() Completed as Fail");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
		extent.endTest(test);
		RemovingBillingProvider();
	}

	// @Test(priority=2,enabled=true)
	public void RemovingBillingProvider() throws InterruptedException {
		try {
			test = extent.startTest("Removing Billing Provider", "Test Case Information" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(@class,'patientHeaderNameLink')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='caseUnSaved']//input[@value='No']"), 5)) {
				System.out.println("Case Unsaved pop up appeared");
				Commons.screenshot();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseUnSaved']//input[@value='No']")));
				Commons.waitForLoad(driver);
			}
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.ClickCaseEdit(driver);
			ActionUtils.click(CasePage.CaseBillingProviderRemoveButton(driver));
			String Message = AddCaseUtils.ClickCaseSave(driver);
			Commons.Explicitwait();
			Assert.assertTrue(Message.contains("You can edit these fields, but you cannot leave them blank."));
			test.log(LogStatus.INFO, "Removing Billing Provider Successfully");
			test.log(LogStatus.INFO, "Test:::: RemovingBillingProvider() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Assertion Error!!!");
			test.log(LogStatus.FAIL, "Test:::: RemovingBillingProvider() Completed as Fail");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
		extent.endTest(test);
		RemovingDxCode();
	}

	// @Test(enabled=true,priority=1)
	public void RemovingDxCode() throws InterruptedException {
		try {
			test = extent.startTest("Removing Dx Code", "Test Case Information" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(@class,'patientHeaderNameLink')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='caseUnSaved']//input[@value='No']"), 5)) {
				System.out.println("Case Unsaved pop up appeared");
				Commons.screenshot();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseUnSaved']//input[@value='No']")));
				Commons.waitForLoad(driver);
			}
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			Commons.Explicitwait();
			AddCaseUtils.AddDXCode(driver);
			Commons.Explicitwait();
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.ClickCaseEdit(driver);
			ActionUtils.click(CasePage.CaseICD10CodeRemoveCheckbox(driver));
			ActionUtils.click(CasePage.DiagnosisDeleteButton(driver));
			Commons.Explicitwait();
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			Commons.Explicitwait();
			Assert.assertEquals(expectedMessage.trim(), "At least One diagnosis code is required");
			test.log(LogStatus.INFO, "Removing Dx Code Successfully");
			test.log(LogStatus.INFO, "Test:::: RemovingDxCode() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: RemovingDxCode() Completed as Fail" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}