package twittersa;


import java.util.List;

import twittersa.TweetsReader;
import twittersa.Classifier;


public class Evaluator {

    private Classifier classifier;
    private List<String> tweets;
    private List<String> sentiments;

    public Evaluator(Classifier classifier, TweetsReader tweetsReader)
    {
        this.classifier = classifier;
        tweets = tweetsReader.readTweets();
        sentiments = tweetsReader.readSentiments();
    }

    public double evaluate()
    {
        int n = tweets.size();
        double numCorrect = 0.0;
        for (int i = 0; i < n; i++) {
            String classifiedCategory = classifier.classify(tweets.get(i));
            String actualCategory = sentiments.get(i);
            if (actualCategory.equals(classifiedCategory))
                numCorrect++;
        }
        return numCorrect / n;
    }

}
