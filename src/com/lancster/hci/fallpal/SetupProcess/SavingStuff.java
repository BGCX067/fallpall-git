package com.lancster.hci.fallpal.SetupProcess;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lancster.hci.fallpal.MainActivity;
import com.lancster.hci.fallpal.basicClasses.Contact;

public class SavingStuff {
	public static String PREFSNAME = "SHAREDPREFS";
	public static String TEMP_CONTACT = "TEMPCONTACT";
	public static String SAVED_LIST = "CONTACTLIST";
	
	private GsonBuilder gBuilder;
	private Gson gson;
	private SharedPreferences prefs;
	
	public SavingStuff(Context context) {
		gBuilder = new GsonBuilder();
        gBuilder.serializeNulls();
        gson = gBuilder.create();
        prefs =  context.getSharedPreferences(PREFSNAME, 0);
	}
	
	public boolean saveContactList(ArrayList<Contact> cList) {
		SharedPreferences.Editor edi = prefs.edit();
		edi.putString(SAVED_LIST, gson.toJson(cList));
		
		return edi.commit();
	}
	
	public ArrayList<Contact> getContactlist() {
		String savedList = prefs.getString(SAVED_LIST, "");
		ArrayList<Contact> result;
    	
    	if (savedList.equals("")) {
    		Log.i("Prefs", "No Shared Prefs found.");
    		result = new ArrayList<Contact>();
    	} else {
    		Log.i("Prefs", "Found contact list.");
    		result = gson.fromJson(prefs.getString(SAVED_LIST, ""), new TypeToken<List<Contact>>(){}.getType());
    	}
		
		return result;
	}
	
	public boolean saveTempContact (Contact tempContact) {
		SharedPreferences.Editor edi = prefs.edit();
		edi.putString(TEMP_CONTACT, gson.toJson(tempContact));
		
		return edi.commit();
	}
	
	public Contact getTempContact() {
		String savedTemp = prefs.getString(TEMP_CONTACT, "");
		Contact result;
		
		if (savedTemp.equals("")) {
			Log.i("Prefs", "No temporary Contact found.");
			result = null;
		} else {
			Log.i("Prefs", "Temporary Contact found.");
			result = gson.fromJson(savedTemp, Contact.class);
		}
		return result;
	}
}
