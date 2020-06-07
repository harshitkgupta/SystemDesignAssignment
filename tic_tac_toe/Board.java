public class Board {
    private int N;
    private int board[][];
    private int rowSum[];
    private int colSum[];
    private int diagSum;
    private int revDiagSum;
    private int winner;
    private int lastPlayer;

    public Board(int N){
        this.N = N;
        rowSum = new int[N];
        colSum = new int[N];
        board = new int[N][N];
    }
    public int[][] getBoard(){
        return this.board;
    }
    public int getLastPlayer(){
        return lastPlayer;
    }

    public int move(int player, int x, int y){
        if(winner != 0){
            throw new IllegalArgumentException("Game is already closed");
        }
        if( x<0 || y<0 || x>=N || y>=N){
            throw new IllegalAccessException("Invalid Position");
        }
        if(board[x][y]!=0){
            throw new IllegalArgumentException("Move is already occupied");
        }

        if(!(player == 1 || player == -1)){
            throw new IllegalAccessException("Invalid Player");
        }
        if(lastPlayer != player){
            lastPlayer = player;
        }
        else {
            throw new IllegalAccessException("Invalid Turn");
        }
        board[x][y]=player;
        rowSum[x]+= player;
        colSum[x]+=player;
        if(x==y)
            diagSum+=player;
        if(x==N-1-y)
            revDiagSum+=player;
        if(Math.abs(rowSum[x])==N || Math.abs(colSum[y])==N || Math.abs(diagSum)==N || Math.abs(revDiagSum)==N){
            winner = player;    
            return player;
        }    
        return 0;    
    }

    public int getWinner(){
        return winner;
    }


}