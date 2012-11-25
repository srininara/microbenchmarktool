package com.nacnez.util.microbenchmarktool.core;

import com.nacnez.util.microbenchmarktool.TimedTask;

public abstract class PrototypeTimedTask implements TimedTask {

	private static final long serialVersionUID = 1L;
	private String name;

	public PrototypeTimedTask(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public boolean hasAnyKindOfState() {
		return true;
	}
	
	public abstract TimedTask clone();

}
