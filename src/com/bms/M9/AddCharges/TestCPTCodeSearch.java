package com.bms.M9.AddCharges;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bms.M15.ArrivalAlert.DifferentAppointmentTypesOnLocationView;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import ReportUtils.ExtentManager;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.Commons;
import Utils.HandlingCalendars;

public class TestCPTCodeSearch extends TestSetUp {
	// CHG.ADD.0025,26,27,23
	//CHG.ADD.120,CHG.ADD.121,CHG.ADD.150,CHG.ADD.160,CHG.ADD.164
	public String strActualtext = null;

	@Test(enabled = true, priority = 1)
	public void TestCPTCodesearch() throws InterruptedException {
		try {
			test = extent.startTest("To Validate CPT Code search with magnifier and autocomplete. ",
					"To Validate CPT Code search with magnifier and autocomplete" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient with complete Case");
			strActualtext = AddChargeUtils.QuickSearchPatient(driver, "EMRMedicare");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			if(!Commons.ExistandDisplayElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"),60)){
				
				AddChargeUtils.AddPatientCaseCompleted(driver, "Addcharge");
				AddChargeUtils.NavigateToAddChargePage(driver);
				Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			}
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			try {
				
				
				// Search CPT by COde AutoComplete
				test.log(LogStatus.INFO, "Enter CPT Code and verify Auto completes");
				Commons.waitforElement(driver, By.xpath("//*[@id='autosearch_ProcedureCode0']"), 50);
				driver.findElement(By.xpath("//div[@id='tbl_proc']//tr[1]//input[@type='search']")).click();
				driver.findElement(By.xpath("//div[@id='tbl_proc']//tr[1]//input[@type='search']")).clear();
				driver.findElement(By.xpath("//div[@id='tbl_proc']//tr[1]//input[@type='search']")).sendKeys("98770");
				Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(.,'98770 - EVAL BRIEF ')]"), 15);
				String autocomplete = ActionUtils
						.getText(driver.findElement(By.xpath("//span[contains(.,'98770 - EVAL BRIEF ')]")));
				Assert.assertEquals(autocomplete.trim(), "98770 - EVAL BRIEF");
				test.log(LogStatus.INFO, "Found CPT Code auto complete" + autocomplete.trim());
				Commons.screenshot();
				ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'98770 - EVAL BRIEF ')]")));
				Thread.sleep(1000);
				ActionUtils.click(driver.findElement(By.xpath("//button[@id='btnCrlProdCode0']")));
			
				
				// Search CPT by COde
				test.log(LogStatus.INFO, "To Verify Magnifying search for CPT Code");
				ActionUtils.click(driver.findElement(By.xpath("//button[@id='btnProcCode0']")));
				Commons.ExistandDisplayElement(driver,
						By.xpath("//div[@id='ProcedureCode']//input[contains(@value,'Search')]"), 15);
				test.log(LogStatus.INFO, "Search Procedure Code by Entering CODE in search field");
				driver.findElement(By.xpath("//input[@id='Proc_Code']")).sendKeys("98770");
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='ProcedureCode']//input[contains(@value,'Search')]")));
				Commons.ExistandDisplayElement(driver, By.xpath("//td[contains(.,'98770')]"), 40);
				Commons.screenshot();
				
