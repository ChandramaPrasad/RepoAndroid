package com.motion.pi;

public class JSONSentss {



	private String destusername;
	private String compdata;
	private String reqdate;
	public String getDestusername() {
		return destusername;
	}
	public void setDestusername(String destusername) {
		this.destusername = destusername;
	}
	public String getCompdata() {
		return compdata;
	}
	public void setCompdata(String compdata) {
		this.compdata = compdata;
	}
	
	public String getReqdate() {
		return reqdate;
	}
	public void setReqdate(String reqdate) {
		this.reqdate = reqdate;
	}
	@Override
	public String toString() {
		return "JSONSentss [destusername=" + destusername + ", compdata="
				+ compdata + ", reqdate=" + reqdate + "]";
	}
	
	
	
}
