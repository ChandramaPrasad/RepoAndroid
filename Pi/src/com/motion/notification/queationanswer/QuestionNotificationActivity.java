package com.motion.notification.queationanswer;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.AnsNotificationCount;
import info.androidhive.customlistviewvolley.model.Question;
import info.androidhive.customlistviewvolley.util.CustomToast;
import info.androidhive.customlistviewvolley.util.LookAndFeel;
import info.androidhive.customlistviewvolley.util.QANotificationdb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.motion.actionbar.CustomActionBar;
import com.motion.adapters.AnswerNotificationAdapter;
import com.motion.pi.R;
import com.motion.pi.ServiceHandler;

public class QuestionNotificationActivity extends CustomActionBar {

	static final int READ_BLOCK_SIZE = 100;
	private String skb = "";
	private ProgressDialog progressDialog;
	private String userid = "";
	private ArrayList<String> questionnotification;
	private QANotificationdb qaNotificationdb;
	private ArrayList<Question> questionArraylist;
	private ListView notificationListVeiw;
	private QuestionNotificationAdapter questionNotificationAdapter;
	private Toast toast = null;
	private View view;
	private TextView text;
	private String question;
	String questionid;
	private ListView ansListView;
	private AnswerNotificationAdapter answerNotificationAdapter;
	private String urlans = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getnewanswercount/";
	private ArrayList<AnsNotificationCount> ansNotificationCountArrayList;

