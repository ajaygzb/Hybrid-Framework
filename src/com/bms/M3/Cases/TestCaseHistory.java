package com.bms.M3.Cases;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.HandlingCalendars;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestCaseHistory extends TestSetUp {
	@Test(priority = 1, enabled = true)
	public void TestCaseHistoryWithCharges() throws InterruptedException {
		test = extent.startTest("Test Case History With Charges", "Test Case History With Charges"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "CaseHistory");// Add
																			// Patient
																			// all
																			// Details
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			AddCaseUtils.AddDXCode(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.PrimaryInsuranceAddPreauthorization(driver);
			AddCaseUtils.PrimaryInsuranceAddEligibity(driver);
			AddCaseUtils.PrimaryInsuranceAddDocotorsOrder(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertEquals(AddCaseUtils.GetFirstCaseStatus(driver), "Complete");
			String expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			Assert.assertTrue(expectedMessage.contains("AutoPri Blue Shield"));
			test.log(LogStatus.INFO, "Verified Primary Insurance as AutoPri Blue Shield");
			test.log(LogStatus.INFO, "Case Completed successfully.");
			// Add Charge to Case
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			// Add charges on 15/06/2016
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "15/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "15/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97110", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "15/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "15/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.waitForLoad(driver);
			Commons.capturemessage(driver, 90);
			// Add charges on 16/06/2016
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "16/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "16/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97110", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "16/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "16/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.waitForLoad(driver);
			Commons.capturemessage(driver, 90);
			// Add charges on 17/06/2016
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "17/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "17/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97110", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "17/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "17/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.waitForLoad(driver);
			Commons.capturemessage(driver, 90);
			// Edit case name and change case effective date to 16/06/2016
			test.log(LogStatus.INFO, "Edit case name and change case effective date to 16/06/2016");
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertEquals(AddCaseUtils.GetFirstCaseStatus(driver), "Complete");
			System.out.println("Validated Visit count is 3");
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='gridCaseDetails']/table/tbody/tr[1]/td[12]"))
					.getText().contains("3"));
			Commons.screenshot();
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'AutoPri Blue Shield')]")));
			ActionUtils.click(CasePage.CaseEditButton(driver));
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "16/06/2016");// enter
																									// Case
																									// effective
																									// date
			CasePage.CaseName(driver).clear();
			ActionUtils.sendKeys(CasePage.CaseName(driver), "New case-1");
			Commons.screenshot();
			Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]"), 60);
			// change INS Code
			
			AddCaseUtils.AddPrimaryInsuranceByCode(driver,"1006");
			
			ActionUtils
					.click(driver.findElement(By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='rebillPopup']//button[contains(.,'Yes')]"),
					12)) {
				System.out.println("Rebill pop up appeared");
				Commons.screenshot();
				driver.findElement(By.xpath("//*[@id='rebillPopup']//button[contains(.,'Yes')]")).click();
			}
			Commons.capturemessage(driver, 150);
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertEquals(AddCaseUtils.GetFirstCaseStatus(driver), "Complete");
			System.out.println("Validated Visit count is 2");
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='gridCaseDetails']/table/tbody/tr[1]/td[12]"))
					.getText().contains("2"));
			System.out.println("Validate New case is created and charges moved to new case");
			Assert.assertEquals(driver.findElements(By.xpath("//*[@id='gridCaseDetails']/table/tbody/tr")).size(), 3,
					"No new Case created");
			Commons.screenshot();
			test.log(LogStatus.INFO, "Assertion PASS!!!");
			test.log(LogStatus.INFO, "Test Case History With Charges PASS");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test Case History With Charges Failed");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
		Commons.screenshot();
		extent.endTest(test);
		test = extent.startTest("Verify data saves and only the Doctors Orders sections should display on new case, the Eligibilities and the Preauthorization should on the old case.  ", "Test Case History With Charges"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.screenshot();
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//*[@id='gridCaseDetails']/table/tbody/tr[1]//td[5]")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//a[@href='#PrimaryInsurance']"),90);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PrimaryInsurance']")));
		Commons.screenshot();
		CasePage.PrimaryDocotorsOrderFromDate(driver);
		Assert.assertTrue(!driver.findElement(By.xpath("//input[contains(@name,'docFromDate')]")).getAttribute("value").isEmpty());
		Assert.assertTrue(!driver.findElement(By.xpath("//input[contains(@name,'docThroughDate')]")).getAttribute("value").isEmpty());
		Assert.assertTrue(!driver.findElement(By.xpath("//input[contains(@name,'docPrescribedVisit')]")).getAttribute("value").isEmpty());
		Assert.assertTrue(!driver.findElement(By.xpath("//input[contains(@name,'docComment')]")).getAttribute("value").isEmpty());
		Assert.assertTrue(driver.findElement(By.xpath("//input[contains(@name,'docComment')]")).getAttribute("value").trim().contains("Hello i am From QA"));
	    System.out.println("*******************Assertion Pass***************");	
	}
}