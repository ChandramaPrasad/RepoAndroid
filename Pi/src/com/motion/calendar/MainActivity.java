package com.motion.calendar;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Jobs;
import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;
import com.motion.alarm.AlarmActivity;
import com.motion.pi.R;
import com.mustafaferhan.MFCalendarView;
import com.mustafaferhan.onMFCalendarViewListener;

public class MainActivity extends CustomActionBar {

	MFCalendarView mf;
	private Toast toast = null;
	private ListView eventListView;
	static final int READ_BLOCK_SIZE = 100;
	private String skb;
	private String username = "";
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private ProgressDialog progressDialog;
	private ArrayList<Jobs> jobArrayList;
	private NotificationAdapter notificationAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_main_activity);
		jobArrayList = new ArrayList<Jobs>();
		eventListView = (ListView) findViewById(R.id.eventListView);
		notificationAdapter = new NotificationAdapter();

		/**
		 * Method to make the json request to get the jobs details.
		 */
		makeJobsListRequest();

		setadapterToListView();

		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

		mf = (MFCalendarView) findViewById(R.id.mFCalendarView);

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
				View someView = findViewById(R.id.background);
				// This method use to change background color when user select
				// from look and feel.
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
		}

		try {
			FileInputStream fileIn = openFileInput("mytextfile.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];

			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				username += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		mf.setOnCalendarViewListener(new onMFCalendarViewListener() {

			public void onDisplayedMonthChanged(int month, int year,
					String monthStr) {

				StringBuffer bf = new StringBuffer().append(" month:")
						.append(month).append(" year:").append(year)
						.append(" monthStr: ").append(monthStr);

				Toast.makeText(MainActivity.this, bf.toString(),
						Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onDateChanged(String date) {

				Toast.makeText(MainActivity.this, "onDateChanged:" + date,
						Toast.LENGTH_SHORT).show();
			}
		});

		/**
		 * you can set calendar date anytime
		 * */
		// mf.setDate("2014-02-19");

		/**
		 * calendar events samples
		 * */

		// Log.e("", "locale:" + Util.getLocale());
	}

	private void makeJobsListRequest() {
		// TODO Auto-generated method stub

		String urljsonArry = "http://166.62.81.118:18080/SpringRestCrud/adminnotification/allladminnotes/"
				+ 1 + "";
		JsonArrayRequest req = new JsonArrayRequest(urljsonArry,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						try {
							// Parsing json array response
							// loop through each json object
							for (int i = 0; i < response.length(); i++) {

								JSONObject obj = response.getJSONObject(i);

								Jobs jobs = new Jobs();
								jobs.setAdvdetails(obj.getString("advdetails"));
								jobs.setReqdate(obj.getString("reqdate"));
								jobs.setIdadm(obj.getLong("idadm"));
								jobs.setDestuserid(obj.getLong("destuserid"));

								// add job details data into List here.
								jobArrayList.add(jobs);

							}
							// sending joblist List to notifiaction method to
							// show notification when launch the application.

							notificationAdapter.notifyDataSetChanged();

							setDateForNotification(jobArrayList);

						} catch (Exception e) {
							e.printStackTrace();
							// Toast.makeText(getApplicationContext(),
							// "Error: " + e.getMessage(),
							// Toast.LENGTH_LONG).show();
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// VolleyLog.d(TAG, "Error: " + error.getMessage());
						// Toast.makeText(getApplicationContext(),
						// error.getMessage(), Toast.LENGTH_SHORT).show();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);

	}

	/**
	 * This method use to set method to show notification to calendar.
	 * 
	 * @param jobArrayList2
	 */
	protected void setDateForNotification(ArrayList<Jobs> jobArrayList) {
		// TODO Auto-generated method stub
		ArrayList<String> eventDays = new ArrayList<String>();
		for (int i = 0; i < jobArrayList.size(); i++) {

			eventDays.add(jobArrayList.get(i).getReqdate().toString());
		}
		System.out.println("Events dates:" + eventDays);
		// eventDays.add(Util.getTomorrow());
		// eventDays.add(Util.getCurrentDate());

		mf.setEvents(eventDays);

	}

	/**
	 * This method use to set the adapter to ListView.
	 */
	private void setadapterToListView() {
		// TODO Auto-generated method stub

		eventListView.setAdapter(notificationAdapter);

	}

	// This adapter user to show the notifiaction to user when application get
	private class NotificationAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return jobArrayList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			if (convertView == null) {

				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.notification_listview_layout, null);

			}

			TextView eventTextView = (TextView) convertView
					.findViewById(R.id.eventTextView);

			eventTextView.setText(jobArrayList.get(position).getAdvdetails());
			final TextView dateTextView = (TextView) convertView
					.findViewById(R.id.dateTextView);
			dateTextView.setText(jobArrayList.get(position).getReqdate());

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					Intent intent = new Intent(MainActivity.this,
							AlarmActivity.class);
					startActivity(intent);

				}
			});

			return convertView;
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (toast != null) {
			toast.cancel();

		}
	}

}
