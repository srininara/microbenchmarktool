package com.nacnez.util.microbenchmarktool.reporter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;

public abstract class AbstractReporter {

	private Map<String,List<TaskExecutionOutput>> mappedOutputs = new HashMap<String,List<TaskExecutionOutput>>();
	protected boolean reportProgress;
	protected PrintWriter writer;

	public AbstractReporter() {
		super();
	}

	public void collect(TaskExecutionOutput output) {
		List<TaskExecutionOutput> outputs = mappedOutputs.get(output.task().name()); 
		if (outputs == null) {
			mappedOutputs.put(output.task().name(), new ArrayList<TaskExecutionOutput>());
			outputs = mappedOutputs.get(output.task().name());
		}
		outputs.add(output);
		if (reportProgress) {
			printTaskOutput(output);
		}
	}

	public void report() throws Exception {
		
		for(String taskName : mappedOutputs.keySet()) {
			int count = 0;
			long sum = 0;
			TaskExecutionOutput currOutput = null;
			for (TaskExecutionOutput output : mappedOutputs.get(taskName)) {
				count++;
				sum += output.executionTimeInMilliSecs();
				currOutput = output;
				if(!reportProgress) {
					printTaskOutput(output);
				}
			}
			if (count>1) {
				printAverage(currOutput,sum,count);
			}
		}
		
		closeWriter();
	}

	protected abstract void closeWriter();

	private void printAverage(TaskExecutionOutput output, long sum, int count) {
		long average = sum/count;
		StringBuilder msgBuilder = new StringBuilder();
		msgBuilder.append(output.task().name());
		msgBuilder.append(" - Average time taken is ");
		msgBuilder.append(average);
		msgBuilder.append(" milli seconds for ");
		msgBuilder.append(count);
		msgBuilder.append(" number of repeated runs");
		writer.println(msgBuilder.toString());
		writer.flush();
	}

	private void printTaskOutput(TaskExecutionOutput output) {
		StringBuilder msgBuilder = new StringBuilder();
		msgBuilder.append("The iteration ");
		msgBuilder.append(output.iteration());
		msgBuilder.append(" of ");
		msgBuilder.append(output.task().name());
		msgBuilder.append(" completed in ");
		msgBuilder.append(output.executionTimeInMilliSecs());
		msgBuilder.append(" milli seconds with result as - ");
		msgBuilder.append(output.task().completedExecutionMessage());
		writer.println(msgBuilder.toString());
		writer.flush();
	}

}