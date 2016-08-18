package info.androidhive.customlistviewvolley.adater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.motion.pi.R;

public class QuestionAnswerAdapter extends BaseAdapter {

	private Context context;

	public QuestionAnswerAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		System.out.println("Enter");
		return 2;
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
		// TODO Auto-generated method stub

		if (convertView == null) {

			convertView = LayoutInflater.from(context).inflate(
					R.layout.question_answer_notification_item_layout, null);

		}

		TextView questioTextView = (TextView) convertView
				.findViewById(R.id.questTextView);

		TextView countTextView = (TextView) convertView
				.findViewById(R.id.countTextView);
		return convertView;
	}
}
