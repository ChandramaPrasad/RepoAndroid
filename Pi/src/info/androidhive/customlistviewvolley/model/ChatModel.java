package info.androidhive.customlistviewvolley.model;

import java.util.ArrayList;

public class ChatModel {

	private ArrayList<ChatHistory> chatHistories;

	public ArrayList<ChatHistory> getChatHistories() {
		return chatHistories;
	}

	public void setChatHistories(ArrayList<ChatHistory> chatHistories) {
		this.chatHistories = chatHistories;
	}

	@Override
	public String toString() {
		return "ChatModel [chatHistories=" + chatHistories + "]";
	}

}
