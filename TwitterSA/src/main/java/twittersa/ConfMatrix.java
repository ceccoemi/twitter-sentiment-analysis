package twittersa;


import com.aliasi.classify.ConfusionMatrix;


public class ConfMatrix {

    private ConfusionMatrix matrix;

    // it is suggested to be constructed with the method evaluate of the Evaluator class
    ConfMatrix(int truePositive, int falsePositive, int trueNegative, 
            int falseNegative)
    {
        matrix = new ConfusionMatrix(new String[] {"positive", "negative"}, 
                new int[][] {{truePositive, falsePositive}, {falseNegative, trueNegative}});
    }

    public double accuracy()
    {
        return matrix.totalAccuracy();
    }

    @Override
    public String toString()
    {
        return matrix.toString();
    }

}
