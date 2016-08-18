package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.ForgotModel;
import info.androidhive.customlistviewvolley.model.User;
import info.androidhive.customlistviewvolley.model.UserList;
import info.androidhive.customlistviewvolley.util.UserLoginDb;
import info.androidhive.customlistviewvolley.util.Util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;

public class LoginActivity extends Activity {
	ImageView l;
	String idsignup;
	Button s;
	EditText e2;
	TextView tl;
	AlaramData audio;
	TextView t2;

	double lat;
	double lng;
	int i = 2;
	static float screen_width;

	EditText pwd;

	// JSON Node Names
	private static final String TAG_USER = "idqtype";
	private static final String TAG_ID = "qtype";
	// private static final String TAG_NAME = "name";
	// private static final String TAG_EMAIL = "email";
	JSONArray jarray;
	String nam;
	private String mobileNumber;
	String pw;
	String abts;
	// LoginDataBaseHelper loginDataBaseAdapter;
	JSONArray user = null;
	int position = 2;
	DatabaseHandler db;
	EditText uname;
	TextView tp;
	Cursor cursor;
	EditText p;
	TextView tu;
	private static String url = "http://166.62.81.118:18080/SpringRestCrud/signup/userlist";
	// Cursor cursor;
	SharedPreferences sp;
	private UserLoginDb userLoginDb;
	private User userdb;
	private Toast toast = null;
	private String userName;
	private String password;
	private String firstnamefromdb;
	private String lastnamefromdb;
	private String imagepathfromdb;
	private ProgressDialog progressDialog;
	private TextView forgotpassword;
	private EditText mobileEditText;
	private ArrayList<UserList> userLists;
	String mobile;
	String userid;
	private boolean isUserPresent = false;
	private ArrayList<ForgotModel> userArryList;
	private List<JSONcircle> bean;
	private boolean isUserValid = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		uname = (EditText) findViewById(R.id.uname);
		pwd = (EditText) findViewById(R.id.pwd);
		userLoginDb = new UserLoginDb(getApplicationContext());
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
		userLists = new ArrayList<UserList>();
		progressDialog = new ProgressDialog(this);
		userArryList = new ArrayList<ForgotModel>();
		userdb = new User();

		// makeUserListArrayRequst();

		// // when user first login it will get data from database and chcek
		// // wheather user present or not
		Cursor cursor = userLoginDb.getAllRecords();
		//
		if (cursor.moveToFirst()) {
			do {
				firstnamefromdb = cursor.getString(cursor
						.getColumnIndex("username"));

			} while (cursor.moveToNext());

			if (firstnamefromdb == null) {
				Intent loginActivity = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(loginActivity);

			} else {
				Intent piAnswerActivity = new Intent(getApplicationContext(),
						PiAnswers.class);
				// setting all required varibles which user to get user
				// details
				// Setting here variable becauser when user restart the
				// application first it will check if user already login the
				// again set username and password.
				// LocalModel.getInstance().setFirstname(firstnamefromdb);
				// LocalModel.getInstance().setLastname(lastnamefromdb);
				// LocalModel.getInstance().setImagePath(imagepathfromdb);
				startActivity(piAnswerActivity);
			}
		}

		cursor.close();

		//

		// // String[] from = new String[]{LoginSQLiteAdapter.KEY_ID,
		// LoginSQLiteAdapter.KEY_CONTENT1, LoginSQLiteAdapter.KEY_CONTENT2};
		// try {
		// if (LoginSQLiteAdapter.KEY_CONTENT1.toString().length() > 1) {
		// // Toast.makeText(getApplicationContext(), "YEs", 9000).show();
		// // uname.setText(""+babu.KEY_CONTENT1);
		// // pwd.setText(""+babu.KEY_CONTENT2);
		// } else {
		// // Toast.makeText(getApplicationContext(), "Nooo", 9000).show();
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		forgotpassword = (TextView) findViewById(R.id.passwordTextView);
		forgotpassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// showInputDialog();

				LayoutInflater factory = LayoutInflater
						.from(LoginActivity.this);
				final View forgotdialogs = factory.inflate(R.layout.forgot,
						null);
				final AlertDialog forgotDialog = new AlertDialog.Builder(
						LoginActivity.this).create();
				forgotDialog.setView(forgotdialogs);

