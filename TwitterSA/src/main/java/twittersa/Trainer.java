package twittersa;


import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.lm.NGramProcessLM;
import com.aliasi.util.CommaSeparatedValues;

import java.io.File;
import java.io.IOException;
import java.util.List;

import twittersa.Classifier;


public class Trainer {

	private DynamicLMClassifier<NGramProcessLM> classifier;
    private List<String> tweets;
    private List<String> sentiments;

    public Trainer(TweetsReader tweetsReader, int nGram) throws IOException
    {
        tweets = tweetsReader.readTweets();
        sentiments = tweetsReader.readSentiments();
        classifier = DynamicLMClassifier.createNGramProcess(new String[] {"0", "1"}, nGram);
    }

    public Classifier train()
    {
        for (int i = 0; i < tweets.size(); i++) {
            String tweet = tweets.get(i);
            String sentiment = sentiments.get(i);
            Classification c = new Classification(sentiment);
            Classified<CharSequence> classified = new Classified<CharSequence>(tweet, c);
            classifier.handle(classified);
        }
        return new Classifier(classifier);
    }

}
