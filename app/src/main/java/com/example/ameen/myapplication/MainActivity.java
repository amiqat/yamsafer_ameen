package com.example.ameen.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    ArrayList<Hotel> Hotels = new ArrayList<Hotel>();
    hotelsAdapter adapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        if (isNetworkAvailable()) {
            GetData getData = new GetData();
            getData.execute();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Internet is not available")
                    .setTitle("Info");
            AlertDialog dialog = builder.create();

            dialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }


            });
            dialog.show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private class GetData extends AsyncTask<Void, Void, Void> {
        String result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
            progress.setVisibility(View.VISIBLE);

            progress.setProgress(50);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            try {
                URL url;

                HttpsURLConnection urlConnection = null;
                BufferedReader reader = null;
                url = new URL("https://api-staging.yamsafer.me/en/api/v3/mobile/cities/625?limit=0&checkin_date=05/13/2015&checkout_date=05/14/2015&api_key=5c75aabcc20a33be13a4466f155d9c7c");

                urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();

                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.

                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.

                }
                result = buffer.toString();


            } catch (Exception e) {
                Log.e("Error:", " ha1");
                e.printStackTrace();

            }
            return null;

        }


        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);

            Hotel newHotle;
            try {
                JSONObject jsonObject1 = new JSONObject(result);
                JSONObject jsonObject = jsonObject1.getJSONObject("city");
                JSONArray jsonArray = (JSONArray) jsonObject.get("data");
                int x = Integer.parseInt(jsonObject.get("total").toString());
                Gson gson = new Gson();


                for (int i = 0; i < x; i++) {

                    newHotle = gson.fromJson(jsonArray.getJSONObject(i).toString(), Hotel.class);
                    Hotels.add(newHotle);
                }


                Log.i("result:", Hotels.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter = new hotelsAdapter(getApplicationContext(), Hotels);
            listView.setAdapter(adapter);
            progress.setVisibility(View.INVISIBLE);
        }

    }


}


