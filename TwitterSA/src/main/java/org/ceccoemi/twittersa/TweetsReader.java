package org.ceccoemi.twittersa;


import java.util.Iterator;

import org.ceccoemi.twittersa.Tweet;


@FunctionalInterface
public interface TweetsReader {

	public Iterator<Tweet> iter();

}
