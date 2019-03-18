package twittersa;


import java.util.List;

import twittersa.TweetsReader;
import twittersa.Classifier;
import twittersa.ConfMatrix;


public class Evaluator {

	private Classifier classifier;
	private List<String> tweets;
	private List<String> sentiments;

	public Evaluator(Classifier classifier, TweetsReader tweetsReader) {
		this.classifier = classifier;
		tweets = tweetsReader.readTweets();
		sentiments = tweetsReader.readSentiments();
	}

	public ConfMatrix evaluate(boolean verbose) {
		int truePositive = 0;
		int falsePositive = 0;
		int trueNegative = 0;
		int falseNegative = 0;
		int n = tweets.size();
		int perc = -1;
		if (verbose)
			System.out.println("Test dataset size: " + n);
		for (int i = 0; i < n; i++) {
			String classifiedCategory = classifier.classify(tweets.get(i));
			String actualCategory = sentiments.get(i);
			if ("1".equals(classifiedCategory))
				if ("1".equals(actualCategory))
					truePositive++;
				else
					falsePositive++;
				else
				if ("0".equals(actualCategory))
					trueNegative++;
				else
					falseNegative++;
				if (verbose) {
					int newPerc = i*100/n;
					if (newPerc != perc) {
						perc = newPerc;
						System.out.print("\rEvaluating ... " + perc + "%");
					}
				}
		}
		if (verbose)
			System.out.println("\rEvaluating ... done!");
		return new ConfMatrix(truePositive, falsePositive, trueNegative, falseNegative);
	}

}
