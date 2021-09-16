package example;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.NoSuchElementException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// https://en.wikipedia.org/wiki/Java_Native_Access
import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeMapped;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
// https://github.com/java-native-access/jna/blob/master/contrib/platform/src/com/sun/jna/platform/win32/Advapi32Util.java
import com.sun.jna.platform.win32.Advapi32Util;

import com.sun.jna.platform.win32.VerRsrc.VS_FIXEDFILEINFO;
// https://github.com/java-native-access/jna/blob/master/contrib/platform/src/com/sun/jna/platform/win32/WinReg.java
import com.sun.jna.platform.win32.WinReg;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

/**
 * based on Baeldung's example at https://www.baeldung.com/java-9-process-api
 */

public class App {
	private static Logger logger = LogManager.getLogger(App.class.getName());
	private static CommandLineParser commandLineparser = new DefaultParser();
	private static CommandLine commandLine = null;
	private final static Options options = new Options();
	private final static List<List<Float>> data = new ArrayList<>();

	public static void help() {
		System.exit(1);
	}

	public static void main(String[] args) {
		options.addOption("h", "help", false, "Help");
		options.addOption("d", "debug", false, "Debug");
		options.addOption("a", "action", true, "Action");
		options.addOption("p", "pid", true, "Pid");
		try {
			commandLine = commandLineparser.parse(options, args);
			if (commandLine.hasOption("h")) {
				help();
			}
			String action = commandLine.getOptionValue("action");
			if (action == null) {
				System.err.println("Missing required argument: action");

			}
			if (action.equals("cmd1")) {
				Launcher.launchCmd1();
			}
			if (action.equals("cmd2")) {
				Launcher.launchCmd2();
			}
			if (action.equals("cmd3")) {
				Launcher.launchCmd2(
						"java.exe -jar c:\\developer\\sergueik\\springboot_study\\basic-rrd4j\\target\\rrd4j-3.9-SNAPSHOT-inspector.jar");
			}
			if (action.equals("powershell")) {
				String command = "java.exe -jar c:\\developer\\sergueik\\springboot_study\\basic-rrd4j\\target\\rrd4j-3.9-SNAPSHOT-inspector.jar";
				Launcher.buildCommand(command);
				Launcher.launchPowershell1();
				sleep(10000);
				int pid = Launcher.getPid();
				Stream<ProcessHandle> liveProcesses = ProcessHandle.allProcesses();
				ProcessHandle processHandle = liveProcesses
						.filter(ProcessHandle::isAlive).filter(ph -> ph.pid() == pid)
						.findFirst().orElse(null);
				boolean status = (processHandle == null) ? false
						: processHandle.isAlive();
				logger.info("status : " + status);
			}
			if (action.equals("list")) {
				infoOfLiveProcesses();
			}
			/*
			 * if (action.equals("current")) { Process process =
			 * Process.getCurrentProcess(); getWindowsProcessId(process,
			 * logger); logger.info(process.toHandle().pid()); }
			 */
			if (action.equals("check")) {
				String resource = commandLine.getOptionValue("pid");
				if (resource == null) {
					System.err.println("Missing required argument: pid");
				} else {
					Integer pid = Integer.parseInt(resource);
					logger.info("looking pid " + pid);
					// Returns an Optional<ProcessHandle> for an existing native process.
					Optional<ProcessHandle> result = Optional.empty();
					ProcessHandle processHandle = null;
					try {
						result = ProcessHandle.of(pid);
						processHandle = result.isPresent() ? result.get() : null;
						logger.info(processHandle);
					} catch (NoSuchElementException e1) {
					}
					boolean status = (processHandle == null) ? false
							: processHandle.isAlive();
					String extraInfo = null;
					if (status)
						try {
							extraInfo = "(" + "command: "
									+ processHandle.info().command().get() + " started:"
									+ processHandle.info().startInstant().get() + " " + "pid:"
									+ processHandle.pid() + ")";
						} catch (NoSuchElementException e1) {
						}
					logger.info("Process pid (via ProcessHandle.of): " + pid + " is: "

							+ (status ? "alive" : "not alive") + " " + extraInfo);
					status = isProcessIdRunningOnWindows((int) pid);
					logger.info("Process pid (via tasklist): " + pid + " is: "
							+ (status ? "alive" : "not alive"));

					Stream<ProcessHandle> processes = ProcessHandle.allProcesses();
					processHandle = null;
					processHandle = processes.filter(o -> o.pid() == pid).findFirst()
							.orElse(null);
					status = (processHandle == null) ? false : processHandle.isAlive();
					logger.info(
							"Process pid (via ProcessHandle.allProcesses): " + pid + " is: "

									+ (status ? "alive" : "not alive"));
				}
			}
		} catch (

		ParseException e) {
		}
	}

	private static void infoOfLiveProcesses() {
		Stream<ProcessHandle> liveProcesses = ProcessHandle.allProcesses();
		liveProcesses.filter(ProcessHandle::isAlive).forEach(ph -> {
			logger.info("PID: " + ph.pid());
			logger.info("Instance: " + ph.info().startInstant());
			logger.info("User: " + ph.info().user());
		});
	}

	// https://stackoverflow.com/questions/2533984/java-checking-if-any-process-id-is-currently-running-on-windows/41489635
	public static boolean isProcessIdRunningOnWindows(int pid) {
		try {
			Runtime runtime = Runtime.getRuntime();
			String cmds[] = { "cmd", "/c", "tasklist /FI \"PID eq " + pid + "\"" };
			Process proc = runtime.exec(cmds);
			logger.info("Running the command: " + Arrays.asList(cmds));
			InputStream inputstream = proc.getInputStream();
			InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			String line;
			while ((line = bufferedreader.readLine()) != null) {
				// Search the PID matched lines single line for the sequence: " 1300 "
				// if you find it, then the PID is still running.
				if (line.contains(" " + pid + " ")) {
					return true;
				}
			}

			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Cannot query the tasklist for some reason.");
			System.exit(0);
		}

		return false;

	}

	// https://www.tabnine.com/code/java/methods/com.sun.jna.platform.win32.Kernel32/GetProcessId
	private static Long getWindowsProcessId(final Process process,
			final Logger logger) {
		/* determine the pid on windows platforms */
		try {
			Field f = process.getClass().getDeclaredField("handle");
			f.setAccessible(true);
			long handl = f.getLong(process);
			Kernel32 kernel = Kernel32.INSTANCE;
			HANDLE handle = new HANDLE();
			handle.setPointer(Pointer.createConstant(handl));
			int ret = kernel.GetProcessId(handle);
			logger.debug("Detected pid: {}", ret);
			return Long.valueOf(ret);
		} catch (final IllegalAccessException | NoSuchFieldException nsfe) {
			logger.debug("Could not find PID for child process due to {}", nsfe);
		}
		return null;
	}

	public static void sleep(Integer milliSeconds) {
		try {
			Thread.sleep((long) milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static boolean isAlive(int pid) {
		Stream<ProcessHandle> liveProcesses = ProcessHandle.allProcesses();
		ProcessHandle processHandle = liveProcesses.filter(ProcessHandle::isAlive)
				.filter(ph -> ph.pid() == pid).findFirst().orElse(null);
		return (processHandle == null) ? false : processHandle.isAlive();
	}
}
