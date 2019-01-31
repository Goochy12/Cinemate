package au.com.liamgooch.cinemate.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import au.com.liamgooch.cinemate.MovieDetails;
import au.com.liamgooch.cinemate.R;

import static au.com.liamgooch.cinemate.String_Values.MOVIE_LOCATION;
import static au.com.liamgooch.cinemate.String_Values.RATING;
import static au.com.liamgooch.cinemate.String_Values.RELEASE;
import static au.com.liamgooch.cinemate.String_Values.SYNOPSIS;
import static au.com.liamgooch.cinemate.String_Values.GENRE;
import static au.com.liamgooch.cinemate.String_Values.MOVIE_ID;
import static au.com.liamgooch.cinemate.String_Values.POSTER_LINK;
import static au.com.liamgooch.cinemate.String_Values.RUNTIME;
import static au.com.liamgooch.cinemate.String_Values.TITLE;

public class HomePageRecyclerAdapter extends RecyclerView.Adapter<HomePageRecyclerAdapter.MovieCardViewHolder> {

    private ArrayList<ArrayList<String>> movieList;
    private Context context;

    public HomePageRecyclerAdapter(ArrayList<ArrayList<String>> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_movie_card,parent,false);
        
        MovieCardViewHolder hVH = new MovieCardViewHolder(v);

//        homeProgressBar.setVisibility(View.GONE);

        return hVH;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCardViewHolder holder, final int position) {
        final String location = movieList.get(position).get(0);
        final String movie_id = movieList.get(position).get(1);
        final String title = movieList.get(position).get(2);
        final String synopsis = movieList.get(position).get(3);
        final String runtime = movieList.get(position).get(4);
        final String genre = movieList.get(position).get(5);
        final String rating = movieList.get(position).get(6);
        final String release = movieList.get(position).get(7);
        final String poster = movieList.get(position).get(8);

        holder.title.setText(title);
        holder.synopsis.setText(synopsis);
        holder.genre.setText(genre);
        holder.runime.setText(runtime);

        if(poster != null && !poster.isEmpty()){
            Picasso.get().load(poster).into(holder.poster);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent movieDetails = new Intent(context, MovieDetails.class);
                movieDetails.putExtra(MOVIE_LOCATION,location);
                movieDetails.putExtra(MOVIE_ID,movie_id);
                movieDetails.putExtra(TITLE,title);
                movieDetails.putExtra(SYNOPSIS,synopsis);
                movieDetails.putExtra(GENRE,genre);
                movieDetails.putExtra(RUNTIME,runtime);
                movieDetails.putExtra(RATING,rating);
                movieDetails.putExtra(RELEASE,release);
                movieDetails.putExtra(POSTER_LINK,poster);

                context.startActivity(movieDetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(ArrayList<ArrayList<String>> movieList){
        this.movieList.clear();
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    public class MovieCardViewHolder extends RecyclerView.ViewHolder{

        private View itemView;
        private TextView title;
        private TextView synopsis;
        private TextView runime;
        private TextView genre;
        private ImageView poster;

        public MovieCardViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            title = (TextView) itemView.findViewById(R.id.card_title_view);
            synopsis = (TextView) itemView.findViewById(R.id.card_desc_view);
            runime = (TextView) itemView.findViewById(R.id.card_runtime_view);
            genre = (TextView) itemView.findViewById(R.id.card_genre_view);
            poster = (ImageView) itemView.findViewById(R.id.card_poster_view);
        }
    }
}
