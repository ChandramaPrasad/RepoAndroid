package info.androidhive.customlistviewvolley.model;

import android.net.Uri;

public class RingTone {
	private Uri rigtoneuri;
	private boolean isVibrateCheck;

	public boolean isVibrateCheck() {
		return isVibrateCheck;
	}

	public void setVibrateCheck(boolean isVibrateCheck) {
		this.isVibrateCheck = isVibrateCheck;
	}

	public RingTone() {
		super();
	}

	public Uri getRigtoneuri() {
		return rigtoneuri;
	}

	public void setRigtoneuri(Uri rigtoneuri) {
		this.rigtoneuri = rigtoneuri;
	}

}
