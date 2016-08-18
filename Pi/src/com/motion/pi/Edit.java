package com.motion.pi;

import info.androidhive.customlistviewvolley.model.UserProfile;
import info.androidhive.customlistviewvolley.util.CustomToast;
import info.androidhive.customlistviewvolley.util.ImageTrans_roundedcorner;
import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpEntity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.motion.actionbar.CustomActionBar;
import com.squareup.picasso.Picasso;

//import com.beingjavaguys.model.UserStatus;
//import com.beingjavaguys.model.Usersignup;
//import com.beingjavaguys.model.login;

//import org.apache.http.HttpEntity;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by Prakash on 8/14/2015.
 */
public class Edit extends CustomActionBar {
	private static final int PICK_FROM_GALLERY = 101;
	final int PIC_CROP = 1;
	Bitmap bitmap;
	private String attachmentFile;
	private int columnIndex;
	private Uri URI = null;
	// String b="pradeep";
	TextView tname;
	TextView tlast;
	String out;
	TextView tnum;
	String userName;
	TextView tmail;
	TextView name;
	TextView lastname;
	TextView mail;
	private static final String TAG = Edit.class.getSimpleName();
	TextView mobileNumber;
	TextView pass;
	String Qual, city, country;
	EditText abt;
	String abts;
	String wdps;
	public String fname;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	SQLiteDatabase dbs;
	private ImageView selectImageView = null;
	String mymail;
	String restUrl;
	private static String url = "http://166.62.81.118:18080/SpringRestCrud/signup/userlist";
	// String ull =
	// "http://166.62.81.118:18080/SpringRestCrud/signup/login/loginis/";
	// login+=username+"/"+password+"/"+"10434328"+"/"+"9701513816"+"/"+"32.67"+"/"+"32.67"+"/"+"43434";
	private Button btninsert = null;
	private static final String TAG_CONTACTS = "";
	private static final String TAG_ID = "userid";
	private static final String TAG_NAME = "imagepath";
	String Abt;
	private static final String TAG_userpost = "userid";
	String mob;
	private static final String TAG_Quest = "aboutmyquestion";
	private static final String TAG_fid = "idsignup";
	private static final String TAG_date = "createddate";
	static final int READ_BLOCK_SIZE = 100;
	String userid;
	ArrayList<HashMap<String, String>> contactList;
	String lnames;
	private Button btnretrive = null;
	String County;
	private MyDataBase mdb = null;
	SharedPreferences sp;
	// private SQLiteDatabase db = null;
	String pwd;
	EditText caption;
	private Cursor c = null;
	private byte[] img = null;
	String Paswd;
	private static final int PICK_IMAGE = 1;
	String names = "";
	String citys;
	String wdp = "";
	private static final String DATABASE_NAME = "ImageDb.db";
	public static final int DATABASE_VERSION = 1;
	private AlaramData audio;
	URI uri;
	String gender;
	DatabaseHandler db;
	String firstName, lastName, email, phone;
	String Quals;
	String countrys;
	private EditText firstNameEditText;
	private TextView naam;
	private TextView mal;
	private TextView mobil;
	private EditText abut;
	private EditText qualificationEditText;
	private EditText cityEditText;
	private EditText conty;
	private TextView pawd;
	private TextView last;
	private EditText successTip;
	private String username = "";
	private EditText ocupationEditText;
	private EditText earlierSuccess;
	ProgressDialog progressDialog;
	private EditText pincodeEditText;
	private ArrayList<UserProfile> userProfileArrayList;
	Toast toast = null;
	private String skb;
	private EditText sdate;

	// LoginDataBaseHelper loginDataBaseAdapter;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edits);

		final TextView spt1 = (TextView) findViewById(R.id.sp1);
		final TextView spt2 = (TextView) findViewById(R.id.sp2);
		final TextView spt3 = (TextView) findViewById(R.id.sp3);
		naam = (TextView) findViewById(R.id.editText);
		mal = (TextView) findViewById(R.id.mail);
		abut = (EditText) findViewById(R.id.abtme);
		qualificationEditText = (EditText) findViewById(R.id.qualification);
		cityEditText = (EditText) findViewById(R.id.cityEditText);
		conty = (EditText) findViewById(R.id.countryEditText);
		pawd = (TextView) findViewById(R.id.editText5);
		last = (TextView) findViewById(R.id.editText50);
		successTip = (EditText) findViewById(R.id.successTip);
		sdate = (EditText) findViewById(R.id.sdate);
		earlierSuccess = (EditText) findViewById(R.id.earlierSuccess);
		ocupationEditText = (EditText) findViewById(R.id.ocupationEditText);
		pincodeEditText = (EditText) findViewById(R.id.pincodeEditText);
		mobileNumber = (TextView) findViewById(R.id.numberTextView);
		firstNameEditText = (EditText) findViewById(R.id.firtnameEditText);
		progressDialog = new ProgressDialog(this);
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

