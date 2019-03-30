package com.ceccoemi.twittersa;

import java.util.Iterator;

@FunctionalInterface
public interface TweetsReader {

  Iterator<Tweet> iter();

}
