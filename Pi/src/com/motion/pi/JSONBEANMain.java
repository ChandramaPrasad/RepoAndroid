package com.motion.pi;

import java.util.List;

import android.app.Activity;

import com.google.gson.Gson;


public class JSONBEANMain extends Activity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JSONBean res=null;
		
		String val="[{\"idqtype\":1,\"qtype\":\"java\"},{\"idqtype\":2,\"qtype\":\".net\"},{\"idqtype\":3,\"qtype\":\"php\"},{\"idqtype\":4,\"qtype\":\"mysql\"},{\"idqtype\":5,\"qtype\":\"oracle\"},{\"idqtype\":6,\"qtype\":\"plsql\"},{\"idqtype\":7,\"qtype\":\"j2ee\"},{\"idqtype\":8,\"qtype\":\"spring\"},{\"idqtype\":9,\"qtype\":\"jsp\"},{\"idqtype\":10,\"qtype\":\"servlet\"},{\"idqtype\":11,\"qtype\":\"hibernet\"},{\"idqtype\":12,\"qtype\":\"jquery\"},{\"idqtype\":13,\"qtype\":\"primeface\"},{\"idqtype\":14,\"qtype\":\"xhtml\"},{\"idqtype\":15,\"qtype\":\"html\"}]";
//		String val = "http://166.62.81.118:18080/SpringRestCrud/question/getQuestionTypeList";

		  Gson gson = new Gson(); 
          String vl="{\"jsonBean\":"+val+"}";
       
          res = gson.fromJson(vl, JSONBean.class);
          List<JSONBeanGenarte> bean = (List<JSONBeanGenarte>) res.getJsonBean();
	    		for (JSONBeanGenarte listBean : bean) {
	    			String idqtype=listBean.getIdqtype();
	    			String Qtype=listBean.getQtype();
	    			System.out.println("idqtype "+idqtype);
	    			System.out.println("Qtype :"+Qtype);     	    			
	    				   }				
	
		
	}

}
