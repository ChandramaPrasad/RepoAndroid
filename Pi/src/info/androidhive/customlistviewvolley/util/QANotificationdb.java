package info.androidhive.customlistviewvolley.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QANotificationdb extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "notificationdb";

	// Contacts table name
	private static final String TABLE_QUESTIONNOTI = "qnotifi";
	private static final String TABLE_ANSNOTIFI = "anotifi";

	// Contacts Table Columns names
	private static final String KEY_QUESTION = "questionid";
	private static final String KEY_ANSID = "ansid";
	private static final String KEY_QID = "questionidss";

	public QANotificationdb(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_SUBJECT_TABLE = "CREATE TABLE " + TABLE_QUESTIONNOTI
				+ "(" + KEY_QUESTION + " TEXT" + ")";
		db.execSQL(CREATE_SUBJECT_TABLE);

		String CREATE_ANSWER_TABLE = "CREATE TABLE " + TABLE_ANSNOTIFI + "("
				+ KEY_ANSID + " TEXT," + KEY_QID + " TEXT" + ")";
		db.execSQL(CREATE_ANSWER_TABLE);

		System.out.println("Table question and ans created>>>>>>>");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONNOTI);
		onCreate(db);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSNOTIFI);
		onCreate(db);

	}

	// This method use to insert the data into database.
	public void inserIntoQuestionId(String questionid) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_QUESTION, questionid);
		db.insert(TABLE_QUESTIONNOTI, null, values);
		db.close(); // Closing database connection
	}

	// This method use to insert the data into database.
	public void inserIntoAnswerId(String ansId, String questionid) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ANSID, ansId);
		values.put(KEY_QID, questionid);
		db.insert(TABLE_ANSNOTIFI, null, values);
		db.close(); // Closing database connection
	}

	// This method use to delete the records from the database.
	public void deleteQuestionId(String questionid) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_QUESTIONNOTI, KEY_QUESTION + "='" + questionid + "'",
				null);

	}

	// This method use to delete the records from the database.
	public void deleteAnsId(String ansId) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_ANSNOTIFI, KEY_ANSID + "='" + ansId + "'", null);

	}

	// This method use to get all records from the database.
	public Cursor getAllQuestionId() {

		SQLiteDatabase database = null;
		Cursor cursor = null;

		try {
			database = getReadableDatabase();

			String result = "select * from " + TABLE_QUESTIONNOTI + "";

			cursor = database.rawQuery(result, null);

			database.execSQL(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}

	// This method use to get all records from the database.
	public Cursor getAllAnsId() {

		SQLiteDatabase database = null;
		Cursor cursor = null;

		try {
			database = getReadableDatabase();

			String result = "select * from " + TABLE_ANSNOTIFI + "";

			cursor = database.rawQuery(result, null);

			database.execSQL(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}

}
