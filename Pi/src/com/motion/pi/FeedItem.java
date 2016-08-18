package com.motion.pi;


public class FeedItem{




	


	private String fastname;


	private String lastname;


	private String gender;


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


	@Override
	public String toString() {
		return "FeedItem [fastname=" + fastname + ", lastname=" + lastname
				+ ", gender=" + gender + "]";
	}



}

