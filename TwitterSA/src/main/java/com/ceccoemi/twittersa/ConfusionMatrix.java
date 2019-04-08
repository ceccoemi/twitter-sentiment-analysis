package com.ceccoemi.twittersa;

import static org.apache.commons.lang3.StringUtils.center;

public class ConfusionMatrix {

  private int n;
  private int tp;
  private int fp;
  private int fn;
  private int tn;

  public ConfusionMatrix(int tp, int fp, int fn, int tn) {
    this.n = tp + fp + fn + tn;
    this.tp = tp;
    this.fp = fp;
    this.fn = fn;
    this.tn = tn;
  }

  public float accuracy() {
    return (float) (tp + tn) / n;
  }

  public static ConfusionMatrix join(ConfusionMatrix ...  matrices) {
    int truePositives = 0;
    int falsePositives = 0;
    int falseNegatives = 0;
    int trueNegatives = 0;
    for (ConfusionMatrix matrix : matrices) {
      truePositives += matrix.tp;
      falsePositives += matrix.fp;
      falseNegatives += matrix.fn;
      trueNegatives += matrix.tn;
    }
    return new ConfusionMatrix(truePositives, falsePositives, falseNegatives, trueNegatives);
  }

  @Override
  public String toString() {
    int spanLen = 9;
    return String.join(System.lineSeparator(),
        "                       Actual sentiment   ",
        "           +-----------------------------+",
        "           |     |    Pos    |    Neg    |",
        "           |---- +-----------------------|",
        "Predicted  | Pos | " + center(String.valueOf(tp), spanLen, ' ') + " | "
            + center(String.valueOf(fp), spanLen, ' ') + " |",
        "sentiment  |---- +-----------------------|",
        "           | Neg | " + center(String.valueOf(fn), spanLen, ' ') + " | "
            + center(String.valueOf(tn), spanLen, ' ') + " |",
        "           +-----------------------------+",
        "",
        "Observations: " + n,
        "Accuracy: " + accuracy());
  }

}
