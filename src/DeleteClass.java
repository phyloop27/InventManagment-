import java.sql.*;                                                                                                      // Import SQL package (all)
import java.util.Scanner;                                                                                               // Import utility package, Scanner Class

public class DeleteClass {                                                                                              // Declare & start of Class
    public static void main(String[] args) {                                                                            // Start of Main Method
        Delete();
    }                                                                                                                   // Main Method End

    static void Delete() {                                                                                              // Start of delete() method
        Scanner deleteNumber = new Scanner(System.in);                                                                  // Creation of scanner object ' deleteInt '
        PreparedStatement preparedStmt;                                                                                 // Initialise prepared statement ' preparedStmt '
        int userIn_6;                                                                                                   // Initialise variable ' userIn_6 '
        ResultSet rSet;                                                                                                 // Initialise resultSet

        int item_ID;                                                                                                    // Declaring variables for use
        int itemID;
        String itemDesc;
        double unitPrice;
        float quantitySold;
        double itemQuantity;
        double itemTotValue;

        System.out.println(
                         "* ---------------------------------------------------- * \n"                                 // Main display to user -
                        + "|         --- Delete Record(s) selected ---            | \n"
                       // + "|               --- DELETE METHOD ---                  | \n"                               // Used for testing purpuses ( For ease )
                        + "|------------------------------------------------------| \n"
                        + "|   1. DELETE INVENTORY RECORD(S)                      | \n"
                        + "|------------------------------------------------------| \n"
                        + "|   2. EXIT TO MAIN MENU                               | \n"
                        + "* ---------------------------------------------------- * ");                                 // Main display to user end -

        System.out.print("|   Enter selection: ");                                                                      // Prompting the user for input
        userIn_6 = deleteNumber.nextInt();                                                                              // Storing user input in variable ' userIn_6 '
        System.out.println("* ---------------------------------------------------- *");

        switch(userIn_6) {
            case 1: {
                System.out.print("|   1.  Enter record ID to be deleted:  ");                                           // Prompt user for input
                item_ID = deleteNumber.nextInt();                                                                       // Store user input in variable ' UPitemID '

                try {
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:InventManagement.db");         // Local database ConnectionDB Driver code
                    System.out.println("* ---------------------------------------------------- *");
                    System.out.println("|      --- Connection to data-base successful! ---     |");                     // Display message for user
                    System.out.println("* ---------------------------------------------------- *");

                    String sqlReadInventory = "SELECT * from Inventory WHERE item_ID =" + item_ID;                      // SQL Statement Table SELECT and row (WHERE)
                    preparedStmt = connection.prepareStatement(sqlReadInventory);
                    rSet = preparedStmt.executeQuery();

                    itemID = rSet.getInt("item_id");                                                         // Pulling information from the table and storing in variable ' item_ID '
                    itemDesc = rSet.getString("item_description");                                           // Pulling information from the table and storing in variable ' itemDesc '
                    unitPrice = rSet.getDouble("unit_price");                                                // Pulling information from the table and storing in variable ' unitPrice '
                    quantitySold = rSet.getFloat("quantity_Sold");                                           // Pulling information from the table and storing in variable ' quantitySold '
                    itemQuantity = rSet.getDouble("quantity_of_units");                                      // Pulling information from the table and storing in variable ' itemQuantity '
                    itemTotValue = rSet.getDouble("total_unit_value");                                       // Pulling information from the table and storing in variable ' itemTotValue '

                    System.out.println(                                                                                 // Display user print out of information to be updated start --
                            "  --- Information fields to be Deleted --- " + "\n" +                                      // Displaying existing records in the database
                                    "    Item ID:           " + itemID + "\n" +                                         // before updating
                                    "    Item Description:  " + itemDesc + "\n" +
                                    "    Unit Price:        " + unitPrice + "\n" +
                                    "    Quantity Sold:     " + quantitySold + "\n" +
                                    "    Stock Quantity:    " + itemQuantity + "\n" +
                                    "    Total Stock Value: " + itemTotValue + "\n" +
                                    "* ---------------------------------------------------- * ");                       // User display end -

                    String sqlDeleteRow = ("DELETE FROM Inventory WHERE item_id = ?");
                    preparedStmt = connection.prepareStatement(sqlDeleteRow);                                           // Using ConnectionDB to prepare the SQL statement for execution
                    preparedStmt.setInt(1, item_ID);                                                      // Using inputted item_ID to select whch row to delete
                    preparedStmt.executeUpdate();                                                                       // Executing statment

                    int checkUpdateRecord = preparedStmt.executeUpdate();                                               // Code to check whether the record being added was a success

                    if (checkUpdateRecord == 1) {                                                                       // Using 'if' logic when record addition was successful
                        System.out.println("|     --- Record has been deleted successfully! ---    |");                    // Display user message
                        String oppTypeDelete = "Delete Record";                                                         // Setting the ' TYPE ' of action being preformed
                        String outCome = "PASS";                                                                        // Variable to indicate Transaction success
                        // trnsID needs to auto increment *****                                                         // Calling transactionUpdate() Method - To update database
                        connection.close();                                                                             // Close DB Connection to make way for transactionUpdate()
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeDelete, outCome);
                    }

                    else {
                        System.out.println("|            --- Failed to delete record! ---          |");                 // Display user message
                        String oppTypeDelete = "Delete Record";                                                         // Setting the ' TYPE ' of action being preformed
                        String outCome = "FAIL";                                                                        // Variable to indicate Transaction failure
                        // trnsID needs to auto increment *****                                                         // Calling transactionUpdate() Method - To update database
                        connection.close();                                                                             // Close DB ConnectionDB to make way for transactionUpdate()
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeDelete, outCome);    // Calling transactionUpdate.update() Method - To update database
                    }

            } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                Delete();                                                                                               // Return to delete() method menu

            }                                                                                                           // Switch case 1 end
                case 2: {
                    System.out.println("* ---------------------------------------------------- *");
                    System.out.println("|             --- Exiting to main menu ---             |");
                    System.out.println("* ---------------------------------------------------- *");
                    MainBuild.home();
            }                                                                                                           // Switch case 2 end
        }                                                                                                               // Switch statment end
    }                                                                                                                   // delete() method end
}                                                                                                                       // DeleteClass() end
