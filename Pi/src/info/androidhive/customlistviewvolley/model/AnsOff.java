package info.androidhive.customlistviewvolley.model;

public class AnsOff {

	private String ansUsername;
	private String answer;
	private String ansRating;

	public String getAnsUsername() {
		return ansUsername;
	}

	public void setAnsUsername(String ansUsername) {
		this.ansUsername = ansUsername;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnsRating() {
		return ansRating;
	}

	public void setAnsRating(String ansRating) {
		this.ansRating = ansRating;
	}

	@Override
	public String toString() {
		return "AnsOff [ansUsername=" + ansUsername + ", answer=" + answer
				+ ", ansRating=" + ansRating + "]";
	}

}
