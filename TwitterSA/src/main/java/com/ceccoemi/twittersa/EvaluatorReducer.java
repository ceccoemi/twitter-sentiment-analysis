package com.ceccoemi.twittersa;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class EvaluatorReducer extends Reducer<Text, IntWritable, Text, NullWritable> {

  private int tp = 0;
  private int fp = 0;
  private int fn = 0;
  private int tn = 0;

  @Override
  protected void reduce(Text key, Iterable<IntWritable> values, Context context) {
    int sum = 0;
    for (IntWritable value : values)
      sum += value.get();
    if ("tp".equals(key.toString())) tp = sum;
    else if ("fp".equals(key.toString())) fp = sum;
    else if ("fn".equals(key.toString())) fn = sum;
    else if ("tn".equals(key.toString())) tn = sum;
  }

  @Override
  protected void cleanup(Context context) throws IOException, InterruptedException {
    ConfusionMatrix confusionMatrix = new ConfusionMatrix(tp, fp, fn, tn);
    context.write(new Text(confusionMatrix.toString()), NullWritable.get());
  }

}
