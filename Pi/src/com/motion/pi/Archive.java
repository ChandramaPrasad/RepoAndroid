package com.motion.pi;

import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.motion.actionbar.CustomActionBar;

/**
 * Created by Admin on 16-09-2015.
 */
public class Archive extends CustomActionBar {
	private SQLiteAdapter mySQLiteAdapter;
	ListView archiveListViewContents;
	String kuid;
	ImageView deleteButton;
	static final int READ_BLOCK_SIZE = 100;
	SimpleCursorAdapter cursorAdapter;
	Cursor cursor;
	String skb;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	String wdps;
	ImageView pic;
	private String username = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archive);

		archiveListViewContents = (ListView) findViewById(R.id.archiveListView);
		pic = (ImageView) findViewById(R.id.headerProfileImage);
		deleteButton = (ImageView) findViewById(R.id.deleteButton);
		mySQLiteAdapter = new SQLiteAdapter(this);
		mySQLiteAdapter.openToWrite();
		try {
			FileInputStream fileIn = openFileInput("lastname.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];

			String ds = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				wdps += readstring;
				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				ds += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

			// name.setText("" + wdp);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileInputStream fileIn = openFileInput("mytextfile.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];

			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				username += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		cursor = mySQLiteAdapter.queueAll();
		String[] from = new String[] { SQLiteAdapter.KEY_ID,
				SQLiteAdapter.KEY_CONTENT1, SQLiteAdapter.KEY_CONTENT2 };
		int[] to = new int[] { R.id.place, R.id.dista, R.id.to };
		cursorAdapter = new SimpleCursorAdapter(this, R.layout.list_second,
				cursor, from, to);
		archiveListViewContents.setAdapter(cursorAdapter);
		// archiveListViewContents
		// .setOnItemClickListener(listContentOnItemClickListener);

		try {
			FileInputStream fileIn = openFileInput("mytextfile.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			String s = "";
			String d = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion
				String readstring = String
						.copyValueOf(inputBuffer, 0, charRead);
				s += readstring;
				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				d += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), d,Toast.LENGTH_SHORT).show();
			// tn.setText("" + s);
			// uname = names.getText().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileInputStream newfile = openFileInput("Skies.txt");
			InputStreamReader InputRead = new InputStreamReader(newfile);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			skb = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				skb += readStrings;
				View someView = findViewById(R.id.archivebackground);
				// This method use to change background color when user select
				// from look and feel.
				LookAndFeel.lookAndFeel(skb, someView);

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
		}

		try {
			FileInputStream fileIn = openFileInput("mytextfiles.txt");
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			kuid = "";
			int charRead;

			while ((charRead = InputRead.read(inputBuffer)) > 0) {
				// char to string conversion

				String readStrings = String.copyValueOf(inputBuffer, 0,
						charRead);
				kuid += readStrings;

			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// when user long press on Listview then action will perform.
		archiveListViewContents
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {

						Toast.makeText(getApplicationContext(),
								"Click on" + position, Toast.LENGTH_LONG)
								.show();
						Cursor cursor = (Cursor) parent
								.getItemAtPosition(position);
						final int item_id = cursor.getInt(cursor
								.getColumnIndex(SQLiteAdapter.KEY_ID));
						String question = cursor.getString(cursor
								.getColumnIndex(SQLiteAdapter.KEY_CONTENT1));
						String answer = cursor.getString(cursor
								.getColumnIndex(SQLiteAdapter.KEY_CONTENT2));
						view.setBackgroundColor(R.drawable.yello);

						AlertDialog.Builder myDialog = new AlertDialog.Builder(
								Archive.this);

						myDialog.setTitle("Delete?");

						TextView dialogTxt_id = new TextView(Archive.this);
						LayoutParams dialogTxt_idLayoutParams = new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
						dialogTxt_id.setLayoutParams(dialogTxt_idLayoutParams);
						dialogTxt_id.setText("#" + String.valueOf(item_id));

						TextView dialogC1_id = new TextView(Archive.this);
						LayoutParams dialogC1_idLayoutParams = new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
						dialogC1_id.setLayoutParams(dialogC1_idLayoutParams);
						dialogC1_id.setText(question);

						TextView dialogC2_id = new TextView(Archive.this);
						LayoutParams dialogC2_idLayoutParams = new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
						dialogC2_id.setLayoutParams(dialogC2_idLayoutParams);
						dialogC2_id.setText(answer);

						LinearLayout layout = new LinearLayout(Archive.this);
						layout.setOrientation(LinearLayout.VERTICAL);
						layout.addView(dialogTxt_id);
						layout.addView(dialogC1_id);
						layout.addView(dialogC2_id);
						myDialog.setView(layout);

						// sending data using intent when user click on
						// particular question and answer.
						// added by prasad to solved issued to send mail.
						Intent sendIntent = new Intent(Archive.this,
								Notessend.class);
						sendIntent.putExtra("answer", answer);
						startActivity(sendIntent);

						// when user click on this button will delete records
						// using id
						deleteButton.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mySQLiteAdapter.delete_byID(item_id);
								updateList();
							}
						});

						myDialog.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									// do something when the button is clicked
									public void onClick(DialogInterface arg0,
											int arg1) {

									}
								});

						myDialog.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									// do something when the button is clicked
									public void onClick(DialogInterface arg0,
											int arg1) {

									}
								});

						// myDialog.show();

						return false;
					}
				});
	}

	private ListView.OnItemClickListener listContentOnItemClickListener = new ListView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub

			Cursor cursor = (Cursor) parent.getItemAtPosition(position);
			final int item_id = cursor.getInt(cursor
					.getColumnIndex(SQLiteAdapter.KEY_ID));
			String item_content1 = cursor.getString(cursor
					.getColumnIndex(SQLiteAdapter.KEY_CONTENT1));
			String item_content2 = cursor.getString(cursor
					.getColumnIndex(SQLiteAdapter.KEY_CONTENT2));
			view.setBackgroundColor(R.drawable.yello);

			AlertDialog.Builder myDialog = new AlertDialog.Builder(Archive.this);

			myDialog.setTitle("Delete?");

			TextView dialogTxt_id = new TextView(Archive.this);
			LayoutParams dialogTxt_idLayoutParams = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			dialogTxt_id.setLayoutParams(dialogTxt_idLayoutParams);
			dialogTxt_id.setText("#" + String.valueOf(item_id));

			TextView dialogC1_id = new TextView(Archive.this);
			LayoutParams dialogC1_idLayoutParams = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			dialogC1_id.setLayoutParams(dialogC1_idLayoutParams);
			dialogC1_id.setText(item_content1);

			TextView dialogC2_id = new TextView(Archive.this);
			LayoutParams dialogC2_idLayoutParams = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			dialogC2_id.setLayoutParams(dialogC2_idLayoutParams);
			dialogC2_id.setText(item_content2);

			LinearLayout layout = new LinearLayout(Archive.this);
			layout.setOrientation(LinearLayout.VERTICAL);
			layout.addView(dialogTxt_id);
			layout.addView(dialogC1_id);
			layout.addView(dialogC2_id);
			myDialog.setView(layout);

			deleteButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mySQLiteAdapter.delete_byID(item_id);
					updateList();
				}
			});

			myDialog.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						// do something when the button is clicked
						public void onClick(DialogInterface arg0, int arg1) {

						}
					});

			myDialog.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						// do something when the button is clicked
						public void onClick(DialogInterface arg0, int arg1) {

						}
					});

			// myDialog.show();

		}
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mySQLiteAdapter.close();
	}

	private void updateList() {
		cursor.requery();
	}

}
