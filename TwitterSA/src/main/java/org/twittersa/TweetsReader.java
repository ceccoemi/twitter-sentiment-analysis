package org.twittersa;


import java.util.Iterator;

import org.twittersa.Tweet;


@FunctionalInterface
public interface TweetsReader {

	public Iterator<Tweet> iter();

}
