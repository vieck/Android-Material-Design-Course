package edu.purdue.vieck.htmlcolorwheel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
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

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;

import java.util.UUID;

import static android.graphics.Color.argb;

/**
 * Author: Michael Vieck
 * Date: 9/20/2015

 * ColorPicker License
 * https://github.com/LarsWerkman/HoloColorPicker
 * Copyright 2012 Lars Werkman

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
public class SliderFragment extends Fragment {
    EditText nameValue;
    Color color;
    ColorPicker colorPicker;
    OpacityBar opacityBar;
    Button saveButton;
    Context mContext;
    DatabaseHandler databaseHandler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.slider_fragment, container, false);
        colorPicker = (ColorPicker) view.findViewById(R.id.color_view);
        opacityBar = (OpacityBar) view.findViewById(R.id.opacity_bar);
        saveButton = (Button) view.findViewById(R.id.save_button);
        mContext = getActivity();
        databaseHandler = new DatabaseHandler(getActivity().getApplicationContext());
        colorPicker.addOpacityBar(opacityBar);
        colorPicker.setOnColorChangedListener(new ColorPicker.OnColorChangedListener() {
            @Override
            public void onColorChanged(int color) {
                Log.d("Debug", String.format("#%06X", (0xFFFFFF & color)));
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
                                        CustomColor customColor = new CustomColor(nameValue.getText().toString(),String.format("#%06X", (0xFFFFFF & colorPicker.getColor())) , UUID.randomUUID().getMostSignificantBits());
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
    }

}
