package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import TestBase.TestSetUp;
import UIMap.HomePage;
import UIMap.LoginPage;
import com.google.common.base.Function;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import ReportUtils.ExtentManager;

public class Commons {
	// private static ActionUtil Actionutil = new ActionUtil();
	public static File projectPath = new File("");
	private static Properties prop = new Properties();
	public static String strfirstname = null;
	public static String strlastname = null;
	public static String strPatientID = null;
	public static long lgphone_home = 0;
	public static String strGender = null;
	public static String strText_Actual = null;
	public static int intDob = 0;
	public static double x = Math.random();
	public static String mi;
	public static String suffix;
	public static String address1;
	public static String address2;
	public static String country;
	public static String city;
	public static long zipcode = 0;
	public static String state;
	public static String email;
	public static String reminder_type;
	public static long ssn;
	public static String maritial_status;
	public static String driver_license;
	public static String spoken_language;
	public static String comment;
	public static long alternatepatient_id;
	public static File savePath = new File("");
	public static String BuildVersion;
	public static String configfile;
	public static WebElement i;
	public static String ENV = System.getProperty("ENV");

	public static void launchRevFlow(WebDriver driver) {
		try {
			driver.get(readPropertyValue("URL"));
			Commons.waitForLoad(driver);
			System.out.println("Loading application...");
			Commons.waitforElement(driver, By.name("username"), 500);
			/*
			 * (new WebDriverWait(driver,120)).until(ExpectedConditions
			 * .visibilityOfAllElementsLocatedBy(By.name("username")));
			 */
			System.out.println("Launched RevFlow5");
			System.out.println(driver.findElement(By.xpath("//img[contains(@src,'logobig')]//..")).getText());
			BuildVersion = driver.findElement(By.xpath("//img[contains(@src,'logobig')]//..")).getText();
			
			
		} catch (Exception e) {
			System.out.println("Not able to Launch Revflow, Timed out" + e);
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
	}

	public static void logintoRevflow(WebDriver driver, String username, String password) {
		try {
			ActionUtils.sendKeys(LoginPage.username(driver), username);
			ActionUtils.sendKeys(LoginPage.password(driver), password);
			//// Thread.sleep(1000);
			ActionUtils.click(LoginPage.signin(driver));
			// System.out.println("Checking Password expire alert!!");
			Commons.waitforElement(driver, By.xpath("//span[@class='ng-binding'][2]"), 160);
			Commons.waitForLoad(driver);
		} catch (Exception e) {
			System.out.println("Unable to login to revflow Retrying....");
			// Assert.assertFalse(true,"Unable to login to revflow");
			try {
				System.out.println("Checking Password Expired Alert!");
				Commons.screenshot();
				WebDriverWait wait = new WebDriverWait(driver, 3);
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='changePasswordAlertBtn']")));
				System.out.println("Found Password Expired Alert!");
				driver.findElement(By.xpath("//*[@id='changePasswordAlertBtn']")).click();
				System.out.println("Closed Password expire alert!!");
			} catch (Exception e1) {
				System.out.println("Not Found Password Expired Alert!");
				ActionUtils.JavaScriptclick(LoginPage.signin(driver));
				System.out.println("Re-Clicked on Sign In Button");
				Commons.waitforElement(driver, By.xpath("//span[@class='ng-binding'][2]"), 60);
			}
		}
	}

