package TestBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import ReportUtils.ExtentManager;
import ReportUtils.GenerateReport;
import ReportUtils.ZipUtils;
import Utils.Commons;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestSetUp implements IRetryAnalyzer {
	public static WebDriver driver;
	private File projectPath = new File("");
	public int intTimeOut;
	public static DesiredCapabilities caps;
	public static Capabilities Browser_ver;
	public static StringBuffer testbrowser = new StringBuffer("");
	public static int index;
	private String driverPath = projectPath.getAbsolutePath() + "\\src\\dataRepository\\browserDrivers\\";
	public  static ExtentReports extent;
	public static String CurrentBrowserName;
	public static String emailtitle;
	public static ExtentTest test;
	private int retryCount = 0;
	private int maxRetryCount = 1;
	public static String date;
	String PROXY = "http://rproxy.india.rsystems.com:8080";
	protected static SoftAssert s_assert = new SoftAssert();
	public static String browser = System.getProperty("browser");
	public static List<String> passedtests = new ArrayList<String>();
	public static List<String> failedtests = new ArrayList<String>();
	public static List<ITestNGMethod> skippedtests = new ArrayList<ITestNGMethod>();
	public static int TotalPass;
	public static int TotalFail;

	@Parameters({ "browsername" })
	@BeforeSuite
	public void beforeSuite(String browsername) {
		try {
			FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + "/Report"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extent = ExtentManager.getReporter(ExtentManager.filePath);
		extent.loadConfig(new File(System.getProperty("user.dir") +"/xmls/config.xml"));
		
		if (browser == null) {
			browser = browsername;
		}
		/*
		 * PrintStream out = null; try { out = new PrintStream(new
		 * FileOutputStream("Automationlogs.txt")); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } System.setOut(out);
		 */
	}

	@Parameters({ "ModuleName" })
	@AfterSuite
	protected void afterSuite(String modulename) {
		try {
			
			Browser_ver = ((RemoteWebDriver) driver).getCapabilities();
			if ((TestSetUp.caps.getBrowserName() != null) && (TestSetUp.Browser_ver.getVersion() != null)) {
				// extent.addSystemInfo("Browsers Name",
				// testbrowser.toString());
				extent.addSystemInfo("Build URL", (Commons.readPropertyValue("URL")));
				extent.addSystemInfo("Host Name", "Ajay").addSystemInfo("Environment", "QA");
				extent.addSystemInfo("BUILD Version", Commons.BuildVersion);
				System.out.println(TotalPass = passedtests.size());
				TotalPass = passedtests.size();
				TotalFail = failedtests.size();
				GenerateReport.createExcelreport();
			}
		} catch (Exception e) {
			Reporter.log("Browser's info won't available in extent report");
		}
		
	
			
		
		// extent.close();
		// driver.quit();
	
		File source = new File(System.getProperty("user.dir") + "/Report/");
		File dest = new File(System.getProperty("user.home") + "/Desktop/AutomationReports/" + Commons.BuildVersion
				+ "_" + modulename + browser);
		emailtitle = Commons.BuildVersion + "_" + modulename + browser;
		try {
			copyFileUsingApacheCommonsIO(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// ZipUtils.Zipfile();
			ZipUtils.SendingMail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Create Report folder in Zip format
		extent.flush();
		extent.endTest(test);
		// extent.close();
	}

	private static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
		FileUtils.copyDirectory(source, dest);
	}

	// @Parameters({ "browser" })
	@BeforeClass
	public void setup() {
		try {
			CurrentBrowserName = browser;// DO NOT REMOVE
			System.out.println("BEFORE Class*******************Launching Browser*************************************");
			extent.addSystemInfo("Build URL", (Commons.readPropertyValue("URL")));
			if (browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				options.addArguments("disable-infobars");
				//String userProfile= "C:\\Users\\ajay.kumar4\\AppData\\Local\\Google\\Chrome\\User Data\\";
				//options.addArguments("user-data-dir="+userProfile);
				options.addArguments("--start-maximized");
				caps = DesiredCapabilities.chrome();
				caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				caps.setCapability(ChromeOptions.CAPABILITY, options);
				// options.addArguments("-incognito"); //to run incognito mode
				driver = new ChromeDriver(caps);
				ClearBrowserCache();
				if (!(testbrowser).toString().contains(caps.getBrowserName()))
					Browser_ver = ((RemoteWebDriver) driver).getCapabilities();
				testbrowser.append(caps.getBrowserName() + "(" + Browser_ver.getVersion() + ")");
				System.out.println(caps.getBrowserName());
				System.out.println(Browser_ver.getVersion());
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				Commons.Explicitwait();
			} else if (browser.equalsIgnoreCase("Firefox")) {
				// Bellow given syntaxes will set browser proxy settings using
				// DesiredCapabilities.
				FirefoxProfile profile = new FirefoxProfile();
				Proxy proxy = new Proxy();
				// proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY)
				// .setSocksProxy(PROXY);
				// proxy.setProxyAutoconfigUrl("http://rproxy.india.rsystems.com:"+8080);
				profile.setPreference("network.proxy.type", 0);
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(CapabilityType.PROXY, proxy);
				FirefoxProfile fp = new FirefoxProfile();
				fp.setPreference("webdriver.load.strategy", "unstable");
				System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
				driver = new FirefoxDriver();
				ClearBrowserCache();
				caps = DesiredCapabilities.firefox();
				System.out.println(caps.getBrowserName());
				Browser_ver = ((RemoteWebDriver) driver).getCapabilities();
				System.out.println(Browser_ver.getVersion());
				if (!(testbrowser).toString().contains(caps.getBrowserName()))
					testbrowser.append(" " + caps.getBrowserName() + "(" + Browser_ver.getVersion() + ")");
				System.out.println(caps.getBrowserName());
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				Commons.Explicitwait();
			} else if (browser.equalsIgnoreCase("mobilechrome")) {
				System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
				Proxy proxy = new Proxy();
				// proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY)
				// .setSocksProxy(PROXY);
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(CapabilityType.PROXY, proxy);
				driver = new ChromeDriver(cap);
				driver.manage().window().setSize(new Dimension(350, 570));
				// tablet Dimension(782,1180);
				Reporter.log("Browser is in Mobile View Mode  to Tablet Ipad");
			} else if (browser.equalsIgnoreCase("Ipad")) {
				System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
				Map<String, Object> deviceMetrics = new HashMap<String, Object>();
				deviceMetrics.put("width", 1024);
				deviceMetrics.put("height", 2732);
				deviceMetrics.put("pixelRatio", 1.0);
				Map<String, Object> mobileEmulation = new HashMap<String, Object>();
				// mobileEmulation.put("deviceMetrics", deviceMetrics);
				// mobileEmulation.put("userAgent", "mobile");
				mobileEmulation.put("deviceName", "Apple iPad");
				Map<String, Object> chromeOptions = new HashMap<String, Object>();
				chromeOptions.put("mobileEmulation", mobileEmulation);
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
				driver = new ChromeDriver(capabilities);
				caps = DesiredCapabilities.chrome();
				/*
				 * JavascriptExecutor js = (JavascriptExecutor) driver;
				 * js.executeScript("document.body.style.zoom='75%'");
				 */
				Browser_ver = ((RemoteWebDriver) driver).getCapabilities();
				if (!(testbrowser).toString().contains(caps.getBrowserName()))
					testbrowser
							.append("TabletView" + "  " + caps.getBrowserName() + "(" + Browser_ver.getVersion() + ")");
				ClearBrowserCache();
				System.out.println("Browser is in Mobile View Mode  to Tablet Ipad");
				driver.manage().window().maximize();
			} else if (browser.equalsIgnoreCase("safari")) {
				if (driver == null) {
					driver = new SafariDriver();
				}
				caps = DesiredCapabilities.safari();
				Browser_ver = ((RemoteWebDriver) driver).getCapabilities();
				if (!(testbrowser).toString().contains(caps.getBrowserName()))
					testbrowser.append(", " + caps.getBrowserName() + "(" + Browser_ver.getVersion() + ")");
				// testbrowser.append(" ,
				// "+caps.getBrowserName().toString()+"("+Browser_ver.getVersion()().toString()+")");
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				Commons.Explicitwait();
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
				capabilities.setCapability("requireWindowFocus", true);
				driver = new InternetExplorerDriver(capabilities);
				// driver = new InternetExplorerDriver();
				caps = DesiredCapabilities.internetExplorer();
				Browser_ver = ((RemoteWebDriver) driver).getCapabilities();
				if (!(testbrowser).toString().contains(caps.getBrowserName()))
					testbrowser.append(", " + caps.getBrowserName() + "(" + Browser_ver.getVersion() + ")");
				// testbrowser.append(" ,
				// "+caps.getBrowserName().toString()+"("+Browser_ver.getVersion()().toString()+")");
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				Commons.Explicitwait();
			} else if (browser.equalsIgnoreCase("Edge")) {
				// testbrowser.append(" ,
				// "+caps.getBrowserName()+"("+Browser_ver.getVersion()()+")");
				driver = new EdgeDriver();
				// caps = ((RemoteWebDriver) driver).getCapabilities();
				Browser_ver = ((RemoteWebDriver) driver).getCapabilities();
				if (!(testbrowser).toString().contains(caps.getBrowserName()))
					testbrowser.append(", " + caps.getBrowserName() + "(" + Browser_ver.getVersion() + ")");
				// testbrowser.append(" ,
				// "+caps.getBrowserName()+"("+Browser_ver.getVersion()()+")");
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				Commons.Explicitwait();
			}
			if (browser.equalsIgnoreCase("Appium")) {
				 caps = new DesiredCapabilities();
				 caps.setCapability(MobileCapabilityType.BROWSER_NAME,"Browser");
				 caps.setCapability("deviceName", "Custom_Tablet___6_0_0___API_23___1536x2048");
					//caps.setCapability("udid", "ENUL6303030010"); //Give Device ID of your mobile phone
					caps.setCapability("platformName", "Android");
					caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"5.0");
					//caps.setCapability("browserName", "Chrome");
					caps.setCapability("noReset", true);
					
					//Set ChromeDriver location
					//System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
					
					//Instantiate Appium Driver
					//AppiumDriver<MobileElement> driver = null;
					try {
						driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
						
					} catch (MalformedURLException e) {
						System.out.println(e.getMessage());
					}
			}
			
			intTimeOut = Integer.parseInt(Commons.readPropertyValue("timeOut"));
			// driver.manage().timeouts().implicitlyWait(intTimeOut,
			// TimeUnit.SECONDS);
			System.out.println("Loading..........");
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			// waitForLoad(driver);
		} catch (Exception e) {
			System.out.println("Unable to launch  **" + browser + "**  browser");
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void setup(Method method) {
		try {
			// recorder.start();
			System.out.println(
					"\n\n******************************BEFORE METHOD ********Launch revflow***********************************");
			System.out.println("\n\n\n******************************Start Logs for test" + "Test name: "
					+ method.getName() + "*******************************************");
			// ClearBrowserCache();
			Commons.launchRevFlow(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("username"),
					Commons.readPropertyValue("password"));
			// checking provider Home page loading or not
			Commons.waitForLoad(driver);
			/*
			 * System.out.println("Loading Home Page..."); if
			 * (Commons.ExistandDisplayElement(driver,
			 * By.xpath("//img[contains(@src,'LogoProvider')]"),5) &&
			 * driver.getCurrentUrl().contains("non-provider")) {
			 * System.out.println("User Logged in as a Non Provider"); } else {
			 * Commons.waitforElement(driver,
			 * By.xpath("//b[contains(.,'Appointment')]"), 80);
			 * System.out.println("On Provider Home Page.");
			 * Commons.Explicitwait(); }
			 */
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='leftMenuList']/li[10]"), 60);
			int i = 0;
			while (driver.findElements(By.xpath("//*[@id='leftMenuList']/li")).size() < 10 && i < 5) {
				System.out.println("Side bar menu not loaded.....reloading page");
				System.out.println(driver.findElements(By.xpath("//*[@id='leftMenuList']/li")).size());
				Commons.screenshot();
				driver.get(driver.getCurrentUrl());
				Commons.waitForLoad(driver);
				if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='leftMenuList']/li"), 80)) {
					System.out.println("Loaded Patient in Sidebar Menu..");
					test.log(LogStatus.INFO, "Loaded Patient in Sidebar Menu..");
					Commons.screenshot();
				}
				i++;
			}
			Commons.waitforElement(driver, By.xpath("//*[@id='leftMenuList']/li"), 10);
			System.out.println("Successfully login to revflow");
		} catch (Exception e) {
			// Assert.assertFalse(true,"Unable to Load Provider Home page in
			// App");
			if (driver != null) {
				driver.get(driver.getCurrentUrl());
				Commons.waitForLoad(driver);
				Commons.ExistandDisplayElement(driver, By.xpath("//i[@class='fa fa-user']"), 30);
			}
		}
	}

	@AfterMethod
	// This mehod will add screenshots in report for each pass and fail Test
	// cases
	protected void afterMethod(ITestResult result) {
		try {
			// if((driver.getCurrentUrl()).toString().contains("10.10.20.90")){
			if (result.getStatus() == ITestResult.FAILURE) {
				test.log(LogStatus.FAIL, result.getThrowable());
				test.log(LogStatus.FAIL, "Test Failed");
				failedtests.add(test.getTest().getName());
				Commons.screenshot();
			} else if (result.getStatus() == ITestResult.SKIP) {
				test.log(LogStatus.INFO, "Test Got failed==>> RERUN THE SAME TEST CASE" + "|  |" + result.getName()
						+ "|   |" + result.getThrowable());
				Commons.screenshot();
				skippedtests.add(result.getMethod());
			} else {
				test.log(LogStatus.PASS, "Test passed");
				Commons.screenshot();
				// passedtests.add(result.getMethod().getMethodName());
				passedtests.add(test.getTest().getName());
				System.out.println(passedtests.size());
			}
		
			 test.assignAuthor("Ajay", "Sabhyata", "Ratee");
			
		//	extent.endTest(test);
			extent.flush();
		} catch (Exception e) {
			test.log(LogStatus.INFO, result.getName().toString() + "     " + "Test Skipped");
			System.out.println("Test Skipped Not addded to report" + result.getName().toString());
		}
		// Commons.logout(driver);
		test.log(LogStatus.INFO, "Executed in Test Class" + "    " + this.getClass().getName());
		System.out.println(this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "method name:" + result.getMethod().getMethodName());
		System.out.println("\n\n\n**************Ending logs for test***********" + "method name:***"
				+ result.getMethod().getMethodName());
		/*
		 * try { Commons.waitforElement(driver,
		 * By.xpath("//a[@data-toggle='dropdown']"),20); Commons.logout(driver);
		 * } catch (Exception e) { //Assert.fail("Unable to logout from app"); }
		 */
	}

	@AfterClass
	public void closedrivers() {
		System.out.println("\n\nAFTER Class Closing Browser********************************************************"
				+ this.getClass().getName());
		extent.addSystemInfo("Browser", browser + " " + Browser_ver.getVersion());
		extent.flush();
		try {
			Commons.logout(driver);
		} catch (Exception e) {
			// Assert.fail("Unable to logout from app");
		}
		try {
			if (!(CurrentBrowserName.contains("safari"))) {
				System.out.println("Closing driver after Class**************************");
				driver.close();
				driver.quit();
			}
		} catch (Exception e) {
			driver = null;
		}
	//	extent.endTest(test);
	
	}

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			System.out.println("Retrying test " + result.getMethod().getMethodName() + " with status "
					+ getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
			test.log(LogStatus.INFO,
					"Retrying test ************* " + result.getMethod().getMethodName() + " with status "
							+ getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
			test.log(LogStatus.INFO, "********************Retry************************************** ");
			Commons.screenshot();
			test.log(LogStatus.INFO, "********************Retry******************************** ");
			retryCount++;
			return true;
		}
		return false;
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1)
			resultName = "SUCCESS";
		if (status == 2)
			resultName = "FAILURE";
		if (status == 3)
			resultName = "SKIP";
		return resultName;
	}

	public void ClearBrowserCache() throws InterruptedException {
		driver.manage().deleteAllCookies(); // delete all cookies
		System.out.println("Clearing Browser Cache...");
		// wait 5 seconds to clear cookies.
	}
}
