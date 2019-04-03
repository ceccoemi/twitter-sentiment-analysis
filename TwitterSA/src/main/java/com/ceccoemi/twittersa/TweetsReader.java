package com.ceccoemi.twittersa;

import java.util.List;

@FunctionalInterface
public interface TweetsReader {

  List<Tweet> readTweets();

}
