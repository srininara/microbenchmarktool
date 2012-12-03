package com.nacnez.util.microbenchmarktool.reporter.format;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;

public class StatRichOutputFormatDecorator implements OutputFormat {

	private OutputFormat parent;

	public StatRichOutputFormatDecorator(OutputFormat parent) {
		this.parent = parent;
	}
	
	@Override
	public String getTaskOutput(TaskExecutionOutput output) {
		return parent.getTaskOutput(output);
	}

	@Override
	public String getStats(TaskExecutionOutput output,
			DescriptiveStatistics stats) {
		StringBuilder builder = new StringBuilder(parent.getStats(output, stats));
		builder.append("median: ").append(stats.getPercentile(50)).append("; standard deviation: ").append(stats.getStandardDeviation());
		builder.append("; min: ").append(stats.getMin()).append("; max: ").append(stats.getMax()).append("; 95 percentile: ").append(stats.getPercentile(95));
		builder.append("; 5 percentile: ").append(stats.getPercentile(5)).append(";\n");
		return builder.toString();
	}

}
