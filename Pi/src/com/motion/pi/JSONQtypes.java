package com.motion.pi;

public class JSONQtypes {



	private String idquestion;
	private String questiondetails;
	private String userid;
	private String aboutmyquestion;
	private String qtypeid;
	private String createddate;


//	public String getIdqtype() {
//		return idqtype;
//	}
//	public void setIdqtype(String idqtype) {
//		this.idqtype = idqtype;
//	}
//	public String getQtype() {
//		return qtype;
//	}
//	public void setQtype(String qtype) {
//		this.qtype = qtype;
//	}
//

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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAboutmyquestion() {
		return aboutmyquestion;
	}
	public void setAboutmyquestion(String aboutmyquestion) {
		this.aboutmyquestion = aboutmyquestion;
	}
	public String getQtypeid() {
		return qtypeid;
	}
	public void setQtypeid(String qtypeid) {
		this.qtypeid = qtypeid;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	@Override
	public String toString() {
		return "JSONQuestions [idquestion=" + idquestion + ", questiondetails="
				+ questiondetails + ", userid=" + userid + ", aboutmyquestion="
				+ aboutmyquestion + ", qtypeid=" + qtypeid + ", createddate="
				+ createddate + "]";
	}

}
