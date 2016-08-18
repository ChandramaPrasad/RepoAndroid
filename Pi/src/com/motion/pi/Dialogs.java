package com.motion.pi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by babu on 28-08-2015.
 */
public class Dialogs extends Activity {

    public final int CATEGORY_ID =0;
    private Context mContext;
    Dialog dialog;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.main);
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)
            {
                     showDialog(CATEGORY_ID);

            }
        });
    }
    protected Dialog onCreateDialog(int id) {

        switch(id) {

            case CATEGORY_ID:

                AlertDialog.Builder builder;
                Context mContext = this;
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.activity_main,(ViewGroup) findViewById(R.id.layout_root));
                GridView gridview = (GridView)layout.findViewById(R.id.gridview);
                gridview.setAdapter(new ImageAdapter(this));

                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int arg2, long arg3) {
                        // TODO Auto-generated method stub
                        Toast.makeText(arg1.getContext(), "Position is " + arg2, 3000).show();
                    }
                });

                ImageView close = (ImageView) layout.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){
                        dialog.dismiss();
                    }
                });

                builder = new AlertDialog.Builder(mContext);
                builder.setView(layout);
                dialog = builder.create();
                break;
            default:
                dialog = null;
        }
        return dialog;
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mInflater;
        public ImageAdapter(Context c) {
            mInflater = LayoutInflater.from(c);
            mContext = c;
        }
        public int getCount() {
            return mThumbIds.length;
        }
        public Object getItem(int position) {
            return null;
        }
        public long getItemId(int position) {
            return 0;
        }
        // create a new ImageView for each item referenced by the
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {  // if it's not recycled,
                convertView = mInflater.inflate(R.layout.custom, null);
                convertView.setLayoutParams(new GridView.LayoutParams(100,100));
                holder = new ViewHolder();
//                holder.title = (TextView) convertView.findViewById(R.id.categoryText);
//                holder.icon = (ImageView )convertView.findViewById(R.id.categoryimage);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.icon.setAdjustViewBounds(true);
            holder.icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.icon.setPadding(5, 5, 5, 5);
            holder.title.setText(categoryContent[position]);
            holder.icon.setImageResource(mThumbIds[position]);
            return convertView;
        }
        class ViewHolder {
            TextView title;
            ImageView icon;
        }
        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.l10, R.drawable.l11,R.drawable.l14,
                R.drawable.l15,R.drawable.l16, R.drawable.l17,
                R.drawable.l18, R.drawable.l19,R.drawable.l20
        };

    }
    private String[] categoryContent = {
            "Pubs", "Restuarants","shopping",
            "theatre","train", "taxi",
            "gas", "police","hospital"
    };


}

