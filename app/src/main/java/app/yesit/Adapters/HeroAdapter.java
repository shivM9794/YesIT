package app.yesit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.yesit.R;
import app.yesit.ResponseModel.Example;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {

    private List<Example> heroList;
    private Context context;
    private static int currentPosition = 0;

    public HeroAdapter(List<Example> heroList, Context context) {
        this.heroList = heroList;
        this.context = context;
    }


    @NonNull
    @Override
    public HeroAdapter.HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marvel_studio,parent,false);
        return new HeroAdapter.HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroAdapter.HeroViewHolder holder,int position) {
        Example example = heroList.get(position);
        holder.textViewName.setText(example.getName());
        holder.textViewRealName.setText(example.getRealname());
        holder.textViewTeam.setText(example.getTeam());
        holder.textViewFirstAppearance.setText(example.getFirstappearance());
        holder.textViewCreatedBy.setText(example.getCreatedby());
        holder.textViewPublisher.setText(example.getPublisher());
        holder.textViewBio.setText(example.getBio().trim());
        Glide.with(context).load(example.getImageurl()).into(holder.imageView);
        holder.linearLayout.setVisibility(View.GONE);
        if (currentPosition == position) {
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.bottom_style);
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.textViewName.setOnClickListener(view -> {
            currentPosition = position;
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public class HeroViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewRealName, textViewTeam, textViewFirstAppearance,
                textViewCreatedBy, textViewPublisher, textViewBio;
        ImageView imageView;
        LinearLayout linearLayout;
        public HeroViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewRealName = itemView.findViewById(R.id.textViewRealName);
            textViewTeam = itemView.findViewById(R.id.textViewTeam);
            textViewFirstAppearance = itemView.findViewById(R.id.textViewFirstAppearance);
            textViewCreatedBy = itemView.findViewById(R.id.textViewCreatedBy);
            textViewPublisher = itemView.findViewById(R.id.textViewPublisher);
            textViewBio = itemView.findViewById(R.id.textViewBio);
            imageView = itemView.findViewById(R.id.imageView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
