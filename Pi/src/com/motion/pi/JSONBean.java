package com.motion.pi;

import java.util.List;


public class JSONBean  {

	
	List<JSONBeanGenarte> jsonBean;

	public List<JSONBeanGenarte> getJsonBean() {
		return jsonBean;
	}

	public void setJsonBean(List<JSONBeanGenarte> jsonBean) {
		this.jsonBean = jsonBean;
	}

	@Override
	public String toString() {
		return "JSONBean [jsonBean=" + jsonBean + "]";
	}
	
	
	
}
