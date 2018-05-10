package com.bms.M11.EMR;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import UIMap.ExamTemplatesPage;
import UIMap.LoginPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.CreateNoteUtils;
import Utils.EMRUtils;
import Utils.SearchPatientUtils;

public class TestPhraseLibraries extends TestSetUp {
	/*
	 * *************Test Cases Covered***********
	 * 
	 * 
	 * Verify Add New Assesment Phrase Libraries and validate template appears
	 * on Assesment tab Library in A page Verify User can Edit/Delete Assesment
	 * Phrase Libraries Verify Assesment Phrase Libraries available in My exam
	 * template Assesment Link library page Verify Add New Diagnostic
	 * Observation Phrase Libraries and validate template appears on Diagnostic
	 * Observation tab Library in A page Verify User can Edit/Delete Diagnostic
	 * Observation Phrase Libraries Verify Diagnostic Observation Phrase
	 * Libraries available in My exam template Diagnostic Observation Link
	 * library page Verify Add New Examination Comments Phrase Libraries and
	 * validate template appears on Examination Comments tab Library in O page
	 * Verify User can Edit/Delete Examination Comments Phrase Libraries Verify
	 * Examination Comments Phrase Libraries available in My exam template
	 * Examination Comments Link library page Verify Add New FLR Justification
	 * Phrase Libraries and validate template appears on FLR tab in A page
	 * Verify User can Edit/Delete FLR Justification Phrase Libraries Verify Add
	 * New Goals Library and validate template appears on Goals tab in A page
	 * Verify User can Edit/Delete Goals Library Verify Goals Library available
	 * in My exam template Goals tab in Link library page Verify Add New
	 * Medication Comment Library and validate template appears on Medication
	 * tab in S page Verify User can Edit/Delete Medication Comment Library
	 * Verify Add New Negative Prognossis library and validate template appears
	 * on Prognosis tab Library in A page Verify User can Edit/Delete Negative
	 * Prognossis Library Verify Add New Non Visit library and validate template
	 * appears on Create Note for Non-visit page Verify User can Edit/Delete Non
	 * Visit library Verify Add New Patient Report library and validate template
	 * appears on Patient Report Library in S page Verify User can Edit/Delete
	 * Patient Report Library Verify Add New Positive Prognosis library and
	 * validate template appears in Assesment on A page Verify User can
	 * Edit/Delete Positive Prognosis Library Verify Add New Problems Libraries
	 * and validate template appears on Current Problems tab Library in S page
	 * Verify User can Edit/Delete Problems Phrase Libraries Verify Examination
	 * Comments Phrase Libraries available in My exam template Problem List Link
	 * library page Verify Add New Rehab Potential Comments library and validate
	 * template appears in Goals/Rehab Potential on A page Verify User can
	 * Edit/Delete Rehab Potential Comments Library Verify Add New Theraphy Cap
	 * Exception Justification library and validate template appears in
	 * Goals/Rehab Potential on A page Verify User can Edit/Delete Theraphy Cap
	 * Exception Justification Library Verify Add New Treatment Plan Library and
	 * validate template appears on Current Problems tab Library in P page
	 * Verify User can Edit/Delete Treatment Plan Library Verify Treatment Plan
	 * Library available in My exam template Treatment Plan Link library page
	 */
	public String PatientID = "Emrmedicare";

