package com.motion.adapters;

import info.androidhive.customlistviewvolley.model.AnsNotificationCount;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.motion.pi.R;

public class AnswerNotificationAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<AnsNotificationCount> ansNotificationCountArrayList;

	public AnswerNotificationAdapter(Context context,
			ArrayList<AnsNotificationCount> ansNotificationCountArrayList) {
		// TODO Auto-generated constructor stub

		this.context = context;
		this.ansNotificationCountArrayList = ansNotificationCountArrayList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ansNotificationCountArrayList.size();
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
					R.layout.question_notification_item_layout, null);

		}

		TextView questionTextView = (TextView) convertView
				.findViewById(R.id.questionTextView);

		questionTextView.setText(ansNotificationCountArrayList.get(position)
				.getQuestiondetails().toString());
		return convertView;
	}
}
