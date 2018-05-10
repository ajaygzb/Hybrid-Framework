package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.google.common.base.Throwables;
import UIMap.CasePage;
import UIMap.SchedulerPage;
import UIMap.WaitList;

public class waiListUtils {
	public static void NavigateToWaitlist(WebDriver driver) {
		ActionUtils.JavaScriptclick(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.JavaScriptclick(WaitList.waitListMenu(driver));
		// SchedulerPage.ScheduleOnMenu(driver).click();
		// WaitList.waitListMenu(driver).click();
		Commons.waitforElement(driver, By.xpath("//h4[contains(text(),'Add Patient to Waitlist')]"), 90);
		Assert.assertNotNull(driver.findElement(By.xpath("//h4[contains(text(),'Add Patient to Waitlist')]")));
		System.out.println("successfully redirected to wait List");
	}

	public static void NavigateToViewWaitlist(WebDriver driver) {
		ActionUtils.JavaScriptclick(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.JavaScriptclick(WaitList.WaitListView(driver));
		// SchedulerPage.ScheduleOnMenu(driver).click();
		// WaitList.WaitListView(driver).click();
		Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Waitlist ')]"), 90);
		Assert.assertNotNull(driver.findElement(By.xpath("//h1[contains(text(),'Waitlist ')]")));
		System.out.println("successfully redirected to view wait List");
	}

	public static void DeleteWholeWaitList(WebDriver driver) {
		NavigateToViewWaitlist(driver);
		while (driver.findElement(By.xpath("//div[@id='viewWaitList']//a[@title='Go to the next page']"))
				.isDisplayed()) {
			WaitList.checkAllBox(driver).click();
			WaitList.DeleteButton(driver).click();
			try {
				Commons.waitforElement(driver,
						By.xpath(
								"//*[@id='confirmdelete']//li[contains(.,'Are you sure you want to remove the checked patient(s) from the waitlist?')]"),
						90);
				String actualMessage = driver
						.findElement(By
								.xpath("//*[@id='confirmdelete']//li[contains(.,'Are you sure you want to remove the checked patient(s) from the waitlist?')]"))
						.getText();
				Assert.assertEquals(actualMessage,
						"Are you sure you want to remove the checked patient(s) from the waitlist?");
				System.out.println("got confirmation message :" + actualMessage);
				driver.findElement(By.xpath("//button[@ng-click='vmWaitList.removeWaitlist()']")).click();
			} catch (AssertionError e) {
				System.out.println("not getting right confirmation pop up message ");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			} catch (Exception e) {
				System.out.println("not getting confirmation pop up ");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
		}
	}
}
