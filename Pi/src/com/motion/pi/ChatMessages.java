package com.motion.pi;

import info.androidhive.customlistviewvolley.model.ChatHistory;

import java.util.ArrayList;

public class ChatMessages {

	public boolean left;
	public String message;
	public ArrayList<ChatHistory> chatHistories;

	public ChatMessages(boolean left, String message,
			ArrayList<ChatHistory> chatHistories) {
		// TODO Auto-generated constructor stub
		super();
		this.left = left;
		this.message = message;
		this.chatHistories = chatHistories;
	}

}
