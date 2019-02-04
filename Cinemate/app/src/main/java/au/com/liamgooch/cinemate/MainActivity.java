package au.com.liamgooch.cinemate;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import au.com.liamgooch.cinemate.fragments.NewReleasesFragment;
import au.com.liamgooch.cinemate.fragments.adapters.MainViewPagerAdapter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout navDrawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBar actionbar;
    private ActionBarDrawerToggle toggle;

    private AdView mAdView;

    //fragment managers
    private FragmentPagerAdapter adapterViewPager;
    private ViewPager vpPager;
    private FragmentManager fragMan;
    //fragments
    private NewReleasesFragment newReleasesFragment;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        setTheme(R.style.SplashStyle);

        super.onCreate(savedInstanceState);
        setTheme(R.style.CinemateMainTheme);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, String.valueOf(R.string.add_mob_app_id));

//        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        navDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, navDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //drawer.setDrawerListener(toggle);
        navDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        createFragments();
}

    public void createFragments(){

        newReleasesFragment = new NewReleasesFragment();

        vpPager = (ViewPager) findViewById(R.id.main_viewpager);

        adapterViewPager = new MainViewPagerAdapter(getSupportFragmentManager(), newReleasesFragment);
        vpPager.setAdapter(adapterViewPager);
        vpPager.setOffscreenPageLimit(3);
        vpPager.addOnPageChangeListener(mPageChangeListener);

    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            changePage(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void changePage(int position){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_account) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_about:
                break;
            case R.id.nav_settings:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
