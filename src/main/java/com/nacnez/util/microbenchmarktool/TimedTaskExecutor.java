package com.nacnez.util.microbenchmarktool;

public interface TimedTaskExecutor {
	void needs(ExecutionTimeCollector collector);
	void execute(TimedTask task);
	void execute(TimedTask task, int repeats);
}
