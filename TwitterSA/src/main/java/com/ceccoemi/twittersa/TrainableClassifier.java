package com.ceccoemi.twittersa;

import com.aliasi.classify.LMClassifier;
import com.aliasi.lm.LanguageModel;
import com.aliasi.stats.MultivariateDistribution;
import com.aliasi.util.AbstractExternalizable;

import java.io.File;
import java.io.IOException;

public class TrainableClassifier implements Classifier {

  private LMClassifier<LanguageModel, MultivariateDistribution> classifier;

  public TrainableClassifier(File modelFile) throws IOException, ClassNotFoundException {
    Config config = Config.getInstance();

    if (config.isVerbose())
      System.out.print("Loading model from \"" + modelFile.getName() + "\" ... ");

    classifier = (LMClassifier<LanguageModel, MultivariateDistribution>)
        AbstractExternalizable.readObject(modelFile);

    if (config.isVerbose())
      System.out.println("\rLoading model from \"" + modelFile.getName() + "\" ... Done!");
  }

  public String classify(String tweet) {
    return classifier.classify(tweet).bestCategory();
  }

}
