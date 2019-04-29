package com.ceccoemi.twittersa.main;

import com.ceccoemi.twittersa.ClassificationDriver;
import org.apache.hadoop.util.ToolRunner;

public class ClassifyMR {

  public static void main(String[] args) throws Exception {
    if (args.length != 3) {
      System.out.println("Usage: classify-mr <model-file> <input-dir> <output-dir>");
      System.exit(-1);
    }
    ToolRunner.run(new ClassificationDriver(), args);
  }
}
