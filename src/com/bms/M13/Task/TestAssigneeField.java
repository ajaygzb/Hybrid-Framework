package com.bms.M13.Task;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.Commons;

// TC- Assignee Field - Individual Search
// TC- Assignee Field - Group Search
// TC- Regarding Patient - Inactive patients not available
// TC- Save Task assigned to inactive group
public class TestAssigneeField extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void Assignee_Field_Individual_Search() throws InterruptedException {
		test = extent.startTest("Assignee Field - Individual Search", "Test Assignee Field - Individual Search"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		/*
		 * Commons.logout(driver); System.out.println("Logged out app");
		 * System.out.println("Login as user Sysadmin");
		 * test.log(LogStatus.INFO, "Login as user Sysadmin");
		 * Commons.logintoRevflow(driver,
		 * Commons.readPropertyValue("userSysadmin"),
		 * Commons.readPropertyValue("password"));
		 */
		// Navigate to Dash board and click on task button
		Commons.waitForLoad(driver);
		test.log(LogStatus.INFO, "Navigate to Dash board and click on task button");
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Dashboard')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Dashboard')]")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//a[@href='#task']"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#task']")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//button[@id='taskNewBtn']"), 90);
		try {
			test.log(LogStatus.INFO, "Case-1: Enter a partial user id in the Assigned to quick search field");
			ActionUtils.click(driver.findElement(
					By.xpath("//*[@id='autosearchUserAssigned']//following::span[1]//button[@id='btn_ClrUser']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='autosearchUserAssigned']//input")));
			driver.findElement(By.xpath("//*[@id='autosearchUserAssigned']//input"))
					.sendKeys(Commons.readPropertyValue("userSysadmin"));
			Assert.assertTrue(Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(.,'QA_Sysadmin')]"), 60),
					"Could not Validate Quick search in Assigned To field");
			System.out.println("**********Ässertion Pass*********");
			Commons.screenshot();
			// ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'QA_Sysadmin')]")));
			// Clicking on Assigned to Magnifier button
			driver.findElement(By.xpath("//*[@id='autosearchUserAssigned']//input")).clear();
			ActionUtils.click(driver.findElement(
					By.xpath("//*[@id='autosearchUserAssigned']//preceding::button[contains(@id,'SearchUser')]")));
			Commons.waitforElement(driver, By.xpath("//div[@id='UserSearchPopup']//*[@id='User_Name']"), 60);
			test.log(LogStatus.INFO,
					"Case-2: click on the advanced search icon The following search fields are available: - login ID,- user first name,- user last name");
			Assert.assertTrue(Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='UserSearchPopup']//label[contains(text(),'First Name')]"), 5));
			Assert.assertTrue(Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='UserSearchPopup']//label[contains(text(),'Last Name')]"), 5));
			Assert.assertTrue(Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='UserSearchPopup']//label[contains(text(),'User Name')]"), 5));
			Commons.screenshot();
			test.log(LogStatus.INFO,
					"enter a  user id and click search data displays that match the search criteria entered");
			driver.findElement(By.xpath("//div[@id='UserSearchPopup']//*[@id='User_Name']"))
					.sendKeys(Commons.readPropertyValue("userSysadmin"));
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='UserSearchPopup']//input[@value='Search']")));
			Commons.waitforElement(driver,
					By.xpath("//div[@id='UserSearchPopup']//td//span[contains(.,'QA_Sysadmin')]"), 40);
			System.out.println("**********Ässertion Pass*********");
			Commons.screenshot();
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//div[@id='UserSearchPopup']//td//span[contains(.,'QA_Sysadmin')]")));
			driver.findElement(By.xpath("//*[@id='autosearchUserAssigned']//input")).clear();
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		extent.endTest(test);
		Commons.screenshot();
		test = extent.startTest("Assignee Field - Group Search", "Test Assignee Field - Group Search"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			test.log(LogStatus.INFO, "Case-1: Enter a partial user id in the Task Group quick search field");
			// ActionUtils.click(driver.findElement(By.xpath("//*[@id='autosearchUserAssigned']//following::span[1]//button[@id='btn_ClrUser']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='autoSearchTaskGroup']//input")));
			driver.findElement(By.xpath("//*[@id='autoSearchTaskGroup']//input")).sendKeys("Autotask");
			Thread.sleep(8000);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='autoSearchTaskGroup']//input")));
			Assert.assertTrue(
					Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(text(),'AutoTask')]"), 80),
					"Could not Validate Quick search in Task Group field");
			System.out.println("**********Ässertion Pass*********");
			Commons.screenshot();
			// ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'QA_Sysadmin')]")));
			// Clicking on Assigned to Magnifier button
			driver.findElement(By.xpath("//*[@id='autoSearchTaskGroup']//input")).clear();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btn_SearchTaskGroup']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='SearchGroup_GroupName']"), 60);
			test.log(LogStatus.INFO,
					"Case-2: click on the  search icon The following search fields are available: - Group Name");
			Assert.assertTrue(Commons.ExistandDisplayElement(driver,
					By.xpath(".//*[@id='taskGroupSearchPopup']//label[contains(text(),'Group Name')]"), 5));
			Commons.screenshot();
			test.log(LogStatus.INFO,
					"enter a Active group name and click search data displays that match the search criteria entered");
			driver.findElement(By.xpath("//input[@id='SearchGroup_GroupName']")).sendKeys("Autotask");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='taskGroupSearchPopup']//input[@value='Search']")));
			Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='taskGroupSearchPopup']//td[contains(.,'AutoTask')]"), 80);
			System.out.println("**********Ässertion Pass*********");
			Commons.screenshot();
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//*[@id='taskGroupSearchPopup']//td[contains(.,'AutoTask')]")));
			driver.findElement(By.xpath("//*[@id='autoSearchTaskGroup']//input")).clear();
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
