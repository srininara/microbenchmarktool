package com.nacnez.util.microbenchmarktool.core;

import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;
import com.nacnez.util.microbenchmarktool.TimedTask;

public class SimpleOutput implements TaskExecutionOutput {
	
	private TimedTask task;
	private int iteration;
	private long executionTimeInMilliSecs;

	SimpleOutput(TimedTask task,int iteration, long executionTimeInMilliSecs) {
		this.task = task;
		this.iteration = iteration;
		this.executionTimeInMilliSecs = executionTimeInMilliSecs;
	}

	public TimedTask task() {
		return task;
	}

	public int iteration() {
		return iteration;
	}

	public long executionTimeInMilliSecs() {
		return executionTimeInMilliSecs;
	}

}
