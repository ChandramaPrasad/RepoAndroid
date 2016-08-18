package com.motion.pi;

import info.androidhive.customlistviewvolley.util.LookAndFeel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.motion.actionbar.CustomActionBar;
import com.motion.back.color.BackColor;

public class AndroidGridLayoutActivity extends CustomActionBar implements
		BackColor {
	// Keep all Images in array
	public Integer[] mThumbIds = { R.drawable.a65, R.drawable.a64,
			R.drawable.a63, R.drawable.a62, R.drawable.a61, R.drawable.a60,
			R.drawable.a59, R.drawable.a58, R.drawable.a57, R.drawable.a56,
			R.drawable.a55, R.drawable.a54, R.drawable.a53, R.drawable.a52,
			R.drawable.a51, R.drawable.a50, R.drawable.a49, R.drawable.a48,
			R.drawable.a47, R.drawable.a46, R.drawable.a45, R.drawable.a44,
			R.drawable.a43, R.drawable.a42, R.drawable.a41, R.drawable.a40,
			R.drawable.a39, R.drawable.a38, R.drawable.a37, R.drawable.a36,
			R.drawable.a35, R.drawable.a34, R.drawable.a33, R.drawable.a32,
			R.drawable.a31, R.drawable.a30, R.drawable.a29, R.drawable.a28,
			R.drawable.a27, R.drawable.a26, R.drawable.a25, R.drawable.a24,
			R.drawable.a23, R.drawable.a22, R.drawable.a21, R.drawable.a20,
			R.drawable.a19, R.drawable.a18, R.drawable.a17, R.drawable.a16,
			R.drawable.a15, R.drawable.a14, R.drawable.a13, R.drawable.a12,
			R.drawable.a11, R.drawable.a10, R.drawable.a9, R.drawable.a8,
			R.drawable.a7, R.drawable.a6, R.drawable.a5, R.drawable.a4,
			R.drawable.a3, R.drawable.a2, R.drawable.a1 };
	String link = "http://166.62.81.118:18080/SpringRestCrud/mailnotes/getimage/";
	String wdps;
	ImageView pis;
	String skb;
	private String username = "";
	private BackColor backColor;
	static final int READ_BLOCK_SIZE = 100;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.looks);

		backColor = new AndroidGridLayoutActivity();
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
				View someView = findViewById(R.id.rsc);
				// This method use to change the background color when user
				// select from look and feel.
				LookAndFeel.lookAndFeel(skb, someView);
			}
			InputRead.close();
			// Toast.makeText(getBaseContext(), kuid,Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

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

		GridView gridView = (GridView) findViewById(R.id.grid_products);
		// rsctiveLayout mb = (rsctiveLayout)findViewById(R.id.malay);
		// mb.getBackground().setAlpha(200);

		// Instance of ImageAdapter Class
		gridView.setAdapter(new ImageAdapter(this));
		RelativeLayout rcx = (RelativeLayout) findViewById(R.id.rsc);

		/**
		 * On Click event for Single Gridview Item
		 * */

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				View someView = findViewById(R.id.rsc);
				TextView sk = (TextView) findViewById(R.id.sky);

				// someView.setBackground(getResources().getDrawable(R.drawable.sky));
				Toast.makeText(getApplicationContext(),
						"Your background has been changed", 9000).show();
				Drawable d = getResources().getDrawable(mThumbIds[position]);
				someView.setBackground(d);
				if (position == 0) {
					sk.setText("SKY");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();

					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 1) {
					sk.setText("SKY1");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 2) {
					sk.setText("SKY2");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 3) {
					sk.setText("SKY3");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 4) {
					sk.setText("SKY4");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 5) {
					sk.setText("SKY5");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 6) {
					sk.setText("SKY6");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 7) {
					sk.setText("SKY7");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 8) {
					sk.setText("SKY8");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 9) {
					sk.setText("SKY9");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 10) {
					sk.setText("SKY10");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 11) {
					sk.setText("SKY11");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 12) {
					sk.setText("SKY12");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 13) {
					sk.setText("SKY13");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 14) {
					sk.setText("SKY14");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 15) {
					sk.setText("SKY15");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 16) {
					sk.setText("SKY16");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 17) {
					sk.setText("SKY17");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 18) {
					sk.setText("SKY18");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 19) {
					sk.setText("SKY19");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 20) {
					sk.setText("SKY20");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 21) {
					sk.setText("SKY21");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 22) {
					sk.setText("SKY22");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 23) {
					sk.setText("SKY23");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 24) {
					sk.setText("SKY24");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 25) {
					sk.setText("SKY25");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {

						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 26) {
					sk.setText("SKY26");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 27) {
					sk.setText("SKY27");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 28) {
					sk.setText("SKY28");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 29) {
					sk.setText("SKY29");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 30) {
					sk.setText("SKY30");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 31) {
					sk.setText("SKY31");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 32) {
					sk.setText("SKY32");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 33) {
					sk.setText("SKY33");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 34) {
					sk.setText("SKY34");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 35) {
					sk.setText("SKY35");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 36) {
					sk.setText("SKY36");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 37) {
					sk.setText("SKY37");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 38) {
					sk.setText("SKY38");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 39) {
					sk.setText("SKY39");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 40) {
					sk.setText("SKY40");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 41) {
					sk.setText("SKY41");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 42) {
					sk.setText("SKY42");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 43) {
					sk.setText("SKY43");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 44) {
					sk.setText("SKY44");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 45) {
					sk.setText("SKY45");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 46) {
					sk.setText("SKY46");
					String B = sk.getText().toString();

					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 47) {
					sk.setText("SKY47");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 48) {
					sk.setText("SKY48");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 49) {
					sk.setText("SKY49");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 50) {
					sk.setText("SKY50");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 51) {
					sk.setText("SKY51");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 52) {
					sk.setText("SKY52");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 53) {
					sk.setText("SKY53");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 54) {
					sk.setText("SKY54");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 55) {
					sk.setText("SKY55");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 56) {
					sk.setText("SKY56");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();

					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 57) {
					sk.setText("SKY57");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 58) {
					sk.setText("SKY58");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 59) {
					sk.setText("SKY59");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 60) {
					sk.setText("SKY60");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 61) {
					sk.setText("SKY61");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 62) {
					sk.setText("SKY62");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 63) {
					sk.setText("SKY63");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (position == 64) {
					sk.setText("SKY64");
					String B = sk.getText().toString();
					Intent intent = new Intent(AndroidGridLayoutActivity.this,
							PiAnswers.class);
					startActivity(intent);
					finish();
					try {
						FileOutputStream newout = openFileOutput("Skies.txt",
								MODE_PRIVATE);
						OutputStreamWriter outputWriter = new OutputStreamWriter(
								newout);
						outputWriter.write(B);

						outputWriter.close();
						// Toast.makeText(getApplicationContext(), B,
						// 9000).show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				// Sending image id to FullScreenActivity
				// Intent i = new Intent(getApplicationContext(),
				// FullImageActivity.class);
				// // passing array index
				// i.putExtra("id", position);
				// startActivity(i);
			}
		});
	}

	class ImageAdapter extends BaseAdapter {
		private Context mContext;

		// Constructor
		public ImageAdapter(Context c) {
			mContext = c;
		}

		@Override
		public int getCount() {
			return mThumbIds.length;
		}

		@Override
		public Object getItem(int position) {
			return mThumbIds[position];
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(mContext);
			imageView.setImageResource(mThumbIds[position]);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setLayoutParams(new GridView.LayoutParams(40, 40));
			return imageView;
		}

	}

	@Override
	public void setBackColor(String backname) {
		// TODO Auto-generated method stub

	}
}