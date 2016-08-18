package info.androidhive.customlistviewvolley.model;

public class Question {
	/**
	 * "idquestion": 91, "questiondetails": "what is virtual",
	 * "aboutmyquestion": "virtual", "createddate": "2016-01-27", "quserid": 65,
	 * "qusername": "Greet",
	 */
	private String questiondetails;
	private String aboutmyquestion;
	private String createddate;
	private String qusername;
	private long idquestion;
	private long ratingtypevalue;
	private String subject;
	private long quserid;

	public long getQuserid() {
		return quserid;
	}

	public void setQuserid(long quserid) {
		this.quserid = quserid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public long getRatingtypevalue() {
		return ratingtypevalue;
	}

	public void setRatingtypevalue(long ratingtypevalue) {
		this.ratingtypevalue = ratingtypevalue;
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

	public String getAboutmyquestion() {
		return aboutmyquestion;
	}

	public void setAboutmyquestion(String aboutmyquestion) {
		this.aboutmyquestion = aboutmyquestion;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getQusername() {
		return qusername;
	}

	public void setQusername(String qusername) {
		this.qusername = qusername;
	}

	@Override
	public String toString() {
		return "Question [questiondetails=" + questiondetails
				+ ", aboutmyquestion=" + aboutmyquestion + ", createddate="
				+ createddate + ", qusername=" + qusername + ", idquestion="
				+ idquestion + ", ratingtypevalue=" + ratingtypevalue
				+ ", subject=" + subject + ", quserid=" + quserid + "]";
	}

}
