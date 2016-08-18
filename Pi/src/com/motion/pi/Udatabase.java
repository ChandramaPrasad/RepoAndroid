package com.motion.pi;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Udatabase extends SQLiteOpenHelper {
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "UcontactsManager";

	// Ucontacts table name
	private static final String TABLE_UcontactS = "Ucontacts";

	// Ucontacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PH_NO = "phone_number";

	public Udatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_UcontactS_TABLE = "CREATE TABLE " + TABLE_UcontactS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_PH_NO + " TEXT" + ")";
		db.execSQL(CREATE_UcontactS_TABLE);

	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_UcontactS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new Ucontact
	void addUcontact(Ucontact ucontact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, ucontact.getName()); // Ucontact Name
		values.put(KEY_PH_NO, ucontact.getPhoneNumber()); // Ucontact Phone

		// Inserting Row
		db.insert(TABLE_UcontactS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single Ucontact
	Ucontact getUcontact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_UcontactS, new String[] { KEY_ID,
				KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Ucontact Ucontact = new Ucontact(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		// return Ucontact
		return Ucontact;
	}

	// Getting All Ucontacts
	public List<Ucontact> getAllUcontacts() {
		List<Ucontact> UcontactList = new ArrayList<Ucontact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_UcontactS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Ucontact Ucontact = new Ucontact();
				Ucontact.setID(Integer.parseInt(cursor.getString(0)));
				Ucontact.setName(cursor.getString(1));
				Ucontact.setPhoneNumber(cursor.getString(2));
				// Adding Ucontact to list
				UcontactList.add(Ucontact);
			} while (cursor.moveToNext());
		}

		// return Ucontact list
		return UcontactList;
	}

	// Updating single Ucontact
	public int updateUcontact(Ucontact Ucontact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, Ucontact.getName());
		values.put(KEY_PH_NO, Ucontact.getPhoneNumber());

		// updating row
		return db.update(TABLE_UcontactS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(Ucontact.getID()) });
	}

	// Deleting single Ucontact
	public void deleteUcontact(Ucontact Ucontact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_UcontactS, KEY_ID + " = ?",
				new String[] { String.valueOf(Ucontact.getID()) });
		db.close();
	}


	// Getting Ucontacts Count
	public int getUcontactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_UcontactS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
