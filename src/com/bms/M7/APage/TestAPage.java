package com.bms.M7.APage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.APage;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import UIMap.HomePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.Commons;
import Utils.SearchPatientUtils;

public class TestAPage extends TestSetUp {
	public String strActualtext;

	@Test(enabled = true, priority = 1)
	public void required_field_indicators() throws InterruptedException {
		test = extent.startTest("To Validate Required Field Indicator for Diagnosis on A page",
				"Test required_field_indicators for Diagnosis tab" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		strActualtext = APageUtils.NavigateToApage(driver);
		test.log(LogStatus.INFO, "Added a Patient with ID" + strActualtext);
		test.log(LogStatus.INFO, "Navigated To A Page");
		String count;
		System.out.println("Checking Number of Mandatory fields");
		test.log(LogStatus.INFO, "Checking Number of Mandatory fields");
		count = APageUtils.CountOfRedButton(driver, "Diagnose");
		System.out.println("Number of mandatory field in Diagnose is  " + count);
		test.log(LogStatus.INFO, "Number of mandatory field in Diagnose is  " + count);
		try {
			System.out.println("Checking Number of Mandatory fields should be 1");
			test.log(LogStatus.INFO, "Checking Number of Mandatory fields should be 1");
			Assert.assertTrue(count.equals("1"));
			System.out.println("Number of mandatory field is 1 ");
			test.log(LogStatus.INFO, "Number of mandatory field is 1");
		} catch (AssertionError e) {
			System.out.println("Number of mandatory field is not 1");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		System.out.println("Adding DX code");
		test.log(LogStatus.INFO, "Adding DX code");
		AddCaseUtils.AddDXCode(driver);
		try {
			System.out.println("Validating that required field indicator is no more display.. ");
			test.log(LogStatus.INFO, "Validating that required field indicator is no more display");
			Assert.assertFalse(Commons.existsElement(driver, By.xpath("//a[@href='#Diagnosis']/span/span")));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
		Commons.screenshot();
		extent.endTest(test);
	
		
		test = extent.startTest("To Validate The following areas when accessed, does NOT allow  the user to change companies: EMR",
				"The following areas when accessed, does NOT allow  the user to change companies: EMR" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		
		// change company
		
		ActionUtils.click(HomePage.datatoggle(driver));
		ActionUtils.click(HomePage.changecompany(driver));
		test.log(LogStatus.INFO, "Clicked on Change Company from Side Menu");
		Commons.waitforElement(driver, By.xpath("//*[@id='ChangeCompany_CompanyCode']"), 30);
		//ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='ChangeCompany_CompanyCode']")), "2016");
		ActionUtils.click(CreateNotePage.SearchCompanybutton(driver));
		Commons.waitforElement(driver, By.xpath("//*[contains(@id,'ChangeCompanyGrid')]//tbody//tr[2]//td[1]"),130);
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//*[contains(@id,'ChangeCompanyGrid')]//tbody//tr[2]//td[1]")));
	    Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Company cannot be changed while a note is being edited."));	
	}

	@Test(enabled = true, priority = 2)
	public void add_diagnosis_and_case_updates() throws InterruptedException {
		test = extent.startTest("To Verify On Adding a Diagnosis code in A page will be shown in Case",
				"Test add_diagnosis_and_case_updates" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		strActualtext = APageUtils.NavigateToApage(driver);
		test.log(LogStatus.INFO, "Added a Patient with ID" + strActualtext);
		test.log(LogStatus.INFO, "Navigated To A Page");
		System.out.println("Adding DX code M545");
		test.log(LogStatus.INFO, "Adding DX code M545");
		APageUtils.AddSpecificDxCode(driver, "M545");
		Commons.waitForLoad(driver);
		APageUtils.click(APage.SaveButton(driver));
		String patientid = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Patient id is  " + patientid);
		SearchPatientUtils.QuickpatientSearch(driver, patientid);
		APageUtils.selectCaseFromPatientPage(driver);
		String casedxcode = APageUtils.GetDxcodeFromCase(driver);
		System.out.println("Dx code is case is :" + casedxcode);
		try {
			System.out.println("checking DX code M545 is added in  case or not");
			test.log(LogStatus.INFO, "checking DX code M545 is added in case or not");
			Assert.assertTrue(casedxcode.equalsIgnoreCase("m545"));
			System.out.println("DX code M545 is added in case");
			test.log(LogStatus.INFO, "DX code M545 is added in case");
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void remove_diagnosis_and_case_does_NOT_update() throws InterruptedException {
		test = extent.startTest("To Verify On Removing a Diagnosis code in A page will not update DX list in Case",
				"Test remove_diagnosis_and_case_does_NOT_update" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		strActualtext = APageUtils.NavigateToApage(driver);
		test.log(LogStatus.INFO, "Added a Patient with ID" + strActualtext);
		test.log(LogStatus.INFO, "Navigated To A Page");
		// AddCaseUtils.AddDXCode(driver);
		System.out.println("Adding DX code M545");
		test.log(LogStatus.INFO, "Adding DX code M545");
		APageUtils.AddSpecificDxCode(driver, "M545");
		Commons.waitForLoad(driver);
		APageUtils.click(APage.SaveButton(driver));
		APageUtils.SelectpatientFromid(driver);
		APageUtils.click(driver, "ClickDiagnoselink");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//tbody[@role='rowgroup']/tr/td[1]/input"), 20);
		Commons.waitForLoad(driver);
		Commons.ExistandDisplayElement(driver, By.xpath("//tbody[@role='rowgroup']/tr/td[1]/input"), 10);
		ActionUtils.click(driver.findElement(By.xpath("//tbody[@role='rowgroup']/tr/td[1]/input")));
		Commons.waitForLoad(driver);
		System.out.println("Deleting DX code M545");
		test.log(LogStatus.INFO, "Deleting DX code M545");
		Commons.waitForLoad(driver);
		ActionUtils.click(APage.DiagnoseDeleteButton(driver));
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		String patientid = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Patient id is  " + patientid);
		SearchPatientUtils.QuickpatientSearch(driver, patientid);
		APageUtils.selectCaseFromPatientPage(driver);
		String casedxcode = APageUtils.GetDxcodeFromCase(driver);
		System.out.println("Dx code is case is :" + casedxcode);
		try {
			System.out.println("checking DX code M545 is still in  case or not");
			test.log(LogStatus.INFO, "checking DX code M545 is still in case or not");
			Assert.assertTrue(casedxcode.equalsIgnoreCase("m545"));
			System.out.println("DX code M545 is still in case");
			test.log(LogStatus.INFO, "DX code M545 is still in case");
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void Goal_table_locked_fields_and_editable_fields() throws InterruptedException {
		test = extent.startTest("To Verify Locked fields and Editable Fields of Goal tabel",
				"Test Goal_table_locked_fields_and_editable_fields" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		strActualtext = APageUtils.NavigateToApage(driver);
		test.log(LogStatus.INFO, "Verify View past Data button Not displays on A page during Initial Visit");
		Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='Assessment']//button[contains(.,'View Past Data')]"),20));
		Commons.screenshot();
		test.log(LogStatus.INFO, "Added a Patient with ID" + strActualtext);
		test.log(LogStatus.INFO, "Navigated To A Page");
		List<String> goals = Arrays.asList("goal1");
		APageUtils.addGoal(driver, 1, goals);
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		APageUtils.createfollowUpNewNote(driver);
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		test.log(LogStatus.INFO, "Verify View past Data button displays on A page");
		Commons.waitforElement(driver, By.xpath("//*[@id='Assessment']//button[contains(.,'View Past Data')]"),30);
		Assert.assertTrue(driver.findElements(By.xpath("//*[@id='Assessment']//button[contains(.,'View Past Data')]")).size()==3);
		Assert.assertTrue(driver.findElements(By.xpath("//*[@id='Diagnosis']//button[contains(.,'View Past Data')]")).size()==1);
		Assert.assertTrue(driver.findElements(By.xpath("//*[@id='Goals-Rehab-Potential']//button[contains(.,'View Past Data')]")).size()==1);
		Commons.screenshot();
		test.log(LogStatus.INFO, "Verify Functionality of each View Past data button on A page");


		// Button-1
		test.log(LogStatus.INFO, "Verify View Past Data for Assessment - Past Data button-1");
		ActionUtils.click(driver.findElements(By.xpath("//*[@id='Assessment']//button[contains(.,'View Past Data')]")).get(0));
		Commons.waitforElement(driver, By.xpath("//h4[contains(.,'View Past Data - ')]"),20);
		Commons.waitforElement(driver, By.xpath("//*[@id='displayPastDataPopup']//label[contains(.,'View data from all clinical notes in the case')]"),15);
		Commons.waitforElement(driver, By.xpath("//*[@id='displayPastDataGrid']//span[contains(.,'Initial Visit')]"),10);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='displayPastDataPopup']//button[contains(.,'×')]")));
		System.out.println("**********Assertion-1 Pass*******");

		// Button-2
		test.log(LogStatus.INFO, "Verify View Past Data for Positive Prognosis - Past Data button-2");
		ActionUtils.click(driver.findElements(By.xpath("//*[@id='Assessment']//button[contains(.,'View Past Data')]")).get(1));
		Commons.waitforElement(driver, By.xpath("//h4[contains(.,'View Past Data - ')]"),20);
		Commons.waitforElement(driver, By.xpath("//*[@id='displayPastDataPopup']//label[contains(.,'View data from all clinical notes in the case')]"),15);
		Commons.waitforElement(driver, By.xpath("//*[@id='displayPastDataGrid']//span[contains(.,'Initial Visit')]"),10);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='displayPastDataPopup']//button[contains(.,'×')]")));
		System.out.println("**********Assertion-2 Pass*******");




		// Button-3
		test.log(LogStatus.INFO, "Verify View Past Data for Negative Prognosis - Past Data button-3");
		ActionUtils.click(driver.findElements(By.xpath("//*[@id='Assessment']//button[contains(.,'View Past Data')]")).get(2));
		Commons.waitforElement(driver, By.xpath("//h4[contains(.,'View Past Data - ')]"),20);
		Commons.waitforElement(driver, By.xpath("//*[@id='displayPastDataPopup']//label[contains(.,'View data from all clinical notes in the case')]"),15);
		Commons.waitforElement(driver, By.xpath("//*[@id='displayPastDataGrid']//span[contains(.,'Initial Visit')]"),10);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='displayPastDataPopup']//button[contains(.,'×')]")));
		System.out.println("**********Assertion-3 Pass*******");





		// Button-4
		test.log(LogStatus.INFO, "Verify View Past Data for Diagnosis - Past Data button-4");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='Diagnosis']//button[contains(.,'View Past Data')]")));
		Commons.waitforElement(driver, By.xpath("//h4[contains(.,'View Past Data - ')]"),20);
		Commons.waitforElement(driver, By.xpath("//*[@id='displayPastDataPopup']//label[contains(.,'View data from all clinical notes in the case')]"),15);
		Commons.waitforElement(driver, By.xpath("//*[@id='displayPastDataGrid']//span[contains(.,'Initial Visit')]"),10);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='displayPastDataPopup']//button[contains(.,'×')]")));
		System.out.println("**********Assertion-4 Pass*******");



		// Button-5
		test.log(LogStatus.INFO, "Verify View Past Data for Rehab Potential - Past Data button-5");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='Goals-Rehab-Potential']//button[contains(.,'View Past Data')]")));
		Commons.waitforElement(driver, By.xpath("//h4[contains(.,'View Past Data - ')]"),20);
		Commons.waitforElement(driver, By.xpath("//*[@id='displayPastDataPopup']//label[contains(.,'View data from all clinical notes in the case')]"),15);
		Commons.waitforElement(driver, By.xpath("//*[@id='displayPastDataGrid']//span[contains(.,'Initial Visit')]"),10);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='displayPastDataPopup']//button[contains(.,'×')]")));
		System.out.println("**********Assertion-5 Pass*******");
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		System.out.println("Successfully redirected to APage");
		APageUtils.click(driver, "ClickGoalsRehabPotentialLink");
		Commons.waitForLoad(driver);
		ActionUtils.click(APage.LockGoal(driver));
		try {
			System.out.println("getting lock value");
			test.log(LogStatus.INFO, "getting lock value");
			/* JavascriptExecutor js = (JavascriptExecutor)driver; */
			WebElement element = driver
					.findElement(By.xpath("//*[@id='goalsListTableBody']//td//input[contains(@islocked,'true')]"));
			/*
			 * String lock = (String) js.executeScript(
			 * "return argument[0].getAttribute('islocked');",element);
			 */
			String lock = element.getAttribute("islocked");
			Assert.assertEquals("true", lock);
			System.out.println("value of lock is " + lock);
			test.log(LogStatus.INFO, "value of lock is" + lock);
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 5)
	public void option_to_remove_goals() throws InterruptedException {
		test = extent.startTest("To Verify User can Remove Goals from goal Tabel", "Test option_to_remove_goals"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		strActualtext = APageUtils.NavigateToApage(driver);
		test.log(LogStatus.INFO, "Added a Patient with ID" + strActualtext);
		test.log(LogStatus.INFO, "Navigated To A Page");
		List<String> goals = Arrays.asList("goal1");
		APageUtils.addGoal(driver, 1, goals);
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		APageUtils.createfollowUpNewNote(driver);
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		System.out.println("Successfully redirected to APage");
		APageUtils.click(driver, "ClickGoalsRehabPotentialLink");
		goals = Arrays.asList("goal2");
		APageUtils.addGoal(driver, 2, goals);
		Commons.waitForLoad(driver);
		try {
			ActionUtils.click(driver
					.findElement(By.xpath("//*[@id='goalsListTableBody']//td//input[contains(@islocked,'false')]")));
			APage.GoalDeleteButton(driver).click();
			Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//*[@id='goalsListTableBody']//td//input[contains(@islocked,'false')]"), 20));
			Commons.waitForLoad(driver);
			APage.LockGoal(driver).click();
			APage.GoalDeleteButton(driver).click();
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.waitForLoad(driver);
	}
}
