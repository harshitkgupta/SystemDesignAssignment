public class Game {
    private String gameid;
    private int user_id1;
    private int user_id2;
    public Game(int user_id, int user_id2){
        gameid = new UUID();
        this.user_id1=user_id1;
        this.user_id2=user_id2;
    }

    public List<Move> getMoves() {}

    public void undo(){}
}