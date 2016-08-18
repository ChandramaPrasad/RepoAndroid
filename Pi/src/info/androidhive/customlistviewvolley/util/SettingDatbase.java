package info.androidhive.customlistviewvolley.util;

import info.androidhive.customlistviewvolley.model.RingTone;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SettingDatbase extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "cal";

	// Contacts table name
	private static final String TABLE_CALENDER = "calset";
	// Contacts table name
	private static final String TABLE_VIBRATION = "vibration";

	// Contacts Table Columns names
	private static final String KEY_RINGTONE = "ringtone";
	private static final String KEY_VIBRATION = "vibration";

	public SettingDatbase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// This table use to create the database for ringtone.
		String CREATE_CALENDER_TABLE = "CREATE TABLE " + TABLE_CALENDER + "("
				+ KEY_RINGTONE + " TEXT" + ")";
		// Thie query use to create vibration table.
		String CREATE_CALENDER_VIBRATION = "CREATE TABLE " + TABLE_VIBRATION
				+ "(" + KEY_VIBRATION + " TEXT" + ")";
		db.execSQL(CREATE_CALENDER_TABLE);
		db.execSQL(CREATE_CALENDER_VIBRATION);
		System.out.println("ringtone table created");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// if both table alredy present then delete it and create new table.
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALENDER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIBRATION);

		// Create tables again
		onCreate(db);

	}

	// Method use to add ringtone to database.
	public void addDataFromRintone(RingTone ringtone) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_RINGTONE, ringtone.getRigtoneuri().toString()); // Contact
																		// Name

		// Inserting Row
		db.insert(TABLE_CALENDER, null, values);
		db.close(); // Closing database connection

	}

	// This method use to store wheather need vibration or not.
	public void addDataFromVibration(RingTone ringtone) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_VIBRATION, String.valueOf(ringtone.isVibrateCheck())); // Contact
		// Name

		// Inserting Row
		db.insert(TABLE_VIBRATION, null, values);
		db.close(); // Closing database connection

	}

	// This method use to get all records from the database.
	public Cursor getAllSettingRecords() {

		SQLiteDatabase database = null;
		Cursor cursor = null;

		try {
			database = getReadableDatabase();

			String result = "select * from " + TABLE_CALENDER + "";

			cursor = database.rawQuery(result, null);

			database.execSQL(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}

	// This method use to get all records from the database.
	public Cursor getAllVibrationSettingRecords() {

		SQLiteDatabase database = null;
		Cursor cursor = null;

		try {
			database = getReadableDatabase();

			String result = "select * from " + TABLE_VIBRATION + "";

			cursor = database.rawQuery(result, null);

			database.execSQL(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}
}
