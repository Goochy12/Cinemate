package au.com.liamgooch.cinemate.adapters;

import android.content.Context;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import au.com.liamgooch.cinemate.ActorItem;
import au.com.liamgooch.cinemate.R;

public class ActorsRecycleAdapter extends RecyclerView.Adapter<ActorsRecycleAdapter.ActorDetailsCardVH> {

    private ArrayList<ActorItem> actorList = new ArrayList<>();
    private Context context;

    public ActorsRecycleAdapter(ArrayList<ActorItem> actorList, Context context){
        this.actorList = actorList;
        this.context = context;
    }

    @NonNull
    @Override
    public ActorDetailsCardVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_card_actor_details,parent,false);

        ActorDetailsCardVH actorDetailsCardVH = new ActorDetailsCardVH(v);

        return actorDetailsCardVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ActorDetailsCardVH holder, int position) {
        String actorName = this.actorList.get(position).getName();
        String characterName = this.actorList.get(position).getCharacterName();
        String image = this.actorList.get(position).getImage();

        ArrayList<String> actorInfo = this.actorList.get(position).getInfoList();

        holder.actorName.setText("("+actorName+")");
        holder.characterName.setText(characterName);

        if(image != null && !image.isEmpty()){
            Picasso.get().load(image).into(holder.image);
        }

        for (int i = 1; i < actorInfo.size(); i++){
            TextView t = new TextView(context);
            SpannableString spannableString = SpannableString.valueOf(actorInfo.get(i));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                spannableString.setSpan(new BulletSpan(10,context.getResources().getColor(R.color.black,null),10),0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }else {
                spannableString = SpannableString.valueOf("\u25BA " + actorInfo.get(i));
            }
            t.setText(spannableString);
//                    t.setText(movieItem.getKey_storylines().get(storylinePos).get(i));
            holder.linearLayout.addView(t);
        }

    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    public void setActors(ArrayList<ActorItem> actorList){
        this.actorList.clear();
        this.actorList = actorList;
    }

    public class ActorDetailsCardVH extends RecyclerView.ViewHolder {

        private TextView actorName;
        private TextView characterName;
        private ImageView image;

        private LinearLayout linearLayout;

        public ActorDetailsCardVH(@NonNull View itemView) {
            super(itemView);
            actorName = (TextView) itemView.findViewById(R.id.actor_card_actor_details_actorname);
            characterName = (TextView) itemView.findViewById(R.id.actor_card_actor_details_charactername);
            image = (ImageView) itemView.findViewById(R.id.actor_card_actor_details_card_image);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.actor_card__actor_details_bullet_linearlayout);
        }
    }
}
