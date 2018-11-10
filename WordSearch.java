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
      try{
        data = new char[rows][cols];
        Scanner s = new Scanner(fileName);
        while(s.hasNext()) {
          wordsToAdd.add(s.next());
        }
        randgen = new Random(randSeed);
        seed = randSeed;
      } catch (FileNotFoundException e){
        e.printStackTrace();
        System.out.println("file" + fileName + "not found");

      }
    }
    public WordSearch( int rows, int cols, String fileName){

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


   private boolean addWord( int row, int col, String word, int rowIncrement, int colIncrement){
     if (colIncrement == 0 && rowIncrement == 0){
       return false;
     }
     if((row + rowIncrement * word.length()) < 0 || (row + rowIncrement * word.length()) > data.length){
       return false;
     }
     if(col + word.length() * colIncrement < 0 || col + word.length() * colIncrement > data[0].length ){
       return false;
     }
     int ogrow = row;
     int ogcol = col;
     for (int x =0; x<word.length();x++){
       if (data[row][col] != '_'){
         if (data[row][col] != word.charAt(x)){
           return false;
         }
       }
       row += rowIncrement;
       col += colIncrement;
     }
     row = ogrow;
     col = ogcol;
     for (int x =0; x <word.length();x++){
       data[row][col] = word.charAt(x);
       row += rowIncrement;
       col += colIncrement;
     }
     wordsToAdd.remove(wordsToAdd.size() - 1);
     wordsAdded.add(word);
     return true;
   }
}
