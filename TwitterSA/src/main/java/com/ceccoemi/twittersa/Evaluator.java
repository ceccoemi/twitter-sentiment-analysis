package com.ceccoemi.twittersa;

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

    int n = tweets.size();
    int progressPercentage = 0;
    int truePositives = 0;
    int trueNegatives = 0;
    int falsePositives = 0;
    int falseNegatives = 0;
    for (int i = 0; i < n; i++) {
      Tweet tweet = tweets.get(i);
      String classifiedSentiment = classifier.classify(tweet.getText());
      if (tweet.getSentiment().equals(classifiedSentiment))
        if ("1".equals(classifiedSentiment))
          truePositives++;
        else
          trueNegatives++;
      else if ("1".equals(classifiedSentiment))
        falsePositives++;
      else
        falseNegatives++;
      if (config.isVerbose()) {
        int newPercentage = i*100/n;
        if (newPercentage != progressPercentage) {
          progressPercentage = newPercentage;
          System.out.print("\rEvaluating ... " + progressPercentage + "%");
        }
      }
    }

    if (config.isVerbose()) {
      System.out.println("\rEvaluating ... Done!    ");
    }

    return new ConfusionMatrix(
        truePositives, falsePositives, falseNegatives, trueNegatives);
  }
}
