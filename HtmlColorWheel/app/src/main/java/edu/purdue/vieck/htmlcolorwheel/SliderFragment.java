package edu.purdue.vieck.htmlcolorwheel;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.slider.AlphaSlider;

import java.util.UUID;

import static android.graphics.Color.argb;

/**
 * Created by vieck on 6/25/15.
 */
public class SliderFragment extends Fragment {
    EditText hexValue, nameValue;
    Color color;
    TextView colorValue;
    SeekThread thread;
    ColorPickerView colorPickerView;
    AlphaSlider alphaSlider;
    //SeekBar rSeekBar, bSeekBar, gSeekBar, aSeekBar;
    String hex = "";
    Button saveButton;
    int converted = 0;
    int[] rgba = new int[4];
    Context mContext;
    DatabaseHandler databaseHandler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slider_fragment, container, false);
        /**hexValue = (EditText) view.findViewById(R.id.edittext_hex);
        colorValue = (TextView) view.findViewById(R.id.textview_color);
        rSeekBar = (SeekBar) view.findViewById(R.id.seekBar_red);
        bSeekBar = (SeekBar) view.findViewById(R.id.seekBar_blue);
        gSeekBar = (SeekBar) view.findViewById(R.id.seekBar_green);
        aSeekBar = (SeekBar) view.findViewById(R.id.seekBar_alpha);**/
        colorPickerView = (ColorPickerView) view.findViewById(R.id.colorview);
        alphaSlider = (AlphaSlider) view.findViewById(R.id.alphaslider);
        nameValue = (EditText) view.findViewById(R.id.edittext_name);
        saveButton = (Button) view.findViewById(R.id.save_button);

        databaseHandler = new DatabaseHandler(getActivity().getApplicationContext());


        /**rSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rgba[1] = progress;
                thread = new SeekThread();
                thread.execute();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rgba[3] = progress;
                thread = new SeekThread();
                thread.execute();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        gSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rgba[2] = progress;
                thread = new SeekThread();
                thread.execute();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        aSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rgba[0] = progress;
                thread = new SeekThread();
                thread.execute();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });**/

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomColor customColor = new CustomColor(nameValue.getText().toString(), hexValue.getText().toString(), UUID.randomUUID().getMostSignificantBits());
                databaseHandler.addColor(customColor);
                Snackbar.make(getView(), "Color Was Saved", Snackbar.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        color = new Color();
        thread = new SeekThread();
    }

    private class SeekThread extends AsyncTask<Void, Integer, String> {
        @Override
        protected String doInBackground(Void... params) {
            converted = argb(rgba[0], rgba[1], rgba[2], rgba[3]);
            hex = String.format("#%06X", (0xFFFFFF & converted));
            publishProgress(converted);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            colorValue.setBackgroundColor(values[0]);
            hexValue.setText(hex);
        }

        @Override
        protected void onPostExecute(String s) {
        }
    }
}
