package edu.purdue.vieck.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;


public class WeatherActivity extends Activity {

    HelperMethods httpRequest;
    private Handler updateHandler;
    private int interval = 10000;
    Context context;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        context = this;
        updateHandler = new Handler();
        runUIThread();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    Runnable UIThread = new Runnable() {
        @Override
        public void run() {
            new JSONRequestTask().execute();
            updateHandler.postDelayed(UIThread, interval);
        }
    };

    void runUIThread() {
        UIThread.run();
    }

    class JSONRequestTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                httpRequest = new HelperMethods(context, "http://api.openweathermap.org/data/2.5/weather?q=West%20Lafayette&units=imperial");
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
