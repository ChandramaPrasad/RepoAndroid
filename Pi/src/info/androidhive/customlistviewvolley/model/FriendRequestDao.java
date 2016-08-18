package info.androidhive.customlistviewvolley.model;

public class FriendRequestDao {

	private long idfrndrequest;
	private String srcusername;
	private String destusername;

	public long getIdfrndrequest() {
		return idfrndrequest;
	}

	public void setIdfrndrequest(long idfrndrequest) {
		this.idfrndrequest = idfrndrequest;
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

}
