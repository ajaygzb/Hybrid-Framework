package com.bms.M3.Cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import UIMap.CreateNotePage;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.HandlingCalendars;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestCaseEdit extends TestSetUp {
	
	String ID;
	
	@Test(priority = 1, enabled = true)
	public void CaseSaveWithNoInfo() {
		
		try {
			test = extent.startTest("Add case with No fields", "Test Case Information" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			AddPatientUtils.QuickAddpatient(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.GoToCaseList(driver);
			
			
			
			// Verify Data On Case List(under Collapse/De-collapse section)
			Assert.assertEquals(driver.findElements(By.xpath("//*[@id='gridCaseDetails']//tbody//tr")).size(), 2);
			System.out.println("Click on + button in case list");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='gridCaseDetails']//tbody//tr[1]//td[1]//a")));
			List<WebElement>caseitems=driver.findElements(By.xpath("//*[@id='gridCaseDetails']/table/tbody/tr[2]/td[2]/div/case-details-row//tbody/tr"));
			List<String>actual=new ArrayList();
			caseitems.forEach(i -> System.out.println(i.getText()));
			System.out.println(caseitems.size());
			for(WebElement e:caseitems){
				
				if(!e.getText().isEmpty()){
					
					actual.add(e.getText());
				}
				
				
				
			}
			System.out.println(actual.size()+"  "+actual);
			String arr[]={"Referring Physician:",
				"Primary Insurance Secondary Insurance Tertiary Insurance",
				"Name",
				"Insurance Class",
				"Payer Group",
				"Group #",
				"Policy #",
				"Address",
				"Phone",
				"Subscriber",
				"Relationship",
				"Address",
				"DoB",
				"SSN",
				"Gender",
					
			};
			
			Assert.assertTrue(Arrays.asList(arr).equals(actual));
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			try {
				s_assert.assertEquals(expectedMessage, "Case saved successfully.",
						"***************Could not capture Toast message*************");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(CasePage.CaseEditButton(driver).isDisplayed(), "Could not save case..");
			}
			Assert.assertEquals(CasePage.GetFirstCaseNameFromCaseList(driver).getText(), "");
			test.log(LogStatus.INFO, "Blank Case Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: CaseSaveWithNoInfo() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: CaseAsCompleted() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 2, enabled = true)
	public void CaseEditWithAllFields() {
		try {
			test = extent.startTest("Edit Case with All fields", "Test Case Information" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			AddPatientUtils.QuickAddpatient(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			test = extent.startTest("Verify Required Field labes on Add case page", "Verify Required Field labes on Add case page" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");			
			String arr[]={"Case Name","Injury Type (required for billing)","Onset Date","Start of Care Date",
			"Discharge Date",
			"Assignment Date (required for billing)",
			"Case Effective Date (required for billing)",
			"Next Doctor Visit",  
			"Referring Physician (required for billing)",
			"Second Referring Physician",
			"Location (required for billing)",
			"Billing Provider (required for billing)",
			"Discipline (required for billing)",
			"Accident Location",
			"Additional Report Text",
			"Use Special Billing",
			"Alternate Referral Source",
			"Referral Date",
			"Referral Status",
			"Special Program",
			"Patient Contact",
			"Patient Contact Comments"};
			test.log(LogStatus.INFO, "validate the following fields are displayed on Add New Case Page");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='mainContent']/ng-form[1]//label"),30);
			Commons.AssertTextArray(driver, By.xpath("//*[@id='mainContent']/ng-form[1]//label"), arr);
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015"); // Enter case effective date
			test.log(LogStatus.INFO, "Verify Use Special Billing,  is checkbox.  ");
			Commons.waitforElement(driver, By.xpath("//*[@id='ProviderandLocation']//label[contains(.,'Use Special Billing')]//input[@type='checkbox']"),10);
			
			AddCaseUtils.ClickCaseSave(driver);
			ActionUtils.click(CasePage.CaseEditButton(driver));
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'At least one ICD-10 code is required.')]"),30);
			ActionUtils.click(CasePage.PrimaryInsuranceTab(driver));
		    
			
			
			test = extent.startTest("Verify Required Field labes on Add Primary Insurance Page", "Verify Required Field labes on Add Primary Insurance Page" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");	
		
			String INSPrimary[]={"Insurance (required for billing)",
					"Group Number (required for billing)",
					"                                   ",
					"Policy Number (required for billing)",
					"                                   ",
					"Same as Patient",
					"First Name (required for billing)",
					"MI",
					"Last Name (required for billing)",
					"Suffix",
					"Address 1 (required for billing)",
					"Address 2",
					"City (required for billing)",
					"Country (required for billing)",
					"State (required for billing)",
					"ZIP Code (required for billing)",
					"Phone",
					"SSN",
					"Date of birth (required for billing)",
					"Relationship (required for billing)",
					"Gender (required for billing)",
					"Name (required if the insurance is Workers’ Compensation)",
					"Address 1",
					"Address 2",
					"City",
					"State",
					"Zip Code",
					"Phone",
					"Subscriber Active",
					"Units",
					"Visits",
					"Contact Name",
					"Amount to Collect",
					"Effective Date",
					"Termination Date",
					"In Network Copay",
					"In Network Deductible",
					"Out of Network Copay",
					"Out of Network Deductible",
					"Separate Copay",
					"Separate Copay Amount",
					"Maximum Outpatient Benefits",
					"Maximum Calendar Benefits",
					"Per Visit Limitation",
					"DME Benefit",
					"YTD Deductible Met",
					"Co-insurance Percentage",
					"Excluded Conditions",
					"Comments",
					"Referral Required",
					"Preauthorization required",
					"Eligibility is Active",
					"In Network",
					"Out of Network"};
			test.log(LogStatus.INFO, "validate the following fields are displayed on Add New Case Page");
			Commons.AssertTextArray(driver, By.xpath("//*[@id='collapseOne']//label"), INSPrimary);
			test.log(LogStatus.INFO, "verify Country fields contains only US and Out Of Country Options");
			System.out.println(driver.findElements(By.xpath("//*[@id='collapseOne']//select[contains(@name,'country')]//option")).size());
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id='collapseOne']//select[contains(@name,'country')]//option")).size()==2);
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id='collapseOne']//select[contains(@name,'country')]//option")).get(0).getText().contains("US"));
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id='collapseOne']//select[contains(@name,'country')]//option")).get(1).getText().contains("Out Of Country"));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#SecondaryInsurance']")));
			
			
			
			
			
			test = extent.startTest("Verify Required Field labes on Add Secondary Insurance Page", "Verify Required Field labes on Add Secondary Insurance Page" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");			
			String INSSecondary[]={"Insurance (required for billing)",
					"Group Number (required for billing)",
					"                                   ",
					"Policy Number (required for billing)",
					"                                   ",
					"Same as Patient",
					"First Name (required for billing)",
					"MI",
					"Last Name (required for billing)",
					"Suffix",
					"Address 1 (required for billing)",
					"Address 2",
					"City (required for billing)",
					"Country (required for billing)",
					"State (required for billing)",
					"ZIP Code (required for billing)",
					"Phone",
					"SSN",
					"Date of birth (required for billing)",
					"Relationship (required for billing)",
					"Gender (required for billing)",
					"Name (required if the insurance is Workers’ Compensation)",
					"Address 1",
					"Address 2",
					"City",
					"State",
					"Zip Code",
					"Phone",
					"Subscriber Active",
					"Units",
					"Visits",
					"Contact Name",
					"Amount to Collect",
					"Effective Date",
					"Termination Date",
					"In Network Copay",
					"In Network Deductible",
					"Out of Network Copay",
					"Out of Network Deductible",
					"Separate Copay",
					"Separate Copay Amount",
					"Maximum Outpatient Benefits",
					"Maximum Calendar Benefits",
					"Per Visit Limitation",
					"DME Benefit",
					"YTD Deductible Met",
					"Co-insurance Percentage",
					"Excluded Conditions",
					"Comments",
					"Referral Required",
					"Preauthorization required",
					"Eligibility is Active",
					"In Network",
					"Out of Network"};
			test.log(LogStatus.INFO, "validate the following fields are displayed on Add New Case Page");
			Commons.AssertTextArray(driver, By.xpath("//*[@id='collapseTwo']//label"), INSSecondary);
			test.log(LogStatus.INFO, "verify Country fields contains only US and Out Of Country Options");
			System.out.println(driver.findElements(By.xpath("//*[@id='collapseTwo']//select[contains(@name,'country')]//option")).size());
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id='collapseTwo']//select[contains(@name,'country')]//option")).size()==2);
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id='collapseTwo']//select[contains(@name,'country')]//option")).get(0).getText().contains("US"));
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id='collapseTwo']//select[contains(@name,'country')]//option")).get(1).getText().contains("Out Of Country"));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#TertiaryInsurance']")));
			test = extent.startTest("Verify Required Field labes on Add Tertiary Insurance Page", "Verify Required Field labes on Add Tertiary Insurance Page" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");			
			String INSTertiary[]={"Insurance (required for billing)",
					"Group Number (required for billing)",
					"                                   ",
					"Policy Number (required for billing)",
					"                                   ",
					"Same as Patient",
					"First Name (required for billing)",
					"MI",
					"Last Name (required for billing)",
					"Suffix",
					"Address 1 (required for billing)",
					"Address 2",
					"City (required for billing)",
					"Country (required for billing)",
					"State (required for billing)",
					"ZIP Code (required for billing)",
					"Phone",
					"SSN",
					"Date of birth (required for billing)",
					"Relationship (required for billing)",
					"Gender (required for billing)",
					"Name (required if the insurance is Workers’ Compensation)",
					"Address 1",
					"Address 2",
					"City",
					"State",
					"Zip Code",
					"Phone",
					"Subscriber Active",
					"Units",
					"Visits",
					"Contact Name",
					"Amount to Collect",
					"Effective Date",
					"Termination Date",
					"In Network Copay",
					"In Network Deductible",
					"Out of Network Copay",
					"Out of Network Deductible",
					"Separate Copay",
					"Separate Copay Amount",
					"Maximum Outpatient Benefits",
					"Maximum Calendar Benefits",
					"Per Visit Limitation",
					"DME Benefit",
					"YTD Deductible Met",
					"Co-insurance Percentage",
					"Excluded Conditions",
					"Comments",
					"Referral Required",
					"Preauthorization required",
					"Eligibility is Active",
					"In Network",
					"Out of Network"};
			test.log(LogStatus.INFO, "validate the following fields are displayed on Add New Case Page");
			Commons.AssertTextArray(driver, By.xpath("//*[@id='collapseThree']//label"), INSTertiary);
			test.log(LogStatus.INFO, "verify Country fields contains only US and Out Of Country Options");
			System.out.println(driver.findElements(By.xpath("//*[@id='collapseThree']//select[contains(@name,'country')]//option")).size());
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id='collapseThree']//select[contains(@name,'country')]//option")).size()==2);
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id='collapseThree']//select[contains(@name,'country')]//option")).get(0).getText().contains("US"));
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id='collapseThree']//select[contains(@name,'country')]//option")).get(1).getText().contains("Out Of Country"));
			Commons.screenshot();
			
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseDetailsLink']//a[contains(.,'Case')]")));
			
			test.log(LogStatus.INFO, "Verify Count of required fields on case page");
			
			List<WebElement> req = driver.findElements(By.xpath("//span[contains(.,'(required for billing)')]"));
			int count = 0;
			for(WebElement e:req){				
				if(e.isDisplayed()){
					count++;					
				}				
			}			
			System.out.println("Expected    "+count);
			Assert.assertEquals(count, 46);
			test = extent.startTest("Verify When billing provider is selected the discipline is auto-populated on new case. ", "Verify When billing provider is selected the discipline is auto-populated on new case. " + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");			
			AddCaseUtils.AddCaseWithAllRequiredFields(driver,"Test caseEdit","01/01/2018","01/01/2018","Automation BMS","Automation BMS Provider");
			AddCaseUtils.ClickCaseSave(driver);
			Assert.assertTrue(driver.findElement(By.xpath("//select[contains(@ng-model,'discipline')]//option[@selected='selected'][@label='PHYSICAL THERAPY ']"))
				    .getText().contains("PHYSICAL THERAPY"));
			ActionUtils.click(CasePage.CaseEditButton(driver));
					ActionUtils.click(CasePage.Discipline(driver));
			Commons.screenshot();
			test.log(LogStatus.INFO, "************PASS****************");
			test = extent.startTest("Verify Changing the discipline on an existing case, save changes.  ", "Verify Changing the discipline on an existing case, save changes.  " + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Commons.screenshot();
			AddCaseUtils.ClickCaseSave(driver);
			Assert.assertTrue(driver.findElement(By.xpath("//select[contains(@ng-model,'discipline')]//option[@selected='selected'][@label='OTHER']"))
					.getText().contains("OTHER"));
			
			test.log(LogStatus.INFO, "************PASS****************");
			ActionUtils.click(CasePage.CaseEditButton(driver));
			AddCaseUtils.AddDXCode(driver);
			ActionUtils.click(CasePage.PrimaryInsuranceTab(driver));
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.PrimaryInsuranceAddPreauthorization(driver);
			AddCaseUtils.PrimaryInsuranceAddEligibity(driver);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseDetailsLink']//a[contains(.,'Case')]")));
			HandlingCalendars.datepick(driver, CasePage.DischargeDate(driver), "01/02/2018"); // Enter discharge date
			String expectedMessage = null;
			expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			if (expectedMessage == null) {
				Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 40);
			} else {
				Assert.assertEquals(expectedMessage, "Case updated successfully.");
			}
			
			test = extent.startTest("Verify Edit elgibilities information to display in the information icon and reports", "Verify Edit elgibilities information to display in the information icon and reports " + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Assert.assertTrue(driver.findElement(By.xpath("//div[text()='In-Network Copay: $ 100.00']")).getText().contains("In-Network Copay: $ 100.00"));
			Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Insurance Name: AutoPri Blue Shield']")).getText().contains("Insurance Name: AutoPri Blue Shield"));
			test.log(LogStatus.INFO, "*********Assertion Pass*******");
			Commons.screenshot();
			test.log(LogStatus.INFO, "Edited Case Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: CaseEditWithAllFields() as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: CaseEditWithAllFields() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Test(priority = 3, enabled = true)
	public void TestDischargedCase() throws InterruptedException{
		try{
			test = extent.startTest("verify that the case with the discharge date does not display as an available case to select for the visit note. ", "Tverify that the case with the discharge date does not display as an available case to select for the visit note. " + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			AddPatientUtils.addpatient_withallfields(driver, "Discharge Cases");
			AddCaseUtils.GoToAddNewCase(driver);
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver),"14/06/2015"); // Enter case effective date
			AddCaseUtils.ClickCaseSave(driver);
			ActionUtils.click(CasePage.CaseEditButton(driver));
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Add Discharge date to Case");
			HandlingCalendars.datepick(driver, CasePage.DischargeDate(driver),"14/06/2015"); // Enter discharge date
			Commons.screenshot();
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
		    AddCaseUtils.ClickCaseSave(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"),140);
			ID = ActionUtils.getText(AddPatientPage.patientID(driver));
			test.log(LogStatus.INFO, "Edited Case Saves  successfully.");
			test.log(LogStatus.INFO, "verify that the case with the discharge date does not display as an available case to select for the visit note. ", 
			"verify that the case with the discharge date does not display as an available case to select for the visit note.");
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			ActionUtils.click(CreateNotePage.AddClinicalNote(driver));
			System.out.println("Opened Create Note Screen");
			Commons.waitForLoad(driver);
			Assert.assertFalse(CreateNotePage.CaseNameddl(driver).getText().contains("Case With All Fields"));
			Assert.assertTrue(CreateNotePage.CaseNameddl(driver).getText().contains("[EffectiveDate - "));
			  System.out.println("****************** Assertion-1 Pass***********");
		}catch(Exception e){
			
			Assert.assertFalse(true,"Could not save case"+Throwables.getStackTraceAsString(e));
			
		     }
	       Commons.screenshot();
	       extent.endTest(test);
	       
	     
	       
	       test = extent.startTest("verify that the case with the discharge date does not display as an available case to select for the appointment. ",
	    		 "verify that the case with the discharge date does not display as an available case to select for the appointment. " + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
		//	SearchPatientUtils.QuickpatientSearch(driver,ID);
			System.out.println("Go to Appointment Screen");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForLoad(driver);
			Commons.screenshot();
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			Thread.sleep(2000);
			Commons.waitforElement(driver,By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"), 30);
			if(!driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']")).getAttribute("value").contains(ID)){
			driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']")).clear();
			driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']")).sendKeys(ID);
			Commons.waitforElement(driver, By.xpath("//li[1]//span[contains(.,'" + ID + "')]"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//li[1]//span[contains(.,'" + ID + "')]")));
			Thread.sleep(2000);
			}
			// Select Case name
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseDropDown']//span[contains(@class,'k-input')]")));
			Thread.sleep(2000);
			Commons.screenshot();
			 Assert.assertFalse((Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='cases_listbox']//li[contains(.,'Case With All Fields')]"),5)));
			 Assert.assertTrue((Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='cases_listbox']//li[contains(.,'[EffectiveDate -')]"),15)));
		    Assert.assertTrue((Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='cases_listbox']//li[contains(.,'Create A New Case')]"),15)));
		    ActionUtils.click(SchedulerPage.CloseAppointmentWindow(driver));
		    System.out.println("****************** Assertion-2 Pass***********");
		    Commons.screenshot();
		    extent.endTest(test);	
		    
		    test = extent.startTest("verify that the case with the discharge date display on Add charge page as an available case to select for the visit note.",
		    		 "verify that the case with the discharge date display on Add charge page as an available case to select for the visit note." + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
				test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
						this.getClass().getPackage().getName().toString());
				test.log(LogStatus.INFO, "Browser started");
				test.log(LogStatus.INFO, "Navigate to Add Charge Page");
				AddChargeUtils.NavigateToAddChargePage(driver);
				Assert.assertTrue((Commons.waitforElement(driver, By.xpath("//*[@id='ddlChargeCaseList']//option[contains(@label,'Case With All Fields')]"),50)));
				System.out.println("****************** Assertion-3 Pass***********");
				Commons.screenshot();
				extent.endTest(test);
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
	}
}
		





















































































