package com.motion.pi;

import info.androidhive.customlistviewvolley.model.ChatNotificationDao;
import info.androidhive.customlistviewvolley.model.User;
import info.androidhive.customlistviewvolley.model.UserSession;
import info.androidhive.customlistviewvolley.util.ColorSessionManager;
import info.androidhive.customlistviewvolley.util.ImageTrans_roundedcorner;
import info.androidhive.customlistviewvolley.util.LookAndFeel;
import info.androidhive.customlistviewvolley.util.UserLoginDb;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
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
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.motion.actionbar.CustomActionBar;
import com.motion.calendar.MainActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 27-08-2015.
 */
public class Profile extends CustomActionBar {

	private static final int PICK_FROM_GALLERY = 101;
	final int PIC_CROP = 1;
	String uname;
	private String attachmentFile;
	private int columnIndex;
	static final int READ_BLOCK_SIZE = 100;
	String uid;
	String LOGOUTVALUE = "logout";
	String wdp;
	String skb;
	private Uri URI = null;
	String nam;
	TextView names;
	private static final String TAG = Edit.class.getSimpleName();
	String wdps;
	Bitmap bitmap;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private String chatnotification = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/getallnewchat/";
	String userid;
	private byte[] img = null;
	private static final int PICK_IMAGE = 1;
	ImageView profilepicImageView;

