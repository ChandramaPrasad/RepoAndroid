package info.androidhive.customlistviewvolley.model;

public class AnswerNotificationDao {

	/**
	 * 
	 [ { "idanswer": 318, "answerdetails": "by using help option menu",
	 * "ansuserid": 3, "ansusername": "baburao", "readstatus": "N",
	 * "ratingtypeid": 1, "ratingtypevalue": 5, "ratting": null, "useriddet":
	 * null, "questiontype": null, "questionid": { "idquestion": 369,
	 * "questiondetails": "how to check java versiong", "aboutmyquestion":
	 * "java", "subject": "test", "createddate": "2016-03-02", "quserid": 3,
	 * "qusername": "baburao", "status": "ACTIVE", "qtypeid": { "idqtype": 1,
	 * "qtype": "Paper I (Preliminary)" }, "readstatus": null, "ratingtypeid":
	 * 1, "ratingtypevalue": 1 } } ]
	 */

	private long idanswer;
	private String answerdetails;
	private long idquestion;
	private String questiondetails;

	public long getIdanswer() {
		return idanswer;
	}

	public void setIdanswer(long idanswer) {
		this.idanswer = idanswer;
	}

	public String getAnswerdetails() {
		return answerdetails;
	}

	public void setAnswerdetails(String answerdetails) {
		this.answerdetails = answerdetails;
	}

	public long getIdquestion() {
		return idquestion;
	}

	public void setIdquestion(long idquestion) {
		this.idquestion = idquestion;
	}

	public String getQuestiondetails() {
		return questiondetails;
	}

	public void setQuestiondetails(String questiondetails) {
		this.questiondetails = questiondetails;
	}

	@Override
	public String toString() {
		return "AnswerNotificationDao [idanswer=" + idanswer
				+ ", answerdetails=" + answerdetails + ", idquestion="
				+ idquestion + ", questiondetails=" + questiondetails + "]";
	}

}
