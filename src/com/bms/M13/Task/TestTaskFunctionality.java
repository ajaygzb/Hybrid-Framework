package com.bms.M13.Task;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.LoginPage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.FinancialUtils;
import Utils.SearchPatientUtils;

public class TestTaskFunctionality extends TestSetUp {
	// TC- Add Task Group with no users
	// TC- Adding duplicate users to task group (negative test)
	// TC- Remove a user from a task group
	// TC- Account Notes
	// TC- Alert Dashboard notificaton on login for Incomplete Tasks
	// TC- Editing/view task marked complete
	public static String strActual_text_username = null;
	public static String Text_Expected = null;
	public static String StrActual = null;

	@Test(enabled = true, priority = 1)
	public void AddTaskGroupWithNoUsers() throws InterruptedException {
		test = extent.startTest("Add Task Group with no users", "Test Add Task Group with no users"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
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
		FinancialUtils.NavigatetoTaskgroups(driver);
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewBtn']")));
		// Verify user list must have one active user
		System.out.println("Verify user list must have one active user");
		test.log(LogStatus.INFO, "Verify user list must have one active user");
		Assert.assertTrue(
				driver.findElement(By.xpath("//select[@id='taskGroupNewUserType']//option[@selected='selected']"))
						.getText().contains("Sys Admin"),
				"Failed user list must have one active user");
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//label[contains(.,'Task Group ID')]//following::label[contains(.,'Name (required)')]"))
				.getText().contains("Name (required)"), "Required label missing on Name field");
		System.out.println("Verify error messages");
		test.log(LogStatus.INFO, "Verify error messages");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewSaveBtn']")));
		StrActual = Commons.capturemessage(driver, 10);
		Assert.assertTrue(StrActual.contains("User list must have at least one active user."), "Error in toast text");
		Assert.assertTrue(StrActual.contains("Group Name is required."), "Error in toast text");
		System.out.println("Enter data in the Name text field and click Save again. ");
		test.log(LogStatus.INFO, "Enter data in the Name text field and click Save again.");
		driver.findElement(By.xpath("//input[@id='taskGroupNewName']")).sendKeys("Test Automation");
		System.out.println("Verify error messages after Entering data in name field");
		test.log(LogStatus.INFO, "Verify error messages after Entering data in name field");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewSaveBtn']")));
		StrActual = Commons.capturemessage(driver, 10);
		Assert.assertTrue(StrActual.contains("User list must have at least one active user."), "Error in toast text");
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// TC-02
		test = extent.startTest("Adding duplicate users to task group (negative test)",
				"Test Adding duplicate users to task group (negative test)" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Add User Account");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupSearchUserBtn']")));
		Commons.waitforElement(driver, By.xpath("//*[@id='taskGroupUserSearchPopup']//input[@value='Search']"), 20);
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='taskGroupUserSearchPopup']//input[@value='Search']")));
		Commons.waitforElement(driver, By.xpath("//div[@id='taskGroupUserSearchGrid']//tr[1]//td[3]"), 20);
		ActionUtils.doubleClick(driver,
				driver.findElement(By.xpath("//div[@id='taskGroupUserSearchGrid']//tr[1]//td[3]")));
		ActionUtils
				.click(driver.findElement(By.xpath("//div[@id='taskGroupSearchUserDiv']//button[contains(.,'Add')]")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewSaveBtn']")));
		StrActual = Commons.capturemessage(driver, 80);
		Assert.assertTrue(StrActual.contains("Task Group saved."), "Error in toast text");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewEditBtn']")));
		test.log(LogStatus.INFO, "Add Duplicate User Account");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupSearchUserBtn']")));
		Commons.waitforElement(driver, By.xpath("//*[@id='taskGroupUserSearchPopup']//input[@value='Search']"), 20);
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='taskGroupUserSearchPopup']//input[@value='Search']")));
		Commons.waitforElement(driver, By.xpath("//div[@id='taskGroupUserSearchGrid']//tr[1]//td[3]"), 20);
		ActionUtils.doubleClick(driver,
				driver.findElement(By.xpath("//div[@id='taskGroupUserSearchGrid']//tr[1]//td[3]")));
		ActionUtils
				.click(driver.findElement(By.xpath("//div[@id='taskGroupSearchUserDiv']//button[contains(.,'Add')]")));
		StrActual = Commons.capturemessage(driver, 20);
		Assert.assertTrue(StrActual.contains("Duplicate users can not be added."), "Error in toast text");
	}

	@Test(enabled = false, priority = 2)
	public void RemoveUserFromTaskGroup() throws InterruptedException {
		test = extent.startTest("Remove a user from a task group", "Test Remove a user from a task group"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
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
		FinancialUtils.NavigatetoTaskgroups(driver);
		// search for automation group or create new automation group
		driver.findElement(By.xpath("//input[@id='taskGroupNameInput']")).sendKeys("AutoTask");
		driver.findElement(By.xpath("//button[@id='taskGroupSearchBtn']")).click();
		Commons.waitForLoad(driver);
		if (Commons.ExistandDisplayElement(driver, By.xpath("//td[contains(.,'AutoTask')]"), 10)) {
			System.out.println("Automation Task Group found");
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'AutoTask')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='UserTypeGrid']/table/tbody/tr"), 60);
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id='UserTypeGrid']/table/tbody/tr")).size() == 2,
					"Incorrect no. of users in Auto Task Group");
			Commons.screenshot();
		} else {
			System.out.println("No Automation Task Group found");
			System.out.println("Creating new task group name as : Autotask");
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewBtn']")));
			Commons.waitforElement(driver, By.xpath("//input[@id='taskGroupNewName']"), 10);
			driver.findElement(By.xpath("//input[@id='taskGroupNewName']")).sendKeys("AutoTask");
			// Adding User to group
			test.log(LogStatus.INFO, "Adding User Account-1");
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupSearchUserBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='taskGroupUserSearchPopup']//input[@value='Search']"), 20);
			ActionUtils.sendKeys(
					driver.findElement(By.xpath("//div[@id='taskGroupUserSearchPopup']//input[@id='User_Name']")),
					"QA_BMS");
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='taskGroupUserSearchPopup']//input[@value='Search']")));
			Commons.waitforElement(driver, By.xpath("//div[@id='taskGroupUserSearchGrid']//tr[1]//td[3]"), 20);
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//div[@id='taskGroupUserSearchGrid']//tr[1]//td[3]")));
			ActionUtils.click(
					driver.findElement(By.xpath("//div[@id='taskGroupSearchUserDiv']//button[contains(.,'Add')]")));
			test.log(LogStatus.INFO, "Adding User Account-1");
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupSearchUserBtn']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='taskGroupUserSearchPopup']//input[@value='Search']"), 20);
			ActionUtils.sendKeys(
					driver.findElement(By.xpath("//div[@id='taskGroupUserSearchPopup']//input[@id='User_Name']")),
					"QA_Sysadmin");
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='taskGroupUserSearchPopup']//input[@value='Search']")));
			Commons.waitforElement(driver, By.xpath("//div[@id='taskGroupUserSearchGrid']//tr[1]//td[3]"), 20);
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//div[@id='taskGroupUserSearchGrid']//tr[1]//td[3]")));
			ActionUtils.click(
					driver.findElement(By.xpath("//div[@id='taskGroupSearchUserDiv']//button[contains(.,'Add')]")));
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewSaveBtn']")));
			Commons.capturemessage(driver, 30);
			FinancialUtils.NavigatetoTaskgroups(driver);
			// search for automation group or create new automation group
			driver.findElement(By.xpath("//input[@id='taskGroupNameInput']")).sendKeys("AutoTask");
			driver.findElement(By.xpath("//button[@id='taskGroupSearchBtn']")).click();
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//td[contains(.,'AutoTask')]"), 10);
			System.out.println("Automation Task Group found");
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'AutoTask')]")));
			Commons.waitForLoad(driver);
			Commons.screenshot();
		}
		// Add Task to Automation Task Group
		SearchPatientUtils.QuickpatientSearch(driver, "EMRMedicare");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//i[@id='createTaskIcon']"), 30);
		ActionUtils.click(driver.findElement(By.xpath("//i[@id='createTaskIcon']")));
		Commons.waitforElement(driver, By.xpath("//input[@id='txtDesc_createTask']"), 30);
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='txtDesc_createTask']")));
		driver.findElement(By.xpath("//input[@id='txtDesc_createTask']")).sendKeys("Automation Test Task");
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='radGrp_createTask']")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_SearchTaskGroupcreateTask']")));
		Commons.waitforElement(driver, By.xpath("//*[@id='taskGroupSearchPopupcreateTask']//input[@value='Search']"),
				30);
		driver.findElement(By.xpath("//*[@id='taskGroupSearchPopupcreateTask']//input[@id='SearchGroup_GroupName']"))
				.sendKeys("AutoTask");
		ActionUtils.click(
				driver.findElement(By.xpath("//*[@id='taskGroupSearchPopupcreateTask']//input[@value='Search']")));
		Commons.waitforElement(driver, By.xpath("//td[contains(.,'AutoTask')]"), 20);
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'AutoTask')]")));
		Commons.ExistandDisplayElement(driver, By.xpath("//textarea[@id='txtArComment_createTask']"), 20);
		driver.findElement(By.xpath("//textarea[@id='txtArComment_createTask']")).sendKeys("Test Automation task");
		ActionUtils.click(driver.findElement(By.xpath("//select[@id='drpdnTaskType_createTask']//option[2]")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_createTask']")));
		Commons.capturemessage(driver, 60);
		// Navigate to Dash board to verify task is added to user
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Dashboard')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Dashboard')]")));
		Commons.waitforElement(driver, By.xpath("//a[@href='#task']"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#task']")));
		Commons.waitforElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"), 60);
		Commons.screenshot();
		// Remove user from group and verify task no longer available to that
		// user
		FinancialUtils.NavigatetoTaskgroups(driver);
		// search for automation group or create new automation group
		driver.findElement(By.xpath("//input[@id='taskGroupNameInput']")).sendKeys("AutoTask");
		driver.findElement(By.xpath("//button[@id='taskGroupSearchBtn']")).click();
		Commons.waitForLoad(driver);
		Commons.ExistandDisplayElement(driver, By.xpath("//td[contains(.,'AutoTask')]"), 10);
		System.out.println("Automation Task Group found");
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'AutoTask')]")));
		Commons.waitForLoad(driver);
		Commons.screenshot();
		// Remove user QA_sysadmin
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewEditBtn']")));
		Commons.screenshot();
		Assert.assertEquals(driver.findElements(By.xpath("//*[@id='UserTypeGrid']/table/tbody/tr")).size(), 2,
				"Incorrect No of users in Autotask group");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='UserTypeGrid']/table/tbody/tr[2]/td[3]/input")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewDeleteBtn']")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewSaveBtn']")));
		Commons.capturemessage(driver, 60);
		Commons.screenshot();
		// Navigate to Dash board to verify task is removed to user
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Dashboard')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Dashboard')]")));
		Commons.waitforElement(driver, By.xpath("//a[@href='#task']"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#task']")));
		Assert.assertTrue(Commons.ExistandDisplayElement(driver, By.xpath("//p[contains(.,'No Record Found')]"), 60));
		Commons.screenshot();
		// Re-set the Automation Task Group user list
		// test.log(LogStatus.INFO, "Adding User Account-1");
		FinancialUtils.NavigatetoTaskgroups(driver);
		// search for automation group or create new automation group
		driver.findElement(By.xpath("//input[@id='taskGroupNameInput']")).sendKeys("AutoTask");
		driver.findElement(By.xpath("//button[@id='taskGroupSearchBtn']")).click();
		Commons.waitForLoad(driver);
		Commons.ExistandDisplayElement(driver, By.xpath("//td[contains(.,'AutoTask')]"), 10);
		System.out.println("Automation Task Group found");
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'AutoTask')]")));
		Commons.waitForLoad(driver);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewEditBtn']")));
		Commons.waitforElement(driver, By.xpath("//button[@id='taskGroupSearchUserBtn']"), 20);
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupSearchUserBtn']")));
		Commons.waitforElement(driver, By.xpath("//*[@id='taskGroupUserSearchPopup']//input[@value='Search']"), 20);
		ActionUtils.sendKeys(
				driver.findElement(By.xpath("//div[@id='taskGroupUserSearchPopup']//input[@id='User_Name']")),
				"QA_Sysadmin");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='taskGroupUserSearchPopup']//input[@value='Search']")));
		Commons.waitforElement(driver, By.xpath("//div[@id='taskGroupUserSearchGrid']//tr[1]//td[3]"), 20);
		ActionUtils.doubleClick(driver,
				driver.findElement(By.xpath("//div[@id='taskGroupUserSearchGrid']//tr[1]//td[3]")));
		ActionUtils
				.click(driver.findElement(By.xpath("//div[@id='taskGroupSearchUserDiv']//button[contains(.,'Add')]")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskGroupNewSaveBtn']")));
		Commons.capturemessage(driver, 30);
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		test = extent.startTest("To verify Alert Dashboard notificaton on login for Incomplete Tasks",
				"Test To verify Alert Dashboard notificaton on login for Incomplete Tasks"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		test.log(LogStatus.INFO, "Login as user Sysadmin");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userSysadmin"),
				Commons.readPropertyValue("password"));
		Assert.assertTrue(
				Commons.ExistandDisplayElement(driver,
						By.xpath("//*[@id='navbar']//span[contains(@ng-if,'TaskCount')]"), 60),
				"Could not verify Task count on Dash board");
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// TC-02
		test = extent.startTest("Editing/view task marked complete", "Test Editing/view task marked complete"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// Mark Task as completed
		// Navigate to Dash board to verify task is added to user
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Dashboard')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Dashboard')]")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//a[@href='#task']"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#task']")));
		Commons.ExistandDisplayElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"), 60);
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'Automation Test Task')]")));
		Commons.waitForLoad(driver);
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='editBtn_dashnoard_Task']")));
		ActionUtils.click(
				driver.findElement(By.xpath("//*[@id='drpdwnStatus_dashnoard_Task']//option[@label='Complete']")));
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
		Commons.capturemessage(driver, 60);
		Commons.ExistandDisplayElement(driver, By.xpath("//p[contains(.,'No Record Found')]"), 60);
		Commons.screenshot();
	}

	@Test(enabled = false, priority = 3)
	public void CreateTaskAccountNotes() throws InterruptedException {
		test = extent.startTest("To verify Tasks assigned to patient accounts that have merged, should also merge. ",
				"Test To verify Tasks assigned to patient accounts that have merged, should also merge. "
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// Add Task to Automation Task Group
		// SearchPatientUtils.QuickpatientSearch(driver,"EMRMedicare");
		AddPatientUtils.QuickAddpatient(driver);
		Commons.waitForLoad(driver);
		String PID = ActionUtils.getText(AddPatientPage.patientID(driver));
		Commons.waitforElement(driver, By.xpath("//i[@id='createTaskIcon']"), 30);
		ActionUtils.click(driver.findElement(By.xpath("//i[@id='createTaskIcon']")));
		Commons.waitforElement(driver, By.xpath("//input[@id='txtDesc_createTask']"), 30);
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='txtDesc_createTask']")));
		driver.findElement(By.xpath("//input[@id='txtDesc_createTask']")).sendKeys("Automation Test Task");
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='radGrp_createTask']")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_SearchTaskGroupcreateTask']")));
		Commons.waitforElement(driver, By.xpath("//*[@id='taskGroupSearchPopupcreateTask']//input[@value='Search']"),
				30);
		driver.findElement(By.xpath("//*[@id='taskGroupSearchPopupcreateTask']//input[@id='SearchGroup_GroupName']"))
				.sendKeys("AutoTask");
		ActionUtils.click(
				driver.findElement(By.xpath("//*[@id='taskGroupSearchPopupcreateTask']//input[@value='Search']")));
		Commons.waitforElement(driver, By.xpath("//td[contains(.,'AutoTask')]"), 20);
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'AutoTask')]")));
		Commons.ExistandDisplayElement(driver, By.xpath("//textarea[@id='txtArComment_createTask']"), 20);
		driver.findElement(By.xpath("//textarea[@id='txtArComment_createTask']")).sendKeys("Test Automation task");
		ActionUtils.click(driver.findElement(By.xpath("//select[@id='drpdnTaskType_createTask']//option[2]")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_createTask']")));
		Commons.capturemessage(driver, 60);
		// Navigate to patient AccountNotes
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userSysadmin"),
				Commons.readPropertyValue("password"));
		SearchPatientUtils.QuickpatientSearch(driver, PID);
		System.out.println("Navigate to patient AccountNotes");
		Commons.waitForLoad(driver);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#AccountNotes']")));
		Commons.ExistandDisplayElement(driver, By.xpath("//a[contains(.,' Account Notes')]"), 70);
		System.out.println("Verify patient task appeared in AccountNotes as incompleted");
		Commons.waitforElement(driver, By.xpath("//*[@id='gridAccountNote']/table/tbody/tr[1]//td[9]"), 70);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='gridAccountNote']/table/tbody/tr[1]//td[9]")).getText()
				.contains("Incomplete"), "Could not verify Task appeared in Account notes");
		// Navigate to Dash board to verify task is added to user
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Dashboard')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Dashboard')]")));
		Commons.waitforElement(driver, By.xpath("//a[@href='#task']"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#task']")));
		Commons.waitforElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"), 60);
		Commons.screenshot();
		// Mark Task as completed
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'Automation Test Task')]")));
		Commons.waitForLoad(driver);
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='editBtn_dashnoard_Task']")));
		ActionUtils.click(
				driver.findElement(By.xpath("//*[@id='drpdwnStatus_dashnoard_Task']//option[@label='Complete']")));
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
		Commons.capturemessage(driver, 60);
		Commons.ExistandDisplayElement(driver, By.xpath("//p[contains(.,'No Record Found')]"), 60);
		Commons.screenshot();
	}
}