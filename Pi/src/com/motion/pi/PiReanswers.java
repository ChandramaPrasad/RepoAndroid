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
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;

public class PiReanswers extends CustomActionBar {
	// Log tag
	View formElementsView;
	View convertView;
	String skb;
	int w = 100;
	private static final String TAG = PiReanswers.class.getSimpleName();

	// Movies json url
	// private static final String url =
	// "http://api.androidhive.info/json/movies.json";
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private static final String url = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswer";
	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	static final int READ_BLOCK_SIZE = 100;
	private ListView listView;
	private CustomListAdapter adapter;
	AlertDialog ald;
	String keyword;
	ImageView pr;
	private String question;
	String wdps;
	private Button okgotitButton;
	private TextView questionTextView;
	private final int SPLASH_DISPLAY_LENGHT = 3000;
	private String username = "";
	private String questionidtype;
	private String userid = "";
	private String subject;
	private URI uri;
	private RelativeLayout postanywayLayout;
	private Toast toast = null;
	private EditText searchEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reanswers);

		Button pst = (Button) findViewById(R.id.button24);
		TextView tb = (TextView) findViewById(R.id.textView13);
		pr = (ImageView) findViewById(R.id.imageView12);
		final EditText se = (EditText) findViewById(R.id.multi);
		searchEditText = (EditText) findViewById(R.id.searchEditText);
		postanywayLayout = (RelativeLayout) findViewById(R.id.postanywayLayout);
		okgotitButton = (Button) findViewById(R.id.okgotitButton);
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
		pDialog = new ProgressDialog(this);
		// This keyword and answer getting from Kompose and Answering from both
		// class.
		// added same key for both class same because this key calling from both
		// class.
		// if any changed did it will not work for both class.

		keyword = getIntent().getStringExtra("keyword");
		question = getIntent().getStringExtra("question");
		subject = getIntent().getStringExtra("subject");
		questionTextView = (TextView) findViewById(R.id.textView3);
		if (question != null) {

			questionTextView.setText(question);
		}

		// This method use to search the question base on the keyword provided
		// by end user and give suggest to that user related question

		SearchmakeJsonArrayRequest(keyword);

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
		postanywayLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (Util.isNetworkAvailable(getApplicationContext())) {

					if (searchEditText.getText().toString().length() == 0) {

						toast.setText("Enter text to search");
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();

					} else {
						String get = searchEditText.getText().toString();

						Intent i = new Intent(PiReanswers.this, Search.class);
						i.putExtra("Search", get);
						startActivity(i);

					}

				} else {
					toast.setText("Please connect to internet");
					toast.setGravity(Gravity.CENTER, 0, 0);
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
				View someView = findViewById(R.id.rean);
				// This method use to change the background color when user
				// select from the look and feel setting.
				LookAndFeel.lookAndFeel(skb, someView);
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, movieList);
		listView.setAdapter(adapter);
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

			tb.setText("" + s);
		} catch (Exception e) {
			e.printStackTrace();
		}

		pst.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// This Asyntaks method use to post the question when user click
				// on post anyway screen.
				new PostQuestion().execute(question, userid, username, keyword,
						questionidtype, subject);

			}

		});

		okgotitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent gotitIntent = new Intent(PiReanswers.this,
						PiAnswers.class);
				startActivity(gotitIntent);
				finish();

			}
		});

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

	// Inner class of custom adapter where setting user question details.
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
			if (convertView == null)
				convertView = inflater.inflate(R.layout.rlist_row, null);

			if (imageLoader == null)
				imageLoader = AppController.getInstance().getImageLoader();
			// NetworkImageView thumbNail = (NetworkImageView) convertView
			// .findViewById(R.id.thumbnail);
			final TextView title = (TextView) convertView
					.findViewById(R.id.title);
			final TextView ques = (TextView) convertView.findViewById(R.id.Q);
			TextView quser = (TextView) convertView.findViewById(R.id.QU);
			TextView auser = (TextView) convertView.findViewById(R.id.AU);
			final TextView rats = (TextView) convertView
					.findViewById(R.id.RATT);
			Movie m = movieItems.get(position);
			// title.setText(m.getAnswerdetails());
			title.setText(m.getQuestiondetails());
			if (title != null) {

				questionTextView.setTextColor(getResources().getColor(
						R.color.green));

			} else {
				questionTextView.setTextColor(getResources().getColor(
						R.color.Yellow));
				// Line added to solve the problem when question answer
				// available then dislplay screen otherwise directly question
				// will be posted and go to homepage.
				Intent homePageIntent = new Intent(PiReanswers.this,
						PiAnswers.class);
				startActivity(homePageIntent);
				finish();
			}
			rats.setText(String.valueOf(m.getRatingtypeid()));
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					String question = title.getText().toString();
					Intent intent = new Intent(PiReanswers.this,
							QuestionAndAnswer.class);

					// sending question to display on the next activity that is
					// QuestionAndAnswer activity.
					intent.putExtra("question", question);

					startActivity(intent);
				}
			});

			return convertView;
		}

	}

	private class PostQuestion extends AsyncTask<String, Integer, Double> {
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {

			System.out.println("on pre execute");
			progressDialog = new ProgressDialog(PiReanswers.this);
			progressDialog.setMessage("Please wait question is posting..");
			progressDialog.setCancelable(false);
			progressDialog.show();

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

				Intent i = new Intent(PiReanswers.this, PiAnswers.class);
				startActivity(i);
				finish();
			}

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String question, String userid,
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
				String characterQuestion;
				try {
					// if the question having special charaters then it will
					// convert and send on server.
					characterQuestion = Util.encodeURIComponent(question);
					basurl3 += characterQuestion + "/" + userid + "/"
							+ questionusername + "/" + keyword + "/" + "Test"
							+ "/" + 1;
					System.out.println("***************" + basurl3);
					// System.out.println("****************"+Sample);

					uri = new URI(basurl3.replace(" ", "%20"));
					System.out.println("**************uri" + uri);

					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(uri);

					HttpResponse responses = httpClient.execute(httpGet);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	protected void SearchmakeJsonArrayRequest(final String keyword) {

		String finalurl = null;

		String url = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswerforkey/";
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
								movie.setRatingtypeid(obj
										.getLong("ratingtypevalue"));
								movie.setAnsuserid(obj.getLong("ansuserid"));
								JSONObject qdetails = obj
										.getJSONObject("questionid");

								movie.setQuestiondetails(qdetails
										.getString("questiondetails"));
								System.out.print("obj2:"
										+ obj.getString("questionid"));

								movieList.add(movie);

							}

							hidePDialog();
							// sending joblist List to notifiaction method to
							// show notification when launch the application.
							adapter.notifyDataSetChanged();

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

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		movieList.clear();
	}

}
