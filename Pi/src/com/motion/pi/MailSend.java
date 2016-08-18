package com.motion.pi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MailSend extends Activity{
	EditText mailid;

	EditText composmail;
	String smailid;
	String ssubject;
	String scomposemail;
	String uname;
	String kuid;
	EditText subject;
	URI uri;
	static final int READ_BLOCK_SIZE = 100;
	String s;
	String A;
	String B;
	String C;
	String names;
	String nids;
	EditText acTextView;
	private static String url = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/composemailnotes/{srcusrid}/{srcusrname}/{destusrid}/{destusrname}/{mailbody}";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//		final DatabaseHandler db = new DatabaseHandler(this);
		setContentView(R.layout.mail);
		ImageView home = (ImageView)findViewById(R.id.button14);
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i =new Intent(MailSend.this,PiAnswers.class);
				startActivity(i);
				
			}
		});
		subject = (EditText)findViewById(R.id.msub);
		composmail = (EditText)findViewById(R.id.cmpdata);
		names = getIntent().getStringExtra("Question");
		nids = getIntent().getStringExtra("qid");
		acTextView = (EditText)findViewById(R.id.fnds);
		//		SharedPreferences sp = getSharedPreferences("prefs", 0);
//		String data = getIntent().getStringExtra("data");
		//        uname = sp.getString("name","noname");
//		acTextView.setText(""+data);
		//        kuid = sp.getString("id","noid" );
		TextView na = (TextView)findViewById(R.id.textView11);

		A = getIntent().getStringExtra("fname");
		B=getIntent().getStringExtra("lname");
		C=getIntent().getStringExtra("time");

		acTextView.setText(""+A);
		composmail.setText(""+B);
		subject.setText(""+C);
//		acTextView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent i=new Intent(MailSend.this,MyFrndslist.class);
//				startActivity(i);
//
//			}
//		});
		na.setText(""+names);
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
			Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

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
			Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		scomposemail = composmail.getText().toString();
		ssubject = subject.getText().toString();
		ImageView smail = (ImageView)findViewById(R.id.msend);

		smail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				smailid = acTextView.getText().toString();
				scomposemail = composmail.getText().toString();
				ssubject = subject.getText().toString();
				new MyAsyncTask().execute(smailid,ssubject,scomposemail);
				Toast.makeText(getApplicationContext(), "Mail Sent Sucessfully", 9000).show();
				/**
				 * CRUD Operations
				 * */
				//				Intent itr = new Intent(MailSend.this,ListViewActivity.class);
				//				AlaramData file = new AlaramData(acTextView.getText().toString(),composmail.getText().toString(),subject.getText().toString());
				//				AlaramDA audioD= new AlaramDA();
				//				audioD.saveAlaram(file);
				//				startActivity(itr);
				//		        // Inserting Contacts
				//		        Log.d("Insert: ", "Inserting ..");
				//		        db.addContact(new Contact(smailid, smailid));
				//		        db.addContact(new Contact(ssubject, ssubject));
				//		        db.addContact(new Contact(scomposemail, scomposemail));
				////		        db.addContact(new Contact("Karthik", "9533333333"));
				//		 
				//		        // Reading all contacts
				//		        Log.d("Reading: ", "Reading all contacts..");
				//		        List<Contact> contacts = db.getAllContacts();       
				//		 
				//		        for (Contact cn : contacts) {
				//		            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
				//		                // Writing Contacts to log
				//		        Log.d("Name: ", log);
				//		        

				//		        }
			}
		});
		//        acTextView.setAdapter(new SuggestionAdapter(this,acTextView.getText().toString()));


	}
	private class MyAsyncTask extends  AsyncTask<String, Integer, Double>{
		@Override
		protected Double doInBackground(String... params) throws  ArrayIndexOutOfBoundsException{
			postData(params[0],params[1],params[2]);
			return null;
		}
		protected void onPostExecute(Double result) {
			//	            pb.setVisibility(View.GONE);
			//	            Toast.makeText()
		}

		protected void onProgressUpdate(Integer... progress) {
			//	            pb.setProgress(progress[0]);
		}

		private void postData(String smailid,String ssubject,String scomposemail) throws IllegalArgumentException {

			try {
				String Mail = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/composemailnotes/{srcusrid}/{srcusrname}/{destusrid}/{destusrname}/{mailbody}";
				String Sample= "http://166.62.81.118:18080/SpringRestCrud/mailnotes/composemailnotes/";
				String basurl2 = "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/Uses%20of%20facebook/80/about%20mobile/3";
				String basurl3= "http://166.62.81.118:18080/SpringRestCrud/question/questionhistory/";
				System.out.println("***************"+basurl2);
				Sample+=kuid+"/"+uname+"/"+nids+"/"+names+"/"+MailSend.this.scomposemail;
				//	                basurl3+= cname+"/"+kuid+"/"+uname+"/"+keyword+"/"+"3";
				System.out.println("***************"+basurl3);
				System.out.println("****************"+Sample);

				uri = new URI(Sample.replace(" ", "%20"));
				System.out.println("**************"+uri);

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}catch (RuntimeException e){
				e.printStackTrace();
			}


			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(uri);

				HttpResponse responses = httpClient.execute(httpGet);

				HttpEntity entity = (HttpEntity) responses.getEntity();

				//	                is = entity.getContent();

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassCastException e) {
				e.printStackTrace();
			}catch (IllegalArgumentException e){
				e.printStackTrace();
			}

		}
	}

}
