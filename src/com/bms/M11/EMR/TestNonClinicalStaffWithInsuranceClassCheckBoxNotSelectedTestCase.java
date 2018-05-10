package com.bms.M11.EMR;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.CreateNoteUtils;
import Utils.EMRUtils;
import Utils.HandlingCalendars;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestNonClinicalStaffWithInsuranceClassCheckBoxNotSelectedTestCase extends TestSetUp {
	// TC-22 DO NOT Allow Non-Clinical Staff to finalize Visit notes
	// TC-23 Allow Non-Clinical Staff to finalize Non Visit notes
	// TC-24 Allow Non-Clinical Staff to finalize Non Visit-DISCHARGE notes
	public String strActualtext = null;

	@Test(enabled = true, priority = 1)
	public void DontAllowNonClinicalStaffToFinalizeVisitNotes() throws InterruptedException {
		test = extent.startTest(
				"Dont Allow Non Clinical Staff To Finalize Visit Notes   with Don't allow Non Clinical Staff Note Sign on insurance class:  unChecked",
				"Dont Allow Non Clinical Staff To Finalize Visit Notes  with  Don't allow Non Clinical Staff Note Sign on insurance class:  unChecked"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			System.out.println("creating patient and finalizing its initial note with Billing provider");
			test.log(LogStatus.INFO, "creating patient and finalizing its initial note with Billing provider");
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
			strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
			System.out.println("patient id is " + strActualtext);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			EMRUtils.AddPrimaryInsuranceNonMedicare(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 200);
			Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
					"Could not save case..");
			APageUtils.createNewNote(driver);
			System.out.println("Created a New Note for Initial Visit");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
			System.out.println("Successfully redirected to APage");
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			APageUtils.FinalizeNote(driver);
			Commons.logout(driver);
			System.out.println("Login application Non Clinical Staff");
			test.log(LogStatus.INFO, "Login application with Non Clinical Staff");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("usernonclinicalstaff"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			EMRUtils.quicksearch(strActualtext, driver);
			Commons.waitForLoad(driver);
			CreateNoteUtils.NavigatetoCreateNote(driver);
			Commons.waitForLoad(driver);
			/*
			 * Select dropdown = new Select (driver.findElement(By.xpath(
			 * "//select[@data-ng-change='vmNote.caseChange()']")));
			 * dropdown.selectByVisibleText(
			 * "06/14/2015 - Case With Required Fields");
			 */
			CreateNotePage.VisitTypeddl(driver, "Followup Visit");
			HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			try {
				Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
				System.out.println("Confirmation pop up appeared");
				driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
			} catch (Exception e) {
				System.out.println("Confirmation pop up Not appeared");
			}
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 250);
			System.out.println("follow up Note crated Successfully");
			test.log(LogStatus.INFO, "follow up Note crated Successfully");
			EMRUtils.ChargeCapture(driver);
			ActionUtils.click(CreateNotePage.SignButton(driver));
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath("//table//td[contains(text(),'You must select a co-signer.')]"), 10));
			System.out.println("Got message " + driver
					.findElement(By.xpath("//table//td[contains(text(),'You must select a co-signer.')]")).getText());
			test.log(LogStatus.INFO,
					"Got message " + driver
							.findElement(By.xpath("//table//td[contains(text(),'You must select a co-signer.')]"))
							.getText());
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='closeErrorPopup()']")));
			System.out.println("Adding Co-signer ");
			test.log(LogStatus.INFO, "Adding Co-signer ");
			ActionUtils.click(CreateNotePage.BillingProviderMagnifier(driver));
			// Thread.sleep(2000);
			Commons.waitforElement(driver, By.xpath("//input[@id='Provider_Name']"), 60);
			// driver.findElement(By.xpath("//input[@id='Provider_Name']")).sendKeys("autoone");
			ActionUtils.click(
					driver.findElement(By.xpath("//div[@id='BillingProvider']//input[contains(@value,'Search')]")));
			// Thread.sleep(2000);
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='BillingId']//td[1]"), 160);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//*[@id='BillingId']//td[1]")));
			test.log(LogStatus.INFO, "Clicking on sign button");
			ActionUtils.click(CreateNotePage.SignButton(driver));
			Commons.waitForLoad(driver);
			Assert.assertTrue(
					Commons.existsElement(driver, By.xpath("//div[contains(text(),'This note was signed by')]")));
			System.out.println("Got message "
					+ driver.findElement(By.xpath("//div[contains(text(),'This note was signed by')]")).getText());
			test.log(LogStatus.INFO, "Got message "
					+ driver.findElement(By.xpath("//div[contains(text(),'This note was signed by')]")).getText());
			Commons.screenshot();
			System.out.println("Login application with Billing provider for finalizing the above signed note");
			test.log(LogStatus.INFO, "Login application with Billing provider for finalizing the above signed note ");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("username"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			EMRUtils.quicksearch(strActualtext, driver);
			APageUtils.GotoNoteList(driver, 1);
			/*
			 * Commons.waitForLoad(driver); driver.findElement(By.xpath(
			 * "//a[contains(text(),'Medical Record')]")).click();
			 * //Thread.sleep(2000); System.out.println(
			 * "clicking on '+' button of medical records");
			 * //Thread.sleep(2000); Commons.waitForElementToBeVisible(driver,
			 * driver.findElement(By.xpath(
			 * "//div[@id='gridMedicalRecordsDetails']//table//tbody//tr//td/a")
			 * ), 50); driver.findElement(By.xpath(
			 * "//div[@id='gridMedicalRecordsDetails']//table//tbody//tr//td/a")
			 * ).click(); System.out.println("clicking on signed note");
			 * //Thread.sleep(2000); Commons.waitForElementToBeVisible(driver,
			 * driver.findElement(By.xpath(
			 * "//table[@id='noteDetails']//tbody//tr//td/span")), 50);
			 * ActionUtils.doubleClick(driver, driver.findElement(By.xpath(
			 * "//table[@id='noteDetails']//tbody//tr//td/span")));
			 */
			System.out.println("Sign note clicked");
			Commons.waitForLoad(driver);
			System.out.println("clicking on sign note link");
			driver.findElement(By.xpath("//a[contains(text(),'Sign Note')]")).click();
			Commons.waitForLoad(driver);
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			/*
			 * if(Commons.ExistandDisplayElement(driver, By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"
			 * ),20)){ System.out.println(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).
			 * getText()); ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']"))); }
			 */
			Commons.waitforElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"), 380);
			System.out.println("Finalizing Note...");
			System.out.println("DO NOT Allow Non Clinical Staff to  finalize Visit notes is successful");
			test.log(LogStatus.INFO, "DO NOT Allow Non Clinical Staff to finalize Visit notes is successful");
			test.log(LogStatus.INFO, "Finalized Note.");
			Commons.screenshot();
		} catch (Exception e) {
			System.out.println("DO NOT Allow Non Clinical Staff to  finalize Visit notes is not successful ");
			Assert.assertFalse(true, "DO NOT Allow Non Clinical Staff to  finalize Visit notes fail"
					+ Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, dependsOnMethods = { "DontAllowNonClinicalStaffToFinalizeVisitNotes" }, priority = 2)
	public void AllowNonClinicalStaffofinalizeNonVisitnotes() throws InterruptedException {
		test = extent.startTest(
				"Allow Non Clinical Staff to finalize NonVisit  notes  with Don't allow Non Clinical Staff Note Sign on insurance class:  unChecked",
				"Allow Non Clinical Staff to finalize NonVisit  notes  with Don't allow Non Clinical Staff Note Sign on insurance class:  unChecked"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			System.out.println("Login application with Non Clinical Staff");
			test.log(LogStatus.INFO, "Login application with Non Clinical Staff");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("usernonclinicalstaff"),
					Commons.readPropertyValue("password"));
			/*
			 * AddPatientUtils.addpatient_withallfields(driver,"EMRMedicare");//
			 * Add Patient all Details strActualtext =
			 * ActionUtils.getText(AddPatientPage.patientID(driver));
			 * AddCaseUtils.GoToAddNewCase(driver);
			 * AddCaseUtils.AddCaseWithAllRequiredFields(driver); Select
			 * dropdown = new Select(driver.findElement(By.xpath(
			 * "//Select[@ng-options='discipline.id as discipline.name for discipline in  vmCase.disciplines']"
			 * ))); dropdown.selectByVisibleText("PHYSICAL THERAPY ");
			 * AddCaseUtils.AddDXCode(driver);
			 * EMRUtils.AddPrimaryInsuranceNonMedicare(driver);
			 * AddCaseUtils.ClickCaseSave(driver);
			 * Commons.waitforElement(driver, By.xpath(
			 * "//button[contains(.,'Edit Case')]"),200);
			 * Assert.assertTrue(Commons.existsElement(driver, By.xpath(
			 * "//button[contains(.,'Edit Case')]")),"Could not save case..");
			 */
			EMRUtils.quicksearch(strActualtext, driver);
			CreateNoteUtils.NavigatetoCreateNote(driver);
			Commons.waitForLoad(driver);
			/*
			 * dropdown = new Select (driver.findElement(By.xpath(
			 * "//select[@data-ng-change='vmNote.caseChange()']")));
			 * dropdown.selectByVisibleText("Case With Required Fields");
			 */
			CreateNotePage.VisitTypeddl(driver, "Non Visit");
			HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																										// Start
																										// Of
																										// CARE
																										// DATE
			HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
			driver.findElement(By.xpath("//textarea[@name='nonVisitStatement']"))
					.sendKeys("test Non Clinical Staff can finalize non visit notes");
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			/*
			 * try{ Commons.waitforElement(driver,By.xpath(
			 * "//*[contains(text(),'Create New Note')]"), 20);
			 * System.out.println("Confirmation pop up appeared");
			 * driver.findElement(By.xpath(
			 * "//*[contains(text(),'Create New Note')]")).click();
			 * }catch(Exception e){ System.out.println(
			 * "Confirmation pop up Not appeared"); }
			 */
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 250);
			System.out.println("Non visit Note crated Successfully");
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			/*
			 * if(Commons.ExistandDisplayElement(driver, By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"
			 * ),20)){ System.out.println(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).
			 * getText()); ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']"))); }
			 */
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					80);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Note is finalized ");
			System.out.println("creating Addendum");
			test.log(LogStatus.INFO, "creating Addendum");
			Commons.screenshot();
			Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath("//input[@value='Add Addendum']")),
					100);
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Add Addendum']")));
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeVisible(driver,
					driver.findElement(By.xpath("//textarea[@name='addendumStatement']")), 100);
			driver.findElement(By.xpath("//textarea[@name='addendumStatement']")).sendKeys("addendum ");
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 250);
			System.out.println("Non visit Note crated Successfully");
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			/*
			 * if(Commons.ExistandDisplayElement(driver, By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"
			 * ),20)){ System.out.println(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).
			 * getText()); ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']"))); }
			 */
			Commons.waitforElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"), 380);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Note is finalized ");
			System.out.println("Allow Student Provider to finalize NonVisit  notes is successful");
			test.log(LogStatus.INFO, "Allow Student Provider to finalize NonVisit  notes is successful");
			Commons.screenshot();
		} catch (Exception e) {
			System.out.println("Unable to Finalize Note");
			Assert.assertFalse(true, "Unable to Finalize Note" + Throwables.getStackTraceAsString(e));
			System.out.println("Allow Non Clinical Staff to finalize NonVisit  notes is not successful");
			test.log(LogStatus.INFO, "Allow Non Clinical Staff to finalize NonVisit  notes is not  successful");
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void AllowNonClinicalStafftofinalizNonVisitDischargenotes() throws InterruptedException {
		test = extent.startTest(
				"Allow Non Clinical Staff to finalize Non Visit Discharge notes  with Don't allow Non Clinical Staff Note Sign on insurance class:  unChecked",
				"Allow Non Clinical Staff to finalize Non Visit Discharge  notes with Don't allow Non Clinical Staff Note Sign on insurance class:  unChecked"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			System.out.println("creating patient and finalizing its initial note with Billing provider");
			test.log(LogStatus.INFO, "creating patient and finalizing its initial note with Billing provider");
			/*
			 * AddPatientUtils.addpatient_withallfields(driver,"EMRMedicare");//
			 * Add Patient all Details strActualtext =
			 * ActionUtils.getText(AddPatientPage.patientID(driver));
			 * System.out.println("patient id is " +strActualtext);
			 */
			EMRUtils.quicksearch(strActualtext, driver);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			EMRUtils.AddPrimaryInsuranceNonMedicare(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 200);
			Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
					"Could not save case..");
			APageUtils.createNewNote(driver);
			System.out.println("Created a New Note for Initial Visit");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
			System.out.println("Successfully redirected to APage");
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			APageUtils.FinalizeNote(driver);
			Commons.logout(driver);
			System.out.println("Login application with Non Clinical Staff");
			test.log(LogStatus.INFO, "Login application with Non Clinical Staff");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("usernonclinicalstaff"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			EMRUtils.quicksearch(strActualtext, driver);
			Commons.waitForLoad(driver);
			CreateNoteUtils.NavigatetoCreateNote(driver);
			Commons.waitForLoad(driver);
			/*
			 * Select dropdown = new Select (driver.findElement(By.xpath(
			 * "//select[@data-ng-change='vmNote.caseChange()']")));
			 * dropdown.selectByVisibleText(
			 * "06/14/2015 - Case With Required Fields");
			 */
			CreateNotePage.VisitTypeddl(driver, "Non Visit Discharge");
			HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																										// Start
																										// Of
																										// CARE
																										// DATE
			HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
			HandlingCalendars.datepick(driver, CreateNotePage.DateofDischarge(driver), "14/06/2015");
			driver.findElement(By.xpath("//textarea[@name='nonVisitStatement']"))
					.sendKeys("test Non Clinical Staff user can finize Non visit discharge notes");
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			/*
			 * try{ Commons.waitforElement(driver,By.xpath(
			 * "//*[contains(text(),'Create New Note')]"), 20);
			 * System.out.println("Confirmation pop up appeared");
			 * driver.findElement(By.xpath(
			 * "//*[contains(text(),'Create New Note')]")).click();
			 * }catch(Exception e){ System.out.println(
			 * "Confirmation pop up Not appeared"); }
			 */
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 250);
			System.out.println("Non visit Note crated Successfully");
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			/*
			 * if(Commons.ExistandDisplayElement(driver, By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"
			 * ),20)){ System.out.println(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).
			 * getText()); ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']"))); }
			 */
			Commons.waitforElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"), 380);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Note is finalized ");
			System.out.println("creating Addendum");
			test.log(LogStatus.INFO, "creating Addendum");
			Commons.screenshot();
			Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath("//input[@value='Add Addendum']")),
					100);
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Add Addendum']")));
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeVisible(driver,
					driver.findElement(By.xpath("//textarea[@name='addendumStatement']")), 100);
			driver.findElement(By.xpath("//textarea[@name='addendumStatement']")).sendKeys("addendum ");
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 250);
			System.out.println("Non visit Note crated Successfully");
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			/*
			 * if(Commons.ExistandDisplayElement(driver, By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"
			 * ),20)){ System.out.println(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).
			 * getText()); ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']"))); }
			 */
			Commons.waitforElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"), 380);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Note is finalized ");
			System.out.println("Allow Non Clinical Staff to finalize NonVisit discharge  notes is successful");
			test.log(LogStatus.INFO, "Allow Non Clinical Staff to finalize NonVisit Discharge notes is successful");
			Commons.screenshot();
		} catch (Exception e) {
			System.out.println("Unable to Finalize Note");
			Assert.assertFalse(true, "Unable to Finalize Note" + Throwables.getStackTraceAsString(e));
			System.out.println("Allow Non Clinical Staff to finalize NonVisit Discharge  notes is not successful");
			test.log(LogStatus.INFO,
					"Allow Non Clinical Staff to finalize NonVisit Discharge  notes is not  successful");
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
