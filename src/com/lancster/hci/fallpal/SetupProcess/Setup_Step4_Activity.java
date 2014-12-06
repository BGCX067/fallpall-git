package com.lancster.hci.fallpal.SetupProcess;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.lancster.hci.fallpal.R;
import com.lancster.hci.fallpal.basicClasses.Contact;
import com.lancster.hci.fallpal.basicClasses.IncidentContact;

public class Setup_Step4_Activity extends Activity {

	EditText IContactName;
	EditText IContactPhone;
	EditText IContactMail;
	EditText IContactTimeoutMins;
	EditText IContactTimeoutSecs;
	TextView MailLabel;
	TextView PhoneLabel;
	RadioGroup IMediumSelector;
	RadioButton IContactViaPhone;
	RadioButton IContactViaSMS;
	RadioButton IContactViaMail;
	ImageButton ISearchButton;
	
	SavingStuff saveme;
	IncidentContact iContact;
	Contact temp;
	
	int myIncident;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_step4);
		
		IContactName = (EditText) findViewById(R.id.IContactName);
		IContactPhone = (EditText) findViewById(R.id.IContactPhone);
		IContactMail = (EditText) findViewById(R.id.IContactMail);
		IContactTimeoutMins = (EditText) findViewById(R.id.IContactMins);
		IContactTimeoutSecs = (EditText) findViewById(R.id.IContactSecs);
		IMediumSelector = (RadioGroup) findViewById(R.id.IMediumSelector);
		IContactViaPhone = (RadioButton) findViewById(R.id.IContactViaPhone);
		IContactViaSMS = (RadioButton) findViewById(R.id.IContactViaSMS);
		IContactViaMail = (RadioButton) findViewById(R.id.IContactViaMail);
		PhoneLabel = (TextView) findViewById(R.id.phoneLabel);
		MailLabel = (TextView) findViewById(R.id.mailLabel);
		ISearchButton = (ImageButton) findViewById(R.id.ISearchContact);
		
		IContactTimeoutSecs.addTextChangedListener(new TextWatcher() {		
			
			@Override
			public void afterTextChanged(Editable s){
				correctSecs();
			}			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {}
		});
		
		IMediumSelector.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				hideItems(checkedId);				
			}
		});
		
		saveme = new SavingStuff(this);
		temp = saveme.getTempContact();
		myIncident = getIntent().getIntExtra("category", 0);
	}
	
	protected void hideItems(int checkedId) {
		if (checkedId == IContactViaMail.getId()) {
			PhoneLabel.setEnabled(false);
			IContactPhone.setEnabled(false);
			MailLabel.setEnabled(true);
			IContactMail.setEnabled(true);
			ISearchButton.setEnabled(false);
			IContactPhone.setText("");
		} else {
			PhoneLabel.setEnabled(true);
			IContactPhone.setEnabled(true);
			MailLabel.setEnabled(false);
			IContactMail.setEnabled(false);
			ISearchButton.setEnabled(true);
			IContactMail.setText("");
		}
	}

	private void correctSecs() {
		if (IContactTimeoutSecs.getText().length() > 0) {
			if (Integer.parseInt(IContactTimeoutSecs.getText().toString()) > 59) {
				IContactTimeoutSecs.setText("59");
			}
			if (Integer.parseInt(IContactTimeoutSecs.getText().toString()) < 0) {
				IContactTimeoutSecs.setText("0");
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup_step4, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.IContactDone) {
			if (checkFields()) {
				finishThis();
			} else {
				Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void finishThis() {
		setResult(RESULT_OK);
		
		int medium = -1;
		long timespan = -1;
		
		if (IContactViaPhone.isChecked()) medium = 0;
		else if (IContactViaSMS.isChecked()) medium = 1;
		else if (IContactViaMail.isChecked()) medium = 2;
		
		timespan = Long.parseLong(IContactTimeoutSecs.getText().toString());
		if (IContactTimeoutMins.getText().length() > 0) {
			timespan += Long.parseLong(IContactTimeoutMins.getText().toString());
		}
		
		IncidentContact result = new IncidentContact(IContactName.getText().toString(), medium, timespan);
		
		switch (myIncident) {
		case 0:
			temp.addFallContact(result);
			break;
			
		case 1:
			temp.addHeartRateContact(result);
			break;
		
		case 2:
			temp.addtNoConnContact(result);
			break;

		default:
			break;
		}
		Log.i("Step4", "My categpry is " + myIncident);
		
		saveme.saveTempContact(temp);		
		finish();
	}

	private boolean checkFields() {		
		if (IContactName.getText().length() > 0 && IContactTimeoutSecs.getText().length() > 0) {
			if (IContactViaPhone.isChecked() || IContactViaSMS.isChecked() ) {
				if (IContactPhone.getText().length() == 0) return false;
			} else if (IContactViaMail.isChecked()) {
				if (IContactMail.getText().length() == 0) return false;
			} else return false;
		} else return false;
		return true;
	}
}
