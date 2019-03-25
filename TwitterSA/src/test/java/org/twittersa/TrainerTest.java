package org.twittersa;


import java.util.Iterator;
import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

import org.twittersa.Tweet;
import org.twittersa.Trainer;


public class TrainerTest {

	@After
	public void tearDown() {
		new File("sentiment.model").delete();
	}

	@Test(expected = RuntimeException.class)
	public void storeModelWithoutTrainingiThrowsException() {
		Trainer trainer = new Trainer();
		trainer.storeModel("sentiment.model");
	}

	private Iterator<Tweet> createMockedIterator() {
		Iterator<Tweet> tweetsIter = mock(Iterator.class);
		when(tweetsIter.hasNext()).thenReturn(true, true, true, true, false);
		when(tweetsIter.next()).thenReturn(
				new Tweet("0", "sad sad sad tweet!"),
				new Tweet("1", "good good good tweet!"),
				new Tweet("1", "good good good tweet!"),
				new Tweet("0", "sad sad sad tweet!"));
		return tweetsIter;
	}

	@Test
	public void testTraining() {
		Iterator<Tweet> tweetsIter = createMockedIterator();

		Trainer trainer = new Trainer();
		trainer.train(tweetsIter);

		trainer.storeModel("sentiment.model");
		assertTrue(new File("sentiment.model").exists());
	}

	public void throwExceptionIfCantStoreModel() {
		new File("sentiment.model").mkdir();  // create a directory to fail the file creation

		Iterator<Tweet> tweetsIter = createMockedIterator();

		Trainer trainer = new Trainer();
		trainer.train(tweetsIter);

		trainer.storeModel("sentiment.model");
		assertTrue(new File("sentiment.model").isDirectory());
	}

}
