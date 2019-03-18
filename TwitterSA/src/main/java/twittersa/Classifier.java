package twittersa;

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

}
