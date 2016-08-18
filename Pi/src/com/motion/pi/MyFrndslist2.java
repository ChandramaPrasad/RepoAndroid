package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.CircleDao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

public class MyFrndslist2 extends Activity {
	// Log tag
	View formElementsView;
	String idd;
	String qid;
	String qqid;
	View convertView;
	int w = 100;
	private static final String TAG = MyFrndslist2.class.getSimpleName();
	String skb;
	// Movies json url
	// private static final String url =
	// "http://api.androidhive.info/json/movies.json";

	private static final String url = "http://166.62.81.118:18080/SpringRestCrud/signup/userlist";
	private ProgressDialog pDialog;
	URI uri;
	private List<CircleDao> movieList = new ArrayList<CircleDao>();
	private ListView listView;
	private CustomListAdapter adapter;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	String nam;
	SharedPreferences sps;
	String id;
	String kuid;
	ImageView serch;
	String Name;
	String s;
	String Nid;
	static final int READ_BLOCK_SIZE = 100;
	ImageView homes;
	private String username = "";
	String wdps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flist);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		homes = (ImageView) findViewById(R.id.headerProfileImage);
		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, movieList);
		listView.setAdapter(adapter);
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
				wdps = stringBuilder.toString();

			}
			inputStream.close();
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
		link += username + ".jpg";
		Picasso.with(this).load(link).into(homes);
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
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a65));
				} else if (skb.contentEquals("SKY1")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a64));
				} else if (skb.contentEquals("SKY2")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a63));
				} else if (skb.contentEquals("SKY3")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a62));
				} else if (skb.contentEquals("SKY4")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a61));
				} else if (skb.contentEquals("SKY5")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a60));
				} else if (skb.contentEquals("SKY6")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a59));
				} else if (skb.contentEquals("SKY7")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a58));
				} else if (skb.contentEquals("SKY8")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a57));
				} else if (skb.contentEquals("SKY9")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a56));
				} else if (skb.contentEquals("SKY10")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a55));
				} else if (skb.contentEquals("SKY11")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a54));
				} else if (skb.contentEquals("SKY12")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a53));
				} else if (skb.contentEquals("SKY13")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a52));
				} else if (skb.contentEquals("SKY14")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a51));
				} else if (skb.contentEquals("SKY15")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a50));
				} else if (skb.contentEquals("SKY16")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a49));
				} else if (skb.contentEquals("SKY17")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a48));
				} else if (skb.contentEquals("SKY18")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a47));
				} else if (skb.contentEquals("SKY19")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a46));
				} else if (skb.contentEquals("SKY20")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a45));
				} else if (skb.contentEquals("SKY21")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a44));
				} else if (skb.contentEquals("SKY22")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a43));
				} else if (skb.contentEquals("SKY23")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a42));
				} else if (skb.contentEquals("SKY24")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a41));
				} else if (skb.contentEquals("SKY25")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a40));
				} else if (skb.contentEquals("SKY26")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a39));
				} else if (skb.contentEquals("SKY27")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a38));
				} else if (skb.contentEquals("SKY28")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a37));
				} else if (skb.contentEquals("SKY29")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a36));
				} else if (skb.contentEquals("SKY30")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a35));
				} else if (skb.contentEquals("SKY31")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a34));
				} else if (skb.contentEquals("SKY32")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a33));
				} else if (skb.contentEquals("SKY33")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a32));
				} else if (skb.contentEquals("SKY34")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a31));
				} else if (skb.contentEquals("SKY35")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a30));
				} else if (skb.contentEquals("SKY36")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a29));
				} else if (skb.contentEquals("SKY37")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a28));
				} else if (skb.contentEquals("SKY38")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a27));
				} else if (skb.contentEquals("SKY39")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a26));
				} else if (skb.contentEquals("SKY40")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a25));
				} else if (skb.contentEquals("SKY41")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a24));
				} else if (skb.contentEquals("SKY42")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a23));
				} else if (skb.contentEquals("SKY43")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a22));
				} else if (skb.contentEquals("SKY44")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a21));
				} else if (skb.contentEquals("SKY45")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a20));
				} else if (skb.contentEquals("SKY46")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a19));
				} else if (skb.contentEquals("SKY47")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a18));
				} else if (skb.contentEquals("SKY48")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a17));
				} else if (skb.contentEquals("SKY49")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a16));
				} else if (skb.contentEquals("SKY50")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a15));
				} else if (skb.contentEquals("SKY51")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a14));
				} else if (skb.contentEquals("SKY52")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a13));
				} else if (skb.contentEquals("SKY53")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a12));
				} else if (skb.contentEquals("SKY54")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a11));
				} else if (skb.contentEquals("SKY55")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a10));
				} else if (skb.contentEquals("SKY56")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a9));
				} else if (skb.contentEquals("SKY57")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a8));
				} else if (skb.contentEquals("SKY58")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a7));
				} else if (skb.contentEquals("SKY59")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a6));
				} else if (skb.contentEquals("SKY60")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a5));
				} else if (skb.contentEquals("SKY61")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a4));
				} else if (skb.contentEquals("SKY62")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a3));
				} else if (skb.contentEquals("SKY63")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a2));
				} else if (skb.contentEquals("SKY64")) {
					View someView = findViewById(R.id.main);
					someView.setBackground(getResources().getDrawable(
							R.drawable.a1));
				}
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		ImageView home = (ImageView) findViewById(R.id.button30);
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MyFrndslist2.this, PiAnswers.class);
				startActivity(i);

			}
		});
		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();
		TextView name = (TextView) findViewById(R.id.sname);
		final EditText search = (EditText) findViewById(R.id.multiss);
		serch = (ImageView) findViewById(R.id.src);
		serch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String get = search.getText().toString();
				// new seartask().execute(get);
				new seartask().execute(get);
			}
		});
		try {
			FileInputStream fileIn = openFileInput("mytextfile.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			s = "";
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

			name.setText("" + s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String nnm = name.getText().toString();
		link += nnm + ".jpg";
		new LoadImage().execute(link);
		System.out.println(">>>>>>>>>>>" + link);
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

		// changing action bar color
		// getActionBar().setBackgroundDrawable(
		// new ColorDrawable(Color.parseColor("#1b1b1b")));

		// Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG,
								"hii&&&&&&&&&&&&&&&&&&&&&&:"
										+ response.toString());
						hidePDialog();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								// Movie movie = new Movie();
								CircleDao movie = new CircleDao();
								movie.setUserid(obj.getString("userid"));
								// movie.setIdsignup(obj.getString("idsignup"));
								movie.setImagepath(obj.getString("imagepath"));
								// movie.setQuestiondetails(obj.getString("questiondetails"));
								// movie.setIdquestion(obj.getString("idquestion"));
								// movie.setQusername(obj.getString("qusername"));
								// movie.setIdquestion(obj.getInt("idquestion"));
								// movie.setAnsusername(obj.getString("ansusername"));
								// movie.setAnswerdetails(obj.getString("answerdetails"));
								// movie.setRatingtypeid(obj.getLong("ratingtypevalue"));
								// movie.setAnsuserid(obj.getLong("ansuserid"));
								// movie.setQuestiondetails(obj);
								// Genre is json array
								// JSONArray genreArry =
								// obj.getJSONArray("questionid");
								// ArrayList<String> genre = new
								// ArrayList<String>();
								// for (int j = 0; j < genreArry.length(); j++)
								// {
								// genre.add((String) genreArry.get(j));
								// }
								// JSONObject qdetails =
								// obj.getJSONObject("questionid");
								// // String questiondetails =
								// qdetails.getString("questiondetails");
								// movie.setQuestiondetails(qdetails.getString("questiondetails"));
								// System.out.print("obj2:"+obj.getString("questionid"));
								// JSONArray genreArry = obj.getJSONArray();
								// System.out.print("genreArry:"+genreArry.toString());
								// JSONObject obj2 = genreArry.getJSONObject(1);

								// System.out.print("obj2:"+obj2.toString());
								// adding movie to movies array
								movieList.add(movie);

							} catch (Exception e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
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
				homes.setImageBitmap(image);
				// pDialog.dismiss();

			} else {

			}
		}
	}

	private class seartask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

			pDialog = new ProgressDialog(MyFrndslist2.this);

			pDialog.setMessage("Please wait...");

			pDialog.setCancelable(true);
			pDialog.show();

		}

		public void execute(String get) {
			// TODO Auto-generated method stub

		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class CustomListAdapter extends BaseAdapter {
		private Activity activity;
		private LayoutInflater inflater;
		View convertView;
		private List<CircleDao> movieItems;
		ImageLoader imageLoader = AppController.getInstance().getImageLoader();

		public CustomListAdapter(Activity activity, List<CircleDao> movieItems) {
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
				convertView = inflater.inflate(R.layout.fnds, null);

			if (imageLoader == null)
				imageLoader = AppController.getInstance().getImageLoader();
			NetworkImageView thumbNail = (NetworkImageView) convertView
					.findViewById(R.id.thumbnail);
			final TextView title = (TextView) convertView
					.findViewById(R.id.title);
			// final TextView qids =
			// (TextView)convertView.findViewById(R.id.qid);
			final TextView qn = (TextView) convertView.findViewById(R.id.genre);

			CircleDao m = movieItems.get(position);
			// qn.setText(m.getIdsignup());
			thumbNail.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// Toast.makeText(getApplicationContext(), ""+title,
					// 9000).show();

				}
			});

			title.setText(m.getUserid());
			qn.setText(m.getIdsignup());
			// title.setText(m.getQuestiondetails());

			// TextView rating = (TextView)
			// convertView.findViewById(R.id.rating);
			// TextView genre = (TextView) convertView.findViewById(R.id.genre);
			// final TextView rate =(TextView)convertView.findViewById(R.id.rt);
			// TextView year = (TextView)
			// convertView.findViewById(R.id.releaseYear);
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// Toast.makeText(getApplicationContext(), "" + qqid,
					// Toast.LENGTH_LONG).show();
					qid = title.getText().toString();
					idd = qn.getText().toString();
					new MyAsyncTask().execute(qid, idd);
					// qqid = qids.getText().toString();
					// Intent itr = new
					// Intent(MyFrndslist2.this,Sky_main.class);
					Toast.makeText(getApplicationContext(),
							"Request Sent to : " + qid, Toast.LENGTH_SHORT)
							.show();
					// AlaramData file = new
					// AlaramData(title.getText().toString(),qn.getText().toString(),qn.getText().toString());
					// AlaramDA audioD= new AlaramDA();
					// audioD.saveAlaram(file);
					// startActivity(itr);
					// Intent i = new Intent(MyFrndslist2.this, Sky.class);
					// i.putExtra("Question", qid);
					// // i.putExtra("QID", qqid);
					// i.putExtra("qid", idd);
					// startActivity(i);
				}
			});
			/*
			 * movie.setAnsusername(obj.getString("ansusername"));
			 * movie.setAnswerdetails(obj.getString("answerdetails"));
			 * movie.setRatingtypeid(obj.getLong("ratingtypeid"));
			 * movie.setAnsuserid(obj.getLong("ansuserid"));
			 */
			// getting movie data for the row

			// rate.setText(String.valueOf(m.getRatingtypevalue()));
			// rating

			// TextView chat =
			// (TextView)convertView.findViewById(R.id.textView1);
			// chat.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// Intent i =new Intent(MyFrndslist.this,Sky_main.class);
			// startActivity(i);
			// }
			// });
			// genre
			/*
			 * String genreStr = ""; for (String str : m.getGenre()) { genreStr
			 * += str + ", "; } genreStr = genreStr.length() > 0 ?
			 * genreStr.substring(0, genreStr.length() - 2) : genreStr;
			 */
			// genre.setText(m.getIdanswer()+"");

			// release year
			// year.setText(String.valueOf(m.getAnswerdetails()));

			return convertView;
		}

	}

	private class MyAsyncTask extends AsyncTask<String, Integer, Double> {
		@Override
		protected Double doInBackground(String... params)
				throws ArrayIndexOutOfBoundsException {
			postData(params[0], params[1]);
			return null;
		}

		protected void onPostExecute(Double result) {
			// pb.setVisibility(View.GONE);
			// Toast.makeText()
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		private void postData(String name, String nameid)
				throws IllegalArgumentException {

			try {
				// String kompose =
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/{questiondetails}/{userid}/{username}/{aboutmyquestion}/{qtypeid}";
				// String Sample=
				// "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/what%20is%20loop%20statement/30/Kavya/c%20language/5";
				String basurl2 = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/freindrequest/{srcuserid}/{srcusername}/{destuserid}/{destusername}";
				String basurl3 = "http://166.62.81.118:18080/SpringRestCrud/friendskywrite/freindrequest/";
				// System.out.println("***************"+basurl2);
				basurl3 += kuid + "/" + s + "/" + nameid + "/" + name;
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
