//package com.motion.pi;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.json.JSONObject;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.BaseAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.google.gson.Gson;
////import com.motion.listviewfeed.adapter.FeedListAdapter;
//
//public class FCircle extends Activity {
//	private static final String TAG = FCircle.class.getSimpleName();
//	ListView listview;
//	public static final String MyPREFERENCES = "MyPrefs" ;
//	JSONObject jsonObj = null;
//	private ProgressDialog pDialog;
//	private int playlistPos;
//	String response;
//	ConnectionDetector cd;
//	private final int SPLASH_DISPLAY_LENGHT = 3000;
//	String qdet;
//	String rname;
//	String rts;
//	String qid;
//	String rid;
//	// Getting JSON Array node
//	//    String ata;
//	String sata;
//	//    private static String url = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswerforquestion/{idqtype}";
//	Boolean isInternetPresent = false;
//	private static  String url ="http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswer";
//	// JSON Node names
//	//    ArrayList<HashMap<String, String>> contactList;
//	private static final String TAG_RATING = "ratingtypevalue";
//	private static final String TAG_ID = "ansuserid";
//	private static final String TAG_NAME = "answerdetails";
//	private static final String TAG_userpost="createddate";
//	//    private static final String TAG_Quest="aboutmyquestion";
//	//    private static final String TAG_Qid="qtypeid";
//	//    private static final String TAG_date ="createddate";
//	ArrayList<HashMap<String, String>> contactList;
//	HashMap mn;
//	//    private FeedListAdapter listAdapter;
//	//	private static final String
//	static final int READ_BLOCK_SIZE = 100;
//	// Getting JSON Array node
//	String ata;
//	AlertDialog ald;
//	String id,nam;
//	String s;
//	private FeedListAdapter listAdapter;
//	String uname,kuid;
////	private List<FeedItem> feedItems;
//	@SuppressLint("NewApi")
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.details);
//
//
//		listview = (ListView) findViewById(R.id.list);
//
////		feedItems = new ArrayList<FeedItem>();
//
//		listAdapter = new FeedListAdapter(this);
//		listview.setAdapter(listAdapter);
//
//		new GetContacts().execute();
//
//
//
//
//		//		// We first check for cached request
//		//		Cache cache = AppController.getInstance().getRequestQueue().getCache();
//		//		Entry entry = cache.get(URL_FEED);
//		//		if (entry != null) {
//		//			// fetch the data from cache
//		//			try {
//		//				String data = new String(entry.data, "UTF-8");
//		//				try {
//		//					parseJsonFeed(new JSONObject(data));
//		//				} catch (JSONException e) {
//		//					e.printStackTrace();
//		//				}
//		//			} catch (UnsupportedEncodingException e) {
//		//				e.printStackTrace();
//		//			}
//		//
//		//		} else {
//		//			// making fresh volley request and getting json
//		//			JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
//		//					URL_FEED, null, new Response.Listener<JSONObject>() {
//		//
//		//				@Override
//		//				public void onResponse(JSONObject response) {
//		//					VolleyLog.d(TAG, "Response: " + response.toString());
//		//					if (response != null) {
//		//						parseJsonFeed(response);
//		//					}
//		//				}
//		//			}, new Response.ErrorListener() {
//		//
//		//				@Override
//		//				public void onErrorResponse(VolleyError error) {
//		//					VolleyLog.d(TAG, "Error: " + error.getMessage());
//		//				}
//		//			});
//		//
//		//			// Adding request to volley request queue
//		//			AppController.getInstance().addToRequestQueue(jsonReq);
//		//		}
//		//		// Create an object for subclass of AsyncTask
//		//		GetXMLTask task = new GetXMLTask();
//		//		// Execute the task
//		//		task.execute(new String[] { URL }); 
//
//	}
//	/**
//	 * Async task class to get json by making HTTP call
//	 * */
//	private class GetContacts extends AsyncTask<Void, Void, Void> {
//
//
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			// Showing progress dialog
//
//
//			pDialog = new ProgressDialog(FCircle.this);
//
//			pDialog.setMessage("Please wait...");
//
//			pDialog.setCancelable(true);
//			pDialog.show();
//
//		}
//
//		@Override
//		protected Void doInBackground(Void... arg0) {
//			// Creating service handler class instance
//
//			ServiceHandler sh = new ServiceHandler();
//
//			// Making a request to url and getting response
//			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
//			String vl="{\"jsonBean\":"+jsonStr+"}";
//			Log.d("Response: ", "> " + vl);
//
//			//JSONBean res=null;
//			if (vl != null) {
//				try {
//					//					JSONObject jsonObj = new JSONObject(jsonStr);
//					Gson gson = new Gson();
//					JSONanss res = gson.fromJson(vl, JSONanss.class);
//					List<AnswerHistory> bean = (List<AnswerHistory>) res.getJsonBean();
////					FeedItem item = new FeedItem();
//					// Getting JSON Array node
//					//contacts = jsonObj.getJSONArray(vl);
//
//					// looping through All Contacts
//					for (AnswerHistory listBean : bean) {
//
//						System.out.println("+++++++abt"+listBean.toString());
//						String abt=listBean.getAnswerdetails();
//						String dt=listBean.getAnsusername();
//						qid=listBean.getQuestionid().getQuestiondetails();
//						long rats = listBean.getRatingtypevalue();
//						rts = rts.valueOf(rats);
//						System.out.println("+++++++abt"+abt);
//						System.out.println("+++++++dt"+dt);
//						System.out.println("+++++++qid"+qid);
//						//                        String qtyp=listBean.getQtypeid();
//						//                        qdet=listBean.getQuestiondetails();
//						//                        String quid=listBean.getUserid();
//
//						HashMap<String, String> contact = new HashMap<String, String>();
//
//						//                        FeedItem item = new FeedItem();
//						// adding each child node to HashMap key => value
//						//                        item.put(TAG_ID, qid);
////						item.setName(listBean.getAnswerdetails());
////						item.setStatus(listBean.getAnsusername());
////						item.setImge(listBean.getQuestionid().getQuestiondetails());
////						item.put(TAG_ID,"By : " + dt);
//						contact.put(TAG_NAME,"Q : " + qid);
//						contact.put(TAG_userpost,"Ans : " + abt);
//						contact.put(TAG_RATING, rts);
//						System.out.println("******************"+rts);
//						//                        contact.put(TAG_Qid, qtyp);
//						//                        contact.put(TAG_date, dt);
//						//                        contact.put(TAG_EMAIL, email);
//						//                        contact.put(TAG_PHONE_MOBILE, mobile);
//
//						// adding contact to contact list
//						contactList.add(contact);
////						feedItems.add(contactList);
//
//
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//			super.onPostExecute(result);
//			// Dismiss the progress dialog
//			if (pDialog.isShowing())
//				pDialog.dismiss();
//
//
//
//			/**
//			 * Updating parsed JSON data into ListView
//			 * */
//			//			ListAdapter adapter = new SimpleAdapter(
//			//					Details.this, contactList,
//			//					R.layout.qlist_item, new String[] {
//			//							TAG_NAME,
//			//							TAG_userpost,TAG_ID,TAG_RATING
//			//
//			//							//							TAG_userpost,TAG_Quest,TAG_Qid,TAG_date
//			//					}, new int[] { R.id.qid,R.id.textView87,R.id.uname,R.id.rt
//			//					});
//			//			//            System.out.println("***********"+TAG_ID);
//			//
//			//			//							,R.id.qdets,R.id.uid,R.id.abt,R.id.qtid,R.id.date
//			//			setListAdapter(adapter);
//
//
//
//		}
//
//	}
//
//	/**
//	 * Parsing json reponse and passing the data to feed view list adapter
//	 * */
//
//	//	private void parseJsonFeed(JSONObject response) {
//	//		try {
//	//			JSONArray feedArray = response.getJSONArray("feed");
//	//
//	//			for (int i = 0; i < feedArray.length(); i++) {
//	//				JSONObject feedObj = (JSONObject) feedArray.get(i);
//	//
//	//				FeedItem item = new FeedItem();
//	//				//				item.setId(feedObj.getInt("id"));
//	//				item.setName(feedObj.getString("name"));
//	//				//				item.setName(feedObj.getString("title"));
//	//
//	//				// Image might be null sometimes
//	//				String image = feedObj.isNull("image") ? null : feedObj
//	//						.getString("image");
//	//				item.setImge(image);
//	//				item.setStatus(feedObj.getString("status"));
//	//				item.setProfilePic(feedObj.getString("profilePic"));
//	//				item.setTimeStamp(feedObj.getString("timeStamp"));
//	//
//	//				// url might be null sometimes
//	//				String feedUrl = feedObj.isNull("url") ? null : feedObj
//	//						.getString("url");
//	//				item.setUrl(feedUrl);
//	//
//	//				feedItems.add(item);
//	//			}
//	//
//	//			// notify data changes to list adapater
//	//			listAdapter.notifyDataSetChanged();
//	//
//	//
//	//
//	//		} 
//	//		catch (JSONException e) {
//	//			e.printStackTrace();
//	//		}
//	//	}
//	public boolean isNetworkAvailable1() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	protected String getString(String name2) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	class FeedListAdapter extends BaseAdapter {	
//		//		public MediaPlayer mMp;
//
//		private FCircle activity;
//		private LayoutInflater inflater;
////		private List<FeedItem> feedItems;
//
//		//		ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//
//
//
//
//		public FeedListAdapter(FCircle activity) {
//			this.activity = activity;
////			this.= feedItems;
//			playlistPos = 0;
//		}
//
////		@Override
////		public int getCount() {
////			return contactList.size();
////		}
//
//		@Override
//		public Object getItem(int location) {
//			return contactList.get(location);
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		//		@SuppressWarnings("null")
//		@Override
//		public View getView(final int position, View convertView, ViewGroup parent) {
//			//			convertView.setSelected(true);
//			LayoutInflater in = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//			convertView = in.inflate(R.layout.qlist_item, null);
//
//			//			if (imageLoader == null)imageLoader = AppController.getInstance().getImageLoader();
//			//
////			feedItems.get(playlistPos);
////			contactList.get(playlistPos);
//			//			tvurl = (TextView)findViewById(R.id.txtUrl);
//
//			final TextView name = (TextView) convertView.findViewById(R.id.qid);
//			final TextView urltxt = (TextView)convertView.findViewById(R.id.textView87);
//
//			//			final TextView br = (TextView)convertView.findViewById(R.id.textView1);
//
//
//
//
//			final TextView statusMsg = (TextView) convertView
//					.findViewById(R.id.textView1);
//			//			url = (TextView) convertView.findViewById(R.id.txtUrl);
//			//			final NetworkImageView profilePic = (NetworkImageView) convertView
//			//					.findViewById(R.id.profilePic);
//
//
//
////			FeedItem item = feedItems.get(position);
////			name.setText(""+TAG_NAME);
//			name.setText(TAG_NAME);
//			//			urltxt.setText(item.getUrl());
//
////			// Converting timestamp into x ago format
////			CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
////					Long.parseLong(item.getTimeStamp()),
////					System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
////			//			timestamp.setText(timeAgo);
////
////			// Chcek for empty status message
////			if (!TextUtils.isEmpty(item.getStatus())) {
////				statusMsg.setText(item.getStatus());
////				statusMsg.setVisibility(View.VISIBLE);
////			} else {
////				// status is empty, remove from view
////				statusMsg.setVisibility(View.GONE);
////			} 
//			//			adto.setOnClickListener(new OnClickListener() {
//			//				@Override
//			//				public void onClick(View v) {
//			//					// TODO Auto-generated method stub
//			//					//					Intent itr = new Intent(FCircle.this,Mylist.class);
//			//					//					AlaramData file = new AlaramData(name.getText().toString(),statusMsg.getText().toString(),urltxt.getText().toString());
//			//					//					AlaramDA audioDA= new AlaramDA();
//			//					//					audioDA.saveAlaram(file);
//			//					//					startActivity(itr);
//			//
//			//				}
//			//			});
//
//			final boolean istrue = true;
//			//			final TextView tscrol = (TextView)findViewById(R.id.sctext);
//			//			tscrol.setMovementMethod(new ScrollingMovementMethod());
////			final TextView txtscrol = (TextView)findViewById(R.id.scrolls);
//
//			convertView.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//
//
//
//				}
//			});
//
//
//
//			// user profile pic
//			//			Links = url.getText().toString();
//			//			profilePic.setImageUrl(item.getProfilePic(), imageLoader);
//
//			//			arrayList.get(playlistPos);
//			//			feedItems.get(playlistPos);
//
//			//			mp = MediaPlayer.create(activity, uri);
//			//			mp.prepareAsync();
//
//
//
//
//
//			return convertView;
//		}
//
//
//
//
//
//		protected void onCreate(Bundle savedInstanceState) {
//			// TODO Auto-generated method stub
//
//		}
//
//		public boolean onCreateOptionsMenu(Menu menu) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public int getCount() {
//			// TODO Auto-generated method stub
//			return 0;
//		}
//
//	}
//
//}
