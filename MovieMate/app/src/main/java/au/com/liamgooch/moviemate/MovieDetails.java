package au.com.liamgooch.moviemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static au.com.liamgooch.moviemate.String_Values.ALL_MOVIES;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Intent intent = getIntent();
        String location = intent.getStringExtra(MOVIE_LOCATION);
        String movie_id = intent.getStringExtra(MOVIE_ID);
        String title = intent.getStringExtra(TITLE);
        String synopsis = intent.getStringExtra(SYNOPSIS);
        String genre = intent.getStringExtra(GENRE);
        String runtime = intent.getStringExtra(RUNTIME);
        String poster = intent.getStringExtra(POSTER_LINK);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(location).child(movie_id);
        databaseReference.addListenerForSingleValueEvent(movie_details_VEL);

    }

    public void setViews(){

    }

    ValueEventListener movie_details_VEL = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot eachFact : dataSnapshot.getChildren()){
                String key = eachFact.getKey();
                if (!key.equals("title")){
                }
                if (key.equals("actors")){
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
