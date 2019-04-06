package com.ceccoemi.twittersa;

import java.util.Random;

public class RandomClassifier implements Classifier {

  private Random random = new Random();

  public String classify(String tweetText) {
    return String.valueOf(random.nextInt(2));
  }

}
