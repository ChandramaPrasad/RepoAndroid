package info.androidhive.customlistviewvolley.util;

import info.androidhive.customlistviewvolley.model.Question;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserHomepageDb extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "homedb";

	// Contacts table name
	private static final String TABLE_HOMEPAGE = "question";

	// Contacts Table Columns names
	private static final String KEY_QUESTION_ID = "questionsid";
	private static final String KEY_QUESTION = "question";
	private static final String KEY_QUESTION_RATING = "qrate";
	private static final String KEY_QUESTION_USER = "quser";
	private static final String KEY_QUESTION_KEYWORD = "keyword";

	public UserHomepageDb(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_HOMEPAGE + "(" + KEY_QUESTION_ID + " TEXT,"
				+ KEY_QUESTION + " TEXT," + KEY_QUESTION_RATING + " TEXT,"
				+ KEY_QUESTION_USER + " TEXT," + KEY_QUESTION_KEYWORD
				+ " TEXT);";
		db.execSQL(CREATE_CONTACTS_TABLE);
		System.out.println("Table created successfully");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOMEPAGE);

		// Create tables again
		onCreate(db);

	}

	public void addQuestionData(List<Question> questions) {
		SQLiteDatabase db = this.getWritableDatabase();

		for (int i = 0; i < questions.size(); i++) {

			ContentValues values = new ContentValues();
			values.put(KEY_QUESTION_ID,
					String.valueOf(questions.get(i).getIdquestion()));

			values.put(KEY_QUESTION, questions.get(i).getQuestiondetails()
					.toString()); // Contact
			// Name
			values.put(KEY_QUESTION_RATING,
					String.valueOf(questions.get(i).getRatingtypevalue()));

			values.put(KEY_QUESTION_USER, questions.get(i).getQusername()
					.toString()); // Contact
			// Name
			values.put(KEY_QUESTION_KEYWORD, questions.get(i)
					.getAboutmyquestion().toString());

			db.insert(TABLE_HOMEPAGE, null, values);

		}
		System.out.println("data inserted successfully");
		db.close(); // Closing database connection
	}

	// This method use to get all records from the database.
	public Cursor getAllRecords() {

		SQLiteDatabase database = null;
		Cursor cursor = null;

		try {
			database = getReadableDatabase();

			String result = "select * from " + TABLE_HOMEPAGE + "";

			cursor = database.rawQuery(result, null);

			database.execSQL(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}

	// This method use to delete the records from the database.
	public void deleteAllRecords() {

		SQLiteDatabase db = this.getWritableDatabase();

		// db.delete(TABLE_CONTACTS, KEY_NAME + "='" + firstname + "'", null);
		db.execSQL("delete from " + TABLE_HOMEPAGE);

	}

}
