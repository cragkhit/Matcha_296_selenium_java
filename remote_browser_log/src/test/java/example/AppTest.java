package example;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import java.lang.StringBuilder;

import java.net.BindException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
// import org.openqa.selenium.firefox.ProfileManager;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.*;
import org.testng.annotations.*;

public class AppTest // extends BaseTest
{
	private RemoteWebDriver driver;
	private Actions actions;
	private WebDriverWait wait;

	@BeforeSuite(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context)
			throws InterruptedException, MalformedURLException {
		// http://www.programcreek.com/java-api-examples/index.php?api=org.testng.ITestContext
		String seleniumHost = context.getCurrentXmlTest().getParameter("host");
		String seleniumPort = context.getCurrentXmlTest().getParameter("port");
		String seleniumBrowser = context.getCurrentXmlTest()
				.getParameter("browser");
		DesiredCapabilities capabilities;
		LoggingPreferences loggingPreferences = new LoggingPreferences();
		;
		// remote Configuration
		if (context.getCurrentXmlTest().getParameter("execution")
				.compareToIgnoreCase("remote") == 0) {
			String hub = "http://" + seleniumHost + ":" + seleniumPort + "/wd/hub";

			loggingPreferences.enable(LogType.BROWSER, Level.ALL);
			loggingPreferences.enable(LogType.CLIENT, Level.INFO);
			loggingPreferences.enable(LogType.SERVER, Level.INFO);

			if (seleniumBrowser.compareToIgnoreCase("chrome") == 0) {
				capabilities = new DesiredCapabilities("chrome", "", Platform.ANY);
				capabilities.setBrowserName("chrome");
				capabilities.setCapability(CapabilityType.LOGGING_PREFS,
						loggingPreferences);

			} else {

				capabilities = new DesiredCapabilities("firefox", "", Platform.ANY);
				capabilities.setBrowserName("firefox");

				FirefoxProfile profile = new ProfilesIni().getProfile("default");
				capabilities.setCapability("firefox_profile", profile);
				capabilities.setCapability(CapabilityType.LOGGING_PREFS,
						loggingPreferences);
			}
			try {
				driver = new RemoteWebDriver(
						new URL("http://" + seleniumHost + ":" + seleniumPort + "/wd/hub"),
						capabilities);
			} catch (MalformedURLException ex) {
			}
			assertThat(driver, notNullValue());
		}
		// standalone
		else {
			if (seleniumBrowser.compareToIgnoreCase("chrome") == 0) {
				System.setProperty("webdriver.chrome.driver", context
						.getCurrentXmlTest().getParameter("webdriver.chrome.driver"));
				capabilities = DesiredCapabilities.chrome();
				loggingPreferences.enable(LogType.BROWSER, Level.ALL);
				capabilities.setCapability(CapabilityType.LOGGING_PREFS,
						loggingPreferences);
				driver = new ChromeDriver(capabilities);
				// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else {
				capabilities = DesiredCapabilities.firefox();
				loggingPreferences.enable(LogType.BROWSER, Level.ALL);
				capabilities.setCapability(CapabilityType.LOGGING_PREFS,
						loggingPreferences);
				/*
				   prefs.js:user_pref("extensions.logging.enabled", true);
				   user.js:user_pref("extensions.logging.enabled", true);
				 */
				driver = new FirefoxDriver(capabilities);
			}
		}
		wait = new WebDriverWait(driver, 5);
		actions = new Actions(driver);
		try {
			driver.manage().window().setSize(new Dimension(600, 800));
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	@AfterSuite(alwaysRun = true, enabled = true)
	public void cleanupSuite() {
		driver.close();
		driver.quit();
	}

	@Test(description = "Opens the site")
	public void LoggingTest() throws InterruptedException {
		String base_url = "http://www.cnn.com/";
		driver.get(base_url);

		String class_name = "logo";
		class_name = "cnn-badge-icon"; 
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.className(class_name)));
		WebElement element = driver.findElement(By.className(class_name));
		assertThat(element, notNullValue());
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].style.border='3px solid yellow'", element);
		}
		// Thread.sleep(3000L);

		actions.moveToElement(element).click().build().perform();
		analyzeLog();
	}

	public void analyzeLog() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);

		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel()
					+ " " + entry.getMessage());
		}
	}

	private static JSONObject extractObject(HttpResponse resp)
			throws IOException, JSONException {
		InputStream contents = resp.getEntity().getContent();
		StringWriter writer = new StringWriter();
		IOUtils.copy(contents, writer, "UTF8");
		JSONObject objToReturn = new JSONObject(writer.toString());
		return objToReturn;
	}
}