	private URI uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_notification);

		qaNotificationdb = new QANotificationdb(this);

		questionnotification = new ArrayList<String>();

		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

		notificationListVeiw = (ListView) findViewById(R.id.notificationListVeiw);

		ansListView = (ListView) findViewById(R.id.ansNotificationListView);

		questionNotificationAdapter = new QuestionNotificationAdapter();

		questionArraylist = new ArrayList<Question>();

		ansNotificationCountArrayList = new ArrayList<AnsNotificationCount>();

		progressDialog = new ProgressDialog(this);

		if (questionArraylist != null) {

			questionArraylist.clear();

		}

		// Here getting all the notification which user not read till now.
		Cursor questionidCursur = qaNotificationdb.getAllQuestionId();
		if (questionidCursur != null) {

			if (questionidCursur.moveToFirst()) {
				do {

					questionnotification.add(questionidCursur
							.getString(questionidCursur
									.getColumnIndex("questionid")));

				} while (questionidCursur.moveToNext());
			}

			questionidCursur.close();

		}

		System.out.println("questionids>>>>>>" + questionnotification);

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
				View someView = findViewById(R.id.questionback);
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

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
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		makeRequestForAllNewQUestions(userid);

		/**
		 * This method use to get the notification details when user click on
		 * answer.
		 */
		new GetAnswerNotificationDetails().execute();

		/**
		 * This is use to set the adapter to listivew.
		 */

		notificationListVeiw.setAdapter(questionNotificationAdapter);

		notificationListVeiw.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				questionid = String.valueOf(questionArraylist.get(position)
						.getIdquestion());

				question = questionArraylist.get(position).getQuestiondetails()
						.toString();

				new ChangeReadStatus().execute(questionid, userid);

				questionArraylist.remove(position);
				questionNotificationAdapter.notifyDataSetChanged();

				// it will deleted the question id from the database once
				// user read that question.
				qaNotificationdb.deleteQuestionId(questionid);

			}
		});

		/**
		 * when user click on the anwser notification then it will get the
		 * notification.
		 */
		ansListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				String question = ansNotificationCountArrayList.get(position)
						.getQuestiondetails().toString();

				/**
				 * finish activity after send the details to answernotification
				 * activity.
				 */
				Intent intent = new Intent(QuestionNotificationActivity.this,
						AnswerNotificationActivity.class);
				intent.putExtra("question", question);
				startActivity(intent);
				finish();

			}
		});
	}

	/**
	 * This method use to get the all new question notification which posted by
	 * other users.
	 */
	private void makeRequestForAllNewQUestions(String userid) {
		// TODO Auto-generated method stub

		progressDialog.setMessage("Loading questions..");
		progressDialog.setCancelable(false);
		progressDialog.show();

		String urljsonArry = "http://166.62.81.118:18080/SpringRestCrud/question/getallnewquestion/";
		urljsonArry += userid;
		System.out.println("URLLLLLL>>>" + urljsonArry);
		JsonArrayRequest req = new JsonArrayRequest(urljsonArry,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						// Log.d(TAG, response.toString());

						try {
							// Parsing json array response
							// loop through each json object
							for (int i = 0; i < response.length(); i++) {

								JSONObject obj = response.getJSONObject(i);

								Question question = new Question();
								question.setIdquestion(obj
										.getLong("idquestion"));
								question.setQuestiondetails(obj
										.getString("questiondetails"));

								questionArraylist.add(question);

							}

							Collections.reverse(questionArraylist);

							questionNotificationAdapter.notifyDataSetChanged();

							if (questionArraylist.isEmpty()) {

								CustomToast.SetmessageToast(
										"you dont have notification to show",
										getApplicationContext());

							}

							System.out.println("idquestion>>>"
									+ questionArraylist);

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

	/**
	 * Here create the custom adapter to set the question notification to
	 * listview.
	 */

	private class QuestionNotificationAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return questionArraylist.size();
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
						.inflate(R.layout.question_notification_item_layout,
								null);

			}

			final TextView questionidTextView = (TextView) convertView
					.findViewById(R.id.questionidTextView);
			questionidTextView.setText(String.valueOf(questionArraylist.get(
					position).getIdquestion()));

			TextView questionotiTextView = (TextView) convertView
					.findViewById(R.id.questionTextView);
			questionotiTextView.setText(questionArraylist.get(position)
					.getQuestiondetails());
			// /**
			// * When user click on the button the it will change the read
			// status.
			// */
			// convertView.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// String questionid = questionidTextView.getText().toString();
			//
			// new ChangeReadStatus().execute(questionid, userid);
			//
			// // it will deleted the question id from the database once
			// // user read that question.
			// qaNotificationdb.deleteQuestionId(questionid);
			//
			// }
			// });
			return convertView;
		}

	}

	/**
	 * This Asyntask method use to change the question read status.
	 * http://166.62.81.118:18080/SpringRestCrud/question/updateunreadques/368/1
	 */

	// This asyntaks use to update the question by own user.
	private class ChangeReadStatus extends AsyncTask<String, Integer, Double> {

		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(
					QuestionNotificationActivity.this);
			progressDialog.setMessage("You read question..");
			progressDialog.setCancelable(false);
			progressDialog.show();

		};

		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1]);
			return null;
		}

		protected void onPostExecute(Double result) {

			if (progressDialog != null && progressDialog.isShowing()) {

				Intent intent = new Intent(QuestionNotificationActivity.this,
						QuestionNotificationActivity.class);

				startActivity(intent);

				progressDialog.dismiss();
			}

		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String questionid, String userid)
				throws IllegalArgumentException {

			try {

				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/question/updateunreadques/";
				// System.out.println("***************"+basurl2);
				basurl3 += questionid + "/" + userid;
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
	 * This asyntask use to get the answer notififation of respected question.
	 * 
	 * @author Admin
	 * 
	 */
	private class GetAnswerNotificationDetails extends
			AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			urlans += userid;
			System.out.println("url>>" + urlans);

			progressDialog.setMessage("Notification loading..");
			progressDialog.setCancelable(false);
			progressDialog.show();

		}

		@SuppressWarnings("unchecked")
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance

			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String answResponse = sh
					.makeServiceCall(urlans, ServiceHandler.GET);

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

			System.out.println("answer notification>>>"
					+ ansNotificationCountArrayList);
			if (!ansNotificationCountArrayList.isEmpty()) {

				answerNotificationAdapter = new AnswerNotificationAdapter(
						getApplicationContext(), ansNotificationCountArrayList);

				ansListView.setAdapter(answerNotificationAdapter);

			}

			if (progressDialog != null && progressDialog.isShowing()) {

				progressDialog.dismiss();

			}

		}
	}
}
