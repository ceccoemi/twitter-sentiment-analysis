package com.ceccoemi.twittersa;

import java.util.Iterator;
import java.util.List;

public class Evaluator {

  private Config config = Config.getInstance();
  private Classifier classifier;

  public Evaluator(Classifier classifier) {
    this.classifier = classifier;
  }

  public ConfusionMatrix evaluate(List<Tweet> tweets) {
    if (config.isVerbose())
      System.out.print("Evaluating ... ");
    int i = 0;
    int truePositive = 0;
    int trueNegative = 0;
    int falsePositive = 0;
    int falseNegative = 0;
    for (Tweet tweet : tweets) {
      String classifiedSentiment = classifier.classify(tweet.getText());
      if (tweet.getSentiment().equals(classifiedSentiment))
        if ("1".equals(classifiedSentiment))
          truePositive++;
        else
          trueNegative++;
      else if ("1".equals(classifiedSentiment))
        falsePositive++;
      else
        falseNegative++;
      if (config.isVerbose()) {
        i++;
        System.out.print("\rEvaluating ... " + i);
      }
    }
    if (config.isVerbose()) {
      System.out.println("\rEvaluating ... Done!    ");
    }
    return new ConfusionMatrix(
        truePositive, falsePositive, falseNegative, trueNegative);
  }
}
