package com.github.sergueik.jprotractor.scripts;

/**
 * @author Carlos Alexandro Becker (caarlos0@gmail.com)
 * @author Serguei Kouzmine (kouzmine_serguei@yahoo.com)
 */

public final class WaitForAngular implements Script {
	@Override
	public String content() {
		return new Loader("waitForAngular").content();
	}
}