		// final Spinner qual = (Spinner) findViewById(R.id.sp01);
		db = new DatabaseHandler(this);
		caption = (EditText) findViewById(R.id.samp);
		abt = (EditText) findViewById(R.id.abtme);
		selectImageView = (ImageView) findViewById(R.id.profilePic);
		userProfileArrayList = new ArrayList<UserProfile>();

		// This method use to make the profile data request.

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
			userid = "";
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
				View someView = findViewById(R.id.profilebackground);
				LookAndFeel.lookAndFeel(skb, someView);
				// This method use to change the background color when user
				// select from the look and feel from settings.
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fileIn = openFileInput("pwd.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];

			String ds = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				wdp += readstring;
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

		// when user click on this button it will show pop message that user
		// cant modified username from here.
		naam.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// toast.setText("Please change username from setting");
				// toast.setGravity(Gravity.CENTER, 0, 0);
				// toast.show();

				CustomToast.SetmessageToast(
						"Please change username from setting",
						getApplicationContext());

			}
		});

		// when user click on this button it will show pop message that user
		// cant modified password from here.
		pawd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				CustomToast.SetmessageToast(
						"Please change password from setting",
						getApplicationContext());
				// toast.setText("Please change password from setting");
				// toast.setGravity(Gravity.CENTER, 0, 0);
				// toast.show();

			}
		});

		// when user click on this button it will show pop message that user
		// cant modified mobile from here.
		mobileNumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				CustomToast.SetmessageToast(
						"Please change Mobile Number from setting",
						getApplicationContext());

			}
		});

		// when user click on this button it will show pop message that user
		// cant modified mail from here.
		mal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				CustomToast.SetmessageToast(
						"Please change Email Id from setting",
						getApplicationContext());

			}
		});
		try {
			FileInputStream fileIn = openFileInput("mytextfile.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];

			String d = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				names += readstring;
				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				d += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

			name.setText("" + names);

		} catch (Exception e) {
			e.printStackTrace();
		}

		new GetProfileDetails().execute();

		// String mane = name.getText().toString();
		// String urlg =
		// "http://166.62.81.118:18080/SpringRestCrud/signup/login/loginis/";
		// urlg += names + "/" + wdp + "/" + "10434328" + "/" + "9701513816" +
		// "/"
		// + "32.67" + "/" + "32.67" + "/" + "43434";
		//
		// System.out.println(">>>>>>>>" + urlg);

		// // Creating volley request obj
		// JsonObjectRequest movieReq = new JsonObjectRequest(url, null,
		// new Response.Listener<JSONObject>() {
		// @Override
		// public void onResponse(JSONObject response) {
		// Log.d(TAG,
		// "hii&&&&&&&&&&&&&&&&&&&&&&:"
		// + response.toString());
		// if (progressDialog != null
		// && progressDialog.isShowing()) {
		//
		// progressDialog.dismiss();
		//
		// }
		//
		// // Parsing json
		// for (int i = 0; i < response.length(); i++) {
		// try {
		//
		// UserProfile userProfile = new UserProfile();
		//
		// // userProfile.setUserid(response.getString(
		// // "userid").toString());
		// // naam.setText(userProfile.getUserid().toString());
		// //
		// // userProfile.setFastname(response.getString(
		// // "fastname").toString());
		// // firstNameEditText.setText(userProfile
		// // .getFastname().toString());
		// //
		// // userProfile.setLastname(response.getString(
		// // "lastname").toString());
		// // last.setText(userProfile.getLastname()
		// // .toString());
		// // userProfile.setPasswd(response.getString(
		// // "passwd").toString());
		// // pawd.setText(userProfile.getPasswd().toString());
		// // userProfile.setEmailid(response.getString(
		// // "emailid").toString());
		// // mal.setText(userProfile.getEmailid().toString());
		// //
		// // userProfile.setMobileno(response.getString(
		// // "mobileno").toString());
		// // mobileNumber.setText(userProfile.getMobileno()
		// // .toString());
		// //
		// // userProfile.setAboutme(response.getString(
		// // "aboutme").toString());
		// //
		// // userProfile.setQualification(response
		// // .getString("qualification").toString());
		// // spin.setText(userProfile.getQualification()
		// // .toString());
		// // userProfile.setCity(response.getString("city")
		// // .toString());
		// // cty.setText(userProfile.getCity().toString());
		// // userProfile.setCountry(response.getString(
		// // "country").toString());
		// // conty.setText(userProfile.getCountry()
		// // .toString());
		// // /**
		// // * "pincode": "442917", "occupation":
		// // "working",
		// // * "earliersuccess": "hi", "successtips": "h",
		// // * "yr": "90"
		// // */
		// // userProfile.setPincode(response
		// // .getString("pincode"));
		// // userProfile.setOccupation(response
		// // .getString("occupation"));
		// // userProfile.setEarliersuccess(response
		// // .getString("earliersuccess"));
		// // userProfile.setSuccesstips(response
		// // .getString("successtips"));
		// // userProfile.setYr(response.getString("yr"));
		// // pincodeEditText.setText(userProfile
		// // .getPincode().toString());
		// //
		// // if (userProfile.getOccupation().toString()
		// // .equals("Empty")) {
		// // ocupationEditText.setHint("Occupation");
		// //
		// // } else {
		// // ocupationEditText.setText(userProfile
		// // .getOccupation().toString());
		// // }
		// //
		// // if
		// // (userProfile.getEarliersuccess().toString()
		// // .equals("Empty")) {
		// // earlierSuccess.setHint("Earlier Selection");
		// // } else {
		// // earlierSuccess.setText(userProfile
		// // .getEarliersuccess().toString());
		// // }
		// //
		// // if (userProfile.getYr().toString()
		// // .equals("Empty")) {
		// // sdate.setHint("Year");
		// //
		// // } else {
		// // sdate.setText(userProfile.getYr()
		// // .toString());
		// //
		// // }
		// //
		// // if (userProfile.getSuccesstips().toString()
		// // .equals("Empty")) {
		// //
		// // successTip.setHint("Success Tips");
		// //
		// // } else {
		// // successTip.setText(userProfile
		// // .getSuccesstips().toString());
		// //
		// // }
		// //
		// // if (userProfile.getAboutme().toString()
		// // .equals("Empty")) {
		// // abut.setHint("About me");
		// // } else {
		// //
		// // abut.setText(userProfile.getAboutme()
		// // .toString());
		// // }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// }
		//
		// }
		// }, new Response.ErrorListener() {
		// @Override
		// public void onErrorResponse(VolleyError error) {
		// VolleyLog.d(TAG, "Error: " + error.getMessage());
		//
		// }
		// });
		//
		// // Adding request to request queue
		// AppController.getInstance().addToRequestQueue(movieReq);

		String first = firstNameEditText.getText().toString();

		link += userid + ".jpg";

		// This line use to remove the cache of the image and load the new image
		// when user load from the edit screen.
		Picasso.with(this).invalidate(link);
		Picasso.with(this).load(link).skipMemoryCache().resize(300, 300)
				.transform(new ImageTrans_roundedcorner())
				.into(selectImageView);

		Button load = (Button) findViewById(R.id.button33);
		// when user click on button he able to select image from the gallary.
		selectImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				selectImage();

			}
		});

		final String v1;
		// SharedPreferences sp = getSharedPreferences("Login", 0);
		sp = getSharedPreferences("prefs", 0);

		// name = (TextView) findViewById(R.id.editText);
		// // fname =name.getText().toString();
		// lastname = (TextView) findViewById(R.id.editText50);
		// // String lname = lastname.getText().toString();
		// mail = (TextView) findViewById(R.id.mail);
		// // String mmail = mail.getText().toString();
		// tname = (TextView) findViewById(R.id.t1);
		// tlast = (TextView) findViewById(R.id.t2);
		// tmail = (TextView) findViewById(R.id.t3);
		// tnum = (TextView) findViewById(R.id.t4);
		// pass = (TextView) findViewById(R.id.editText5);
		// pwd = pass.getText().toString();
		// rg.check(R.id.rd1);

		Button submmit = (Button) findViewById(R.id.sub);
		// ddszdsd
		submmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				userName = naam.getText().toString();
				lnames = last.getText().toString();
				Paswd = pawd.getText().toString();
				mymail = mal.getText().toString();
				mob = mobileNumber.getText().toString();
				Abt = abut.getText().toString();
				Quals = qualificationEditText.getText().toString();
				citys = cityEditText.getText().toString();
				County = conty.getText().toString();
				String firstname = firstNameEditText.getText().toString();
				String picode = pincodeEditText.getText().toString();
				String occupation = ocupationEditText.getText().toString();
				String earliderSuccess = earlierSuccess.getText().toString();
				String successtip = successTip.getText().toString();
				String year = sdate.getText().toString();

				if (pincodeEditText.getText().toString().equals("null")
						|| pincodeEditText.getText().toString().length() == 0) {

					picode = "Empty";

				}
				if (ocupationEditText.getText().toString().equals("null")
						|| ocupationEditText.getText().toString().length() == 0) {

					occupation = "Empty";

				}

				if (earlierSuccess.getText().toString().equals("null")
						|| earlierSuccess.getText().toString().length() == 0) {
					earliderSuccess = "Empty";

				}

				if (successTip.getText().toString().equals("null")
						|| successTip.getText().toString().length() == 0) {

					successtip = "Empty";

				}
				if (sdate.getText().toString().equals("null")
						|| sdate.getText().toString().length() == 0) {

					year = "Empty";

				}

				new MyAsyncTask().execute(userName, Paswd, firstname, lnames,
						gender, Abt, mymail, mob, Quals, County, citys, picode,
						occupation, earliderSuccess, successtip, year);

				// new GetContacts().execute();
				// pb.setVisibility(View.VISIBLE);
				// new MyAsyncTask().execute(name.getText().toString(),
				// pass.getText().toString(), name.getText().toString(),
				// lastname.getText().toString(),
				// num.getText().toString(), mail.getText().toString(),
				// abt.getText().toString(),gender);

				/**
				 * CRUD Operations
				 * */

				// gender = selectedRedioButton.getText().toString();
				// userName = name.getText().toString();
				// Paswd = pass.getText().toString();
				// lnames = lastname.getText().toString();
				// Abt = abt.getText().toString();
				// mymail = mail.getText().toString();
				// mob = num.getText().toString();
				// Quals = spt1.getText().toString();
				// citys=spt2.getText().toString();
				// countrys=spt3.getText().toString();
				// SharedPreferences.Editor Ed = sp.edit();
				//
				// Ed.putString("Unm", userName);
				// Ed.putString("Psw", Paswd);
				// Ed.commit();
				// Save the Data in Database
				// loginDataBaseAdapter.insertEntry(userName, Paswd);

				// new MyAsyncTask().execute(userName, Paswd, lnames, gender,
				// Abt, mymail, link,mob, Quals, citys);
				//
				//
				// new ImageUploadTask().execute();
				// AlaramData file = new AlaramData(userName,Paswd);
				// AlaramDA audioDA = new AlaramDA();
				// audioDA.saveAlaram(file);
				// Inserting Contacts
				Log.d("Insert: ", "Inserting ..");
				// db.addContact(new Contact(userName, Paswd));

			}

		});
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

		// String stateToSave = sedit.getText().toString();
		// outState.putString("saved_state", stateToSave);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
			Uri selectedImageUri = data.getData();
			String filePath = null;

			try {
				// OI FILE Manager
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();
				columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				attachmentFile = cursor.getString(columnIndex);
				URI = Uri.parse("file://" + attachmentFile);
				// first check uri is null or not if not null then call
				// method
				// to crop the image.

				if (URI != null) {
					performCrop(URI);

				}
				bitmap = MediaStore.Images.Media.getBitmap(
						this.getContentResolver(), selectedImage);
				selectImageView.setVisibility(View.VISIBLE);
				selectImageView.setImageBitmap(bitmap);

				cursor.close();
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "Internal error",
						Toast.LENGTH_LONG).show();
				Log.e(e.getClass().getName(), e.getMessage(), e);
			}

		}
		// This will crop the image according to select image by user.
		if (requestCode == PIC_CROP) {
			if (data != null) {
				// get the returned data
				Bundle extras = data.getExtras();
				// get the cropped bitmap
				Bitmap selectedBitmap = extras.getParcelable("data");

				selectImageView.setVisibility(View.VISIBLE);
				selectImageView.setImageBitmap(selectedBitmap);
			}
		}

	}

	class ImageUploadTask extends AsyncTask<String, String, String> {
		private String sResponse;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("onpreExecute");

			progressDialog = new ProgressDialog(Edit.this);
			progressDialog.setMessage("Please wait");
			progressDialog.show();

		}

		@Override
		protected String doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			// TODO Auto-generated method stub
			String response = postData(params[0]);

			return response;
		}

		private String postData(String userid) {
			// TODO Auto-generated method stub

			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(
					"http://166.62.81.118:18080/SpringRestCrud/mailnotes/singleSave");

			MultipartEntity entity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			// check weather bitmap null or not if not null then proceed for the
			// upload on server.
			if (bitmap != null) {

				bitmap.compress(CompressFormat.JPEG, 10, bos);

				byte[] data = bos.toByteArray();
				// entity.addPart("photoId", new
				// StringBody(getIntent().getStringExtra("photoId")));
				// entity.addPart("returnformat", new StringBody("json"));
				entity.addPart("uploaded", new ByteArrayBody(data, userid
						+ ".jpg"));
				try {
					entity.addPart("photoCaption", new StringBody(caption
							.getText().toString()));
					httpPost.setEntity(entity);
					HttpResponse response = httpClient.execute(httpPost,
							localContext);
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(response.getEntity()
									.getContent(), "UTF-8"));

					String sResponse = reader.readLine();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			return sResponse;

		}

		@Override
		protected void onPostExecute(String sResponse) {

			if (progressDialog != null && progressDialog.isShowing()) {

				progressDialog.dismiss();
				Intent homepageIntent = new Intent(getApplicationContext(),
						Profile.class);
				startActivity(homepageIntent);
				finish();

			}

		}
	}

	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		if (cursor != null) {
			// HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
			// THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} else
			return null;
	}

	public void decodeFile(String filePath) {
		// Decode image size
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, o);

		// The new size we want to scale to
		final int REQUIRED_SIZE = 1024;

		// Find the correct scale value. It should be the power of 2.
		int width_tmp = o.outWidth, height_tmp = o.outHeight;
		int scale = 1;
		/*
		 * while (true) { if (width_tmp &lt; REQUIRED_SIZE &amp;&amp; height_tmp
		 * &lt; REQUIRED_SIZE) break; width_tmp /= 2; height_tmp /= 2; scale *=
		 * 2; }
		 */

		// Decode with inSampleSize
		BitmapFactory.Options o2 = new BitmapFactory.Options();
		o2.inSampleSize = scale;
		bitmap = BitmapFactory.decodeFile(filePath, o2);

		selectImageView.setImageBitmap(bitmap);

	}

	class MyAsyncTask extends AsyncTask<String, Integer, Double> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("On preExecute");

			progressDialog = new ProgressDialog(Edit.this);
			progressDialog.setMessage("Please wait");
			progressDialog.show();

		}

		/**
		 * /**
		 * 
		 * http://localhost:18080/SpringRestCrud/signup/updatealluser/{ iduser
		 * }/{userid}/{passwd}/{fastname}/{lastname}/{gender}/{aboutme
		 * }/{stid}/{emailid}/{mobileno}/{imagepath}/{qualification}/{
		 * country}/{city}/{pincode}/{occupation}/{earliersuccess}/{
		 * successtips}/{yr}
		 */

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1], params[2], params[3], params[4],
					params[5], params[6], params[7], params[8], params[9],
					params[10], params[11], params[12], params[13], params[14],
					params[15]);

			return null;
		}

		protected void onPostExecute(Double result) {
			// pb.setVisibility(View.GONE);
			// Toast.makeText()
			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
			new ImageUploadTask().execute(userid);
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		/**
		 * /**
		 * 
		 * http://localhost:18080/SpringRestCrud/signup/updatealluser/{ iduser
		 * }/{userid}/{passwd}/{fastname}/{lastname}/{gender}/{aboutme
		 * }/{stid}/{emailid}/{mobileno}/{imagepath}/{qualification}/{
		 * country}/{city}/{pincode}/{occupation}/{earliersuccess}/{
		 * successtips}/{yr}
		 */

		public void postData(String username, String Paswds, String firstname,
				String lastname, String gender, String aboutme, String emailid,
				String mobile, String qualification, String country,
				String city, String pincode, String occupation,
				String earlidersuccess, String successtip, String year) {
			// Create a new HttpClient and Post Header
			String sample = "http://166.62.81.118:18080/SpringRestCrud/signup/updatealluser/135/Jamesbond/123/Jamesbond/Jamesbond/male/aboutme/stid/emailid/mobileno/imagepath/qualification/country/city%7D/%7Bpincode%7D/%7Boccupation%7D/%7Bearliersuccess%7D/%7Bsuccesstips%7D/%7Byr%7D";
			System.out.println("***********" + sample);
			try {
				String baseurl = "http://166.62.81.118:18080/SpringRestCrud/signup/updatealluser/";
				baseurl += userid + "/" + username + "/" + Paswds + "/"
						+ firstname + "/" + lastname + "/" + gender + "/"
						+ aboutme + "/" + 1 + "/" + emailid + "/" + mobile
						+ "/" + "Image" + "/" + qualification + "/" + country
						+ "/" + city + "/" + pincode + "/" + occupation + "/"
						+ earlidersuccess + "/" + successtip + "/" + year;

				System.out.println("***************" + baseurl);

				uri = new URI(baseurl.replace(" ", "%20"));
				// uri = Uri.parse(out);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			// restUrl = URLEncoder.encode(baseurl, "UTF-8");
			// } catch (UnsupportedEncodingException e) {
			// e.printStackTrace();}
			// }catch (IllegalStateException e){
			// e.printStackTrace();
			// }

			String baseurl2 = "http://166.62.81.118:18080/SpringRestCrud/signup/createuser/Sarkar/passwords/Sarkar/Raj/M/software/7/sarkar@gmail.com/00789/hello";
			// String baseurl2=
			// "http://166.62.81.118:18080/SpringRestCrud/signup/createuser/diptim/password/balaram/barada/M/software%20eng/7/balaram.b@pyrogroup.com/9701513816/hi";
			// String baseurl3 = "";
			System.out.println("***************" + uri);

			// baseurl+="Baburao/"+"babu/"+"BabuRao/"+"Siddi/"+"Male/"+"Iam Engineer/"+"07/04/1990"+""+"baburao4790@gmail.com"+"7799591404"+"Hello";
			// List<NameValuePair> nameValuePairs = new
			// ArrayList<NameValuePair>(2);
			// nameValuePairs.add(new BasicNameValuePair("setFastname",
			// "Baburao"));
			// nameValuePairs.add(new BasicNameValuePair("lastname", lname));

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(uri);
				// Intent i= new Intent(Firsts.this,MainActivity.class);
				// // String msgs = "Details Entered Succesfully";
				//
				// startActivity(i);
				// HttpPost httpPost = new
				// HttpPost("http://166.62.81.118:18080/SpringRestCrud/signup/createuser");
				// httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// HttpResponse response = httpClient.execute(httpPost);
				HttpResponse responses = httpClient.execute(httpGet);
				// Toast.makeText(getApplicationContext(),
				// "Details Entered Succesfully", Toast.LENGTH_LONG).show();
				// Toast.makeText(getApplicationContext(),"Please login to ur Account ",Toast.LENGTH_LONG).show();
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

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {
		String vl;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

			// pDialog.show();

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
					List<JSONcircle> bean = (List<JSONcircle>) res
							.getJsonBean();

					// Getting JSON Array node
					// contacts = jsonObj.getJSONArray(vl);

					// looping through All Contacts
					for (JSONcircle listBean : bean) {

						abts = listBean.getUserid();
						// dt=listBean.getImagepath();
						// fid = listBean.getIdsignup();
						// String qid=listBean.getIdquestion();
						// String qtyp=listBean.getQtypeid();
						// qdet=listBean.getQuestiondetails();
						// String quid=listBean.getUserid();

						HashMap<String, String> contact = new HashMap<String, String>();
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
			// if (pDialog.isShowing())
			// pDialog.dismiss();
			if (vl.contains(name.getText().toString())) {
				Toast.makeText(getApplicationContext(), "Username Exists", 9000)
						.show();
			} else {

				Toast.makeText(getApplicationContext(), "New Username", 9000)
						.show();
			}
			Log.d("Message", abts);
		}

	}

	private void selectImage() {

		try {

			Intent intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			intent.setType("image/*");

			startActivityForResult(
					Intent.createChooser(intent, "Complete action using"),

					PICK_FROM_GALLERY);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void performCrop(Uri picUri) {
		try {

			Intent cropIntent = new Intent("com.android.camera.action.CROP");
			// indicate image type and Uri
			cropIntent.setDataAndType(picUri, "image/*");
			// set crop properties
			cropIntent.putExtra("crop", "true");
			// indicate aspect of desired crop
			cropIntent.putExtra("aspectX", 0);
			cropIntent.putExtra("aspectY", 0);
			// indicate output X and Y
			cropIntent.putExtra("outputX", 200);
			cropIntent.putExtra("outputY", 150);
			// retrieve data on return
			cropIntent.putExtra("return-data", true);
			// start the activity - we handle returning in onActivityResult
			startActivityForResult(cropIntent, PIC_CROP);
		}
		// respond to users whose devices do not support the crop action
		catch (ActivityNotFoundException anfe) {
			// display an error message
			String errorMessage = "Whoops - your device doesn't support the crop action!";
			Toast toast = Toast
					.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		CustomToast.removeToast(true);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (toast != null) {
			toast.cancel();
		}
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetProfileDetails extends AsyncTask<Void, Void, Void> {

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

			pDialog = new ProgressDialog(Edit.this);

			pDialog.setMessage("Please wait...");

			pDialog.setCancelable(true);
			pDialog.show();

		}

		@SuppressWarnings("unchecked")
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance

			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			String vl = "{\"jsonBean\":" + jsonStr + "}";
			Log.d("Response: ", "> " + vl);

			// JSONBean res=null;
			if (vl != null) {

				Gson gson = new Gson();
				Type listType = new TypeToken<List<UserProfile>>() {
				}.getType();
				userProfileArrayList = (ArrayList<UserProfile>) gson.fromJson(
						jsonStr, listType);

			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();

			for (int i = 0; i < userProfileArrayList.size(); i++) {

				if (String.valueOf(userProfileArrayList.get(i).getIdsignup())
						.equals(userid)) {
					naam.setText(userProfileArrayList.get(i).getUserid()
							.toString());
					firstNameEditText.setText(userProfileArrayList.get(i)
							.getFastname().toString());
					last.setText(userProfileArrayList.get(i).getLastname()
							.toString());
					pawd.setText(userProfileArrayList.get(i).getPasswd()
							.toString());
					mal.setText(userProfileArrayList.get(i).getEmailid()
							.toString());
					mobileNumber.setText(userProfileArrayList.get(i)
							.getMobileno().toString());

					if (userProfileArrayList.get(i).getAboutme().toString()
							.equals("Empty")) {
						abut.setHint("About me");

					} else {

						abut.setText(userProfileArrayList.get(i).getAboutme()
								.toString());

					}
					pincodeEditText.setText(userProfileArrayList.get(i)
							.getPincode().toString());

					if (userProfileArrayList.get(i).getOccupation().toString()
							.equals("Empty")) {

						ocupationEditText.setHint("Occupation");

					} else {
						ocupationEditText.setText(userProfileArrayList.get(i)
								.getOccupation().toString());

					}

					if (userProfileArrayList.get(i).getSuccesstips().toString()
							.equals("Empty")) {

						successTip.setHint("Success Tips");

					} else {
						successTip.setText(userProfileArrayList.get(i)
								.getSuccesstips().toString());

					}

					if (userProfileArrayList.get(i).getEarliersuccess()
							.toString().equals("Empty")) {

						earlierSuccess.setHint("Earlier success");

					} else {
						successTip.setText(userProfileArrayList.get(i)
								.getEarliersuccess().toString());

					}
					if (userProfileArrayList.get(i).getYr().toString()
							.equals("Empty")) {

						sdate.setHint("Year");

					} else {
						sdate.setText(userProfileArrayList.get(i).getYr()
								.toString());

					}

					if (userProfileArrayList.get(i).getSuccesstips().toString()
							.equals("Empty")) {

						successTip.setHint("Success tips");

					} else {
						successTip.setText(userProfileArrayList.get(i)
								.getSuccesstips().toString());

					}
					qualificationEditText.setText(userProfileArrayList.get(i)
							.getQualification().toString());
					cityEditText.setText(userProfileArrayList.get(i).getCity()
							.toString());
					conty.setText(userProfileArrayList.get(i).getCountry()
							.toString());

				}

			}

		}
	}

}
