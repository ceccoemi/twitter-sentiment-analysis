package com.ceccoemi.twittersa;

import java.util.Arrays;

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

  @Override
  public String toString() {
    int spanLen = 9;
    return String.join(System.lineSeparator(), Arrays.asList(
        "Total observations: " + n,
        "",
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
        "Accuracy: " + accuracy()));
  }

}
