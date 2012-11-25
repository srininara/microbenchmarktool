package com.nacnez.util.microbenchmarktool.reporter;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.nacnez.util.microbenchmarktool.ExecutionReporter;
import com.nacnez.util.microbenchmarktool.reporter.format.OutputFormat;

public class StdOutReporter extends AbstractReporter implements ExecutionReporter {

	public StdOutReporter(OutputFormat outputFormat, boolean reportProgress)
			throws Exception {
		super(outputFormat,reportProgress,new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out))));
	}

	@Override
	protected void closeWriter() {
		// Don't do anything since we don't want to close the standard output. The caller might be using it as well.
	}

}
