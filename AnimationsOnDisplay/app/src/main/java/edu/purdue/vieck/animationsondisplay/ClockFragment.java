package edu.purdue.vieck.animationsondisplay;

import android.app.Fragment;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by vieck on 6/16/15.
 */
public class ClockFragment extends Fragment {

    private ImageView clockview;
    private boolean isRunning;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isRunning = false;
        View v = inflater.inflate(R.layout.clock_animation, container, false);
        clockview = (ImageView) v.findViewById(R.id.clock);
        clockview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable vector = clockview.getDrawable();
                if (vector instanceof Animatable) {
                    if (isRunning) {
                        ((Animatable) vector).stop();
                        isRunning = false;
                    } else {
                        ((Animatable) vector).start();
                        isRunning = true;
                    }
                }
            }
        });
        return v;
    }
}
