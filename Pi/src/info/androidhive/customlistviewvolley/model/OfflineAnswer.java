package info.androidhive.customlistviewvolley.model;

public class OfflineAnswer {

	private String answer;
	private String username;
	private String rating;
	private String questionid;

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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
		return "OfflineAnswer [answer=" + answer + ", username=" + username
				+ ", rating=" + rating + ", questionid=" + questionid + "]";
	}

}
