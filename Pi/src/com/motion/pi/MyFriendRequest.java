package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.MyFriendRequestDao;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;

public class MyFriendRequest extends CustomActionBar {
	private static final int READ_BLOCK_SIZE = 100;
	private String userid = "";
	private ProgressDialog progressDialog;
	private ArrayList<MyFriendRequestDao> myFriendRequestDaoArrayList;
	private MyFriendRequestAdapter friendRequestAdapter;
	private ListView myfriendsListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_friend_request);
		myFriendRequestDaoArrayList = new ArrayList<MyFriendRequestDao>();
		progressDialog = new ProgressDialog(this);
		myfriendsListView = (ListView) findViewById(R.id.myfriendsListView);
		friendRequestAdapter = new MyFriendRequestAdapter();

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

		// This method use to get the user all data using the userid.
		makeFriendsListRequest(userid);

		// after getting the rquest here set the adapter to listview.

		myfriendsListView.setAdapter(friendRequestAdapter);

	}

	/**
	 * This method use to send the friends request to the user.
	 */
	private void makeFriendsListRequest(String userid) {

		progressDialog.setMessage("Please wait..");
		progressDialog.setCancelable(false);
		progressDialog.show();

		String urljsonArry = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/allrequestedfriends/";
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

								MyFriendRequestDao friendReq = new MyFriendRequestDao();

								friendReq.setDestuserid(obj
										.getLong("destuserid"));
								friendReq.setDestusername(obj
										.getString("destusername"));
								friendReq.setIdfrndrequest(obj
										.getLong("idfrndrequest"));

								myFriendRequestDaoArrayList.add(friendReq);

							}
							// This methos use to show the user profile data who
							// posted answer or question.

							if (progressDialog != null
									&& progressDialog.isShowing()) {

								progressDialog.dismiss();
							}

							friendRequestAdapter.notifyDataSetChanged();

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
	 * This is a custom adapter use to show the friends list to which user send
	 * the friends request.
	 * 
	 * @author Admin
	 * 
	 */
	private class MyFriendRequestAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return myFriendRequestDaoArrayList.size();
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

			TextView nameTextView = (TextView) convertView
					.findViewById(R.id.circleNameTextView);
			nameTextView.setText(myFriendRequestDaoArrayList.get(position)
					.getDestusername());
			return convertView;
		}

	}

}
