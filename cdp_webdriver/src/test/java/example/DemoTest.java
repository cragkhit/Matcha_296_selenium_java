package example;

// TODO: get rid of
import com.neovisionaries.ws.client.WebSocketException;

import example.messaging.CDPClient;
import example.messaging.MessageBuilder;
import example.messaging.ServiceWorker;

import example.utils.UIUtils;
import example.utils.Utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

public class DemoTest {
	private WebDriver driver;
	private String wsURL;
	private Utils utils;
	private UIUtils uiUtils;
	private CDPClient CDPClient;
	private ChromeDriverService chromeDriverService;

	@Before
	public void beforeTest() {
		this.utils = Utils.getInstance();
		this.uiUtils = UIUtils.getInstance();
	}

	@After
	public void afterTest() {
		if (!Objects.isNull(CDPClient))
			CDPClient.disconnect();
		utils.stopChrome();
		if (!Objects.isNull(chromeDriverService))
			chromeDriverService.stop();
	}

	// @Ignore
	@Test
	public void doFakeGeoLocation()
			throws IOException, WebSocketException, InterruptedException {
		driver = utils.launchBrowser();
		wsURL = utils.getWebSocketDebuggerUrl();
		CDPClient = new CDPClient(wsURL);
		int id = Utils.getInstance().getDynamicID();
		CDPClient.sendMessage(
				MessageBuilder.buildGeoLocationMessage(id, 37.422290, -122.084057));
		// google HQ
		utils.waitFor(3);
		driver.navigate().to("https://www.google.com.sg/maps");
		uiUtils
				.findElement(By.cssSelector(
						"div[class *='widget-mylocation-button-icon-common']"), 120)
				.click();
		utils.waitFor(10);
		uiUtils.takeScreenShot();
	}

	@Ignore
	@Test
	public void doNetworkTracking()
			throws IOException, WebSocketException, InterruptedException {
		driver = utils.launchBrowser(true);
		wsURL = utils.getWebSocketDebuggerUrl();
		CDPClient = new CDPClient(wsURL);
		int id = Utils.getInstance().getDynamicID();
		CDPClient.sendMessage(MessageBuilder.buildNetWorkEnableMessage(id));
		driver.navigate().to("http://petstore.swagger.io/v2/swagger.json");
		utils.waitFor(3);
		String message = CDPClient.getResponseMessage("Network.requestWillBeSent");
		JSONObject jsonObject = new JSONObject(message);
		String reqId = jsonObject.getJSONObject("params").getString("requestId");
		int id2 = Utils.getInstance().getDynamicID();
		CDPClient
				.sendMessage(MessageBuilder.buildGetResponseBodyMessage(id2, reqId));
		String networkResponse = CDPClient.getResponseBodyMessage(id2);
		System.out.println("Here is the network Response: " + networkResponse);
		utils.waitFor(1);
		uiUtils.takeScreenShot();
	}

	@Ignore
	@Test
	public void doResponseMocking() throws Exception {
		driver = utils.launchBrowser();
		wsURL = utils.getWebSocketDebuggerUrl();
		CDPClient = new CDPClient(wsURL);
		int id = Utils.getInstance().getDynamicID();
		CDPClient.sendMessage(MessageBuilder
				.buildRequestInterceptorPatternMessage(id, "*", "Document"));
		CDPClient.mockResponse("This is mocked!!!");
		driver.navigate().to("http://petstore.swagger.io/v2/swagger.json");
		utils.waitFor(3);
	}

	@Ignore
	@Test
	public void doFunMocking() throws Exception {
		byte[] fileContent = FileUtils.readFileToByteArray(
				new File(System.getProperty("user.dir") + "/data/durian.png"));
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		driver = utils.launchBrowser();
		wsURL = utils.getWebSocketDebuggerUrl();
		CDPClient = new CDPClient(wsURL);
		int id = Utils.getInstance().getDynamicID();
		CDPClient.sendMessage(
				MessageBuilder.buildRequestInterceptorPatternMessage(id, "*", "Image"));
		CDPClient.mockFunResponse(encodedString);
		driver.navigate().to("https://sg.carousell.com/");
		utils.waitFor(300);
	}

	@Ignore
	@Test
	public void doClearSiteData() throws Exception {
		String URL = "https://framework.realtime.co/demo/web-push";
		driver = utils.launchBrowser();
		wsURL = utils.getWebSocketDebuggerUrl();
		CDPClient CDPClient = new CDPClient(wsURL);
		driver.navigate().to(URL);
		driver.manage().deleteAllCookies();
		int id = Utils.getInstance().getDynamicID();
		CDPClient.sendMessage(MessageBuilder.buildClearBrowserCookiesMessage(id));
		CDPClient.sendMessage(MessageBuilder.buildClearDataForOriginMessage(id,
				"https://framework.realtime.co"));
		utils.waitFor(3);
	}

	// Page.handleJavaScriptDialog

