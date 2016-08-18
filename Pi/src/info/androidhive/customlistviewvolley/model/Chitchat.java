package info.androidhive.customlistviewvolley.model;

public class Chitchat {

	private String srcuserid;
	private String srcusername;
	private String destuserid;
	private String destusername;
	private String chatdesc;
	private String dchatdesc;

	public String getDchatdesc() {
		return dchatdesc;
	}

	public void setDchatdesc(String dchatdesc) {
		this.dchatdesc = dchatdesc;
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

	public String getChatdesc() {
		return chatdesc;
	}

	public void setChatdesc(String chatdesc) {
		this.chatdesc = chatdesc;
	}

	@Override
	public String toString() {
		return "Chitchat [srcuserid=" + srcuserid + ", srcusername="
				+ srcusername + ", destuserid=" + destuserid
				+ ", destusername=" + destusername + ", chatdesc=" + chatdesc
				+ ", dchatdesc=" + dchatdesc + "]";
	}

}
