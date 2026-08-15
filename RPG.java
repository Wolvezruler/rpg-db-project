import java.sql.*;
import java.util.*;

public class RPG {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your password: ");
        String pw = input.nextLine();
        System.out.println();

        Database db = new Database(pw);

        db.connect();

        // Run the desktop application
        Desktop app = new Desktop(db);
        app.setUpButtonListeners();

        // db.disconnect();
    }
}