package com.ceccoemi.twittersa;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class EvaluatorMapper extends Mapper<Object, Text, Text, IntWritable> {

  private Classifier classifier;

  @Override
  public void setup(Context context) {
    try {
      File modelFile = new File("sentiment.model");
      classifier = new TrainableClassifier(modelFile);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    // classifier = new RandomClassifier();
  }

  @Override
  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {
    String[] splitted = value.toString().split(",", 2);
    String actualSentiment = splitted[0];
    if (!actualSentiment.matches("[01]")) return;
    String tweetText = splitted[1];
    String classifiedSentiment = classifier.classify(tweetText);
    String outputKey = "";
    if ("1".equals(classifiedSentiment))
      outputKey = classifiedSentiment.equals(actualSentiment) ? "tp" : "fp";
    else
      outputKey = classifiedSentiment.equals(actualSentiment) ? "tn" : "fn";
    context.write(new Text(outputKey), new IntWritable(1));
  }

}