	private UserSession userSession;
	private UserLoginDb loginDb;
	private User user;
	private String firstname;
	private String username = "";
	private EditText caption;
	private TextView nameTextView;
	private ColorSessionManager colorSessionManager;
	private ProgressDialog progressDialog;
	private ArrayList<ChatNotificationDao> chatNotificationArraylist;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myprofile);
		ImageView edit = (ImageView) findViewById(R.id.ImageView01);
		caption = (EditText) findViewById(R.id.samp);
		nameTextView = (TextView) findViewById(R.id.textView10);
		userSession = new UserSession(getApplicationContext());
		loginDb = new UserLoginDb(getApplicationContext());
		colorSessionManager = new ColorSessionManager(this);
		chatNotificationArraylist = new ArrayList<ChatNotificationDao>();
		progressDialog = new ProgressDialog(this);
		user = new User();

		edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Profile.this, Edit.class);

				startActivity(i);
				finish();
			}
		});

		ImageView logss = (ImageView) findViewById(R.id.logs);
		ImageView load = (ImageView) findViewById(R.id.imageView100);
		profilepicImageView = (ImageView) findViewById(R.id.imageView10);

		progressDialog.setMessage("Picture loading..");
		progressDialog.setCancelable(true);
		progressDialog.show();

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

		chatnotification += userid;

		// new ChatNotification().execute();
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
		nameTextView.setText(username);
		link += userid + ".jpg";

		Picasso.with(this).invalidate(link);
		Picasso.with(this).load(link).skipMemoryCache().resize(300, 300)
				.placeholder(R.drawable.profilepic_sml)
				.transform(new ImageTrans_roundedcorner())
				.into(profilepicImageView, new Callback() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub

						if (progressDialog != null) {
							progressDialog.dismiss();
						}

					}

					@Override
					public void onError() {
						// TODO Auto-generated method stub

					}
				});
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
				wdps = stringBuilder.toString();
				System.out.println("Lastname>>" + wdps);
			}
		} catch (FileNotFoundException e) {
			Log.e(TAG, "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e(TAG, "Can not read file: " + e.toString());
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
		profilepicImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				selectImage();

			}
		});

		ImageView uload = (ImageView) findViewById(R.id.subbmit);
		// when user click on this button it will upload the profile picture of
		// user.
		uload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (bitmap != null) {

					new ImageUploadTask().execute(userid);
				}
			}
		});
		logss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(Profile.this);
				View myview = inflater.inflate(R.layout.logout_dialog, null);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						Profile.this);
				alertDialogBuilder.setView(myview);
				alertDialogBuilder.setTitle("Exit");
				// setup a dialog window
				alertDialogBuilder
						.setCancelable(false)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										Intent i = new Intent(Profile.this,
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
										colorSessionManager
												.removeAllPreferences();

										// if user will logout then all color
										// will be reset as yellow.
										// colorSessionManager
										// .removeAllPreferences();

										startActivity(i);

										// set boolean value if user logout.

										finish();
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});

				// create an alert dialog
				AlertDialog alert = alertDialogBuilder.create();
				alert.show();

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
				View someView = findViewById(R.id.mypf);

				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// sps = getSharedPreferences("prefs", 0);
		//
		//
		// uname = sps.getString("name", "no name found");
		// kuid = sps.getString("id", "no name found");

		System.out.println("++++++++++++" + userid);
		System.out.println("++++++++++++" + uname);

		final ImageView prof = (ImageView) findViewById(R.id.imageView3);

		final ImageView sky = (ImageView) findViewById(R.id.button9);
		sky.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(Profile.this, Sky_main.class);
				startActivity(i);
			}
		});

		final ImageView rema = (ImageView) findViewById(R.id.button10);
		rema.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// CalendarView
				Intent i = new Intent(Profile.this, MainActivity.class);
				startActivity(i);

			}
		});

		final ImageView notes = (ImageView) findViewById(R.id.button11);
		notes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(Profile.this, Notesmail.class);
				startActivity(i);

			}
		});

		final ImageView cile = (ImageView) findViewById(R.id.button12);
		cile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(Profile.this, Circle.class);
				startActivity(i);
			}
		});

		final ImageView my = (ImageView) findViewById(R.id.button13);
		my.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(Profile.this, Myaccount.class);
				startActivity(i);
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
				bitmap = MediaStore.Images.Media.getBitmap(
						this.getContentResolver(), selectedImage);
				profilepicImageView.setVisibility(View.VISIBLE);
				profilepicImageView.setImageBitmap(bitmap);

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

				profilepicImageView.setVisibility(View.VISIBLE);
				profilepicImageView.setImageBitmap(selectedBitmap);
			}
		}
	}

	class ImageUploadTask extends AsyncTask<String, String, String> {
		private String sResponse;
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("onpreExecute");
			progressDialog = new ProgressDialog(Profile.this);
			progressDialog.setMessage("Loading..");
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
				Bitmap screen = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				float oHeight = screen.getHeight();
				float oWidth = screen.getWidth();
				float aspectRatio = oWidth / oHeight;
				int newHeight = 0;
				int newWidth = 0;
				newHeight = 450;
				newWidth = (int) (newHeight * aspectRatio);
				screen = Bitmap.createScaledBitmap(screen, newWidth, newHeight,
						true);
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
		final int REQUIRED_SIZE = 500;

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

		profilepicImageView.setImageBitmap(bitmap);

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

	/**
	 * This method use to get the all chat notification when user get by other
	 * user.
	 * 
	 * @author Admin
	 * 
	 */
	class ChatNotification extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

		}

		@SuppressWarnings("unchecked")
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(chatnotification,
					ServiceHandler.GET);

			// JSONBean res=null;
			if (jsonStr != null) {

				Gson gson = new Gson();
				Type listType = new TypeToken<ArrayList<ChatNotificationDao>>() {
				}.getType();
				chatNotificationArraylist = (ArrayList<ChatNotificationDao>) gson
						.fromJson(jsonStr, listType);

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			showChatNotification(chatNotificationArraylist);
		}

	}

	/**
	 * method call when having chat notification from other user.
	 * 
	 * @param chatNotificationArraylist
	 */
	private void showChatNotification(
			ArrayList<ChatNotificationDao> chatNotificationArraylist) {

		NotificationManager mNotificationManager = null;
		NotificationCompat.Builder mBuilder = null;

		Bitmap icon1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.icon);

		mBuilder = new NotificationCompat.Builder(this).setAutoCancel(true)
				.setContentTitle("Chat Notification")
				.setSmallIcon(R.drawable.icon).setLargeIcon(icon1)
				.setContentText("");

		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

		// Sets a title for the Inbox style big view
		inboxStyle.setBigContentTitle("Chat Notification");

		System.out.println("chat size>>>" + chatNotificationArraylist);

		// Moves events into the big view
		for (int i = 0; i < chatNotificationArraylist.size(); i++) {

			inboxStyle.addLine(chatNotificationArraylist.get(i).getChatdesc()
					.toString());
			inboxStyle.addLine(chatNotificationArraylist.get(i)
					.getSrcusername().toString());

		}
		// Moves the big view style object into the notification object.
		inboxStyle.addLine("More..");
		mBuilder.setStyle(inboxStyle);

		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, Sky_main.class);

		resultIntent.putExtra("array_list", chatNotificationArraylist);

		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(Sky_main.class);

		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// mId allows you to update the notification later on.
		mNotificationManager.notify(100, mBuilder.build());
	}

	// This method use to Abuse the question of users.
	private class ChatStatusUpdate extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;
		String b;
		String ib;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			// TODO Auto-generated method stub
			postData(params[0]);

			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String chatid) {

			String updateChatStatus = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/updateunreadvhat/";
			updateChatStatus += chatid;
			System.out.println("++++++++++" + updateChatStatus);

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(updateChatStatus);
				responses = httpClient.execute(httpGet);
				entity = responses.getEntity();
				// String responseStr = EntityUtils.toString(entity);
				String content = EntityUtils.toString(entity);
				System.out.println("***********" + content);
				JSONObject myObject = new JSONObject(content);

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

		}

	}

}
