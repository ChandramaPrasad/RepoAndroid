package com.motion.pi;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Displays extends Activity{
	private ListView mainListView ;  
	private ArrayAdapter<String> listAdapter ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.babu);
		Intent i = getIntent();
		String sata = i.getStringExtra("Data get");
//		String [] satas = sata.get
		mainListView = (ListView) findViewById( R.id.list ); 



		ArrayList<String> planetList = new ArrayList<String>();  
		planetList.addAll( Arrays.asList(sata) );  

		// Create ArrayAdapter using the planet list.  sa
//		listAdapter = new ArrayAdapter<String>(this, R.layout.dataitem, sata);  

		// Add more planets. If you passed a String[] instead of a List<String>   
		// into the ArrayAdapter constructor, you must not add more items.   
		// Otherwise an exception will occur.  
//		listAdapter.add( "Ceres" );  
//		listAdapter.add( "Pluto" );  
//		listAdapter.add( "Haumea" );  
//		listAdapter.add( "Makemake" );  
//		listAdapter.add( "Eris" );  

		// Set the ArrayAdapter as the ListView's adapter.  
		mainListView.setAdapter( listAdapter ); 
	}

}
