package com.ceccoemi.twittersa;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    List<Tweet> tweets = reader.readTweets();
    assertEquals(0, tweets.size());
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
    List<Tweet> tweets = reader.readTweets();

    assertEquals(4, tweets.size());

    Tweet tweet = tweets.get(0);
    assertEquals("sad sad sad tweet!", tweet.getText());
    assertEquals("0", tweet.getSentiment());

    tweet = tweets.get(1);
    assertEquals("good good good tweet!", tweet.getText());
    assertEquals("1", tweet.getSentiment());

    tweet = tweets.get(2);
    assertEquals("good good good tweet!", tweet.getText());
    assertEquals("1", tweet.getSentiment());

    tweet = tweets.get(3);
    assertEquals("sad sad sad tweet!", tweet.getText());
    assertEquals("0", tweet.getSentiment());
  }

}
