//package com.motion.pi;
//
//import info.androidhive.customlistviewvolley.app.AppController;
//import info.androidhive.customlistviewvolley.model.Movie;
//
//import java.util.List;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.toolbox.ImageLoader;
//
//public class CustomListAdapter extends BaseAdapter {
//	private Activity activity;
//	private LayoutInflater inflater;
//	private List<Movie> movieItems;
//	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//
//	public CustomListAdapter(Activity activity, List<Movie> movieItems) {
//		this.activity = activity;
//		this.movieItems = movieItems;
//	}
//
//	@Override
//	public int getCount() {
//		return movieItems.size();
//	}
//
//	@Override
//	public Object getItem(int location) {
//		return movieItems.get(location);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//
//		if (inflater == null)
//			inflater = (LayoutInflater) activity
//					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		if (convertView == null)
//			convertView = inflater.inflate(R.layout.list_row, null);
//
//		if (imageLoader == null)
//			imageLoader = AppController.getInstance().getImageLoader();
////		NetworkImageView thumbNail = (NetworkImageView) convertView
////				.findViewById(R.id.thumbnail);
//		TextView title = (TextView) convertView.findViewById(R.id.title);
////		TextView rating = (TextView) convertView.findViewById(R.id.rating);
////		TextView genre = (TextView) convertView.findViewById(R.id.genre);
//		TextView rate =(TextView)convertView.findViewById(R.id.rt);
//		TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
//
//		/*movie.setAnsusername(obj.getString("ansusername"));
//		movie.setAnswerdetails(obj.getString("answerdetails"));
//		movie.setRatingtypeid(obj.getLong("ratingtypeid"));
//		movie.setAnsuserid(obj.getLong("ansuserid"));*/
//		// getting movie data for the row
//		Movie m = movieItems.get(position);
//
//		// thumbnail image
//		//thumbNail.setImageUrl(null, imageLoader);
//		
//		// title
//		title.setText(m.getQuestiondetails());
////		rate.setText(String.valueOf(m.getRatingtypevalue()));
//		// rating
//		rate.setText( String.valueOf(m.getRatingtypeid()));
//		
//		
//		// genre
//		/*String genreStr = "";
//		for (String str : m.getGenre()) {
//			genreStr += str + ", ";
//		}
//		genreStr = genreStr.length() > 0 ? genreStr.substring(0,
//				genreStr.length() - 2) : genreStr;*/
////		genre.setText(m.getIdanswer()+"");
//		
//		// release year
////		year.setText(String.valueOf(m.getAnswerdetails()));
//
//		return convertView;
//	}
//
//}