package example;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.rrd4j.ConsolFun;
import org.rrd4j.DsType;
import org.rrd4j.core.RrdDb;
import org.rrd4j.core.RrdMemoryBackendFactory;
import java.lang.IllegalArgumentException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.equalTo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.RowId;

// https://bitbucket.org/xerial/sqlite-jdbc
// https://docs.oracle.com/javase/tutorial/jdbc/basics/sqlrowid.html

public class App {

	private static final Random r = new Random();
	private static Connection connection = null;
	private static String osName = getOSName();
	public static final int INVALID_OPTION = 42;
	private Map<String, String> flags = new HashMap<>();
	private static Map<String, List<String>> dsMap = new HashMap<>();

	private static boolean debug = false;
	private static boolean save = false;
	private static boolean verifylinks = false;
	private final static Options options = new Options();
	private static CommandLineParser commandLineparser = new DefaultParser();
	private static CommandLine commandLine = null;

	public static void main(String args[]) throws ParseException {
		options.addOption("h", "help", false, "Help");
		options.addOption("d", "debug", false, "Debug");
		options.addOption("s", "save", false, "Save");
		options.addOption("s", "save", false, "Save");
		options.addOption("p", "path", true, "Path to scan");
		options.addOption("v", "verifylinks", false,
				"verify file links that are found during scan");
		options.addOption("r", "reject", true, "folder(s) to reject");
		commandLine = commandLineparser.parse(options, args);
		if (commandLine.hasOption("h")) {
			help();
		}
		if (commandLine.hasOption("d")) {
			debug = true;
		}
		if (commandLine.hasOption("verifylinks")) {
			verifylinks = true;
		}

		if (commandLine.hasOption("save")) {
			save = true;
		}
		String path = commandLine.getOptionValue("path");
		if (path == null) {
			System.err.println("Missing required argument: path");
			return;
		}
		String collect = commandLine.getOptionValue("collect");
		String reject = commandLine.getOptionValue("reject");

		try {
			List<String> collectFolders = collect == null ? new ArrayList<>()
					: Arrays.asList(collect.split(","));
			List<String> rejectFolders = reject == null ? new ArrayList<>()
					: Arrays.asList(reject.split(","));
			if (collectFolders.size() != 0 || rejectFolders.size() != 0) {
				dsMap = listFilesDsNames(path, collectFolders, rejectFolders);
			} else {
				dsMap = listFilesDsNames(path);
			}
		} catch (IOException e) {
		}

		if (save) {
			createTable();
			saveData(dsMap);
			displayData();
		}
		if (debug) {
			System.err.println("Done: " + path);
		}

	}

	private static void displayData() {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);

