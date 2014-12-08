package com.lancster.hci.fallpal.basicClasses;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import android.location.Location;
import android.text.InputFilter.LengthFilter;
import android.util.Log;

import com.lancster.hci.fallpal.R;
import com.lancster.hci.fallpal.R.integer;

public class Contact {
	private String name;
	private int status;
	private String phone;
	
	private ArrayList<IncidentContact> fallContacts;
	private ArrayList<IncidentContact> noConnContacts;
	private ArrayList<IncidentContact> heartRateContacts;
	
	private Location homeLoc;
	
	private String address;
	
	public Contact(String contactName) {
		this.setName(contactName);
		this.status = R.integer.STATUS_UNKNOWN;
		this.fallContacts = new ArrayList<IncidentContact>();
		this.noConnContacts = new ArrayList<IncidentContact>();
		this.heartRateContacts = new ArrayList<IncidentContact>();
		
		Location temp = new Location("Reverse");
		temp.setLatitude(0.0);
		temp.setLongitude(0.0);
		this.homeLoc = temp;
		
		this.address = "";
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<IncidentContact> getFallContacts() {
		return fallContacts;
	}

	public void addFallContact(IncidentContact fallContact) {
		this.fallContacts.add(fallContact);
		Log.i("Contact", "Saved new Fall Contact, length is now " + getFallContacts().size());
	}

	public ArrayList<IncidentContact> getNoConnContacts() {
		return noConnContacts;
	}

	public void addtNoConnContact(IncidentContact noConnContact) {
		this.noConnContacts.add(noConnContact);
		Log.i("Contact", "Saved new Conn Contact, length is now " + getNoConnContacts().size());
	}

	public ArrayList<IncidentContact> getHeartRateContacts() {
		return heartRateContacts;
	}

	public void addHeartRateContact(IncidentContact heartRateContact) {
		this.heartRateContacts.add(heartRateContact);
		Log.i("Contact", "Saved new Heart Contact, length is now "+ getHeartRateContacts().size());
	}

	public Location getHomeLoc() {
		return homeLoc;
	}

	public void setHomeLoc(Location homeLoc) {
		this.homeLoc = homeLoc;
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

	public void setFallContacts(ArrayList<IncidentContact> fallContacts) {
		this.fallContacts = fallContacts;
	}

	public void setNoConnContacts(ArrayList<IncidentContact> noConnContacts) {
		this.noConnContacts = noConnContacts;
	}

	public void setHeartRateContacts(ArrayList<IncidentContact> heartRateContacts) {
		this.heartRateContacts = heartRateContacts;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
