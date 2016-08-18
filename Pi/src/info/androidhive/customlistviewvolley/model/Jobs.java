package info.androidhive.customlistviewvolley.model;

public class Jobs {

	/**
	 * { "idadm": 1, "advdetails": "EMCET exam", "advtitle": "Exams",
	 * "destusername": "balaram", "destuserid": 1, "readstatus": "N", "reqdate":
	 * "2016-01-03", "expitydate": "2015-01-04" }
	 */

	private String advdetails;
	private String advtitle;
	private String destusername;
	private String readstatus;
	private String reqdate;
	private String expitydate;
	private long idadm;
	private long destuserid;

	public long getDestuserid() {
		return destuserid;
	}

	public void setDestuserid(long destuserid) {
		this.destuserid = destuserid;
	}

	public long getIdadm() {
		return idadm;
	}

	public void setIdadm(long idadm) {
		this.idadm = idadm;
	}

	public String getAdvdetails() {
		return advdetails;
	}

	public void setAdvdetails(String advdetails) {
		this.advdetails = advdetails;
	}

	public String getAdvtitle() {
		return advtitle;
	}

	public void setAdvtitle(String advtitle) {
		this.advtitle = advtitle;
	}

	public String getDestusername() {
		return destusername;
	}

	public void setDestusername(String destusername) {
		this.destusername = destusername;
	}

	public String getReadstatus() {
		return readstatus;
	}

	public void setReadstatus(String readstatus) {
		this.readstatus = readstatus;
	}

	public String getReqdate() {
		return reqdate;
	}

	public void setReqdate(String reqdate) {
		this.reqdate = reqdate;
	}

	public String getExpitydate() {
		return expitydate;
	}

	public void setExpitydate(String expitydate) {
		this.expitydate = expitydate;
	}

	@Override
	public String toString() {
		return "Jobs [advdetails=" + advdetails + ", advtitle=" + advtitle
				+ ", destusername=" + destusername + ", readstatus="
				+ readstatus + ", reqdate=" + reqdate + ", expitydate="
				+ expitydate + ", idadm=" + idadm + ", destuserid="
				+ destuserid + "]";
	}

}
