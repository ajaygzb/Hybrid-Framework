package com.bms.M11.EMR;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.APage;
import UIMap.AddPatientPage;
import UIMap.SPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.SPageUtils;

public class TestGoalsInEMR extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void VerifyWhileAddingProblemListfromLibraryonSpagePlusSymbolAppears() {
		String strActualtext;
		test = extent.startTest("Verify While Adding Problem List from Library on Spage " + " symbol appears  ",
				"To Verify + sign appears on s page when problem is added " + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
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
			SPage.CurrentProblemLink(driver).click();
			SPage.ProblemTextBox(driver).click();
			Select dropdown = new Select(SPage.ProblemLoadFromLib(driver));
			List<WebElement> options = dropdown.getOptions();
			if (options.size() > 1)
				dropdown.selectByIndex(1);
			else
				SPageUtils.AddCurrentProblem(driver);
			Commons.waitforElement(driver, By.xpath("//i[@ng-show='nitem.isAddProblemGoal']"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).isDisplayed());
			test.log(LogStatus.INFO, "+ is Appears to add goals ");
			System.out.println(
					"Verify While Adding Problem List from Library on Spage Plus Symbol Appears is successful");
			test.log(LogStatus.INFO,
					"Verify While Adding Problem List from Library on Spage Plus Symbol Appears is successful");
		} catch (Exception e) {
			System.out.println(
					" Verify While Adding Problem List from Library on Spage Plus Symbol Appears is not successful ");
			Assert.assertFalse(true,
					"Verify While Adding Problem List from Library on Spage Plus Symbol Appears is not successful"
							+ Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void VerifyWhileTyingProblemListonSpagePlusSymbolDoesNotAppears() {
		String strActualtext;
		test = extent.startTest("Verify While Adding Problem List from Library on Spage " + " symbol appears  ",
				"To Verify + sign appears on s page when problem is added " + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
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
			SPage.CurrentProblemLink(driver).click();
			SPage.ProblemTextBox(driver).click();
			Select dropdown = new Select(SPage.ProblemLoadFromLib(driver));
			List<WebElement> options = dropdown.getOptions();
			if (options.size() > 1)
				dropdown.selectByIndex(1);
			else
				SPageUtils.AddCurrentProblem(driver);
			Commons.waitforElement(driver, By.xpath("//i[@ng-show='nitem.isAddProblemGoal']"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).isDisplayed());
			SPage.ProblemTextBox(driver).click();
			SPage.ProblemTextBox(driver).sendKeys("212");
			Thread.sleep(2000);
			Assert.assertFalse(driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).isDisplayed());
			test.log(LogStatus.INFO, "+ does not Appears to add goals ");
			System.out.println("Verify While Tying Problem List on Spage Plus Symbol does not Appears is successful ");
			test.log(LogStatus.INFO,
					"Verify While Tying Problem List on Spage Plus Symbol does not Appears is successful ");
		} catch (Exception e) {
			System.out.println(
					" Verify While Tying Problem List on Spage Plus Symbol does not Appears is not successful ");
			Assert.assertFalse(true,
					"Verify While Tying Problem List on Spage Plus Symbol does not Appears is not successful "
							+ Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void VerifyUserCanAssociateGoalsByClickingOnPlusSymbol() {
		String strActualtext;
		test = extent.startTest("Verify User Can Associate Goals By Clicking On Plus Symbol " + " symbol appears  ",
				"To Verify User Can Associate Goals By Clicking On Plus Symbol  " + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
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
			SPage.CurrentProblemLink(driver).click();
			SPage.ProblemTextBox(driver).click();
			Select dropdown = new Select(SPage.ProblemLoadFromLib(driver));
			List<WebElement> options = dropdown.getOptions();
			if (options.size() > 1)
				dropdown.selectByIndex(1);
			else
				SPageUtils.AddCurrentProblem(driver);
			System.out.println("clicking on + sign");
			test.log(LogStatus.INFO, "clicking on + sign");
			Commons.waitforElement(driver, By.xpath("//i[@ng-show='nitem.isAddProblemGoal']"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).isDisplayed());
			driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).click();
			System.out.println("Selecting goal from goals pop up");
			test.log(LogStatus.INFO, "Selecting goal from goals pop up");
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='addSelectedProblemGoalToNote()']"), 120);
			driver.findElement(By.xpath("//div[@id='problemGoalsGrid']//tbody/tr/td[2]")).click();
			String goalDesc = driver.findElement(By.xpath("//div[@id='problemGoalsGrid']//tbody/tr/td[3]/span"))
					.getText();
			System.out.println("goal with description " + goalDesc + "is selected ");
			test.log(LogStatus.INFO, "goal with description " + goalDesc + "is selected ");
			driver.findElement(By.xpath("//button[@ng-click='addSelectedProblemGoalToNote()']")).click();
			System.out.println("goal with description " + goalDesc + "is added");
			test.log(LogStatus.INFO, "goal with description " + goalDesc + "is added ");
			System.out.println(
					"Verify User Can Associate Goals By Clicking On Plus Symbol " + " symbol appears   is successful ");
			test.log(LogStatus.INFO,
					"Verify User Can Associate Goals By Clicking On Plus Symbol " + " symbol appears   is successful ");
		} catch (Exception e) {
			System.out.println(" Verify User Can Associate Goals By Clicking On Plus Symbol "
					+ " symbol appears   is not successful ");
			Assert.assertFalse(true, "Verify User Can Associate Goals By Clicking On Plus Symbol "
					+ " symbol appears   is not successful" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void VerifyAssociatedGoalswillAppearonApageGoalsSection() {
		String strActualtext;
		test = extent.startTest("Verify Associated Goals will Appearon A page Goals Section",
				"Verify Associated Goals will Appearon A page Goals Section" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
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
			SPage.CurrentProblemLink(driver).click();
			SPage.ProblemTextBox(driver).click();
			Select dropdown = new Select(SPage.ProblemLoadFromLib(driver));
			List<WebElement> options = dropdown.getOptions();
			if (options.size() > 1)
				dropdown.selectByIndex(1);
			else
				SPageUtils.AddCurrentProblem(driver);
			System.out.println("clicking on + sign");
			test.log(LogStatus.INFO, "clicking on + sign");
			Commons.waitforElement(driver, By.xpath("//i[@ng-show='nitem.isAddProblemGoal']"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).isDisplayed());
			driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).click();
			System.out.println("Selecting goal from goals pop up");
			test.log(LogStatus.INFO, "Selecting goal from goals pop up");
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='addSelectedProblemGoalToNote()']"), 120);
			driver.findElement(By.xpath("//div[@id='problemGoalsGrid']//tbody/tr/td[2]")).click();
			String goalDesc = driver.findElement(By.xpath("//div[@id='problemGoalsGrid']//tbody/tr/td[3]/span"))
					.getText();
			System.out.println("goal with description " + goalDesc + "is selected ");
			test.log(LogStatus.INFO, "goal with description " + goalDesc + "is selected ");
			driver.findElement(By.xpath("//button[@ng-click='addSelectedProblemGoalToNote()']")).click();
			System.out.println("goal with description " + goalDesc + "is added");
			test.log(LogStatus.INFO, "goal with description " + goalDesc + "is added ");
			Commons.waitForElementToBeClickable(driver, APage.APageLink(driver), 120);
			APage.APageLink(driver).click();
			APage.GoalsRehabPotentialLink(driver).click();
			String xpath = "//table[@id='goalsListTable']//tr//td/textarea[contains(text(),'" + goalDesc + "')]";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			Assert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
			System.out.println("Verify Associated Goals will Appearon A page Goals Section  is successful");
			test.log(LogStatus.INFO, "Verify Associated Goals will Appearon A page Goals Section  is successful");
		} catch (Exception e) {
			System.out.println(" Verify Associated Goals will Appearon A page Goals Section  is not successful ");
			Assert.assertFalse(true, "Verify Associated Goals will Appearon A page Goals Section is not successful"
					+ Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 5)
	public void VerifyAssociatedGoalsWillAppearOnPPageViewGoalsSection() {
		String strActualtext;
		test = extent.startTest("Verify Associated Goals Will Appear On P Page View Goals Section",
				"Verify Associated Goals Will Appear On P Page View Goals Section" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
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
			SPage.CurrentProblemLink(driver).click();
			SPage.ProblemTextBox(driver).click();
			Select dropdown = new Select(SPage.ProblemLoadFromLib(driver));
			List<WebElement> options = dropdown.getOptions();
			if (options.size() > 1)
				dropdown.selectByIndex(1);
			else
				SPageUtils.AddCurrentProblem(driver);
			System.out.println("clicking on + sign");
			test.log(LogStatus.INFO, "clicking on + sign");
			Commons.waitforElement(driver, By.xpath("//i[@ng-show='nitem.isAddProblemGoal']"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).isDisplayed());
			driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).click();
			System.out.println("Selecting goal from goals pop up");
			test.log(LogStatus.INFO, "Selecting goal from goals pop up");
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='addSelectedProblemGoalToNote()']"), 120);
			driver.findElement(By.xpath("//div[@id='problemGoalsGrid']//tbody/tr/td[2]")).click();
			String goalDesc = driver.findElement(By.xpath("//div[@id='problemGoalsGrid']//tbody/tr/td[3]/span"))
					.getText();
			System.out.println("goal with description " + goalDesc + "is selected ");
			test.log(LogStatus.INFO, "goal with description " + goalDesc + "is selected ");
			driver.findElement(By.xpath("//button[@ng-click='addSelectedProblemGoalToNote()']")).click();
			System.out.println("goal with description " + goalDesc + "is added");
			test.log(LogStatus.INFO, "goal with description " + goalDesc + "is added ");
			Commons.waitForElementToBeClickable(driver, APage.PpageLink(driver), 120);
			APage.PpageLink(driver).click();
			APage.PlannedInterventionLink(driver).click();
			Commons.waitForElementToBeClickable(driver,
					driver.findElement(By.xpath("//button[@id='planViewCurrentProblems']")), 120);
			driver.findElement(By.xpath("//button[@id='planViewGoals']")).click();
			String xpath = "//div[@id='popupGoals']//tbody//tr//td[contains(text(),'" + goalDesc + "')]";
			Commons.waitforElement(driver, By.xpath(xpath), 120);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			Assert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
			System.out.println("Verify Associated Goals Will Appear On P Page View Goals Section is successful");
			test.log(LogStatus.INFO, "Verify Associated Goals Will Appear On P Page View Goals Section is successful");
		} catch (Exception e) {
			System.out.println(" Verify Associated Goals Will Appear On P Page View Goals Section is not successful ");
			Assert.assertFalse(true,
					"Verify Associated Goals Will Appear On P Page View Goals Section is not successful"
							+ Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 6)
	public void VerifyWhileNavigatingOutFromEMRThenNavigateBackToSPagePlusSymbolNextToProblemListGetsDisappears() {
		String strActualtext;
		test = extent.startTest(
				"Verify While Navigating Out From EMR Then Navigate Back To SPage Plus Symbol Next To Problem List Gets Disappears",
				"Verify While Navigating Out From EMR Then Navigate Back To SPage Plus Symbol Next To Problem List Gets Disappears"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
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
			SPage.CurrentProblemLink(driver).click();
			SPage.ProblemTextBox(driver).click();
			Select dropdown = new Select(SPage.ProblemLoadFromLib(driver));
			List<WebElement> options = dropdown.getOptions();
			if (options.size() > 1)
				dropdown.selectByIndex(1);
			else
				SPageUtils.AddCurrentProblem(driver);
			System.out.println("clicking on + sign");
			test.log(LogStatus.INFO, "clicking on + sign");
			Commons.waitforElement(driver, By.xpath("//i[@ng-show='nitem.isAddProblemGoal']"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).isDisplayed());
			driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).click();
			System.out.println("Selecting goal from goals pop up");
			test.log(LogStatus.INFO, "Selecting goal from goals pop up");
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='addSelectedProblemGoalToNote()']"), 120);
			driver.findElement(By.xpath("//div[@id='problemGoalsGrid']//tbody/tr/td[2]")).click();
			String goalDesc = driver.findElement(By.xpath("//div[@id='problemGoalsGrid']//tbody/tr/td[3]/span"))
					.getText();
			System.out.println("goal with description " + goalDesc + "is selected ");
			test.log(LogStatus.INFO, "goal with description " + goalDesc + "is selected ");
			driver.findElement(By.xpath("//button[@ng-click='addSelectedProblemGoalToNote()']")).click();
			System.out.println("goal with description " + goalDesc + "is added");
			test.log(LogStatus.INFO, "goal with description " + goalDesc + "is added ");
			Thread.sleep(3000);
			AddPatientPage.PatientNameInHeader(driver).click();
			Thread.sleep(3000);
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver, driver.findElement(By.xpath("//a[@href='#MedicalRecord']")),
					120);
			driver.findElement(By.xpath("//a[@href='#MedicalRecord']")).click();
			Commons.waitForElementToBeClickable(driver,
					driver.findElement(By.xpath("//div[@id='gridMedicalRecordsDetails']//tbody//tr/td/a")), 120);
			driver.findElement(By.xpath("//div[@id='gridMedicalRecordsDetails']//tbody//tr/td/a")).click();
			Commons.waitForElementToBeClickable(driver,
					driver.findElement(By.xpath("//table[@id='noteDetails']//tr//td[4]")), 120);
			driver.findElement(By.xpath("//table[@id='noteDetails']//tr//td[4]")).click();
			Commons.waitForLoad(driver);
			SPage.CurrentProblemLink(driver).click();
			Assert.assertFalse(driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).isDisplayed());
			System.out.println(
					" Verify While Navigating Out From EMR Then Navigate Back To SPage Plus Symbol Next To Problem List Gets Disappears is successful ");
			test.log(LogStatus.INFO,
					"Verify While Navigating Out From EMR Then Navigate Back To SPage Plus Symbol Next To Problem List Gets Disappears is successful ");
		} catch (Exception e) {
			System.out.println(
					" Verify While Navigating Out From EMR Then Navigate Back To SPage Plus Symbol Next To Problem List Gets Disappears is not successful ");
			Assert.assertFalse(true,
					"Verify Associated Goals Will Appear On P Page View Goals Section is not successful"
							+ Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 7)
	public void VerifyDeAssociationOfGoalsByDeletingProblemFromSPage() {
		String strActualtext;
		test = extent.startTest("Verify De Association Of Goals By Deleting Problem From SPage",
				"Verify De Association Of Goals By Deleting Problem From SPage" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
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
			SPage.CurrentProblemLink(driver).click();
			SPage.ProblemTextBox(driver).click();
			Select dropdown = new Select(SPage.ProblemLoadFromLib(driver));
			List<WebElement> options = dropdown.getOptions();
			if (options.size() > 1)
				dropdown.selectByIndex(1);
			else
				SPageUtils.AddCurrentProblem(driver);
			System.out.println("clicking on + sign");
			test.log(LogStatus.INFO, "clicking on + sign");
			Commons.waitforElement(driver, By.xpath("//i[@ng-show='nitem.isAddProblemGoal']"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).isDisplayed());
			driver.findElement(By.xpath("//i[@ng-show='nitem.isAddProblemGoal']")).click();
			System.out.println("Selecting goal from goals pop up");
			test.log(LogStatus.INFO, "Selecting goal from goals pop up");
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='addSelectedProblemGoalToNote()']"), 120);
			driver.findElement(By.xpath("//div[@id='problemGoalsGrid']//tbody/tr/td[2]")).click();
			String goalDesc = driver.findElement(By.xpath("//div[@id='problemGoalsGrid']//tbody/tr/td[3]/span"))
					.getText();
			System.out.println("goal with description " + goalDesc + "is selected ");
			test.log(LogStatus.INFO, "goal with description " + goalDesc + "is selected ");
			driver.findElement(By.xpath("//button[@ng-click='addSelectedProblemGoalToNote()']")).click();
			System.out.println("goal with description " + goalDesc + "is added");
			test.log(LogStatus.INFO, "goal with description " + goalDesc + "is added ");
			Thread.sleep(3000);
			System.out.println("deleting problem from list ");
			test.log(LogStatus.INFO, "deleting problem from list ");
			SPage.ProblemTextBox(driver).click();
			SPage.ProblemTextBox(driver).clear();
			System.out
					.println("problem is deleted from s page and now checking goal should be remove from A and P Page");
			test.log(LogStatus.INFO,
					"problem is deleted from s page and now checking goal should be remove from A and P Page");
			Commons.waitForElementToBeClickable(driver, APage.APageLink(driver), 120);
			APage.APageLink(driver).click();
			APage.GoalsRehabPotentialLink(driver).click();
			Commons.waitForLoad(driver);
			String xpath = "//table[@id='goalsListTable']//tr//td/textarea[contains(text(),'" + goalDesc + "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			System.out.println("Goal is removed frpm A page and now checking goal should be removed from P page also");
			test.log(LogStatus.INFO,
					"Goal is removed frpm A page and now checking goal should be removed from P page also");
			Commons.waitForElementToBeClickable(driver, APage.PpageLink(driver), 120);
			APage.PpageLink(driver).click();
			APage.PlannedInterventionLink(driver).click();
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver,
					driver.findElement(By.xpath("//button[@id='planViewCurrentProblems']")), 120);
			driver.findElement(By.xpath("//button[@id='planViewGoals']")).click();
			xpath = "//div[@id='popupGoals']//p[contains(text(),'No record found')]";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			System.out.println(
					" Verify While Navigating Out From EMR Then Navigate Back To SPage Plus Symbol Next To Problem List Gets Disappears is successful ");
			test.log(LogStatus.INFO,
					"Verify While Navigating Out From EMR Then Navigate Back To SPage Plus Symbol Next To Problem List Gets Disappears is successful ");
		} catch (Exception e) {
			System.out.println(
					" Verify While Navigating Out From EMR Then Navigate Back To SPage Plus Symbol Next To Problem List Gets Disappears is not successful ");
			Assert.assertFalse(true,
					"Verify Associated Goals Will Appear On P Page View Goals Section is not successful"
							+ Throwables.getStackTraceAsString(e));
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
