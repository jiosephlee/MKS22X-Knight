public class KnightBoard{

    private int[][] board;
    private int area;
    private final int[][] moves;
    private static int sum;

    public static void main(String[] args) {
        KnightBoard test = new KnightBoard(5,3);
        System.out.println(test);
        System.out.println(test.solve());
        System.out.println(test);

        System.out.println(test.countSolutions());
        System.out.println(test);
    }
    public KnightBoard(int startingRows,int startingCols){
        board = new int[startingRows][startingCols];
        area = startingRows * startingCols;
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

    public boolean solve(){
        for(int i = 0;i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if (solveH(i, j, 1)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean solveH(int row ,int col, int level){
        if (level == area){
            return true;
        }
        for (int[] i : moves){
            if (board[row][col] == 0){
                board[row][col] = level;
                if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length){
                    return solveH(row + i[0], col + i[1], level + 1);
                }
                if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length){
                    board[row + i[0]][col + i[1]] = 0;
                }
            }
        }
        return false;
    }

    public int countSolutions(){
        int output = 0;
        if (board.length%2 == 0 && board[0].length%2 == 0){
            for (int x = 0;x < board.length/2; x++){
                for (int y = 0;y < board[0].length/2;y++){
                    output += countH(x, y, 1, 0);
                }
            }
        } else{
            for(int i = 0;i < board.length; i++){
                for(int j = 0; j < board[0].length; j++){
                    output+= countH(i, j, 1, 0);
                }
            }
        }
        return output;
    }

    private int countH(int row ,int col, int level, int output){
        for (int[] i : moves){
            if (board[row][col] == 0){
                board[row][col] = level;
                if(row + i[0] >= 0 || row + i[0] < board.length || col + i[1] >= 0 || col + i[1] < board[0].length
                && solveH(row + i[0], col + i[1], level + 1)){
                    output += countH(row + i[0], col + i[1], level + 1, output + 1);
            }
            board[row + i[0]][col + i[1]] = 0;
            }
        }
        return output;
    }
}
