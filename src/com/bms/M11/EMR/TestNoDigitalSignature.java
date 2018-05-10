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
import UIMap.LoginPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;

public class TestNoDigitalSignature extends TestSetUp {
	// TC-20 Digital Signature
	// TC-21 ADD NEW IV NOTE for patient with INCOMPLETE case
	@Test(enabled = true, priority = 1)
	public void NoteFinalizeWithoutDigitalSignature() throws InterruptedException {
		test = extent.startTest("To verify user with No Digital Signature recorded is unable to Sign and finalize Note",
				"Test No Digital Signature" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		ActionUtils.sendKeys(LoginPage.username(driver), Commons.readPropertyValue("usernodigitalsignature"));
		ActionUtils.sendKeys(LoginPage.password(driver), Commons.readPropertyValue("password"));
		ActionUtils.click(LoginPage.signin(driver));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@id='signatureRequiredAlertPopUp']//input[@value='OK']")));
		// Commons.waitforElement(driver,
		// By.xpath("//div[@id='signatureRequiredAlertPopUp']//input[@value='OK']"),60);
		Commons.screenshot();
		driver.findElement(By.xpath("//div[@id='signatureRequiredAlertPopUp']//input[@value='OK']")).click();
		Commons.waitforElement(driver, By.xpath("//span[@class='ng-binding'][2]"), 60);
		Commons.screenshot();
		Commons.waitForLoad(driver);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
		Commons.waitForLoad(driver);
		APageUtils.GotoFLR(driver);
		test.log(LogStatus.INFO, "Navigated to A PAge");
		APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "Test Automation FLRDataSubsequentNote");
		// Finalize Note with user having no digital signature recored
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "clickAssesmentAndPrognosislink");
		Commons.waitForLoad(driver);
		System.out.println("Setting Candidate for therapy as NO");
		ActionUtils.click(driver.findElement(By.xpath("//input[@name='CandidateTherapy'][@value='2']")));
		Commons.waitForLoad(driver);
		Commons.screenshot();
		// Assert.assertTrue(driver.findElements(By.xpath("//span[contains(@ng-if,'ValidationCount')]")).size()<2,"Red
		// Dot Indicator failure when Patient is not candidate for therapy");
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
		ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
		System.out.println("Clicked on Validate Charge button");
		Commons.waitForLoad(driver);
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"), 10)) {
			System.out.println(
					"Warning Pop up appeared: You have not charged to the maximum number of units based on the number of one-on-one minutes documented.  Are you sure you want to complete the charges? ");
			Commons.screenshot();
			driver.findElement(By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']")).click();
		}
		// warning-2
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']"), 10)) {
			Commons.screenshot();
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
		}
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
		Commons.waitForLoad(driver);
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='signatureRequiredAlertPopUp']//input[@value='OK']"),
					40);
			Assert.assertTrue(driver
					.findElement(By
							.xpath("//div[@id='signatureRequiredAlertPopUp']//div[contains(text(),'We do not have a record')]"))
					.getText().contains("We do not have a record of your digital signature"));
			Commons.screenshot();
			driver.findElement(By.xpath("//div[@id='signatureRequiredAlertPopUp']//input[@value='OK']")).click();
			Commons.logout(driver);
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Test failed" + Throwables.getStackTraceAsString(e));
		}
	}

	// ****************************patient with incomplete case
	@Test(enabled = true, priority = 2)
	public void NoteFinalizeIncompleteCASE() throws InterruptedException {
		test = extent.startTest("To verify user with INCOMPLETE CASE is unable to Sign and finalize Note",
				"Test To verify user with INCOMPLETE CASE is unable to Sign and finalize Note"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// Add Patient with incomplete case
		try {
			AddPatientUtils.QuickAddpatient(driver);
			String strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 200);
			Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
					"Could not save case..");
			AddCaseUtils.GoToCaseList(driver);
			String expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			System.out.println("Expected=>" + expectedMessage);
			// Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
			test.log(LogStatus.INFO, "Added Patient with all fields and completed Case:" + strText_Actual);
			Commons.screenshot();
			// Create Notes and Navigating to A page
			Commons.waitForLoad(driver);
			APageUtils.createNewNote(driver);
			System.out.println("Created a New Note for Initial Visit");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
			System.out.println("Successfully redirected to APage");
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			System.out.println("Validating that FLR Link Appear on A Page..");
			Commons.waitforElement(driver, By.xpath("//a[@href='#Medicare-Functional-Limitation-Reporting']"), 60);
			APageUtils.click(driver, "ClickMedicareFLRLink");
			Commons.waitForLoad(driver);
			Commons.screenshot();
			test.log(LogStatus.INFO, "Navigated to A PAge");
			APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "Test Automation FLRDataSubsequentNote");
			// Finalize Note with user having no digital signature recored
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.waitForLoad(driver);
			System.out.println("Setting Candidate for therapy as NO");
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='CandidateTherapy'][@value='2']")));
			Commons.waitForLoad(driver);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			String stractual = Commons.capturemessage(driver, 30);
			Assert.assertTrue(stractual
					.contains("Your information has been saved but the case associated to this note is not complete"));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitForLoad(driver);
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
