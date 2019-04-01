package com.ceccoemi.twittersa;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TweetTest {

  @Test
  public void buildTweetFromCsvLine() {
    String csvLine = "1,\"Hey Joe! I'm feeling well, and you?";
    Tweet tweet = new Tweet(csvLine);
    assertEquals("1", tweet.getSentiment());
    assertEquals("\"Hey Joe! I'm feeling well, and you?", tweet.getText());
  }
}
