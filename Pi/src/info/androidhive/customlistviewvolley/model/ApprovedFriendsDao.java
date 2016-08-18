package info.androidhive.customlistviewvolley.model;

public class ApprovedFriendsDao {

	/**
	 * "idfrndrequest": 115, "srcuserid": 90, "srcusername": "Divya",
	 * "destuserid": 133, "destusername": "Swamy", "reqdate": "2016-03-06",
	 * "approvetime": "2016-03-06", "status": "ACTIVE"
	 */

	private long idfrndrequest;
	private long destuserid;
	private String destusername;
	private long srcuserid;
	private String srcusername;

	public long getSrcuserid() {
		return srcuserid;
	}

	public void setSrcuserid(long srcuserid) {
		this.srcuserid = srcuserid;
	}

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

	public String getSrcusername() {
		return srcusername;
	}

	public void setSrcusername(String srcusername) {
		this.srcusername = srcusername;
	}

	@Override
	public String toString() {
		return "ApprovedFriendsDao [idfrndrequest=" + idfrndrequest
				+ ", destuserid=" + destuserid + ", destusername="
				+ destusername + ", srcuserid=" + srcuserid + ", srcusername="
				+ srcusername + "]";
	}

}
