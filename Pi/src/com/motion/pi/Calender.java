package com.motion.pi;

import info.androidhive.customlistviewvolley.model.LocalModel;
import info.androidhive.customlistviewvolley.model.RingTone;
import info.androidhive.customlistviewvolley.util.LookAndFeel;
import info.androidhive.customlistviewvolley.util.SettingDatbase;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.motion.actionbar.CustomActionBar;

public class Calender extends CustomActionBar {
	static final int READ_BLOCK_SIZE = 100;
	String s;
	String skb;
	ToggleButton toggleButton1;
	String kuid;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	String wdps;
	ImageView pis;
	MediaPlayer mediaPlayer;
	RingtoneManager mRingtoneManager;
	Cursor mcursor;
	Intent Mringtone;
	String title;
	private static Bundle bundle = new Bundle();
	public static ArrayList<RingTone> userRingToneArryList;
	private RingTone ringTone;
	boolean isVibrateon = false;
	private SettingDatbase settingDatbase;
	private SharedPreferences preferences;
	private SharedPreferences notificationPreferance;
	private ToggleButton notifiactionToggleButton;
	private Toast toast = null;
	private String username = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cali);
		TextView tv = (TextView) findViewById(R.id.textView15);
		toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
		notifiactionToggleButton = (ToggleButton) findViewById(R.id.notifiactionToggleButton);
		mRingtoneManager = new RingtoneManager(this);
		userRingToneArryList = new ArrayList<RingTone>();
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
		preferences = getPreferences(MODE_PRIVATE);
		notificationPreferance = getPreferences(MODE_PRIVATE);
		ringTone = new RingTone();
		settingDatbase = new SettingDatbase(this);

		// while loading source chcek wheather button vibration on or off base
		// on that set button state.
		boolean isVibrationSet = preferences.getBoolean("tgpref", true); // default
																			// is
		boolean isNotification = notificationPreferance.getBoolean("notifi",
				true); // default

		// is
		// true
		if (isVibrationSet == true) {
			toggleButton1.setChecked(true);
		} else {
			toggleButton1.setChecked(false);
		}

		/**
		 * in the on create method check weather user change the setting for
		 * jobs and events notification.
		 * 
		 */
		if (isNotification == true) {

			notifiactionToggleButton.setChecked(true);
			// Here set the value as a true if the notification is on.
			LocalModel.getInstance().setNotification(true);

		} else {
			notifiactionToggleButton.setChecked(false);
			// Here set the value as false if user want to stop the
			// notification.
			LocalModel.getInstance().setNotification(false);
		}

		// toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);

		try {
			FileInputStream fileIn = openFileInput("lastname.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];

			String ds = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				wdps += readstring;
				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				ds += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

			// name.setText("" + wdp);

		} catch (Exception e) {
			e.printStackTrace();
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

		// when user click on this button he/she will able to change the setting
		// of the notification getting from the user.
		notifiactionToggleButton
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						// check the value, if its check then its value will set
						// to sharepreferance.
						if (notifiactionToggleButton.isChecked()) {

							SharedPreferences.Editor editor = notificationPreferance
									.edit();
							editor.putBoolean("notifi", true); // value to store
							editor.commit();
						} else {
							// check the value, if its value is not check by the
							// user then it will also store in sharepreferance.
							SharedPreferences.Editor editor = notificationPreferance
									.edit();
							editor.putBoolean("notifi", false); // value to
																// store
							editor.commit();
						}

					}
				});
		// when user click on this button he/she will bale to change the
		// vibration setting of the calendar.
		toggleButton1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				// TODO Auto-generated method stub
				if (toggleButton1.isChecked()) {
					Toast.makeText(getApplicationContext(), "Vibrate ON", 9000)
							.show();
					toast.setText("Vibrate ON");
					toast.show();

					SharedPreferences.Editor editor = preferences.edit();
					editor.putBoolean("tgpref", true); // value to store
					editor.commit();
					isVibrateon = true;
				} else {
					toast.setText("Vibration Off");
					toast.show();

					SharedPreferences.Editor editor = preferences.edit();
					editor.putBoolean("tgpref", false); // value to store
					editor.commit();
					isVibrateon = false;
				}
				ringTone.setVibrateCheck(isVibrateon);
				settingDatbase.addDataFromVibration(ringTone);
			}
		});
		AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		ImageView home = (ImageView) findViewById(R.id.button38);
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
				View someView = findViewById(R.id.cals);

				// This method use to change the background color when user
				// click on look and feel from settings.
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		Button ring = (Button) findViewById(R.id.button2);
		ring.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						RingtoneManager.ACTION_RINGTONE_PICKER);
				// startActivityForResult(intent, RQS_RINGTONEPICKER);
				// specifies what type of tone we want, in this case "ringtone",
				// can be notification if you want
				// the following appends the cursor with the cursor that is used
				// when the ringtone picker pops up

				mcursor = mRingtoneManager.getCursor();
				title = mRingtoneManager.EXTRA_RINGTONE_TITLE;

				Mringtone = new Intent(mRingtoneManager.ACTION_RINGTONE_PICKER);

				// specifies what type of tone we want, in this case "ringtone",
				// can be notification if you want
				Mringtone.putExtra(mRingtoneManager.EXTRA_RINGTONE_TYPE,
						RingtoneManager.TYPE_RINGTONE);

				// gives the title of the RingtoneManager picker title
				Mringtone.putExtra(mRingtoneManager.EXTRA_RINGTONE_TITLE,
						"This is the title Of Your Picker!");

				// returns true shows the rest of the song on the device in the
				// default location
				Mringtone.getBooleanExtra(
						mRingtoneManager.EXTRA_RINGTONE_INCLUDE_DRM, true);

				String uri = null;
				// chooses and keeps the selected item as a uri
				if (uri != null) {
					Mringtone.putExtra(
							mRingtoneManager.EXTRA_RINGTONE_EXISTING_URI,
							Uri.parse(uri));
				} else {
					Mringtone.putExtra(
							mRingtoneManager.EXTRA_RINGTONE_EXISTING_URI,
							(Uri) null);
				}

				startActivityForResult(Mringtone, 0);

			}
		});

		try {
			FileInputStream fileIn = openFileInput("mytextfile.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			s = "";
			String d = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				s += readstring;
				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				d += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();
			tv.setText("" + s);
			// uname = names.getText().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileInputStream fileIn = openFileInput("mytextfiles.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			kuid = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				kuid += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// if(requestCode == RQS_RINGTONEPICKER && resultCode == RESULT_OK){
	// Uri uri =
	// data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
	// ringTone = RingtoneManager.getRingtone(getApplicationContext(), uri);
	// Toast.makeText(Calender.this,
	// ringTone.getTitle(Calender.this),
	// Toast.LENGTH_LONG).show();
	// ringTone.play();
	// }
	// }

	public Uri getRingtone() {
		Uri alert = null;
		try {
			alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setDataSource(this, alert);
			final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
				mediaPlayer.setLooping(true);
				mediaPlayer.prepare();
				mediaPlayer.start();
			}
		} catch (Exception e) {
		}
		return alert;
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent Mringtone) {
		switch (resultCode) {
		/*
		* 
		*/
		case RESULT_OK:
			// sents the ringtone that is picked in the Ringtone Picker Dialog
			Uri uri = Mringtone
					.getParcelableExtra(mRingtoneManager.EXTRA_RINGTONE_PICKED_URI);

			// send the output of the selected to a string
			// String test = uri.toString();

			// the program creates a "line break" when using the "\n" inside a
			// string value
			// text.setText("\n " + test + "\n " + title);

			// prints out the result in the console window

			// To check weather uri null or not?
			if (uri != null) {

				ringTone.setRigtoneuri(uri);
				settingDatbase.addDataFromRintone(ringTone);

				System.out.println("ringtone>>>" + ringTone);
				System.out.println("setting>>>" + settingDatbase);
			}

			// this passed the ringtone selected from the user to a new method
			// play(uri);

			// inserts another line break for more data, this times adds the
			// cursor count on the selected item
			// text.append("\n " + mcursor.getCount());

			// set default ringtone
			try {
				RingtoneManager.setActualDefaultRingtoneUri(this, resultCode,
						uri);
			} catch (Exception localException) {

			}
			break;

		}

	}

	// this method captures the ringtone from the selection and plays it in the
	// main activity
	public void play(Uri uri) {
		// TODO Auto-generated method stub
		if (uri != null) {

			Ringtone rt;

			// in order to play the ringtone, you need to create a new Ringtone
			// with RingtoneManager and pass it to a variable
			rt = mRingtoneManager.getRingtone(this, uri);
			rt.play();

		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		// Here chcek if toast if not null then close the toast popup to solving
		// extented display problem.
		if (toast != null) {
			toast.cancel();
		}
	}

}
