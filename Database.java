import java.sql.*;
import java.util.ArrayList;

public class Database {
    private String url = "jdbc:mysql://127.0.0.1:3306/"; // URL for server
    private String dbName = "cs364_rpg_proj"; // Database name on the server
    private String username = "root"; // Username on the server
    private String password; // Password on the server, set in constructor for privacy
    private Connection connection; // Connection object for running queries

    /**
     * Constructor for the Database class.
     * Set the password for the user in this function.
     */
    public Database(String pw) {
        password = pw;
    }

    /**
     * Construct the url to connect to the database, and establish a connection.
     */
    public void connect() {
        try {
            url = url + dbName + "?";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Cannot connect!");
            System.out.println(e);
            System.exit(1);
        }
    }

    /**
     * Closes the connection with the database.
     */
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Cannot disconnect!");
        }
    }

    /**
     * Runs a query with no parameters using prepared statements.
     * @param query : the query to run
     * @return the result set from the database
     * @throws SQLException
     */
    public ResultSet runQuery(String query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet results = stmt.executeQuery();
        return results;
    }

    /**
     * Inserts a new Character into the database.
     *
     * @param c The new Character to be inserted into the database
     */
    public String insertCharacter(Character c) throws SQLException {
        String query = "INSERT INTO `Character`(CharacterID, Name, Level) VALUES (?, ?, 1);";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, c.getCharacterID());
        stmt.setString(2, c.getName());

        int numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 0) {
            return "<html>No Character was created<html>";
        } else if (numRowsAffected == 1) {
            return "<html>1 Character was created<html>";
        } else {
            return "<html>" + numRowsAffected + " Characters were created<html>";
        }
    }

    /**
     * Creates a new relationship between a Player and a Character in the PLAYS table.
     *
     * @param playerID The PlayerID
     * @param characterID The CharacterID
     * @throws SQLException
     */
    public String insertPlays(int playerID, int characterID) throws SQLException {
        String query = "INSERT INTO PLAYS(PlayerID, CharacterID) VALUES (?, ?);";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, playerID);
        stmt.setInt(2, characterID);

        int numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 0) {
            return "<html>No Player was assigned to this Character<html>";
        } else if (numRowsAffected == 1) {
            return "<html>1 Player was assigned to this Character<html>";
        } else {
            return "<html>" + numRowsAffected + " Players were assigned to this Character<html>";
        }
    }

    /**
     * Creates a new relationship between a Character and a Skill in the HAS table.
     *
     * @param characterID The CharacterID
     * @param skillID The SkillID
     * @throws SQLException
     */
    public String insertHas(int characterID, int skillID) throws SQLException {
        String query = "INSERT INTO HAS(CharacterID, SkillID) VALUES (?, ?);";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, characterID);
        stmt.setInt(2, skillID);

        int numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 0) {
            return "<html>No Skill was assigned to this Character<html>";
        } else if (numRowsAffected == 1) {
            return "<html>1 Skill was assigned to this Character<html>";
        } else {
            return "<html>" + numRowsAffected + " Skills were assigned to this Character<html>";
        }
    }

    /**
     * Creates a new relationship between a Character and an Item in the USES table.
     *
     * @param characterID The CharacterID
     * @param itemID The ItemID
     * @throws SQLException
     */
    public String insertUses(int characterID, int itemID) throws SQLException {
        String query = "INSERT INTO USES(CharacterID, ItemID) VALUES (?, ?);";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, characterID);
        stmt.setInt(2, itemID);

        int numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 0) {
            return "<html>No Item was assigned to this Character<html>";
        } else if (numRowsAffected == 1) {
            return "<html>1 Item was assigned to this Character<html>";
        } else {
            return "<html>" + numRowsAffected +  " Items were assigned to this Character<html>";
        }
    }

    /**
     * Creates a new relationship between a Character and a Class in the IS table.
     *
     * @param characterID The CharacterID
     * @param classID The ClassID
     * @throws SQLException
     */
    public String insertIs(int characterID, int classID) throws SQLException {
        String query = "INSERT INTO `IS`(CharacterID, ClassID) VALUES (?, ?);";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, characterID);
        stmt.setInt(2, classID);

        int numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 0) {
            return "<html>No Class was assigned to this Character<html>";
        } else if (numRowsAffected == 1) {
            return "<html>1 Class was assigned to this Character<html>";
        } else {
            return "<html>" + numRowsAffected + " Classes were assigned to this Character<html>";
        }
    }

    /**
     * Deletes a Character from the Character table.
     *
     * @param characterID The Character ID for the Character to delete
     */
    public String deleteCharacter(int characterID) throws SQLException {
        String query = "DELETE FROM `Character` WHERE CharacterID = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, characterID);

        int numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 0) {
            return "<html>No Character was deleted<html>";
        } else if (numRowsAffected == 1) {
            return "<html>1 Character was deleted<html>";
        } else {
            return "<html>" + numRowsAffected + " Characters were deleted<html>";
        }
    }

    /**
     * Deletes the association between a Player and Character from the PLAYS table.
     *
     * @param characterID The Character ID for the association to delete
     */
    public String deletePlays(int characterID) throws SQLException {
        String query = "DELETE FROM PLAYS WHERE CharacterID = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, characterID);

        int numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 0) {
            return "<html>Character " + characterID + " was not played by a Player<html>";
        } else if (numRowsAffected == 1) {
            return "<html>Character " + characterID + " was played by 1 Player<html>";
        } else {
            return "<html>Character " + characterID + " was played by " + numRowsAffected + " Players<html>";
        }
    }

    /**
     * Deletes the association(s) between a Character and their Skill(s) from the HAS table.
     *
     * @param characterID The Character ID for the association(s) to delete
     */
    public String deleteHas(int characterID) throws SQLException {
        String query = "DELETE FROM HAS WHERE CharacterID = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, characterID);

        int numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 0) {
            return "<html>Character " + characterID + " did not have any Skills<html>";
        } else if (numRowsAffected == 1) {
            return "<html>Character " + characterID + " had 1 Skill<html>";
        } else {
            return "<html>Character " + characterID + " had " + numRowsAffected + " Skills<html>";
        }
    }

    /**
     * Deletes the association(s) between a Character and their Item(s) from the USES table.
     *
     * @param characterID The Character ID for the association(s) to delete
     */
    public String deleteUses(int characterID) throws SQLException {
        String query = "DELETE FROM USES WHERE CharacterID = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, characterID);

        int numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 0) {
            return "<html>Character " + characterID + " did not have any Items<html>";
        } else if (numRowsAffected == 1) {
            return "<html>Character " + characterID + " had 1 Item<html>";
        } else {
            return "<html>Character " + characterID + " had " + numRowsAffected + " Items<html>";
        }
    }

    /**
     * Deletes the association between a Character and their Class from the IS table.
     *
     * @param characterID The Character ID for the association to delete
     */
    public String deleteIs(int characterID) throws SQLException {
        String query = "DELETE FROM `IS` WHERE CharacterID = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, characterID);

        int numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 0) {
            return "<html>Character " + characterID + " did not have a Class<html>";
        } else if (numRowsAffected == 1) {
            return "<html>Character " + characterID + " had 1 Class<html>";
        } else {
            return "<html>Character " + characterID + " had " + numRowsAffected + " Classes<html>";
        }
    }

    //Read part of CRUD
    public ResultSet displayTable(String tableName) throws SQLException {
        String query = "SELECT * FROM `" + tableName + "`";

        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet results = stmt.executeQuery();

        return results;
    }

    // Change a Player's username
    public int updateUsername(int playerID, String newUsername) throws SQLException {
        String query = "UPDATE Player SET Username = ? WHERE PlayerID = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, newUsername); // value to set
        stmt.setInt(2, playerID);    // value to match

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected;
    }

    /**
     * Change a Character's name.
     *
     * @param characterID The ID of the Character whose name we want to change
     * @param newName The new name of the Character
     * @return
     */
    public int updateCharacterName(int characterID, String newName) throws SQLException {
        String query = "UPDATE `Character` SET Name = ? WHERE CharacterID = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, newName);
        stmt.setInt(2, characterID);

        int numRowsAffected = stmt.executeUpdate();
        return numRowsAffected;
    }

    public int incrementCharacterLevel(int characterID) throws SQLException {
        String query = "UPDATE `Character` SET Level = Level + 1 WHERE CharacterID = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, characterID);    // value to match

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected;
    }

    public int characterClassChange(int characterID, int newClassID) throws SQLException {
        String query = "UPDATE `IS`\n" +
                "SET ClassID = ?\n" +
                "WHERE CharacterID = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, newClassID);
        stmt.setInt(2, characterID);// value to match

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected;
    }

    /**
     * Compares two class's attributes.
     * @param name1 The name of the first class to compare
     * @param name2 The name of the second class to compare
     * @return The result set from the database
     * @throws SQLException
     */
    public ResultSet classComparison(String name1, String name2) throws SQLException {
        String query = "SELECT oc.Attack AS AttackC1, C2Attr.Attack AS AttackC2, C2Attr.Attack - oc.Attack AS AttackComp, " +
                "oc.Resistance AS ResistanceC1, C2Attr.Resistance AS ResistanceC2, C2Attr.Resistance - oc.Resistance AS ResistanceComp, " +
                "oc.HP AS HPC1, C2Attr.HP AS HPC2, C2Attr.HP - oc.HP AS HPComp, oc.Speed AS SpeedC1, C2Attr.Speed AS SpeedC2, C2Attr.Speed - oc.Speed AS SpeedComp, " +
                "oc.Defense AS DefenseC1, C2Attr.Defense AS DefenseC2, C2Attr.Defense - oc.Defense AS DefenseComp, " +
                "oc.CritRate AS CritRateC1, C2Attr.CritRate AS CritRateC2, C2Attr.CritRate - oc.CritRate AS CritRateComp, " +
                "oc.CritDamage AS CritDamageC1, C2Attr.CritDamage AS CritDamageC2, C2Attr.CritDamage - oc.CritDamage AS CritDamageComp " +
                "FROM (SELECT ic.Attack, ic.Resistance, ic.HP, ic.Speed, ic.Defense, ic.CritRate, ic.CritDamage " +
                "FROM Class AS ic WHERE ClassName = ?) AS C2Attr " +
                "JOIN Class AS oc WHERE ClassName = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, name2);
        stmt.setString(2, name1);

        ResultSet results = stmt.executeQuery();

        return results;
    }

    /**
     * Get the mean level for each player and total number of characters played by each player.
     * Results are ordered by highest to lowest mean level, then highest to lowest total number of characters, then alphabetically by player username.
     * @param limit The total amount of rows to be returned
     * @return The result set from the database
     * @throws SQLException
     */
    public ResultSet meanLvlLeaderboard(int limit) throws SQLException {
        String query = "SELECT Player.Username, sum(`Character`.Level) / count(*) AS MeanLvl, count(*) AS TotalChars " +
                "FROM Player NATURAL JOIN PLAYS NATURAL JOIN `Character` GROUP BY Player.PlayerID, Player.Username " +
                "ORDER BY sum(`Character`.Level) / count(*) DESC, count(*) DESC, Player.Username ASC LIMIT ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, limit);

        ResultSet results = stmt.executeQuery();

        return results;
    }

    /**
     * List all player usernames, the number of characters they play, and the total number of items and skills they have between all of their characters.
     * Order alphabetically by player username.
     * If a username is provided, then only that username will be returned.
     * @param username The username of a player, or "" if none is provided
     * @return The result set from the database
     * @throws SQLException
     */
    public ResultSet summary(String username) throws SQLException {
        String query = "";
        if (username == "") { // If there was no user input / specific search
            query = "SELECT Username, TotalCharsPerPlayer.TotalChars, sum(TotalSkillsPerChar.TotalSkills) AS TotalSkills, sum(TotalItemsPerChar.TotalItems) AS TotalItems " +
                    "FROM (SELECT PlayerID, count(*) AS TotalChars FROM PLAYS GROUP BY PlayerID) AS TotalCharsPerPlayer " +
                    "NATURAL JOIN Player NATURAL JOIN PLAYS NATURAL JOIN `Character` " +
                    "NATURAL JOIN (SELECT CharacterID, count(*) AS TotalSkills FROM HAS GROUP BY CharacterID) AS TotalSkillsPerChar " +
                    "NATURAL JOIN (SELECT CharacterID, count(*) AS TotalItems FROM USES GROUP BY CharacterID) AS TotalItemsPerChar " +
                    "GROUP BY PlayerID, TotalChars, Username ORDER BY Username ASC";
        } else {
            query = "SELECT Username, TotalCharsPerPlayer.TotalChars, sum(TotalSkillsPerChar.TotalSkills) AS TotalSkills, sum(TotalItemsPerChar.TotalItems) AS TotalItems " +
                    "FROM (SELECT PlayerID, count(*) AS TotalChars FROM PLAYS GROUP BY PlayerID) AS TotalCharsPerPlayer " +
                    "NATURAL JOIN Player NATURAL JOIN PLAYS NATURAL JOIN `Character` " +
                    "NATURAL JOIN (SELECT CharacterID, count(*) AS TotalSkills FROM HAS GROUP BY CharacterID) AS TotalSkillsPerChar " +
                    "NATURAL JOIN (SELECT CharacterID, count(*) AS TotalItems FROM USES GROUP BY CharacterID) AS TotalItemsPerChar " +
                    "WHERE Username = ? " +
                    "GROUP BY PlayerID, TotalChars, Username ORDER BY Username ASC";
        }

        PreparedStatement stmt = connection.prepareStatement(query);
        if (username != "") {
            stmt.setString(1, username);
        }

        ResultSet results = stmt.executeQuery();

        return results;
    }

    /**
     * List the player with the most items owned, then list all of the items that they own as well as their item stats.
     * @return The result set from the database
     * @throws SQLException
     */
    public ResultSet playerWithMostItems() throws SQLException {
        String query = "SELECT  c.Name, i.ItemName, i.ItemClassification, i.ItemValue, i.ItemType, i.ItemDurability " +
                "FROM `Character` AS c JOIN USES AS u ON c.CharacterID = u.CharacterID " +
                "JOIN Item AS i ON u.ItemID = i.ItemID " +
                "WHERE c.CharacterID = (SELECT CharacterID " +
                "FROM USES " +
                "GROUP BY CharacterID " +
                "ORDER BY COUNT(*) DESC " +
                "LIMIT 1) " +
                "ORDER BY i.ItemID";

        PreparedStatement stmt = connection.prepareStatement(query);

        ResultSet results = stmt.executeQuery();

        return results;
    }

    /**
     * Gets the most used skill of a class
     * @param className The name of the class to get the skills used by those in the clas
     * @return The result set from the database
     * @throws SQLException
     */
    public ResultSet classesMostUsedSkill(String className) throws SQLException {
        String query = "SELECT Skill.SkillID, Skill.SkillName, Skill.SkillClassification, Skill.SkillValue, Skill.SkillType, Skill.SkillCooldown " +
                "FROM Skill natural join HAS natural join `Character` natural join `IS` natural join Class " +
                "WHERE Class.ClassName LIKE ? " +
                "GROUP BY Skill.SkillID " +
                "ORDER BY count(*) desc " +
                "LIMIT 1";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, className);

        ResultSet results = stmt.executeQuery();

        return results;
    }

    /**
     * Lists the top 10 damage dealing items of the classification weapon
     * @return The result set from the database
     * @throws SQLException
     */
    public ResultSet weaponList() throws SQLException {
        String query = "SELECT ItemName, ItemClassification, ItemValue, Itemtype, ItemDurability " +
                "FROM Item " +
                "WHERE ItemClassification LIKE 'Weapon' " +
                "ORDER BY ItemValue DESC " +
                "LIMIT 10";

        PreparedStatement stmt = connection.prepareStatement(query);

        ResultSet results = stmt.executeQuery();

        return results;
    }

    /**
     * Finds the next unused CharacterID in the database to assign to a new character.
     * @return An unused CharacterID
     * @throws SQLException
     */
    public int generateCharacterID() throws SQLException {
        int highestID = -1;
        String query = "SELECT CharacterID FROM `Character`";

        PreparedStatement stmt = connection.prepareStatement(query);

        ResultSet results = stmt.executeQuery();

        // All of the current CharacterIDs assigned to characters
        ArrayList<Integer> ids = new ArrayList<>(500);

        while (results.next()) {
            int characterID = results.getInt("CharacterID");

            if (characterID > highestID) {
                highestID = characterID;
            }
        }

        // Return the next unused CharacterID
        return highestID + 1;
    }

    /**
     * Finds the highest PlayerID.
     * @return Highest PlayerID
     * @throws SQLException
     */
    public int getHighestPlayerID() throws SQLException {
        int highestID = -1;
        String query = "SELECT PlayerID FROM Player";

        PreparedStatement stmt = connection.prepareStatement(query);

        ResultSet results = stmt.executeQuery();

        // All of the current PlayerIDs assigned to players
        ArrayList<Integer> ids = new ArrayList<>(200);

        while (results.next()) {
            int playerID = results.getInt("PlayerID");

            if (playerID > highestID) {
                highestID = playerID;
            }
        }

        // Return the highest PlayerID
        return highestID;
    }
}