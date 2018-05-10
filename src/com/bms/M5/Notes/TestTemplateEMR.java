package com.bms.M5.Notes;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import UIMap.ExamTemplatesPage;
import UIMap.HomePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.CreateNoteUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestTemplateEMR extends TestSetUp {
	public String strActualtext;
	CreateNoteUtils callto = new CreateNoteUtils();

	@Test(enabled = true, priority = 1)
	public void ValidateNoCustomTemplate() throws InterruptedException {
		test = extent.startTest(
				"To Validate available custom templates (user templates) for provider without customer exam templates",
				"Test Validate Custome Template not Display" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Login as a provider having no personal template");
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider100"),
				Commons.readPropertyValue("password"));
		AddPatientUtils.QuickAddpatient(driver);
		strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		test.log(LogStatus.INFO, "Quick Add a Patient with ID==>" + strActualtext);
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		test.log(LogStatus.INFO, "Navigated to Create Notes");
		if (Commons.existsElement(driver,
				By.xpath("//select[@name='ddlVisitType']//option[@label='Evaluation/Transfer']"))) {
			CreateNotePage.VisitTypeddl(driver, "Evaluation/Transfer");
		} else {
			CreateNotePage.VisitTypeddl(driver, "Followup Visit");
		}
		try {
			System.out.println("Validating That No Custom/Personal template appaer for current user"); // open
																										// DDL
			test.log(LogStatus.INFO, "Validating That No Custom/Personal template appaer for current user");
			driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click();
			Commons.Explicitwait();
			Assert.assertFalse(Commons.existsElement(driver,
					By.xpath("//*[@id='exam-templates']//*[contains(text(),'Personal Exam Templates')]")));
			test.log(LogStatus.INFO, "Test:::: Validate No CustomTemplate() Completed as Pass");
			System.out.println("Test:::: Validate No CustomTemplate() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Validate NoCustom Template() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void ValidateCustomTemplate() throws InterruptedException {
		test = extent.startTest(
				"To Validate available custom templates (user templates) for provider with customer exam templates",
				"Test Validate Custome Template Display" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Login as a provider having no personal template");
		AddPatientUtils.QuickAddpatient(driver);
		strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		test.log(LogStatus.INFO, "Quick Add a Patient with ID==>" + strActualtext);
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		test.log(LogStatus.INFO, "Navigated to Create Notes");
		try {
			System.out.println("Validating That  Custom/Personal template appaer for current user"); // open
																										// DDL
			test.log(LogStatus.INFO, "Validating That  Custom/Personal template appaer for current user");
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='exam-templates']//*[contains(text(),'Personal Exam Templates')]"), 8)) {
				Assert.assertTrue(Commons.existsElement(driver,
						By.xpath("//*[@id='exam-templates']//*[contains(text(),'Personal Exam Templates')]")));
			} else {
				// Add a new My Exam Template
				ActionUtils.click(CreateNotePage.EMROnMenu(driver));
				ActionUtils.click(ExamTemplatesPage.MyExamTemplate(driver));
				Commons.waitforElement(driver, By.xpath("//h1[contains(.,'My Exam Templates')]"), 60);
				Commons.screenshot();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='createTemplateBtn']")));
				Commons.waitforElement(driver,
						By.xpath("//*[@id='CreateTemplate']//h4[contains(.,'New Exam Template')]"), 60);
				Commons.SelectElement(driver, By.xpath("//*[@id='bodyPartsDropDown']"), "Ankle Sprain");
				ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='templateNameTxt']")), "Automation");
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='CreateTemplate']//button[contains(.,'Create')]")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//button[contains(.,'Return to My Exam Templates List')]"),
						60);
				Commons.screenshot();
				CreateNoteUtils.NavigatetoCreateNote(driver);
				Commons.waitforElement(driver,
						By.xpath("//*[@id='exam-templates']//*[contains(text(),'Personal Exam Templates')]"), 30);
			}
			test.log(LogStatus.INFO, "Test:::: Validate Custom Template Completed as Pass");
			System.out.println("Validate Custom Template Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Validate Custom Template Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = false, priority = 3)
	public void ValidateNoPublicTemplate() throws InterruptedException {
		test = extent.startTest(
				"To Validate available company templates (public templates) for Company with no shared exam template",
				"Test Validate Public Template not Display" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Login as a provider with Company having no Public template");
		ActionUtils.click(HomePage.datatoggle(driver));
		ActionUtils.click(HomePage.changecompany(driver));
		test.log(LogStatus.INFO, "Clicked on Change Company from Side Menu");
		Commons.waitforElement(driver, By.xpath("//*[@id='ChangeCompany_CompanyCode']"), 30);
		ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='ChangeCompany_CompanyCode']")), "2016");
		ActionUtils.click(CreateNotePage.SearchCompanybutton(driver));
		Commons.Explicitwait();
		Commons.waitforElement(driver, By.xpath("//tr[contains(@class,'ng-scope')]"), 30);
		ActionUtils.doubleClick(driver, driver.findElements(By.xpath("//tr[contains(@class,'ng-scope')]//td")).get(0));
		test.log(LogStatus.INFO, "Changed Company to AutomationEMR");
		System.out.println("Changed Company to AutomationEMR");
		Commons.Explicitwait();
		AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
		// CreateNoteUtils.NavigatetoCreateNote(driver);
		ActionUtils.click(CreateNotePage.EMROnMenu(driver));
		ActionUtils.click(CreateNotePage.AddClinicalNote(driver));
		System.out.println("Opened Create Note Screen");
		ActionUtils.click(CreateNotePage.CaseNameddl(driver));
		Commons.Explicitwait();
		if (Commons.existsElement(driver,
				By.xpath("//select[@name='ddlVisitType']//option[@label='Evaluation/Transfer']"))) {
			CreateNotePage.VisitTypeddl(driver, "Evaluation/Transfer");
		} else {
			CreateNotePage.VisitTypeddl(driver, "Followup Visit");
		}
		try {
			System.out.println("Validating That No Company/Public template appaer for current user"); // open
																										// DDL
			test.log(LogStatus.INFO, "Validating That No Company/Public template appaer for current user");
			driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click();
			Commons.Explicitwait();
			Assert.assertFalse(Commons.existsElement(driver,
					By.xpath("//*[@id='exam-templates']//*[contains(text(),'Public Exam Templates')]")));
			test.log(LogStatus.INFO, "Test:::: Validate No PublicTemplate() Completed as Pass");
			System.out.println("Test:::: Validate No publicTemplate() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Validate Nopublic Template() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void ValidatePublicTemplate() throws InterruptedException {
		test = extent.startTest(
				"To Validate available company templates (public templates) for Company with shared exam template",
				"Test Validate Public Template Display" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Login as a provider with Company having Public template");
		AddPatientUtils.QuickAddpatient(driver);
		strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		test.log(LogStatus.INFO, "Quick Add a Patient with ID==>" + strActualtext);
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		CreateNoteUtils.NavigatetoCreateNote(driver);
		if (Commons.existsElement(driver,
				By.xpath("//select[@name='ddlVisitType']//option[@label='Evaluation/Transfer']"))) {
			CreateNotePage.VisitTypeddl(driver, "Evaluation/Transfer");
		} else {
			CreateNotePage.VisitTypeddl(driver, "Followup Visit");
		}
		try {
			System.out.println("Validating That Company/Public template appaer for current user"); // open
																									// DDL
			test.log(LogStatus.INFO, "Validating That Company/Public template appaer for current user");
			driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click();
			Commons.Explicitwait();
			Assert.assertTrue(
					Commons.existsElement(driver,
							By.xpath("//*[@id='exam-templates']//*[contains(text(),'Public Exam Templates')]")),
					"Public Exam Templates Not found");
			test.log(LogStatus.INFO, "Test:::: Validate  PublicTemplate() Completed as Pass");
			System.out.println("Test:::: Validate  publicTemplate() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Validate public Template() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
