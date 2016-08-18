package info.androidhive.customlistviewvolley.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		Toast.makeText(context, "Alarm received", 900).show();
	}

}
