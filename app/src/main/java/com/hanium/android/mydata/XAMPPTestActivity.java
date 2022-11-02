package com.hanium.android.mydata;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

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
    private static final String TAG_TESTADDR = "testaddr";

    JSONArray tests = null;
    ArrayList<HashMap<String, String>> testList;

    ListView listView = null;

    final static String TAG = "XAMPPTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_benefit_list);
        listView = (ListView)findViewById(R.id.benefitListView);

        Log.d(TAG, "in XAMPPTestActivity");

        testList = new ArrayList<HashMap<String, String>>();

        getData("http://192.168.43.1/PHP_connection.php"); // 주소는 수정해야될수도 ......

    }

    protected void showList() {

        try {
            JSONObject jsonObject = new JSONObject(myJSON);
            tests = jsonObject.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < tests.length(); i++) {
                JSONObject jo = tests.getJSONObject(i);
                String testname = jo.getString(TAG_TESTNAME);
                String testaddr = jo.getString(TAG_TESTADDR);

                HashMap<String, String> test = new HashMap<String, String>();

                test.put(TAG_TESTNAME, testname);
                test.put(TAG_TESTADDR, testaddr);

                Log.d(TAG, "<username>: " +testname+ "  <useraddr>: " +testaddr);

                testList.add(test);
            }

            ListAdapter adapter = new SimpleAdapter(
              XAMPPTestActivity.this, testList, R.layout.adapter_benefit,
              new String[]{TAG_TESTNAME, TAG_TESTADDR},
              new int[]{R.id.benefitCategory, R.id.benefitBrandName}
            );

            listView.setAdapter(adapter);

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
