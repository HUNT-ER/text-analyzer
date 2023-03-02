package readability;

public class Index {

  Text text;
  double ARIndex;
  double FKIndex;
  double SMOGIndex;
  double CLIndex;

  double avgReadabilityAge;

  ReadabilityScore readabilityScore;


  public Index(Text text) {
    this.text = text;
    readabilityScore = new ReadabilityScore();
    setARI(text.getNumberChars(), text.getNumberWords(), text.getNumberSentences());
    setFKI(text.getNumberWords(), text.getNumberSentences(), text.getNumberSyllables());
    setSMOG(text.getNumberPolysyllables(), text.getNumberSentences());
    setCLI(text.getNumberWords(), text.getNumberChars(), text.getNumberSentences());
    setAvgReadabilityAge();
  }


  private void setARI(int numberChars, int numberWords, int numberSentences) {
    ARIndex =
        4.71 * numberChars / numberWords + 0.5 * numberWords / numberSentences - 21.43;
  }

  private void setFKI(int numberWords, int numberSentences, int numberSyllables) {
    FKIndex =
        0.39 * numberWords / numberSentences + 11.8 * numberSyllables / numberWords - 15.59;
  }

  private void setSMOG(int numberPolysyllables, int numberSentences) {
    SMOGIndex = 1.043 * Math.sqrt(numberPolysyllables * 30 / numberSentences) + 3.1291;
  }

  private void setCLI(int numberWords, int numberChars, int numberSentences) {
    CLIndex = 0.0588 * (numberChars * 100.0 / numberWords) - 0.296 * (numberSentences * 100.0
        / numberWords) - 15.8;
  }

  public void showARI() {
    System.out.println();
  }

  private int getReadabilityAge(double index) {
    return readabilityScore.getAge((int) Math.ceil(index));
  }

  private void setAvgReadabilityAge() {
    avgReadabilityAge = (getReadabilityAge(ARIndex) + getReadabilityAge(FKIndex) +
        getReadabilityAge(SMOGIndex) + getReadabilityAge(CLIndex)) / 4.00;
  }

  public String showARIndex() {
    return "Automated Readability Index: " + ((int) (ARIndex * 100)) / 100.0 + " about "
        + getReadabilityAge(ARIndex) + "-year-olds).";
  }

  public String showFKIndex() {
    return "Flesch–Kincaid readability tests: " + ((int) (FKIndex * 100)) / 100.0 + " about "
        + getReadabilityAge(FKIndex) + "-year-olds).";
  }

  public String showSMOGIndex() {
    return "Simple Measure of Gobbledygook: " + ((int) (SMOGIndex * 100)) / 100.0 + " about "
        + getReadabilityAge(SMOGIndex) + "-year-olds).";
  }

  public String showCLIndex() {
    return "Coleman–Liau index: " + ((int) (CLIndex * 100)) / 100.0 + " about "
        + getReadabilityAge(CLIndex) + "-year-olds).";
  }

  public String showAvgReadabilityAge() {
    return "This text should be understood in average by "
        + ((int) (avgReadabilityAge * 100)) / 100.0 + "-year-olds.";
  }

  public String showAllIndexes() {
    StringBuilder output = new StringBuilder();
    output.append(showARIndex() + "\n");
    output.append(showFKIndex() + "\n");
    output.append(showSMOGIndex() + "\n");
    output.append(showCLIndex());
    return output.toString();
  }
}
