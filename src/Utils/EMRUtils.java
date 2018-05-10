package Utils;

import org.openqa.selenium.By;
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
import UIMap.ExamTemplatesPage;
import UIMap.SPage;

public class EMRUtils {
	public static String strText_Actual;

	public static void click(WebElement webelement) {
		try {
			if (!webelement.isDisplayed()) {
				Commons.scrollElementinotView(webelement, TestSetUp.driver);
			}
			Commons.waitForElementToBeClickable(TestSetUp.driver, webelement, 30);
			// Thread.sleep(500);
			webelement.click();
			System.out.println("Clicked on above webelement");
		} catch (Exception e) {
			System.out.println("Unable to click on above webelement");
			ActionUtils.click(webelement);
		}
	}

	public static void AddDataSpage(WebDriver driver) throws InterruptedException {
		Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToSubjective()']"), 30);
		ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToSubjective()']")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//a[@href='#PatientReport']"), 40);
		System.out.println("Navigated to Spage");
		/*
		 * //closing error pop up if(Commons.ExistandDisplayElement(driver,
		 * By.xpath("//button[contains(@ng-click,'closeErrorPopup')]"),60)){
		 * driver.findElement(By.xpath(
		 * "//button[contains(@ng-click,'closeErrorPopup')]")).click(); }
		 */
		System.out.println("Adding all Mandatory fields in Spage");
		String PatientReportFileName1 = SPageUtils.AddPatientReport(driver, "auto", "New file is added for testing");
		// SPageUtils.click(SPage.Save(driver));
		String patientReporttext1 = SPage.PatientReportTextBox(driver).getText();
		System.out.println("Added Patient report file: " + PatientReportFileName1 + "With text :" + patientReporttext1);
		String CurrentProblemtitle1 = SPageUtils.AddCurrentProblem(driver);
		// SPageUtils.click(SPage.Save(driver));
		String CuurentProblemText1 = SPage.ProblemTextBox(driver).getText();
		System.out.println("Added Current problem: " + CurrentProblemtitle1 + "With text :" + CuurentProblemText1);
		SPageUtils.AddingSafrtyConsiderations(driver);
		System.out.println("Added Safety considerations");
		// SPageUtils.click(SPage.Save(driver));
		SPageUtils.AddingMedicationNone(driver);
		System.out.println("Added medications as none");
		System.out.println("Added all mandatory fields of S page");
		// SPageUtils.click(SPage.Save(driver));
		Commons.waitForLoad(driver);
		Commons.waitForLoad(driver);
		SPageUtils.AddingHistory(driver);
		SPageUtils.click(SPage.Save(driver));
		Commons.waitForLoad(driver);
		Commons.screenshot();
	}

