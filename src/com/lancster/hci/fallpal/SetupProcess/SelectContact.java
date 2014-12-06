package com.lancster.hci.fallpal.SetupProcess;

import com.lancster.hci.fallpal.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;

public class SelectContact extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
		ab.setTitle(R.string.setup_add_inci_contact_1_title);
		
		
		return super.onCreateDialog(savedInstanceState);
	}
}
