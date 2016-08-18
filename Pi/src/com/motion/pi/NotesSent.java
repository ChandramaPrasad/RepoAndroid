package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.AllSentmailDao;
import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;

public class NotesSent extends CustomActionBar {

	public static final String MyPREFERENCES = "MyPrefs";
	JSONObject jsonObj = null;
	private ProgressDialog pDialog;
	String response;
	ConnectionDetector cd;
	String qdet;
	// Getting JSON Array node
	// String ata;
	String sata;
	private static String url = "http://localhost:18080/SpringRestCrud/mailnotes/allsentmails/14";
	Boolean isInternetPresent = false;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	String wdps;
	ImageView pis;
	private static final String TAG_CONTACTS = "";
	private static final String TAG_ID = "idquestion";
	String kuid;
	private static final String TAG_NAME = "questiondetails";
	private static final String TAG_userpost = "quserid";
	private static final String TAG_Quest = "qusername";
	private static final String TAG_Qid = "qtypeid";
	private static final String TAG_date = "createddate";
	ArrayList<HashMap<String, String>> contactList;
	static final int READ_BLOCK_SIZE = 100;
	String skb;
	String dt;
	String slink;
	HashMap mn;
	// private FeedListAdapter listAdapter;
	// private static final String
	// Getting JSON Array node
	String uname;
	String ata;
	String uid;
	private String username = "";
	private URI uri;
	private ArrayList<AllSentmailDao> allSentmailDaosArrayList;
	private SentMailAdpater sentMailAdpater;
	private ListView sentmailListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sentmail);
		allSentmailDaosArrayList = new ArrayList<AllSentmailDao>();
		sentmailListView = (ListView) findViewById(R.id.sentmailListView);
		sentMailAdpater = new SentMailAdpater();

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
			// tn.setText("" + s);
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
		/**
		 * This method use to send the request to get all sent mail by user.
		 */
		getAllSentMail(kuid);
		// setting the adapter here.
		sentmailListView.setAdapter(sentMailAdpater);
		/**
		 * setting the profile picture here.
		 */

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
				View someView = findViewById(R.id.sentmailback);
				LookAndFeel.lookAndFeel(skb, someView);
				// This method use to change the background color when user
				// select from the look and feel from settings.
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This adapter use to set the mail to sent item.
	 * 
	 * @author Admin
	 * 
	 */
	private class SentMailAdpater extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return allSentmailDaosArrayList.size();
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

			ViewHolder viewHolder;

			if (convertView == null) {

				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.slist_iten, null);

				viewHolder.nameTextView = (TextView) convertView
						.findViewById(R.id.from);
				viewHolder.messageBodyTextView = (TextView) convertView
						.findViewById(R.id.mmm);
				viewHolder.dateTextView = (TextView) convertView
						.findViewById(R.id.cdate);
				viewHolder.mailIdTextView = (TextView) convertView
						.findViewById(R.id.mailIdTextView);

				convertView.setTag(viewHolder);

			}

			final ViewHolder holder = (ViewHolder) convertView.getTag();

			holder.nameTextView.setText(allSentmailDaosArrayList.get(position)
					.getDestusername());
			holder.messageBodyTextView.setText(allSentmailDaosArrayList.get(
					position).getCompdata());
			holder.dateTextView.setText(allSentmailDaosArrayList.get(position)
					.getReqdate());
			holder.mailIdTextView
					.setText(String.valueOf(allSentmailDaosArrayList.get(
							position).getIdmail()));

			/***
			 * when user click on the button it will show mail in full screen.
			 */
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String username = holder.nameTextView.getText().toString();
					String messageBody = holder.messageBodyTextView.getText()
							.toString();
					String date = holder.dateTextView.getText().toString();
					String mailid = holder.mailIdTextView.getText().toString();

					Intent detailIntent = new Intent(NotesSent.this,
							DetailsEmailActivity.class);
					detailIntent.putExtra("username", username);
					detailIntent.putExtra("composebody", messageBody);
					detailIntent.putExtra("date", date);
					detailIntent.putExtra("mailid", mailid);
					detailIntent.putExtra("mail", "sent");
					startActivity(detailIntent);
				}
			});

			return convertView;
		}
	}

	/**
	 * This class work as a view holder to hold the views.
	 * 
	 * @author Admin
	 * 
	 */
	class ViewHolder {
		private TextView nameTextView, dateTextView, messageBodyTextView,
				mailIdTextView;
	}

	/***
	 * This method use to get all the sent mail records using userid.
	 * 
	 * @param userid
	 */
	private void getAllSentMail(String userid) {

		String finalurl = null;

		String url = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/allsentmails/";
		url += userid;
		try {
			uri = new URI(url.replace(" ", "%20"));
			URL url2 = uri.toURL();
			finalurl = url2.toString();
			pDialog = new ProgressDialog(NotesSent.this);
			// Showing progress dialog before making http request
			pDialog.setMessage("Loading sent mails...");
			pDialog.show();
		} catch (URISyntaxException | MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("**************uri" + uri);
		System.out.println("**************uri" + uri);
		JsonArrayRequest req = new JsonArrayRequest(finalurl,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						try {
							// Parsing json array response
							// loop through each json object
							for (int i = 0; i < response.length(); i++) {

								JSONObject obj = response.getJSONObject(i);

								AllSentmailDao allSentmailDao = new AllSentmailDao();

								allSentmailDao.setSrcusername(obj
										.getString("srcusername"));
								allSentmailDao.setReqdate(obj
										.getString("reqdate"));
								allSentmailDao.setCompdata(obj
										.getString("compdata"));
								allSentmailDao.setDestusername(obj
										.getString("destusername"));
								allSentmailDao.setIdmail(obj.getLong("idmail"));

								allSentmailDaosArrayList.add(allSentmailDao);

							}

							sentMailAdpater.notifyDataSetChanged();

							hidePDialog();

						} catch (Exception e) {
							e.printStackTrace();

							hidePDialog();
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);
	}

	/**
	 * After getting response from the server hide the dialog.
	 */
	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

}
