package com.nacnez.util.microbenchmarktool.core;

import com.nacnez.util.microbenchmarktool.ExecutionTimeCollector;
import com.nacnez.util.microbenchmarktool.MicroBenchmarkToolException;
import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;
import com.nacnez.util.microbenchmarktool.TimedTask;
import com.nacnez.util.microbenchmarktool.TimedTaskExecutor;

public class SimpleExecutor implements TimedTaskExecutor {
	
	ExecutionTimeCollector collector;
	
	public void needs(ExecutionTimeCollector collector) {
		this.collector = collector;
	}

	public void execute(TimedTask task) {
		this.execute(task,DEFAULT_REPEATS);
	}

	public void execute(TimedTask task, int repeats) {
		collectorMustBePresent();
		taskMustBeValid(task);
		for (int i=0;i<repeats;i++) {
			TimedTask localTask = task.hasAnyKindOfState()?task.clone():task;
	        long startTime = System.currentTimeMillis();
			localTask.doTask();
			long finishedTimeInMilliSecs = (System.currentTimeMillis()-startTime);
			collector.collect(createOutput(task,i,finishedTimeInMilliSecs));
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

	private void collectorMustBePresent() {
		if (collector == null) {
			throw new MicroBenchmarkToolException(COLLECTOR_REQUIRED);
		}
	}

	
	

	private static final String COLLECTOR_REQUIRED = "Executor needs the collector before execute";
	private static final String INVALID_TASK = "Executor can't execute a null task";
	private static final int DEFAULT_REPEATS = 1;

}
