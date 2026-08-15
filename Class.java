public class Class {
    private int classID;
    private String className;
    private int attack;
    private int resistance;
    private int hp;
    private int speed;
    private int defense;
    private int critRate;
    private int critDamage;

    public Class(int classID, String className, int attack, int resistance, int hp, int speed, int defense, int critRate, int critDamage) {
        this.classID = classID;
        this.className = className;
        this.attack = attack;
        this.resistance = resistance;
        this.hp = hp;
        this.speed = speed;
        this.defense = defense;
        this.critRate = critRate;
        this.critDamage = critDamage;
    }

    public int getClassID() {
        return this.classID;
    }

    public String getClassName() {
        return this.className;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getResistance() {
        return this.resistance;
    }

    public int getHp() {
        return this.hp;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getCritRate() {
        return this.critRate;
    }

    public int getCritDamage() {
        return this.critDamage;
    }
}