package edu.purdue.vieck.htmlcolorwheel;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.graphics.Color.argb;


public class ColorActivity extends Activity {

    EditText hexValue;
    Color color;
    TextView colorValue;
    SeekThread thread;
    SeekBar rSeekBar;
    SeekBar bSeekBar;
    SeekBar gSeekBar;
    SeekBar aSeekBar;
    String hex = "";
    int converted = 0;
    int[] rgba = new int[4];


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        hexValue = (EditText) findViewById(R.id.edittext_hex);
        colorValue = (TextView) findViewById(R.id.textview_color);
        rSeekBar = (SeekBar) findViewById(R.id.seekBar_red);
        bSeekBar = (SeekBar) findViewById(R.id.seekBar_blue);
        gSeekBar = (SeekBar) findViewById(R.id.seekBar_green);
        aSeekBar = (SeekBar) findViewById(R.id.seekBar_alpha);
        color = new Color();

        thread = new SeekThread();

        rSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color, menu);
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

    public class SeekThread extends AsyncTask<Void, Integer, String> {
        @Override
        protected String doInBackground(Void... params) {
            converted = argb(rgba[0],rgba[1],rgba[2],rgba[3]);
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