				mobileEditText = (EditText) forgotdialogs
						.findViewById(R.id.edittext);
				forgotdialogs.findViewById(R.id.mobileSubmitButon)
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// your business logic
								if (mobileEditText.getText().toString()
										.length() == 0) {
									mobileEditText
											.setError("Please enter mobile number");
								} else if (mobileEditText.getText().toString()
										.length() != 10) {
									mobileEditText
											.setError("Enter 10 digit number");

								} else {
									/**
									 * This method use to send the request to
									 * check weather mobile number is present or
									 * not.
									 */
									new CheckMobileNumber().execute();
									forgotDialog.dismiss();

								}

							}
						});
				forgotdialogs.findViewById(R.id.mobilecancleButton)
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								forgotDialog.dismiss();

							}
						});

				forgotDialog.show();

			}
		});

		tp = (TextView) findViewById(R.id.p);
		tu = (TextView) findViewById(R.id.u);
		db = new DatabaseHandler(this);

		// create the instance of Databse
		// loginDataBaseAdapter=new LoginDataBaseHelper(this);
		// loginDataBaseAdapter=loginDataBaseAdapter.open();
		final ImageView signupButton = (ImageView) findViewById(R.id.sigup);
		// e2 = (EditText) findViewById(R.id.pwd);

		if (!isNetworkAvailable1()) {
			AlertDialog ald = new AlertDialog.Builder(LoginActivity.this)
					.create();
			ald.setMessage("Please check Your Network Connection");
			// Toast.makeText(MainActivity.this,
			// "Please check Your Network Connection",
			// Toast.LENGTH_SHORT).show();
			ald.show();
		}

		tl = (TextView) findViewById(R.id.tll);
		// screen_width = metrics.widthPixels;
		String result = null;
		String main = "http://166.62.81.118:18080/SpringRestCrud/question/getQuestionTypeList";

		signupButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(LoginActivity.this, SignupActivity.class);
				startActivity(i);

			}
		});
		final ImageView login = (ImageView) findViewById(R.id.log);
		// AlaramData audioFile=(AlaramData) arg0.getItemAtPosition(arg2);
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// fetch the Password form database for respective user name
				// storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

				if (Util.isNetworkAvailable(getApplicationContext())) {

					userName = uname.getText().toString();
					password = pwd.getText().toString();
					// set the username and password to save in database.

					//
					if (TextUtils.isEmpty(userName)
							|| TextUtils.isEmpty(password)) {

						toast.setText("Please enter username or password");
						toast.show();
					} else {

						new MyAsyncTask().execute(userName, password);

					}

					// new MyAsyncTask().execute(name.getText().toString(),
					// pass.getText().toString(), lastname.getText().toString(),
					// abt.getText().toString(), mail.getText().toString(),
					// num.getText().toString());
				} else {
					toast.setText("Please check internet connection");
					toast.show();
				}

			}
		});

		uname.setOnKeyListener(new OnKeyListener() {

			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on Enter key press
					uname.clearFocus();
					pwd.requestFocus();
					return true;
				}
				return false;
			}
		});

		// pwd.setOnKeyListener(new OnKeyListener() {
		//
		// public boolean onKey(View v, int keyCode, KeyEvent event) {
		//
		// if ((event.getAction() == KeyEvent.ACTION_DOWN)
		// && (keyCode == KeyEvent.KEYCODE_ENTER)) {
		// // Perform action on Enter key press
		// // check for username - password correctness here
		// return true;
		// }
		// return false;
		// }
		// });

	}

	private void makeUserListArrayRequst() {

		String urljsonArry = "http://166.62.81.118:18080/SpringRestCrud/signup/userlist";
		JsonArrayRequest req = new JsonArrayRequest(urljsonArry,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						try {
							// Parsing json array response
							// loop through each json object
							for (int i = 0; i < response.length(); i++) {

								JSONObject obj = response.getJSONObject(i);

								ForgotModel forgotuserlist = new ForgotModel();

								forgotuserlist.setCity(obj.getString("city"));
								forgotuserlist.setUserid(obj
										.getString("userid"));
								forgotuserlist.setIdsignup(obj
										.getLong("idsignup"));
								forgotuserlist.setCountry(obj
										.getString("country"));
								forgotuserlist.setEmailid(obj
										.getString("emailid"));
								forgotuserlist.setFastname(obj
										.getString("fastname"));
								forgotuserlist.setLastname(obj
										.getString("lastname"));
								forgotuserlist.setMobileno(obj
										.getString("mobileno"));
								forgotuserlist.setImagepath(obj
										.getString("imagepath"));
								forgotuserlist.setQualification(obj
										.getString("qualification"));

								userArryList.add(forgotuserlist);

							}
							// This methos use to show the user profile data who
							// posted answer or question.ser

							System.out.println("userlist final>>"
									+ userArryList);

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

	private boolean isNetworkAvailable1() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	class MyAsyncTask extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;
		String b;
		String ib;
		private String userId;
		private String imagePath;
		private String userFirstname;
		private String userMail;
		private String Lastname;
		private String userstatus;
		private String userPassword;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog.setMessage("Checking for authorization");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			// TODO Auto-generated method stub
			postData(params[0], params[1]);

			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String username, String password) {
			// String baseurl =
			// "http://166.62.81.118:18080/SpringRestCrud/signup/login/{sk}/{userid}/{pwd}/{macid}/{mobile}/{latitude}/{longiture}/{deviceid}";
			// String baseurl =
			// "http://166.62.81.118:18080/SpringRestCrud/signup/login/";
			// baseurl += "loginis" + "/" + username + "/" + passwd + "/" +
			// "10434328" + "/" + "7799591404" + "/" + "32.67" + "/" + "32.67" +
			// "/" + "43434";
			// String
			// login="http://166.62.81.118:18080/SpringRestCrud/signup/login/loginis/diptim/password/10434328/9701513816/32.67/34.34/43434";
			String login = "http://166.62.81.118:18080/SpringRestCrud/signup/login/loginis/";
			login += username + "/" + password + "/" + "10434328" + "/"
					+ "9701513816" + "/" + "32.67" + "/" + "32.67" + "/"
					+ "43434";
			// System.out.println("***************" + baseurl);
			// Execute the request
			// HttpResponse response;
			// List<ReuseItem> items=new ArrayList<ReuseItem>();

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(login);
				responses = httpClient.execute(httpGet);
				entity = responses.getEntity();
				// String responseStr = EntityUtils.toString(entity);
				String content = EntityUtils.toString(entity);
				System.out.println("***********" + content);
				System.out.println("************" + login);
				JSONObject myObject = new JSONObject(content);
				idsignup = myObject.getString("idsignup");
				userId = myObject.getString("userid");
				userFirstname = myObject.getString("fastname");
				userMail = myObject.getString("createddate");
				Lastname = myObject.getString("lastname");
				userPassword = myObject.getString("passwd");
				imagePath = myObject.getString("imagepath");
				userstatus = myObject.getString("status");

				b = idsignup.toString();
				ib = userId.toString();

				System.out.println("id name>>" + idsignup);
				System.out.println("lastname>>" + Lastname);
				System.out.println("userpassword>>" + userPassword);
				System.out.println("imagepath>>" + imagePath);

				// try {
				// FileOutputStream fileoutlast = openFileOutput("last.txt",
				// MODE_PRIVATE);
				// OutputStreamWriter outputWriterlast = new OutputStreamWriter(
				// fileoutlast);
				// outputWriterlast.write(Lastname);
				// System.out.println("************************" + Lastname);
				//
				// outputWriterlast.close();
				FileOutputStream fileout = openFileOutput("mytextfile.txt",
						MODE_PRIVATE);
				OutputStreamWriter outputWriter = new OutputStreamWriter(
						fileout);
				outputWriter.write(ib);

				outputWriter.close();

				FileOutputStream UID = openFileOutput("myid.txt", MODE_PRIVATE);
				OutputStreamWriter outputWritersss = new OutputStreamWriter(UID);
				outputWritersss.write(idsignup);

				outputWritersss.close();

				FileOutputStream fileouts = openFileOutput("mytextfiles.txt",
						MODE_PRIVATE);
				OutputStreamWriter outputWriters = new OutputStreamWriter(
						fileouts);

				outputWriters.write(b);
				outputWriters.close();

				FileOutputStream fileoutss = openFileOutput("pwd.txt",
						MODE_PRIVATE);
				OutputStreamWriter outputWriterss = new OutputStreamWriter(
						fileoutss);

				outputWriterss.write(userPassword);
				outputWriterss.close();

				// display file saved message
				// Toast.makeText(getBaseContext(), "File saved successfully!",
				// Toast.LENGTH_SHORT).show();
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

		}

		protected void onPostExecute(Double result) {

			try {
				if (userFirstname.contains("null")) {
					// pDialog = new ProgressDialog(MainActivity.this);
					// // Showing progress dialog before making http request
					// pDialog.setMessage("Loading...");
					// pDialog.show();
					System.out.println("username");

					toast.setText("invalid Credentials");
					toast.show();
					// Here chceking the condition weather dialog is showing or
					// not
					if (progressDialog.isShowing() && progressDialog != null) {

						progressDialog.dismiss();

					}

				} else if (userMail.contains("null")) {

					toast.setText("Check your Mail for Account activation");
					toast.show();
				} else {

					Intent i = new Intent(LoginActivity.this, PiAnswers.class);
					i.putExtra("name", userId);
					i.putExtra("id", idsignup);

					// LocalModel.getInstance().setUserId(userId);
					// LocalModel.getInstance().setFirstname(userFirstname);
					// LocalModel.getInstance().setLastname(Lastname);
					// LocalModel.getInstance().setImagePath(imagePath);
					// adding all records here into database because first chcek
					// usernam and password authorized or not.
					// added by prasad to solve issue of login with invalid
					// username and password.
					userdb.setUsername(userId);
					userdb.setPassword(userPassword);
					userdb.setLastname(Lastname);
					userdb.setImagePath(imagePath);
					System.out.println("userid>>>" + userId);
					System.out.println("userpasssword>>" + userPassword);
					System.out.println("lastname>>" + Lastname);
					System.out.println("imagepath>>>" + imagePath);
					// setting all required varibles which user to get user
					// details

					// LocalModel.getInstance().se
					// add username and password to database.
					userLoginDb.inserIntoUserDatabase(userdb);

					startActivity(i);
					finish();
					if (progressDialog.isShowing() && progressDialog != null) {

						progressDialog.dismiss();

					}

				}
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private Boolean exit = false;

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);// ***Change Here***
		startActivity(intent);
		finish();
		// System.exit(1);
		System.exit(0);

	}

	/**
	 * This method use to check weather username present or not while register
	 * the user.
	 * 
	 * @author Admin
	 * 
	 */
	private class CheckMobileNumber extends AsyncTask<Void, Void, Void> {
		String vl;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

			progressDialog = new ProgressDialog(LoginActivity.this);

			progressDialog.setMessage("Please wait...");

			progressDialog.setCancelable(true);
			progressDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance

			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			vl = "{\"jsonBean\":" + jsonStr + "}";
			Log.d("Response: ", "> " + vl);

			// JSONBean res=null;
			if (vl != null) {
				try {
					// JSONObject jsonObj = new JSONObject(jsonStr);
					Gson gson = new Gson();
					JSONcirc res = gson.fromJson(vl, JSONcirc.class);
					bean = (List<JSONcircle>) res.getJsonBean();
					String mobilenumber = mobileEditText.getText().toString();

					if (vl.contains(mobilenumber)) {

						for (int i = 0; i < bean.size(); i++) {

							ForgotModel forgotModel = new ForgotModel();
							if (mobilenumber.equals(bean.get(i).getMobileno()
									.toString())) {

								forgotModel.setMobileno(bean.get(i)
										.getMobileno().toString());
								forgotModel.setIdsignup(Long.parseLong(bean
										.get(i).getIdsignup()));
								userArryList.add(forgotModel);
								isUserValid = true;

							}

						}

					} else {
						/**
						 * This is clear the userlist if enter wrong mobile
						 * number.
						 */
						if (userArryList != null) {

							userArryList.clear();
							isUserValid = false;
						}
					}

					System.out.println("users>>" + userArryList);

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
			if (progressDialog != null && progressDialog.isShowing()) {

				progressDialog.dismiss();
			}

			System.out.println("user valid>>>>>" + isUserValid);

			/**
			 * Here checking the condition if user is registered then send the
			 * otp otherwise simply show that user mobile number is not
			 * registered.
			 */
			if (isUserValid == true) {

				for (int i = 0; i < userArryList.size(); i++) {

					mobile = userArryList.get(i).getMobileno().toString();
					userid = String.valueOf(userArryList.get(i).getIdsignup());

					/**
					 * Here to genereate the new mobil
					 */

				}
				new ChangePassword().execute(userid, mobile);

			} else {
				toast.setText("Please enter registered mobile number");
				toast.show();
			}

		}
	}

	// // deleting user credential on an onpause method because when user
	// // forcefully stop application it again get login to solve this issue
	// delete
	// // it
	// // added by prasad.
	// @Override
	// protected void onPause() {
	// // TODO Auto-generated method stub
	// super.onPause();
	// // send request to delete the records of user.
	// userLoginDb.deleteContact("");
	// }

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (toast != null) {
			toast.cancel();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (toast != null) {
			toast.cancel();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(true);

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private class ChangePassword extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;
		String b;
		String ib;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			progressDialog.setMessage("Please wait OTP generating..");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			// TODO Auto-generated method stub
			postData(params[0], params[1]);

			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String userid, String mobileno) {
			String updates = "http://166.62.81.118:18080/SpringRestCrud/signup/sendforgetpasswordotp/";
			updates += userid + "/" + mobileno;
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

			if (progressDialog != null && progressDialog.isShowing()) {

				progressDialog.dismiss();

			}

			LayoutInflater factory = LayoutInflater.from(LoginActivity.this);
			final View deleteDialogView = factory.inflate(
					R.layout.forgot_password_dialog, null);
			final AlertDialog forgotDialog = new AlertDialog.Builder(
					LoginActivity.this).create();
			forgotDialog.setView(deleteDialogView);
			final EditText motp = (EditText) deleteDialogView
					.findViewById(R.id.otpedt);
			final EditText mpwd = (EditText) deleteDialogView
					.findViewById(R.id.newpwd);
			deleteDialogView.findViewById(R.id.submitButton)
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// your business logic

							if (motp.getText().toString().length() == 0) {

								motp.setError("Enter otp");

							} else if (mpwd.getText().toString().length() == 0) {

								mpwd.setError("Enter password");

							} else {
								String otp = motp.getText().toString();
								String password = mpwd.getText().toString();

								new PasswordUpdate().execute(userid, mobile,
										otp, password);
								forgotDialog.dismiss();

							}

						}
					});
			deleteDialogView.findViewById(R.id.cancelButton)
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							forgotDialog.dismiss();

						}
					});

			forgotDialog.show();
			// LayoutInflater inflater =
			// LayoutInflater.from(LoginActivity.this);
			// View myview = inflater.inflate(R.layout.forgot_password_dialog,
			// null);
			// final EditText motp = (EditText)
			// myview.findViewById(R.id.otpedt);
			// final EditText mpwd = (EditText)
			// myview.findViewById(R.id.newpwd);
			// Button submitButton = (Button) myview
			// .findViewById(R.id.submitButton);
			// Button cancelButton = (Button) myview
			// .findViewById(R.id.cancelButton);
			// AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			// LoginActivity.this);
			// alertDialogBuilder.setTitle("Enter OTP and Password");
			// alertDialogBuilder.setView(myview);
			//
			// submitButton.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			//
			// String otp = motp.getText().toString();
			// String password = mpwd.getText().toString();
			//
			// new PasswordUpdate().execute(userid, mobile, otp, password);
			//
			// }
			// });
			// cancelButton.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			//
			// }
			// });
			//
			// // create an alert dialog
			// AlertDialog alert = alertDialogBuilder.create();
			// alert.show();

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

	/**
	 * This method use to updtate the password.
	 * 
	 * @author Admin
	 * 
	 */
	private class PasswordUpdate extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;
		String b;
		String ib;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog.setMessage("Password Updating..");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			// TODO Auto-generated method stub
			postData(params[0], params[1], params[2], params[3]);

			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String xid, String mobileno, String otp,
				String password) {
			// String baseurl =
			// "http://166.62.81.118:18080/SpringRestCrud/signup/login/{sk}/{userid}/{pwd}/{macid}/{mobile}/{latitude}/{longiture}/{deviceid}";
			// String baseurl =
			// "http://166.62.81.118:18080/SpringRestCrud/signup/login/";
			// baseurl += "loginis" + "/" + username + "/" + passwd + "/" +
			// "10434328" + "/" + "7799591404" + "/" + "32.67" + "/" + "32.67" +
			// "/" + "43434";
			// String
			// login="http://166.62.81.118:18080/SpringRestCrud/signup/login/loginis/diptim/password/10434328/9701513816/32.67/34.34/43434";
			String update = "http://166.62.81.118:18080/SpringRestCrud/signup//updateforgetpassword/{userid}/{mobile}/{otp}/{nwpwd};";
			String updates = "http://166.62.81.118:18080/SpringRestCrud/signup//updateforgetpassword/";
			updates += xid + "/" + mobileno + "/" + otp + "/" + password;
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

			if (progressDialog != null && progressDialog.isShowing()) {

				progressDialog.dismiss();

			}

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
