//package com.motion.pi;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//public class PiDA {
//
//	
//public long saveAlaram(AlaramData alaramData){
//		
//		SQLiteDatabase sqLiteDatabase = null;
//		long ret = 0;
//		
//		try {
//			sqLiteDatabase  = DatabaseHelper.getInstance().openDatabase();
//			
//			ContentValues cv = new ContentValues();
//			cv.put(DatabaseHelper.TO_LOCATION, alaramData.to_location);
//			cv.put(DatabaseHelper.DISTANCE, alaramData.time);
//			cv.put(DatabaseHelper.FROM_LOCATION, alaramData.from_location);
//
//			ret = sqLiteDatabase.insert(DatabaseHelper.TABLE_ALARAM, null, cv);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ret;
//}
//
//public long updateAlaram(String notesEdit,String id){
//	
//	SQLiteDatabase sqLiteDatabase = null;
//	long ret = 0;
//	
//	try {
//		sqLiteDatabase  = DatabaseHelper.getInstance().openDatabase();
//		
//		ContentValues cv = new ContentValues();
//		
////		cv.put(DatabaseHelper.AUDIO_NOTES, notesEdit);
//
//		ret = sqLiteDatabase.update(DatabaseHelper.TABLE_ALARAM, cv,DatabaseHelper.DATA_ID+"=?",new String[]{id});
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return ret;
//}
//
//public List<AlaramData> getRecordingsList(){
//	List<AlaramData> categories = null;
//	
//	SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
//	AlaramData audioFile = null;
//	Cursor cr = null;
//	try {
//		if(sqLiteDatabase!=null){
//		 cr = sqLiteDatabase.query(DatabaseHelper.TABLE_ALARAM, null, null, null, null, null, null);
//
//		if(cr.moveToFirst()){
//			categories = new ArrayList<AlaramData>();
//			do{
//				audioFile = new AlaramData();
//				audioFile.id                  = cr.getString(cr.getColumnIndex(DatabaseHelper.DATA_ID));
//				audioFile.to_location           = cr.getString(cr.getColumnIndex(DatabaseHelper.TO_LOCATION));
//				audioFile.time           = cr.getString(cr.getColumnIndex(DatabaseHelper.DISTANCE));
//				audioFile.from_location       = cr.getString(cr.getColumnIndex(DatabaseHelper.FROM_LOCATION));
////				audioFile.file_creation_time  = cr.getString(cr.getColumnIndex(DatabaseHelper.AUDIO_CREATION_DATE));
////				audioFile.file_notes          = cr.getString(cr.getColumnIndex(DatabaseHelper.AUDIO_NOTES));
//				categories.add(audioFile);
//			
//				
//		}while(cr.moveToNext());
//		}
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	}finally {
//		if (cr != null && !cr.isClosed())
//			cr.close();
//		DatabaseHelper.getInstance().closeDatabase();
//	}
//	return categories;
//	
//}
//
//public AlaramData getRecording(String id){
//	
//	SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
//	AlaramData audioFile = null;
//	Cursor cr = null;
//	try {
//		 cr = sqLiteDatabase.query(DatabaseHelper.TABLE_ALARAM, null, DatabaseHelper.DATA_ID+"=?", new String[]{id}, null, null, null);
//
//		if(cr.moveToFirst()){
//				audioFile = new AlaramData();
//				audioFile.id                  = cr.getString(cr.getColumnIndex(DatabaseHelper.DATA_ID));
//				audioFile.to_location           = cr.getString(cr.getColumnIndex(DatabaseHelper.TO_LOCATION));
//				audioFile.time           = cr.getString(cr.getColumnIndex(DatabaseHelper.DISTANCE));
//				audioFile.from_location       = cr.getString(cr.getColumnIndex(DatabaseHelper.FROM_LOCATION));
////				audioFile.file_creation_time  = cr.getString(cr.getColumnIndex(DatabaseHelper.AUDIO_CREATION_DATE));
////				audioFile.file_notes          = cr.getString(cr.getColumnIndex(DatabaseHelper.AUDIO_NOTES));
//				
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	}finally {
//		if (cr != null && !cr.isClosed())
//			cr.close();
//		DatabaseHelper.getInstance().closeDatabase();
//	}
//	return audioFile;
//	
//}
//
//
//public int deleteRecording(String id){
//	
//	SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
//	AlaramData audioFile = null;
//	int count = 0;
//	try {
//		 count = sqLiteDatabase.delete(DatabaseHelper.TABLE_ALARAM, DatabaseHelper.DATA_ID+"=?", new String[]{id});
//
//		
//	} catch (Exception e) {
//		e.printStackTrace();
//	}finally {
//		
//		DatabaseHelper.getInstance().closeDatabase();
//	}
//	return count;
//	
//}
//
//}
