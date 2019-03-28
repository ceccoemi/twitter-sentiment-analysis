package com.ceccoemi.twittersa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.io.IOException;
import org.junit.Test;
import com.ceccoemi.twittersa.Tweet;
import com.ceccoemi.twittersa.TweetsReader;
import com.ceccoemi.twittersa.TweetsReaderCsv;

public class TweetsReaderCsvTest {

  @Test
  public void testIterationOnEmptyCsv() throws IOException {
    /*
    Load the file src/test/resources/tweets_train.csv
    It's a CSV file with the following content:

    sentiment,text
    */
    ClassLoader classLoader = getClass().getClassLoader();
    String fileName = classLoader.getResource("empty.csv").getFile();

    TweetsReader reader = new TweetsReaderCsv(fileName);
    Iterator<Tweet> tweetsIter = reader.iter();
    assertFalse(tweetsIter.hasNext());
  }


  @Test
  public void testIteration() throws IOException {
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
    Iterator<Tweet> tweetsIter = reader.iter();

    Tweet tweet = tweetsIter.next();
    assertEquals("sad sad sad tweet!", tweet.getText());
    assertEquals("0", tweet.getSentiment());

    tweet = tweetsIter.next();
    assertEquals("good good good tweet!", tweet.getText());
    assertEquals("1", tweet.getSentiment());

    tweet = tweetsIter.next();
    assertEquals("good good good tweet!", tweet.getText());
    assertEquals("1", tweet.getSentiment());

    tweet = tweetsIter.next();
    assertEquals("sad sad sad tweet!", tweet.getText());
    assertEquals("0", tweet.getSentiment());

    assertFalse(tweetsIter.hasNext());
  }

}
