package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.BlockEmailUser;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;

public class BlockMailActivity extends CustomActionBar {

	private String username = "";
	private String userid = "";
	static final int READ_BLOCK_SIZE = 100;
	String skb;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";

	private EditText selectUsernameEditText;
	private ArrayList<BlockEmailUser> blockEmailUsersArrayList;
	private ProgressDialog dialog;
	private ListView blockmailListView;
	private BlcokMailUserlistAdapter blcokMailUserlistAdapter;
	private String blockusername = "";
	private String blockuserid = "";
	private ImageView submitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.block_mail_layout);

		selectUsernameEditText = (EditText) findViewById(R.id.selectUsername);
		blockmailListView = (ListView) findViewById(R.id.blockmailListView);
		submitButton = (ImageView) findViewById(R.id.submitButton);
		dialog = new ProgressDialog(getApplicationContext());
		blockEmailUsersArrayList = new ArrayList<BlockEmailUser>();
		blcokMailUserlistAdapter = new BlcokMailUserlistAdapter();

		blockusername = getIntent().getStringExtra("blockusername");
		blockuserid = getIntent().getStringExtra("blockuserid");
		makeBlockUserlistRequest();

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

				View someView = findViewById(R.id.blcokmailback);
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

		if (blockusername != null) {

			selectUsernameEditText.setText(blockusername);

		}

		/**
		 * When user click on this button it will block the user to send the
		 * mail
		 */
		submitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String blockname = selectUsernameEditText.getText().toString();
				if (blockname.matches("")) {

					Toast.makeText(getApplicationContext(),
							"Please enter name", Toast.LENGTH_SHORT).show();

				} else {

					new BlockMail().execute(userid, username, blockuserid,
							blockusername);
					// Clear the edittext area after sending the mail.
					selectUsernameEditText.setText("");

				}

			}
		});

		selectUsernameEditText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(getApplicationContext(),
						BlockMailList.class);
				startActivity(intent);
				finish();

			}
		});

		// set adapter to ListView.
		blockmailListView.setAdapter(blcokMailUserlistAdapter);
	}

	private void makeBlockUserlistRequest() {
		String url = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/allblockedmails";

		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								BlockEmailUser blockEmailUser = new BlockEmailUser();
								blockEmailUser.setIdblkl(obj.getLong("idblkl"));
								blockEmailUser.setSrcuserid(obj
										.getLong("srcuserid"));
								blockEmailUser.setDestusername(obj
										.getString("destusername"));
								blockEmailUser.setSrcusername(obj
										.getString("srcusername"));
								// Here the logic to filter the list of user
								// which block by the owner.
								if (String.valueOf(
										blockEmailUser.getSrcuserid()).equals(
										userid)) {

									blockEmailUsersArrayList
											.add(blockEmailUser);

								}

							} catch (Exception e) {
								e.printStackTrace();
							}

						}

						blcokMailUserlistAdapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// VolleyLog.d(TAG, "Error: " + error.getMessage());

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
		// sending request for job notification details.

	}

	private class BlcokMailUserlistAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return blockEmailUsersArrayList.size();
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
						.inflate(R.layout.block_user_item_layout, null);

			}

			TextView blockusername = (TextView) convertView
					.findViewById(R.id.blockuserTextView);
			TextView srcUsername = (TextView) convertView
					.findViewById(R.id.sourceBlockUser);
			final TextView blockIdTextView = (TextView) convertView
					.findViewById(R.id.unblockid);
			srcUsername.setText(blockEmailUsersArrayList.get(position)
					.getSrcusername());
			blockusername.setText(blockEmailUsersArrayList.get(position)
					.getDestusername());

			blockIdTextView.setText(String.valueOf(blockEmailUsersArrayList
					.get(position).getIdblkl()));

			String user = srcUsername.getText().toString();

			if (user.equals(username)) {
				blockusername.setText(blockEmailUsersArrayList.get(position)
						.getDestusername());

			}

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					final String blockuserid = blockIdTextView.getText()
							.toString();

					AlertDialog.Builder builder1 = new AlertDialog.Builder(
							BlockMailActivity.this);
					builder1.setMessage("Are you sure,want to unblock the user?");
					builder1.setCancelable(true);

					builder1.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									new UnblockMail().execute(blockuserid);
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

	private void hidePDialog() {
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}

	/**
	 * This method use to block the mail from the user.
	 * 
	 * @author Admin
	 * 
	 */
	private class BlockMail extends AsyncTask<String, Integer, Double> {
		URI uri;
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("OnPreExecute");
			progressDialog = new ProgressDialog(BlockMailActivity.this);
			progressDialog.setMessage("Blocking user..");
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
			Intent intent = new Intent(BlockMailActivity.this,
					BlockMailActivity.class);
			// This line add for clear the activity when user
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String userid, String username,
				String blockuserid, String blockusername)
				throws IllegalArgumentException {

			try {
				// String kompose =
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/{questiondetails}/{userid}/{username}/{aboutmyquestion}/{qtypeid}";
				// String Sample=
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/what%20is%20loop%20statement/30/Kavya/c%20language/5";
				// String basurl2 =
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/wahat%20is%20processor/70/ajay/hi/1";
				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/blockmailorchat/";
				// System.out.println("***************"+basurl2);
				basurl3 += userid + "/" + username + "/" + blockuserid + "/"
						+ blockusername + "/" + "mail" + "/" + "mail";

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

	/****
	 * This method use to unblock the mail from the user.
	 */

	private class UnblockMail extends AsyncTask<String, Integer, Double> {
		URI uri;
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("OnPreExecute");
			progressDialog = new ProgressDialog(BlockMailActivity.this);
			progressDialog.setMessage("Unblocking user..");
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

			Intent intent = new Intent(BlockMailActivity.this,
					BlockMailActivity.class);
			// This line add for clear the activity when user
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String blockid) throws IllegalArgumentException {

			try {

				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/unblockemail/";
				basurl3 += blockid;

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
