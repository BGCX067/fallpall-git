package com.lancster.hci.fallpal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class AddNewDialog extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
		b.setTitle(R.string.add_dialog_title);
		b.setItems(new String[] {getResources().getString(R.string.add_create_new), getResources().getString(R.string.add_create_existing)}, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				sendResult(which);
			}
		});
		
		return b.create();
	}
	
	private void sendResult(int pos) {
		Intent intent = new Intent("createAction");
		intent.putExtra("actionValue", pos);
		Log.i("NewFragment","send intent");
		LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
	}
}
