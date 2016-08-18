package info.androidhive.customlistviewvolley.model;

public class UserList {

	/*
	 * 
	 * "idsignup": 65, "userid": "Greet", "passwd": "123", "fastname": "Greet",
	 * "lastname": "Fggg", "gender": "genders", "aboutme": "gfghaaaaaaaaaaaa",
	 * "dob": "2016-01-20", "status": "ACTIVE", "emailid":
	 * "cprasad362@gmail.com", "mobileno": "8801108425", "createddate":
	 * "2016-01-08", "modifieddate": "2016-01-20", "imagepath": "null.jpg",
	 * "qualification": "MBBS", "country": "India", "city": "Bangalore",
	 */

	private long idsignup;
	private String userid;
	private String fastname;
	private String lastname;
	private String emailid;
	private String qualification;
	private String country;
	private String city;
	private String mobileno;
	private String imagepath;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public UserList() {
		super();
	}

	public long getIdsignup() {
		return idsignup;
	}

	public void setIdsignup(long idsignup) {
		this.idsignup = idsignup;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFastname() {
		return fastname;
	}

	public void setFastname(String fastname) {
		this.fastname = fastname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	@Override
	public String toString() {
		return "UserList [idsignup=" + idsignup + ", userid=" + userid
				+ ", fastname=" + fastname + ", lastname=" + lastname
				+ ", emailid=" + emailid + ", qualification=" + qualification
				+ ", country=" + country + ", city=" + city + ", mobileno="
				+ mobileno + ", imagepath=" + imagepath + ", status=" + status
				+ "]";
	}

}
