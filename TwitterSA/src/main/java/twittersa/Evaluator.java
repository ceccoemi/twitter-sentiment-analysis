package twittersa;


import java.util.List;

import twittersa.TweetsReader;
import twittersa.Classifier;
import twittersa.ConfMatrix;


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

    public ConfMatrix evaluate()
    {
        int truePositive = 0;
        int falsePositive = 0;
        int trueNegative = 0;
        int falseNegative = 0;
        for (int i = 0; i < tweets.size(); i++) {
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
        }
        return new ConfMatrix(truePositive, falsePositive, trueNegative, falseNegative);
    }

}
