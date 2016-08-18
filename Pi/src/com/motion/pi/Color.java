package com.motion.pi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Admin on 08-09-2015.
 */
public class Color extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color);
        TextView t = (TextView)findViewById(R.id.textView73);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                change bg color for chatt screen
            }
        });

    }
}
