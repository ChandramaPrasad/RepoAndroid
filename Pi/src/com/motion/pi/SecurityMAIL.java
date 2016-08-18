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
import com.motion.actionbar.CustomActionBar;

public class SecurityMAIL extends CustomActionBar {
	String uname, kuid;
	static final int READ_BLOCK_SIZE = 100;
	String s;
	String x;
	String sdp;
	int foo;
	String skb;
	String Mobil;
	TextView v;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	String wdps;
	ImageView pis;
	String pas1;
	String pas2;
	String pas3;
	String Email;
	String Passwd;
	private ProgressDialog progressDialog;
	private UserLoginDb userLoginDb;
	private String username;
	private String profileUsername = "";
	private static final String TAG = SecurityPASS.class.getSimpleName();
	String name, pswd, mobil, mails;
	private Toast toast = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.securitymail);
		TextView tvc = (TextView) findViewById(R.id.textView15);
		final EditText pw1 = (EditText) findViewById(R.id.sname);
		final EditText pw2 = (EditText) findViewById(R.id.uname);
		final EditText pw3 = (EditText) findViewById(R.id.pwds);
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
		progressDialog = new ProgressDialog(this);
		userLoginDb = new UserLoginDb(this);
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
		try {
			FileInputStream fileIn = openFileInput("mytextfile.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];

			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				profileUsername += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		ImageView submit = (ImageView) findViewById(R.id.imageView1);

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

			String d = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				sdp = readstring;
				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				d += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

			// name.setText("" + wdp);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// String mane = name.getText().toString();
		String urlg = "http://166.62.81.118:18080/SpringRestCrud/signup/login/loginis/";
		urlg += s + "/" + sdp + "/" + "10434328" + "/" + "9701513816" + "/"
				+ "32.67" + "/" + "32.67" + "/" + "43434";

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
								Passwd = response.getString("passwd");
								String Lastname = response
										.getString("lastname");
								String About = response.getString("aboutme");
								Email = response.getString("emailid");
								Mobil = response.getString("mobileno");
								String Qualif = response
										.getString("qualification");
								String Country = response.getString("country");
								String City = response.getString("city");
								System.out.println("AAAAAAAAAAAAA" + Passwd);

								EditText last = (EditText) findViewById(R.id.editText50);
								last.setText(Lastname);
								EditText pawd = (EditText) findViewById(R.id.editText5);
								pawd.setText(Passwd);
								EditText mal = (EditText) findViewById(R.id.sname);
								mal.setText(Email);
								// EditText mobil = (EditText)
								// findViewById(R.id.num);
								// mobil.setText(Mobil);
								EditText abut = (EditText) findViewById(R.id.abtme);
								abut.setText(About);
								// EditText spin = (EditText)
								// findViewById(R.id.sp001);
								// spin.setText(Qualif);
								// EditText cty = (EditText)
								// findViewById(R.id.sp002);
								// cty.setText(City);
								// EditText conty = (EditText)
								// findViewById(R.id.sp003);
								// conty.setText(Country);
								// qual.setText(Qualif);

								// Toast.makeText(getApplicationContext(),
								// ""+Naam, 9000).show();
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
				pas1 = pw1.getText().toString();
				pas2 = pw2.getText().toString();
				pas3 = pw3.getText().toString();

				if (pw1.getText().toString().length() == 0) {

					pw1.setError("Please enter email id");

				} else if (pw2.getText().toString().length() == 0) {
					pw2.setError("Please enter email id");
				} else if (pw3.getText().toString().length() == 0) {

					pw3.setError("Please enter password");
				} else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
						pw1.getText().toString()).matches()) {
					pw1.setError("Invalid email");

				} else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
						pw2.getText().toString()).matches()) {
					pw2.setError("Invalid email");
				} else {

					new MyTask().execute(kuid, s, Passwd, pas2, Mobil);

				}
				// if (pas1.equals("") || pas2.equals("") || pas3.equals("")) {
				//
				// toast.setText("Please enter all details");
				// toast.show();
				//
				// } else {
				//
				// new MyTask().execute(kuid, s, Passwd, pas2, Mobil);
				//
				// }

				// calling the Asyntask to change user mailid enter by the
				// user.

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

	/**
	 * when user stop the app it will stop showing notification to the user,
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (toast != null) {
			toast.cancel();

		}
	}

	/**
	 * This method use to change the mail.
	 * 
	 * @author Admin
	 * 
	 */
	private class MyTask extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;
		String b;
		String ib;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			progressDialog.setMessage("Please wait..");
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

			Intent i = new Intent(SecurityMAIL.this, LoginActivity.class);

			// When user logout then it will first
			// take records from database and delete
			// it.
			Cursor cursor = userLoginDb.getAllRecords();

			if (cursor.moveToFirst()) {
				do {
					username = cursor.getString(cursor
							.getColumnIndex("username"));

					// do what ever you want here

				} while (cursor.moveToNext());
			}

			cursor.close();

			userLoginDb.deleteContact(username);

			startActivity(i);

			// set boolean value if user logout.

			finish();

			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();

			}
		}

	}

}
