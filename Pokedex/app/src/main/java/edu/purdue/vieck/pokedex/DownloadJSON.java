package edu.purdue.vieck.pokedex;

import android.util.Log;

import org.json.JSONArray;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vieck on 7/8/15.
 */
public class DownloadJSON {

    String mUrl;

    public DownloadJSON(String mUrl) {
        this.mUrl = mUrl;
    }

    public JSONArray parseJson() {
        String jsonString = getJson();
        JSONArray jsonArray= (JSONArray)  JSONValue.parse(jsonString);
        return jsonArray;
    }

    private String getJson() {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(mUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String stream;
                while ((stream = bufferedReader.readLine()) != null)
                    builder.append(stream);
                Log.d("JSON", "JSON: " + builder.toString());
                return builder.toString();
            }
        } catch (IOException e) {
            Log.e("Error", "IO Exception");
        }
        return null;
    }

}
