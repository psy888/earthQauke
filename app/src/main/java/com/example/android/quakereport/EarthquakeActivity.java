/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class EarthquakeActivity extends AppCompatActivity {

    // public static final String LOG_TAG = EarthquakeActivity.class.getName();
    String MAIN_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=40";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        new EarthquakeAsyncTask().execute(MAIN_URL);


    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            URL url = QueryUtils.createUrl(urls[0]);
            List earthquakes = null;
            try {
                HttpURLConnection usgsConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(usgsConnection.getInputStream());
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null) {
                    responseStrBuilder.append(inputStr);
                }
                JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
                usgsConnection.disconnect();
                streamReader.close();
                in.close();
                earthquakes = QueryUtils.extractEarthquakes(jsonObject);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return earthquakes;
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            if (earthquakes != null) {
                final ArrayList<Earthquake> earthQ = (ArrayList) earthquakes;


                // Find a reference to the {@link ListView} in the layout
                ListView earthquakeListView = (ListView) findViewById(R.id.list);
                // Create a new {@link ArrayAdapter} of earthquakes
                EarthquakeAdapter adapter = new EarthquakeAdapter(getApplicationContext(), earthQ);
                earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(earthQ.get(position).getUrl()));
                        startActivity(i);
                    }
                });
                // Set the adapter on the {@link ListView}
                // so the list can be populated in the user interface
                earthquakeListView.setAdapter(adapter);
            } else {
                return;
            }
        }
    }
}
