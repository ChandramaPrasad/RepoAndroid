package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;

public class QuestionAndAnswer extends CustomActionBar implements
		OnClickListener {
	private TextView questionTextView;
	private ListView questionAnswerListView;
	private ImageView iconButton;
	private ProgressDialog progressDialog;
	private List<Movie> movieList;
	private ImageView profilePictureImageView;
	private TextView nameTextView;
	private URI uri;
	private String username = "";
	static final int READ_BLOCK_SIZE = 100;
	private AnswerListViewAdapter answerListViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_answer_layout);
		initViews();
		registerEvents();

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

		String question = getIntent().getStringExtra("question");
		// here check condition weather string null or not
		if (question != null) {

			questionTextView.setText(question);

		}

		// This method will use to search the question and then give the answer
		// of that question.

		SearchmakeJsonArrayRequest(question);

		// Method is use to set adapter to ListView.
		setAdapterToListView();
	}

	// This method use to set the adapter to ListView.
	private void setAdapterToListView() {

		answerListViewAdapter = new AnswerListViewAdapter();
		questionAnswerListView.setAdapter(answerListViewAdapter);

	}

	// This method use to register the events.
	private void registerEvents() {

//		iconButton.setOnClickListener(this);

	}

	// This method use to initialise the views.
	private void initViews() {

		questionTextView = (TextView) findViewById(R.id.newquestionTextView);
		questionAnswerListView = (ListView) findViewById(R.id.answerListView);
		profilePictureImageView = (ImageView) findViewById(R.id.profileImageView);
		nameTextView = (TextView) findViewById(R.id.usernameTextView);
		progressDialog = new ProgressDialog(this);
		movieList = new ArrayList<Movie>();

	}

	private class AnswerListViewAdapter extends BaseAdapter {

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

			if (convertView == null) {

				convertView = LayoutInflater
						.from(getApplicationContext())
						.inflate(
								R.layout.question_and_answer_list_adapter_layout,
								null);

			}

			TextView answerTextView = (TextView) convertView
					.findViewById(R.id.answer_TextView);
			answerTextView.setText(movieList.get(position).getAnswerdetails()
					.toString());
			return convertView;
		}

	}

	@Override
	public void onClick(View view) {

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

		progressDialog.setMessage("Please Wait..");
		progressDialog.setCancelable(false);
		progressDialog.show();
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
							answerListViewAdapter.notifyDataSetChanged();

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
						Toast.makeText(getApplicationContext(),
								error.getMessage(), Toast.LENGTH_SHORT).show();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);

	}

	private void hidePDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

}
