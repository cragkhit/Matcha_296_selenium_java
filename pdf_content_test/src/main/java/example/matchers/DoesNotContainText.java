package example.matchers;

import org.hamcrest.Description;

import example.PDF;

public class DoesNotContainText extends PDFMatcher {
  private final String substring;

  public DoesNotContainText(String substring) {
    this.substring = substring;
  }

  @Override
  protected boolean matchesSafely(PDF item) {
    return !reduceSpaces(item.text).contains(reduceSpaces(substring));
  }

  @Override
  protected void describeMismatchSafely(PDF item, Description mismatchDescription) {
    mismatchDescription.appendText("was \"").appendText(reduceSpaces(item.text)).appendText("\"");
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a PDF not containing ").appendValue(reduceSpaces(substring));
  }
}
