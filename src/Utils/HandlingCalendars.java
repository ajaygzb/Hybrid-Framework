package Utils;

import java.util.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import TestBase.TestSetUp;

public class HandlingCalendars extends TestSetUp {
	static int targetDay = 0, targetMonth = 0, targetYear = 0;
	static int currentDay = 0, currentMonth = 0, currentYear = 0;
	static int jumpMonthsBy = 0;
	static boolean increment = true;

	public static void datepick(WebDriver driver, By locator, String dateToSet) {
		/*
		 * 
		 * target day, month, year current day, month, year jump to the month
		 * increment or decrement
		 */
		// String dateToSet= "14/05/2016";
		// get current date
		getCurrentDateMonthAndYear();
		System.out.println("Current date" + "****" + currentDay + "   " + currentMonth + "   " + currentYear);
		// get target date
		GetTargetDateMonthAndYear(dateToSet);
		System.out.println("Target date" + "****" + targetDay + "   " + targetMonth + "   " + targetYear);
		// Get Jump month
		// CalculateHowManyMonthsToJump();
		// System.out.println(jumpMonthsBy);
		// System.out.println(increment);
		driver.findElement(locator).click();
		SeTtargetyear();
		/*
		 * CalculateHowManyMonthsToJump(); for(int i=0; i<jumpMonthsBy;i++){
		 * if(increment){ Commons.waitforElement(driver,By.xpath(
		 * "//button[@ng-click='move(1)']"),10);
		 * driver.findElement(By.xpath("//button[@ng-click='move(1)']")).click()
		 * ; }else{ Commons.waitforElement(driver,By.xpath(
		 * "//button[@ng-click='move(-1)']"),10);
		 * driver.findElement(By.xpath("//button[@ng-click='move(-1)']")).click(
		 * ); } }
		 */
		Commons.ExistandDisplayElement(driver,
				By.xpath("//button[contains(@ng-click,'date')]//span[contains(text(),'" + (targetDay) + "')]"), 10);
		ActionUtils.JavaScriptclick(driver
				.findElement(By.xpath("//button[contains(@ng-click,'date')]//span[text()='" + (targetDay) + "']")));
	}

	
	
	public static void Schedulerdatepick(WebDriver driver, By locator, String dateToSet) {
	
		Commons.waitForElementToBeClickable(driver, driver.findElement(locator), 60);
		driver.findElement(locator).click();
		Commons.ExistandDisplayElement(driver,
				By.xpath("//div[@id='meetingStartTime_dateview']//td//a[contains(@title,'" +dateToSet+ "')]"), 10);
		ActionUtils.click(driver.findElement(By.xpath("//div[@id='meetingStartTime_dateview']//td//a[contains(@title,'" +dateToSet+ "')]")));
		
	}
	public static void getCurrentDateMonthAndYear() {
		Calendar cal = Calendar.getInstance();
		currentDay = cal.get(Calendar.DAY_OF_MONTH);
		currentMonth = cal.get(Calendar.MONTH) + 1;
		currentYear = cal.get(Calendar.YEAR);
	}

	public static void GetTargetDateMonthAndYear(String dateString) {
		int firstIndex = dateString.indexOf("/");
		int lastIndex = dateString.lastIndexOf("/");
		String day = dateString.substring(0, firstIndex);
		targetDay = Integer.parseInt(day);
		String month = dateString.substring(firstIndex + 1, lastIndex);
		targetMonth = Integer.parseInt(month);
		String year = dateString.substring(lastIndex + 1, dateString.length());
		targetYear = Integer.parseInt(year);
	}

	public static void CalculateHowManyMonthsToJump() {
		if ((targetMonth - currentMonth) > 0) {
			jumpMonthsBy = (targetMonth - currentMonth);
		} else {
			jumpMonthsBy = (currentMonth - targetMonth);
			increment = false;
		}
	}

	public static String theMonth(int month) {
		String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		return monthNames[month - 1];
	}

