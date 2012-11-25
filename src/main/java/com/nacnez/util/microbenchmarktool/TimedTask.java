package com.nacnez.util.microbenchmarktool;

import java.io.Serializable;

public interface TimedTask extends Serializable {

	void doTask();
	
	String name();
	
	String completedExecutionMessage();
	
	boolean hasAnyKindOfState();
	
	boolean idemPotent();
	
	TimedTask clone();
}
