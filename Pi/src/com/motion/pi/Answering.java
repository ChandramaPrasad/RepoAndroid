package com.motion.pi;

import info.androidhive.customlistviewvolley.model.LocalModel;
import info.androidhive.customlistviewvolley.util.LookAndFeel;
import info.androidhive.customlistviewvolley.util.QANotificationdb;
import info.androidhive.customlistviewvolley.util.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.motion.actionbar.CustomActionBar;

/**
 * Created by Admin on 21-09-2015.
 */
public class Answering extends CustomActionBar {
	JSONObject jsonObj = null;
	private ProgressDialog pDialog;
	String response;
	ListView dialogs;
	// LazyAdapter adapter;
	String KEY_TEXT = "TEXTPSS";
	static final int CUSTOM_ID = 0;
	ConnectionDetector cd;
	private static String url = "http://166.62.81.118:18080/SpringRestCrud/question/questionlist";
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	Boolean isInternetPresent = false;
	// JSON Node names

	// private static String urls =
	// "http://166.62.81.118:18080/SpringRestCrud/questionanswer/composeanswer/{answerdetails}/{ansuserid}/{questionid}/{ratingtypeid}/{ratingtypevalue}";
	private static final String TAG_CONTACTS = "";
	private static final String QID = "idqtype";
	String QNAME = "qtype";

	ArrayList<HashMap<String, String>> QtyptList;
	ArrayList<HashMap<String, String>> contactList;
	public final int CATEGORY_ID = 1;
	private Context mContext;
	// Dialog dialog;
	String KEY_TEXTPSS = "TEXTPSS";
	static final int CUSTOM_DIALOG_ID = 0;
	Drawable d;
	ImageAdapter imges;
	EditText edt;

	// private List<FeedItem> feedItems;
	ImageView mDoneButton;
	private TextView questionTextView;
	private TextView questionUserTextVeiw;
	private TextView rateTextView;
	private LinearLayout ratingLinearLayout;
	private Toast toast = null;
	URI uri;
	String[] items = { "Paper I (Preliminary)", "Paper II (Preliminary)",
			"General Studies I  (Mains)", "General Studies II  (Mains)",
			"General Studies III  (Mains)", "General Studies IV  (Mains)",
			"Agriculture", "Animal Husbandry and Veterinary Science ",
			"Anthropology ", "Botany", "Chemistry", "Civil Engineering",
			"Commerce and Accountancy", "Economics", "Electrical Engineering",
			"Geography", "Geology", "History", "Law ", "Management",
			"Mathematics", "Mechanical Engineering", "Medical Science ",
			"Philosophy", "Physics",
			"Political Science and International Relations", "Psychology",
			"Public Administration", "Sociology", "Statistics ", "Zoology ",
			"Optional" };

	// final CharSequence[] items ={
	// "Java","Php",".Net"," C"," C+","Networking","Unix","Linux","Physics","Biology","Mathematics"
	// };
	static final int READ_BLOCK_SIZE = 100;
	String keyword;
	TextView resultText;
	String uname;
	String cname;
	String unid;
	String uids;
	String uid;

