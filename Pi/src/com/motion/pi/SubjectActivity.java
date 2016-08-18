package com.motion.pi;

import info.androidhive.customlistviewvolley.util.SubjectDb;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SubjectActivity extends Activity implements OnClickListener {

	private ImageView homepageButton;
	private ImageView headerProfileImageView;
	private ListView subjectListView;
	static final int READ_BLOCK_SIZE = 100;
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	private String username = "";
	private TextView snameTextView;
	private SharedPreferences sharedPreferences;
	private Boolean[] checkedStatus;
	private SubjectDb subjectDb;
	// Here declare the subject from user want only question and rest of the he
	// will ignore.
	String[] subject = { "Paper I (Preliminary)", "Paper II (Preliminary)",
			"General Studies I  (Mains)", "General Studies II  (Mains)",
			"General Studies III  (Mains)", "General Studies IV  (Mains)",
			"Agriculture", "Animal Husbandry and Veterinary Science ",
			"Anthropology ", "Botany", "Chemistry", "Computer",
			"Civil Engineering", "Commerce and Accountancy", "Economics",
			"Electrical Engineering", "Geography", "Geology", "History",
			"Law ", "Management", "Mathematics", "Mechanical Engineering",
			"Medical Science ", "Philosophy", "Physics",
			"Political Science and International Relations", "Psychology",
			"Public Administration", "Sociology", "Statistics ", "Zoology ",
			"Other" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_layout);
		initViews();
		registerEvents();
		setAdapterToListView();

		// added this line of code to solve the issue to show image different in
		// setting screen.
		// added by prasad.
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

		for (int index = 0; index < checkedStatus.length; index++)
			checkedStatus[index] = sharedPreferences.getBoolean(
					Integer.toString(index), false);

		link += username + ".jpg";
		// set the username here.
		snameTextView.setText(username);
		// new LoadImage().execute(link);
		Picasso.with(this).load(link).into(headerProfileImageView);
	}

	/**
	 * Method use to set the adapter to ListView.
	 */
	private void setAdapterToListView() {
		// TODO Auto-generated method stub

		SubjectAdapter subjectAdapter = new SubjectAdapter();
		subjectListView.setAdapter(subjectAdapter);

	}

	/**
	 * This method use to register the events.
	 */
	private void registerEvents() {
		// TODO Auto-generated method stub

		homepageButton.setOnClickListener(this);

	}

	/**
	 * This method use to initialize the views.
	 */
	private void initViews() {

		homepageButton = (ImageView) findViewById(R.id.homepageButton);
		headerProfileImageView = (ImageView) findViewById(R.id.headerProfileImage);
		subjectListView = (ListView) findViewById(R.id.subjectListView);
		snameTextView = (TextView) findViewById(R.id.snameTextView);
		sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
		checkedStatus = new Boolean[subject.length];
		subjectDb = new SubjectDb(this);

	}

	/**
	 * Here the adpater to see the which subject user want to get the questions.
	 * 
	 * @author Admin
	 * 
	 */
	private class SubjectAdapter extends BaseAdapter implements
			OnCheckedChangeListener {

		TextView subjectTextView;
		SharedPreferences.Editor editor;
		String selected;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return subject.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			if (convertView == null) {

				ViewHolder viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.subject_item_layout, null);
				viewHolder.subjectCheckBox = (CheckBox) convertView
						.findViewById(R.id.subjectCheckBox);
				convertView.setTag(viewHolder);

			}

			ViewHolder holder = (ViewHolder) convertView.getTag();

			holder.subjectCheckBox.setText(subject[position]);

			CheckBox checkBox = (CheckBox) convertView
					.findViewById(R.id.subjectCheckBox);
			checkBox.setTag(position);
			checkBox.setOnCheckedChangeListener(this);
			checkBox.setChecked(checkedStatus[position]);

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

				}
			});

			return convertView;
		}

		// This method will call when user click on the checkbox to check that
		// he want to subject related questions.
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub

			Integer index = (Integer) buttonView.getTag();

			checkedStatus[index] = isChecked;
			String key = index.toString();

			// When user click on the button to check from which subject he/she
			// don't want the question.
			if (isChecked == true) {

				SharedPreferences sharedPreferences = getApplicationContext()
						.getSharedPreferences("status", Context.MODE_PRIVATE);
				editor = sharedPreferences.edit();
				editor.putBoolean(key, isChecked);
				selected = buttonView.getText().toString();

				// Here chcek if record already present in database then dont
				// insert into database otherwise insert into database.
				boolean isExits = subjectDb.Exists(selected);
				if (isExits != true) {
					subjectDb.inserIntoSubjectDataBase(selected);
					System.out.println("record inserted");
				}
				// editor.clear();
				editor.commit();

			} else {

				// if record is uncheck then deleted from the database.
				SharedPreferences sharedPreferences = getApplicationContext()
						.getSharedPreferences("status", Context.MODE_PRIVATE);
				editor = sharedPreferences.edit();
				// Here remove the record by using the share preferance key .
				editor.remove(key);
				selected = buttonView.getText().toString();
				editor.commit();
				// once remove from the database is will also remove from the
				// database.
				subjectDb.deleteSubject(selected);
			}

		}
	}

	private class ViewHolder {
		CheckBox subjectCheckBox;
	}

	/**
	 * When user click on this button it will go to that funcationality.
	 */
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.homepageButton:

			Intent intent = new Intent(SubjectActivity.this, PiAnswers.class);
			startActivity(intent);
			finish();

			break;

		default:
			break;
		}

	}
}
