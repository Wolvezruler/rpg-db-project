public class Player {
    private int playerID;
    private String username;

    public Player(int playerID, String username) {
        this.playerID = playerID;
        this.username = username;
    }

    public int getPlayerID() {
        return this.playerID;
    }

    public String getUsername() {
        return this.username;
    }
}
