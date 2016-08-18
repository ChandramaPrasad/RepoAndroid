package com.motion.pi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Selection;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

/**
 * Created by Admin on 28-08-2015.
 */
public class HMainActivity extends Activity{
    EditText display, writeboard;
    Button submit, select,clear;
    public static final String TAG = "EmojiSelection";
    GridView gridView;
    CustomEmojis customEmojis;
    final Context context = this;
    Dialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sssmain);

        initUiElement();
        customEmojis = new CustomEmojis(this);
//        gridView.setAdapter(customEmojis);
        gridView = (GridView) findViewById(R.id.gridview1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setText(display.getText().append(writeboard.getText()));
                writeboard.setText("");

            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                Intent i = new Intent(HMainActivity.this, EmojiSelection.class);
//                startActivity(i);
                 dialog = new Dialog(HMainActivity.this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.emojis);

                dialog.show();
            }
        });
        dialog.setContentView(R.layout.emojis);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

//                        Log.i(TAG, "U are in OnItemSelected");
                SharedPreferences preferences = HMainActivity.this
                        .getSharedPreferences("pref", MODE_WORLD_READABLE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("smiley", position);
                System.out.println("Selected emojis ---> " + position);

                // dont forgot to commit preference
                editor.commit();

                finish();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setText("");
            }
        });
    }

    private void initUiElement() {
        display = (EditText) findViewById(R.id.display);
        writeboard = (EditText) findViewById(R.id.writeboard);
        submit = (Button) findViewById(R.id.btnsubmit);
        select = (Button) findViewById(R.id.btnselect);
        clear = (Button)findViewById(R.id.btnclear);
    }

    CharSequence cs;
    CustomEmojis emojis;
    int index;

    @Override
    protected void onRestart() {
        super.onRestart();
        emojis = new CustomEmojis(this);

        SharedPreferences preferences = this.getSharedPreferences("pref",
                this.MODE_WORLD_READABLE);
        index = preferences.getInt("smiley", 0);
        System.out.println("smiley index is---> " + index);

        ImageGetter imageGetter = new ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                Drawable d = getResources().getDrawable(emojis.images[index]);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                return d;
            }
        };
        cs = Html.fromHtml(
                "<img src ='"
                        + getResources().getDrawable(emojis.images[index])
                        + "'/>", imageGetter, null);
        writeboard.setText(cs);
        int position = writeboard.length();
        Editable editable = writeboard.getText();
        Selection.setSelection(editable, position);
    }


    private void initUIElement() {
        gridView = (GridView) findViewById(R.id.gridview1);

    }
}
