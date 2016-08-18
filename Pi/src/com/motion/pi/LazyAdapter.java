//package com.motion.pi;
//
//import java.util.HashMap;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//public class LazyAdapter extends BaseAdapter{
//
////	Lazyadapter(Activity a, ArrayList<HashMap<String, String>> d) {
////		activity = a;
////		data=d;
////		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////		imageLoader=new ImageLoader(activity.getApplicationContext());
////	}
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		View vi=convertView;
//		if(convertView==null)
//			vi = inflater.inflate(R.layout.circle_item, null);
//		TextView artist = (TextView)vi.findViewById(R.id.usersss); // artist name
//		Button duration = (Button)vi.findViewById(R.id.addf); // duration
//		ImageView thumb_image=(ImageView)vi.findViewById(R.id.profile); // thumb image
////		HashMap<String, String> song = new HashMap<String, String>();
////		song = data.get(position);
//		
//		HashMap<String, String> maps = new HashMap<String, String>();
//        String value = maps.get(TAG_ID);
////		HashMap<String, String> map = (HashMap<String, String>) parent.getItemAtPosition(position);
//		//                String value = map.get(TAG_NAME);
//		maps = data.get(position);
//		String idd = maps.get(TAG_Quest);
//		// Setting all values in listview
//		artist.setText(maps.get(Circles.TAG_Quest));
////		artist.setText(song.get(CustomizedListView.KEY_ARTIST));
////		duration.setText(song.get(CustomizedListView.KEY_DURATION));
////		imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
//		return vi;
//	}
//
//
//}