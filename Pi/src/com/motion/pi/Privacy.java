package com.motion.pi;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Admin on 07-09-2015.
 */
public class Privacy extends Activity {
	String uname;
	String uid,kuid;
	AlertDialog ald;
	String skb;
	String s;
	static final int READ_BLOCK_SIZE = 100;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.privacy);
		TextView tv = (TextView)findViewById(R.id.textView15);
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
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a65));
				}else if (skb.contentEquals("SKY1")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a64));
				}else if (skb.contentEquals("SKY2")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a63));
				}else if (skb.contentEquals("SKY3")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a62));
				}else if (skb.contentEquals("SKY4")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a61));
				}else if (skb.contentEquals("SKY5")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a60));
				}else if (skb.contentEquals("SKY6")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a59));
				}else if (skb.contentEquals("SKY7")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a58));
				}else if (skb.contentEquals("SKY8")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a57));
				}else if (skb.contentEquals("SKY9")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a56));
				}else if (skb.contentEquals("SKY10")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a55));
				}else if (skb.contentEquals("SKY11")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a54));
				}else if (skb.contentEquals("SKY12")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a53));
				}else if (skb.contentEquals("SKY13")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a52));
				}else if (skb.contentEquals("SKY14")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a51));
				}else if (skb.contentEquals("SKY15")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a50));
				}else if (skb.contentEquals("SKY16")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a49));
				}else if (skb.contentEquals("SKY17")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a48));
				}else if (skb.contentEquals("SKY18")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a47));
				}else if (skb.contentEquals("SKY19")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a46));
				}else if (skb.contentEquals("SKY20")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a45));
				}else if (skb.contentEquals("SKY21")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a44));
				}else if (skb.contentEquals("SKY22")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a43));
				}else if (skb.contentEquals("SKY23")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a42));
				}else if (skb.contentEquals("SKY24")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a41));
				}else if (skb.contentEquals("SKY25")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a40));
				}else if (skb.contentEquals("SKY26")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a39));
				}else if (skb.contentEquals("SKY27")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a38));
				}else if (skb.contentEquals("SKY28")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a37));
				}else if (skb.contentEquals("SKY29")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a36));
				}else if (skb.contentEquals("SKY30")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a35));
				}else if (skb.contentEquals("SKY31")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a34));
				}else if (skb.contentEquals("SKY32")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a33));
				}else if (skb.contentEquals("SKY33")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a32));
				}else if (skb.contentEquals("SKY34")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a31));
				}else if (skb.contentEquals("SKY35")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a30));
				}else if (skb.contentEquals("SKY36")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a29));
				}else if (skb.contentEquals("SKY37")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a28));
				}else if (skb.contentEquals("SKY38")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a27));
				}else if (skb.contentEquals("SKY39")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a26));
				}else if (skb.contentEquals("SKY40")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a25));
				}else if (skb.contentEquals("SKY41")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a24));
				}else if (skb.contentEquals("SKY42")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a23));
				}else if (skb.contentEquals("SKY43")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a22));
				}else if (skb.contentEquals("SKY44")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a21));
				}else if (skb.contentEquals("SKY45")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a20));
				}else if (skb.contentEquals("SKY46")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a19));
				}else if (skb.contentEquals("SKY47")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a18));
				}else if (skb.contentEquals("SKY48")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a17));
				}else if (skb.contentEquals("SKY49")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a16));
				}else if (skb.contentEquals("SKY50")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a15));
				}else if (skb.contentEquals("SKY51")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a14));
				}else if (skb.contentEquals("SKY52")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a13));
				}else if (skb.contentEquals("SKY53")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a12));
				}else if (skb.contentEquals("SKY54")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a11));
				}else if (skb.contentEquals("SKY55")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a10));
				}else if (skb.contentEquals("SKY56")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a9));
				}else if (skb.contentEquals("SKY57")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a8));
				}else if (skb.contentEquals("SKY58")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a7));
				}else if (skb.contentEquals("SKY59")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a6));
				}else if (skb.contentEquals("SKY60")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a5));
				}else if (skb.contentEquals("SKY61")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a4));
				}else if (skb.contentEquals("SKY62")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a3));
				}else if (skb.contentEquals("SKY63")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a2));
				}else if (skb.contentEquals("SKY64")) {
					View someView = findViewById(R.id.priv);
					someView.setBackground(getResources().getDrawable(R.drawable.a1));
				}
			}
			InputRead.close();
			//			Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageView home = (ImageView)findViewById(R.id.button38);
		home.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Privacy.this,PiAnswers.class);
				startActivity(i);
			}
		});
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
			tv.setText("" + s);
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


		System.out.println("++++++++++++"+kuid);
		System.out.println("++++++++++++"+uname);
		//		tv.setText(""+uname);




	}
}
