package com.hanium.android.mydata.ui.benefit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.hanium.android.mydata.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class XAMPPTestActivity extends AppCompatActivity {

    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_USERNAME = "userName";
    private static final String TAG_USERID = "userID";

    JSONArray users = null;
    ArrayList<HashMap<String, String>> userList;

    ListView lvContacts = null;

    final static String TAG = "XAMPPTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_benefit_list);
        lvContacts = (ListView)findViewById(R.id.benefitListView);

        Log.d(TAG, "in XAMPPTestActivity");

        userList = new ArrayList<HashMap<String, String>>();

        getData("http://192.168.43.1/PHP_connection_user.php"); // 주소는 수정해야될수도 ......

    }

    protected void showList() {

        try {
            JSONObject jsonObject = new JSONObject(myJSON);
            users = jsonObject.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                String username = jo.getString(TAG_USERNAME);
                String userid = jo.getString(TAG_USERID);

                HashMap<String, String> user = new HashMap<String, String>();

                user.put(TAG_USERNAME, username);
                user.put(TAG_USERID, userid);

                Log.d(TAG, "<username>: " +username+ "  <userid>: " +userid);

                userList.add(user);
            }

            ListAdapter adapter = new SimpleAdapter(
              XAMPPTestActivity.this, userList, R.layout.adapter_benefit,
              new String[]{TAG_USERNAME, TAG_USERID},
              new int[]{R.id.benefitCategory, R.id.benefitBrandName}
            );

            lvContacts.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    Log.d(TAG, String.valueOf(url));

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    Log.d(TAG, String.valueOf(con));

                    Log.d(TAG, String.valueOf(con.getResponseCode()));

                    bufferedReader = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}
