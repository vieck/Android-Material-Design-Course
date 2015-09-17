package edu.purdue.vieck.htmlcolorwheel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.slider.AlphaSlider;

import java.util.UUID;

import static android.graphics.Color.argb;

/**
 * Created by vieck on 6/25/15.
 */
public class SliderFragment extends Fragment {
    EditText nameValue;
    Color color;
    TextView colorValue;
    SeekThread thread;
    ColorPickerView colorPickerView;
    AlphaSlider alphaSlider;
    String hex = "";
    Button saveButton;
    int converted = 0;
    int[] rgba = new int[4];
    Context mContext;
    DatabaseHandler databaseHandler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.slider_fragment, container, false);
        colorPickerView = (ColorPickerView) view.findViewById(R.id.colorview);
        alphaSlider = (AlphaSlider) view.findViewById(R.id.alphaslider);
        saveButton = (Button) view.findViewById(R.id.save_button);
        mContext = getActivity();
        databaseHandler = new DatabaseHandler(getActivity().getApplicationContext());

        colorPickerView.addOnColorSelectedListener(new OnColorSelectedListener() {
            @Override
            public void onColorSelected(int i) {
                Log.d("Debug",String.format("#%06X", (0xFFFFFF & i)));
            }
        });

      saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(mContext);
                View alertView = li.inflate(R.layout.alert_text, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setView(alertView);
                nameValue = (EditText) alertView.findViewById(R.id.edittext_name);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        CustomColor customColor = new CustomColor(nameValue.getText().toString(),String.format("#%06X", (0xFFFFFF & colorPickerView.getSelectedColor())) , UUID.randomUUID().getMostSignificantBits());
                                        databaseHandler.addColor(customColor);
                                        Snackbar.make(getView(), "Color Was Saved", Snackbar.LENGTH_SHORT).show();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


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
        }

        @Override
        protected void onPostExecute(String s) {
        }
    }
}
