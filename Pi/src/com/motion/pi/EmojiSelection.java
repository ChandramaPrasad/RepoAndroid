package com.motion.pi;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by Admin on 28-08-2015.
 */
public class EmojiSelection extends Activity {
    public static final String TAG = "EmojiSelection";

    GridView gridView;
    CustomEmojis customEmojis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emojis);

        initUIElement();

        customEmojis = new CustomEmojis(this);
        gridView.setAdapter(customEmojis);
        gridView = (GridView) findViewById(R.id.gridview1);
        final Dialog dialog = new Dialog(EmojiSelection.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.emojis);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                Log.i(TAG, "U are in OnItemSelected");
                SharedPreferences preferences = EmojiSelection.this
                        .getSharedPreferences("pref", MODE_WORLD_READABLE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("smiley", position);
                System.out.println("Selected emojis ---> " + position);

                // dont forgot to commit preference
                editor.commit();

                finish();
            }
        });
        dialog.show();
    }

    private void initUIElement() {
        gridView = (GridView) findViewById(R.id.gridview1);

    }

}
