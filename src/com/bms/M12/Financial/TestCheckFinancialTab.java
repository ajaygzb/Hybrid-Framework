package com.bms.M12.Financial;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import UIMap.CasePage;
import UIMap.CreateNotePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.Commons;
import Utils.FinancialUtils;
import Utils.HandlingCalendars;
import Utils.SearchPatientUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestCheckFinancialTab extends TestSetUp {
	public String strText_Actual;

	@Test(enabled = true, priority = 1)
	public void AmountBalancingafterAddingNewpatient() throws InterruptedException {
		test = extent.startTest("Amount Balancing after adding a new patient",
				"Amount Balancing after adding a new patient" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		strText_Actual = null;
		strText_Actual = FinancialUtils.AddPatientMedicare(driver);
		Commons.waitforElement(driver, By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]//td[5]"), 100);
		ActionUtils.doubleClick(driver,
				driver.findElement(By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]//td[5]")));
		ActionUtils.click(CasePage.CaseEditButton(driver));
		HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "01/01/2017");// Enter
																							// Assignment
																							// date
		HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "01/01/2017");// Enter
																								// Case
																								// effective
																								// date
		Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]")));
		Commons.capturemessage(driver, 200);
		test.log(LogStatus.INFO,
				"Create a new patient and a full patient registration and case information with a case effective date of 01/01/2017");
		FinancialUtils.NavigatetoAccountSummary(driver);
		test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
		String BAL_AccountSummary = ActionUtils
				.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
		Assert.assertTrue(BAL_AccountSummary.contains("$0.00"));
		FinancialUtils.NavigatetoLedgerFull(driver);
		test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
		String BAL_Ledgerfull = ActionUtils.getText(
				driver.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
		Assert.assertTrue(BAL_Ledgerfull.contains("$0.00"));
		test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
		FinancialUtils.NavigatetoLedgerVisit(driver);
		String BAL_LedgerVisit = ActionUtils
				.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
		Assert.assertTrue(BAL_LedgerVisit.contains("$0.00"));
		test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
		test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
		test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
	}

	// *****************************/*************************************//****************************//*********************//****************
	@Test(enabled = true, dependsOnMethods = { "AmountBalancingafterAddingNewpatient" }, priority = 2)
	public void AmountBalancingafterAddingCharge() throws InterruptedException {
		try {
			test = extent.startTest("Amount Balancing after adding a Charge",
					"Amount Balancing after adding a Charge" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			if (strText_Actual == null) {
				test.log(LogStatus.INFO, "Could not fetch Patient Id from previous test");
				strText_Actual = FinancialUtils.AddPatientMedicare(driver);
				ActionUtils.click(CasePage.CaseEditButton(driver));
				HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "01/01/2017");// Enter
																									// Assignment
																									// date
				HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "01/01/2017");// Enter
																										// Case
																										// effective
																										// date
				Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]"),
						60);
				ActionUtils.click(
						driver.findElement(By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]")));
				Commons.capturemessage(driver, 200);
				test.log(LogStatus.INFO,
						"Create a new patient and a full patient registration and case information with a case effective date of 01/01/2017");
			} else {
				SearchPatientUtils.QuickpatientSearch(driver, strText_Actual);
			}
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dosCalendarBtn']"), "01/01/2017");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			AddChargeUtils.AddCPTCode(driver, "97110", 2);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='planofcaredate']/span"), "01/01/2017");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='KxModifierPopup']//button[@id='btnCloseKxModifierWarning']"), 30)) {
				driver.findElement(By.xpath("//div[@id='KxModifierPopup']//button[@id='btnCloseKxModifierWarning']"))
						.click();
				Commons.capturemessage(driver, 90);
			}
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary.contains("$100.00"));
			FinancialUtils.NavigatetoLedgerFull(driver);
			test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
			String BAL_Ledgerfull = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
			Assert.assertTrue(BAL_Ledgerfull.contains("$100.00"));
			test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
			FinancialUtils.NavigatetoLedgerVisit(driver);
			String BAL_LedgerVisit = ActionUtils
					.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
			Assert.assertTrue(BAL_LedgerVisit.contains("$100.00"));
			test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
			test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
			test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Amount Balancing after Adding Charge failed" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3, dependsOnMethods = { "AmountBalancingafterAddingCharge" })
	public void AmountBalancingafterAddingPayment() throws InterruptedException {
		try {
			test = extent.startTest("Amount Balancing after adding a payment",
					"Amount Balancing after adding a payment" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			if (strText_Actual == null) {
				Assert.assertFalse(true, "Test got failed could not fetch Patient Id from previous test");
			}
			SearchPatientUtils.QuickpatientSearch(driver, strText_Actual);
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page");
			Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
			HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"), "01/01/2017");
			ActionUtils.click(driver.findElement(By.xpath("//option[@label='PATIENT PD IN OFFICE']")));
			ActionUtils.click(driver.findElement(By.xpath("//option[@label='Cash']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
			Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
			Commons.waitforElement(driver, By.xpath("//div[@id='patientCredits']//table/tbody/tr[1]"), 50);
			System.out.println("Added credits to Patient");
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary.contains("$85.00"));
			FinancialUtils.NavigatetoLedgerFull(driver);
			test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
			String BAL_Ledgerfull = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
			Assert.assertTrue(BAL_Ledgerfull.contains("$85.00"));
			test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
			FinancialUtils.NavigatetoLedgerVisit(driver);
			String BAL_LedgerVisit = ActionUtils
					.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
			Assert.assertTrue(BAL_LedgerVisit.contains("$85.00"));
			test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
			test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
			test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Amount Balancing after Adding Payment failed" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4, dependsOnMethods = { "AmountBalancingafterAddingPayment" })
	public void AmountBalancingafterAddingclinicalnote() throws InterruptedException {
		try {
			test = extent.startTest("Amount Balancing after adding a clinical note",
					"Amount Balancing after adding a clinical note" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			if (strText_Actual == null) {
				Assert.assertFalse(true, "Test got failed could not fetch Patient Id from previous test");
			}
			SearchPatientUtils.QuickpatientSearch(driver, strText_Actual);
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
			APageUtils.AddFLRDATA(driver, "G8978", "CM", "CI", "Test Automation AddendumFLRDataSubsequentNote");
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			System.out.println("Setting Candidate for therapy as NO");
			Commons.waitforElement(driver, By.xpath("//input[@name='CandidateTherapy'][@value='2']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='CandidateTherapy'][@value='2']")));
			Commons.waitForLoad(driver);
			Commons.screenshot();
			// Charge Capture and Finalize Note with Procedure code 97003
			test.log(LogStatus.INFO, "Charge Capture and Finalize Note with Procedure code 97003");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 160);
			// *[@id='autosearchProcedureCodesChargeCaptureChargeCapture']//input
			APageUtils.addProcedureCode(driver, "97140");
			APageUtils.addProcedureCode(driver, "97116");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "100");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitForLoad(driver);
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"), 70)) {
				System.out.println(
						"Warning Pop up appeared: You have not charged to the maximum number of units based on the number of one-on-one minutes documented.  Are you sure you want to complete the charges? ");
				Commons.screenshot();
				driver.findElement(By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"))
						.click();
			}
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"), 20)) {
				System.out.println(driver
						.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
			}
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					280);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Finalized Note.");
			Commons.screenshot();
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary.contains("$172.02"));
			FinancialUtils.NavigatetoLedgerFull(driver);
			test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
			String BAL_Ledgerfull = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
			Assert.assertTrue(BAL_Ledgerfull.contains("$172.02"));
			test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
			FinancialUtils.NavigatetoLedgerVisit(driver);
			String BAL_LedgerVisit = ActionUtils
					.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
			Assert.assertTrue(BAL_LedgerVisit.contains("$172.02"));
			test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
			test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
			test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Amount Balancing after Adding a Clinical Note failed" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 5, dependsOnMethods = { "AmountBalancingafterAddingclinicalnote" })
	public void AmountBalancingafterRemovingCharge() throws InterruptedException {
		try {
			test = extent.startTest("Amount Balancing after removing a charge",
					"Amount Balancing after removing a charge" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			if (strText_Actual == null) {
				Assert.assertFalse(true, "Test got failed could not fetch Patient Id from previous test");
			}
			SearchPatientUtils.QuickpatientSearch(driver, strText_Actual);
			AddChargeUtils.NavigateToViewChargePage(driver);
			// Deleting one existing charge on current patient 97140
			test.log(LogStatus.INFO, "Deleting Existing charges on Patient");
			Commons.waitforElement(driver,
					By.xpath("//*[@id='viewChargeGrid']/table//tr[1]//input[contains(@type,'checkbox')]"), 60);
			ActionUtils.click(driver.findElement(
					By.xpath("//*[@id='viewChargeGrid']/table//tr[1]//input[contains(@type,'checkbox')]")));
			ActionUtils.click(driver.findElement(By.xpath("//button//span[contains(text(),'Delete')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='btnDeleteWarning']"), 20);
			driver.findElement(By.xpath("//*[@id='btnDeleteWarning']")).click();
			Commons.capturemessage(driver, 40);
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary.contains("$127.02"));
			FinancialUtils.NavigatetoLedgerFull(driver);
			test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
			String BAL_Ledgerfull = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
			Assert.assertTrue(BAL_Ledgerfull.contains("$127.02"));
			test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
			FinancialUtils.NavigatetoLedgerVisit(driver);
			String BAL_LedgerVisit = ActionUtils
					.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
			Assert.assertTrue(BAL_LedgerVisit.contains("$127.02"));
			test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
			test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
			test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Amount Balancing after removing a charge failed" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 6, dependsOnMethods = { "AmountBalancingafterRemovingCharge" })
	public void AmountBalancingafterRemovingPayment() throws InterruptedException {
		try {
			test = extent.startTest("Amount Balancing after removing a Payment",
					"Amount Balancing after removing a Payment" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			if (strText_Actual == null) {
				Assert.assertFalse(true, "Test got failed could not fetch Patient Id from previous test");
			}
			SearchPatientUtils.QuickpatientSearch(driver, strText_Actual);
			AddChargeUtils.NavigateToPaymentPage(driver);
			// Deleting one existing charge on current patient 97140
			test.log(LogStatus.INFO, "Select Checkbox to delete Payment");
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='selectAll']")));
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_DeleteCredit']")));
			Commons.waitforElement(driver,
					By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]"), 15);
			test.log(LogStatus.INFO, "Got Confirmation Pop up");
			Commons.screenshot();
			ActionUtils.click(driver
					.findElement(By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]")));
			Commons.capturemessage(driver, 60);
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary.contains("$142.02"));
			FinancialUtils.NavigatetoLedgerFull(driver);
			test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
			String BAL_Ledgerfull = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
			Assert.assertTrue(BAL_Ledgerfull.contains("$142.02"));
			test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
			FinancialUtils.NavigatetoLedgerVisit(driver);
			String BAL_LedgerVisit = ActionUtils
					.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
			Assert.assertTrue(BAL_LedgerVisit.contains("$142.02"));
			test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
			test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
			test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Amount Balancing after removing a Payment failed" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 7, dependsOnMethods = { "AmountBalancingafterRemovingPayment" })
	public void AmountBalancingafterAddingAddendumtoClinicalnote() throws InterruptedException {
		try {
			test = extent.startTest("Amount Balancing after adding an Addendum to clinical note",
					"Amount Balancing after adding an Addendum to clinical note" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			if (strText_Actual == null) {
				Assert.assertFalse(true, "Test got failed could not fetch Patient Id from previous test");
			}
			SearchPatientUtils.QuickpatientSearch(driver, strText_Actual);
			// Create Notes and Navigating to A page
			Commons.waitForLoad(driver);
			APageUtils.GotoNoteList(driver, 1);
			APageUtils.AddAddendum(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Charge Capture')]"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitForLoad(driver);
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"), 70)) {
				System.out.println(
						"Warning Pop up appeared: You have not charged to the maximum number of units based on the number of one-on-one minutes documented.  Are you sure you want to complete the charges? ");
				Commons.screenshot();
				driver.findElement(By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"))
						.click();
			}
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"), 15)) {
				System.out.println(driver
						.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
			}
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					280);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Finalized Note.");
			Commons.screenshot();
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary.contains("$142.02"));
			FinancialUtils.NavigatetoLedgerFull(driver);
			test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
			String BAL_Ledgerfull = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
			Assert.assertTrue(BAL_Ledgerfull.contains("$142.02"));
			test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
			FinancialUtils.NavigatetoLedgerVisit(driver);
			String BAL_LedgerVisit = ActionUtils
					.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
			Assert.assertTrue(BAL_LedgerVisit.contains("$142.02"));
			test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
			test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
			test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Amount Balancing after Adding an Addendum to Clinical Note failed"
					+ Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 8, dependsOnMethods = { "AmountBalancingafterAddingAddendumtoClinicalnote" })
	public void AmountBalancingafterEditingCharge() throws InterruptedException {
		try {
			test = extent.startTest("Amount Balancing after Editing a charge",
					"Amount Balancing after Editing a charge" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			if (strText_Actual == null) {
				Assert.assertFalse(true, "Test got failed could not fetch Patient Id from previous test");
			}
			SearchPatientUtils.QuickpatientSearch(driver, strText_Actual);
			AddChargeUtils.NavigateToViewChargePage(driver);
			// Deleting one existing charge on current patient 97140
			test.log(LogStatus.INFO, "Editing Existing charges on Patient");
			Commons.waitforElement(driver,
					By.xpath("//*[@id='viewChargeGrid']/table//tr[1]//input[contains(@type,'checkbox')]"), 60);
			ActionUtils.click(driver.findElement(
					By.xpath("//*[@id='viewChargeGrid']/table//tr[1]//input[contains(@type,'checkbox')]")));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Edit Charge')]")));
			Commons.waitforElement(driver, By.xpath("//button[@id='btnCrlProdCode0']"), 60);
			driver.findElement(By.xpath("//button[@id='btnCrlProdCode0']")).click();
			// Thread.sleep(1000);
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.screenshot();
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='KxModifierPopup']//button[@id='btnCloseKxModifierWarning']"), 90)) {
				driver.findElement(By.xpath("//div[@id='KxModifierPopup']//button[@id='btnCloseKxModifierWarning']"))
						.click();
				Commons.capturemessage(driver, 90);
			}
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary.contains("$132.02"));
			FinancialUtils.NavigatetoLedgerFull(driver);
			test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
			String BAL_Ledgerfull = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
			Assert.assertTrue(BAL_Ledgerfull.contains("$132.02"));
			test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
			FinancialUtils.NavigatetoLedgerVisit(driver);
			String BAL_LedgerVisit = ActionUtils
					.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
			Assert.assertTrue(BAL_LedgerVisit.contains("$132.02"));
			test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
			test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
			test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Amount Balancing after Editing a charge failed" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 9, dependsOnMethods = { "AmountBalancingafterEditingCharge" })
	public void AmountBalancingafterEditingCase() throws InterruptedException {
		try {
			test = extent.startTest("Amount Balancing after editing the case with charges",
					"Amount Balancing after editing the case with charges" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			if (strText_Actual == null) {
				Assert.assertFalse(true, "Test got failed could not fetch Patient Id from previous test");
			}
			SearchPatientUtils.QuickpatientSearch(driver, strText_Actual);
			AddCaseUtils.GoToCaseList(driver);
			Commons.waitforElement(driver, By.xpath("//td[contains(.,'AutoPri Medicare')]"), 80);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'AutoPri Medicare')]")));
			ActionUtils.click(CasePage.CaseEditButton(driver));
			// AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "01/01/2017");// Enter
																								// Assignment
																								// date
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "01/01/2017");// Enter
																									// Case
																									// effective
																									// date
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]"), 60);
			ActionUtils
					.click(driver.findElement(By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='rebillPopup']//button[contains(.,'Yes')]"),
					12)) {
				System.out.println("Rebill pop up appeared");
				Commons.screenshot();
				driver.findElement(By.xpath("//*[@id='rebillPopup']//button[contains(.,'Yes')]")).click();
			}
			Commons.capturemessage(driver, 150);
			Commons.waitForLoad(driver);
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary.contains("$132.02"));
			FinancialUtils.NavigatetoLedgerFull(driver);
			test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
			String BAL_Ledgerfull = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
			Assert.assertTrue(BAL_Ledgerfull.contains("$132.02"));
			test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
			FinancialUtils.NavigatetoLedgerVisit(driver);
			String BAL_LedgerVisit = ActionUtils
					.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
			Assert.assertTrue(BAL_LedgerVisit.contains("$132.02"));
			test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
			test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
			test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Amount Balancing after editing the case with charges" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 10, dependsOnMethods = { "AmountBalancingafterEditingCase" })
	public void AmountBalancingafteraddingChargetoNewCase() throws InterruptedException {
		try {
			test = extent.startTest("Amount Balancing after adding a charge to new case",
					"Amount Balancing after adding a charge to new case" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			if (strText_Actual == null) {
				Assert.assertFalse(true, "Test got failed could not fetch Patient Id from previous test");
			}
			SearchPatientUtils.QuickpatientSearch(driver, strText_Actual);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			AddCaseUtils.AddDXCode(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.GoToCaseList(driver);
			AddCaseUtils.GetFirstCaseStatus(driver);
			test.log(LogStatus.INFO, "Verified Primary Insurance as AutoPri Blue Shield");
			test.log(LogStatus.INFO, "Case Completed successfully.");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(
					By.xpath("//select[@id='ddlChargeCaseList']//option[contains(@label,'Case With All Fields')]")));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dosCalendarBtn']"), "01/01/2017");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			AddChargeUtils.AddCPTCode(driver, "97110", 2);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "01/01/2017");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.waitForLoad(driver);
			/*
			 * if(Commons.ExistandDisplayElement(driver, By.xpath(
			 * "//div[@id='KxModifierPopup']//button[@id='btnCloseKxModifierWarning']"
			 * ),20)){ driver.findElement(By.xpath(
			 * "//div[@id='KxModifierPopup']//button[@id='btnCloseKxModifierWarning']"
			 * )).click(); }
			 */
			Commons.capturemessage(driver, 90);
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary.contains("$232.02"));
			FinancialUtils.NavigatetoLedgerFull(driver);
			test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
			String BAL_Ledgerfull = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
			Assert.assertTrue(BAL_Ledgerfull.contains("$232.02"));
			test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
			FinancialUtils.NavigatetoLedgerVisit(driver);
			String BAL_LedgerVisit = ActionUtils
					.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
			Assert.assertTrue(BAL_LedgerVisit.contains("$232.02"));
			test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
			test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
			test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Amount Balancing after adding a charge to new case" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 11, dependsOnMethods = { "AmountBalancingafteraddingChargetoNewCase" })
	public void AmountBalancingAfterAddingModifiertoCharge() throws InterruptedException {
		try {
			test = extent.startTest("Amount Balancing after adding a modifier to charge",
					"Amount Balancing after adding a modifier to charge" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			if (strText_Actual == null) {
				Assert.assertFalse(true, "Test got failed could not fetch Patient Id from previous test");
			}
			SearchPatientUtils.QuickpatientSearch(driver, strText_Actual);
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(
					By.xpath("//select[@id='ddlChargeCaseList']//option[contains(@label,'Case With All Fields')]")));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dosCalendarBtn']"), "01/01/2017");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			AddChargeUtils.AddCPTCode(driver, "97110", 2);
			// Select KX Modifier
			ActionUtils.click(
					driver.findElement(By.xpath("//select[@id='ddlMod10']//option[@label='KX - REQ DOC IS ON FILE']")));
			ActionUtils.click(
					driver.findElement(By.xpath("//select[@id='ddlMod11']//option[@label='KX - REQ DOC IS ON FILE']")));
			ActionUtils.click(
					driver.findElement(By.xpath("//select[@id='ddlMod20']//option[@label='KX - REQ DOC IS ON FILE']")));
			ActionUtils.click(
					driver.findElement(By.xpath("//select[@id='ddlMod21']//option[@label='KX - REQ DOC IS ON FILE']")));
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "01/01/2017");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.waitForLoad(driver);
			/*
			 * if(Commons.ExistandDisplayElement(driver, By.xpath(
			 * "//div[@id='KxModifierPopup']//button[@id='btnCloseKxModifierWarning']"
			 * ),20)){ driver.findElement(By.xpath(
			 * "//div[@id='KxModifierPopup']//button[@id='btnCloseKxModifierWarning']"
			 * )).click(); }
			 */
			Commons.capturemessage(driver, 90);
			FinancialUtils.NavigatetoAccountSummary(driver);
			test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
			String BAL_AccountSummary = ActionUtils
					.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
			Assert.assertTrue(BAL_AccountSummary.contains("$332.02"));
			FinancialUtils.NavigatetoLedgerFull(driver);
			test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
			String BAL_Ledgerfull = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
			Assert.assertTrue(BAL_Ledgerfull.contains("$332.02"));
			test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
			FinancialUtils.NavigatetoLedgerVisit(driver);
			String BAL_LedgerVisit = ActionUtils
					.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
			Assert.assertTrue(BAL_LedgerVisit.contains("$332.02"));
			test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
			test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
			test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Amount Balancing after adding a modifier to charge" + Throwables.getStackTraceAsString(e));
		}
	}
	// *************EOC*************************//
}
