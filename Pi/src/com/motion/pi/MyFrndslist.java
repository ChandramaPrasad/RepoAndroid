package com.motion.pi;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.CircleDao;
import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.motion.actionbar.CustomActionBar;
import com.squareup.picasso.Picasso;

public class MyFrndslist extends CustomActionBar {
	// Log tag
	View formElementsView;
	String idd;
	String qid;
	String qqid;
	View convertView;
	int w = 100;
	private static final String TAG = MyFrndslist.class.getSimpleName();
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private static final String url = "http://166.62.81.118:18080/SpringRestCrud/signup/userlist";
	private ProgressDialog pDialog;
	private List<CircleDao> movieList = new ArrayList<CircleDao>();
	private ListView listView;
	private CustomListAdapter adapter;
	String skb;
	String nam;
	SharedPreferences sps;
	String id;
	String kuid;
	ImageView serch;
	String Name;
	String Nid;
	String answer;
	private String username = "";
	private TextView nameTextView;
	private String userid = "";
	private ImageView profilepictureImageView;
	private ImageView home;
	static final int READ_BLOCK_SIZE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myfnd);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		listView = (ListView) findViewById(R.id.list);
		nameTextView = (TextView) findViewById(R.id.sname);
		final EditText search = (EditText) findViewById(R.id.multiss);
		home = (ImageView) findViewById(R.id.button30);
		adapter = new CustomListAdapter(this, movieList);

		answer = getIntent().getStringExtra("answerfrom");
		listView.setAdapter(adapter);

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
				View someView = findViewById(R.id.maillistback);
				// This method use to change the background color when user
				// select from look and feel settings.
				LookAndFeel.lookAndFeel(skb, someView);
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		// serch = (ImageView)findViewById(R.id.src);
		// serch.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// String get = search.getText().toString();
		// // new seartask().execute(get);
		// new seartask().execute(get);
		// }
		// });

		// final ImageView compose = (ImageView)findViewById(R.id.button);
		// compose.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// Intent i= new Intent(MyFrndslist.this,Kompose.class);
		// i.putExtra("name", Name);
		// i.putExtra("id", Nid);
		// startActivity(i);
		// }
		// });
		// Button prof = (Button)findViewById(R.id.button1);
		// prof.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// Intent i=new Intent(MyFrndslist.this,Profile.class);
		// startActivity(i);
		// }
		// });
		// Button sets= (Button)findViewById(R.id.button2);
		// sets.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// Intent i=new Intent(MyFrndslist.this,Settings.class);
		// startActivity(i);
		// }
		// });
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
								movie.setIdsignup(obj.getString("idsignup"));
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

	private class seartask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

			pDialog = new ProgressDialog(MyFrndslist.this);

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

	/**
	 * Here set the custom adapter to show the list of the user.
	 * 
	 * @author Admin
	 * 
	 */
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

			final TextView usernameTextView = (TextView) convertView
					.findViewById(R.id.chatNameTextView);
			profilepictureImageView = (ImageView) convertView
					.findViewById(R.id.thumbnail);
			// final TextView qids =
			// (TextView)convertView.findViewById(R.id.qid);
			final TextView useridTextView = (TextView) convertView
					.findViewById(R.id.useridTextView);

			CircleDao m = movieItems.get(position);

			if (!m.getIdsignup().toString().equals(userid)) {
				usernameTextView.setText(m.getUserid());
				useridTextView.setText(m.getIdsignup());
				String userid = useridTextView.getText().toString();
				loadProfileImage(userid);

			}

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					// send username and userid to send mail to destination
					// user to noteSend Activity.
					String username = usernameTextView.getText().toString();
					String userid = useridTextView.getText().toString();
					Intent i = new Intent(MyFrndslist.this, Notessend.class);
					i.putExtra("username", username);
					i.putExtra("userid", userid);
					startActivity(i);
					finish();
				}
			});

			return convertView;
		}

		// This method user to load user profile picture when user click on
		// circular picture.
		protected void loadProfileImage(String currentUsername) {

			String getimage = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
			getimage += currentUsername + ".jpg";

			if (getimage != null) {

				Picasso.with(getApplicationContext()).load(getimage)
						.placeholder(R.drawable.profilepic_sml)
						.into(profilepictureImageView);
				System.out.println(">>>>>>>>>>>" + getimage);
				// Here clear the path to set the image again tap on user
				// picture.
				getimage = "";

			}

		}

	}
}
