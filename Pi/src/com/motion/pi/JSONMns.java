package com.motion.pi;

public class JSONMns {



	private String idmail;
	private String srcuserid;
	private String srcusername;
	private String destuserid;
	private String compdata;
	private String destusername;
	private String reqdate;
	
	public String getCompdata() {
		return compdata;
	}
	public void setCompdata(String compdata) {
		this.compdata = compdata;
	}
	public String getIdmail() {
		return idmail;
	}
	public void setIdmail(String idmail) {
		this.idmail = idmail;
	}
	public String getSrcuserid() {
		return srcuserid;
	}
	public void setSrcuserid(String srcuserid) {
		this.srcuserid = srcuserid;
	}
	public String getSrcusername() {
		return srcusername;
	}
	public void setSrcusername(String srcusername) {
		this.srcusername = srcusername;
	}
	public String getDestuserid() {
		return destuserid;
	}
	public void setDestuserid(String destuserid) {
		this.destuserid = destuserid;
	}
	public String getDestusername() {
		return destusername;
	}
	public void setDestusername(String destusername) {
		this.destusername = destusername;
	}
	public String getReqdate() {
		return reqdate;
	}
	public void setReqdate(String reqdate) {
		this.reqdate = reqdate;
	}
	@Override
	public String toString() {
		return "JSONMns [idmail=" + idmail + ", srcuserid=" + srcuserid
				+ ", srcusername=" + srcusername + ", destuserid=" + destuserid
				+ ", compdata=" + compdata + ", destusername=" + destusername
				+ ", reqdate=" + reqdate + "]";
	}
	
	

}
