package com.nacnez.util.microbenchmarktool.reporter.format;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;

public class StatRichVerboseOutputFormatDecorator implements OutputFormat {

	private OutputFormat parent;

	public StatRichVerboseOutputFormatDecorator(OutputFormat parent) {
		this.parent = parent;
	}

	@Override
	public String getTaskOutput(TaskExecutionOutput output) {
		return parent.getTaskOutput(output);
	}

	@Override
	public String getStats(TaskExecutionOutput output,
			DescriptiveStatistics stats) {
		StringBuilder builder = new StringBuilder(
				parent.getStats(output, stats));
		builder.append("Median: ").append(stats.getPercentile(50))
				.append(";\n95 Percentile: ").append(stats.getPercentile(95))
				.append(";\nStandard deviation: ")
				.append(stats.getStandardDeviation());
		builder.append(";\nMin: ").append(stats.getMin()).append(";\nMax: ")
				.append(stats.getMax()).append(";\n--------------\n\n");
		return builder.toString();
	}

}
