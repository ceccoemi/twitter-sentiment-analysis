package twittersa;

import java.util.List;


public interface TweetsReader {

    public List<String> readTweets();

    public List<Integer> readSentiments();

}
