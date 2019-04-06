package com.ceccoemi.twittersa;

@FunctionalInterface
public interface Classifier {

  public String classify(String tweetText);

}
