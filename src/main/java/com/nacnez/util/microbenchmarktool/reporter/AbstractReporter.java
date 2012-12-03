package com.nacnez.util.microbenchmarktool.reporter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.nacnez.util.microbenchmarktool.OutputType;
import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;
import com.nacnez.util.microbenchmarktool.reporter.format.OutputFormat;

public abstract class AbstractReporter {

	private Map<String, List<TaskExecutionOutput>> mappedOutputs = new HashMap<String, List<TaskExecutionOutput>>();
	protected boolean reportProgress;
	protected PrintWriter writer;
	private OutputFormat outputFormat;

	public AbstractReporter(OutputFormat outputFormat, boolean reportProgress,
			PrintWriter writer) {
		this.outputFormat = outputFormat;
		this.reportProgress = reportProgress;
		this.writer = writer;
	}

	public void collect(TaskExecutionOutput output) {
		List<TaskExecutionOutput> outputs = mappedOutputs.get(output.task()
				.name());
		if (outputs == null) {
			mappedOutputs.put(output.task().name(),
					new ArrayList<TaskExecutionOutput>());
			outputs = mappedOutputs.get(output.task().name());
		}
		outputs.add(output);
		if (reportProgress) {
			print(outputFormat.getTaskOutput(output));
		}
	}

	public void report() throws Exception {

		for (String taskName : mappedOutputs.keySet()) {
			TaskExecutionOutput currOutput = null;
			DescriptiveStatistics stats = new DescriptiveStatistics();

			for (TaskExecutionOutput output : mappedOutputs.get(taskName)) {
				if (OutputType.MEASUREMENT.equals(output.type())) {
					stats.addValue(output.executionTimeInMilliSecs());
					currOutput = output;
				}
				if (!reportProgress) {
					print(outputFormat.getTaskOutput(output));
				}
			}
			print(outputFormat.getStats(currOutput, stats));
		}

		closeWriter();
	}

	private void print(String output) {
		writer.print(output);
		writer.flush();
	}

	protected abstract void closeWriter();

}