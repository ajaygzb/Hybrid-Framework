package com.bms.M6.Scheduler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;

public class TestEditEditAllPersonSchedule extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void testEditEditAllPersonScheduleFlagIsSelected() {
		test = extent.startTest("testing user is allowed to edit all persons schedule",
				"test Edit Edit All Person Schedule Flag Is Selected" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			System.out.println("testing user is allowed to edit all persons schedule  ");
			test.log(LogStatus.INFO, "navigating to scheduler ");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			/*
			 * Commons.waitforElement(driver, By.xpath(
			 * "//select[@ng-click ='vmScheduler.updateDropDownPatient()']"),
			 * 200); Select dropdown = new Select (driver.findElement(By.xpath(
			 * "//select[@ng-click ='vmScheduler.updateDropDownPatient()']")));
			 * dropdown.selectByVisibleText(Commons.readPropertyValue(
			 * "userprovideravail"));
			 */
			AddScheduleUtils.SelectViewBy(driver, "Provider View", Commons.readPropertyValue("userprovideravail"));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ProviderAvailabilityButton(driver));
			Commons.waitforElement(driver, By.id("providerHoursDropDown"), 120);
			Select dropdown = new Select(driver.findElement(By.id("providerHoursDropDown")));
			dropdown = new Select(driver.findElement(By.id("providerHoursDropDown")));
			dropdown.selectByVisibleText(Commons.readPropertyValue("userprovideravail"));
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver, driver.findElement(By.id("providerHoursMainedit")), 90);
			ActionUtils.click(driver.findElement(By.id("providerHoursMainedit")));
			Commons.waitforElement(driver,
					By.xpath("//button[@ng-click='vmProviderAvailability.addProviderAvailabilityGrid()']"), 90);
			ActionUtils.click(driver.findElement(
					By.xpath("//button[@ng-click='vmProviderAvailability.addProviderAvailabilityGrid()']")));
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate localDate = LocalDate.now();
			System.out.println(dtf.format(localDate));
			Commons.waitForLoad(driver);
			ActionUtils.sendKeys(SchedulerPage.availableHoursStart(driver), dtf.format(localDate));
			Commons.waitForLoad(driver);
			ActionUtils.sendKeys(SchedulerPage.availableHoursEnd(driver), dtf.format(localDate));
			ActionUtils.click(driver.findElement(By
					.xpath("//input[@ng-model=' vmProviderAvailability.providerAvailabiltyDataScheduler.isMonday']")));
			List<WebElement> datelist = driver.findElements(By.xpath(
					"//ng-form[@name='providerAvailabilityInfoForm']//button[@class='btn btn-default dropdown-toggle']"));
			ActionUtils.click(datelist.get(0));
			ActionUtils.click(
					driver.findElement(By.xpath("//table[@id='startTimePicker']//a[@ng-click='incrementHours()']")));
			ActionUtils.click(datelist.get(1));
			ActionUtils.click(
					driver.findElement(By.xpath("//table[@id='endTimePicker']//a[@ng-click='incrementHours()']")));
			ActionUtils.click(
					driver.findElement(By.xpath("//table[@id='endTimePicker']//a[@ng-click='incrementHours()']")));
			dropdown = new Select(driver.findElement(By.id("availableHoursLocationScheduler")));
			dropdown.selectByVisibleText("Automation BMS");
			driver.findElement(By.id("saveAvailableData")).click();
			Commons.waitforElement(driver, By.xpath("//table[@data-role='selectable']//p"), 90);
			String result = driver.findElement(By.xpath("//table[@data-role='selectable']//p")).getText();
			Assert.assertEquals(result, "Automation BMS");
			ActionUtils.click(driver.findElement(
					By.xpath("//input[@ng-keydown='vmProviderAvailability.focusCheckBoxAvailable($event)']")));
			ActionUtils.click(driver.findElement(By.id("inactivateSelectedA")));
			test.log(LogStatus.INFO, "Test:::: testEditAllPersonScheduleFlagIsSelected Completed as pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testEditEditAllPersonScheduleFlagIsSelected Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testEditEditAllPersonScheduleFlagIsSelected Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void testEditAllPersonScheduleFlagIsNotSelected() {
		test = extent.startTest("testing user is not allowed to edit all persons schedule",
				"test Edit Edit All Person Schedule Flag Is not Selected" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			System.out.println("login with user which have EditAllPersonSchedule flag uncheck  ");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userprovideravail"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "navigating to scheduler ");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			/*
			 * Commons.waitforElement(driver, By.xpath(
			 * "//select[@ng-click ='vmScheduler.updateDropDownPatient()']"),
			 * 200); Select dropdown = new Select (driver.findElement(By.xpath(
			 * "//select[@ng-click ='vmScheduler.updateDropDownPatient()']")));
			 * //dropdown.selectByVisibleText(Commons.readPropertyValue(
			 * "username")); dropdown.selectByIndex(1);
			 */
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ProviderAvailabilityButton(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(4000);
			Commons.waitforElement(driver, By.xpath("//div[@id='ViewSchedule']//button[contains(text(),'OK')]"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='ViewSchedule']//button[contains(text(),'OK')]")));
			test.log(LogStatus.INFO, "Test:::: testEditAllPersonScheduleFlagIsNotSelected Completed as pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testEditAllPersonScheduleFlagIsNotSelected Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testEditAllPersonScheduleFlagIsNotSelected Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
