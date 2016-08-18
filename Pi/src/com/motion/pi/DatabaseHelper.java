package com.motion.pi;


import info.androidhive.customlistviewvolley.app.AppController;

import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper{

	public static final String DB_NAME="travelalaram";
	public static final int DB_VERSION=1;

	private AtomicInteger mOpenCounter = new AtomicInteger();

	private static DatabaseHelper dbHelper;
	private SQLiteDatabase mDatabase;

	public static final String TABLE_ALARAM="alaramtable";

	public static final String DATA_ID = "audio_id";
	public static final String TO_LOCATION="audio_name";
	public static final String DISTANCE="audio_size";
	public static final String FROM_LOCATION="audio_duration";


	public static final String CREATE_ALARAMTABLE =
			"create table "+TABLE_ALARAM + "("
					+ DATA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ TO_LOCATION + " TEXT,"
					+ DISTANCE + " TEXT,"
					+ FROM_LOCATION + " TEXT);";


	public DatabaseHelper(Context context){
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_ALARAMTABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


	}

	public static synchronized DatabaseHelper getInstance() {
		if (dbHelper == null) {
			dbHelper = new DatabaseHelper(AppController.getAppContext());
		}

		return dbHelper;
	}

	public synchronized SQLiteDatabase openDatabase() {
		try {
			if(mOpenCounter.incrementAndGet() == 1) {
				// Opening new database
				mDatabase = dbHelper.getWritableDatabase();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mDatabase;
	}

	public synchronized void closeDatabase() {
		try {
			if(mOpenCounter.decrementAndGet() == 0) {
				// Closing database
				if(mDatabase!=null && mDatabase.isOpen())
					mDatabase.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
