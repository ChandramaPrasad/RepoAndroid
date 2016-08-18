package com.motion.pi;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{

	private static Context context;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
	}
	
	public static Context getAppContext(){
		return context;
		
	}
	public static MyApplication getInstance()
	{
		return (MyApplication) context;
	}
	
}