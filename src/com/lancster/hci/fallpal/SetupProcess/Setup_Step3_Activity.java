package com.lancster.hci.fallpal.SetupProcess;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.lancster.hci.fallpal.R;
import com.lancster.hci.fallpal.ListAdapters.IncidentContactAdapter;
import com.lancster.hci.fallpal.basicClasses.Contact;
import com.lancster.hci.fallpal.basicClasses.IncidentContact;

public class Setup_Step3_Activity extends Activity {

	ArrayList<IncidentContact> iContacts;
	SavingStuff saveme;
	int myCategory;
	Contact temp;
	IncidentContactAdapter iContactAdapter;
	
	TextView topLabel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_step3);
		
		saveme = new SavingStuff(this);
		myCategory = this.getIntent().getExtras().getInt("category");
		
		temp = saveme.getTempContact();
		
		topLabel = (TextView) findViewById(R.id.IListIncident);
		topLabel.setText(setLabel());
		
		if (temp != null) {
			updateLocalList();
		} 	else {
			iContacts = new ArrayList<IncidentContact>();
		}
		
		initIContactList();
	}

	private String setLabel() {
		return myCategory == 0 ? "Fall" : myCategory == 1 ? "Irregular Heartrate" : myCategory == 2 ? "No Connection" : "Wrong category";
	}

	private void updateLocalList() {
		Log.i("Step3", "My category is " + myCategory);
		switch (myCategory) {
		case 0:
			iContacts = temp.getFallContacts();
			break;
		
		case 1:
			iContacts = temp.getHeartRateContacts();
			break;

		case 2:
			iContacts = temp.getNoConnContacts();
			break;
		default:
			break;
		}
		
		Collections.sort(iContacts);
	}

	private void initIContactList() {
		iContactAdapter = new IncidentContactAdapter(this, iContacts);
		ListView iContactList = (ListView) findViewById(R.id.IncidentContactList);
		iContactList.setAdapter(iContactAdapter);
		registerForContextMenu(iContactList);
		
		iContactList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup_step3, menu);
		return true;
	}

	/**
	 * this has to be called whenever @iContacts is edited within this 
	 * Activity
	 */
	private void saveListToContact() {
		switch (myCategory) {
		case 0:
			temp.setFallContacts(iContacts);
			break;
		
		case 1:
			temp.setHeartRateContacts(iContacts);
			break;

		case 2:
			temp.setNoConnContacts(iContacts);
			break;
		default:
			break;
		}
		
		saveme.saveTempContact(temp);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.addIContact:
			Intent intent = new Intent(this, Setup_Step4_Activity.class);
			intent.putExtra("category", myCategory);
			startActivityForResult(intent, myCategory);
			return true;

		case R.id.IContactListDone:
			setResult(RESULT_OK);
			finish();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == myCategory && resultCode == RESULT_OK) {
			temp = saveme.getTempContact();
			updateLocalList();
			iContactAdapter.notifyDataSetChanged();
			System.out.println("FINISHED");
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inf = getMenuInflater();
		inf.inflate(R.menu.ilist_context_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo ainfo = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.deleteIContact:
			iContacts.remove(ainfo.position);
			updateLocalList();
			iContactAdapter.notifyDataSetChanged();
			saveListToContact();
			return true;

		default:
			return super.onContextItemSelected(item);
		}
	}
}
