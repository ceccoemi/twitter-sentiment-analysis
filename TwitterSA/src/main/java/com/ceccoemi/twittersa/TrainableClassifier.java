package com.ceccoemi.twittersa;

import com.aliasi.classify.LMClassifier;
import com.aliasi.lm.LanguageModel;
import com.aliasi.stats.MultivariateDistribution;
import com.aliasi.util.AbstractExternalizable;

import java.io.File;
import java.io.IOException;

public class TrainableClassifier implements Classifier {

  private LMClassifier<LanguageModel, MultivariateDistribution> classifier;

  public TrainableClassifier(String modelPath) throws IOException, ClassNotFoundException {
    Config config = Config.getInstance();

    if (config.isVerbose())
      System.out.print("Loading model from \"" + modelPath + "\" ... ");

    File modelFile = new File(modelPath);
    classifier = (LMClassifier<LanguageModel, MultivariateDistribution>)
        AbstractExternalizable.readObject(modelFile);

    if (config.isVerbose())
      System.out.println("\rLoading model from \"" + modelPath + "\" ... Done!");
  }

  public String classify(String tweet) {
    return classifier.classify(tweet).bestCategory();
  }

}
