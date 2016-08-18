package com.motion.pi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpEntity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;

public class Chatt extends ListActivity {
	private static final int MESSAGE_CANNOT_BE_SENT = 0;
	private ProgressDialog pDialog;
	public String username;
	private static String url = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/skchatbyuser/28/16";
	private EditText messageText;
	URI uri;
	private static final String TAG_CONTACTS = "";
	private static final String TAG_ID = "userid";
	private static final String TAG_NAME = "imagepath";
	private static final String TAG_userpost="userid";
	private static final String TAG_Quest="aboutmyquestion";
	private static final String TAG_fid="chatdesc";
	private static final String TAG_date ="createddate";
	private EditText messageHistoryText;
	private Button sendMessageButton;
	private IAppManager imService;
	private FriendInfo friend = new FriendInfo();
	//	private LocalStorageHandler localstoragehandler; 
	private Cursor dbCursor;
	ArrayList<HashMap<String, String>> contactList;

	String fidd;
	ListView lv;
	String msg1;
	String una;

	String ui;
	static final int READ_BLOCK_SIZE = 100;
	String s;
	String kuid;
	String fname;
	String fid;
	@Override
	protected void onCreate(Bundle savedInstanceState){

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	   
		fname = getIntent().getStringExtra("DNAME");
		fid = getIntent().getStringExtra("DUID");
		new GetContacts().execute();
		setContentView(R.layout.messaging_screen); //messaging_screen);
		try {
			FileInputStream fileIn=openFileInput("mytextfile.txt");
			InputStreamReader InputRead= new InputStreamReader(fileIn);

			char[] inputBuffer= new char[READ_BLOCK_SIZE];
			s="";
			String d="";
			int charRead;

			while ((charRead=InputRead.read(inputBuffer))>0) {
				// char to string conversion
				String readstring=String.copyValueOf(inputBuffer,0,charRead);
				s +=readstring;		
				String readStrings = String.copyValueOf(inputBuffer,0,charRead);
				d += readStrings;

			}
			InputRead.close();
			Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();
			//			prof.setText("" + s);
			//			uname = prof.getText().toString();


		} catch (Exception e) {
			e.printStackTrace();
		}
		lv = getListView();


		try {
			FileInputStream fileIn=openFileInput("mytextfiles.txt");
			InputStreamReader InputRead= new InputStreamReader(fileIn);

			char[] inputBuffer= new char[READ_BLOCK_SIZE];
			kuid="";
			int charRead;

			while ((charRead=InputRead.read(inputBuffer))>0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer,0,charRead);
				kuid += readStrings;

			}
			InputRead.close();
			Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		fidd = getIntent().getStringExtra("fid");

		//		messageHistoryText = (EditText) findViewById(R.id.messageHistory);

		messageText = (EditText) findViewById(R.id.message);

		messageText.requestFocus();			

		sendMessageButton = (Button) findViewById(R.id.sendMessageButton);

		Bundle extras = this.getIntent().getExtras();


		////		friend.userName = extras.getString(FriendInfo.USERNAME);
		//		friend.ip = extras.getString(FriendInfo.IP);
		//		friend.port = extras.getString(FriendInfo.PORT);
		//		String msg = extras.getString(MessageInfo.MESSAGETEXT);



		setTitle("Messaging with " + friend.userName);


		//	EditText friendUserName = (EditText) findViewById(R.id.friendUserName);
		//	friendUserName.setText(friend.userName);


		//		localstoragehandler = new LocalStorageHandler(this);
		//		dbCursor = localstoragehandler.get(friend.userName, IMService.USERNAME );

		//		if (dbCursor.getCount() > 0){
		//			int noOfScorer = 0;
		//			dbCursor.moveToFirst();
		//			while ((!dbCursor.isAfterLast())&&noOfScorer<dbCursor.getCount()) 
		//			{
		//				noOfScorer++;
		//
		//				this.appendToMessageHistory(dbCursor.getString(2) , dbCursor.getString(3));
		//				dbCursor.moveToNext();
		//			}
		//		}
		//		localstoragehandler.close();

		//		if (msg != null) 
		//		{
		//			this.appendToMessageHistory(friend.userName , msg);
		//			((NotificationManager)getSystemService(NOTIFICATION_SERVICE)).cancel((friend.userName+msg).hashCode());
		//		}

		sendMessageButton.setOnClickListener(new OnClickListener(){
			CharSequence message;
			Handler handler = new Handler();
			public void onClick(View arg0) {
				message = messageText.getText();


				msg1 =messageText.getText().toString();
				//				messageHistoryText.setText(""+msg1);
				//				String citys=spt2.getText().toString();
				//				String countrys=spt3.getText().toString();
				// Save the Data in Database
				//                    loginDataBaseAdapter.insertEntry(userName, Paswd);

				new MyAsyncTask().execute(msg1, fname, fid);
				

				//				if (message.length()>0) 
				//				{		
				//					appendToMessageHistory(imService.getUsername(), message.toString());
				//
				//					//					localstoragehandler.insert(imService.getUsername(), friend.userName, message.toString());
				//
				//					messageText.setText("");
				//					Thread thread = new Thread(){					
				//						public void run() {
				//							try {
				//								if (imService.sendMessage(imService.getUsername(), friend.userName, message.toString()) == null)
				//								{
				//
				//									handler.post(new Runnable(){	
				//
				//										public void run() {
				//
				//											Toast.makeText(getApplicationContext(),R.string.message_cannot_be_sent, Toast.LENGTH_LONG).show();
				//
				//
				//											//showDialog(MESSAGE_CANNOT_BE_SENT);										
				//										}
				//
				//									});
				//								}
				//							} catch (UnsupportedEncodingException e) {
				//								Toast.makeText(getApplicationContext(),R.string.message_cannot_be_sent, Toast.LENGTH_LONG).show();
				//
				//								e.printStackTrace();
				//							}
				//						}						
				//					};
				//					thread.start();
				//
				//				}

			}});



	}
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog


