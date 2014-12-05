package com.lancster.hci.fallpal.SetupProcess;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lancster.hci.fallpal.MainActivity;
import com.lancster.hci.fallpal.R;
import com.lancster.hci.fallpal.basicClasses.Contact;

public class Setup_Step1_Activity extends Activity {
	private Contact temp;
	
	private SavingStuff saveme;
	
	private TextView nameField;
	private TextView pwField;
	private TextView phonefield;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_step1);
		
		nameField = (TextView) findViewById(R.id.step1name);
		pwField = (TextView) findViewById(R.id.step1pw);
		phonefield = (TextView) findViewById(R.id.step1phone);
		
		saveme = new SavingStuff(this);
		
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
		readFields();
		saveme.saveTempContact(temp);
	}

	private void readFields() {
		temp.setName(nameField.getText().toString());
		temp.setPhone(phonefield.getText().toString());		
	}

	private void fillForm() {
		nameField.setText(temp.getName());
		phonefield.setText(temp.getPhone());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup_step1, menu);
		menu.findItem(R.id.step12back).setEnabled(false);
		menu.findItem(R.id.step12proceed).setEnabled(true);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.step12proceed) {
			if (fieldsNotEmpty()) {
				readFields();
			saveme.saveTempContact(temp);
			Intent intent = new Intent(this, Setup_Step2_Activity.class);
			startActivity(intent);
			return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	private boolean fieldsNotEmpty() {
		if (nameField.getText().length() > 0 && pwField.getText().length() > 0 && phonefield.getText().length() > 0) {
			return true;
		} else {
			Toast.makeText(this, R.string.form_not_complete, Toast.LENGTH_SHORT).show();
		}
		return false;
	}
}
