package Utils;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import TestBase.TestSetUp;
import UIMap.CasePage;

public class AddCaseUtils {
	public static String patientId = null;

	public static void GoToAddNewCase(WebDriver driver) {
		// ActionUtils.click(CasePage.PatientOnMenu(driver));//Enter Case Name
		Commons.waitForLoad(driver);
		ActionUtils.click(CasePage.CaseOnMenu(driver));
		Commons.waitforElement(driver, By.xpath("//*[@id='casesId']"), 90); // RB
		Commons.Explicitwait();
		Commons.Explicitwait();
		ActionUtils.click(driver.findElement(By.id("casesId")));
		System.out.println("Clicked on case hyperlink");
		Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='gridCaseDetails']/table/tbody"), 100);
		Commons.waitForLoad(driver);
		Commons.Explicitwait();
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//button[text()='New Case']"), 80);// Waiting
																					// for
																					// New
																					// Case
																					// button
		Commons.Explicitwait();
		Commons.screenshot();
		ActionUtils.JavaScriptclick(CasePage.NewCaseButton(driver));
	}

	public static void GoToCaseList(WebDriver driver) throws InterruptedException {
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		ActionUtils.click(CasePage.PatientOnMenu(driver));// Enter Case Name
		Commons.existsElement(driver, By.xpath("//span[contains(.,'Cases')]"));
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Cases')]"), 80);
		Commons.waitForElementToBeClickable(driver, driver.findElement(By.xpath("//span[contains(.,'Cases')]")), 15);
		System.out.println("Found Cases on Menu");
		ActionUtils.JavaScriptclick(driver.findElement(By.xpath("//span[contains(.,'Cases')]")));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(.,'Cases')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 60);
		Commons.waitForLoad(driver);
		// Thread.sleep(5000);
		Commons.waitforElement(driver, By.xpath("//*[@id='casesId']"), 90); // RB
		Commons.Explicitwait();
		Commons.Explicitwait();
		ActionUtils.click(driver.findElement(By.id("casesId")));
		System.out.println("Clicked on case hyperlink");
		Commons.waitforElement(driver, By.xpath("//*[@id='gridCaseDetails']/table/tbody"), 60);
		Commons.waitForLoad(driver);
		if (driver.findElement(By.xpath("//*[@id='gridCaseDetails']/table/tbody")).isDisplayed()) {
			System.out.println("Scrolling to case tab");
			Commons.scrollElementinotView(driver.findElement(By.xpath("//*[@id='gridCaseDetails']/table/tbody")),
					driver);
			try {
				System.out.println("Waiting for case tab to Open");
				if (!Commons.existsElement(driver, By.xpath(
						"//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']//i[@class='fa fa-check']"))) {
					ActionUtils.click(driver.findElement(By.id("casesId")));
					System.out.println("Clicked on case hyperlink to expand case list");
				}
				Commons.waitforElement(driver,
						By.xpath(
								"//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']//i[@class='fa fa-check']"),
						150);
				System.out.println("Case tab is opened");
			} catch (Exception e) {
				ActionUtils.click(driver.findElement(By.id("casesId")));
			}
		}
		System.out.println("Waiting for Case list to Copmlete load.......");
		Commons.waitforElement(driver, By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]"), 100);
		Commons.scrollElementinotView(driver.findElement(By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]")),
				driver);
	}

	public static void GetRandomPatientFomPatientListOnAdvanceSearchPage(WebDriver driver) {
		ActionUtils.JavaScriptclick(CasePage.PatientOnMenu(driver));
		ActionUtils.JavaScriptclick(CasePage.PatientSearchOnMenu(driver));
		ActionUtils.click(CasePage.SearchButtonOnAdvanceSearchPage(driver));
		Commons.waitForLoad(driver);
		ActionUtils.doubleClick(driver, CasePage.GetRandomPatientFromAdvanceSearchPage(driver));
		Commons.Explicitwait();
	}

	public static String GetFirstCaseName(WebDriver driver) {
		return ActionUtils.getText(CasePage.GetFirstCaseNameFromCaseList(driver));
	}

	public static String GetFirstCasePrimaryInsuranceName(WebDriver driver) {
		return ActionUtils.getText(CasePage.GetFirstCasePrimaryInsuranceName(driver));
	}

	public static String GetFirstCaseSecondaryInsuranceName(WebDriver driver) {
		return ActionUtils.getText(CasePage.GetFirstCaseSecondaryInsuranceName(driver));
	}

	public static String GetFirstCaseTertiaryInsuranceName(WebDriver driver) {
		return ActionUtils.getText(CasePage.GetFirstCaseTertiaryInsuranceName(driver));
	}

	public static String GetFirstCaseStatus(WebDriver driver) {
		int status = CasePage.GetFirstCaseStatusFromCaseList(driver);
		if (status == 1)
			return "Complete"; // 1 means Active and Complete
		else
			return "Incomplete"; // 2 Means Active Incomplete
	}

	public static void AddCaseWithAllFields(WebDriver driver) throws InterruptedException {
		ActionUtils.sendKeys(CasePage.CaseName(driver), "Case With All Fields");
		ActionUtils.click(CasePage.InjuryTypeAsNone(driver));
		HandlingCalendars.datepick(driver, CasePage.OnsetDate(driver), "15/03/2016");// Enter
																						// injury
																						// onset
																						// date
		HandlingCalendars.datepick(driver, CasePage.StartofCareDate(driver), "15/04/2016");// Enter
																							// Start
																							// care
																							// of
																							// date
		HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "15/05/2016");// Enter
																							// assignment
																							// date
		HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "15/06/2016");// enter
																								// Case
																								// effective
																								// date
		ActionUtils.click(CasePage.ReferringPhysicianMagnifier(driver));
		ActionUtils.click(CasePage.ReferringPhysicianSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.ReferringPhysicianList(driver));
		ActionUtils.click(CasePage.SecondReferringPhysicianMagnifier(driver));
		ActionUtils.click(CasePage.SecondReferringPhysicianSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.SecondReferringPhysicianList(driver));
		ActionUtils.click(CasePage.LocationMagnifier(driver));
		ActionUtils.click(CasePage.LocationSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
		ActionUtils.click(CasePage.BillingProviderMagnifier(driver));
		ActionUtils.click(CasePage.BillingProviderSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.BillingProviderList(driver));
		ActionUtils.click(CasePage.Discipline(driver));
		ActionUtils.sendKeys(CasePage.AdditionalReportText(driver), "Hello I am from QA Team");// Enter
																								// additional
																								// report
																								// comment
		ActionUtils.click(CasePage.AlternateReferralSourceMagnifier(driver));
		ActionUtils.click(CasePage.AlternateReferralSourceSearchbutton(driver));
		ActionUtils.doubleClick(driver, CasePage.AlternateReferralSourceList(driver));
	}

	public static void AddCaseWithAllRequiredFields(WebDriver driver) throws InterruptedException {
		Commons.waitForLoad(driver);
		ActionUtils.click(CasePage.CaseName(driver));
		ActionUtils.sendKeys(CasePage.CaseName(driver), "Case With Required Fields");// Enter
																						// Case
																						// Name
		ActionUtils.click(CasePage.InjuryTypeAsNone(driver));
		HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "14/05/2015");// Enter
																							// Assignment
																							// date
		HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015");// Enter
																								// Case
																								// effective
																								// date
		ActionUtils.click(CasePage.ReferringPhysicianMagnifier(driver));
		ActionUtils.click(CasePage.ReferringPhysicianSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.ReferringPhysicianList(driver));
		ActionUtils.click(CasePage.LocationMagnifier(driver));
		ActionUtils.click(CasePage.LocationSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
		ActionUtils.click(CasePage.BillingProviderMagnifier(driver));
		ActionUtils.click(CasePage.BillingProviderSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.BillingProviderList(driver));
		ActionUtils.click(CasePage.Discipline(driver));
	}
	
	
	public static void AddCaseWithAllRequiredFields(WebDriver driver,String CaseName,String AssignmentDateDDMMYYYY,String 
		CaseeffectivedateDDMMYYYY,String Location,String BillingProvider) throws InterruptedException {
		Commons.waitForLoad(driver);
		ActionUtils.click(CasePage.CaseName(driver));
		ActionUtils.sendKeys(CasePage.CaseName(driver),CaseName);
		ActionUtils.click(CasePage.InjuryTypeAsNone(driver));
		HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver),AssignmentDateDDMMYYYY);
		HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver),CaseeffectivedateDDMMYYYY);
		ActionUtils.click(CasePage.ReferringPhysicianMagnifier(driver));
		ActionUtils.click(CasePage.ReferringPhysicianSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.ReferringPhysicianList(driver));
		ActionUtils.click(CasePage.LocationMagnifier(driver));
		ActionUtils.click(CasePage.LocationInputbox(driver));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='Location_Name']")),Location);
		ActionUtils.click(CasePage.LocationSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.LocationList(driver,Location));
		Assert.assertTrue(driver.findElement(By.xpath("//select[contains(@ng-model,'discipline')]//option[@selected='selected'][@label='--Select--']"))
		.getText().contains("--Select--"));
		ActionUtils.click(CasePage.BillingProviderMagnifier(driver));
		ActionUtils.click(CasePage.BillingProviderInputBox(driver));
		ActionUtils.sendKeys(CasePage.BillingProviderInputBox(driver),BillingProvider);
		ActionUtils.click(CasePage.BillingProviderSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.BillingProviderList(driver,BillingProvider));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void AddDXCode(WebDriver driver) {
		Commons.scrollElementinotView(
				driver.findElement(By.xpath("//*[@id='btnICDCode_']")), driver);
		Commons.waitForLoad(driver);
		ActionUtils.click(CasePage.AddICD10CodeButton(driver));
		ActionUtils.click(CasePage.DiagnosisCodeSearchButton(driver));
		Commons.waitForLoad(driver);
		Commons.ExistandDisplayElement(driver, By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td[2]"),
				390);
		if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
			System.out.println("$$In safari block$$");
			Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td/*"),
					390);
			driver.findElements(By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td/*"))
					.get(new Random().nextInt(5)).click();
			driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);
			driver.switchTo().activeElement().sendKeys(Keys.ENTER);
		} else {
			ActionUtils.doubleClick(driver, CasePage.DxCodeList(driver));
		}
		Commons.waitForLoad(driver);
		Commons.scrollElementinotView(CasePage.AddICD10CodeButton(driver), driver);
		Commons.Explicitwait();
	}

	public static void AddDXCodebycode(WebDriver driver, String dxcode) {
		Commons.scrollElementinotView(driver.findElement(By.xpath("//button[contains(@data-target,'ICDCode')]")),
				driver);
		Commons.waitForLoad(driver);
		ActionUtils.click(CasePage.AddICD10CodeButton(driver));
		Commons.waitforElement(driver, By.xpath("//*[@id='dx10']"), 30);
		ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='dx10']")), dxcode);
		ActionUtils.click(CasePage.DiagnosisCodeSearchButton(driver));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td[2]"), 40);
		if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
			System.out.println("$$In safari block$$");
			Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td[2]"),
					40);
			driver.findElement(By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td[2]")).click();
			driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);
			driver.switchTo().activeElement().sendKeys(Keys.ENTER);
		} else {
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td[2]")));
		}
		Commons.waitForLoad(driver);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//div[@id='gridSearch']"), 10);
		Commons.Explicitwait();
	}

	/* PRIMARY INSURANCE UTILS */
	public static void AddPrimaryInsuranceAsBlueShield(WebDriver driver) {
		try {
			ActionUtils.click(CasePage.PrimaryInsuranceTab(driver));
			ActionUtils.click(CasePage.PrimaryInsuranceMagnifier(driver));
			ActionUtils.sendKeys(CasePage.PrimaryInsuranceName(driver), "AutoPri Blue Shield");// Enter
																								// Primary
																								// Insurance
																								// As
																								// Blue
																								// Shield
			ActionUtils.click(CasePage.PrimaryInsuranceSearchButton(driver));
			ActionUtils.doubleClick(driver, CasePage.PrimaryInsuranceList(driver));
			Commons.waitForLoad(driver);
			// driver.switchTo().activeElement().sendKeys(Keys.PAGE_DOWN);
			Commons.scrollElementinotView(CasePage.PrimarySameAsPatient(driver), driver);// To
																							// Scroll
																							// down
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			ActionUtils.sendKeys(CasePage.primaryInsuranceGroupNumberORClaimNumber(driver), "GrpNum123");// Enter
																											// Primary
																											// Insurance
																											// groupNumber
																											// or
																											// Claim
																											// Number
			ActionUtils.sendKeys(CasePage.primaryInsurancePolicyNameORSSN(driver), "policyNum123");// Enter
																									// Primary
																									// Insurance
																									// policy
																									// number
																									// or
																									// SSN
			ActionUtils.click(CasePage.PrimarySameAsPatient(driver));
			ActionUtils.click(CasePage.RelationshipAsOther(driver));
		} catch (Exception e) {
			Assert.assertFalse(true, "Could not Add Primary Insurance As Blue Shield ");
		}
	}

	public static void AddPrimaryInsuranceByCode(WebDriver driver, String code) throws InterruptedException {
		ActionUtils.click(CasePage.PrimaryInsuranceTab(driver));
		ActionUtils.click(CasePage.PrimaryInsuranceMagnifier(driver));
		Thread.sleep(5000);
		ActionUtils.click(CasePage.PrimaryInsurancecode(driver));
		ActionUtils.sendKeys(CasePage.PrimaryInsurancecode(driver), code);// Enter
																			// Primary
																			// Insurance
																			// As
																			// Blue
																			// Shield
		ActionUtils.click(CasePage.PrimaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.PrimaryInsuranceList(driver));
		Commons.waitForLoad(driver);
		Commons.scrollElementinotView(CasePage.PrimarySameAsPatient(driver), driver);// To
																						// Scroll
																						// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.primaryInsuranceGroupNumberORClaimNumber(driver), "GrpNum123");// Enter
																										// Primary
																										// Insurance
																										// groupNumber
																										// or
																										// Claim
																										// Number
		ActionUtils.sendKeys(CasePage.primaryInsurancePolicyNameORSSN(driver), "123456789A");// Enter
																								// Primary
																								// Insurance
																								// policy
																								// number
																								// or
																								// SSN
		ActionUtils.click(CasePage.PrimarySameAsPatient(driver));
		ActionUtils.sendKeys(CasePage.PrimarySubscriberOrEmployerName(driver), "Testing Team");
		// ActionUtils.click(CasePage.RelationshipAsOther(driver));
	}

	public static void AddPrimaryInsuranceAsWorkerCompensation(WebDriver driver) {
		ActionUtils.click(CasePage.PrimaryInsuranceTab(driver));
		ActionUtils.click(CasePage.PrimaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.PrimaryInsuranceName(driver), "AutoPri WorkersCompensation");// Enter
																									// Primary
																									// Insurance
																									// As
																									// WorkerCompensation
		ActionUtils.click(CasePage.PrimaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.PrimaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.PrimarySameAsPatient(driver), driver);// To
																						// Scroll
																						// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.primaryInsuranceGroupNumberORClaimNumber(driver), "ClaimNum123");// Enter
																										// Primary
																										// Insurance
																										// groupNumber
																										// or
																										// Claim
																										// Number
		ActionUtils.sendKeys(CasePage.primaryInsurancePolicyNameORSSN(driver), "SSN123");// Enter
																							// Primary
																							// Insurance
																							// policy
																							// number
																							// or
																							// SSN
		ActionUtils.click(CasePage.PrimarySameAsPatient(driver));
		ActionUtils.sendKeys(CasePage.PrimarySubscriberOrEmployerName(driver), "Testing Team");
	}

	public static void AddPrimaryInsuranceAsMedicare(WebDriver driver) throws InterruptedException {
		ActionUtils.click(CasePage.PrimaryInsuranceTab(driver));
		ActionUtils.click(CasePage.PrimaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.PrimaryInsuranceName(driver), "AutoPri Medicare");// Enter
																						// Primary
																						// Insurance
																						// As
																						// Medicare
		ActionUtils.click(CasePage.PrimaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.PrimaryInsuranceList(driver));
		Thread.sleep(5000);
		Commons.scrollElementinotView(CasePage.PrimarySameAsPatient(driver), driver);// To
																						// Scroll
																						// down
		Commons.Explicitwait();
		Thread.sleep(5000);
		ActionUtils.sendKeys(CasePage.primaryInsuranceGroupNumberORClaimNumber(driver), "GrpNum123");// Enter
																										// Primary
																										// Insurance
																										// groupNumber
																										// or
																										// Claim
																										// Number
		ActionUtils.sendKeys(CasePage.primaryInsurancePolicyNameORSSN(driver), "123456789a");// Enter
																								// Primary
																								// Insurance
																								// policy
																								// number
																								// or
																								// SSN
		ActionUtils.click(CasePage.PrimarySameAsPatient(driver));
	}

	public static void AddPrimaryInsuranceAsTriCare(WebDriver driver) {
		ActionUtils.click(CasePage.PrimaryInsuranceTab(driver));
		ActionUtils.click(CasePage.PrimaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.PrimaryInsuranceName(driver), "AutoPri Tricare(Champus)");// Enter
																								// Primary
																								// Insurance
																								// As
																								// Tricare
		ActionUtils.click(CasePage.PrimaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.PrimaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.PrimarySameAsPatient(driver), driver);// To
																						// Scroll
																						// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.primaryInsuranceGroupNumberORClaimNumber(driver), "GrpNum123");// Enter
																										// Primary
																										// Insurance
																										// groupNumber
																										// or
																										// Claim
																										// Number
		ActionUtils.sendKeys(CasePage.primaryInsurancePolicyNameORSSN(driver), "123456789");// Enter
																							// Primary
																							// Insurance
																							// policy
																							// number
																							// or
																							// SSN
		ActionUtils.click(CasePage.PrimarySameAsPatient(driver));
		ActionUtils.click(CasePage.RelationshipAsOther(driver));
	}

	public static void AddPrimaryInsuranceAsRailRoad(WebDriver driver) throws InterruptedException {
		ActionUtils.click(CasePage.PrimaryInsuranceTab(driver));
		ActionUtils.click(CasePage.PrimaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.PrimaryInsuranceName(driver), "AutoPri Medicare RailRoad");// Enter
																									// Primary
																									// Insurance
																									// As
																									// RailRoad
		ActionUtils.click(CasePage.PrimaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.PrimaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.PrimarySameAsPatient(driver), driver);// To
																						// Scroll
																						// down
		Commons.Explicitwait();
		Thread.sleep(3000);
		ActionUtils.sendKeys(CasePage.primaryInsuranceGroupNumberORClaimNumber(driver), "GrpNum123");// Enter
																										// Primary
																										// Insurance
																										// groupNumber
																										// or
																										// Claim
																										// Number
		ActionUtils.sendKeys(CasePage.primaryInsurancePolicyNameORSSN(driver), "a123456789");// Enter
																								// Primary
																								// Insurance
																								// policy
																								// number
																								// or
																								// SSN
		ActionUtils.click(CasePage.PrimarySameAsPatient(driver));
	}

	public static void PrimaryInsuranceAddPreauthorization(WebDriver driver) throws InterruptedException {
		// ActionUtils.click(CasePage.PrimaryAddPreauthorizationButton(driver));
		ActionUtils.sendKeys(CasePage.PrimaryPreAuthorizationNumber(driver), "12345");// Enter
																						// Primary
																						// Insurance
																						// PreAuth
																						// number
		HandlingCalendars.datepick(driver, CasePage.PrimaryPreAuthorizationFromDate(driver), "01/01/2018");// Enter
																											// Primary
																											// Insurance
																											// PreAuth
																											// From
																											// Date
		HandlingCalendars.datepick(driver, CasePage.PrimaryPreAuthorizationThroughDate(driver), "12/12/2018");// Enter
																												// Primary
																												// Insurance
																												// PreAuth
																												// Through
																												// Date
		ActionUtils.sendKeys(CasePage.PrimaryPreAuthorizationVisits(driver), "5");// Enter
																					// Primary
																					// Insurance
																					// PreAuth
																					// Visits
	}

	public static void PrimaryInsuranceAddEligibity(WebDriver driver) throws InterruptedException {
		//ActionUtils.click(CasePage.PrimaryAddEligibilityButton(driver));
		ActionUtils.sendKeys(CasePage.PrimaryEligibilityContactName(driver), "QA Team");
		Commons.scrollElementinotView(CasePage.PrimaryEligibilityEffectiveDate(driver), driver);
		HandlingCalendars.datepick(driver, CasePage.PrimaryEligibilityEffectiveDate(driver), "01/01/2018");
		HandlingCalendars.datepick(driver, CasePage.PrimaryEligibilityTerminationDate(driver), "12/12/2018");
		ActionUtils.clear(CasePage.PrimaryEligibilityInNetworkCopay(driver));
		ActionUtils.sendKeys(CasePage.PrimaryEligibilityInNetworkCopay(driver), "100");
		ActionUtils.clear(CasePage.PrimaryEligibilityOutOfNetworkCopay(driver));
		ActionUtils.sendKeys(CasePage.PrimaryEligibilityOutOfNetworkCopay(driver), "200");
		ActionUtils.clear(CasePage.PrimaryEligibilityInNetworkDeductible(driver));
		ActionUtils.sendKeys(CasePage.PrimaryEligibilityInNetworkDeductible(driver), "300"); 
		ActionUtils.clear(CasePage.PrimaryEligibilityOutofNetworkDeductible(driver));
		ActionUtils.sendKeys(CasePage.PrimaryEligibilityOutofNetworkDeductible(driver), "350"); 
	}
	
	

	public static void PrimaryInsuranceAddDocotorsOrder(WebDriver driver) throws InterruptedException {
		// ActionUtils.click(CasePage.PrimaryDocotorsOrderAddButton(driver));
		HandlingCalendars.datepick(driver, CasePage.PrimaryDocotorsOrderFromDate(driver), "14/05/2016");// Enter
																										// Primary
																										// Insurance
																										// docotor's
																										// order
																										// from
																										// date
		HandlingCalendars.datepick(driver, CasePage.PrimaryDocotorsOrderThroughDate(driver), "14/06/2016");// Enter
																											// Primary
																											// Insurance
																											// docotor's
																											// order
																											// through
																											// date
		ActionUtils.sendKeys(CasePage.PrimaryDocotorsOrderPrescribedVisits(driver), "3");// Enter
																							// Primary
																							// Insurance
																							// docotor's
																							// order
																							// prescribed
																							// visits
		ActionUtils.sendKeys(CasePage.PrimaryDocotorsOrderComment(driver), "Hello i am From QA");// Enter
																									// Primary
																									// Insurance
																									// docotor's
																									// order
																									// Comments
	}

	/* SECONDARY INSURANCE UTILS */
	public static void AddSecondaryInsuranceAsBlueShield(WebDriver driver) {
		ActionUtils.click(CasePage.SecondaryInsuranceTab(driver));
		ActionUtils.click(CasePage.SecondaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceName(driver), "AutoSec Blue Shield");// Enter
																								// secondary
																								// insurance
																								// As
																								// Blue
																								// Shield
		ActionUtils.click(CasePage.SecondaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.SecondaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.SecondaryInsuranceSameAsPatientCheckbox(driver), driver);// To
																										// Scroll
																										// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceGroupNumberOrClaimNumber(driver), "GrpNum123");// Enter
																										// secondary
																										// insurance
																										// Group
																										// number
																										// or
																										// claim
																										// number
		ActionUtils.sendKeys(CasePage.SecondaryInsurancePolicyNumberOrSSN(driver), "PolicyNum123");// Enter
																									// secondary
																									// insurance
																									// Policy
																									// number
																									// or
																									// SSN
		ActionUtils.click(CasePage.SecondaryInsuranceSameAsPatientCheckbox(driver));
		ActionUtils.click(CasePage.SecondaryInsuranceRelationshipAsOther(driver));
	}

	public static void AddSecondaryInsuranceAsMedicare(WebDriver driver) {
		ActionUtils.click(CasePage.SecondaryInsuranceTab(driver));
		ActionUtils.click(CasePage.SecondaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceName(driver), "AutoSec Medicare");// Enter
																							// secondary
																							// insurance
																							// As
																							// Medicare
		ActionUtils.click(CasePage.SecondaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.SecondaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.SecondaryInsuranceSameAsPatientCheckbox(driver), driver);// To
																										// Scroll
																										// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceGroupNumberOrClaimNumber(driver), "GrpNum123");// Enter
																										// secondary
																										// insurance
																										// Group
																										// number
																										// or
																										// claim
																										// number
		ActionUtils.sendKeys(CasePage.SecondaryInsurancePolicyNumberOrSSN(driver), "123456789a");// Enter
																									// secondary
																									// insurance
																									// Policy
																									// number
																									// or
																									// SSN
		ActionUtils.click(CasePage.SecondaryInsuranceSameAsPatientCheckbox(driver));
	}

	public static void AddSecondaryInsuranceAsTricare(WebDriver driver) {
		ActionUtils.click(CasePage.SecondaryInsuranceTab(driver));
		ActionUtils.click(CasePage.SecondaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceName(driver), "AutoSec Tricare(Champus)");// Enter
																									// secondary
																									// insurance
																									// As
																									// Tricare
		ActionUtils.click(CasePage.SecondaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.SecondaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.SecondaryInsuranceSameAsPatientCheckbox(driver), driver);// To
																										// Scroll
																										// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceGroupNumberOrClaimNumber(driver), "GrpNum123");// Enter
																										// secondary
																										// insurance
																										// Group
																										// number
																										// or
																										// claim
																										// number
		ActionUtils.sendKeys(CasePage.SecondaryInsurancePolicyNumberOrSSN(driver), "123456789");// Enter
																								// secondary
																								// insurance
																								// Policy
																								// number
																								// or
																								// SSN
		ActionUtils.click(CasePage.SecondaryInsuranceSameAsPatientCheckbox(driver));
	}

	public static void AddSecondaryInsuranceAsRailRoad(WebDriver driver) {
		ActionUtils.click(CasePage.SecondaryInsuranceTab(driver));
		ActionUtils.click(CasePage.SecondaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceName(driver), "AutoSec Medicare RailRoad");// Enter
																									// secondary
																									// insurance
																									// As
																									// RailRoad
		ActionUtils.click(CasePage.SecondaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.SecondaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.SecondaryInsuranceSameAsPatientCheckbox(driver), driver);// To
																										// Scroll
																										// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceGroupNumberOrClaimNumber(driver), "GrpNum123");// Enter
																										// secondary
																										// insurance
																										// Group
																										// number
																										// or
																										// claim
																										// number
		ActionUtils.sendKeys(CasePage.SecondaryInsurancePolicyNumberOrSSN(driver), "a123456789");// Enter
																									// secondary
																									// insurance
																									// Policy
																									// number
																									// or
																									// SSN
		ActionUtils.click(CasePage.SecondaryInsuranceSameAsPatientCheckbox(driver));
	}

	public static void SecondaryInsuranceAddPreauthorization(WebDriver driver) throws InterruptedException {
		// ActionUtils.click(CasePage.SecondaryInsurancePreauthorizationAddButton(driver));
		ActionUtils.sendKeys(CasePage.SecondaryInsurancePreauthorizationNumber(driver), "12345");// Enter
																									// Secondary
																									// Insurance
																									// PreAuth
																									// number
		HandlingCalendars.datepick(driver, CasePage.SecondaryInsurancePreauthorizationFromDate(driver), "14/06/2015");// Enter
																														// Secondary
																														// Insurance
																														// PreAuth
																														// From
																														// Date
		HandlingCalendars.datepick(driver, CasePage.SecondaryInsurancePreauthorizationThroughDate(driver),
				"14/07/2015");// Enter Secondary Insurance PreAuth Through Date
		ActionUtils.sendKeys(CasePage.SecondaryInsurancePreauthorizationVisits(driver), "5");// Enter
																								// Secondary
																								// Insurance
																								// PreAuth
																								// Visits
	}

	public static void SecondaryInsuranceAddEligibility(WebDriver driver) throws InterruptedException {
		ActionUtils.click(CasePage.SecondaryInsuranceEligibilityAddButton(driver));
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceEligibilityContactName(driver), "QA Team");// Enter
																									// Contact
																									// name
																									// in
																									// Secondary
																									// Insurance
																									// eligibility
																									// section
		HandlingCalendars.datepick(driver, CasePage.SecondaryInsuranceEligibilityEffectiveDate(driver), "14/06/2015");// Enter
																														// Secondary
																														// Insurance
																														// eligibility
																														// Effective
																														// Date
		HandlingCalendars.datepick(driver, CasePage.SecondaryInsuranceEligibilityTerminationDate(driver), "14/07/2015");// Enter
																														// Secondary
																														// Insurance
																														// eligibility
																														// Termination
																														// Date
		ActionUtils.clear(CasePage.SecondaryInsuranceEligibilityInNetworkCopay(driver));
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceEligibilityInNetworkCopay(driver), "100");// Enter
																									// in
																									// network
																									// copay
																									// in
																									// Secondary
																									// Insurance
																									// eligibility
																									// section
		ActionUtils.clear(CasePage.SecondaryInsuranceEligibilityOutNetworkCopay(driver));
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceEligibilityOutNetworkCopay(driver), "200");// Enter
																									// Out
																									// of
																									// network
																									// copay
																									// in
																									// Secondary
																									// Insurance
																									// eligibility
																									// section
		ActionUtils.clear(CasePage.SecondaryInsuranceEligibilityInNetworkDeductible(driver));
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceEligibilityInNetworkDeductible(driver), "360"); // Enter
																										// in
																										// network
																										// deduct
																										// in
																										// Secondary
																										// Insurance
																										// eligibility
																										// section
		ActionUtils.clear(CasePage.SecondaryInsuranceEligibilityOutNetworkDeductible(driver));
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceEligibilityOutNetworkDeductible(driver), "400"); // Enter
																											// out
																											// network
																											// deduct
																											// in
																											// Secondary
																											// Insurance
																											// eligibility
																											// section
	}

	/* TERTIARY INSURANCE UTILS */
	public static void AddTertiaryInsuranceAsBlueShield(WebDriver driver) {
		ActionUtils.click(CasePage.TertiaryInsuranceTab(driver));
		ActionUtils.click(CasePage.TertiaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceName(driver), "AutoTert Blue Shield");// Enter
																								// Tertiary
																								// Insurance
																								// as
																								// Blue
																								// Shield
		ActionUtils.click(CasePage.TertiaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.TertiaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.TertiaryInsuranceSameAsPatientCheckbox(driver), driver);// To
																										// Scroll
																										// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceGroupNumberOrClaimNumber(driver), "GrpNum123");// Enter
																										// Tertiary
																										// Insurance
																										// Group
																										// number
																										// or
																										// claim
																										// number
		ActionUtils.sendKeys(CasePage.TertiaryInsurancePolicyNumberOrSSN(driver), "PolicyNum123");// Enter
																									// Tertiary
																									// Insurance
																									// Policy
																									// number
																									// or
																									// SSN
		ActionUtils.click(CasePage.TertiaryInsuranceSameAsPatientCheckbox(driver));
		ActionUtils.click(CasePage.TertiaryInsuranceRelationshipAsOther(driver));
	}

	public static void AddTertiaryInsuranceAsMedicare(WebDriver driver) {
		ActionUtils.click(CasePage.TertiaryInsuranceTab(driver));
		ActionUtils.click(CasePage.TertiaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceName(driver), "AutoTert Medicare");// Enter
																							// Tertiary
																							// Insurance
																							// as
																							// Medicare
		ActionUtils.click(CasePage.TertiaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.TertiaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.TertiaryInsuranceSameAsPatientCheckbox(driver), driver);// To
																										// Scroll
																										// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceGroupNumberOrClaimNumber(driver), "GrpNum123");// Enter
																										// Tertiary
																										// Insurance
																										// Group
																										// number
																										// or
																										// claim
																										// number
		ActionUtils.sendKeys(CasePage.TertiaryInsurancePolicyNumberOrSSN(driver), "123456789a");// Enter
																								// Tertiary
																								// Insurance
																								// Policy
																								// number
																								// or
																								// SSN
		ActionUtils.click(CasePage.TertiaryInsuranceSameAsPatientCheckbox(driver));
	}

	public static void AddTertiaryInsuranceAsTricare(WebDriver driver) {
		ActionUtils.click(CasePage.TertiaryInsuranceTab(driver));
		ActionUtils.click(CasePage.TertiaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceName(driver), "AutoTert Tricare(Champus)");// Enter
																									// Tertiary
																									// Insurance
																									// as
																									// Tricare
		ActionUtils.click(CasePage.TertiaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.TertiaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.TertiaryInsuranceSameAsPatientCheckbox(driver), driver);// To
																										// Scroll
																										// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceGroupNumberOrClaimNumber(driver), "GrpNum123");// Enter
																										// Tertiary
																										// Insurance
																										// Group
																										// number
																										// or
																										// claim
																										// number
		ActionUtils.sendKeys(CasePage.TertiaryInsurancePolicyNumberOrSSN(driver), "123456789");// Enter
																								// Tertiary
																								// Insurance
																								// Policy
																								// number
																								// or
																								// SSN
		ActionUtils.click(CasePage.TertiaryInsuranceSameAsPatientCheckbox(driver));
	}

	public static void AddTertiaryInsuranceAsRailRoad(WebDriver driver) {
		ActionUtils.click(CasePage.TertiaryInsuranceTab(driver));
		ActionUtils.click(CasePage.TertiaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceName(driver), "AutoTert Medicare RailRoad");// Enter
																									// Tertiary
																									// Insurance
																									// as
																									// Tricare
		ActionUtils.click(CasePage.TertiaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.TertiaryInsuranceList(driver));
		Commons.scrollElementinotView(CasePage.TertiaryInsuranceSameAsPatientCheckbox(driver), driver);// To
																										// Scroll
																										// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceGroupNumberOrClaimNumber(driver), "GrpNum123");// Enter
																										// Tertiary
																										// Insurance
																										// Group
																										// number
																										// or
																										// claim
																										// number
		ActionUtils.sendKeys(CasePage.TertiaryInsurancePolicyNumberOrSSN(driver), "a123456789");// Enter
																								// Tertiary
																								// Insurance
																								// Policy
																								// number
																								// or
																								// SSN
		ActionUtils.click(CasePage.TertiaryInsuranceSameAsPatientCheckbox(driver));
	}

	public static void TertiaryInsuranceAddPreauthorization(WebDriver driver) throws InterruptedException {
		// ActionUtils.click(CasePage.TertiaryInsurancePreauthorizationAddButton(driver));
		ActionUtils.sendKeys(CasePage.TertiaryInsurancePreauthorizationNumber(driver), "12345");// Enter
																								// Tertiary
																								// Insurance
																								// PreAuth
																								// number
		HandlingCalendars.datepick(driver, CasePage.TertiaryInsurancePreauthorizationFromDate(driver), "14/06/2015");// Enter
																														// Tertiary
																														// Insurance
																														// PreAuth
																														// From
																														// Date
		HandlingCalendars.datepick(driver, CasePage.TertiaryInsurancePreauthorizationThroughDate(driver), "14/07/2015");// Enter
																														// Tertiary
																														// Insurance
																														// PreAuth
																														// Through
																														// Date
		ActionUtils.sendKeys(CasePage.TertiaryInsurancePreauthorizationVisits(driver), "5");// Enter
																							// Tertiary
																							// Insurance
																							// PreAuth
																							// Visits
	}

	public static void TertiaryInsuranceAddEligibility(WebDriver driver) throws InterruptedException {
		ActionUtils.click(CasePage.TertiaryInsuranceEligibiltyAddButton(driver));
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceEligibiltyContactName(driver), "QA Team");// Enter
																									// Contact
																									// name
																									// in
																									// Tertiary
																									// Insurance
																									// eligibility
																									// section
		HandlingCalendars.datepick(driver, CasePage.TertiaryInsuranceEligibiltyEffectiveDate(driver), "14/06/2015");// Enter
																													// Tertiary
																													// Insurance
																													// eligibility
																													// Effective
																													// Date
		HandlingCalendars.datepick(driver, CasePage.TertiaryInsuranceEligibiltyTerminationDate(driver), "14/07/2015");// Enter
																														// Tertiary
																														// Insurance
																														// eligibility
																														// Termination
																														// Date
		ActionUtils.clear(CasePage.TertiaryInsuranceEligibiltyInNetworkCopay(driver));
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceEligibiltyInNetworkCopay(driver), "100");// Enter
																								// In
																								// network
																								// copay
																								// in
																								// Tertiary
																								// Insurance
																								// eligibility
																								// section
		ActionUtils.clear(CasePage.TertiaryInsuranceEligibiltyOutNetworkCopay(driver));
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceEligibiltyOutNetworkCopay(driver), "200");// Enter
																									// Out
																									// of
																									// network
																									// copay
																									// in
																									// Tertiary
																									// Insurance
																									// eligibility
																									// section
		ActionUtils.clear(CasePage.TertiaryInsuranceEligibiltyInNetworkDeductible(driver));
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceEligibiltyInNetworkDeductible(driver), "250"); // Enter
																										// in
																										// network
																										// deduct
																										// in
																										// Tertiary
																										// Insurance
																										// eligibility
																										// section
		ActionUtils.clear(CasePage.TertiaryInsuranceEligibiltyOutNetworkDeductible(driver));
		ActionUtils.sendKeys(CasePage.TertiaryInsuranceEligibiltyOutNetworkDeductible(driver), "350"); // Enter
																										// in
																										// network
																										// deduct
																										// in
																										// Tertiary
																										// Insurance
																										// eligibility
																										// section
	}

	/* CLICK ON CASE SAVE BUTTON */
	public static String ClickCaseSave(WebDriver driver) {
		if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
			System.out.println("$$In safari block$$");
			try {
				Commons.Explicitwait();
				Commons.waitforElement(driver, By.xpath("//button[@id='saveCaseDetailsButton']"), 40);
				ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveCaseDetailsButton']")));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//button[@id='saveCaseDetailsButton']")));
			} catch (Exception e) {
				System.out.println("Clicked on case save");
			}
		} else {
			ActionUtils.click(CasePage.CaseSaveButton(driver));
		}
		System.out.println("Saving Case..............");
		String strMessage = Commons.capturemessage(driver, 900);
		return strMessage;
	}

	public static void ClickCaseEdit(WebDriver driver) {
		ActionUtils.click(CasePage.CaseEditButton(driver));
		Commons.Explicitwait();
		Commons.Explicitwait();
	}

	// ***********************************************************************
	public static void AddPrimaryInsurancebycode(WebDriver driver) {
		ActionUtils.click(CasePage.PrimaryInsuranceTab(driver));
		ActionUtils.click(CasePage.PrimaryInsuranceMagnifier(driver));
		ActionUtils.sendKeys(CasePage.PrimaryInsuranceName(driver), "AutoPri Blue Shield");// Enter
																							// Primary
																							// Insurance
																							// As
																							// Blue
																							// Shield
		ActionUtils.click(CasePage.PrimaryInsuranceSearchButton(driver));
		ActionUtils.doubleClick(driver, CasePage.PrimaryInsuranceList(driver));
		Commons.Explicitwait();
		driver.switchTo().activeElement().sendKeys(Keys.PAGE_DOWN);
		Commons.scrollElementinotView(CasePage.PrimarySameAsPatient(driver), driver);// To
																						// Scroll
																						// down
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.primaryInsuranceGroupNumberORClaimNumber(driver), "GrpNum123");// Enter
																										// Primary
																										// Insurance
																										// groupNumber
																										// or
																										// Claim
																										// Number
		ActionUtils.sendKeys(CasePage.primaryInsurancePolicyNameORSSN(driver), "policyNum123");// Enter
																								// Primary
																								// Insurance
																								// policy
																								// number
																								// or
																								// SSN
		ActionUtils.click(CasePage.PrimarySameAsPatient(driver));
		ActionUtils.click(CasePage.RelationshipAsOther(driver));
	}

	public static void OpenShellCase(WebDriver driver) {
		if (Commons.existsElement(driver, By.xpath("//tbody[@role='rowgroup']//tr[1]//td[3]"))) {
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//tbody[@role='rowgroup']//tr[1]//td[3]")));
			Commons.waitForLoad(driver);
			System.out.println("Opened Shell Case");
		}
		Commons.waitForElementToBeClickable(driver, CasePage.CaseEditButton(driver), 80);
		Commons.waitForLoad(driver);
		ActionUtils.click(CasePage.CaseEditButton(driver));
		Commons.waitForElementToBeClickable(driver, CasePage.CaseName(driver), 30);
		Commons.waitForLoad(driver);
		ActionUtils.click(CasePage.CaseName(driver));
	}
}
