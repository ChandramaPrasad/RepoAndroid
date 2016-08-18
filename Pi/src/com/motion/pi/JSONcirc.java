package com.motion.pi;

import java.util.List;

public class JSONcirc {


	List<JSONcircle> jsonBean;

	public List<JSONcircle> getJsonBean() {
		return jsonBean;
	}

	public void setJsonBean(List<JSONcircle> jsonBean) {
		this.jsonBean = jsonBean;
	}

	@Override
	public String toString() {
		return "JSONBean [jsonBean=" + jsonBean + "]";
	}
	
	
}
