package edu.purdue.vieck.weatherapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;


public class WeatherActivity extends Activity {

    HelperMethods httpRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
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

    class JSONRequestTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
            httpRequest = new HelperMethods(getApplicationContext(), "http://api.openweathermap.org/data/2.5/weather?q=West%20Lafayette&units=imperial");
            httpRequest.seperateJSON(httpRequest.makeRequest());
            } catch (JSONException e){
                Log.e("Error","JSON Error");
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            httpRequest.updateScreen();
        }
    }

}
