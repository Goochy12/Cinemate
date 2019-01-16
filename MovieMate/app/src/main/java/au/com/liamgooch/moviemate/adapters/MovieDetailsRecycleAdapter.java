package au.com.liamgooch.moviemate.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import au.com.liamgooch.moviemate.MovieItem;
import au.com.liamgooch.moviemate.R;

import static au.com.liamgooch.moviemate.String_Values.ACTORS_CARD;
import static au.com.liamgooch.moviemate.String_Values.NOTITLE_DETAILS_CARD;
import static au.com.liamgooch.moviemate.String_Values.TITLE_BULLET_CARD;
import static au.com.liamgooch.moviemate.String_Values.TITLE_DETAILS_CARD;
import static au.com.liamgooch.moviemate.String_Values.TAG;

public class MovieDetailsRecycleAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private MovieItem movieItem;
    private ArrayList<Integer> cardList;

    public MovieDetailsRecycleAdapter(Context context, MovieItem movieItem){
        this.context = context;
        this.movieItem = movieItem;
    }

    @Override
    public int getItemViewType(int position) {

        switch (cardList.get(position)){
            case TITLE_DETAILS_CARD:
                return TITLE_DETAILS_CARD;
            case ACTORS_CARD:
                return ACTORS_CARD;
            case TITLE_BULLET_CARD:
                return TITLE_BULLET_CARD;
            case NOTITLE_DETAILS_CARD:
                return NOTITLE_DETAILS_CARD;
            default:
                Log.i(TAG, "getItemViewType: Error");
                return 0;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case TITLE_DETAILS_CARD:
                View importantInfoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_details_card,parent,false);
                TitleDetailsViewHolder tDVH = new TitleDetailsViewHolder(importantInfoView);
                return tDVH;
            case ACTORS_CARD:
                View actorsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.actors_card,parent,false);
                ActorsViewHolder aVH = new ActorsViewHolder(actorsView);
                return aVH;
            case TITLE_BULLET_CARD:
                View bulletView = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_bullet_card,parent,false);
                TitleBulletViewHolder bVH = new TitleBulletViewHolder(bulletView);
                return bVH;
            case NOTITLE_DETAILS_CARD:
                View detailsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notitle_detail_card,parent,false);
                NoTitleDetailsViewHolder dVH = new NoTitleDetailsViewHolder(detailsView);
                return dVH;
            default:
                Log.i(TAG, "onCreateViewHolder: Error");
                View importantInfoViewError = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_details_card,parent,false);
                TitleDetailsViewHolder tDVHE = new TitleDetailsViewHolder(importantInfoViewError);
                return tDVHE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.title.setText(test.get(0));
//        for (int i = 1; i < test.size(); i++){
//            TextView tx = new TextView(context);
//            tx.setText(test.get(i));
//            tx.setTextSize(12);
//            holder.linearLayout.addView(tx);
//        }

        switch (cardList.get(position)){
            case TITLE_DETAILS_CARD:
                return;
            case ACTORS_CARD:
                return;
            case TITLE_BULLET_CARD:
                return;
            case NOTITLE_DETAILS_CARD:
                return;
            default:
                Log.i(TAG, "getItemViewType: Error");
        }

    }


    @Override
    public int getItemCount() {
        return movieItem.getSize();
    }

    public void setItem(MovieItem item, ArrayList<Integer> cardList){
        this.movieItem = item;
        this.cardList = cardList;
        notifyDataSetChanged();
    }

    //title
    public class TitleDetailsViewHolder extends ViewHolder {

        private TextView title;
        private LinearLayout linearLayout;

        public TitleDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_bullet_title_textview);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.title_bullet_linearlayout);
        }
    }

    private class NoTitleDetailsViewHolder extends ViewHolder{

        public NoTitleDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class TitleBulletViewHolder extends ViewHolder{

        public TitleBulletViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class ActorsViewHolder extends ViewHolder{

        public ActorsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
