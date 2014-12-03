package com.lancster.hci.fallpal;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class SetupNewActivity extends FragmentActivity {

	SetupPagesAdapter sPageAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_new);
		
		List<Fragment> sFragments = getFragments();
		Log.i("SetupNewActivity", "got fragments " + sFragments.size());
		sPageAdapter = new SetupPagesAdapter(getSupportFragmentManager(), sFragments);
			
		ActionBar ab = getActionBar();
		
		ViewPager vp = (ViewPager) findViewById(R.id.setupViewPager);
		vp.setAdapter(sPageAdapter);
	}

	private List<Fragment> getFragments() {
		List<Fragment> fList = new ArrayList<Fragment>();
		
		fList.add(SetupFragment.newInstance("Step1"));
		fList.add(SetupFragment.newInstance("Step2"));
		fList.add(SetupFragment.newInstance("Step3"));
		
		Log.i("SetupNewActivity", "created Fragments");
		
		return fList;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup_new, menu);
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
