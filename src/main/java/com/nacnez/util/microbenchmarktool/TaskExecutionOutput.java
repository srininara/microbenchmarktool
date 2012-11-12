package com.nacnez.util.microbenchmarktool;

public interface TaskExecutionOutput {
	TimedTask task();
	int iteration();
	long executionTimeInMilliSecs();
}
