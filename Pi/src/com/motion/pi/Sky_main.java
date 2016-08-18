package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.ChatNotificationDao;
import info.androidhive.customlistviewvolley.model.Skywrite;
import info.androidhive.customlistviewvolley.util.ImageTrans_roundedcorner;
import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;
import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 27-08-2015.
 */
public class Sky_main extends CustomActionBar {
	String uname;
	static final int READ_BLOCK_SIZE = 100;
	View formElementsView;
	String skb;
	View convertView;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private ProgressDialog pDialog;
	private List<Skywrite> skyArrayList;
	private ListView skylistView;
	private ChatuserListAdapter chatuserListAdapter;
	private String chatnotification = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/getallnewchat/";
	String uid;
	ImageView profilepic;
	private String userid = "";
	private ArrayList<ChatNotificationDao> chatNotificationArraylist;
	String s;
	int w = 100;
	private static final String TAG = Sky_main.class.getSimpleName();
	ImageView homes;
	String wdps;
	private String username = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skrite);
		homes = (ImageView) findViewById(R.id.headerProfileImage);
		TextView t = (TextView) findViewById(R.id.sname);
		skylistView = (ListView) findViewById(R.id.skylistView1);
		chatuserListAdapter = new ChatuserListAdapter();
		skyArrayList = new ArrayList<Skywrite>();
		chatNotificationArraylist = new ArrayList<ChatNotificationDao>();
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
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				username += readstring;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();
			t.setText(username);
			// uname = names.getText().toString();

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

		chatnotification += userid;

		System.out.println("url>>>>>>>>>>" + chatnotification);

		/**
		 * Here send the request of User list.
		 */
		makeUserListRequest(userid);

		/**
		 * send the request to get the chat notification.
		 */

		// set the user profile picture here.

		/**
		 * THis method use to show the chat notification to user.
		 */

		// Here set the adapter to show the list of user to chat.
		skylistView.setAdapter(chatuserListAdapter);

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
				View someView = findViewById(R.id.skw);
				// This method use to set background color of the view when user
				// select from look and feel settings.
				LookAndFeel.lookAndFeel(skb, someView);
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * method call when having chat notification from other user.
	 * 
	 * @param chatNotificationArraylist
	 */
	private void showChatNotification(
			ArrayList<ChatNotificationDao> chatNotificationArraylist) {

		NotificationManager mNotificationManager = null;
		NotificationCompat.Builder mBuilder = null;

		Bitmap icon1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.icon);

		mBuilder = new NotificationCompat.Builder(this).setAutoCancel(true)
				.setContentTitle("Chat Notification")
				.setSmallIcon(R.drawable.icon).setLargeIcon(icon1)
				.setContentText("");

		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

		// Sets a title for the Inbox style big view
		inboxStyle.setBigContentTitle("Chat Notification");

		System.out.println("chat size>>>" + chatNotificationArraylist);

		// Moves events into the big view
		for (int i = 0; i < chatNotificationArraylist.size(); i++) {

			inboxStyle.addLine(chatNotificationArraylist.get(i).getChatdesc()
					.toString());
			inboxStyle.addLine(chatNotificationArraylist.get(i)
					.getSrcusername().toString());

		}
		// Moves the big view style object into the notification object.
		inboxStyle.addLine("More..");
		mBuilder.setStyle(inboxStyle);

		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, Sky_main.class);

		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(Sky_main.class);

		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// mId allows you to update the notification later on.
		mNotificationManager.notify(100, mBuilder.build());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void makeUserListRequest(String userids) {

		pDialog = new ProgressDialog(Sky_main.this);
		pDialog.setMessage("Please Wait..");
		pDialog.setCancelable(false);
		pDialog.show();

		String url = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/allapprovedfriends/";
		url += userids;

		// Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG,
								"hii&&&&&&&&&&&&&&&&&&&&&&:"
										+ response.toString());
						hidePDialog();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Skywrite chat = new Skywrite();

								chat.setDestuserid(obj.getLong("destuserid"));
								chat.setDestusername(obj
										.getString("destusername"));
								chat.setIdfrndrequest(obj
										.getLong("idfrndrequest"));
								chat.setSrcuserid(obj.getLong("srcuserid"));

								chat.setSrcusername(obj
										.getString("srcusername"));

								if (String.valueOf(chat.getSrcuserid()).equals(
										userid)
										|| String.valueOf(chat.getDestuserid())
												.equals(userid)) {
									skyArrayList.add(chat);

								}

							} catch (Exception e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						chatuserListAdapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);

	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

	/**
	 * This adapter use to show the userlist wher user can chat with anyone.
	 * 
	 * @author Admin
	 * 
	 */
	class ChatuserListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return skyArrayList.size();
		}

		@Override
		public Object getItem(int location) {
			return skyArrayList.get(location);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {

				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.fnds, null);

			}

			final TextView chatNameTextView = (TextView) convertView
					.findViewById(R.id.chatNameTextView);

			final TextView useridTextView = (TextView) convertView
					.findViewById(R.id.useridTextView);
			TextView srcIdTextView = (TextView) convertView
					.findViewById(R.id.sourceIdTextView);
			TextView destIdTextView = (TextView) convertView
					.findViewById(R.id.destIdTextView);
			destIdTextView.setText(String.valueOf(skyArrayList.get(position)
					.getDestuserid()));
			srcIdTextView.setText(String.valueOf(skyArrayList.get(position)
					.getSrcuserid()));
			profilepic = (ImageView) convertView.findViewById(R.id.thumbnail);

			if (String.valueOf(skyArrayList.get(position).getSrcuserid())
					.equals(userid)) {
				chatNameTextView.setText(skyArrayList.get(position)
						.getDestusername().toString());
				useridTextView.setText(String.valueOf(skyArrayList
						.get(position).getDestuserid()));
				String userid = useridTextView.getText().toString();
				loadProfileImage(userid);

			}
			if (String.valueOf(skyArrayList.get(position).getDestuserid())
					.equals(userid)) {

				chatNameTextView.setText(skyArrayList.get(position)
						.getSrcusername().toString());
				useridTextView.setText(String.valueOf(skyArrayList
						.get(position).getSrcuserid()));
				String userid = useridTextView.getText().toString();
				loadProfileImage(userid);

			}

			/**
			 * When user click on this button he can chat with that user.
			 */
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String username = chatNameTextView.getText().toString();
					String userid = useridTextView.getText().toString();
					Intent intent = new Intent(Sky_main.this, PiChat.class);
					intent.putExtra("destusername", username);
					intent.putExtra("destuserid", userid);
					System.out.println("User ids>>" + userid);
					startActivity(intent);

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
						.placeholder(R.drawable.profilepic_sml).resize(40, 40)
						.transform(new ImageTrans_roundedcorner())
						.into(profilepic);

				System.out.println(">>>>>>>>>>>" + getimage);
				// Here clear the path to set the image again tap on user
				// picture.
				getimage = "";

			}

		}
	}

}
