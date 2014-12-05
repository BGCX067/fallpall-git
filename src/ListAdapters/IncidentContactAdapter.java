package ListAdapters;

import java.util.List;

import com.lancster.hci.fallpal.R;
import com.lancster.hci.fallpal.R.id;
import com.lancster.hci.fallpal.R.layout;

import basicClasses.IncidentContact;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class IncidentContactAdapter extends ArrayAdapter<IncidentContact> {
	private List<IncidentContact> iList;
	private Context context;
	
	
	public IncidentContactAdapter(Context context, List<IncidentContact> objects) {
		super(context, R.layout.incident_contact_list_tem, objects);
		this.iList = objects;
		this.context = context;
		Log.i("ICA", "context:"+context.toString());
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView contactText = (TextView) convertView.findViewById(R.id.incidentListItemContact);
		TextView inciText = (TextView) convertView.findViewById(R.id.incidentListItemIncident);
		TextView timeText = (TextView) convertView.findViewById(R.id.incidentListItemTimespan);
		TextView channelText = (TextView) convertView.findViewById(R.id.incidentListItemMedium);
		
		contactText.setText(iList.get(position).getName());
		inciText.setText(Integer.toString(iList.get(position).getChannel()));
		timeText.setText(Long.toString(iList.get(position).getTimeout()));
		channelText.setText(Integer.toString(iList.get(position).getChannel()));
		
		return convertView;
	}

}
