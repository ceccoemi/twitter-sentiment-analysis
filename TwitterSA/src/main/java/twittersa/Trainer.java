package twittersa;


import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.lm.NGramProcessLM;
import com.aliasi.util.CommaSeparatedValues;

import java.io.File;
import java.io.IOException;
import java.util.List;

import twittersa.Config;
import twittersa.Classifier;


public class Trainer {

	private Config config;
	private DynamicLMClassifier<NGramProcessLM> classifier;
	private List<String> tweets;
	private List<String> sentiments;

	public Trainer(TweetsReader tweetsReader, int nGram) throws IOException {
		config = Config.getInstance();
		if (config.isVerbose())
			System.out.print("Reading input file ... ");
		tweets = tweetsReader.readTweets();
		sentiments = tweetsReader.readSentiments();
		if (config.isVerbose())
			System.out.println("Done!");
		classifier = DynamicLMClassifier.createNGramProcess(new String[] {"0", "1"}, nGram);
	}

	public Classifier train() {
		int n = tweets.size();
		int perc = -1;
		if (config.isVerbose())
			System.out.println("Training set size: " + n);
		for (int i = 0; i < n; i++) {
			String tweet = tweets.get(i);
			String sentiment = sentiments.get(i);
			Classification c = new Classification(sentiment);
			Classified<CharSequence> classified = new Classified<CharSequence>(tweet, c);
			classifier.handle(classified);
			if (config.isVerbose()) {
				int newPerc = i*100/n;
				if (newPerc != perc) {
					perc = newPerc;
					System.out.print("\rTraining ... " + perc + "%");
				}
			}
		}
		if (config.isVerbose())
			System.out.println("\rTraining ... Done!");
		return new Classifier(classifier);
	}

}
