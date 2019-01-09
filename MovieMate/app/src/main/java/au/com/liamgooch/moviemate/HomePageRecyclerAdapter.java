package au.com.liamgooch.moviemate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomePageRecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<ArrayList<String>> movieList;


    public HomePageRecyclerAdapter(ArrayList<ArrayList<String>> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_movie_card,parent,false);

        HomeViewHolder hVH = new HomeViewHolder(v);

//        homeProgressBar.setVisibility(View.GONE);

        return hVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setMovieList(ArrayList<ArrayList<String>> movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
