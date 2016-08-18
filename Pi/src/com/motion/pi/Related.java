//package com.motion.pi;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.json.JSONObject;
//
//import android.app.AlertDialog;
//import android.app.ListActivity;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.gson.Gson;
//
///**
// * Created by Admin on 27-08-2015.
// */
//public class Related extends ListActivity {
//    ListView lv;
//    public static final String MyPREFERENCES = "MyPrefs" ;
//    JSONObject jsonObj = null;
//    private ProgressDialog pDialog;
//    String response;
//    ConnectionDetector cd;
//    String qdet;
//    String rname;
//    String rid;
//    // Getting JSON Array node
////    String ata;
//    String sata;
////    private static String url = "http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswerforquestion/{idqtype}";
//    Boolean isInternetPresent = false;
//    private static  String url ="http://166.62.81.118:18080/SpringRestCrud/questionanswer/getanswer";
//    // JSON Node names
////    ArrayList<HashMap<String, String>> contactList;
//    private static final String TAG_CONTACTS = "";
//    private static final String TAG_ID = "ansuserid";
//    private static final String TAG_NAME = "answerdetails";
//    private static final String TAG_userpost="createddate";
////    private static final String TAG_Quest="aboutmyquestion";
////    private static final String TAG_Qid="qtypeid";
////    private static final String TAG_date ="createddate";
//    ArrayList<HashMap<String, String>> contactList;
//    HashMap mn;
//    //    private FeedListAdapter listAdapter;
//    //	private static final String
//    // Getting JSON Array node
//    String ata;
//    AlertDialog ald;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.relatans);
//        new GetContacts().execute();
//        TextView name = (TextView)findViewById(R.id.textView5);
////        new QuestContacts().execute();
////        Intent i= getIntent();
////        String uname = i.getStringExtra("fname");
////        name.setText(""+uname);
//        Button posts = (Button)findViewById(R.id.button24);
////        SharedPreferences sp = getSharedPreferences("prefs", 0);
////
////        rname = sp.getString("name","noname");
////        rid = sp.getString("id","noid" );
//
//        posts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ald = new AlertDialog.Builder(Related.this).create();
//                ald.setMessage("Your Question has been Posted Sucessfully");
////            Toast.makeText(MainActivity.this, "Please check Your Network Connection", Toast.LENGTH_SHORT).show();
//
////                Toast.makeText(getApplicationContext(),"Your Question has been Posted",Toast.LENGTH_SHORT).show();
//                Intent i=new Intent(Related.this,Details.class);
//
//                ald.show();
//                startActivity(i);
//            }
//
//        });
//        
//        contactList = new ArrayList<HashMap<String, String>>();
//
////        listAdapter = new FeedListAdapter(this, contactList);
////        lv.setAdapter(listAdapter);
//
//        lv = getListView();
//
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // getting values from selected ListItem
////                String selectedFromList = (String) lv.getItemAtPosition(position);
////                String cm;
////                cm = (String) parent.getItemAtPosition(position);
//                HashMap<String, String> map = (HashMap<String, String>) parent.getItemAtPosition(position);
//                String value = map.get(TAG_ID);
////                    String products = map.get(TAG_PRODUCTS);
////                    String name= map.get(TAG_NAME);
//
////                mn= (HashMap) lv.getItemAtPosition(position);
////                mn.get(TAG_NAME);
//                Toast.makeText(getApplicationContext(), "" + value, Toast.LENGTH_LONG).show();
////                Intent i = new Intent(Related.this, Details.class);
//////                i.putExtra("Question", value);
////                startActivity(i);
//            }
//        });
////        name.setText("" + rname);
//        Toast.makeText(getApplicationContext(),""+rname,Toast.LENGTH_LONG).show();
//
//
//
//    }
//    /**
//     * Async task class to get json by making HTTP call
//     * */
//    private class GetContacts extends AsyncTask<Void, Void, Void> {
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // Showing progress dialog
//
//
//            pDialog = new ProgressDialog(Related.this);
//
//            pDialog.setMessage("Please wait...");
//
//            pDialog.setCancelable(true);
//            pDialog.show();
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            // Creating service handler class instance
//
//            ServiceHandler sh = new ServiceHandler();
//
//            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
//            String vl="{\"jsonBean\":"+jsonStr+"}";
//            Log.d("Response: ", "> " + vl);
//
//            //JSONBean res=null;
//            if (vl != null) {
//                try {
//                    //					JSONObject jsonObj = new JSONObject(jsonStr);
//                    Gson gson = new Gson();
//                    JSONanss res = gson.fromJson(vl, JSONanss.class);
//                    List<AnswerHistory> bean = (List<AnswerHistory>) res.getJsonBean();
//
//                    // Getting JSON Array node
//                    //contacts = jsonObj.getJSONArray(vl);
//
//                    // looping through All Contacts
//                    for (AnswerHistory listBean : bean) {
//
//                        String abt=listBean.getAnsuserid();
//                        String dt=listBean.getAnswerdetails();
//                        String qid=listBean.getCreateddate();
//                        System.out.println();
////                        String qtyp=listBean.getQtypeid();
////                        qdet=listBean.getQuestiondetails();
////                        String quid=listBean.getUserid();
//
//                        HashMap<String, String> contact = new HashMap<String, String>();
//
////                        FeedItem item = new FeedItem();
//                        // adding each child node to HashMap key => value
////                        item.put(TAG_ID, qid);
//
//                        contact.put(TAG_ID, dt);
//                        contact.put(TAG_NAME, qid);
//                        contact.put(TAG_userpost, abt);
////                        contact.put(TAG_Qid, qtyp);
////                        contact.put(TAG_date, dt);
//                        //                        contact.put(TAG_EMAIL, email);
//                        //                        contact.put(TAG_PHONE_MOBILE, mobile);
//
//                        // adding contact to contact list
//                        contactList.add(contact);
//
//
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            // Dismiss the progress dialog
//            if (pDialog.isShowing())
//                pDialog.dismiss();
//
//
//
//            /**
//             * Updating parsed JSON data into ListView
//             * */
//            ListAdapter adapter = new SimpleAdapter(
//                    Related.this, contactList,
//                    R.layout.qlist_item, new String[] {
//                    TAG_ID,
//                    TAG_NAME
//
////							TAG_userpost,TAG_Quest,TAG_Qid,TAG_date
//            }, new int[] { R.id.qid,R.id.textView87,
//            });
////            System.out.println("***********"+TAG_ID);
//
////							,R.id.qdets,R.id.uid,R.id.abt,R.id.qtid,R.id.date
//
//
//
//            setListAdapter(adapter);
//
//
//
//        }
//
//    }
//
//}
