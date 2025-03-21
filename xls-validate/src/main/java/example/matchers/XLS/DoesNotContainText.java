package example.matchers.XLS;

import org.hamcrest.Description;

import example.XLS;

public class DoesNotContainText extends XLSMatcher {

	private final String substring;

	public DoesNotContainText(String substring) {
		this.substring = reduceSpaces(substring);
	}

	@Override
	protected boolean matchesSafely(XLS item) {
		boolean contains = new ContainsText(substring).matchesSafely(item);
		return !contains;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("a XLS not containing text ")
				.appendValue(reduceSpaces(substring));
	}
}