	public static void logout(WebDriver driver) {
		if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("IE")) {
			System.out.println("$$In IE browser$$");
			launchRevFlow(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='username']"), 60);
		} else {
			try {
				ActionUtils.click(HomePage.datatoggle(driver));
				if (Commons.ExistandDisplayElement(driver, By.xpath("//a[contains(.,'Log Out')]"), 20)) {
					ActionUtils.click(HomePage.logoutlink(driver));
				}
				if (Commons.ExistandDisplayElement(driver, By.xpath("//input[@name='username']"), 20)) {
					System.out.println("Logged out of Revflow");
				} else {
					launchRevFlow(driver);
					Commons.waitforElement(driver, By.xpath("//input[@name='username']"), 60);
				}
			} catch (Exception e) {
				System.out.println("Unable to logout of RevFlow");
			}
		}
	}

	public static String readPropertyValue(String strPropertyName) {
		String value = null;
		FileInputStream fileinputstream;
		// System.out.println("Loading Environemtn as::=>>"
		// +ENV+"**********************************************");
		if (ENV == null || ENV.contains("Default")) {
			configfile = "Config.properties";
		} else if (ENV.contains("10.131.168.10")) {
			configfile = "RSI.properties";
		} else if (ENV.contains("betarevflow")) {
			configfile = "BMSBETA.properties";
		} else if (ENV.contains("qagold")) {
			configfile = "R5QAgold.properties";
		} else if (ENV.contains("stagegold")) {
			configfile = "R5Stage.properties";
		} else if (ENV.contains("R5Beta")) {
			configfile = "RSIBeta.properties";
		}
		/* configfile = "RSI.properties"; */
		try {
			fileinputstream = new FileInputStream(
					projectPath.getAbsolutePath() + "/src/Config/" + configfile.toString());
			prop.load(fileinputstream);
			value = prop.getProperty(strPropertyName);
			fileinputstream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not read from Property File ");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static boolean waitforElement(WebDriver driver, final By locator, int intTimout) {
		WebDriverWait wait = new WebDriverWait(driver, intTimout);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator)));
		if (!driver.findElement(locator).isDisplayed()) {
			Commons.scrollElementinotView(driver.findElement(locator), TestSetUp.driver);
		}
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(locator));
		Commons.waitForLoad(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.ignoring(StaleElementReferenceException.class);
		Commons.waitForLoad(driver);
		Commons.Explicitwait();
		return driver.findElement(locator).isDisplayed();
	}

	public static void Explicitwait() {
		try {
			//// Thread.sleep(500);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void waitForLoad(WebDriver driver) {
		try {
			// System.out.println("DOM Processing...");
			untilAngularFinishHttpCalls(driver);
			Commons.waitForElementToBeNotVisible(TestSetUp.driver, By.xpath("//*[@id='mySpinner']"), 30);
			new WebDriverWait(driver, 60).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
					.executeScript("return document.readyState").equals("complete"));
		} catch (Exception e) {
		}
	}

	public static String capturemessage(WebDriver driver, int timeout) {
		String strMessage = null;
		try {
			// Commons.fluentWait(driver,
			// By.xpath("//div[contains(@class,'toast-message')]"),timeout);
			// Commons.waitforElement(driver,
			// By.xpath("//div[contains(@class,'toast-message')]"), timeout);
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'toast-message')]")));
			strMessage = driver.findElement(By.xpath("//div[contains(@class,'toast-message')]")).getText().trim();
			System.out.println("*****" + strMessage + "*****");
			TestSetUp.test.log(LogStatus.INFO, "*****" + strMessage + "*****");
			Commons.screenshot();
			System.out.println("Waiting for toast message to disappear..");
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='toast-container']//button[contains(.,'×')]"),
					5)) {
				System.out.println("Closing toast message..");
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='toast-container']//button[contains(.,'×')]")));
			}
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//div[contains(@class,'toast-message')]"), 80);
		} catch (Exception e) {
			System.out.println("Could not read / see Toast Message");
			// Assert.assertFalse(true,"Could not read / see Toast Message");
		}
		return strMessage;
	}

	public static void fnHighlightMe(WebDriver driver, WebElement element) throws InterruptedException {
		// Creating JavaScriptExecuter Interface
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int iCnt = 0; iCnt < 2; iCnt++) {
			// Execute javascript
			js.executeScript("arguments[0].style.border='4px groove green'", element);
			// Thread.sleep(500);
			js.executeScript("arguments[0].style.border=''", element);
		}
	}

	// Waiting 900 seconds for an element to be present on the page, checking
	// for its presence once every 2 seconds.
	public static WebElement fluentWait(WebDriver driver, final By locator, int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return foo;
	}

	public static boolean waitForElementToBeNotVisible(WebDriver driver, By locator, int intTimout) {
		boolean webElement = false;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			FluentWait<WebDriver> wait = new WebDriverWait(driver, intTimout)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.pollingEvery(5, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			// System.out.println("Element is not visible");
			webElement = true;
		} catch (Exception e) {
			System.out.println("Element is visible | ");
			webElement = false;
		}
		return webElement;
	}

	/**
	 * 
	 * @param webelement
	 * @param keys
	 * @throws InterruptedException
	 */
	public static void scrollElementinotView(WebElement webelement, WebDriver driver) {
		try {
			Commons.waitForElementToBeVisible(driver, webelement, 10);
			if (!webelement.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webelement);
				// Thread.sleep(8000); //scrolling page and wait to re-arrage
				// DOM
				Commons.waitForLoad(driver);
			}
		} catch (Exception e) {
			System.out.println("Unable to perform Scroll action" + Throwables.getStackTraceAsString(e));
		}
	}

	public static void checkAlert(WebDriver driver) {
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver, 2)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.pollingEvery(1, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			System.out.println("Alert Pop up Found");
			alert.dismiss();
			System.out.println("Select Cancel");
			System.out.println("Alert Found");
		} catch (Exception e) {
			// exception handling
		}
	}

	// function to check is element exist on page.
	public static boolean existsElement(WebDriver driver, By locator) {
		try {
			driver.findElements(locator).get(0);
			Commons.waitforElement(driver, locator, 5);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// function to check is element exist and display on page without throwing
	// exception during execution.
	public static boolean ExistandDisplayElement(WebDriver driver, By locator, int timeout) {
		try {
			// Commons.waitforElement(driver, locator,timeout);
			Commons.waitForLoad(driver);
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			wait.ignoring(StaleElementReferenceException.class);
			Commons.waitForLoad(driver);
			// driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void untilAngularFinishHttpCalls(WebDriver driver) {
		try {
			final String javaScriptToLoadAngular = "var injector = window.angular.element('body').injector();"
					+ "var $http = injector.get('$http');" + "return ($http.pendingRequests.length === 0)";
			ExpectedCondition<Boolean> pendingHttpCallsCondition = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript(javaScriptToLoadAngular).equals(true);
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, 20); // timeout = 30
																// secs
			wait.until(pendingHttpCallsCondition);
		} catch (Exception e) {
		}
	}

	public static boolean waitForElementToBeClickable(WebDriver driver, WebElement element, int timeout) {
		boolean webElement = false;
		try {
			FluentWait<WebDriver> wait;
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, timeout)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.pollingEvery(12, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			webElement = true;
		} catch (Exception e) {
			webElement = false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		return webElement;
	}

	public static boolean waitForElementToBeVisible(WebDriver driver, WebElement element, long waitingTime) {
		boolean webElement = false;
		try {
			FluentWait<WebDriver> wait;
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, waitingTime)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.pollingEvery(5, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOf(element));
			webElement = true;
		} catch (Exception e) {
			webElement = false;
		}
		return webElement;
	}

	public static void dragAndDrop(WebDriver driver, WebElement sourceElement, WebElement destinationElement) {
		System.out.println("start drop down");
		Commons.waitForElementToBeVisible(driver, sourceElement, 30);
		Commons.waitForElementToBeVisible(driver, destinationElement, 30);
		// sourceElement.click();
		(new Actions(driver)).dragAndDrop(sourceElement, destinationElement).build().perform();
		new Actions(driver).clickAndHold(sourceElement).moveByOffset(100, 100).release().perform();
		System.out.println("Done drop down");
	}

	public static void screenshot() {
		
		
		TestSetUp.test.log(LogStatus.INFO,"Snapshot below: " + TestSetUp.test.addScreenCapture(ReportUtils.ExtentManager.CaptureScreen(
		TestSetUp.driver, System.getProperty("user.home") + "/screenshots/"+ ExtentManager.getCurrentTimeStamp())));
	}

	public static boolean SelectElement(WebDriver driver, By locator, String Option) {
		try {
			driver.findElement(locator).click();
			Thread.sleep(1000);
			Select dropdown = new Select(driver.findElement(locator));
			dropdown.selectByVisibleText(Option);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	public static String Addcurrentdate(){
		
		String curentdate = null;
		
		Calendar cal = Calendar.getInstance();
		int    currentDay = cal.get(Calendar.DAY_OF_MONTH);
		String currentMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String currentYear = Integer.toString(cal.get(Calendar.YEAR));
		curentdate=currentDay + "/" + currentMonth + "/" + currentYear;
		return curentdate;	
	}
	
	
	public  static Boolean AssertTextArray(WebDriver driver, By locator,String arr[]){
		
	
		try{
		    
			List<WebElement> label = driver.findElements(locator);
			// Remove Empty/Whitespaces String From List of Webelements
			
			for(int i = 0; i < label.size(); i++){
		           
	        	
				if((!label.get(i).isDisplayed())){
	        		
	        		System.out.println("Remove Blank-");
	        		label.remove(i);
			}else if(StringUtils.isWhitespace(label.get(i).getText().toString().trim())){
				
				System.out.println("Remove Blank--");
        		label.remove(i);
				
				
			}
              else if(label.get(i).getText().toString().trim().isEmpty()){
				
				System.out.println("Remove Blank---");
        		label.remove(i);
				
				
			}
			}
			System.out.println("After Removing White spaces     "+label.size());
            
           
            for(WebElement e:label){
            	
            	System.out.println(e.getText());
             }
			System.out.println(label.size()+arr.length);
            Assert.assertTrue(label.size()==arr.length);
			// Assertion Block
			for(int k=0;k<arr.length;k++){
				String expected=arr[k].trim();
				String actual=label.get(k).getText().trim();	
				System.out.println(actual);
				System.out.println(expected);
			
				TestSetUp.test.log(LogStatus.INFO,"Actual:   "+actual);
				TestSetUp.test.log(LogStatus.INFO,"Expected:   "+expected);
			//	Assert.assertTrue(actual.contains(expected));	
				Assert.assertEquals(actual,expected);
				
				}
			return true;
			}catch(AssertionError e){
			
			System.out.println("**************Not Found****************  ");	
		
			TestSetUp.test.log(LogStatus.INFO, "*************Not Found*************   ");
			System.out.println("Error in Asserting text Array"+Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true,"Error in Asserting text Array"+Throwables.getStackTraceAsString(e));
			return false;
		
			
			
		}
		
		
		
		
		
		
	}
	
	
	public static List<String> ReadExceldata(int SheetIndex) throws InterruptedException {
	String str;
	List<String> data = new ArrayList<String>();
		try {
			FileInputStream file = new FileInputStream(
			new File(System.getProperty("user.dir")+"/src/dataRepository/inputdata/BMS.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(SheetIndex);
			System.out.println("rows" + sheet.getLastRowNum());
			// Find number of rows in excel file
			int rowCount = sheet.getLastRowNum();  // Index from zero
			System.out.println("Total rows" + rowCount);
			System.out.println("Getting Patient data from Excel..");
			// Create a loop over all the rows of excel file to read it
			for (int i = 1; i <= rowCount; i++) {
				Row row = sheet.getRow(i);
				// Create a loop to print cell values in a row
				for (int j = 0; j < row.getLastCellNum(); j++) {
					DataFormatter formatter = new DataFormatter();
					str = formatter.formatCellValue(sheet.getRow(i).getCell(j));
					data.add(str);
				}
			}
			for(String s:data)
				System.out.println(s);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		return data;
	}
	
	
	
	
	
}
