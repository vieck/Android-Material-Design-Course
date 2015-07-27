package edu.purdue.vieck.topgamesrssfeed;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by vieck on 7/3/15.
 */
public class ScienceFragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    RecycleAdapter mRecycleAdapter;
    Context mContext;
    @Override
    public void onAttach(Activity activity) {
        mContext = activity.getApplicationContext();
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.android_rss_feed, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.android_swipe_refresh_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.android_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecycleAdapter = new RecycleAdapter(mContext, new ArrayList<RssDataParser.Item>());
        mRecyclerView.setAdapter(mRecycleAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRecycleAdapter.clear();
                new GetScienceFeed().execute();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    private class GetScienceFeed extends AsyncTask<Void, Void, ArrayList<RssDataParser.Item>> {
        @Override
        protected ArrayList<RssDataParser.Item> doInBackground(Void... params) {
            try {
                //http://www.reuters.com/rssFeed/scienceNews
                return loadXmlFromNetwork("http://www.reuters.com/rssFeed/scienceNews");
            } catch (IOException e) {
                Log.d("Error", "IOException: " + e.getMessage());
            } catch (XmlPullParserException e) {
                Log.d("Error", "XmlException " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<RssDataParser.Item> items) {
            if (items != null) {
                Log.d("ArrayList", "Items:" + items.toString());
                int itemsLength = items.size();
                Log.d("Size", itemsLength + "");
                for (int i = 0; i < itemsLength; i++) {
                    mRecycleAdapter.add(i, items.get(i));
                }
                mRecycleAdapter.notifyDataSetChanged();
            } else {
                Log.e("OnPostExecute", "ArrayList is null");
                Snackbar.make(getView(), "No Connection Was Made", Snackbar.LENGTH_LONG).show();
            }
        }

    }

    // Uploads XML from stackoverflow.com, parses it, and combines it with
    // HTML markup. Returns HTML string.
    private ArrayList loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        // Instantiate the parser
        RssDataParser rssDataParser = new RssDataParser();
        ArrayList<RssDataParser.Item> entries = null;

        try {
            stream = downloadUrl(urlString);
            entries = rssDataParser.parse(stream);
            return entries;
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }

        }
    }

    // Given a string representation of a URL, sets up a connection and gets
// an input stream.
    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        HttpURLConnection.setFollowRedirects(false);
        huc.setConnectTimeout(15 * 1000);
        huc.setRequestMethod("GET");
        huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
        huc.connect();
        InputStream inputStream = huc.getInputStream();
        return inputStream;
    }
}
