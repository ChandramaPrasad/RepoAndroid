package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.AnsOff;
import info.androidhive.customlistviewvolley.model.LocalModel;
import info.androidhive.customlistviewvolley.model.Movie;
import info.androidhive.customlistviewvolley.model.OfflineAnswer;
import info.androidhive.customlistviewvolley.util.LookAndFeel;
import info.androidhive.customlistviewvolley.util.OfflineAnswerDb;
import info.androidhive.customlistviewvolley.util.QANotificationdb;
import info.androidhive.customlistviewvolley.util.Util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
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
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;

public class AnswersSearch extends CustomActionBar {
	// Log tag
	View formElementsView;
	String skb;
	View convertView;
	int w = 100;
	private static final String TAG = AnswersSearch.class.getSimpleName();
	URI uri;
	// Movies json url
	// private static final String url =
	// "http://api.androidhive.info/json/movies.json";

	// private static final String url =
	// "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswerforkey/";
	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView answerListView;
	private CustomListAdapter adapter;
	static final int READ_BLOCK_SIZE = 100;
	ImageView iprof;
	ImageView icompose;
	String data;
	private SQLiteAdapter mySQLiteAdapter;;
	ImageView isets;
	String Rs;
	String lname;
	private TextView shareTextView;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	ImageView pic;
	private TextView abuseTextView;
	private TextView archiveTextView;
	private TextView questionTextView;
	private SQLiteAdapter sqLiteAdapter;
	private String question;
	private RelativeLayout ratingRelativeLayout;
	private TextView questionratingTextView;
	private ImageView myanswerButton;;
	private TextView questionUsernameTextView;
	private String idsignup;
	private String userid = "";
	private String questionid;
	private String questionUsername;
	private String username = "";
	private String keyword;
	private String questionRate;
	private ArrayList<OfflineAnswer> offlineAnswerArray;
	private RelativeLayout QuestionRealtiveLayout;
	private QANotificationdb qaNotificationdb;
	private Toast toast = null;
	private RelativeLayout answerSearchLayout;
	private EditText sedit;
	private OfflineAnswerDb offlineAnswerDb;
	private ArrayList<AnsOff> ansOffs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newrelatans);
		TextView name = (TextView) findViewById(R.id.textView13);
		ImageView home = (ImageView) findViewById(R.id.button30);
		TextView na = (TextView) findViewById(R.id.textView5);
		pic = (ImageView) findViewById(R.id.imageView5);
		sedit = (EditText) findViewById(R.id.multi);
		// shareTextView = (TextView) findViewById(R.id.shareTextView);
		// abuseTextView = (TextView) findViewById(R.id.abuseTextView);
		// archiveTextView = (TextView) findViewById(R.id.archiveTextView);
		questionTextView = (TextView) findViewById(R.id.QuestionText);
		myanswerButton = (ImageView) findViewById(R.id.myanswerButton);
		ratingRelativeLayout = (RelativeLayout) findViewById(R.id.ratingReltiveLayout);
		questionratingTextView = (TextView) findViewById(R.id.questionRatingTextView);
		sqLiteAdapter = new SQLiteAdapter(this);
		questionUsernameTextView = (TextView) findViewById(R.id.textView4);
		qaNotificationdb = new QANotificationdb(this);
		QuestionRealtiveLayout = (RelativeLayout) findViewById(R.id.Question);
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
		answerListView = (ListView) findViewById(R.id.answerListView);
		offlineAnswerArray = new ArrayList<OfflineAnswer>();
		answerSearchLayout = (RelativeLayout) findViewById(R.id.answerSearchLayout);
		offlineAnswerDb = new OfflineAnswerDb(this);
		ansOffs = new ArrayList<AnsOff>();

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
				username += readstring;

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

		if (!Util.isNetworkAvailable(this)) {

			String question = getIntent().getStringExtra("question");
			String questionusername = getIntent()
					.getStringExtra("questionuser");
			String questionrate = getIntent().getStringExtra("rate");
			String questionid = getIntent().getStringExtra("questionid");

			questionTextView.setText(question);

			questionUsernameTextView.setText(questionusername);
			questionratingTextView.setText(questionrate);
			/**
			 * get all save data from the local database.
			 */

			Cursor offlineAnsCursor = offlineAnswerDb
					.getAllRecordsFromAnswerDb();
			if (offlineAnsCursor != null) {

				if (offlineAnsCursor.moveToFirst()) {
					do {

						OfflineAnswer offlineAnswer = new OfflineAnswer();
						offlineAnswer.setAnswer(offlineAnsCursor
								.getString(offlineAnsCursor
										.getColumnIndex("oflineans")));
						offlineAnswer.setQuestionid(offlineAnsCursor
								.getString(offlineAnsCursor
										.getColumnIndex("quesid")));
						offlineAnswer.setRating(offlineAnsCursor
								.getString(offlineAnsCursor
										.getColumnIndex("ansrate")));
						offlineAnswer.setUsername(offlineAnsCursor
								.getString(offlineAnsCursor
										.getColumnIndex("ansuser")));

						offlineAnswerArray.add(offlineAnswer);

						// do what ever you want here

					} while (offlineAnsCursor.moveToNext());

				}

				offlineAnsCursor.close();

				System.out.println("offline Records>>>" + offlineAnswerArray);

				/**
				 * if data not coming by internet then it will show the offline
				 * data which is read by user.
				 * 
				 */
				OfflineAnswerAdapter offlineansAdapter = new OfflineAnswerAdapter();

				for (int i = 0; i < offlineAnswerArray.size(); i++) {

					if (offlineAnswerArray.get(i).getQuestionid()
							.equals(question)) {
						answerListView.setAdapter(offlineansAdapter);

						System.out.println("question adapter set");

					}

				}

			}

		} else {

			keyword = getIntent().getStringExtra("keyword");
			questionUsername = getIntent().getStringExtra("questionUsername");
			// question = getIntent().getStringExtra("Questions");
			// To get the question id from the localModel
			questionid = LocalModel.getInstance().getQuestionid().toString();
			System.out.println("questionid>>" + questionid);
			questionRate = getIntent().getStringExtra("questionrate");
			mySQLiteAdapter = new SQLiteAdapter(this);
			System.out.println(">>>>>>>>>>>>>oncreate");

			mySQLiteAdapter.openToWrite();
			// if (keyword != null) {

			// } else {
			// Toast.makeText(getApplicationContext(),
			// "Keyword not match for Question", Toast.LENGTH_LONG).show();
			// }
			if (questionRate != null) {
				questionratingTextView.setText(questionRate);
			}

			questionTextView.setText(LocalModel.getInstance().getQuestion()
					.toString());

			questionUsernameTextView.setText(LocalModel.getInstance()
					.getQuestionname().toString());
			// }
			// // this will use to set the question username.
			// if (questionUsername != null) {
			//
			//
			// }

			String questionUsername = questionUsernameTextView.getText()
					.toString();

			System.out.println("question username>>>" + questionUsername);
			System.out.println("Login username>>>>" + username);

			question = LocalModel.getInstance().getQuestion().toString();

			makeJsonArrayRequest(question);

			adapter = new CustomListAdapter(this, movieList);
			adapter.notifyDataSetChanged();
			answerListView.setAdapter(adapter);

			// This will check user is having own question or not.
			if (questionUsername.equals(username)) {

				// This method show two additional option that is edit and
				// delete
				// for own user.
				questionOwnOption();

			} else {
				// This method show only three option to other user than own
				// user.
				questioncommonOption();
			}

			// when user click on the my answer button he will be able to post
			// his
			// or her answer.
			myanswerButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String questionansqus = questionTextView.getText()
							.toString();
					String quser = questionUsernameTextView.getText()
							.toString();
					// get the question id from the localModel because it will
					// every
					// time reset the question id for that question is not
					// getting
					// send.
					questionid = LocalModel.getInstance().getQuestionid()
							.toString();

					Intent intent = new Intent(AnswersSearch.this,
							Answering.class);
					intent.putExtra("question", questionansqus);
					intent.putExtra("idsignup", idsignup);
					intent.putExtra("userid", userid);
					intent.putExtra("questionid", questionid);
					System.out.println("questionid>>>" + questionid);
					startActivity(intent);
					finish();

				}
			});

			answerSearchLayout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					String get = sedit.getText().toString();

					if (TextUtils.isEmpty(get)) {

						toast.setText("Please enter text to search");
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();

					} else {

						Intent i = new Intent(AnswersSearch.this, Search.class);
						i.putExtra("Search", get);
						startActivity(i);

					}

				}
			});

		}

		// set the question here.
		// questionTextView.setText(question);

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
			}
		} catch (FileNotFoundException e) {
			Log.e(TAG, "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e(TAG, "Can not read file: " + e.toString());
		}

		// click on sharetextview button it will store data to share textview
		// shareTextView.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// Toast.makeText(getApplicationContext(), "Share" + "",
		// Toast.LENGTH_SHORT).show();
		// Intent i = new Intent(AnswersSearch.this, Archive.class);
		// startActivity(i);
		//
		// }
		// });

		// // user click on archive then it will store data in archive.
		// archiveTextView.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// Toast.makeText(getApplicationContext(), "Saved to Archive",
		// Toast.LENGTH_SHORT).show();
		// Intent i = new Intent(AnswersSearch.this, Archive.class);
		// startActivity(i);
		// }
		// });
		// // user click on abuse textview text will not display.
		// abuseTextView.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// new Abuse().execute();
		//
		// }
		// });

		// data = keyword.replace(" ", "%20");
		String Aa = getIntent().getStringExtra("Answer");
		Rs = getIntent().getStringExtra("RATE");
		String Rr = getIntent().getStringExtra("RATI");

		try {
			FileInputStream newfile = openFileInput("Skies.txt");
			InputStreamReader InputRead = new InputStreamReader(newfile);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			skb = "";
			int charRead;

			if (InputRead != null) {

				while ((charRead = InputRead.read(inputBuffer)) > 0) {
					// char to string conversion

					String readStrings = String.copyValueOf(inputBuffer, 0,
							charRead);
					skb += readStrings;
					View someView = findViewById(R.id.rela);
					LookAndFeel.lookAndFeel(skb, someView);

				}

			}

			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// try {
		// FileInputStream fileIn = openFileInput("mytextfile.txt");
		// InputStreamReader InputRead = new InputStreamReader(fileIn);
		//
		// char[] inputBuffer = new char[READ_BLOCK_SIZE];
		// String s = "";
		// String d = "";
		// int charRead;
		//
		// while ((charRead = InputRead.read(inputBuffer)) > 0) {
		// // char to string conversion
		// String readstring = String
		// .copyValueOf(inputBuffer, 0, charRead);
		// s += readstring;
		// String readStrings = String.copyValueOf(inputBuffer, 0,
		// charRead);
		// d += readStrings;
		//
		// }
		// InputRead.close();
		// // Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// FileInputStream fileIn = openFileInput("mytextfiles.txt");
		// InputStreamReader InputRead = new InputStreamReader(fileIn);
		//
		// char[] inputBuffer = new char[READ_BLOCK_SIZE];
		// String d = "";
		// int charRead;
		//
		// while ((charRead = InputRead.read(inputBuffer)) > 0) {
		// // char to string conversion
		//
		// String readStrings = String.copyValueOf(inputBuffer, 0,
		// charRead);
		// d += readStrings;
		//
		// }
		// InputRead.close();
		// // Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// try {
		// FileInputStream newfile = openFileInput("Skies.txt");
		// InputStreamReader InputRead = new InputStreamReader(newfile);
		//
		// char[] inputBuffer = new char[READ_BLOCK_SIZE];
		// skb = "";
		// int charRead;
		//
		// while ((charRead = InputRead.read(inputBuffer)) > 0) {
		// // char to string conversion
		//
		// String readStrings = String.copyValueOf(inputBuffer, 0,
		// charRead);
		// skb += readStrings;
		// if (skb.contentEquals("SKY")) {
		// View someView = findViewById(R.id.rela);
		// someView.setBackground(getResources().getDrawable(
		// R.drawable.sky));
		//
		// } else if (skb.contentEquals("YEL")) {
		// View someView = findViewById(R.id.rela);
		// someView.setBackground(getResources().getDrawable(
		// R.drawable.yello));
		// }
		// }
		// InputRead.close();
		// // Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// getWindow().setSoftInputMode(
		// WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		//
		// try {
		// FileInputStream fileIn = openFileInput("mytextfile.txt");
		// InputStreamReader InputRead = new InputStreamReader(fileIn);
		//
		// char[] inputBuffer = new char[READ_BLOCK_SIZE];
		// String s = "";
		// String d = "";
		// int charRead;
		//
		// while ((charRead = InputRead.read(inputBuffer)) > 0) {
		// // char to string conversion
		// String readstring = String
		// .copyValueOf(inputBuffer, 0, charRead);
		// s += readstring;
		// String readStrings = String.copyValueOf(inputBuffer, 0,
		// charRead);
		// d += readStrings;
		//
		// }
		// InputRead.close();
		// // Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// try {
		// FileInputStream fileIn = openFileInput("mytextfiles.txt");
		// InputStreamReader InputRead = new InputStreamReader(fileIn);
		//
		// char[] inputBuffer = new char[READ_BLOCK_SIZE];
		// String d = "";
		// int charRead;
		//
		// while ((charRead = InputRead.read(inputBuffer)) > 0) {
		// // char to string conversion
		//
		// String readStrings = String.copyValueOf(inputBuffer, 0,
		// charRead);
		// d += readStrings;
		//
		// }
		// InputRead.close();
		// // Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// changing action bar color
		// getActionBar().setBackgroundDrawable(
		// new ColorDrawable(Color.parseColor("#1b1b1b")));

		// new MyAsyncTask().execute(question);

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		if (LocalModel.getInstance().isQuestionRatingUpdate() == true
				|| LocalModel.getInstance().isQuestionUpdate() == true) {

			// after refreshing the button then reset the question rating to
			// false.
			LocalModel.getInstance().setQuestionRatingUpdate(false);
			// after refreshing then reset again to value as false.
			LocalModel.getInstance().setQuestionUpdate(false);
			Intent intent = new Intent(AnswersSearch.this, PiAnswers.class);
			startActivity(intent);
			finish();

		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println(">>>>>>>>>>>>>onpause");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println(">>>>>>>>>>>>>onresume");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println(">>>>>>>>>>>>>onstart");

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		System.out.println(">>>>>>>>>>>>>onrestart");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 100 && resultCode == RESULT_OK) {
			toast.setText("Success message");
			toast.show();
			adapter.notifyDataSetChanged();
		}
	}

	/**
	 * This method use to show only three option to other users.
	 */
	private void questioncommonOption() {
		// TODO Auto-generated method stub

		QuestionRealtiveLayout
				.setOnLongClickListener(new OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {

						PopupMenu popup = new PopupMenu(AnswersSearch.this,
								QuestionRealtiveLayout);
						// Inflating the Popup using xml file
						popup.getMenuInflater().inflate(
								R.menu.popup_menu_question_common,
								popup.getMenu());

						popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
							public boolean onMenuItemClick(MenuItem item) {

								if (item.getTitle().equals("Rate")) {
									// This method use to rate the question
									// which is posted by user
									rateToQuestion();

								} else if (item.getTitle().equals(
										"Report as abuse")) {
									// This method use to report the question
									reportQuestion();

								} else if (item.getTitle().equals("Archive")) {
									// This method use to archive.
									archiveQuestion();

								}
								return true;
							}

						});

						popup.show();// showing popup menu

						return false;
						// TODO Auto-generated method stub
					}
				});

	}

	/**
	 * This method use to show two other option include common option to user.
	 */
	private void questionOwnOption() {

		QuestionRealtiveLayout
				.setOnLongClickListener(new OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {

						PopupMenu popup = new PopupMenu(AnswersSearch.this,
								QuestionRealtiveLayout);
						// Inflating the Popup using xml file
						popup.getMenuInflater()
								.inflate(R.menu.popup_menu_question_own,
										popup.getMenu());

						popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
							public boolean onMenuItemClick(MenuItem item) {

								if (item.getTitle().equals("Archive")) {
									// This method use to archive.
									archiveQuestion();

								} else if (item.getTitle().equals("Edit")) {

									updateQuestionByOwnUser();

								} else if (item.getTitle().equals("Delete")) {

									// when user click on the delete button
									// he/she able to delete tha question.

									AlertDialog.Builder builder1 = new AlertDialog.Builder(
											AnswersSearch.this);
									builder1.setMessage("Are you sure,want to delete the Question?");
									builder1.setCancelable(true);

									builder1.setPositiveButton(
											"Yes",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int id) {
													new DeleteQuestion()
															.execute(questionid);

													dialog.cancel();
												}
											});

									builder1.setNegativeButton(
											"No",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int id) {
													dialog.cancel();
												}
											});

									AlertDialog alert11 = builder1.create();
									alert11.show();

								}
								return true;
							}

						});

						popup.show();// showing popup menu

						return false;
						// TODO Auto-generated method stub
					}
				});

	}

	/**
	 * This method use to update the question which is posted by him/her only.
	 */
	private void updateQuestionByOwnUser() {

		// showInputDialog();
		LayoutInflater inflater = LayoutInflater.from(AnswersSearch.this);
		View answerUpdateView = inflater.inflate(
				R.layout.update_question_layout, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				AnswersSearch.this);
		alertDialogBuilder.setView(answerUpdateView);
		final EditText questionEditText = (EditText) answerUpdateView
				.findViewById(R.id.questionEditText);
		questionEditText.setText(question);

		// setup a dialog window
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("Update",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								String questiondetails = questionEditText
										.getText().toString();

								if (questiondetails != null) {

									// This asyntask use to update the question
									// by user.
									new UpdatingQuestion().execute(questionid,
											userid, questiondetails, keyword);
									questionTextView.setText(questiondetails);

								}

								dialog.cancel();

							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create an alert dialog
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();

	}

	private void archiveQuestion() {

		AlaramData2 file = new AlaramData2(questionTextView.getText()
				.toString(), questionTextView.getText().toString(),
				questionTextView.getText().toString());
		AlaramDA2 audioD = new AlaramDA2();
		audioD.saveAlaram(file);
		String data1 = questionTextView.getText().toString();
		String data2 = questionTextView.getText().toString();
		mySQLiteAdapter.insert(data2, data1);
		toast.setText("Saved to archive");
		toast.show();
		Intent i = new Intent(AnswersSearch.this, Archive.class);
		startActivity(i);

	}

	private void reportQuestion() {

		new Abuse().execute();
		toast.setText("Report abuse");
		toast.show();

	}

	private void rateToQuestion() {

		AlertDialog.Builder dialog = new AlertDialog.Builder(AnswersSearch.this);

		// Create a custom layout for the dialog box
		LayoutInflater inflater = (LayoutInflater) AnswersSearch.this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		final View layout = inflater.inflate(R.layout.sams, null, false);
		final RadioGroup genderRadioGroups = (RadioGroup) layout
				.findViewById(R.id.radiogrouprating);

		Button sendButton = (Button) layout.findViewById(R.id.okbutton);

		dialog.setView(layout);
		// dialog.setInverseBackgroundForced(true);

		final AlertDialog alertDialog = dialog.create();

		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				int selectedId = genderRadioGroups.getCheckedRadioButtonId();

				// find the radiobutton by
				// returned
				// id
				RadioButton selectedRadioButtons = (RadioButton) layout
						.findViewById(selectedId);

				if (selectedRadioButtons != null) {

					questionratingTextView.setText(""
							+ selectedRadioButtons.getText());

					String questionRate = questionratingTextView.getText()
							.toString();

					// This asyntask use to update the question rating when user
					// want to update.
					new QuestionRatingUpdateAsynTask().execute(questionid,
							questionRate);

				} else {
					Toast.makeText(getApplicationContext(),
							"You have not select rating", Toast.LENGTH_LONG)
							.show();
				}
				alertDialog.dismiss();

			}
		});

		alertDialog.show();
		// This line which use to show the fix size of window on
		// users screen.
		// alertDialog.getWindow().setLayout(250, 650);

		try {
			ViewGroup viewGroup1 = (ViewGroup) layout.getParent();
			if (viewGroup1 != null) {
				viewGroup1.setBackgroundResource(android.R.color.transparent);

				ViewGroup viewGroup2 = (ViewGroup) viewGroup1.getParent();
				if (viewGroup2 != null) {
					viewGroup2
							.setBackgroundResource(android.R.color.transparent);
				}
			}
		} catch (Exception e) {
		}

	}

	private class MyAsyncTask extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;
		String url;
		String b;
		String ib;
		String url3;

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

		private void postData(String username) {
			// Creating volley request obj
			username.replace(" ", "%20");
			Log.d("Question", username);

			try {
				url = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswerforkey/";
				url += username;
				uri = new URI(url.replace(" ", "%20"));
				System.out.println("**************uri" + uri);
				URL url2 = uri.toURL();
				url3 = url2.toString();
			} catch (RuntimeException | URISyntaxException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Log.d("SerachURL", url);
			JsonArrayRequest movieReq = new JsonArrayRequest(url3,
					new Response.Listener<JSONArray>() {

						@Override
						public void onResponse(JSONArray response) {
							Log.d(TAG,
									"hii&&&&&&&&&&&&&&&&&&&&&&:"
											+ response.toString());
							if (response.toString().equals("[]")) {
								Toast.makeText(getApplicationContext(),
										"Search item not data found", 9000)
										.show();

							}

							hidePDialog();

							// Parsing json
							for (int i = 0; i < response.length(); i++) {
								try {

									JSONObject obj = response.getJSONObject(i);
									Movie movie = new Movie();
									movie.setAnsuserid(obj.getLong("ansuserid"));
									movie.setIdanswer(obj.getLong("idanswer"));
									movie.setAnswerdetails(obj
											.getString("answerdetails"));
									movie.setRatingtypevalue(obj
											.getLong("ratingtypevalue"));

									JSONObject qdetails = obj
											.getJSONObject("questionid");
									// String questiondetails =
									// qdetails.getString("questiondetails");
									movie.setQuestiondetails(qdetails
											.getString("questiondetails"));
									movie.setAnsusername(obj
											.getString("ansusername"));
									System.out.print("obj2:"
											+ obj.getString("questionid"));
									// JSONArray genreArry = obj.getJSONArray();
									// System.out.print("genreArry:"+genreArry.toString());
									// JSONObject obj2 =
									// genreArry.getJSONObject(1);

									// System.out.print("obj2:"+obj2.toString());
									// adding movie to movies array
									movieList.add(movie);

								} catch (Exception e) {
									e.printStackTrace();
								}

							}

							// notifying list adapter about data changes
							// so that it renders the list view with updated
							// data

							adapter.notifyDataSetChanged();
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							VolleyLog.d(TAG, "Error: " + error.getMessage());
							hidePDialog();

						}
					});

			// Adding request to request queue
			AppController.getInstance().addToRequestQueue(movieReq);
		}

		protected void onPostExecute(Double result) {

		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

	/**
	 * This is custom adpater use to set to show the answer of the respective
	 * question.
	 * 
	 * @author Admin
	 * 
	 */
	class CustomListAdapter extends BaseAdapter {
		private Activity activity;
		private LayoutInflater inflater;
		View convertView;
		private List<Movie> movieItems;
		ImageLoader imageLoader = AppController.getInstance().getImageLoader();

		public CustomListAdapter(Activity activity, List<Movie> movieItems) {
			this.activity = activity;
			this.movieItems = movieItems;
		}

		@Override
		public int getCount() {
			return movieItems.size();
		}

		@Override
		public Object getItem(int location) {
			return movieItems.get(location);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (inflater == null)
				inflater = (LayoutInflater) activity
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.answersearch_item, null);

				ViewHolder viewHolder = new ViewHolder();

				viewHolder.rateTextView = (TextView) convertView
						.findViewById(R.id.ratingTextView);
				viewHolder.answerTextView = (TextView) convertView
						.findViewById(R.id.releaseYear);
				viewHolder.answerusername = (TextView) convertView
						.findViewById(R.id.answerby);
				viewHolder.answerRatingId = (TextView) convertView
						.findViewById(R.id.ansratingId);

				viewHolder.answerId = (TextView) convertView
						.findViewById(R.id.answerId);
				viewHolder.answerRelativeLayout = (RelativeLayout) convertView
						.findViewById(R.id.answerRelativeLayout);

				convertView.setTag(viewHolder);
			}

			final ViewHolder holder = (ViewHolder) convertView.getTag();

			Movie m = movieItems.get(position);

			// thumbnail image
			// thumbNail.setImageUrl(null, imageLoader);

			// title
			// title.setText(m.getQuestiondetails());
			holder.answerusername.setText(m.getAnsusername());
			holder.answerTextView.setText(m.getAnswerdetails());
			holder.answerRatingId.setText(String.valueOf(m.getIdanswer()));
			holder.rateTextView.setText(String.valueOf(m.getRatingtypevalue()));
			holder.answerId.setText(String.valueOf(m.getIdanswer()));

			// NetworkImageView thumbNail = (NetworkImageView) convertView
			// .findViewById(R.id.thumbnail);
			// final TextView title = (TextView)
			// convertView.findViewById(R.id.title);
			// TextView rating = (TextView)
			// convertView.findViewById(R.id.rating);
			// TextView genre = (TextView) convertView.findViewById(R.id.genre);
			// =

			// RelativeLayout ratingRelativeLayout = (RelativeLayout)
			// convertView
			// .findViewById(R.id.ratingRelativeLayout);
			//
			// TextView archiveTextView = (TextView) convertView
			// .findViewById(R.id.archiveTextView);

			// getting movie data for the row

			final String ansid = holder.answerId.getText().toString();
			// final String ansuserid = answerId.getText().toString();
			String ansusername = holder.answerusername.getText().toString();

			if (ansusername.equals(username)) {

				holder.answerRelativeLayout
						.setOnLongClickListener(new OnLongClickListener() {

							@Override
							public boolean onLongClick(View v) {
								PopupMenu popup = new PopupMenu(
										AnswersSearch.this,
										holder.answerRelativeLayout);
								// Inflating the Popup using xml file
								popup.getMenuInflater().inflate(
										R.menu.pop_up_answer_own_menu,
										popup.getMenu());

								popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
									public boolean onMenuItemClick(MenuItem item) {

										if (item.getTitle().equals("Archive")) {
											AlaramData2 file = new AlaramData2(
													holder.answerTextView
															.getText()
															.toString(),
													holder.answerTextView
															.getText()
															.toString(),
													holder.answerTextView
															.getText()
															.toString());
											AlaramDA2 audioD = new AlaramDA2();
											audioD.saveAlaram(file);
											String data1 = holder.answerTextView
													.getText().toString();
											String data2 = holder.answerTextView
													.getText().toString();
											mySQLiteAdapter
													.insert(data1, data2);
											toast.setText("Saved to archive");
											toast.show();
											Intent i = new Intent(
													AnswersSearch.this,
													Archive.class);
											startActivity(i);

										} else if (item.getTitle().equals(
												"Edit")) {

											toast.setText("Edit");
											toast.show();
											// When user click on this menu
											// option it will update the answer
											// to that questions.
											updateAnswerByOwn();

										} else if (item.getTitle().equals(
												"Delete")) {

											final String ansid = holder.answerId
													.getText().toString();

											toast.setText("Delete");
											toast.show();
											// To delete the question by the
											// owner

											AlertDialog.Builder builder1 = new AlertDialog.Builder(
													AnswersSearch.this);
											builder1.setMessage("Are you sure,want to delete the Answer?");
											builder1.setCancelable(true);

											builder1.setPositiveButton(
													"Yes",
													new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int id) {
															// This method use
															// to Delete the
															// Answer of posted
															// question.
															new DeleteAnswer()
																	.execute(
																			ansid,
																			userid);

															dialog.cancel();
														}
													});

											builder1.setNegativeButton(
													"No",
													new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int id) {
															dialog.cancel();
														}
													});

											AlertDialog alert11 = builder1
													.create();
											alert11.show();

										}
										return true;
									}

									/**
									 * This method use to update the answer to
									 * that question.
									 */
									private void updateAnswerByOwn() {

										// showInputDialog();
										LayoutInflater inflater = LayoutInflater
												.from(AnswersSearch.this);
										View answerUpdateView = inflater
												.inflate(
														R.layout.update_answer_layout,
														null);
										AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
												AnswersSearch.this);
										alertDialogBuilder
												.setView(answerUpdateView);

										final EditText answerText = (EditText) answerUpdateView
												.findViewById(R.id.answerEditText);
										answerText
												.setText(holder.answerTextView
														.getText().toString());

										// setup a dialog window
										alertDialogBuilder
												.setCancelable(false)
												.setPositiveButton(
														"Update",
														new DialogInterface.OnClickListener() {
															public void onClick(
																	DialogInterface dialog,
																	int id) {
																String answerDetails = answerText
																		.getText()
																		.toString();

																if (answerDetails != null) {

																	new UpdatingAnswer()
																			.execute(
																					ansid,
																					answerDetails,
																					userid);
																	holder.answerTextView
																			.setText(answerText
																					.getText()
																					.toString());
																	System.out
																			.println("answer>>>"
																					+ answerText
																							.getText()
																							.toString());

																}

																dialog.cancel();

															}
														})
												.setNegativeButton(
														"Cancel",
														new DialogInterface.OnClickListener() {
															public void onClick(
																	DialogInterface dialog,
																	int id) {
																dialog.cancel();
															}
														});

										// create an alert dialog
										AlertDialog alert = alertDialogBuilder
												.create();
										alert.show();

									}

								});

								popup.show();// showing popup menu

								return false;
							}
						});

			} else {
				holder.answerRelativeLayout
						.setOnLongClickListener(new OnLongClickListener() {

							@Override
							public boolean onLongClick(View v) {
								PopupMenu popup = new PopupMenu(
										AnswersSearch.this,
										holder.answerRelativeLayout);
								// Inflating the Popup using xml file
								popup.getMenuInflater().inflate(
										R.menu.pop_up_answer_common_menu,
										popup.getMenu());

								popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
									public boolean onMenuItemClick(MenuItem item) {

										if (item.getTitle().equals("Rate")) {

											// add viewGroup to solve the
											// problem
											// for unfortunetly
											// stop of application when user
											// click
											// on rating button.
											// added by prasad.

											AlertDialog.Builder dialog = new AlertDialog.Builder(
													AnswersSearch.this);

											// Create a custom layout for the
											// dialog
											// box
											LayoutInflater inflater = (LayoutInflater) AnswersSearch.this
													.getSystemService(LAYOUT_INFLATER_SERVICE);
											final View layout = inflater
													.inflate(R.layout.sams,
															null, false);
											final RadioGroup genderRadioGroups = (RadioGroup) layout
													.findViewById(R.id.radiogrouprating);

											Button sendButton = (Button) layout
													.findViewById(R.id.okbutton);

											dialog.setView(layout);
											// dialog.setInverseBackgroundForced(true);

											final AlertDialog alertDialog = dialog
													.create();

											sendButton
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View v) {

															int selectedId = genderRadioGroups
																	.getCheckedRadioButtonId();

															// find the
															// radiobutton
															// by
															// returned
															// id
															RadioButton selectedRadioButtons = (RadioButton) layout
																	.findViewById(selectedId);

															if (selectedRadioButtons != null) {

																holder.rateTextView
																		.setText(selectedRadioButtons
																				.getText()
																				.toString());

																String ansRating = holder.rateTextView
																		.getText()
																		.toString();

																String idanswer = holder.answerRatingId
																		.getText()
																		.toString();

																if (ansRating != null
																		&& idanswer != null) {

																	new AnswerRatingUpdateAsynTask()
																			.execute(
																					idanswer,
																					ansRating);

																	holder.rateTextView
																			.setText(""
																					+ selectedRadioButtons
																							.getText());

																}

															} else {

																toast.setText("You have not select rating");
																toast.show();
															}
															alertDialog
																	.dismiss();

														}
													});

											alertDialog.show();
											// This line which use to show the
											// fix
											// size of window on
											// users screen.
											// alertDialog.getWindow().setLayout(
											// 250, 700);

											try {
												ViewGroup viewGroup1 = (ViewGroup) layout
														.getParent();
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

											// if user select as reposet as
										} else if (item.getTitle().equals(
												"Report as abuse")) {

											String aid = holder.answerRatingId
													.getText().toString();
											// new Abuses().execute(aid);

										} else if (item.getTitle().equals(
												"Archive")) {
											AlaramData2 file = new AlaramData2(
													holder.answerTextView
															.getText()
															.toString(),
													holder.answerTextView
															.getText()
															.toString(),
													holder.answerTextView
															.getText()
															.toString());
											AlaramDA2 audioD = new AlaramDA2();
											audioD.saveAlaram(file);
											String data1 = holder.answerTextView
													.getText().toString();
											String data2 = holder.answerTextView
													.getText().toString();
											mySQLiteAdapter
													.insert(data1, data2);
											toast.setText("Saved to archive");
											toast.show();
											Intent i = new Intent(
													AnswersSearch.this,
													Archive.class);
											startActivity(i);

										}
										return true;
									}

								});

								popup.show();// showing popup menu

								return false;
							}
						});
			}

			return convertView;
		}
	}

	class ViewHolder {
		public TextView rateTextView, answerTextView, answerusername, answerId,
				answerRatingId;
		public RelativeLayout answerRelativeLayout;
	}

	private class Abuse extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;
		String b;
		String ib;

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			// TODO Auto-generated method stub
			postData();

			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData() {

			String updates = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/deleteans/";
			// updates += aids;
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
			Toast.makeText(getApplicationContext(), "Repost abuse",
					Toast.LENGTH_LONG).show();

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
	 * This method use to update the Answer rating when user want to update
	 * Rating of answer.
	 * 
	 * @author Admin
	 * 
	 */
	private class AnswerRatingUpdateAsynTask extends
			AsyncTask<String, Integer, Double> {
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("On prexcute method");
			progressDialog = new ProgressDialog(AnswersSearch.this);
			progressDialog.setMessage("Please wait..");
			progressDialog.show();
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1]);
			return null;
		}

		protected void onPostExecute(Double result) {

			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();

			}

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String idanswer, String ansrating)
				throws IllegalArgumentException {

			try {
				String answerRating = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/updateratting/";

				answerRating += idanswer + "/" + ansrating;
				System.out.println("url of response" + answerRating);

				uri = new URI(answerRating.replace(" ", "%20"));

				// uri = Uri.parse(out);
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

	private class QuestionRatingUpdateAsynTask extends
			AsyncTask<String, Integer, Double> {
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("On prexcute method");
			progressDialog = new ProgressDialog(AnswersSearch.this);
			progressDialog.setMessage("Please wait..");
			progressDialog.show();
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1]);
			return null;
		}

		protected void onPostExecute(Double result) {

			// Here set the boolean value weather question get update or not.
			LocalModel.getInstance().setQuestionRatingUpdate(true);
			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();

			}

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String idquestion, String querating)
				throws IllegalArgumentException {

			try {
				String answerRating = "http://166.62.81.118:18080/SpringRestCrud/question/updateratting/";

				answerRating += idquestion + "/" + querating;
				System.out.println("url of response" + answerRating);

				uri = new URI(answerRating.replace(" ", "%20"));

				// uri = Uri.parse(out);
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

	// This method use to Abuse the answer of users.
	private class DeleteAnswer extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;
		String b;
		String ib;
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = new ProgressDialog(AnswersSearch.this);
			progressDialog.setMessage("Question Deleting..");
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

		private void postData(String questionid, String userid) {

			String deleteAnswer = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/deleteans/";
			deleteAnswer += questionid + "/" + userid;
			System.out.println("++++++++++" + deleteAnswer);

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(deleteAnswer);
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

			if (progressDialog.isShowing() && progressDialog != null) {

				progressDialog.dismiss();

				Intent intent = new Intent(AnswersSearch.this,
						AnswersSearch.class);
				// This line add for clear the activity when user
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

			}

		}

	}

	// This method use to Abuse the question of users.
	private class DeleteQuestion extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;
		String b;
		String ib;
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = new ProgressDialog(AnswersSearch.this);
			progressDialog.setMessage("Question Deleting..");
			progressDialog.setCancelable(false);
			progressDialog.show();
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

		private void postData(String questionid) {

			String deleteQuestion = "http://166.62.81.118:18080/SpringRestCrud/question/abuseQuestion/";
			deleteQuestion += questionid;
			System.out.println("++++++++++" + deleteQuestion);

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(deleteQuestion);
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

			if (progressDialog.isShowing() && progressDialog != null) {

				progressDialog.dismiss();

			}

			Intent intent = new Intent(AnswersSearch.this, PiAnswers.class);
			startActivity(intent);
			finish();

		}

	}

	/**
	 * This method use to send the request to get the respective question and
	 * answer.
	 * 
	 * @param keyword
	 */
	protected void makeJsonArrayRequest(final String question) {

		String finalurl = null;

		String url = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswerforkey/";
		url += question;
		try {
			uri = new URI(url.replace(" ", "%20"));
			URL url2 = uri.toURL();
			finalurl = url2.toString();

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
						Log.d(TAG, response.toString());

						try {
							// Parsing json array response
							// loop through each json object
							for (int i = 0; i < response.length(); i++) {

								JSONObject obj = response.getJSONObject(i);
								Movie movie = new Movie();
								movie.setAnswerdetails(obj
										.getString("answerdetails"));

								// movie.setQuestiondetails(obj);
								// Genre is json array
								// JSONArray genreArry =
								// obj.getJSONArray("questionid");
								// ArrayList<String> genre = new
								// ArrayList<String>();
								// for (int j = 0; j < genreArry.length();
								// j++) {
								// genre.add((String) genreArry.get(j));
								// }
								JSONObject qdetails = obj
										.getJSONObject("questionid");
								// String questiondetails =
								// qdetails.getString("questiondetails");
								movie.setIdquestion(qdetails
										.getLong("idquestion"));
								movie.setQuestiondetails(qdetails
										.getString("questiondetails"));
								movie.setAnsusername(obj
										.getString("ansusername"));
								movie.setAnsuserid(obj.getLong("ansuserid"));
								movie.setIdanswer(obj.getLong("idanswer"));
								movie.setRatingtypevalue(obj
										.getLong("ratingtypevalue"));
								System.out.print("obj2:"
										+ obj.getString("questionid"));

								movieList.add(movie);

							}
							// sending joblist List to notifiaction method to
							// show notification when launch the application.

							// This is use to post the answer to top of the
							// ListView.
							Collections.reverse(movieList);

							/**
							 * THis method use to add record to database.
							 */
							// offlineAnswerDb.addAnswerToDatabase(movieList,
							// questionid);

							String ansid = String.valueOf(movieList.get(0)
									.getIdanswer());
							String questionid = String.valueOf(movieList.get(0)
									.getQuestiondetails());

							if (!offlineAnswerDb.Exists(question)
									&& !movieList.isEmpty()) {

								offlineAnswerDb.addAnswerToDatabase(movieList);
								System.out
										.println("Not present present>>>>>>>");
							}

							System.out.println("Already present>>>>>>>");

							// OfflineDb(movieList);

							if (LocalModel.getInstance().isAnswerposting() == true) {

								// set the question posting false again.
								LocalModel.getInstance()
										.setAnswerposting(false);

							}

							adapter.notifyDataSetChanged();
							hidePDialog();

						} catch (Exception e) {
							e.printStackTrace();

							hidePDialog();
						}

					}

				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);

	}

	// This asyntaks use to update the question by own user.
	private class UpdatingQuestion extends AsyncTask<String, Integer, Double> {

		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(AnswersSearch.this);
			progressDialog.setMessage("Please wait..");
			progressDialog.setCancelable(false);
			progressDialog.show();

		};

		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1], params[2], params[3]);
			return null;
		}

		protected void onPostExecute(Double result) {

			// set the value to true when question get update.
			LocalModel.getInstance().setQuestionUpdate(true);
			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();

			}

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String questionid, String useridname,
				String questiondetails, String aboutmyquestion)
				throws IllegalArgumentException {

			try {
				// String kompose =
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/{questiondetails}/{userid}/{username}/{aboutmyquestion}/{qtypeid}";
				// String Sample=
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/what%20is%20loop%20statement/30/Kavya/c%20language/5";

				// If the question having with special charaters the it will
				// convert and send to the server.
				String encodedQuestionDetails = URLEncoder.encode(
						questiondetails, "UTF-8").replace("+", "%20");
				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/question/updatequestionbyuser/";
				// System.out.println("***************"+basurl2);
				basurl3 += questionid + "/" + useridname + "/"
						+ encodedQuestionDetails + "/" + aboutmyquestion;
				System.out.println("***************" + basurl3);
				// System.out.println("****************"+Sample);

				uri = new URI(basurl3.replace(" ", "%20"));
				System.out.println("**************uri" + uri);

			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(uri);

				HttpResponse responses = httpClient.execute(httpGet);

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

	// Asyntask use to update the Answer by the user.
	private class UpdatingAnswer extends AsyncTask<String, Integer, Double> {

		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {

			progressDialog = new ProgressDialog(AnswersSearch.this);
			progressDialog.setMessage("Updating Answer..");
			progressDialog.setCancelable(false);
			progressDialog.show();

		};

		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1], params[2]);
			return null;
		}

		protected void onPostExecute(Double result) {

			if (progressDialog.isShowing() && progressDialog != null) {
				progressDialog.dismiss();

			}

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String answerId, String answerDetails,
				String ansuserid) throws IllegalArgumentException {

			try {
				// String kompose =
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/{questiondetails}/{userid}/{username}/{aboutmyquestion}/{qtypeid}";
				// String Sample=
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/what%20is%20loop%20statement/30/Kavya/c%20language/5";

				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/updateans/";
				// System.out.println("***************"+basurl2);
				String updateanswer = URLEncoder.encode(answerDetails, "UTF-8")
						.replace("+", "%20");
				basurl3 += answerId + "/" + updateanswer + "/" + ansuserid;
				System.out.println("***************" + basurl3);
				// System.out.println("****************"+Sample);

				uri = new URI(basurl3.replace(" ", "%20"));
				System.out.println("**************uri" + uri);

			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(uri);

				HttpResponse responses = httpClient.execute(httpGet);

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
	 * THis adapter will call when records will going to offline.
	 * 
	 * @author Admin
	 * 
	 */
	private class OfflineAnswerAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return offlineAnswerArray.size();
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

			if (convertView == null) {

				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.answersearch_item, null);

			}

			TextView answerTextView = (TextView) convertView
					.findViewById(R.id.releaseYear);
			TextView usernameTextView = (TextView) convertView
					.findViewById(R.id.answerby);
			TextView ratingTextView = (TextView) convertView
					.findViewById(R.id.ratingTextView);

			answerTextView.setText(offlineAnswerArray.get(position).getAnswer()
					.toString());
			usernameTextView.setText(offlineAnswerArray.get(position)
					.getUsername().toString());
			ratingTextView
					.setText(offlineAnswerArray.get(position).getRating());

			return convertView;
		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (toast != null) {
			toast.cancel();

		}
	}
}
