package com.ceccoemi.twittersa;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class EvaluatorMapper extends Mapper<Object, Text, Text, IntWritable> {

  private Classifier classifier;

  public EvaluatorMapper(Classifier classifier) {
    this.classifier = classifier;
  }

  @Override
  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {
    Tweet tweet = new Tweet(value.toString());
    String classifiedSentiment = classifier.classify(tweet.getText());
  }
}
