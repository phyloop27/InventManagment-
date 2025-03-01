import java.sql.*;                                                                                                      // Import SQL package's (all)
import java.util.InputMismatchException;
import java.util.Scanner;                                                                                               // Import utility package, Scanner Class

public class ReadClass {                                                                                                // Declare & start of Class
    public static void main(String[] args) {                                                                            // Inside  Main Method
        read();
    }                                                                                                                   // Main Method End

    public static void read() {                                                                                         // Start of read() method
        Scanner readInt = new Scanner(System.in);                                                                       // Creation of scanner object ' readInt '
        PreparedStatement preparedStmt;                                                                                 // Initialise prepared statement ' preparedStmt '
        int userIn_3;                                                                                                   // Initialise variable ' userIn_3 '
        ResultSet rSet;                                                                                                 // Initialise resultSet

        int item_ID = 0;                                                                                                // Creating required variables for class & method(s)
        String itemDesc = "";
        double unitPrice = 0;
        float quantitySold = 0;
        double itemQuantity = 00.00;
        double itemTotValue;
        String oppTypeRead;
        String outCome;
        int trnsID = 0;

        System.out.println(
                  "* ---------------------------------------------------- * \n"                                           // Main display to user -
                + "|            --- Read Record(s) selected ---           | \n"
                // + "|                --- READ METHOD ---                   | \n"                                      // Used for testing purpuses ( For ease )
                + "|------------------------------------------------------| \n"
                + "|   1. READ INVENTORY RECORD(S)                        | \n"
                + "|   2. READ TRANSACTION RECORD(S)                      | \n"
                + "|------------------------------------------------------| \n"
                + "|   3. EXIT TO MAIN MENU                               | \n"
                + "* ---------------------------------------------------- * ");                                         // Main display to user end -

        try {                                                                                                           // Wrap in try/catch block - data missMatch excp -
            System.out.print("|   Enter selection: ");                                                                  // Prompting the user for input
            userIn_3 = readInt.nextInt();                                                                               // Storing user input in variable ' userIn_3 '

        switch(userIn_3) {
            case 1: {
                try {                                                                                                   // Use of try block in case of error connecting
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:InventManagement.db");         // Local database ConnectionDB
                    System.out.println("* ---------------------------------------------------- *");                     // Display message for user start -
                    System.out.println("|     --- Connection to data-base successful! ---      |");
                    System.out.println("|                     *    *    *                      |");                     // Display message for user end -

                    String sqlRead = "select * from Inventory";                                                         // Selecting ' all ' (*) from Inventory Table
                    preparedStmt = connection.prepareStatement(sqlRead);                                                // Using ConnectionDB to prepare the SQL statement
                    rSet = preparedStmt.executeQuery();

                    System.out.println("|     Please see records from the inventory table:     |");                     // Display user message
                    System.out.println("* ---------------------------------------------------- *  ");                   // Display spacer

                    while (rSet.next()) {                                                                               // Using a ' while ' loop to gather all info from table
                        item_ID = rSet.getInt("item_id");                                                    // Pulling information from the table and storing in variable ' item_ID '
                        itemDesc = rSet.getString("item_description");                                       // Pulling information from the table and storing in variable ' itemDesc '
                        unitPrice = rSet.getDouble("unit_price");                                            // Pulling information from the table and storing in variable ' unitPrice '
                        quantitySold = rSet.getFloat("quantity_Sold");                                       // Pulling information from the table and storing in variable ' quantitySold '
                        itemQuantity = rSet.getDouble("quantity_of_units");                                  // Pulling information from the table and storing in variable ' itemQuantity '
                        itemTotValue = rSet.getDouble("total_unit_value");                                   // Pulling information from the table and storing in variable ' itemTotValue '

                        System.out.println(                                                                             // Display user print out of requested information start --
                                "    Item ID: " + item_ID + "\n" +
                                        "    Item Description:  " + itemDesc + "\n" +
                                        "    Unit Price:        " + unitPrice + "\n" +
                                        "    Quantity Sold:     " + quantitySold + "\n" +
                                        "    Stock Quantity:    " + itemQuantity + "\n" +                               // ' while ' loop stops when there is no more information in the table -
                                        "    Total Stock Value: " + itemTotValue + "\n" +                               // - to iterate over
                                        "* ---------------------------------------------------- *  ");                  // Display user print out of requested information end -- Display spacer

                    }
                                                                                                                        // Using 'if' logic when record pull request was successful
                    if (item_ID >= 1) {                                                                                 // Code to check whether the record being added was a success
                        System.out.println("|     --- All record(s) retrieved successfully ---     | ");                // Display user message
                        outCome = "PASS";                                                                               // Variable to indicate Transaction success
                        oppTypeRead = "Read Record";                                                                    // Variable to indicate Record Type Transaction
                        connection.close();                                                                             // Closing connection to free up resources to database
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeRead, outCome);         // Calling transactionUpdate.update() Method - To update database

                    } else {
                        System.out.println("|        --- Failed to read record(s)! --- ");                              // Display user message
                        outCome = "FAIL";                                                                               // Variable to indicate Transaction fail
                        oppTypeRead = "Read Record";                                                                    // Variable to indicate Record Type Transaction
                        connection.close();                                                                             // Closing connection to free up resources to database
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeRead, outCome);         // Calling transactionUpdate.update() Method - To update database
                    }

                    connection.close();                                                                                 // Closing ConnectionDB to database
                    System.out.println("| ");                                                                           // Display spacer
                    read();                                                                                             // calling read() Method to return to sub-menu

                }

                catch (SQLException e) {                                                                                // In-case of try failure -
                    System.out.println("|            --- Connection to database failed! ---      |");                   // Display message for user
                    read();                                                                                             // Return to read() main menu
                }
            }

            case 2: {
                try {                                                                                                   // Use of try block in case of error connecting
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:InventManagement.db");         // Local database Connection
                    System.out.println("* ---------------------------------------------------- *");                     // Display message for user start -
                    System.out.println("|      --- Connection to data-base successful! ---     |");
                    System.out.println("|                     *    *    *                      |");                     // Display message for user end -

                    String sqlReadTransactions = "select * from Transactions";                                          // Selecting ' all ' (*) from Transaction Table
                    preparedStmt = connection.prepareStatement(sqlReadTransactions);                                    // Using ConnectionDB to prepare the SQL statement
                    rSet = preparedStmt.executeQuery();

                    System.out.println("|    Please see records from the Transaction Log's:    |");                     // Display user message
                    System.out.println("* ---------------------------------------------------- * ");                    // Display spacer

                    while (rSet.next()) {                                                                               // Using a ' while ' loop to gather all info from table
                        trnsID = rSet.getInt("transactionID");                                               // Pulling information from the table and storing in required variables -
                        itemDesc = rSet.getString("itemDescription");
                        unitPrice = rSet.getDouble("UnitPrice");
                        quantitySold = rSet.getFloat("quantitySold");
                        itemQuantity = rSet.getDouble("itemQuantity");
                        String trnsType = rSet.getString("transactionType");
                        String action = rSet.getString("outCome");                                           // Pulling information from the table and storing in required variables end -

                        System.out.println(
                                "    Transaction ID:    " + trnsID + "\n" +                                             // Display user print out of requested information start --
                                        "    Item Description:  " + itemDesc + "\n" +
                                        "    Unit Price:        " + unitPrice + "\n" +
                                        "    Quantity Sold:     " + quantitySold + "\n" +
                                        "    Item Quantity:     " + itemQuantity + "\n" +
                                        "    Transaction Type:  " + trnsType + "\n" +                                   // ' while ' loop stops when there is no more information in the table -
                                        "    Record Action:     " + action + "\n" +                                     // - to iterate over
                                        "* ---------------------------------------------------- *  ");                  // Display user print out of requested information end -- Display spacer
                    }

                    if (trnsID < 0) {                                                                                   // Using 'if' logic when record pull request was successful
                        System.out.println("|  --- Failed to retrieve record! --- ");                                   // Display user message
                        outCome = "FAIL";                                                                               // Variable to indicate Transaction fail
                        oppTypeRead = "Read Record";                                                                    // Variable to indicate Record Type Transaction
                        connection.close();                                                                             // Closing connection to free up resources to database
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeRead, outCome);         // Calling transactionUpdate() Method - To update database
                    }

                    else {
                        System.out.println("|     --- All records retrieved successfully ---       |");                 // Display user message
                        outCome = "PASS";                                                                               // Variable to indicate Transaction success
                        oppTypeRead = "Read Record";                                                                    // Variable to indicate Record Type Transaction
                        connection.close();                                                                             // Closing connection to free up resources to database
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeRead, outCome);         // Calling transactionUpdate() Method - To update database

                    }

                    connection.close();                                                                                 // Closing connection to free up resources to database
                    System.out.println("| ");                                                                           // Display spacer
                    read();                                                                                             // calling search() Method to return to sub-menu

                }

                catch (SQLException e) {                                                                                // In-case of try failure -
                    System.out.println("|   --- Connection to database failed! --- ");                                  // Display message for user
                    throw new RuntimeException(e);                                                                      // Exception thrown
                }
            }
            case 3: {
                System.out.println("* ---------------------------------------------------- * ");
                System.out.println("|           --- Exiting to Main Menu ---               |");                         // Display User message
                MainBuild.home();                                                                                       // Calling home() method to return to home menu
            }

            default:
                System.out.println("Please eneter a valid choice!");
                read();
        }                                                                                                               // End of switch statement

        } catch (InputMismatchException e) {                                                                            // Catch block start -
            System.out.print("Please enter your choice using digets (1 - 3");
            read();
        }                                                                                                               // End of Catch -
    }                                                                                                                   // End of read() method
}                                                                                                                       // End of ReadClass
