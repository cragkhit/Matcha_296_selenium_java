package example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.google.common.io.Resources;

import example.Grok;
import example.GrokCompiler;
import example.Match;
import example.exception.GrokException;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApacheTest {

	public static final String LOG_FILE = "src/test/resources/access_log";
	public static final String LOG_DIR_NASA = "src/test/resources/nasa/";

	private GrokCompiler compiler;

	@Before
	public void setup() throws Exception {
		compiler = GrokCompiler.newInstance();
		compiler
				.register(Resources.getResource(ResourceManager.PATTERNS).openStream());
	}

	@Test
	public void test001_httpd_access() throws GrokException, IOException {
		Grok grok = compiler.compile("%{COMMONAPACHELOG}");

		BufferedReader br = new BufferedReader(new FileReader(LOG_FILE));
		String line;
		System.out.println("Starting test with httpd log");
		while ((line = br.readLine()) != null) {
			Match gm = grok.match(line);
			final Map<String, Object> capture = gm.capture();
			Assertions.assertThat(capture).doesNotContainKey("Error");
		}
		br.close();
	}

	@Ignore
	@Test
	public void test002_nasa_httpd_access() throws GrokException, IOException {
		Grok grok = compiler.compile("%{COMMONAPACHELOG}");
		System.out.println("Starting test with nasa log -- may take a while");
		BufferedReader br;
		String line;
		File dir = new File(LOG_DIR_NASA);
		for (File child : dir.listFiles()) {
			br = new BufferedReader(new FileReader(LOG_DIR_NASA + child.getName()));
			while ((line = br.readLine()) != null) {
				Match gm = grok.match(line);
				final Map<String, Object> capture = gm.capture();
				Assertions.assertThat(capture).doesNotContainKey("Error");
			}
			br.close();
		}
	}

}
