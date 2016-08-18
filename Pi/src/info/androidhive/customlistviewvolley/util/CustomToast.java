package info.androidhive.customlistviewvolley.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.motion.pi.R;

public class CustomToast {

	static Toast toast;

	public static String SetmessageToast(String message, Context context) {

		View view;
		TextView text;

		toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);

		toast.setText(message);
		view = toast.getView();
		text = (TextView) view.findViewById(android.R.id.message);
		text.setTextColor(context.getResources().getColor(R.color.Green));
		text.setShadowLayer(0, 0, 0, 0);
		text.setTextSize(15);
		toast.setDuration(900);

		view.setBackgroundResource(R.color.white);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();

		return toast.getView().toString();

	}

	public static void removeToast(boolean remove) {

		if (remove == true && toast != null) {

			toast.cancel();

			System.out.println("toast cancel");

		}

	}

}
