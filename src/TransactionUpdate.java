import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionUpdate {                                                                                        // Declare & start of Class

    public static void Update(String itemDesc, double unitPrice, float quantitySold, double itemQuantity, String oppTypeUpdate, String outCome) {

        //  UPDATING TRANSACTION RECORDS                                                                                 // Start of transactionUpdate() Method

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:InventManagement.db");                  // Local database ConnectionDB
            System.out.println("|                     *    *    *                      |");                              // Display user message

            String sqlTransactionUpdate = " insert into Transactions "
                    + "(transactionID, itemDescription, unitPrice, quantitySold, itemQuantity, transactionType, outCome)"
                    + "values (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(sqlTransactionUpdate);                         // Using ConnectionDB to prepare the SQL statement

            //preparedStmt.setInt (1, trnsID);                                                                           // SQL placeholder 1 - transactionID is autoIncremented
            preparedStmt.setString (2, itemDesc);                                                          // SQL placeholder 2, value ' itemDesc ' variable
            preparedStmt.setDouble (3, unitPrice);                                                         // SQL placeholder 3, value ' unitPrice ' variable
            preparedStmt.setFloat (4, quantitySold);                                                       // SQL placeholder 4, value ' quantitySold ' variable
            preparedStmt.setDouble (5, itemQuantity);                                                      // SQL placeholder 5, value ' itemQuantity ' Variable
            preparedStmt.setString (6, oppTypeUpdate);                                                     // SQL placeholder 6, value 'appTypeUpdate ' variable
            preparedStmt.setString (7, outCome);                                                           // SQL placeholder 7, value ' outCome ' variable

            int checkUpdateTrns = preparedStmt.executeUpdate();                                                          // Code to check whether the record being added was a success

            if (checkUpdateTrns > 0) {                                                                                   // Using 'if' logic when record addition was successful
                System.out.println("|   --- Transaction has been updated successfully! --- |");                          // Display user message
                connection.close();                                                                                      // Close DB Connection to make way for transactionUpdate()
                MainBuild.home();                                                                                        // Return back to home() due to record success
            }
            else {
                System.out.println("|     --- Failed to update Transaction! --- ");                                      // Display user message
                connection.close();                                                                                      // Close DB Connection to make way for transactionUpdate()
                MainBuild.home();                                                                                        // Return back to home()
            }

        } catch (SQLException e) {                                                                                       // Error catching -
            System.out.print("Fater error, please contact system administrator!");
            throw new RuntimeException(e);                                                                               // Error Throwing -
        }
    }
}
