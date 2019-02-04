package au.com.liamgooch.cinemate.fragments.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import au.com.liamgooch.cinemate.fragments.NewReleasesFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    public static final int NUM_PAGES = 1;

    private NewReleasesFragment newReleasesFragment;

    public MainViewPagerAdapter(FragmentManager fm, NewReleasesFragment newReleasesFragment) {
        super(fm);
        this.newReleasesFragment = newReleasesFragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return newReleasesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
