package com.mala.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mala.movie.Model.Episode;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeHolder> {
    private ArrayList<Episode> episodeList;
    private Context mContext;

    public EpisodeAdapter(ArrayList<Episode> episodeList, Context mContext){
        this.episodeList = episodeList;
        this.mContext = mContext;
    }
    @Override
    public EpisodeAdapter.EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.layout_movie, parent, false);
        return new EpisodeHolder(view);
    }

    @Override
    public int getItemCount() {
        return episodeList == null? 0: episodeList.size();
    }
    // This method is called when binding the data to the views being created in RecyclerView

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.EpisodeHolder holder, final int position) {
        final Episode episode = episodeList.get(position);

        // Set the data to the views here
        holder.setEpisode(episode);
        // You can set click listners to indvidual items in the viewholder here
        // make sure you pass down the listner or make the Data members of the viewHolder public
    }

    public class EpisodeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_episode)
        ImageView ivEpisodeImage;
        @BindView(R.id.tv_episodename)
        TextView tvEpisodeName;
        @BindView(R.id.tv_director) TextView tvDirector;
        @BindView(R.id.tv_episodeaairedsseason) TextView tvAired;
        @BindView(R.id.tv_gueststar) TextView tv_guest_star;
        @BindView(R.id.tv_episodeid) TextView tvID;
        @BindView(R.id.tv_rating) TextView tvRating;

        public EpisodeHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setEpisode(Episode episode){
            String path = "https://www.thetvdb.com/banners/" + episode.getFilename();
            Picasso.get().load(path).into(ivEpisodeImage);

            tvEpisodeName.setText(episode.getEpisodeName());
            tvDirector.setText(episode.getDirector());
            tvAired.setText(episode.getFirstAired());

            tvID.setText("ID : " + episode.getId());
            tvAired.setText("Aired Season : " + episode.getAiredSeason());
            tvRating.setText("Rating : " + episode.getRating());

            String guestStars = "";
            String tempGuestStar = null;
            for (String guestStar: episode.getGuestStarsList()) {
                if (tempGuestStar != null){
                    guestStars = guestStars + tempGuestStar + ", ";
                }
                tempGuestStar = guestStar;
            }

            guestStars = guestStars + tempGuestStar;
            tv_guest_star.setText(guestStars);
        }

    }

}
