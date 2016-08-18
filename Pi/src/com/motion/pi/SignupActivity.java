package com.motion.pi;

import info.androidhive.customlistviewvolley.util.Util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

//import com.beingjavaguys.model.UserStatus;
//import com.beingjavaguys.model.Usersignup;
//import com.beingjavaguys.model.login;

//import org.apache.http.HttpEntity;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by Prakash on 8/14/2015.
 */
public class SignupActivity extends Activity {
	private static final int PICK_FROM_GALLERY = 101;
	final int PIC_CROP = 1;
	Bitmap bitmap;
	// String b="pradeep";
	TextView tname;
	private ProgressDialog pDialog;
	TextView tlast;
	String out;
	TextView tnum;
	private String attachmentFile;
	private int columnIndex;
	String userName;
	TextView tmail;
	private ProgressDialog dialog;
	EditText usernameEditText;
	EditText lastnameEditText;
	EditText mailEditText;
	EditText mobilenumberEditText;
	EditText passwordEditText;

	String Qual, city, country;
	EditText abt;
	String abts;
	public String fname;
	SQLiteDatabase dbs;
	private Uri URI = null;
	// private ImageView imageview = null;
	String mymail;
	String restUrl;
	private static String url = "http://166.62.81.118:18080/SpringRestCrud/signup/userlist";
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
	ArrayList<HashMap<String, String>> contactList;
	String lnames;
	private Button btnretrive = null;
	String link;
	private MyDataBase mdb = null;
	// private SQLiteDatabase db = null;
	EditText caption;
	private Cursor c = null;
	private byte[] img = null;
	String Paswd;
	private static final int PICK_IMAGE = 1;
	String citys;
	private static final String DATABASE_NAME = "ImageDb.db";
	public static final int DATABASE_VERSION = 1;
	private AlaramData audio;
	URI uri;
	String gender;
	DatabaseHandler db;
	String firstName, lastName, email, phone;
	String pincode;
	ImageView profileImageView;
	EditText firstNameEditTex;
	private EditText pincodeEditText;
	private RadioGroup radiogroup;
	private EditText yearEditText;
	String Quals;
	String countrys;
	private ImageView writeWrongImageView;
	private ImageView mailImageView;
	private ImageView mobileImageView;
	private EditText earlierSuccesEditText;
	private EditText ocupationEditText;
	private EditText successEditText;
	private Toast toast = null;
	private boolean isEnterAllDetails = false;
	private boolean isUserLogintryToLoginWithPresentUsername = false;
	private boolean isUserLogintryToLoginWithPresentMobile = false;
	private boolean isUserLogintryToLoginWithPresentEmailid = false;
	// This spinner declare to show user to select their qualification.
	String[] items = new String[] { "  Qualification", "  Ph.d", "  PG",
			"  Engineering", "  MBBS", "  Degree", "  Diploma" };

	// This spinner declare to show user to select their city from given list
	String[] cities = new String[] { "  City", "  Ahmadabad", "  Bhopal",
			"  Bangalore", "  Chennai", "  Coimbatore", "  Delhi",
			"  Hyderabad", "  Vizag", "  Indore", "  Luknow" };

	// This spinner declare to show user to select their country from given list
	String[] countrySpinnerArray = new String[] { "  Country", "  Australia",
			"  Belgium", "  China", "  Canada", "  Russia", "  India",
			"  United States", "  United Kingdom", "  New Zealand" };

