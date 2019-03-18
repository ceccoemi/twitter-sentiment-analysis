package twittersa;


import java.io.IOException;
import java.io.File;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		Classifier classifier = trainer.train();
		assertEquals("0", classifier.classify("sad sad sad tweet"));
	}

	@Test
	public void testStoreModel() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		String fileName = classLoader.getResource("tweets_train.csv").getFile();
		TweetsReader tweetsReader = new TweetsReaderCsv(fileName);

		Trainer trainer = new Trainer(tweetsReader, 8);
		Classifier classifier = trainer.train();

		classifier.storeModel("my_model.model");
		File modelFile = new File("my_model.model");
		assertTrue(modelFile.exists());

		modelFile.delete();
	}

}
