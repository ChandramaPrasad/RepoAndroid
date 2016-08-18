package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.InboxMailDao;
import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;

/**
 * Created by Admin on 16-09-2015.
 */
public class Notesmail extends CustomActionBar {
	ListView lv;
	public static final String MyPREFERENCES = "MyPrefs";
	JSONObject jsonObj = null;
	private ProgressDialog pDialog;
	String response;
	ConnectionDetector cd;
	String skb;
	String qdet;
	// Getting JSON Array node
	// String ata;
	String sata;
	// private static String url =
	// "http://166.62.81.118:18080/SpringRestCrud/mailnotes/alllmails/19";
	Boolean isInternetPresent = false;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private static final String TAG_CONTACTS = "";
	private static final String TAG_ID = "idquestion";
	private static final String TAG_NAME = "questiondetails";
	private static final String TAG_userpost = "quserid";
	private static final String TAG_Quest = "qusername";
	private static final String TAG_Qid = "qtypeid";
	static final int READ_BLOCK_SIZE = 100;
	ArrayList<HashMap<String, String>> contactList;
	String dt;
	HashMap mn;
	String uname;
	String ata;
	String uid;
	String kuid;
	String urls;
	String s;
	String wdps;
	ImageView pr;
	String popUpContents[];
	private String username = "";
	PopupWindow popupWindowDogs;
	public static ArrayList<InboxMailDao> inboxMailDaoArrayList;
	private ListView inListView;
	private URI uri;
	private MailAdapter mailAdapter;

	// Button buttonShowDropDown;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nottes);
		// TextView name = (TextView)findViewById(R.id.textView11);
		// DatabaseHandler db = new DatabaseHandler(this);
		inListView = (ListView) findViewById(R.id.inboxListView);
		inboxMailDaoArrayList = new ArrayList<InboxMailDao>();
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

		// This method use to send request to get inbox mail from the user.
		// Here Kuid meand userid that is Greeet is euqual to 65.
		makeInboxMailRequest(kuid);
		// set the adapter of listview here.
		mailAdapter = new MailAdapter();
		inListView.setAdapter(mailAdapter);
		// set the user profile picture to header.

