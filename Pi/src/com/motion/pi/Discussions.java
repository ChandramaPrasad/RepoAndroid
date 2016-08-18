package com.motion.pi;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class Discussions extends ListViewActivity {
	static final int READ_BLOCK_SIZE = 100;
	ListView lv;
	String s;
	String kuid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.discus);
		ImageView home = (ImageView)findViewById(R.id.hds);
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Discussions.this,PiAnswers.class);
				startActivity(i);

			}
		});
		TextView na = (TextView)findViewById(R.id.textView11);
		try {
			FileInputStream fileIn=openFileInput("mytextfile.txt");
			InputStreamReader InputRead= new InputStreamReader(fileIn);

			char[] inputBuffer= new char[READ_BLOCK_SIZE];
			String s="";
			String d="";
			int charRead;

			while ((charRead=InputRead.read(inputBuffer))>0) {
				// char to string conversion
				String readstring=String.copyValueOf(inputBuffer,0,charRead);
				s +=readstring;		
				String readStrings = String.copyValueOf(inputBuffer,0,charRead);
				d += readStrings;

			}
			InputRead.close();
			//			Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

			na.setText(""+s);


		} catch (Exception e) {
			e.printStackTrace();
		}



		try {
			FileInputStream fileIn=openFileInput("mytextfiles.txt");
			InputStreamReader InputRead= new InputStreamReader(fileIn);

			char[] inputBuffer= new char[READ_BLOCK_SIZE];
			kuid="";
			int charRead;

			while ((charRead=InputRead.read(inputBuffer))>0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer,0,charRead);
				kuid += readStrings;

			}
			InputRead.close();
			//			Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}


		getListView().setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				AlaramData2 audioFile=(AlaramData2) arg0.getItemAtPosition(arg2);


				//				intent.putExtra("audioFile", audioFile);
				//				startActivityForResult(intent, some);
				/*	AlaramData file = new AlaramData(t1.getText().toString(),t3.getText().toString(),t2.getText().toString());
				AlaramDA audioDA = new AlaramDA();
				audioDA.saveAlaram(file);*/

			}
		});
	}

	public class abc extends BaseAdapter {
		List<AlaramData> list;
		public abc(List<AlaramData> list){
			this.list = list;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub

			if(list!=null)
				return list.size();
			else
				return 0;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			LayoutInflater in = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			arg1 = in.inflate(R.layout.discu_item, null);
			TextView t1 = (TextView)arg1.findViewById(R.id.place);
			TextView t2 = (TextView)arg1.findViewById(R.id.dista);
			TextView t3 = (TextView)arg1.findViewById(R.id.to);

			AlaramData2 audioFile = (AlaramData2) getItem(arg0);


			t1.setText(audioFile.from_location+" - ");
			t3.setText(audioFile.to_location);
			//			t2.setText(audioFile.time);
			// t1.setText(MainActivity.aa.get(a)

			return arg1;
		}


	}

}
