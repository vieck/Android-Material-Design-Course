package edu.purdue.vieck.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by vieck on 6/5/15.
 */
public class HelperMethods {
    View rootView;
    Context context;
    String url;
    String country, description, main, name, icon;
    int id, sunrise, sunset;
    double speed, degree, humidity, latitude, longitude, temperature, pressure, min_temp, max_temp;

    /* Views **/
    TextView txtCity, txtTemp, txtDescription, txtMinTemp, txtMaxTemp, txtWindSpeed,
            txtWindDegree, txtHumidity, txtPressure, txtVisibility, txtSunrise, txtSunset;

    public HelperMethods(Context context, String url) {
        this.context = context;
        rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        this.url = url;
        txtCity = (TextView) rootView.findViewById(R.id.cityText);
        txtTemp = (TextView) rootView.findViewById(R.id.descrWeather);
        txtMinTemp = (TextView) rootView.findViewById(R.id.tempMin);
        txtMaxTemp = (TextView) rootView.findViewById(R.id.tempMax);
        txtDescription = (TextView) rootView.findViewById(R.id.descrWeather);
        txtWindSpeed = (TextView) rootView.findViewById(R.id.windSpeed);
        txtWindDegree = (TextView) rootView.findViewById(R.id.windDeg);
        txtHumidity = (TextView) rootView.findViewById(R.id.hum);
        txtPressure = (TextView) rootView.findViewById(R.id.pressure);
        txtVisibility = (TextView) rootView.findViewById(R.id.visibility);
        txtSunrise = (TextView) rootView.findViewById(R.id.sunrise);
        txtSunset = (TextView) rootView.findViewById(R.id.sunset);
    }

    public JSONObject makeRequest() {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(this.url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String stream;
                while ((stream = bufferedReader.readLine()) != null)
                    builder.append(stream);
                Log.d("JSON", "JSON: " + builder.toString());
                return new JSONObject(builder.toString());
            }
        } catch (IOException e) {
            Log.e("Error", "IO Exception");
        } catch (JSONException e) {
            Log.e("Error", "JSON Exception");
        }
        return null;
    }

    public void seperateJSON(JSONObject json) throws JSONException {
        JSONObject coordinates = json.getJSONObject("coord");
        longitude = coordinates.getDouble("lon");
        latitude = coordinates.getDouble("lat");

        JSONObject sys = json.getJSONObject("sys");
        country = sys.getString("country");
        sunrise = sys.getInt("sunrise");
        sunset = sys.getInt("sunset");

        JSONArray weatherArray = json.getJSONArray("weather");
        if (weatherArray.length() > 0) {
            JSONObject weather = weatherArray.getJSONObject(0);
            id = weather.getInt("id");
            main = weather.getString("main");
            description = weather.getString("description");
            icon = weather.getString("icon");
        }

        JSONObject main = json.getJSONObject("main");
        temperature = main.getDouble("temp");
        min_temp = main.getDouble("temp_min");
        max_temp = main.getDouble("temp_max");
        pressure = main.getDouble("pressure");
        humidity = main.getDouble("humidity");

        JSONObject wind = json.getJSONObject("wind");
        speed = wind.getDouble("speed");
        degree = wind.getDouble("deg");

        name = json.getString("name");
    }

    public void updateScreen() {
        txtCity.setText(name);
        txtTemp.setText(temperature + "");
        txtDescription.setText(description);
        txtMinTemp.setText(min_temp+"℉");
        txtMaxTemp.setText(max_temp+"℉");
        txtWindSpeed.setText(speed+"");
        txtWindDegree.setText(degree+"");
        txtHumidity.setText(humidity+"%");
        txtPressure.setText(pressure+"° HPA");
        txtVisibility.setText("%");
        long time = sunrise * (long) 1000;
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss a yyyy");
        format.setTimeZone(TimeZone.getTimeZone("EST"));
        txtSunrise.setText("" + format.format(date));

        time = sunset * (long) 1000;
        date = new Date(time);
        format = new SimpleDateFormat("EEE MMM dd HH:mm:ss a yyyy");
        format.setTimeZone(TimeZone.getTimeZone("EST"));
        txtSunset.setText("" + format.format(date));
    }
}
