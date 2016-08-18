package com.motion.pi;

public class JSONAns{



	private String createddate;
	private String answerdetails;
	private String ansuserid;
	private String questiondetails;
	private String questionid;;
//	private String aboutmyquestion;
//	private String qtypeid;
//	private String createddate;

	
	public String getCreateddate() {
		return createddate;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getQuestiondetails() {
		return questiondetails;
	}

	public void setQuestiondetails(String questiondetails) {
		this.questiondetails = questiondetails;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getAnswerdetails() {
		return answerdetails;
	}

	public void setAnswerdetails(String answerdetails) {
		this.answerdetails = answerdetails;
	}

	public String getAnsuserid() {
		return ansuserid;
	}

	public void setAnsuserid(String ansuserid) {
		this.ansuserid = ansuserid;
	}

	@Override
	public String toString() {
		return "JSONAns [createddate=" + createddate + ", answerdetails="
				+ answerdetails + ", ansuserid=" + ansuserid
				+ ", questiondetails=" + questiondetails + ", questionid="
				+ questionid + "]";
	}


	
}
