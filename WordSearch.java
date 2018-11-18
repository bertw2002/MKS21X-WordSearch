import java.util.*; //, scanner, arraylist
import java.io.*; //file, filenotfoundexception
public class WordSearch{

    private char[][]data;
    //the random seed used to produce this WordSearch
    private int seed;

    //a random Object to unify your random calls
    private Random randgen;

    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd;

    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded;

    public WordSearch(int rows, int cols, String fileName, int randSeed){
      try {
        if (rows < 0 || cols < 0){
          throw new IllegalArgumentException("bad row or col index");
        }
        seed = randSeed;
        randgen = new Random();
        randgen = new Random(seed);
        data = new char[rows][cols];
        clear();
        wordsToAdd = new ArrayList<>();
        wordsAdded = new ArrayList<>();
        File f = new File(fileName);
        Scanner in = new Scanner(f);
        while(in.hasNext()) {
          wordsToAdd.add(in.next());
        }
        randgen = new Random(randSeed);
        addAllWords();
      }catch(FileNotFoundException e){
        System.out.println(e);
      }
    }
    public WordSearch( int rows, int cols, String fileName){
      try {
        if (rows < 0 || cols < 0){
          throw new IllegalArgumentException("bad row or col index");
        }
        randgen = new Random();
        seed = randgen.nextInt(1001);
        randgen = new Random(seed);
        data = new char[rows][cols];
        clear();
        wordsToAdd = new ArrayList<>();
        wordsAdded = new ArrayList<>();
        File f = new File(fileName);
        Scanner in = new Scanner(f);
        while(in.hasNext()) {
          wordsToAdd.add(in.next());
        }
        addAllWords();
      }catch(FileNotFoundException e){
        System.out.println(e);
      }
    }

    //Both will read in the word text file, then run addAllWords(). Do not fill in random letters after.
/*
    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      for (int x = 0; x < rows; x++){
        for (int y = 0; y< cols;y++){
          data[x][y] = '_';
        }
      }
    }
*/
    private void clear(){
      for (int x = 0; x < data.length; x++){
        for (int y = 0; y < data[x].length;y++){
          data[x][y] = '_';
        }
      }
    }

    public String toString(){
      String var = "";
      for (int x = 0; x < data.length; x++){
        var += "|";
        for (int y = 0; y < data[x].length;y++){
          var+= data[x][y] + " ";
        }
        var += "|";
        var+= "\n";
      }
      var += "Words:";
      if (wordsAdded != null){
        for (int x = 0;x < wordsAdded.size(); x++){
          if (x != wordsAdded.size() - 1){
            var += wordsAdded.get(x);
            var += ", ";
          }else{
            var += wordsAdded.get(x);
            var += " ";
          }
        }
      }
      var += "(seed : " + seed +")";
      return var;
    }
    public void undertoletter(){
      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      for (int x = 0; x< data.length; x++){
        for(int y = 0;y < data[0].length;y ++){
          if (data[x][y] == '_'){
            data[x][y] = alphabet.charAt((int)(Math.random() * 26));
          }
        }
      }
    }

    public boolean addWordHorizontal(String word,int row, int col){
      if (row < 0 || col < 0 || row > data.length){
        return false;
      }
      int check = col + word.length() - 1;
      if (check >= data[row].length){
        return false;
      }
      int og = col;
      for (int x =0; x < word.length();x++){
        if (!(word.charAt(x) == data[row][col] || data[row][col] == '_')){
          return false;
        }
        col ++;
      }
      col = og;
      for (int x =0; x < word.length();x++){
        data[row][col] = word.charAt(x);
        col++;
      }
      return true;
    }

    public boolean addWordVertical(String word,int row, int col){
      if (row < 0 || col < 0 || row > data.length){
        return false;
      }
      int check = row + word.length() - 1;
      if (check >= data.length){
        return false;
      }
      if (col >= data[row].length){
        return false;
      }
      int og = row;
      for (int x =0; x < word.length();x++){
        if (!(word.charAt(x) == data[row][col] || data[row][col] == '_')){
          return false;
        }
        row++;
      }
      row = og;
      for (int x =0; x < word.length();x++){
        data[row][col] = word.charAt(x);
        row++;
      }
      return true;
    }

   public boolean addWordDiagonal(String word,int row, int col){
     if (row < 0 || col < 0 || row > data.length){
       return false;
     }
     int check = col + word.length() - 1;
     if (check >= data[row].length){
       return false;
     }
     int og = col;
     for (int x =0; x < word.length();x++){
       if (!(word.charAt(x) == data[row][col] || data[row][col] == '_')){
         return false;
       }
       col ++;
     }
     col = og;
     if (row < 0 || col < 0 || row > data.length){
       return false;
     }
     int check2 = row + word.length() - 1;
     if (check2 >= data.length){
       return false;
     }
     if (col >= data[row].length){
       return false;
     }
     int og2 = row;
     for (int x =0; x < word.length();x++){
       if (!(word.charAt(x) == data[row][col] || data[row][col] == '_')){
         return false;
       }
       row++;
     }
     row = og2;
     for (int x =0; x < word.length(); x++){
       data[row][col] = word.charAt(x);
       col++;
       row++;
     }
     return true;
   }


   private boolean addWord( String word, int r, int c, int rowIncrement, int colIncrement){ //private
     if (colIncrement == 0 && rowIncrement == 0){
       return false;
     }
     if((r + rowIncrement * word.length()) < 0 || ( r + rowIncrement * word.length()) > data.length){
       return false;
     }
     if((c + word.length() * colIncrement < 0 )|| ( c + word.length() * colIncrement > data[0].length) ){
       return false;
     }
     int ogrow = r;
     int ogcol = c;
     for (int x =0; x<word.length();x++){
       if (data[r][c] != '_'){
         if (data[r][c] != word.charAt(x)){
           return false;
         }
       }
       r += rowIncrement;
       c += colIncrement;
     }
     r = ogrow;
     c = ogcol;
     for (int x =0; x <word.length();x++){
       data[r][c] = word.charAt(x);
       r += rowIncrement;
       c += colIncrement;
     }
     System.out.println(wordsToAdd);
     System.out.println(word);
     wordsToAdd.remove(word);
     System.out.println(wordsToAdd);
     wordsAdded.add(word);
     return true;
   }
   private void addAllWords(){ //private
     int whichword;
     String word;
     int r;
     int c;
     int rowIncrement;
     int colIncrement;
     int fails = 0;

       while (fails < 1000){
         whichword = randgen.nextInt(wordsToAdd.size());
         if (whichword < 0){
           whichword = 0 - whichword;
         }
         word = wordsToAdd.get(whichword);
         rowIncrement = randgen.nextInt(3) - 1;
         colIncrement = randgen.nextInt(3) - 1;
         r = randgen.nextInt(data.length);
         c = randgen.nextInt(data[0].length);
         if (!addWord(word,r,c, rowIncrement, colIncrement)){
           fails ++;
         }
       }
   }
   public static void main(String[] args){
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
