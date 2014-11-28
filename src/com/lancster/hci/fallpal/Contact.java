package com.lancster.hci.fallpal;

public class Contact {
	private String name;
	private int status;
	
	public Contact(String contactName) {
		this.setName(contactName);
		this.status = R.integer.STATUS_UNKNOWN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
