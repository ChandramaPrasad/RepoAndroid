package info.androidhive.customlistviewvolley.model;

public class AnsNotificationCount {

	private String questionid;
	private String cnt;
	private String questiondetails;

	public String getQuestiondetails() {
		return questiondetails;
	}

	public void setQuestiondetails(String questiondetails) {
		this.questiondetails = questiondetails;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "AnsNotificationCount [questionid=" + questionid + ", cnt="
				+ cnt + ", questiondetails=" + questiondetails + "]";
	}

}
