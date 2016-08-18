package com.motion.receiver;

import info.androidhive.customlistviewvolley.util.NetworkUtil;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		String status = NetworkUtil.getConnectivityStatusString(context);

		// Intent intentone = new Intent(context.getApplicationContext(),
		// PiAnswers.class);
		// intentone.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// context.startActivity(intentone);
	}

}
