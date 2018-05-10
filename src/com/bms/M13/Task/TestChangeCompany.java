package com.bms.M13.Task;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.ChangeCompany;
import UIMap.LoginPage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.ChangeCompanyUtils;
import Utils.Commons;
import Utils.SearchPatientUtils;

public class TestChangeCompany extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void ToValidatePatientIsAssociatedWithOneCompany() throws InterruptedException {
		test = extent.startTest("To verify patient created in one company is not associated with other company",
				"To Validate Patient Is Associated With One Company" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text_Toast = null;
		strActual_text_Toast = AddPatientUtils.QuickAddpatient(driver);
		System.out.println("Toast text in app" + "  " + strActual_text_Toast);
		String patientId = ActionUtils.getText(AddPatientPage.patientID(driver));
		String patientheader = ActionUtils.getText(AddPatientPage.PatientNameInHeader(driver));
		String patientName[] = patientheader.split(" ");
		System.out.println("changing company to zzw");
		test.log(LogStatus.INFO, "changing company to zzw");
		ChangeCompanyUtils.changeCompany(driver, Commons.readPropertyValue("company1"));
		System.out.println("searching for patientid " + patientId + " and first name is " + patientName[0]
				+ " and last name is " + patientName[1]);
		ActionUtils.click(AddPatientPage.PatientMenu(driver));
		ActionUtils.click(AddPatientPage.patientSearchSubmenu(driver));
		System.out.println("Clicked on patient search button");
		AddPatientPage.patient_search_lastname(driver).clear();
		ActionUtils.sendKeys(AddPatientPage.patient_search_ID(driver), patientId);
		ActionUtils.sendKeys(AddPatientPage.patient_search_firstname(driver), patientName[0]);
		ActionUtils.sendKeys(AddPatientPage.patient_search_lastname(driver), patientName[1]);
		ActionUtils.click(AddPatientPage.patient_search__green_btn(driver));
		try {
			Assert.assertNotNull(driver.findElement(By.xpath("//p[contains(text(),'No Patient Found')]")));
			System.out.println(
					"patient" + patientId + "is not linked with " + ChangeCompany.companyName(driver).getText());
			test.log(LogStatus.INFO,
					"To verify patient created in one company is not associated with other companyis pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void ProviderLinkwithDifferentCompanies() throws InterruptedException {
		test = extent.startTest("To verify login user is linked as provider in other companies ",
				"To Validate login user is linked as provider in other companies" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		System.out.println("chnaging company in which user is a provider");
		test.log(LogStatus.INFO, "chnaging company in which user is a provider");
		ChangeCompanyUtils.changeCompany(driver, Commons.readPropertyValue("company1"));
		test.log(LogStatus.INFO, "company is changed");
		try {
			Assert.assertTrue(driver.getCurrentUrl().toString().contains("provider"));
			Commons.ExistandDisplayElement(driver, By.xpath("//b[contains(text(),'Appointment')]"), 90);
			Assert.assertTrue(driver.findElement(By.xpath("//b[contains(text(),'Appointment')]")).isDisplayed());
			System.out.println("got provider home page , hence user is a provider in this company :"
					+ ChangeCompany.companyName(driver).getText());
		} catch (AssertionError e) {
			System.out.println("unable to get provider home page ");
			test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
			// Assert.assertFalse(true,Throwables.getStackTraceAsString(e));
		}
		System.out.println("changing company in which user is a non provider");
		test.log(LogStatus.INFO, "changing company in which user is a non provider");
		ChangeCompanyUtils.changeCompany(driver, Commons.readPropertyValue("company2"));
		test.log(LogStatus.INFO, "company is changed");
		Commons.waitForLoad(driver);
		try {
			Assert.assertTrue(driver.getCurrentUrl().toString().contains("non-provider"));
			Assert.assertTrue(
					driver.findElement(By.xpath("//img[@src='/content/images/LogoProvider.png']")).isDisplayed());
			System.out.println("Did not get provider home page , hence user is not a provider in this company : "
					+ ChangeCompany.companyName(driver).getText());
		} catch (AssertionError e) {
			System.out.println("unable to get provider home page ");
			test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
