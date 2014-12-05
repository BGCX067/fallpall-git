package com.lancster.hci.fallpal.ListAdapters;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lancster.hci.fallpal.R;

public class IncidentListAdapter extends ArrayAdapter<Integer> {
	private List<Integer> incInfo;
	private Context context;
	
	private String[] inc;
	
	public IncidentListAdapter(Context context, List<Integer> values) {
		super(context, R.layout.incident_list_item, values);
		this.context = context;
		this.incInfo = values;
		
		inc = context.getResources().getStringArray(R.array.incidents);
		
		Log.i("IListAd","found array " + inc.length);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater la = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = la.inflate(R.layout.incident_list_item, parent, false);
		}
		
		TextView incName = (TextView) convertView.findViewById(R.id.incidentTitle);
		TextView incNr = (TextView) convertView.findViewById(R.id.nrOfActions);
		
		incName.setText(inc[position]);
		incNr.setText(Integer.toString(incInfo.get(position)));
		
		Log.i("IListAd","inserted " + inc[position]  + " " + incInfo.get(position));
		
		return convertView;
	}
	
}
