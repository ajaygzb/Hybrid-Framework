package com.bms.M13.Task;

import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import ReportUtils.ExtentManager;
import TestBase.TestSetUp;
import UIMap.LoginPage;
import Utils.ActionUtils;
import Utils.Commons;
import Utils.FinancialUtils;

public class TestEditGlobaltask extends TestSetUp {
	// TC- EDIT Global task type to make Unavailable
	// TC- EDIT task type to reenable an Unavailable Task Type
	public static String strActual_text_username = null;
	public static String Text_Expected = null;
	public static String StrActual = null;
	public static int Global_Task_Count;
	public static int Global_Task_Count_after;

	@Test(enabled = true, priority = 1)
	public void MakeGlobaltaskIsUnavailable() throws InterruptedException {
		test = extent.startTest("EDIT Global task type to make Unavailable",
				"Test EDIT Global task type to make Unavailable" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user Sysadmin");
		test.log(LogStatus.INFO, "Login as user Sysadmin");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userSysadmin"),
				Commons.readPropertyValue("password"));
		strActual_text_username = null;
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userSysadmin");
		System.out.println("go to Admin > Task Management > Task Groups");
		test.log(LogStatus.INFO, "go to Admin > Task Management > Task");
		FinancialUtils.NavigatetoTaskTypes(driver);
		System.out.println("Click on show global checkbox");
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='showGlobalChkBx']")));
		System.out.println("Click on search button");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskTypeSearchBtn']")));
		Commons.waitForLoad(driver);
		if (Commons.ExistandDisplayElement(driver, By.xpath("//input[@checked='checked']"), 20)) {
			System.out.println("Search result loaded");
			Global_Task_Count = driver.findElements(By.xpath("//input[@checked='checked']")).size();
			System.out.println("Available Global task" + Global_Task_Count);
		} else {
			System.out.println("No Global Task found hence creating a new global task as available");
			Commons.waitforElement(driver, By.xpath("//button[@id='taskTypeNewBtn']"), 10);
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskTypeNewBtn']")));
			System.out.println("Clicked on New button");
			Commons.waitforElement(driver, By.xpath("//input[@id='newDescriptionTxtBx']"), 10);
			driver.findElement(By.xpath("//input[@id='newDescriptionTxtBx']")).click();
			driver.findElement(By.xpath("//input[@id='newDescriptionTxtBx']")).sendKeys("Automation task");
			driver.findElement(By.xpath(".//*[@id='taskTypeSaveBtn']")).click();
			Commons.capturemessage(driver, 30);
			FinancialUtils.NavigatetoTaskTypes(driver);
			System.out.println("Click on show global checkbox");
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='showGlobalChkBx']")));
			System.out.println("Click on search button");
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskTypeSearchBtn']")));
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//input[@checked='checked']"), 20);
			System.out.println("Search result loaded");
			Global_Task_Count = driver.findElements(By.xpath("//input[@checked='checked']")).size();
			System.out.println("Available Global task" + Global_Task_Count);
		}
		System.out.println("Make Global Task as Un-available");
		int i = 0;
		do {
			Commons.ExistandDisplayElement(driver, By.xpath("(//input[@checked='checked'])[1]//preceding::td[1]"), 30);
			StrActual = ActionUtils
					.getText(driver.findElement(By.xpath("(//input[@checked='checked'])[1]//preceding::td[1]")));
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("(//input[@checked='checked'])[1]//preceding::td[1]")));
		} while (!Commons.ExistandDisplayElement(driver, By.xpath("//button[@id='taskTypeEditBtn']"), 20) && i < 3);
		System.out.println("Click on task edit button");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskTypeEditBtn']")));
		System.out.println("Uncheck available check box. ");
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='isAvailableChkBx']")));
		Commons.screenshot();
		// Renaming the Task name to avoid duplicacy
		driver.findElement(By.xpath("//*[@id='newDescriptionTxtBx']")).sendKeys(UUID.randomUUID().toString());
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskTypeSaveBtn']")));
		Commons.capturemessage(driver, 20);
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskTypeViewSearchBtn']")));
		FinancialUtils.NavigatetoTaskTypes(driver);
		System.out.println("Click on show global checkbox");
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='showGlobalChkBx']")));
		System.out.println("Click on search button");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskTypeSearchBtn']")));
		Commons.waitForLoad(driver);
		if (Commons.ExistandDisplayElement(driver, By.xpath("//input[@checked='checked']"), 20)) {
			System.out.println("Search result loaded");
			Global_Task_Count_after = driver.findElements(By.xpath("//input[@checked='checked']")).size();
			System.out.println("Available Global task after edit " + Global_Task_Count_after);
			System.out.println(Global_Task_Count_after + "   " + Global_Task_Count);
			Assert.assertTrue(Global_Task_Count_after < Global_Task_Count);
		}
		/*
		 * //Make global task again available
		 * ActionUtils.sendKeys(driver.findElement(By.xpath(
		 * "//input[@id='descriptionTxtBx']")),StrActual);
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//input[@id='showNonAvailableChkBx']"))); System.out.println(
		 * "Click on search button");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//button[@id='taskTypeSearchBtn']")));
		 * Commons.ExistandDisplayElement(driver, By.xpath(
		 * "(//*[@id='taskTypeGrid']//input[not(@checked='checked')])[1]//preceding::td[1]"
		 * ),30); ActionUtils.doubleClick(driver, driver.findElement(By.xpath(
		 * "(//*[@id='taskTypeGrid']//input[not(@checked='checked')])[1]//preceding::td[1]"
		 * ))); System.out.println("Click on task edit button");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//button[@id='taskTypeEditBtn']"))); System.out.println(
		 * "check available check box. ");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//input[@id='isAvailableChkBx']"))); Commons.screenshot();
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//button[@id='taskTypeSaveBtn']")));
		 * Commons.capturemessage(driver,30);
		 */
	}

	@Test(enabled = true, dependsOnMethods = { "MakeGlobaltaskIsUnavailable" })
	public void MakeGlobaltaskIsAvailable() throws InterruptedException {
		test = extent.startTest("EDIT task type to re-enable an Unavailable Task Type",
				"Test EDIT task type to re-enable an Unavailable Task Type" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user Sysadmin");
		test.log(LogStatus.INFO, "Login as user Sysadmin");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userSysadmin"),
				Commons.readPropertyValue("password"));
		strActual_text_username = null;
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userSysadmin");
		System.out.println("go to Admin > Task Management > Task Groups");
		test.log(LogStatus.INFO, "go to Admin > Task Management > Task");
		FinancialUtils.NavigatetoTaskTypes(driver);
		// Make global task again available
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='descriptionTxtBx']")), StrActual);
		System.out.println("Click on show global checkbox");
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='showGlobalChkBx']")));
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='showNonAvailableChkBx']")));
		System.out.println("Click on search button");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskTypeSearchBtn']")));
		Commons.ExistandDisplayElement(driver,
				By.xpath("(//*[@id='taskTypeGrid']//input[not(@checked='checked')])[1]//preceding::td[1]"), 30);
		Commons.screenshot();
		ActionUtils.doubleClick(driver, driver.findElement(
				By.xpath("(//*[@id='taskTypeGrid']//input[not(@checked='checked')])[1]//preceding::td[1]")));
		System.out.println("Click on task edit button");
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskTypeEditBtn']")));
		System.out.println("check available check box. ");
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='isAvailableChkBx']")));
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskTypeSaveBtn']")));
		Commons.capturemessage(driver, 30);
		/*
		 * ActionUtils.sendKeys(driver.findElement(By.xpath(
		 * "//input[@id='descriptionTxtBx']")), "Automation");
		 * System.out.println("Click on show unavailable checkbox");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//input[@id='showNonAvailableChkBx']"))); System.out.println(
		 * "Click on search button");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//button[@id='taskTypeSearchBtn']"))); Commons.waitForLoad(driver);
		 * if(Commons.ExistandDisplayElement(driver,
		 * By.xpath("//*[@id='taskTypeGrid']//input[not(@checked='checked')]"),
		 * 20)){ System.out.println("Search result loaded"); Global_Task_Count =
		 * driver.findElements(By.xpath(
		 * "//*[@id='taskTypeGrid']//input[not(@checked='checked')]")).size();
		 * System.out.println("UnAvailable task"+Global_Task_Count); }else{
		 * System.out.println(
		 * "No Unavailable Task found hence creating a new global task as available"
		 * ); Commons.waitforElement(driver,
		 * By.xpath("//button[@id='taskTypeNewBtn']"), 10);
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//button[@id='taskTypeNewBtn']"))); System.out.println(
		 * "Clicked on New button"); Commons.waitforElement(driver,
		 * By.xpath("//input[@id='newDescriptionTxtBx']"), 10);
		 * driver.findElement(By.xpath("//input[@id='newDescriptionTxtBx']")).
		 * click();
		 * driver.findElement(By.xpath("//input[@id='newDescriptionTxtBx']")).
		 * sendKeys("Automation task");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//input[@id='isAvailableChkBx']")));
		 * driver.findElement(By.xpath(".//*[@id='taskTypeSaveBtn']")).click();
		 * Commons.capturemessage(driver, 30);
		 * FinancialUtils.NavigatetoTaskTypes(driver); System.out.println(
		 * "Click on show unavailable checkbox");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//input[@id='showNonAvailableChkBx']"))); System.out.println(
		 * "Click on search button");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//button[@id='taskTypeSearchBtn']"))); Commons.waitForLoad(driver);
		 * Commons.ExistandDisplayElement(driver,
		 * By.xpath("//*[@id='taskTypeGrid']//input[not(@checked='checked')]"),
		 * 20); System.out.println("Search result loaded"); Global_Task_Count =
		 * driver.findElements(By.xpath(
		 * "//*[@id='taskTypeGrid']//input[not(@checked='checked')]")).size();
		 * System.out.println("Available Global task"+Global_Task_Count); }
		 * System.out.println("Make  Task as available"); int i = 0; do{
		 * Commons.ExistandDisplayElement(driver, By.xpath(
		 * "(//*[@id='taskTypeGrid']//input[not(@checked='checked')])[1]//preceding::td[1]"
		 * ),30); ActionUtils.doubleClick(driver, driver.findElement(By.xpath(
		 * "(//*[@id='taskTypeGrid']//input[not(@checked='checked')])[1]//preceding::td[1]"
		 * ))); }while(!Commons.ExistandDisplayElement(driver,
		 * By.xpath("//button[@id='taskTypeEditBtn']"), 20)&& i<3);
		 * System.out.println("Click on task edit button");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//button[@id='taskTypeEditBtn']"))); System.out.println(
		 * "check available check box. ");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//input[@id='isAvailableChkBx']"))); Commons.screenshot();
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//button[@id='taskTypeSaveBtn']"))); Commons.capturemessage(driver,
		 * 20); ActionUtils.click(driver.findElement(By.xpath(
		 * "//button[@id='taskTypeViewSearchBtn']")));
		 * FinancialUtils.NavigatetoTaskTypes(driver); System.out.println(
		 * "Click on show unavailable checkbox");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//input[@id='showNonAvailableChkBx']"))); System.out.println(
		 * "Click on search button");
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//button[@id='taskTypeSearchBtn']"))); Commons.waitForLoad(driver);
		 * if(Commons.ExistandDisplayElement(driver,
		 * By.xpath("//*[@id='taskTypeGrid']//input[not(@checked='checked')]"),
		 * 20)){ System.out.println("Search result loaded");
		 * Global_Task_Count_after = driver.findElements(By.xpath(
		 * "//*[@id='taskTypeGrid']//input[not(@checked='checked')]")).size();
		 * System.out.println("Available Global task after edit "
		 * +Global_Task_Count_after);
		 * System.out.println(Global_Task_Count_after+"   "+Global_Task_Count);
		 * Assert.assertTrue(Global_Task_Count_after<Global_Task_Count); }
		 */
	}
}
