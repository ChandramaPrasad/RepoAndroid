package com.motion.pi;

/**
 * Created by Admin on 02-09-2015.
 */


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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class Smiley extends Activity implements OnClickListener {
    public static final String TAG = "EmojiSelection";
    EditText display, writeboard;
    Button submit, select,clear;
    GridView gridView;
    CustomEmojis customEmojis;
    Dialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sssmain);

        initUiElement();

        submit.setOnClickListener(this);
        select.setOnClickListener(this);
        clear.setOnClickListener(this);
        customEmojis = new CustomEmojis(this);
        gridView = (GridView) findViewById(R.id.gridview1);
        dialog = new Dialog(Smiley.this);
//        dialog.setContentView(R.layout.emojis);
//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1,
//                                    int position, long arg3) {
//
//                Log.i(TAG, "U are in OnItemSelected");
//                SharedPreferences preferences = Smiley.this
//                        .getSharedPreferences("pref", MODE_WORLD_READABLE);
//                SharedPreferences.Editor editor = preferences.edit();
//
//                editor.putInt("smiley", position);
//                System.out.println("Selected emojis ---> " + position);
//
//                // dont forgot to commit preference
//                editor.commit();
//
//                finish();
//            }
//        });
//        dialog.show();
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
        Context mContext = this;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.mygrid, (ViewGroup) findViewById(R.id.layout_root));
        gridView = (GridView) layout.findViewById(R.id.gridview1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsubmit:
                display.setText(display.getText().append(writeboard.getText()));
                writeboard.setText("");
                break;
            case R.id.btnselect:
//                Intent i = new Intent(Smiley.this, EmojiSelection.class);

                dialog.setContentView(R.layout.emojis);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int position, long arg3) {

                        Log.i(TAG, "U are in OnItemSelected");
                        SharedPreferences preferences = Smiley.this
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
//                startActivity(i);
                break;
            case R.id.btnclear:
                display.setText("");
                break;

            default:
                break;
        }

    }

}

