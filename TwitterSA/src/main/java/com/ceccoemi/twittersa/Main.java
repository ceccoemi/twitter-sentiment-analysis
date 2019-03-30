package com.ceccoemi.twittersa;

import java.io.IOException;

public class Main {

  private void printHelpAndExit() {
    System.out.println(String.format("%s\n\n%s\n%s%s\n\n%s\n%s%s",
        "Usage: twittersa <command> [<args>]",
        "Available commands:",
        "    train <input-file> <output-file>",
        "    Train the model with data in <input-file> and store it in <output-file>",
        "Available options:",
        "    -v, --verbose",
        "    Append this to a command to print some information during the process"));
    System.exit(-1);
  }

  private void trainModelAndSaveIt(String inputPath, String outputPath) throws IOException {
    Trainer trainer = new Trainer();
    TweetsReader tweetsReader = new TweetsReaderCsv(inputPath);
    trainer.train(tweetsReader.iter());
    trainer.storeModel(outputPath);
  }

  private void run(String[] args) throws IOException {
    if (args.length < 1
        || !args[0].matches("train|validate|classify")
        || ("train".equals(args[0]) && args.length < 3))
      printHelpAndExit();

    if (args[args.length - 1].matches("-v|--verbose"))
      Config.getInstance().setVerbose(true);

    if ("train".equals(args[0])) {
      trainModelAndSaveIt(args[1], args[2]);
    }
  }

  public static void main(String[] args) throws IOException {
    new Main().run(args);
  }

}
