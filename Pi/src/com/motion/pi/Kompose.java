package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.LocalModel;
import info.androidhive.customlistviewvolley.model.Movie;
import info.androidhive.customlistviewvolley.util.LookAndFeel;
import info.androidhive.customlistviewvolley.util.Util;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;

/**
 * Created by Admin on 28-08-2015.
 */
public class Kompose extends CustomActionBar {
	JSONObject jsonObj = null;
	private ProgressDialog pDialog;
	String response;
	ListView dialogs;
	// LazyAdapter adapter;
	String KEY_TEXT = "TEXTPSS";
	static final int CUSTOM_ID = 0;

	ConnectionDetector cd;
	private static String url = "http://166.62.81.118:18080/SpringRestCrud/question/questionlist";
	Boolean isInternetPresent = false;
	// JSON Node names
	// private static final String TAG_CONTACTS = "";

	private static String urls = "http://166.62.81.118:18080/SpringRestCrud/question/getQuestionTypeList";
	private static final String TAG_CONTACTS = "";
	private static final String QID = "idqtype";
	final String TAG = "States";
	String QNAME = "qtype";
	String kuid;
	String uname;
	ArrayList<HashMap<String, String>> QtyptList;
	ArrayList<HashMap<String, String>> contactList;
	public final int CATEGORY_ID = 1;
	private Context mContext;
	// Dialog dialog;
	String KEY_TEXTPSS = "TEXTPSS";
	static final int CUSTOM_DIALOG_ID = 0;
	Drawable d;
	ImageAdapter imges;
	EditText questionEditText;
	private String skb;
	ListView dialog_ListView;
	private List<Movie> movieList;
	private boolean isArrayEmpty = false;
	String popUpContents[];
	PopupWindow popupWindowDogs;
	private Toast toast = null;
	int index;
	// private List<FeedItem> feedItems;
	ImageView mDoneButton;
	URI uri;
	String[] items = { "Paper I (Preliminary)", "Paper II (Preliminary)",
			"General Studies I  (Mains)", "General Studies II  (Mains)",
			"General Studies III  (Mains)", "General Studies IV  (Mains)",
			"Agriculture", "Animal Husbandry and Veterinary Science ",
			"Anthropology ", "Botany", "Chemistry", "Computer",
			"Civil Engineering", "Commerce and Accountancy", "Economics",
			"Electrical Engineering", "Geography", "Geology", "History",
			"Law ", "Management", "Mathematics", "Mechanical Engineering",
			"Medical Science ", "Philosophy", "Physics",
			"Political Science and International Relations", "Psychology",
			"Public Administration", "Sociology", "Statistics ", "Zoology ",
			"Other" };

