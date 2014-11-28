package com.lancster.hci.fallpal;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
	
	private ArrayList<Contact> myContacts; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initContactList();
    }

    private void initContactList() {
    	myContacts = new ArrayList<Contact>();
    	
    	ContactAdapter conAd = new ContactAdapter(this, myContacts);
        ListView contactList = (ListView) findViewById(R.id.contactList);
        contactList.setAdapter(conAd);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	public ArrayList<Contact> getMyContacts() {
		return myContacts;
	}


	public void setMyContacts(ArrayList<Contact> myContacts) {
		this.myContacts = myContacts;
	}
}
