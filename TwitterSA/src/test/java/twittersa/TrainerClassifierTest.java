package twittersa;


import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import twittersa.TweetsReader;
import twittersa.TweetsReaderCsv;
import twittersa.Trainer;
import twittersa.Classifier;


public class TrainerClassifierTest {

	@Test
	public void testTrain() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		String fileName = classLoader.getResource("tweets_train.csv").getFile();
		TweetsReader tweetsReader = new TweetsReaderCsv(fileName);

		Trainer trainer = new Trainer(tweetsReader, 8);
		Classifier classifier = trainer.train(false);
		assertEquals("0", classifier.classify("sad sad sad tweet"));
	}

}
