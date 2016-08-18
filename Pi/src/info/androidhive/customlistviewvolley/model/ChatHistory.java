package info.androidhive.customlistviewvolley.model;

public class ChatHistory {
	/**
	 * [{"idskywritefrnd":151,"srcuserid":90,"destuserid":135,"chatdesc":
	 * "how are you"
	 * ,"chattime":"2016-03-10","trackingid":90,"srcusername":"Divya"
	 * ,"destusername" :"Jameshbond","readstatus":"N"},]
	 */

	private long srcuserid;
	private long destuserid;
	private long idskywritefrnd;

	
	private String chatdesc;
	private String srcusername;
	private String destusername;

	public long getSrcuserid() {
		return srcuserid;
	}
	public long getIdskywritefrnd() {
		return idskywritefrnd;
	}

	public void setIdskywritefrnd(long idskywritefrnd) {
		this.idskywritefrnd = idskywritefrnd;
	}


	public void setSrcuserid(long srcuserid) {
		this.srcuserid = srcuserid;
	}

	public long getDestuserid() {
		return destuserid;
	}

	public void setDestuserid(long destuserid) {
		this.destuserid = destuserid;
	}

	public String getChatdesc() {
		return chatdesc;
	}

	public void setChatdesc(String chatdesc) {
		this.chatdesc = chatdesc;
	}

	public String getSrcusername() {
		return srcusername;
	}

	public void setSrcusername(String srcusername) {
		this.srcusername = srcusername;
	}

	public String getDestusername() {
		return destusername;
	}

	public void setDestusername(String destusername) {
		this.destusername = destusername;
	}

	@Override
	public String toString() {
		return "ChatHistory [srcuserid=" + srcuserid + ", destuserid="
				+ destuserid + ", idskywritefrnd=" + idskywritefrnd
				+ ", chatdesc=" + chatdesc + ", srcusername=" + srcusername
				+ ", destusername=" + destusername + "]";
	}

}
