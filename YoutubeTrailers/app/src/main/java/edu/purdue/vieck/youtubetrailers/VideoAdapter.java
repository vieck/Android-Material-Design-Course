package edu.purdue.vieck.youtubetrailers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vieck on 6/11/15.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{
    public final String developer_key = "AIzaSyCtIek1PU6OB2pXfvHESoFH3ppOfOJ5JEY";
    private List<Video> videoList = new ArrayList<>();


    public VideoAdapter(List<Video> videoList) {
        this.videoList = videoList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        YouTubePlayerView playerView;
        public ViewHolder(View v) {
            super(v);
            playerView = (YouTubePlayerView) v.findViewById(R.id.youtube_video);
        }
    }

    public static class Video {
        String title, id;
        public Video(String title, String id) {
            this.title = title;
            this.id = id;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_feed_item, parent, false);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Video video = videoList.get(position);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
