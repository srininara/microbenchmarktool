package com.nacnez.util.microbenchmarktool;

public interface TaskExecutionOutput {
	TimedTask task();
	OutputType type();
	int iteration();
	long executionTimeInMilliSecs();
}
