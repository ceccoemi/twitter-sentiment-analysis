package twittersa;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.lm.NGramProcessLM;

import twittersa.Config;


public class Classifier {

	private Config config;
	private DynamicLMClassifier<NGramProcessLM> classifier;

	// It is suggested to be constructed with the train() method of the Trainer class
	Classifier(DynamicLMClassifier<NGramProcessLM> classifier) {
		config = Config.getInstance();
		this.classifier = classifier;
	}

	public String classify(String tweet) {
		return classifier.classify(tweet).bestCategory();
	}

	public void storeModel(String path) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(path);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		if (config.isVerbose())
			System.out.print("Saving the model in " + path + " ... ");
		classifier.compileTo(objOut);
		objOut.close();
		if (config.isVerbose())
			System.out.println("Done!");
	}

}
