package com.lancster.hci.fallpal;

import java.util.ArrayList;

import basicClasses.Contact;

import ListAdapters.ContactAdapter;
import SetupProcess.Setup_Step1_Activity;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {
	
	private ArrayList<Contact> myContacts; 
	private BroadcastReceiver bRec = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			int selected = intent.getExtras().getInt("actionValue");
			if (selected < myContacts.size()) {
				startSetupProcess(selected);
			} else {
				Toast.makeText(MainActivity.this, "Contact Index ouf of bounds", Toast.LENGTH_SHORT).show();
			}
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalBroadcastManager.getInstance(this).registerReceiver(bRec, new IntentFilter("createAction"));
        initContactList();
    }

    protected void startSetupProcess(int selected) {
		Intent intent = new Intent(this, Setup_Step1_Activity.class);
		intent.putExtra("getExisting", selected);
		startActivity(intent);
	}

	private void initContactList() {
    	myContacts = new ArrayList<Contact>();
    	addTestEntries();
    	
    	ContactAdapter conAd = new ContactAdapter(this, myContacts);
        ListView contactList = (ListView) findViewById(R.id.contactList);
        contactList.setAdapter(conAd);
        
        contactList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(MainActivity.this, myContacts.get(position).getName(), Toast.LENGTH_SHORT).show();
			}
        	
		});
        registerForContextMenu(contactList);
    }
    
    private void addTestEntries() {
    	myContacts.add(new Contact("Adi"));
    	myContacts.add(new Contact("Cuthbert"));
    }
    
    public void addContact(String name) {
    	myContacts.add(new Contact(name));
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        switch (id) {
		case R.id.action_settings:
			Toast.makeText(MainActivity.this, "GoTo Settings", Toast.LENGTH_SHORT).show();
            return true;
            
		case R.id.add_contact:
			showAddPopup();
			return true;
			
		default:
			return true;
		}
    }


	private void showAddPopup() {
		AddNewDialog andy = new AddNewDialog();
		FragmentManager fm = getFragmentManager();
		andy.show(fm, "itsmee");
	}

	public ArrayList<Contact> getMyContacts() {
		return myContacts;
	}


	public void setMyContacts(ArrayList<Contact> myContacts) {
		this.myContacts = myContacts;
	}
}
