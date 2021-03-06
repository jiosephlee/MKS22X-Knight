import java.util.Collections;
import java.util.ArrayList;
public class KnightBoard{

    private int[][] board;
    private final int[][] moves;
    private int[][] optimize;

    public static String toString(int[][] input){
        String output = "";
        for(int i = 0;i < input.length; i++){
            for(int j = 0; j < input[0].length; j++){
                if(input[i][j] == 0){
                    output+= " _";
                } else{
                    output+= " " + input[i][j];
                }
            }
            output += '\n';
        }
        return output;
    }

    public static void insertionSort(int[][] data){
        int orig[];
        for (int x = 1; x < data.length; x++){
            orig = data[x];
            for (int y = x; y > 0 && data[y][0] < data[y-1][0] ; y--){
                data[y] = data[y-1];
                data[y-1] = orig;
            }
        }
    }

    public static void main(String[] args) {
        KnightBoard test = new KnightBoard(5,6);
        System.out.println(toString(test.optimize));
        System.out.println(test);
        System.out.println(test.solve(0,0));
        System.out.println(test);
        test.clear();
        System.out.println(test.countSolutions(0,0));
        System.out.println(test);
        test.makeMoves();
        test.runTest(0);
    }

    public KnightBoard(int startingRows,int startingCols){
        board = new int[startingRows][startingCols];
        optimize = new int[startingRows][startingCols];
        moves = new int[][] {
                {2,1}, {2,-1},
                {-2,1}, {-2,-1},
                {1,2}, {-1,-2},
                {1,-2}, {-1,2}
                };
        makeMoves();
    }

    public void clear(){
        for(int i = 0;i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = 0;
            }
        }
    }

    public String toString(){
        String output = "";
        for(int i = 0;i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 0){
                    output+= " _";
                } else{
                    output+= " " + board[i][j];
                }
            }
            output += '\n';
        }
        return output;
    }

    public boolean solve(int startingRow,int startingCol){
        //check for exceptions
        if(startingCol < 0 || startingRow < 0){
            throw new IllegalArgumentException();
        }
        for (int[] row :board ) {
            for (int num: row){
                if(num != 0 ){
                    throw new IllegalStateException();
                }
            }
        }
        return solveOptH(startingRow,startingCol,1);
    }

    private boolean solveH(int row ,int col, int level){
        //System.out.println(this.debug());
        //place knight on board
        board[row][col] = level;
        //if the knight placed is the last knight, meaning the board is full, then return true
        if (level == board.length * board[0].length) return true;
        //if not, branch out to next possible paths
        for (int[] i : moves){
            //System.out.println("yo");
            //check if the next move is within bounds and then branch out.
            //if that brach works then this branch works as well --> return true
            if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length && board[row + i[0]][col + i[1]] == 0
               && solveH(row + i[0], col + i[1], level + 1)){
                return true;
            }
        }
        //if the branch fails, reset the spot to 0 and move on to other brances
        board[row][col] = 0;
        return false;
    }

    public int countSolutions(int startingRow,int startingCol){
        //check for exceptions
        if(startingCol < 0 || startingRow < 0){
            throw new IllegalArgumentException();
        }
        for (int[] row :board ) {
            for (int num: row){
                if(num != 0 ){
                    throw new IllegalStateException();
                }
            }
        }
        return countH(startingRow, startingCol, 1);
    }

    private int countH(int row ,int col, int level){
        //same logic as solveH but instead of returning true, you keep track of the number of solutions by adding to a count variable
        //System.out.println(this.debug());
        int output = 0;
        board[row][col] = level;
        if (level == board.length * board[0].length){
            output++;
            //System.out.println("here");
        }
        for (int[] i : moves){
            if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length
               && board[row + i[0]][col + i[1]] == 0){
                output += countH(row + i[0], col + i[1], level + 1);
            }
        }
        board[row][col] = 0;
        //System.out.println(output);
        return output;
    }

    private void makeMoves(){
    // loop through each spot of the board, and see how many moves are viable and not out of bounds
    // put that number on a separate board called optimize
        for(int i = 0;i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                int count = 0;
                for (int[] x : moves){
                    if(i + x[0] >= 0 && i + x[0] < board.length && j + x[1] >= 0 && j + x[1] < board[0].length){
                        count++;
                    }
                optimize[i][j] = count;
                }
            }
        }
    }


    private boolean solveOptH(int row ,int col, int level){
        //same logic as solveH but instead you prioritize smaller branches first
        //System.out.println(toString(this.optimize));
        board[row][col] = level;
        if (level == board.length * board[0].length) return true;
        //Add viable moves and their respective optimize number into an array
        ArrayList<ArrayList<Integer>> movesOpt = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < moves.length; i++){
            if(row + moves[i][0] >= 0 && row + moves[i][0] < board.length && col + moves[i][1] >= 0 && col + moves[i][1] < board[0].length && board[row + moves[i][0]][col + moves[i][1]] == 0){
                ArrayList<Integer> add = new ArrayList<Integer>();
                add.add(optimize[row + moves[i][0]][col + moves[i][1]]);
                add.add(moves[i][0]);
                add.add(moves[i][1]);
                movesOpt.add(add);
              }
        }
        //sort moves based on optimization number
        //thank you stackoverflow for inspiring this code snippet
        Collections.sort(movesOpt, (one,two) -> one.get(0).compareTo(two.get(0)));
        //System.out.println(toString(movesOpt));

        for(ArrayList<Integer> i : movesOpt){
            optimize[row + i.get(1)][col + i.get(2)]--;
        }
        for(ArrayList<Integer> i : movesOpt){
            int old = optimize[row + i.get(1)][col + i.get(2)]--;
            optimize[row + i.get(1)][col + i.get(2)] = 0;
            if (solveOptH(row + i.get(1), col + i.get(2), level + 1)){
                return true;
            }
            optimize[row + i.get(1)][col + i.get(2)] = old;
        }
        board[row][col] = 0;
        return false;
    }
    //testcase must be a valid index of your input/output array
    public static void runTest(int i){

      KnightBoard b;
      int[]m =   {4,5,5,5,5};
      int[]n =   {4,5,4,5,5};
      int[]startx = {0,0,0,1,2};
      int[]starty = {0,0,0,1,2};
      int[]answers = {0,304,32,56,64};
      if(i >= 0 ){
        try{
          int correct = answers[i];
          b = new KnightBoard(m[i%m.length],n[i%m.length]);

          int ans  = b.countSolutions(startx[i],starty[i]);

          if(correct==ans){
            System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
          }else{
            System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
          }
        }catch(Exception e){
          System.out.println("FAIL Exception case: "+i);

        }
      }
    }

}
