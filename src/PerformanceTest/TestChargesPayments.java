package PerformanceTest;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import ReportUtils.ExtentManager;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import UIMap.CreateNotePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.FinancialUtils;
import Utils.HandlingCalendars;
import Utils.SearchPatientUtils;

public class TestChargesPayments extends TestSetUp {
	public String actual = null;
	public String strActualtext = null;
	public String strText_Actual;

	@Test(enabled = true, priority = 3)
	public void Add_Delete_Update_Payments() throws InterruptedException {
		// TC-1
		test = extent.startTest("Add Payments to a Patient", "Add Payments to a Patient" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("ChargesAndPayments");
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Search patient with complete Case");
		AddPatientUtils.QuickAddpatient(driver);
		AddChargeUtils.NavigateToPaymentPage(driver);
		test.log(LogStatus.INFO, "Navigate to Payments page");
		Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
		try {
			// Case-1
			// Add Payments to Patient
			HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"), "01/01/2017");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "100");
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_CCnum']")), "1234");
			test.log(LogStatus.INFO, "Add Location And Billing Provider fields");
			ActionUtils.click(CasePage.LocationMagnifier(driver));
			ActionUtils.click(CasePage.LocationSearchButton(driver));
			ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
			Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
			Commons.waitforElement(driver, By.xpath("//div[@id='patientCredits']//table/tbody/tr[1]"), 50);
			System.out.println("Added credits to Patient");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			Commons.waitForLoad(driver);
			ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientID']")));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Financial']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Financial']")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//h2[contains(.,'Account Summary')]"), 60)) {
				System.out.println("Got Patient's Account Summary");
			} else {
				ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Financial']")));
				Commons.ExistandDisplayElement(driver, By.xpath("//h2[contains(.,'Account Summary')]"), 60);
			}
			Commons.waitforElement(driver, By.xpath("//div[@id='gridAccountSummaryNotes']//tbody//tr[3]//td[3]"), 30);
			ActionUtils
					.getText(driver.findElement(By.xpath("//div[@id='gridAccountSummaryNotes']//tbody//tr[3]//td[3]")));
			Assert.assertEquals(
					ActionUtils.getText(
							driver.findElement(By.xpath("//div[@id='gridAccountSummaryNotes']//tbody//tr[3]//td[3]"))),
					"$100.00");
			test.log(LogStatus.INFO, "Validate Payments in Account summary");
			Commons.screenshot();
			extent.endTest(test);
			// Case-2
			// Update Payments
			test = extent.startTest("Update Payments to a Patient",
					"Update Payments to a Patient" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("ChargesAndPayments");
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient having existing Payments");
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page");
			Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
			Commons.screenshot();
			String beforeupdate = ActionUtils.getText(driver
					.findElement(By.xpath("//select[contains(@id,'sel_tblTransType')]//option[@selected='selected']")));
			test.log(LogStatus.INFO, "Select Payment COde to CASH DOS and click Update");
			ActionUtils.click(driver.findElement(
					By.xpath("//select[contains(@id,'sel_tblTransType')]//option[contains(text(),'Cash DOS')]")));
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_UpdateCredit']")));
			actual = Commons.capturemessage(driver, 60);
			if (actual != null) {
				Assert.assertEquals(actual, "Credit updated.");
			}
			String afterupdate = ActionUtils.getText(driver
					.findElement(By.xpath("//select[contains(@id,'sel_tblTransType')]//option[@selected='selected']")));
			Assert.assertNotEquals(beforeupdate, afterupdate);
			extent.endTest(test);
			// Case-3
			// Delete Payments
			test = extent.startTest("Delete Payments to a Patient",
					"Delete Payments to a Patient" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("ChargesAndPayments");
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient having existing Payments");
			Commons.screenshot();
			test.log(LogStatus.INFO, "Select Checkbox to delete Payment");
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='selectAll']")));
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_DeleteCredit']")));
			Commons.waitforElement(driver,
					By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]"), 15);
			test.log(LogStatus.INFO, "Got Confirmation Pop up");
			Commons.screenshot();
			ActionUtils.click(driver
					.findElement(By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]")));
			actual = Commons.capturemessage(driver, 60);
			if (actual != null) {
				Assert.assertEquals(actual, "Credit Deleted.");
			}
		} catch (Exception e) {
			Assert.assertFalse(true, "Payments Add Failed" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 1)
	public void AddAccountNotes() throws InterruptedException {
		try {
			test = extent.startTest("To Verify User can Add Account Notes from Charge Audit Page",
					"To Verify User can Add Account Notes from Charge Audit Page" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("ChargesAndPayments");
			test.log(LogStatus.INFO, "Browser started");
			AddChargeUtils.NavigateToChargeAuditPage(driver);
			test.log(LogStatus.INFO, "go to Transactions > Audit Charges");
			ActionUtils.click(
					driver.findElement(By.xpath("//div[@id='auditChargeSummaryGrid']//tr[1]//*[@id='chargeAnchor']")));
			test.log(LogStatus.INFO, "Select a hyperlink  numeric value under the Charge for Audit column.");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='btnCreateReport']"), 30);
			Commons.screenshot();
			test.log(LogStatus.INFO, "Click Create Report");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnCreateReport']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='mainContent']//table//th[1]"), 30);
			Commons.screenshot();
			List<WebElement> listings = driver
					.findElements(By.xpath("//*[@id='mainContent']//table//th[contains(text(),'Patient')]"));
			Random r = new Random();
			int randomValue = r.nextInt(listings.size()); // Getting a random
															// value that is
															// between 0 and
															// (list's size)-1
			String str = listings.get(randomValue).getText(); // Clicking on the
																// random item
																// in the list.
			// String
			// str=driver.findElements(By.xpath("//*[@id='mainContent']//table//th[contains(text(),'Patient')]")).get(0).getText();
			String pid = str.replaceAll("\\D+", "");
			System.out.println(str.trim());
			test.log(LogStatus.INFO,
					"Choose a patient for testing and under their charge details select Add Account Note" + str);
			test.log(LogStatus.INFO, "Click Create Report");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnNewNote" + randomValue + "']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='txtNote']"), 20);
			String Randomstr = RandomStringUtils.randomAlphabetic(10);
			driver.findElement(By.xpath("//*[@id='txtNote']")).sendKeys("Automation Test" + Randomstr);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlNoteCode']//option[contains(text(),'INFO')]")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlSticky']//option[contains(text(),'Charge')]")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnSaveNote']")));
			Commons.capturemessage(driver, 30);
			SearchPatientUtils.QuickpatientSearch(driver, pid);
			Commons.waitforElement(driver, By.xpath("//*[@id='stickyNoteIcon']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='stickyNoteIcon']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath("//div[@id='displayStickyNotePopup']//button[contains(text(),'Print')]"), 30);
			boolean Text_Expected = driver.getPageSource().contains(Randomstr);
			Assert.assertTrue(Text_Expected);
			Commons.screenshot();
			ActionUtils.click(driver
					.findElement(By.xpath("//div[@id='displayStickyNotePopup']//button[contains(text(),'Close')]")));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "***************Test Validate Mandatory Fields Add Charges Fail*************");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		test.log(LogStatus.INFO, "***************Test Validate Mandatory Fields Add Charges Pass*************");
	}

	@Test(enabled = true, priority = 1)
	public void ValidateViewChargesUserBMS() throws InterruptedException {
		try {
			test = extent.startTest("To Verify VIEW CHARGE with and without billing/Payments as BMS user",
					"To Verify VIEW CHARGE with and without billing/Payments as BMS user" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("ChargesAndPayments");
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Login as user BMS");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userBMS"), Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "Add patient with complete Case");
			AddChargeUtils.AddPatientCaseCompleted(driver, "testAutomationAddcharge");
			test.log(LogStatus.INFO, ActionUtils.getText(AddPatientPage.patientID(driver)));
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			// VIEW CHARGE without payments as BMS user
			// Adding multiple Charges to current Patient on different DOS
			test.log(LogStatus.INFO, "Adding multiple Charges to current Patient on different DOS");
			// charge-1
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			// charge-2
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "15/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "15/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97110", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			// charge-3
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "16/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "16/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97010", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			// Navigate to View Charge Screen
			AddChargeUtils.NavigateToViewChargePage(driver);
			test.log(LogStatus.INFO, "Navigated to View charges");
			// Verify charge added
			test.log(LogStatus.INFO, "Verifying Total Charge Added as :==  $150 ");
			Assert.assertTrue(
					Commons.waitforElement(driver, By.xpath("//*[@id='viewChargeGrid']/table/tfoot/tr/td[21]"), 80));
			Assert.assertTrue(
					ActionUtils.getText(driver.findElement(By.xpath("//*[@id='viewChargeGrid']/table/tfoot/tr/td[21]")))
							.contains("$126"));
			try {
				test.log(LogStatus.INFO,
						"Verifying Edit Charge,Edit all CHarges For Visit,Delete button appears on View charge");
				Assert.assertTrue(
						Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Edit Charge')]"), 30));
				Assert.assertTrue(Commons.waitforElement(driver,
						By.xpath("//button[contains(text(),'Edit all Charges for Visit')]"), 30));
				Assert.assertTrue(
						Commons.waitforElement(driver, By.xpath("//button//span[contains(text(),'Delete')]"), 30));
				test.log(LogStatus.INFO,
						"Verifying Charges display in date order with the most recent date of Service at the top.");
				int chargerowcount = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//tr")).size();
				Assert.assertEquals(chargerowcount, 3);
				test.log(LogStatus.INFO, "Total 3 rows added for charge");
				String date1 = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]")).get(0)
						.getText().toString().trim();
				String date2 = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]")).get(1)
						.getText().toString().trim();
				String date3 = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]")).get(2)
						.getText().toString().trim();
				Assert.assertEquals(date1, "06/16/2016");
				Assert.assertEquals(date2, "06/15/2016");
				Assert.assertEquals(date3, "06/14/2016");
				test.log(LogStatus.INFO, "Verifying Checkbox are unchecked");
				int checkboxcount = driver
						.findElements(By
								.xpath("//*[@id='viewChargeGrid']/table/tbody//input[contains(@type,'checkbox')][contains(@class,'ng-not-modified')]"))
						.size();
				Assert.assertEquals(checkboxcount, 3);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Assertion Failed" + Throwables.getStackTraceAsString(e));
				test.log(LogStatus.FAIL, "Test fail");
			}
			test.log(LogStatus.INFO,
					"To Verify Checkboxes convert into Lock symbol when user makes payment and charges are not editable after Payment is Done ");
			// Deleting all existing charges on current patient
			test.log(LogStatus.INFO, "Deleting Existing charges on Patient");
			ActionUtils.click(driver
					.findElement(By.xpath("//*[@id='viewChargeGrid']/table//th//input[contains(@type,'checkbox')]")));
			ActionUtils.click(driver.findElement(By.xpath("//button//span[contains(text(),'Delete')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='btnDeleteWarning']"), 20);
			driver.findElement(By.xpath("//*[@id='btnDeleteWarning']")).click();
			Commons.capturemessage(driver, 40);
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'No Charges Available')]"), 30);
			test.log(LogStatus.INFO, "Adding Charges to current user with Full Payment");
			AddChargeUtils.NavigateToAddChargePage(driver);
			// VIEW CHARGE with payments as BMS user
			// charge-1
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save charge and payment Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeandPaymentButton(driver));
			Commons.capturemessage(driver, 90);
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlPayCode']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlPayCode']//option[4]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='ddlMoneyType']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlMoneyType']//option[contains(text(),'Cash')]")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnSavePayment']")));
			Commons.waitForLoad(driver);
			Commons.capturemessage(driver, 60);
			// charge-2
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "15/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "15/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97110", 1);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save charge and payment Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeandPaymentButton(driver));
			Commons.capturemessage(driver, 90);
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlPayCode']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlPayCode']//option[4]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='ddlMoneyType']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlMoneyType']//option[contains(text(),'Cash')]")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnSavePayment']")));
			Commons.waitForLoad(driver);
			Commons.capturemessage(driver, 60);
			AddChargeUtils.NavigateToViewChargePage(driver);
			test.log(LogStatus.INFO, "Navigated to View charges");
			test.log(LogStatus.INFO,
					"To verify Checkboxes changed to Lock Symbol and charges cannot be changed after Payment is Done");
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(".//*[@id='viewChargeGrid']/table/tbody//i[contains(@class,'lock')]"), 30));
			System.out.println("Assertion Pass");
		} catch (Exception e) {
			Assert.assertFalse(true, "Test View Charge BMS User Fail" + Throwables.getStackTraceAsString(e));
		}
	}
}
