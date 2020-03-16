package com.mycompany.app;
// example from

// https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/284
// implementation derived from
// http://bharathautomation.blogspot.in/p/selenium-webdriver-faqs.html
// has errors.
// TODO: explore workarounds suggested in the archive

// import org.openqa.selenium.phantomjs.PhantomJSDriver;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.lang.RuntimeException;
import java.net.BindException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import java.util.Formatter;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Boolean.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class App implements Runnable {
	public static WebDriver driver;
	private static Set<String> windowHandles;
	Thread thread;

	App() throws InterruptedException {
		thread = new Thread(this, "test");
		thread.start();
	}

	public void run() {
		String currentHandle = null;

		try {
			System.err.println("Thread: sleep 3 sec.");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("Thread: wake.");
		// With modal window, WebDriver appears to be hanging on [get current window
		// handle]
		try {
			currentHandle = driver.getWindowHandle();
			System.err.println("Thread: Current Window handle" + currentHandle);
		} catch (NoSuchWindowException e) {

		}
		while (true) {
			try {
				System.out.println("Thread: wait .5 sec");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread: inspecting all Window handles");
			// when a modal window is created by Javascript window.showModalDialog
			// WebDriver appears to be hanging on [get current window handle], [get window
			// handles]
			// Node console shows no Done: [get current window handle] or Done: [get window
			// handles]
			// if the window is closed manually, and cleater again, the problem goes away
			windowHandles = driver.getWindowHandles();
			if (windowHandles.size() > 1) {
				System.err.println("Found " + (windowHandles.size() - 1) + " additional Windows");
				break;
			} else {
				System.out.println("Thread: no other Windows");
			}
		}

		Iterator<String> windowHandleIterator = windowHandles.iterator();
		while (windowHandleIterator.hasNext()) {
			String handle = (String) windowHandleIterator.next();
			if (!handle.equals(currentHandle)) {
				System.out.println("Switch to " + handle);
				driver.switchTo().window(handle);
				// move, print attributes
				System.out.println("Switch to main window.");
				driver.switchTo().defaultContent();
			}
		}
		/*
		 * // the rest of example commented out String nextHandle =
		 * driver.getWindowHandle(); System.out.println("nextHandle" + nextHandle);
		 * 
		 * driver.findElement(By.xpath("//input[@type='button'][@value='Close']")).click
		 * ();
		 * 
		 * // Switch to main window for (String handle : driver.getWindowHandles()) {
		 * driver.switchTo().window(handle); } // Accept alert
		 * driver.switchTo().alert().accept();
		 */
	}

	public static void main(String args[]) throws InterruptedException, MalformedURLException {
		// ProfilesIni p=new ProfilesIni();
		// WebDriver hangs on navigation with Firefox 40 / Selenium 2.44
		// driver=new FirefoxDriver(p.getProfile("default"));
		// only works with Firefox
		/*
		 * DesiredCapabilities capabilities = new DesiredCapabilities("firefox", "",
		 * Platform.ANY); FirefoxProfile profile = new
		 * ProfilesIni().getProfile("default"); profile.setEnableNativeEvents(false);
		 * capabilities.setCapability("firefox_profile", profile);
		 */

		System.setProperty("webdriver.chrome.driver", "/home/sergueik/Downloads/chromedriver");
		/*
		 * DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		 * LoggingPreferences logging_preferences = new LoggingPreferences();
		 * logging_preferences.enable(LogType.BROWSER, Level.ALL);
		 * capabilities.setCapability(CapabilityType.LOGGING_PREFS,
		 * logging_preferences);
		 */
		// java.lang.NoClassDefFoundError: org/apache/commons/exec/DaemonExecutor
		// https://github.com/SeleniumHQ/selenium/issues/3284
		// prefs.js:user_pref("extensions.logging.enabled", true);
		// user.js:user_pref("extensions.logging.enabled", true);
		driver = new ChromeDriver(/* capabilities */ );

		// driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"),
		// capabilities);
		/*
		 * System.setProperty("webdriver.ie.driver",
		 * "c:/java/selenium/IEDriverServer.exe"); driver = new
		 * InternetExplorerDriver();
		 */
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		new App();
		// non-modal windows are handled successfully.
		// driver.get("http://www.naukri.com/");
		driver.get("https://developer.mozilla.org/samples/domref/showModalDialog.html");
		// https://developer.mozilla.org/en-US/docs/Web/HTML/Element/dialog
		driver.get("https://developer.mozilla.org/en-US/docs/Web/HTML/Element/dialog");
		WebElement element = driver.findElement(By.cssSelector("iframe#frame_Advanced_example"));
		WebDriver iframe = driver.switchTo().frame(element);
		System.err.println(iframe.getPageSource());
		element = iframe.findElement(By.cssSelector("button#updateDetails"));
		System.err.println(element.getAttribute("outerHTML"));

		element.click();
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
		// iframe class="live-sample-frame sample-code-frame" frameborder="0"
		// height="300" id="frame_Advanced_example"
		// following two locator do not work with IE
		// driver.findElement(By.xpath("//input[@value='Open modal dialog']")).click();
		// driver.findElement(By.cssSelector("input[type='button']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.pollingEvery(500, TimeUnit.MILLISECONDS);
		// Actions actions = new Actions(driver);

		wait.until(ExpectedConditions.visibilityOf(iframe.findElement(By.xpath("//*[@id=\"favDialog\"]"))));

		WebElement body = iframe.findElement(By.xpath("//*[@id=\"favDialog\"]"));
		body.findElement(By.xpath("//*[@id=\"confirmBtn\"]")).click();

		System.out.println("main: sleeping 10 sec");

		Thread.sleep(20000);
		System.out.println("main: close");
		driver.close();
		driver.quit();

	}

}
