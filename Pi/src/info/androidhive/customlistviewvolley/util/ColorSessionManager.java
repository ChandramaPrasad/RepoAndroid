package info.androidhive.customlistviewvolley.util;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class ColorSessionManager {

	public static ArrayList<Boolean> listBoolTrain = new ArrayList<Boolean>();

	private int giftRemaining;

	private SharedPreferences prefs;

	// Editor for Shared preferences
	Editor editor;

	// Context
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "AndroidHivePref";

	public ColorSessionManager(Context context) {
		this._context = context;

		prefs = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

		editor = prefs.edit();
	}

	public void setNumberOfGits(int numberOfGifts) {
		editor.putInt("numberOfGifts", numberOfGifts);

		editor.commit();
	}

	public int getNumberOfGits() {
		int nog = prefs.getInt("numberOfGifts", -5);

		return nog;
	}

	public void initializerBooleans(int arraySiz) {
		int arraySize = prefs.getInt("arraySize", 10);

		for (int x = 0; x < arraySize; x++) {
			editor.putBoolean("Bool" + x, false);

			editor.commit();
		}
	}

	public void setItemVisited(int x) {
		editor.putBoolean("Bool" + x, true);

		editor.commit();
	}

	public boolean isItemVisited(int x) {
		return prefs.getBoolean("Bool" + x, false);
	}

	public int getUnVisitedItemCount() {
		int count = 0;

		int arraySize = prefs.getInt("arraySize", 10);

		for (int x = 0; x < arraySize; x++)// listBoolTrain.size(); x++)
		{
			boolean bol = prefs.getBoolean("Bool" + x, false);

			if (!bol) {
				count++;
			}
		}

		return count;
	}

	public void remainingGift() {
	}

	public void setFirstRun(boolean status) {
		editor.putBoolean("firstrun", status);

		editor.commit();
	}

	public boolean getFirstRun() {
		return prefs.getBoolean("firstrun", true);
	}

	public void removeAllPreferences() {
		prefs.edit().clear().commit();
	}

	public void removeKey(String keyName) {
		prefs.edit().remove(keyName).commit();
	}

	public void showAll() {
		Map<String, ?> keys = prefs.getAll();

		for (Map.Entry<String, ?> entry : keys.entrySet()) {
			Log.d("map values", entry.getKey() + ": "
					+ entry.getValue().toString());
		}
	}

	public void setArraySize(int boolSize) {
		editor.putInt("arraySize", boolSize);

		editor.commit();

		initializerBooleans(boolSize);
	}

	public int getArraySize() {
		return prefs.getInt("arraySize", -1);
	}

	public boolean ItemVisited(int position) {
		return prefs.getBoolean("Bool" + position, false);

	}

}
