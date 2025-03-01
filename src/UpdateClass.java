import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UpdateClass {                                                                                              // Declare & start of Class
    public static void main(String[] args) {                                                                            // Start of Main Method
        update();
    }                                                                                                                   // Main Method End

    public static void update() {
        // Required variable's
        Scanner updateText = new Scanner(System.in);                                                                    // Create new Scanner object within the update() method - for Text
        Scanner updateNumber = new Scanner(System.in);                                                                  // Create new Scanner object within the update() method - for Numbers
        PreparedStatement preparedStmt;                                                                                 // Initialise prepared statement ' preparedStmt '
        PreparedStatement preparedStmtValues;                                                                           // Initialise prepared statement ' preparedStmtValues '
        ResultSet rSet;                                                                                                 // Initialise resultSet
        int userIn_4;                                                                                                   // Declare variable for user input

        // Temporary Update variables
        int UPitemID;                                                                                                   // Variable to store temp updated info start -
        String UPitemDesc;
        double UPunitPrice;
        float UPquantitySold;
        double UPitemQuantity;
        double UPitemTotValue;                                                                                          // Temporary variable storage end -

        // Existing Database variables
        int itemID;                                                                                                     // 'Integer' value to represent an ID number of seven digits
        String itemDesc;                                                                                                // 'String' variable to represent an Item description
        double unitPrice;                                                                                               // 'Double' variable to represent Unit price £'s + Pence
        float quantitySold;                                                                                             // 'Float' value to represent quantity Sold
        double itemQuantity;                                                                                            // 'Double' value to represent an items quantity
        double itemTotValue;                                                                                            // 'Double' variable to represent item's in stock total value
        boolean exit;


        System.out.println(
                "* ---------------------------------------------------- *  \n"                                          // Main display to user -
                + "|          --- Update Record(s) selected ---           | \n"
                 // + "|              --- UPDATE METHOD ---                   | \n"                                     // Used for testing purpuses ( For ease )
                + "|------------------------------------------------------| \n"
                + "|   1. UPDATE INVENTORY RECORD(S)                      | \n"
                + "|   2. PRINT / READ INVENTORY RECORD(S)                | \n"
                + "|------------------------------------------------------| \n"
                + "|   3. EXIT TO MAIN MENU                               | \n"
                + "* ---------------------------------------------------- * ");                                         // Display message end

        System.out.print("|   Enter selection: ");                                                                      // Prompting the user for input
        userIn_4 = updateNumber.nextInt();                                                                              // Storing user input in variable

        switch (userIn_4) {
            /*
            Case one will ask the user for the new information to fill each record field required by the Inventory Table
            and store the information given in Temporary variables.
             */
            case 1: {
                        // Should be a five-digit number but database only holds incremental values so far for ID
                        System.out.print("|   1.  Enter record ID to be updated:  ");                                           // Prompt user for input
                        UPitemID = updateNumber.nextInt();                                                                      // Store user input in variable ' UPitemID '

                        System.out.print("|   2. Enter new item description (Text): ");                                         // Prompt user for input
                        UPitemDesc = updateText.nextLine();                                                                     // Store user input in variable ' UPitemDesc '

                        System.out.print("|   3. Enter new item prince (£0.00): ");                                             // Prompt user for input
                        UPunitPrice = updateNumber.nextDouble();                                                                // Store user input in variable ' UPunitPrice '

                        System.out.print("|   4. Enter quantity sold (00.00):");                                                // Prompt user for input
                        UPquantitySold = updateNumber.nextFloat();                                                              // Store user input in variable ' UPquantitySold '

                        System.out.print("|   5. Enter new item quantity (0.00): ");                                            // Prompt user for input
                        UPitemQuantity = updateNumber.nextDouble();                                                             // Store user input in variable ' UPitemQuantity '

                        System.out.print("|   6. Total unit value is calculated automatically ");                               // Prompt user for input
                        UPitemTotValue = (UPunitPrice * UPitemQuantity);                                                        // Calculate variable ' UPitemTotValue '
                        System.out.println(" ");                                                                                // Display spacer

                /*
                Pulling records from the database and assigning them to variables to be updated with the already received
                user input above -
                */

                try {                                                                                                   // Use of try block in case of error connecting
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:InventManagement.db");         // Local database ConnectionDB Driver code
                    System.out.println("* ---------------------------------------------------- *");
                    System.out.println("|      --- Connection to data-base successful! ---     |");                          // Display message for user
                    System.out.println("* ---------------------------------------------------- *");

                    String sqlReadInventory = "SELECT * from Inventory WHERE item_ID =" + UPitemID;                     // SQL Statement Table SELECT and row (WHERE)
                    preparedStmt = connection.prepareStatement(sqlReadInventory);                                       // Using ConnectionDB to prepare the SQL statement for execution
                    rSet = preparedStmt.executeQuery();

                    itemID = rSet.getInt("item_id");                                                                    // Pulling information from the table and storing in variable ' item_ID '
                    itemDesc = rSet.getString("item_description");                                                      // Pulling information from the table and storing in variable ' itemDesc '
                    unitPrice = rSet.getDouble("unit_price");                                                           // Pulling information from the table and storing in variable ' unitPrice '
                    quantitySold = rSet.getFloat("quantity_Sold");                                                      // Pulling information from the table and storing in variable ' quantitySold '
                    itemQuantity = rSet.getDouble("quantity_of_units");                                                 // Pulling information from the table and storing in variable ' itemQuantity '
                    itemTotValue = rSet.getDouble("total_unit_value");                                                  // Pulling information from the table and storing in variable ' itemTotValue '

                    System.out.println(                                                                                 // Display user print out of information to be updated start --
                            "  --- Information fields to be updated --- " + "\n" +                                      // Displaying existing records in the database
                                    "    Item ID:           " + itemID + "\n" +                                         // before updating
                                    "    Item Description:  " + itemDesc + "\n" +
                                    "    Unit Price:        " + unitPrice + "\n" +
                                    "    Quantity Sold:     " + quantitySold + "\n" +
                                    "    Stock Quantity:    " + itemQuantity + "\n" +
                                    "    Total Stock Value: " + itemTotValue + "\n" +
                                    "* ---------------------------------------------------- * ");                       // User display end -

                    String sqlUpdateInventory = "UPDATE Inventory" +                                                    // Using SQL UPDATE Statement
                            " SET item_description = ?," +                                                              // Using SET Statement to direct which fields to update
                            " unit_price = ?," +                                                                        // Using ' ? ' as SQL placeholders
                            " quantity_Sold = ?," +
                            " quantity_of_units = ?," +
                            " total_unit_value = ?" +
                            " WHERE item_id = ?";                                                                        // SQL Statement (placeholders end) -

                    preparedStmtValues = connection.prepareStatement(sqlUpdateInventory);                                // Using connection to prepare the SQL statement for execution

                    preparedStmtValues.setString(1, UPitemDesc);                                                        // SQL placeholder 1 updated variable insert into field
                    preparedStmtValues.setDouble(2, UPunitPrice);                                                       // SQL placeholder 2 updated variable insert into field
                    preparedStmtValues.setFloat(3, UPquantitySold);                                                     // SQL placeholder 3 updated variable insert into field
                    preparedStmtValues.setDouble(4, UPitemQuantity);                                                    // SQL placeholder 4 updated variable insert into field
                    preparedStmtValues.setDouble(5, UPitemTotValue);                                                    // SQL placeholder 5 updated variable insert into field
                    preparedStmtValues.setInt(6, UPitemID);                                                             // SQL placeholder 6 updated variable insert into field
                    preparedStmtValues.executeUpdate();

                    String sqlUpdatedInventory = "SELECT * from Inventory WHERE item_ID =" + UPitemID;                  // SQL Statement Table SELECT and row (WHERE)
                    preparedStmt = connection.prepareStatement(sqlUpdatedInventory);                                    // Using connection to prepare the SQL statement for execution
                    rSet = preparedStmt.executeQuery();

                    itemID = rSet.getInt("item_id");                                                                    // Pulling Updated information from the table and storing in variable ' item_ID '
                    itemDesc = rSet.getString("item_description");                                                      // Pulling Updated information from the table and storing in variable ' itemDesc '
                    unitPrice = rSet.getDouble("unit_price");                                                           // Pulling Updated information from the table and storing in variable ' unitPrice '
                    quantitySold = rSet.getFloat("quantity_Sold");                                                      // Pulling Updated information from the table and storing in variable ' quantitySold '
                    itemQuantity = rSet.getDouble("quantity_of_units");                                                 // Pulling Updated information from the table and storing in variable ' itemQuantity '
                    itemTotValue = rSet.getDouble("total_unit_value");                                                  // Pulling Updated information from the table and storing in variable ' itemTotValue '

                    System.out.println(                                                                                 // Display user print out of updated information start -
                            "  --- Updated fields are --- " + "\n" +                                                    // Displaying the Updated values within the database
                                    "    Updated Item ID:           " + itemID + "\n" +
                                    "    Updated Item Description:  " + itemDesc + "\n" +
                                    "    Updated Unit Price:        " + unitPrice + "\n" +
                                    "    Updated Quantity Sold:     " + quantitySold + "\n" +
                                    "    Updated Stock Quantity:    " + itemQuantity + "\n" +
                                    "    Updated Total Stock Value: " + itemTotValue + "\n" +
                                    "* ---------------------------------------------------- * ");                       // User display end -

                    int checkUpdateRecord = preparedStmtValues.executeUpdate();                                         // Code to check whether the record being added was a success

                    if (checkUpdateRecord > 0) {                                                                        // Using 'if' logic when record addition was successful
                        System.out.println("|     --- Record has been updated successfully! ---    |");                      // Display user message
                        String oppTypeCreate = "Update Record";                                                         // Setting the ' TYPE ' of action being preformed
                        String outCome = "PASS";                                                                        // Variable to indicate Transaction success
                        // trnsID needs to auto increment *****                                                         // Calling transactionUpdate.update() Method - To update database
                        connection.close();                                                                             // Close DB ConnectionDB to make way for transactionUpdate() DB ConnectionDB
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeCreate, outCome); // Passing values to Update() method for DB update
                    } else {
                        System.out.println("|        --- Failed to add record! --- ");                                  // Display user message
                        String oppTypeCreate = "Update Record";                                                         // Setting the ' TYPE ' of action being preformed
                        String outCome = "FAIL";                                                                        // Variable to indicate Transaction fail
                        // trnsID needs to auto increment *****                                                         // Calling transactionUpdate.update() Method - To update database
                        connection.close();                                                                             // Close DB ConnectionDB to make way for transactionUpdate() DB ConnectionDB
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeCreate, outCome);  // Passing values to Update() method for DB update
                    }

                    System.out.println("| ");                                                                           // Display spacer
                    UpdateClass.update();                                                                               // Return to update() method menu

                }
                catch (Exception e) {                                                                                   // Catch block for errors and exceptions
                    System.out.print("A Runtime Eroor has occured - Restart");                                          // Display message to user
                    //throw new RuntimeException(e);                                                                    // Throws error - Replaced to catch Exception
                    UpdateClass.update();                                                                               // Returns flow control back to update start menu
                }
            }                                                                                                           // End of Switch case 1 -

            case 2: {
                System.out.println("|   Read Record(s) Selected");                                                      // User display
                ReadClass.read();                                                                                       // Return to ReadClass.read()  Class & Method
            }                                                                                                           // End of switch case 2

            case 3: {
                System.out.println("|   Returning to main menu");                                                       // User display
                MainBuild.home();                                                                                       // Return to MainBuild.home() Class & Method - Main menu
            }                                                                                                           // End of switch case 3
            default:
                System.out.print("Invalid input, please try again");
                UpdateClass.update();
                break;

        }                                                                                                               // End of switch
    }                                                                                                                   // End of update() method
}                                                                                                                       // End of TransactionUpdate.update() Class