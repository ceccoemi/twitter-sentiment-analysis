package com.ceccoemi.twittersa;

import org.apache.hadoop.util.ToolRunner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

  private void printHelpAndExit() {
    System.out.println(String.join(System.lineSeparator(),
        "Usage: twittersa <command> [<args>]",
        "",
        "Available commands:",
        "    train <input-file> <output-file> [-v|--verbose]",
        "    evaluate <model-file> <input-dir> [-v|--verbose]",
        "    evaluate-mr <model-file> <input-dir> <output-dir>"));
    System.exit(-1);
  }

  private void trainModelAndSaveIt(String inputPath, String outputPath) throws IOException {
    Trainer trainer = new Trainer();
    TweetsReader tweetsReader = new TweetsReaderCsv(inputPath);
    trainer.train(tweetsReader.readTweets());
    trainer.storeModel(outputPath);
  }

  private void evaluateModel(String modelPath, String inputDir)
      throws IOException, ClassNotFoundException {
    File modelFile = new File(modelPath);
    Classifier classifier = new TrainableClassifier(modelFile);
    Evaluator evaluator = new Evaluator(classifier);

    File rootDir = new File(inputDir);
    File[] files = rootDir.listFiles();
    ConfusionMatrix[] matrices = new ConfusionMatrix[files.length];
    for (int i = 0; i < files.length; i++) {
      String fileName = files[i].getCanonicalPath();
      TweetsReader reader = new TweetsReaderCsv(fileName);
      List<Tweet> tweets = reader.readTweets();
      matrices[i] = evaluator.evaluate(tweets);
    }
    ConfusionMatrix matrix = ConfusionMatrix.join(matrices);
    System.out.println(matrix.toString());
  }

  private void run(String[] args) throws Exception {
    if (args.length < 3 || !args[0].matches("train|evaluate|evaluate-mr")) {
      printHelpAndExit();
    }

    if (args[args.length - 1].matches("-v|--verbose")) {
      Config.getInstance().setVerbose(true);
    }

    if ("train".equals(args[0])) {
      trainModelAndSaveIt(args[1], args[2]);
    } else if ("evaluate".equals(args[0])) {
      evaluateModel(args[1], args[2]);
    } else if ("evaluate-mr".equals(args[0])) {
      if (args.length < 4) printHelpAndExit();
      ToolRunner.run(new EvaluatorDriver(), Arrays.copyOfRange(args, 1, 4));
    }
  }

  public static void main(String[] args) throws Exception {
    new Main().run(args);
  }

}
