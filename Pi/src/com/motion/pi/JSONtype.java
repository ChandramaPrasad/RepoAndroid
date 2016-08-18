package com.motion.pi;

import java.util.List;

public class JSONtype {


	List<JSONQtypes> jsonBean;

	public List<JSONQtypes> getJsonBean() {
		return jsonBean;
	}

	public void setJsonBean(List<JSONQtypes> jsonBean) {
		this.jsonBean = jsonBean;
	}

	@Override
	public String toString() {
		return "JSONBean [jsonBean=" + jsonBean + "]";
	}
	
	
}
