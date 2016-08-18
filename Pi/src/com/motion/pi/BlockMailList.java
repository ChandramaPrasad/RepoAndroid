package com.motion.pi;

import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.motion.actionbar.CustomActionBar;
import com.squareup.picasso.Picasso;

public class BlockMailList extends CustomActionBar {
	// Log tag
	View formElementsView;
	String idd;
	String qid;
	String qqid;
	View convertView;
	int w = 100;
	private static final String TAG = BlockMailList.class.getSimpleName();
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private ProgressDialog pDialog;
	private ListView listView;
	String skb;
	String nam;
	SharedPreferences sps;
	private QuestionListAdapter questionListAdapter;
	String id;
	String kuid;
	ImageView serch;
	String Name;
	String Nid;
	private String userid = "";
	private ImageView blockImageView;
	private String username = "";
	static final int READ_BLOCK_SIZE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myfnd);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		listView = (ListView) findViewById(R.id.list);
		final EditText search = (EditText) findViewById(R.id.multiss);
		questionListAdapter = new QuestionListAdapter();
		listView.setAdapter(questionListAdapter);
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

				View someView = findViewById(R.id.maillistback);
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

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
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// serch = (ImageView)findViewById(R.id.src);
		// serch.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// String get = search.getText().toString();
		// // new seartask().execute(get);
		// new seartask().execute(get);
		// }
		// });

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

	class QuestionListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return PiAnswers.userListArrayList.size();
		}

		@Override
		public Object getItem(int location) {
			return location;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null)
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.block_friend_list_item_layout, null);

			final TextView blockUsernameTextView = (TextView) convertView
					.findViewById(R.id.blockusernameTextView);
			blockImageView = (ImageView) convertView
					.findViewById(R.id.blockImageView);
			final TextView idTextView = (TextView) convertView
					.findViewById(R.id.idTextView);

			if (!String.valueOf(
					PiAnswers.userListArrayList.get(position).getIdsignup())
					.equals(userid)) {
				idTextView.setText(String.valueOf(PiAnswers.userListArrayList
						.get(position).getIdsignup()));

				blockUsernameTextView.setText(PiAnswers.userListArrayList.get(
						position).getUserid());
				String userid = idTextView.getText().toString();

				loadProfileImage(userid);
			}

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					String blockusreid = idTextView.getText().toString();
					String blockusername = blockUsernameTextView.getText()
							.toString();
					Intent i = new Intent(BlockMailList.this,
							BlockMailActivity.class);
					i.putExtra("blockuserid", blockusreid);
					// i.putExtra("QID", qqid);
					i.putExtra("blockusername", blockusername);
					startActivity(i);
					finish();
				}
			});

			return convertView;
		}

		// This method user to load user profile picture when user click on
		// circular picture.
		protected void loadProfileImage(String currentUsername) {

			String getimage = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
			getimage += currentUsername + ".jpg";

			if (getimage != null) {

				Picasso.with(getApplicationContext()).load(getimage)
						.placeholder(R.drawable.profilepic_sml)
						.into(blockImageView);
				System.out.println(">>>>>>>>>>>" + getimage);
				// Here clear the path to set the image again tap on user
				// picture.
				getimage = "";

			}

		}

	}
}
