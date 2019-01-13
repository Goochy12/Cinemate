package au.com.liamgooch.moviemate;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import static au.com.liamgooch.moviemate.String_Values.TAG;

public class HomePageRecyclerAdapter extends RecyclerView.Adapter<HomePageRecyclerAdapter.MovieCardViewHolder> {

    private ArrayList<ArrayList<String>> movieList;

    public HomePageRecyclerAdapter(ArrayList<ArrayList<String>> movieList) {
        this.movieList = movieList;
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
    public void onBindViewHolder(@NonNull MovieCardViewHolder holder, int position) {
        String title = movieList.get(position).get(0);
        String description = movieList.get(position).get(1);
        String runtime = movieList.get(position).get(2);
        String genre = movieList.get(position).get(3);
        String poster = movieList.get(position).get(4);

        holder.title.setText(title);
        holder.description.setText(description);
        holder.genre.setText(genre);
        holder.runime.setText(runtime);

        if(poster != null && !poster.isEmpty()){
            Picasso.get().load(poster).into(holder.poster);
        }
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
        private TextView description;
        private TextView runime;
        private TextView genre;
        private ImageView poster;

        public MovieCardViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            title = (TextView) itemView.findViewById(R.id.card_title_view);
            description = (TextView) itemView.findViewById(R.id.card_desc_view);
            runime = (TextView) itemView.findViewById(R.id.card_runtime_view);
            genre = (TextView) itemView.findViewById(R.id.card_genre_view);
            poster = (ImageView) itemView.findViewById(R.id.card_poster_view);
        }
    }
}
