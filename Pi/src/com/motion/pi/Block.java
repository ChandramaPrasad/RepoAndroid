package com.motion.pi;

import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpEntity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.motion.actionbar.CustomActionBar;

//import android.widget.Toast;

public class Block extends CustomActionBar {
	static final int READ_BLOCK_SIZE = 100;
	String skb;
	String names, nids;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	String blockemail = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/blockmailorchat/1/balaram/3/baburao/mail/mail";
	String wdps;
	ImageView pis;
	String s;
	CheckBox chk1;
	private String userid = "";
	private ImageView submitButton;
	private String username = "";
	private String blockUsername = null;
	private String blockUserid;
	private ImageView blockmailImageView;
	private ImageView blockquestionImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.block);
		submitButton = (ImageView) findViewById(R.id.submit);

		blockmailImageView = (ImageView) findViewById(R.id.BlockmailImageView);
		blockquestionImageView = (ImageView) findViewById(R.id.blockquestionImageView);

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
				View someView = findViewById(R.id.blo);

				// This method use to change the background color when user
				// select from the look and feel
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		blockquestionImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(Block.this,
						BlockQuestionActivity.class);
				startActivity(intent);

			}
		});

		blockmailImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Block.this, BlockMailActivity.class);
				startActivity(intent);

			}
		});

		// submitButton.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// if (!TextUtils.isEmpty(blockUsername) && blockUsername != null
		// && blockUserid != null) {
		// new Blockchat().execute(userid, username, blockUserid,
		// blockUsername);
		// } else {
		//
		// Toast.makeText(getApplicationContext(),
		// "Please select friend", 9000).show();
		// }
		//
		// }
		// });

	}

	/**
	 * This method use to block the user when user dont want to receive mail
	 * from that particular user.
	 * 
	 * @author Admin
	 * 
	 */
	private class Blockchat extends AsyncTask<String, Integer, Double> {
		URI uri;
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("OnPreExecute");
			progressDialog = new ProgressDialog(Block.this);
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

}
