package com.bms.M15.ArrivalAlert;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.TestSetUp;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.PolicyChargeUtils;

import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestPatientView extends TestSetUp{
	@Test(enabled = true, priority = 1)
	public void TestSttausShouldBeUpdatedFromListView() {
		test = extent.startTest(
				"To Verify Status should be updated for appointment when select canceled from list view window.",
				"TestStatus should be updated for appointment when select canceled from list view window." + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider2"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "adding patient ");
			AddPatientUtils.QuickAddpatient(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[1] + ", " + namesplit[0];
			System.out.println("new patient added is :" + newName);
			test.log(LogStatus.INFO, "new patient added is :" + newName);
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Thread.sleep(3000);
			// selecting location view
			
			for(int i=0 ; i<3 ;i++)
			{
			System.out.println("creating appointmnet of patient :" + newName);
			test.log(LogStatus.INFO, "creating appointmnet of patient :" + newName);
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			Thread.sleep(1000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For schedule Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			}
			Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode']"), 120);
			Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown-view-mode']")));
			dropdown.selectByVisibleText("Patient View");
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode-value']"), 120);
			Thread.sleep(1000);
			dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown-view-mode-value']")));
			dropdown.selectByIndex(1);
			Commons.waitForLoad(driver);
			Thread.sleep(1000);
			System.out.println("open patient view of patient :" + newName);
			test.log(LogStatus.INFO, "open patient view of patient :" + newName);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath("//li[@id='ListViewli']/a")));
		
			Commons.waitForElementToBeClickable(driver, driver.findElement(By.id("cancelAppointments")), 70);
			ActionUtils.click(driver.findElement(By.id("cancelAppointments")));
			Commons.waitforElement(driver, By.id("patientAppointmentCancelCodeDropDown"), 120);
			Select dropdown2 = new Select(driver.findElement(By.id("patientAppointmentCancelCodeDropDown")));
			dropdown2.selectByIndex(1);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.id("ListViewOkButton")));
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath("//li[@id='ListViewli']/a")));
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			List<WebElement> appointments = driver.findElements(By.xpath("//div[@id='gridSearchModel']//tbody//tr//td[8]"));
			for(WebElement currentAppointment :appointments )
			{
				Assert.assertTrue(currentAppointment.getText().equalsIgnoreCase("Cancel"));
			}
			test.log(LogStatus.INFO,
					"Status should be updated for appointment when select canceled from list view window. test case is pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Status should be updated for appointment when select canceled from list view window. Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Status should be updated for appointment when select canceled from list view window. Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

}
