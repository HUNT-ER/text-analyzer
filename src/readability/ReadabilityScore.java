package readability;

import java.util.HashMap;
import java.util.Map;

public class ReadabilityScore {
  public ReadabilityScore() {

    score = new HashMap<>();
    score.put(1, 6);
    score.put(2, 7);
    score.put(3, 8);
    score.put(4, 9);
    score.put(5, 10);
    score.put(6, 11);
    score.put(7, 12);
    score.put(8, 13);
    score.put(9, 14);
    score.put(10, 15);
    score.put(11, 16);
    score.put(12, 17);
    score.put(13, 18);
    score.put(14, 22);
  }
  private Map<Integer, Integer> score;

  public int getAge(int score) {
    if (!this.score.containsKey(score)) {
      return 1;
    }
    return this.score.get(score);
  }
}
