package twittersa;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.lm.NGramProcessLM;


public class Classifier {

	private DynamicLMClassifier<NGramProcessLM> classifier;

	// It is suggested to be constructed with the train() method of the Trainer class
	Classifier(DynamicLMClassifier<NGramProcessLM> classifier) {
		this.classifier = classifier;
	}

	public String classify(String tweet) {
		return classifier.classify(tweet).bestCategory();
	}

	public void storeModel(String path) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(path);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		classifier.compileTo(objOut);
		objOut.close();
	}

}
