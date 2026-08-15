public class Character {
    private int characterID;
    private String name;
    private int level;

    public Character(int characterID, String name, int level) {
        this.characterID = characterID;
        this.name = name;
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incrementLevel() {
        this.level++;
    }

    public int getCharacterID() {
        return this.characterID;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

}
