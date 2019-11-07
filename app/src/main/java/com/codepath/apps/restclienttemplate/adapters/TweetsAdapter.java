package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    List<Tweet> tweets;
    Context context;

    public TweetsAdapter (List<Tweet> tweets, Context context) {
        this.tweets = tweets;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tweet_item, parent, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind (tweets.get(position));
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> tweets) {
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePicture;
        TextView twitterHandle;
        TextView tweetBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.profilePicture = itemView.findViewById(R.id.ivProfilePicture);
            this.twitterHandle = itemView.findViewById(R.id.tvTwitterHandle);
            this.tweetBody = itemView.findViewById(R.id.tvDescription);
        }

        public void bind(Tweet tweet) {
            this.tweetBody.setText(tweet.body);
            this.twitterHandle.setText(tweet.user.screenName);

            Glide.with(context)
                    .load(tweet.user.imgUrl)
                    .into(profilePicture);
        }
    }
}
