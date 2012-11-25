package com.nacnez.util.microbenchmarktool.reporter.format;

import com.nacnez.util.microbenchmarktool.TaskExecutionOutput;

public interface OutputFormat {

	String getTaskOutput(TaskExecutionOutput output);

	String getAverage(TaskExecutionOutput output, long sum, int count);

}
