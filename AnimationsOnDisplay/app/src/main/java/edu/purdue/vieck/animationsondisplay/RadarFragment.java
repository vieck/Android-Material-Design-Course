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
 * A simple {@link Fragment} subclass.
 */
public class RadarFragment extends Fragment {

    ImageView radarView;
    Boolean isRunning;
    public RadarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        isRunning = false;
        View v = inflater.inflate(R.layout.radar_animation, container, false);
        radarView = (ImageView) v.findViewById(R.id.radar);
        radarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable vector = radarView.getDrawable();
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
