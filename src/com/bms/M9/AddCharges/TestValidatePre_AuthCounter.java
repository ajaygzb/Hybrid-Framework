package com.bms.M9.AddCharges;

import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.HandlingCalendars;

public class TestValidatePre_AuthCounter extends TestSetUp {
	public String strActualtext;

	// CHG.ADD.009,10,20,21,22,29,30,110,111,112,218

	@Test(enabled = true, priority = 1)
	public void ValidatePreAuthValuesAddchargeDropdown() throws InterruptedException {
		try {
			test = extent.startTest(
					"To Validate auth no.,from date,to date,total visit,visits remaining in Preauth dropdown on add charge page",
					"To Validate Pre-Auth values in Add charge Page" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Add patient with complete Case and Preauthrization");
			try {
				AddPatientUtils.addpatient_withallfields(driver, "testPreauthautomation");
				strActualtext = null;
				strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
				AddCaseUtils.GoToAddNewCase(driver);
				AddCaseUtils.AddCaseWithAllRequiredFields(driver);
				AddCaseUtils.AddDXCode(driver);
				AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
				AddChargeUtils.PrimaryInsuranceAddPreauthorization(driver, "14/06/2016", "14/07/2016", "10");
				AddCaseUtils.ClickCaseSave(driver);
				Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 200);
				Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
						"Could not save case..");
				AddCaseUtils.GoToCaseList(driver);
				String expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
				System.out.println("Expected=>" + expectedMessage);
				Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
			} catch (Exception e) {
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			try {
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlPreAuth']")));
				String preauthdata = driver.findElement(By.xpath("//*[@id='ddlPreAuth']/option[2]")).getText();
				Assert.assertTrue(driver.findElement(By.xpath("//*[@id='ddlPreAuth']/option[2]")).isDisplayed(),
						"Pre autrization Data Not displayed in Add charge Page");
				Assert.assertFalse(preauthdata.isEmpty());
				System.out.println(preauthdata);
				test.log(LogStatus.INFO, "validating Data in Pre-authorization Drop down list");
				System.out.println("Getting Data from Preauthorization drop down");
				String[] result = preauthdata.split(Pattern.quote("|"));
				System.out.println(result[0] + result[1] + result[2]);
				test.log(LogStatus.INFO, "Preautrization Number display" + result[0]);
				test.log(LogStatus.INFO, "Preautrization From thru date display" + result[1]);
				test.log(LogStatus.INFO, "Preautrization Total and remaining visit" + result[2]);
				test.log(LogStatus.INFO,
						"To Validate Preautrization Total and remaining visit gets update after adding charge");
				test.log(LogStatus.INFO, "Adding charge to current patient");
				test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
				HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
				System.out.println("Selecting pre auth");
				driver.findElement(By.xpath("//*[@id='ddlPreAuth']/option[2]")).click();
				// Add CPT code and click save when preauthorization is selected
				test.log(LogStatus.INFO, "Adding CPT Code 97140");
				Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//tr[1]//input[@type='search']"), 20);
				ActionUtils.sendKeys(
						driver.findElement(By.xpath("//div[@id='tbl_proc']//tr[1]//input[@type='search']")), "97140");
				Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"),
						10);
				Commons.waitForElementToBeVisible(driver,
						driver.findElement(By.xpath("//li[@class='ng-scope selected']")), 20);
				ActionUtils.click(driver.findElement(By.xpath("//li[@class='ng-scope selected']")));
				System.out.println("Preauth visit count before adding charge" + result[2]);
				strActualtext = null;
				strActualtext = result[2];
				test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
				HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
				ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
				Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
				test.log(LogStatus.INFO, "*******************Got  Message  Assertion Pass****************");
				strActualtext = null;
				String preauthdataafter = driver.findElement(By.xpath("//*[@id='ddlPreAuth']/option[2]")).getText();
				String[] resultafter = preauthdataafter.split(Pattern.quote("|"));
				System.out.println(resultafter[0] + resultafter[1] + resultafter[2]);
				strActualtext = resultafter[2];
				System.out.println("Preauth visit count after adding charge" + resultafter[2]);
				System.out.println(resultafter[2] + result[2]);
				Assert.assertFalse(resultafter[2].contentEquals(result[2]));
				// Add CPT code and click save when preauthorization is not
				// selected
				test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
				HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
				System.out.println("UnSelecting pre auth");
				driver.findElement(By.xpath("//*[@id='ddlPreAuth']/option[1]")).click();
				test.log(LogStatus.INFO, "Adding CPT Code 97140");
				Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//tr[1]//input[@type='search']"), 20);
				ActionUtils.sendKeys(
						driver.findElement(By.xpath("//div[@id='tbl_proc']//tr[1]//input[@type='search']")), "97140");
				Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"),
						10);
				Commons.waitForElementToBeVisible(driver,
						driver.findElement(By.xpath("//li[@class='ng-scope selected']")), 20);
				ActionUtils.click(driver.findElement(By.xpath("//li[@class='ng-scope selected']")));
				test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
				HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
				test.log(LogStatus.INFO,
						"Clicked on Save Button On Add charge page When no pre-Authorization Selected");
				ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
				Commons.waitforElement(driver,
						By.xpath("//div[@id='warningPopup']//li[contains(text(),'No Preauthorization is selected.')]"),
						30);
				test.log(LogStatus.INFO, "Got No Preauthorization selected pop up window");
				Commons.screenshot();
				String preauthPopup = ActionUtils.getText(driver.findElement(By
						.xpath("//div[@id='warningPopup']//li[contains(text(),'No Preauthorization is selected.')]")));
				Assert.assertEquals(preauthPopup.trim(),
						"No Preauthorization is selected. Are you sure you want to continue?");
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='warningPopup']//button[contains(text(),'OK')]")));
				Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
				test.log(LogStatus.INFO, "*******************Got  Message  Assertion Pass****************");
				// Add CPT code and click save when preauthorization is selected
				// with Date of service not in range
				test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
				HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/05/2016");
				System.out.println("Selecting pre auth");
				driver.findElement(By.xpath("//*[@id='ddlPreAuth']/option[2]")).click();
				test.log(LogStatus.INFO, "Adding CPT Code 97140");
				Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//tr[1]//input[@type='search']"), 20);
				ActionUtils.sendKeys(
						driver.findElement(By.xpath("//div[@id='tbl_proc']//tr[1]//input[@type='search']")), "97140");
				Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"),
						10);
				Commons.waitForElementToBeVisible(driver,
						driver.findElement(By.xpath("//li[@class='ng-scope selected']")), 20);
				ActionUtils.click(driver.findElement(By.xpath("//li[@class='ng-scope selected']")));
				test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/05/2016");
				HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/05/2016");
				test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page when DOS is not in Range");
				ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
				Assert.assertEquals(Commons.capturemessage(driver, 90).trim(),
						"The Date of Service must be between the from and through dates of the Pre-Authorization.");
				test.log(LogStatus.INFO, "*******************Got  Message  Assertion Pass****************");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Error*************" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true, "Pre Autrization Data on Add charge not displayed");
			}
			test.log(LogStatus.INFO, "Test Validate PreAuth Values in Addcharge parge Dropdown Pass");
			extent.flush();
		} catch (Exception e) {
			test.log(LogStatus.INFO,
					"***************Test Validate PreAuth Values in Addcharge parge Dropdown Fail*************"
							+ Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, "Test Validate PreAuth Values in Addcharge parge Dropdown Fail"
					+ Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void TestProviderPOSLink() throws InterruptedException {
		try {
			test = extent.startTest(
					"To Validate Providers listed are ONLY those that are associated to the select Location. Providers that are NOT associated to the selected Location are not displayed. ",
					"To Validate Providers listed are ONLY those that are associated to the select Location"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient with complete Case");
			strActualtext = AddChargeUtils.QuickSearchPatient(driver, "EMRMedicare");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			try {
				test.log(LogStatus.INFO, "Remove location and billing provider");
				Commons.waitforElement(driver, By.xpath("//button[contains(@id,'btnClrLocation')]"), 10);
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'btnClrLocation')]")));
				test.log(LogStatus.INFO, "Adding Loaction");
				ActionUtils.click(CasePage.LocationMagnifier(driver));
				ActionUtils.click(CasePage.LocationSearchButton(driver));
				ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
				test.log(LogStatus.INFO, "Adding Billing provider Not available to selected loaction");
				driver.findElement(By.xpath(".//*[@id='autosearchBP']//input")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath(".//*[@id='autosearchBP']//input")).sendKeys("****");
				driver.findElement(By.xpath(".//*[@id='autosearchBP']//input")).click();
				Commons.ExistandDisplayElement(driver,
						By.xpath("//span[contains(text(),'No Billing Provider found !')]"), 45);
				Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'No Billing Provider found !')]"))
						.getText().contains("No Billing Provider found !"));
				System.out.println("AssertionPass");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Error*************" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true, "Test Provider/POS Link Fail");
			}
			test.log(LogStatus.INFO, "Test Provider/POS Link Pass");
			extent.flush();
		} catch (Exception e) {
			test.log(LogStatus.INFO,
					"***************Test Provider POS Link Fail*************" + Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, "Test Provider/POS Link Fail" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void TestPlanofcareexpired() throws InterruptedException {
		try {
			test = extent.startTest("To Validate .Plan of care expired Warning mesage",
					"To Validate .Plan of care expired Warning mesage" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient with complete Case");
			strActualtext = AddChargeUtils.AddPatientCaseCompleted(driver, "TestPlanofcare");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			try {
				test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
				HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
				AddChargeUtils.AddCPTCode(driver, "97140", 1);
				test.log(LogStatus.INFO, "Enter Date in Plan of care is 90 days before DOS Field" + "14/06/2015");
				HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2015");
				// Add Billing Provider
				ActionUtils.click(AddChargesPage.BillingProviderMagnifier(driver));
				ActionUtils.click(CasePage.BillingProviderSearchButton(driver));
				ActionUtils.click(CasePage.BillingProviderList(driver));
				ActionUtils.doubleClick(driver, CasePage.BillingProviderList(driver));
				test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
				ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
				Commons.ExistandDisplayElement(driver,
						By.xpath(
								"//*[@id='warningPopup']//li[contains(text(),'The Date of Service is not less than 90 days after the Plan of Care Date')]"),
						15);
				String str_actual = ActionUtils.getText(driver.findElement(By.xpath(
						"//*[@id='warningPopup']//li[contains(text(),'The Date of Service is not less than 90 days after the Plan of Care Date')]")));
				Assert.assertTrue(str_actual
						.contains("The Date of Service is not less than 90 days after the Plan of Care Date"));
				// The Date of Service is before the Plan of Care Date. Are you
				// sure you want to continue?
				Commons.screenshot();
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='warningPopup']//button[@id='btnSaveWarning']")));
				Commons.capturemessage(driver, 90);
				System.out.println("AssertionPass");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Error*************" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true, "Test Plan of care expired Fail");
			}
			test.log(LogStatus.INFO, "Test Plan of care expired Pass");
			extent.flush();
		} catch (Exception e) {
			test.log(LogStatus.INFO,
					"***************Test Plan of care expired Fail*************" + Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, "Test Plan of care expired Fail" + Throwables.getStackTraceAsString(e));
		}
	}
}