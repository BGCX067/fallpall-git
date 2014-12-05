package com.lancster.hci.fallpal.SetupProcess;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lancster.hci.fallpal.MainActivity;
import com.lancster.hci.fallpal.R;
import com.lancster.hci.fallpal.basicClasses.Contact;

public class Setup_Step1_Activity extends Activity {
	private GsonBuilder gBuilder;
	private Gson gson;
	private Contact temp;
	
	private SavingStuff saveme;
	
	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_step1);
		
		saveme = new SavingStuff(this);
		
		gBuilder = new GsonBuilder();
		gson = gBuilder.create();
		
		temp = saveme.getTempContact();
		
		if (temp == null) {
			temp = new Contact("");
		} else {
			fillForm();
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		saveme.saveTempContact(temp);
	}

	private void readFields() {
		// TODO Auto-generated method stub
		
	}

	private void fillForm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup_step1, menu);
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
