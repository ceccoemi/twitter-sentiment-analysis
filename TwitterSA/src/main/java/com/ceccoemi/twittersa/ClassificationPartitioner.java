package com.ceccoemi.twittersa;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class ClassificationPartitioner extends Partitioner<Text, IntWritable> {

  @Override
  public int getPartition(Text key, IntWritable value, int reduceTasks) {
    return "negatives".equals(key.toString()) ? 0 : 1;
  }
}
