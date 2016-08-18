package com.motion.chat.adapter;

import info.androidhive.customlistviewvolley.model.ChatHistory;

import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.motion.pi.R;

public class ChatMessageAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<ChatHistory> chatHistory;
	private String userid;

	public ChatMessageAdapter(Context context,
			ArrayList<ChatHistory> chatMessages, String userid) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.chatHistory = chatMessages;
		this.userid = userid;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return chatHistory.size();
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

		ViewHolder viewHolder;

		if (convertView == null) {

			convertView = LayoutInflater.from(context).inflate(
					R.layout.chat_layout, null);
			viewHolder = new ViewHolder();
			viewHolder.messageTextView = (TextView) convertView
					.findViewById(R.id.SingleMessage);
			viewHolder.messageLinearLayout = (LinearLayout) convertView
					.findViewById(R.id.Message1);
			convertView.setTag(viewHolder);

		}

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);

		// notifyDataSetChanged();

		viewHolder = (ViewHolder) convertView.getTag();

		viewHolder.messageTextView = (TextView) convertView
				.findViewById(R.id.SingleMessage);

		viewHolder.messageLinearLayout = (LinearLayout) convertView
				.findViewById(R.id.Message1);

		if (String.valueOf(chatHistory.get(position).getSrcuserid()).equals(
				userid)) {

			params.gravity = Gravity.RIGHT;
			viewHolder.messageTextView.setText(chatHistory.get(position)
					.getChatdesc().toString());
			viewHolder.messageTextView
					.setBackgroundResource(R.drawable.bubble_b);

		} else {
			params.gravity = Gravity.LEFT;
			viewHolder.messageTextView.setText(chatHistory.get(position)
					.getChatdesc().toString());
			viewHolder.messageTextView
					.setBackgroundResource(R.drawable.bubble_a);

		}

		viewHolder.messageLinearLayout.setLayoutParams(params);

		return convertView;
	}

	private class ViewHolder {

		private TextView messageTextView;
		private LinearLayout messageLinearLayout;

	}
}
