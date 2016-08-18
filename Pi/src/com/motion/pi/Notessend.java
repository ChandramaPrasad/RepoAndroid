package com.motion.pi;

import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.motion.actionbar.CustomActionBar;

public class Notessend extends CustomActionBar {
	EditText mailid;

	EditText composmail;
	String ssubject;
	ImageView sendmailButton;
	String uname;
	String kuid;
	EditText subject;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	String wdps;
	ImageView pi;
	URI uri;
	String skb;
	static final int READ_BLOCK_SIZE = 100;
	String s;
	String A;
	String B;
	String lname;
	String C;
	String names;
	String nids;
	EditText usernameEditTex;
	String answer;
	String answerFromFriendList;
	private String destinationUsername;
	private String destinationuserId;
	private Toast toast = null;
	private String username = "";
	private String mailbody;
	private static String url = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/composemailnotes/{srcusrid}/{srcusrname}/{destusrid}/{destusrname}/{mailbody}";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// final DatabaseHandler db = new DatabaseHandler(this);
		setContentView(R.layout.sendmail);
		composmail = (EditText) findViewById(R.id.cmpdata);
		sendmailButton = (ImageView) findViewById(R.id.msend);
		destinationUsername = getIntent().getStringExtra("username");
		destinationuserId = getIntent().getStringExtra("userid");
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

		// id mapping to solve the unfortunatoy closing application when click
		// on the share button to share question.
		// code added by prasad.
		pi = (ImageView) findViewById(R.id.headerProfileImage);

		// get data from archive class, user select any question to send mail to
		// that particular recipients.
		Bundle b = new Bundle();
		if (b != null) {

			b = getIntent().getExtras();
			// answer = b.getString("answer");
			// composmail.setText(answer);

		}

		usernameEditTex = (EditText) findViewById(R.id.autoCompleteTextView);
		if (usernameEditTex.equals("")) {
			usernameEditTex.setHint("Friend");
		} else if (usernameEditTex.length() >= 0) {
			usernameEditTex.setText(destinationUsername);
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
				lname = stringBuilder.toString();

			}
			inputStream.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

			// name.setText("" + wdp);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// String ans = getIntent().getStringExtra("Answer");

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
				View someView = findViewById(R.id.smail);
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileInputStream fileIn = openFileInput("Data.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			String s = "";
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

			// composmail.setText("" + s);
			// uname = na.getText().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// SharedPreferences sp = getSharedPreferences("prefs", 0);
		String data = getIntent().getStringExtra("data");
		// uname = sp.getString("name","noname");

		// kuid = sp.getString("id","noid" );

		// when user click on username fields then it will show the list
		// of user to send the mail to him/her.
		usernameEditTex.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Notessend.this, MyFrndslist.class);
				i.putExtra("answerfrom", answer);
				startActivity(i);

			}
		});

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
		subject = (EditText) findViewById(R.id.mailbodyEditText);

		// scomposemail = composmail.getText().toString();
		ssubject = subject.getText().toString();

		sendmailButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// scomposemail = composmail.getText().toString();
				mailbody = composmail.getText().toString();

				if (composmail.getText().toString().length() == 0) {

					composmail.setError("Please enter mail body");

				} else if (usernameEditTex.getText().toString().length() == 0) {

					usernameEditTex.setError("Please enter username");

				} else {

					new SendmailAsynTask().execute(kuid, username,
							destinationuserId, destinationUsername, mailbody);

					// /**
					// * CRUD Operations
					// * */
					// Intent itr = new Intent(Notessend.this,
					// ListViewActivity.class);
					// AlaramData file = new
					// AlaramData(usernameEditTex.getText()
					// .toString(), answer, subject.getText().toString());
					// AlaramDA audioD = new AlaramDA();
					// audioD.saveAlaram(file);
					// startActivity(itr);
					// finish();
				}

			}
		});

	}

	// *This is use to filter the answer
	public String encodeURIComponent(String s) {
		String result;

		try {
			result = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20")
					.replaceAll("\\%21", "!").replaceAll("\\%27", "'")
					.replaceAll("\\%28", "(").replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~").replaceAll("\\n", " ");
		} catch (UnsupportedEncodingException e) {
			result = s;
		}

		return result;
	}

	/**
	 * This method use to send the eamil to end user.
	 * 
	 * @author Admin
	 * 
	 */

	private class SendmailAsynTask extends AsyncTask<String, Integer, Double> {

		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = new ProgressDialog(Notessend.this);
			progressDialog.setMessage("Sending mail..");
			progressDialog.setCancelable(false);
			progressDialog.show();

		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1], params[2], params[3], params[4]);
			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String userid, String username,
				String destUserid, String destusername, String composeBody)
				throws IllegalArgumentException {

			try {
				String Mail = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/composemailnotes/{srcusrid}/{srcusrname}/{destusrid}/{destusrname}/{mailbody}";
				String Sample = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/composemailnotes/";
				String basurl2 = "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/Uses%20of%20facebook/80/about%20mobile/3";
				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/";
				System.out.println("***************" + basurl2);

				String encodedCompostMailBody = encodeURIComponent(composeBody);
				Sample += userid + "/" + username + "/" + destUserid + "/"
						+ destusername + "/" + encodedCompostMailBody;
				// basurl3+= cname+"/"+kuid+"/"+uname+"/"+keyword+"/"+"3";
				System.out.println("***************" + basurl3);
				System.out.println("****************" + Sample);

				uri = new URI(Sample.replace(" ", "%20"));
				System.out.println("**************" + uri);

			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(uri);

				HttpResponse responses = httpClient.execute(httpGet);

				// is = entity.getContent();
				System.out.println("Response>>" + responses);

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

		@Override
		protected void onPostExecute(Double result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if (progressDialog != null && progressDialog.isShowing()) {

				progressDialog.dismiss();
				usernameEditTex.setText("");
				subject.setText("");
				composmail.setText("");
				finish();

			}

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

}
