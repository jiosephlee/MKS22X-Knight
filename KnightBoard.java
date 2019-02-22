public class KnightBoard{

    private int[][] board;
    private final int[][] moves;

    public static void main(String[] args) {
        KnightBoard test = new KnightBoard(5,3);
        System.out.println(test);
        System.out.println(test.solve(0,0));
        System.out.println(test);

        System.out.println(test.countSolutions(0,0));
        System.out.println(test);
    }
    public KnightBoard(int startingRows,int startingCols){
        board = new int[startingRows][startingCols];
        moves = new int[][] {
                {2,1}, {2,-1},
                {-2,1}, {-2,-1},
                {1,2}, {-1,-2},
                {1,-2}, {-1,2}
                };
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
    public String debug(){
        String output = "";
        for(int i = 0;i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                output+= " " + board[i][j];
            }
            output += '\n';
        }
        return output;
    }

    public boolean solve(int startingRow,int startingCol){
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
        return solveH(startingRow,startingCol,1);
    }

    private boolean solveH(int row ,int col, int level){
        System.out.println(this.debug());
        if (level == board.length * board[0].length){
            return true;
        }
        board[row][col] = level;
        for (int[] i : moves){
            System.out.println("yo");
            if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length && board[row + i[0]][col + i[1]] == 0
               && solveH(row + i[0], col + i[1], level + 1)){
                return true;
            }
        }
        board[row][col] = 0;
        return false;
    }

    public int countSolutions(int startingRow,int startingCol){
        return countH(startingRow, startingCol, 0);
    }

    private int countH(int row ,int col, int level){
        int output = 0;
        if (level == board.length * board[0].length){
            return 1;
        }
        board[row][col] = level;
        for (int[] i : moves){
            if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length
               && board[row + i[0]][col + i[1]] == 0){
                output+= countH(row + i[0], col + i[1], level + 1);
            }
        }
        board[row][col] = 0;
        return output;
    }
}
