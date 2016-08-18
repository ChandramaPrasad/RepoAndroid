package info.androidhive.customlistviewvolley.model;

public class MyFriendRequestDao {

	private long idfrndrequest;
	private long destuserid;
	private String destusername;

	public long getIdfrndrequest() {
		return idfrndrequest;
	}

	public void setIdfrndrequest(long idfrndrequest) {
		this.idfrndrequest = idfrndrequest;
	}

	public long getDestuserid() {
		return destuserid;
	}

	public void setDestuserid(long destuserid) {
		this.destuserid = destuserid;
	}

	public String getDestusername() {
		return destusername;
	}

	public void setDestusername(String destusername) {
		this.destusername = destusername;
	}

	@Override
	public String toString() {
		return "MyFriendRequestDao [idfrndrequest=" + idfrndrequest
				+ ", destuserid=" + destuserid + ", destusername="
				+ destusername + "]";
	}

}
