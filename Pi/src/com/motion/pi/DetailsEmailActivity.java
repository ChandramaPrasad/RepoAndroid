package com.motion.pi;

import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpEntity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.motion.actionbar.CustomActionBar;

public class DetailsEmailActivity extends CustomActionBar implements
		OnClickListener {

	private static final int READ_BLOCK_SIZE = 100;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private TextView mailnameTextview;
	private TextView dateTextView;
	private TextView bodyTextView;
	private String username;
	private String mailBody;
	private String mailid;
	private ImageView iconImageView;
	private String date;
	private String profileusername = "";
	private ImageView deleteImageView;
	private String backToPage;
	private String skb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_email_layout);
		initalizeViews();
		registerEvents();

		/**
		 * Here getting the values via intent from the inbox or sent mail.
		 */

		backToPage = getIntent().getStringExtra("mail");
		mailid = getIntent().getStringExtra("mailid");
		username = getIntent().getStringExtra("username");
		mailBody = getIntent().getStringExtra("composebody");
		date = getIntent().getStringExtra("date");
		System.out.println("mailid>>>>>>" + mailid);

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
				View someView = findViewById(R.id.detailsmailback);
				LookAndFeel.lookAndFeel(skb, someView);
				// This method use to change the background color when user
				// select from the look and feel from settings.
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (username != null && mailBody != null && date != null) {

			mailnameTextview.setText(username);
			dateTextView.setText(date);
			bodyTextView.setText(mailBody);

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
				profileusername += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method use to register the events.
	 */
	private void registerEvents() {

		deleteImageView.setOnClickListener(this);

	}

	/**
	 * This method use to initialze the views.
	 */
	private void initalizeViews() {

		mailnameTextview = (TextView) findViewById(R.id.senderTextView);
		dateTextView = (TextView) findViewById(R.id.dateTextView);
		bodyTextView = (TextView) findViewById(R.id.bodyTextView);

		deleteImageView = (ImageView) findViewById(R.id.deleteImageView);

	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

		case R.id.deleteImageView:

			deleteMailByUser();

			break;

		default:
			break;
		}

	}

	/**
	 * This method call when user click to delete mail.
	 */
	private void deleteMailByUser() {
		// TODO Auto-generated method stub

		AlertDialog.Builder builder1 = new AlertDialog.Builder(
				DetailsEmailActivity.this);
		builder1.setMessage("Are you sure,want to delete the email?");
		builder1.setCancelable(true);

		builder1.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						new DeleteMail().execute(mailid);

						dialog.cancel();
					}
				});

		builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		AlertDialog alert11 = builder1.create();
		alert11.show();

	}

	/**
	 * When user click on this button it will go to home screen.
	 */
	private void goToHomeScreen() {

		Intent homescreenIntent = new Intent(DetailsEmailActivity.this,
				PiAnswers.class);
		startActivity(homescreenIntent);
		finish();

	}

	/***
	 * This method use to block the question.
	 * 
	 * @author Admin
	 * 
	 */
	private class DeleteMail extends AsyncTask<String, Integer, Double> {
		URI uri;
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("OnPreExecute");
			progressDialog = new ProgressDialog(DetailsEmailActivity.this);
			progressDialog.setMessage("Please wait, Deleting mail..");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0]);
			return null;
		}

		protected void onPostExecute(Double result) {

			if (progressDialog.isShowing() && progressDialog != null) {

				progressDialog.dismiss();
			}
			/**
			 * here checking from which activity delete opetion perform and base
			 * on that go to that screen.
			 */
			if (backToPage.equals("inbox")) {
				Intent intent = new Intent(DetailsEmailActivity.this,
						Notesmail.class);
				// This line add for clear the activity when user
				startActivity(intent);
				System.out.println("mail send");
				finish();

			} else if (backToPage.equals("sent")) {
				Intent intent = new Intent(DetailsEmailActivity.this,
						NotesSent.class);
				// This line written because the activity already in stack that
				// why it showing duplicate.
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// This line add for clear the activity when user
				startActivity(intent);
				System.out.println("mail sent:");
				finish();
			}

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String mailid) throws IllegalArgumentException {

			try {

				String deleteMail = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/deletemail/";
				// System.out.println("***************"+basurl2);
				deleteMail += mailid;

				System.out.println("***************" + deleteMail);
				// System.out.println("****************"+Sample);

				uri = new URI(deleteMail.replace(" ", "%20"));
				System.out.println("**************uri" + uri);

			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(uri);

				HttpResponse responses = httpClient.execute(httpGet);

				HttpEntity entity = (HttpEntity) responses.getEntity();
				String resp = responses.toString();

				System.out.println("RESPONSE   " + entity);

				// is = entity.getContent();

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassCastException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

		}

	}

}
