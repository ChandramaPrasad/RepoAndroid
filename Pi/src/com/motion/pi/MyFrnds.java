package com.motion.pi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

public class MyFrnds extends ListActivity{
	 ListView lv;
	    public static final String MyPREFERENCES = "MyPrefs" ;
	    JSONObject jsonObj = null;
	    private ProgressDialog pDialog;
	    String response;
	    ConnectionDetector cd;
	    String qdet;
	    // Getting JSON Array node
//	    String ata;
	    String sata;
	    private static String url = "http://166.62.81.118:18080/SpringRestCrud/signup/userlist";
	    Boolean isInternetPresent = false;
	    // JSON Node names
//	    ArrayList<HashMap<String, String>> contactList;
	    private static final String TAG_CONTACTS = "";
	    private static final String TAG_ID = "idquestion";
	    private static final String TAG_NAME = "questiondetails";
	    private static final String TAG_userpost="quserid";
	    private static final String TAG_Quest="qusername";
	    private static final String TAG_Qid="qtypeid";
//	    private static final String TAG_date ="createddate";
	    ArrayList<HashMap<String, String>> contactList;
	    String dt;
	    HashMap mn;
	    //    private FeedListAdapter listAdapter;
	    //	private static final String
	    // Getting JSON Array node
	    String uname;
	    String ata;
	    String uid;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.categ);
	        TextView name = (TextView)findViewById(R.id.textView5);

	        SharedPreferences sp = getSharedPreferences("prefs", 0);

	        uname = sp.getString("name","noname");
	        uid = sp.getString("id","noid" );
	        new GetContacts().execute();
	        contactList = new ArrayList<HashMap<String, String>>();


	        lv = getListView();
//
//
//	        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//	            @Override
//	            public void onItemClick(AdapterView<?> parent, View view,
//	                                    int position, long id) {
//	                // getting values from selected ListItem
////	                String selectedFromList = (String) lv.getItemAtPosition(position);
////	                String cm;
////	                cm = (String) parent.getItemAtPosition(position);
//	                HashMap<String, String> map = (HashMap<String, String>) parent.getItemAtPosition(position);
//	                String value = map.get(TAG_NAME);
//	                String idd = map.get(TAG_ID);
////	                    String products = map.get(TAG_PRODUCTS);
////	                    String name= map.get(TAG_NAME);
//
////	                mn= (HashMap) lv.getItemAtPosition(position);
////	                mn.get(TAG_NAME);
//	                Toast.makeText(getApplicationContext(), "" + value, Toast.LENGTH_LONG).show();
//	                Intent i = new Intent(MyFrnds.this, Notessend.class);
//	                i.putExtra("fname", value);
//	                i.putExtra("fid", idd);
//	                startActivity(i);
//	            }
//	        });
	        name.setText("" + uname);
//	        Toast.makeText(getApplicationContext(),""+uname,Toast.LENGTH_LONG).show();

	        

	    }
	    /**
	     * Async task class to get json by making HTTP call
	     * */
	    private class GetContacts extends AsyncTask<Void, Void, Void> {


	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            // Showing progress dialog


	            pDialog = new ProgressDialog(MyFrnds.this);

	            pDialog.setMessage("Please wait...");

	            pDialog.setCancelable(true);
	            pDialog.show();

	        }

	        @Override
	        protected Void doInBackground(Void... arg0) {
	            // Creating service handler class instance

	            ServiceHandler sh = new ServiceHandler();

	            // Making a request to url and getting response
	            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
	            String vl="{\"jsonBean\":"+jsonStr+"}";
	            Log.d("Response: ", "> " + vl);

	            //JSONBean res=null;
	            if (vl != null) {
	                try {
	                    //					JSONObject jsonObj = new JSONObject(jsonStr);
	                    Gson gson = new Gson();
	                    Frnds res = gson.fromJson(vl, Frnds.class);
	                    List<Frndslist> bean = (List<Frndslist>) res.getJsonBean();

	                    // Getting JSON Array node
	                    //contacts = jsonObj.getJSONArray(vl);

	                    // looping through All Contacts
	                    for (Frndslist listBean : bean) {

	                       String name = listBean.getUserid();
	                       String nid = listBean.getIdsignup();

	                        HashMap<String, String> contact = new HashMap<String, String>();

//	                        FeedItem item = new FeedItem();
	                        // adding each child node to HashMap key => value
//	                        item.put(TAG_ID, qid);

	                        contact.put(TAG_NAME, name);
	                        contact.put(TAG_ID, nid);
//	                        contact.put(TAG_Quest, abt);
//	                        contact.put(TAG_Qid, qtyp);
//	                        contact.put(TAG_date, dt);
//	                        System.out.println("DATEE**********"+dt);
	                        //                        contact.put(TAG_EMAIL, email);
	                        //                        contact.put(TAG_PHONE_MOBILE, mobile);

	                        // adding contact to contact list
	                        contactList.add(contact);




	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }

	            return null;
	        }

	        @Override
	        protected void onPostExecute(Void result) {
	            super.onPostExecute(result);
	            // Dismiss the progress dialog
	            if (pDialog.isShowing())
	                pDialog.dismiss();

//	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	            Date convertedDate = new Date();

//	            try {
//	                Date date = (Date) dateFormat.parse(TAG_date);
//	                System.out.println(date);
//	            } catch (ParseException e) {
//	                // TODO Auto-generated catch block
//	                e.printStackTrace();
//	            } catch (java.text.ParseException e) {
//	                e.printStackTrace();
//	            }

//	            System.out.println(date);
	            /**
	             * Updating parsed JSON data into ListView
	             * */
	            ListAdapter adapter = new SimpleAdapter(
	                    MyFrnds.this, contactList,
	                    R.layout.flist_item, new String[] {
	                    TAG_NAME,
	                    TAG_ID
//								TAG_userpost,TAG_Quest,TAG_Qid,TAG_date
	            }, new int[] { R.id.names,R.id.idies

//								,R.id.qdets,R.id.uid,R.id.abt,R.id.qtid,R.id.date
	            });


	            setListAdapter(adapter);



	        }

	    }

}
