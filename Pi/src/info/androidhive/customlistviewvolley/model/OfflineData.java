package info.androidhive.customlistviewvolley.model;

public class OfflineData {

	private String keyword;
	private String question;
	private String username;
	private String rating;
	private String questionid;

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "OfflineData [keyword=" + keyword + ", question=" + question
				+ ", username=" + username + ", rating=" + rating
				+ ", questionid=" + questionid + "]";
	}

}
