package twittersa;


import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.BaseClassifier;
import com.aliasi.lm.NGramProcessLM;

import twittersa.TweetsReader;
import twittersa.TweetsReaderCsv;
import twittersa.Trainer;


public class TrainerTest {

    @Test
    public void testTrain() throws IOException
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
    
        TweetsReader tweetsReader = new TweetsReaderCsv(fileName);
        Trainer trainer = new Trainer(tweetsReader, 8);

        DynamicLMClassifier<NGramProcessLM> classifier = trainer.train();
        assertNotNull(classifier);
    }

}
