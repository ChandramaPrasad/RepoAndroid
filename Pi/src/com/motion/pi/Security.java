package com.motion.pi;

import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.motion.actionbar.CustomActionBar;

public class Security extends CustomActionBar {
	String uname, kuid;
	static final int READ_BLOCK_SIZE = 100;
	String s;
	String x;
	int foo;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	String wdps;
	ImageView pis;
	String skb;
	TextView v;
	String name, pswd, mobil, mails;
	private String username = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.security);
		pis = (ImageView) findViewById(R.id.headerProfileImage);
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

				// WHen user click on this background color will chage when
				// select from the look and feels settings.

				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

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

		TextView v = (TextView) findViewById(R.id.textView15);

		final RelativeLayout pwd = (RelativeLayout) findViewById(R.id.changePasswordLineaLayout);
		final RelativeLayout mail = (RelativeLayout) findViewById(R.id.suname);
		final RelativeLayout mob = (RelativeLayout) findViewById(R.id.mobilenumberRelativeLayout);
		final RelativeLayout names = (RelativeLayout) findViewById(R.id.usernameLinearlayout);

		mail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Security.this, SecurityMAIL.class);
				startActivity(i);
			}
		});
		mob.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Security.this, SecurityMOBILE.class);
				startActivity(i);
			}
		});
		names.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Security.this, SecurityUSERNAME.class);
				startActivity(i);
			}
		});
		pwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Security.this, SecurityPASS.class);
				startActivity(i);
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
			v.setText("" + s);
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

	private class MyTask extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;
		String b;
		String ib;

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
				// id= myObject.getString("idsignup");
				// nm = myObject.getString("userid");
				// pds = myObject.getString("fastname");
				// //
				// Toast.makeText(getApplicationContext(),""+myObject,Toast.LENGTH_LONG).show();
				// System.out.println("************************" + id);
				// System.out.println("************************" + nm);
				// // FileOutputStream fileout;
				// b = id.toString();
				// ib = nm.toString();

				// try {
				FileOutputStream fileout = openFileOutput("mytextfile.txt",
						MODE_PRIVATE);
				OutputStreamWriter outputWriter = new OutputStreamWriter(
						fileout);
				outputWriter.write(ib);

				outputWriter.close();

				// }
				// catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				FileOutputStream fileouts = openFileOutput("mytextfiles.txt",
						MODE_PRIVATE);
				OutputStreamWriter outputWriters = new OutputStreamWriter(
						fileouts);

				outputWriters.write(b);
				outputWriters.close();

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
			// if (pds.contains("null")) {
			// Toast.makeText(getApplicationContext(), "invalid Credentials",
			// Toast.LENGTH_LONG).show();
			// } else {
			// Intent i = new Intent(Security.this,Top.class);
			// i.putExtra("name", nm);
			// i.putExtra("id", id);
			// startActivity(i);
			// }
			// pb.setVisibility(View.GONE);
			// Toast.makeText()
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

}
