package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.util.LookAndFeel;
import info.androidhive.customlistviewvolley.util.UserLoginDb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.motion.actionbar.CustomActionBar;

public class SecurityUSERNAME extends CustomActionBar {
	String uname, kuid;
	static final int READ_BLOCK_SIZE = 100;
	String s;
	String x;
	String sdp;
	int foo;
	String skb;
	String Mobil;
	TextView v;
	String user;
	String pass;
	String pas3;
	String Email;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	String wdps;
	ImageView pis;
	private ProgressDialog progressDialog;
	String abts;
	ArrayList<HashMap<String, String>> contactList;
	private UserLoginDb userLoginDb;
	private String firstname;
	private String username = "";
	private EditText nameEditText;
	private String enterUsername;
	private String mypassword;
	List<JSONcircle> bean;

	EditText pw2;
	private static final String TAG = SecurityPASS.class.getSimpleName();
	String name, pswd, mobil, mails;
	private static String url = "http://166.62.81.118:18080/SpringRestCrud/signup/userlist";
	Toast toast = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.securityusername);
		ImageView home = (ImageView) findViewById(R.id.button38);
		nameEditText = (EditText) findViewById(R.id.sname);
		userLoginDb = new UserLoginDb(this);
		progressDialog = new ProgressDialog(this);
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
		pw2 = (EditText) findViewById(R.id.uname);
		bean = new ArrayList<JSONcircle>();
		try {
			InputStream inputStream = openFileInput("last.txt");

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append(receiveString);
				}

				inputStream.close();
				wdps = stringBuilder.toString();

			}
			inputStream.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

			// name.setText("" + wdp);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ImageView submit = (ImageView) findViewById(R.id.imageView1);

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
		try {
			FileInputStream fileIn = openFileInput("pwd.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];

			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				sdp = readstring;
				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				mypassword += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

			// name.setText("" + wdp);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// String mane = name.getText().toString();
		String urlg = "http://166.62.81.118:18080/SpringRestCrud/signup/login/loginis/";
		urlg += username + "/" + sdp + "/" + "10434328" + "/" + "9701513816"
				+ "/" + "32.67" + "/" + "32.67" + "/" + "43434";

		System.out.println(">>>>>>>>" + urlg);

		// Creating volley request obj
		JsonObjectRequest movieReq = new JsonObjectRequest(urlg, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG,
								"hii&&&&&&&&&&&&&&&&&&&&&&:"
										+ response.toString());

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {
								String Naam = response.getString("fastname");
								mypassword = response.getString("passwd");
								String Lastname = response
										.getString("lastname");
								String About = response.getString("aboutme");
								Email = response.getString("emailid");
								Mobil = response.getString("mobileno");
								String Qualif = response
										.getString("qualification");
								String Country = response.getString("country");
								String City = response.getString("city");

							} catch (Exception e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						// adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);

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
				View someView = findViewById(R.id.secid);
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String pass = pw2.getText().toString();
				enterUsername = nameEditText.getText().toString();

				if (nameEditText.getText().toString().length() == 0) {

					nameEditText.setError("Please enter username");

				} else if (pw2.getText().toString().length() == 0) {
					pw2.setError("Please enter password");
				} else if (!pass.equalsIgnoreCase(mypassword)) {

					toast.setText("Invalid details");
					toast.show();

				} else {

					// Here sending the request to change the username.
					new GetContacts().execute();
				}

			}
		});
		try {
			FileInputStream fileIn = openFileInput("color.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			s = "";
			String d = "";
			foo = Integer.parseInt(s);
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
			// Toast.makeText(getBaseContext(), foo,Toast.LENGTH_SHORT).show();
			v.setText("" + s);
			View someView = findViewById(R.id.secid);

			// Find the root view
			View root = someView.getRootView();
			root.setBackgroundColor(foo);
			// uname = names.getText().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileInputStream fileIn = openFileInput("myid.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			x = "";
			String y = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				x += readstring;
				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				y += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), y,Toast.LENGTH_SHORT).show();
			// v.setText("" + s);
			// uname = names.getText().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (toast != null) {
			toast.cancel();

		}
	}

	private class MyTask extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;
		String b;
		String ib;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			progressDialog.setMessage("Changing username..");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			// TODO Auto-generated method stub
			postData(params[0], params[1], params[2], params[3], params[4]);

			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String xid, String unames, String upawd,
				String umail, String umob) {
			// String baseurl =
			// "http://166.62.81.118:18080/SpringRestCrud/signup/login/{sk}/{userid}/{pwd}/{macid}/{mobile}/{latitude}/{longiture}/{deviceid}";
			// String baseurl =
			// "http://166.62.81.118:18080/SpringRestCrud/signup/login/";
			// baseurl += "loginis" + "/" + username + "/" + passwd + "/" +
			// "10434328" + "/" + "7799591404" + "/" + "32.67" + "/" + "32.67" +
			// "/" + "43434";
			// String
			// login="http://166.62.81.118:18080/SpringRestCrud/signup/login/loginis/diptim/password/10434328/9701513816/32.67/34.34/43434";
			String update = "http://localhost:18080/SpringRestCrud/signup/updateuser/14/diptim/password/balaram.dipti@pyrogroup.com/9701513877";
			String updates = "http://166.62.81.118:18080/SpringRestCrud/signup/updateuser/";
			updates += xid + "/" + unames + "/" + upawd + "/" + umail + "/"
					+ umob;
			System.out.println("++++++++++" + updates);
			// String
			// login="http://166.62.81.118:18080/SpringRestCrud/signup/login/loginis/";
			// login+=username+"/"+password+"/"+"10434328"+"/"+"9701513816"+"/"+"32.67"+"/"+"32.67"+"/"+"43434";
			// System.out.println("***************" + baseurl);
			// Execute the request
			// HttpResponse response;
			// List<ReuseItem> items=new ArrayList<ReuseItem>();

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(updates);
				responses = httpClient.execute(httpGet);
				entity = responses.getEntity();
				// String responseStr = EntityUtils.toString(entity);
				String content = EntityUtils.toString(entity);
				System.out.println("***********" + content);
				JSONObject myObject = new JSONObject(content);

				// display file saved message
				Toast.makeText(getBaseContext(), "File saved successfully!",
						Toast.LENGTH_SHORT).show();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassCastException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// onPostExecute(result);
		}

		protected void onPostExecute(Double result) {

			Intent i = new Intent(SecurityUSERNAME.this, LoginActivity.class);

			// When user logout then it will first
			// take records from database and delete
			// it.
			Cursor cursor = userLoginDb.getAllRecords();

			if (cursor.moveToFirst()) {
				do {
					firstname = cursor.getString(cursor
							.getColumnIndex("username"));

					// do what ever you want here

				} while (cursor.moveToNext());
			}

			cursor.close();

			userLoginDb.deleteContact(firstname);

			startActivity(i);

			// set boolean value if user logout.

			finish();

			if (progressDialog != null && progressDialog.isShowing()) {

				progressDialog.dismiss();

			}

		}

		private String convertStreamToString(InputStream is) {
			/*
			 * To convert the InputStream to String we use the
			 * BufferedReader.readLine() method. We iterate until the
			 * BufferedReader return null which means there's no more data to
			 * read. Each line will appended to a StringBuilder and returned as
			 * String.
			 */
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();

			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		}

	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {
		String vl;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			progressDialog.setMessage("Please wait..");
			progressDialog.setCancelable(false);
			progressDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance

			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			vl = "{\"jsonBean\":" + jsonStr + "}";
			Log.d("Response: ", "> " + vl);

			// JSONBean res=null;
			if (vl != null) {
				try {
					// JSONObject jsonObj = new JSONObject(jsonStr);
					Gson gson = new Gson();
					JSONcirc res = gson.fromJson(vl, JSONcirc.class);
					bean = (List<JSONcircle>) res.getJsonBean();

					System.out.println("Response>>" + bean);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			boolean isUserPresent = false;

			/**
			 * Here check the Arraylist of user to check the condtion weather
			 * user present or not
			 */
			if (!bean.isEmpty()) {

				for (int i = 0; i < bean.size(); i++) {

					if (bean.get(i)
							.getUserid()
							.toString()
							.equalsIgnoreCase(nameEditText.getText().toString())) {

						// set the variable to true if user is present.
						isUserPresent = true;

					}

				}

			}

			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();

			}

			/**
			 * Here checking if user present then set to user is present.
			 */
			if (isUserPresent == true) {
				Toast.makeText(getApplicationContext(), "Username Exists", 9000)
						.show();
				isUserPresent = false;
			} else {
				// The final Asyntask use to change the username.
				new MyTask().execute(kuid, enterUsername, mypassword, Email,
						Mobil);
			}

		}
	}
}
