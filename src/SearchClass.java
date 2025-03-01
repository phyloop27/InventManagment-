import java.sql.*;                                                                                                      // Import SQL package's (all)
import java.util.Scanner;                                                                                               // Import utility package, Scanner Class

public class SearchClass {                                                                                              // Declare & start of Class
    public static void main(String[] args) {                                                                            // Inside  Main Method
        search();
    }                                                                                                                   // Main Method End

    public static void search() {                                                                                       // Start of search() method
        Scanner searchNumber = new Scanner(System.in);                                                                  // Creation of scanner object ' searchNumber '
        Scanner searchText = new Scanner(System.in);                                                                    // Creation of scanner object ' searchText '
        PreparedStatement preparedStmt;                                                                                 // Initialise prepared statement ' preparedStmt '
        int userIn_6;                                                                                                   // Initialise variable ' userIn_6 '
        String searchFor;                                                                                               // Initialise variable ' searchFor '
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
                          "* ---------------------------------------------------- * \n"                                 // Main display to user -
                        + "|           --- Search Record(s) selected ---          | \n"
                        // + "|                --- SEARCH METHOD ---                 | \n"                              // Used for testing purpuses ( For ease )
                        + "|------------------------------------------------------| \n"
                        + "|   1. SEARCH INVENTORY RECORD(S)                      | \n"
                        + "|   2. SEARCH TRANSACTION RECORD(S)                    | \n"
                        + "|------------------------------------------------------| \n"
                        + "|   3. EXIT TO MAIN MENU                               | \n"
                        + "* ---------------------------------------------------- * ");                                 // Main display to user end -

        System.out.print("|   Enter selection: ");                                                                      // Prompting the user for input for menu selection
        userIn_6 = searchNumber.nextInt();                                                                              // Storing user input in variable ' userIn_6 '



        switch (userIn_6) {
            case 1: {
                try {                                                                                                   // Use of try block in case of error connecting
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:InventManagement.db");         // Local database ConnectionDB
                    System.out.println("* ---------------------------------------------------- *");                     // Display message for user start -
                    System.out.println("|     --- Connection to data-base successful! ---      |");
                    System.out.println("|                     *    *    *                      |");                     // Display message for user end -

                    String sqlSearch = "select * from Inventory WHERE item_description LIKE ?";                         // Selecting ' all ' (*) from Inventory Table to match query
                    preparedStmt = connection.prepareStatement(sqlSearch);                                              // Using ConnectionDB to prepare the SQL statement

                    System.out.print("|   Enter search description: ");                                                 // Prompting user for search criterea
                    searchFor = searchText.nextLine();                                                                  // Stroing user search criterea in variable ' searchFor '

                    preparedStmt.setString(1, "%" + searchFor + "%");                                   // Setting parameters for DB search ' close too ' set string
                    rSet = preparedStmt.executeQuery();                                                                 // Executing SQL query

                    System.out.println("|   Please see search results from the inventory table:   ");                   // Display user message
                    System.out.println("* ---------------------------------------------------- *  ");                   // Display spacer

                    while (rSet.next()) {                                                                               // Using a ' while ' loop to gather relevent info from table
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
                    // Using 'if' logic when record search request was successful
                    if (item_ID >= 1) {                                                                                 // Code to check whether the record being added was a success
                        System.out.println("|     --- All record(s) retrieved successfully ---     | ");                // Display user message
                        outCome = "PASS";                                                                               // Variable to indicate Transaction success
                        oppTypeRead = "Search Record";                                                                  // Variable to indicate Record Type Transaction
                        connection.close();                                                                             // Closing connection to free up resources to database
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeRead, outCome);         // Calling transactionUpdate.update() Method - To update database

                    } else {
                        System.out.println("|        --- Failed to find record(s)! --- ");                              // Display user message
                        outCome = "FAIL";                                                                               // Variable to indicate Transaction fail
                        oppTypeRead = "Search Record";                                                                  // Variable to indicate Record Type Transaction
                        connection.close();                                                                             // Closing connection to free up resources to database
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeRead, outCome);         // Calling transactionUpdate.update() Method - To update database
                    }

                    connection.close();                                                                                 // Closing ConnectionDB to database
                    System.out.println("| ");                                                                           // Display spacer
                    search();                                                                                           // calling search() Method to return to sub-menu

                } catch (
                        SQLException e) {                                                                               // In-case of try failure -
                    System.out.println("|         --- Connection to database failed! ---      |");                      // Display message for user
                    throw new RuntimeException(e);                                                                      // Exception thrown
                }
                search();                                                                                               // Return to seach() menu
            }

            case 2: {
                try {                                                                                                   // Use of try block in case of error connecting
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:InventManagement.db");         // Local database Connection
                    System.out.println("* ---------------------------------------------------- *");                     // Display message for user start -
                    System.out.println("|      --- Connection to data-base successful! ---     |");
                    System.out.println("|                     *    *    *                      |");                     // Display message for user end -

                    String sqlSearch = "select * from Transactions WHERE itemDescription LIKE ? ";                      // Selecting ' all ' (*) from Transaction Table to match query
                    preparedStmt = connection.prepareStatement(sqlSearch);                                              // Using ConnectionDB to prepare the SQL statement

                    System.out.print("|   Enter search description: ");                                                 // Prompting user for search criterea
                    searchFor = searchText.nextLine();                                                                  // Stroing user search criterea in variable ' searchFor '

                    preparedStmt.setString(1, "%" + searchFor + "%");                                   // Setting parameters for search ' close too ' set string
                    rSet = preparedStmt.executeQuery();                                                                 // Executing SQL query

                    System.out.println("| Please see search results from the Transaction Log's:  ");                    // Display user message
                    System.out.println("* ---------------------------------------------------- * ");                    // Display spacer

                    while (rSet.next()) {                                                                               // Using a ' while ' loop to gather relevent info from table
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
                        oppTypeRead = "Search Record";                                                                  // Variable to indicate Record Type Transaction
                        connection.close();                                                                             // Closing connection to free up resources to database
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeRead, outCome);         // Calling transactionUpdate() Method - To update database
                    } else {
                        System.out.println("|     --- All records retrieved successfully ---       |");                 // Display user message
                        outCome = "PASS";                                                                               // Variable to indicate Transaction success
                        oppTypeRead = "Search Record";                                                                  // Variable to indicate Record Type Transaction
                        connection.close();                                                                             // Closing connection to free up resources to database
                        TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeRead, outCome);         // Calling transactionUpdate() Method - To update database

                    }

                    connection.close();                                                                                 // Closing connection to free up resources to database
                    System.out.println("| ");                                                                           // Display spacer
                    search();                                                                                           // calling search() Method to return to sub-menu

                } catch (
                        SQLException e) {                                                                                // In-case of try failure -
                    System.out.println("|   --- Connection to database failed! --- ");                                  // Display message for user
                    throw new RuntimeException(e);                                                                      // Exception thrown
                }
            }
            case 3: {
                System.out.println("* ---------------------------------------------------- * ");
                System.out.println("|           --- Exiting to Main Menu ---               |");                         // Display User message
                MainBuild.home();                                                                                       // Calling home() method to return to home menu
            }

        }                                                                                                               // End of switch statement
    }                                                                                                                   // End of main mathod
}                                                                                                                       // End of Class