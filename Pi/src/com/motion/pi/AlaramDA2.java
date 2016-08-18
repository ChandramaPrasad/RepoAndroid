package com.motion.pi;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AlaramDA2 {

	
public long saveAlaram(AlaramData2 AlaramData2){
		
		SQLiteDatabase sqLiteDatabase = null;
		long ret = 0;
		
		try {
			sqLiteDatabase  = DatabaseHelper2.getInstance().openDatabase();
			
			ContentValues cv = new ContentValues();
			cv.put(DatabaseHelper2.TO_LOCATION, AlaramData2.to_location);
			cv.put(DatabaseHelper2.DISTANCE, AlaramData2.time);
			cv.put(DatabaseHelper2.FROM_LOCATION, AlaramData2.from_location);

			ret = sqLiteDatabase.insert(DatabaseHelper2.TABLE_ALARAM, null, cv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
}

public long updateAlaram(String notesEdit,String id){
	
	SQLiteDatabase sqLiteDatabase = null;
	long ret = 0;
	
	try {
		sqLiteDatabase  = DatabaseHelper2.getInstance().openDatabase();
		
		ContentValues cv = new ContentValues();
		
//		cv.put(DatabaseHelper.AUDIO_NOTES, notesEdit);

		ret = sqLiteDatabase.update(DatabaseHelper2.TABLE_ALARAM, cv,DatabaseHelper2.DATA_ID+"=?",new String[]{id});
	} catch (Exception e) {
		e.printStackTrace();
	}
	return ret;
}

public List<AlaramData2> getRecordingsList(){
	List<AlaramData2> categories = null;
	
	SQLiteDatabase sqLiteDatabase = DatabaseHelper2.getInstance().openDatabase();
	AlaramData2 audioFile = null;
	Cursor cr = null;
	try {
		if(sqLiteDatabase!=null){
		 cr = sqLiteDatabase.query(DatabaseHelper2.TABLE_ALARAM, null, null, null, null, null, null);

		if(cr.moveToFirst()){
			categories = new ArrayList<AlaramData2>();
			do{
				audioFile = new AlaramData2();
				audioFile.id                  = cr.getString(cr.getColumnIndex(DatabaseHelper2.DATA_ID));
				audioFile.to_location           = cr.getString(cr.getColumnIndex(DatabaseHelper2.TO_LOCATION));
				audioFile.time           = cr.getString(cr.getColumnIndex(DatabaseHelper2.DISTANCE));
				audioFile.from_location       = cr.getString(cr.getColumnIndex(DatabaseHelper2.FROM_LOCATION));
//				audioFile.file_creation_time  = cr.getString(cr.getColumnIndex(DatabaseHelper.AUDIO_CREATION_DATE));
//				audioFile.file_notes          = cr.getString(cr.getColumnIndex(DatabaseHelper.AUDIO_NOTES));
				categories.add(audioFile);
			
				
		}while(cr.moveToNext());
		}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (cr != null && !cr.isClosed())
			cr.close();
		DatabaseHelper2.getInstance().closeDatabase();
	}
	return categories;
	
}

public AlaramData2 getRecording(String id){
	
	SQLiteDatabase sqLiteDatabase = DatabaseHelper2.getInstance().openDatabase();
	AlaramData2 audioFile = null;
	Cursor cr = null;
	try {
		 cr = sqLiteDatabase.query(DatabaseHelper2.TABLE_ALARAM, null, DatabaseHelper2.DATA_ID+"=?", new String[]{id}, null, null, null);

		if(cr.moveToFirst()){
				audioFile = new AlaramData2();
				audioFile.id                  = cr.getString(cr.getColumnIndex(DatabaseHelper2.DATA_ID));
				audioFile.to_location           = cr.getString(cr.getColumnIndex(DatabaseHelper2.TO_LOCATION));
				audioFile.time           = cr.getString(cr.getColumnIndex(DatabaseHelper2.DISTANCE));
				audioFile.from_location       = cr.getString(cr.getColumnIndex(DatabaseHelper2.FROM_LOCATION));
//				audioFile.file_creation_time  = cr.getString(cr.getColumnIndex(DatabaseHelper.AUDIO_CREATION_DATE));
//				audioFile.file_notes          = cr.getString(cr.getColumnIndex(DatabaseHelper.AUDIO_NOTES));
				
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (cr != null && !cr.isClosed())
			cr.close();
		DatabaseHelper2.getInstance().closeDatabase();
	}
	return audioFile;
	
}


public int deleteRecording(String id){
	
	SQLiteDatabase sqLiteDatabase = DatabaseHelper2.getInstance().openDatabase();
	AlaramData2 audioFile = null;
	int count = 0;
	try {
		 count = sqLiteDatabase.delete(DatabaseHelper2.TABLE_ALARAM, DatabaseHelper2.DATA_ID+"=?", new String[]{id});

		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		
		DatabaseHelper2.getInstance().closeDatabase();
	}
	return count;
	
}

}
