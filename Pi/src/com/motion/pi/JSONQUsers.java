package com.motion.pi;

import java.util.List;

public class JSONQUsers {


	List<JSONQuestions> jsonBean;

	public List<JSONQuestions> getJsonBean() {
		return jsonBean;
	}

	public void setJsonBean(List<JSONQuestions> jsonBean) {
		this.jsonBean = jsonBean;
	}

	@Override
	public String toString() {
		return "JSONBean [jsonBean=" + jsonBean + "]";
	}
	
	
}
