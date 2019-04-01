package com.ceccoemi.twittersa;

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
    String repr = "Total observations: " + n + "\n";
    repr += "\n";
    repr += "                       Actual sentiment   \n";
    repr += "           +-----------------------------+\n";
    repr += "           |     |    Pos    |    Neg    |\n";
    String tpSpan = computeSpan(spanLen, tp);
    String fpSpan = computeSpan(spanLen, fp);
    repr += "           |---- +-----------------------|\n";
    repr += "Predicted  | Pos | " + tpSpan + tp + " | " + fpSpan + fp + " |\n";
    repr += "sentiment  |---- +-----------------------|\n";
    String fnSpan = computeSpan(spanLen, fn);
    String tnSpan = computeSpan(spanLen, tn);
    repr += "           | Neg | " + fnSpan + fn + " | " + tnSpan + tn + " |\n";
    repr += "           +-----------------------------+\n";
    repr += "\n";
    repr += "Accuracy: " + accuracy();
    return repr;
  }

  private String computeSpan(int availableSpace, int n) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < availableSpace - String.valueOf(n).length(); i++)
      builder.append(" ");
    return builder.toString();
  }

}
