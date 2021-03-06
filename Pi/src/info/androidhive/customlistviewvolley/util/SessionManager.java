package info.androidhive.customlistviewvolley.util;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
	// Shared Preferences
	private SharedPreferences pref;

	// Editor for Shared preferences
	private Editor editor;

	// Context
	private Context context;

	// Shared pref mode
	private int PRIVATE_MODE = 0;

	// All Shared Preferences Keys
	private static final String IS_LOGIN = "IsLoggedIn";

	// User name (make variable public to access from outside)
	public static final String KEY_NAME = "name";

	// Email address (make variable public to access from outside)
	public static final String KEY_EMAIL = "email";

	// customer alert dialog
	// private CustomAlertDialog customAlertDialog;

	// Constructor
	public SessionManager(Context context) {
		this.context = context;
		pref = context.getSharedPreferences("", PRIVATE_MODE);
		editor = pref.edit();
	}

	/**
	 * Create login session
	 * */
	public void createLoginSession(String name, String email) {
		// Storing login value as TRUE
		editor.putBoolean(IS_LOGIN, true);

		// Storing name in pref
		editor.putString(KEY_NAME, name);

		// Storing email in pref
		editor.putString(KEY_EMAIL, email);

		// commit changes
		editor.commit();
	}

	/**
	 * Check login method wil check user login status If false it will redirect
	 * user to login page Else won't do anything
	 * */
	public void checkLogin() {
		// Check login status
		if (!this.isLoggedIn()) {
			// customAlertDialog = new CustomAlertDialog(context);
			// customAlertDialog.showMsgAlert(context.getString(R.string.message),
			// context.getString(R.string.sign_in_message));

		}

	}

	/**
	 * Get stored session data
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		// user name
		user.put(KEY_NAME, pref.getString(KEY_NAME, null));

		// user email id
		user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

		// return user
		return user;
	}

	/**
	 * Clear session details
	 * */
	public void logoutUser() {
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();

		// // After logout redirect user to Loing Activity
		// Intent i = new Intent(_context, LoginActivity.class);
		// // Closing all the Activities
		// i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//
		// // Add new Flag to start new Activity
		// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//
		// // Staring Login Activity
		// _context.startActivity(i);
	}

	/**
	 * Quick check for login
	 * **/
	// Get Login State
	public boolean isLoggedIn() {
		return pref.getBoolean(IS_LOGIN, false);
	}
}
