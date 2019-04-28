package com.ceccoemi.twittersa;

import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TweetsReaderCsvTest {

  @Test
  public void readEmptyCsv() throws IOException {
    /*
    Load the file src/test/resources/tweets_train.csv
    It's a CSV file with the following content:

    sentiment,text
    */
    ClassLoader classLoader = getClass().getClassLoader();
    String fileName = classLoader.getResource("empty.csv").getFile();

    TweetsReader reader = new TweetsReaderCsv(fileName);
    Iterator<Tweet> tweets = reader.readTweets();
    assertFalse(tweets.hasNext());
  }


  @Test
  public void readCsv() throws IOException {
    /*
    Load the file src/test/resources/tweets_train.csv
    It's a CSV file with the following content:

    sentiment,text
    0,sad sad sad tweet!
    1,good good good tweet!
    1,good good good tweet!
    0,sad sad sad tweet!
    */
    ClassLoader classLoader = getClass().getClassLoader();
    String fileName = classLoader.getResource("tweets.csv").getFile();

    TweetsReader reader = new TweetsReaderCsv(fileName);
    Iterator<Tweet> tweets = reader.readTweets();

    Tweet tweet = tweets.next();
    assertEquals("sad sad sad tweet!", tweet.getText());
    assertEquals("0", tweet.getSentiment());

    tweet = tweets.next();
    assertEquals("good good good tweet!", tweet.getText());
    assertEquals("1", tweet.getSentiment());

    tweet = tweets.next();
    assertEquals("good good good tweet!", tweet.getText());
    assertEquals("1", tweet.getSentiment());

    tweet = tweets.next();
    assertEquals("sad sad sad tweet!", tweet.getText());
    assertEquals("0", tweet.getSentiment());

    assertFalse(tweets.hasNext());
  }

}
