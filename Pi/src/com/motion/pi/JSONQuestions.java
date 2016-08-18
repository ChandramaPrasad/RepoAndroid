package com.motion.pi;

public class JSONQuestions {



	private String idquestion;
	private String questiondetails;
	private String quserid;
	private String aboutmyquestion;

	private String qusername;
	private String createddate;

	public String getIdquestion() {
		return idquestion;
	}

	public void setIdquestion(String idquestion) {
		this.idquestion = idquestion;
	}

	public String getQuestiondetails() {
		return questiondetails;
	}

	public void setQuestiondetails(String questiondetails) {
		this.questiondetails = questiondetails;
	}

	public String getQuserid() {
		return quserid;
	}

	public void setQuserid(String quserid) {
		this.quserid = quserid;
	}

	public String getAboutmyquestion() {
		return aboutmyquestion;
	}

	public void setAboutmyquestion(String aboutmyquestion) {
		this.aboutmyquestion = aboutmyquestion;
	}

	public String getQusername() {
		return qusername;
	}

	public void setQusername(String qusername) {
		this.qusername = qusername;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	@Override
	public String toString() {
		return "JSONQuestions{" +
				"idquestion='" + idquestion + '\'' +
				", questiondetails='" + questiondetails + '\'' +
				", quserid='" + quserid + '\'' +
				", aboutmyquestion='" + aboutmyquestion + '\'' +
				", qusername='" + qusername + '\'' +
				", createddate='" + createddate + '\'' +
				'}';
	}
}
