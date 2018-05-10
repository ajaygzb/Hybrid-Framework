package com.bms.M7.APage;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestAPageCurrentStatusList extends TestSetUp {
	public String strText_Actual;

	@Test(enabled = true, priority = 1)
	public void G_CodeValidation_SLP() throws InterruptedException {
		try {
			test = extent.startTest(
					"Current status drop down - available options per discipline (SLP) for Medicare Patient",
					"Test available options per discipline (SLP) " + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "Logged out from app");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userSLP"), Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "Logged in as Provider SLP");
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
			strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			test.log(LogStatus.INFO, "Added Patient With All Fields ID:==>" + "   " + strText_Actual);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
						"Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
			test.log(LogStatus.INFO, "Case with Insurance As Medicare Saves  successfully.");
			test.log(LogStatus.INFO, "Insurance Name is ==>AutoPri Medicare");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		// Create Notes and Navigating to A page
		Commons.waitForLoad(driver);
		APageUtils.createNewNote(driver);
		test.log(LogStatus.INFO, "Created a New Note for Initial Visit");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		System.out.println("Successfully redirected to APage");
		test.log(LogStatus.INFO, "Successfully redirected to APage");
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Scrolled to Current Status Drop down");
		Commons.existsElement(driver, By.xpath("//*[@id='flrCurrentStatus']/option[@label]"));
		List<WebElement> actual = driver.findElements(By.xpath("//*[@id='flrCurrentStatus']/option[@label]"));
		System.out.println("No Of Dropdown options in current status available are:" + actual.size());
		test.log(LogStatus.INFO, "No Of Dropdown options in current status available are:" + actual.size());
		for (WebElement we : actual) {
			System.out.println(we.getText());
			test.log(LogStatus.INFO, we.getText());
		}
		// make sure you found the right number of elements
		String[] expected = { "G8996 Swallow Current Status", "G8999 Motor Speech Current Status",
				"G9159 Language Compostion Current Status", "G9162 Language Expression Current Status",
				"G9165 Attention Current Status", "G9168 Memory Current Status", "G9171 Voice Current Status",
				"G9174 Other Speech Language Current Status", };
		APageUtils.DropDownValuesAssertion(driver, actual, expected);
	}

	@Test(enabled = true, priority = 2)
	public void FLR_tab_Validation_RT() throws InterruptedException {
		try {
			test = extent.startTest(
					"Verify the Medicare FLR and Therapy Cap section does not display for provider (RT) discipline. ",
					"Test FLR_tab_Validation_RT" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userRT"), Commons.readPropertyValue("password"));
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
			strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			test.log(LogStatus.INFO, "Added Patient With All Fields ID:==>" + "   " + strText_Actual);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
						"Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
			test.log(LogStatus.INFO, "Case with Insurance As Medicare Saves  successfully.");
			test.log(LogStatus.INFO, "Insurance Name is ==>AutoPri Medicare");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Fail");
			Assert.assertFalse(true, "Assertion Error!!!" + e);
		}
		// Create Notes and Navigating to A page
		Commons.waitForLoad(driver);
		APageUtils.createNewNote(driver);
		test.log(LogStatus.INFO, "Created a New Note for Initial Visit");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		System.out.println("Successfully redirected to APage");
		test.log(LogStatus.INFO, "Successfully redirected to APage");
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		test.log(LogStatus.INFO,
				"Validating that the Medicare FLR and Therapy Cap section " + "does not display for provider as RT  ");
		try {
			Assert.assertFalse(driver.findElement(By.xpath("//a[@href='#Medicare-Functional-Limitation-Reporting']"))
					.isDisplayed(), "FLR Link Displayed for provider as RT");
			test.log(LogStatus.INFO, "************Assertion Passed*************");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "************Assertion Failed*************");
			Assert.assertFalse(true, "************Assertion Failed*************");
		}
	}

	@Test(enabled = true, priority = 3)
	public void GoalstatusValidation() throws InterruptedException {
		try {
			test = extent.startTest("To Validate selection of current status auto-selects projected status",
					"Test GoalstatusValidation" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPT"), Commons.readPropertyValue("password"));
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
			strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			test.log(LogStatus.INFO, "Added Patient With All Fields ID:==>" + "   " + strText_Actual);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
						"Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
			test.log(LogStatus.INFO, "Case with Insurance As Medicare Saves  successfully.");
			test.log(LogStatus.INFO, "Insurance Name is ==>AutoPri Medicare");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Fail");
			Assert.assertFalse(true, "Assertion Error!!!" + e);
		}
		// Create Notes and Navigating to A page
		Commons.waitForLoad(driver);
		APageUtils.createNewNote(driver);
		test.log(LogStatus.INFO, "Created a New Note for Initial Visit");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		System.out.println("Successfully redirected to APage");
		test.log(LogStatus.INFO, "Successfully redirected to APage");
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Scrolled to Current Status Drop down");
		Commons.existsElement(driver, By.xpath("//*[@id='flrCurrentStatus']/option[@label]"));
		List<WebElement> actual = driver.findElements(By.xpath("//*[@id='flrCurrentStatus']/option[@label]"));
		System.out.println("No Of Dropdown options in current status available are:" + actual.size());
		test.log(LogStatus.INFO, "No Of Dropdown options in current status available are:" + actual.size());
		// *[@id='flrProjectedStatus']/option[@selected]
		test.log(LogStatus.INFO, "Selecting FLR current status from dropdown");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='flrCurrentStatus']/option[@label][1]")));
		test.log(LogStatus.INFO, "Validating that Goal status is Auto selected");
		try {
			Assert.assertTrue(
					Commons.existsElement(driver, By.xpath("//*[@id='flrProjectedStatus']/option[@selected]")));
			System.out.println(ActionUtils
					.getText(driver.findElement(By.xpath("//*[@id='flrProjectedStatus']/option[@selected]"))));
			test.log(LogStatus.INFO, "Goal Status selected as:");
			test.log(LogStatus.INFO, ActionUtils
					.getText(driver.findElement(By.xpath("//*[@id='flrProjectedStatus']/option[@selected]"))));
			test.log(LogStatus.INFO, "Validation Passed");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "Validation Failed");
		}
	}

	@Test(enabled = true, priority = 4)
	public void SeverityModifierDropdDownListValidation() throws InterruptedException {
		try {
			test = extent.startTest(
					"To Validate Current Status & Projected Status Severity modifier drop down for the FLR table",
					"Test Severity Modifier DropdDown List Validation" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPT"), Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
			strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			test.log(LogStatus.INFO, "Added Patient With All Fields ID:==>" + "   " + strText_Actual);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
						"Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
			test.log(LogStatus.INFO, "Case with Insurance As Medicare Saves  successfully.");
			test.log(LogStatus.INFO, "Insurance Name is ==>AutoPri Medicare");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Fail");
			Assert.assertFalse(true, "Assertion Error!!!" + e);
		}
		// Create Notes and Navigating to A page
		Commons.waitForLoad(driver);
		APageUtils.createNewNote(driver);
		test.log(LogStatus.INFO, "Created a New Note for Initial Visit");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		System.out.println("Successfully redirected to APage");
		test.log(LogStatus.INFO, "Successfully redirected to APage");
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Scrolled to Current Status Drop down");
		Commons.existsElement(driver, By.xpath("//*[@id='flrCurrentStatus']/option[@label]"));
		test.log(LogStatus.INFO, "Selecting FLR current status from dropdown");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='flrCurrentStatus']/option[@label][1]")));
		Commons.waitForLoad(driver);
		test.log(LogStatus.INFO, "Validating current status severity modifier drop down List");
		try {
			Commons.existsElement(driver, By.xpath("//*[@id='flrCurrentSeverityModifier']/option[@label]"));
			List<WebElement> actual = driver
					.findElements(By.xpath("//*[@id='flrCurrentSeverityModifier']/option[@label]"));
			System.out.println("No Of Dropdown options in current status Severity Modifier List  are:" + actual.size());
			test.log(LogStatus.INFO,
					"No Of Dropdown options in current status Severity Modifier List   are:" + actual.size());
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			String[] expected = { "CH 0% impaired", "CI < 20% impaired", "CJ 20% < 40% impaired",
					"CK 40% < 60% impaired", "CL 60% < 80% impaired", "CM 80% < 100% impaired", "CN 100% impaired" };
			APageUtils.DropDownValuesAssertion(driver, actual, expected);
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "Validation Failed");
		}
		test.log(LogStatus.INFO, "Validating projected status severity modifier drop down List");
		try {
			Commons.existsElement(driver, By.xpath("//*[@id='flrProjectedSeverityModifier']/option[@label]"));
			List<WebElement> actual = driver
					.findElements(By.xpath("//*[@id='flrProjectedSeverityModifier']/option[@label]"));
			System.out
					.println("No Of Dropdown options in Projected status Severity Modifier List  are:" + actual.size());
			test.log(LogStatus.INFO,
					"No Of Dropdown options in Projected status Severity Modifier List   are:" + actual.size());
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			String[] expected = { "CH 0% impaired", "CI < 20% impaired", "CJ 20% < 40% impaired",
					"CK 40% < 60% impaired", "CL 60% < 80% impaired", "CM 80% < 100% impaired", "CN 100% impaired" };
			APageUtils.DropDownValuesAssertion(driver, actual, expected);
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "Validation Failed");
		}
	}
}
