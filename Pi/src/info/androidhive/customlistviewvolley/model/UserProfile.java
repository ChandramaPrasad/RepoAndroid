package info.androidhive.customlistviewvolley.model;

public class UserProfile {

	/**
	 * 
	 { "idsignup": 136, "userid": "Chunk", "passwd": "1234", "fastname":
	 * "Ram", "lastname": "Gopal", "gender": "Male", "aboutme":
	 * "android developer", "dob": "2016-03-06", "status": "ACTIVE", "emailid":
	 * "baburao@motionpixel.co.in", "mobileno": "8801108426", "createddate":
	 * "2016-03-06", "modifieddate": "2016-03-06", "imagepath": "Chunk",
	 * "qualification": "Degree", "country": "India", "city": "Hyderabad",
	 * "islogined": null, "pincode": "442917", "occupation": "working",
	 * "earliersuccess": "hi", "successtips": "h", "yr": "90" }
	 */

	private long idsignup;
	private String userid;
	private String passwd;
	private String fastname;
	private String lastname;
	private String gender;
	private String aboutme;
	private String dob;
	private String emailid;
	private String mobileno;
	private String qualification;
	private String country;
	private String city;
	private String pincode;
	private String occupation;
	private String earliersuccess;
	private String successtips;
	private String yr;

	public long getIdsignup() {
		return idsignup;
	}

	public void setIdsignup(long idsignup) {
		this.idsignup = idsignup;
	}

	public UserProfile() {
		super();
	}

	public String getUserid() {
		return userid;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEarliersuccess() {
		return earliersuccess;
	}

	public void setEarliersuccess(String earliersuccess) {
		this.earliersuccess = earliersuccess;
	}

	public String getSuccesstips() {
		return successtips;
	}

	public void setSuccesstips(String successtips) {
		this.successtips = successtips;
	}

	public String getYr() {
		return yr;
	}

	public void setYr(String yr) {
		this.yr = yr;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAboutme() {
		return aboutme;
	}

	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
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

	@Override
	public String toString() {
		return "UserProfile [idsignup=" + idsignup + ", userid=" + userid
				+ ", passwd=" + passwd + ", fastname=" + fastname
				+ ", lastname=" + lastname + ", gender=" + gender
				+ ", aboutme=" + aboutme + ", dob=" + dob + ", emailid="
				+ emailid + ", mobileno=" + mobileno + ", qualification="
				+ qualification + ", country=" + country + ", city=" + city
				+ ", pincode=" + pincode + ", occupation=" + occupation
				+ ", earliersuccess=" + earliersuccess + ", successtips="
				+ successtips + ", yr=" + yr + "]";
	}

}
