package com.nacnez.util.microbenchmarktool;

import com.nacnez.util.microbenchmarktool.core.SimpleExecutor;
import com.nacnez.util.microbenchmarktool.reporter.FileReporter;
import com.nacnez.util.microbenchmarktool.reporter.StdOutReporter;
import com.nacnez.util.microbenchmarktool.reporter.format.StatRichOutputFormatDecorator;
import com.nacnez.util.microbenchmarktool.reporter.format.VerboseOutputFormat;
import com.nacnez.util.microbenchmarktool.reporter.format.OutputFormat;
import com.nacnez.util.microbenchmarktool.reporter.format.SimpleOutputFormat;

public abstract class MicroBenchmarkTool {
	
	public static TimedTaskExecutor newSimpleExecutor() {
		return new SimpleExecutor();
	}
	
	public static ExecutionReporter newStandardOutputReporter() {
		return createStandardOutputReporter(new VerboseOutputFormat(),false);
	}

	public static ExecutionReporter newFileOutputReporter(String fileName) {
		return createFileOutputReporter(new VerboseOutputFormat(),false, fileName);
	}

	public static ExecutionReporter newStatRichStandardOutputReporter() {
		return createStandardOutputReporter(new StatRichOutputFormatDecorator(new VerboseOutputFormat()),false);
	}

	public static ExecutionReporter newStandardOutputReporterWithProgress() {
		return createStandardOutputReporter(new VerboseOutputFormat(),true);
	}
	
	public static ExecutionReporter newSimpleStandardOutputReporter() {
		return createStandardOutputReporter(new SimpleOutputFormat(), false);
	}
	
	public static ExecutionReporter newStatRichSimpleStandardOutputReporter() {
		return createStandardOutputReporter(new StatRichOutputFormatDecorator(new SimpleOutputFormat()),false);
	}

	public static ExecutionReporter newStatRichSimpleFileOutputReporter(String fileName) {
		return createFileOutputReporter(new StatRichOutputFormatDecorator(new SimpleOutputFormat()),false,fileName);
	}
	private static ExecutionReporter createStandardOutputReporter(OutputFormat outputFormat,boolean reportProgress) {
		try {
			return new StdOutReporter(outputFormat, reportProgress);
		} catch (Exception e) {
			throw new MicroBenchmarkToolException(CANNOT_CREATE_STD_OUT_REPORTER,e);
		}
	}
	
	private static ExecutionReporter createFileOutputReporter(OutputFormat outputFormat, boolean reportProgress, String fileName) {
		try {
			return new FileReporter(outputFormat, reportProgress, fileName);
		} catch (Exception e) {
			throw new MicroBenchmarkToolException(CANNOT_CREATE_FILE_OUT_REPORTER,e);
		}
		
	}
	
	private static final String CANNOT_CREATE_STD_OUT_REPORTER = "Cannot create standard out reporter";
	private static final String CANNOT_CREATE_FILE_OUT_REPORTER = "Cannot create file output reporter";

}
