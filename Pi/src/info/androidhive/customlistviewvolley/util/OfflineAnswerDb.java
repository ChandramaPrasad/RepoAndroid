package info.androidhive.customlistviewvolley.util;

import info.androidhive.customlistviewvolley.model.Movie;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OfflineAnswerDb extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String ANSWERDB = "ofans";

	// Contacts table name
	private static final String TABLE_ANSWER = "ofanstable";

	// Contacts Table Columns names
	private static final String KEY_QUESTIONID = "quesid";
	private static final String KEY_ANSWER = "oflineans";
	private static final String KEY_ANS_RATE = "ansrate";
	private static final String KEY_ANS_USER = "ansuser";

	public OfflineAnswerDb(Context context) {
		super(context, ANSWERDB, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String CREATE_ANSWER_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_ANSWER + "(" + KEY_QUESTIONID + " TEXT," + KEY_ANSWER
				+ " TEXT," + KEY_ANS_RATE + " TEXT," + KEY_ANS_USER + " TEXT);";
		db.execSQL(CREATE_ANSWER_TABLE);
		System.out.println("Table created successfully");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWER + "");
		onCreate(db);

	}

	/**
	 * THis method use to add the record to database.
	 * 
	 * @param answerList
	 */
	public void addAnswerToDatabase(List<Movie> anslist) {

		SQLiteDatabase db = this.getWritableDatabase();

		for (int i = 0; i < anslist.size(); i++) {

			ContentValues values = new ContentValues();

			values.put(KEY_QUESTIONID, anslist.get(i).getQuestiondetails()
					.toString()); // Contact

			values.put(KEY_ANSWER, anslist.get(i).getAnswerdetails().toString());
			System.out.println("answer:"
					+ anslist.get(i).getAnswerdetails().toString());
			// Name
			values.put(KEY_ANS_USER, anslist.get(i).getAnsusername().toString());
			System.out.println("user:"
					+ anslist.get(i).getAnsusername().toString());
			values.put(KEY_ANS_RATE,
					String.valueOf(anslist.get(i).getRatingtypevalue()));
			System.out.println("rate:"
					+ String.valueOf(anslist.get(i).getRatingtypevalue()));
			db.insert(TABLE_ANSWER, null, values);

		}
		System.out.println("data inserted successfully to Answer table");
		db.close(); // Closing database connection

	}

	// This method use to get all records from the database.
	public Cursor getAllRecordsFromAnswerDb() {

		SQLiteDatabase database = null;
		Cursor cursor = null;

		try {
			database = getReadableDatabase();

			String result = "select * from " + TABLE_ANSWER + "";

			cursor = database.rawQuery(result, null);

			database.execSQL(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}

	/**
	 * This method use to check answer already added to database or not.
	 */

	public boolean Exists(String searchItem) {

		SQLiteDatabase db = this.getReadableDatabase();

		String[] columns = { KEY_QUESTIONID };
		String selection = KEY_QUESTIONID + " =?";
		String[] selectionArgs = { searchItem };
		String limit = "1";

		Cursor cursor = db.query(TABLE_ANSWER, columns, selection,
				selectionArgs, null, null, null, limit);
		boolean exists = (cursor.getCount() > 0);
		cursor.close();
		return exists;
	}

}
