package com.nacnez.util.microbenchmarktool.core;

import com.nacnez.util.microbenchmarktool.ExecutionReporter;
import com.nacnez.util.microbenchmarktool.MicroBenchmarkToolException;
import com.nacnez.util.microbenchmarktool.OutputType;
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
		doWarmup(task);
		OutputType type = OutputType.MEASUREMENT;
		for (int i = 0; i < repeats; i++) {
			executeAndMeasure(task, type, i);
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

	private void executeAndMeasure(TimedTask task, OutputType type, int i) {
		TimedTask localTask = task.hasAnyKindOfState() ? task.clone()
				: task;
		long startTime = System.currentTimeMillis();
		localTask.doTask();
		long finishedTimeInMilliSecs = (System.currentTimeMillis() - startTime);
		reporter.collect(createOutput(localTask, type, i, finishedTimeInMilliSecs));
	}

	private void doWarmup(TimedTask task) {
		if (task.idemPotent()) {
			executeAndMeasure(task, OutputType.WARMUP, 0);
		}
	}

	private TaskExecutionOutput createOutput(TimedTask task, OutputType type, int iteration,
			long executionTimeInMilliSecs) {
		return new SimpleOutput(task, type, iteration, executionTimeInMilliSecs);
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
	private static final int DEFAULT_REPEATS = 150;

}
