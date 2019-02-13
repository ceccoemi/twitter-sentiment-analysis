package twittersa;

import java.util.List;
import java.io.IOException;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import twittersa.TweetsReader;
import twittersa.TweetsReaderCsv;


public class TweetsReaderCsvTest {

    private TweetsReader tweetsReader;

    @Before
    public void setUp() throws IOException
    {
        /*
        Load the file src/test/resources/tweets_train.csv
        It's a CSV file with the following content:

        0,bad bad bad tweet!
        1,good good good tweet!
        0,bad bad bad tweet!
        1,good good good tweet!
        */
        ClassLoader classLoader = getClass().getClassLoader();
	    String fileName = classLoader.getResource("tweets_train.csv").getFile();
        
        tweetsReader = new TweetsReaderCsv(fileName);
    }

    @Test
    public void testReadTweets() throws IOException
    {
        List<String> tweets = tweetsReader.readTweets();
        assertEquals(4, tweets.size());
        assertEquals("sad sad sad tweet!", tweets.get(0));
        assertEquals("good good good tweet!", tweets.get(1));
        assertEquals("good good good tweet!", tweets.get(1));
        assertEquals("sad sad sad tweet!", tweets.get(0));
    }

    @Test
    public void testReadSentiments() throws IOException
    {
        List<String> sentiments = tweetsReader.readSentiments();
        assertEquals(4, sentiments.size());
        assertEquals("0", sentiments.get(0));  // negative sentiment: 0
        assertEquals("1", sentiments.get(1));  // positive sentiment: 1
        assertEquals("1", sentiments.get(1));  // positive sentiment: 1
        assertEquals("0", sentiments.get(0));  // negative sentiment: 0
    }
    

}
