package readability;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Text {

  private final String text;
  private int numberChars;
  private int numberWords;
  private int numberSentences;
  private int numberSyllables;
  private int numberPolysyllables;
  private int avgCharsNumber;
  private int avgSentences;
  Index indexes;

  public Text(String text) {
    this.text = text;
    analyzeText();
    indexes = new Index(this);
  }

  private void countWords() {
    String pattern = "\\s+";
    numberWords = text.split(pattern).length;
    avgCharsNumber = numberWords / 100;
  }

  private void countSentences() {
    String pattern = "[.?!]";
    numberSentences = text.split(pattern).length;
    avgSentences = numberSentences / 100;
  }

  private void countChars() {
    numberChars = (int) text.chars().filter(ch -> ch != ' ').count();
  }

  private void countSyllables() {
    //разбиваем предложение на слова
    String[] sentence = text.toLowerCase().split("\\s+");
    //определяем гласные
    Set<Character> sd = "aeiouy".chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
    int numberSyllablesInSentence = 0;
    for (String s : sentence) {
      //количество слогов
      int numberSyllablesInWord = 0;
      //разбиваем слово на буквы и анализируем побуквенно
      char[] word = s.toCharArray();
      char previousChar = '0';
      for (int j = 0; j < word.length; j++) {
        //проверяем, если последняя 'e', пропускаем
        //если две гласных подряд - пропускаем
        //иначе добавляем значение к количеству слогов
        char currentChar = word[j];
        if (j == word.length - 1 & currentChar == 'e') {
          break;
        } else if (!Character.isAlphabetic(currentChar) & previousChar == 'e') {
          numberSyllablesInWord--;
          break;
        } else if (sd.contains(currentChar) & sd.contains(previousChar)) {
          previousChar = currentChar;
          continue;
        } else if (sd.contains(currentChar)) {
          numberSyllablesInWord++;
        }
        previousChar = currentChar;
      }
      //если в слове нет гласных, то считаем одним слогом
      if (numberSyllablesInWord == 0) {
        numberSyllablesInWord++;
      } else if (numberSyllablesInWord > 2) {
        numberPolysyllables++;
      }
      numberSyllablesInSentence += numberSyllablesInWord;
      //System.out.println(sentence[i] + " " + numberSyllablesInWord); //debug
    }
    numberSyllables = numberSyllablesInSentence;
  }

  private void analyzeText() {
    countWords();
    countSentences();
    countChars();
    countSyllables();
  }

  public int getNumberChars() {
    return numberChars;
  }

  public int getNumberSentences() {
    return numberSentences;
  }

  public int getNumberSyllables() {
    return numberSyllables;
  }

  public int getNumberPolysyllables() {
    return numberPolysyllables;
  }

  public int getNumberWords() {
    return numberWords;
  }

  public void showTextInfo() {

    System.out.println(this);
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
    StringBuilder output = new StringBuilder();
    String input = scanner.nextLine().trim().toLowerCase();
    switch (input) {
      case "ari":
        output.append("\n").append(indexes.showARIndex());
        break;
      case "fk":
        output.append("\n" + indexes.showFKIndex());
        break;
      case "smog":
        output.append("\n" + indexes.showSMOGIndex());
        break;
      case "cl":
        output.append("\n" + indexes.showCLIndex());
        break;
      default:
        output.append("\n" + indexes.showAllIndexes());
        break;
    }
    output.append("\n\n" + indexes.showAvgReadabilityAge());
    System.out.println(output);
  }


  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("The text is:\n" + text + "\n\n");
    result.append("Words: " + numberWords + "\n");
    result.append("Sentences: " + numberSentences + "\n");
    result.append("Characters: " + numberChars + "\n");
    result.append("Syllables: " + numberSyllables + "\n");
    result.append("Polysyllables: " + numberPolysyllables);

    return result.toString();
  }


}
