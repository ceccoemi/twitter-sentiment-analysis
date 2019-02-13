package twittersa;

import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.lm.NGramProcessLM;
import com.aliasi.util.CommaSeparatedValues;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Classifier {

	private DynamicLMClassifier<NGramProcessLM> mClassifier; 
    private List<String> tweets;
    private List<Integer> sentiments;

    public Classifier(TweetsReader tweetsReader) throws IOException
    {
        tweets = tweetsReader.readTweets();
        sentiments = tweetsReader.readSentiments();
    }

}
