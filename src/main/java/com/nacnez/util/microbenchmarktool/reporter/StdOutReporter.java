package com.nacnez.util.microbenchmarktool.reporter;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.nacnez.util.microbenchmarktool.ExecutionReporter;

public class StdOutReporter extends AbstractReporter implements ExecutionReporter {

	public StdOutReporter(boolean reportProgress)
			throws Exception {
		this.reportProgress = reportProgress;
		this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	}

	@Override
	protected void closeWriter() {
		// Don't do anything since we don't want to close the standard output. The caller might be using it as well.
	}

}
