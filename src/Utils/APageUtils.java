package Utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.APage;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import UIMap.CreateNotePage;

public class APageUtils {
	public static String strText_Actual = null;

	public static void click(WebElement webelement) {
		try {
			if (!webelement.isDisplayed()) {
				Commons.scrollElementinotView(webelement, TestSetUp.driver);
			}
			// Thread.sleep(500);
			ActionUtils.click(webelement);
			System.out.println("Clicked on above webelement");
		} catch (Exception e) {
			System.out.println("Unable to click on above webelement");
			e.printStackTrace();
		}
	}

	public static void click(WebDriver driver, String clickelement) {
		WebElement element;
		switch (clickelement) {
		case "clickAPAge":
			element = APage.APageLink(driver);
			try {
				ActionUtils.click(element);
				Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
			} catch (Exception e) {
				System.out.println("unable to click A page link");
			}
			break;
		case "clickAssesmentAndPrognosislink":
			element = APage.AssesmentAndPrognosislink(driver);
			try {
				APageUtils.click(element);
			} catch (Exception e) {
				System.out.println("unable to click Assesment And Prognosis link");
			}
			break;
		case "ClickDiagnoselink":
			element = APage.Diagnoselink(driver);
			try {
				APageUtils.click(element);
			} catch (Exception e) {
				System.out.println("unable to click Diagnose link");
			}
			break;
		case "ClickGoalsRehabPotentialLink":
			element = APage.AssesmentAndPrognosislink(driver);
			try {
				APageUtils.click(element);
			} catch (Exception e) {
				System.out.println("unable to click Assesment And Prognosis link");
			}
			break;
		case "ClickMedicareFLRLink":
			element = APage.MedicareFLRandTherapyCapLink(driver);
			try {
				APageUtils.click(element);
			} catch (Exception e) {
				System.out.println("unable to click Medicare FLR link");
			}
			break;
		case "ClickOPage":
			element = APage.OpageLink(driver);
			try {
				ActionUtils.click(element);
			} catch (Exception e) {
				System.out.println("unable to click O page");
			}
			break;
		case "ClickSPage":
			element = APage.SpageLink(driver);
			try {
				ActionUtils.click(element);
			} catch (Exception e) {
				System.out.println("unable to click O page");
			}
			break;
		case "ClickPPage":
			element = APage.PpageLink(driver);
			try {
				ActionUtils.click(element);
			} catch (Exception e) {
				System.out.println("unable to click P page");
			}
			break;
		}
		Commons.waitForLoad(driver);
	}

	public static void ClickCandidateTherapy(WebDriver driver, String option) {
		WebElement element = APage.CandidateTherapyCheckbox(driver, option);
		try {
			APageUtils.click(element);
		} catch (Exception e) {
			System.out.println("unable to click Candidate Therapy");
		}
	}

	public static void input(WebDriver driver, String textbox, String textdata, String title) {
		WebElement element;
		WebElement element2;
		WebElement element3;
		switch (textbox) {
		case "Assessment":
			element = APage.AssessmentTextBox(driver);
			element2 = APage.AssessmentTitleBox(driver);
			element3 = APage.AssessmentSaveButton(driver);
			try {
				element.sendKeys(textdata);
				element2.sendKeys(title);
				element3.click();
			} catch (Exception e) {
				System.out.println("unable to enter data in Assessment");
			}
			break;
		case "Prognosis":
			element = APage.PrognosisTextBox1(driver);
			element2 = APage.PrognosisTitleBox(driver);
			element3 = APage.PrognosisSaveButton(driver);
			try {
				element.sendKeys(textdata);
				element2.sendKeys(title);
				element3.click();
			} catch (Exception e) {
				System.out.println("unable to enter data in Prognosis");
			}
			break;
		case "NegativePrognosis":
			element = APage.PrognosisTextBox2(driver);
			element2 = APage.negativePrognosisTitleBox(driver);
			element3 = APage.negativePrognosisSaveButton(driver);
			try {
				element.sendKeys(textdata);
				element2.sendKeys(title);
				element3.click();
			} catch (Exception e) {
				System.out.println("unable to enter data in Negative Prognosis");
			}
			break;
		case "DiagnosisComment":
			element = APage.diagnosisobservationTextBox(driver);
			element2 = APage.diagnosisCommentTitleBox(driver);
			element3 = APage.diagnosisCommentSaveButton(driver);
			try {
				element.sendKeys(textdata);
				element2.sendKeys(title);
				element3.click();
			} catch (Exception e) {
				System.out.println("unable to enter data in Diagnosis Comment");
			}
			break;
		}
	}

