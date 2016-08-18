package com.motion.pi;

public class Frndslist {



	private String userid;
	private String idsignup;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getIdsignup() {
		return idsignup;
	}
	public void setIdsignup(String idsignup) {
		this.idsignup = idsignup;
	}
	@Override
	public String toString() {
		return "Frndslist [userid=" + userid + ", idsignup=" + idsignup + "]";
	}
	
}