			ResultSet rs = statement.executeQuery(
					"SELECT DISTINCT fname, ds FROM cache ORDER BY fname, ds");
			while (rs.next()) {
				System.err.println("fname = " + rs.getString("fname") + "\t" + "ds = "
						+ rs.getString("ds"));
			}
			statement.close();
			statement = connection.createStatement();
			statement.executeUpdate("delete from cache");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	private static void createTable() {

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite::memory:");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			String sql = String.format(
					"CREATE TABLE IF NOT EXISTS %s "
							+ "(id INT PRIMARY KEY NOT NULL, ds CHAR(50) NOT NULL, fname CHAR(50) NOT NULL)",
					"cache");
			statement.executeUpdate(sql);
			statement.close();

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e.getMessage());
			return;
		}
	}

	private static void saveData(Map<String, List<String>> dsMap) {
		dsMap.entrySet().stream().forEach(o -> {
			String fname = o.getKey();
			o.getValue().stream().forEach(ds -> {
				try {
					Statement statement = connection.createStatement();
					statement.setQueryTimeout(30);

					PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO cache (id, fname, ds) VALUES (?, ?, ?)");
					int id = r.nextInt(1_000_000_000);
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, fname);
					preparedStatement.setString(3, ds);
					preparedStatement.execute();

				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}

			});
		});

	}

	@SuppressWarnings("unused")
	private static Map<String, List<String>> listFilesDsNames(String path,
			List<String> collectFolders, List<String> rejectFolders)
			throws IOException {

		final List<Path> result = new ArrayList<>();
		final Map<String, List<String>> dsMap = new HashMap<>();
		Path basePath = Paths.get(path);
		final String basePathUri = new URL(
				getDataFileUri(basePath.toAbsolutePath().toString())).getFile() + "/";
		System.err.println("Scanning path: " + basePathUri);
		List<Path> folders = new ArrayList<>();
		// Probably quite sub-optimal
		try (Stream<Path> walk = Files.walk(basePath)) {

			folders = walk.filter(Files::isDirectory).filter(o -> {
				String key = o.getFileName().toString();
				System.err.println("inspect: " + key);
				boolean status = true;
				if ((collectFolders.size() > 0 && !collectFolders.contains(key))
						|| rejectFolders.size() > 0 && rejectFolders.contains(key)) {
					status = false;
				}
				System.err.println("status: " + status);
				return status;
			}).collect(Collectors.toList());
		}

		for (Path folder : folders) {
			Stream<Path> walk = Files.walk(folder);
			walk.filter(Files::isRegularFile)
					.filter(o -> o.getFileName().toString().matches(".*rrd$"))
					.forEach(o -> {
						System.err.println("add: " + o.getFileName().toString());
						result.add(o);
					});
		}
		result.stream().forEach(o -> {
			try {
				List<String> dsList = new ArrayList<>();
				URL url = new URL(getDataFileUri(o.toAbsolutePath().toString()));
				final String key = url.getFile().replaceFirst(basePathUri, "")
						.replaceAll("/", ":").replaceFirst(".rrd$", "");
				System.err.println("Reading RRD file: " + key);
				RrdDb rrd = RrdDb.getBuilder().setPath("test")
						.setRrdToolImporter(url.getFile())
						.setBackendFactory(new RrdMemoryBackendFactory()).build();
				for (int cnt = 0; cnt != rrd.getDsCount(); cnt++) {
					String ds = rrd.getDatasource(cnt).getName();
					System.err.println(String.format("ds[%d]= \"%s\"", cnt, ds));
					assertThat(ds, notNullValue());
					dsList.add(ds);
				}
				dsMap.put(key, dsList);
			} catch (IllegalArgumentException | IOException e) {
				System.err.println("Exception (ignored): " + e.toString());
			}
		});
		assertThat(dsMap, notNullValue());
		return dsMap;
	}

	private static Map<String, List<String>> listFilesDsNames(String path)
			throws IOException {
		/*		return listFilesDsNames(path, new ArrayList<String>(),
						new ArrayList<String>());
						*/

		final Map<String, List<String>> dsMap = new HashMap<>();
		Path basePath = Paths.get(path);
		// NOTE: do not use File.separator
		final String basePathUri = new URL(
				getDataFileUri(basePath.toAbsolutePath().toString())).getFile() + "/";
		System.err.println("Scanning path: " + basePathUri);
		// origin:
		// https://github.com/mkyong/core-java/blob/master/java-io/src/main/java/com/mkyong/io/api/FilesWalkExample.java
		// sub-optimal
		List<Path> result;
		List<Path> result2;
		//
		try (Stream<Path> walk = Files.walk(basePath)) {
			result = walk.filter(Files::isRegularFile)
					.filter(o -> o.getFileName().toString().matches(".*rrd$"))
					.collect(Collectors.toList());
		}
		// NOTE: streams are not meant to be reused
		if (verifylinks) {
			try (Stream<Path> walk = Files.walk(basePath)) {
				result2 = walk.filter(Files::isSymbolicLink).filter(o -> {
					try {
						Path targetPath = Files.readSymbolicLink(o.toAbsolutePath());
						System.err.println("Testing link " + o.getFileName().toString()
								+ " target path " + targetPath.toString());

						File target = new File(String.format("%s/%s",
								o.getParent().toAbsolutePath(), targetPath.toString()));
						if (target.exists() && target.isFile())
							System.err.println("Valid link " + o.getFileName().toString()
									+ " target path " + target.getCanonicalPath());
						return true;
					} catch (IOException e) {
						// fall through
					}
					return false;
				}).filter(o -> o.getFileName().toString().matches(".*rrd$"))
						.collect(Collectors.toList());
			}
		}
		// NOTE: streams are not meant to be reused
		if (debug) {
			System.err.println("RRD File paths: "
					+ result.stream().map(o -> o.toAbsolutePath().toString())
							.collect(Collectors.toList()).toString());
			return null;
		} else {
			result.stream().forEach(o -> {
				try {
					List<String> dsList = new ArrayList<>();
					URL url = new URL(getDataFileUri(o.toAbsolutePath().toString()));
					final String key = url.getFile().replaceFirst(basePathUri, "")
							.replaceAll("/", ":").replaceFirst(".rrd$", "");
					System.err.println("Reading RRD file: " + key);
					RrdDb rrd = RrdDb.getBuilder().setPath("test")
							.setRrdToolImporter(url.getFile())
							.setBackendFactory(new RrdMemoryBackendFactory()).build();
					for (int cnt = 0; cnt != rrd.getDsCount(); cnt++) {
						String ds = rrd.getDatasource(cnt).getName();
						System.err.println(String.format("ds[%d]= \"%s\"", cnt, ds));
						assertThat(ds, notNullValue());
						dsList.add(ds);
					}
					dsMap.put(key, dsList);
				} catch (IllegalArgumentException | IOException e) {
					System.err.println("Exception (ignored): " + e.toString());
				}
			});
			assertThat(dsMap, notNullValue());
			return dsMap;
		}

	}

	private static String getOSName() {
		if (osName == null) {
			osName = System.getProperty("os.name").toLowerCase();
			if (osName.startsWith("windows")) {
				osName = "windows";
			}
		}
		return osName;
	}

	private static String getDataFileUri(String dataFilePath) {
		return osName.equals("windows")
				? "file:///" + dataFilePath.replaceAll("\\\\", "/")
				: "file://" + dataFilePath;
	}

	public static void help() {
		System.exit(1);
	}

}
