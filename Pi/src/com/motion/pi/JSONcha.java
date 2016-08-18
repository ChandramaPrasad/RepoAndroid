package com.motion.pi;

import java.util.List;

public class JSONcha {


	List<JSONchat> jsonBean;

	public List<JSONchat> getJsonBean() {
		return jsonBean;
	}

	public void setJsonBean(List<JSONchat> jsonBean) {
		this.jsonBean = jsonBean;
	}

	@Override
	public String toString() {
		return "JSONBean [jsonBean=" + jsonBean + "]";
	}
	
	
}
