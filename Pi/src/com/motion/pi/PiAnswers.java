package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.AnsNotificationCount;
import info.androidhive.customlistviewvolley.model.Jobs;
import info.androidhive.customlistviewvolley.model.LocalModel;
import info.androidhive.customlistviewvolley.model.Movie;
import info.androidhive.customlistviewvolley.model.OfflineData;
import info.androidhive.customlistviewvolley.model.Question;
import info.androidhive.customlistviewvolley.model.RingTone;
import info.androidhive.customlistviewvolley.model.UserList;
import info.androidhive.customlistviewvolley.util.ColorSessionManager;
import info.androidhive.customlistviewvolley.util.ImageTrans_roundedcorner;
import info.androidhive.customlistviewvolley.util.LookAndFeel;
import info.androidhive.customlistviewvolley.util.QANotificationdb;
import info.androidhive.customlistviewvolley.util.SettingDatbase;
import info.androidhive.customlistviewvolley.util.SubjectDb;
import info.androidhive.customlistviewvolley.util.UserHomepageDb;
import info.androidhive.customlistviewvolley.util.UserLoginDb;
import info.androidhive.customlistviewvolley.util.Util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.motion.calendar.MainActivity;
import com.motion.notification.queationanswer.QuestionNotificationActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PiAnswers extends Activity {
	View formElementsView;
	View formElementsView2;
	final Context context = this;
	private SQLiteAdapter mySQLiteAdapter;
	String skb;
	SimpleCursorAdapter cursorAdapter;
	private ImageView profileImage;
	View convertView;
	int w = 100;
	private static final String TAG = PiAnswers.class.getSimpleName();
	private static final String questionlist = "http://166.62.81.118:18080/SpringRestCrud/question/questionlist";
	String jobs = "http://166.62.81.118:18080/SpringRestCrud/adminnotification/allladminnotes/";
	private static final String url = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswer";
	private String urlans = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getnewanswercount/";
	private List<Movie> movieList = new ArrayList<Movie>();
	public static List<Jobs> jobsList;
	String get;
	private ListView homeListView;
	private OnlineQuestionAdapter adapter;
	private OfflineQuestionAdapter offlineAdapter;
	static final int READ_BLOCK_SIZE = 100;
	// set your json string url here
	String yourJsonStringUrl = "";
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";

	ImageView iprof;
	String lname;
	int requestCode;
	AlertDialog alertDialog;
	Bitmap bitmap;
	ImageView icompose;
	EditText sedit;
	String not = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getnewanswercount/";
	ImageView isets;
	ImageView him;
	TextView name;
	String data;
	private RingTone ringTone;
	private RingtoneManager ringtoneManager;
	private Ringtone rt;
	private String ringtoneFromDatabase;
	private SettingDatbase settingDatbase;
	private boolean isVibration;
	private Uri urifromDatabase;
	private ImageLoader imageLoader;

	private Movie movie;
	Toast toast = null;
	private UserHomepageDb userHomepageDb;
	private LinearLayout popNotificationImageView;
	public static ArrayList<UserList> userListArrayList;
	private ArrayList<OfflineData> offlinedata;
	public static ArrayList<Question> questionsArrayList;
	private String userid = "";
	private String username = "";
	private String counturl = "http://166.62.81.118:18080/SpringRestCrud/question/getnewqcount/";
	private URI uri;
	private ImageView largImageview;
	private ColorSessionManager colorSessionManager;
	NotificationManager notificationManager;
	private SubjectDb subjectDb;
	private UserHomepageDb homepageDb;
	private ArrayList<String> subjectList;
	private ProgressDialog progressDialog;
	private QANotificationdb qaNotificationdb;
	private RelativeLayout piSearchLayout;
	private UserLoginDb loginDb;
	private boolean isblockUser = false;
	private String firstname;
	private String mNotificationsCount;
	private TextView notificationcountTextView;
	private TextView ansnotificationcountTextView;
	private ArrayList<AnsNotificationCount> ansNotificationCountArrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relatans);
		long startTime = System.currentTimeMillis();

		name = (TextView) findViewById(R.id.textView5);
		iprof = (ImageView) findViewById(R.id.profile);
		him = (ImageView) findViewById(R.id.imageView5);
		icompose = (ImageView) findViewById(R.id.button);
		homeListView = (ListView) findViewById(R.id.homeListView);
		sedit = (EditText) findViewById(R.id.inputEditText);
		notificationcountTextView = (TextView) findViewById(R.id.notificationcountTextView);
		piSearchLayout = (RelativeLayout) findViewById(R.id.piSearchLayout);
		popNotificationImageView = (LinearLayout) findViewById(R.id.popNotificationLinearLayout);
		ansnotificationcountTextView = (TextView) findViewById(R.id.ansnotificationcountTextView);
		jobsList = new ArrayList<Jobs>();
		userListArrayList = new ArrayList<UserList>();
		homepageDb = new UserHomepageDb(this);
		questionsArrayList = new ArrayList<Question>();
		ringtoneManager = new RingtoneManager(this);
		progressDialog = new ProgressDialog(this);
		subjectDb = new SubjectDb(this);
		ringTone = new RingTone();
		settingDatbase = new SettingDatbase(this);
		userHomepageDb = new UserHomepageDb(this);
		offlinedata = new ArrayList<OfflineData>();
		isets = (ImageView) findViewById(R.id.settings);
		qaNotificationdb = new QANotificationdb(this);
		subjectList = new ArrayList<String>();
		loginDb = new UserLoginDb(this);
		ansNotificationCountArrayList = new ArrayList<AnsNotificationCount>();
		// Create the instance of color class by using a sharepreferences.
		colorSessionManager = new ColorSessionManager(this);

		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

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
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		makeJsonArrayRequest();

		makeUserListArrayRequst();
		makeUserQuestionRequest();

		// Here getting the all list of block subject list from user dont want
		// the question.
		Cursor subjectcursor = subjectDb.getAllSubjects();
		if (subjectcursor != null) {

			if (subjectcursor.moveToFirst()) {
				do {
					subjectList.add(subjectcursor.getString(subjectcursor
							.getColumnIndex("subj")));

					// do what ever you want here

				} while (subjectcursor.moveToNext());
			}

			subjectcursor.close();

		}

		mySQLiteAdapter = new SQLiteAdapter(this);
		mySQLiteAdapter.openToWrite();
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
				Log.d("LastName Image", lname);
			}

		} catch (Exception e) {
			Log.e(TAG, "Can not read file: " + e.toString());
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

		name.setText(username);
		link += userid + ".jpg";
		// new LoadImage().execute(link);
		// if the image not present the it will set the default image
		Picasso.with(this)
				.load(link)
				.placeholder(
						context.getResources().getDrawable(
								R.drawable.profilepic_sml)).into(him);
		System.out.println(">>>>>>>>>>>" + link);

		/**
		 * This method use to get the question count for showing the
		 * notification to end user.
		 */
		new GetCount().execute();

		/**
		 * This Asyntask use to get the answer notification count.
		 */
		new GetCountAnswer().execute();

		// when user click on search option it will first ask to enter keyword
		// to search if found it will go to next page otherwise ask to enter
		// keyword to search result.
		piSearchLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				get = sedit.getText().toString();

				if (Util.isNetworkAvailable(context)) {
					if (get.equals("")) {
						toast.setText("Please enter keyword to search");
						toast.setGravity(Gravity.CENTER, 0, 0);

						toast.show();

					} else {

						Intent i = new Intent(PiAnswers.this, Search.class);
						i.putExtra("Search", get);
						// after going to search the keyword clear the EditText
						// fields.
						sedit.setText("");
						startActivity(i);

					}

				} else {
					// Clear the text if internet is not ther.
					sedit.setText("");
					toast.setText("Please connect to internet");
					toast.show();

				}

			}
		});

		icompose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (Util.isNetworkAvailable(context)) {
					Intent i = new Intent(PiAnswers.this, Kompose.class);
					startActivity(i);
				} else {
					toast.setText("Please connect to internet");
					toast.show();
				}

			}
		});

		// When user click on the popup button then it will show the option to
		// user.
		popNotificationImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// PopupMenu popup = new PopupMenu(PiAnswers.this,
				// popNotificationImageView);
				// // Inflating the Popup using xml file
				// popup.getMenuInflater().inflate(R.menu.notification_menu,
				// popup.getMenu());
				//
				// popup.setOnMenuItemClickListener(new
				// PopupMenu.OnMenuItemClickListener() {
				// public boolean onMenuItemClick(MenuItem item) {
				//
				// if (item.getTitle().equals("Got answer")) {
				//
				// Intent intent = new Intent(PiAnswers.this,
				// AnswerNotificationActivity.class);
				// startActivity(intent);
				//
				// } else if (item.getTitle().equals("New question")) {
				//
				// Intent intent = new Intent(PiAnswers.this,
				// QuestionNotificationActivity.class);
				// startActivity(intent);
				// }
				//
				// return true;
				// }
				// });
				// popup.show();

				Intent intent = new Intent(PiAnswers.this,
						QuestionNotificationActivity.class);
				startActivity(intent);

			}
		});

		iprof.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (Util.isNetworkAvailable(context)) {
					Intent i = new Intent(PiAnswers.this, Profile.class);
					startActivity(i);
				} else {
					toast.setText("Please connect to inernet");
					toast.show();
				}

			}
		});
		isets.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (Util.isNetworkAvailable(context)) {
					Intent i = new Intent(PiAnswers.this, Settings.class);
					startActivity(i);
				} else {
					toast.setText("Please connect to inernet");
					toast.show();
				}

			}
		});

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
				View someView = findViewById(R.id.rela);

				// This method will take color by selected by user and view of
				// that class and set color to that view.
				// added by prasad.
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// if (Util.isNetworkAvailable(this)) {
		if (Util.isNetworkAvailable(this)) {
			adapter = new OnlineQuestionAdapter(this, questionsArrayList);
			// some fictitious objectList where we're populating data

			homeListView.setAdapter(adapter);

		} else {

			Cursor offlinecursor = homepageDb.getAllRecords();
			if (offlinecursor != null) {

				if (offlinecursor.moveToFirst()) {
					do {

						OfflineData offlineData = new OfflineData();
						offlineData.setQuestion(offlinecursor
								.getString(offlinecursor
										.getColumnIndex("question")));
						offlineData.setQuestionid(offlinecursor
								.getString(offlinecursor
										.getColumnIndex("questionsid")));
						offlineData.setKeyword(offlinecursor
								.getString(offlinecursor
										.getColumnIndex("keyword")));
						offlineData.setUsername(offlinecursor
								.getString(offlinecursor
										.getColumnIndex("quser")));
						offlineData.setRating(offlinecursor
								.getString(offlinecursor
										.getColumnIndex("qrate")));

						offlinedata.add(offlineData);

						// do what ever you want here

					} while (offlinecursor.moveToNext());

				}

				offlinecursor.close();

			}

			OfflineQuestionAdapter offlineQuestionAdapter = new OfflineQuestionAdapter();
			homeListView.setAdapter(offlineQuestionAdapter);

			if (progressDialog != null && progressDialog.isShowing()) {

				progressDialog.dismiss();

			}
		}

		// String link =
		// "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/gowri.jpg";

		// long endTime = System.currentTimeMillis();
		// long totalTime = endTime - startTime;
		// System.out.println("Total time>>>>" + totalTime);

	}

	// This method is use to make json paring request for the question list.

	private void makeUserQuestionRequest() {

		// Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(questionlist,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG,
								"hii&&&&&&&&&&&&&&&&&&&&&&:"
										+ response.toString());
						String QuestionResponse = response.toString();

						if (progressDialog != null
								&& progressDialog.isShowing()) {

							progressDialog.dismiss();

						}

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Question question = new Question();
								question.setIdquestion(obj
										.getLong("idquestion"));
								question.setAboutmyquestion(obj
										.getString("aboutmyquestion"));
								question.setQuestiondetails(obj
										.getString("questiondetails"));
								question.setQusername(obj
										.getString("qusername"));
								question.setIdquestion(obj
										.getLong("idquestion"));
								question.setRatingtypevalue(obj
										.getLong("ratingtypevalue"));
								question.setQuserid(obj.getLong("quserid"));
								question.setSubject(obj.getString("subject"));
								String subject = question.getSubject()
										.toString();
								// Check the condition weather subject list
								// empty or not if not empty then filter the
								// records to not get the records from the
								// specific list of the subject.
								// if ((subjectList != null)
								// && (!subjectList.contains(subject))) {
								// questionsArrayList.add(question);
								// }

								questionsArrayList.add(question);

								// String questionid = String.valueOf(question
								// .getIdquestion());
								// //
								// if (!userHomepageDb.Exists(questionid)) {
								//
								// userHomepageDb
								// .addQuestionData(questionsArrayList);
								// }

							} catch (Exception e) {
								e.printStackTrace();
							}

						}

						// This is solution to arrange the question is posted to
						// top of the ListView.
						Collections.reverse(questionsArrayList);

						homepageDb.deleteAllRecords();
						homepageDb.addQuestionData(questionsArrayList);

						String questionid = String.valueOf(questionsArrayList
								.get(0).getIdquestion());

						if (LocalModel.getInstance().isNewQuestionPosted() == true) {

							qaNotificationdb.inserIntoQuestionId(questionid);
							LocalModel.getInstance()
									.setNewQuestionPosted(false);
						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// VolleyLog.d(TAG, "Error: " + error.getMessage());

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
		// sending request for job notification details.

	}

	private void makeUserListArrayRequst() {

		String urljsonArry = "http://166.62.81.118:18080/SpringRestCrud/signup/userlist";
		JsonArrayRequest req = new JsonArrayRequest(urljsonArry,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());

						try {
							// Parsing json array response
							// loop through each json object
							for (int i = 0; i < response.length(); i++) {

								JSONObject obj = response.getJSONObject(i);

								UserList userList = new UserList();

								userList.setCity(obj.getString("city"));
								userList.setUserid(obj.getString("userid"));
								userList.setIdsignup(obj.getLong("idsignup"));
								userList.setCountry(obj.getString("country"));
								userList.setEmailid(obj.getString("emailid"));
								userList.setFastname(obj.getString("fastname"));
								userList.setLastname(obj.getString("lastname"));
								userList.setMobileno(obj.getString("mobileno"));
								userList.setImagepath(obj
										.getString("imagepath"));
								userList.setQualification(obj
										.getString("qualification"));
								userList.setStatus(obj.getString("status"));
								userListArrayList.add(userList);

								if (String.valueOf(
										userListArrayList.get(i).getIdsignup())
										.equals(userid)
										&& userListArrayList.get(i).getStatus()
												.toString().equals("INACTIVE")) {

									// set the boolean varible as true when user
									// is inactive by adminstrator.

									isblockUser = true;

								}

							}

							System.out.println("current user id" + userid);
							// This methos use to show the user profile data who
							// posted answer or question.

							if (isblockUser == true) {

								Toast.makeText(
										PiAnswers.this,
										"Your account has been block by administrator",
										Toast.LENGTH_LONG).show();

								Intent i = new Intent(PiAnswers.this,
										LoginActivity.class);

								// When user logout then it will first
								// take records from database and delete
								// it.
								Cursor cursor = loginDb.getAllRecords();

								if (cursor.moveToFirst()) {
									do {
										firstname = cursor.getString(cursor
												.getColumnIndex("username"));

										// do what ever you want here

									} while (cursor.moveToNext());
								}

								cursor.close();

								loginDb.deleteContact(firstname);
								colorSessionManager.removeAllPreferences();

								// if user will logout then all color
								// will be reset as yellow.
								// colorSessionManager
								// .removeAllPreferences();
								progressDialog.dismiss();

								startActivity(i);

								// set boolean value if user logout.

								finish();

							}

						} catch (Exception e) {
							e.printStackTrace();

						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// // VolleyLog.d(TAG, "Error: " + error.getMessage());
						// Toast.makeText(getApplicationContext(),
						// error.getMessage(), Toast.LENGTH_SHORT).show();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);

	}

	/**
	 * This method user to show user current data when user click on that
	 * specific user name.
	 * 
	 * @param currentuser
	 */
	protected void showCurrentUserProfile(String userid) {

		String firstname = null;
		String lastname = null;
		String emailid = null;
		String mobileno = null;
		String city = null;
		String country = null;
		String qualification = null;
		boolean isUserfound = false;

		for (int j = 0; j < userListArrayList.size(); j++) {

			try {
				String useridsign = String.valueOf(userListArrayList.get(j)
						.getIdsignup());

				// Here compare the username from Arraylist with getting user
				// from the when user press to any username.
				if (useridsign.equals(userid)) {
					firstname = userListArrayList.get(j).getFastname()
							.toString();
					lastname = userListArrayList.get(j).getLastname()
							.toString();
					emailid = userListArrayList.get(j).getEmailid().toString();
					mobileno = userListArrayList.get(j).getMobileno()
							.toString();
					city = userListArrayList.get(j).getCity().toString();
					country = userListArrayList.get(j).getCountry().toString();
					qualification = userListArrayList.get(j).getQualification()
							.toString();

					// set value as true if user found .
					isUserfound = true;

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// This condition will chcek weather user present in database or not if
		// present then it will show user profile.
		if (isUserfound) {

			Intent intent = new Intent(PiAnswers.this,
					UserProfileActivity.class);
			intent.putExtra("firstname", firstname);
			intent.putExtra("lastname", lastname);
			intent.putExtra("emailid", emailid);
			intent.putExtra("mobileno", mobileno);
			intent.putExtra("city", city);
			intent.putExtra("country", country);
			intent.putExtra("qualification", qualification);
			startActivity(intent);

		} else {
			toast.setText("user not present");
			toast.show();
			// if user not present in database the reset that user again to find
			// new user as per user click.
			isUserfound = false;
		}

	}

	/**
	 * Method to make json array request where response starts with [
	 * */
	private void makeJsonArrayRequest() {

		progressDialog.setMessage("Loading Please wait..");
		progressDialog.setCancelable(false);
		progressDialog.setIndeterminate(true);
		progressDialog.show();

		String urljsonArry = "http://166.62.81.118:18080/SpringRestCrud/adminnotification/allladminnotes/"
				+ 1 + "";
		JsonArrayRequest req = new JsonArrayRequest(urljsonArry,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());

						try {
							// Parsing json array response
							// loop through each json object
							for (int i = 0; i < response.length(); i++) {

								JSONObject obj = response.getJSONObject(i);

								Jobs jobs = new Jobs();
								jobs.setAdvdetails(obj.getString("advdetails"));
								jobs.setReqdate(obj.getString("reqdate"));

								// add job details data into List here.
								jobsList.add(jobs);

							}
							// sending joblist List to notifiaction method to
							// show notification when launch the application.

							showNotification(jobsList);

						} catch (Exception e) {
							e.printStackTrace();
							// Toast.makeText(getApplicationContext(),
							// "Error: " + e.getMessage(),
							// Toast.LENGTH_LONG).show();
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// VolleyLog.d(TAG, "Error: " + error.getMessage());
						// Toast.makeText(getApplicationContext(),
						// error.getMessage(), Toast.LENGTH_SHORT).show();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);

	}

	/* notification View */
	@SuppressWarnings("static-access")
	public void showNotification(List<Jobs> jobsList) {

		int TIMEOUT = 3000;

		// It will get all setting relate to ringtone which is currently sore in
		// database and then play.
		Cursor ringtoneCursor = settingDatbase.getAllSettingRecords();

		if (ringtoneCursor.moveToFirst()) {
			do {
				ringtoneFromDatabase = ringtoneCursor.getString(ringtoneCursor
						.getColumnIndex("ringtone"));

				urifromDatabase = Uri.parse(ringtoneFromDatabase);

			} while (ringtoneCursor.moveToNext());
		}

		// Here chceking condition if ringtone not null then it will close the
		// cusor.
		if (ringtoneCursor != null) {

			ringtoneCursor.close();
		}

		// This cursor use to get all vibration ralted setting from the local
		// database ann play base on that vibration or not.
		Cursor vibrationCursor = settingDatbase.getAllVibrationSettingRecords();

		if (vibrationCursor.moveToFirst()) {
			do {
				isVibration = Boolean
						.parseBoolean(vibrationCursor.getString(vibrationCursor
								.getColumnIndex("vibration")));

				urifromDatabase = Uri.parse(ringtoneFromDatabase);

			} while (vibrationCursor.moveToNext());
		}

		// when vibration cursor not null then close the cursor.
		if (vibrationCursor != null) {

			vibrationCursor.close();
		}

		// set boolean value if user logout.

		/*
		 * // itrate the loops to show jobs notification. for (int i = 0; i <
		 * jobsList.size(); i++) {
		 * 
		 * String jobTitle = jobsList.get(i).getAdvdetails().toString(); String
		 * jobExp = jobsList.get(i).getReqdate().toString();
		 * 
		 * Notification.Builder builder = new Notification.Builder(this); //
		 * Here first chcek condition weather user on vibration or not if // yes
		 * then on vibration. if (isVibration) {
		 * 
		 * builder.setContentTitle("" + jobTitle) .setContentText("" + jobExp)
		 * .setSmallIcon(R.drawable.icon) .setContentIntent(pIntent)
		 * .setSound(ringTone.getRigtoneuri()) .setVibrate(new long[] { 1000,
		 * 1000 }); notification = builder.build();
		 * 
		 * } else {
		 * 
		 * // if user not select Vibration on option then it will not play //
		 * Vibration. builder.setContentTitle("" + jobTitle) .setContentText(""
		 * + jobExp) .setSmallIcon(R.drawable.icon) .setContentIntent(pIntent)
		 * .setSound(ringTone.getRigtoneuri());
		 * 
		 * notification = builder.build();
		 * 
		 * }
		 * 
		 * // mNotification.sound = RingtoneManager //
		 * .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		 * notificationManager = (NotificationManager)
		 * getSystemService(NOTIFICATION_SERVICE);
		 * 
		 * // increment the notification values to show whatever jobs //
		 * notification are there. // rt = ringtoneManager.getRingtone(this, //
		 * Calender.userRingToneArryList.get(i).getRigtoneuri());
		 * 
		 * notificationManager.notify(i, notification);
		 * 
		 * // Here check the condition weather the notification is on/off, //
		 * bydefault it will on,if user click on the off button it will no //
		 * more longer visible to user. if
		 * (LocalModel.getInstance().isNotification() == true) {
		 * 
		 * notificationManager.cancel(i); }
		 * 
		 * }
		 */

		NotificationManager mNotificationManager = null;
		NotificationCompat.Builder mBuilder = null;

		Bitmap icon1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.icon);
		if (isVibration) {
			mBuilder = new NotificationCompat.Builder(this).setAutoCancel(true)
					.setContentTitle("Event Details")
					.setSmallIcon(R.drawable.icon)
					.setSound(ringTone.getRigtoneuri())
					.setVibrate(new long[] { 1000, 1000 }).setLargeIcon(icon1)
					.setContentText("");

		} else {
			mBuilder = new NotificationCompat.Builder(this).setAutoCancel(true)
					.setContentTitle("Event Details")
					.setSmallIcon(R.drawable.icon).setLargeIcon(icon1)
					.setContentText("");
		}

		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

		// Sets a title for the Inbox style big view
		inboxStyle.setBigContentTitle("Event Details");

		// Moves events into the big view
		for (int i = 0; i < jobsList.size(); i++) {

			inboxStyle.addLine(jobsList.get(i).getAdvdetails().toString());
			inboxStyle.addLine(jobsList.get(i).getReqdate().toString());

		}
		// Moves the big view style object into the notification object.
		inboxStyle.addLine("More..");
		mBuilder.setStyle(inboxStyle);

		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, MainActivity.class);

		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(MainActivity.class);

		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// mId allows you to update the notification later on.
		mNotificationManager.notify(100, mBuilder.build());

		/**
		 * If user change the setting to dont shwo notification then it will
		 * stop showing notification to user.
		 */
		if (LocalModel.getInstance().isNotification() == true) {

			notificationManager.cancel(100);

		}

		// Here playing ringtone for notification when user launch application
		// getting uri from the local database.
		if (urifromDatabase != null) {

			rt = ringtoneManager.getRingtone(this, urifromDatabase);
			rt.play();

			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					// it will chcek after 3 second if still Ringtone is
					// playing
					// then it will stop.
					if (rt.isPlaying())
						rt.stop();
				}
			}, TIMEOUT);

		} else {

		}

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);

		String stateSaved = savedInstanceState.getString("saved_state");

		// if(stateSaved == null){
		// Toast.makeText(PiAnswers.this,
		// "onRestoreInstanceState:\n" +
		// "NO state saved!",
		// Toast.LENGTH_LONG).show();
		// }else{
		// Toast.makeText(PiAnswers.this,
		// "onRestoreInstanceState:\n" +
		// "saved state = " + stateSaved,
		// Toast.LENGTH_LONG).show();
		// // textviewSavedState.setText(stateSaved);
		// sedit.setText(stateSaved);
		// }

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		String stateToSave = sedit.getText().toString();
		outState.putString("saved_state", stateToSave);
	}

	@Override
	public void onBackPressed() {
		Log.d("CDA", "onBackPressed Called");

		Intent setIntent = new Intent(PiAnswers.this, PiAnswers.class);
		startActivity(setIntent);

	}

	class OnlineQuestionAdapter extends BaseAdapter {
		private Activity activity;

		TextView quids;
		TextView answerTextView;
		private LayoutInflater inflater;
		View convertView;

		private ArrayList<Question> questionsArrayList;

		public OnlineQuestionAdapter(Activity activity,
				ArrayList<Question> questionsArrayList) {
			this.activity = activity;
			this.questionsArrayList = questionsArrayList;
		}

		@Override
		public int getCount() {
			return questionsArrayList.size();
		}

		@Override
		public Object getItem(int location) {
			return questionsArrayList.get(location);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			if (inflater == null)
				inflater = (LayoutInflater) activity
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (convertView == null) {

				convertView = inflater.inflate(
						R.layout.question_and_answer_list_layout, null);
				// store the holder with the view.
				ViewHolder viewHolder = new ViewHolder();

				viewHolder.ratingTextView = (TextView) convertView
						.findViewById(R.id.ratingTextView);
				viewHolder.questionTextView = (TextView) convertView
						.findViewById(R.id.questionTextView);
				viewHolder.keywordTextView = (TextView) convertView
						.findViewById(R.id.keywordTextView);
				viewHolder.questionIdTextView = (TextView) convertView
						.findViewById(R.id.idquestionTextView);
				viewHolder.questionRelativeLayout = (RelativeLayout) convertView
						.findViewById(R.id.questionRelativeLayout);
				viewHolder.nameTextView = (TextView) convertView
						.findViewById(R.id.nameTextView);
				viewHolder.useridTextView = (TextView) convertView
						.findViewById(R.id.useridTextView);

				convertView.setTag(viewHolder);
			}

			profileImage = (ImageView) convertView
					.findViewById(R.id.questionProfilePicture);

			// fill data
			final ViewHolder holder = (ViewHolder) convertView.getTag();

			holder.questionTextView.setText(questionsArrayList.get(position)
					.getQuestiondetails().toString());
			holder.keywordTextView.setText(questionsArrayList.get(position)
					.getAboutmyquestion().toString());
			/**
			 * This code is use to underline the text keyword.
			 */
			SpannableString mySpannableString = new SpannableString(
					questionsArrayList.get(position).getAboutmyquestion()
							.toString());
			mySpannableString.setSpan(new UnderlineSpan(), 0,
					mySpannableString.length(), 0);
			holder.keywordTextView.setText(mySpannableString);

			holder.questionIdTextView.setText(String.valueOf(questionsArrayList
					.get(position).getIdquestion()));
			holder.nameTextView.setText(questionsArrayList.get(position)
					.getQusername().toString());
			holder.useridTextView.setText(String.valueOf(questionsArrayList
					.get(position).getQuserid()));
			holder.ratingTextView.setText(String.valueOf(questionsArrayList
					.get(position).getRatingtypevalue()));

			final String iduser = holder.useridTextView.getText().toString();

			for (int i = 0; i < userListArrayList.size(); i++) {

				String userfromServer = String.valueOf(userListArrayList.get(i)
						.getIdsignup());

				if (userfromServer.equals(iduser)) {

					// profileImage.setImageUrl(
					// "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/"
					// + userListArrayList.get(i).getImagepath(),
					// imageLoader);

					loaduserProfile(iduser);
					System.out.println("username>>>" + iduser);
					// new LoadImageForListView()
					// .execute("http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/"
					// + userListArrayList.get(i).getImagepath());

				}

			}

			// Here check weather that position is visited or not if visited the
			// marked as white
			if (colorSessionManager.ItemVisited(position)) {
				holder.questionRelativeLayout.setBackgroundColor(getResources()
						.getColor(R.color.white));
			} else {
				holder.questionRelativeLayout.setBackgroundColor(getResources()
						.getColor(R.color.light_yellow));
			}

			// When user click on button it will show user profile in large
			// view.
			profileImage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					AlertDialog.Builder dialog = new AlertDialog.Builder(
							PiAnswers.this);

					// Create a custom layout for the dialog box
					LayoutInflater inflater = (LayoutInflater) PiAnswers.this
							.getSystemService(LAYOUT_INFLATER_SERVICE);
					final View layout = inflater.inflate(
							R.layout.image_preview, null, false);

					dialog.setView(layout);
					// dialog.setInverseBackgroundForced(true);

					final AlertDialog alertDialog = dialog.create();

					largImageview = (ImageView) layout
							.findViewById(R.id.pofileLargeImageView);
					String userids = holder.useridTextView.getText().toString();

					loadProfileImage(userids);

					// This class will call to load image of user profile.

					alertDialog.show();
					// This line which use to show the fix size of window on
					// users screen.

					try {
						ViewGroup viewGroup1 = (ViewGroup) layout.getParent();
						if (viewGroup1 != null) {
							viewGroup1
									.setBackgroundResource(android.R.color.transparent);

							ViewGroup viewGroup2 = (ViewGroup) viewGroup1
									.getParent();
							if (viewGroup2 != null) {
								viewGroup2
										.setBackgroundResource(android.R.color.transparent);
							}
						}
					} catch (Exception e) {
					}

				}
			});

			holder.nameTextView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String userid = holder.useridTextView.getText().toString();
					showCurrentUserProfile(userid);

				}
			});

			// when user click on listview then that answer to to question and
			// answer session.
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String question = holder.questionTextView.getText()
							.toString();
					String keyword = holder.keywordTextView.getText()
							.toString();
					String questionUsername = holder.nameTextView.getText()
							.toString();
					String questionid = holder.questionIdTextView.getText()
							.toString();
					String questionRating = holder.ratingTextView.getText()
							.toString();

					Intent reAnswerIntent = new Intent(PiAnswers.this,
							AnswersSearch.class);
					reAnswerIntent.putExtra("Questions", question);
					reAnswerIntent.putExtra("keyword", keyword);
					reAnswerIntent.putExtra("questionUsername",
							questionUsername);
					reAnswerIntent.putExtra("questionid", questionid);
					reAnswerIntent.putExtra("questionrate", questionRating);
					LocalModel.getInstance().setQuestion(question);
					LocalModel.getInstance().setQuestionname(questionUsername);
					LocalModel.getInstance().setQuestionid(questionid);

					// Here when user click on the question it will set the
					// position of that question.
					colorSessionManager.setItemVisited(position);

					startActivity(reAnswerIntent);

				}
			});

			// questionRelativeLayout
			// .setOnLongClickListener(new OnLongClickListener() {
			//
			// @Override
			// public boolean onLongClick(View v) {
			// PopupMenu popup = new PopupMenu(PiAnswers.this,
			// questionRelativeLayout);
			// // Inflating the Popup using xml file
			// popup.getMenuInflater().inflate(R.menu.popup_menu,
			// popup.getMenu());
			//
			// popup.setOnMenuItemClickListener(new
			// PopupMenu.OnMenuItemClickListener() {
			// public boolean onMenuItemClick(MenuItem item) {
			//
			// if (item.getTitle().equals("Share")) {
			//
			// toast.setText("Saved to archive");
			// toast.show();
			// Intent i = new Intent(PiAnswers.this,
			// Archive.class);
			// startActivity(i);
			// } else if (item.getTitle().equals(
			// "Report abuse")) {
			//
			// } else if (item.getTitle()
			// .equals("archive")) {
			//
			// toast.setText("Saved to archive");
			// toast.show();
			//
			// }
			// return true;
			// }
			// });
			//
			// popup.show();// showing popup menu
			//
			// return false;
			// }
			// });

			// cursor.close();

			return convertView;
		}

		// This method user to load user profile picture when user click on
		// circular picture.
		protected void loadProfileImage(String currentUsername) {

			progressDialog.setMessage("Loading..");
			progressDialog.setCancelable(true);
			progressDialog.show();
			String getimage = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
			getimage += currentUsername + ".jpg";

			if (getimage != null) {

				Picasso.with(getApplicationContext()).load(getimage)
						.into(largImageview, new Callback() {

							@Override
							public void onSuccess() {

								if (progressDialog != null
										&& progressDialog.isShowing()) {

									progressDialog.dismiss();
								}

							}

							@Override
							public void onError() {
								// TODO Auto-generated method stub
								largImageview
										.setImageResource(R.drawable.profilepic_sml);
								if (progressDialog != null
										&& progressDialog.isShowing()) {

									progressDialog.dismiss();
								}

							}
						});
				System.out.println(">>>>>>>>>>>" + getimage);
				// Here clear the path to set the image again tap on user
				// picture.
				getimage = "";

			}

		}

	}

	class ViewHolder {
		public TextView ratingTextView, questionTextView, keywordTextView,
				questionIdTextView, nameTextView, useridTextView;
		public RelativeLayout questionRelativeLayout;
		public ImageView profilePictureImageView;
	}

	public void loaduserProfile(String questionusername) {
		// TODO Auto-generated method stub
		String getimage = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
		getimage += questionusername + ".jpg";

		if (getimage != null && profileImage != null) {

			/**
			 * Here user placeholder if profile picture not present then store
			 * default profile picture.
			 */
			Picasso.with(getApplicationContext())
					.load(getimage)
					.placeholder(
							context.getResources().getDrawable(
									R.drawable.profilepic_sml)).resize(40, 40)
					.transform(new ImageTrans_roundedcorner())
					.into(profileImage);
			System.out.println(">>>>>>>>>>>" + getimage);
			// Here clear the path to set the image again tap on user
			// picture.
			getimage = "";

		}

	}

	// private class Abuse extends AsyncTask<String, Integer, Double> {
	// HttpEntity entity;
	// HttpResponse responses;
	// String b;
	// String ib;
	//
	// @Override
	// protected Double doInBackground(String... params)
	// throws ArrayIndexOutOfBoundsException {
	// // TODO Auto-generated method stub
	// postData(params[0]);
	//
	// return null;
	// }
	//
	// protected void onProgressUpdate(Integer... progress) {
	// // pb.setProgress(progress[0]);
	// }
	//
	// private void postData(String aids) {
	//
	// String updates =
	// "http://166.62.81.118:18080/SpringRestCrud/questionanswer/deleteans/";
	// updates += aids;
	// System.out.println("++++++++++" + updates);
	// // String
	// //
	// login="http://166.62.81.118:18080/SpringRestCrud/signup/login/loginis/";
	// //
	// login+=username+"/"+password+"/"+"10434328"+"/"+"9701513816"+"/"+"32.67"+"/"+"32.67"+"/"+"43434";
	// // System.out.println("***************" + baseurl);
	// // Execute the request
	// // HttpResponse response;
	// // List<ReuseItem> items=new ArrayList<ReuseItem>();
	//
	// try {
	// HttpClient httpClient = new DefaultHttpClient();
	// HttpGet httpGet = new HttpGet(updates);
	// responses = httpClient.execute(httpGet);
	// entity = responses.getEntity();
	// // String responseStr = EntityUtils.toString(entity);
	// String content = EntityUtils.toString(entity);
	// System.out.println("***********" + content);
	// JSONObject myObject = new JSONObject(content);
	//
	// } catch (ClientProtocolException e) {
	// e.printStackTrace();
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (ClassCastException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// // onPostExecute(result);
	// }
	//
	// protected void onPostExecute(Double result) {
	//
	// }
	//
	// private String convertStreamToString(InputStream is) {
	// /*
	// * To convert the InputStream to String we use the
	// * BufferedReader.readLine() method. We iterate until the
	// * BufferedReader return null which means there's no more data to
	// * read. Each line will appended to a StringBuilder and returned as
	// * String.
	// */
	// BufferedReader reader = new BufferedReader(
	// new InputStreamReader(is));
	// StringBuilder sb = new StringBuilder();
	//
	// String line = null;
	// try {
	// while ((line = reader.readLine()) != null) {
	// sb.append(line + "\n");
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// is.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// return sb.toString();
	// }
	//
	// }

	// private class Abuses extends AsyncTask<String, Integer, Double> {
	// HttpEntity entity;
	// HttpResponse responses;
	// String b;
	// String ib;
	//
	// @Override
	// protected Double doInBackground(String... params)
	// throws ArrayIndexOutOfBoundsException {
	// // TODO Auto-generated method stub
	// postData(params[0]);
	//
	// return null;
	// }
	//
	// protected void onProgressUpdate(Integer... progress) {
	// // pb.setProgress(progress[0]);
	// }
	//
	// private void postData(String aids) {
	//
	// String updates =
	// "http://166.62.81.118:18080/SpringRestCrud/question/deleteQuestion/";
	// updates += aids;
	// System.out.println("++++++++++" + updates);
	// // String
	// //
	// login="http://166.62.81.118:18080/SpringRestCrud/signup/login/loginis/";
	// //
	// login+=username+"/"+password+"/"+"10434328"+"/"+"9701513816"+"/"+"32.67"+"/"+"32.67"+"/"+"43434";
	// // System.out.println("***************" + baseurl);
	// // Execute the request
	// // HttpResponse response;
	// // List<ReuseItem> items=new ArrayList<ReuseItem>();
	//
	// try {
	// HttpClient httpClient = new DefaultHttpClient();
	// HttpGet httpGet = new HttpGet(updates);
	// responses = httpClient.execute(httpGet);
	// entity = responses.getEntity();
	// // String responseStr = EntityUtils.toString(entity);
	// String content = EntityUtils.toString(entity);
	// System.out.println("***********" + content);
	// JSONObject myObject = new JSONObject(content);
	//
	// } catch (ClientProtocolException e) {
	// e.printStackTrace();
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (ClassCastException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// // onPostExecute(result);
	// }
	//
	// protected void onPostExecute(Double result) {
	//
	// }
	//
	// private String convertStreamToString(InputStream is) {
	// /*
	// * To convert the InputStream to String we use the
	// * BufferedReader.readLine() method. We iterate until the
	// * BufferedReader return null which means there's no more data to
	// * read. Each line will appended to a StringBuilder and returned as
	// * String.
	// */
	// BufferedReader reader = new BufferedReader(
	// new InputStreamReader(is));
	// StringBuilder sb = new StringBuilder();
	//
	// String line = null;
	// try {
	// while ((line = reader.readLine()) != null) {
	// sb.append(line + "\n");
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// is.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// return sb.toString();
	// }
	//
	// }

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (toast != null) {
			toast.cancel();

		}
		/**
		 * Solve the problem to close the notification when application get
		 * closed.
		 */
		if (notificationManager != null) {
			notificationManager.cancelAll();

		}
	}

	/**
	 * This adapter use to show the data in offline mode to user when internet
	 * connection not available.
	 * 
	 * @author Admin
	 * 
	 */
	private class OfflineQuestionAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return offlinedata.size();
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
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (view == null) {

				view = LayoutInflater.from(context).inflate(
						R.layout.question_offline_layout, null);

			}

			final TextView ratingTextView = (TextView) view
					.findViewById(R.id.ratingTextView);
			final TextView questionTextView = (TextView) view
					.findViewById(R.id.questionTextView);
			TextView keywordTextView = (TextView) view
					.findViewById(R.id.keywordTextView);

			final TextView questionIdTextView = (TextView) view
					.findViewById(R.id.idquestionTextView);
			RelativeLayout questionsRelativeLayout = (RelativeLayout) view
					.findViewById(R.id.questionRelativeLayout);
			questionsRelativeLayout.setBackgroundColor(getResources().getColor(
					R.color.light_yellow));

			final TextView nameTextView = (TextView) view
					.findViewById(R.id.nameTextView);
			ratingTextView.setText(offlinedata.get(position).getRating()
					.toString());
			questionTextView.setText(offlinedata.get(position).getQuestion()
					.toString());
			keywordTextView.setText(offlinedata.get(position).getKeyword()
					.toString());
			nameTextView.setText(offlinedata.get(position).getUsername()
					.toString());
			questionIdTextView.setText(offlinedata.get(position)
					.getQuestionid());
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String question = questionTextView.getText().toString();
					String questionusername = nameTextView.getText().toString();
					String questionrating = ratingTextView.getText().toString();
					String questionid = questionIdTextView.getText().toString();

					Intent intent = new Intent(PiAnswers.this,
							AnswersSearch.class);

					intent.putExtra("question", question);
					intent.putExtra("questionuser", questionusername);
					intent.putExtra("rate", questionrating);
					intent.putExtra("questionid", questionid);
					startActivity(intent);

				}
			});
			return view;
		}
	}

	// private class AnswerRatingUpdateAsynTask extends
	// AsyncTask<String, Integer, Double> {
	// private ProgressDialog progressDialog;
	//
	// @Override
	// protected void onPreExecute() {
	// // TODO Auto-generated method stub
	// super.onPreExecute();
	// System.out.println("On prexcute method");
	// progressDialog = new ProgressDialog(getBaseContext());
	// progressDialog.setMessage("Please wait..");
	// progressDialog.show();
	// }
	//
	// @Override
	// protected Double doInBackground(String... params)
	// throws ArrayIndexOutOfBoundsException {
	// postData(params[0], params[1]);
	// return null;
	// }
	//
	// protected void onPostExecute(Double result) {
	//
	// if (progressDialog != null && progressDialog.isShowing()) {
	// progressDialog.dismiss();
	//
	// }
	//
	// }
	//
	// protected void onProgressUpdate(Integer... progress) {
	// // pb.setProgress(progress[0]);
	// }
	//
	// private void postData(String idanswer, String ansrating)
	// throws IllegalArgumentException {
	//
	// try {
	// String answerRating =
	// "http://166.62.81.118:18080/SpringRestCrud/questionanswer/updateratting/";
	//
	// answerRating += idanswer + "/" + ansrating;
	// System.out.println("url of response" + answerRating);
	//
	// uri = new URI(answerRating.replace(" ", "%20"));
	//
	// // uri = Uri.parse(out);
	// } catch (URISyntaxException e) {
	// e.printStackTrace();
	// } catch (RuntimeException e) {
	// e.printStackTrace();
	// }
	//
	// try {
	// HttpClient httpClient = new DefaultHttpClient();
	// HttpGet httpGet = new HttpGet(uri);
	//
	// HttpResponse responses = httpClient.execute(httpGet);
	//
	// HttpEntity entity = (HttpEntity) responses.getEntity();
	//
	// // is = entity.getContent();
	//
	// } catch (ClientProtocolException e) {
	// e.printStackTrace();
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (ClassCastException e) {
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	// }

	// // This asyntaks use to load the image of user profile.
	// class LoadImageForListView extends AsyncTask<String, String, Bitmap> {
	//
	// Bitmap profileBitmap;
	//
	// @Override
	// protected void onPreExecute() {
	// super.onPreExecute();
	//
	// }
	//
	// protected Bitmap doInBackground(String... args) {
	// try {
	// profileBitmap = BitmapFactory
	// .decodeStream((InputStream) new URL(args[0])
	// .getContent());
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return profileBitmap;
	// }
	//
	// protected void onPostExecute(Bitmap image) {
	//
	// if (image != null) {
	// Bitmap roundImage = CircularImage.getRoundedCroppedBitmap(
	// image, 30);
	// profileImage.setImageBitmap(roundImage);
	//
	// } else {
	//
	// }
	// }
	// }

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetCount extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			counturl += userid;

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance

			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			mNotificationsCount = sh.makeServiceCall(counturl,
					ServiceHandler.GET);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog

			if (mNotificationsCount != null
					&& Integer.parseInt(mNotificationsCount) != 0) {

				notificationcountTextView.setVisibility(View.VISIBLE);
				notificationcountTextView.setText(mNotificationsCount);
				// notificationTextview.set

			} else {
				notificationcountTextView.setVisibility(View.GONE);
			}

		}
	}

	/**
	 * This asyntask use to get the answer notififation of respected question.
	 * 
	 * @author Admin
	 * 
	 */
	private class GetCountAnswer extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			urlans += userid;

			System.out.println("URL>>>" + url);

		}

		@SuppressWarnings("unchecked")
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance

			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String answResponse = sh
					.makeServiceCall(urlans, ServiceHandler.GET);

			System.out.println("Response>>>>" + answResponse);

			Gson gson = new Gson();
			Type listType = new TypeToken<List<AnsNotificationCount>>() {
			}.getType();
			ansNotificationCountArrayList = (ArrayList<AnsNotificationCount>) gson
					.fromJson(answResponse, listType);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog

			int countTotal = 0;

			if (ansNotificationCountArrayList != null) {

				for (int i = 0; i < ansNotificationCountArrayList.size(); i++) {

					int anscount = Integer
							.parseInt(ansNotificationCountArrayList.get(i)
									.getCnt().toString());

					System.out.println("ans" + anscount);

					if (anscount != 0) {

						countTotal = countTotal + anscount;

					}

				}

			}

			/**
			 * check the condition weather user having notification, if yes then
			 * show otherwise hide textview.
			 */
			if (countTotal == 0) {

				ansnotificationcountTextView.setVisibility(View.GONE);

			} else {

				ansnotificationcountTextView
						.setText(String.valueOf(countTotal));
			}

		}
	}

	private boolean checkData() {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobileNetInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean hasMobileConnection = (mobileNetInfo != null)
				&& mobileNetInfo.isConnected();
		return hasMobileConnection;
	}

}
