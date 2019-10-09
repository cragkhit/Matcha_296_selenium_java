package jp.vmi.selenium.selenese;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.openqa.selenium.WebDriver;

import jp.vmi.selenium.selenese.command.AbstractCommand;
import jp.vmi.selenium.selenese.config.DefaultConfig;
import jp.vmi.selenium.selenese.config.IConfig;
import jp.vmi.selenium.selenese.inject.Binder;
import jp.vmi.selenium.selenese.result.Error;
import jp.vmi.selenium.selenese.result.Result;
import jp.vmi.selenium.testutils.TestBase;
import jp.vmi.selenium.webdriver.DriverOptions;
import jp.vmi.selenium.webdriver.DriverOptions.DriverOption;
import jp.vmi.selenium.webdriver.WebDriverManager;

import static jp.vmi.selenium.selenese.result.Success.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@SuppressWarnings("javadoc")
public class RunnerTest extends TestBase {

    @Rule
    public TemporaryFolder tmpDir = new TemporaryFolder();

    private WebDriver driver;

    @Before
    public void setup() {
        setWebDriverFactory(WebDriverManager.HTMLUNIT, new DriverOptions());
        driver = manager.get();
    }

    @Test
    public void proxyOption() throws IllegalArgumentException, IOException {
        File tmp = File.createTempFile("aaa", "test.html");
        IConfig config = new DefaultConfig("--proxy", "proxy.example.com", tmp.getAbsolutePath());
        DriverOptions opt = new DriverOptions(config);
        assertThat(opt.get(DriverOption.PROXY), is("proxy.example.com"));
    }

    @Test
    public void emptyFile() throws IOException {
        File tmp = File.createTempFile("aaa", "test.html");
        Runner runner = new Runner();
        runner.setDriver(driver);
        Result result = runner.run(tmp.getCanonicalPath());
        assertThat(result, is(instanceOf(Error.class)));
        assertThat(result.getMessage(), containsString("Not selenese script."));
    }

    @Test
    public void noSuchFile() throws IOException {
        Runner runner = new Runner();
        runner.setDriver(driver);
        Result result = runner.run("nosuchfile.html");
        assertTrue(result.isFailed());
    }

    @Test
    public void runFiles() throws IOException {
        File tmp = File.createTempFile("aaa", "test.html");
        Runner runner = new Runner();
        runner.setDriver(driver);
        runner.run(tmp.getPath(), tmp.getPath());
    }

    @Test
    public void testRetries() {
        Runner runner = new Runner();
        runner.setMaxRetries(3);
        runner.setDriver(driver);
        TestCase testCase = Binder.newTestCase(SourceType.SIDE, "", "testRetries", wsr.getBaseURL());
        testCase.addCommand(new AbstractCommand(1, "Error2Count", new String[0]) {
            private int count = 0;

            @Override
            protected Result executeImpl(Context context, String... curArgs) {
                if (++count <= 2)
                    return new Error("Error3Count: " + count);
                else
                    return SUCCESS;
            }
        });
        testCase.addCommand(new AbstractCommand(2, "ErrorAlways", new String[0]) {
            @Override
            protected Result executeImpl(Context context, String... curArgs) {
                return new Error("ErrorAlways");
            }
        });
        Result result = runner.execute(testCase);
        assertThat(result.getMessage(), is("Error: ErrorAlways"));
    }
}
