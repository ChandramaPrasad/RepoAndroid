package info.androidhive.customlistviewvolley.util;

import info.androidhive.customlistviewvolley.model.User;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserLoginDb extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "login";

	// Contacts table name
	private static final String TABLE_CONTACTS = "user";

	// Contacts table calendar settings
	private static final String TABLE_CALENDER = "cal";

	// Contacts table name

	// Contacts Table Columns names
	private static final String KEY_NAME = "username";
	private static final String KEY_PASSWORD = "password";

	public UserLoginDb(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_NAME + " TEXT," + KEY_PASSWORD + " TEXT" + ")";

		System.out.println("Table created>>>>>>>");
		db.execSQL(CREATE_CONTACTS_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		// Create tables again
		onCreate(db);

	}

	// This method use to insert the data into database.
	public void inserIntoUserDatabase(User user) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, user.getUsername());
		values.put(KEY_PASSWORD, user.getPassword());
		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection

	}

	// This method use to delete the records from the database.
	public void deleteContact(String firstname) {

		SQLiteDatabase db = this.getWritableDatabase();

		// db.delete(TABLE_CONTACTS, KEY_NAME + "='" + firstname + "'", null);
		db.execSQL("delete from " + TABLE_CONTACTS);

	}

	// This method use to get all records from the database.
	public Cursor getAllRecords() {

		SQLiteDatabase database = null;
		Cursor cursor = null;

		try {
			database = getReadableDatabase();

			String result = "select * from " + TABLE_CONTACTS + "";

			cursor = database.rawQuery(result, null);

			database.execSQL(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}

}
