package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    StringBuilder rawText = new StringBuilder();
    try (Scanner scanner = new Scanner(new File(args[0]))) {
      while (scanner.hasNextLine()) {
        rawText.append(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    Text text = new Text(rawText.toString());

    text.showTextInfo();
  }
}