			pDialog = new ProgressDialog(Chatt
					.this);

			pDialog.setMessage("Please wait...");

			pDialog.setCancelable(true);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance

			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			String vl="{\"jsonBean\":"+jsonStr+"}";
			Log.d("Response: ", "> " + vl);

			//JSONBean res=null;
			if (vl != null) {
				try {
					//					JSONObject jsonObj = new JSONObject(jsonStr);
					Gson gson = new Gson();
					JSONcha res = gson.fromJson(vl, JSONcha.class);
					List<JSONchat> bean = (List<JSONchat>) res.getJsonBean();

					// Getting JSON Array node
					//contacts = jsonObj.getJSONArray(vl);

					// looping through All Contacts
					for (JSONchat listBean : bean) {

						System.out.println("+++++++abt"+listBean.toString());
						String abt=listBean.getChatdesc();
//						String dt=listBean.get
//						String idq = listBean.getIdquestion();
						//						String qid=listBean.getQuestionid().getQuestiondetails();
						//						long rats = listBean.getRatingtypevalue();
						//						rts = rts.valueOf(rats);
						System.out.println("+++++++abt"+abt);
//						System.out.println("+++++++dt"+dt);
						//						System.out.println("+++++++qid"+qid);
						//                        String qtyp=listBean.getQtypeid();
						//                        qdet=listBean.getQuestiondetails();
						//                        String quid=listBean.getUserid();

						HashMap<String, String> contact = new HashMap<String, String>();

						//                        FeedItem item = new FeedItem();
						// adding each child node to HashMap key => value
						//                        item.put(TAG_ID, qid);

//						contact.put(TAG_ID, dt);
						contact.put(TAG_NAME, abt);
//						contact.put(TAG_userpost, idq);
						//						contact.put(TAG_userpost,"Ans : " + abt);
//						contact.put(TAG_RATING, rts);
//						System.out.println("******************"+rts);
						//                        contact.put(TAG_Qid, qtyp);
						//                        contact.put(TAG_date, dt);
						//                        contact.put(TAG_EMAIL, email);
						//                        contact.put(TAG_PHONE_MOBILE, mobile);

						// adding contact to contact list
						contactList.add(contact);



					}
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
			if (pDialog.isShowing())
				pDialog.dismiss();



			/**
			 * Updating parsed JSON data into ListView
			 * */
			ListAdapter adapter = new SimpleAdapter(
					Chatt.this, contactList,
					R.layout.chatitem, new String[] {
							TAG_NAME,


							//							TAG_userpost,TAG_Quest,TAG_Qid,TAG_date
					}, new int[] { R.id.qid
					});
			//            System.out.println("***********"+TAG_ID);

			//							,R.id.qdets,R.id.uid,R.id.abt,R.id.qtid,R.id.date
			setListAdapter(adapter);
		}

	}
	private class MyAsyncTask extends AsyncTask<String, Integer, Double> {
		@Override
		protected Double doInBackground(String... params) throws ArrayIndexOutOfBoundsException{
			// TODO Auto-generated method stub
			postData(params[0],params[1],params[2]);



			return null;
		}

