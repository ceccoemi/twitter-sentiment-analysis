package com.ceccoemi.twittersa;

import java.util.Iterator;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.Classified;
import com.aliasi.classify.Classification;
import com.aliasi.lm.NGramProcessLM;
import com.ceccoemi.twittersa.Config;
import com.ceccoemi.twittersa.Tweet;

public class Trainer {

  private Config config = Config.getInstance();
  private DynamicLMClassifier<NGramProcessLM> classifier =
      DynamicLMClassifier.createNGramProcess(new String[]{"1", "0"}, 8);
  private boolean trained = false;

  public void train(Iterator<Tweet> tweetsIter) {
    if (config.isVerbose())
      System.out.print("Training ... ");
    if (tweetsIter.hasNext())
      trained = true;
    int i = 0;
    while (tweetsIter.hasNext()) {
      Tweet tweet = tweetsIter.next();
      String text = tweet.getText();
      String sentiment = tweet.getSentiment();
      Classification classification = new Classification(sentiment);
      Classified<CharSequence> classified = new Classified<CharSequence>(text, classification);
      classifier.handle(classified);
      if (config.isVerbose()) {
        i++;
        System.out.print("\rTraining ... " + i);
      }
    }
    if (config.isVerbose())
      System.out.println("\rTraining ... Done!");
  }

  public void storeModel(String fileName) throws IOException {
      if (!trained)
        throw new RuntimeException("Please train the model before saving it");

      FileOutputStream fos = new FileOutputStream(fileName);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      if (config.isVerbose())
        System.out.print("Saving the model in " + fileName + " ...");
      classifier.compileTo(oos);
      if (config.isVerbose())
        System.out.println("\rSaving the model in " + fileName + " Done!");
  }

}
