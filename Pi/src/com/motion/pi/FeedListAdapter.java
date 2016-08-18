//package com.motion.pi;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//public class FeedListAdapter extends BaseAdapter {
//
//	private Delateds activity;
//	private ArrayList<HashMap<String, String>> data;
//	private static LayoutInflater inflater=null;
//	public ImageLoader imageLoader; 
//
//	public FeedListAdapter(Delateds a, ArrayList<HashMap<String, String>> d) {
//		activity = a;
//		data=d;
//		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		imageLoader=new ImageLoader(activity.getApplicationContext());
//	}
//
////	public int getCount() {
////		return data.size();
////	}
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return data.size();
//	}
//	public Object getItem(int position) {
//		return position;
//	}
//
//	public long getItemId(int position) {
//		return position;
//	}
//
//	public View getView(int position, View convertView, ViewGroup parent) {
//		View vi=convertView;
//		if(convertView==null)
//			vi = inflater.inflate(R.layout.qlist_item, null);
//
//		TextView title = (TextView)vi.findViewById(R.id.qid); // title
//		TextView artist = (TextView)vi.findViewById(R.id.textView87); // artist name
//		TextView duration = (TextView)vi.findViewById(R.id.uname); // duration
//		//        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
//
//		HashMap<String, String> song = new HashMap<String, String>();
//		song = data.get(position);
//
//		// Setting all values in listview
//		title.setText(song.get(Delateds.TAG_ID));
//		artist.setText(song.get(Delateds.TAG_NAME));
//		duration.setText(song.get(Delateds.TAG_userpost));
//		//        imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
//		return vi;
//	}
//
//	
//}