package edu.purdue.vieck.animationsondisplay;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GalleryActivity extends AppCompatActivity {
    private ImageView aperatureView, clockView;
    private Button stop_button;
    private Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Animation Gallery");
        setSupportActionBar(toolbar);

        aperatureView = (ImageView) findViewById(R.id.aperature);
        clockView = (ImageView) findViewById(R.id.clock);
        stop_button = (Button) findViewById(R.id.stop_animation);
        aperatureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable vector = aperatureView.getDrawable();
                if (vector instanceof Animatable) {
                    ((Animatable) vector).start();
                }
            }
        });
/*        clockView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable clock = clockView.getDrawable();
                if (clock instanceof Animatable) {
                    ((Animatable) clock).start();
                }
            }
        });*/
        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable vector;
                if (aperatureView != null) {
                    vector = aperatureView.getDrawable();
                    ((Animatable) vector).stop();
                } else if (clockView != null) {
                    vector = clockView.getDrawable();
                    ((Animatable) vector).stop();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
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
}
