package info.androidhive.customlistviewvolley.model;

import java.util.ArrayList;

public class LocalModel {

	private static LocalModel instance = null;

	private String questionname;
	private String userId;
	private String firstname;
	private String imagePath;
	private String question;
	private boolean isQuestionChecked;
	private String questionid;
	private boolean isNotification;
	private boolean isAnswerposting;
	private boolean isNewQuestionPosted;

	private boolean isFriendRequestSent;

	public boolean isFriendRequestSent() {
		return isFriendRequestSent;
	}

	public void setFriendRequestSent(boolean isFriendRequestSent) {
		this.isFriendRequestSent = isFriendRequestSent;
	}

	public boolean isNewQuestionPosted() {
		return isNewQuestionPosted;
	}

	public void setNewQuestionPosted(boolean isNewQuestionPosted) {
		this.isNewQuestionPosted = isNewQuestionPosted;
	}

	public boolean isAnswerposting() {
		return isAnswerposting;
	}

	public void setAnswerposting(boolean isAnswerposting) {
		this.isAnswerposting = isAnswerposting;
	}

	private ArrayList<Question> questionArrayList;

	private String alarmMessage;

	public String getAlarmMessage() {
		return alarmMessage;
	}

	public void setAlarmMessage(String alarmMessage) {
		this.alarmMessage = alarmMessage;
	}

	private boolean isQuestionUpdate;
	private boolean isQuestionRatingUpdate;

	public ArrayList<Question> getQuestionArrayList() {
		return questionArrayList;
	}

	public void setQuestionArrayList(ArrayList<Question> questionArrayList) {
		this.questionArrayList = questionArrayList;
	}

	public boolean isQuestionUpdate() {
		return isQuestionUpdate;
	}

	public void setQuestionUpdate(boolean isQuestionUpdate) {
		this.isQuestionUpdate = isQuestionUpdate;
	}

	public boolean isQuestionRatingUpdate() {
		return isQuestionRatingUpdate;
	}

	public void setQuestionRatingUpdate(boolean isQuestionRatingUpdate) {
		this.isQuestionRatingUpdate = isQuestionRatingUpdate;
	}

	public boolean isNotification() {
		return isNotification;
	}

	public void setNotification(boolean isNotification) {
		this.isNotification = isNotification;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public boolean isQuestionChecked() {
		return isQuestionChecked;
	}

	public void setQuestionChecked(boolean isQuestionChecked) {
		this.isQuestionChecked = isQuestionChecked;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	private String lastname;

	public String getQuestionname() {
		return questionname;
	}

	public void setQuestionname(String questionname) {
		this.questionname = questionname;
	}

	private LocalModel() {
		// Exists only to defeat instantiation.
	}

	public static LocalModel getInstance() {
		if (instance == null) {
			instance = new LocalModel();
		}
		return instance;
	}

}
