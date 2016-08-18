/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.motion.pi;



/**
 *
 * @author dipti
 */

public class AnswerHistory {


	private long idanswer;


	private String answerdetails;


	private long ansuserid;


	private String ansusername;  


	private long ratingtypeid;


	private long ratingtypevalue;


	private Ratting ratting;


	private Usersignup useriddet;



	private QuestionType questiontype;



	private Questionhistory questionid;

	private String createddate;

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	/**
	 * @return the idanswer
	 */
	public long getIdanswer() {
		return idanswer;
	}

	/**
	 * @param idanswer the idanswer to set
	 */
	public void setIdanswer(long idanswer) {
		this.idanswer = idanswer;
	}

	/**
	 * @return the answerdetails
	 */
	public String getAnswerdetails() {
		return answerdetails;
	}

	/**
	 * @param answerdetails the answerdetails to set
	 */
	public void setAnswerdetails(String answerdetails) {
		this.answerdetails = answerdetails;
	}

	/**
	 * @return the ansuserid
	 */
	public long getAnsuserid() {
		return ansuserid;
	}

	/**
	 * @param ansuserid the ansuserid to set
	 */
	public void setAnsuserid(long ansuserid) {
		this.ansuserid = ansuserid;
	}



	/**
	 * @return the ratingtypeid
	 */
	public long getRatingtypeid() {
		return ratingtypeid;
	}

	/**
	 * @param ratingtypeid the ratingtypeid to set
	 */
	public void setRatingtypeid(long ratingtypeid) {
		this.ratingtypeid = ratingtypeid;
	}

	/**
	 * @return the ratingtypevalue
	 */
	public long getRatingtypevalue() {
		return ratingtypevalue;
	}

	/**
	 * @param ratingtypevalue the ratingtypevalue to set
	 */
	public void setRatingtypevalue(long ratingtypevalue) {
		this.ratingtypevalue = ratingtypevalue;
	}

	/**
	 * @return the ratting
	 */
	public Ratting getRatting() {
		return ratting;
	}

	/**
	 * @param ratting the ratting to set
	 */
	public void setRatting(Ratting ratting) {
		this.ratting = ratting;
	}

	/**
	 * @return the useriddet
	 */
	public Usersignup getUseriddet() {
		return useriddet;
	}

	/**
	 * @param useriddet the useriddet to set
	 */
	public void setUseriddet(Usersignup useriddet) {
		this.useriddet = useriddet;
	}

	/**
	 * @return the questiontype
	 */
	public QuestionType getQuestiontype() {
		return questiontype;
	}

	/**
	 * @param questiontype the questiontype to set
	 */
	public void setQuestiontype(QuestionType questiontype) {
		this.questiontype = questiontype;
	}

	/**
	 * @return the questionid
	 */
	public Questionhistory getQuestionid() {
		return questionid;
	}

	/**
	 * @param questionid the questionid to set
	 */
	public void setQuestionid(Questionhistory questionid) {
		this.questionid = questionid;
	}

	/**
	 * @return the ansusername
	 */
	public String getAnsusername() {
		return ansusername;
	}

	/**
	 * @param ansusername the ansusername to set
	 */
	public void setAnsusername(String ansusername) {
		this.ansusername = ansusername;
	}

}
