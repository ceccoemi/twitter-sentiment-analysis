package com.ceccoemi.twittersa;


import java.util.Iterator;

import com.ceccoemi.twittersa.Tweet;


@FunctionalInterface
public interface TweetsReader {

  public Iterator<Tweet> iter();

}
