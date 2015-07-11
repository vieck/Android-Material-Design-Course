package edu.purdue.vieck.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;


public class WeatherActivity extends Activity {

    SwipeRefreshLayout mSwipeRefreshLayout;

    HelperMethods httpRequest;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new JSONRequestTask().execute();
            }
        });
        context = this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Enable these if you want an automatic refresh

    /*Runnable UIThread = new Runnable() {
        @Override
        public void run() {
            new JSONRequestTask().execute();
            updateHandler.postDelayed(UIThread, interval);
        }
    };

    void runUIThread() {
        UIThread.run();
    }**/

    class JSONRequestTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String city = settings.getString("city","New York");
                httpRequest = new HelperMethods(context, "http://api.openweathermap.org/data/2.5/weather?q=" + "West%20Lafayette" + "&units=imperial");
                return httpRequest.seperateJSON(httpRequest.makeRequest());
            } catch (JSONException e) {
                Log.e("Error", "JSON Error");
            }
            return false;
        }


        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool)
                httpRequest.updateScreen();
        }
    }

}
