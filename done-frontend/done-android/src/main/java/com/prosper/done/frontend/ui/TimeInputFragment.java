package com.prosper.done.frontend.ui;

import com.prosper.done.frontend.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class TimeInputFragment extends DialogFragment {

	private int fieldId;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		fieldId = getArguments().getInt("fieldId");

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();

		builder.setView(inflater.inflate(R.layout.fragment_time_input, null))
		.setPositiveButton(R.string.time_input_confirm, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				DatePicker dp = ((DatePicker) getDialog().findViewById(R.id.time_input_date));
				int year = dp.getYear();
				int month = dp.getMonth() + 1;
				int day = dp.getDayOfMonth();

				TimePicker tp = ((TimePicker) getDialog().findViewById(R.id.time_input_time));
				int hour = tp.getCurrentHour();
				int minute = tp.getCurrentMinute();
				
				((EditText) getActivity().findViewById(fieldId)).setText(
							Integer.toString(year) + "-" + 
							Integer.toString(month) + "-" + 
							Integer.toString(day) + " " + 
							Integer.toString(hour) + ":" + 
							Integer.toString(minute)
							+ ":00"
						);
			}
		})
		.setNegativeButton(R.string.time_input_cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				TimeInputFragment.this.getDialog().cancel();
			}
		});      
		return builder.create();
	}

}
