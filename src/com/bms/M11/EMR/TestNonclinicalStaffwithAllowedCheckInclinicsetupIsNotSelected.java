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

public class TestNonclinicalStaffwithAllowedCheckInclinicsetupIsNotSelected extends TestSetUp {
	// TC-40 DO NOT Allow Non-Clinical Staff to finalize Visit notes
	// TC-41 Allow Non-Clinical Staff to finalize Non Visit notes
	// TC-42 Allow Non-Clinical Staff to finalize Non Visit-DISCHARGE notes
	public String strActualtext = null;

	@Test(enabled = true, priority = 1)
	public void DONOTAlowNonClinicalStafToFinalizeVisitNotes() throws InterruptedException {
		test = extent.startTest(
				"DO NOT Allow Non clinical staff to finalize Visit notes with allow Non-Clinical Staff Note Sign in clinic setup :  Un-CHECKED",
				"DO NOT Allow Non clinical staff to finalize Visit notes  with  allow Non-Clinical Staff Note Sign in clinic setup :  Un-CHECKED"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			System.out.println("Login application with Non clinical staff");
			test.log(LogStatus.INFO, "Login application with Non clinical staff");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("usernonclinicalstaff2"),
					Commons.readPropertyValue("password"));
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");
			strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			Commons.SelectElement(driver, By.xpath("//select[contains(@ng-options,'vmCase.disciplines')]"),
					"PHYSICAL THERAPY");
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 200);
			Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
					"Could not save case..");
			APageUtils.createNewNote(driver);
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.waitForLoad(driver);
			System.out.println("Setting Candidate for therapy as NO");
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='CandidateTherapy'][@value='2']")));
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
			Commons.waitForLoad(driver);
			EMRUtils.ChargeCapture(driver);
			Commons.waitforElement(driver,
					By.xpath("//*[@id='signNoteMainContent']//div[text()='You are not allowed to sign this note.']"),
					90);
			Assert.assertEquals(driver
					.findElement(By
							.xpath("//*[@id='signNoteMainContent']//div[text()='You are not allowed to sign this note.']"))
					.getText(), "You are not allowed to sign this note.");
			System.out.println("text on sign page is " + driver
					.findElement(By
							.xpath("//*[@id='signNoteMainContent']//div[text()='You are not allowed to sign this note.']"))
					.getText());
			test.log(LogStatus.INFO,
					"found text " + driver
							.findElement(By
									.xpath("//*[@id='signNoteMainContent']//div[text()='You are not allowed to sign this note.']"))
							.getText());
			System.out.println("Login as Billing provider");
			test.log(LogStatus.INFO, "Login as Billing provider");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("username2"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			EMRUtils.quicksearch(strActualtext, driver);
			Commons.waitForLoad(driver);
			APageUtils.GotoNoteList(driver, 1);
			System.out.println("Sign note clicked");
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Sign Note')]")));
			Commons.waitForLoad(driver);
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			/*
			 * if(Commons.ExistandDisplayElement(driver, By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"
			 * ),10)){ System.out.println(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).
			 * getText()); ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']"))); }
			 */
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					80);
			System.out.println("Finalizing Note...");
			System.out.println("DO NOT Allow Non clinical staff to inalize Visit notes is successful ");
			test.log(LogStatus.INFO, "DO NOT Allow Non clinical staff to inalize Visit notes is successful");
		} catch (Exception e) {
			System.out.println("DO NOT Allow Non clinical staff to inalize Visit notes is not successful ");
			Assert.assertFalse(true, "DO NOT Allow Non clinical staff to inalize Visit notes fail"
					+ Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, dependsOnMethods = { "DONOTAlowNonClinicalStafToFinalizeVisitNotes" }, priority = 2)
	public void AllowNonClinicalStaffToFINALIZENOnVisitNotes() throws InterruptedException {
		test = extent.startTest(
				"Allow Non clinical staff to finalize NonVisit  notes with  allow Non-Clinical Staff Note Sign in clinic setup :  Un-CHECKED",
				"Allow Non clinical staff to finalize NonVisit  notes with  allow Non-Clinical Staff Note Sign in clinic setup :  Un-CHECKED"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			System.out.println("Login application with Non clinical staff");
			test.log(LogStatus.INFO, "Login application with Non clinical staff");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("usernonclinicalstaff2"),
					Commons.readPropertyValue("password"));
			/*
			 * Commons.waitForLoad(driver);
			 * AddPatientUtils.addpatient_withallfields(driver,"EMRMedicare");//
			 * Add Patient all Details strActualtext =
			 * ActionUtils.getText(AddPatientPage.patientID(driver));
			 * AddCaseUtils.GoToAddNewCase(driver);
			 * AddCaseUtils.AddCaseWithAllRequiredFields(driver); Select
			 * dropdown = new Select(driver.findElement(By.xpath(
			 * "//Select[@ng-options='discipline.id as discipline.name for discipline in  vmCase.disciplines']"
			 * ))); dropdown.selectByVisibleText("PHYSICAL THERAPY ");
			 * AddCaseUtils.AddDXCode(driver);
			 * AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			 * AddCaseUtils.ClickCaseSave(driver);
			 * Commons.waitforElement(driver, By.xpath(
			 * "//button[contains(.,'Edit Case')]"),200);
			 * Assert.assertTrue(Commons.existsElement(driver, By.xpath(
			 * "//button[contains(.,'Edit Case')]")),"Could not save case..");
			 * CreateNoteUtils.NavigatetoCreateNote(driver);
			 * Commons.waitForLoad(driver); dropdown = new Select
			 * (driver.findElement(By.xpath(
			 * "//select[@data-ng-change='vmNote.caseChange()']")));
			 * dropdown.selectByVisibleText("Case With Required Fields");
			 */
			EMRUtils.quicksearch(strActualtext, driver);
			CreateNoteUtils.NavigatetoCreateNote(driver);
			Commons.waitForLoad(driver);
			/*
			 * Select dropdown = new Select (driver.findElement(By.xpath(
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
			 * "//*[contains(text(),'Create New Note')]"),60);
			 * System.out.println("Confirmation pop up appeared");
			 * ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[contains(text(),'Create New Note')]"))); }catch(Exception
			 * e){ System.out.println("Confirmation pop up Not appeared"); }
			 */
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 250);
			System.out.println("Non visit Note crated Successfully");
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			/*
			 * if(Commons.ExistandDisplayElement(driver, By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"
			 * ),10)){ System.out.println(driver.findElement(By.xpath(
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
			 * ),10)){ System.out.println(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).
			 * getText()); ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']"))); }
			 */
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					80);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Note is finalized ");
			System.out.println("Allow Non clinical Staff to finalize NonVisit  notes is successful");
			test.log(LogStatus.INFO, "Allow Non clinical Staff to finalize NonVisit  notes is successful");
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

	@Test(enabled = true, dependsOnMethods = { "DONOTAlowNonClinicalStafToFinalizeVisitNotes" }, priority = 3)
	public void AllowNonclinicalstaffToFinalizNonVisitDischargenotes() throws InterruptedException {
		test = extent.startTest(
				"Allow Non clinical staff to finalize Non visit discharge  notes with  allow Non-Clinical Staff Note Sign in clinic setup :  Un-CHECKED",
				"Allow Non clinical staff to finalize Non visit discharge  notes with  allow Non-Clinical Staff Note Sign in clinic setup :  Un-CHECKED"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			System.out.println("creating patient and finalizing its initial note with Billing provider");
			test.log(LogStatus.INFO, "creating patient and finalizing its initial note with Billing provider");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("username2"),
					Commons.readPropertyValue("password"));
			EMRUtils.quicksearch(strActualtext, driver);
			/*
			 * AddPatientUtils.addpatient_withallfields(driver,"EMRMedicare");//
			 * Add Patient all Details strActualtext =
			 * ActionUtils.getText(AddPatientPage.patientID(driver));
			 * System.out.println("patient id is " +strActualtext);
			 */
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 200);
			Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
					"Could not save case..");
			APageUtils.createNewNote(driver);
			System.out.println("Created a New Note for Initial Visit");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.existsElement(driver, By.xpath("//span[contains(text(),'Save')]"));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
			System.out.println("Successfully redirected to APage");
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			APageUtils.FinalizeNote(driver);
			Commons.logout(driver);
			System.out.println("Login application with Non clinical staff");
			test.log(LogStatus.INFO, "Login application with Non clinical staff");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("usernonclinicalstaff2"),
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
					.sendKeys("test Non clinical staff user can finize Non visit discharge notes");
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
					180);
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
			 * ),10)){ System.out.println(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).
			 * getText()); ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']"))); }
			 */
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					80);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Note is finalized ");
			System.out.println("Allow Non clinical staff to finalize NonVisit discharge  notes is successful");
			test.log(LogStatus.INFO, "Allow Non clinical staff to finalize NonVisit Discharge notes is successful");
			Commons.screenshot();
		} catch (Exception e) {
			System.out.println("Unable to Finalize Note");
			Assert.assertFalse(true, "Unable to Finalize Note" + Throwables.getStackTraceAsString(e));
			System.out.println("Allow Non clinical staff to finalize NonVisit Discharge  notes is not successful");
			test.log(LogStatus.INFO,
					"Allow Non clinical staff to finalize NonVisit Discharge  notes is not  successful");
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
