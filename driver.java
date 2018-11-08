public class driver {
  public static void main(String[] args) {
    WordSearch p1 = new WordSearch(15,15);
    // Should be 15 by 15 with only underscores
    System.out.println(p1);
    // Should return true and add fortnite to the puzzle
    System.out.println(p1.addWordHorizontal("FORTNITE",1,1));
    System.out.println(p1);
    // Should return false and not change the puzzle
    System.out.println(p1.addWordHorizontal("YEET",1,13));
    System.out.println(p1);
    // Should return true and add victory to the puzzle
    System.out.println(p1.addWordVertical("VICTORY",0,6));
    System.out.println(p1);
    // Should return false and not change the puzzle
    System.out.println(p1.addWordVertical("ROYALE",13,13));
    System.out.println(p1);
    // Doing some cool things
    String[] words = {"YEET", "DAB", "YURR", "TIMBS"};
    for (int i=0;i<words.length;i++) {
      System.out.println(p1.addWordHorizontal(words[i],(int)(Math.random()*14),(int)(Math.random()*14)));
    }
    System.out.println(p1);
    String[] words1 = {"CAR", "NICE", "COOL", "THANOS"};
    for (int i=0;i<words1.length;i++) {
      System.out.println(p1.addWordVertical(words1[i],(int)(Math.random()*14),(int)(Math.random()*14)));
    }
    System.out.println(p1);
    }
  }
  
