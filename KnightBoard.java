public class KnightBoard{

    private int[][] board;
    private int area;
    private final int[][] moves;
    private static int sum;

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

    public boolean solve(int startingRow,int startingCol){
        return solveH(startingRow,startingCol,0);
    }

    private boolean solveH(int row ,int col, int level){
        if (level == area){
            return true;
        }
        if (board[row][col] == 0){
            board[row][col] = level;
            for (int[] i : moves){
                if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length){
                    return solveH(row + i[0], col + i[1], level + 1);
                }
                if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length){
                    board[row + i[0]][col + i[1]] = 0;
                }
            }
            board[row][col] = 0;
        }
        return false;
    }

    public int countSolutions(int startingRow,int startingCol){
        return countH(startingRow, startingCol, 0, 0);
    }

    private int countH(int row ,int col, int level, int output){
        int output = 0;
        if (level == area){
            return 1;
        }
        if (board[row][col] == 0){
            board[row][col] = level;
            for (int[] i : moves){
                if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length){
                    output+= countH(row + i[0], col + i[1], level + 1);
                }
                if(row + i[0] >= 0 && row + i[0] < board.length && col + i[1] >= 0 && col + i[1] < board[0].length){
                    board[row + i[0]][col + i[1]] = 0;
                }
            }
            board[row][col] = 0;
        }
        return 0;
    }
}
