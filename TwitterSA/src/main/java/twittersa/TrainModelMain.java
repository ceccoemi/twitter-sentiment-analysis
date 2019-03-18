package twittersa;


import java.io.IOException;
import java.io.File;

import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;

import twittersa.TweetsReader;
import twittersa.TweetsReaderCsv;
import twittersa.Trainer;
import twittersa.Classifier;


public class TrainModelMain {

	public static void main(String[] args) throws IOException {
		Options options = new Options();

		options.addOption(Option.builder("i")
				.longOpt("input-file")
				.required()
				.hasArg()
				.argName("FILE")
				.desc("input file used to train the model")
				.build());
		options.addOption(Option.builder("o")
				.longOpt("output-file")
				.hasArg()
				.argName("FILE")
				.optionalArg(true)
				.desc("where to store the model (default: ./sentiment.model)")
				.build());
		options.addOption(Option.builder("v")
				.longOpt("verbose")
				.optionalArg(true)
				.desc("run the training in verbose mode")
				.build());

		HelpFormatter helpFormatter = new HelpFormatter();
		try {

			CommandLine cmd = new DefaultParser().parse(options, args);

			// get command line arguments
			String inputPath = cmd.getOptionValue("i");
			boolean verbose = cmd.hasOption("verbose");
			String outputPath = cmd.hasOption("o") ?
					cmd.getOptionValue("o") : "." + File.separator + "sentiment.model";

			// train and store the model
			Config.getInstance().setVerbose(verbose);
			TweetsReader tweetsReaderTrain = new TweetsReaderCsv(inputPath);
			Trainer trainer = new Trainer(tweetsReaderTrain, 8);
			Classifier classifier = trainer.train();
			classifier.storeModel(outputPath);

			System.exit(0);
		} catch (ParseException exc) {
			helpFormatter.printHelp("train-model", options, true);
			System.exit(1);
		}
	}

}
