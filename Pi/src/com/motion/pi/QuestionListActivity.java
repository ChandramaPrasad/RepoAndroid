package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Question;
import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;

public class QuestionListActivity extends CustomActionBar {

	private QuestionListAdapter questionListAdapter;
	private ListView questionListView;
	private String username = "";
	static final int READ_BLOCK_SIZE = 100;
	private String userid = "";
	private String skb;
	private ArrayList<Question> questionsArraylist;
	private String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private Toast toast = null;
	View view;
	TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_list_layout);

		questionListView = (ListView) findViewById(R.id.questionListView);
		questionsArraylist = new ArrayList<Question>();
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

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

				View someView = findViewById(R.id.questionlistback);
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

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
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		/***
		 * Make the question request from the server to show user to block the
		 * questions.
		 */
		makeQuestionListRequest();

		questionListAdapter = new QuestionListAdapter();
		questionListView.setAdapter(questionListAdapter);

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (toast != null) {

		}
	}

	/***
	 * This method use to show the list of question to choose for blocking.
	 */
	private void makeQuestionListRequest() {
		// TODO Auto-generated method stub

		String url = "http://166.62.81.118:18080/SpringRestCrud/question/questionlist";

		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Question question = new Question();

								question.setIdquestion(obj
										.getLong("idquestion"));
								question.setQuserid(obj.getLong("quserid"));
								question.setQuestiondetails(obj
										.getString("questiondetails"));
								question.setQusername(obj
										.getString("qusername"));

								/***
								 * Here filter the question which is posted by
								 * own user other question he/she cannot blcok.
								 */
								if (String.valueOf(question.getQuserid())
										.equals(userid)) {

									questionsArraylist.add(question);
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

						}

						if (questionsArraylist.isEmpty()) {

							toast.setText("You not posted question yet,can block own questions");
							toast.setDuration(2000);
							toast.setGravity(Gravity.CENTER, 0, 0);
							view = toast.getView();
							text = (TextView) view
									.findViewById(android.R.id.message);
							text.setTextColor(getResources().getColor(
									R.color.Green));
							text.setShadowLayer(0, 0, 0, 0);
							text.setTextSize(20);
							view.setBackgroundResource(R.color.white);

							toast.show();

						}

						questionListAdapter.notifyDataSetChanged();
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
	 * Here will show the list of question which user can be block.
	 * 
	 * @author Admin
	 * 
	 */
	private class QuestionListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return questionsArraylist.size();
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
						.inflate(R.layout.question_list_item_layout, null);
			}

			final TextView questionTextView = (TextView) convertView
					.findViewById(R.id.questionTextView);
			final TextView idQuestionTextView = (TextView) convertView
					.findViewById(R.id.questionId);
			questionTextView.setText(questionsArraylist.get(position)
					.getQuestiondetails());
			idQuestionTextView.setText(String.valueOf(questionsArraylist.get(
					position).getIdquestion()));

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String question = questionTextView.getText().toString();
					String questionid = idQuestionTextView.getText().toString();
					Intent intent = new Intent(QuestionListActivity.this,
							BlockQuestionActivity.class);
					intent.putExtra("question", question);
					intent.putExtra("questionid", questionid);
					startActivity(intent);
					finish();

				}
			});
			return convertView;
		}
	}
}
