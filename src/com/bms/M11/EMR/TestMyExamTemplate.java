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
import Utils.SearchPatientUtils;

public class TestMyExamTemplate extends TestSetUp {
	// TC- To Verify Add,Edit,Delete My Exam template.
	// TC- To Linked,Unlinked My Exam template.
	// TC- To Verify Error messages on Link,Edit,Delete My Exam template without
	// select from check box.
	@Test(enabled = true, priority = 1)
	public void Add_Edit_Delete_MyExamTemplate() throws InterruptedException {
		test = extent.startTest("To Verify Add,Edit,Delete My Exam template.",
				"To Verify Add,Edit,Delete My Exam template." + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			ActionUtils.click(ExamTemplatesPage.MyExamTemplate(driver));
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'My Exam Templates')]"), 60);
			Commons.screenshot();
			// Delete All Existing Template (if any)
			// Delete Exam Template
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='viewExamTemplate']//tbody//tr[1]//input[@type='checkbox']"), 5)) {
				ActionUtils.click(driver
						.findElement(By.xpath("//button[contains(.,'Delete')][contains(@ng-click,'RemoveTemplate')]")));
				Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Please select to delete."));
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkSelecAll']")));
				ActionUtils.click(driver
						.findElement(By.xpath("//button[contains(.,'Delete')][contains(@ng-click,'RemoveTemplate')]")));
				Commons.waitforElement(driver, By.xpath("//*[@id='myModalLabel'][contains(.,'Confirm')]"), 40);
				Assert.assertTrue(ActionUtils
						.getText(driver.findElement(
								By.xpath("//*[@id='confirmdelete']//p[contains(.,'Do you wish to remove')]")))
						.contains("Do you wish to remove the selected template library ?"));
				Commons.screenshot();
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='confirmdelete']//button[contains(.,'Delete')]")));
				Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Template Removed"));
				Commons.waitforElement(driver, By.xpath("//p[contains(.,'No Record Found')]"), 30);
				Commons.screenshot();
			}
			// Add a new My Exam Template
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='createTemplateBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='CreateTemplate']//h4[contains(.,'New Exam Template')]"),
					60);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='CreateTemplate']//button[contains(.,'Create')]")));
			Assert.assertTrue(
					Commons.capturemessage(driver, 30).contains("Please select a body part and enter a Name."));
			Commons.SelectElement(driver, By.xpath("//*[@id='bodyPartsDropDown']"), "Ankle Sprain");
			ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='templateNameTxt']")), "Automation");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='CreateTemplate']//button[contains(.,'Create')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Return to My Exam Templates List')]"), 60);
			Commons.screenshot();
			// Edit Template Name
			ActionUtils.click(
					driver.findElement(By.xpath("//button[contains(.,'Edit')][contains(@class,'editTemplate')]")));
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='templateName']")));
			driver.findElement(By.xpath("//input[@id='templateName']")).clear();
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='templateName']")), "Automation123");
			ActionUtils.click(
					driver.findElement(By.xpath("//button[contains(.,'Save')][contains(@class,'saveTemplateTab')]")));
			Assert.assertTrue(driver.findElement(By.xpath("//input[@id='templateName']")).getAttribute("value")
					.contains("Automation123"));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Return to My Exam Templates List')]")));
			// Delete Exam Template
			ActionUtils.click(driver
					.findElement(By.xpath("//button[contains(.,'Delete')][contains(@ng-click,'RemoveTemplate')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Please select to delete."));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkSelecAll']")));
			ActionUtils.click(driver
					.findElement(By.xpath("//button[contains(.,'Delete')][contains(@ng-click,'RemoveTemplate')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='myModalLabel'][contains(.,'Confirm')]"), 40);
			Assert.assertTrue(ActionUtils
					.getText(driver
							.findElement(By.xpath("//*[@id='confirmdelete']//p[contains(.,'Do you wish to remove')]")))
					.contains("Do you wish to remove the selected template library ?"));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='confirmdelete']//button[contains(.,'Delete')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Template Removed"));
			Commons.waitforElement(driver, By.xpath("//p[contains(.,'No Record Found')]"), 30);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test To Verify Add,Edit,Delete My Exam template. Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true,
					"Test To Verify Add,Edit,Delete My Exam template. Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 2)
	public void LinkMyExamTemplate() throws InterruptedException {
		test = extent.startTest("To Verify User can Link/Unlink My Exam template.",
				"To Verify User can Link/Unlink My Exam template." + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			ActionUtils.click(ExamTemplatesPage.MyExamTemplate(driver));
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'My Exam Templates')]"), 60);
			Commons.screenshot();
			// Delete All Existing Template (if any)
			// Delete Exam Template
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='viewExamTemplate']//tbody//tr[1]//input[@type='checkbox']"), 5)) {
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkSelecAll']")));
				ActionUtils.click(driver
						.findElement(By.xpath("//button[contains(.,'Delete')][contains(@ng-click,'RemoveTemplate')]")));
				Commons.waitforElement(driver, By.xpath("//*[@id='myModalLabel'][contains(.,'Confirm')]"), 40);
				Assert.assertTrue(ActionUtils
						.getText(driver.findElement(
								By.xpath("//*[@id='confirmdelete']//p[contains(.,'Do you wish to remove')]")))
						.contains("Do you wish to remove the selected template library ?"));
				Commons.screenshot();
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='confirmdelete']//button[contains(.,'Delete')]")));
				Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Template Removed"));
				Commons.waitforElement(driver, By.xpath("//p[contains(.,'No Record Found')]"), 30);
				Commons.screenshot();
			}
			// Add a new My Exam Template
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='createTemplateBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='CreateTemplate']//h4[contains(.,'New Exam Template')]"),
					60);
			Commons.SelectElement(driver, By.xpath("//*[@id='bodyPartsDropDown']"), "Ankle Sprain");
			ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='templateNameTxt']")), "Automation");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='CreateTemplate']//button[contains(.,'Create')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Return to My Exam Templates List')]"), 60);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Return to My Exam Templates List')]")));
			// Link Template
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Link Selected')]"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Link Selected')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 40).contains("You must select one check box."));
			ActionUtils.click(driver.findElement(By.xpath("(//*[@id='viewExamTemplate']//tbody//input)[1]")));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Link Selected')]")));
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Edit Template Links')]"), 60);
			Commons.screenshot();
			// Add Libraries to My Template
			// Adding Interventions
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'InterventionsExamTemplate')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='interventionLibraryPublicRadio']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='interventionLibraryPublicRadio']")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='interventionsDetails']/table/tbody/tr[2]/td[3]/input[@type='checkbox']"), 10);
			// Commons.waitforElement(driver,
			// By.xpath("//*[@id='interventionsSelectedDetails']//span[contains(.,'No
			// records found')]"),30);
			ActionUtils.click(driver.findElement(
					By.xpath("//*[@id='interventionsDetails']/table/tbody/tr[2]/td[3]/input[@type='checkbox']")));
			ActionUtils.click(driver
					.findElement(By.xpath("//*[@id='InterventionsExamTemplate']//button[contains(.,'Add Selected')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='interventionsSelectedDetails']//tbody//tr"), 40);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Added Interventions Library Successfully*******");
			System.out.println("*******Added Interventions Library Successfully*******");
			// Adding Assesment
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'Assessment')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='assesmentPublic']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='assesmentPublic']")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='assesment']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']"),
					10);
			// Commons.waitforElement(driver,
			// By.xpath("//*[@id='interventionsSelectedDetails']//span[contains(.,'No
			// records found')]"),30);
			ActionUtils.click(driver.findElement(By
					.xpath("//*[@id='assesment']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']")));
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='Assessment']//button[contains(.,'Add Selected')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='assesmentSelected']//tbody//tr"), 40);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Added Assesment Library Successfully*******");
			System.out.println("*******Added Assesment Library Successfully*******");
			// Adding Diagnostic Observations
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'DiagnosticObservations')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='diagnosticPublic']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='diagnosticPublic']")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='diagnostic']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']"),
					10);
			// Commons.waitforElement(driver,
			// By.xpath("//*[@id='interventionsSelectedDetails']//span[contains(.,'No
			// records found')]"),30);
			ActionUtils.click(driver.findElement(By
					.xpath("//*[@id='diagnostic']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']")));
			ActionUtils.click(driver
					.findElement(By.xpath("//*[@id='DiagnosticObservations']//button[contains(.,'Add Selected')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='diagnosticSelected']//tbody//tr"), 40);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Added Diagnostic Observations Library Successfully*******");
			System.out.println("*******Added Diagnostic Observations Library Successfully*******");
			// Adding Goals
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'Goals')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='goalsPublic']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='goalsPublic']")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='goals']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']"), 10);
			ActionUtils.click(driver.findElement(
					By.xpath("//*[@id='goals']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='Goals']//button[contains(.,'Add Selected')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='goalsSelected']//tbody//tr"), 40);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Added Goals Library Successfully*******");
			System.out.println("*******Added Goals Library Successfully*******");
			// Adding ProblemList
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'ProblemList')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='problemListPublic']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='problemListPublic']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='problemList']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']"),
					10);
			ActionUtils.click(driver.findElement(By.xpath(
					"//*[@id='problemList']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']")));
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='ProblemList']//button[contains(.,'Add Selected')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='problemListSelected']//tbody//tr"), 40);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Added ProblemList Library Successfully*******");
			System.out.println("*******Added ProblemList Library Successfully*******");
			// Adding Treatment plan
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'Treatmentplan')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='treatmentPublic']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='treatmentPublic']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='treatmentPlan']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']"),
					10);
			ActionUtils.click(driver.findElement(By.xpath(
					"//*[@id='treatmentPlan']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']")));
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='Treatmentplan']//button[contains(.,'Add Selected')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='treatmentPlanSelected']//tbody//tr"), 40);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Added Treatment plan Library Successfully*******");
			System.out.println("*******Added Treatment plan Library Successfully*******");
			// Adding Examination Comments
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'ExaminationComments')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='examinationPublic']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='examinationPublic']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='examinationComments']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']"),
					10);
			ActionUtils.click(driver.findElement(By.xpath(
					"//*[@id='examinationComments']/tbody[1]/tr[1]/td/table/tbody/tr[1]/td[1]/input[@type='checkbox']")));
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='ExaminationComments']//button[contains(.,'Add Selected')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='examinationCommentsSelected']//tbody//tr"), 40);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Added Examination Comments Library Successfully*******");
			System.out.println("*******Added Examination Comments Library Successfully*******");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='saveAllLinkBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='returnToListBtn']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='returnToListBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='viewExamTemplate']//td[contains(.,'Yes')]"), 60);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******My Exam Templates Linked successfully*******");
			System.out.println("My Exam Templates Linked successfully");
			Commons.screenshot();
			// Case-2
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			ActionUtils.click(ExamTemplatesPage.MyExamTemplate(driver));
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'My Exam Templates')]"), 60);
			Commons.screenshot();
			test.log(LogStatus.INFO, "Remove Linked library from each tab");
			// Open MET
			ActionUtils.click(driver.findElement(By.xpath("(//*[@id='viewExamTemplate']//tbody//input)[1]")));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Link Selected')]")));
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Edit Template Links')]"), 60);
			Commons.screenshot();
			// Removing Interventions
			test.log(LogStatus.INFO, "**Removing Linked library from Intervention Tab**");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'#InterventionsExamTemplate')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='interventionsSelectedDetails']//input[@type='checkbox']"),
					130);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='interventionsSelectedDetails']//input[@type='checkbox']")));
			ActionUtils.click(driver
					.findElement(By.xpath("//*[@id='InterventionsExamTemplate']//button[contains(.,'Remove All')]")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='interventionsSelectedDetails']//span[contains(.,'No records found')]"), 20);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Removed Interventions Library Successfully*******");
			System.out.println("*******Removed Interventions Library Successfully*******");
			// Removing Assesment
			test.log(LogStatus.INFO, "**Removing Linked library from Assesment Tab**");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'#Assessment')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='assesmentSelected']//input[@type='checkbox']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='assesmentSelected']//input[@type='checkbox']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='Assessment']//button[contains(.,'Remove All')]")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='assesmentSelected']//span[contains(.,'No records found')]"), 20);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Removed Assesment Library Successfully*******");
			System.out.println("*******Removed Assesment Library Successfully*******");
			// Removing Diagnostic Observation
			test.log(LogStatus.INFO, "**Removing Linked library from Diagnostic Observation Tab**");
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#DiagnosticObservations']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='diagnosticSelected']//input[@type='checkbox']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='diagnosticSelected']//input[@type='checkbox']")));
			ActionUtils.click(driver
					.findElement(By.xpath("//*[@id='DiagnosticObservations']//button[contains(.,'Remove All')]")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='diagnosticSelected']//span[contains(.,'No records found')]"), 20);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Removed Diagnostic Observation Library Successfully*******");
			System.out.println("*******Removed Diagnostic Observation Library Successfully*******");
			// Removing Goals
			test.log(LogStatus.INFO, "**Removing Linked library from Goals Tab**");
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Goals']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='goalsSelected']//input[@type='checkbox']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='goalsSelected']//input[@type='checkbox']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='Goals']//button[contains(.,'Remove All')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='goalsSelected']//span[contains(.,'No records found')]"),
					20);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Removed Goals Library Successfully*******");
			System.out.println("*******Removed Goals Library Successfully*******");
			// Removing Problem List
			test.log(LogStatus.INFO, "**Removing Linked library from Problem List Tab**");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'#ProblemList')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='problemListSelected']//input[@type='checkbox']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='problemListSelected']//input[@type='checkbox']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ProblemList']//button[contains(.,'Remove All')]")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='problemListSelected']//span[contains(.,'No records found')]"), 20);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Removed Problem List Library Successfully*******");
			System.out.println("*******Removed Problem List Library Successfully*******");
			// Removing Treatment Plan
			test.log(LogStatus.INFO, "**Removing Linked library from Treatment Plan Tab**");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'#Treatmentplan')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='treatmentPlanSelected']//input[@type='checkbox']"), 30);
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='treatmentPlanSelected']//input[@type='checkbox']")));
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='Treatmentplan']//button[contains(.,'Remove All')]")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='treatmentPlanSelected']//span[contains(.,'No records found')]"), 20);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Removed Treatment Plan Library Successfully*******");
			System.out.println("*******Removed Treatment Plan Library Successfully*******");
			// Removing Examination Comments
			test.log(LogStatus.INFO, "**Removing Linked library from Examination Comments Tab**");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'#ExaminationComments')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='examinationCommentsSelected']//input[@type='checkbox']"),
					30);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='examinationCommentsSelected']//input[@type='checkbox']")));
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='ExaminationComments']//button[contains(.,'Remove All')]")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='examinationCommentsSelected']//span[contains(.,'No records found')]"), 20);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******Removed Examination Comments Library Successfully*******");
			System.out.println("*******Removed Examination Comments Library Successfully*******");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='saveAllLinkBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='returnToListBtn']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='returnToListBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='viewExamTemplate']//td[contains(.,'No')]"), 60);
			Commons.screenshot();
			test.log(LogStatus.INFO, "*******My Exam Templates Un-Linked successfully*******");
			System.out.println("My Exam Templates Un-Linked successfully");
			Commons.screenshot();
			// Delete Exam Template
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkSelecAll']")));
			ActionUtils.click(driver
					.findElement(By.xpath("//button[contains(.,'Delete')][contains(@ng-click,'RemoveTemplate')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='myModalLabel'][contains(.,'Confirm')]"), 40);
			Assert.assertTrue(ActionUtils
					.getText(driver
							.findElement(By.xpath("//*[@id='confirmdelete']//p[contains(.,'Do you wish to remove')]")))
					.contains("Do you wish to remove the selected template library ?"));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='confirmdelete']//button[contains(.,'Delete')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Template Removed"));
			Commons.waitforElement(driver, By.xpath("//p[contains(.,'No Record Found')]"), 30);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Test To Verify User can Link/Unlink My Exam template. Failed"
					+ Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true, "Test To Verify User can Link/Unlink My Exam template. Failed"
					+ Throwables.getStackTraceAsString(a));
		}
	}

	@Test(enabled = true, priority = 3)
	public void AddPublicMyExamTemplate() throws InterruptedException {
		test = extent.startTest("To Verify Add My Exam template as public", "To Verify Add My Exam template as public"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			ActionUtils.click(ExamTemplatesPage.MyExamTemplate(driver));
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'My Exam Templates')]"), 60);
			Commons.screenshot();
			// Delete All Existing Template (if any)
			// Delete Exam Template
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='viewExamTemplate']//tbody//tr[1]//input[@type='checkbox']"), 5)) {
				ActionUtils.click(driver
						.findElement(By.xpath("//button[contains(.,'Delete')][contains(@ng-click,'RemoveTemplate')]")));
				Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Please select to delete."));
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkSelecAll']")));
				ActionUtils.click(driver
						.findElement(By.xpath("//button[contains(.,'Delete')][contains(@ng-click,'RemoveTemplate')]")));
				Commons.waitforElement(driver, By.xpath("//*[@id='myModalLabel'][contains(.,'Confirm')]"), 40);
				Assert.assertTrue(ActionUtils
						.getText(driver.findElement(
								By.xpath("//*[@id='confirmdelete']//p[contains(.,'Do you wish to remove')]")))
						.contains("Do you wish to remove the selected template library ?"));
				Commons.screenshot();
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='confirmdelete']//button[contains(.,'Delete')]")));
				Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Template Removed"));
				Commons.waitforElement(driver, By.xpath("//p[contains(.,'No Record Found')]"), 30);
				Commons.screenshot();
			}
			// Add a new My Exam Template
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='createTemplateBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='CreateTemplate']//h4[contains(.,'New Exam Template')]"),
					60);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='CreateTemplate']//button[contains(.,'Create')]")));
			Assert.assertTrue(
					Commons.capturemessage(driver, 30).contains("Please select a body part and enter a Name."));
			Commons.SelectElement(driver, By.xpath("//*[@id='bodyPartsDropDown']"), "Ankle Sprain");
			ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='templateNameTxt']")), "Automation public");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='publicCheck']")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='CreateTemplate']//button[contains(.,'Create')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Return to My Exam Templates List')]"), 60);
			Commons.screenshot();
			SearchPatientUtils.QuickpatientSearch(driver, "Automate");
			CreateNoteUtils.NavigatetoCreateNote(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='exam-templates']//span[contains(.,'Automation public')]"),
					60);
			System.out.println("****************Assertion-1 Pass**************");
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			ActionUtils.click(ExamTemplatesPage.MyExamTemplate(driver));
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'My Exam Templates')]"), 60);
			Commons.waitforElement(driver, By.xpath("//*[@id='viewExamTemplate']//td[contains(.,'Yes')]"), 60);
			// Delete Exam Template
			ActionUtils.click(driver
					.findElement(By.xpath("//button[contains(.,'Delete')][contains(@ng-click,'RemoveTemplate')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Please select to delete."));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkSelecAll']")));
			ActionUtils.click(driver
					.findElement(By.xpath("//button[contains(.,'Delete')][contains(@ng-click,'RemoveTemplate')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='myModalLabel'][contains(.,'Confirm')]"), 40);
			Assert.assertTrue(ActionUtils
					.getText(driver
							.findElement(By.xpath("//*[@id='confirmdelete']//p[contains(.,'Do you wish to remove')]")))
					.contains("Do you wish to remove the selected template library ?"));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='confirmdelete']//button[contains(.,'Delete')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Template Removed"));
			Commons.waitforElement(driver, By.xpath("//p[contains(.,'No Record Found')]"), 30);
			Commons.screenshot();
			CreateNoteUtils.NavigatetoCreateNote(driver);
			Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//*[@id='exam-templates']//span[contains(.,'Automation Public')]"), 60);
			System.out.println("****************Assertion-2 Pass**************");
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test To Verify Add Public My Exam template. Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true,
					"Test To Verify Add Public My Exam template. Failed" + Throwables.getStackTraceAsString(a));
		}
	}
}
