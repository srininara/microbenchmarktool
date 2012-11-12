package com.nacnez.util.microbenchmarktool.reporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.nacnez.util.microbenchmarktool.ExecutionReporter;

public class FileReporter extends AbstractReporter implements ExecutionReporter {

	public FileReporter(String fileName, boolean reportProgress) throws Exception {
		this.reportProgress = reportProgress;
		this.writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
	}


}
