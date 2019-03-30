package com.ceccoemi.twittersa;

import com.aliasi.classify.LMClassifier;
import com.aliasi.lm.LanguageModel;
import com.aliasi.stats.MultivariateDistribution;
import com.aliasi.util.AbstractExternalizable;

import java.io.File;
import java.io.IOException;

public class Classifier {

  private LMClassifier<LanguageModel, MultivariateDistribution> classifier;

  public Classifier(String modelPath) throws IOException, ClassNotFoundException {
    File modelFile = new File(modelPath);
    classifier = (LMClassifier<LanguageModel, MultivariateDistribution>)
        AbstractExternalizable.readObject(modelFile);
  }

  public String classify(String tweet) {
    return classifier.classify(tweet).bestCategory();
  }

}
