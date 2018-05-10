package com.bms.M11.EMR;

import org.apache.commons.lang3.RandomStringUtils;
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
import UIMap.ExamTemplatesPage;
import UIMap.LoginPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.CreateNoteUtils;
import Utils.SearchPatientUtils;

public class TestInterventionLibraries extends TestSetUp {
	// TC- To Verify Add,Edit,Delete Intervention Libraries.
	// TC- Add Intervention Library as Public
	// TC- Add Intervention Library as Global and public
	
	String Libname = "Automation Intervention Library"+RandomStringUtils.randomAlphanumeric(10).toUpperCase();
	@Test(enabled = true, priority = 1)
	public void Add_Edit_Delete_InterventionLibraries() throws InterruptedException {
		test = extent.startTest("To Verify Add,Edit,Delete Intervention Libraries",
				"To Verify Add,Edit,Delete Intervention Libraries." + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			ActionUtils.click(ExamTemplatesPage.MyInterventionLibraries(driver));
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Interventions Libraries')]"), 60);
			Commons.screenshot();
			// Add a new Intervention Library
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'New')]")));
			Commons.waitforElement(driver, By.xpath("//input[@id='interventionNewLibraryNameTxt']"), 60);
			ActionUtils.click(driver
					.findElement(By.xpath("//button[contains(.,'Save')][contains(@class,'saveInterventionLibrary')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Please enter a name"));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-1 Pass**********");
			Commons.screenshot();
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='interventionNewLibraryNameTxt']")),
					Libname);
			ActionUtils.click(driver
					.findElement(By.xpath("//button[contains(.,'Save')][contains(@class,'saveInterventionLibrary')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Please select a type"));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-2 Pass**********");
			Commons.screenshot();
			Commons.SelectElement(driver, By.xpath("//*[@id='interventionType']"), "Activity / Mobility Techniques");
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Available to add to this library:')]"), 40);
			Commons.waitforElement(driver, By.xpath("(//*[@id='interventionsBlockChck'])[1]"), 40);
			ActionUtils.click(driver
					.findElement(By.xpath("//button[contains(.,'Save')][contains(@class,'saveInterventionLibrary')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Please select at least one item"));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-3 Pass**********");
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("(//*[@id='interventionsBlockChck'])[1]")));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Add Selected')]")));
			Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//*[@id='interventionTypeSelectedGrid']//span[contains(.,'No records found')]"), 20);
			// Make Library is public in My company
			ActionUtils
					.click(driver.findElement(By.xpath("//input[contains(@ng-model,'isPublic')][@type='checkbox']")));
			ActionUtils.click(driver
					.findElement(By.xpath("//button[contains(.,'Save')][contains(@class,'saveInterventionLibrary')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Data Saved"));
			Commons.waitForLoad(driver);
			System.out.println("*******************Assertion-4 Pass**********");
			Commons.screenshot();
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'"+Libname+"')]"), 60);
			// Navigate to O page
			AddPatientUtils.QuickAddpatient(driver);
			APageUtils.createNewNote(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToObjective()']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToObjective()']")));
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Interventions']")));
			Commons.ExistandDisplayElement(driver, By.xpath("//h2[contains(.,'Activity/Mobility Techniques')]"), 40);
			// Thread.sleep(4000);
			Commons.waitforElement(driver,
					By.xpath(
							"//h2[contains(text(),'Activity/Mobility Techniques')]//following::select[contains(@ng-change,'vmNote.getLibraryItem(3)')]"),
					30);
			Commons.SelectElement(driver,
					By.xpath(
							"//h2[contains(text(),'Activity/Mobility Techniques')]//following::select[contains(@ng-change,'vmNote.getLibraryItem(3)')]"),
					Libname);
			ActionUtils.click(driver.findElement(By.xpath(
					"//h2[contains(text(),'Activity/Mobility Techniques')]//following::button[contains(text(),'Add')][1]")));
			Commons.waitForLoad(driver);
			Assert.assertFalse(driver
					.findElement(By
							.xpath("//h2[contains(text(),'Activity/Mobility Techniques')]//following::textarea[contains(@data-ng-change,'vmNote.editItem(intervention)')][1]"))
					.getAttribute("value").isEmpty());
			System.out.println("*******************Assertion-5 Pass**********");
			Commons.screenshot();
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			ActionUtils.click(ExamTemplatesPage.MyInterventionLibraries(driver));
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Interventions Libraries')]"), 60);
			Commons.screenshot();
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'"+Libname+"')]"), 30);
			Assert.assertTrue(
					ActionUtils
							.getText(driver.findElement(By
									.xpath("//span[contains(.,'"+Libname+"')]//preceding::td[2]")))
							.contains("Yes"));
			System.out.println("*******************Assertion-6 Pass**********");
			Commons.screenshot();
			// Edit Intervention Library
			ActionUtils.click(driver.findElement(By.xpath(
					"//span[contains(.,'"+Libname+"')]//preceding::td[4]//input[@type='checkbox']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='editIntervention']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='interventionEditLibraryTxt']"), 30);
			driver.findElement(By.xpath("//*[@id='interventionEditLibraryTxt']")).clear();
			driver.findElement(By.xpath("//*[@id='interventionEditLibraryTxt']"))
					.sendKeys("Edit"+Libname);
			ActionUtils.click(driver.findElement(
					By.xpath("//button[contains(.,'Save')][contains(@class,'saveInterventionEditTabb')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Data Saved"));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'Edit"+Libname+"')]"), 35);
			System.out.println("*******************Assertion-7 Pass**********");
			Commons.screenshot();
			// Delete Intervention Library
			ActionUtils.click(driver.findElement(By.xpath(
					"//span[contains(.,'Edit"+Libname+"')]//preceding::td[4]//input[@type='checkbox']")));
			ActionUtils.click(driver
					.findElement(By.xpath("//button[contains(.,'Delete')][contains(@ng-click,'deletedSelected()')]")));
			System.out.println(Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//span[contains(.,'Edit"+Libname+"')]"), 5));
			System.out.println("*******************Assertion-8 Pass**********");
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Test Add,Edit,Delete Intervention Library Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			Assert.assertFalse(true,
					"Test Add,Edit,Delete Intervention Library Failed" + Throwables.getStackTraceAsString(a));
		}
	}
}
