# RPG Database Application Project
This project is a database application that models an RPG. It was completed in the CS364 Introduction to Databases course offered at the University of Wisconsin-La Crosse.

The project tracks the player accounts that play the RPG, the characters that are played in the RPG, the class of those characters, and the skills and items in the RPG. The stakeholders for the application are the players that play characters in the RPG. Players use the database application to manage their account, such as creating new characters and updating their username, and displaying various information about the RPG, such as viewing class statistics and information about other players and characters.

The project uses Java and SQL. The database is managed on MySQL, the backend code application is run through Java, and the frontend desktop application is designed with Java Swing and Java AWT.

This project is a team project. It was completed in collaboration with Ahren Heinrich (WarriorTwins2), another student taking CS364.

Note: Only the Java code is included in this repository. It is not meant for project recreation.

--------------------------------------------------------------------------------------------------------------------------------------------------------
**Description of .java Files:**

RPG.java: Contains the main method for establishing a connection to the database and starting the desktop application.

Database.java: Contains the methods necessary for interacting with the database, including connecting, disconnecting, and performing queries.

Desktop.java: Contains the methods necessary for the desktop application. This includes the frontend design of the application, the calling of Database.java methods for querying from the database, and the formatting of the result set returned from queries.

All other .java files: Representation of the database's entity and relationship tables. Capable of creating Java objects with the attributes from each table respectively.

--------------------------------------------------------------------------------------------------------------------------------------------------------
**What I Specifically Completed:**

RPG.java: Entire file.

Database.java: All "insert(TableName)" methods, all "delete(TableName)" methods, "classComparison" method, "meanLvlLeaderboard" method, "summary" method, "generateCharacterID" method, and "getHighestPlayerID" method.

Desktop.java: The layout and design of the user interface, all objects in the "Home" and "CUD/Account" panels, the "cudListener" action listener, "createCharacter_db" method, "deleteCharacter_db" method, "classComparision_db" method, "meanLvlLeaderboard_db" method, and "summary_db" method.

Finally, I created all of the .java files that represent the database's entity and relationship tables.

--------------------------------------------------------------------------------------------------------------------------------------------------------
**ER Diagram:**

<img width="996" height="675" alt="ER Diagram" src="https://github.com/user-attachments/assets/2b8a554e-d8a6-44c0-ac7c-829bd3a45d94" />
Each player can play one or more characters. Each character is of a specific class. Finally, each character has a set of 1-5 skills and uses a set of 1-16 items.

--------------------------------------------------------------------------------------------------------------------------------------------------------
**Description and Screenshots of Application's User Interface:**

"Home" page description: Contains a button for traversing to the "Manage Account" page and a button for traversing to the "Perform Queries" page with a list below each button explaining what can be completed in each page.

"Home" page:
<img width="1880" height="820" alt="image" src="https://github.com/user-attachments/assets/efa62029-f626-4973-92cb-e3d21471bf10" />

"Manage Account" page description: Contains a "Back" button for going back to the "Home" page, a dropdown list of operations that can be performed, an "Execute" button that begins executing that option when pressed, a textbox of instructions for the user, an input text field for user input, an "Enter" button that confirms the user is done entering input, and a textbox that displays the results of the operation.

"Manage Account" page: Example showing the ability to create a new character
<img width="1000" height="660" alt="image" src="https://github.com/user-attachments/assets/e399c423-bc1a-48c0-b312-0eb8263cc1da" />

"Perform Queries" page description: Contains a "Back" button for going back to the "Home" page, a dropdown list of operations that can be performed, an "Execute" button that begins executing that option when pressed, a textbox of instructions for the user, an input text field for user input, an "Enter" button that confirms the user is done entering input, and a textbox that displays the results of the operation.

"Perform Queries" page: Example showing the mean level leaderboard query
<img width="1196" height="672" alt="image" src="https://github.com/user-attachments/assets/df4d0597-d18c-49c3-b025-6e7c602b25a2" />

"Perform Queries" page: Example showing the class comparison query
<img width="984" height="676" alt="image" src="https://github.com/user-attachments/assets/da1b4660-389f-4837-9abb-3e8f8e6e7701" />

Note: Both the "Manage Account" and "Perform Queries" pages change the instructions after the user enters the necessary user input if the operation they are performing requires multiple inputs from the user. The results of the operation are not shown until all user input is provided.
