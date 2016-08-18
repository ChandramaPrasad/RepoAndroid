/* Copyright 2014 Sheldon Neilson www.neilson.co.za
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.motion.alarm;

import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.motion.actionbar.CustomActionBar;
import com.motion.pi.R;
//import View.OnClickListener;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;

public class AlarmActivity extends CustomActionBar {

	// ImageButton newButton;
	ListView mathAlarmListView;
	AlarmListAdapter alarmListAdapter;
	Button setalarm;
	private String username = "";
	private static final int READ_BLOCK_SIZE = 100;
	private TextView nameTextView;
	private String skb;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.alarm_activity);
		setalarm = (Button) findViewById(R.id.set_alarm);
		mathAlarmListView = (ListView) findViewById(R.id.list);
		mathAlarmListView.setLongClickable(true);

		try {
			FileInputStream fileIn = openFileInput("mytextfile.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];

			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				username += readstring;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileInputStream newfile = openFileInput("Skies.txt");
			InputStreamReader InputRead = new InputStreamReader(newfile);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			skb = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				skb += readStrings;
				View someView = findViewById(R.id.alarmBackground);
				// This method use to change background color when user select
				// from look and feel.
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
		}

		setalarm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						AlarmPreferencesActivity.class);
				startActivity(i);
			}

		});
		mathAlarmListView
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> adapterView,
							View view, int position, long id) {
						view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
						final Alarm alarm = (Alarm) alarmListAdapter
								.getItem(position);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								AlarmActivity.this);
						alertDialogBuilder.setTitle("Delete");
						alertDialogBuilder
								.setMessage("Are you sure want to delete?");

						// setup a dialog window
						alertDialogBuilder
								.setCancelable(false)
								.setPositiveButton("Yes",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
												Database.init(AlarmActivity.this);
												Database.deleteEntry(alarm);
												AlarmActivity.this
														.callMathAlarmScheduleService();

												updateAlarmList();

											}
										})
								.setNegativeButton("No",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
												dialog.dismiss();
											}
										});

						// create an alert dialog
						AlertDialog alert = alertDialogBuilder.create();
						alert.show();

						return true;
					}
				});

		callMathAlarmScheduleService();

		alarmListAdapter = new AlarmListAdapter(this);
		this.mathAlarmListView.setAdapter(alarmListAdapter);
		mathAlarmListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
				Alarm alarm = (Alarm) alarmListAdapter.getItem(position);
				Intent intent = new Intent(AlarmActivity.this,
						AlarmPreferencesActivity.class);
				intent.putExtra("alarm", alarm);
				startActivity(intent);
			}

		});
	}

	protected void callMathAlarmScheduleService() {
		// TODO Auto-generated method stub
		Intent mathAlarmServiceIntent = new Intent(this,
				AlarmServiceBroadcastReciever.class);
		sendBroadcast(mathAlarmServiceIntent, null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		// menu.findItem(R.id.menu_item_save).setVisible(false);
		// menu.findItem(R.id.menu_item_delete).setVisible(false);
		return result;
	}

	@Override
	protected void onPause() {
		// setListAdapter(null);
		Database.deactivate();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateAlarmList();
	}

	public void updateAlarmList() {
		Database.init(AlarmActivity.this);
		final List<Alarm> alarms = Database.getAll();
		alarmListAdapter.setMathAlarms(alarms);

		runOnUiThread(new Runnable() {
			public void run() {
				// reload content
				AlarmActivity.this.alarmListAdapter.notifyDataSetChanged();
				if (alarms.size() > 0) {
					findViewById(R.id.empty).setVisibility(View.INVISIBLE);
				} else {
					findViewById(R.id.empty).setVisibility(View.VISIBLE);
				}
			}
		});
	}

	public void onClick(View v) {
		if (v.getId() == R.id.checkBox_alarm_active) {
			CheckBox checkBox = (CheckBox) v;
			Alarm alarm = (Alarm) alarmListAdapter.getItem((Integer) checkBox
					.getTag());
			alarm.setAlarmActive(checkBox.isChecked());
			Database.update(alarm);
			AlarmActivity.this.callMathAlarmScheduleService();
			if (checkBox.isChecked()) {
				Toast.makeText(AlarmActivity.this,
						alarm.getTimeUntilNextAlarmMessage(), Toast.LENGTH_LONG)
						.show();
			}
		}

	}

	class AlarmListAdapter extends BaseAdapter {

		private AlarmActivity alarmActivity;
		private List<Alarm> alarms = new ArrayList<Alarm>();

		String ALARM_FIELDS[] = { Database.COLUMN_ALARM_ACTIVE,
				Database.COLUMN_ALARM_TIME, Database.COLUMN_ALARM_DAYS };

		public AlarmListAdapter(AlarmActivity alarmActivity) {
			this.alarmActivity = alarmActivity;
			// Database.init(alarmActivity);
			// alarms = Database.getAll();
		}

		@Override
		public int getCount() {
			return alarms.size();
		}

		@Override
		public Object getItem(int position) {
			return alarms.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup viewGroup) {
			if (null == view)
				view = LayoutInflater.from(alarmActivity).inflate(
						R.layout.alarm_list_element, null);

			Alarm alarm = (Alarm) getItem(position);

			CheckBox checkBox = (CheckBox) view
					.findViewById(R.id.checkBox_alarm_active);
			checkBox.setChecked(alarm.getAlarmActive());
			checkBox.setTag(position);
			// checkBox.setOnClickListener(alarmActivity);

			TextView alarmTimeView = (TextView) view
					.findViewById(R.id.textView_alarm_time);
			alarmTimeView.setText(alarm.getAlarmTimeString());
			alarmTimeView.setTextColor(Color.BLACK);

			TextView alarmDaysView = (TextView) view
					.findViewById(R.id.textView_alarm_days);
			alarmDaysView.setText(alarm.getRepeatDaysString());
			alarmDaysView.setTextColor(Color.BLACK);

			return view;
		}

		public List<Alarm> getMathAlarms() {
			return alarms;
		}

		public void setMathAlarms(List<Alarm> alarms) {
			this.alarms = alarms;
		}
		// public void onClick(View v) {
		// if (v.getId() == R.id.set_alarm) {
		// Intent i=new
		// Intent(getApplicationContext(),AlarmPreferencesActivity.class);
		// startActivity(i);
		// }
		// }

	}

}