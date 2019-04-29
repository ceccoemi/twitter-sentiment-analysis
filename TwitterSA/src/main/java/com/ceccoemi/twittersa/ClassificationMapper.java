package com.ceccoemi.twittersa;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.File;
import java.io.IOException;

public class ClassificationMapper extends Mapper<Object, Text, Text, IntWritable> {

  private Classifier classifier;

  public ClassificationMapper(Classifier classifier) {
    this.classifier = classifier;
  }

  @Override
  public void setup(Context context) {
    try {
      File modelFile = new File("./model");
      classifier = new Classifier(modelFile);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {
    String sentiment = classifier.classify(value.toString());
    String outputKey = "";
    if ("0".equals(sentiment)) {
      outputKey = "negatives";
  } else {
      outputKey = "positives";
  }
    context.write(new Text(outputKey), new IntWritable(1));
  }

}
