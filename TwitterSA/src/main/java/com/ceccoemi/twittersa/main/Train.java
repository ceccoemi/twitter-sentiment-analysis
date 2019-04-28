package com.ceccoemi.twittersa.main;

import com.ceccoemi.twittersa.Trainer;
import com.ceccoemi.twittersa.TweetsReader;
import com.ceccoemi.twittersa.TweetsReaderCsv;

import java.io.IOException;

public class Train {

  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.out.println("Usage: train.jar <dataset> <model-file>");
      System.exit(-1);
    }

    String inputPath = args[0];
    String outputPath = args[1];

    Trainer trainer = new Trainer();
    TweetsReader tweetsReader = new TweetsReaderCsv(inputPath);
    trainer.train(tweetsReader.readTweets());
    trainer.storeModel(outputPath);
  }

}
