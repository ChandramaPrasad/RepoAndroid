package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.ApprovedFriendsDao;
import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;
import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 16-09-2015.
 */
public class Circle extends CustomActionBar {

	String uid;
	String userid = "";

	static final int READ_BLOCK_SIZE = 100;
	String skb;
	private String username = "";
	private ListView circleListView;
	private Button invitImageView;
	private Button acceptsImageView;
	private Button myrequestButton;
	private ImageView profileImageView;
	private ArrayList<ApprovedFriendsDao> approvedFriendsDaoArrayList;
	private ProgressDialog progressDialog;
	private senderFriendsListAdapter sendfriendrequestAdapter;
	// private receiverFriendsListAdapter receiverFriendsAdapter;
	private URI uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circle);

		circleListView = (ListView) findViewById(R.id.circleList);
		invitImageView = (Button) findViewById(R.id.invitimageView);
		acceptsImageView = (Button) findViewById(R.id.acceptImageView);
		// myrequestButton = (Button) findViewById(R.id.myrequestButton);
		approvedFriendsDaoArrayList = new ArrayList<ApprovedFriendsDao>();
		progressDialog = new ProgressDialog(this);
		sendfriendrequestAdapter = new senderFriendsListAdapter();

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
		makeFriendsListRequest(userid);
		circleListView.setAdapter(sendfriendrequestAdapter);

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
				View someView = findViewById(R.id.circs);

				// This method use to change background color when user select
				// from the look and feel settings.
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * Here the method use to send the request to get the friends list of
		 * the users.
		 */

		/**
		 * When user click on this button it will invites to the users.
		 */

		invitImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(Circle.this, InviteFriends.class);
				startActivity(intent);

			}
		});

		/**
		 * When user click on this button it will go to friends rquest send by
		 * all user.
		 */
		acceptsImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(Circle.this, FriendRequest.class);
				startActivity(intent);

			}
		});

		// myrequestButton.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// Intent myFriendsIntent = new Intent(Circle.this,
		// MyFriendRequest.class);
		// startActivity(myFriendsIntent);
		//
		// }
		// });

		/**
		 * This line use to set the adapter to listiview.
		 */
		//

	}

	/**
	 * Create the custom listview to show the user list to send the friend
	 * request to user.
	 */

	// This adapter user to show the notifiaction to user when application get
	private class senderFriendsListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return approvedFriendsDaoArrayList.size();
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

			if (convertView == null) {

				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.circle_item, null);

			}

			profileImageView = (ImageView) convertView
					.findViewById(R.id.profile);

			final TextView nameTextView = (TextView) convertView
					.findViewById(R.id.circleNameTextView);
			final TextView idSourceTextView = (TextView) convertView
					.findViewById(R.id.idsourceTextView);
			final TextView iddestTextView = (TextView) convertView
					.findViewById(R.id.iddestTextView);

			final TextView idfriendsTextView = (TextView) convertView
					.findViewById(R.id.idfriendsTextView);

			ImageView addImage = (ImageView) convertView
					.findViewById(R.id.addbutton);
			addImage.setVisibility(View.GONE);

			nameTextView.setText(approvedFriendsDaoArrayList.get(position)
					.getDestusername().toString());
			idSourceTextView.setText(String.valueOf(approvedFriendsDaoArrayList
					.get(position).getSrcuserid()));
			iddestTextView.setText(String.valueOf(approvedFriendsDaoArrayList
					.get(position).getDestuserid()));
			idfriendsTextView.setText(String
					.valueOf(approvedFriendsDaoArrayList.get(position)
							.getIdfrndrequest()));

			/**
			 * Here set the condition to which user is currently login to show
			 * his username and profile picture to other users,
			 */

			if (String.valueOf(
					approvedFriendsDaoArrayList.get(position).getSrcuserid())
					.equals(userid)) {
				nameTextView.setText(approvedFriendsDaoArrayList.get(position)
						.getDestusername().toString());
				iddestTextView.setText(String
						.valueOf(approvedFriendsDaoArrayList.get(position)
								.getDestuserid()));
				String userid = iddestTextView.getText().toString();
				loadProfileImage(userid);

			}
			if (String.valueOf(
					approvedFriendsDaoArrayList.get(position).getDestuserid())
					.equals(userid)) {

				nameTextView.setText(approvedFriendsDaoArrayList.get(position)
						.getSrcusername().toString());
				iddestTextView.setText(String
						.valueOf(approvedFriendsDaoArrayList.get(position)
								.getSrcuserid()));
				String userid = iddestTextView.getText().toString();
				loadProfileImage(userid);

			}

			convertView.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub

					AlertDialog.Builder dialog = new AlertDialog.Builder(
							Circle.this);

					// Create a custom layout for the dialog box
					LayoutInflater inflater = (LayoutInflater) Circle.this
							.getSystemService(LAYOUT_INFLATER_SERVICE);
					final View layout = inflater.inflate(
							R.layout.circle_friend_delete_option_layout, null,
							false);

					TextView unfriendTextView = (TextView) layout
							.findViewById(R.id.unfriendTextView);

					dialog.setView(layout);

					String username = nameTextView.getText().toString();
					// dialog.setInverseBackgroundForced(true);
					unfriendTextView.setText(username);

					final String friendre = idfriendsTextView.getText()
							.toString();

					final AlertDialog alertDialog = dialog.create();

					unfriendTextView.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							new UnfriendAsynTask().execute(friendre);

							alertDialog.dismiss();

						}
					});

					alertDialog.show();
					// This line which use to show the fix size of window on
					// users screen.
					// alertDialog.getWindow().setLayout(250, 650);

					try {
						ViewGroup viewGroup1 = (ViewGroup) layout.getParent();
						if (viewGroup1 != null) {
							viewGroup1
									.setBackgroundResource(android.R.color.transparent);

							ViewGroup viewGroup2 = (ViewGroup) viewGroup1
									.getParent();
							if (viewGroup2 != null) {
								viewGroup2
										.setBackgroundResource(android.R.color.transparent);
							}
						}
					} catch (Exception e) {
					}
					return false;
				}
			});

			return convertView;
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

	}

	/**
	 * This method use to send the friends request to the user.
	 */
	private void makeFriendsListRequest(String userids) {

		progressDialog.setMessage("Please wait..");
		progressDialog.setCancelable(false);
		progressDialog.show();

		String urljsonArry = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/allapprovedfriends/";
		urljsonArry += userids;
		JsonArrayRequest req = new JsonArrayRequest(urljsonArry,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						try {
							// Parsing json array response
							// loop through each json object
							for (int i = 0; i < response.length(); i++) {

								JSONObject obj = response.getJSONObject(i);

								ApprovedFriendsDao friendReq = new ApprovedFriendsDao();

								friendReq.setDestuserid(obj
										.getLong("destuserid"));
								friendReq.setDestusername(obj
										.getString("destusername"));
								friendReq.setIdfrndrequest(obj
										.getLong("idfrndrequest"));
								friendReq.setSrcuserid(obj.getLong("srcuserid"));
								friendReq.setSrcusername(obj
										.getString("srcusername"));

								if (String.valueOf(friendReq.getSrcuserid())
										.equals(userid)
										|| String.valueOf(
												friendReq.getDestuserid())
												.equals(userid)) {
									approvedFriendsDaoArrayList.add(friendReq);

								}

							}
							// This methos use to show the user profile data who
							// posted answer or question.

							if (progressDialog != null
									&& progressDialog.isShowing()) {

								progressDialog.dismiss();
							}

							sendfriendrequestAdapter.notifyDataSetChanged();

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
	 * This method use to unfriend the user from the friends list.
	 * 
	 * @author Admin
	 * 
	 */
	class UnfriendAsynTask extends AsyncTask<String, Integer, Double> {
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("On prexcute method");
			progressDialog = new ProgressDialog(Circle.this);
			progressDialog.setMessage("Please wait..");
			progressDialog.show();
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0]);
			return null;
		}

		protected void onPostExecute(Double result) {

			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();

				Intent intent = new Intent(Circle.this, Circle.class);
				// This line add for clear the activity when user
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

			}

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String userid) throws IllegalArgumentException {

			try {
				String answerRating = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/freindrequestapproval/";

				answerRating += userid + "/" + "DELETED";
				System.out.println("url of response" + answerRating);

				uri = new URI(answerRating.replace(" ", "%20"));

				// uri = Uri.parse(out);
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
