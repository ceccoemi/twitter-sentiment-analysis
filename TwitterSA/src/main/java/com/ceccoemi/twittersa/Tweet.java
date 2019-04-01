package com.ceccoemi.twittersa;


public class Tweet {

  private String sentiment;  // 0: NEGATIVE  -  1: POSITIVE
  private String text;

  public Tweet(String sentiment, String text) {
    this.sentiment = sentiment;
    this.text = text;
  }

  public Tweet(String csvLine) {
    String[] splitted = csvLine.split(",", 2);
    sentiment = splitted[0];
    text = splitted[1];
  }

  public String getSentiment() {
    return sentiment;
  }

  public String getText() {
    return text;
  }

}
