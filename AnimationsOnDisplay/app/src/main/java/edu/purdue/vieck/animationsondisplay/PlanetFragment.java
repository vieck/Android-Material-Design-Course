package edu.purdue.vieck.animationsondisplay;


import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlanetFragment extends Fragment {

    ImageView planet_view;
    Boolean isRunning;

    public PlanetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        isRunning = false;
        View v = inflater.inflate(R.layout.planet_animation, container, false);
        // Inflate the layout for this fragment
        planet_view = (ImageView) v.findViewById(R.id.planet);
        planet_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable vector = planet_view.getDrawable();
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
