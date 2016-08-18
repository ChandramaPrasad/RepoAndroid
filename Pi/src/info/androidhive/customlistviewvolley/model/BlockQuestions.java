package info.androidhive.customlistviewvolley.model;

public class BlockQuestions {

	/**
	 * "idquestion": 108, "questiondetails": "what is session",
	 * "aboutmyquestion": "session", "subject": "Agriculture", "createddate":
	 * "2016-02-16", "quserid": 90, "qusername": "Divya", "status": "ACTIVE",
	 * "qtypeid": { "idqtype": 1, "qtype": "Paper I (Preliminary)" },
	 * "readstatus": "N", "ratingtypeid": 0, "ratingtypevalue": 1 }
	 */
	private String qusername;
	private long idquestion;
	private String questiondetails;
	private long quserid;

	public long getQuserid() {
		return quserid;
	}

	public void setQuserid(long quserid) {
		this.quserid = quserid;
	}

	public String getQuestiondetails() {
		return questiondetails;
	}

	public void setQuestiondetails(String questiondetails) {
		this.questiondetails = questiondetails;
	}

	public String getQusername() {
		return qusername;
	}

	public void setQusername(String qusername) {
		this.qusername = qusername;
	}

	public long getIdquestion() {
		return idquestion;
	}

	public void setIdquestion(long idquestion) {
		this.idquestion = idquestion;
	}

	@Override
	public String toString() {
		return "BlockQuestions [qusername=" + qusername + ", idquestion="
				+ idquestion + ", questiondetails=" + questiondetails
				+ ", quserid=" + quserid + "]";
	}

}
