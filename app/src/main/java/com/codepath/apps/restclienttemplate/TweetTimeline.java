package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Headers;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.restclienttemplate.adapters.TweetsAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TweetTimeline extends AppCompatActivity {

    TwitterClient client;
    TweetsAdapter tweetsAdapter;
    List<Tweet> tweets;
    RecyclerView recyclerViewTweets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_timeline);
        this.tweets = new ArrayList<>();

        client = TwitterApp.getRestClient(this);
        populateTimeline();

        this.tweetsAdapter = new TweetsAdapter(tweets, this);
        this.recyclerViewTweets = findViewById(R.id.rvTweets);
        this.recyclerViewTweets.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerViewTweets.setAdapter(tweetsAdapter);

    }

    private void populateTimeline() {
        this.client.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    tweets.addAll(Tweet.fromJsonArray(json.jsonArray));
                    tweetsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });
    }

}