	public static void SeTtargetyear() {
		if ((targetYear) > 2001) {
			targetYear = targetYear % 100;
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='toggleMode()']"), 30);
			driver.findElement(By.xpath("//button[@ng-click='toggleMode()']")).click();
			Commons.Explicitwait();
			driver.findElement(By.xpath("//button[@ng-click='toggleMode()']")).click();
			Commons.Explicitwait();
			if (targetYear < 10) {
				// for years having 01-09
				String numberAsString = String.format("%02d", targetYear);
				int i = 0;
				while (!Commons.ExistandDisplayElement(driver,
						By.xpath("//button//span[contains(text(),'" + (numberAsString) + "')]/.."), 2) && i < 3) {
					ActionUtils.JavaScriptclick(driver.findElement(By.xpath("//button[@ng-click='move(1)']")));
					i++;
				}
				System.out.println("Setting Target year in calender..");
				ActionUtils.click(
						driver.findElement(By.xpath("//button//span[contains(text(),'" + (numberAsString) + "')]/..")));
				Commons.Explicitwait();
			} else {
				// Commons.waitforElement(driver,By.xpath("//span[contains(text(),'"+(targetYear)+"')]"),30);
				Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(text(),'" + (targetYear) + "')]/.."),
						20);
				System.out.println("Setting Target year in calender..");
				ActionUtils.click(
						driver.findElement(By.xpath("//button//span[contains(text(),'" + (targetYear) + "')]/..")));
				Commons.Explicitwait();
			}
			Commons.waitforElement(driver, By.xpath("//span[text()='" + (theMonth(targetMonth)) + "']"), 30);
			Commons.Explicitwait();
			ActionUtils.click(driver.findElement(By.xpath("//span[text()='" + (theMonth(targetMonth)) + "']")));
			// span[text()='2001']
		} else {
			targetYear = targetYear % 100;
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='toggleMode()']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='toggleMode()']")));
			// Commons.Explicitwait();
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='toggleMode()']")));
			// Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='move(-1)']"), 30);
			// ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='move(-1)']")));
			int i = 0;
			while (!Commons.ExistandDisplayElement(driver,
					By.xpath("//button//span[contains(text(),'" + (targetYear) + "')]"), 2) && i < 6) {
				ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='move(-1)']")));
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Commons.ExistandDisplayElement(driver, By.xpath("//button//span[contains(text(),'" + (targetYear) + "')]"),
					30);
			String year = driver.findElement(By.xpath("//button//span[contains(text(),'" + (targetYear) + "')]"))
					.getText();
			System.out.println("Setting Target year in calender.." + year);
			driver.findElement(By.xpath("//button//span[contains(text(),'" + (targetYear) + "')]")).click();
			Commons.waitforElement(driver, By.xpath("//span[text()='" + (theMonth(targetMonth)) + "']"), 30);
			// Commons.Explicitwait();
			ActionUtils.click(driver.findElement(By.xpath("//span[text()='" + (theMonth(targetMonth)) + "']")));
		}
	}

	public static void datepick(WebDriver driver, WebElement element, String dateToSet) throws InterruptedException {
		/*
		 * 
		 * target day, month, year current day, month, year jump to the month
		 * increment or decrement
		 */
		// String dateToSet= "14/05/2016";
		// get current date
		getCurrentDateMonthAndYear();
		System.out.println("Current date" + "****" + currentDay + "   " + currentMonth + "   " + currentYear);
		// get target date
		GetTargetDateMonthAndYear(dateToSet);
		System.out.println("Target date" + "****" + targetDay + "   " + targetMonth + "   " + targetYear);
		// Get Jump month
		CalculateHowManyMonthsToJump();
		ActionUtils.click(element);
		SeTtargetyear(); // set target year with target month
		Commons.ExistandDisplayElement(driver,
				By.xpath("//button[contains(@ng-click,'date')]//span[contains(.,'" + (targetDay) + "')]"), 10);
		ActionUtils.click(driver
				.findElement(By.xpath("//button[contains(@ng-click,'date')]//span[text()='" + (targetDay) + "']")));
	}
}
