package edu.purdue.vieck.youtubetrailers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by vieck on 6/13/15.
 */
public class YoutubeSearch {
    private final String developer_key = "AIzaSyCtIek1PU6OB2pXfvHESoFH3ppOfOJ5JEY";
    private String baseUrl = "https://www.googleapis.com/youtube/v3/search";
    private Context context;
    private String searchQuery, response;
    public YoutubeSearch(Context context) {
        this.context = context;
    }

    public String httpRequest() {
        try {
            String query = URLEncoder.encode("Android Material Design Apps", "UTF-8");
            URL url = new URL(baseUrl + "?part=snippet&q=" + query + "videoCaption=closedCaption&type=video&key="+developer_key);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String read = null;
            while ((read = bufferedReader.readLine()) != null) {
                builder.append(read);
            }
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            Log.e("Error",e.getMessage());
        } catch (MalformedURLException e) {
            Log.e("Error",e.getMessage());
        } catch (IOException e) {
            Log.e("Error",e.getMessage());
        }
        return null;
    }

    public void showMessage(String message) {
        Toast.makeText(context,message,Toast.LENGTH_LONG);
    }

}
