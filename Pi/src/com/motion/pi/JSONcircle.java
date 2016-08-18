package com.motion.pi;

public class JSONcircle {

	/**
	 * Json response: "idsignup": 116, "userid": "Rajesh", "passwd": "123",
	 * "fastname": "Rajesh", "lastname": "Ram", "gender": "M", "aboutme": "hi",
	 * "dob": "2016-03-03", "status": "ACTIVE", "emailid":
	 * "prasad.c@motionpixel.co.in", "mobileno": "123456789", "createddate":
	 * "2016-03-03", "modifieddate": "2016-03-03", "imagepath": "hi",
	 * "qualification": "mac", "country": "india", "city": "hyd", "islogined":
	 * null
	 */

	private String userid;
	private String imagepath;
	private String idsignup;
	private String mobileno;
	private String emailid;

	public String getIdsignup() {
		return idsignup;
	}

	public void setIdsignup(String idsignup) {
		this.idsignup = idsignup;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Override
	public String toString() {
		return "JSONcircle [userid=" + userid + ", imagepath=" + imagepath
				+ ", idsignup=" + idsignup + ", mobileno=" + mobileno
				+ ", emailid=" + emailid + "]";
	}

}
