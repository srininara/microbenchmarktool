package com.nacnez.util.microbenchmarktool;

import com.nacnez.util.microbenchmarktool.core.SimpleExecutor;
import com.nacnez.util.microbenchmarktool.reporter.StdOutReporter;
import com.nacnez.util.microbenchmarktool.reporter.format.DescriptiveOutputFormat;
import com.nacnez.util.microbenchmarktool.reporter.format.OutputFormat;
import com.nacnez.util.microbenchmarktool.reporter.format.SimpleOutputFormat;

public abstract class MicroBenchmarkTool {
	
	public static TimedTaskExecutor newSimpleExecutor() {
		return new SimpleExecutor();
	}
	
	public static ExecutionReporter newStandardOutputReporter() {
		return createStandardOutputReporter(new DescriptiveOutputFormat(),false);
	}

	public static ExecutionReporter newStandardOutputReporterWithProgress() {
		return createStandardOutputReporter(new DescriptiveOutputFormat(),true);
	}
	
	public static ExecutionReporter newSimpleStandardOutputReporter() {
		return createStandardOutputReporter(new SimpleOutputFormat(), false);
	}
	
	private static ExecutionReporter createStandardOutputReporter(OutputFormat outputFormat,boolean reportProgress) {
		try {
			return new StdOutReporter(outputFormat, reportProgress);
		} catch (Exception e) {
			throw new MicroBenchmarkToolException(CANNOT_CREATE_STD_OUT_REPORTER,e);
		}
	}
	
	private static final String CANNOT_CREATE_STD_OUT_REPORTER = "Cannot create standard out reporter";

}
