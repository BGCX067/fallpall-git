package com.lancster.hci.fallpal;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatusMonitor extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status_monitor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status_monitor, menu);
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
	
	public void pushRequest(View view) {
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setText("Waiting for Steve's Reply");
		b1.setClickable(false);
		
		TextView t1 = (TextView) findViewById(R.id.TextView01);
		t1.setText("12:15pm: You nudged Steve.");
		t1.setVisibility(TextView.VISIBLE);
		
		TextView t5 = (TextView) findViewById(R.id.textView5);
		t5.setVisibility(TextView.VISIBLE);
		
		CountDownTimer ct = new CountDownTimer(7000,1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
			}
			
			@Override
			public void onFinish() {
				receiveRequest();
			}
		};
		ct.start();
	}
	
	private void receiveRequest() {
		TextView z3 = (TextView) findViewById(R.id.TextView03);
		z3.setVisibility(TextView.VISIBLE);
		
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setText("Send Steve a Nudge");
		b1.setClickable(true);
	}
}
