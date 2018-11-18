import java.util.*;

public class driver {
        public static void main(String[] args) {
          String directions = "";
          directions += "You need more than 2 but less than 6 paramaters to make a word Search. \n";
          directions += "The first parameter is number of rows, second is number of columns, third is the filename. \n";
          directions += "You can optionally add two more: fourth being a seed, and fifth being a key to the word Search. You simply have to type key.\n";
          try{
            if (args.length < 3){
              System.out.println(directions);
            }
            else if (args.length > 5){
              System.out.println(directions);
            }
            else if (args.length == 3){
              int rows = Integer.parseInt(args[0]);
              int cols = Integer.parseInt(args[1]);
              String fileName = args[2];
              WordSearch puzzle = new WordSearch(rows, cols, fileName);
              puzzle.undertoletter();
              System.out.println(puzzle);
           	}
            else if (args.length == 4){
              int rows = Integer.parseInt(args[0]);
              int cols = Integer.parseInt(args[1]);
              String fileName = args[2];
              int seed = Integer.parseInt(args[3]);
              WordSearch puzzle = new WordSearch(rows, cols, fileName, seed);
              puzzle.undertoletter();
              System.out.println(puzzle);
            }else if (args[4] == "key"){
              int rows = Integer.parseInt(args[0]);
              int cols = Integer.parseInt(args[1]);
              String fileName = args[2];
              int seed = Integer.parseInt(args[3]);
              WordSearch puzzle = new WordSearch(rows, cols, fileName, seed);
              System.out.println(puzzle);
            }
          }catch(Exception e){
            System.out.println(directions);
          }
        }
}
