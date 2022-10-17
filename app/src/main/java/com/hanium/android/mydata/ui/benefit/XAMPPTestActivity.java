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
    private static final String TAG_TESTNAME = "testname";
    private static final String TAG_TESTAGE = "testage";

    JSONArray tests = null;
    ArrayList<HashMap<String, String>> testList;

    ListView lvContacts = null;

    final static String TAG = "XAMPPTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_benefit_list);
        lvContacts = (ListView)findViewById(R.id.benefitListView);

        Log.d(TAG, "in XAMPPTestActivity");

        testList = new ArrayList<HashMap<String, String>>();

        getData("http://172.30.1.14/PHP_connection.php"); // 주소는 수정해야될수도 ......

    }

    protected void showList() {

        try {
            JSONObject jsonObject = new JSONObject(myJSON);
            tests = jsonObject.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < tests.length(); i++) {
                JSONObject jo = tests.getJSONObject(i);
                String testname = jo.getString(TAG_TESTNAME);
                String testage = jo.getString(TAG_TESTAGE);

                HashMap<String, String> tests = new HashMap<String, String>();

                tests.put(TAG_TESTNAME, testname);
                tests.put(TAG_TESTAGE, testage);

                Log.d(TAG, "<testname>: " +testname+ "  <testage>: " +testage);

                testList.add(tests);
            }

            ListAdapter adapter = new SimpleAdapter(
              XAMPPTestActivity.this, testList, R.layout.adapter_benefit,
              new String[]{TAG_TESTNAME, TAG_TESTAGE},
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