	String rating = "1";
	TextView fft;
	EditText enterEditText;
	RadioButton selectedRedioButton;
	int width = 50;
	String skb;
	int height = 80;
	String id, nam;
	TextView rts;
	String gender;
	SharedPreferences sp;
	String qid;
	private String question;
	private EditText suggestKeyowrdEditText;
	private String questionid;
	private String idSignup;
	private String userid = "";
	private String username = "";
	private ImageView profileImageView;
	private ProgressDialog progressDialog;
	private QANotificationdb qaNotificationdb;
	private EditText searchEditText;
	private RelativeLayout searchLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.canswer);
		// Intent i=getIntent();
		profileImageView = (ImageView) findViewById(R.id.profileImageView);
		searchEditText = (EditText) findViewById(R.id.searchEditText);
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
		qaNotificationdb = new QANotificationdb(this);
		// newquestion = getIntent().getStringExtra("Question");
		// news.setText("" + newquestion);
		TextView use = (TextView) findViewById(R.id.user);
		// use.setText("" + user);
		suggestKeyowrdEditText = (EditText) findViewById(R.id.suggestKeyowrd);
		enterEditText = (EditText) findViewById(R.id.enterEditText);
		questionTextView = (TextView) findViewById(R.id.questionFromAnswerTextView);
		searchLayout = (RelativeLayout) findViewById(R.id.searchLayout);
		progressDialog = new ProgressDialog(this);

		question = getIntent().getStringExtra("question");
		// idSignup = getIntent().getStringExtra("idsignup");
		// userid = getIntent().getStringExtra("userid");
		// Here getting the question id from the previous activity.
		questionid = getIntent().getStringExtra("questionid");

		System.out.println("question" + question);
		// System.out.println("idsignup" + idSignup);
		// System.out.println("userid>>" + userid);
		System.out.println("questionid" + questionid);
		if (question != null) {
			questionTextView.setText(question);
			// questionUserTextVeiw.setText(questionuser);
		}

		// home.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Intent i = new Intent(Answering.this, PiTop.class);
		// startActivity(i);
		// }
		// });

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
			System.out.println("username>>>>>>>>>" + username);
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileInputStream fileIn = openFileInput("myid.txt");
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

			System.out.println("userid>>>>>>>>>>>>" + userid);
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

				View someView = findViewById(R.id.answerback);
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// use.setText("by :"+user);
		// Toast.makeText(getApplicationContext(),
		// "Here you can Compose Ur questions", Toast.LENGTH_LONG).show();

		// new GetQuestion().execute();
		rts = (TextView) findViewById(R.id.textView100000);

		fft = (TextView) findViewById(R.id.cft);

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
				uid = readStrings;

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
			String d = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				d += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();
			uid = d.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// QtyptList = new ArrayList<HashMap<String, String>>();
		// final SharedPreferences sp = getSharedPreferences("prefs", 0);
		//
		// uname = sp.getString("name","noname");
		// uid = sp.getString("id","noid" );

		ListView lv = (ListView) findViewById(R.id.lvb);

		ImageView im = (ImageView) findViewById(R.id.keyword);
		im.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showInputDialog();
			}
		});

		// initialize pop up window
		// popupWindowDogs = popupWindowDogs();
		SharedPreferences sp = getSharedPreferences("prefs", 0);

		// uname = sp.getString("name","noname");
		// uid = sp.getString("id","noid" );

		ImageView go = (ImageView) findViewById(R.id.post);
		go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				String suggestkeyword = suggestKeyowrdEditText.getText()
						.toString();
				cname = enterEditText.getText().toString();

				if (TextUtils.isEmpty(cname)) {

					toast.setText("Please type your answer to post");
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				} else {
					if (cname != null) {

						// When user press on the go button it will send answer
						// to related question.
						new AnswerPosting().execute(cname, userid, username,
								questionid, rating);
						// set the value as true that answer is posted.

						LocalModel.getInstance().setAnswerposting(true);

					}

				}

			}
		});
		/**
		 * when user type to search the it will search the result.
		 */

		searchLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (Util.isNetworkAvailable(getApplicationContext())) {
					if (searchEditText.getText().toString().length() == 0) {
						toast.setText("Please enter keyword to search");
						toast.show();

					} else {

						String inputText = searchEditText.getText().toString();
						Intent i = new Intent(Answering.this, Search.class);
						i.putExtra("Search", inputText);
						// after going to search the keyword clear the EditText
						// fields.
						searchEditText.setText("");
						startActivity(i);

					}

				} else {
					// Clear the text if internet is not ther.
					searchEditText.setText("");
					toast.setText("Please connect to internet");
					toast.show();

				}

			}
		});
		// final TextView mDoneButton = (TextView)findViewById(R.id.textView71);
		// final AlertDialog.Builder builder = new AlertDialog.Builder(this);

	}

	// // @Override;
	//
	// // protected Dialog onCreateDialog(final int ids) {
	//
	// // This will show user to select rating from rating window.
	// @SuppressWarnings("unused")
	// protected void showRatingDialog() {
	//
	// // add viewGroup to solve the problem for unfortunetly
	// // stop of application when user click on rating button.
	// // added by prasad.
	//
	// AlertDialog.Builder dialog = new AlertDialog.Builder(Answering.this);
	//
	// // Create a custom layout for the dialog box
	// LayoutInflater inflater = (LayoutInflater) Answering.this
	// .getSystemService(LAYOUT_INFLATER_SERVICE);
	// final View layout = inflater.inflate(R.layout.sams, null, false);
	// final RadioGroup genderRadioGroups = (RadioGroup) layout
	// .findViewById(R.id.radiogrouprating);
	//
	// Button sendButton = (Button) layout.findViewById(R.id.okbutton);
	//
	// dialog.setView(layout);
	// // dialog.setInverseBackgroundForced(true);
	//
	// final AlertDialog alertDialog = dialog.create();
	//
	// sendButton.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	//
	// int selectedId = genderRadioGroups.getCheckedRadioButtonId();
	//
	// // find the radiobutton by
	// // returned
	// // id
	// RadioButton selectedRadioButtons = (RadioButton) layout
	// .findViewById(selectedId);
	//
	// if (selectedRadioButtons != null) {
	//
	// rateTextView.setText("" + selectedRadioButtons.getText());
	// rating = rateTextView.getText().toString();
	//
	// } else {
	//
	// toast.setText("You have not select rating");
	// toast.show();
	// }
	// alertDialog.dismiss();
	//
	// }
	// });
	//
	// alertDialog.show();
	// // This line which use to show the fix size of window on
	// // users screen.
	// alertDialog.getWindow().setLayout(250, 700);
	//
	// try {
	// ViewGroup viewGroup1 = (ViewGroup) layout.getParent();
	// if (viewGroup1 != null) {
	// viewGroup1.setBackgroundResource(android.R.color.transparent);
	//
	// ViewGroup viewGroup2 = (ViewGroup) viewGroup1.getParent();
	// if (viewGroup2 != null) {
	// viewGroup2
	// .setBackgroundResource(android.R.color.transparent);
	// }
	// }
	// } catch (Exception e) {
	// }
	//
	// }

	protected void showInputDialog() {

		// get prompts.xml view
		LayoutInflater layoutInflater = LayoutInflater.from(Answering.this);
		View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				Answering.this);
		alertDialogBuilder.setView(promptView);

		final EditText editText = (EditText) promptView
				.findViewById(R.id.edittext);
		// setup a dialog window
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						fft.setText("" + editText.getText());
						keyword = fft.getText().toString();
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

	/**
	 * This method use to post the answer of the particular question.
	 * 
	 * @author Admin
	 * 
	 */
	private class AnswerPosting extends AsyncTask<String, Integer, Double> {

		private ProgressDialog ansprogressDialog;

		@Override
		protected void onPreExecute() {

			ansprogressDialog = new ProgressDialog(Answering.this);
			ansprogressDialog.setMessage("Please wait Answer is posting..");
			ansprogressDialog.setCancelable(false);
			ansprogressDialog.show();

		};

		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1], params[2], params[3], params[4]);
			return null;
		}

		protected void onPostExecute(Double result) {

			// store the question id to database to show the notification that
			// is this question is having new answer.
			// qaNotificationdb.inserIntoAnswerId(questionid);
			// if progressbar is showing the stop the progress bar.
			if (ansprogressDialog != null && ansprogressDialog.isShowing()) {
				ansprogressDialog.dismiss();
				// After sending the question it will Refresh the answer from
				// that question.
				Intent intent = new Intent(Answering.this, AnswersSearch.class);
				startActivity(intent);
				finish();
			}
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String ans, String kname, String id,
				String questionid, String rat) throws IllegalArgumentException {

			try {
				String compose = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/composeanswer/program%20language%20/28/baburao/2/1/5";
				System.out.println("***************" + compose);
				String compose2 = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/composeanswer/";
				// String answer = URLEncoder.encode(ans, "UTF-8").replace("+",
				// "%20");
				String answer = Util.encodeURIComponent(ans);

				compose2 += answer + "/" + kname + "/" + id + "/" + questionid
						+ "/" + rating + "/" + 1;
				System.out.println("posing answer>>>>>>>>>>>" + compose2);
				System.out.println("++++++++" + answer);
				uri = new URI(compose2.replaceAll("\\n|\\r", " "));
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

	// *This is use to filter the answer
	public String encodeURIComponent(String s) {
		String result;

		try {
			result = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20")
					.replaceAll("\\%21", "!").replaceAll("\\%27", "'")
					.replaceAll("\\%28", "(").replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~").replaceAll("\\n", " ");
		} catch (UnsupportedEncodingException e) {
			result = s;
		}

		return result;
	}

	public String urldecode(String encoded) {
		try {
			return URLDecoder.decode(encoded, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// Logger.e(e.toString());
		}
		return null;
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
