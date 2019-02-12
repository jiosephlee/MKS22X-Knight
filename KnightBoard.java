public class KnightBoard{

    private int[][] board;

    public KnightBoard(int startingRows,int startingCols){
        board = new int[startingRows][startingCols];
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

    public boolean solve(int startingRow, int startingCol){
        return solveH(startingRow, startingCol, 0);
    }

    public int countSolutions(int startingRow, int startingCol){

    }

    private boolean solveH(int row ,int col, int level){

    }
}