	// @Ignore
	@Test
	public void doElementScreenshot() throws Exception {
		String URL = "https://www.google.com/";
		driver = utils.launchBrowser();
		wsURL = utils.getWebSocketDebuggerUrl();
		CDPClient = new CDPClient(wsURL);
		driver.navigate().to(URL);
		WebElement logo = uiUtils.findElement(By.cssSelector("img#hplogo"), 5);
		int x = logo.getLocation().getX();
		int y = logo.getLocation().getY();
		int width = logo.getSize().getWidth();
		int height = logo.getSize().getHeight();
		int scale = 1;
		int id = Utils.getInstance().getDynamicID();
		CDPClient.sendMessage(MessageBuilder.buildTakeElementScreenShotMessage(id,
				x, y, height, width, scale));
		String encodedBytes = CDPClient.getResponseDataMessage(id);
		byte[] bytes = Base64.getDecoder().decode(encodedBytes);
		File f = new File(System.getProperty("user.dir") + "/target/img.png");
		if (f.exists())
			f.delete();
		Files.write(f.toPath(), bytes);
		uiUtils.takeScreenShot();
	}

	@Ignore
	// No message received
	@Test
	public void doprintPDF() throws Exception {
		String URL = "https://www.wikipedia.com/";
		driver = utils.launchBrowser();
		wsURL = utils.getWebSocketDebuggerUrl();
		CDPClient = new CDPClient(wsURL);
		driver.navigate().to(URL);
		int id = Utils.getInstance().getDynamicID();
		CDPClient.sendMessage(MessageBuilder.printPDFMessage(id));
		String encodedBytes = CDPClient.getResponseDataMessage(id);
		byte[] bytes = Base64.getDecoder().decode(encodedBytes);
		String start_time = (new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"))
				.format(new Date());
		String imageName = "cdp_img_" + start_time + ".pdf";
		File f = new File(System.getProperty("user.dir") + "/target/" + imageName);
		if (f.exists())
			f.delete();
		Files.write(f.toPath(), bytes);
	}

	@Ignore
	@Test
	public void doFullPageScreenshot() throws Exception {
		String URL = "https://www.meetup.com/";
		driver = utils.launchBrowser();
		wsURL = utils.getWebSocketDebuggerUrl();
		CDPClient = new CDPClient(wsURL);
		driver.navigate().to(URL);
		long docWidth = (long) uiUtils
				.executeJavaScript("return document.body.offsetWidth");
		long docHeight = (long) uiUtils
				.executeJavaScript("return document.body.offsetHeight");
		int scale = 1;
		int id = Utils.getInstance().getDynamicID();
		CDPClient.sendMessage(MessageBuilder.buildTakeElementScreenShotMessage(id,
				0, 0, docHeight, docWidth, scale));
		String encodedBytes = CDPClient.getResponseDataMessage(id);
		byte[] bytes = Base64.getDecoder().decode(encodedBytes);
		String start_time = (new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"))
				.format(new Date());
		String imageName = "cdp_img_" + start_time + ".png";
		File f = new File(System.getProperty("user.dir") + "/target/" + imageName);
		if (f.exists())
			f.delete();
		Files.write(f.toPath(), bytes);
		uiUtils.takeScreenShot();
	}

	@Ignore
	@Test
	public void doServiceWorkerTesting() throws Exception {
		String URL = "https://www.meetup.com/";
		driver = utils.launchBrowser();
		wsURL = utils.getWebSocketDebuggerUrl();
		CDPClient = new CDPClient(wsURL);
		int id = Utils.getInstance().getDynamicID();
		CDPClient.sendMessage(MessageBuilder.buildServiceWorkerEnableMessage(id));
		driver.navigate().to(URL);
		ServiceWorker serviceWorker = CDPClient.getServiceWorker(URL, 10,
				"activated");
		System.out.println(serviceWorker.toString());
		Assert.assertEquals(serviceWorker.getStatus(), "activated");
	}

	@Ignore
	@Test
	public void doPushNotificationTesting() throws Exception {
		String URL = "https://framework.realtime.co/demo/web-push";
		driver = utils.launchBrowser();
		wsURL = utils.getWebSocketDebuggerUrl();
		CDPClient = new CDPClient(wsURL);
		int id = Utils.getInstance().getDynamicID();
		CDPClient.sendMessage(MessageBuilder.buildServiceWorkerEnableMessage(id));
		driver.navigate().to(URL);
		utils.waitFor(2);
		utils.waitFor(3);
		ServiceWorker serviceWorker = CDPClient.getServiceWorker(URL, 5,
				"activated");
		int id1 = Utils.getInstance().getDynamicID();
		int id2 = Utils.getInstance().getDynamicID();

		CDPClient.sendMessage(MessageBuilder.buildEnableLogMessage(id1));
		CDPClient.sendMessage(MessageBuilder.buildEnableRuntimeMessage(id2));

		CDPClient.sendMessage(MessageBuilder.buildServiceWorkerInspectMessage(id2,
				serviceWorker.getVersionId()));
		WebElement elem = uiUtils.findElement(By.cssSelector("button#sendButton"),
				3);
		uiUtils.scrollToElement(elem);
		elem.click();
		utils.waitFor(3);
		utils.waitFor(60);
	}
}
