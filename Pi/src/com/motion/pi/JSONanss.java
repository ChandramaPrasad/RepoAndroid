package com.motion.pi;

import java.util.List;

public class JSONanss {


	List<AnswerHistory> jsonBean;

	public List<AnswerHistory> getJsonBean() {
		return jsonBean;
	}

	public void setJsonBean(List<AnswerHistory> jsonBean) {
		this.jsonBean = jsonBean;
	}

	@Override
	public String toString() {
		return "JSONBean [jsonBean=" + jsonBean + "]";
	}
	
	
}