				// Search CPT by Description
				test.log(LogStatus.INFO, "Search Procedure Code by Entering Description in search field");
				driver.findElement(By.xpath("//input[@id='Proc_Code']")).clear();
				driver.findElement(By.xpath("//input[@id='Proc_Des']")).sendKeys("EVAL BRIEF");
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='ProcedureCode']//input[contains(@value,'Search')]")));
				Commons.ExistandDisplayElement(driver, By.xpath("//td[contains(.,'98770')]"), 40);
			    Commons.screenshot();
				Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='ProcCode']//tr//td[contains(.,'98770')]"),10);
				ActionUtils.doubleClick(driver,
						driver.findElement(By.xpath("//*[@id='ProcCode']//tr//td[contains(.,'98770')]")));
				System.out.println("AssertionPass");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Error*************" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true, "Test CPT Code search Fail" + Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "Test CPT Code search Pass");
			
		} catch (Exception e) {
			test.log(LogStatus.INFO,"To Validate CPT Code search with magnifier and autocomplete."+ Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, "Test CPT Code search Fail" + Throwables.getStackTraceAsString(e));
		}
		
}

	
	
	@Test(enabled = true, priority = 2)
	public void TestModifiers() throws InterruptedException {
	
		
		try {
			test = extent
					.startTest("To Validate Modifier 1 and Modifier 2 List display dropdown values on Add charges page",
							"To Validate Modifier 1 and Modifier 2 List on Add charges page"
									+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
									+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Add patient with complete Case");
			strActualtext = AddChargeUtils.AddPatientCaseCompleted(driver, "dfvffvf");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			
				// Validate Modifier 1 list
				test.log(LogStatus.INFO, "Validating List of Modifier 1");
				Commons.waitforElement(driver, By.xpath("//select[@id='ddlMod10']"), 50);
				ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlMod10']")));
				Commons.screenshot();
				List<WebElement> allElements1 = driver.findElements(By.xpath("//select[@id='ddlMod10']//option"));
				for (WebElement ele : allElements1) {
					System.out.println("COde + Description===>" + ele.getText());
					test.log(LogStatus.INFO, "Found Modifier 1:" + "  " + ele.getText());
				}
				
				
				
				// Validate Modifier 2 list
				test.log(LogStatus.INFO, "Validating List of Modifier 2");
				Commons.waitforElement(driver, By.xpath("//select[@id='ddlMod20']"), 50);
				ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlMod20']")));
				Commons.screenshot();
				List<WebElement> allElements2 = driver.findElements(By.xpath("//select[@id='ddlMod20']//option"));
				for (WebElement ele : allElements2) {
					System.out.println("COde + Description===>" + ele.getText());
					test.log(LogStatus.INFO, "Found Modifier 2:" + "  " + ele.getText());
				}
				System.out.println("AssertionPass");
			} catch (Exception e) {
				test.log(LogStatus.INFO,
						"*************** Error*************" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true,
						" Test Validate Modifier 1 and Modifier 2 List Fail" + Throwables.getStackTraceAsString(e));
			}

			test.log(LogStatus.INFO, "Test Validate Modifier 1 and Modifier 2 List Pass");
			Commons.screenshot();
			extent.flush();
			extent.endTest(test);
		
			
			//CHG.ADD.120
			test = extent.startTest("To Validate Functional reporting message before 7/1/13 and after 7/1/13 ",
					"To Validate Functional reporting message before 7/1/13 and after 7/1/13 " + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient with complete Case and Navigate to Add charge page");
			
			try{
			// Case-1 FLR message display
			test.log(LogStatus.INFO, "The following message display=> "+"Functional reporting is required on or before the 10th visit.");
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "01/07/2013");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "01/07/2013");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/07/2013");
			Commons.waitforElement(driver, By.xpath("//div[contains(@class,'redMessage')][contains(.,'Functional reporting is required on or before the 10th visit.')]"),20);
			//CHG.ADD.121
			// Case-2 FLR message  does not display
			test.log(LogStatus.INFO, "The following message does NOT display=> "+"Functional reporting is required on or before the 10th visit.");
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "01/06/2013");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "01/06/2013");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/06/2013");
		    Assert.assertFalse(Commons.existsElement(driver, By.xpath("//div[contains(@class,'redMessage')][contains(.,'Functional reporting is required on or before the 10th visit.')]")));
			}catch(Exception e){
				
				test.log(LogStatus.INFO,"To Validate Functional reporting message before 7/1/13 and after 7/1/13"+ Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true, "Test To Validate Functional reporting message before 7/1/13 and after 7/1/13 Fail" + Throwables.getStackTraceAsString(e));	
			}
			
			
			
			//CHG.ADD.150
			test = extent.startTest("To Vrify Missing Dx",
					"To Validate Functional reporting message before 7/1/13 and after 7/1/13 " + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient with complete Case and Navigate to Add charge page");
			// Add Active ICD-10 Dx code
			//AddCaseUtils.AddDXCodebycode(driver, "A001");
			Assert.assertTrue(
					Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='gridDiagnosis']//tbody//tr"), 10));
			Commons.screenshot();
		//	test.log(LogStatus.INFO, "Added an active ICD code");
			driver.findElement(By.xpath("//*[@id='tbl_proc']/table/tbody/tr[1]/td[8]/input")).click();
			driver.findElement(By.xpath("//*[@id='tbl_proc']/table/tbody/tr[1]/td[8]/input")).clear();
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			Commons.screenshot();
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("All charges must have at least 1 diagnosis."));
			Commons.screenshot();
			extent.endTest(test);
			
			
			//CHG.ADD.160
			
			test = extent.startTest("To Validate error messages when Functional limitation report codes reported w/out modifiers",
					"To Validate error messages when Functional limitation report codes reported w/out modifiers" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient with complete Case and Navigate to Add charge page");
			 AddCaseUtils.GoToCaseList(driver);
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
		//	ActionUtils.click(driver.findElement(By.xpath("//button[@id='btnCrlProdCode0']")));
			AddChargeUtils.AddCPTCode(driver, "G8978", 1);
			AddChargeUtils.AddCPTCode(driver, "G8979", 2);
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Functional Code G8979 does not have a valid modifier selected."));
			System.out.println("***********Assertion-1 Pass********");
            Commons.SelectElement(driver, By.xpath("//*[@id='ddlMod11']"),"CI - < 20% impaired");
            ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
            Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Functional Code G8978 does not have a valid modifier selected."));
            System.out.println("***********Assertion-2 Pass********");
            Commons.SelectElement(driver, By.xpath("//*[@id='ddlMod10']"),"CN - 100% impaired");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
		    Assert.assertTrue(Commons.capturemessage(driver, 180).contains("Charges Saved."));
		    AddCaseUtils.GoToCaseList(driver); 
			Commons.waitforElement(driver, By.xpath("//*[@id='gridCaseDetails']//td[12][contains(.,'1')]"),90);
			AddChargeUtils.NavigateToAddChargePage(driver);
		    test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "15/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "15/06/2016");
			AddChargeUtils.AddCPTCode(driver, "G8978", 1);
			Commons.SelectElement(driver, By.xpath("//*[@id='ddlMod10']"),"CN - 100% impaired");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "15/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "15/06/2016");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.waitforElement(driver, By.xpath("//*[@id='functionalReportingErrorPopup']//div[3]//div[contains(.,' You have not entered a matching code for Functional Code G8978. Are you sure you want to save the charges?')]"),90);
			Commons.screenshot();
			
			 System.out.println("***********Assertion-3 Pass********");
			
			
			
			
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='functionalReportingErrorPopup']//button[contains(.,'No')]")));
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Charges ')]"),30); // user remains on add charges page
			
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.waitforElement(driver, By.xpath("//*[@id='functionalReportingErrorPopup']//div[3]//div[contains"
			+ "(.,' You have not entered a matching code for Functional Code G8978. Are you sure you want to save the charges?')]"),90);
			Commons.screenshot();
			
			 System.out.println("***********Assertion-4 Pass********");
			
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='functionalReportingErrorPopup']//button[contains(.,'Yes')]")));
			 Assert.assertTrue(Commons.capturemessage(driver, 180).contains("Charges Saved."));
			 AddCaseUtils.GoToCaseList(driver); 
			Commons.waitforElement(driver, By.xpath("//*[@id='gridCaseDetails']//td[12][contains(.,'2')]"),90);
			
			 System.out.println("***********Assertion-5 Pass********");
              test = extent.startTest(
				"To Validate Add/Remove charge line,Add active ICD code,Add Charges with CPT code and modifier on Add charges page",
				"To Validate Add/Remove charge line on Add charges page" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Search patient with complete Case");
		try {
			
			AddChargeUtils.NavigateToAddChargePage(driver);
			// Validate Add/Remove charge line
			test.log(LogStatus.INFO, "Validate Add/Remove charge line");
			int beforeadd = driver.findElements(By.xpath("//div[@id='tbl_proc']//tbody//tr")).size();
			test.log(LogStatus.INFO, "charge line count before adding" + beforeadd);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Add Charge')]")));
			int afteradd = driver.findElements(By.xpath("//div[@id='tbl_proc']//tbody//tr")).size();
			test.log(LogStatus.INFO, "charge line count after adding charge line" + afteradd);
			ActionUtils.click(driver.findElement(By.xpath(".//*[@id='tbl_proc']//tbody/tr[6]/td[1]/input")));
			Commons.screenshot();
			// Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(.,'Remove Charge')]")).click();
			int removeline = driver.findElements(By.xpath("//div[@id='tbl_proc']//tbody//tr")).size();
			test.log(LogStatus.INFO, "charge line count after removeing charge line" + removeline);
			Assert.assertEquals(beforeadd, removeline);
			// Delete Active ICD codes if any
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='gridDiagnosis']//tbody//tr"), 10)) {
				System.out.println("Deleting Existing ICD codes from Current Patient on Charge page");
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='gridDiagnosis']//*[@id='chkSelecAll']")));
				ActionUtils.click(driver.findElement(
						By.xpath("//*[@id='dxCode']//button[contains(text(),'Delete')][@ng-click='removeDxcode()']")));
			}
			// Add Active ICD-10 Dx code
			AddCaseUtils.AddDXCodebycode(driver, "A001");
			Assert.assertTrue(
					Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='gridDiagnosis']//tbody//tr"), 10));
			Commons.screenshot();
			test.log(LogStatus.INFO, "Added an active ICD code");
			// Add a CPT code with a mod 1 selection and save the charge.
			test.log(LogStatus.INFO, "Add a CPT code with a mod 1 selection and save the charge");
			ActionUtils.click(driver.findElement(
					By.xpath("//select[@id='ddlMod10']//option[contains(.,'50 - BILATERAL PROCEDURE')][1]")));
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			Commons.screenshot();
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.screenshot();
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			AddChargeUtils.NavigateToViewChargePage(driver);
			test.log(LogStatus.INFO, "Navigated to View charges");
			test.log(LogStatus.INFO, "Verifying Modifier-1 has applied and Modifier 2 is empty");
			Assert.assertFalse(Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='viewChargeGrid']/table/tbody/tr/td[9][contains(text(),'50')]"), 12));
			Assert.assertTrue(Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='viewChargeGrid']/table/tbody/tr/td[8][contains(text(),'50')]"), 12));
			test.log(LogStatus.INFO, "*****************Assertion Pass***************");
			extent.flush();
			// extent.endTest(test);
		} catch (AssertionError e) {
			System.out.println("*************Assertion error*********");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
		
		//CHG.ADD164
		
		DifferentAppointmentTypesOnLocationView obj = new DifferentAppointmentTypesOnLocationView();
		obj.TestCancelationOnLocationView();
		
		test = extent.startTest(
				"To Validate Self-Pay patients and charges (Yes/No)",
				"To Validate Self-Pay patients and charges (Yes/No)" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		
		
		//case-1 with No
		AddChargeUtils.NavigateToAddChargePage(driver);
		test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "01/01/2017");
		HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "01/01/2017");
		AddChargeUtils.AddCPTCode(driver, "G8981", 1);
		Commons.SelectElement(driver, By.xpath("//*[@id='ddlMod10']"),"CN - 100% impaired");
		test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
		HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "01/01/2017");
		ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
		Commons.waitforElement(driver, By.xpath("//*[@id='CPInsuranceCheck']//div[3]//div[3][contains(.,'This charge is assigned to a cash patient case. Do you want to count it as a visit?')]"),90);
		Commons.screenshot();
		System.out.println("***********Assertion-6 Pass********");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='CPInsuranceCheck']//button[contains(.,'No')]")));
		Commons.capturemessage(driver, 40);
		
		// Case-2 with Yes
		
		
		AddChargeUtils.NavigateToAddChargePage(driver);
		test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "02/02/2017");
		HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "02/02/2017");
		AddChargeUtils.AddCPTCode(driver, "G8981", 1);
		Commons.SelectElement(driver, By.xpath("//*[@id='ddlMod10']"),"CN - 100% impaired");
		test.log(LogStatus.INFO, "Enter Date in Plan of care" + "02/02/2017");
		HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "02/02/2017");
		ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
		Commons.waitforElement(driver, By.xpath("//*[@id='CPInsuranceCheck']//div[3]//div[3][contains(.,'This charge is assigned to a cash patient case. Do you want to count it as a visit?')]"),90);
		Commons.screenshot();
		System.out.println("***********Assertion-6 Pass********");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='CPInsuranceCheck']//button[contains(.,'Yes')]")));
		Assert.assertTrue(Commons.capturemessage(driver, 180).contains("Charges Saved."));
		AddCaseUtils.GoToCaseList(driver); 
		Commons.waitforElement(driver, By.xpath("//*[@id='gridCaseDetails']//td[12][contains(.,'1')]"),90);

		
	}
}
