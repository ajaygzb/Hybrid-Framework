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
import com.relevantcodes.extentreports.LogStatus;

public class TestAPageGCodeValidationOT extends TestSetUp {
	// 5,11,17,23,29 inscode
	// 1,7,13,19,25 lastname
	public String strText_Actual;

	@Test(enabled = true, priority = 1)
	public void G_CodeValidation_OT() throws InterruptedException {
		try {
			test = extent.startTest(
					"Current status drop down - available options per discipline (OT) for Medicare Patient",
					"Test available options per discipline (OT) " + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userOT"), Commons.readPropertyValue("password"));
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
		String[] expected = { "G8978 Mobility Current Status", "G8981 Body Position Current Status",
				"G8984 Carrying/Handling Objects Current Status", "G8987 Self-care Current Status",
				"G8990 Other PT/OT Current Status", "G8993 Subsequent PT/OT Current Status",
				"G8996 Swallow Current Status", "G8999 Motor Speech Current Status",
				"G9159 Language Compostion Current Status", "G9162 Language Expression Current Status",
				"G9165 Attention Current Status", "G9168 Memory Current Status", "G9171 Voice Current Status",
				"G9174 Other Speech Language Current Status" };
		APageUtils.DropDownValuesAssertion(driver, actual, expected);
	}
}
