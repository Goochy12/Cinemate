package au.com.liamgooch.moviemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import au.com.liamgooch.moviemate.adapters.MovieDetailsRecycleAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static au.com.liamgooch.moviemate.String_Values.ACTORS;
import static au.com.liamgooch.moviemate.String_Values.ALL_MOVIES;
import static au.com.liamgooch.moviemate.String_Values.KEY_INFORMATION;
import static au.com.liamgooch.moviemate.String_Values.KEY_STORYLINES;
import static au.com.liamgooch.moviemate.String_Values.OTHER;
import static au.com.liamgooch.moviemate.String_Values.SYNOPSIS;
import static au.com.liamgooch.moviemate.String_Values.GENRE;
import static au.com.liamgooch.moviemate.String_Values.MOVIE_ID;
import static au.com.liamgooch.moviemate.String_Values.MOVIE_LOCATION;
import static au.com.liamgooch.moviemate.String_Values.POSTER_LINK;
import static au.com.liamgooch.moviemate.String_Values.RUNTIME;
import static au.com.liamgooch.moviemate.String_Values.TITLE;
import static au.com.liamgooch.moviemate.String_Values.TAG;

public class MovieDetails extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private LinearLayoutManager recyclerViewLayoutManager;
    private MovieDetailsRecycleAdapter MovieDetailsRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        ArrayList<String> importantInfo = new ArrayList<>();
        String location = intent.getStringExtra(MOVIE_LOCATION);
        String movie_id = intent.getStringExtra(MOVIE_ID);

        importantInfo.add(location);
        importantInfo.add(movie_id);
        importantInfo.add(intent.getStringExtra(TITLE));
        importantInfo.add(intent.getStringExtra(SYNOPSIS));
        importantInfo.add(intent.getStringExtra(GENRE));
        importantInfo.add(intent.getStringExtra(RUNTIME));
        importantInfo.add(intent.getStringExtra(POSTER_LINK));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(location).child(movie_id);
//        databaseReference.addListenerForSingleValueEvent(movie_details_VEL);

        //recycler view
        recyclerView = (RecyclerView) findViewById(R.id.movieDetailsRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        MovieItem movieItem = new MovieItem(importantInfo,null,null,null,null);

        // specify an adapter (see also next example)
        MovieDetailsRecycleAdapter = new MovieDetailsRecycleAdapter(this,movieItem);
        recyclerView.setAdapter(MovieDetailsRecycleAdapter);

    }

    public void setViews(){

    }

    ValueEventListener movie_details_VEL = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot eachFact : dataSnapshot.getChildren()){
                String key = eachFact.getKey();
                if (key.equals(KEY_STORYLINES)){
                    //bullet points
                }else if (key.equals(KEY_INFORMATION)){

                }else if (key.equals(ACTORS)){

                }else if (key.equals(OTHER)){

                }
                if (eachFact.getChildren().iterator().hasNext()){
                }
            }
            setViews();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
