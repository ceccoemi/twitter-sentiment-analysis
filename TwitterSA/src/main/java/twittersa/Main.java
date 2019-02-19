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
        if (args.length < 2 || args.length > 3) {
            System.err.println("Usage: java -jar TwitterSA train_csv test_csv [-v|--verbose]");
            System.exit(-1);
        }
        String trainFile = args[0];
        String testFile = args[1];
        boolean verbose = false;
        if (args.length == 3 && ("--verbose".equals(args[2]) || "-v".equals(args[2])))
            verbose = true; 
        
        // train
        TweetsReader tweetsReaderTrain = new TweetsReaderCsv(trainFile);
        Trainer trainer = new Trainer(tweetsReaderTrain, 8);
        Classifier classifier = trainer.train(verbose);

        // evaluate
        TweetsReader tweetsReaderTest = new TweetsReaderCsv(testFile);
        Evaluator evaluator = new Evaluator(classifier, tweetsReaderTest);
        ConfMatrix matrix = evaluator.evaluate(verbose);
    
        String output = matrix.toString();
        String[] s = output.split("Macro", 2);
        System.out.println(s[0]);
    }

}
