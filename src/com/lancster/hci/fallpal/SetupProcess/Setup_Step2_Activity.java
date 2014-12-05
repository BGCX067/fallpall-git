package com.lancster.hci.fallpal.SetupProcess;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lancster.hci.fallpal.R;
import com.lancster.hci.fallpal.ListAdapters.IncidentListAdapter;
import com.lancster.hci.fallpal.basicClasses.Contact;

public class Setup_Step2_Activity extends Activity {

	String[] incidents;
	ListView inciList;
	
	SavingStuff saveme;
	Contact temp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_step2);
		incidents = getResources().getStringArray(R.array.incidents);
		saveme = new SavingStuff(this);
		temp = saveme.getTempContact();
		initList();
	}

	private void initList() {
		inciList = (ListView) findViewById(R.id.incidentList);
		
		List<Integer> inci = new ArrayList<Integer>();
		inci.add(temp.getFallContacts().size());
		inci.add(temp.getHeartRateContacts().size());
		inci.add(temp.getNoConnContacts().size());
		
		System.out.println(inci.size());
		IncidentListAdapter inciAd = new IncidentListAdapter(this, inci);
		inciList.setAdapter(inciAd);
		
		inciList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup_step1, menu);
		menu.findItem(R.id.step12back).setEnabled(true);
		menu.findItem(R.id.step12proceed).setEnabled(true);
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
}
