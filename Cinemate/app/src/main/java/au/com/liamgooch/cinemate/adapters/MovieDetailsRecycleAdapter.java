package au.com.liamgooch.cinemate.adapters;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import au.com.liamgooch.cinemate.MovieItem;
import au.com.liamgooch.cinemate.R;
import au.com.liamgooch.cinemate.fragments.adapters.NewReleasesRecyclerAdapter;

import static au.com.liamgooch.cinemate.String_Values.ACTORS_CARD;
import static au.com.liamgooch.cinemate.String_Values.INFO_CARD;
import static au.com.liamgooch.cinemate.String_Values.NOTITLE_DETAILS_CARD;
import static au.com.liamgooch.cinemate.String_Values.OTHER_CARD;
import static au.com.liamgooch.cinemate.String_Values.TITLE;
import static au.com.liamgooch.cinemate.String_Values.TITLE_BULLET_CARD;
import static au.com.liamgooch.cinemate.String_Values.TITLE_DETAILS_CARD;
import static au.com.liamgooch.cinemate.String_Values.TAG;

public class MovieDetailsRecycleAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private MovieItem movieItem;
    private ArrayList<Integer> cardList;

//    private int storylinePos = 0;
//    private int keyInfoPos = 0;
//    private int otherInfoPos = 0;

    private ProgressBar progressBar;

    public MovieDetailsRecycleAdapter(Context context, MovieItem movieItem, ProgressBar progressBar){
        this.context = context;
        this.movieItem = movieItem;
        this.cardList = movieItem.getCardList();

        this.progressBar = progressBar;
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
            case INFO_CARD:
                return INFO_CARD;
            case OTHER_CARD:
                return OTHER_CARD;
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
            case INFO_CARD:
                View infoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_other_card,parent,false);
                OtherCardViewHolder iVH = new OtherCardViewHolder(infoView);
                return iVH;
            case OTHER_CARD:
                View otherView = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_other_card,parent,false);
                OtherCardViewHolder oVH = new OtherCardViewHolder(otherView);
                return oVH;
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

        progressBar.setVisibility(View.GONE);


        switch (holder.getItemViewType()){
            case TITLE_DETAILS_CARD:
                TitleDetailsViewHolder viewHolder;
                viewHolder = (TitleDetailsViewHolder) holder;
                viewHolder.title.setText(movieItem.getTitle());
                viewHolder.release.setText(movieItem.getRelease());
                viewHolder.rating.setText(movieItem.getRating());
                viewHolder.runtime.setText(movieItem.getRuntime());
                viewHolder.genre.setText(movieItem.getGenre());
                return;
            case ACTORS_CARD:
                ActorsViewHolder actorsViewHolder;
                actorsViewHolder = (ActorsViewHolder) holder;

                actorsViewHolder.actorRecyclerView.setLayoutManager(actorsViewHolder.linearLayoutManager);

                actorsViewHolder.actorRecyclerView.setAdapter(actorsViewHolder.actorsRecycleAdapter);

//                actorsViewHolder.actorsRecycleAdapter.setActors(movieItem.getActorList());

                return;
            case TITLE_BULLET_CARD:
                TitleBulletViewHolder titleBulletViewHolder;
                titleBulletViewHolder = (TitleBulletViewHolder) holder;

                titleBulletViewHolder.title.setText(movieItem.getDetailsList().get(position).get(0));
                for (int i = 1; i < movieItem.getDetailsList().get(position).size(); i++){
                    TextView t = new TextView(context);
                    SpannableString spannableString = SpannableString.valueOf(movieItem.getDetailsList().get(position).get(i));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        spannableString.setSpan(new BulletSpan(10,context.getResources().getColor(R.color.black,null),10),0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }else {
                        spannableString = SpannableString.valueOf("\u25BA " + movieItem.getDetailsList().get(position).get(i));
                    }
                    t.setText(spannableString);
//                    t.setText(movieItem.getKey_storylines().get(storylinePos).get(i));
                    titleBulletViewHolder.linearLayout.addView(t);
                }
                return;
            case NOTITLE_DETAILS_CARD:
                NoTitleDetailsViewHolder noTitleDetailsViewHolder;
                noTitleDetailsViewHolder = (NoTitleDetailsViewHolder) holder;
                noTitleDetailsViewHolder.text.setText(movieItem.getSynopsis());
                return;
            case INFO_CARD:
                OtherCardViewHolder infoViewHolder;
                infoViewHolder = (OtherCardViewHolder) holder;
                infoViewHolder.title.setText(movieItem.getDetailsList().get(position).get(0));
                infoViewHolder.other.setText(movieItem.getDetailsList().get(position).get(1));
                return;
            case OTHER_CARD:
                OtherCardViewHolder otherCardViewHolder;
                otherCardViewHolder = (OtherCardViewHolder) holder;
                otherCardViewHolder.title.setText(movieItem.getDetailsList().get(position).get(0));
                otherCardViewHolder.other.setText(movieItem.getDetailsList().get(position).get(1));
                return;
            default:
                Log.i(TAG, "getItemViewType: Error");
        }

    }

    @Override
    public int getItemCount() {
        return movieItem.getCardList().size();
    }

    public void setItem(MovieItem item){
        this.movieItem = item;
//        this.cardList.clear();
        this.cardList = item.getCardList();

        notifyDataSetChanged();
    }

    //title
    public class TitleDetailsViewHolder extends ViewHolder {

        private TextView title;
        private TextView release;
        private TextView rating;
        private TextView runtime;
        private TextView genre;

        public TitleDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_details_card_title_textView);
            release = (TextView) itemView.findViewById(R.id.title_details_card_release_textview);
            rating = (TextView) itemView.findViewById(R.id.title_details_card_rating_textview);
            runtime = (TextView) itemView.findViewById(R.id.title_details_card_runtime_textview);
            genre = (TextView) itemView.findViewById(R.id.title_details_card_genre_textview);
        }
    }

    private class NoTitleDetailsViewHolder extends ViewHolder{

        private TextView text;

        public NoTitleDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.notitle_detail_textview);
        }
    }

    private class TitleBulletViewHolder extends ViewHolder{

        private TextView title;
        private LinearLayout linearLayout;

        public TitleBulletViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_bullet_title_textview);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.title_bullet_linearlayout);
        }
    }

    private class ActorsViewHolder extends ViewHolder{

        private RecyclerView actorRecyclerView;
        private LinearLayoutManager linearLayoutManager;
        private ActorsRecycleAdapter actorsRecycleAdapter;

        public ActorsViewHolder(@NonNull View itemView) {
            super(itemView);

            actorRecyclerView = (RecyclerView) itemView.findViewById(R.id.actor_card_recyclerView);
            linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            actorsRecycleAdapter = new ActorsRecycleAdapter(movieItem.getActorList(),context);
        }
    }

    private class OtherCardViewHolder extends ViewHolder{

        private TextView title;
        private TextView other;

        public OtherCardViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_other_title_textview);
            other = (TextView) itemView.findViewById(R.id.title_other_detail_textview);
        }
    }
}