	public Integer[] mThumbIds = { R.drawable.ba1, R.drawable.ba2,
			R.drawable.ba3, R.drawable.ba6, R.drawable.ba5, R.drawable.ba4,
			R.drawable.ba7, R.drawable.ba8, R.drawable.ba9, R.drawable.ba10,
			R.drawable.ba11, R.drawable.ba12, R.drawable.ba13, R.drawable.ba14,
			R.drawable.ba15, R.drawable.ba16, R.drawable.ba17, R.drawable.ba18,
			R.drawable.ba19, R.drawable.ba20, R.drawable.ba21, R.drawable.ba22,
			R.drawable.ba23, R.drawable.ba24, R.drawable.ba25, R.drawable.ba27,
			R.drawable.ba28, R.drawable.g1, R.drawable.g2, R.drawable.g3,
			R.drawable.g4, R.drawable.g5, R.drawable.g6, R.drawable.g7,
			R.drawable.g8, R.drawable.g9, R.drawable.g10, R.drawable.g11,
			R.drawable.g12, R.drawable.g13, R.drawable.g14, R.drawable.g15,
			R.drawable.g16, R.drawable.g17, R.drawable.g18, R.drawable.g20,
			R.drawable.g21, R.drawable.g22, R.drawable.g23, R.drawable.g24,
			R.drawable.g25, R.drawable.g26, R.drawable.g27, R.drawable.g28,
			R.drawable.g29, R.drawable.g30, R.drawable.g31, R.drawable.g32,
			R.drawable.g33, R.drawable.g34, R.drawable.l8, R.drawable.l2,
			R.drawable.l3, R.drawable.l4, R.drawable.l5, R.drawable.l6,
			R.drawable.l7, R.drawable.l10, R.drawable.l11, R.drawable.l14,
			R.drawable.l15, R.drawable.l16, R.drawable.l17, R.drawable.l18,
			R.drawable.l19, R.drawable.l20, R.drawable.l21, R.drawable.l22,
			R.drawable.l23, R.drawable.l24, R.drawable.l25, R.drawable.l26,
			R.drawable.l27, R.drawable.l28, R.drawable.l29, R.drawable.l30,
			R.drawable.l31, R.drawable.l32, R.drawable.l35, R.drawable.l34,
			R.drawable.l36, R.drawable.l37, R.drawable.l38, R.drawable.l39,
			R.drawable.l40, R.drawable.l41, R.drawable.m1, R.drawable.m2,
			R.drawable.m3, R.drawable.m4, R.drawable.m5, R.drawable.m6,
			R.drawable.m7, R.drawable.m8, R.drawable.m9, R.drawable.m10,
			R.drawable.m11, R.drawable.m12, R.drawable.m13, R.drawable.m14,
			R.drawable.m15, R.drawable.m17, R.drawable.m18, R.drawable.m19,
			R.drawable.m20, R.drawable.m21, R.drawable.m22, R.drawable.m23,
			R.drawable.m24, R.drawable.m25, R.drawable.m26, R.drawable.m27,
			R.drawable.m28, R.drawable.m29, R.drawable.m30, R.drawable.m31,
			R.drawable.r1, R.drawable.r2, R.drawable.r3, R.drawable.r4,
			R.drawable.r5, R.drawable.r6, R.drawable.r8, R.drawable.r9,
			R.drawable.r10, R.drawable.r11, R.drawable.r14, R.drawable.r13,
			R.drawable.r15, R.drawable.r16, R.drawable.r17, R.drawable.r18,
			R.drawable.r19, R.drawable.r20, R.drawable.r22, R.drawable.r23,
			R.drawable.r24, R.drawable.r25, R.drawable.r26, R.drawable.r27,
			R.drawable.r28, R.drawable.r29, R.drawable.r30, R.drawable.r31,
			R.drawable.r32, R.drawable.r33, R.drawable.r34, R.drawable.r35,
			R.drawable.r37, R.drawable.r38, R.drawable.r39, R.drawable.r40,
			R.drawable.r41, R.drawable.r42, R.drawable.s1, R.drawable.s2,
			R.drawable.s3, R.drawable.s4, R.drawable.s5, R.drawable.s6,
			R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
			R.drawable.s11, R.drawable.s12, R.drawable.s13, R.drawable.s14,
			R.drawable.s15, R.drawable.s16, R.drawable.s14, R.drawable.s15,
			R.drawable.s16, R.drawable.s17, R.drawable.s18, R.drawable.s19,
			R.drawable.s20, R.drawable.s21, R.drawable.s22, R.drawable.s23,
			R.drawable.s24, R.drawable.s25, R.drawable.s26, R.drawable.s27,
			R.drawable.s28, R.drawable.s29, R.drawable.s30, R.drawable.s31,
			R.drawable.s32, R.drawable.s33, R.drawable.s34, R.drawable.s35,
			R.drawable.s36, R.drawable.s37, R.drawable.s38, R.drawable.s39,
			R.drawable.s40, R.drawable.s42, R.drawable.s43, R.drawable.s44,

	};
	// EditText editText;
	String keyword;
	TextView resultText;
	EditText sedit;
	String questionDeatils;
	TextView mainly;
	String uu;
	SharedPreferences sps;
	TextView det;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	String id, nam;
	EditText mainlys;
	String kname;
	static final int READ_BLOCK_SIZE = 100;
	String kid;
	String wdps;
	String txts;
	ImageView prf;
	String currentTime = null;
	private String questionusername;
	private String questionidtype;
	private String userid;
	private String username = "";
	private boolean isKeywordfound = false;
	private TextView subject;
	private String subjectfall = "";
	private ImageView refreshButton;
	private LinearLayout myquestinLinearLayout;
	private TextView keywordTextView;
	private String question;
	private RelativeLayout composeSearchLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compose_question_layout);
		// Alert dialog manager
		/* SharedPreferences: */
		kname = getIntent().getStringExtra("name");
		kid = getIntent().getStringExtra("id");
		TextView names = (TextView) findViewById(R.id.sname);
		refreshButton = (ImageView) findViewById(R.id.refreshButton);
		myquestinLinearLayout = (LinearLayout) findViewById(R.id.myquestinLinearLayout);
		keywordTextView = (TextView) findViewById(R.id.keywordTextView);
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

		subject = (TextView) findViewById(R.id.textView2);
		questionEditText = (EditText) findViewById(R.id.editEmojicon);
		composeSearchLayout = (RelativeLayout) findViewById(R.id.composeSearchLayout);

		questionEditText.setSelection(0);
		movieList = new ArrayList<Movie>();
		pDialog = new ProgressDialog(this);
		// ImageView del = (ImageView) findViewById(R.id.delete);

		// getting all the details from the questionusrname,questionid and
		// userid.
		questionusername = getIntent().getStringExtra("questionusername");
		questionidtype = getIntent().getStringExtra("questiontypeid");
		userid = getIntent().getStringExtra("userid");

		currentTime = getCurrentTime();

		try {
			FileInputStream fileIn = openFileInput("lastname.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];

			String ds = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				wdps += readstring;
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

			if (InputRead != null) {

				while ((charRead = InputRead.read(inputBuffer)) > 0) {
					// char to string conversion

					String readStrings = String.copyValueOf(inputBuffer, 0,
							charRead);
					skb += readStrings;
					View someView = findViewById(R.id.composeback);
					LookAndFeel.lookAndFeel(skb, someView);

				}

			}

			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// del.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Intent i = new Intent(Kompose.this, Kompose.class);
		// startActivity(i);
		// finish();
		// }
		// });
		sedit = (EditText) findViewById(R.id.multiss);

		composeSearchLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String get = sedit.getText().toString();
				if (get.equals("")) {

					toast.setText("Please Enter Keyword To Search");
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();

				} else {
					Intent i = new Intent(Kompose.this, Search.class);
					i.putExtra("Search", get);
					startActivity(i);

				}

				// new seartask().execute(get);
				// new seartask().execute(get);

			}
		});
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
				d += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();
			names.setText("" + s);
			uname = names.getText().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

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

		System.out.println("++++++++++++" + kuid);
		System.out.println("++++++++++++" + uname);

		refreshButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				questionEditText.setText("");

			}
		});

		// AlertDialogManager alert = new AlertDialogManager();
		// Toast.makeText(getApplicationContext(),
		// "Here you can Compose Ur questions", Toast.LENGTH_LONG).show();

		// new GetQuestion().execute();
		// QtyptList = new ArrayList<HashMap<String, String>>();

		// names.setText("" + kname);

		ListView lv = (ListView) findViewById(R.id.lvb);
		// EditText cet = (EditText)findViewById(R.id.editEmojicon);
		// cname = cet.getText().toString();
		ImageView button = (ImageView) findViewById(R.id.tool);
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				showDialog(CATEGORY_ID);
			}
		});

		myquestinLinearLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// showInputDialog();
				LayoutInflater inflater = LayoutInflater.from(Kompose.this);
				View myview = inflater.inflate(R.layout.input_dialog, null);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						Kompose.this);
				alertDialogBuilder.setView(myview);
				final EditText editTexts = (EditText) myview
						.findViewById(R.id.edittext);
				if (!keywordTextView.getText().toString().equalsIgnoreCase("")) {

					editTexts.setText(keywordTextView.getText().toString());

				}

				// setup a dialog window
				alertDialogBuilder
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										txts = editTexts.getText().toString();
										keywordTextView.setText("" + txts);

									}
								})
						.setNegativeButton("Cancel",
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

		// initialize pop up window
		// popupWindowDogs = popupWindowDogs();

		mDoneButton = (ImageView) findViewById(R.id.fals);
		ImageView go = (ImageView) findViewById(R.id.button55);

		// Check if Internet present
		// if (!cd.isConnectingToInternet()) {
		// // Internet Connection is not present
		// alert.showAlertDialog(Kompose.this,
		// "Internet Connection Error",
		// "Please connect to working Internet connection", false);
		// // stop executing code by return
		// return;
		// }

		// when user click on the fall in categories button then this alert
		// dialog box will open to show user to select categories,
		mDoneButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// show the list view as dropdown
				// popupWindowDogs.showAsDropDown(v, -5, 0);

				AlertDialog.Builder alert = new AlertDialog.Builder(
						Kompose.this);
				// get prompts.xml view
				// LayoutInflater layoutInflater =
				// LayoutInflater.from(Kompose.this);
				// View promptView = layoutInflater.inflate(R.layout.statics,
				// null);
				// // promptView
				// int width =
				// getResources().getDimensionPixelSize(R.dimen.icon_width);
				// int height =
				// getResources().getDimensionPixelSize(R.dimen.icon_height);

				// alert.setMessage("Sample alert dialog from http://www.android-codes-examples.blogspot.com");
				// alert.setIcon(R.drawable.icon);

				alert.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {

						Toast.makeText(getApplicationContext(), items[item],
								Toast.LENGTH_SHORT).show();
						subject.setText("" + items[item]);
						subjectfall = subject.getText().toString();
						if (subject.getText().equals("Other")) {
							LayoutInflater inflater = LayoutInflater
									.from(Kompose.this);
							View myview = inflater.inflate(
									R.layout.subject_dialog, null);
							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
									Kompose.this);
							alertDialogBuilder.setView(myview);
							final EditText editTexts = (EditText) myview
									.findViewById(R.id.edittext);

							// setup a dialog window
							alertDialogBuilder
									.setCancelable(false)
									.setPositiveButton(
											"OK",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int id) {
													String get = editTexts
															.getText()
															.toString();
													subject.setText("" + get);

													// mainlys.setText(""+editText);
													// String txts =
													// editTexts.getText().toString();
													// mainlys.setText(""+txts);
													// Toast.makeText(getApplicationContext(),
													// txts,
													// Toast.LENGTH_SHORT).show();
													// resultText.setText("" +
													// editText.getText());
													// mainlys.setText(""+
													// editText.getText());
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
							AlertDialog alert = alertDialogBuilder.create();
							alert.show();

						}
					}
				});

				alert.show();

			}
		});

		// When user click on the go button will go to next screen to post the
		// question.
		go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (questionEditText.getText().toString().length() < 1) {
					toast.setText("please enter Question");
					toast.show();

				} else if (keywordTextView.getText().toString().length() < 1) {
					toast.setText("please enter Keyword");
					toast.show();
				} else {

					keyword = keywordTextView.getText().toString();
					//
					// try {
					// question = URLEncoder.encode(
					// questionEditText.getText().toString(), "UTF-8")
					// .replace("+", "%20");
					// System.out.println("question>>>>" + question);
					// } catch (UnsupportedEncodingException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }

					question = questionEditText.getText().toString();

					if (!TextUtils.isEmpty(keyword)
							&& !TextUtils.isEmpty(question)) {

						makeJsonArrayRequest(keyword);
					}

				}

			}

		});
	}

	protected void makeJsonArrayRequest(final String keyword) {

		String finalurl = null;

		url = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswerforkey/";
		url += keyword;
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

		pDialog.setMessage("Please Wait..");
		pDialog.setCancelable(false);
		pDialog.show();
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
								movie.setQuestiondetails(qdetails
										.getString("questiondetails"));
								movie.setAboutmyquestion(qdetails
										.getString("aboutmyquestion"));
								movie.setAnsusername(obj
										.getString("ansusername"));
								System.out.print("obj2:"
										+ obj.getString("questionid"));
								// JSONArray genreArry = obj.getJSONArray();
								//

								// JSONObject obj2 =
								// genreArry.getJSONObject(1);

								// System.out.print("obj2:"+obj2.toString());
								// adding movie to movies array
								movieList.add(movie);
							}
							// sending joblist List to notifiaction method to
							// show notification when launch the application.
							hidePDialog();
							chcekKeyword(movieList);

						} catch (Exception e) {
							e.printStackTrace();
							Toast.makeText(getApplicationContext(),
									"Error: " + e.getMessage(),
									Toast.LENGTH_LONG).show();
							hidePDialog();
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						Toast.makeText(getApplicationContext(),
								error.getMessage(), Toast.LENGTH_SHORT).show();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);

	}

	Dialog dialog = null;

	// protected void showInputDialog() {
	//
	// // get prompts.xml view
	// LayoutInflater layoutInflater = LayoutInflater.from(Kompose.this);
	// View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
	// AlertDialog.Builder alertDialogBuilder = new
	// AlertDialog.Builder(Kompose.this);
	// alertDialogBuilder.setView(promptView);
	//
	// final EditText editText = (EditText)
	// promptView.findViewById(R.id.edittext);
	// mainlys.setText(""+editText);
	// final String keyword = mainlys.getText().toString();
	// // setup a dialog window
	// alertDialogBuilder.setCancelable(false)
	// .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int id) {
	//
	// // Toast.makeText(getApplicationContext(), ""+keyword,
	// Toast.LENGTH_SHORT).show();
	// // resultText.setText("" + editText.getText());
	// // mainlys.setText(""+ editText.getText());
	// }
	// })
	// .setNegativeButton("Cancel",
	// new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int id) {
	// dialog.cancel();
	// }
	// });
	//
	// // create an alert dialog
	// AlertDialog alert = alertDialogBuilder.create();
	// alert.show();
	// }
	// go.setOnClickListener(new View.OnClickListener() {
	// // @Override
	// public void onClick(View view) {
	//
	// if ((mainlys.getText().toString().length()<1))
	// {
	// Toast.makeText(getApplicationContext(), "please enter Keyword",
	// 9000).show();
	//
	// }else {
	// cname = edt.getText().toString();
	// keyword = mainlys.getText().toString();
	//
	// new MyAsyncTask().execute(cname,kuid,uname,keyword);
	// Intent i = new Intent(Kompose.this, Delated.class);
	// startActivity(i);
	// }
	//
	// }
	// });
	// @Override
	// public void onClick(View v) {

	// }

	// This method will call when user click on the tools button to get image
	// from given images.
	protected Dialog onCreateDialog(final int iid) {

		switch (iid) {

		case CATEGORY_ID:

			AlertDialog.Builder builder;
			Context mContext = this;
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.mygrid,
					(ViewGroup) findViewById(R.id.layout_root));
			GridView gridview = (GridView) layout.findViewById(R.id.gridviews);
			gridview.setAdapter(new ImageAdapter(this));

			gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						final int position, long arg3) {
					// TODO Auto-generated method stub
					// Toast.makeText(arg1.getContext(), "Position is " +
					// position, Toast.LENGTH_LONG).show();
					try {

						Html.ImageGetter imageGetter = new Html.ImageGetter() {

							public Drawable getDrawable(String source) {
								StringTokenizer st = new StringTokenizer(String
										.valueOf(index), ".");
								// d = new BitmapDrawable(getResources(),
								// emoticons[Integer.parseInt(st.nextToken())]);
								Drawable d = getResources().getDrawable(
										mThumbIds[position]);
								d.setBounds(0, 0, d.getIntrinsicWidth(),
										d.getIntrinsicHeight());

								return d;
							}
						};

						Spanned cs = Html.fromHtml(
								"<img src ='" + String.valueOf(index) + "'/>",
								imageGetter, null);

						int cursorPosition = questionEditText
								.getSelectionStart();

						questionEditText.getText().insert(cursorPosition, cs);
					} catch (Exception e) {
						e.getMessage();
					}

				}
			});

			ImageView close = (ImageView) layout.findViewById(R.id.close);
			close.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

			builder = new AlertDialog.Builder(mContext);
			builder.setView(layout);
			dialog = builder.create();
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			WindowManager.LayoutParams wmlp = dialog.getWindow()
					.getAttributes();

			wmlp.gravity = Gravity.BOTTOM | Gravity.CENTER;
			// wmlp.x = 30; // x position
			wmlp.y = 68; // y position

			dialog.show();
			break;
		default:
			dialog = null;

		}

		return dialog;

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);

		String stateSaved = savedInstanceState.getString("saved_state");

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		String stateToSave = questionEditText.getText().toString();
		outState.putString("saved_state", stateToSave);
	}

	public class ImageAdapter extends BaseAdapter {
		private Context mContext;
		private LayoutInflater mInflater;

		public ImageAdapter(Context c) {
			mInflater = LayoutInflater.from(c);
			mContext = c;
		}

		public int getCount() {
			return mThumbIds.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// create a new ImageView for each item referenced by the
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) { // if it's not recycled,
				convertView = mInflater.inflate(R.layout.customs, null);
				convertView.setLayoutParams(new GridView.LayoutParams(80, 80));
				holder = new ViewHolder();
				// holder.title = (TextView)
				// convertView.findViewById(R.id.text);
				holder.icon = (ImageView) convertView.findViewById(R.id.image);
				// convertView.setOnClickListener(new View.OnClickListener() {
				// @Override
				// public void onClick(View view) {
				// Toast.makeText(arg1.getContext(), "Position is " + position,
				// Toast.LENGTH_LONG).show();
				// }
				// });

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.icon.setAdjustViewBounds(true);
			holder.icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
			holder.icon.setPadding(5, 5, 5, 5);
			// holder.title.setText(categoryContent[position]);
			holder.icon.setImageResource(mThumbIds[position]);
			return convertView;
		}

		class ViewHolder {
			TextView title;
			ImageView icon;
		}
		// references to our images

	}

	CharSequence cs;
	// int index;
	ImageAdapter imagss;

	// @Override
	// protected void onRestart() {
	// // TODO Auto-generated method stub
	// super.onRestart();
	// imagss = new ImageAdapter(this);
	// @SuppressWarnings("static-access")
	// SharedPreferences myPrefs = this.getSharedPreferences("myPrefs",
	// this.MODE_WORLD_READABLE);
	// index = myPrefs.getInt("key1", 0);
	// System.out.println("Pref Data index is:- " + index);
	// Html.ImageGetter imageGetter = new Html.ImageGetter() {
	// @Override
	// public Drawable getDrawable(String s) {
	// return null;
	// }
	//
	// };
	// cs = Html.fromHtml(
	// "<img src='"
	// + getResources()
	// .getDrawable(mThumbIds[index])
	// + "'/>", imageGetter, null);
	// System.out.println("cs is:- " + cs);
	// edt.setText(cs);
	// }

	// private void addImageBetweentext(Drawable drawable) {
	// drawable .setBounds(0, 0, drawable.getIntrinsicWidth(),
	// drawable.getIntrinsicHeight());
	//
	// int selectionCursor = edt.getSelectionStart();
	// edt.getText().insert(selectionCursor, ".");
	// selectionCursor = edt.getSelectionStart();
	//
	// SpannableStringBuilder builder = new
	// SpannableStringBuilder(edt.getText());
	// builder.setSpan(new ImageSpan(drawable), selectionCursor - ".".length(),
	// selectionCursor, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	// edt.setText(builder);
	// edt.setSelection(selectionCursor);
	// }
	Bitmap bitmap;

	/***
	 * This class is use to send to question to server.
	 * 
	 * @author Admin
	 * 
	 */
	private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {

			progressDialog = new ProgressDialog(Kompose.this);
			progressDialog.setMessage("Please wait question is posting..");
			progressDialog.setCancelable(false);
			progressDialog.show();

			System.out.println("on pre execute");

		};

		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1], params[2], params[3], params[4]);
			return null;
		}

		protected void onPostExecute(Double result) {

			LocalModel.getInstance().setNewQuestionPosted(true);

			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();
				Intent homepageIntent = new Intent(Kompose.this,
						PiAnswers.class);
				startActivity(homepageIntent);
				finish();
			}
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String questions, String userid,
				String questionusername, String keyword, String subject)
				throws IllegalArgumentException {

			try {
				// String kompose =
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/{questiondetails}/{userid}/{username}/{aboutmyquestion}/{qtypeid}";
				// String Sample=
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/what%20is%20loop%20statement/30/Kavya/c%20language/5";
				String basurl2 = "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/wahat%20is%20processor/70/ajay/hi/1";
				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/";
				// System.out.println("***************"+basurl2);
				try {
					// If the question having with special charaters the it will
					// convert and send to the server.

					String question = Util.encodeURIComponent(questions);
					basurl3 += question + "/" + userid + "/" + questionusername
							+ "/" + keyword + "/" + "Test" + "/" + 1;
					// uri = new URI(basurl3.replace(" ", "%20"));
					uri = new URI(basurl3);
					System.out.println("**************uri" + uri);

					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(uri);

					HttpResponse responses = httpClient.execute(httpGet);
					System.out.println("***************" + basurl3);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// System.out.println("****************"+Sample);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// This method use to get current system data and time.

	private String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("kkmmss");
		String currentTime = dateFormat.format(System.currentTimeMillis());
		return currentTime;

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Changes 'back' button action
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				finish();

				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	// This method use to check weather record present in database or not.
	protected void chcekKeyword(List<Movie> movieList) {

		for (int i = 0; i < movieList.size(); i++) {

			String keywordfromserver = movieList.get(i).getAboutmyquestion()
					.toString().trim();

			if (keywordfromserver.equalsIgnoreCase(keyword)) {

				isKeywordfound = true;

			}
		}
		// Here check the condition weather keyword found or not.
		if (isKeywordfound) {

			// if the keyword is found then it will go to next screen that
			// is PiReanswer screen to show related question of that
			// keywords and from here question will not post will ask on
			// another screen to post.
			Intent piReanswerIntent = new Intent(Kompose.this,
					PiReanswers.class);
			piReanswerIntent.putExtra("question", question);
			piReanswerIntent.putExtra("keyword", txts);
			piReanswerIntent.putExtra("subject", subjectfall);

			startActivity(piReanswerIntent);

		} else {

			// if result will not found then that question
			// will directly go to homepage.
			new MyAsyncTask().execute(question, kuid, username, keyword,
					subjectfall);

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

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
		}
	}

}