		protected void onPostExecute(Double result) {
			//            pb.setVisibility(View.GONE);
			//            Toast.makeText()
		}

		protected void onProgressUpdate(Integer... progress) {
			//            pb.setProgress(progress[0]);
		}


		public void postData(String fnames, String Paswds, String lnamess
				) {
			// Create a new HttpClient and Post Header

			//            String fname = "" + name.getText().toString();
			//        System.out.println("***************"+name);
			//        System.out.println("***************"+pawd);
			//        System.out.println("***************"+name);
			//        System.out.println("***************"+lname);
			//        System.out.println("***************"+num);
			//        System.out.println("***************"+nmn);
			//        System.out.println("***************"+abt);
			//            String lname = "" + lastname.getText().toString();

			//            String baseurl="http://166.62.81.118:18080/SpringRestCrud/signup/createuser/"+fname+;
			String exurl = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/frndsywrite/28/baburao/29/balaram/hi";
			System.out.println("*********"+exurl);
			try {
				String baseurl = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/frndsywrite/";
				//				baseurl+= "28"+"/"+"baburao"+"/"+"16"+"/"+"Varun"+"/"+msg1;
				baseurl+= kuid+"/"+s+"/"+fid+"/"+fname+"/"+msg1;
				//				messageHistoryText.setText(""+msg1);
				System.out.println("***************" + baseurl);


				uri = new URI(baseurl.replace(" ", "%20"));
				System.out.println("***************" + uri);
				//                uri = Uri.parse(out);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}catch (RuntimeException e){
				e.printStackTrace();
			}

			//                restUrl = URLEncoder.encode(baseurl, "UTF-8");
			//            } catch (UnsupportedEncodingException e) {
			//                e.printStackTrace();}
			//            }catch (IllegalStateException e){
			//                e.printStackTrace();
			//            }

			//			String baseurl2 = "http://166.62.81.118:18080/SpringRestCrud/signup/createuser/Sarkar/passwords/Sarkar/Raj/M/software/7/sarkar@gmail.com/00789/hello";
			//			//            String baseurl2= "http://166.62.81.118:18080/SpringRestCrud/signup/createuser/diptim/password/balaram/barada/M/software%20eng/7/balaram.b@pyrogroup.com/9701513816/hi";
			//			//        String baseurl3 = "";
			//			System.out.println("***************" + uri);

			//            baseurl+="Baburao/"+"babu/"+"BabuRao/"+"Siddi/"+"Male/"+"Iam Engineer/"+"07/04/1990"+""+"baburao4790@gmail.com"+"7799591404"+"Hello";
			//            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			//            nameValuePairs.add(new BasicNameValuePair("setFastname", "Baburao"));
			//            nameValuePairs.add(new BasicNameValuePair("lastname", lname));

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(uri);
				//                Intent i= new Intent(Firsts.this,MainActivity.class);
				////                String msgs = "Details Entered Succesfully";
				//
				//                startActivity(i);
				//                HttpPost httpPost = new HttpPost("http://166.62.81.118:18080/SpringRestCrud/signup/createuser");
				//                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				//                    HttpResponse response = httpClient.execute(httpPost);
				HttpResponse responses = httpClient.execute(httpGet);
				//                Toast.makeText(getApplicationContext(), "Details Entered Succesfully", Toast.LENGTH_LONG).show();
				//                Toast.makeText(getApplicationContext(),"Please login to ur Account ",Toast.LENGTH_LONG).show();
				HttpEntity entity = (HttpEntity) responses.getEntity();

				//                is = entity.getContent();

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassCastException e) {
				e.printStackTrace();
			}catch (IllegalArgumentException e){
				e.printStackTrace();
			}


		}
	}


}
