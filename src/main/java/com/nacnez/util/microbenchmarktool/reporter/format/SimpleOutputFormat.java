package com.nacnez.util.microbenchmarktool.reporter.format;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.nacnez.util.microbenchmarktool.OutputType;
import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;

public class SimpleOutputFormat implements OutputFormat {

	@Override
	public String getAverage(TaskExecutionOutput output, DescriptiveStatistics stats) {
		StringBuilder msgBuilder = new StringBuilder();
		msgBuilder.append(stats.getMean());
		msgBuilder.append("\n");
		return msgBuilder.toString();
	}

	@Override
	public String getTaskOutput(TaskExecutionOutput output) {
		StringBuilder msgBuilder = new StringBuilder();
		if (output.task().idemPotent() && output.type().equals(OutputType.WARMUP)) {
			msgBuilder.append(output.task().name());
			msgBuilder.append(": \t");
			msgBuilder.append(output.executionTimeInMilliSecs());
			msgBuilder.append("*\t");
			return msgBuilder.toString();
		}
		if (!output.task().idemPotent() && output.iteration()==0) {
			msgBuilder.append(output.task().name());
			msgBuilder.append(": \t");
		}
		msgBuilder.append(output.executionTimeInMilliSecs());
		msgBuilder.append("\t");
		return msgBuilder.toString();
	}

}
