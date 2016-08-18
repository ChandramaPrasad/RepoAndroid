package info.androidhive.customlistviewvolley.model;

public class InboxMailDao {
	/**
	 * "idmail": 20, "srcuserid": 5, "srcusername": "Baburao", "destuserid": 65,
	 * "destusername": "Greet", "status": null, "compdata": "hi greet...",
	 * "readstatus": "N", "attachmentpath": "hi", "reqdate": "2016-02-11"
	 */

	private String srcusername;
	private String reqdate;
	private String compdata;
	private long idmail;

	public long getIdmail() {
		return idmail;
	}

	public void setIdmail(long idmail) {
		this.idmail = idmail;
	}

	public String getSrcusername() {
		return srcusername;
	}

	public void setSrcusername(String srcusername) {
		this.srcusername = srcusername;
	}

	public String getReqdate() {
		return reqdate;
	}

	public void setReqdate(String reqdate) {
		this.reqdate = reqdate;
	}

	public String getCompdata() {
		return compdata;
	}

	public void setCompdata(String compdata) {
		this.compdata = compdata;
	}

	@Override
	public String toString() {
		return "InboxMailDao [srcusername=" + srcusername + ", reqdate="
				+ reqdate + ", compdata=" + compdata + ", idmail=" + idmail
				+ "]";
	}

}
