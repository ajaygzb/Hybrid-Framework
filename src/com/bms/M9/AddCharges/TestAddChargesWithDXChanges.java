package com.bms.M9.AddCharges;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.HandlingCalendars;

public class TestAddChargesWithDXChanges extends TestSetUp{
	
	
	@Test(enabled = true , priority= 2)
	public void TestADDchargewithNewDX()
	{
		test = extent.startTest(
				"To Test Add Charge with New DX", "To Test Add Charge with New DX" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add patient with complete Case");
		try
		{
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCodebycode(driver, "a001");
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "01/01/2017");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "01/01/2017");
			test.log(LogStatus.INFO, "Adding Dx code 2 :  A000");
		//	Commons.scrollElementinotView(driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")), driver);
		     ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
			/*Commons.scrollElementinotView(driver.findElement(By.id("btnDiagnosisCodeDelete")), driver);
*/			
			driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
			try
			{
			Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 20);
			}
			catch(Exception e)
			{
				  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
				  driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
				  Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
			}
			driver.findElement(By.xpath("//input[@id='dx10']")).sendKeys("a000");
			driver.findElement(By.xpath("//button[@id='searchBtn']")).click();
			Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//table//tbody//tr/td"), 120);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//div[@id='gridSearch']//table//tbody//tr/td")));
			test.log(LogStatus.INFO, "Adding CPT Code 97140");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Add new Dx to proc code ");
			Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//table//tbody//tr[1]//td//input[@ng-model='charge.dx']"), 120);
			ActionUtils.clear(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[1]//td//input[@ng-model='charge.dx']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[1]//td//input[@ng-model='charge.dx']")), "2");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			//verifing dx under view charges 
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[11]"), 120);
			String dx =  driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[11]")).getText();
			System.out.println("dx2 visible under view charges" + dx);
			test.log(LogStatus.INFO, "dx2 visible under view charges" + dx);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[11]")));
			Assert.assertTrue(dx.equalsIgnoreCase("a000"));
			test.log(LogStatus.INFO, "verified Newly added Dx is visible under view charges ");
			//Checking dx in case
			driver.findElement(By.xpath("//span[@id='patientFirstName']")).click();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@id='casesId']"), 120);
			driver.findElement(By.xpath("//a[@id='casesId']")).click();
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//div[@id='gridCaseDetails']//table//tbody//tr//td//span[contains(text(),'Case With All Fields')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A000')]"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A000')]")).isDisplayed());
			Commons.scrollElementinotView(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A000')]")), driver);
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A000')]")));
			test.log(LogStatus.INFO, "verified Newly added Dx is visible in case ");
			//Checking ledger report 
			driver.findElement(By.xpath("//span[@id='patientFirstName']")).click();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Financial')]"), 120);
			driver.findElement(By.xpath("//a[contains(text(),'Financial')]")).click();
			Commons.waitforElement(driver, By.xpath("//button[@id='LedgerFull']"), 120);
			driver.findElement(By.xpath("//button[@id='LedgerFull']")).click();
			Commons.waitforElement(driver, By.xpath("//button[@id='ledgerFullCreateReport']"), 120);
			driver.findElement(By.xpath("//button[@id='ledgerFullCreateReport']")).click();
			Commons.waitforElement(driver, By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A000')]"), 40);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A000')]")).isDisplayed());
			test.log(LogStatus.INFO, "verified Newly added Dx codes are visible in Ledger full ");
			System.out.println("verified Newly added Dx codes are visible in Ledger full");
		
		}
		
		catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test Add Charge with New DX Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test Add Charge with New DX Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	
	
	@Test(enabled = true , priority= 2)
	public void TestEDITCharge_withNEWdx()
	{
		test = extent.startTest(
				"To Test edit Charge with New DX", "To Test edit Charge with New DX" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add patient with complete Case");
		try
		{
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCodebycode(driver, "a001");
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "01/01/2017");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "01/01/2017");
			test.log(LogStatus.INFO, "Adding Dx code 2 :  A000");
			   ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
			   driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
			   try
				{
				Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 30);
				}
				catch(Exception e)
				{
					  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
					  driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
					  Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
				}
			
			Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
			driver.findElement(By.xpath("//input[@id='dx10']")).sendKeys("a000");
			driver.findElement(By.xpath("//button[@id='searchBtn']")).click();
			Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//table//tbody//tr/td"), 120);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//div[@id='gridSearch']//table//tbody//tr/td")));
			test.log(LogStatus.INFO, "Adding CPT Code 97140");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Add new Dx to proc code ");
			Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//table//tbody//tr[1]//td//input[@ng-model='charge.dx']"), 120);
			ActionUtils.sendKeys(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[1]//td//input[@ng-model='charge.dx']")), ",2");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			test.log(LogStatus.INFO, "Navigating to view charges page ");
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[1]/input"), 120);
			driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[1]/input")).click();
			test.log(LogStatus.INFO, "Editing selected charge ");
			driver.findElement(By.xpath("//button[@id='btnEditCharge']")).click();
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Adding Dx code 3 :  A009");
			   ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
			driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
			try
			{
			Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 30);
			}
			catch(Exception e)
			{
				  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
				  driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
				  Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
			}
			driver.findElement(By.xpath("//input[@id='dx10']")).sendKeys("a009");
			driver.findElement(By.xpath("//button[@id='searchBtn']")).click();
			Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//table//tbody//tr/td"), 120);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//div[@id='gridSearch']//table//tbody//tr/td")));
			Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//table//tbody//tr[1]//td//input[@ng-model='charge.dx']"), 120);
			ActionUtils.clear(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[1]//td//input[@ng-model='charge.dx']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[1]//td//input[@ng-model='charge.dx']")), "3");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[11]"), 120);
			String dx =  driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[11]")).getText();
			System.out.println("dx3 visible under view charges" + dx);
			test.log(LogStatus.INFO, "dx3 visible under view charges" + dx);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[11]")));
			Assert.assertTrue(dx.equalsIgnoreCase("a009"));
			test.log(LogStatus.INFO, "verified Newly added Dx is visible under view charges ");
			//Checking dx in case
			driver.findElement(By.xpath("//span[@id='patientFirstName']")).click();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@id='casesId']"), 120);
			driver.findElement(By.xpath("//a[@id='casesId']")).click();
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//div[@id='gridCaseDetails']//table//tbody//tr//td//span[contains(text(),'Case With All Fields')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A009')]"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A009')]")).isDisplayed());
			Commons.scrollElementinotView(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A009')]")), driver);
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A009')]")));
			test.log(LogStatus.INFO, "verified Newly added Dx is visible in case ");
			//Checking ledger report 
			driver.findElement(By.xpath("//span[@id='patientFirstName']")).click();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Financial')]"), 120);
			driver.findElement(By.xpath("//a[contains(text(),'Financial')]")).click();
			Commons.waitforElement(driver, By.xpath("//button[@id='LedgerFull']"), 120);
			driver.findElement(By.xpath("//button[@id='LedgerFull']")).click();
			Commons.waitforElement(driver, By.xpath("//button[@id='ledgerFullCreateReport']"), 120);
			driver.findElement(By.xpath("//button[@id='ledgerFullCreateReport']")).click();
			Commons.waitforElement(driver, By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A009')]"), 40);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A009')]")).isDisplayed());
			test.log(LogStatus.INFO, "verified Newly added Dx codes are visible in Ledger full ");
			System.out.println("verified Newly added Dx codes are visible in Ledger full");
		}
		
		catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestEDITCharge_withNEWdx Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestEDITCharge_withNEWdx Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
	
	
	@Test(enabled = true , priority= 3)
	public void TestADDchargewithDifferentDxPointer()
	{
		test = extent.startTest(
				"To Test ADD charge with different dx pointer", "To Test ADD charge with different dx pointer" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add patient with complete Case");
		try
		{
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCodebycode(driver, "a001");
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "01/01/2017");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "01/01/2017");
			test.log(LogStatus.INFO, "Adding Dx code 2 :  A000");
			   ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
			driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
			try
			{
			Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 30);
			}
			catch(Exception e)
			{
				  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
				  driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
				  Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
			}
			Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
			driver.findElement(By.xpath("//input[@id='dx10']")).sendKeys("a000");
			driver.findElement(By.xpath("//button[@id='searchBtn']")).click();
			Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//table//tbody//tr/td"), 120);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//div[@id='gridSearch']//table//tbody//tr/td")));
			test.log(LogStatus.INFO, "Adding Dx code 3 :  A009");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
			try
			{
			Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
			}
			catch(Exception e)
			{
				  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
				  driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
				  Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
			}
		    Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
			driver.findElement(By.xpath("//input[@id='dx10']")).sendKeys("a009");
			driver.findElement(By.xpath("//button[@id='searchBtn']")).click();
			try
			{
			Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 30);
			}
			catch(Exception e)
			{
				  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
				  driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
				  Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
			}
			Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//table//tbody//tr/td"), 120);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//div[@id='gridSearch']//table//tbody//tr/td")));
			test.log(LogStatus.INFO, "Adding Dx code 4 :  C7A00");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
			 try
				{
				Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 30);
				}
				catch(Exception e)
				{
					  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
					  driver.findElement(By.xpath("//button[@id='btnICDCode_']/i")).click();
					  Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
				}
			Commons.waitforElement(driver, By.xpath("//input[@id='dx10']"), 120);
			driver.findElement(By.xpath("//input[@id='dx10']")).sendKeys("C7A00");
			driver.findElement(By.xpath("//button[@id='searchBtn']")).click();
			Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//table//tbody//tr/td"), 120);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//div[@id='gridSearch']//table//tbody//tr/td")));
			test.log(LogStatus.INFO, "Adding CPT Code 97140");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Adding CPT Code 97112");
			AddChargeUtils.AddCPTCode(driver, "97112", 2);
			Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//table//tbody//tr[2]//td//input[@ng-model='charge.dx']"), 120);
			ActionUtils.clear(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[2]//td//input[@ng-model='charge.dx']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[2]//td//input[@ng-model='charge.dx']")), "2");
			test.log(LogStatus.INFO, "Adding CPT Code 97113");
			AddChargeUtils.AddCPTCode(driver, "97113", 3);
			Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//table//tbody//tr[3]//td//input[@ng-model='charge.dx']"), 120);
			ActionUtils.clear(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[3]//td//input[@ng-model='charge.dx']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[3]//td//input[@ng-model='charge.dx']")), "3");
			test.log(LogStatus.INFO, "Adding CPT Code 97110");
			AddChargeUtils.AddCPTCode(driver, "97110", 4);
			Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//table//tbody//tr[4]//td//input[@ng-model='charge.dx']"), 120);
			ActionUtils.clear(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[4]//td//input[@ng-model='charge.dx']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[4]//td//input[@ng-model='charge.dx']")), "4");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			try
			{
				Commons.waitforElement(driver, By.xpath("//button[@id='btnSaveWarning']"), 40);
				driver.findElement(By.xpath("//button[@id='btnSaveWarning']")).click();
				ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			}
			catch(Exception e)
			{
				
			}
			test.log(LogStatus.INFO, "Navigating to view charges page ");
			AddChargeUtils.NavigateToViewChargePage(driver);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[contains(text(),'A000')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[contains(text(),'C7A00')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[contains(text(),'A009')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[contains(text(),'A001')]")).isDisplayed());
			test.log(LogStatus.INFO, "verified Newly added Dx codes are visible under view charges ");
			System.out.println("verified Newly added Dx codes are visible under view charges ");
			//Checking dx in case
			driver.findElement(By.xpath("//span[@id='patientFirstName']")).click();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@id='casesId']"), 120);
			driver.findElement(By.xpath("//a[@id='casesId']")).click();
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//div[@id='gridCaseDetails']//table//tbody//tr//td//span[contains(text(),'Case With All Fields')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A000')]"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A000')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'C7A00')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A009')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A001')]")).isDisplayed());
			test.log(LogStatus.INFO, "verified Newly added Dx codes are visible in case ");
			System.out.println("verified Newly added Dx codes are visible in case ");
			//Checking ledger report 
			driver.findElement(By.xpath("//span[@id='patientFirstName']")).click();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Financial')]"), 120);
			driver.findElement(By.xpath("//a[contains(text(),'Financial')]")).click();
			Commons.waitforElement(driver, By.xpath("//button[@id='LedgerFull']"), 120);
			driver.findElement(By.xpath("//button[@id='LedgerFull']")).click();
			Commons.waitforElement(driver, By.xpath("//button[@id='ledgerFullCreateReport']"), 120);
			driver.findElement(By.xpath("//button[@id='ledgerFullCreateReport']")).click();
			Commons.waitforElement(driver, By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A000')]"), 40);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A000')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'C7A00')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A009')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A001')]")).isDisplayed());
			test.log(LogStatus.INFO, "verified Newly added Dx codes are visible in Ledger full ");
			System.out.println("verified Newly added Dx codes are visible in Ledger full");
		}
		catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test ADD charge with different dx pointer Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test ADD charge with different dx pointer Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
	}
	
	
	
	
	@Test(enabled = true , priority= 4)
	public void TestEDITvisitwithDifferentDxPointer()
	{
		test = extent.startTest(
				"To Test EDIT visit with Different Dx Pointer", "To Test EDIT visit with Different Dx Pointer" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add patient with complete Case");
		try
		{
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCodebycode(driver, "a001");
			AddCaseUtils.AddDXCodebycode(driver, "a000");
			AddCaseUtils.AddDXCodebycode(driver, "a009");
			AddCaseUtils.AddDXCodebycode(driver, "A0100");
			AddCaseUtils.AddDXCodebycode(driver, "A0101");
			AddCaseUtils.AddDXCodebycode(driver, "A0102");
			AddCaseUtils.AddDXCodebycode(driver, "A0103");
			AddCaseUtils.AddDXCodebycode(driver, "A0104");
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "01/01/2017");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "01/01/2017");
			test.log(LogStatus.INFO, "Adding CPT Code 97140");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Adding CPT Code 97112");
			AddChargeUtils.AddCPTCode(driver, "97112", 2);
			test.log(LogStatus.INFO, "Adding CPT Code 97113");
			AddChargeUtils.AddCPTCode(driver, "97113", 3);
			test.log(LogStatus.INFO, "Adding CPT Code 97110");
			AddChargeUtils.AddCPTCode(driver, "97110", 4);
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			try
			{
				Commons.waitforElement(driver, By.xpath("//button[@id='btnSaveWarning']"), 40);
				driver.findElement(By.xpath("//button[@id='btnSaveWarning']")).click();
				ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			}
			catch(Exception e)
			{
				
			}
			test.log(LogStatus.INFO, "Navigating to view charges page ");
			//navigating to view charges
			//editing charges
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[1]/input"), 120);
			driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[1]/input")).click();
			test.log(LogStatus.INFO, "Editing selected charge ");
			driver.findElement(By.xpath("//button[@id='btnEditAllCharge']")).click();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//table//tbody//tr[1]//td//input[@ng-model='charge.dx']"), 120);
			ActionUtils.clear(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[1]//td//input[@ng-model='charge.dx']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[1]//td//input[@ng-model='charge.dx']")), "8");
			Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//table//tbody//tr[2]//td//input[@ng-model='charge.dx']"), 120);
			ActionUtils.clear(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[2]//td//input[@ng-model='charge.dx']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[2]//td//input[@ng-model='charge.dx']")), "7");
			Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//table//tbody//tr[3]//td//input[@ng-model='charge.dx']"), 120);
			ActionUtils.clear(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[3]//td//input[@ng-model='charge.dx']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//div[@id='tbl_proc']//table//tbody//tr[3]//td//input[@ng-model='charge.dx']")), "6");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			try
			{
				Commons.waitforElement(driver, By.xpath("//button[@id='btnSaveWarning']"), 40);
				driver.findElement(By.xpath("//button[@id='btnSaveWarning']")).click();
				ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			}
			catch(Exception e)
			{
				
			}
			//Navigating to view charges
			AddChargeUtils.NavigateToViewChargePage(driver);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[contains(text(),'A0104')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[contains(text(),'A0103')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[contains(text(),'A0102')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='viewChargeGrid']//table//tbody//tr//td[contains(text(),'A001')]")).isDisplayed());
			test.log(LogStatus.INFO, "verified Newly added Dx codes are visible under view charges ");
			System.out.println("verified Newly added Dx codes are visible under view charges ");
			//Checking dx in case
			driver.findElement(By.xpath("//span[@id='patientFirstName']")).click();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@id='casesId']"), 120);
			driver.findElement(By.xpath("//a[@id='casesId']")).click();
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//div[@id='gridCaseDetails']//table//tbody//tr//td//span[contains(text(),'Case With All Fields')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A0104')]"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A0104')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A0103')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A0102')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='gridDiagnosis']//table/tbody//tr//td//span[contains(text(),'A001')]")).isDisplayed());
			test.log(LogStatus.INFO, "verified Newly added Dx codes are visible in case ");
			System.out.println("verified Newly added Dx codes are visible in case ");
			//Checking ledger report 
			driver.findElement(By.xpath("//span[@id='patientFirstName']")).click();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Financial')]"), 120);
			driver.findElement(By.xpath("//a[contains(text(),'Financial')]")).click();
			Commons.waitforElement(driver, By.xpath("//button[@id='LedgerFull']"), 120);
			driver.findElement(By.xpath("//button[@id='LedgerFull']")).click();
			Commons.waitforElement(driver, By.xpath("//button[@id='ledgerFullCreateReport']"), 120);
			driver.findElement(By.xpath("//button[@id='ledgerFullCreateReport']")).click();
			Commons.waitforElement(driver, By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A0104')]"), 40);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A0104')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A0103')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A0102')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ledgerFull']//table//tbody//tr//td[contains(text(),'A001')]")).isDisplayed());
			test.log(LogStatus.INFO, "verified Newly added Dx codes are visible in Ledger full ");
			System.out.println("verified Newly added Dx codes are visible in Ledger full");
		}
		catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test EDIT visit with Different Dx Pointer Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test EDIT visit with Different Dx Pointer Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
	}
	
	
	@Test(enabled = true , priority= 1)
	public void TestReleasePendingCharges()
	{
		test = extent.startTest(
				"To Test Release Pending Charges", "To Test Release Pending Charges" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add patient with complete Case");
		try
		{
			AddChargeUtils.NavigateToChargeAuditPage(driver);
			Commons.waitforElement(driver, By.xpath("//a[@id='chargeAnchor']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//a[@id='chargeAnchor']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.id("btnCreateReport"), 40);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.id("btnCreateReport")));
			Commons.ExistandDisplayElement(driver, By.xpath("(//table[@id='auditTable']//tr//th[1])[1]"), 60);
			String patient_id = ActionUtils.getText(driver.findElement(By.xpath("(//table[@id='auditTable']//tr//th[1])[1]")));
			System.out.println("patient 1 id is "+ patient_id);
			ActionUtils.click(driver.findElement
			(By.xpath("(//table[@id='auditTable']//tbody//tr//td//select//option[contains(text(),'No Authorization')])[1]")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.id("btnRelease")));
			Commons.capturemessage(driver, 60);
			Commons.waitforElement(driver, By.id("btnCreateReport"), 40);
			ActionUtils.click(driver.findElement(By.id("btnCreateReport")));
			Commons.waitForLoad(driver);
			if(Commons.ExistandDisplayElement(driver, By.xpath("(//table[@id='auditTable']//tr//th[1])[1]"),60)){
			String patient_id2 = ActionUtils.getText(driver.findElement(By.xpath("(//table[@id='auditTable']//tr//th[1])[1]")));
			System.out.println("patient 2 id is "+ patient_id2);
			Assert.assertFalse(patient_id.equalsIgnoreCase(patient_id2));
			}
			test.log(LogStatus.INFO,
					"Test:::: Test Release Pending Charges Completed as Pass");
		}
		catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test Release Pending Charges Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test Release Pending Charges Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
	
		}
	
	
	@Test(enabled = false , priority= 6)
	public void TestReleaseAllChargesFromALocation()
	{
		test = extent.startTest(
				"To Test Release All charges from a location", "To Test Release All charges from a location" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add patient with complete Case");
		try
		{
			AddChargeUtils.NavigateToChargeAuditPage(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='auditChargeSummaryGrid']//table//tbody//tr//td[2]//div"), 40);
			String location = driver.findElement(By.xpath("//div[@id='auditChargeSummaryGrid']//table//tbody//tr//td[2]//div")).getText();
			System.out.println("releasing charges of location : "+ location);
			Commons.waitforElement(driver, By.xpath("//div[@id='auditChargeSummaryGrid']//table//tbody//tr//td[1]//input"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='auditChargeSummaryGrid']//table//tbody//tr//td[1]//input")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.id("btn_ReleaseCharges"), 40);
			ActionUtils.click(driver.findElement(By.id("btn_ReleaseCharges")));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			Commons.waitforElement(driver, By.xpath("//div[@id='auditChargeSummaryGrid']//table//tbody//tr//td[2]//div"), 120);
			String location2 = driver.findElement(By.xpath("//div[@id='auditChargeSummaryGrid']//table//tbody//tr//td[2]//div")).getText();
			System.out.println("location 2: "+ location2);
			Assert.assertFalse(location.equalsIgnoreCase(location2));
			test.log(LogStatus.INFO,
					"Test:::: To Test Release All charges from a location Completed as Pass");
		}
		catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: To Test Release All charges from a location Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: To Test Release All charges from a location Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
	
		}
	@Test(enabled = false , priority= 7)
	public void TestReleaseCharges()
	{
		test = extent.startTest(
				"To Test Release Charges", "To Test Release Charges" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add patient with complete Case");
		try
		{
			AddChargeUtils.NavigateToChargeAuditPage(driver);
			Commons.waitforElement(driver, By.xpath("//a[@id='chargeAnchor']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//a[@id='chargeAnchor']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.id("btnCreateReport"), 40);
			ActionUtils.click(driver.findElement(By.id("btnCreateReport")));
			String patient_id = ActionUtils.getText(driver.findElement(By.xpath("//table[@id='auditTable']//tr//th[1]")));
			System.out.println("patient 1 id is "+ patient_id);
			driver.findElement(By.xpath("//table[@id='auditTable']//tr//th[9]/select//option[2]")).click();
			ActionUtils.click(driver.findElement(By.id("btnRelease")));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			String patient_id2 = driver.findElement(By.xpath("//table[@id='auditTable']//tr//th[1]")).getText();
			System.out.println("patient 2 id is "+ patient_id2);
			Assert.assertFalse(patient_id.equalsIgnoreCase(patient_id2));
			test.log(LogStatus.INFO,
					"Test:::: Test Release  Charges Completed as Pass");
		}
		catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test Release  Charges Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test Release  Charges Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
	
		}
	
	
	@Test(enabled = true , priority= 8)
	public void TestChargeswithEvalcodesforDOS12_31_2016andearlier()
	{
		test = extent.startTest(
				"To Test New Charges with Eval codes 97001 & 97003 for DOS 12/31/2016 and earlier", "To Test New Charges with Eval codes 97001 & 97003 for DOS 12/31/2016 and earlier" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add patient with complete Case");
		try
		{
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCodebycode(driver, "a001");
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "12/01/2016");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "12/01/2016");
			test.log(LogStatus.INFO, "Adding CPT Code 97001");
			AddChargeUtils.AddCPTCode(driver, "97001", 1);
			test.log(LogStatus.INFO, "Add new Dx to proc code ");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			test.log(LogStatus.INFO,
					"Test:::: Test New Charges with Eval codes 97001 & 97003 for DOS 12/31/2016 and earlier Completed as PAss");	
		}
		catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test New Charges with Eval codes 97001 & 97003 for DOS 12/31/2016 and earlier Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test New Charges with Eval codes 97001 & 97003 for DOS 12/31/2016 and earlier Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
	}
	
	
	@Test(enabled = true , priority= 9)
	public void TestChargeswithReEvalcodesforDOS12_31_2016andearlier()
	{
		test = extent.startTest(
				"To Test New Charges with ReEval codes 97002 & 97004 for DOS 12/31/2016 and earlier", "To Test New Charges with ReEval codes 97002 & 97004 for DOS 12/31/2016 and earlier" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add patient with complete Case");
		try
		{
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCodebycode(driver, "a001");
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "12/01/2016");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "12/01/2016");
			test.log(LogStatus.INFO, "Adding CPT Code 97002");
			AddChargeUtils.AddCPTCode(driver, "97002", 1);
			test.log(LogStatus.INFO, "Add new Dx to proc code ");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			test.log(LogStatus.INFO,
					"Test:::: Test New Charges with ReEval codes 97002 & 97004 for DOS 12/31/2016 and earlier Completed as PAss");	
		}
		catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test New Charges with ReEval codes 97002 & 97004 for DOS 12/31/2016 and earlier Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test New Charges with ReEval codes 97002 & 97004 for DOS 12/31/2016 and earlier Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
	}
	
	
	@Test(enabled = true , priority= 10)
	public void TestProcCodeReplacementforManuallyenteredchargefor97001forDOS1_1_2017andLATER()
	{
		test = extent.startTest(
				"To Test ProcCode Replacement for Manually entered charge for 97001 for DOS 1/1/2017 and LATER", "To Test ProcCode Replacement for Manually entered charge for 97001 for DOS 1/1/2017 and LATER" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add patient with complete Case");
		try
		{
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCodebycode(driver, "a001");
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "12/01/2017");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2017");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "12/01/2017");
			test.log(LogStatus.INFO, "Adding CPT Code 97001");
			AddChargeUtils.AddCPTCode(driver, "97001", 1);
			test.log(LogStatus.INFO, "Add new Dx to proc code ");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Commons.waitforElement(driver, By.xpath("//div[@id='procReplacementPopup']//div[1]//div[1]//div[3]//div[3]"), 120);
			String message = driver.findElement(By.xpath("//div[@id='procReplacementPopup']//div[1]//div[1]//div[3]//div[3]")).getText();
			Assert.assertTrue(message.contains("Below code is/are not valid for this payer. Please select from one of the valid codes for their replacement."));
			driver.findElement(By.xpath("//button[@data-ng-click='vmCharge.saveProcReplacementCharges()']")).click();
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			test.log(LogStatus.INFO,
					"Test:::: Test ProcCode Replacement for Manually entered charge for 97001 for DOS 1/1/2017 and LATER Completed as PAss");	
		}
		catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test ProcCode Replacement for Manually entered charge for 97001 for DOS 1/1/2017 and LATER Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: Test ProcCode Replacement for Manually entered charge for 97001 for DOS 1/1/2017 and LATER Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
	}
}
