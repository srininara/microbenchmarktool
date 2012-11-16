package com.nacnez.util.microbenchmarktool.core;

import com.nacnez.util.microbenchmarktool.ExecutionReporter;
import com.nacnez.util.microbenchmarktool.MicroBenchmarkToolException;
import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;
import com.nacnez.util.microbenchmarktool.TimedTask;
import com.nacnez.util.microbenchmarktool.TimedTaskExecutor;

public class SimpleExecutor implements TimedTaskExecutor {

	ExecutionReporter reporter;

	public TimedTaskExecutor with(ExecutionReporter reporter) {
		this.reporter = reporter;
		return this;
	}

	public TimedTaskExecutor execute(TimedTask task) {
		this.execute(task, DEFAULT_REPEATS);
		return this;
	}

	public TimedTaskExecutor execute(TimedTask task, int repeats) {
		reporterMustBePresent();
		taskMustBeValid(task);
		for (int i = 0; i < repeats; i++) {
			TimedTask localTask = task.hasAnyKindOfState() ? task.clone()
					: task;
			long startTime = System.currentTimeMillis();
			localTask.doTask();
			long finishedTimeInMilliSecs = (System.currentTimeMillis() - startTime);
			reporter.collect(createOutput(localTask, i, finishedTimeInMilliSecs));
		}
		return this;
	}

	public void report() throws MicroBenchmarkToolException {
		reporterMustBePresent();
		try {
			reporter.report();
		} catch (Exception e) {
			throw new MicroBenchmarkToolException(PROBLEM_WITH_REPORTING, e);
		}
	}

	private TaskExecutionOutput createOutput(TimedTask task, int iteration,
			long executionTimeInMilliSecs) {
		return new SimpleOutput(task, iteration, executionTimeInMilliSecs);
	}

	private void taskMustBeValid(TimedTask task) {
		if (task == null) {
			throw new MicroBenchmarkToolException(INVALID_TASK);
		}
	}

	private void reporterMustBePresent() {
		if (reporter == null) {
			throw new MicroBenchmarkToolException(REPORTER_REQUIRED);
		}
	}

	private static final String REPORTER_REQUIRED = "Executor needs the collector before execute";
	private static final String INVALID_TASK = "Executor can't execute a null task";
	private static final String PROBLEM_WITH_REPORTING = "Problem with reporting";
	private static final int DEFAULT_REPEATS = 1;

}
