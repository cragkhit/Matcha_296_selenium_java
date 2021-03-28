package example;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.Test;

import example.Grok;
import example.GrokCompiler;
import example.Match;

public class GrokDocumentationTest {
	private static final boolean debug = false;
	private static Object[] keywordArray = new Object[] { "COMBINEDAPACHELOG",
			"COMMONAPACHELOG", "clientip", "ident", "auth", "timestamp", "MONTHDAY",
			"MONTH", "YEAR", "TIME", "HOUR", "MINUTE", "SECOND", "INT", "verb",
			"httpversion", "rawrequest", "request", "response", "bytes", "referrer",
			"agent" };

	@SuppressWarnings("deprecation")
	@Test
	public void assureCodeInReadmeWorks() {
		GrokCompiler grokCompiler = GrokCompiler.newInstance();
		grokCompiler.registerDefaultPatterns();

		final Grok grok = grokCompiler.compile("%{COMBINEDAPACHELOG}");

		String log = "112.169.19.192 - - [06/Mar/2013:01:36:30 +0900] \"GET / HTTP/1.1\" 200 44346 \"-\" "
				+ "\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.22 (KHTML, like Gecko) "
				+ "Chrome/25.0.1364.152 Safari/537.22\"";

		Match gm = grok.match(log);

		/* Get the map with matches */
		final Map<String, Object> capture = gm.capture();

		Assertions.assertThat(capture).hasSize(22);
		if (debug)
			capture.keySet().stream().forEach(System.err::println);
		assertTrue(new HashSet<Object>(Arrays.asList(keywordArray))
				.containsAll(new HashSet<Object>(capture.keySet())));

		Arrays.asList(keywordArray).stream()
				.forEach(o -> assertThat(capture.keySet(), hasItem((String) o)));
		assertThat(new HashSet<Object>(capture.keySet()),
				containsInAnyOrder(keywordArray));
		assertTrue(new HashSet<Object>(capture.keySet())
				.containsAll(new HashSet<Object>(Arrays.asList(keywordArray))));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test2() {
		GrokCompiler grokCompiler = GrokCompiler.newInstance();
		grokCompiler.registerPatternFromClasspath("/patterns/patterns");

		final Grok grok = grokCompiler.compile("%{COMBINEDAPACHELOG}");

		String log = "112.169.19.192 - - [06/Mar/2013:01:36:30 +0900] \"GET / HTTP/1.1\" 200 44346 \"-\" "
				+ "\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.22 (KHTML, like Gecko) "
				+ "Chrome/25.0.1364.152 Safari/537.22\"";

		Match gm = grok.match(log);

		/* Get the map with matches */
		final Map<String, Object> capture = gm.capture();

		Assertions.assertThat(capture).hasSize(22);
		capture.keySet().stream().forEach(System.err::println);
		assertTrue(new HashSet<Object>(Arrays.asList(keywordArray))
				.containsAll(new HashSet<Object>(capture.keySet())));

		Arrays.asList(keywordArray).stream()
				.forEach(o -> assertThat(capture.keySet(), hasItem((String) o)));
		// NOTE: fails with this test, but not with
		assertThat(new HashSet<Object>(capture.keySet()),
				containsInAnyOrder(keywordArray));
		assertTrue(new HashSet<Object>(capture.keySet())
				.containsAll(new HashSet<Object>(Arrays.asList(keywordArray))));
	}
}