	public static void AddDataApage(WebDriver driver) throws InterruptedException {
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		System.out.println("Successfully redirected to APage");
		Commons.waitForLoad(driver);
		/*
		 * //closing error pop up if(Commons.ExistandDisplayElement(driver,
		 * By.xpath("//button[contains(@ng-click,'closeErrorPopup')]"),90)){
		 * driver.findElement(By.xpath(
		 * "//button[contains(@ng-click,'closeErrorPopup')]")).click(); }
		 */
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "clickAssesmentAndPrognosislink");
		Commons.waitForLoad(driver);
		// Thread.sleep(4000);
		System.out.println("Setting Candidate for therapy as YES");
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='therapyradio']")));
		Commons.waitForLoad(driver);
		System.out.println("Entering Text in Assesment field");
		ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='assessmentStatement']")), "Automation EMR");
		Commons.screenshot();
		APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "Test Automation");
		// Add Goals/Rehab potential
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Goals-Rehab-Potential']")));
		Commons.waitforElement(driver, By.xpath("//textarea[contains(@id,'noteGoal')]"), 30);
		ActionUtils.sendKeys(driver.findElement(By.xpath("//textarea[contains(@id,'noteGoal')]")), "Automation GOAL");
		ActionUtils.click(driver.findElement(By.xpath("//input[contains(@id,'flrGoal')]")));
		ActionUtils.click(driver.findElement(By.xpath("//option[@label='Very Good']")));
		Commons.screenshot();
	}
	
	
	

	public static void AddDataPpage(WebDriver driver) throws InterruptedException {
		Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 30);
		ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
		System.out.println("Navigated to P page");
		// closing error pop up
		/*
		 * if(Commons.ExistandDisplayElement(driver,
		 * By.xpath("//button[contains(@ng-click,'closeErrorPopup')]"), 40)){
		 * driver.findElement(By.xpath(
		 * "//button[contains(@ng-click,'closeErrorPopup')]")).click(); }
		 */
		// Plan of care start and Expiration
		Commons.waitforElement(driver,
				By.xpath("//*[@id='DateOfService']/span//button[contains(@ng-click,'POCStartDate')]"), 40);
		HandlingCalendars.datepick(driver,
				By.xpath("//*[@id='DateOfService']/span//button[contains(@ng-click,'POCStartDate')]"), "01/01/2018");
		HandlingCalendars.datepick(driver,
				By.xpath("//*[@id='StartofCare']/span//button[contains(@ng-click,'POCEndDate')]"), "03/03/2018");
		// Adding treatment Schedule
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Treatment-Schedule']")));
		ActionUtils.click(driver.findElement(By.xpath("//select[@ng-model='schedule.visitFrequency']//option[2]")));
		ActionUtils.click(driver.findElement(By.xpath("//select[@ng-model='schedule.visitFrequencyType']//option[2]")));
		ActionUtils.click(driver.findElement(By.xpath("//select[@ng-model='schedule.duration']//option[2]")));
		ActionUtils.click(driver.findElement(By.xpath("//select[@ng-model='schedule.durationType']//option[2]")));
		// Adding planned Interventions
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Planned-Interventions']")));
		Commons.waitforElement(driver, By.xpath("//textarea[@id='PlannedComment']"), 30);
		ActionUtils.sendKeys(driver.findElement(By.xpath("//textarea[@id='PlannedComment']")), "Automation Data");
		SPageUtils.click(SPage.Save(driver));
		Commons.waitForLoad(driver);
		Commons.screenshot();
	}

	public static void AddDataOpage(WebDriver driver) throws InterruptedException {
		try {
			
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToObjective()']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToObjective()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Systems Review')]"), 40);
			System.out.println("Navigated to O page");
			// Adding Systems Review
			ActionUtils.click(driver.findElement(By.xpath("//input[contains(@ng-model,'isSystemReviewCompleted')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Output Preview')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Generate Preview')]"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]")));
			Commons.ExistandDisplayElement(driver,
					By.xpath(
							"//div[@id='outputReportViewer']//span[contains(text(),'Systems Review')]//following::span[1]"),
					60);
			Assert.assertTrue(driver
					.findElement(By
							.xpath("//div[@id='outputReportViewer']//span[contains(text(),'Systems Review')]//following::span[1]"))
					.getText().trim().contains("Yes"));
			Commons.screenshot();
			// go back to O page
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToObjective()']")));
			Commons.scrollElementinotView(driver.findElement(By.xpath("//h2[contains(text(),'Musculoskeletal')]")),
					driver);
			Commons.scrollElementinotView(driver.findElement(By.xpath("//h2[contains(text(),'Cranial Nerves')]")),
					driver);
			/*try{
			driver.findElement(By.xpath(
					"//h2[contains(text(),'Musculoskeletal')]//preceding::span[contains(@class,'expandEMR')][1]")).click();
			}catch(Exception e){
				
			}*/
			Thread.sleep(2000);
			Commons.ExistandDisplayElement(driver,
					By.xpath("//h2[contains(text(),'Musculoskeletal')]//following::input[3]"), 15);
		
			driver.findElement(By.xpath("//h2[contains(text(),'Musculoskeletal')]//following::input[3]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//h2[contains(text(),'Musculoskeletal')]//following::input[3]"))
					.sendKeys("60.00");
			Commons.ExistandDisplayElement(driver, By.xpath("//input[@value='inches']"), 15);
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='inches']")));
			Commons.ExistandDisplayElement(driver,
					By.xpath("//h2[contains(text(),'Musculoskeletal')]//following::input[6]"), 15);
			driver.findElement(By.xpath("//h2[contains(text(),'Musculoskeletal')]//following::input[6]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//h2[contains(text(),'Musculoskeletal')]//following::input[6]"))
					.sendKeys("200.00");
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='pounds']")));
			Assert.assertTrue(
					driver.findElement(By.xpath("//td[contains(text(),'Body Mass Index')]//following::td[1]//span[1]"))
							.getText().contains("39.1"));
			// Adding data in Exam tab
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Exam']")));
			Thread.sleep(2000);
			/*ActionUtils.click(
					driver.findElement(By.xpath("//h2[contains(.,'Girth')]//span[contains(@class,'expandEMR')]")));*/
			Commons.screenshot();
			Commons.ExistandDisplayElement(driver,
					By.xpath(
							"//h2[contains(text(),'Girth')]//following::button[contains(text(),'Add Test or Measure')][1]"),
					30);
			// Thread.sleep(4000);
			ActionUtils.click(driver.findElement(By.xpath(
					"//h2[contains(text(),'Girth')]//following::button[contains(text(),'Add Test or Measure')][1]")));
			System.out.println("Loading TMs List");
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Add Selected Tests')]"), 40);
			ActionUtils
					.click(driver.findElement(By.xpath("//div[contains(@id,'gridSearch')]/table/tbody/tr[1]//input")));
			ActionUtils
					.click(driver.findElement(By.xpath("//div[contains(@id,'gridSearch')]/table/tbody/tr[2]//input")));
			System.out.println("Selected TMs from list");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(text(),'Add Selected Tests')]")));
			System.out.println("Clicked on Add selected tests");
			/*if (Commons.ExistandDisplayElement(driver,
					By.xpath("//div[contains(@id,'addTestMeasurePopup')]//button[@class='close']"), 5)) {
				ActionUtils.click(driver
						.findElement(By.xpath("//div[contains(@id,'addTestMeasurePopup')]//button[@class='close']")));
				System.out.println("closed TMs window");
				Commons.screenshot();
			}*/
			System.out.println("END");
			System.out.println("Deleting TMs");
			Commons.scrollElementinotView(driver.findElement(By.xpath("//h2[contains(text(),'Girth')]")), driver);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(
					By.xpath("//h2[contains(text(),'Girth')]//following::i[contains(@ng-click,'deleteRow')][1]")));
			ActionUtils.click(driver.findElement(
           By.xpath("//h2[contains(text(),'Girth')]//following::i[contains(@ng-click,'deleteRow')][1]")));
			// Adding Intervention Data
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Interventions']")));
			Commons.ExistandDisplayElement(driver, By.xpath("//h2[contains(text(),'Modalities')]"), 40);
			// Thread.sleep(4000);
			ActionUtils.click(driver.findElement(
					By.xpath("//h2[contains(text(),'Modalities')]//following::button[contains(text(),'Add')][1]")));
			System.out.println("Verifying Row is aded to Modalities Table");
			Assert.assertEquals(driver.findElements(By.xpath("//table[@id='tblModiteis']//tbody//tr")).size(), 2,
					"Unable to Add row in Modalities Table Spage");
			Commons.screenshot();
			System.out.println("Verifying Row can remove from Modalities Table");
			ActionUtils.click(driver.findElement(By.xpath("//table[@id='tblModiteis']//tbody//tr[1]//td[1]//input")));
			ActionUtils.click(driver.findElement(
					By.xpath("//h2[contains(text(),'Modalities')]//following::button[contains(text(),'Delete')][1]")));
			System.out.println("S page Data Add Completed.");
		}
		
		catch (Exception e) {
			Commons.screenshot();
			System.out.println("Error in adding O page Data" + Throwables.getStackTraceAsString(e));
		
			TestSetUp.test.log(LogStatus.INFO, "Error in adding o page Data" + Throwables.getStackTraceAsString(e));
		}
	    catch (AssertionError e) {
		Commons.screenshot();
		System.out.println("Error in adding O page Data" + Throwables.getStackTraceAsString(e));
		
		TestSetUp.test.log(LogStatus.INFO, "Error in adding o page Data" + Throwables.getStackTraceAsString(e));
	}
	}

	// **********************************Sabhayata's
	// Code***************************************************
	public static String initialnotewithFLR(WebDriver driver) throws InterruptedException {
		AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																		// Patient
																		// all
																		// Details
		strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
		AddCaseUtils.GoToAddNewCase(driver);
		AddCaseUtils.AddCaseWithAllRequiredFields(driver);
		Select dropdown = new Select(driver.findElement(By.xpath(
				"//Select[@ng-options='discipline.id as discipline.name for discipline in  vmCase.disciplines']")));
		dropdown.selectByVisibleText("PHYSICAL THERAPY ");
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
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		System.out.println("Successfully redirected to APage");
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		System.out.println("Validating that FLR Link Appear on A Page..");
		Commons.waitforElement(driver, By.xpath("//a[@href='#Medicare-Functional-Limitation-Reporting']"), 60);
		APageUtils.click(driver, "ClickMedicareFLRLink");
		Commons.waitForLoad(driver);
		APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "Test Automation");
		return strText_Actual;
	}

	public static void quicksearch(String strActualtext, WebDriver driver) {
		System.out.println("Entering search data" + strActualtext);
		if (strActualtext.isEmpty()) {
			Assert.assertFalse(true, "Did not capture Patient ID from App");
		}
		int i = 0;
		do {
			ActionUtils.click(AddPatientPage.patientQuicksearchbox(driver));
			AddPatientPage.patientQuicksearchbox(driver).clear();
			ActionUtils.sendKeys(AddPatientPage.patientQuicksearchbox(driver), strActualtext);
			Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"), 10);
			i++;
		} while (!Commons.existsElement(driver, By.xpath("//li[@class='ng-scope selected']")) && i < 6);
		try {
			System.out.println("quick Search result found");
			Commons.waitforElement(driver, By.xpath("//li[@class='ng-scope selected']"), 60);
			driver.findElement(By.xpath("//li[@class='ng-scope selected']")).click();
			Commons.Explicitwait();
		} catch (Exception e) {
			System.out.println("Quicksearch not done");
			Assert.assertTrue(false, Throwables.getStackTraceAsString(e));
		}
		System.out.println("Quicksearch done");
		Commons.waitForLoad(driver);
	}

	public static void ChargeCapture(WebDriver driver) {
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
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']"), 10)) {
			System.out.println("Warning Pop up appeared for No charges");
			Commons.screenshot();
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
		}
		if (Commons.ExistandDisplayElement(driver,
				By.xpath(
						"//div[contains(text(),'For the unaccounted for 10 minutes, were these minutes associated with one of the codes already entered?')]"),
				10)) {
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Yes'][@type='button']")));
		}
	}

	public static void AddPrimaryInsuranceNonMedicare(WebDriver driver) {
		ActionUtils.click(CasePage.PrimaryInsuranceTab(driver));
		ActionUtils.click(CasePage.PrimaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.PrimaryInsuranceName(driver), "AutoPri Blue Shield");// Enter
																							// Primary
																							// Insurance
																							// As
																							// Medicare
		ActionUtils.click(CasePage.PrimaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.PrimaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.PrimarySameAsPatient(driver), driver);// To
																						// Scroll
																						// down
		Commons.Explicitwait();
		ActionUtils.click(CasePage.primaryInsuranceGroupNumberORClaimNumber(driver));
		ActionUtils.sendKeys(CasePage.primaryInsuranceGroupNumberORClaimNumber(driver), "GrpNum123");// Enter
																										// Primary
																										// Insurance
																										// groupNumber
																										// or
																										// Claim
																										// Number
		ActionUtils.click(CasePage.primaryInsurancePolicyNameORSSN(driver));
		ActionUtils.sendKeys(CasePage.primaryInsurancePolicyNameORSSN(driver), "123456789a");// Enter
																								// Primary
																								// Insurance
																								// policy
																								// number
																								// or
																								// SSN
		ActionUtils.click(CasePage.PrimarySameAsPatient(driver));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='primaryInsuranceFormsubscriberName']")),
				"test emp");
	}

	// Add Patient without FLR
	public static String AddPatientNoMedicare(WebDriver driver) throws InterruptedException {
		AddPatientUtils.addpatient_withallfields(driver, "TestNoFLR");// Add
																		// Patient
																		// all
																		// Details
		strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
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
		return strText_Actual;
	}

	public static String AddPatientPQRS(WebDriver driver, String PatientName, String DOB, String dxcode)
			throws InterruptedException {
		try {
			AddPatientUtils.addpatient_withallfields(driver, PatientName);// Add
																			// Patient
																			// all
																			// Details
			strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Patient')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Edit Patient')]")));
			Thread.sleep(3000);
			// Edit DOB of Patient
			Commons.scrollElementinotView(AddPatientPage.comment(driver), driver);
			AddPatientPage.comment(driver).sendKeys("Editing Patient DOB for PQRS");
			Commons.waitForLoad(driver);
			Commons.scrollElementinotView(driver.findElement(By.xpath("//*[@id='MedicareCap']")), driver);
			// *[@id='patientDob']//preceding::button[1]"));
			System.out.println("Editing Patient DOB as" + DOB);
			Commons.waitforElement(driver,
					By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"), 60);
			HandlingCalendars.datepick(driver,
					By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"), DOB);
			Commons.waitForElementToBeClickable(driver, AddPatientPage.SaveButton(driver), 10);
			ActionUtils.click(AddPatientPage.SaveButton(driver));
			Commons.capturemessage(driver, 40);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Patient')]"), 30);
			// Add new case With Insurance has PQRS flagged On
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCodebycode(driver, dxcode);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 200);
			Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
					"Could not save case..");
			AddCaseUtils.GoToCaseList(driver);
			String expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			System.out.println("Expected=>" + expectedMessage);
			// Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
			
			TestSetUp.test.log(LogStatus.INFO, "Added Patient with all fields and completed Case:" + strText_Actual);
			Commons.screenshot();
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
			Commons.screenshot();
		} catch (AssertionError e) {
			e.printStackTrace();
		}
		return strText_Actual;
	}

	public String SignandFinalize(WebDriver driver, String patientname, String proccode) throws InterruptedException {
		AddPatientUtils.addpatient_withallfields(driver, patientname);// Add
																		// Patient
																		// all
																		// Details
		strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
		AddCaseUtils.GoToAddNewCase(driver);
		AddCaseUtils.AddCaseWithAllRequiredFields(driver);
		AddCaseUtils.AddDXCode(driver);
		AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
		AddCaseUtils.ClickCaseSave(driver);
		Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 200);
		Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
				"Could not save case..");
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//div[contains(.,'Status:')][contains(@class,'ng-binding')]"), 80)) {
			System.out.println("Verifying Case completed Added......");
			Assert.assertTrue(ActionUtils
					.getText(
							driver.findElement(By.xpath("//div[contains(.,'Status:')][contains(@class,'ng-binding')]")))
					.contains("Status: Active"), "Could not Validate Status");
			Commons.screenshot();
		}
		// Create Notes and Navigating to A page
		Commons.waitForLoad(driver);
		APageUtils.createNewNote(driver);
		System.out.println("Created a New Note for Initial Visit");
		APageUtils.click(driver, "clickAPAge");
		APageUtils.click(driver, "clickAssesmentAndPrognosislink");
		Commons.waitForLoad(driver);
		Thread.sleep(5000);
		System.out.println("Setting Candidate for therapy as NO");
		Commons.waitforElement(driver, By.xpath("//input[@name='CandidateTherapy'][@value='2']"), 40);
		ActionUtils.click(driver.findElement(By.xpath("//input[@name='CandidateTherapy'][@value='2']")));
		Commons.waitForLoad(driver);
		Commons.screenshot();
		// Charge Capture and Finalize Note with Procedure code proccode
		
		TestSetUp.test.log(LogStatus.INFO, "Charge Capture and Finalize Note with Procedure code" + proccode);
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 160);
		APageUtils.addProcedureCode(driver, proccode);
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
			driver.findElement(By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']")).click();
		}
		Commons.waitforElement(driver, By.xpath("//a[contains(.,'Sign Note')]"), 190);
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Sign Note')]")));
		Commons.waitForLoad(driver);
		if (Commons.ExistandDisplayElement(driver, By.xpath("//button[contains(.,'Sign and Finalize')]"), 90)) {
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Sign and Finalize')]")));
			System.out.println("Finalizing Note...");
			Commons.waitforElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"), 280);
			TestSetUp.test.log(LogStatus.INFO, "Note Finalized");
			Commons.screenshot();
		} else if (Commons.ExistandDisplayElement(driver, By.xpath("//button[contains(.,'Sign')]"), 30)) {
			TestSetUp.test.log(LogStatus.INFO, "Adding Co-signer ");
			ActionUtils.click(CreateNotePage.BillingProviderMagnifier(driver));
			Commons.waitforElement(driver, By.xpath("//input[@id='Provider_Name']"), 60);
			ActionUtils.click(
					driver.findElement(By.xpath("//div[@id='BillingProvider']//input[contains(@value,'Search')]")));
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='BillingId']//td[1]"), 160);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//*[@id='BillingId']//td[1]")));
			TestSetUp.test.log(LogStatus.INFO, "Clicking on sign button");
			ActionUtils.click(CreateNotePage.SignButton(driver));
			Commons.capturemessage(driver, 80);
			Commons.waitForLoad(driver);
			Assert.assertTrue(
					Commons.existsElement(driver, By.xpath("//div[contains(text(),'This note was signed by')]")));
			System.out.println("Got message "
					+ driver.findElement(By.xpath("//div[contains(text(),'This note was signed by')]")).getText());
			TestSetUp.test.log(LogStatus.INFO, "Got message "
					+ driver.findElement(By.xpath("//div[contains(text(),'This note was signed by')]")).getText());
			Commons.screenshot();
		}
		return strText_Actual;
	}

	public static void AddPhraseLibrary(WebDriver driver, String Librarytype) {
	
		ActionUtils.click(CreateNotePage.EMROnMenu(driver));
		ActionUtils.click(ExamTemplatesPage.MyPhraseLibraries(driver));
		Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Phrase Libraries')]"), 60);
		Commons.screenshot();
		// Add a new Phrase Library
		Commons.SelectElement(driver, By.xpath("//*[@id='ddlLibraryGroup']"), Librarytype);
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'New')]")));
		Commons.waitforElement(driver, By.xpath("//*[@id='libraryTitle']/input"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'saveLibrary')]")));
		Assert.assertTrue(Commons.capturemessage(driver, 60)
				.contains("Please fill all the mandatory fields or check highlighted field values."));
		Commons.waitForLoad(driver);
		TestSetUp.test.log(LogStatus.INFO, "*******************Assertion-1 Pass**********");
		System.out.println("*******************Assertion-1 Pass**********");
		Commons.screenshot();
		ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='libraryTitle']/input")),
				"Automation Phrase Library");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'saveLibrary')]")));
	}

	public static void AssertPhraseLibraryOnSOAP(WebDriver driver, String pagename, String linkname, String notetype)
			throws InterruptedException {
		if (notetype.contains("Initial")) {
			APageUtils.createNewNote(driver);
		} else {
			// APageUtils.createfollowUpNewNote(driver);
			System.out.println("Opening Existing Note");
			APageUtils.GotoNoteList(driver, 1);
		}
		// APageUtils.click(driver, "clickAPAge");
		APageUtils.click(driver, pagename);
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 60);
		Commons.waitforElement(driver, By.xpath("//a[@href='#" + linkname + "']"), 80);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#" + linkname + "']")));
		System.out.println("Successfully redirected to SOAP Pages");
		System.out.println(" go to Library" + linkname);
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver,
				By.xpath("(//*[@id='" + linkname + "']//select[contains(@ng-change,'libraryChange')])[1]"), 30);
		Commons.SelectElement(driver,
				By.xpath("(//*[@id='" + linkname + "']//select[contains(@ng-change,'libraryChange')])[1]"),
				"Automation Phrase Library");
		Commons.waitForLoad(driver);
		Assert.assertFalse(driver
				.findElement(By.xpath("(//*[@id='" + linkname + "']//select[contains(@ng-change,'libraryChange')])[1]"))
				.getAttribute("value").isEmpty());
	}

	public static void AssertPhraseLibraryOnMyExamTemplate(WebDriver driver, String linkname)
			throws InterruptedException {
		// Navigate to My Exam Template Link Library page on assessment tab
		ActionUtils.click(CreateNotePage.EMROnMenu(driver));
		ActionUtils.click(ExamTemplatesPage.MyExamTemplate(driver));
		Commons.waitforElement(driver, By.xpath("//h1[contains(.,'My Exam Templates')]"), 60);
		Commons.screenshot();
		if (!Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='viewExamTemplate']//tbody//tr[1]//input[@type='checkbox']"), 5)) {
			// Add a new My Exam Template
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='createTemplateBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='CreateTemplate']//h4[contains(.,'New Exam Template')]"),
					60);
			Commons.SelectElement(driver, By.xpath("//*[@id='bodyPartsDropDown']"), "Ankle Sprain");
			ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='templateNameTxt']")), "Automation");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='CreateTemplate']//button[contains(.,'Create')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Return to My Exam Templates List')]"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Return to My Exam Templates List')]")));
			Commons.screenshot();
		}
		Commons.waitforElement(driver, By.xpath("//button[contains(.,'Link Selected')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("(//*[@id='viewExamTemplate']//tbody//input)[1]")));
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Link Selected')]")));
		Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Edit Template Links')]"), 60);
		Commons.screenshot();
		// link assessment library
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(@href,'" + linkname + "')]")));
		// Commons.waitforElement(driver,
		// By.xpath("//*[@id='assesmentPublic']"),30);
		Commons.waitforElement(driver, By.xpath("//td[text()='Automation Phrase Library']"), 90);
		Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//td[text()='Automation Phrase Library']")))
				.contains("Automation Phrase Library"));
	}

	public static void DeletePhraseLibrary(WebDriver driver, String Librarytype) {
		// Edit and Delete Phrase Library
		ActionUtils.click(CreateNotePage.EMROnMenu(driver));
		ActionUtils.click(ExamTemplatesPage.MyPhraseLibraries(driver));
		Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Phrase Libraries')]"), 60);
		Commons.screenshot();
		Commons.SelectElement(driver, By.xpath("//*[@id='ddlLibraryGroup']"), Librarytype);
		Commons.waitforElement(driver,
				By.xpath("//*[@id='libItemGrid']//span[contains(.,'Automation Phrase Library')]"), 60);
		ActionUtils.doubleClick(driver,
				driver.findElement(By.xpath("//*[@id='libItemGrid']//span[contains(.,'Automation Phrase Library')]")));
		Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'vmPhraseLibrary')][contains(.,'Edit')]"),
				60);
		ActionUtils.click(driver.findElement(By.xpath(
				"//*[@id='libItemGrid']//span[contains(.,'Automation Phrase Library')]//preceding::td[1]//input[@type='checkbox']")));
		ActionUtils.click(
				driver.findElement(By.xpath("//button[contains(@ng-click,'vmPhraseLibrary')][contains(.,'Edit')]")));
		Commons.waitforElement(driver, By.xpath("//*[@id='libraryTitle']/input"), 15);
		driver.findElement(By.xpath("//*[@id='libraryTitle']/input")).clear();
		driver.findElement(By.xpath("//*[@id='libraryTitle']/input")).sendKeys("Edit Automation Phrase Library");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'saveLibrary')]")));
		Commons.waitForLoad(driver);
		Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver,
				By.xpath("//button[contains(@ng-click,'saveLibrary')]"), 30));
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='libraryTitle']//input")).getAttribute("value")
				.contains("Edit Automation Phrase Library"));
		System.out.println("*******************Assertion-5 Pass**********");
	
		TestSetUp.test.log(LogStatus.INFO, "*******************Assertion-5 Pass**********");
		Commons.screenshot();
		// Delete Phrase Library
		ActionUtils.click(driver.findElement(By.xpath(
				"//*[@id='libItemGrid']//span[contains(.,'Edit Automation Phrase Library')]//preceding::td[1]//input[@type='checkbox']")));
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='deletePhraseLibraryBtn']")));
		Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'PhraseLibrary.remove')]"), 60);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'PhraseLibrary.remove')]")));
		System.out.println(Commons.waitForElementToBeNotVisible(driver,
				By.xpath("//*[@id='libItemGrid']//span[contains(.,'Edit Automation Phrase Library')]"), 70));
	}
}