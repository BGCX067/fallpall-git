package com.lancster.hci.fallpal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SetupFragment extends Fragment {
	
	
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

	public static final SetupFragment newInstance(String message) {
		Log.i("SetupFragment", "new sf " + message);
		
		SetupFragment sf = new SetupFragment();
		Bundle b = new Bundle(1);
		b.putString(EXTRA_MESSAGE, message);
		sf.setArguments(b);
		return sf;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		String message = getArguments().getString(EXTRA_MESSAGE);
		View v = inflater.inflate(R.layout.setupfragment_layout, container, false);
//		TextView mView = (TextView) v.findViewById(R.id.setup_fragment_label);
//		mView.setText(message);
		
		
		return v;
	}
}
