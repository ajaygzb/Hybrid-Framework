package UIMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.google.common.base.Throwables;
import Utils.Commons;

public class WaitList {
	static WebElement element;

	public static WebElement waitListMenu(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Add Patient to Waitlist')]"), 90);
			element = driver.findElement(By.xpath("//span[contains(text(),'Add Patient to Waitlist')]"));
			System.out.println("Got  waitlist");
		} catch (Exception e) {
			System.out.println("not able to findout waitlist");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement waitSaveButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmWaitList.addWaitlist()']"), 90);
			element = driver.findElement(By.xpath("//button[@ng-click='vmWaitList.addWaitlist()']"));
			System.out.println("got save button");
		} catch (Exception e) {
			System.out.println("Unable to get save button ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement closeButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmWaitList.clear()']"), 90);
			element = driver.findElement(By.xpath("//button[@ng-click='vmWaitList.clear()']"));
		} catch (Exception e) {
			System.out.println("Unable to get save button ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement WaitListView(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'View Waitlist')]"), 90);
			element = driver.findElement(By.xpath("//span[contains(text(),'View Waitlist')]"));
			System.out.println("got view waitList menu");
		} catch (Exception e) {
			System.out.println("Unable to get wait list menu ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement WaitListSearchButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.id("waitListPatientSrchBtn"), 90);
			element = driver.findElement(By.id("waitListPatientSrchBtn"));
			System.out.println("got view waitList search buuton");
		} catch (Exception e) {
			System.out.println("Unable to get wait list search button ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement patientnameSearchBox(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.id("firstName"), 90);
			element = driver.findElement(By.id("firstName"));
			System.out.println("got firstName text box");
		} catch (Exception e) {
			System.out.println("Unable to get firstName textbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement patientLastnameSearchBox(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.id("lastName"), 90);
			element = driver.findElement(By.id("lastName"));
			System.out.println("got lastName text box");
		} catch (Exception e) {
			System.out.println("Unable to get lastName textbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement PatientSearchButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmWaitList.patientSearch()']"), 90);
			element = driver.findElement(By.xpath("//button[@ng-click='vmWaitList.patientSearch()']"));
			System.out.println("got view patient search buuton");
		} catch (Exception e) {
			System.out.println("Unable to get patient search button ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement checkAllBox(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='scannedDocumentTable']//input[@id='chkSelecAll']"), 90);
			element = driver.findElement(By.xpath("//div[@id='scannedDocumentTable']//input[@id='chkSelecAll']"));
			System.out.println("got all select check box");
		} catch (Exception e) {
			System.out.println("Unable to get all select check box");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement DeleteButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@id='removeWaitlist']"), 90);
			element = driver.findElement(By.xpath("//button[@id='removeWaitlist']"));
			System.out.println("got Delete button");
		} catch (Exception e) {
			System.out.println("Unable to get delete button ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement nextButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='viewWaitList']//a[@title='Go to the next page']"), 90);
			element = driver.findElement(By.xpath("//div[@id='viewWaitList']//a[@title='Go to the next page']"));
			System.out.println("got next button");
		} catch (Exception e) {
			System.out.println("Unable to get next button ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}
}
