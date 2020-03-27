package example;
/**
 * Copyright 2014 - 2019 Serguei Kouzmine
 */

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
// https://self-learning-java-tutorial.blogspot.com/2018/03/hamcrest-arraycontaininginanyorder.html
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.hasItems;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import example.CommandLineParser;

/**
 * Unit Tests for CommandLineParser 
 * @author: Serguei Kouzmine (kouzmine_serguei@yahoo.com)
 */

public class CommandLineParserTest {

	private static boolean debug = true;
	private static CommandLineParser commandLineParser;

	@BeforeClass
	public static void load() {
		commandLineParser = new CommandLineParser();
	}

	@BeforeClass
	public static void convertSetsToArrays() {

	}

	// @Ignore
	@Test
	public void blankArgumensTest() {
		commandLineParser.parse(new String[] {});
		if (debug) {
			System.err
					.println("Flags: " + Arrays.asList(commandLineParser.getFlags()));
		}
		assertThat(commandLineParser.getNumberOfFlags(), is(0));
	}

	// @Ignore
	@Test
	public void argumentCountsTest() {
		final String[] argsArray = new String[] { "-a", "42", "-b", "41" };
		commandLineParser.saveFlagValue("a");
		commandLineParser.parse(argsArray);
		assertThat(commandLineParser.getNumberOfArguments(), is(1));
		if (debug) {
			System.err.println(
					"Arguments: " + Arrays.asList(commandLineParser.getArguments()));
		}
		assertThat(commandLineParser.getNumberOfFlags(), is(2));

		if (debug) {
			System.err
					.println("Flags: " + Arrays.asList(commandLineParser.getFlags()));
		}
	}

	// @Ignore
	@Test
	public void argumentNamesValuesTest() {

		final String[] argsArray = new String[] { "-a", "42", "-b", "41" };
		commandLineParser.saveFlagValue("a");
		commandLineParser.parse(argsArray);
		assertThat(commandLineParser.hasFlag("a"), is(true));
		assertThat(commandLineParser.getFlagValue("a"), notNullValue());
		//
		System.err.println(
				"Arguments: " + Arrays.asList(commandLineParser.getArguments()));
	}

}