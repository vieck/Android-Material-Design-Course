package edu.purdue.vieck.youtubetrailers;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.youtube.player.YouTubePlayer;

import java.util.ArrayList;

public class VideoListActivity extends Activity {
    RecyclerView mRecyclerView;
    VideoAdapter mAdapter;
    YouTubePlayer mYoutubePlayer;
    RecyclerView.LayoutManager mLayoutManager;
    YoutubeSearch mSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ArrayList<VideoAdapter.Video> videos = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.staggered_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new VideoAdapter(videos);
        mRecyclerView.setAdapter(mAdapter);
        mYoutubePlayer = (YouTubePlayer) findViewById(R.id.youtube_video);
        mSearch = new YoutubeSearch(this);
        new Search().execute("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    private class Search extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return mSearch.httpRequest();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mSearch.showMessage(s);
        }
    }
}
