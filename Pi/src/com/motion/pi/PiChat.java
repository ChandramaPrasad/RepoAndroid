package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.ChatHistory;
import info.androidhive.customlistviewvolley.model.Chitchat;
import info.androidhive.customlistviewvolley.util.LookAndFeel;
import info.androidhive.customlistviewvolley.util.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.motion.actionbar.CustomActionBar;
import com.motion.chat.adapter.ChatMessageAdapter;
import com.squareup.picasso.Picasso;

public class PiChat extends CustomActionBar {
	// Log tag
	View formElementsView;
	String idd;
	String qid;
	String qqid;
	String skb;
	View convertView;
	int w = 100;
	private static final String TAG = PiChat.class.getSimpleName();

	// Movies json url
	// private static final String url =
	// "http://api.androidhive.info/json/movies.json";

	// private static final String url =
	// "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/frndsywrite";
	private ProgressDialog pDialog;
	URI uri;
	private List<Chitchat> movieList = new ArrayList<Chitchat>();
	private ListView listView;
	private Timer timer;
	String nam;
	SharedPreferences sps;
	String id;
	String kuid;
	private String username = "";
	ImageView sendchat;
	private final int SPLASH_DISPLAY_LENGHT = 3000;
	String Name;
	String Cid;
	String s;
	String Nid;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private String chatbetweenurl = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/skchatbyuser/";
	String matter;
	static final int READ_BLOCK_SIZE = 100;
	String wdps;
	private String destusername;
	private String destuserid;
	private TextView nameTextView;
	private EditText chatDescription;
	private ImageView chatProfilePic;
	private ChatMessageAdapter chatMessageAdapter;
	private ArrayList<ChatMessage> chatMessages;
	private ArrayList<ChatHistory> chatHistories;
	ArrayAdapter<ChatMessageAdapter> arrayAdapter;
	private Handler handler;
	private EditText frndssearchEditText;
	int textlength = 0;
	private Context context;

