package com.motion.pi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomEmojis extends BaseAdapter{
	private Activity activity;
	private static LayoutInflater inflater = null;
	
	public final int[] images = new int[] { R.drawable.a1, R.drawable.a2,
			R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
			R.drawable.a9, R.drawable.a8, R.drawable.a7,
			R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13,
			R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17,
			R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a21,
			R.drawable.a22, R.drawable.a23, R.drawable.a24, R.drawable.a25,
			R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17,
			R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a21,
			R.drawable.a22, R.drawable.a23, R.drawable.a24, R.drawable.a25,
			R.drawable.l26,R.drawable.l25,R.drawable.l24,};
	
	public CustomEmojis(Activity act) {
		activity = act;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.grid_row, null);
			holder.imageView = (ImageView)convertView.findViewById(R.id.imageView1);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.imageView.setImageResource(images[position]);
		return convertView;
	}
	public static class ViewHolder{
		public ImageView imageView;
	}

}
