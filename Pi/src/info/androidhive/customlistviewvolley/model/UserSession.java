package info.androidhive.customlistviewvolley.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

@SuppressLint("CommitPrefEdits")
public class UserSession {

	private SharedPreferences prefs;

	public UserSession(Context cntx) {
		// TODO Auto-generated constructor stub
		prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
	}

	public void setusename(String usename) {
		prefs.edit().putString("usename", usename).commit();
	}

	public String getusename() {
		String usename = prefs.getString("usename", "");
		return usename;
	}

	public void setpassword(String password) {
		prefs.edit().putString("password", password).commit();
	}

	public String getpassword() {
		String usename = prefs.getString("password", "");
		return usename;
	}

}
