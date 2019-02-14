package twittersa;


import java.io.IOException;

import twittersa.TweetsReader;
import twittersa.TweetsReaderCsv;
import twittersa.Trainer;
import twittersa.Classifier;
import twittersa.Evaluator;
import twittersa.ConfMatrix;


public class Main {

    public static void main(String[] args) throws IOException
    {
        String trainFile = args[0];
        String testFile = args[1];
        
        // train
        TweetsReader tweetsReaderTrain = new TweetsReaderCsv(trainFile);
        Trainer trainer = new Trainer(tweetsReaderTrain, 8);
        Classifier classifier = trainer.train();

        // evaluate
        TweetsReader tweetsReaderTest = new TweetsReaderCsv(testFile);
        Evaluator evaluator = new Evaluator(classifier, tweetsReaderTest);
        ConfMatrix matrix = evaluator.evaluate();
    
        String output = matrix.toString();
        String[] s = output.split("Macro", 2);
        System.out.println(s[0]);
    }

}
