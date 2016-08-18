package info.androidhive.customlistviewvolley.model;

public class Skywrite {

	/**
	 * { "idfrndrequest": 93, "srcuserid": 65, "srcusername": "Tarun",
	 * "destuserid": 3, "destusername": "baburao", "reqdate": "2016-02-29",
	 * "approvetime": "2016-02-29", "status": "ACTIVE" }
	 */
	private long idfrndrequest;
	private long destuserid;
	private long srcuserid;
	private String srcusername;

	public long getSrcuserid() {
		return srcuserid;
	}

	public void setSrcuserid(long srcuserid) {
		this.srcuserid = srcuserid;
	}

	public String getSrcusername() {
		return srcusername;
	}

	public void setSrcusername(String srcusername) {
		this.srcusername = srcusername;
	}

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
		return "Skywrite [idfrndrequest=" + idfrndrequest + ", destuserid="
				+ destuserid + ", srcuserid=" + srcuserid + ", srcusername="
				+ srcusername + ", destusername=" + destusername + "]";
	}

}