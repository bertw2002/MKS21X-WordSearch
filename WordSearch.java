public class WordSearch{
    private char[][]data;

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
        for (int y = 0; y < data[x].length;y++){
          var+= data[x][y] + " ";
        }
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
      for (int x =0; x < word.length();x++){
        if (word.charAt(x) == data[row][col] || data[row][col] == '_'){
          data[row][col] = word.charAt(x);
        }else{
          return false;
        }
        col ++;
      }
      return true;
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
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
      for (int x =0; x < word.length();x++){
        if (word.charAt(x) == data[row][col] || data[row][col] == '_'){
          data[row][col] = word.charAt(x);
        }else{
          return false;
        }
        row++;
      }
      return true;
    }
}
