import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Desktop {
    private Database db;

    private JFrame frame;

    private CardLayout card;
    private GridBagLayout grid;

    private JPanel defaultPanel;

    private JPanel homePanel;
    private JButton cudButton;
    private JButton queryButton;
    private JLabel welcome;
    private JLabel cudDesc;
    private JLabel queryDesc;

    private JPanel cudPanel;
    private JButton cudBack;
    private JComboBox cudMenu;
    private JButton cudExecute;
    private JLabel cudInstructions;
    private JTextField cudInput;
    private JButton cudEnter;
    private JLabel cudResult;

    private JPanel queryPanel;
    private JButton queryBack;
    private JComboBox queryMenu;
    private JButton queryExecute;
    private JLabel queryInstructions;
    private JTextField queryInput;
    private JButton queryEnter;
    private JTextArea queryResultArea;
    private JScrollPane queryScrollPane;

    public Desktop(Database database) {
        this.db = database;

        // Layouts
        grid = new GridBagLayout();
        card = new CardLayout();

        // Frame -------------------------------------------------------------------------------------------------------
        frame = new JFrame();
        frame.setSize(960, 540);
        frame.setTitle("CS364 Database Project");

        // Default panel -----------------------------------------------------------------------------------------------
        defaultPanel = new JPanel();
        defaultPanel.setLayout(card);

        // Home panel --------------------------------------------------------------------------------------------------
        homePanel = new JPanel();
        homePanel.setLayout(grid);
        GridBagConstraints homeC = new GridBagConstraints();

        welcome = new JLabel("<html>Welcome to the Shadow Raid Legends RPG database!<html>", SwingConstants.CENTER);
        welcome.setFont(new Font("Serif", Font.BOLD, 38));
        homeC.fill = GridBagConstraints.HORIZONTAL;
        homeC.weightx = 1;
        homeC.gridx = 0;
        homeC.gridy = 0;
        homeC.gridwidth = 3;
        homeC.anchor = GridBagConstraints.NORTH;
        homeC.insets= new Insets(50, 0, 100, 0);
        homePanel.add(welcome, homeC);

        cudButton = new JButton("Manage Account");
        cudButton.setFont(new Font("Serif", Font.PLAIN, 24));
        homeC.ipady = 20;
        homeC.weightx = 0.5;
        homeC.gridx = 0;
        homeC.gridy = 1;
        homeC.gridwidth = 1;
        homeC.anchor = GridBagConstraints.CENTER;
        homeC.insets = new Insets(0, 50, 0, 50);
        homePanel.add(cudButton, homeC);

        queryButton = new JButton("Perform Queries");
        queryButton.setFont(new Font("Serif", Font.PLAIN, 24));
        homeC.weightx = 0.5;
        homeC.gridx = 2;
        homeC.gridy = 1;
        homeC.gridwidth = 1;
        homePanel.add(queryButton, homeC);

        cudDesc = new JLabel("<html>Here you can:<br>- Create a new character<br>- Delete a character<br>- Update your username<br>- Update a character's name<br>- Update a character's class<br>- Increase a character's level by 1<html>");
        cudDesc.setFont(new Font("Serif", Font.PLAIN, 22));
        homeC.ipady = 0;
        homeC.gridx = 0;
        homeC.gridy = 2;
        homeC.anchor = GridBagConstraints.NORTH;
        homeC.insets = new Insets(10, 50, 0, 50);
        homePanel.add(cudDesc, homeC);

        queryDesc = new JLabel("<html>Here you can:<br>- Display tables<br>- Display a leaderboard of mean levels<br>- Compare classes<br>- Summarize player stats<br>- View the top 10 damage weapons<br>- View the player with the most items<br>- View the skill used most by a specific class<html>");
        queryDesc.setFont(new Font("Serif", Font.PLAIN, 22));
        homeC.gridx = 2;
        homeC.gridy = 2;
        homePanel.add(queryDesc, homeC);

        // CUD/Account panel -------------------------------------------------------------------------------------------
        cudPanel = new JPanel();
        cudPanel.setLayout(grid);
        GridBagConstraints cudC = new GridBagConstraints();

        cudBack = new JButton("Back");
        cudBack.setFont(new Font("Serif", Font.PLAIN, 20));
        cudC.fill = GridBagConstraints.HORIZONTAL;
        cudC.gridx = 0;
        cudC.gridy = 0;
        cudC.gridwidth = 1;
        cudC.anchor = GridBagConstraints.NORTH;
        cudC.insets = new Insets(20, 20, 0, 20);
        cudPanel.add(cudBack, cudC);

        cudMenu = new JComboBox();
        cudMenu.setFont(new Font("Serif", Font.PLAIN, 20));
        cudMenu.addItem("");
        cudMenu.addItem("Create a new character");
        cudMenu.addItem("Delete a character");
        cudMenu.addItem("Update your username");
        cudMenu.addItem("Update a character's name");
        cudMenu.addItem("Update a character's class");
        cudMenu.addItem("Increase a character's level by 1");
        cudC.weightx = 1;
        cudC.gridx = 1;
        cudC.gridy = 0;
        cudC.insets = new Insets(20, 0, 0, 20);
        cudPanel.add(cudMenu, cudC);

        cudExecute = new JButton("Execute");
        cudExecute.setFont(new Font("Serif", Font.PLAIN, 20));
        cudC.fill = GridBagConstraints.NONE;
        cudC.ipadx = 0;
        cudC.gridx = 2;
        cudC.gridy = 0;
        cudC.weightx = 0;
        cudC.insets = new Insets(20, 0, 0, 20);
        cudPanel.add(cudExecute, cudC);

        cudInstructions = new JLabel("Choose something from the drop down menu, then press the Execute button", SwingConstants.CENTER);
        cudInstructions.setFont(new Font("Serif", Font.PLAIN, 28));
        cudC.fill = GridBagConstraints.HORIZONTAL;
        cudC.ipady = 20;
        cudC.gridx = 0;
        cudC.gridy = 1;
        cudC.gridwidth = 3;
        cudC.anchor = GridBagConstraints.CENTER;
        cudC.insets = new Insets(40, 20, 0, 20);
        cudPanel.add(cudInstructions, cudC);

        cudInput = new JTextField();
        cudInput.setFont(new Font("Serif", Font.PLAIN, 20));
        cudC.gridx = 0;
        cudC.gridy = 2;
        cudC.gridwidth = 2;
        cudC.insets = new Insets(40, 20, 0, 20);
        cudPanel.add(cudInput, cudC);

        cudEnter = new JButton("Enter");
        cudEnter.setFont(new Font("Serif", Font.PLAIN, 20));
        cudC.gridx = 2;
        cudC.gridy = 2;
        cudC.gridwidth = 1;
        cudC.insets = new Insets(40, 0, 0, 20);
        cudPanel.add(cudEnter, cudC);

        cudResult = new JLabel("(See results here)");
        cudResult.setFont(new Font("Serif", Font.PLAIN, 24));
        cudC.gridx = 0;
        cudC.gridy = 3;
        cudC.gridwidth = 3;
        cudC.weighty = 1;
        cudC.anchor = GridBagConstraints.NORTH;
        cudC.insets = new Insets(20, 20, 20, 20);
        cudPanel.add(cudResult, cudC);

        // Query panel -------------------------------------------------------------------------------------------------
        queryPanel = new JPanel();
        queryPanel.setLayout(grid);
        GridBagConstraints queryC = new GridBagConstraints();

        queryBack = new JButton("Back");
        queryBack.setFont(new Font("Serif", Font.PLAIN, 20));
        queryC.fill = GridBagConstraints.HORIZONTAL;
        queryC.gridx = 0;
        queryC.gridy = 0;
        queryC.gridwidth = 1;
        queryC.anchor = GridBagConstraints.NORTH;
        queryC.insets = new Insets(20, 20, 0, 20);
        queryPanel.add(queryBack, queryC);

        queryMenu = new JComboBox();
        queryMenu.setFont(new Font("Serif", Font.PLAIN, 20));
        queryMenu.addItem("");
        queryMenu.addItem("Weapon List");
        queryMenu.addItem("Player with Most Items");
        queryMenu.addItem("Classes Most Used Skill");
        queryMenu.addItem("Class Comparison");
        queryMenu.addItem("Mean Level Leaderboard");
        queryMenu.addItem("Summary");
        queryMenu.addItem("Display Specific Table");
        queryC.weightx = 1;
        queryC.gridx = 1;
        queryC.gridy = 0;
        queryC.insets = new Insets(20, 0, 0, 20);
        queryPanel.add(queryMenu, queryC);

        queryExecute = new JButton("Execute");
        queryExecute.setFont(new Font("Serif", Font.PLAIN, 20));
        queryC.fill = GridBagConstraints.NONE;
        queryC.ipadx = 0;
        queryC.gridx = 2;
        queryC.gridy = 0;
        queryC.weightx = 0;
        queryC.insets = new Insets(20, 0, 0, 20);
        queryPanel.add(queryExecute, queryC);

        queryInstructions = new JLabel("Choose something from the drop down menu, then press the Execute button", SwingConstants.CENTER);
        queryInstructions.setFont(new Font("Serif", Font.PLAIN, 28));
        queryC.fill = GridBagConstraints.HORIZONTAL;
        queryC.ipady = 20;
        queryC.gridx = 0;
        queryC.gridy = 1;
        queryC.gridwidth = 3;
        queryC.anchor = GridBagConstraints.CENTER;
        queryC.insets = new Insets(40, 20, 0, 20);
        queryPanel.add(queryInstructions, queryC);

        queryInput = new JTextField();
        queryInput.setFont(new Font("Serif", Font.PLAIN, 20));
        queryC.gridx = 0;
        queryC.gridy = 2;
        queryC.gridwidth = 2;
        queryC.insets = new Insets(40, 20, 0, 20);
        queryPanel.add(queryInput, queryC);

        queryEnter = new JButton("Enter");
        queryEnter.setFont(new Font("Serif", Font.PLAIN, 20));
        queryC.gridx = 2;
        queryC.gridy = 2;
        queryC.gridwidth = 1;
        queryC.insets = new Insets(40, 0, 0, 20);
        queryPanel.add(queryEnter, queryC);

        queryResultArea = new JTextArea("(See results here)");
        queryResultArea.setFont(new Font("Serif", Font.PLAIN, 24));
        queryC.gridx = 0;
        queryC.gridy = 3;
        queryC.gridwidth = 3;
        queryC.weighty = 1;
        queryC.anchor = GridBagConstraints.NORTH;
        queryC.insets = new Insets(20, 20, 20, 20);
        queryResultArea = new JTextArea("(See results here)");
        queryResultArea.setFont(new Font("Monospaced", Font.PLAIN, 24));
        queryResultArea.setLineWrap(true);
        queryResultArea.setWrapStyleWord(true);

        // Wrap it
        queryScrollPane = new JScrollPane(queryResultArea);

        // Add the scroll pane instead of the text area
        queryC.gridx = 0;
        queryC.gridy = 3;
        queryC.gridwidth = 3;
        queryC.weighty = 1;
        queryC.fill = GridBagConstraints.BOTH; // important for resizing
        queryC.insets = new Insets(20, 20, 20, 20);

        queryPanel.add(queryScrollPane, queryC);


        // Add all the panels to the card layout panel -----------------------------------------------------------------
        defaultPanel.add(homePanel, "Home");
        defaultPanel.add(cudPanel, "CUD");
        defaultPanel.add(queryPanel,"Queries");


        frame.add(defaultPanel);
        card.show(defaultPanel, "Home"); // Intially show the Home panel

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When user closes window, the application will exit
        frame.setVisible(true); // Make sure everything is set up before calling
    }

    public void setUpButtonListeners() {
        ActionListener queryListener = new ActionListener() {

            String queryOption = "";
            String input1 = "";
            String input2 = "";

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == queryButton) {
                    card.show(defaultPanel, "Queries");
                } else if (e.getSource() == queryBack) {
                    queryInstructions.setText("Choose something from the drop down menu, then press the Execute button");
                    queryInput.setText("");
                    queryResultArea.setText("(See results here)");
                    queryMenu.setSelectedIndex(0);

                    queryOption = "";
                    input1 = "";
                    input2 = "";

                    card.show(defaultPanel, "Home");
                } else if (e.getSource() == queryExecute) {

                    queryOption = (String) queryMenu.getSelectedItem();
                    input1 = "";
                    input2 = "";

                    queryInput.setText("");
                    queryResultArea.setText("(See results here)");

                    if (queryOption.equals("Weapon List")) {
                        weaponList_db(db, queryResultArea);
                        queryInstructions.setText("Weapon list displayed.");
                        queryOption = "";
                    } else if (queryOption.equals("Player with Most Items")) {
                        playerWithMostItems_db(db, queryResultArea);
                        queryInstructions.setText("Player with Most Items");
                        queryOption = "";
                    } else if (queryOption.equals("Classes Most Used Skill")) {
                        queryInstructions.setText("Enter class name:");
                    } else if (queryOption.equals("Class Comparison")) {
                        queryInstructions.setText("Enter first class name:");
                    } else if (queryOption.equals("Mean Level Leaderboard")) {
                        queryInstructions.setText("Enter limit (number of players):");
                    } else if (queryOption.equals("Summary")) {
                        queryInstructions.setText("Enter username (or type N/A):");
                    } else if (queryOption.equals("Display Specific Table")) {
                        queryInstructions.setText("Enter table name (Player, PLAYS, Character, IS, Class, USES, Item, HAS, or Skill):");
                    }
                } else if (e.getSource() == queryEnter) {

                    if (queryInput.getText().isEmpty() || queryOption.equals("")) {
                        return;
                    }
                    // STEP 1
                    if (input1.equals("")) {

                        input1 = queryInput.getText();

                        if (queryOption.equals("Class Comparison")) {
                            queryInstructions.setText("Enter second class name:");
                        } else if (queryOption.equals("Mean Level Leaderboard")) {
                            int limit = Integer.parseInt(input1);
                            meanLvlLeaderboard_db(db, limit, queryResultArea);
                            queryReset();
                        } else if (queryOption.equals("Summary")) {
                            summary_db(db, input1, queryResultArea);
                            queryReset();
                        } else if (queryOption.equals("Classes Most Used Skill")) {
                            classesMostUsedSkill_db(db, input1, queryResultArea);
                            queryReset();
                        } else if (queryOption.equals("Display Specific Table")) {
                            displayTable_db(db, input1, queryResultArea);
                            queryReset();
                        }
                        queryInput.setText("");
                        // STEP 2
                    } else if (input2.equals("")) {
                        input2 = queryInput.getText();

                        if (queryOption.equals("Class Comparison")) {
                            classComparison_db(db, input1, input2, queryResultArea);
                            queryReset();
                        }
                        queryInput.setText("");
                    }
                }
            }

            private void queryReset() {
                queryInstructions.setText("Choose something from the drop down menu, then press the Execute button");
                queryMenu.setSelectedIndex(0);
                queryOption = "";
                input1 = "";
                input2 = "";
            }
        };

        ActionListener cudListener = new ActionListener() { // Creating an object without having to define another class in a separate file
            String menuOption = "";
            String input1 = "";
            String input2 = "";
            String input3 = "";
            String input4 = "";
            String input5 = "";

            @Override
            public void actionPerformed(ActionEvent e) { // When button is clicked, actionEvent is created which contains information
                if (e.getSource() == cudButton) {
                    card.show(defaultPanel, "CUD");
                } else if (e.getSource() == cudBack) {
                    // Reset page components
                    cudInstructions.setText("Choose something from the drop down menu, then press the Execute button");
                    cudInput.setText("");
                    cudResult.setText("(See results here)");
                    cudMenu.setSelectedItem("");
                    menuOption = "";

                    card.show(defaultPanel, "Home");
                } else if (e.getSource() == cudExecute) {
                    menuOption = (String) cudMenu.getSelectedItem();
                    input1 = "";
                    input2 = "";
                    input3 = "";
                    input4 = "";
                    input5 = "";

                    cudInput.setText("");
                    cudResult.setText("(See results here)");

                    // Beginning instructions
                    if (cudMenu.getSelectedItem() == "Create a new character") {
                        cudInstructions.setText("Creating a new character: Enter your Player ID");
                    } else if (cudMenu.getSelectedItem() == "Delete a character") {
                        cudInstructions.setText("Deleting a character: Enter the Character ID for the character you'd like to delete");
                    } else if (cudMenu.getSelectedItem() == "Update your username") {
                        cudInstructions.setText("Updating your username: Enter your Player ID");
                    } else if (cudMenu.getSelectedItem() == "Update a character's name") {
                        cudInstructions.setText("Updating a character's name: Enter the Character ID for the character whose name you'd like to change");
                    } else if (cudMenu.getSelectedItem() == "Update a character's class") {
                        cudInstructions.setText("Updating a character's class: Enter the Character ID for the character whose class you'd like to change");
                    } else if (cudMenu.getSelectedItem() == "Increase a character's level by 1") {
                        cudInstructions.setText("Increasing character level: Enter the Character ID for the character whose level you want to increase by 1");
                    } else {
                        cudInstructions.setText("Choose something from the drop down menu, then press the Execute button");
                    }
                } else if (e.getSource() == cudEnter) {
                    if (!cudInput.getText().equals("") && !menuOption.equals("")) { // Check for invalid input and valid drop down menu option
                        if (input1.equals("")) { // If no user input in input1
                            input1 = cudInput.getText();

                            // Next instruction
                            if (menuOption.equals("Create a new character")) {
                                cudInstructions.setText("Creating a new character: What is the name of your new character?");
                            } else if (menuOption.equals("Delete a character")) {
                                cudInstructions.setText("Are you sure you want to delete Character " + input1 + "? This cannot be undone. Type y to confirm deletion");
                            } else if (menuOption.equals("Update your username")) {
                                cudInstructions.setText("Updating your username: Enter your new username");
                            } else if (menuOption.equals("Update a character's name")) {
                                cudInstructions.setText("Updating a character's name: What is the new name for this character?");
                            } else if (menuOption.equals("Update a character's class")) {
                                cudInstructions.setText("Updating a character's class: What is the new class for this character? Enter the ID");
                            }

                            // Execute increment level method
                            if (menuOption.equals("Increase a character's level by 1")) {
                                try {
                                    int charID = Integer.parseInt(input1);

                                    String result = incrementCharacterLevel_db(db, charID);

                                    cudResult.setText(result);
                                } catch (NumberFormatException ex) {
                                    cudResult.setText("Invalid ID; please reselect the increase level option if you'd like to try again");
                                }

                                cudInstructions.setText("Choose something from the drop down menu, then press the Execute button");
                                cudMenu.setSelectedItem("");
                                menuOption = "";
                            }

                            cudInput.setText("");

                        } else if (input2.equals("")) { // If no user input in input2
                            input2 = cudInput.getText();

                            // Next instruction
                            if (menuOption.equals("Create a new character")) {
                                cudInstructions.setText("Creating a new character: What skill does this character have? Enter the ID");
                            }

                            // Execute character deletion method
                            if (menuOption.equals("Delete a character")) {
                                if (input2.equals("y")) {
                                    int charId;

                                    try {
                                        charId = Integer.parseInt(input1);

                                        String result = deleteCharacter_db(db, charId);

                                        cudResult.setText(result);
                                    } catch (NumberFormatException ex) {
                                        cudResult.setText("Invalid ID; please reselect the delete option if you'd like to try again");
                                    }
                                } else {
                                    cudResult.setText("Character deletion cancelled");
                                }

                                cudInstructions.setText("Choose something from the drop down menu, then press the Execute button");
                                cudMenu.setSelectedItem("");
                                menuOption = "";
                            }

                            // Execute update username method
                            if (menuOption.equals("Update your username")) {
                                try {
                                    int playerID = Integer.parseInt(input1);

                                    String result = updateUsername_db(db, playerID, input2);

                                    cudResult.setText(result);
                                } catch (NumberFormatException ex) {
                                    cudResult.setText("Invalid ID; please reselect the update username option if you'd like to try again");
                                }

                                cudInstructions.setText("Choose something from the drop down menu, then press the Execute button");
                                cudMenu.setSelectedItem("");
                                menuOption = "";
                            }

                            // Execute update character name method
                            if (menuOption.equals("Update a character's name")) {
                                try {
                                    int charID = Integer.parseInt(input1);

                                    String result = updateCharacterName_db(db, charID, input2);

                                    cudResult.setText(result);
                                } catch (NumberFormatException ex) {
                                    cudResult.setText("Invalid ID; please reselect the update character name option if you'd like to try again");
                                }

                                cudInstructions.setText("Choose something from the drop down menu, then press the Execute button");
                                cudMenu.setSelectedItem("");
                                menuOption = "";
                            }

                            // Execute update character class method
                            if (menuOption.equals("Update a character's class")) {
                                try {
                                    int charID = Integer.parseInt(input1);
                                    int classID = Integer.parseInt(input2);

                                    String result = characterClassChange_db(db, charID, classID);

                                    cudResult.setText(result);
                                } catch (NumberFormatException ex) {
                                    cudResult.setText("Invalid ID; please reselect the update class option if you'd like to try again");
                                }

                                cudInstructions.setText("Choose something from the drop down menu, then press the Execute button");
                                cudMenu.setSelectedItem("");
                                menuOption = "";
                            }

                            cudInput.setText("");

                        } else if (input3.equals("")) { // If no user input in input3
                            input3 = cudInput.getText();

                            // Next instruction
                            if (menuOption.equals("Create a new character")) {
                                cudInstructions.setText("Creating a new character: What item does this character have? Enter the ID");
                            }

                            cudInput.setText("");

                        } else if (input4.equals("")) { // If no user input in input4
                            input4 = cudInput.getText();

                            // Next instruction
                            if (menuOption.equals("Create a new character")) {
                                cudInstructions.setText("Creating a new character: What class is this character? Enter the ID");
                            }

                            cudInput.setText("");

                        } else if (input5.equals("")) { // If no user input in input5
                            input5 = cudInput.getText();

                            // Execute create character method
                            if (menuOption.equals("Create a new character")) {
                                try {
                                    int playerID = Integer.parseInt(input1);
                                    int skillID = Integer.parseInt(input3);
                                    int itemID = Integer.parseInt(input4);
                                    int classID = Integer.parseInt(input5);

                                    String result = createCharacter_db(db, playerID, input2, skillID, itemID, classID);

                                    cudResult.setText(result);
                                } catch (NumberFormatException ex) {
                                    cudResult.setText("Invalid ID; please reselect the create character option if you'd like to try again");
                                }

                                cudInstructions.setText("Choose something from the drop down menu, then press the Execute button");
                                cudMenu.setSelectedItem("");
                                menuOption = "";
                            }

                            cudInput.setText("");
                        }
                    }
                }
            }
        };

        cudButton.addActionListener(cudListener);
        queryButton.addActionListener(cudListener);
        cudBack.addActionListener(cudListener);
        cudExecute.addActionListener(cudListener);
        cudEnter.addActionListener(cudListener);

        queryButton.addActionListener(queryListener);
        queryBack.addActionListener(queryListener);
        queryExecute.addActionListener(queryListener);
        queryEnter.addActionListener(queryListener);
    };

    /**
     * Runs the necessary queries to create a new Character.
     *
     * @param db Database object to interact with
     * @param player The Player's PlayerID that plays the new character
     * @param name The new Character's name
     * @param skill The new Character's skill
     * @param item The new Character's item
     * @param charClass The new Character's class
     */
    public static String createCharacter_db(Database db, int player, String name, int skill, int item, int charClass) {
        int maxPlayerID = -1;

        try {
            maxPlayerID = db.getHighestPlayerID();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something went wrong when checking the Player ID";
        }

        if (player < 1 || player > maxPlayerID) {
            return "Invalid Player ID; please reselect the create character option if you'd like to try again";
        }

        if (skill < 1 || skill > 65) {
            return "Invalid Skill ID; please reselect the create character option if you'd like to try again";
        }

        if (item < 1 || item > 300) {
            return "Invalid Item ID; please reselect the create character option if you'd like to try again";
        }

        if (charClass < 1 || charClass > 16) {
            return "Invalid Class ID; please reselect the create character option if you'd like to try again";
        }

        int cid = -1; // CharacterID for the new Character
        try {
            cid = db.generateCharacterID();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something went wrong when generating a Character ID for the character";
        }

        Character c = new Character(cid, name, 1);

        String result = "";

        try {
            result += db.insertCharacter(c); // Insert into Character table
            result += "<html><br><html>";
            result += db.insertPlays(player, cid); // Insert into PLAYS table
            result += "<html><br><html>";
            result += db.insertHas(cid, skill); // Insert into HAS table
            result += "<html><br><html>";
            result += db.insertUses(cid, item); // Insert into USES table
            result += "<html><br><html>";
            result += db.insertIs(cid, charClass); // Insert into IS table
            result += "<html><br><html>";

            result += ("Successfully created Character " + cid);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something went wrong when creating a new character";
        }
    }

    /**
     * Runs the necessary queries to delete a Character.
     *
     * @param db Database object to interact with
     * @param character The CharacterID for the Character to delete
     */
    public static String deleteCharacter_db(Database db, int character) {
        String result = "";

        try {
            result += db.deletePlays(character); // Delete from PLAYS
            result += "<html><br><html>";
            result += db.deleteHas(character); // Delete from HAS
            result += "<html><br><html>";
            result += db.deleteUses(character); // Delete from USES
            result += "<html><br><html>";
            result += db.deleteIs(character); // Delete from IS
            result += "<html><br><html>";
            result += db.deleteCharacter(character); // Delete from Character

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something went wrong when deleting Character " + character;
        }
    }

    public static String updateUsername_db(Database db, int playerID, String newName){
        try {
            int rows = db.updateUsername(playerID, newName);
            return rows + " username(s) updated";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something went wrong when updating your username";
        }
    }

    public static String incrementCharacterLevel_db(Database db, int charID){
        try {
            int rows = db.incrementCharacterLevel(charID);
            return "Increased the level of " + rows + " character(s)";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something went wrong when updating character level";
        }

    }

    public static String characterClassChange_db(Database db, int characterID, int newClassID){
        if (newClassID < 1 || newClassID > 16) {
            return "Invalid Class ID; please reselect the update class option if you'd like to try again";
        }

        try {
            int rows = db.characterClassChange(characterID, newClassID);
            return rows + " character class(es) updated";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something went wrong when changing character class";
        }
    }

    /**
     * Runs the updateCharacterName query.
     *
     * @param db The database object to interact with
     * @param characterID The ID of the Character whose name we want to change
     * @param newName The new name for the character
     */
    public static String updateCharacterName_db(Database db, int characterID, String newName) {
        try {
            int rows = db.updateCharacterName(characterID, newName);
            return rows + " character(s) updated";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something went wrong when changing the character's name";
        }
    }

    public static void displayTable_db(Database db, String tableName, JTextArea output){
        StringBuilder sb = new StringBuilder();

        if(!(tableName.compareTo("Character") == 0 || tableName.compareTo("Class") == 0 || tableName.compareTo("HAS") == 0 || tableName.compareTo("IS") == 0 || tableName.compareTo("Item") == 0 || tableName.compareTo("Player") == 0 || tableName.compareTo("PLAYS") == 0 || tableName.compareTo("Skill") == 0 || tableName.compareTo("USES") == 0)){
            sb.append(String.format("%s was not a valid table", tableName));
            output.setText(sb.toString());
            return;
        }

        try {
            ResultSet results = db.displayTable(tableName);
            if(tableName.compareTo("Character") == 0){
                sb.append("Listing all entries in the Character table\n");
                sb.append(String.format("%-16s %-32s %-12s\n","Character ID", "Name", "Level"));

                while (results.next()) {
                    int ID = results.getInt("CharacterID");
                    String name = results.getString("Name");
                    int level = results.getInt("Level");
                    sb.append(String.format("%-16d %-32s %-12d\n", ID, name, level));
                }
            } else if (tableName.compareTo("Class") == 0){
                sb.append("Listing all entries in the Class table\n");
                sb.append(String.format("%-10s %-17s %-8s %-12s %-6s %-7s %-10s %-10s %-14s\n","Class ID", "Class Name", "Attack", "Resistance", "HP", "Speed", "Defense", "CritRate", "CritDamage"));

                while (results.next()) {
                    int ID = results.getInt("ClassID");
                    String name = results.getString("ClassName");
                    int attack = results.getInt("Attack");
                    int resistance= results.getInt("Resistance");
                    int HP = results.getInt("HP");
                    int speed = results.getInt("Speed");
                    int defense = results.getInt("Defense");
                    int critRate = results.getInt("CritRate");
                    int critDamage = results.getInt("CritDamage");
                    sb.append(String.format("%-10d %-17s %-8d %-12d %-6d %-7d %-10d %-10d %-14d\n", ID, name, attack, resistance, HP, speed, defense, critRate, critDamage));
                }
            } else if (tableName.compareTo("HAS") == 0) {
                sb.append("Listing all entries in the HAS table\n");
                sb.append(String.format("%-16s %-14s\n", "Character ID", "Skill ID"));

                while (results.next()) {
                    int charID = results.getInt("CharacterID");
                    int skillID = results.getInt("SkillID");
                    sb.append(String.format("%-16d %-14d\n", charID, skillID));
                }
            } else if (tableName.compareTo("IS") == 0){
                sb.append("Listing all entries in the IS table\n");
                sb.append(String.format("%-16s %-14s\n", "Character ID", "Class ID"));

                while (results.next()) {
                    int charID = results.getInt("CharacterID");
                    int classID = results.getInt("ClassID");
                    sb.append(String.format("%-16d %-14d\n", charID, classID));
                }
            } else if (tableName.compareTo("Item") == 0){
                sb.append("Listing all entries in the Item table\n");
                sb.append(String.format("%-8s %-26s %-21s %-11s %-18s %-12s\n","Item ID","Item Name", "Item Classification", "Item Effect","Item Type", "Durability"));

                while (results.next()) {
                    int itemID = results.getInt("ItemID");
                    String name = results.getString("ItemName");
                    String classification = results.getString("ItemClassification");
                    int damage = results.getInt("ItemValue");
                    String type = results.getString("ItemType");
                    int durability = results.getInt("ItemDurability");

                    sb.append(String.format("%-8d %-26s %-21s %-11d %-18s %-12d\n",itemID, name, classification, damage, type, durability ));
                }
            } else if (tableName.compareTo("Player") == 0) {
                sb.append("Listing all entries in the Player table\n");
                sb.append(String.format("%-12s %-28s\n", "Player ID", "Username"));

                while (results.next()) {
                    int playerID = results.getInt("PlayerID");
                    String username = results.getString("Username");
                    sb.append(String.format("%-12s %-28s\n", playerID, username));
                }
            } else if (tableName.compareTo("PLAYS") == 0) {
                sb.append("Listing all entries in the PLAYS table\n");
                sb.append(String.format("%-14s %-14s\n", "Player ID", "Character ID"));

                while (results.next()) {
                    int playerID = results.getInt("PlayerID");
                    int charID = results.getInt("CharacterID");
                    sb.append(String.format("%-14d %-14d\n", playerID, charID));
                }
            } else if (tableName.compareTo("Skill") == 0) {
                sb.append("Listing all entries in the Skill table\n");
                sb.append(String.format("%-8s %-26s %-23s %-15s %-15s %-10s\n","Skill ID","Skill Name", "Skill Classification", "Skill Effect","Skill Type", "Cooldown"));

                while (results.next()) {
                    int itemID = results.getInt("SkillID");
                    String name = results.getString("SkillName");
                    String classification = results.getString("SkillClassification");
                    int effect = results.getInt("SkillValue");
                    String type = results.getString("SkillType");
                    int cooldown = results.getInt("SkillCooldown");

                    sb.append(String.format("%-8d %-26s %-23s %-15d %-15s %-10d\n",itemID, name, classification, effect, type, cooldown));
                }
            } else if (tableName.compareTo("USES") == 0) {
                sb.append("Listing all entries in the USES table\n");
                sb.append(String.format("%-16s %-14s\n","Character ID", "Item ID" ));

                while (results.next()) {
                    int charID = results.getInt("CharacterID");
                    int itemID = results.getInt("ItemID");

                    sb.append(String.format("%-16d %-14d\n", charID, itemID));
                }
            }
            output.setText(sb.toString());
        } catch (SQLException e) {
            sb.append(String.format("Something went wrong when listing attributes from the " + tableName + " table"));
            e.printStackTrace();
        }
    }

    /**
     * Runs the top 10 damage dealing weapon query and properly formats the results.
     * @param db Database object to interact with
     */
    public static void weaponList_db(Database db, JTextArea output) {

        try {
            ResultSet results = db.weaponList();

            StringBuilder sb = new StringBuilder();
            sb.append("Top 10 weapons ordered by damage\n");
            sb.append(String.format("%-32s %-22s %-12s %-18s %-14s\n", "Item Name", "Classification", "Damage", "Type", "Durability"));

            while (results.next()) {
                String name = results.getString("ItemName");
                String classification = results.getString("ItemClassification");
                int damage = results.getInt("ItemValue");
                String type = results.getString("ItemType");
                int durability = results.getInt("ItemDurability");

                sb.append(String.format("%-32s %-22s %-12d %-18s %-14d\n", name, classification, damage, type, durability));

            }
            output.setText(sb.toString());

        } catch (SQLException e) {
            System.out.println("Something went wrong when listing items.");
            e.printStackTrace();
        }
    }

    public static void playerWithMostItems_db(Database db, JTextArea output) {

        try {
            ResultSet results = db.playerWithMostItems();

            StringBuilder sb = new StringBuilder();

            sb.append("All items owned by the person with the most items\n");
            sb.append(String.format("%-16s %-22s %-20s %-12s %-13s %-13s\n","Player Name", "Item Name", "Item Classification", "Item Effect","Item Type", "Durability"));

            while (results.next()) {
                String player = results.getString("Name");
                String name = results.getString("ItemName");
                String classification = results.getString("ItemClassification");
                int effect = results.getInt("ItemValue");
                String type = results.getString("ItemType");
                int durability = results.getInt("ItemDurability");

                sb.append(String.format("%-16s %-22s %-20s %-12d %-13s %-13d\n",player, name, classification, effect, type, durability));
            }
            output.setText(sb.toString());
        } catch (SQLException e) {
            System.out.println("Something went wrong when listing items.");
            e.printStackTrace();
        }
    }

    public static void classesMostUsedSkill_db(Database db, String chosenClass, JTextArea output) {

        try {
            ResultSet results = db.classesMostUsedSkill(chosenClass);
            StringBuilder sb = new StringBuilder();

            sb.append(String.format("%s's Most Used Skill\n", chosenClass));
            sb.append(String.format("%-30s %-22s %-12s %-18s %-12s\n", "Skill Name", "Skill Classification", "Skill Effect","Skill Type", "Cooldown"));

            while (results.next()) {
                String name = results.getString("SkillName");
                String classification = results.getString("SkillClassification");
                int effect = results.getInt("SkillValue");
                String type = results.getString("SkillType");
                int cooldown = results.getInt("SkillCooldown");

                sb.append(String.format("%-30s %-22s %-12d %-18s %-12d\n", name, classification, effect, type, cooldown));
            }
            output.setText(sb.toString());
        } catch (SQLException e) {
            System.out.println("Something went wrong when listing most used skill of selected class.");
            e.printStackTrace();
        }
    }
    /**
     * Runs the class comparison query and properly formats the results.
     *
     * @param db Database object to interact with
     * @param name1 The name of the first class to compare
     * @param name2 The name of the second class to compare
     * @param output
     */
    public static void classComparison_db(Database db, String name1, String name2, JTextArea output) {
        int n1Len = name1.length();
        int n2Len = name2.length();
        boolean validName = false;

        try {
            ResultSet results = db.classComparison(name1, name2);

            StringBuilder sb = new StringBuilder();

            if (results.next()) {
                validName = true;

                int attack1 = results.getInt("AttackC1");
                int attack2 = results.getInt("AttackC2");
                int attackComp = results.getInt("AttackComp");
                int resistance1 = results.getInt("ResistanceC1");
                int resistance2 = results.getInt("ResistanceC2");
                int resistanceComp = results.getInt("ResistanceComp");
                int hP1 = results.getInt("HPC1");
                int hP2 = results.getInt("HPC2");
                int hPComp = results.getInt("HPComp");
                int speed1 = results.getInt("SpeedC1");
                int speed2 = results.getInt("SpeedC2");
                int speedComp = results.getInt("SpeedComp");
                int defense1 = results.getInt("DefenseC1");
                int defense2 = results.getInt("DefenseC2");
                int defenseComp = results.getInt("DefenseComp");
                int critRate1 = results.getInt("CritRateC1");
                int critRate2 = results.getInt("CritRateC2");
                int critRateComp = results.getInt("CritRateComp");
                int critDamage1 = results.getInt("CritDamageC1");
                int critDamage2 = results.getInt("CritDamageC2");
                int critDamageComp = results.getInt("CritDamageComp");

                if (n1Len % 2 == 0) { // Even length
                    sb.append(String.format(" "));
                }

                sb.append(String.format("           %s | Comparison | %s\n", name1, name2));
                sb.append(String.format("Attack:      %" + (n1Len / 2) + "d%" + (n1Len / 2) + "s| %6d%4s |  %" + (n2Len / 2) + "d\n", attack1, "", attackComp, "", attack2));
                sb.append(String.format("Resistance:  %" + (n1Len / 2) + "d%" + (n1Len / 2) + "s| %6d%4s |  %" + (n2Len / 2) + "d\n", resistance1, "", resistanceComp, "", resistance2));
                sb.append(String.format("HP:          %" + (n1Len / 2) + "d%" + (n1Len / 2) + "s| %6d%4s |  %" + (n2Len / 2) + "d\n", hP1, "", hPComp, "", hP2));
                sb.append(String.format("Speed:       %" + (n1Len / 2) + "d%" + (n1Len / 2) + "s| %6d%4s |  %" + (n2Len / 2) + "d\n", speed1, "", speedComp, "", speed2));
                sb.append(String.format("Defense:     %" + (n1Len / 2) + "d%" + (n1Len / 2) + "s| %6d%4s |  %" + (n2Len / 2) + "d\n", defense1, "", defenseComp, "", defense2));
                sb.append(String.format("CritRate:    %" + (n1Len / 2) + "d%" + (n1Len / 2) + "s| %6d%4s |  %" + (n2Len / 2) + "d\n", critRate1, "", critRateComp, "", critRate2));
                sb.append(String.format("CritDamage:  %" + (n1Len / 2) + "d%" + (n1Len / 2) + "s| %6d%4s |  %" + (n2Len / 2) + "d\n", critDamage1, "", critDamageComp, "", critDamage2));

            }

            if (!validName) { // No result was returned from the query
                sb.append("Invalid class name(s)");
            }

            output.setText(sb.toString());
        } catch (SQLException e) {
            System.out.println("Something went wrong when comparing classes.");
            e.printStackTrace();
        }
    }

    /**
     * Runs the mean level leaderboard query and properly formats the results.
     * @param db Database object to interact with
     * @param limit The total amount of rows to be returned
     */
    public static void meanLvlLeaderboard_db(Database db, int limit, JTextArea output) {
        try {
            ResultSet results = db.meanLvlLeaderboard(limit);
            StringBuilder sb = new StringBuilder();

            sb.append(String.format("%-11s %-25s %-11s %-14s\n", "Ranking", "Username", "MeanLvl", "TotalChars"));

           int ranking = 1;

            while (results.next()) {
                String username = results.getString("Username");
                double meanLvl = results.getDouble("MeanLvl");
                int totalChars = results.getInt("TotalChars");

                sb.append(String.format("   %-6d %-28s %-14.2f %-14d\n", ranking, username, meanLvl, totalChars));

                ranking++;
            }
            output.setText(sb.toString());
        } catch (SQLException e) {
            System.out.println("Something went wrong when getting mean levels.");
            e.printStackTrace();
        }
    }

    /**
     * Runs the summary query and properly formats the results.
     * @param db Database object to interact with
     * @param username The username of a player, or "" if none is provided
     */
    public static void summary_db(Database db, String username, JTextArea output) {
        try {
            if (username.equals("N/A")) { // User indicated no specific username search
                username = "";
            }

            ResultSet results = db.summary(username);
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-28s %-14s %-14s %-14s\n", "Username", "TotalChars", "TotalSkills", "TotalItems"));

            while (results.next()) {
                String player = results.getString("Username");
                int totalChars = results.getInt("TotalChars");
                int totalSkills = results.getInt("TotalSkills");
                int totalItems = results.getInt("TotalItems");

                sb.append(String.format("%-28s %-14d %-14d %-14d\n", player, totalChars, totalSkills, totalItems));
            }
            output.setText(sb.toString());
        } catch (SQLException e) {
            System.out.println("Something went wrong when getting summary.");
            e.printStackTrace();
        }
    }
}