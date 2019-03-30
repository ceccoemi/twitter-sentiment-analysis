package com.ceccoemi.twittersa;

import com.aliasi.classify.ConfusionMatrix;

import java.util.Iterator;

public class Evaluator {

  private Classifier classifier;

  public Evaluator(Classifier classifier) {
    this.classifier = classifier;
  }

  public ConfusionMatrix evaluate(Iterator<Tweet> tweetsIterator) {
    int truePositive = 0;
    int trueNegative = 0;
    int falsePositive = 0;
    int falseNegative = 0;
    while (tweetsIterator.hasNext()) {
      Tweet tweet = tweetsIterator.next();
      String classifiedSentiment = classifier.classify(tweet.getText());
      if (tweet.getSentiment().equals(classifiedSentiment)) {
        if ("1".equals(classifiedSentiment)) {
          truePositive++;
        } else {
          trueNegative++;
        }
      } else {
        if ("1".equals(classifiedSentiment)) {
          falsePositive++;
        } else {
          falseNegative++;
        }
      }
    }
    return new ConfusionMatrix(
        new String[] {"positive", "negative"},
        new int[][] {{truePositive, falsePositive}, {falseNegative, trueNegative}});
  }
}
