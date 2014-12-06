package com.lancster.hci.fallpal.SetupProcess;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_step3);
		
		saveme = new SavingStuff(this);
		myCategory = this.getIntent().getExtras().getInt("category");
		
		temp = saveme.getTempContact();
		
		if (temp != null) {
			updateList();
		} 	else {
			iContacts = new ArrayList<IncidentContact>();
		}
		
		initIContact();
	}

	private void updateList() {
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
	}
	
	private void initIContact() {
		iContactAdapter = new IncidentContactAdapter(this, iContacts);
		ListView iContactList = (ListView) findViewById(R.id.IncidentContactList);
		iContactList.setAdapter(iContactAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup_step3, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.addIContact) {
			Intent intent = new Intent(this, Setup_Step4_Activity.class);
			intent.putExtra("category", myCategory);
			startActivityForResult(intent, myCategory);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == myCategory && resultCode == RESULT_OK) {
			temp = saveme.getTempContact();
			updateList();
			iContactAdapter.notifyDataSetChanged();
			System.out.println("FINISHED");
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
