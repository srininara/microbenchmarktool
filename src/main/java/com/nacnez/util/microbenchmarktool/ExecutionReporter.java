package com.nacnez.util.microbenchmarktool;

public interface ExecutionReporter extends ExecutionTimeCollector {
	void report() throws Exception;
}
