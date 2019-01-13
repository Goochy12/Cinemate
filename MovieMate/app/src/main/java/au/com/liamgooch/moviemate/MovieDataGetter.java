package au.com.liamgooch.moviemate;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

import static au.com.liamgooch.moviemate.String_Values.DESCRIPTION;
import static au.com.liamgooch.moviemate.String_Values.GENRE;
import static au.com.liamgooch.moviemate.String_Values.NEW_RELEASES;
import static au.com.liamgooch.moviemate.String_Values.POSTER_LINK;
import static au.com.liamgooch.moviemate.String_Values.RUNTIME;
import static au.com.liamgooch.moviemate.String_Values.TAG;
import static au.com.liamgooch.moviemate.String_Values.TITLE;

public class MovieDataGetter {
    private MainActivity mainActivity;
    private ArrayList<ArrayList<String>> new_releases = new ArrayList<>();

    public MovieDataGetter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getReleaseCardData(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().child(NEW_RELEASES);
        Log.i(TAG, "getReleaseCardData: ");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                new_releases.clear();

                for (DataSnapshot eachRelease : dataSnapshot.getChildren()){
                    ArrayList<String> movie = new ArrayList<>();
                    try {
                        String title = eachRelease.child(TITLE).getValue(String.class);
                        String description = eachRelease.child(DESCRIPTION).getValue(String.class);
                        String runtime = eachRelease.child(RUNTIME).getValue(String.class);
                        String genre = eachRelease.child(GENRE).getValue(String.class);
                        String poster = eachRelease.child(POSTER_LINK).getValue(String.class);

                        movie.add(title);
                        movie.add(description);
                        movie.add(runtime);
                        movie.add(genre);
                        movie.add(poster);

                        //get other information
//                        for (DataSnapshot other_info : eachRelease.getChildren()){
//
//                        }
                        new_releases.add(movie);
                    }catch (NullPointerException ignore){
                        Log.i(TAG, "Nullpointer getting new release cards");
                    }
                }

                mainActivity.updateNewReleaseCards(new_releases);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
