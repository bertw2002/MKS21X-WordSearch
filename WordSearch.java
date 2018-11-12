import java.util.*; //random, scanner, arraylist
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

    public WordSearch(int rows, int cols, String fileName, int randSeed) throws FileNotFoundException{
      data = new char[rows][cols];
      File f = new File(fileName);
      Scanner in = new Scanner(fileName);
      while(in.hasNext()) {
        wordsToAdd.add(in.next());
      }
      randgen = new Random(randSeed);
    }
    public WordSearch( int rows, int cols, String fileName)throws FileNotFoundException{
      long randSeed = System.currentTimeMillis();
      data = new char[rows][cols];
      File f = new File(fileName);
      Scanner in = new Scanner(fileName);
      while(in.hasNext()) {
        wordsToAdd.add(in.next());
      }
      randgen = new Random(randSeed);

    }

    //Both will read in the word text file, then run addAllWords(). Do not fill in random letters after.

    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      for (int x = 0; x < rows; x++){
        for (int y = 0; y< cols;y++){
          data[x][y] = '_';
        }
      }
    }

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
      return var;
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


   private boolean addWord( String word, int r, int c, int rowIncrement, int colIncrement){
     if (colIncrement == 0 && rowIncrement == 0){
       return false;
     }
     if((r + rowIncrement * word.length()) < 0 || (r + rowIncrement * word.length()) > data.length){
       return false;
     }
     if(c + word.length() * colIncrement < 0 || c + word.length() * colIncrement > data[0].length ){
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
     wordsToAdd.remove(wordsToAdd.size() - 1);
     wordsAdded.add(word);
     return true;
   }
   private void addAllWords(){
     ArrayList<String>randomword = wordsToAdd;
     int whichword;
     String word;
     int r;
     int c;
     for (int x = 0; x < randomword.size();){//removed x++. idk if still work.
      whichword = randgen.nextInt(randomword.size());
      word = randomword.get(whichword);
      randomword.remove(whichword);
       for (int y = 0; y < data[0].length;y++){
         int rowIncrement = randgen.nextInt(3) - 1;
         int colIncrement = randgen.nextInt(3) - 1;
         r = data.length;
         c = data[0].length;
         if (addWord(word,r,c, rowIncrement, colIncrement)){
           y = data[0].length;
         }
       }
     }
   }
}
