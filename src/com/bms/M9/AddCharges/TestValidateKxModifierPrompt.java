package com.bms.M9.AddCharges;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddChargeUtils;
import Utils.Commons;
import Utils.HandlingCalendars;

public class TestValidateKxModifierPrompt extends TestSetUp {
	public String strActualtext;

	// CHG.ADD.101,102,103
	@Test(enabled = true, priority = 1)
	public void ValidateKxModifierPrompt() throws InterruptedException {
		test = extent.startTest(
				"To Validate KX modifier Prompt - charges that would exceed the cap during that charge entry session ",
				"To Validate KX modifier Prompt - charges that would exceed the cap during that charge entry session "
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add patient with complete Case");
		try {
			AddChargeUtils.AddPatientCaseCompleted(driver, "PatientKxModifier");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlPreAuth']")));
			test.log(LogStatus.INFO, "Adding charge to current patient");
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "01/01/2017");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "01/01/2017");
			// Add CPT code and click save
			test.log(LogStatus.INFO, "Adding CPT Code 97140");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page to Verify KX Modifier Pop up");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='KxModifierPopup']//div[contains(text(),'Based on the estimated Medicare')]"),
					30);
			test.log(LogStatus.INFO, "Got KX Modifier pop up window");
			Commons.screenshot();
			String kxmodifierPopup = ActionUtils.getText(driver.findElement(
					By.xpath("//*[@id='KxModifierPopup']//div[contains(text(),'Based on the estimated Medicare')]")));
			Assert.assertEquals(kxmodifierPopup.trim(),
					"Based on the estimated Medicare allowable amount of charges entered this calendar year, the patient may have met the therapy cap. Do you want to apply the KX modifier?");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='KxModifierPopup']//button[contains(.,'Yes')]")));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			test.log(LogStatus.INFO, "*******************Got  Message  Assertion Pass****************");
			strActualtext = null;
			// ******************************************************
			test.log(LogStatus.INFO, "To Verify KxModifier is attached to current charges on View charge Page");
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='viewChargeGrid']/table/tbody/tr/td[8]"), 50);
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='viewChargeGrid']/table/tbody/tr/td[8]")).getText()
					.trim().contains("KX"));
			// Case-2 CHG.ADD.103
			// KX modifier Prompt - previous charges that have already exceeded
			// the cap
			test.log(LogStatus.INFO,
					"To Verify KX modifier Prompt - previous charges that have already exceeded the cap ");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			test.log(LogStatus.INFO, "Adding charge to current patient");
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "01/01/2017");
			// Add CPT code and click save
			test.log(LogStatus.INFO, "Adding CPT Code 97140");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page to Verify KX Modifier Pop up");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "01/01/2017");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='KxModifierPopup']//div[contains(text(),'Based on the estimated Medicare')]"),
					30);
			test.log(LogStatus.INFO, "Got KX Modifier pop up window");
			Commons.screenshot();
			String kxmodifierPopup2 = ActionUtils.getText(driver.findElement(
					By.xpath("//*[@id='KxModifierPopup']//div[contains(text(),'Based on the estimated Medicare')]")));
			Assert.assertEquals(kxmodifierPopup2.trim(),
					"Based on the estimated Medicare allowable amount of charges entered this calendar year, the patient may have met the therapy cap. Do you want to apply the KX modifier?");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='KxModifierPopup']//button[contains(.,'Yes')]")));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			test.log(LogStatus.INFO, "*******************Got  Message  Assertion Pass****************");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "***************Test Validate KxModifier Prompt Fail*************"
					+ Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, "Test Validate KxModifier Prompt Fail" + Throwables.getStackTraceAsString(e));
		}
	}
}
