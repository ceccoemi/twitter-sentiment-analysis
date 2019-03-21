package twittersa;


import java.util.Iterator;

import twittersa.Tweet;


@FunctionalInterface
public interface TweetsReader {

	public Iterator<Tweet> iter();

}
