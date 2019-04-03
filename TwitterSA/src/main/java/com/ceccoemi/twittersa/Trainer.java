package com.ceccoemi.twittersa;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.lm.NGramProcessLM;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Trainer {

  private Config config = Config.getInstance();
  private DynamicLMClassifier<NGramProcessLM> classifier =
      DynamicLMClassifier.createNGramProcess(new String[]{"1", "0"}, 8);

  public void train(List<Tweet> tweets) {
    if (config.isVerbose())
      System.out.print("Training ... ");

    int n = tweets.size();
    int progressPercentage = 0;
    for (int i = 0; i < n; i++) {
      Tweet tweet = tweets.get(i);
      String text = tweet.getText();
      String sentiment = tweet.getSentiment();
      Classification classification = new Classification(sentiment);
      Classified<CharSequence> classified = new Classified<CharSequence>(text, classification);
      classifier.handle(classified);
      if (config.isVerbose()) {
        int newPercentage = i*100/n;
        if (newPercentage != progressPercentage) {
          progressPercentage = newPercentage;
          System.out.print("\rTraining ... " + progressPercentage + "%");
        }
      }
    }

    if (config.isVerbose())
      System.out.println("\rTraining ... Done!");
  }

  public void storeModel(String fileName) throws IOException {
      FileOutputStream fos = new FileOutputStream(fileName);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      if (config.isVerbose())
        System.out.print("Saving the model in \"" + fileName + "\" ... ");
      classifier.compileTo(oos);
      if (config.isVerbose())
        System.out.println("\rSaving the model in \"" + fileName + "\" Done!");
  }

}
