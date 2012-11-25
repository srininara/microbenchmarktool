package com.nacnez.util.microbenchmarktool.core;

import com.nacnez.util.microbenchmarktool.OutputType;
import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;
import com.nacnez.util.microbenchmarktool.TimedTask;

public class SimpleOutput implements TaskExecutionOutput {
	
	private TimedTask task;
	private OutputType type;
	private int iteration;
	private long executionTimeInMilliSecs;

	SimpleOutput(TimedTask task, OutputType type, int iteration, long executionTimeInMilliSecs) {
		this.task = task;
		this.type = type;
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

	@Override
	public OutputType type() {
		return type;
	}

}
