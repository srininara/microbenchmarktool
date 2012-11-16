package com.nacnez.util.microbenchmarktool;

public interface TimedTaskExecutor {
	TimedTaskExecutor with(ExecutionReporter reporter);
	TimedTaskExecutor execute(TimedTask task);
	TimedTaskExecutor execute(TimedTask task, int repeats);
	void report() throws MicroBenchmarkToolException;
}
