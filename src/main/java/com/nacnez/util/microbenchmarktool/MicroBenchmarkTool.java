package com.nacnez.util.microbenchmarktool;

import com.nacnez.util.microbenchmarktool.core.SimpleExecutor;
import com.nacnez.util.microbenchmarktool.reporter.StdOutReporter;

public abstract class MicroBenchmarkTool {
	
	public static TimedTaskExecutor newSimpleExecutor() {
		return new SimpleExecutor();
	}
	
	public static ExecutionReporter newStandardOutputReporter() {
		return createStandardOutputReporter(false);
	}

	public static ExecutionReporter newStandardOutputReporterWithProgress() {
		return createStandardOutputReporter(true);
	}
	
	private static ExecutionReporter createStandardOutputReporter(boolean reportProgress) {
		try {
			return new StdOutReporter(reportProgress);
		} catch (Exception e) {
			throw new MicroBenchmarkToolException(CANNOT_CREATE_STD_OUT_REPORTER,e);
		}
	}
	
	private static final String CANNOT_CREATE_STD_OUT_REPORTER = "Cannot create standard out reporter";

}
