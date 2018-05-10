package PerformanceTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import TestBase.TestSetUp;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.Commons;
import Utils.SearchPatientUtils;

public class TestEditFunctionality extends TestSetUp {
	
	
	@Test(enabled = true, priority = 1)
	public void EditPatient() throws InterruptedException {
		
		test = extent.startTest("Edit existing patient, enter data save.","Edit existing patient, enter data save."+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		
		//Open existing patient
		SearchPatientUtils.SearchRandomPatient(driver, "EMRMedicare");
		// Edit Patient Name and Save
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Edit Patient')]")));
		driver.findElement(By.xpath("//input[@id='patientRegistrationFirstName']")).click();
		driver.findElement(By.xpath("//input[@id='patientRegistrationFirstName']")).clear();
		driver.findElement(By.xpath("//input[@id='patientRegistrationFirstName']")).sendKeys("NameEdit");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='savePatientDetailsButton']")));
		Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Patient details updated successfully."));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='patientRegistrationFirstName']")).getAttribute("value").contains("NameEdit"));
		Commons.screenshot();
		extent.endTest(test);
		
		// Case Edit
		test = extent.startTest("Edit existing Case, enter data save.","Edit existing Case, enter data save."+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		AddCaseUtils.GoToCaseList(driver);		
		//OPenExistingCase
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//*[@id='gridCaseDetails']//tr[1]//td[4]")));
	    Commons.waitforElement(driver, By.xpath("//button[@id='editCaseDetailsButton']"),80);
	    ActionUtils.click(driver.findElement(By.xpath("//button[@id='editCaseDetailsButton']")));
		driver.findElement(By.xpath("//input[@id='caseNameTxt']")).click();
		driver.findElement(By.xpath("//input[@id='caseNameTxt']")).clear();
		driver.findElement(By.xpath("//input[@id='caseNameTxt']")).sendKeys("CaseEdit");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveCaseDetailsButton']")));
		Commons.capturemessage(driver, 60);	
		if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='rebillPopup']//button[contains(.,'Yes')]"),
				5)) {
			System.out.println("Rebill pop up appeared");
			Commons.screenshot();
			driver.findElement(By.xpath("//*[@id='rebillPopup']//button[contains(.,'Yes')]")).click();
			Commons.capturemessage(driver, 60);	
		}
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='caseNameTxt']")).getAttribute("value").contains("CaseEdit"));
		Commons.screenshot();
		extent.endTest(test);

	}

		
	}
	









	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


