package com.motion.pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Json extends ListActivity {

	private ProgressDialog pDialog;

	// URL to get contacts JSON
//	private static String url = "http://api.androidhive.info/contacts/";
	private static  String url ="http://166.62.81.118:18080/SpringRestCrud/question/getQuestionTypeList";

	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "idqtype";
	private static final String TAG_NAME = "idqtype";
	private static final String TAG_EMAIL = "qtype";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_GENDER = "gender";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_PHONE_MOBILE = "mobile";
	private static final String TAG_PHONE_HOME = "home";
	private static final String TAG_PHONE_OFFICE = "office";

	// contacts JSONArray
	JSONArray contacts = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.json);

		contactList = new ArrayList<HashMap<String, String>>();

		ListView lv = getListView();

		// Listview on item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// getting values from selected ListItem
				String name = ((TextView) view.findViewById(R.id.name))
						.getText().toString();
				String cost = ((TextView) view.findViewById(R.id.email))
						.getText().toString();
				String description = ((TextView) view.findViewById(R.id.mobile))
						.getText().toString();

				// Starting single contact activity
				Intent in = new Intent(getApplicationContext(),
						SingleContactActivity.class);
				in.putExtra(TAG_NAME, name);
				in.putExtra(TAG_EMAIL, cost);
				in.putExtra(TAG_PHONE_MOBILE, description);
				startActivity(in);

			}
		});

		// Calling async task to get json
		new GetContacts().execute();
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(Json.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			String TAG_CONTACTS = "";

			Log.d("Response: ", "> " + jsonStr);
			String json ="";
			String readTwitterFeed = readTwitterFeed();
			try {
				JSONArray jsonArray = new JSONArray(readTwitterFeed);
				Log.i(ParseJson.class.getName(),
						"Number of entries " + jsonArray.length());
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					Log.i(ParseJson.class.getName(), jsonObject.getString("qtype"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

//			JSONArray jsonarray = null;
//			try {
//				jsonarray = new JSONArray(json);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//
//			for (int i = 0; i < jsonarray.length(); i++) {
//
//				JSONObject jsonobj = null;
//				try {
//					jsonobj = jsonarray.getJSONObject(i);
//					System.out.println("categoryId : " + i + " = " + jsonobj.getString("idqtype"));
//					System.out.println("Title : " + i + " = " + jsonobj.getString("qtype"));
//					System.out.println("songs : " + i + " = " + jsonobj.getString("songs"));
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//
//			}

//			if (jsonStr != null) {
//			try {
//				JSONObject jsonObj = new JSONObject(jsonStr);
//
//				// Getting JSON Array node
//				contacts = jsonObj.getJSONArray(jsonStr);
//				for (int i= 0;i < contacts.length();i++){
//					// looping through All Contacts
////					for (int i = 0; i < contacts.length(); i++) {
//					JSONObject c = contacts.getJSONObject(i);
//
//					String id = c.getString(TAG_ID);
//					String name = c.getString(TAG_NAME);
//					String email = c.getString(TAG_EMAIL);
//					String address = c.getString(TAG_ADDRESS);
//					String gender = c.getString(TAG_GENDER);
//
//					// Phone node is JSON Object
//					JSONObject phone = c.getJSONObject(TAG_PHONE);
//					String mobile = phone.getString(TAG_PHONE_MOBILE);
//					String home = phone.getString(TAG_PHONE_HOME);
//					String office = phone.getString(TAG_PHONE_OFFICE);
//
//					// tmp hashmap for single contact
//					HashMap<String, String> contact = new HashMap<String, String>();
//
//					// adding each child node to HashMap key => value
//					contact.put(TAG_ID, id);
//					contact.put(TAG_NAME, name);
//					contact.put(TAG_EMAIL, email);
//					contact.put(TAG_PHONE_MOBILE, mobile);
//
//					// adding contact to contact list
//					contactList.add(contact);
//				}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
//			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			ListAdapter adapter = new SimpleAdapter(
					Json.this, contactList,
					R.layout.uulist_item, new String[] { TAG_NAME, TAG_EMAIL,
					TAG_PHONE_MOBILE }, new int[] { R.id.name,
					R.id.email, R.id.mobile });

			setListAdapter(adapter);
		}

	}
	public String readTwitterFeed() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://twitter.com/statuses/user_timeline/vogella.json");
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(ParseJson.class.toString(), "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}

