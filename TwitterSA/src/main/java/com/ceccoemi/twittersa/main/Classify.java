package com.ceccoemi.twittersa.main;

import com.ceccoemi.twittersa.Classifier;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

public class Classify {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    if (args.length != 2) {
      System.out.println("Usage: classify.jar <model-file> <input-dir>");
      System.exit(-1);
    }

    String modelFile = args[0];
    String inputDir = args[1];

    Classifier classifier = new Classifier(new File(modelFile));

    int total = 0;
    int positives = 0;
    int negatives = 0;
    File rootDir = new File(inputDir);
    for (File inputFile : rootDir.listFiles()) {
      LineIterator it = FileUtils.lineIterator(inputFile);
      while (it.hasNext()) {
        String tweetText = it.next();
        String sentiment = classifier.classify(tweetText);
        if ("0".equals(sentiment)) {
          negatives++;
        } else {
          positives++;
        }
        total++;
      }
    }
    System.out.println("Total: " + total);
    System.out.println("Negatives: " + negatives + " - " + negatives*100/total + "%");
    System.out.println("Positives: " + positives + " - " + positives*100/total + "%");
  }

}
