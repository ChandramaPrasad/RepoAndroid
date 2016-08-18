package com.motion.pi;

public class JSONchat {



	private String srcusername;
	private String destusername;
	private String chatdesc;
	private String chattime;
	
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
	public String getChatdesc() {
		return chatdesc;
	}
	public void setChatdesc(String chatdesc) {
		this.chatdesc = chatdesc;
	}
	public String getChattime() {
		return chattime;
	}
	public void setChattime(String chattime) {
		this.chattime = chattime;
	}
	@Override
	public String toString() {
		return "JSONchat [srcusername=" + srcusername + ", destusername="
				+ destusername + ", chatdesc=" + chatdesc + ", chattime="
				+ chattime + "]";
	}
	
	
}
