package com.lancster.hci.fallpal;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<Contact> {
	private List<Contact> cList;
	private Context context;
	
	public ContactAdapter(Context context, List<Contact> contacts) {
		super(context, R.layout.clistitem, contacts);
		this.cList = contacts;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater la = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = la.inflate(R.layout.clistitem, parent, false);
		}
		
		TextView name = (TextView) convertView.findViewById(R.id.contactName);
		TextView status = (TextView) convertView.findViewById(R.id.contactStatus);
		
		Contact c = cList.get(position);
		name.setText(c.getName());
		status.setText(c.getStatus());
		
		return convertView;
		
	}
}
