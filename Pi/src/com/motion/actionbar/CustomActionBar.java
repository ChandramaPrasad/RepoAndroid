package com.motion.actionbar;

import info.androidhive.customlistviewvolley.model.AnsNotificationCount;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.motion.notification.queationanswer.QuestionNotificationActivity;
import com.motion.pi.PiAnswers;
import com.motion.pi.R;
import com.motion.pi.ServiceHandler;
import com.squareup.picasso.Picasso;

@SuppressWarnings("deprecation")
public abstract class CustomActionBar extends ActionBarActivity {
	private ActionBar actionBar;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private FrameLayout screenBodyLayout;
	private ImageView homeIconImageView;
	private TextView notificationTextView;
	private ImageView profileImageView;
	private TextView usernameTextView;
	private String url = "http://166.62.81.118:18080/SpringRestCrud/question/getnewqcount/";
	private String urlans = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getnewanswercount/";
	private static final int READ_BLOCK_SIZE = 100;
	private String username = "";
	private Context context;
	private String userid = "";
	private Toast toast = null;
	private String mNotificationsCount;
	private LinearLayout notificationLinearLayout;
	private PiAnswers piAnswers;
	private TextView answerTextView;
	private ArrayList<AnsNotificationCount> ansNotificationCountArrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_bar_main);

		initViews();
		setCustomTitleBar(R.layout.custom_action_bar);

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

		try {
			FileInputStream fileIn = openFileInput("mytextfiles.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				userid += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// calling the asyntask to load the question notifiation.
		new GetCount().execute();
		// calling the asyntask to load the answer notitfation.
		new GetCountAnswer().execute();

		usernameTextView.setText(username);
		link += userid + ".jpg";
		// new LoadImage().execute(link);
		// if the image not present the it will set the default image
		Picasso.with(this)
				.load(link)
				.placeholder(
						context.getResources().getDrawable(
								R.drawable.profilepic_sml))
				.into(profileImageView);

		homeIconImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(CustomActionBar.this,
						PiAnswers.class);
				startActivity(intent);
				finish();

			}
		});

		notificationLinearLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(CustomActionBar.this,
						QuestionNotificationActivity.class);
				startActivity(intent);

			}
		});

	}

	/***
	 * This method use to initialise the views
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		context = this;
		piAnswers = new PiAnswers();
		screenBodyLayout = (FrameLayout) findViewById(R.id.screenBodyLayout);

		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
		ansNotificationCountArrayList = new ArrayList<AnsNotificationCount>();

	}

	/**
	 * This method use to set the custom title bar to each Activty which extends
	 * from as a CustomActionBar.
	 * 
	 * @param customActionBar
	 */
	private void setCustomTitleBar(int customActionBar) {

		// actionBar.setDisplayShowTitleEnabled(true);
		// actionBar.setDisplayHomeAsUpEnabled(false);
		// actionBar.setDisplayShowHomeEnabled(false);
		// actionBar.setDisplayShowCustomEnabled(true);
		// // actionBar.setDisplayShowTitleEnabled(false);
		// // actionBar.setBackgroundDrawable(null);
		// getActionBar().setIcon(
		// new ColorDrawable(getResources().getColor(
		// android.R.color.transparent)));

		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setCustomView(customActionBar);

		homeIconImageView = (ImageView) actionBar.getCustomView().findViewById(
				R.id.homeIconImageView);

		profileImageView = (ImageView) actionBar.getCustomView().findViewById(
				R.id.profileImageView);

		usernameTextView = (TextView) actionBar.getCustomView().findViewById(
				R.id.usernameTextView);

		notificationTextView = (TextView) actionBar.getCustomView()
				.findViewById(R.id.notificationcountTextView);
		notificationLinearLayout = (LinearLayout) actionBar.getCustomView()
				.findViewById(R.id.notificationLinearLayout);
		answerTextView = (TextView) actionBar.getCustomView().findViewById(
				R.id.ansnotification);

	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetCount extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			url += userid;

			System.out.println("URL>>>" + url);

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance

			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			mNotificationsCount = sh.makeServiceCall(url, ServiceHandler.GET);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog

			if (mNotificationsCount != null
					&& Integer.parseInt(mNotificationsCount) != 0) {

				notificationTextView.setVisibility(View.VISIBLE);
				notificationTextView.setText(mNotificationsCount);
				// notificationTextview.set

			} else {
				notificationTextView.setVisibility(View.GONE);
			}

		}
	}

	/**
	 * This asyntask use to get the answer notififation of respected question.
	 * 
	 * @author Admin
	 * 
	 */
	private class GetCountAnswer extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			urlans += userid;

			System.out.println("URL>>>" + url);

		}

		@SuppressWarnings("unchecked")
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance

			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String answResponse = sh
					.makeServiceCall(urlans, ServiceHandler.GET);

			Gson gson = new Gson();
			Type listType = new TypeToken<List<AnsNotificationCount>>() {
			}.getType();
			ansNotificationCountArrayList = (ArrayList<AnsNotificationCount>) gson
					.fromJson(answResponse, listType);

			System.out.println("ansNotification>>"
					+ ansNotificationCountArrayList);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog

			int countTotal = 0;

			if (ansNotificationCountArrayList != null) {

				for (int i = 0; i < ansNotificationCountArrayList.size(); i++) {

					int anscount = Integer
							.parseInt(ansNotificationCountArrayList.get(i)
									.getCnt().toString());

					if (anscount != 0) {

						countTotal = countTotal + anscount;

					}

				}

			}

			/**
			 * check the condition weather user having notification, if yes then
			 * show otherwise hide textview.
			 */
			if (countTotal == 0) {

				answerTextView.setVisibility(View.GONE);

			} else {

				answerTextView.setText(String.valueOf(countTotal));
			}

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
