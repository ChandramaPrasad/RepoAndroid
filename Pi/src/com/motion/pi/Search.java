package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;
import info.androidhive.customlistviewvolley.util.LookAndFeel;
import info.androidhive.customlistviewvolley.util.Util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
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

public class Search extends CustomActionBar {
	// Log tag
	View formElementsView;
	String skb;
	View convertView;
	int w = 100;
	private static final String TAG = Search.class.getSimpleName();

	// Movies json url
	// private static final String url =
	// "http://api.androidhive.info/json/movies.json";

	// private static final String url =
	// "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswerforkey/";
	private ProgressDialog pDialog;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView listView;
	private CustomListAdapter adapter;
	static final int READ_BLOCK_SIZE = 100;
	ImageView iprof;
	ImageView icompose;
	ImageView isets;
	ImageView pic;
	String wdps;
	private ImageView searchImageView;
	private String username = "";
	private Toast toast = null;
	private EditText searchEditText;
	private RelativeLayout searchLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		pic = (ImageView) findViewById(R.id.imageView5);
		searchEditText = (EditText) findViewById(R.id.inputEditText);
		searchImageView = (ImageView) findViewById(R.id.searchButton);
		searchLayout = (RelativeLayout) findViewById(R.id.searchLayout);
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
		String data = getIntent().getStringExtra("Search");

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

		new MyAsyncTask().execute(data);

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
				View someView = findViewById(R.id.rela);
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
		searchLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				movieList.clear();
				String get = searchEditText.getText().toString();
				if (Util.isNetworkAvailable(getApplicationContext())) {

					if (movieList.isEmpty() && !TextUtils.isEmpty(get)) {
						new MyAsyncTask().execute(get);
					} else {
						toast.setText("Please enter text to search");
						toast.show();
					}

				} else {
					toast.setText("Please connect to internet");
					toast.setDuration(900);
					toast.show();
				}

			}
		});

		// String nnm = name.getText().toString();
		// link += nnm + ".jpg";
		// new LoadImage().execute(link);
		// System.out.println(">>>>>>>>>>>" + link);
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
				if (skb.contentEquals("SKY")) {
					View someView = findViewById(R.id.rela);
					someView.setBackground(getResources().getDrawable(
							R.drawable.sky));

				} else if (skb.contentEquals("YEL")) {
					View someView = findViewById(R.id.rela);
					someView.setBackground(getResources().getDrawable(
							R.drawable.yello));
				}
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

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

		} catch (Exception e) {
			e.printStackTrace();
		}
		// changing action bar color
		// getActionBar().setBackgroundDrawable(
		// new ColorDrawable(Color.parseColor("#1b1b1b")));

	}

	private class MyAsyncTask extends AsyncTask<String, Integer, Double> {
		HttpEntity entity;
		HttpResponse responses;

		String b;
		String ib;

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

			String url = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswerforkey/";
			url += username;
			JsonArrayRequest movieReq = new JsonArrayRequest(url,
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
								Intent i = new Intent(Search.this,
										PiAnswers.class);
								startActivity(i);
							}

							hidePDialog();

							// Parsing json
							for (int i = 0; i < response.length(); i++) {
								try {

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

							Collections.reverse(movieList);

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

	Bitmap bitmap;

	private class LoadImage extends AsyncTask<String, String, Bitmap> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// pDialog = new ProgressDialog(PiTop.this);
			// pDialog.setMessage("Loading Image ....");
			// pDialog.show();

		}

		protected Bitmap doInBackground(String... args) {
			try {
				bitmap = BitmapFactory.decodeStream((InputStream) new URL(
						args[0]).getContent());

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bitmap;
		}

		protected void onPostExecute(Bitmap image) {

			if (image != null) {
				pic.setImageBitmap(image);
				// pDialog.dismiss();

			} else {

			}
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
	 * Here set the custom adapter to the listview.
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
			if (convertView == null)
				convertView = inflater.inflate(R.layout.search_item, null);

			if (imageLoader == null)
				imageLoader = AppController.getInstance().getImageLoader();

			final TextView title = (TextView) convertView
					.findViewById(R.id.title);

			final TextView year = (TextView) convertView
					.findViewById(R.id.releaseYear);

			// getting movie data for the row
			Movie m = movieItems.get(position);

			// title
			title.setText(m.getQuestiondetails());
			year.setText(m.getAnswerdetails());

			// when user click on this button he/she able to answer or rate the
			// question from the homepage.
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					Intent intent = new Intent(Search.this, PiAnswers.class);
					startActivity(intent);
					finish();

				}
			});

			return convertView;
		}
	}
}
