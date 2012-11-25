package com.nacnez.util.microbenchmarktool.reporter.format;

import com.nacnez.util.microbenchmarktool.OutputType;
import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;

public class DescriptiveOutputFormat implements OutputFormat {

	@Override
	public String getAverage(TaskExecutionOutput output, long sum, int count) {
		long average = sum / count;
		StringBuilder msgBuilder = new StringBuilder();
		msgBuilder.append(output.task().name())
				.append(" - Average time taken is ").append(average)
				.append(" milli seconds for ").append(count)
				.append(" number of repeated runs").append("\n");
		return msgBuilder.toString();
	}

	@Override
	public String getTaskOutput(TaskExecutionOutput output) {
		StringBuilder msgBuilder = new StringBuilder();
		if (output.type().equals(OutputType.WARMUP)) {
			msgBuilder.append("Warmup Run of ").append(output.task().name())
					.append(" completed in ")
					.append(output.executionTimeInMilliSecs())
					.append(" milli seconds.").append("\n");
			return msgBuilder.toString();
		}
		msgBuilder.append("Run ").append(output.iteration() + 1).append(" of ")
				.append(output.task().name()).append(" completed in ")
				.append(output.executionTimeInMilliSecs())
				.append(" milli seconds with result as - ")
				.append(output.task().completedExecutionMessage()).append("\n");
		return msgBuilder.toString();
	}

}
