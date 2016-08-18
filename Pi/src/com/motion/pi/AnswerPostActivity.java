package com.motion.pi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class AnswerPostActivity extends Activity implements OnClickListener {

	private ImageView postButton;
	private Toast toast = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_answer_layout);
		initaliseViews();
		registerEventds();
	}

	/**
	 * This method use to registert the events.
	 */
	private void registerEventds() {

		postButton.setOnClickListener(this);

	}

	private void initaliseViews() {

		postButton = (ImageView) findViewById(R.id.postButton);
		toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.postButton:

			postAnswer();

			break;

		default:
			break;
		}

	}

	/**
	 * This method is responsible to post answer to question.
	 */
	private void postAnswer() {

		Intent postIntent = new Intent(AnswerPostActivity.this, PiAnswers.class);
		startActivity(postIntent);
		toast.setText("Your answer is posted");
		toast.show();
		finish();

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (toast != null) {
			toast.cancel();

		}
	}

}