	@Test(enabled = true, priority = 1)
	public void TestAssessmentPhraseLibraries() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add New Assessment Phrase Libraries and validate template appears on Assessment tab Library in A page",
				"To Verify Add New Assessment Phrase Libraries and validate template appears on Assessment tab Library in A page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.QuickAddpatient(driver);
			PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
			System.out.println("Patient ID:" + PatientID);
			test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			EMRUtils.AddPhraseLibrary(driver, "Assessment Library");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			EMRUtils.AssertPhraseLibraryOnSOAP(driver, "clickAPAge", "Assessment", "Initial");
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 02
			test = extent.startTest(
					"To Verify Assesment Phrase Libraries available in My exam template Assesment Link library page",
					"To Verify Assesment Phrase Libraries available in My exam template Assesment Link library page"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.AssertPhraseLibraryOnMyExamTemplate(driver, "Assessment");
			System.out.println("*******************Assertion-4 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-4 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Assessment Phrase Libraries",
					"To Verify Edit/Delete Assessment Phrase Libraries" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Assesment");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Assessment Phrase Library Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true, "Test Assessment Library Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 2, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestDiagnosticObservationLibrary() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add New Diagnostic Observation Phrase Libraries and validate template appears on Diagnostic Observation tab Library in A page",
				"To Verify Add New Diagnostic Observation Phrase Libraries and validate template appears on Diagnostic Observation tab Library in A page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Diagnostic Observation Library");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			EMRUtils.AssertPhraseLibraryOnSOAP(driver, "clickAPAge", "Diagnosis", "Followup");
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 02
			test = extent.startTest(
					"To Verify Add New Diagnostic Observation Phrase Libraries available in My exam template Diagnostic Observations Link library page",
					"To Verify Add New Diagnostic Observation Phrase Libraries available in My exam template Diagnostic Observations Link library page"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.AssertPhraseLibraryOnMyExamTemplate(driver, "DiagnosticObservations");
			System.out.println("*******************Assertion-4 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-4 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Diagnostic Observations Phrase Libraries",
					"To Verify Edit/Delete Diagnostic Observations Phrase Libraries" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Diagnostic Observation Library");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Diagnostic Observation Phrase Libraries Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true,
					"Test Diagnostic Observation Phrase Libraries Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 3, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestExaminationCommentsLibrary() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add New Examination Comments Phrase Libraries and validate template appears on Diagnostic Observation tab Library in A page",
				"To Verify Add New Examination Comments Phrase Libraries and validate template appears on Diagnostic Observation tab Library in A page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Examination Comments");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			EMRUtils.AssertPhraseLibraryOnSOAP(driver, "ClickOPage", "Exam", "Followup");
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 02
			test = extent.startTest(
					"To Verify Add New Examination Comments Phrase Libraries available in My exam template Diagnostic Observations Link library page",
					"To Verify Add New Examination Comments Phrase Libraries available in My exam template Diagnostic Observations Link library page"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.AssertPhraseLibraryOnMyExamTemplate(driver, "ExaminationComments");
			System.out.println("*******************Assertion-4 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-4 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Examination Comments Phrase Libraries",
					"To Verify Edit/Delete Examination Comments Phrase Libraries" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Examination Comments");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Examination Comments Phrase Libraries Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true,
					"Test Examination Comments Phrase Libraries Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 4, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestFLRJustificationLibrary() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add New FLR Justification Phrase Libraries and validate template appears on Diagnostic Observation tab Library in A page",
				"To Verify Add New FLR Justification Phrase Libraries and validate template appears on Diagnostic Observation tab Library in A page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "FLR Justification");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			APageUtils.GotoFLR(driver);
			PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
			// EMRUtils.AssertPhraseLibraryOnSOAP(driver,"clickAPAge","Medicare-Functional-Limitation-Reporting","Followup");
			System.out.println("Opening Existing Note");
			APageUtils.GotoNoteList(driver, 1);
			// APageUtils.click(driver, "clickAPAge");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 60);
			Commons.waitforElement(driver, By.xpath("//a[@href='#Medicare-Functional-Limitation-Reporting']"), 80);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Medicare-Functional-Limitation-Reporting']")));
			System.out.println("Successfully redirected to SOAP Pages");
			System.out.println(" go to FLR justification Library");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath(
							"//table[contains(@library-files,'fLRJustificationLibraryList')]//select[contains(@ng-change,'libraryChange')]"),
					30);
			Commons.SelectElement(driver,
					By.xpath(
							"//table[contains(@library-files,'fLRJustificationLibraryList')]//select[contains(@ng-change,'libraryChange')]"),
					"Automation Phrase Library");
			Commons.waitForLoad(driver);
			Assert.assertFalse(driver
					.findElement(By
							.xpath("//table[contains(@library-files,'fLRJustificationLibraryList')]//select[contains(@ng-change,'libraryChange')]"))
					.getAttribute("value").isEmpty());
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete FLR Justification Phrase Libraries",
					"To Verify Edit/Delete FLR Justification Phrase Libraries" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "FLR Justification");
			System.out.println("*******************Assertion-4 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-4 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test FLR Justification Phrase Libraries Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true,
					"Test FLR Justification Phrase Libraries Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 5, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestGoalsLibrary() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add New Goals Libraries and validate template appears on Goals tab Library in A page",
				"To Verify Add New Goals Phrase Libraries and validate template appears on Goals tab Library in A page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Goals Library");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			EMRUtils.AssertPhraseLibraryOnSOAP(driver, "clickAPAge", "Goals-Rehab-Potential", "Followup");
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 02
			test = extent.startTest(
					"To Verify Goals Libraries available in My exam template Diagnostic Observations Link library page",
					"To Verify Goals Library available in My exam template Diagnostic Observations Link library page"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.AssertPhraseLibraryOnMyExamTemplate(driver, "Goals");
			System.out.println("*******************Assertion-4 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-4 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Goals Libraries",
					"To Verify Edit/Delete Examination Comments Phrase Libraries" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Goals Library");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Goals Libraries Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true, "Test Goals Libraries Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 6, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestMedicationCommentsLibrary() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add New Medication Comments Libraries and validate template appears on Medication tab Library in S page",
				"To Verify Add New Medication Comments Libraries and validate template appears on Medication tab Library in S page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Medication Comments");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			EMRUtils.AssertPhraseLibraryOnSOAP(driver, "ClickSPage", "Medications", "Followup");
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Goals Libraries",
					"To Verify Edit/Delete Examination Comments Phrase Libraries" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Medication Comments");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Medication Comments Library Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true, "Test Medication Comments Library Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 7, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestNegativePrognosisLibrary() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add New Negative Prognosis Library and validate template appears on Assesment tab in A page",
				"To Verify Add New Negative Prognosis Library and validate template appears on Assesment tab in A page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Negative Prognosis Library");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			// EMRUtils.AssertPhraseLibraryOnSOAP(driver,"ClickSPage","Medications","Followup");
			System.out.println("Opening Existing Note");
			APageUtils.GotoNoteList(driver, 1);
			// APageUtils.click(driver, "clickAPAge");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 60);
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Assessment']")));
			System.out.println("Successfully redirected to SOAP Pages");
			System.out.println(" go to Library Assesment");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath("(//*[@id='Assessment']//select[contains(@ng-change,'libraryChange')])[3]"), 30);
			Commons.SelectElement(driver,
					By.xpath("(//*[@id='Assessment']//select[contains(@ng-change,'libraryChange')])[3]"),
					"Automation Phrase Library");
			Commons.waitForLoad(driver);
			Assert.assertFalse(driver
					.findElement(By.xpath("(//*[@id='Assessment']//select[contains(@ng-change,'libraryChange')])[3]"))
					.getAttribute("value").isEmpty());
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Negative Prognosis Library",
					"To Verify Edit/Delete Negative Prognosis Library Phrase Libraries" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Negative Prognosis Library");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Negative Prognosis Library Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true, "Test Negative Prognosis Library Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 8, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestNonVisitLibrary() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add New Non Visit Library and validate template appears on Create Note Page for Non visit",
				"To Verify Add New Non Visit Library and validate template appears on Create Note Page for Non visit"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Non Visit Library");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			ActionUtils.click(CreateNotePage.AddClinicalNote(driver));
			System.out.println("Opened Create Note Screen");
			Commons.waitForLoad(driver);
			ActionUtils.click(CreateNotePage.CaseNameddl(driver));
			Thread.sleep(1000);
			Commons.waitForLoad(driver);
			System.out.println("Navigated to Create Notes");
			test.log(LogStatus.INFO, "Navigated to Create Notes");
			System.out.println("Setting Visit Type as Non Visit");
			test.log(LogStatus.INFO, "Setting Visit Type as Non Visit");
			CreateNotePage.VisitTypeddl(driver, "Non Visit");
			Commons.waitforElement(driver, By.xpath("//*[@id='dvNonVisitStatement']//select[@ng-model='libraryValue']"),
					60);
			Commons.screenshot();
			Commons.SelectElement(driver, By.xpath("//*[@id='dvNonVisitStatement']//select[@ng-model='libraryValue']"),
					"Automation Phrase Library");
			Assert.assertFalse(
					driver.findElement(By.xpath("//*[@id='dvNonVisitStatement']//select[@ng-model='libraryValue']"))
							.getAttribute("value").isEmpty());
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Non Visit Library",
					"To Verify Edit/Delete Non Visit Library" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Non Visit Library");
			System.out.println("*******************Assertion-4 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-4 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Non Visit Library Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true, "Test  Non Visit Library Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 9, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestPatientReportLibrary() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add New Patient Report Library and validate template appears on Patient Report tab in S page",
				"To Verify Add New Patient Report Library and validate template appears on Patient Report tab in S page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Patient Report Library");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			EMRUtils.AssertPhraseLibraryOnSOAP(driver, "ClickSPage", "PatientReport", "Followup");
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete  Patient Report Library",
					"To Verify Edit/Delete Patient Report Library" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Patient Report Library");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Patient Report Library Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true, "Test Patient Report Library Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 10, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestPositivePrognosisLibrary() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add New Positive Prognosis Library and validate template appears on Assesment tab in A page",
				"To Verify Add New Positive Prognosis Library and validate template appears on Assesment tab in A page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Positive Prognosis Library");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			// EMRUtils.AssertPhraseLibraryOnSOAP(driver,"ClickSPage","Medications","Followup");
			System.out.println("Opening Existing Note");
			APageUtils.GotoNoteList(driver, 1);
			// APageUtils.click(driver, "clickAPAge");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 60);
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Assessment']")));
			System.out.println("Successfully redirected to SOAP Pages");
			System.out.println(" go to Library Assesment");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath("(//*[@id='Assessment']//select[contains(@ng-change,'libraryChange')])[2]"), 30);
			Commons.SelectElement(driver,
					By.xpath("(//*[@id='Assessment']//select[contains(@ng-change,'libraryChange')])[2]"),
					"Automation Phrase Library");
			Commons.waitForLoad(driver);
			Assert.assertFalse(driver
					.findElement(By.xpath("(//*[@id='Assessment']//select[contains(@ng-change,'libraryChange')])[2]"))
					.getAttribute("value").isEmpty());
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Positive Prognosis Library",
					"To Verify Edit/Delete Positive Prognosis Library Phrase Libraries" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Positive Prognosis Library");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Positive Prognosis Library Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true, "Test Positive Prognosis Library Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 11, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestRehabPotentialComments() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add Rehab Potential Comments and validate template appears on Goals/Rehab tab in A page",
				"To Verify Add Rehab Potential Comments and validate template appears on Goals/Rehab tab in A page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Rehab Potential Comments");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			// EMRUtils.AssertPhraseLibraryOnSOAP(driver,"ClickSPage","Medications","Followup");
			System.out.println("Opening Existing Note");
			APageUtils.GotoNoteList(driver, 1);
			// APageUtils.click(driver, "clickAPAge");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 60);
			Commons.waitforElement(driver, By.xpath("//a[@href='#Goals-Rehab-Potential']"), 80);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Goals-Rehab-Potential']")));
			System.out.println("Successfully redirected to SOAP Pages");
			System.out.println(" go to Library Goal/Rehab Potential");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath(
							"(//*[@id='Goals-Rehab-Potential']//select[contains(@ng-change,'libraryChange(libraryValue')])[2]"),
					30);
			Commons.SelectElement(driver,
					By.xpath(
							"(//*[@id='Goals-Rehab-Potential']//select[contains(@ng-change,'libraryChange(libraryValue')])[2]"),
					"Automation Phrase Library");
			Commons.waitForLoad(driver);
			Assert.assertFalse(driver
					.findElement(By
							.xpath("(//*[@id='Goals-Rehab-Potential']//select[contains(@ng-change,'libraryChange(libraryValue')])[2]"))
					.getAttribute("value").isEmpty());
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Rehab Potential Comments",
					"To Verify Edit/Delete Rehab Potential Comments Phrase Libraries" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Rehab Potential Comments");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Rehab Potential Comments Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true, "Test Rehab Potential Comments Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 12, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestThreapyCapExceptionJustification() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add Threapy Cap Exception Justification and validate template appears on Goals/Rehab tab in A page",
				"To Verify Add Threapy Cap Exception Justification and validate template appears on Goals/Rehab tab in A page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Therapy Cap Exception Justification");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			// EMRUtils.AssertPhraseLibraryOnSOAP(driver,"ClickSPage","Medications","Followup");
			System.out.println("Opening Existing Note");
			APageUtils.GotoNoteList(driver, 1);
			// APageUtils.click(driver, "clickAPAge");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 60);
			Commons.waitforElement(driver, By.xpath("//a[contains(@href,'#Medicare-Functional-Limitation-Reporting')]"),
					80);
			ActionUtils.click(
					driver.findElement(By.xpath("//a[contains(@href,'#Medicare-Functional-Limitation-Reporting')]")));
			System.out.println("Successfully redirected to SOAP Pages");
			System.out.println(" go to Library Threaphy cap exception justification");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//h2[contains(.,'Therapy Cap')]//following::select[1]"), 30);
			Commons.SelectElement(driver, By.xpath("//h2[contains(.,'Therapy Cap')]//following::select[1]"),
					"Automation Phrase Library");
			Commons.waitForLoad(driver);
			Assert.assertFalse(driver.findElement(By.xpath("//h2[contains(.,'Therapy Cap')]//following::select[1]"))
					.getAttribute("value").isEmpty());
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Threapy Cap Exception Justification",
					"To Verify Edit/Delete Threapy Cap Exception Justification Phrase Libraries"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Therapy Cap Exception Justification");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true,
					"TestThreapy Cap Exception Justification Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true,
					"Test Threapy Cap Exception Justification Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 13, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestProblemsLibraries() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add Problems Libraries and validate template appears on Current Problems tab Library in S page",
				"To Verify Add Problems Libraries and validate template appears on Current Problems tab Library in S page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Problems");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			EMRUtils.AssertPhraseLibraryOnSOAP(driver, "ClickSPage", "CurrentProblems", "Followup");
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 02
			test = extent.startTest(
					"To Verify Problems Libraries available in My exam template Problem List Link library page",
					"To Verify Problems Libraries available in My exam template Problem List Link library page"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.AssertPhraseLibraryOnMyExamTemplate(driver, "ProblemList");
			System.out.println("*******************Assertion-4 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-4 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Problem List Phrase Libraries",
					"To Verify Edit/Delete Problem List Phrase Libraries" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Problems");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Problem List Phrase Library Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true, "Test Problem List Library Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 14, dependsOnMethods = { "TestAssessmentPhraseLibraries" })
	public void TestTreatmentPlanLibrary() throws InterruptedException {
		test = extent.startTest(
				"To Verify Add New Treatment Plan Library and validate template appears on Planned Intervention tab Library in P page",
				"To Verify Add New Treatment Plan Library and validate template appears on Planned Intervention tab Library in P page"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			if (PatientID == null) {
				AddPatientUtils.QuickAddpatient(driver);
				PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
				System.out.println("Patient ID:" + PatientID);
				test.log(LogStatus.INFO, "Patient ID:" + PatientID);
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			}
			EMRUtils.AddPhraseLibrary(driver, "Treatment Plan Library");
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-2 Pass**********");
			System.out.println("*******************Phrase Library added **********");
			Commons.screenshot();
			// EMRUtils.AssertPhraseLibraryOnSOAP(driver,"ClickPPage","Planned-Interventions","Followup");
			System.out.println("Opening Existing Note");
			APageUtils.GotoNoteList(driver, 1);
			// APageUtils.click(driver, "clickAPAge");
			APageUtils.click(driver, "ClickPPage");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 60);
			Commons.waitforElement(driver, By.xpath("//a[@href='#Planned-Interventions']"), 80);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Planned-Interventions']")));
			System.out.println("Successfully redirected to SOAP Pages");
			System.out.println(" go to Library Planned Intervention");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath("//*[@id='Planned-Interventions']//select[contains(@ng-change,'libraryChange')]"), 30);
			Commons.SelectElement(driver,
					By.xpath("//*[@id='Planned-Interventions']//select[contains(@ng-change,'libraryChange')]"),
					"Automation Phrase Library");
			Commons.waitForLoad(driver);
			Assert.assertFalse(driver
					.findElement(
							By.xpath("//*[@id='Planned-Interventions']//select[contains(@ng-change,'libraryChange')]"))
					.getAttribute("value").isEmpty());
			System.out.println("*******************Assertion-3 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-3 Pass**********");
			extent.endTest(test);
			Commons.screenshot();
			// Test 02
			test = extent.startTest(
					"To Verify Treatment plan Libraries available in My exam template Treatment plan List Link library page",
					"To Verify Treatment plan Libraries available in My exam template Treatment plan List Link library page"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.AssertPhraseLibraryOnMyExamTemplate(driver, "Treatmentplan");
			System.out.println("*******************Assertion-4 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-4 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
			// Test 03
			test = extent.startTest("To Verify Edit/Delete Treatment plan Phrase Libraries",
					"To Verify Edit/Delete Treatment plan Phrase Libraries" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			EMRUtils.DeletePhraseLibrary(driver, "Treatment Plan Library");
			System.out.println("*******************Assertion-6 Pass**********");
			test.log(LogStatus.INFO, "*******************Assertion-6 Pass**********");
			Commons.screenshot();
			extent.endTest(test);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Treatment plan Phrase Library Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true, "TestTreatment plan Library Failed" + Throwables.getStackTraceAsString(a));
		}
	}
}
