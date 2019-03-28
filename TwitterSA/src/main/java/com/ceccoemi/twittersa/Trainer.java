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
import com.ceccoemi.twittersa.Tweet;


public class Trainer {

  private DynamicLMClassifier<NGramProcessLM> classifier =
      DynamicLMClassifier.createNGramProcess(new String[]{"1", "0"}, 8);
  private boolean trained = false;

  public void train(Iterator<Tweet> tweetsIter) {
    while (tweetsIter.hasNext()) {
      Tweet tweet = tweetsIter.next();
      String text = tweet.getText();
      String sentiment = tweet.getSentiment();
      Classification classification = new Classification(sentiment);
      Classified<CharSequence> classified = new Classified<CharSequence>(text, classification);
      classifier.handle(classified);
    }
    trained = true;
  }

  public void storeModel(String fileName) throws IOException {
      if (!trained)
        throw new RuntimeException("Please train the model before saving it");

      FileOutputStream fos = new FileOutputStream(fileName);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      classifier.compileTo(oos);
  }

}
