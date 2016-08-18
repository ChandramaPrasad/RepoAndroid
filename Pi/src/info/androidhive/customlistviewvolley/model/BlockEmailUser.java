package info.androidhive.customlistviewvolley.model;

public class BlockEmailUser {

	/**
	 * { "idblkl": 15, "srcuserid": 65, "srcusername": "Greet", "destuserid":
	 * 23, "destusername": "naveen", "blacklisttype": "mail",
	 * "blacklistremarks": "mail", "blklistdate": "2016-02-16" }
	 */
	private String destusername;
	private long idblkl;
	private String srcusername;
	private long srcuserid;

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

	public String getDestusername() {
		return destusername;
	}

	public void setDestusername(String destusername) {
		this.destusername = destusername;
	}

	public long getIdblkl() {
		return idblkl;
	}

	public void setIdblkl(long idblkl) {
		this.idblkl = idblkl;
	}

	@Override
	public String toString() {
		return "BlockEmailUser [destusername=" + destusername + ", idblkl="
				+ idblkl + ", srcusername=" + srcusername + ", srcuserid="
				+ srcuserid + "]";
	}

}
