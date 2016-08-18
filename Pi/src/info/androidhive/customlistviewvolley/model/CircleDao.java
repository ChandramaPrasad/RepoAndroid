package info.androidhive.customlistviewvolley.model;

public class CircleDao {

	private String userid;
	private String imagepath;
	private String idsignup;

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

	@Override
	public String toString() {
		return "Circle [userid=" + userid + ", imagepath=" + imagepath
				+ ", idsignup=" + idsignup + "]";
	}

	public String getIdsignup() {
		return idsignup;
	}

	public void setIdsignup(String idsignup) {
		this.idsignup = idsignup;
	}

}