	public static void addGoal(WebDriver driver, int numberOfgoals, List<String> goals) {
		WebElement element;
		int temp = 1;
		do {
			Commons.scrollElementinotView(APage.GoalAddButton(driver), driver);
			element = APage.GoalAddButton(driver);
			click(element);
			String row = Integer.toString(numberOfgoals - 1);
			element = APage.goalTextBox(driver, row);
			element.sendKeys(goals.get(temp - 1));
			element = APage.GoalSaveButton(driver);
			click(element);
			temp++;
		} while (temp < numberOfgoals);
	}

	public static void addProcedureCode(WebDriver driver, String procedurecode) throws InterruptedException {
		System.out.println("Adding procedure code:=>" + procedurecode);
		Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 40);
		ActionUtils.click(CreateNotePage.SelectProcCodeMagnifier(driver));
		System.out.println("Clicked on Magnifier");
		Thread.sleep(8000); // To avoid error during send keys as focus comes
							// with procedure description while loading pop up.
		ActionUtils.click(CreateNotePage.ProcCodeInputBox(driver));
		ActionUtils.sendKeys(CreateNotePage.ProcCodeInputBox(driver),procedurecode);
		System.out.println("Entered Proc code to search");
		ActionUtils.click(CreateNotePage.ProcCodeSearchButton(driver));
		System.out.println("Clicked on Search");
		Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='GetProcedureCodeIdChargeCapture']//td[contains(.,'" + procedurecode + "')]"),160);
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='GetProcedureCodeIdChargeCapture']//td[contains(.,'" + procedurecode + "')]")));
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//*[@id='GetProcedureCodeIdChargeCapture']//td[contains(.,'" + procedurecode + "')]")));
		Commons.waitforElement(driver, By.xpath("//*[contains(@id,'chargeCapturAllGridDiv')]//td[contains(.,'" + procedurecode + "')]"), 30);
		System.out.println("Proc code Added successfully");
		Commons.waitForLoad(driver);
	}

	public static String CountOfRedButton(WebDriver driver, String countfield) {
		WebElement element;
		String count = null;
		switch (countfield) {
		case "Diagnose":
			element = APage.DiagnoseRedDot(driver);
			count = element.getText();
			break;
		}
		return count;
	}

	public static void AddSpecificDxCode(WebDriver driver, String DxCode) {
		WebElement element;
		Commons.scrollElementinotView(driver.findElement(By.xpath("//button[contains(@data-target,'ICDCode')]")),
				driver);
		ActionUtils.click(CasePage.AddICD10CodeButton(driver));
		Commons.waitforElement(driver, By.xpath("//div[@id='ICDCode']//input[@id='dx10']"), 80);
		driver.findElement(By.xpath("//div[@id='ICDCode']//input[@id='dx10']")).sendKeys(DxCode);
		ActionUtils.click(CasePage.DiagnosisCodeSearchButton(driver));
		if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
			System.out.println("$$In safari block$$");
			Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td[1]"),
					40);
			driver.findElement(By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td[1]")).click();
			driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);
			driver.switchTo().activeElement().sendKeys(Keys.ENTER);
		} else {
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='gridSearch']//td[contains(.,'" + DxCode + "')]"), 60);
			element = driver.findElement(By.xpath("//div[@id='gridSearch']//td[contains(.,'" + DxCode + "')]"));
			ActionUtils.doubleClick(driver, element);
		}
		ActionUtils.sendKeys(CasePage.AddICD10CodeButton(driver), Keys.PAGE_DOWN);
		Commons.Explicitwait();
	}

	public static void selectNotesFromPatientPage(WebDriver driver) throws InterruptedException {
		WebElement element;
		element = APage.MedicalRecordsOnPatient(driver);
		element.click();
		// Thread.sleep(2000);
		Commons.waitforElement(driver, By.xpath("//div[@id='gridMedicalRecordsDetails']//td[1]/a"), 40);
		driver.findElement(By.xpath("//div[@id='gridMedicalRecordsDetails']//td[1]/a")).click();
		Commons.waitforElement(driver, By.xpath("//table[@id='noteDetails']//td[2]"), 40);
		element = driver.findElement(By.xpath("//table[@id='noteDetails']//td[2]"));
		ActionUtils.doubleClick(driver, element);
	}

	public static void SelectpatientFromid(WebDriver driver) throws InterruptedException {
		String patientid = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Patient id is  " + patientid);
		SearchPatientUtils.QuickpatientSearch(driver, patientid);
		APageUtils.selectNotesFromPatientPage(driver);
		click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		System.out.println("Successfully redirected to APage");
	}

	public static void selectCaseFromPatientPage(WebDriver driver) throws InterruptedException {
		WebElement element;
		AddCaseUtils.GoToCaseList(driver);
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//div[@id='gridCaseDetails']//tr[1]//td[3]"), 20);
		element = driver.findElement(By.xpath("//div[@id='gridCaseDetails']//tr[1]//td[3]"));
		ActionUtils.doubleClick(driver, element);
		Commons.waitForLoad(driver);
		System.out.println("Opened case Page");
	}

	public static String GetDxcodeFromCase(WebDriver driver) throws InterruptedException {
		String DxCocde;
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//div[@id='gridDiagnosis']//tbody/tr[1]/td[3]/span"), 30);
		Commons.scrollElementinotView(
				driver.findElement(By.xpath("//div[@id='gridDiagnosis']//tbody/tr[1]/td[3]/span")), driver);
		DxCocde = ActionUtils
				.getText(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//tbody/tr[1]/td[3]/span")));
		return DxCocde;
	}

	public static void createNewNote(WebDriver driver) throws InterruptedException {
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		try {
			System.out.println("Setting Visit Type as Initial Visit");
			Commons.waitForLoad(driver);
			CreateNotePage.VisitTypeddl(driver, "Evaluation/Transfer");
		//	Commons.Explicitwait();
			HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																										// Start
																										// Of
																										// CARE
																										// DATE
			HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
			// ActionUtils.click(CreateNotePage.AddAll(driver));
			if (Commons.existsElement(driver,
					By.xpath("//span[contains(text(),'Ankle Sprain')]//preceding::input[1]"))) {
				// ActionUtils.click(CreateNotePage.AddAll(driver,
				// "PersonalTemplates"));
				// Adding first body part from RevFlow exam Template Ankle
				// Sprain
				ActionUtils.click(
						driver.findElement(By.xpath("//span[contains(text(),'Ankle Sprain')]//preceding::input[1]")));
				ActionUtils.click(driver.findElement(By.xpath(
						"//div[@title-details='vmNote.globalTitle']/div/div[1]//input[contains(@value,'Add Selected')]")));
			} else {
				// ActionUtils.click(CreateNotePage.AddAll(driver,
				// "bodyparts"));
				// Adding first body part from RevFlow exam Template Ankle
				// Sprain
				ActionUtils.click(driver.findElement(By.xpath(
						"//div[@title-details='vmNote.globalTitle']/div/div[1]//input[contains(@value,'Add All')]")));
				// ActionUtils.click(driver.findElement(By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[1]//input[contains(@value,'Add
				// Selected')]")));
			}
			// ActionUtils.click(CreateNotePage.AddAll(driver,
			// "PublicTemplates"));
			Commons.waitForElementToBeClickable(driver, CreateNotePage.CreateNoteButton(driver), 10);
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			try {
				Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 30);
				System.out.println("Confirmation pop up appeared");
				Commons.screenshot();
				driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
			} catch (Exception e) {
				System.out.println("Confirmation pop up Not appeared **** Retry click on Create note");
				if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='btnCreateNote']"), 10))
					;
				{
					ActionUtils.JavaScriptclick(CreateNotePage.CreateNoteButton(driver));
					Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 30);
					System.out.println("Confirmation pop up appeared");
					driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
				}
			}
			// driver.navigate().refresh();
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 250);
			System.out.println("Initial-Visit Note crated Successfully");
			Commons.Explicitwait();
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	public static String NavigateToApage(WebDriver driver) throws InterruptedException {
		AddPatientUtils.QuickAddpatient(driver);
		String strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		APageUtils.createNewNote(driver);
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		System.out.println("Successfully redirected to APage");
		Commons.Explicitwait();
		return strActualtext;
	}

	public static void DropDownValuesAssertion(WebDriver driver, List<WebElement> actual, String[] expected)
			throws InterruptedException {
		for (WebElement we : actual) {
			System.out.println("*******" + we.getText());
			boolean match = false;
			for (int i = 0; i < expected.length; i++) {
				if (we.getText().trim().equals(expected[i])) {
					match = true;
				}
			}
			Assert.assertTrue(match, we.getText() + "   " + " Values Not Matched");
		}
	}

	public static void AssertFLRTABDATA(WebDriver driver, String currentstatus, String severityModifier,
			String Projectedstatus, String projectedseverityModifier) throws InterruptedException {
		Commons.ExistandDisplayElement(driver, By.xpath("//a[@id='Apage_Medicare-Functional-Limitation-Reporting']"),
				30);
		ActionUtils.click(driver.findElement(By.xpath("//a[@id='Apage_Medicare-Functional-Limitation-Reporting']")));
		if (!Commons.ExistandDisplayElement(driver, By.xpath("//select[@id='flrCurrentStatus']//option[@selected]"),
				40)) {
			System.out.println(
					"****************************Refreshing Browser as FLR data not carry forwarded.....*******************");
			driver.navigate().refresh();
			Commons.ExistandDisplayElement(driver,
					By.xpath("//a[@id='Apage_Medicare-Functional-Limitation-Reporting']"), 30);
			ActionUtils
					.click(driver.findElement(By.xpath("//a[@id='Apage_Medicare-Functional-Limitation-Reporting']")));
			Commons.ExistandDisplayElement(driver, By.xpath("//select[@id='flrCurrentStatus']//option[@selected]"), 40);
		}
		
		TestSetUp.test.log(LogStatus.INFO,
				"To verify FLR Current Status Data is Copied from Previous finalized Note/Addendum" + "____" + driver
						.findElement(By.xpath("//select[@id='flrCurrentStatus']//option[@selected]")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//select[@id='flrCurrentStatus']//option[@selected]")).getText()
				.contains(currentstatus));
		TestSetUp.test
				.log(LogStatus.INFO,
						"To verify FLR Severity Modifier Data is Copied from Previous finalized Note/Addendum" + "____"
								+ driver.findElement(
										By.xpath("//select[@id='flrCurrentSeverityModifier']//option[@selected]"))
										.getText());
		Assert.assertTrue(driver.findElement(By.xpath("//select[@id='flrCurrentSeverityModifier']//option[@selected]"))
				.getText().contains(severityModifier));
		TestSetUp.test.log(LogStatus.INFO,
				"To verify FLR Projected Status Data is Copied from Previous finalized Note/Addendum" + "____" + driver
						.findElement(By.xpath("//select[@id='flrProjectedStatus']//option[@selected]")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//select[@id='flrProjectedStatus']//option[@selected]"))
				.getText().contains(Projectedstatus));
		TestSetUp.test
				.log(LogStatus.INFO,
						"To verify FLR Projected Severity Modifier Data is Copied from Previous finalized Note/Addendum"
								+ "____"
								+ driver.findElement(
										By.xpath("//select[@id='flrProjectedSeverityModifier']//option[@selected]"))
										.getText());
		Assert.assertTrue(
				driver.findElement(By.xpath("//select[@id='flrProjectedSeverityModifier']//option[@selected]"))
						.getText().contains(projectedseverityModifier));
		TestSetUp.test.log(LogStatus.INFO,
				"To verify Severity Modifier Rationale  Data is Copied from Previous finalized Note/Addendum");
		// Assert.assertTrue(Commons.existsElement(driver,
		// By.xpath("//textarea[@id='severityModifierRationale'][contains(@class,'ng-not-empty')]")),"Severity
		// Modifier Rationale data not found");
	}

	public static void createfollowUpNewNote(WebDriver driver) throws InterruptedException {
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		try {
			System.out.println("Setting Visit Type as Followup Visit");
			CreateNotePage.VisitTypeddl(driver, "Followup Visit");
			Commons.Explicitwait();
			Commons.screenshot();
			HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																										// Start
																										// Of
																										// CARE
																										// DATE
			HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			// Thread.sleep(2000);
			try {
				Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 40);
				System.out.println("Confirmation pop up appeared");
				driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
			} catch (Exception e) {
				System.out.println("Confirmation pop up Not appeared");
			}
			// driver.navigate().refresh();
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 120);
			System.out.println("Follow-UP Note crated Successfully");
			Commons.Explicitwait();
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	// Method to Navigate to FLR Page by adding a medicare patient with complete
	// case
	public static void GotoFLR(WebDriver driver) throws InterruptedException {
		try {
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
			strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 200);
			Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
					"Could not save case..");
			/*
			 * AddCaseUtils.GoToCaseList(driver); String
			 * expectedMessage=AddCaseUtils.GetFirstCasePrimaryInsuranceName(
			 * driver); System.out.println("Expected=>"+expectedMessage );
			 * //Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"
			 * )); TestSetUp.test.log(LogStatus.INFO,
			 * "Added Patient with all fields and completed Case:"
			 * +strText_Actual);
			 */
			// Status
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//div[contains(.,'Status:')][contains(@class,'ng-binding')]"), 80)) {
				System.out.println("Verifying Case completed Added......");
				Assert.assertTrue(ActionUtils
						.getText(driver
								.findElement(By.xpath("//div[contains(.,'Status:')][contains(@class,'ng-binding')]")))
						.contains("Status: Active"), "Could not Validate Status");
			}
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
			System.out.println("Validating that FLR Link Appear on A Page..");
			Commons.waitforElement(driver, By.xpath("//a[@href='#Medicare-Functional-Limitation-Reporting']"), 60);
			APageUtils.click(driver, "ClickMedicareFLRLink");
			Commons.waitForLoad(driver);
			Commons.screenshot();
		} catch (AssertionError e) {
			e.printStackTrace();
		}
	}

	public static String FinalizeNote(WebDriver driver) throws InterruptedException {
		try {
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.waitForLoad(driver);
			System.out.println("Setting Candidate for therapy as NO");
			Commons.waitforElement(driver, By.xpath("//input[@name='CandidateTherapy'][@value='2']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='CandidateTherapy'][@value='2']")));
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			Commons.screenshot();
			// Assert.assertTrue(driver.findElements(By.xpath("//span[contains(@ng-if,'ValidationCount')]")).size()<2,"Red
			// Dot Indicator failure when Patient is not candidate for
			// therapy");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "100");
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Validate Charges')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitForLoad(driver);
			try {
				// warning-1
				// div[contains(text(),'You have not charged to the maximum
				// number of units based on the number of one-on-one minutes
				// documented. Are you sure you want to complete the charges?')]
				if (Commons.ExistandDisplayElement(driver,
						By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"), 10)) {
					System.out.println(
							"Warning Pop up appeared: You have not charged to the maximum number of units based on the number of one-on-one minutes documented.  Are you sure you want to complete the charges? ");
					Commons.screenshot();
					driver.findElement(By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"))
							.click();
				}
				// warning-2
				if (Commons.ExistandDisplayElement(driver,
						By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']"), 10)) {
					Commons.screenshot();
					ActionUtils.click(driver
							.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
				}
			} catch (Exception e) {
			}
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"), 10)) {
				System.out.println(driver
						.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
			}
			Commons.waitForLoad(driver);
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='errorGridId']//tbody/tr[1]"), 15)) {
				System.out.println("Cannot Finalize Note as Hard stop messages triggred");
				Commons.screenshot();
			} else {
				Commons.waitForLoad(driver);
				Commons.ExistandDisplayElement(driver,
						By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"), 100);
				System.out.println("Finalizing Note...");
				
				TestSetUp.test.log(LogStatus.INFO, "Finalized Note.");
				Commons.waitForLoad(driver);
				Commons.screenshot();
			}
		} catch (Exception e) {
			System.out.println("Unable to Finalize Note");
			Assert.assertFalse(true, "Unable to Finalize Note" + Throwables.getStackTraceAsString(e));
		}
		return strText_Actual;
	}

	public static void AddFLRDATA(WebDriver driver, String Currentstatus, String SeverityModifier1,
			String SeverityModifier2, String SeverityModifierRationale) throws InterruptedException {
		try {
			Commons.waitForLoad(driver);
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='Apage_Medicare-Functional-Limitation-Reporting']")));
			Commons.ExistandDisplayElement(driver, By.xpath("//h2[contains(text(),'Functional Limitation Reporting')]"),
					30);
			System.out.println("Scrolled to FLR Tab");
			TestSetUp.test.log(LogStatus.INFO, "Scrolled to FLR Tab");
			System.out.println("Adding FLR DATA");
			TestSetUp.test.log(LogStatus.INFO, "Adding FLR DATA" + "___" + Currentstatus + "__" + SeverityModifier1
					+ "__" + SeverityModifier2 + "__" + SeverityModifierRationale);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//select[@id='flrCurrentStatus']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='flrCurrentStatus']")));
			ActionUtils.click(driver.findElement(
					By.xpath("//*[@id='flrCurrentStatus']//option[contains(@label,'" + Currentstatus + "')]")));
			ActionUtils.click(driver.findElement(By.xpath(
					"//*[@id='flrCurrentSeverityModifier']//option[contains(@label,'" + SeverityModifier1 + "')]")));
			ActionUtils.click(driver.findElement(By.xpath(
					"//*[@id='flrProjectedSeverityModifier']//option[contains(@label,'" + SeverityModifier2 + "')]")));
			Commons.scrollElementinotView(
					driver.findElement(By.xpath("//textarea[contains(@name,'severityModifierRationale')]")), driver);
			ActionUtils.click(driver.findElement(By.xpath("//textarea[contains(@name,'severityModifierRationale')]")));
			Thread.sleep(2000);
			System.out.println("Adding Rationale");
			ActionUtils.clear(driver.findElement(By.xpath("//textarea[contains(@name,'severityModifierRationale')]")));
			ActionUtils.sendKeys(
					driver.findElement(By.xpath("//textarea[contains(@name,'severityModifierRationale')]")),
					SeverityModifierRationale);
			ActionUtils.click(APage.SaveButton(driver));
		} catch (Exception e) {
			Assert.assertFalse(true, "Could not add FLR Data" + Throwables.getStackTraceAsString(e));
		}
	}

	public static void AddAddendum(WebDriver driver) throws InterruptedException {
		try {
			// Add Addendum
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@value='Add Addendum']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Add Addendum']")));
			System.out.println("Clicked on Add addendum button");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@value='Create Note']"), 50);
			Commons.scrollElementinotView(driver.findElement(By.xpath("//textarea[@name='addendumStatement']")),
					driver);
			ActionUtils.sendKeys(driver.findElement(By.xpath("//textarea[@name='addendumStatement']")),
					"Test Addendum statement Initial Note");
			Commons.waitforElement(driver, By.xpath("//input[@value='Create Note']"), 30);
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver, driver.findElement(By.xpath("//input[@value='Create Note']")),
					30);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath("//input[@value='Create Note']")));
			System.out.println("Clicked on Create Note button for addendum");
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAPAge");
			System.out.println("Clicked on A page");
			Commons.waitForLoad(driver);
			Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		} catch (Exception e) {
			Assert.assertFalse(true, "Could not add Addendum" + Throwables.getStackTraceAsString(e));
		}
	}

	public static void GotoNoteList(WebDriver driver, int rownum) throws InterruptedException {
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.ViewPatient(driver));
			System.out.println("Opened View patient page");
			Commons.waitforElement(driver, By.xpath("//span[@id='patientID']"), 180);
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			System.out.println("Go to Pending Note");
			if (!Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@class='row btnsection']//a[contains(text(),'Medical Record')]"), 60)) {
				ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//span[@id='patientID']"), 80);
			}
			ActionUtils.click(CreateNotePage.MedicalRecordlink(driver));
			Commons.waitforElement(driver, By.xpath("//*[@id='gridMedicalRecordsDetails']//tbody//tr[1]//a"), 30);
			// Thread.sleep(3000);
			ActionUtils.click(CreateNotePage.MedicalRecordExpandlink(driver));
			Commons.waitforElement(driver,
					By.xpath("//div[@k-options='vmPatient.clinicalNoteInfoGridOptions']/table/tbody/tr[2][1]"), 30);
			System.out.println("Opened clinical note List");
			Commons.waitForLoad(driver);
			System.out.println(Commons.ExistandDisplayElement(driver,
					By.xpath("//table[@id='noteDetails']//tr[" + rownum + "]//td[4]"), 20));
			Commons.waitforElement(driver, By.xpath("//table[@id='noteDetails']//tr[" + rownum + "]//td[4]"), 60);
			System.out.println("Opened Clinical Notes lIst");
			Commons.screenshot();
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//table[@id='noteDetails']//tr[" + rownum + "]//td[4]")));
			System.out.println("Opened Note at row" + rownum);
			Commons.waitForLoad(driver);
			System.out.println("Checking For Note Locked..?");
			if (Commons.ExistandDisplayElement(driver, By.xpath("//div[@id='lockMessagePopUp']//input[@value='OK']"),
					5)) {
				Commons.screenshot();
				System.out.println("Locked Window appeared");
				ActionUtils.click(driver.findElement(By.xpath("//div[@id='lockMessagePopUp']//input[@value='OK']")));
				System.out.println("Clicked On OK");
				Commons.waitForLoad(driver);
			}
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
			System.out.println("Successfully redirected to APage");
			Commons.waitForLoad(driver);
		} catch (Exception e) {
			Assert.assertFalse(true, "Could not Go to Pending Note" + Throwables.getStackTraceAsString(e));
		}
	}

	public static void AddNonMedicarePatient(WebDriver driver, String patientname) throws InterruptedException {
		AddPatientUtils.addpatient_withallfields(driver, patientname);// Add
																		// Patient
																		// all
																		// Details
		strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
	
		TestSetUp.test.log(LogStatus.INFO, "Added Patient With All Fields ID:==>" + "   " + strText_Actual);
		AddCaseUtils.GoToAddNewCase(driver);
		AddCaseUtils.AddCaseWithAllRequiredFields(driver);
		AddCaseUtils.AddDXCode(driver);
		AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
		String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
		TestSetUp.test.log(LogStatus.INFO, expectedMessage);
		Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 40);
		// Create Notes and Navigating to A page
		Commons.waitForLoad(driver);
		APageUtils.createNewNote(driver);
		TestSetUp.test.log(LogStatus.INFO, "Created a New Note for Initial Visit");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		System.out.println("Successfully redirected to APage");
		TestSetUp.test.log(LogStatus.INFO, "Successfully redirected to APage");
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
	}

	public static void ClickCandidateTherapyNo(WebDriver driver, String option) {
		WebElement element = APage.CandidateTherapyCheckbox(driver, "no");
		try {
			APageUtils.click(element);
		} catch (Exception e) {
			System.out.println("Unable to click Candidate Therapy as No");
		}
	}
	// **********************************
}
