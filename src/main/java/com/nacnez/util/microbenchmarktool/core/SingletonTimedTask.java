package com.nacnez.util.microbenchmarktool.core;

import com.nacnez.util.microbenchmarktool.TimedTask;

public abstract class SingletonTimedTask implements TimedTask{

	private static final long serialVersionUID = 1L;
	private String name;
	private String completedExecutionMessage;

	public SingletonTimedTask(String name) {
		this(name,"");
	}
	
	public SingletonTimedTask(String name, String completedExecutionMessage) {
		this.name = name;
		this.completedExecutionMessage = completedExecutionMessage;
	}
	
	public String name() {
		return name;
	}
	
	public String completedExecutionMessage() {
		return completedExecutionMessage;
	}
	
	public boolean hasAnyKindOfState() {
		return false;
	}
	
	public TimedTask clone() {
		return this;
	}
}
