package info.androidhive.customlistviewvolley.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SubjectDb extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "subjectdb";

	// Contacts table name
	private static final String TABLE_SUBJECT = "subject";

	// Contacts Table Columns names
	private static final String KEY_SUBJECT = "subj";

	public SubjectDb(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String CREATE_SUBJECT_TABLE = "CREATE TABLE " + TABLE_SUBJECT + "("
				+ KEY_SUBJECT + " TEXT" + ")";

		System.out.println("Table created>>>>>>>");
		db.execSQL(CREATE_SUBJECT_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);

		// Create tables again
		onCreate(db);

	}

	// This method use to insert the data into database.
	public void inserIntoSubjectDataBase(String subject) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_SUBJECT, subject);
		// Inserting Row
		db.insert(TABLE_SUBJECT, null, values);
		db.close(); // Closing database connection

	}

	// This method use to delete the records from the database.
	public void deleteSubject(String subject) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_SUBJECT, KEY_SUBJECT + "='" + subject + "'", null);

	}

	// This method use to get all records from the database.
	public Cursor getAllSubjects() {

		SQLiteDatabase database = null;
		Cursor cursor = null;

		try {
			database = getReadableDatabase();

			String result = "select * from " + TABLE_SUBJECT + "";

			cursor = database.rawQuery(result, null);

			database.execSQL(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}

	public boolean Exists(String searchItem) {

		SQLiteDatabase db = this.getReadableDatabase();

		String[] columns = { KEY_SUBJECT };
		String selection = KEY_SUBJECT + " =?";
		String[] selectionArgs = { searchItem };
		String limit = "1";

		Cursor cursor = db.query(TABLE_SUBJECT, columns, selection,
				selectionArgs, null, null, null, limit);
		boolean exists = (cursor.getCount() > 0);
		cursor.close();
		return exists;
	}

}
