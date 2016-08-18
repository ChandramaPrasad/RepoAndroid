package info.androidhive.customlistviewvolley.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.entity.StringEntity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Util {
	public static final String invalid_email = "Invalid Email Id";
	public static final String invalid_password = "Password should be minimum 5 and maximum 15 characters";
	public static final String invalid_phone_number = "Enter 10 digits";
	public static final String invalid_reentered_password = "Password do not match!";

	private Context context;

	public Util(Context context) {
		this.context = context;
	}

	/*
	 * showErrorAlert() display the error message
	 */

	public static void showErrorAlert(Activity activity, String msg) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				activity);
		alertDialogBuilder.setTitle("");

		// set dialog message
		alertDialogBuilder.setMessage(msg).setCancelable(true);
		alertDialogBuilder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	/*
	 * showErrorAlert() display the message
	 */

	public static void showMsgAlert(Activity activity, String msg) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				activity);
		alertDialogBuilder.setTitle("");

		// set dialog message
		alertDialogBuilder.setMessage(msg).setCancelable(true);
		alertDialogBuilder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	/**
	 * validating email id
	 */
	public static final String isValidEmail(String email) {
		String EMAIL_ID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_ID_PATTERN);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return null;
		}
		return invalid_email;
	}

	/**
	 * validating phone number
	 */
	public static final String isValidPhoneNumber(String phoneNumber) {
		String PHONE_NUMBER_PATTERN = "\\d{10}";
		Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
		Matcher matcher = pattern.matcher(phoneNumber);
		if (matcher.matches()) {
			return null;
		}
		return invalid_phone_number;
	}

	/**
	 * validating password
	 */
	public static final String isValidPassword(String password) {
		if (password.length() >= 5 && password.length() <= 15) {
			return null;
		}
		return invalid_password;
	}

	/**
	 * Generates the Verification code of 6 digits.
	 * 
	 * @return
	 */
	public static String getRandomVerificationCode() {
		int randomNum = 0;
		String code = "";
		Random rn = new Random();
		while (code.length() < 6) {
			randomNum = rn.nextInt(9);
			if (randomNum < 0) {
				randomNum *= -1;
			}
			code += randomNum;
		}
		return code;
	}

	/**
	 * Check for network availability
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity.getActiveNetworkInfo() != null
				&& connectivity.getActiveNetworkInfo().isAvailable()
				&& connectivity.getActiveNetworkInfo().isConnected()) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					Log.w("INTERNET:", String.valueOf(i));
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						Log.w("INTERNET:", "connected!");
						return true;
					}
				}
			}
		}
		return false;
	}

	// *This is use to filter the answer
	public static String encodeURIComponent(String s) {
		String result;

		try {

			result = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20")
					.replaceAll("\\%21", "!").replaceAll("\\%27", "'")
					.replaceAll("\\%28", "(").replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~").replaceAll("%0A", "%20")
					.replaceAll("\\%2F", "OR");
		} catch (UnsupportedEncodingException e) {
			result = s;
		}

		return result;
	}
}
