package com.motion.pi;

import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.motion.actionbar.CustomActionBar;

public class UserProfileActivity extends CustomActionBar implements
		OnClickListener {

	private TextView firstnameTextView;
	private TextView lastnameTextView;
	private TextView mobileTextView;
	private TextView emailTextView;
	private TextView cityTextView;
	private TextView qualificationTextView;
	private TextView countryTextView;
	private Button okButton;
	private String firstname;
	private String lastname;
	private String emailid;
	private String mobileno;
	private String city;
	private String country;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private String qualification;
	private TextView usernameTextView;
	private ImageView headerProfileImage;
	private String skb = "";
	static final int READ_BLOCK_SIZE = 100;
	private String username = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userprofile_layout);
		// This method use to initialize the views.
		initViews();
		registerEvents();

		firstname = getIntent().getExtras().getString("firstname");
		lastname = getIntent().getExtras().getString("lastname");
		emailid = getIntent().getExtras().getString("emailid");
		mobileno = getIntent().getExtras().getString("mobileno");
		city = getIntent().getExtras().getString("city");
		country = getIntent().getExtras().getString("country");
		qualification = getIntent().getExtras().getString("qualification");

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
			FileInputStream newfile = openFileInput("Skies.txt");
			InputStreamReader InputRead = new InputStreamReader(newfile);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				skb += readStrings;
				View someView = findViewById(R.id.userprofileback);
				LookAndFeel.lookAndFeel(skb, someView);
				// This method use to change the background color when user
				// select from the look and feel from settings.
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		setNameToTextView();
	}

	// This method use to set text of userprofile.
	private void setNameToTextView() {

		firstnameTextView.setText(firstname);
		lastnameTextView.setText(lastname);
		mobileTextView.setText(mobileno);
		emailTextView.setText(emailid);
		cityTextView.setText(city);
		countryTextView.setText(country);
		qualificationTextView.setText(qualification);

	}

	/**
	 * This method use to register the events.
	 */
	private void registerEvents() {

		okButton.setOnClickListener(this);

	}

	/**
	 * This methos use to initialise the view here.
	 */
	private void initViews() {

		firstnameTextView = (TextView) findViewById(R.id.firstnameTextView);
		lastnameTextView = (TextView) findViewById(R.id.lastnameTextView);
		mobileTextView = (TextView) findViewById(R.id.mobilenumberTextView);
		emailTextView = (TextView) findViewById(R.id.emailTextView);
		cityTextView = (TextView) findViewById(R.id.cityTextView);
		qualificationTextView = (TextView) findViewById(R.id.qualificationTextView);
		countryTextView = (TextView) findViewById(R.id.countryTextView);
		okButton = (Button) findViewById(R.id.userprofileokButton);
		usernameTextView = (TextView) findViewById(R.id.usernameTextView);
		headerProfileImage = (ImageView) findViewById(R.id.headerProfileImage);

	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.userprofileokButton:

			goToHomepage();

			break;

		default:
			break;
		}

	}

	/**
	 * When user click on thi button it simply go to homepage.
	 */
	private void goToHomepage() {

		Intent hompageIntnet = new Intent(UserProfileActivity.this,
				PiAnswers.class);
		startActivity(hompageIntnet);
		finish();

	}

}
