package com.nacnez.util.microbenchmarktool.reporter.format;

import static com.nacnez.util.microbenchmarktool.reporter.format.FormatUtil.roundTwoDecimals;
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
		StringBuilder builder = new StringBuilder(
				parent.getStats(output, stats));
		builder.append("\n");
		builder.append(roundTwoDecimals(stats.getPercentile(50)));
		builder.append("\t");
		builder.append(roundTwoDecimals(stats.getPercentile(95)));
		builder.append("\t");
		builder.append(roundTwoDecimals(stats.getStandardDeviation()));
		builder.append("\t");
		builder.append(stats.getMin());
		builder.append("\t");
		builder.append(stats.getMax());
		builder.append("\n");
		builder.append("\n");
		return builder.toString();
	}

}
