package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.BlockQuestions;
import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;

public class BlockQuestionActivity extends CustomActionBar {

	/**
	 * Declare the variables here.
	 */
	private String username = "";
	static final int READ_BLOCK_SIZE = 100;
	private String userid = "";
	private String skb;
	private String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private ListView questionListView;
	private EditText questionEditText;
	private ImageView submitButton;
	private ArrayList<BlockQuestions> blockQuestionsArrayList;
	private String question;
	private BlockQestionAdapter blockQestionAdapter;
	private String questionid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.block_question_layout);

		intiViews();

		question = getIntent().getStringExtra("question");
		questionid = getIntent().getStringExtra("questionid");
		makeBlockQuestionRequest();

		try {
			FileInputStream fileIn = openFileInput("mytextfile.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];

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
				View someView = findViewById(R.id.blockquestionback);

				// This method use to change the background color when user
				// select from the look and feel
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

		submitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String question = questionEditText.getText().toString();
				if (question.matches("")) {

					Toast.makeText(getApplicationContext(),
							"Please enter the question", Toast.LENGTH_SHORT)
							.show();

				} else {

					// if question is there then send request to block that
					// question
					// from the user.
					new BlockQuestion().execute(questionid);
					questionEditText.setText("");
				}

			}
		});

		questionEditText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(BlockQuestionActivity.this,
						QuestionListActivity.class);
				startActivity(intent);
				finish();

			}
		});

		if (question != null) {

			questionEditText.setText(question);

		}

		questionListView.setAdapter(blockQestionAdapter);

	}

	/***
	 * This method use to make a fresh request for the block questionlist.
	 */
	private void makeBlockQuestionRequest() {
		// TODO Auto-generated method stub

		String url = "http://166.62.81.118:18080/SpringRestCrud/question/blockquestionlist";

		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								BlockQuestions blockQuestions = new BlockQuestions();

								blockQuestions.setIdquestion(obj
										.getLong("idquestion"));
								blockQuestions.setQuestiondetails(obj
										.getString("questiondetails"));
								blockQuestions.setQusername(obj
										.getString("qusername"));
								blockQuestions.setQuserid(obj
										.getLong("quserid"));

								// This is check if question is block by the
								// owner of the question then it only show
								// otherwise not show.
								if (String.valueOf(blockQuestions.getQuserid())
										.equals(userid)) {
									blockQuestionsArrayList.add(blockQuestions);

								}

							} catch (Exception e) {
								e.printStackTrace();
							}

						}

						blockQestionAdapter.notifyDataSetChanged();
						System.out
								.println("size>>>>" + blockQuestionsArrayList);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// VolleyLog.d(TAG, "Error: " + error.getMessage());

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
		// sending request for job notification details.

	}

	/**
	 * This method use to initialize the views here.
	 */
	private void intiViews() {
		// TODO Auto-generated method stub

		questionListView = (ListView) findViewById(R.id.questionListView);
		questionEditText = (EditText) findViewById(R.id.questionEditText);
		submitButton = (ImageView) findViewById(R.id.submitButton);
		blockQuestionsArrayList = new ArrayList<BlockQuestions>();
		blockQestionAdapter = new BlockQestionAdapter();

	}

	/**
	 * This adapter use to show the list of block the questions.
	 * 
	 * @author Admin
	 * 
	 */
	private class BlockQestionAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return blockQuestionsArrayList.size();
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
						.inflate(R.layout.block_question_list_item_layout, null);

			}
			TextView questionTextView = (TextView) convertView
					.findViewById(R.id.questionItemTextView);

			final TextView idquestion = (TextView) convertView
					.findViewById(R.id.questionidTextView);
			idquestion.setText(String.valueOf(blockQuestionsArrayList.get(
					position).getIdquestion()));
			questionTextView.setText(blockQuestionsArrayList.get(position)
					.getQuestiondetails());

			// when user click on this button he/she able to unblock the
			// question.
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					final String questionid = idquestion.getText().toString();

					AlertDialog.Builder builder1 = new AlertDialog.Builder(
							BlockQuestionActivity.this);
					builder1.setMessage("Are you sure,want to unblock the Question?");
					builder1.setCancelable(true);

					builder1.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									new UnblockQuestion().execute(questionid);
									dialog.cancel();
								}
							});

					builder1.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});

					AlertDialog alert11 = builder1.create();
					alert11.show();

				}
			});

			return convertView;
		}
	}

	/***
	 * This method use to block the question.
	 * 
	 * @author Admin
	 * 
	 */
	private class BlockQuestion extends AsyncTask<String, Integer, Double> {
		URI uri;
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("OnPreExecute");
			progressDialog = new ProgressDialog(BlockQuestionActivity.this);
			progressDialog.setMessage("Blocking Question..");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0]);
			return null;
		}

		protected void onPostExecute(Double result) {

			if (progressDialog.isShowing() && progressDialog != null) {

				progressDialog.dismiss();
			}

			Intent intent = new Intent(BlockQuestionActivity.this,
					BlockQuestionActivity.class);
			// This line add for clear the activity when user
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String questionId)
				throws IllegalArgumentException {

			try {

				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/question/blockquestion/";
				// System.out.println("***************"+basurl2);
				basurl3 += questionId;

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

				HttpEntity entity = (HttpEntity) responses.getEntity();
				String resp = responses.toString();

				System.out.println("RESPONSE   " + entity);

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

	/***
	 * This method use to unblock the question.
	 * 
	 * @author Admin
	 * 
	 */
	private class UnblockQuestion extends AsyncTask<String, Integer, Double> {
		URI uri;
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			System.out.println("OnPreExecute");
			progressDialog = new ProgressDialog(BlockQuestionActivity.this);
			progressDialog.setMessage("Unlocking Question..");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0]);
			return null;
		}

		protected void onPostExecute(Double result) {

			if (progressDialog.isShowing() && progressDialog != null) {

				progressDialog.dismiss();
			}
			Intent intent = new Intent(BlockQuestionActivity.this,
					BlockQuestionActivity.class);
			// This line add for clear the activity when user
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String questionId)
				throws IllegalArgumentException {

			try {

				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/question/unblockquestion/";
				// System.out.println("***************"+basurl2);
				basurl3 += questionId;

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

				HttpEntity entity = (HttpEntity) responses.getEntity();
				String resp = responses.toString();

				System.out.println("RESPONSE   " + entity);

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

}
