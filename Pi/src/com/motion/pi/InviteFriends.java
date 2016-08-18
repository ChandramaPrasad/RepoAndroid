package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.LocalModel;
import info.androidhive.customlistviewvolley.model.UserList;
import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;
import com.squareup.picasso.Picasso;

public class InviteFriends extends CustomActionBar {

	private ListView inviteFriendListView;
	private ArrayList<UserList> userListArrayList;
	private static final int READ_BLOCK_SIZE = 100;
	private String username = "";
	private String userid = "";
	ProgressDialog progressDialog;
	private UserFriendsList userFriendsList;
	private String skb;
	private ImageView profileImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invite_friends);

		inviteFriendListView = (ListView) findViewById(R.id.inviteFrindsList);
		userListArrayList = new ArrayList<UserList>();

		progressDialog = new ProgressDialog(this);
		userFriendsList = new UserFriendsList();

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
			skb = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				skb += readStrings;
				View someView = findViewById(R.id.inviteFriendsBack);
				LookAndFeel.lookAndFeel(skb, someView);
				// This method use to change the background color when user
				// select from the look and feel from settings.
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

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

		// send the request to get the friends list.
		makeUserListArrayRequst();

		// set the adapter to ListView.

		inviteFriendListView.setAdapter(userFriendsList);
	}

	/**
	 * This method use to send the friends request to the user.
	 */
	private void makeUserListArrayRequst() {

		progressDialog.setMessage("Please wait..");
		progressDialog.setCancelable(false);
		progressDialog.show();

		String urljsonArry = "http://166.62.81.118:18080/SpringRestCrud/signup/userlist";
		JsonArrayRequest req = new JsonArrayRequest(urljsonArry,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						try {
							// Parsing json array response
							// loop through each json object
							for (int i = 0; i < response.length(); i++) {

								JSONObject obj = response.getJSONObject(i);

								UserList userList = new UserList();

								userList.setUserid(obj.getString("userid"));
								userList.setIdsignup(obj.getLong("idsignup"));
								userList.setFastname(obj.getString("fastname"));
								userList.setLastname(obj.getString("lastname"));
								if (!userList.getUserid().equals(username)) {

									userListArrayList.add(userList);
								}

							}
							// This methos use to show the user profile data who
							// posted answer or question.

							if (progressDialog != null
									&& progressDialog.isShowing()) {

								progressDialog.dismiss();
							}

							userFriendsList.notifyDataSetChanged();

						} catch (Exception e) {
							e.printStackTrace();

						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// // VolleyLog.d(TAG, "Error: " + error.getMessage());
						// Toast.makeText(getApplicationContext(),
						// error.getMessage(), Toast.LENGTH_SHORT).show();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);

	}

	// This method user to load user profile picture when user click on
	// circular picture.
	protected void loadProfileImage(String userid) {

		String getimage = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
		getimage += userid + ".jpg";

		if (getimage != null) {

			Picasso.with(getApplicationContext()).load(getimage)
					.placeholder(R.drawable.profilepic_sml)
					.into(profileImageView);
			System.out.println(">>>>>>>>>>>" + getimage);
			// Here clear the path to set the image again tap on user
			// picture.
			getimage = "";

		}

	}

	/**
	 * This is custom adapter use to show the userlist to send the friend
	 * request.
	 */

	private class UserFriendsList extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return userListArrayList.size();
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
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			if (convertView == null) {

				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.circle_item, null);

			}

			final TextView circleNameTextView = (TextView) convertView
					.findViewById(R.id.circleNameTextView);
			circleNameTextView.setText(userListArrayList.get(position)
					.getUserid());
			profileImageView = (ImageView) convertView
					.findViewById(R.id.profile);
			final TextView idTextView = (TextView) convertView
					.findViewById(R.id.idsourceTextView);
			idTextView.setText(String.valueOf(userListArrayList.get(position)
					.getIdsignup()));
			String iduser = idTextView.getText().toString();

			/**
			 * This method use to load the user profile picture.
			 */
			loadProfileImage(iduser);

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String destUserid = idTextView.getText().toString();
					String destUsername = circleNameTextView.getText()
							.toString();
					if (destUsername != null && destUserid != null) {

						new SendingFriendRequest().execute(userid, username,
								destUserid, destUsername);

					}

				}
			});

			return convertView;
		}
	}

	/**
	 * Method use to send the friend request to user.
	 * 
	 * @author Admin
	 * 
	 */
	private class SendingFriendRequest extends
			AsyncTask<String, Integer, Double> {
		URI uri;
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("OnPreExecute");
			progressDialog = new ProgressDialog(InviteFriends.this);
			progressDialog.setMessage("Sending friend request..");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1], params[2], params[3]);
			return null;
		}

		protected void onPostExecute(Double result) {

			if (progressDialog.isShowing() && progressDialog != null) {

				progressDialog.dismiss();
			}

			Toast.makeText(getApplicationContext(), "Request sent",
					Toast.LENGTH_SHORT).show();

			// set the value as true when user sent friends request to another
			// user..
			LocalModel.getInstance().setFriendRequestSent(true);

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String userid, String username, String destuseid,
				String destusername) throws IllegalArgumentException {

			try {
				// String kompose =
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/{questiondetails}/{userid}/{username}/{aboutmyquestion}/{qtypeid}";
				// String Sample=
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/what%20is%20loop%20statement/30/Kavya/c%20language/5";
				// String basurl2 =
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/wahat%20is%20processor/70/ajay/hi/1";
				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/freindrequest/";
				// System.out.println("***************"+basurl2);
				basurl3 += userid + "/" + username + "/" + destuseid + "/"
						+ destusername;

				System.out.println("***************" + basurl3);
				// System.out.println("****************"+Sample);

				uri = new URI(basurl3.replace(" ", "%20"));
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
