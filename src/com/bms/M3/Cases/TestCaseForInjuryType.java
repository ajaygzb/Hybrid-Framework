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
import Utils.HandlingCalendars;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestCaseForInjuryType extends TestSetUp {
	@Test(priority = 1)
	public void InjuryTypeAsOther() {
		try {
			test = extent.startTest("Add Injury Type As Other", "Test Case Information" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.QuickAddpatient(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			ActionUtils.sendKeys(CasePage.CaseName(driver), "Injury Type As Other");
			ActionUtils.click(CasePage.InjuryTypeAsOther(driver));
			HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "14/05/2015");// Enter
																								// Assignment
																								// date
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015");// Enter
																									// Case
																									// effective
																									// date
			AddCaseUtils.ClickCaseSave(driver);
			System.out
					.println(driver
							.findElement(By
									.xpath("//select[@name='injuryType']//option[@label='Other'][@selected='selected']"))
							.isDisplayed());
			Assert.assertTrue(driver
					.findElement(By.xpath("//select[@name='injuryType']//option[@label='Other'][@selected='selected']"))
					.isDisplayed(), "Injury type as Other not found");
			test.log(LogStatus.INFO, "Case With Injury Type As Other Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: InjuryTypeAsOther() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: InjuryTypeAsOther() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.endTest(test);
		InjuryTypeAsNone();
	}

	// @Test( priority = 2 )
	public void InjuryTypeAsNone() {
		try {
			test = extent.startTest("Add Injury Type As None", "Test Case Information" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(@class,'patientHeaderNameLink')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			ActionUtils.sendKeys(CasePage.CaseName(driver), "Injury Type As None");
			ActionUtils.click(CasePage.InjuryTypeAsNone(driver));
			HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "14/05/2015");// Enter
																								// Assignment
																								// date
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015");// Enter
																									// Case
																									// effective
																									// date
			AddCaseUtils.ClickCaseSave(driver);
			Assert.assertTrue(driver
					.findElement(By.xpath("//select[@name='injuryType']//option[@label='None'][@selected='selected']"))
					.isDisplayed(), "Injury type as None not found");
			test.log(LogStatus.INFO, "Case With Injury Type As None Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: InjuryTypeAsNone() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: InjuryTypeAsNone() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.endTest(test);
		InjuryTypeAsEmploymentRelated();
	}

	// @Test( priority = 3 )
	public void InjuryTypeAsEmploymentRelated() {
		try {
			test = extent.startTest("Add Injury Type As Employment Related",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(@class,'patientHeaderNameLink')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			ActionUtils.sendKeys(CasePage.CaseName(driver), "Injury Type As Employment Related");
			ActionUtils.click(CasePage.InjuryTypeAsEmploymentRelated(driver));
			HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "14/05/2015");// Enter
																								// Assignment
																								// date
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015");// Enter
																									// Case
																									// effective
																									// date
			AddCaseUtils.ClickCaseSave(driver);
			Assert.assertTrue(driver
					.findElement(By
							.xpath("//select[@name='injuryType']//option[@label='Employment Related'][@selected='selected']"))
					.isDisplayed(), "Injury type as Employment Related not found");
			test.log(LogStatus.INFO, "Case With Injury Type As Employment Related Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: InjuryTypeAsEmploymentRelated() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: InjuryTypeAsEmploymentRelated() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.endTest(test);
		InjuryTypeAsAutoAccidentRelated();
	}

	// @Test( priority = 4 )
	public void InjuryTypeAsAutoAccidentRelated() {
		try {
			test = extent.startTest("Add Injury Type As Auto Accident Related",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(@class,'patientHeaderNameLink')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			ActionUtils.sendKeys(CasePage.CaseName(driver), "Injury Type As Auto Accident Related");
			ActionUtils.click(CasePage.InjuryTypeAsAutoAccidentRelated(driver));
			HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "14/05/2015");// Enter
																								// Assignment
																								// date
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015");// Enter
																									// Case
																									// effective
																									// date
			AddCaseUtils.ClickCaseSave(driver);
			Assert.assertTrue(driver
					.findElement(By
							.xpath("//select[@name='injuryType']//option[@label='Auto Accident Related'][@selected='selected']"))
					.isDisplayed(), "Injury type as Auto Accident Related not found");
			test.log(LogStatus.INFO, "Case With Injury Type As Auto Accident Related Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: InjuryTypeAsAutoAccidentRelated() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: InjuryTypeAsAutoAccidentRelated() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
