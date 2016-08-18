package com.motion.notification.queationanswer;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.AnswerNotificationDao;
import info.androidhive.customlistviewvolley.model.LocalModel;
import info.androidhive.customlistviewvolley.model.Movie;
import info.androidhive.customlistviewvolley.util.LookAndFeel;
import info.androidhive.customlistviewvolley.util.QANotificationdb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.motion.actionbar.CustomActionBar;
import com.motion.pi.R;
import com.motion.pi.ServiceHandler;

public class AnswerNotificationActivity extends CustomActionBar {
	static final int READ_BLOCK_SIZE = 100;
	private String skb = "";
	private ArrayList<AnswerNotificationDao> answernotiArrayList;
	private ProgressDialog progressDialog;
	private AnswerNotificationAdapter answerNotificationAdapter;
	private ListView ansListView;
	private ArrayList<AnswerNotificationDao> questionid;
	private QANotificationdb qaNotificationdb;
	private List<Movie> movieList = new ArrayList<Movie>();
	private URI uri;
	private Toast toast = null;
	private View view;
	private TextView text;
	private String question;
	private String questionids;
	private TextView questionTextView;
	private String ansUrl = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswerforkey/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_notification);
		progressDialog = new ProgressDialog(this);
		ansListView = (ListView) findViewById(R.id.ansListView);
		qaNotificationdb = new QANotificationdb(this);
		questionid = new ArrayList<AnswerNotificationDao>();
		answernotiArrayList = new ArrayList<AnswerNotificationDao>();

		questionTextView = (TextView) findViewById(R.id.idquestionTextView);
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
		question = getIntent().getStringExtra("question");

		// Here getting all the notification which user not read till now.
		Cursor answeridCursor = qaNotificationdb.getAllAnsId();
		if (answeridCursor != null) {

			if (answeridCursor.moveToFirst()) {
				do {

					AnswerNotificationDao answerNotificationDao = new AnswerNotificationDao();
					answerNotificationDao.setIdanswer(answeridCursor
							.getLong(answeridCursor.getColumnIndex("ansid")));
					answerNotificationDao.setIdquestion(answeridCursor
							.getLong(answeridCursor
									.getColumnIndex("questionidss")));

					questionid.add(answerNotificationDao);

				} while (answeridCursor.moveToNext());
			}

			answeridCursor.close();

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
				View someView = findViewById(R.id.ansnotificatioback);
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (question != null) {

			questionTextView.setText(question);

			makeQuestioAnswerRequest(question);

			answerNotificationAdapter = new AnswerNotificationAdapter();

			ansListView.setAdapter(answerNotificationAdapter);

		}

		/**
		 * when user click on the listview then it will read as check of answer
		 * listview
		 */
		ansListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				String ansid = String.valueOf(movieList.get(position)
						.getIdanswer());

				System.out.println("answerid>>>" + ansid);

				/**
				 * THis asyntask use to read the answer status when user click
				 * on the listview.
				 */
				new ChangeReadStatus().execute(ansid);

			}
		});

	}

	/**
	 * This class use to show the custom notification which are not read by the
	 * users.
	 * 
	 * @author Admin
	 * 
	 */
	private class AnswerNotificationAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return movieList.size();
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

				convertView = LayoutInflater
						.from(getApplicationContext())
						.inflate(R.layout.answer_notification_item_layout, null);

			}

			TextView ansnotiTextView = (TextView) convertView
					.findViewById(R.id.ansnotificationTextView);
			final TextView ansidTextView = (TextView) convertView
					.findViewById(R.id.ansIdTextView);
			TextView quesTextView = (TextView) convertView
					.findViewById(R.id.quesIdTextView);
			ansidTextView.setText(String.valueOf(movieList.get(position)
					.getIdanswer()));
			quesTextView.setText(String.valueOf(movieList.get(position)
					.getIdquestion()));

			ansnotiTextView.setText(movieList.get(position).getAnswerdetails()
					.toString());

			return convertView;
		}
	}

	// This asyntaks use to update the question by own user.
	private class ChangeReadStatus extends AsyncTask<String, Integer, Double> {

		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(AnswerNotificationActivity.this);
			progressDialog.setMessage("You read answer..");
			progressDialog.setCancelable(false);
			progressDialog.show();

		};

		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0]);
			return null;
		}

		protected void onPostExecute(Double result) {

			if (progressDialog != null && progressDialog.isShowing()) {

				/**
				 * To Refresh the notification after user get read the
				 * notification.
				 */
				Intent intent = new Intent(AnswerNotificationActivity.this,
						QuestionNotificationActivity.class);
				startActivity(intent);
				finish();
				progressDialog.dismiss();

			}

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String answerid) throws IllegalArgumentException {

			try {

				/**
				 * http://166.62.81.118:18080/SpringRestCrud/questionanswer/
				 * updateunreadans/230
				 */
				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/updateunreadans/";
				// System.out.println("***************"+basurl2);
				basurl3 += answerid;
				System.out.println("***************" + basurl3);
				// System.out.println("****************"+Sample);

				uri = new URI(basurl3.replace(" ", "%20"));
				System.out.println("**************uri" + uri);

			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
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
	 * when user open the notification then it will show to user as notification
	 * list.
	 * 
	 * @param question
	 */
	protected void makeQuestioAnswerRequest(final String question) {

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

						try {
							// Parsing json array response
							// loop through each json object
							for (int i = 0; i < response.length(); i++) {

								JSONObject obj = response.getJSONObject(i);
								Movie movie = new Movie();
								movie.setAnswerdetails(obj
										.getString("answerdetails"));

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

							System.out.println("Response>>>" + movieList);

							answerNotificationAdapter.notifyDataSetChanged();

						} catch (Exception e) {
							e.printStackTrace();

						}

					}

				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);

	}
}
