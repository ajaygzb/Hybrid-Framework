package com.bms.M10.SPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.SPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.Commons;
import Utils.SPageUtils;

public class TestCaseSpageR extends TestSetUp {
	public String strActualtext;

	@Test(enabled = true, priority = 1)
	public void required_field_indicators_initial_visit() throws InterruptedException {
		test = extent.startTest("To Validate Required Field indicators of initial note visit of S page",
				"Test required_field_indicators_initial_visit for S page " + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		strActualtext = SPageUtils.NavigateToSpage(driver);
		test.log(LogStatus.INFO, "Added a Patient with ID" + strActualtext);
		test.log(LogStatus.INFO, "Navigated To S Page");
		try {
			System.out.println("checking number of required field under Patient Report");
			Assert.assertNull(SPage.PatientReportRedDot(driver));
			System.out.println("No required field under Patient Reoprt");
			test.log(LogStatus.INFO, "No required field under Patient Reoprt");
			System.out.println("checking number of required field under Current problem");
			String count = SPage.CurrentProblemsRedDot(driver).getText();
			System.out.println("number of required field under current problems are " + count);
			Assert.assertEquals("1", count);
			test.log(LogStatus.INFO, "number of required field under current problems are " + count);
			System.out.println("checking number of required field under Safety considerartions");
			count = SPage.SafetyConsiderationsRedDot(driver).getText();
			System.out.println("number of required field under Safety considerartions are " + count);
			Assert.assertEquals("3", count);
			test.log(LogStatus.INFO, "number of required field under Safety considerartions are " + count);
			System.out.println("checking number of required field under  medication");
			count = SPage.MedicationsRedDot(driver).getText();
			System.out.println("number of required field under medication are " + count);
			Assert.assertEquals("1", count);
			test.log(LogStatus.INFO, "number of required field under medication are " + count);
			System.out.println("checking number of required field under  History");
			Assert.assertNull(SPage.HistoryRedDot(driver));
			System.out.println("No required field under History");
			test.log(LogStatus.INFO, "No required field under History");
			System.out
					.println("Validation of  Required Field indicators of initial note visit on S page is successful");
			test.log(LogStatus.INFO,
					"Validation of Required Field indicators of initial note visit on S page is successful");
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void required_field_indicators_Followup_visit() throws InterruptedException {
		test = extent.startTest("To Validate required field indicators Followup_visit on S page",
				"Test required_field_indicators_Followup_visit for S Page" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		SPageUtils.addPatientWithFullCase(driver);
		try {
			SPageUtils.creatingInitialnoteWithAllSMandatory(driver);
			SPageUtils.creatingfollowupNote(driver);
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='therapyradio']")));
			Commons.waitForLoad(driver);
			driver.findElement(By.xpath("//a[@data-ng-click='redirectToSubjective()']")).click();
			Commons.waitForLoad(driver);
			System.out.println("checking number of required field under Patient Report");
			Assert.assertNull(SPage.PatientReportRedDot(driver));
			System.out.println("No required field under Patient Reoprt");
			test.log(LogStatus.INFO, "No required field under Patient Reoprt");
			System.out.println("checking number of required field under Current problem");
			Assert.assertNull(SPage.CurrentProblemsRedDot(driver));
			System.out.println("No required field under current problems");
			System.out.println("checking carry forward data in current problem");
			test.log(LogStatus.INFO, "No required field under current problems");
			String testfieldStatus = SPage.ProblemTextBox(driver).getAttribute("disabled");
			Assert.assertEquals(testfieldStatus, "true");
			System.out.println("carry forward data in current problem is pass");
			test.log(LogStatus.INFO, "carry forward data in current problem is pass");
			System.out.println("checking number of required field under Safety considerartions");
			Assert.assertNull(SPage.SafetyConsiderationsRedDot(driver));
			System.out.println("No required field under Safety considerartions");
			test.log(LogStatus.INFO, "No required field under Safety considerartions");
			System.out.println("checking passed data  under Safety considerartions");
			Assert.assertTrue(SPage.ReDFlagSafety(driver).isSelected());
			Assert.assertTrue(SPage.AlergySafety(driver).isSelected());
			Assert.assertTrue(SPage.PrecautionsFlag(driver).isSelected());
			System.out.println("carry forward data  under Safety considerartions is correct");
			test.log(LogStatus.INFO, "carry forward data  under Safety considerartions is correct");
			System.out.println("checking number of required field under  medication");
			Assert.assertNull(SPage.MedicationsRedDot(driver));
			System.out.println("No required field under Medication ");
			test.log(LogStatus.INFO, "No required field under Medication");
			System.out.println("checking passed data  under Medication");
			Assert.assertTrue(
					driver.findElement(By.xpath("//ng-form[@id='medicationsForm']//input[@value='1']")).isSelected());
			System.out.println(" carry forward  under Medication is pass");
			test.log(LogStatus.INFO, "carry forward data  under Medication is pass");
			System.out.println("checking number of required field under  History");
			Assert.assertNull(SPage.HistoryRedDot(driver));
			System.out.println("No required field under History");
			test.log(LogStatus.INFO, "No required field under History");
			System.out.println("Validation of required field indicators Followup up on S page is successful");
			test.log(LogStatus.INFO, "Validation of required field indicators Followup up on S page is successful");
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void required_field_indicators_FollowupReEvaluationvisit() throws InterruptedException {
		test = extent.startTest("To Validate required field indicators Followup Re Evaluationvisit on S page",
				"Test required_field_indicators_FollowupReEcaluationvisit for S Page" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		SPageUtils.addPatientWithFullCase(driver);
		try {
			SPageUtils.creatingInitialnoteWithAllSMandatory(driver);
			SPageUtils.creatingfollowupReEvaluationNote(driver);
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='therapyradio']")));
			Commons.waitForLoad(driver);
			driver.findElement(By.xpath("//a[@data-ng-click='redirectToSubjective()']")).click();
			Commons.waitForLoad(driver);
			System.out.println("checking number of required field under Patient Report");
			Assert.assertNull(SPage.PatientReportRedDot(driver));
			System.out.println("No required field under Patient Reoprt");
			test.log(LogStatus.INFO, "No required field under Patient Reoprt");
			System.out.println("checking number of required field under Current problem");
			Assert.assertNull(SPage.CurrentProblemsRedDot(driver));
			System.out.println("No required field under current problems");
			System.out.println("checking carry forward data in current problem");
			test.log(LogStatus.INFO, "No required field under current problems");
			String testfieldStatus = SPage.ProblemTextBox(driver).getAttribute("disabled");
			Assert.assertEquals(testfieldStatus, "true");
			System.out.println("carry forward data in current problem is pass");
			test.log(LogStatus.INFO, "carry forward data in current problem is pass");
			System.out.println("checking number of required field under Safety considerartions");
			Assert.assertNull(SPage.SafetyConsiderationsRedDot(driver));
			System.out.println("No required field under Safety considerartions");
			test.log(LogStatus.INFO, "No required field under Safety considerartions");
			System.out.println("checking passed data  under Safety considerartions");
			Assert.assertTrue(SPage.ReDFlagSafety(driver).isSelected());
			Assert.assertTrue(SPage.AlergySafety(driver).isSelected());
			Assert.assertTrue(SPage.PrecautionsFlag(driver).isSelected());
			System.out.println("carry forward data  under Safety considerartions is correct");
			test.log(LogStatus.INFO, "carry forward data  under Safety considerartions is correct");
			System.out.println("checking number of required field under  medication");
			Assert.assertNull(SPage.MedicationsRedDot(driver));
			System.out.println("No required field under Medication ");
			test.log(LogStatus.INFO, "No required field under Medication");
			System.out.println("checking passed data  under Medication");
			Assert.assertTrue(
					driver.findElement(By.xpath("//ng-form[@id='medicationsForm']//input[@value='1']")).isSelected());
			System.out.println(" carry forward  under Medication is pass");
			test.log(LogStatus.INFO, "carry forward data  under Medication is pass");
			System.out.println("checking number of required field under  History");
			Assert.assertNull(SPage.HistoryRedDot(driver));
			System.out.println("No required field under History");
			test.log(LogStatus.INFO, "No required field under History");
			System.out.println(
					"Validation of required field indicators Followup Re Evaluationvisit on S page is successful");
			test.log(LogStatus.INFO,
					"Validation of required field indicators Followup Re Evaluationvisit on S page is successful");
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void required_field_indicators_FollowupDischarge_visit() throws InterruptedException {
		test = extent.startTest("To Validate required field indicators Followup discharge visit on S page",
				"Test required_field_indicators_Followupdischargevisit for S Page" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		SPageUtils.addPatientWithFullCase(driver);
		try {
			SPageUtils.creatingInitialnoteWithAllSMandatory(driver);
			SPageUtils.creatingfollowupDischargeNote(driver);
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='therapyradio']")));
			Commons.waitForLoad(driver);
			driver.findElement(By.xpath("//a[@data-ng-click='redirectToSubjective()']")).click();
			Commons.waitForLoad(driver);
			System.out.println("checking number of required field under Patient Report");
			Assert.assertNull(SPage.PatientReportRedDot(driver));
			System.out.println("No required field under Patient Reoprt");
			test.log(LogStatus.INFO, "No required field under Patient Reoprt");
			System.out.println("checking number of required field under Current problem");
			Assert.assertNull(SPage.CurrentProblemsRedDot(driver));
			System.out.println("No required field under current problems");
			System.out.println("checking carry forward data in current problem");
			test.log(LogStatus.INFO, "No required field under current problems");
			String testfieldStatus = SPage.ProblemTextBox(driver).getAttribute("disabled");
			Assert.assertEquals(testfieldStatus, "true");
			System.out.println("carry forward data in current problem is pass");
			test.log(LogStatus.INFO, "carry forward data in current problem is pass");
			System.out.println("checking number of required field under Safety considerartions");
			Assert.assertNull(SPage.SafetyConsiderationsRedDot(driver));
			System.out.println("No required field under Safety considerartions");
			test.log(LogStatus.INFO, "No required field under Safety considerartions");
			System.out.println("checking passed data  under Safety considerartions");
			Assert.assertTrue(SPage.ReDFlagSafety(driver).isSelected());
			Assert.assertTrue(SPage.AlergySafety(driver).isSelected());
			Assert.assertTrue(SPage.PrecautionsFlag(driver).isSelected());
			System.out.println("carry forward data  under Safety considerartions is correct");
			test.log(LogStatus.INFO, "carry forward data  under Safety considerartions is correct");
			System.out.println("checking number of required field under  medication");
			Assert.assertNull(SPage.MedicationsRedDot(driver));
			System.out.println("No required field under Medication ");
			test.log(LogStatus.INFO, "No required field under Medication");
			System.out.println("checking passed data  under Medication");
			Assert.assertTrue(
					driver.findElement(By.xpath("//ng-form[@id='medicationsForm']//input[@value='1']")).isSelected());
			System.out.println(" carry forward  under Medication is pass");
			test.log(LogStatus.INFO, "carry forward data  under Medication is pass");
			System.out.println("checking number of required field under  History");
			Assert.assertNull(SPage.HistoryRedDot(driver));
			System.out.println("No required field under History");
			test.log(LogStatus.INFO, "No required field under History");
			System.out.println("Validation of required field indicators Followup Discharge on S page is successful");
			test.log(LogStatus.INFO,
					"Validation of required field indicators Followup Discharge on S page is successful");
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
