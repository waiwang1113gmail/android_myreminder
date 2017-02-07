package com.waiwang1113.myreminder.entity;

public class ReminderTask {
	private String name;
	
	public ReminderTask(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ReminderTask [name=" + name + "]";
	}
	
	
}
