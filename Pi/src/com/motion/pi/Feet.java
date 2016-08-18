package com.motion.pi;
//package pi.motion.com.pi;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.media.MediaPlayer;
//import android.media.MediaPlayer.OnCompletionListener;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.text.TextUtils.TruncateAt;
//import android.text.format.DateUtils;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.Cache;
//import com.android.volley.Cache.Entry;
//import com.android.volley.Request.Method;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.VolleyLog;
//import com.android.volley.toolbox.ImageLoader;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.NetworkImageView;
//
//
//public class Feet extends Activity implements OnCompletionListener{
//    private static final String TAG = Feet.class.getSimpleName();
//    ListView listview;
//    //	String feedUrl;
//    String ulr;
//    String Links;
//    TextView url;
//    Uri link;
//    Bitmap bm;
//    Uri uri;
//
//    private int playlistPos;
//    static Song previousSong;
//    TextView tvurl;
//    //	private Button btnNext;
//    Uri urls;
//    String name;
//    //	private Utilities utils;
//    private static MediaPlayer mp;
//    private FeedListAdapter listAdapter;
//    ArrayList<String> arrayList = new ArrayList<String>();
//    private int currentSongIndex = 0;
//    private Button Play;
//    private Button btNext;
//    Button prevv;
//    private List<FeedItem> feedItems;
//    ImageView imageview;
//    //	ImageView img;
////    private String URL_FEED = "http://www.greeturfrnd.com/Babufiles/Feet.json";
//    private String URL_FEED="http://166.62.81.118:18080/SpringRestCrud/question/getQuestionTypeList";
//    public static final String URL ="http://greeturfrnd.com/Babufiles/durgabanner.png";
//
//
//    @SuppressLint("NewApi")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.feet);
//
//
//        listview = (ListView) findViewById(R.id.lv);
//
//        feedItems = new ArrayList<FeedItem>();
//
//        listAdapter = new FeedListAdapter(this, feedItems);
//        listview.setAdapter(listAdapter);
//
//        // Create an object for subclass of AsyncTask
//        GetXMLTask task = new GetXMLTask();
//        // Execute the task
//        task.execute(new String[] { URL });
//        Button src = (Button)findViewById(R.id.button1);
//
//        // Prepare the Interstitial Ad
//
//
//
//
//
//        // We first check for cached request
//        Cache cache = AppController.getInstance().getRequestQueue().getCache();
//        Entry entry = cache.get(URL_FEED);
//        if (entry != null) {
//            // fetch the data from cache
//            try {
//                String data = new String(entry.data, "UTF-8");
//                try {
//                    parseJsonFeed(new JSONObject(data));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            // making fresh volley request and getting json
//            JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
//                    URL_FEED, null, new Response.Listener<JSONObject>() {
//
//                @Override
//                public void onResponse(JSONObject response) {
//                    VolleyLog.d(TAG, "Response: " + response.toString());
//                    if (response != null) {
//                        parseJsonFeed(response);
//                    }
//                }
//            }, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    VolleyLog.d(TAG, "Error: " + error.getMessage());
//                }
//            });
//
//            // Adding request to volley request queue
//            AppController.getInstance().addToRequestQueue(jsonReq);
//        }
//        //		// Create an object for subclass of AsyncTask
//        //		GetXMLTask task = new GetXMLTask();
//        //		// Execute the task
//        //		task.execute(new String[] { URL });
//
//    }
//    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
//        @Override
//        protected Bitmap doInBackground(String... urls) {
//            Bitmap map = null;
//            for (String url : urls) {
//                map = downloadImage(url);
//            }
//            return map;
//        }
//
//        // Sets the Bitmap returned by doInBackground
//        @Override
//        protected void onPostExecute(Bitmap result) {
////            imageview.setImageBitmap(result);
//        }
//
//        // Creates Bitmap from InputStream and returns it
//        private Bitmap downloadImage(String url) {
//            Bitmap bitmap = null;
//            InputStream stream = null;
//            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//            bmOptions.inSampleSize = 1;
//
//            try {
//                stream = getHttpConnection(url);
//                bitmap = BitmapFactory.
//                        decodeStream(stream, null, bmOptions);
//                stream.close();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            return bitmap;
//        }
//
//        // Makes HttpURLConnection and returns InputStream
//        private InputStream getHttpConnection(String urlString)
//                throws IOException {
//            InputStream stream = null;
//            URL url = new URL(urlString);
//            URLConnection connection = url.openConnection();
//
//            try {
//                HttpURLConnection httpConnection = (HttpURLConnection) connection;
//                httpConnection.setRequestMethod("GET");
//                httpConnection.connect();
//
//                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    stream = httpConnection.getInputStream();
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            return stream;
//        }
//    }
//
//
//
//    /**
//     * Parsing json reponse and passing the data to feed view list adapter
//     * */
//
//    private void parseJsonFeed(JSONObject response) {
//        try {
//            JSONArray feedArray = response.getJSONArray("feed");
//
//            for (int i = 0; i < feedArray.length(); i++) {
//                JSONObject feedObj = (JSONObject) feedArray.get(i);
//
//                FeedItem item = new FeedItem();
//                //				item.setId(feedObj.getInt("id"));
//                item.setName(feedObj.getString("idqtype"));
//                //				item.setName(feedObj.getString("title"));
//
//                // Image might be null sometimes
//                String image = feedObj.isNull("image") ? null : feedObj
//                        .getString("image");
//                item.setImge(image);
//                item.setStatus(feedObj.getString("qtype"));
//                item.setProfilePic(feedObj.getString("profilePic"));
//                item.setTimeStamp(feedObj.getString("timeStamp"));
//
//                // url might be null sometimes
//                String feedUrl = feedObj.isNull("url") ? null : feedObj
//                        .getString("url");
//                item.setUrl(feedUrl);
//
//                feedItems.add(item);
//            }
//
//            // notify data changes to list adapater
//            listAdapter.notifyDataSetChanged();
//
//
//
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//    public boolean isNetworkAvailable1() {
//        // TODO Auto-generated method stub
//        return false;
//    }
//    protected String getString(String name2) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//
//    class FeedListAdapter extends BaseAdapter {
//        //		public MediaPlayer mMp;
//
//        private Feet activity;
//        private LayoutInflater inflater;
//        private List<FeedItem> feedItems;
////        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//
//
//
//
//        public FeedListAdapter(Feet activity, List<FeedItem> feedItems) {
//            this.activity = activity;
//            this.feedItems = feedItems;
//            playlistPos = 0;
//        }
//
//        @Override
//        public int getCount() {
//            return feedItems.size();
//        }
//
//        @Override
//        public Object getItem(int location) {
//            return feedItems.get(location);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        //		@SuppressWarnings("null")
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            //			convertView.setSelected(true);
//            LayoutInflater in = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//            convertView = in.inflate(R.layout.feed_item, null);
//
////            if (imageLoader == null)imageLoader = AppController.getInstance().getImageLoader();
//
//            feedItems.get(playlistPos);
//            tvurl = (TextView)findViewById(R.id.txtUrl);
//
//            final TextView name = (TextView) convertView.findViewById(R.id.name);
//            final TextView urltxt = (TextView)convertView.findViewById(R.id.txtUrl);
//            final ImageView img = (ImageView)convertView.findViewById(R.id.profilePic);
//            //			final TextView br = (TextView)convertView.findViewById(R.id.textView1);
//            final Button adto= (Button)convertView.findViewById(R.id.adto);
//
//
//
//            final TextView statusMsg = (TextView) convertView
//                    .findViewById(R.id.txtStatusMsg);
//            url = (TextView) convertView.findViewById(R.id.txtUrl);
//            final NetworkImageView profilePic = (NetworkImageView) convertView
//                    .findViewById(R.id.profilePic);
//
//
//
//            FeedItem item = feedItems.get(position);
//
//
//
//            name.setText(item.getName());
//            urltxt.setText(item.getUrl());
//
//            // Converting timestamp into x ago format
//            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
//                    Long.parseLong(item.getTimeStamp()),
//                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
//            //			timestamp.setText(timeAgo);
//
//            // Chcek for empty status message
//            if (!TextUtils.isEmpty(item.getStatus())) {
//                statusMsg.setText(item.getStatus());
//                statusMsg.setVisibility(View.VISIBLE);
//            } else {
//                // status is empty, remove from view
//                statusMsg.setVisibility(View.GONE);
//            }
//
//
//            final boolean istrue = true;
//            //			final TextView tscrol = (TextView)findViewById(R.id.sctext);
//            //			tscrol.setMovementMethod(new ScrollingMovementMethod());
//            final TextView txtscrol = (TextView)findViewById(R.id.scrolls);
//
//            convertView.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    // TODO Auto-generated method stub
//
//                    String strname = name.getText().toString();
//                    String singers = statusMsg.getText().toString();
//
//
//                    ulr = urltxt.getText().toString();
//                    urls = Uri.parse(ulr);
//                    txtscrol.setText("_______"+"Now Playing Song: "+strname + singers+"_______");
//                    txtscrol.setSelected(true);
//                    txtscrol.setEllipsize(TruncateAt.MARQUEE);
//                    txtscrol.setSingleLine(true);
//
//                    Toast.makeText(getApplicationContext()," Now Playing :" +strname, Toast.LENGTH_LONG).show();
//
//
//                }
//                });
//
//        return convertView;
//        }
//
//
//
//
//
//
//
//
//        protected void onCreate(Bundle savedInstanceState) {
//            // TODO Auto-generated method stub
//
//        }
//
//        public boolean onCreateOptionsMenu(Menu menu) {
//            // TODO Auto-generated method stub
//            return false;
//        }
//
//    }
//
//
//    private class Song{
//        private int thumbnail;
//        private String songname;
//        private String songdetail;
//        private boolean isPlaying;
//        private String duration;
//
//        public Song(int thumbnail,String songname,String songdetail,boolean isPlaying,String duration){
//            this.thumbnail  = thumbnail;
//            this.songname   = songname;
//            this.songdetail = songdetail;
//            this.isPlaying  = isPlaying;
//            this.duration   = duration;
//        }
//    }
//
//
//    @Override
//    public void onCompletion(MediaPlayer mp) {
//        // TODO Auto-generated method stub
//        if (currentSongIndex< arrayList.size()) {
//            mp.reset();
//
//            try {
//                mp.setDataSource(arrayList.get(currentSongIndex));
//            } catch (IllegalArgumentException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (SecurityException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IllegalStateException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            mp.prepareAsync();
//            mp.start();
//
//
//        }
//        else {
//            mp.release();
//        }
//
//
//
//    }
//}
