package com.ceccoemi.twittersa;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class EvaluatorPartitioner extends Partitioner<Text, IntWritable> {

  @Override
  public int getPartition(Text key, IntWritable value, int reduceTasks) {
    if ("tp".equals(key.toString())) return 0;
    if ("fp".equals(key.toString())) return 1;
    if ("fn".equals(key.toString())) return 2;
    if ("tn".equals(key.toString())) return 3;
    return -1;
  }
}
