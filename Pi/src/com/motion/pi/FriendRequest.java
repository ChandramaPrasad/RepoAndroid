package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.FriendRequestDao;
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

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;

public class FriendRequest extends CustomActionBar {

	private ProgressDialog progressDialog;
	private ArrayList<FriendRequestDao> friendRequestDaoArrayList;
	private static final int READ_BLOCK_SIZE = 100;
	private String username = "";
	private String userid = "";
	private ListView allrequestListView;
	private FriendsRequestList friendsRequestList;
	private String skb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_request);
		progressDialog = new ProgressDialog(this);
		friendRequestDaoArrayList = new ArrayList<FriendRequestDao>();
		allrequestListView = (ListView) findViewById(R.id.allrequestListView);
		friendsRequestList = new FriendsRequestList();

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
				View someView = findViewById(R.id.friendreback);
				LookAndFeel.lookAndFeel(skb, someView);
				// This method use to change the background color when user
				// select from the look and feel from settings.
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// This method use to send the request to paring data using the volley
		// library.

		makeUserListArrayRequst(userid);

		// THis line use to set the adapter to ListView.

		allrequestListView.setAdapter(friendsRequestList);

	}

	/**
	 * This method use to send the friends request to the user.
	 */
	private void makeUserListArrayRequst(String userid) {

		progressDialog.setMessage("Please wait..");
		progressDialog.setCancelable(false);
		progressDialog.show();

		String urljsonArry = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/allrequestedfriendsreceiver/";
		urljsonArry += userid;
		JsonArrayRequest req = new JsonArrayRequest(urljsonArry,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						try {
							// Parsing json array response
							// loop through each json object
							for (int i = 0; i < response.length(); i++) {

								JSONObject obj = response.getJSONObject(i);

								FriendRequestDao friendReq = new FriendRequestDao();

								friendReq.setDestusername(obj
										.getString("destusername"));
								friendReq.setSrcusername(obj
										.getString("srcusername"));
								friendReq.setIdfrndrequest(obj
										.getLong("idfrndrequest"));

								friendRequestDaoArrayList.add(friendReq);

							}
							// This methos use to show the user profile data who
							// posted answer or question.

							if (progressDialog != null
									&& progressDialog.isShowing()) {

								progressDialog.dismiss();
							}
							friendsRequestList.notifyDataSetChanged();

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

	/**
	 * This is an adapter to show the list of the friends send the friends
	 * request from the other user.
	 */

	private class FriendsRequestList extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return friendRequestDaoArrayList.size();
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
						.inflate(R.layout.all_friends_request_layout, null);

			}

			TextView nameTextView = (TextView) convertView
					.findViewById(R.id.nameTextView);
			final TextView idTextView = (TextView) convertView
					.findViewById(R.id.idTextView);

			nameTextView.setText(friendRequestDaoArrayList.get(position)
					.getSrcusername());
			idTextView.setText(String.valueOf(friendRequestDaoArrayList.get(
					position).getIdfrndrequest()));

			/**
			 * when user click on this button it will accept friends request.
			 */
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					final String userid = idTextView.getText().toString();

					AlertDialog.Builder builder1 = new AlertDialog.Builder(
							FriendRequest.this);
					builder1.setMessage("Do you want to accept friend request?");
					builder1.setCancelable(true);

					builder1.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									new AcceptingFriendRequest()
											.execute(userid);
									dialog.cancel();
								}
							});

					builder1.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});

					AlertDialog alert11 = builder1.create();
					alert11.show();

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
	private class AcceptingFriendRequest extends
			AsyncTask<String, Integer, Double> {
		URI uri;
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("OnPreExecute");
			progressDialog = new ProgressDialog(FriendRequest.this);
			progressDialog.setMessage("Accepting friend request..");
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
			 * After accepting the friends request then it will go to main
			 * circle screen.
			 */
			Intent allAproviedIntent = new Intent(FriendRequest.this,
					Circle.class);
			startActivity(allAproviedIntent);
			allAproviedIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			finish();

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String userid) throws IllegalArgumentException {

			try {

				String friendRequest = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/freindrequestapproval/";
				// System.out.println("***************"+basurl2);
				friendRequest += userid + "/" + "ACTIVE";

				System.out.println("***************" + friendRequest);
				// System.out.println("****************"+Sample);

				uri = new URI(friendRequest.replace(" ", "%20"));
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
