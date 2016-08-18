package com.motion.pi;

public class JSONBeanGenarte {

	

	private String idqtype;
	  private String qtype;
	public String getIdqtype() {
		return idqtype;
	}
	public void setIdqtype(String idqtype) {
		this.idqtype = idqtype;
	}
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	  
	  
	  @Override
	public String toString() {
		return "JSONBeanGenarte [idqtype=" + idqtype + ", qtype=" + qtype + "]";
	}  
}