		ImageView home = (ImageView) findViewById(R.id.hds);
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
				View someView = findViewById(R.id.nots);
				// This method use to change the background color when user
				// select from look and feel.
				LookAndFeel.lookAndFeel(skb, someView);
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
		}

		ImageView more = (ImageView) findViewById(R.id.moreImageView);
		try {
			FileInputStream fileIn = openFileInput("mytextfile.txt");
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
			// tn.setText("" + s);
			// uname = names.getText().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("++++++++++++" + kuid);
		System.out.println("++++++++++++" + uname);

		// new GetContacts().execute();
		contactList = new ArrayList<HashMap<String, String>>();

		// lv = getListView();

		List<String> dogsList = new ArrayList<String>();
		dogsList.add("Compose");
		dogsList.add("Sent Mails");

		// convert to simple array
		popUpContents = new String[dogsList.size()];
		dogsList.toArray(popUpContents);

		/*
		 * initialize pop up window
		 */
		popupWindowDogs = popupWindowDogs();

		/*
		 * button on click listener
		 */
		more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindowDogs.showAsDropDown(v, -5, 0);
			}
		});
		// View.OnClickListener handler = new View.OnClickListener() {
		// public void onClick(View v) {
		//
		// switch (v.getId()) {
		//
		// case R.id.imageView40:
		// // show the list view as dropdown
		//
		// break;
		// }
		// }
		// };

		// our button
		// buttonShowDropDown = (Button) findViewById(R.id.clists);
		/**
		 * This button is use to refresh the mail when user click on refresh
		 * button.
		 */
		ImageView refresh = (ImageView) findViewById(R.id.imageView37);
		refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new GetContacts().execute();
			}
		});

		// lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// // getting values from selected ListItem
		// // String selectedFromList = (String)
		// // lv.getItemAtPosition(position);
		// // String cm;
		// // cm = (String) parent.getItemAtPosition(position);
		// HashMap<String, String> map = (HashMap<String, String>) parent
		// .getItemAtPosition(position);
		// String value = map.get(TAG_NAME);
		// String idd = map.get(TAG_ID);
		// }
		// });
		// name.setText("" + uname);
		// Toast.makeText(getApplicationContext(),""+uname,Toast.LENGTH_LONG).show();

		final ImageView archive = (ImageView) findViewById(R.id.imageView36);
		archive.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(Notesmail.this, Archive.class);
				startActivity(i);
			}
		});

	}

	/**
	 * Popup window menu show to user to compose the mail to send via circle.
	 * 
	 * @return
	 */
	public PopupWindow popupWindowDogs() {

		// initialize a pop up window type
		final PopupWindow popupWindow = new PopupWindow(this);

		// the drop down list is a list view
		ListView listViewDogs = new ListView(this);

		// set our adapter and pass our pop up window contents
		listViewDogs.setAdapter(dogsAdapter(popUpContents));

		// set the item click listener
		// listViewDogs.setOnItemClickListener(new
		// DogsDropdownOnItemClickListener());
		listViewDogs.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String item = parent.getItemAtPosition(position).toString();
				if (item.equals("Compose")) {
					// activityToOpen = DisplayMessageActivity.class;
					/* To compose Mail */
					Intent i1 = new Intent(Notesmail.this, Notessend.class);
					startActivity(i1);
					popupWindow.dismiss();
				} else if (item.equals("Sent Mails")) {
					// activityToOpen = Numbers.class;
					/* To get list of Sent Mails */
					Intent i2 = new Intent(Notesmail.this, NotesSent.class);
					startActivity(i2);
					popupWindow.dismiss();
				}

			}
		});

		// some other visual settings
		popupWindow.setFocusable(true);
		popupWindow.setWidth(250);
		popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

		// set the list view as pop up window content
		popupWindow.setContentView(listViewDogs);
		popupWindow.dismiss();

		return popupWindow;
	}

	/*
	 * adapter where the list values will be set
	 */
	private ArrayAdapter<String> dogsAdapter(String dogsArray[]) {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dogsArray) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				// setting the ID and text for every items in the list
				String item = getItem(position);
				String[] itemArr = item.split("::");
				String text = itemArr[0];
				// String id = itemArr[1];

				// visual settings for the list item
				TextView listItem = new TextView(Notesmail.this);

				listItem.setText(text);
				// listItem.setTag(id);
				listItem.setTextSize(22);
				listItem.setPadding(10, 10, 10, 10);
				listItem.setTextColor(Color.WHITE);

				return listItem;
			}
		};

		return adapter;
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

			pDialog = new ProgressDialog(Notesmail.this);

			pDialog.setMessage("Loading Inbox...");

			pDialog.setCancelable(true);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();

		}

	}

	// Design the custom adapter to Disaly the mail.
	private class MailAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return inboxMailDaoArrayList.size();
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
			ViewHolder viewHolder;

			if (convertView == null) {

				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.mail_item, null);

				viewHolder.usernameTextView = (TextView) convertView
						.findViewById(R.id.mailnameTextView);
				viewHolder.dateTextView = (TextView) convertView
						.findViewById(R.id.mailDateTextView);
				viewHolder.messageTextView = (TextView) convertView
						.findViewById(R.id.from);
				viewHolder.mailIdTextView = (TextView) convertView
						.findViewById(R.id.mailIdTextView);

				convertView.setTag(viewHolder);

			}

			final ViewHolder holder = (ViewHolder) convertView.getTag();

			holder.usernameTextView.setText(inboxMailDaoArrayList.get(position)
					.getCompdata());
			holder.dateTextView.setText(inboxMailDaoArrayList.get(position)
					.getReqdate());
			holder.messageTextView.setText(inboxMailDaoArrayList.get(position)
					.getSrcusername());
			holder.mailIdTextView.setText(String.valueOf(inboxMailDaoArrayList
					.get(position).getIdmail()));

			/**
			 * When user click on the convertview he/she will able to see mail
			 * in large view.
			 */
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String composebody = holder.usernameTextView.getText()
							.toString();
					String date = holder.dateTextView.getText().toString();
					String username = holder.messageTextView.getText()
							.toString();
					String mailid = holder.mailIdTextView.getText().toString();

					Intent sendmailIntent = new Intent(Notesmail.this,
							DetailsEmailActivity.class);
					sendmailIntent.putExtra("composebody", composebody);
					sendmailIntent.putExtra("username", username);
					sendmailIntent.putExtra("date", date);
					sendmailIntent.putExtra("mailid", mailid);
					sendmailIntent.putExtra("mail", "inbox");
					startActivity(sendmailIntent);

				}
			});

			return convertView;
		}
	}

	// create the class as the viewHolder to hold the object.
	private class ViewHolder {

		TextView usernameTextView, dateTextView, messageTextView,
				mailIdTextView;

	}

	/*
	 * This method use to send the request to get the mail from the server.
	 */
	private void makeInboxMailRequest(String userid) {

		String finalurl = null;

		String url = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/alllmails/";
		url += userid;
		try {
			uri = new URI(url.replace(" ", "%20"));
			URL url2 = uri.toURL();
			finalurl = url2.toString();
			pDialog = new ProgressDialog(Notesmail.this);
			// Showing progress dialog before making http request
			pDialog.setMessage("Loading Inbox...");
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

								InboxMailDao inboxMailDao = new InboxMailDao();

								inboxMailDao.setSrcusername(obj
										.getString("srcusername"));
								inboxMailDao.setReqdate(obj
										.getString("reqdate"));
								inboxMailDao.setCompdata(obj
										.getString("compdata"));
								inboxMailDao.setIdmail(obj.getLong("idmail"));

								inboxMailDaoArrayList.add(inboxMailDao);

							}

							mailAdapter.notifyDataSetChanged();
							System.out.println("Inbox response>>>"
									+ inboxMailDaoArrayList);

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
