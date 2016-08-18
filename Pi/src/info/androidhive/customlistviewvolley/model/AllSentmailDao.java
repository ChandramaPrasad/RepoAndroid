package info.androidhive.customlistviewvolley.model;

public class AllSentmailDao {

	private String srcusername;
	private String reqdate;
	private String compdata;
	private String destusername;
	private long idmail;

	public long getIdmail() {
		return idmail;
	}

	public void setIdmail(long idmail) {
		this.idmail = idmail;
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
		return "AllSentmailDao [srcusername=" + srcusername + ", reqdate="
				+ reqdate + ", compdata=" + compdata + ", destusername="
				+ destusername + ", idmail=" + idmail + "]";
	}

}
