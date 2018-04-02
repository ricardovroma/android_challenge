package com.example.roma.android_challenge.repository_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.android_challenge.R;
import com.example.roma.android_challenge.repository_list.models.RepositoryItemModelRest;
import com.example.roma.android_challenge.core.api.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class RepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final Listener listener;
    private ArrayList<RepositoryItemModelRest> items = new ArrayList<>();

    private static class ViewHolder extends RecyclerView.ViewHolder {


        private View viewContainer;
        private TextView tvRepositoryName;
        private TextView tvRepositoryDescription;
        private ImageView ivRepository;
        private TextView tvUsername;
        private TextView tvName;
        private ImageView ivStar;
        private ImageView ivShare;
        private TextView tvRepositoryForks;
        private TextView tvRepositoryStars;

        @SuppressLint("WrongViewCast")
        ViewHolder(View itemView) {
            super(itemView);
            viewContainer = itemView.findViewById(R.id.view_container);
            tvRepositoryName = itemView.findViewById(R.id.tvRepositoryName);
            tvRepositoryDescription = itemView.findViewById(R.id.tvRepositoryDescription);
            ivRepository = itemView.findViewById(R.id.ivRepository);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivStar = itemView.findViewById(R.id.iv_star);
            ivShare = itemView.findViewById(R.id.iv_share);
            tvRepositoryForks = itemView.findViewById(R.id.tv_repository_forks);
            tvRepositoryStars = itemView.findViewById(R.id.tv_repository_stars);
        }
    }

    public RepositoryAdapter(ArrayList<RepositoryItemModelRest> items, Context context, Listener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.repository_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final RepositoryItemModelRest item = (RepositoryItemModelRest) items.get(position);
        ViewHolder holder = (ViewHolder) viewHolder;

        Picasso.with(context).load(item.owner.avatarUrl).transform(new CircleTransform()).into(holder.ivRepository);
        holder.ivShare.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));
        holder.ivStar.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));

        holder.tvRepositoryName.setText(item.name);
        holder.tvRepositoryDescription.setText(item.description);
        holder.tvUsername.setText(item.owner.login);
        holder.tvRepositoryForks.setText(item.forksCount.toString());
        holder.tvRepositoryStars.setText(item.stargazersCount.toString());

        holder.viewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListener(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public ArrayList<RepositoryItemModelRest> getItems() {
        return items;
    }

    public void setItems(ArrayList<RepositoryItemModelRest> items) {
        this.items = items;
    }

    interface Listener {
        void onClickListener(RepositoryItemModelRest repositoryItemModelRest);
    }
}