//package com.motion.pi;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.database.DataSetObserver;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.View.OnKeyListener;
//import android.widget.AbsListView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class ChatBubbleActivity extends Activity {
//    private static final String TAG = "ChatActivity";
//    Intent intent;
//    private ChatArrayAdapter chatArrayAdapter;
//    private ListView listView;
//    private EditText chatText;
//    private Button buttonSend;
//    private boolean side = false;
//    String fnam;
//    String fids;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Intent i = getIntent();
//        setContentView(R.layout.activity_chat);
//
////        Here the user types the message and hits on send button.. then message will fetches to SERVER...
////        & frm there it moves to ur Friend mobile screen with MESAGE NOTIFICATION...
//        fnam =getIntent().getStringExtra("friend");
//        fids = getIntent().getStringExtra("fid");
//        TextView sf = (TextView)findViewById(R.id.textView46);
//        sf.setText(""+fnam);
//        
//        buttonSend = (Button) findViewById(R.id.buttonSend);
//
//        listView = (ListView) findViewById(R.id.listView1);
//
//        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.activity_chat_singlemessage);
//        listView.setAdapter(chatArrayAdapter);
//
//        chatText = (EditText) findViewById(R.id.chatText);
//        chatText.setOnKeyListener(new OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    return sendChatMessage();
//                }
//                return false;
//            }
//        });
//        buttonSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                sendChatMessage();
//            }
//        });
//
//        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
//        listView.setAdapter(chatArrayAdapter);
//
//        //to scroll the list view to bottom on data change
//        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                listView.setSelection(chatArrayAdapter.getCount() - 1);
//            }
//        });
//    }
//
//    private boolean sendChatMessage() {
//        chatArrayAdapter.add(new ChatMessage(side, chatText.getText().toString()));
//        chatText.setText("");
//        side = !side;
//        return true;
//    }
//
//}