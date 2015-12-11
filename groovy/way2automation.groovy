@Grapes([
    @Grab(group = 'com.google.code.gson', module = 'gson', version = '2.4'),
    @Grab(group = 'org.apache.httpcomponents', module = 'httpcore', version = '4.3.2'),
    @Grab(group = 'org.gebish', module = 'geb-core', version = '0.9.3'),
    @Grab(group = 'org.seleniumhq.selenium', module = 'selenium-chrome-driver', version = '[2.43.0,2.44.0)'),
    @Grab(group = 'org.seleniumhq.selenium', module = 'selenium-firefox-driver', version = '[2.43.0,2.44.0)'),
    @Grab(group = 'org.seleniumhq.selenium', module = 'selenium-remote-driver', version = '[2.43.0,2.44.0)'),
    @Grab(group = 'org.seleniumhq.selenium', module = 'selenium-support', version = '[2.43.0,2.44.0)'),
    @GrabExclude('xerces:xercesImpl'),
    @GrabExclude('xml-apis:xml-apis'),
])

import groovy.transform.Field
import groovy.grape.Grape

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogEntry
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions

import java.util.concurrent.TimeUnit
import java.util.logging.Level
// global variables
@Field WebDriver driver 
@Field int implicit_wait_interval = 1
@Field int flexible_wait_interval = 5
@Field long wait_polling_interval = 500
@Field WebDriverWait wait
@Field Actions actions
@Field WebElement element

@Field long highlight_interval = 1000

def printErr = System.err.&println
def highlight(WebElement element) {
	if (wait!= null)         {
		wait = new WebDriverWait(driver, flexible_wait_interval );
	}
	wait.pollingEvery(wait_polling_interval,TimeUnit.MILLISECONDS);
	wait.until(ExpectedConditions.visibilityOf(element));
	if (driver instanceof JavascriptExecutor) {
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid yellow'", element);
	}
	Thread.sleep(highlight_interval);
	if (driver instanceof JavascriptExecutor) {
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border=''", element);
	}
}

def driver_path =  (System.properties['os.name'].toLowerCase().contains('windows')) ? 'c:/java/selenium/chromedriver.exe' : '/home/vncuser/selenium/chromedriver/chromedriver'

System.setProperty('webdriver.chrome.driver', driver_path )
def capabilities = DesiredCapabilities.chrome()
def logging_preferences = new LoggingPreferences()
logging_preferences.enable(LogType.BROWSER, Level.ALL)
capabilities.setCapability(CapabilityType.LOGGING_PREFS, logging_preferences)
driver = new ChromeDriver(capabilities)
driver.manage().timeouts().implicitlyWait(implicit_wait_interval, TimeUnit.SECONDS)
wait = new WebDriverWait(driver, flexible_wait_interval )
actions = new Actions(driver)
driver.get('http://way2automation.com/way2auto_jquery/index.php')
def signup_css_selector = 'div#load_box.popupbox form#load_form a.fancybox[href="#login"]'
element = driver.findElement(By.cssSelector(signup_css_selector))
highlight(element)
element.click()

def login_username_selector = "div#login.popupbox form#load_form input[name='username']"
element = driver.findElement(By.cssSelector(login_username_selector))
highlight(element)
element.sendKeys('sergueik')

login_password_selector = "div#login.popupbox form#load_form input[type='password'][name='password']"
element = driver.findElement(By.cssSelector(login_password_selector))
highlight(element)
element.sendKeys('<PASSWORD>')

login_button_selector = "div#login.popupbox form#load_form [value='Submit']"
element = driver.findElement(By.cssSelector(login_button_selector))
highlight(element)
element.submit()

// wait until Login lightbox is visible 
(new WebDriverWait(driver, 10)).until(new ExpectedCondition() {
     public Boolean apply(WebDriver d) {
 // printErr(d.findElements(By.cssSelector('div#login.popupbox')).size() )
return (d.findElements(By.cssSelector('div#login.popupbox')).size() == 0)
     }
})

println('Page title is: ' + driver.getTitle())
driver.quit()

