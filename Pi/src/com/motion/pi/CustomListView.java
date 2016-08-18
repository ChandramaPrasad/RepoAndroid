//package com.motion.pi;
//
//import java.util.ArrayList;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.app.Activity;
//import android.content.Context;
///**
// * 
// * @author manish
// *
// */
//
//public class CustomListView extends Activity {
//	ArrayList<Item> imageArry = new ArrayList<Item>();
//	CustomImageAdapter adapter;
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.sssmain);
//		// add image and text in arraylist
//		String Video[]={"xyz","abcd"};
//		String Song[]={"xyz","abcd","pqerttt","yuioo"};
//		String Movi[]={"xyz","abcd","qwer","yuioo","tyyuu","ggggggg","yuioo","tyyuu","ggggggg"};
//		
//		imageArry.add(new Item(R.drawable.ic_launcher, Song));
//		imageArry.add(new Item(R.drawable.icon, Video));
//		imageArry.add(new Item(R.drawable.ic_launcher, Movi));
//		
//		// add data in contact image adapter
//		adapter = new CustomImageAdapter(this, R.layout.ssslist, imageArry);
//		ListView dataList = (ListView) findViewById(R.id.list);
//		dataList.setAdapter(adapter);
//
//	}
//	class CustomImageAdapter extends ArrayAdapter<Item> {
//		Context context;
//		int layoutResourceId;
//		LinearLayout linearMain;
//		ArrayList<Item> data = new ArrayList<Item>();
//
//		public CustomImageAdapter(Context context, int layoutResourceId,
//				ArrayList<Item> data) {
//			super(context, layoutResourceId, data);
//			this.layoutResourceId = layoutResourceId;
//			this.context = context;
//			this.data = data;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			View row = convertView;
//
//			if (row == null) {
//				LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//				row = inflater.inflate(layoutResourceId, parent, false);
//
//				linearMain = (LinearLayout) row.findViewById(R.id.lineraMain);
//
//				Item myImage = data.get(position);
//				for (int j = 0; j < myImage.getName().length; j++) {
//					TextView label = new TextView(context);
//					label.setText(myImage.name[j]);
//					linearMain.addView(label);
//				}
//				ImageView image = new ImageView(context);
//				int outImage = myImage.image;
//				image.setImageResource(outImage);
//				linearMain.addView(image);
//			}
//
//			return row;
//
//		}
//
//
//}
//}
//
