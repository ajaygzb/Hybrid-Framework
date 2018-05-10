package com.bms.M13.Task;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.LoginPage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.HandlingCalendars;

public class TestAddTaskByRole extends TestSetUp {
	public static String PID;

	// TC- ADD task as SYS Admin user type
	// TC- ADD task as BMS user type
	// TC- ADD task as Customer user type with single company
	// TC- EDIT task
	// TC- Required Field task
	@Test(enabled = true, priority = 1)
	public void CreateTaskUserSysAdmin() throws InterruptedException {
		test = extent.startTest("ADD task as SYS Admin user type", "Test ADD task as SYS Admin user type"
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
		AddPatientUtils.QuickAddpatient(driver);
		Commons.waitForLoad(driver);
		PID = ActionUtils.getText(AddPatientPage.patientID(driver));
		// Navigate to Dash board and click on Create new task button
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Dashboard')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Dashboard')]")));
		Commons.waitforElement(driver, By.xpath("//a[@href='#task']"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#task']")));
		Commons.waitforElement(driver, By.xpath("//button[@id='taskNewBtn']"), 90);
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskNewBtn']")));
		// Assert New Task Window
		Commons.waitforElement(driver, By.xpath("//h4[contains(.,'Task')]"), 60);
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Company (required)')]")).getText()
				.contains("Company (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Description (required)')]")).getText()
				.contains("Description (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Assign To (required)')]")).getText()
				.contains("Assign To (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Task Type (required)')]")).getText()
				.contains("Task Type (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Status (required)')]")).getText()
				.contains("Status (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Individual (required)')]")).getText()
				.contains("Individual (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Date Due (required)')]")).getText()
				.contains("Date Due (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Status (required)')]")).getText()
				.contains("Status (required)"));
		System.out.println(driver.findElement(By.xpath("//label[contains(.,'Comment')]")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//button[contains(.,'Assign to Me')]")).getText()
				.contains("Assign to Me"));
		Commons.screenshot();
		// Enter Task details Click on task Save button
		ActionUtils.click(driver.findElement(By.xpath("//input[@name='taskDescription']")));
		driver.findElement(By.xpath("//input[@name='taskDescription']")).sendKeys("Automation Test Task");
		driver.findElement(By.xpath("//textarea[@id='txtArComment_dashnoard_Task']")).sendKeys("Test Automation task");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdnTaskType_dashnoard_Task']/option[3]")));
		ActionUtils.click(driver.findElement(By
				.xpath("//div[@id='taskpopupModalFixedBodydashnoard_Task']//div[@id='divPatientAutoSearch']//input")));
		ActionUtils.sendKeys(
				driver.findElement(By
						.xpath("//div[@id='taskpopupModalFixedBodydashnoard_Task']//div[@id='divPatientAutoSearch']//input")),
				PID);
		Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'ctrl.searchText')]"), 120);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(@md-highlight-text,'ctrl.searchText')]")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
		Commons.capturemessage(driver, 60);
		// Open new Task IN View Mode
		
		int i = 0;
		// Mark Task as completed
		do{ i++;
			System.out.println("Mark All Tasks as completed");
			Commons.waitforElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"), 60);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'Automation Test Task')]")));
			Commons.waitForLoad(driver);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='editBtn_dashnoard_Task']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdwnStatus_dashnoard_Task']//option[@label='Complete']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdnTaskType_dashnoard_Task']/option[3]")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
			Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Task saved."));
		}while(Commons.ExistandDisplayElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"),10)&&i<10);
		Commons.waitforElement(driver, By.xpath("//p[contains(.,'No Record Found')]"),20);
		Commons.screenshot();
	}

	@Test(enabled = true, priority = 2)
	public void CreateTaskUserBMS() throws InterruptedException {
		test = extent.startTest("ADD task as BMS user type", "Test ADD task as BMS user type"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user BMS");
		test.log(LogStatus.INFO, "Login as user BMS");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userBMS"), Commons.readPropertyValue("password"));
		AddPatientUtils.QuickAddpatient(driver);
		Commons.waitForLoad(driver);
		PID = ActionUtils.getText(AddPatientPage.patientID(driver));
		// Navigate to Dash board and click on Create new task button
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Dashboard')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Dashboard')]")));
		Commons.waitforElement(driver, By.xpath("//a[@href='#task']"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#task']")));
		Commons.waitforElement(driver, By.xpath("//button[@id='taskNewBtn']"), 90);
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskNewBtn']")));
		// Assert New Task Window
		Commons.waitforElement(driver, By.xpath("//h4[contains(.,'Task')]"), 60);
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Company (required)')]")).getText()
				.contains("Company (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Description (required)')]")).getText()
				.contains("Description (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Assign To (required)')]")).getText()
				.contains("Assign To (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Task Type (required)')]")).getText()
				.contains("Task Type (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Status (required)')]")).getText()
				.contains("Status (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Individual (required)')]")).getText()
				.contains("Individual (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Date Due (required)')]")).getText()
				.contains("Date Due (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Status (required)')]")).getText()
				.contains("Status (required)"));
		System.out.println(driver.findElement(By.xpath("//label[contains(.,'Comment')]")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//button[contains(.,'Assign to Me')]")).getText()
				.contains("Assign to Me"));
		Commons.screenshot();
		// Enter Task details Click on task Save button
		ActionUtils.click(driver.findElement(By.xpath("//input[@name='taskDescription']")));
		driver.findElement(By.xpath("//input[@name='taskDescription']")).sendKeys("Automation Test Task");
		driver.findElement(By.xpath("//textarea[@id='txtArComment_dashnoard_Task']")).sendKeys("Test Automation task");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdnTaskType_dashnoard_Task']/option[3]")));
		ActionUtils.click(driver.findElement(By
				.xpath("//div[@id='taskpopupModalFixedBodydashnoard_Task']//div[@id='divPatientAutoSearch']//input")));
		ActionUtils.sendKeys(
				driver.findElement(By
						.xpath("//div[@id='taskpopupModalFixedBodydashnoard_Task']//div[@id='divPatientAutoSearch']//input")),
				PID);
		Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'ctrl.searchText')]"), 120);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(@md-highlight-text,'ctrl.searchText')]")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
		Commons.capturemessage(driver, 60);
		int i = 0;
		// Mark Task as completed
		do{ i++;
			System.out.println("Mark All Tasks as completed");
			Commons.waitforElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"), 60);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'Automation Test Task')]")));
			Commons.waitForLoad(driver);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='editBtn_dashnoard_Task']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdwnStatus_dashnoard_Task']//option[@label='Complete']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdnTaskType_dashnoard_Task']/option[3]")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
			Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Task saved."));
		}while(Commons.ExistandDisplayElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"),10)&&i<10);
		Commons.waitforElement(driver, By.xpath("//p[contains(.,'No Record Found')]"),20);
		Commons.screenshot();
	}

	@Test(enabled = true, priority = 3)
	public void CreateTaskUserPartner() throws InterruptedException {
		test = extent.startTest("ADD task as Partner user type", "Test ADD task as Partner user type"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user Partner");
		test.log(LogStatus.INFO, "Login as user Partner");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userPartner"), Commons.readPropertyValue("password"));
		// AddPatientUtils.QuickAddpatient(driver);
		Commons.waitForLoad(driver);
		// String PID = ActionUtils.getText(AddPatientPage.patientID(driver));
		// Navigate to Dash board and click on Create new task button
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Dashboard')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Dashboard')]")));
		Commons.waitforElement(driver, By.xpath("//a[@href='#task']"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#task']")));
		Commons.waitforElement(driver, By.xpath("//button[@id='taskNewBtn']"), 90);
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskNewBtn']")));
		// Assert New Task Window
		Commons.waitforElement(driver, By.xpath("//h4[contains(.,'Task')]"), 60);
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Company (required)')]")).getText()
				.contains("Company (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Description (required)')]")).getText()
				.contains("Description (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Assign To (required)')]")).getText()
				.contains("Assign To (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Task Type (required)')]")).getText()
				.contains("Task Type (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Status (required)')]")).getText()
				.contains("Status (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Individual (required)')]")).getText()
				.contains("Individual (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Date Due (required)')]")).getText()
				.contains("Date Due (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Status (required)')]")).getText()
				.contains("Status (required)"));
		System.out.println(driver.findElement(By.xpath("//label[contains(.,'Comment')]")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//button[contains(.,'Assign to Me')]")).getText()
				.contains("Assign to Me"));
		Commons.screenshot();
		// Enter Task details Click on task Save button
		ActionUtils.click(driver.findElement(By.xpath("//input[@name='taskDescription']")));
		driver.findElement(By.xpath("//input[@name='taskDescription']")).sendKeys("Automation Test Task");
		driver.findElement(By.xpath("//textarea[@id='txtArComment_dashnoard_Task']")).sendKeys("Test Automation task");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdnTaskType_dashnoard_Task']/option[3]")));
		ActionUtils.click(driver.findElement(By
				.xpath("//div[@id='taskpopupModalFixedBodydashnoard_Task']//div[@id='divPatientAutoSearch']//input")));
		ActionUtils.sendKeys(
				driver.findElement(By
						.xpath("//div[@id='taskpopupModalFixedBodydashnoard_Task']//div[@id='divPatientAutoSearch']//input")),
				PID);
		Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'ctrl.searchText')]"), 120);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(@md-highlight-text,'ctrl.searchText')]")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
		Commons.capturemessage(driver, 60);
		Commons.waitforElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"), 60);
		extent.endTest(test);
		Commons.screenshot();
		extent.flush();
		// Open new Task IN View Mode
		test = extent.startTest("Edit/Update task", "Test Edit/Update task" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Open Existing Task IN View Mode");
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'Automation Test Task')]")));
		Commons.waitForLoad(driver);
		Commons.screenshot();
		// Edit task data
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='editBtn_dashnoard_Task']")));
		test.log(LogStatus.INFO, "Edit task data");
		test.log(LogStatus.INFO, "Change task Description");
		ActionUtils.click(driver.findElement(By.xpath("//input[@name='taskDescription']")));
		driver.findElement(By.xpath("//input[@name='taskDescription']")).clear();
		driver.findElement(By.xpath("//input[@name='taskDescription']")).sendKeys("Edited Automation Test Task");
		//select task type drop down..
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdnTaskType_dashnoard_Task']//option[2]")));
		// Click on Save
		test.log(LogStatus.INFO, "Click on Save");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
		Commons.capturemessage(driver, 60);
		test.log(LogStatus.INFO, "Verify data updated in task");
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(.,'Edited Automation Test Task')]")).getText()
				.contains("Edited Automation Test Task"), "Could not varify Edit task");
		int i = 0;
		// Mark Task as completed
		do{ i++;
			System.out.println("Mark All Tasks as completed");
			Commons.waitforElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"), 60);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'Automation Test Task')]")));
			Commons.waitForLoad(driver);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='editBtn_dashnoard_Task']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdwnStatus_dashnoard_Task']//option[@label='Complete']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdnTaskType_dashnoard_Task']/option[3]")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
			Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Task saved."));
		}while(Commons.ExistandDisplayElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"),10)&&i<10);
		Commons.waitforElement(driver, By.xpath("//p[contains(.,'No Record Found')]"),20);
		Commons.screenshot();
	}

	@Test(enabled = true, priority = 4,dependsOnMethods = { "CreateTaskUserBMS" })
	public void CreateTaskUserCustomer() throws InterruptedException {
		test = extent.startTest("ADD task as Customer user type", "Test ADD task as Customer user type"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user Customer");
		test.log(LogStatus.INFO, "Login as user Customer");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userCustomer"),
				Commons.readPropertyValue("password"));
		AddPatientUtils.QuickAddpatient(driver);
		Commons.waitForLoad(driver);
		// String PID = ActionUtils.getText(AddPatientPage.patientID(driver));
		// Navigate to Dash board and click on Create new task button
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Dashboard')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Dashboard')]")));
		Commons.waitforElement(driver, By.xpath("//a[@href='#task']"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#task']")));
		Commons.waitforElement(driver, By.xpath("//button[@id='taskNewBtn']"), 90);
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskNewBtn']")));
		// Assert New Task Window
		Commons.waitforElement(driver, By.xpath("//h4[contains(.,'Task')]"), 60);
		// Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Company
		// (required)')]")).getText().contains("Company (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Description (required)')]")).getText()
				.contains("Description (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Assign To (required)')]")).getText()
				.contains("Assign To (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Task Type (required)')]")).getText()
				.contains("Task Type (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Status (required)')]")).getText()
				.contains("Status (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Individual (required)')]")).getText()
				.contains("Individual (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Date Due (required)')]")).getText()
				.contains("Date Due (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Status (required)')]")).getText()
				.contains("Status (required)"));
		System.out.println(driver.findElement(By.xpath("//label[contains(.,'Comment')]")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//button[contains(.,'Assign to Me')]")).getText()
				.contains("Assign to Me"));
		Commons.screenshot();
		// Enter Task details Click on task Save button
		ActionUtils.click(driver.findElement(By.xpath("//input[@name='taskDescription']")));
		driver.findElement(By.xpath("//input[@name='taskDescription']")).sendKeys("Automation Test Task");
		driver.findElement(By.xpath("//textarea[@id='txtArComment_dashnoard_Task']")).sendKeys("Test Automation task");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdnTaskType_dashnoard_Task']/option[3]")));
		ActionUtils.click(driver.findElement(By
				.xpath("//div[@id='taskpopupModalFixedBodydashnoard_Task']//div[@id='divPatientAutoSearch']//input")));
		ActionUtils.sendKeys(
				driver.findElement(By
						.xpath("//div[@id='taskpopupModalFixedBodydashnoard_Task']//div[@id='divPatientAutoSearch']//input")),
				PID);
		Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'ctrl.searchText')]"), 120);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(@md-highlight-text,'ctrl.searchText')]")));
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdnTaskType_dashnoard_Task']/option[3]")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
		Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Task saved."));
		// Open new Task IN View Mode
		Commons.waitforElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"), 60);
		// Mark Task as completed
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'Automation Test Task')]")));
		Commons.waitForLoad(driver);
		Commons.screenshot();
		int i = 0;
		// Mark Task as completed
		do{ i++;
			System.out.println("Mark All Tasks as completed");
			Commons.waitforElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"), 60);
			ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//td[contains(.,'Automation Test Task')]")));
			Commons.waitForLoad(driver);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='editBtn_dashnoard_Task']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdwnStatus_dashnoard_Task']//option[@label='Complete']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='drpdnTaskType_dashnoard_Task']/option[3]")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
			Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Task saved."));
		}while(Commons.ExistandDisplayElement(driver, By.xpath("//td[contains(.,'Automation Test Task')]"),10)&&i<10);
		Commons.waitforElement(driver, By.xpath("//p[contains(.,'No Record Found')]"),20);
		Commons.screenshot();
	}

	@Test(enabled = true, priority = 5)
	public void Task_Required_Field_Validation() throws InterruptedException {
		test = extent.startTest("Validate required field on Add task screen",
				"Test Validate required field on Add task screen" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// Navigate to Dash board and click on Create new task button
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Dashboard')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Dashboard')]")));
		Commons.waitforElement(driver, By.xpath("//a[@href='#task']"), 90);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#task']")));
		Commons.waitforElement(driver, By.xpath("//button[@id='taskNewBtn']"), 90);
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='taskNewBtn']")));
		// Assert New Task Window
		Commons.waitforElement(driver, By.xpath("//h4[contains(.,'Task')]"), 60);
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Company (required)')]")).getText()
				.contains("Company (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Description (required)')]")).getText()
				.contains("Description (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Assign To (required)')]")).getText()
				.contains("Assign To (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Task Type (required)')]")).getText()
				.contains("Task Type (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Status (required)')]")).getText()
				.contains("Status (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Individual (required)')]")).getText()
				.contains("Individual (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Date Due (required)')]")).getText()
				.contains("Date Due (required)"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'Status (required)')]")).getText()
				.contains("Status (required)"));
		System.out.println(driver.findElement(By.xpath("//label[contains(.,'Comment')]")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//button[contains(.,'Assign to Me')]")).getText()
				.contains("Assign to Me"));
		Commons.screenshot();
		test.log(LogStatus.INFO, "Opened Task Screen");
		test.log(LogStatus.INFO,
				"case-1 Remove the Assignee and Due date. Without entered another other data and click Save");
		ActionUtils.click(driver.findElement(
				By.xpath("//div[contains(@id,'taskpopupModalFixedBodydashnoard')]//*[@id='btn_ClrUser']")));
		driver.findElement(By.xpath("//input[@id='txtDueDate_dashnoard_Task']")).clear();
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
		Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Please fill all the mandatory fields"),
				"Could not validate required field Case-1");
		test.log(LogStatus.INFO, "case-2 Enter in a description and click save");
		ActionUtils.click(driver.findElement(By.xpath("//input[@name='taskDescription']")));
		driver.findElement(By.xpath("//input[@name='taskDescription']")).sendKeys("Automation Test Task");
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
		Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Please fill all the mandatory fields"),
				"Could not validate required field Case-2");
		test.log(LogStatus.INFO, "case-3 Enter in a description and Due date click save");
		ActionUtils.click(driver.findElement(By.xpath("//input[@name='taskDescription']")));
		driver.findElement(By.xpath("//input[@name='taskDescription']")).sendKeys("Automation Test Task");
		ActionUtils.click(driver.findElement(
				By.xpath("//div[contains(@id,'taskpopupModalFixedBodydashnoard')]//*[@id='btn_ClrUser']")));
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='saveBtn_dashnoard_Task']")));
		Assert.assertTrue(Commons.capturemessage(driver, 60).contains("Please fill all the mandatory fields"),
				"Could not validate required field Case-3");
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='taskpopupModalHeaderdashnoard_Task']/button")));
	}
}