	// LoginDataBaseHelper loginDataBaseAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sing);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		final TextView qualificationTextView = (TextView) findViewById(R.id.sp1);
		final TextView cityTextView = (TextView) findViewById(R.id.sp2);
		final TextView countryTextView = (TextView) findViewById(R.id.sp3);
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
		final Spinner qualificationSpinner = (Spinner) findViewById(R.id.qualificationSpinner);
		final Spinner citySpinner = (Spinner) findViewById(R.id.citySpinner);
		final Spinner countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		firstNameEditTex = (EditText) findViewById(R.id.firstnameEditText);
		lastnameEditText = (EditText) findViewById(R.id.lastnameEditText);
		pincodeEditText = (EditText) findViewById(R.id.pincodeEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		ocupationEditText = (EditText) findViewById(R.id.ocupationEditText);
		radiogroup = (RadioGroup) findViewById(R.id.radiogrouplayout);
		successEditText = (EditText) findViewById(R.id.successTipEditText);
		mobilenumberEditText = (EditText) findViewById(R.id.mobilenumberEditText);
		mailEditText = (EditText) findViewById(R.id.mailEditText);
		yearEditText = (EditText) findViewById(R.id.yearEditText);
		earlierSuccesEditText = (EditText) findViewById(R.id.earlierSucces);
		mailImageView = (ImageView) findViewById(R.id.emailImageView);
		mobileImageView = (ImageView) findViewById(R.id.mobileImageView);
		writeWrongImageView = (ImageView) findViewById(R.id.wrongWriteImageView);
		contactList = new ArrayList<HashMap<String, String>>();
		db = new DatabaseHandler(this);
		caption = (EditText) findViewById(R.id.samp);
		abt = (EditText) findViewById(R.id.abtme);
		// profileImageView = (ImageView)
		// findViewById(R.id.profilePicImageView);
		// Button load = (Button)findViewById(R.id.button33);
		// ImageView load = (ImageView)findViewById(R.id.imageView25);
		// profileImageView.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// selectImage();
		// }
		// });

		// set the qualification adapter of spinner.
		qualificationSpinner.setAdapter(new SpinnerQualificationAdapter(
				SignupActivity.this, R.layout.spinner_item_layout, items));
		qualificationSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						qualificationTextView.setText(qualificationSpinner
								.getSelectedItem().toString());
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		// Here set the adapter to spinner.
		citySpinner.setAdapter(new SpinnerCityAdapter(SignupActivity.this,
				R.layout.spinner_item_layout, cities));
		citySpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub

						cityTextView.setText(citySpinner.getSelectedItem()
								.toString());
						// city = cityTextView.getText().toString();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		// Here set the adapter to country spinner.
		countrySpinner.setAdapter(new SpinnerCountryAdapter(
				SignupActivity.this, R.layout.spinner_item_layout,
				countrySpinnerArray));

		countrySpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						countryTextView.setText(countrySpinner
								.getSelectedItem().toString());
						country = countryTextView.getText().toString();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		// fname =name.getText().toString();
		// String lname = lastname.getText().toString();

		// String mmail = mail.getText().toString();

		tname = (TextView) findViewById(R.id.t1);
		tlast = (TextView) findViewById(R.id.t2);
		tmail = (TextView) findViewById(R.id.t3);
		tnum = (TextView) findViewById(R.id.t4);

		String pwd = passwordEditText.getText().toString();

		// rg.check(R.id.rd1);

		// select the gender from the radion group button.

		radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton rb = (RadioButton) group.findViewById(checkedId);
				if (null != rb && checkedId > -1) {

					gender = rb.getText().toString();
					System.out.println("gender>>>" + gender);
				}

			}
		});

		mailEditText.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				String mail = mailEditText.getText().toString();

				if (mail.equals("")) {
					toast.setText("Please enter mail");
					toast.show();
					mailImageView.setVisibility(View.GONE);

				} else if (Util.isNetworkAvailable(getApplicationContext())) {
					new CheckEmailid().execute();
				} else {
					toast.setText("Please connect to internet to chcek user availability");
					toast.show();
				}

			}
		});

		mobilenumberEditText
				.setOnFocusChangeListener(new OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						// TODO Auto-generated method stub

						String mobilenumber = mobilenumberEditText.getText()
								.toString();

						if (mobilenumber.equals("")) {
							toast.setText("Please enter mobile number");
							toast.show();
							mobileImageView.setVisibility(View.GONE);

						} else if (Util
								.isNetworkAvailable(getApplicationContext())) {
							new MobileCheck().execute();
						} else {
							toast.setText("Please connect to internet to chcek user availability");
							toast.show();
						}

					}
				});
		usernameEditText.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				String username = usernameEditText.getText().toString();

				if (username.equals("")) {
					toast.setText("Please enter username");
					toast.show();
					writeWrongImageView.setVisibility(View.GONE);

				} else if (Util.isNetworkAvailable(getApplicationContext())) {
					new Checkusername().execute();
				} else {
					toast.setText("Please connect to internet to chcek user availability");
					toast.show();
				}

			}
		});

		Button submmit = (Button) findViewById(R.id.sub);
		// ddszdsd
		submmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				// firstName = firstNameEditTex.getText().toString();
				// pincode = pincodeEditText.getText().toString();
				//
				// // get selected radio button from radioGroup
				//
				// // find the radiobutton by returned id
				// userName = usernameEditText.getText().toString();
				// Paswd = passwordEditText.getText().toString();
				// lnames = lastnameEditText.getText().toString();
				// Abt = abt.getText().toString();
				// mymail = mailEditText.getText().toString();
				// mob = mobilenumberEditText.getText().toString();
				// Quals = qualificationTextView.getText().toString();
				// citys = cityTextView.getText().toString();
				// countrys = countryTextView.getText().toString();

				/**
				 * Here given the validation while user signup to get register
				 */

				if (usernameEditText.getText().toString().length() == 0) {

					usernameEditText.setError("Please enter username");

				} else if (firstNameEditTex.getText().toString().length() == 0) {

					firstNameEditTex.setError("Please enter firstname");

				} else if (lastnameEditText.getText().toString().length() == 0) {

					lastnameEditText.setError("Please enter lastname");

				} else if (passwordEditText.getText().toString().length() == 0) {

					passwordEditText.setError("please enter password");

				} else if (radiogroup.getCheckedRadioButtonId() <= 0) {

					toast.setText("Please select gender");
					toast.show();

				} else if (mailEditText.getText().toString().length() == 0) {
					mailEditText.setError("Please enter email id");

				} else if (mobilenumberEditText.getText().toString().length() == 0) {

					mobilenumberEditText.setError("Please enter mobile number");

				} else if (pincodeEditText.getText().toString().length() == 0) {

					pincodeEditText.setError("Please enter pincode");

				} else if (qualificationSpinner.getSelectedItem().toString()
						.trim().equals("Qualification")) {

					toast.setText("Please select qualification");
					toast.show();

				} else if (citySpinner.getSelectedItem().toString().trim()
						.equals("City")) {

					toast.setText("Please select city");
					toast.show();

				} else if (countrySpinner.getSelectedItem().toString().trim()
						.equals("Country")) {
					toast.setText("Please enter country");
					toast.show();
				} else if (mobilenumberEditText.getText().toString().length() != 10) {

					mobilenumberEditText
							.setError("Please enter only 10 digit number");

				} else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
						mailEditText.getText().toString()).matches()) {
					mailEditText.setError("Please enter valid mail id");

				} else if (isUserLogintryToLoginWithPresentEmailid == true) {
					toast.setText("Please enter different email id");
					toast.show();
					mailEditText.setText("");

				} else if (isUserLogintryToLoginWithPresentMobile == true) {
					toast.setText("Please enter different mobile number");
					toast.show();
					mobilenumberEditText.setText("");

				} else if (isUserLogintryToLoginWithPresentUsername == true) {
					toast.setText("Please enter different username");
					toast.show();
					usernameEditText.setText("");

				} else {

					String username = usernameEditText.getText().toString();
					String firstname = firstNameEditTex.getText().toString();
					String lastname = lastnameEditText.getText().toString();
					String password = passwordEditText.getText().toString();
					// gender
					String mailid = mailEditText.getText().toString();
					String mobile = mobilenumberEditText.getText().toString();
					String aboutme = abt.getText().toString();
					String pincode = pincodeEditText.getText().toString();
					String qualification = qualificationSpinner
							.getSelectedItem().toString();
					String occupation = ocupationEditText.getText().toString();
					String city = citySpinner.getSelectedItem().toString();
					String country = countrySpinner.getSelectedItem()
							.toString();
					String earliderSuccess = earlierSuccesEditText.getText()
							.toString();
					String successTips = successEditText.getText().toString();
					String year = yearEditText.getText().toString();

					if (earlierSuccesEditText.getText().toString().length() == 0) {
						earliderSuccess = "Empty";

					}
					if (successEditText.getText().toString().length() == 0) {
						successTips = "Empty";

					}
					if (yearEditText.getText().toString().length() == 0) {
						year = "Empty";
					}
					if (abt.getText().toString().length() == 0) {
						aboutme = "Empty";
					}
					if (ocupationEditText.getText().toString().length() == 0) {

						occupation = "Empty";

					}
					/**
					 * http://166.62.81.118:18080/SpringRestCrud/signup/
					 * createuser
					 * /James/123/James/Bond/Male/Bad/2/baburao4790@gmail.
					 * com/1234567890
					 * /image/PG/India/Hyderabad/500030/WOrking/good/hi/90
					 */

					new RegistrationAsynTask().execute(username, password,
							firstname, lastname, gender, aboutme, mailid,
							mobile, qualification, country, city, pincode,
							occupation, earliderSuccess, successTips, year);

					// // new ImageUploadTask().execute();
					// // AlaramData file = new AlaramData(userName,Paswd);
					// // AlaramDA audioDA = new AlaramDA();
					// // audioDA.saveAlaram(file);
					// // Inserting Contacts
					// Log.d("Insert: ", "Inserting ..");
					// db.addContact(new Contact(userName, Paswd));

				}

			}

		});
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
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(
						this.getContentResolver(), selectedImage);
				// profileImageView.setVisibility(View.VISIBLE);
				// profileImageView.setImageBitmap(bitmap);

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

				// profileImageView.setVisibility(View.VISIBLE);
				// profileImageView.setImageBitmap(selectedBitmap);
			}
		}
	}

	class ImageUploadTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... unsued) {
			userName = usernameEditText.getText().toString();
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpContext localContext = new BasicHttpContext();
				HttpPost httpPost = new HttpPost(
						"http://166.62.81.118:18080/SpringRestCrud/mailnotes/singleSave");

				MultipartEntity entity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);

				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				bitmap.compress(CompressFormat.JPEG, 10, bos);
				byte[] data = bos.toByteArray();
				// entity.addPart("photoId", new
				// StringBody(getIntent().getStringExtra("photoId")));
				// entity.addPart("returnformat", new StringBody("json"));
				entity.addPart("uploaded", new ByteArrayBody(data, userName
						+ ".jpg"));
				entity.addPart("photoCaption", new StringBody(caption.getText()
						.toString()));
				httpPost.setEntity(entity);
				HttpResponse response = httpClient.execute(httpPost,
						localContext);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));

				String sResponse = reader.readLine();
				return sResponse;
			} catch (Exception e) {
				// if (dialog.isShowing())
				// dialog.dismiss();
				// Toast.makeText(getApplicationContext(),
				// "Uploaded",
				// Toast.LENGTH_LONG).show();
				Log.e(e.getClass().getName(), e.getMessage(), e);
				return null;
			}

			// (null);
		}

		@Override
		protected void onProgressUpdate(Void... unsued) {

		}

		@Override
		protected void onPostExecute(String sResponse) {
			try {
				// if (dialog.isShowing())
				// dialog.dismiss();

				if (sResponse != null) {
					System.out.println("**********************" + sResponse);
					JSONObject JResponse = new JSONObject(sResponse);
					int success = JResponse.getInt("SUCCESS");
					String message = JResponse.getString("MESSAGE");
					if (success == 0) {
						Toast.makeText(getApplicationContext(), message,
								Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(getApplicationContext(),
								"Photo uploaded successfully",
								Toast.LENGTH_SHORT).show();
						caption.setText("");
					}
				}
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(),
						getString(R.string.hello_world), Toast.LENGTH_LONG)
						.show();
				Log.e(e.getClass().getName(), e.getMessage(), e);
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

		// profileImageView.setImageBitmap(bitmap);

	}

	class MyAsyncTask extends AsyncTask<String, Integer, Double> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("onPreExecute");
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			// TODO Auto-generated method stub
			postData(params[0], params[1], params[2], params[3], params[4],
					params[5], params[6], params[7], params[8], params[9],
					params[10]);

			return null;
		}

		protected void onPostExecute(Double result) {
			// pb.setVisibility(View.GONE);
			// Toast.makeText()
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		public void postData(String username, String fnames, String Paswds,
				String lnamess, String genders, String Abts, String mymail,
				String mob, String qlf, String cty, String country) {
			// Create a new HttpClient and Post Header

			// String fname = "" + name.getText().toString();
			// System.out.println("***************"+name);
			// System.out.println("***************"+pawd);
			// System.out.println("***************"+name);
			// System.out.println("***************"+lname);
			// System.out.println("***************"+num);
			// System.out.println("***************"+nmn);
			// System.out.println("***************"+abt);
			// String lname = "" + lastname.getText().toString();

			// String
			// baseurl="http://166.62.81.118:18080/SpringRestCrud/signup/createuser/"+fname+;
			/*
			 * http://166.62.81.118:18080/SpringRestCrud/signup/createuser/James/
			 * 123
			 * /James/Bond/Male/Bad/2/baburao4790@gmail.com/1234567890/image/PG
			 * /India/Hyderabad/500030/WOrking/good/hi/90
			 */

			String exurl = "http://166.62.81.118:18080/SpringRestCrud/signup/createuser/Baburao/12345/Baburao/rao/M/Android%20developer/ACTIVE/baburao4790@gmail.com/7799591404/hi/btech/india/hyd";
			System.out.println("*********" + exurl);
			try {

				String baseurl = "http://166.62.81.118:18080/SpringRestCrud/signup/createuser/";
				baseurl += username + "/" + Paswds + "/" + fnames + "/"
						+ lnamess + "/" + genders + "/" + Abts + "aa" + "/"
						+ "ACTIVE" + "/" + mymail + "/" + mob + "/" + "pic"
						+ username + ".jpg" + "/" + qlf + "/" + country + "/"
						+ cty;
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

			pDialog = new ProgressDialog(SignupActivity.this);

			pDialog.setMessage("Please wait...");

			pDialog.setCancelable(true);
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
			if (vl.contains(usernameEditText.getText().toString())) {
				Toast.makeText(getApplicationContext(),
						"Username Already Exists", Toast.LENGTH_LONG).show();

			} else {
				new MyAsyncTask().execute(userName, Paswd, lnames, gender, Abt,
						mymail, mob, Quals, citys);
				new ImageUploadTask().execute();
				// Toast.makeText(getApplicationContext(), "New Username",
				// 9000).show();
			}
			Log.d("Message", abts);
			// else {
			//
			// }
		}

	}

	// This class for registration to new user.
	class RegistrationAsynTask extends AsyncTask<String, Integer, Double> {

		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("On preExecute");
			progressDialog = new ProgressDialog(SignupActivity.this);
			progressDialog.setMessage("Please wait..");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

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

			if (progressDialog != null && progressDialog.isShowing()) {

				progressDialog.dismiss();

			}

			new AlertDialog.Builder(SignupActivity.this).setTitle("Account")
					.setMessage("Please activate account from email id")
					.setCancelable(false)
					.setPositiveButton("ok", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(SignupActivity.this,
									LoginActivity.class);
							startActivity(intent);
							finish();
						}
					}).create().show();

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		/**
		 * pincode, occupation, earliderSuccess, successTips, year
		 */

		public void postData(String username, String password,
				String firstname, String lastname, String genders,
				String aboutme, String mymail, String mob,
				String qualification, String county, String city,
				String pincode, String occupation, String earlidersuccess,
				String successtip, String year) {
			// Create a new HttpClient and Post Header

			// String fname = "" + name.getText().toString();
			// System.out.println("***************"+name);
			// System.out.println("***************"+pawd);
			// System.out.println("***************"+name);
			// System.out.println("***************"+lname);
			// System.out.println("***************"+num);
			// System.out.println("***************"+nmn);
			// System.out.println("***************"+abt);
			// String lname = "" + lastname.getText().toString();

			// String
			// baseurl="http://166.62.81.118:18080/SpringRestCrud/signup/createuser/"+fname+;
			/*
			 * http://166.62.81.118:18080/SpringRestCrud/signup/createuser/balaram
			 * /
			 * balaram/balaram/barada/M/hi/ACTIVE/balaram.b@gmail.com/9594789819
			 * /hi/mac/india/hyd
			 */
			/**
			 * 
			 */

			/**
			 * for example:>>>>
			 * "http://166.62.81.118:18080/SpringRestCrud/signup/createuser/Baburao/12345/Baburao/rao/M/Android%20developer/ACTIVE/baburao4790@gmail.com/7799591404/hi/btech/india/hyd"
			 * ;
			 */
			try {
				String baseurl = "http://166.62.81.118:18080/SpringRestCrud/signup/createuser/";
				baseurl += username + "/" + password + "/" + firstname + "/"
						+ lastname + "/" + genders + "/" + aboutme + "/"
						+ "ACTIVE" + "/" + mymail + "/" + mob + "/" + username
						+ "/" + qualification + "/" + county + "/" + city + "/"
						+ pincode + "/" + occupation + "/" + earlidersuccess
						+ "/" + successtip + "/" + year;
				System.out.println("Signup user>>" + baseurl);

				uri = new URI(baseurl.replace(" ", "%20"));
				// uri = Uri.parse(out);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}

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
				System.out.println("Response" + entity);
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
	 * This method use to check weather username present or not while register
	 * the user.
	 * 
	 * @author Admin
	 * 
	 */
	private class Checkusername extends AsyncTask<Void, Void, Void> {
		String vl;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

			pDialog = new ProgressDialog(SignupActivity.this);

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
			vl = "{\"jsonBean\":" + jsonStr + "}";
			Log.d("Response: ", "> " + vl);
			System.out.println("User Response>>>>" + vl);

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

					System.out.println("userid>>>" + abts);
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
			if (vl.contains(usernameEditText.getText().toString())) {
				writeWrongImageView.setImageDrawable(getResources()
						.getDrawable(R.drawable.wrong));
				writeWrongImageView.setVisibility(View.VISIBLE);
				isUserLogintryToLoginWithPresentUsername = true;
			} else {

				writeWrongImageView.setImageDrawable(getResources()
						.getDrawable(R.drawable.write));
				writeWrongImageView.setVisibility(View.VISIBLE);
				isUserLogintryToLoginWithPresentUsername = false;
			}
		}

	}

	/**
	 * This method use to check the email id.
	 * 
	 * @author Admin
	 * 
	 */
	private class CheckEmailid extends AsyncTask<Void, Void, Void> {
		String vl;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

			pDialog = new ProgressDialog(SignupActivity.this);

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
			if (pDialog.isShowing())
				pDialog.dismiss();
			if (vl.contains(mailEditText.getText().toString())) {
				mailImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.wrong));
				mailImageView.setVisibility(View.VISIBLE);
				isUserLogintryToLoginWithPresentEmailid = true;
			} else {

				mailImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.write));
				mailImageView.setVisibility(View.VISIBLE);
				isUserLogintryToLoginWithPresentEmailid = false;
			}
		}

	}

	/**
	 * This method use to check weather user want to register with the present
	 * mobile number.
	 * 
	 * @author Admin
	 * 
	 */
	private class MobileCheck extends AsyncTask<Void, Void, Void> {
		String vl;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

			pDialog = new ProgressDialog(SignupActivity.this);

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
			vl = "{\"jsonBean\":" + jsonStr + "}";
			Log.d("Response: ", "> " + vl);
			System.out.println("Mobile response>>" + vl);

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
			if (pDialog.isShowing())
				pDialog.dismiss();

			if (vl.contains(mobilenumberEditText.getText().toString())) {
				mobileImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.wrong));
				mobileImageView.setVisibility(View.VISIBLE);
				isUserLogintryToLoginWithPresentMobile = true;
			} else {

				mobileImageView.setImageDrawable(getResources().getDrawable(
						R.drawable.write));
				mobileImageView.setVisibility(View.VISIBLE);
				isUserLogintryToLoginWithPresentMobile = false;
			}
		}

	}

	// This is an inner class adapter to make custome qualification spinner.
	private class SpinnerQualificationAdapter extends ArrayAdapter<String> {

		private SpinnerQualificationAdapter(Context context,
				int textViewResourceId, String[] objects) {
			super(context, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}

		public View getCustomView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub

			if (convertView == null) {

				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.spinner_item_layout, null);

			}

			TextView nameTextView = (TextView) convertView
					.findViewById(R.id.spinnerNameTextView);

			nameTextView.setText(items[position]);

			return convertView;
		}

	}

	// This is an inner class adapter to make custome qualification spinner.
	private class SpinnerCityAdapter extends ArrayAdapter<String> {

		private SpinnerCityAdapter(Context context, int textViewResourceId,
				String[] objects) {
			super(context, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}

		public View getCustomView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub

			if (convertView == null) {

				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.spinner_item_layout, null);

			}

			TextView nameTextView = (TextView) convertView
					.findViewById(R.id.spinnerNameTextView);

			nameTextView.setText(cities[position]);

			return convertView;
		}

	}

	// This is an inner class adapter to make custome country spinner.
	private class SpinnerCountryAdapter extends ArrayAdapter<String> {

		private SpinnerCountryAdapter(Context context, int textViewResourceId,
				String[] objects) {
			super(context, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}

		public View getCustomView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub

			if (convertView == null) {

				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.spinner_item_layout, null);

			}

			TextView nameTextView = (TextView) convertView
					.findViewById(R.id.spinnerNameTextView);

			nameTextView.setText(countrySpinnerArray[position]);

			return convertView;
		}

	}

	@Override
	protected void onDestroy() {
		if (toast != null) {

			toast.cancel();
		}
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (toast != null) {

			toast.cancel();
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

}
