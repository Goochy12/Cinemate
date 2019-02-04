package au.com.liamgooch.cinemate.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import au.com.liamgooch.cinemate.MovieDataGetter;
import au.com.liamgooch.cinemate.R;
import au.com.liamgooch.cinemate.adapters.HomePageRecyclerAdapter;
import au.com.liamgooch.cinemate.data.FirebaseCallbacks;

public class NewReleases extends Fragment {

    private ProgressBar newReleaseProgressBar;

    private RecyclerView recyclerView;
    private LinearLayoutManager recyclerViewLayoutManager;
    private HomePageRecyclerAdapter homePageRecyclerAdapter;

    private ArrayList<ArrayList<String>> movieList = new ArrayList<>();
    private MovieDataGetter movieDataGetter;

    private ArrayList<ArrayList<String>> new_releases = new ArrayList<>();

    public NewReleases() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newReleaseProgressBar = (ProgressBar) getActivity().findViewById(R.id.mainProgressBar);
        newReleaseProgressBar.setVisibility(View.VISIBLE);
        newReleaseProgressBar.animate();

        //recycler view
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.new_releases_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        // specify an adapter (see also next example)
        homePageRecyclerAdapter = new HomePageRecyclerAdapter(movieList,getContext());
        recyclerView.setAdapter(homePageRecyclerAdapter);

        movieDataGetter = new MovieDataGetter(new FirebaseCallbacks() {
            @Override
            public ArrayList<ArrayList<String>> returnMovies(ArrayList<ArrayList<String>> movie_list) {
                new_releases = new ArrayList<>();
                new_releases = movie_list;
                updateNewReleaseCards(new_releases);
                return null;
            }
        });
        movieDataGetter.getReleaseCardData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_releases, container, false);
    }

    public void updateNewReleaseCards(ArrayList<ArrayList<String>> new_releases){
        newReleaseProgressBar.setVisibility(View.GONE);
        homePageRecyclerAdapter.setMovieList(new_releases);
    }

}