	String url = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/skchatbyuser/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chatlist);

		final String Cname = getIntent().getStringExtra("DNAME");
		Cid = getIntent().getStringExtra("DUID");
		// new getchat().execute();
		nameTextView = (TextView) findViewById(R.id.nameTextView);
		listView = (ListView) findViewById(R.id.list);
		final EditText search = (EditText) findViewById(R.id.multiss);
		sendchat = (ImageView) findViewById(R.id.sendMessage);
		chatDescription = (EditText) findViewById(R.id.chatText);
		chatProfilePic = (ImageView) findViewById(R.id.chatProfilePic);
		frndssearchEditText = (EditText) findViewById(R.id.searchEditText);
		chatMessages = new ArrayList<ChatMessage>();
		chatHistories = new ArrayList<ChatHistory>();
		context = this;
		handler = new Handler();

		listView.setDivider(null);
		timer = new Timer();

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
		destusername = getIntent().getStringExtra("destusername");
		destuserid = getIntent().getStringExtra("destuserid");
		nameTextView.setText(destusername);
		url += destuserid + "/" + kuid;

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
				View someView = findViewById(R.id.main);
				// This method use to change the background color when user
				// going to select from look and feel settings.
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		link += destuserid + ".jpg";
		// new LoadImage().execute(link);
		// if the image not present the it will set the default image
		Picasso.with(this)
				.load(link)
				.placeholder(
						context.getResources().getDrawable(
								R.drawable.profilepic_sml))
				.into(chatProfilePic);
		// getChatMessage();

		sendchat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				sendMessage(chatDescription.getText().toString());
				chatDescription.setText("");

			}

		});

		// chatDescription.setOnTouchListener(new OnTouchListener() {
		//
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		// // TODO Auto-generated method stub
		// // do your stuff here..
		// timer.cancel();
		// return false;
		// }
		// });
		// listView.setAdapter(customListAdapter);

		chatDescription.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub

				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {

					timer.cancel();

					// sendMessage(chatDescription.getText().toString(),
					// UserType.OTHER);

					chatDescription.setText("");
					return true;
				}

				return false;
			}
		});

		/**
		 * The runnable method that is called every 2 seconds.
		 */
		Runnable run = new Runnable() {
			public void run() {
				// new Comments(false).execute();
				new SendToServer().execute();
				handler.postDelayed(this, 1600);
			}
		};
		runOnUiThread(run);

		// frndssearchEditText.addTextChangedListener(new TextWatcher() {
		//
		// @Override
		// public void onTextChanged(CharSequence s, int start, int before,
		// int count) {
		// // TODO Auto-generated method stub
		//
		//
		//
		// }
		//
		// @Override
		// public void beforeTextChanged(CharSequence s, int start, int count,
		// int after) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void afterTextChanged(Editable s) {
		// // TODO Auto-generated method stub
		//
		// }
		// });

		// handler = new Handler();
		// handler.postDelayed(new Runnable() {
		//
		// @Override
		// public void run() {
		//
		// PiChat.this.runOnUiThread(new Runnable() {
		// public void run() {
		//
		// if (chatHistories != null) {
		// chatHistories.clear();
		// }
		//
		// }
		//
		// });
		// makeRequestToGetFriendsMessage();
		// handler.postDelayed(this, 60 * 100);
		// }
		// }, 60 * 100);

		// getChatMessage();
		// chatMessageAdapter = new ChatMessageAdapter(PiChat.this,
		// chatHistories,
		// kuid);
		// listView.setAdapter(chatMessageAdapter);
		// chatMessageAdapter.notifyDataSetChanged();

		// chatMessageAdapter.notifyDataSetChanged();

		// chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
		//
		// public void OnChanged() {
		// super.onChanged();
		// listView.setSelection(chatArrayAdapter.getCount() - 1);
		// }
		// });

	}

	private void sendMessage(String sentmessage) {

		if (sentmessage.trim().length() == 0)
			return;

		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setMessage(sentmessage);
		chatMessages.add(chatMessage);

		if (chatMessageAdapter != null) {
			chatMessageAdapter.notifyDataSetChanged();

		}
		new SendingMessage().execute(kuid, username, destuserid, destusername,
				sentmessage);

	}

	/**
	 * This method use to send the receiver message from the user.
	 */
	public void makeRequestToGetFriendsMessage() {

		try {
			JsonArrayRequest movieReq = new JsonArrayRequest(url,
					new Response.Listener<JSONArray>() {
						@Override
						public void onResponse(JSONArray response) {
							Log.d(TAG,
									"hii&&&&&&&&&&&&&&&&&&&&&&:"
											+ response.toString());
							// hidePDialog();

							// Parsing json
							for (int i = 0; i < response.length(); i++) {
								try {

									JSONObject obj = response.getJSONObject(i);
									// Movie movie = new Movie();
									ChatHistory chathistory = new ChatHistory();

									chathistory.setIdskywritefrnd(obj
											.getLong("idskywritefrnd"));
									chathistory.setChatdesc(obj
											.getString("chatdesc"));
									chathistory.setDestuserid(obj
											.getLong("destuserid"));
									chathistory.setDestusername(obj
											.getString("destusername"));
									chathistory.setSrcuserid(obj
											.getLong("srcuserid"));
									chathistory.setSrcusername(obj
											.getString("srcusername"));

									chatHistories.add(chathistory);

								} catch (Exception e) {
									e.printStackTrace();
								}

							}

							chatMessageAdapter.notifyDataSetChanged();

						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							VolleyLog.d(TAG, "Error: " + error.getMessage());
							// hidePDialog();

						}
					});

			// Adding request to request queue
			AppController.getInstance().addToRequestQueue(movieReq);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/***
	 * This asyntask use to chat to user.
	 * 
	 * @author Admin
	 * 
	 */
	private class SendingMessage extends AsyncTask<String, Integer, Double> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			System.out.println("OnpreExecute");
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1], params[2], params[3], params[4]);
			return null;
		}

		protected void onPostExecute(Double result) {
			// pb.setVisibility(View.GONE);
			// Toast.makeText()

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String userid, String username,
				String destUserid, String destUsername, String chatdes)
				throws IllegalArgumentException {

			try {
				String basurl2 = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/freindrequest/{srcuserid}/{srcusername}/{destuserid}/{destusername}";
				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/frndsywrite/";
				String cht = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/frndsywrite/28/baburao/14/diptim/where%20are%20you";
				// System.out.println("***************"+basurl2);

				String allchat = Util.encodeURIComponent(chatdes);
				//String allchat = StringEscapeUtils.escapeJava(chatdes);
				basurl3 += userid + "/" + username + "/" + destUserid + "/"
						+ destUsername + "/" + allchat;
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

	// Every 10000 ms
	private void getChatMessage() {

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {

				try {

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}, 0, 10000);
	}

	class SendToServer extends AsyncTask<String, Integer, Double> {

		String chathistory;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			System.out.println("My url is>>>>" + url);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {

			ServiceHandler sh = new ServiceHandler();
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			chathistory = "{\"chatHistories\":" + jsonStr + "}";
			Gson gson = new Gson();
			Type listType = new TypeToken<List<ChatHistory>>() {
			}.getType();
			chatHistories = (ArrayList<ChatHistory>) gson.fromJson(jsonStr,
					listType);
			// ChatModel res = gson.fromJson(chathistory, ChatModel.class);

			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		@Override
		protected void onPostExecute(Double result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			chatMessageAdapter = new ChatMessageAdapter(PiChat.this,
					chatHistories, kuid);
			listView.setAdapter(chatMessageAdapter);

		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (timer != null) {
			timer.cancel();
		}
		handler.removeMessages(0);
		handler.removeCallbacksAndMessages(null);
	}

}
