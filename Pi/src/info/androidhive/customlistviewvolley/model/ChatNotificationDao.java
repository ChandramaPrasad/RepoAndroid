package info.androidhive.customlistviewvolley.model;


public class ChatNotificationDao {
	/**
	 * "idskywritefrnd": 207, "srcuserid": 3, "destuserid": 135, "chatdesc":
	 * "hi", "chattime": "2016-03-12", "trackingid": 3, "srcusername":
	 * "Baburao", "destusername": "jamesbond", "readstatus": "N"
	 */

	private long srcuserid;
	private long idskywritefrnd;
	private long destuserid;
	private String chatdesc;
	private String srcusername;
	private String destusername;

	public long getSrcuserid() {
		return srcuserid;
	}

	public void setSrcuserid(long srcuserid) {
		this.srcuserid = srcuserid;
	}

	public long getIdskywritefrnd() {
		return idskywritefrnd;
	}

	public void setIdskywritefrnd(long idskywritefrnd) {
		this.idskywritefrnd = idskywritefrnd;
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
		return "ChatNotificationDao [srcuserid=" + srcuserid
				+ ", idskywritefrnd=" + idskywritefrnd + ", destuserid="
				+ destuserid + ", chatdesc=" + chatdesc + ", srcusername="
				+ srcusername + ", destusername=" + destusername + "]";
	}

}
