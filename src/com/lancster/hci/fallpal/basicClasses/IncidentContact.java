package com.lancster.hci.fallpal.basicClasses;

public class IncidentContact {
	private String name;
	private int channel;
	private long timeout;
	
	public IncidentContact(String name, int channel, long timeout) {
		this.name = name;
		this.channel = channel;
		this.timeout = timeout;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}
