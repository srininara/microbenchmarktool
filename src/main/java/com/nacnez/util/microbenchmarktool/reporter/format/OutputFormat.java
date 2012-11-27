package com.nacnez.util.microbenchmarktool.reporter.format;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;

public interface OutputFormat {

	String getTaskOutput(TaskExecutionOutput output);

	String getAverage(TaskExecutionOutput output, DescriptiveStatistics stats);

}
