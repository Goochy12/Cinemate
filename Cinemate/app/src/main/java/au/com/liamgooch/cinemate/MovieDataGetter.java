package au.com.liamgooch.cinemate;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

import static au.com.liamgooch.cinemate.String_Values.IMPORTANT_INFORMATION;
import static au.com.liamgooch.cinemate.String_Values.RATING;
import static au.com.liamgooch.cinemate.String_Values.SYNOPSIS;
import static au.com.liamgooch.cinemate.String_Values.GENRE;
import static au.com.liamgooch.cinemate.String_Values.NEW_RELEASES;
import static au.com.liamgooch.cinemate.String_Values.POSTER_LINK;
import static au.com.liamgooch.cinemate.String_Values.RUNTIME;
import static au.com.liamgooch.cinemate.String_Values.TAG;
import static au.com.liamgooch.cinemate.String_Values.TITLE;

public class MovieDataGetter {
    private MainActivity mainActivity;
    private ArrayList<ArrayList<String>> new_releases = new ArrayList<>();

    public MovieDataGetter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getReleaseCardData(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().child(NEW_RELEASES);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                new_releases.clear();

                for (DataSnapshot eachRelease : dataSnapshot.getChildren()){
                    ArrayList<String> movie = new ArrayList<>();
                    try {
                        String location = NEW_RELEASES;
                        String movie_id = eachRelease.getKey();
                        String title = eachRelease.child(IMPORTANT_INFORMATION).child(TITLE).getValue(String.class);
                        String synopsis = eachRelease.child(IMPORTANT_INFORMATION).child(SYNOPSIS).getValue(String.class);
                        String runtime = eachRelease.child(IMPORTANT_INFORMATION).child(RUNTIME).getValue(String.class);
                        String genre = eachRelease.child(IMPORTANT_INFORMATION).child(GENRE).getValue(String.class);
                        String rating = eachRelease.child(IMPORTANT_INFORMATION).child(RATING).getValue(String.class);
                        String poster = eachRelease.child(IMPORTANT_INFORMATION).child(POSTER_LINK).getValue(String.class);

                        movie.add(location);
                        movie.add(movie_id);
                        movie.add(title);
                        movie.add(synopsis);
                        movie.add(runtime);
                        movie.add(genre);
                        movie.add(rating);
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

    public void getKeyInfo(String title){

    }
}
