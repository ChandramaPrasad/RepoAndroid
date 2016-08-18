package com.motion.pi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
//import android.widget.Toast;

public class Look extends Activity{
	String uname,kuid;
	String s;
	//	String colour ="#f3ff00";
	String clr;
	int foo;
	static final int READ_BLOCK_SIZE = 100;
	String B;
	String Y;
	String D;
	String skb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.look);
		TextView st =(TextView)findViewById(R.id.ltv);
		ImageView home = (ImageView)findViewById(R.id.button38);
		ImageView deff = (ImageView)findViewById(R.id.def);
		try {
			FileInputStream newfile=openFileInput("Skies.txt");
			InputStreamReader InputRead= new InputStreamReader(newfile);

			char[] inputBuffer= new char[READ_BLOCK_SIZE];
			skb="";
			int charRead;

			while ((charRead=InputRead.read(inputBuffer))>0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer,0,charRead);
				skb += readStrings;
				if (skb.contentEquals("SKY")) {
					View someView = findViewById(R.id.rsc);
					someView.setBackground(getResources().getDrawable(R.drawable.a1));

				}
			}
			InputRead.close();
			//			Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		home.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Look.this,PiAnswers.class);
				startActivity(i);
			}
		});
		//		foo =Integer.parseInt("#ffff00");
		try {
			FileInputStream fileIn=openFileInput("mytextfile.txt");
			InputStreamReader InputRead= new InputStreamReader(fileIn);

			char[] inputBuffer= new char[READ_BLOCK_SIZE];
			s="";
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
			st.setText("" + s);
			//			uname = names.getText().toString();


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




		ImageView yl = (ImageView)findViewById(R.id.View1);
		ImageView y2 = (ImageView)findViewById(R.id.bg2);

		final RelativeLayout lLayout = (RelativeLayout) findViewById(R.layout.look);
		yl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				View someView = findViewById(R.id.rsc);
				someView.setBackground(getResources().getDrawable(R.drawable.sky));
				TextView sk = (TextView)findViewById(R.id.sky);
				sk.setText("SKY");
				B= sk.getText().toString();
				try {
					FileOutputStream newout = openFileOutput											
							("Skies.txt", MODE_PRIVATE);
					OutputStreamWriter outputWriter=new OutputStreamWriter(newout);
					outputWriter.write(B);

					outputWriter.close(); 
					//					Toast.makeText(getApplicationContext(), B, 9000).show();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		y2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				View someView = findViewById(R.id.rsc);
				someView.setBackground(getResources().getDrawable(R.drawable.yello));
				TextView ley = (TextView)findViewById(R.id.yel);
				ley.setText("YEL");
				Y = ley.getText().toString();
				try {
					FileOutputStream yellow = openFileOutput											
							("Skies.txt", MODE_PRIVATE);
					OutputStreamWriter outputWriter=new OutputStreamWriter(yellow);
					outputWriter.write(Y);

					outputWriter.close(); 
					//					Toast.makeText(getApplicationContext(), Y, 9000).show();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		deff.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				View someView = findViewById(R.id.rsc);
				someView.setBackground(getResources().getDrawable(R.drawable.defa));
				TextView ley = (TextView)findViewById(R.id.defult);
				ley.setText("DEF");
				D = ley.getText().toString();
				try {
					FileOutputStream yellow = openFileOutput											
							("Skies.txt", MODE_PRIVATE);
					OutputStreamWriter outputWriter=new OutputStreamWriter(yellow);
					outputWriter.write(D);

					outputWriter.close(); 
					//					Toast.makeText(getApplicationContext(), Y, 9000).show();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}





}
