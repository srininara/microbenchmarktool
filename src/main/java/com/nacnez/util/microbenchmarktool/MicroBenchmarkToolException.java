package com.nacnez.util.microbenchmarktool;

public class MicroBenchmarkToolException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MicroBenchmarkToolException(String message) {
		super(message);
	}

	public MicroBenchmarkToolException(String message,Throwable cause) {
		super(message,cause);
	}
}
