package com.motion.pi;


public class Edts {
	private long idanswer;


	private String answerdetails;
	private String questiondetails;

	private long ansuserid;


	private String fastname;  
	//	private String passwd;
	//	private String lastname;  
	//	private String aboutme; 
	//	private String emailid;  
	//	private String mobileno; 
	//	private String qualification;  
	//	private String country; 
	//	private String city; 
	//	private String qualification;  
	//	private String country; 


	//	private Usersignup useriddet;
	//
	//
	//
	//	private QuestionType questiontype;
	//
	//
	//
	//	private Questionhistory questionid;



	/*public Movie(String name, String thumbnailUrl, int year, double rating,
			ArrayList<String> genre) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.year = year;
		this.rating = rating;
		this.genre = genre;
	}*/

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

	public String getQuestiondetails() {
		return questiondetails;
	}

	public void setQuestiondetails(String questiondetails) {
		this.questiondetails = questiondetails;
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
	 * @return the ansusername
	 */
	public String getAnsusername() {
		return fastname;
	}

	/**
	 * @param ansusername the ansusername to set
	 */
	public void setAnsusername(String fastname) {
		this.fastname = fastname;
	}

	//	public void setQuestionid(String string, String passwd) {
	//		// TODO Auto-generated method stub
	//		this.passwd = passwd;
	//	}


}
