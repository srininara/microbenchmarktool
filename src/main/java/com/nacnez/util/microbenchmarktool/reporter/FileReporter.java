package com.nacnez.util.microbenchmarktool.reporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.nacnez.util.microbenchmarktool.ExecutionReporter;
import com.nacnez.util.microbenchmarktool.reporter.format.OutputFormat;

public class FileReporter extends AbstractReporter implements ExecutionReporter {

	public FileReporter(OutputFormat outputFormat, boolean reportProgress, String fileName) throws Exception {
		super(outputFormat,reportProgress,new PrintWriter(new BufferedWriter(new FileWriter(fileName))));
	}

	@Override
	protected void closeWriter() {
		writer.close();
	}


}
