package SetupProcess;

import java.util.ArrayList;

import com.lancster.hci.fallpal.R;
import com.lancster.hci.fallpal.R.id;
import com.lancster.hci.fallpal.R.layout;
import com.lancster.hci.fallpal.R.menu;

import basicClasses.IncidentContact;

import ListAdapters.IncidentContactAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Setup_Step3_Activity extends Activity {

	ArrayList<IncidentContact> iContacts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_step3);
		
		initIContact();
	}

	private void initIContact() {
		iContacts = new ArrayList<IncidentContact>();
		IncidentContactAdapter iContactAdapter = new IncidentContactAdapter(this, iContacts);
		ListView iContactList = (ListView) findViewById(R.id.IncidentContactList);
		iContactList.setAdapter(iContactAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup_step2, menu);
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
