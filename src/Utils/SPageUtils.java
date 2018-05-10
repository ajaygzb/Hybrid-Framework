package Utils;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import UIMap.CreateNotePage;
import UIMap.SPage;

public class SPageUtils {
	public static String strText_Actual;

	public static void click(WebElement webelement) {
		try {
			if (!webelement.isDisplayed()) {
				Commons.scrollElementinotView(webelement, TestSetUp.driver);
			}
			// Thread.sleep(500);
			webelement.click();
			System.out.println("Clicked on above webelement");
		} catch (Exception e) {
			System.out.println("Unable to click on above webelement");
			e.printStackTrace();
		}
	}

	public static String NavigateToSpage(WebDriver driver) throws InterruptedException {
		AddPatientUtils.QuickAddpatient(driver);
		String strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		APageUtils.createNewNote(driver);
		System.out.println("Successfully redirected to SPage");
		Commons.Explicitwait();
		return strActualtext;
	}

	public static void getrequiredfieldofCurrentProblem(WebDriver driver) {
		List<WebElement> elemnts = driver.findElements(
				By.xpath("//div[@value='vmSubjective.currentProblem.value']//span[@class='instructinfo2']"));
		for (WebElement element : elemnts) {
			try {
				Assert.assertEquals("(required)", element.getText());
			} catch (AssertionError e) {
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
		}
		System.out.println("Mandatory fields are Problem , Onset Date ,Mechanism of Onset");
	}

	public static void requiredfieldsofSafetyconsiderations(WebDriver driver) {
		try {
			WebElement element = driver.findElement(By.xpath("//table[@class='table Red_Flags ng-scope']//td[1]"));
			System.out.println("Mandatory fields are " + element.getText());
			Assert.assertEquals("Red Flags", element.getText());
			element = driver.findElement(By.xpath("//table[@class='table Patient_Allergies ng-scope']//td[1]"));
			System.out.println("Mandatory fields are " + element.getText());
			Assert.assertEquals("Patient has Allergies (required)", element.getText());
			element = driver.findElement(By.xpath("//table[@class='table Patient_Precautions ng-scope']//td[1]"));
			System.out.println("Mandatory fields are " + element.getText());
			Assert.assertEquals("Patient Precautions/ Contraindications (required)", element.getText());
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (NoSuchElementException e) {
			System.out.println("S page Medications red dot not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	public static void requiredfieldsofMedication(WebDriver driver) {
		try {
			WebElement element = driver.findElement(By.xpath("//table[@class='table Red_Flags ng-scope']//td[1]"));
			System.out.println("Mandatory fields are " + element.getText());
			Assert.assertEquals("Red Flags (required)", element.getText());
			element = driver.findElement(By.xpath("//table[@class='table Patient_Allergies ng-scope']//td[1]"));
			System.out.println("Mandatory fields are " + element.getText());
			Assert.assertEquals("Patient has Allergies (required)", element.getText());
			element = driver.findElement(By.xpath("//table[@class='table Patient_Precautions ng-scope']//td[1]"));
			System.out.println("Mandatory fields are " + element.getText());
			Assert.assertEquals("Patient Precautions/ Contraindications (required)", element.getText());
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (NoSuchElementException e) {
			System.out.println("S page Medications red dot not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	public static String AddPatientReport(WebDriver driver, String filename, String value) throws InterruptedException {
		SPageUtils.click(SPage.PatientReportTextBox(driver));
		SPage.PatientReportTextBox(driver).sendKeys(value);
		System.out.println("text " + value + " is inserted in S page patient report text box");
		SPageUtils.click(SPage.PatientReportLoadText(driver));
		filename = filename + new Random().nextInt(1000) + new Random().nextInt(1000);
		SPage.PatientReportLoadText(driver).sendKeys(filename);
		System.out.println("text " + filename + " is inserted in S page patient report load file name text box");
		SPageUtils.click(SPage.PatientReportLoadSaveButton(driver));
		// Thread.sleep(2000);
		System.out.println("Save button is clicked ");
		return filename;
	}

	public static String AddCurrentProblem(WebDriver driver) {
		SPageUtils.click(SPage.ProblemTextBox(driver));
		String value = "a new Current problem is loaded ";
		SPage.ProblemTextBox(driver).sendKeys(value);
		System.out.println("text " + value + " is inserted in cuurent problem text box");
		String title = "current problem" + new Random().nextInt(1000) + new Random().nextInt(1000);
		SPageUtils.click(SPage.ProblemLibTextBox(driver));
		SPage.ProblemLibTextBox(driver).sendKeys(title);
		System.out.println("text " + title + " is inserted in current load text box");
		value = "01/11/2014";
		SPageUtils.click(SPage.OnSetDate(driver));
		SPage.OnSetDate(driver).sendKeys(value);
		System.out.println("text " + value + " is inserted in onSet date text box");
		WebElement element = driver.findElement(By.xpath("//div[@class='checkbox']/label/input"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		SPageUtils.click(SPage.CurrentProblemSave(driver));
		System.out.println("current problem is added ");
		return title;
	}

	public static void AddProblemList(WebDriver driver) {
		System.out.println("Adding problem list");
		SPageUtils.click(SPage.AddNewProblemListButton(driver));
		Commons.waitforElement(driver, By.xpath("//div[@value='vmSubjective.currentProblem.value']//tr[2]//textarea"),
				90);
		SPageUtils.click(
				driver.findElement(By.xpath("//div[@value='vmSubjective.currentProblem.value']//tr[2]//textarea")));
		driver.findElement(By.xpath("//div[@value='vmSubjective.currentProblem.value']//tr[2]//textarea"))
				.sendKeys("new problem added to list");
		SPageUtils.click(SPage.CurrentProblemSave(driver));
		System.out.println("new Problem  is added to list");
	}

	public static void deletingListProblem(WebDriver driver) {
		System.out.println("deleting problem from list");
		SPageUtils.click(SPage.AddNewProblemListButton(driver));
		Commons.waitforElement(driver,
				By.xpath("//div[@value='vmSubjective.currentProblem.value']//td[1]//input[@type='checkbox']"), 90);
		SPageUtils.click(driver.findElement(
				By.xpath("//div[@value='vmSubjective.currentProblem.value']//td[1]//input[@type='checkbox']")));
		SPageUtils.click(SPage.CurrentProblemTrashIcon(driver));
		System.out.println("Problem  is deleted from list");
	}

	public static void AddingSafrtyConsiderations(WebDriver driver) {
		System.out.println("Adding Safety considerations ");
		SPageUtils.click(SPage.SafetyConsiderationLink(driver));
		Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='SafetyConsiderations']//h1//div[contains(.,'Safety Considerations')]"), 30);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SPageUtils.click(SPage.ReDFlagSafety(driver));
		SPageUtils.click(SPage.AlergySafety(driver));
		SPageUtils.click(SPage.PrecautionsFlag(driver));
	}

	public static void AddingMedicationNone(WebDriver driver) {
		System.out.println("Adding medication as none");
		SPageUtils.click(SPage.MedicationLink(driver));
		Commons.waitforElement(driver, By.xpath("//div[@id='Medications']//span[contains(.,'None')]//input"), 90);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SPageUtils.click(driver.findElement(By.xpath("//div[@id='Medications']//span[contains(.,'None')]//input")));
		System.out.println("medication none is added ");
		// SPageUtils.click(SPage.Save(driver));
	}

	public static void AddingHistory(WebDriver driver) {
		System.out.println("Adding History ");
		SPageUtils.click(SPage.HistoryLink(driver));
		Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='History']//h1//div[contains(.,'History')]"), 40);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SPageUtils.click(SPage.OtherMedicalConditions(driver));
		SPage.OtherMedicalConditionstextBox(driver).sendKeys("test automation");
		SPageUtils.click(SPage.PriorSurgery(driver));
		SPage.PriorSurgerytextArea(driver).sendKeys("test automation");
		System.out.println("history is added ");
		// SPageUtils.click(SPage.Save(driver));
	}

	public static void creatingfollowupNote(WebDriver driver) throws InterruptedException {
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		CreateNotePage.VisitTypeddl(driver, "Followup Visit");
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		try {
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
			System.out.println("Confirmation pop up appeared");
			driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
		} catch (Exception e) {
			System.out.println("Confirmation pop up Not appeared");
		}
		// driver.navigate().refresh();
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 250);
		System.out.println("follow up Note crated Successfully");
	}

	public static void creatingfollowupReEvaluationNote(WebDriver driver) throws InterruptedException {
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		CreateNotePage.VisitTypeddl(driver, "Plan of Care Update");
		Commons.screenshot();
		HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																									// Start
																									// Of
																									// CARE
																									// DATE
		HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		try {
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
			System.out.println("Confirmation pop up appeared");
			driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
		} catch (Exception e) {
			System.out.println("Confirmation pop up Not appeared");
		}
		// driver.navigate().refresh();
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 250);
		System.out.println("Followup Re-Evaluation Note crated Successfully");
	}

	public static void creatingfollowupDischargeNote(WebDriver driver) throws InterruptedException {
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		CreateNotePage.VisitTypeddl(driver, "Followup Discharge");
		HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																									// Start
																									// Of
																									// CARE
																									// DATE
		HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
		driver.findElement(By.xpath("//input[@data-ng-model='vmNote.dischargeDate']")).sendKeys("2017");
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		try {
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
			System.out.println("Confirmation pop up appeared");
			driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
		} catch (Exception e) {
			System.out.println("Confirmation pop up Not appeared");
		}
		// driver.navigate().refresh();
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 250);
		System.out.println("Followup Discharge Note crated Successfully");
	}

	public static void creatingNonVisitNote(WebDriver driver) throws InterruptedException {
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		System.out.println("Setting Visit Type as Non Visit");
		CreateNotePage.VisitTypeddl(driver, "Non Visit");
		Commons.Explicitwait();
		HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																									// Start
																									// Of
																									// CARE
																									// DATE
		HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
		ActionUtils.sendKeys(CreateNotePage.NonVisitStatement(driver), "Non-Visit Statement test");
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		Commons.waitforElement(driver, By.xpath("//a[contains(.,'Sign Note')]"), 190);
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Sign Note')]")));
		Commons.waitforElement(driver, By.xpath("//button[contains(.,'Sign and Finalize')]"), 190);
		System.out.println("Non-Visit Note crated Successfully");
	}

	public static void creatingNonVisitDischargeNote(WebDriver driver) throws InterruptedException {
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		System.out.println("Setting Visit Type as Non Visit");
		CreateNotePage.VisitTypeddl(driver, "Non Visit Discharge");
		Commons.Explicitwait();
		HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																									// Start
																									// Of
																									// CARE
																									// DATE
		HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
		ActionUtils.sendKeys(CreateNotePage.NonVisitStatement(driver), "Non-Visit Statement test");
		// Adding Discharge Date
		HandlingCalendars.datepick(driver, CreateNotePage.DateofDischarge(driver), "14/06/2015");
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		CreateNotePage.SignandFinalizeButton(driver);
		Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Sign and Finalize')]"), 170);
		System.out.println("Non-Visit Note crated Successfully");
	}

	public static void addPatientWithFullCase(WebDriver driver) throws InterruptedException {
		try {
			AddPatientUtils.addpatient_withallfields(driver, "SpageAutomation");
			String strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
			System.out.println("Quick Add a Patient with ID==>" + strActualtext);
			AddCaseUtils.GoToAddNewCase(driver);
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015");// Enter
																									// Case
																									// effective
																									// date
			AddCaseUtils.ClickCaseSave(driver);
			ActionUtils.click(CasePage.CaseEditButton(driver));
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			Commons.Explicitwait();
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			// AddCaseUtils.PrimaryInsuranceAddPreauthorization(driver);
			// AddCaseUtils.PrimaryInsuranceAddEligibity(driver);
			// AddCaseUtils.PrimaryInsuranceAddDocotorsOrder(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			Assert.assertEquals(expectedMessage, "Case updated successfully.");
			APageUtils.createNewNote(driver);
			System.out.println("Successfully redirected to SPage");
			Commons.Explicitwait();
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	public static void creatingInitialnoteWithAllSMandatory(WebDriver driver) throws InterruptedException {
		try {
			System.out.println("initail note with all mandatory fields of S page will be created");
			String PatientReportFileName1 = SPageUtils.AddPatientReport(driver, "auto",
					"New file is added for testing");
			SPageUtils.click(SPage.Save(driver));
			String patientReporttext1 = SPage.PatientReportTextBox(driver).getText();
			System.out.println(
					"Added Patient report file: " + PatientReportFileName1 + "With text :" + patientReporttext1);
			String CurrentProblemtitle1 = SPageUtils.AddCurrentProblem(driver);
			SPageUtils.click(SPage.Save(driver));
			String CuurentProblemText1 = SPage.ProblemTextBox(driver).getText();
			System.out.println("Added Current problem: " + CurrentProblemtitle1 + "With text :" + CuurentProblemText1);
			SPageUtils.AddingSafrtyConsiderations(driver);
			System.out.println("Added Safety considerations");
			SPageUtils.click(SPage.Save(driver));
			Commons.waitForLoad(driver);
			SPageUtils.AddingHistory(driver);
			SPageUtils.click(SPage.Save(driver));
			Commons.waitForLoad(driver);
			SPageUtils.AddingMedicationNone(driver);
			System.out.println("Added medications as none");
			System.out.println("initail note with all mandatory fields of S page is created");
			SPageUtils.click(SPage.Save(driver));
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='CandidateTherapy'][@value='2']")));
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
			Commons.waitForLoad(driver);
			try {
				Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
				ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "10");
				// ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate
				// Charges')]")));
			} catch (Exception e) {
			}
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitForLoad(driver);
			try {
				Commons.waitforElement(driver,
						By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']"), 10);
				System.out.println("Warning Pop up appeared for No charges");
				if (driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']"))
						.isDisplayed()) {
					ActionUtils.click(driver
							.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
				}
			} catch (Exception e) {
			}
			try {
				Commons.waitforElement(driver,
						By.xpath(
								"//div[contains(text(),'For the unaccounted for 10 minutes, were these minutes associated with one of the codes already entered?')]"),
						10);
				System.out.println("Warning Pop up appeared for No charges");
				if (driver
						.findElement(By
								.xpath("//div[contains(text(),'For the unaccounted for 10 minutes, were these minutes associated with one of the codes already entered?')]"))
						.isDisplayed()) {
					ActionUtils.click(driver.findElement(By.xpath("//input[@value='Yes'][@type='button']")));
				}
			} catch (Exception e) {
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
					80);
			System.out.println("Finalizing Note...");
		
			TestSetUp.test.log(LogStatus.INFO, "Finalized Note.");
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	public static void navigateToIntervensionLib(WebDriver driver, String libname) {
		ActionUtils.click(CreateNotePage.EMROnMenu(driver));
		ActionUtils.click(SPage.AddPhraseLibraries(driver));
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='libGroup']")));
		dropdown.selectByVisibleText(libname);
		Commons.waitForLoad(driver);
	}
}
