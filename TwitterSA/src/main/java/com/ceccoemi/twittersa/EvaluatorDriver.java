package com.ceccoemi.twittersa;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class EvaluatorDriver extends Configured implements Tool {

  @Override
  public int run(String[] args) throws Exception {
    Configuration conf = getConf();
    conf.setIfUnset("model-path", args[0]);

    Job job = Job.getInstance(conf, "evaluator");
    job.setJarByClass(Main.class);

    job.setMapperClass(EvaluatorMapper.class);
    job.setReducerClass(EvaluatorReducer.class);
    job.setNumReduceTasks(1);

    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(NullWritable.class);

    FileInputFormat.addInputPath(job, new Path(args[1]));
    FileOutputFormat.setOutputPath(job, new Path(args[2]));

    return job.waitForCompletion(true) ? 0 : 1;
  }

 public static void main(String[] args) throws Exception {
    System.exit(ToolRunner.run(new EvaluatorDriver(), args));
 }

}
