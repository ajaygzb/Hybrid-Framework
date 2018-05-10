package UIMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import TestBase.TestSetUp;
import Utils.ActionUtils;
import Utils.Commons;
import com.google.common.base.Throwables;

public class ExamTemplatesPage {
	public static WebElement webelement;

	public static WebElement MyExamTemplate(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,' My Exam Templates')]"), 40);
			webelement = driver
					.findElement(By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,' My Exam Templates')]"));
			System.out.println("Got My Exam Template on Sub Menu");
		} catch (Exception e) {
			System.out.println("Did not get My Exam Template on Sub Menu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MyInterventionLibraries(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,' My Interventions Libraries')]"), 40);
			webelement = driver.findElement(
					By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,' My Interventions Libraries')]"));
			System.out.println("Got My Interventions Libraries on Sub Menu");
		} catch (Exception e) {
			System.out.println("Did not get My Interventions Libraries on Sub Menu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MyPhraseLibraries(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,'My Phrase Libraries')]"), 40);
			webelement = driver
					.findElement(By.xpath("//div[@id='sidebar-wrapper']//a[contains(.,'My Phrase Libraries')]"));
			System.out.println("Got My Interventions Libraries on Sub Menu");
		} catch (Exception e) {
			System.out.println("Did not get My Interventions Libraries on Sub Menu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
}