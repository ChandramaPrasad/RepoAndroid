package info.androidhive.customlistviewvolley.model;

public class Movie {
	private long idanswer;
	private long idquestion;
	String advtitle;

	private String answerdetails;
	private String questiondetails;

	private long ansuserid;
	String imagepath;
	private String createddate;

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	private String ansusername;
	private String qusername;

	public long getIdquestion() {
		return idquestion;
	}

	public String getAdvtitle() {
		return advtitle;
	}

	public void setAdvtitle(String advtitle) {
		this.advtitle = advtitle;
	}

	public void setIdquestion(long idquestion) {
		this.idquestion = idquestion;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getQusername() {
		return qusername;
	}

	public void setQusername(String qusername) {
		this.qusername = qusername;
	}

	private long ratingtypeid;

	private long ratingtypevalue;

	private Ratting ratting;

	private Usersignup useriddet;

	private QuestionType questiontype;

	private Questionhistory questionid;
	private long idqtype;
	private String aboutmyquestion;
	private long quserid;

	public long getQuserid() {
		return quserid;
	}

	public void setQuserid(long quserid) {
		this.quserid = quserid;
	}

	public String getAboutmyquestion() {
		return aboutmyquestion;
	}

	public void setAboutmyquestion(String aboutmyquestion) {
		this.aboutmyquestion = aboutmyquestion;
	}

	public long getIdqtype() {
		return idqtype;
	}

	public void setIdqtype(long idqtype) {
		this.idqtype = idqtype;
	}

	public Movie() {
	}

	/*
	 * public Movie(String name, String thumbnailUrl, int year, double rating,
	 * ArrayList<String> genre) { this.title = name; this.thumbnailUrl =
	 * thumbnailUrl; this.year = year; this.rating = rating; this.genre = genre;
	 * }
	 */

	/**
	 * @return the idanswer
	 */
	public long getIdanswer() {
		return idanswer;
	}

	/**
	 * @param idanswer
	 *            the idanswer to set
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
	 * @param answerdetails
	 *            the answerdetails to set
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
	 * @param ansuserid
	 *            the ansuserid to set
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
	 * @param ratingtypeid
	 *            the ratingtypeid to set
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
	 * @param ratingtypevalue
	 *            the ratingtypevalue to set
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
	 * @param ratting
	 *            the ratting to set
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
	 * @param useriddet
	 *            the useriddet to set
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
	 * @param questiontype
	 *            the questiontype to set
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
	 * @param questionid
	 *            the questionid to set
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
	 * @param ansusername
	 *            the ansusername to set
	 */
	public void setAnsusername(String ansusername) {
		this.ansusername = ansusername;
	}

	public void setQuestionid(String string, String qusername) {
		// TODO Auto-generated method stub
		this.qusername = qusername;
	}

	@Override
	public String toString() {
		return "Movie [idanswer=" + idanswer + ", idquestion=" + idquestion
				+ ", advtitle=" + advtitle + ", answerdetails=" + answerdetails
				+ ", questiondetails=" + questiondetails + ", ansuserid="
				+ ansuserid + ", imagepath=" + imagepath + ", createddate="
				+ createddate + ", ansusername=" + ansusername + ", qusername="
				+ qusername + ", ratingtypeid=" + ratingtypeid
				+ ", ratingtypevalue=" + ratingtypevalue + ", ratting="
				+ ratting + ", useriddet=" + useriddet + ", questiontype="
				+ questiontype + ", questionid=" + questionid + ", idqtype="
				+ idqtype + ", aboutmyquestion=" + aboutmyquestion
				+ ", quserid=" + quserid + "]";
	}

}
