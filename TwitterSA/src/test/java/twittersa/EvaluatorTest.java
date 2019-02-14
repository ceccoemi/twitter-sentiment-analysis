package twittersa;

import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import twittersa.TweetsReader;
import twittersa.TweetsReaderCsv;
import twittersa.Trainer;
import twittersa.Classifier;
import twittersa.Evaluator;
import twittersa.ConfMatrix;


public class EvaluatorTest {

    @Test
    public void testEvaluate() throws IOException
    {
        // train
        TweetsReader tweetsReaderTest = buildTweetsReader("tweets_train.csv");
        Trainer trainer = new Trainer(tweetsReaderTest, 8);
        Classifier classifier = trainer.train();
        
        // evaluate
        TweetsReader tweetsReaderTrain = buildTweetsReader("tweets_test.csv");
        Evaluator evaluator = new Evaluator(classifier, tweetsReaderTrain);
        ConfMatrix matrix = evaluator.evaluate();
        assertEquals(1.0, matrix.accuracy(), 0);
    }

    private TweetsReader buildTweetsReader(String resourceName) throws IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
	    String fileName = classLoader.getResource(resourceName).getFile();    
        return new TweetsReaderCsv(fileName);
    }

}
