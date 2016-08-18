package com.motion.pi;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends ListActivity {
	/** The view to show the ad. */
	ListView lv;
	static final int READ_BLOCK_SIZE = 100;
	private String id;
	TextView t1;
	String s;
	String popUpContents[];
	String kuid;
	TextView t2;
	PopupWindow popupWindowDogs;
	TextView t3;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.arch);

		TextView na = (TextView) findViewById(R.id.sname);
		List<String> dogsList = new ArrayList<String>();
		dogsList.add("Discussions");

		// convert to simple array
		popUpContents = new String[dogsList.size()];
		dogsList.toArray(popUpContents);

		/*
		 * initialize pop up window
		 */
		popupWindowDogs = popupWindowDogs();
		ImageView home = (ImageView) findViewById(R.id.hds);
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ListViewActivity.this, PiAnswers.class);
				startActivity(i);

			}
		});
		ImageView mors = (ImageView) findViewById(R.id.mor);
		mors.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindowDogs.showAsDropDown(v, -5, 0);
			}
		});
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

			na.setText("" + s);

		} catch (Exception e) {
			e.printStackTrace();
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

		// getListView().setOnItemClickListener(new
		// AdapterView.OnItemClickListener(){
		//
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		// long arg3) {
		//
		// AlaramData audioFile=(AlaramData) arg0.getItemAtPosition(arg2);
		// arg0.removeViewAt(arg2);
		//
		// // Intent intent = new Intent(ListViewActivity.this,MailSend.class);
		// // if(audioFile!=null){
		// // intent.putExtra("id", audioFile.id);
		// // intent.putExtra("fname", audioFile.from_location);
		// // intent.putExtra("lname", audioFile.to_location);
		// // intent.putExtra("time", audioFile.time);
		// // }
		// // startActivity(intent);
		// // intent.putExtra("audioFile", audioFile);
		// // startActivityForResult(intent, some);
		// /* AlaramData file = new
		// AlaramData(t1.getText().toString(),t3.getText().toString(),t2.getText().toString());
		// AlaramDA audioDA = new AlaramDA();
		// audioDA.saveAlaram(file);*/
		//
		// }
		// });

		ImageView del = (ImageView) findViewById(R.id.imageView1);
		del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// SparseBooleanArray checkedItemPositions =
				// getListView().getCheckedItemPositions();
				// int itemCount = getListView().getCount();

				// for(int i=itemCount-1; i >= 0; i--){
				// if(checkedItemPositions.get(i)){
				// adapter.remove(list.get(i));
				new AlaramDA().deleteRecording(id);
				finish();
			}
			// }
			// checkedItemPositions.clear();
			// adapter.notifyDataSetChanged();

			// }
		});

	}

	public PopupWindow popupWindowDogs() {

		// initialize a pop up window type
		PopupWindow popupWindow = new PopupWindow(this);

		// the drop down list is a list view
		ListView listViewDogs = new ListView(this);

		// set our adapter and pass our pop up window contents
		listViewDogs.setAdapter(dogsAdapter(popUpContents));

		// set the item click listener
		// listViewDogs.setOnItemClickListener(new
		// DogsDropdownOnItemClickListener());
		listViewDogs.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String item = parent.getItemAtPosition(position).toString();
				if (item.equals("Discussions")) {
					// activityToOpen = DisplayMessageActivity.class;
					Intent i1 = new Intent(ListViewActivity.this,
							Discussions.class);
					startActivity(i1);
				}

			}
		});

		// some other visual settings
		popupWindow.setFocusable(true);
		popupWindow.setWidth(250);
		popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

		// set the list view as pop up window content
		popupWindow.setContentView(listViewDogs);

		return popupWindow;
	}

	private ArrayAdapter<String> dogsAdapter(String dogsArray[]) {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dogsArray) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				// setting the ID and text for every items in the list
				String item = getItem(position);
				String[] itemArr = item.split("::");
				String text = itemArr[0];
				// String id = itemArr[1];

				// visual settings for the list item
				TextView listItem = new TextView(ListViewActivity.this);

				listItem.setText(text);
				// listItem.setTag(id);
				listItem.setTextSize(22);
				listItem.setPadding(10, 10, 10, 10);
				listItem.setTextColor(Color.WHITE);

				return listItem;
			}
		};

		return adapter;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		AlaramDA audioDA = new AlaramDA();

		setListAdapter(new abc(audioDA.getRecordingsList()));

	}

	public class abc extends BaseAdapter {
		List<AlaramData> list;

		public abc(List<AlaramData> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub

			if (list != null)
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
		public View getView(final int posistion, View convertview,
				ViewGroup arg2) {
			// TODO Auto-generated method stub
			LayoutInflater in = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

			if (convertview == null) {
				convertview = in.inflate(R.layout.list_second, null);

			}

			TextView t1 = (TextView) convertview.findViewById(R.id.place);
			TextView t2 = (TextView) convertview.findViewById(R.id.dista);
			TextView t3 = (TextView) convertview.findViewById(R.id.to);

			AlaramData audioFile = (AlaramData) getItem(posistion);

			t1.setText(audioFile.from_location + " - ");
			t3.setText(audioFile.to_location);
			t2.setText(audioFile.time);
			CheckBox chk = (CheckBox) findViewById(R.id.radioButton1);
			// t1.setText(MainActivity.aa.get(a)
			convertview.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// getListView().getItemAtPosition(arg0);

					Toast.makeText(getApplicationContext(),
							"" + getItemId(posistion), 9000).show();
					// lis.removeViewAt(posistion);
				}
			});

			return convertview;
		}

	}

}
