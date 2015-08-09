package edu.purdue.vieck.animationsondisplay;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

public class GalleryActivity extends AppCompatActivity {
    private ImageView aperatureView, clockView;
    private Button stop_button;
    private Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Animation Gallery");
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_layout);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.aperature_nav_item:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AnimationFragment()).commit();
                        break;
                    case R.id.clock_nav_item:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClockFragment()).commit();
                        break;
                    case R.id.planet_nav_item:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new PlanetFragment()).commit();
                    case R.id.radar_nav_item:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RadarFragment()).commit();
                }
                return true;
            }
        });
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AnimationFragment(), "Animation_Fragment").commit();
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


        return super.onOptionsItemSelected(item);
    }
}
