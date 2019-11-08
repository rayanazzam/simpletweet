package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {
    public String createdAt;
    public String body;
    public User user;

    public Tweet () {}

    public static Tweet fromJson (JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.body = jsonObject.getString("text");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));

        return tweet;
    }

    public static List<Tweet> fromJsonArray (JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i ++) {
            tweets.add(Tweet.fromJson(jsonArray.getJSONObject(i)));
        }

        return tweets;
    }
}
