package com.motion.pi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public  class  FakeExpadableListTest  extends  Activity {  
	@Override  
	public  void  onCreate (Bundle savedinstanceState) {  
		super.onCreate (savedinstanceState);  
		setContentView (R.layout.mama);  

		int  GroupiDs [] = {R.id.group1, R.id.group2, R.id.group3, R.id.group4, R.id.group5, R.id.group6};  

		for ( int  i=  0  ; i <GroupiDs.length; i ++) {  
			LinearLayout ll = (LinearLayout) findViewById (GroupiDs [i]);  
			Button button = (Button) ll.findViewById (R.id.button);  
			button.setOnClickListener ( new  View.OnClickListener () {  
				@Override
				public  void  onClick (View v) {  
					LinearLayout ll = (LinearLayout) v.getParent ();  
					LinearLayout groupll = (LinearLayout) ll.findViewById (R.id.inputgroup);  
					switch (groupll.getVisibility ()) {  
					case  View.GONE:  
						groupll.setVisibility (View.VISIBLE);  
						break ;  
					case  View.VISIBLE:  
						groupll.setVisibility (View.GONE);  
						break ;  
					}  

					((Button) v) .setText ( "Hidden Group" );  
				}  
			});  
		}  
	}  
}  
