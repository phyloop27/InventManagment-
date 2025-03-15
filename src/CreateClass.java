import java.sql.*;                                                                                                      // Import SQL package (all)
import java.util.InputMismatchException;
import java.util.Scanner;                                                                                               // Import utility package, Scanner Class

public class CreateClass {                                                                                              // Declare & start of Class
    public static void main(String[] args) {                                                                            // Start of Main method
        create();
    }                                                                                                                   // Main Method End

    public static void create() {                                                                                       // Start of Create() Method
        Scanner createText = new Scanner(System.in);                                                                    // Create new Scanner object within the create() method - for Text
        Scanner createNumber = new Scanner(System.in);                                                                  // Create new Scanner object within the create() method - for Numbers

        int userIn_2;                                                                                                   // Declare Integer value to represent user's choice
        String itemDesc = "";                                                                                           // 'String' variable to represent an Item description ( 300 chars )
        double unitPrice = 0;                                                                                           //'Double' variable to represent Unit price £'s + Pence (8/15 decimal digits)
        float quantitySold = 0;
        double itemQuantity = 0;                                                                                        // 'Float' value to represent an items quantity (Seven decimal bits)
        double itemTotValue = 0;                                                                                        //'Double' variable to represent item's in stock total value (8/15 decimal digits)

        System.out.println(
                          "* ---------------------------------------------------- *  \n"                                // Main display to user -
                        + "|          --- Create Record(s) selected ---           | \n"
                        // + "|                --- CREATE METHOD ---                 | \n"                              // Used for testing purpuses ( For ease )
                        + "|------------------------------------------------------| \n"
                        + "|   1. CREATE NEW ITEM RECORD                          | \n"
                        + "|      CREATE NEW ITEM DESCRIPTION                     | \n"
                        + "|      CREATE NEW ITEM UNIT PRICE   (£)                | \n"
                        + "|      CREATE NEW ITEM QUANTITY     (No. of units)     | \n"
                        + "|      CREATE NEW ITEM TOTAL VALUE  (£)                | \n"
                        + "|------------------------------------------------------| \n"
                        + "|   2. EXIT TO MAIN MENU                               | \n"
                        + "* ---------------------------------------------------- * ");                                 // Main display to user end -

        try {
            System.out.print("|   Enter selection: ");                                                                  // Prompting the user for input
            userIn_2 = createNumber.nextInt();                                                                          // Storing user input in variable ' userIn_1 '

            switch (userIn_2) {                                                                                         // future use
                case 1: {
                    System.out.println("|   1. -- Item ID is auto assigned  ");                                         // Prompt user for input
                    //itemID = createNumber.nextInt();                                                                  // Store user input in variable ' itemID '

                    System.out.print("|   2. Enter new item description: ");                                            // Prompt user for input
                    itemDesc = createText.nextLine();                                                                   // Store user input in variable ' itemDesc

                 /*
                 Use nextLine() NOT next() :
                 nextLine() returns all of the inputted string
                 next() only returns the first word
                 */

                    System.out.print("|   3. Enter new item prince (£0.00): ");                                         // Prompt user for input
                    unitPrice = createNumber.nextDouble();                                                              // Store user input in variable ' unitPrice '

                    System.out.print("|   4. Enter new item quantity (0.00): ");                                        // Prompt user for input
                    itemQuantity = createNumber.nextDouble();                                                           // Store user input in variable ' itemQuantity '

                    System.out.print("|   5. Total unit value is calculated automatically ");                           // Prompt user for input

                    itemTotValue = (unitPrice * itemQuantity);                                                          // Calculate variable ' itemTotValue '
                    System.out.println("   | ");                                                                        // Display spacer
                    quantitySold = 0;                                                                                   // Setting variable value as ' 0 ' for table input value

                    try {                                                                                               // Use of try block in case of error connecting
                        Connection connection = DriverManager.getConnection("jdbc:sqlite:InventManagement.db");     // Local database ConnectionDB
                        System.out.println("* ---------------------------------------------------- *");                 // Display spacer
                        System.out.println("|      --- Connection to data-base successful! ---     |");                 // Display message for user
                        System.out.println("|                     *    *    *                      |");                 // Display spacer

                        // CREATING  INVENTORY RECORDS

                        String sqlCreateRecord = " insert into Inventory "                                                       // SQL table insert statement
                                + "(item_id, item_description, unit_price, quantity_Sold, quantity_of_units, total_unit_value)"  // SQL colum for INSERT
                                + " values (?, ?, ?, ?, ?, ?)";                                                                  // SQL placeholder values for INSERT

                        PreparedStatement preparedStmt = connection.prepareStatement(sqlCreateRecord);                   // Using ConnectionDB to prepare the SQL statement
                        //preparedStmt.setInt (1, itemID);                                                               // SQL placeholder 1, value ' itemID ' variable
                        preparedStmt.setString(2, itemDesc);                                               // SQL placeholder 2, value ' itemDesc ' variable
                        preparedStmt.setDouble(3, unitPrice);                                              // SQL placeholder 3, value ' unitPrice ' variable
                        preparedStmt.setFloat(4, quantitySold);                                            // SQL placeholder 4, value ' itemQuantity ' variable
                        preparedStmt.setDouble(5, itemQuantity);                                           // SQL placeholder 5, value ' itemQuantity ' variable
                        preparedStmt.setDouble(6, itemTotValue);                                           // SQL placeholder 6, value ' itemTotValue ' variable

                        int checkUpdateRecord = preparedStmt.executeUpdate();                                           // Code to check whether the record being added was a success

                        if (checkUpdateRecord > 0) {                                                                    // Using 'if' logic when record addition was successful
                            System.out.println("|     --- Record has been created successfully! ---    |");             // Display user message
                            String oppTypeCreate = "Create Record";                                                     // Setting the ' TYPE ' of action being preformed
                            String outCome = "PASS";                                                                    // Variable to indicate Transaction success
                            connection.close();                                                                         // Close DB ConnectionDB to make way for transactionUpdate() DB ConnectionDB
                            // trnsID needs to auto increment *****
                            TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeCreate, outCome);      // Calling transactionUpdate.update() Method - To update database
                        } else {
                            System.out.println("|     --- Failed to create record! --- ");                              // Display user message
                            String oppTypeCreate = "Create Record";                                                     // Setting the ' TYPE ' of action being preformed
                            String outCome = "FAIL";                                                                    // Variable to indicate Transaction fail
                            connection.close();                                                                         // Close DB ConnectionDB to make way for transactionUpdate() DB ConnectionDB
                            // trnsID needs to auto increment *****
                            TransactionUpdate.Update(itemDesc, unitPrice, quantitySold, itemQuantity, oppTypeCreate, outCome);      // Calling transactionUpdate.update() Method - To update database
                        }

                    } catch (SQLException e) {                                                                          // In-case of try failure -
                        System.out.println("|      --- Connection to database failed! ---    |");                       // Display message for user
                        throw new RuntimeException(e);                                                                  // For testing pupose's - Exception thrown
                        //create();                                                                                     // Return to creat() menu to restart

                    } catch (InputMismatchException e) {                                                                // Handeling Data Type mismatch's
                        System.out.println("|   Please enter a valid data type, please try again!  |");                 // User display message
                        create();                                                                                       // Return to creat() menu to restart
                    }
                }

                case 2: {
                    System.out.println("* ---------------------------------------------------- * ");
                    System.out.println("|            --- Exiting to Main Menu ---              | ");                    // Display User message
                    MainBuild.home();                                                                                   // Calling the home() method
                    //break;
                }

                default: {                                                                                              // Default switch case
                    System.out.println("* ---------------------------------------------------- * ");
                    System.out.println("|       --- Invalid input, please try again!---        |");
                    create();
                }
            }                                                                                                           // End of switch

        } catch (InputMismatchException e) {                                                                            // Handeling Data Type mismatch's
            System.out.println("* ---------------------------------------------------- * ");                            // Display spacer
            System.out.println("|  --- Please enter your choice using digets only ---  |");                             // User message
            create();                                                                                                   // Return to creat() menu to restart
        }
    }                                                                                                                   // End of create() method
}                                                                                                                       // End of Class