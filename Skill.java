public class Skill {
    private int skillID;
    private String skillName;
    private String skillClassification;
    private int skillValue;
    private String skillType;
    private int skillCooldown;

    public Skill(int skillID, String skillName, String skillClassification, int skillValue, String skillType, int skillCooldown) {
        this.skillID = skillID;
        this.skillName = skillName;
        this.skillClassification = skillClassification;
        this.skillValue = skillValue;
        this.skillType = skillType;
        this.skillCooldown = skillCooldown;
    }

    public int getSkillID() {
        return this.skillID;
    }

    public String getSkillName() {
        return this.skillName;
    }

    public String getSkillClassification() {
        return this.skillClassification;
    }

    public int getSkillValue() {
        return this.skillValue;
    }

    public String getSkillType() {
        return this.skillType;
    }

    public int getSkillCooldown() {
        return this.skillCooldown;
    }
}
