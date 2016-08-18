package com.motion.pi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginSQLiteAdapter {

	public static final String MYDATABASE_NAMEs = "Login";
	public static final String MYDATABASE_TABLEs = "Logs";
	public static final int MYDATABASE_VERSIONs = 1;
	public static final String KEY_ID = "_id";
	public static  String KEY_CONTENT1 = "m1";
	public static  String KEY_CONTENT2 = "n1";

	//create table MY_DATABASE (ID integer primary key, Content text not null);
	private static final String SCRIPT_CREATE_DATABASE =
		"create table " + MYDATABASE_TABLEs + " ("
		+ KEY_ID + " integer primary key autoincrement, "
		+ KEY_CONTENT1 + " text not null, "
		+ KEY_CONTENT2 + " text not null);";
	
	private SQLiteHelperss sqLiteHelper;
	private SQLiteDatabase sqLiteDatabase;

	private Context context;
	
	public LoginSQLiteAdapter(Context c){
		context = c;
	}
	
	public LoginSQLiteAdapter openToRead() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelperss(context, MYDATABASE_NAMEs, null, MYDATABASE_VERSIONs);
		sqLiteDatabase = sqLiteHelper.getReadableDatabase();
		return this;	
	}
	
	public LoginSQLiteAdapter openToWrite() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelperss(context, MYDATABASE_NAMEs, null, MYDATABASE_VERSIONs);
		sqLiteDatabase = sqLiteHelper.getWritableDatabase();
		return this;	
	}
	
	public void close(){
		sqLiteHelper.close();
	}
	
	public long insert(String content1, String content2){
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_CONTENT1, content1);
		contentValues.put(KEY_CONTENT2, content2);
		return sqLiteDatabase.insert(MYDATABASE_TABLEs, null, contentValues);
	}
	
	public int deleteAll(){
		return sqLiteDatabase.delete(MYDATABASE_TABLEs, null, null);
	}
	
	public void delete_byID(int id){
		sqLiteDatabase.delete(MYDATABASE_TABLEs, KEY_ID+"="+id, null);
	}
	
	public Cursor queueAll(){
		String[] columns = new String[]{KEY_ID, KEY_CONTENT1, KEY_CONTENT2};
		Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLEs, columns, 
				null, null, null, null, null);
		
		return cursor;
	}
	
	public class SQLiteHelperss extends SQLiteOpenHelper {

		public SQLiteHelperss(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(SCRIPT_CREATE_DATABASE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

	}
	
	
}
