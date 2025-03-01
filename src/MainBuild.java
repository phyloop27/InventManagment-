// Introduction to Programming 15/01/2025 - Inventory Management Assignment                                             // Project information & start date - For referance -
import java.util.InputMismatchException;
import java.util.Scanner;                                                                                               // Import utility package, Scanner Class

public class MainBuild {                                                                                                // Declare & start of Class
    public static void main(String[] args) {                                                                            // Start of Main method
        home();
    }                                                                                                                   // Main Method End                                                                                                    // End of Main Method

    public static void home() {                                                                                         // Inside home() Method
        Scanner input = new Scanner(System.in);                                                                         // Create Scanner object ' input '
        System.out.println(
                 "* ---------------------------------------------------- *  \n"                                         // Display user message start -
                + "|  --- WELCOME TO YOUR INVENTORY MANAGEMENT SYSTEM --- | \n"
               // + "|                 --- HOME METHOD ---                  | \n"                                       // Used for testing purpuses ( For ease )
                + "|------------------------------------------------------| \n"
                + "|   1. CREATE NEW ITEM RECORD                          | \n"
                + "|   2. READ AN ITEM RECORD                             | \n"
                + "|   3. UPDATE EXISTING ITEM RECORD                     | \n"
                + "|   4. DELETE AN ITEM RECORD                           | \n"
                + "|   5. SEARCH FOR A RECORD                             | \n"
                + "|------------------------------------------------------| \n"
                + "|   6. EXIT PROGRAM                                    | \n"
                + "* ---------------------------------------------------- *  ");                                        // Display user message end -

        int userIn_1;                                                                                                   // Declare variable ' userIn_0 '
        System.out.print("|   Enter selection: ");                                                                      // Prompting the user for input

        try {                                                                                                           // Using a try & catch block to handle incorrect data type inputs
            userIn_1 = input.nextInt();                                                                                 // Assign user input to Variable ' userIn_1 '
            //System.out.println("** Testing ** Choice entered: " + userIn_1);                                          // Test purposes only ***

            switch (userIn_1) {
                case 1: {                                                                                               // Directing Flow control of the program -
                    CreateClass.create();                                                                               // Jumps to Create Record Class
                    break;                                                                                              // ' break ' out of switch statement
                }
                case 2: {                                                                                               // Directing Flow control of the program -
                    ReadClass.read();                                                                                   // Jumps to Read Record Class
                    break;                                                                                              // ' break ' out of switch statement
                }
                case 3: {                                                                                               // Directing Flow control of the program -
                    UpdateClass.update();                                                                               // Jumps to Update Record Class
                    break;                                                                                              // ' break ' out of switch statement
                }
                case 4: {                                                                                               // Directing Flow control of the program -
                    DeleteClass.Delete();                                                                               // Jumps to Delete Record Class
                    break;                                                                                              // ' break ' out of switch statement
                }
                case 5: {                                                                                               // Directing Flow control of the program -
                    SearchClass.search();                                                                               // Jumps to Delete Record Class
                    break;                                                                                              // ' break ' out of switch statement
                }
                case 6: {
                    System.out.println("* ---------------------------------------------------- *  ");                   // Display exit message to user start -
                    System.out.println("|              --- Thank you for using ---             | ");
                    System.out.println("|        --- The Inventory Management System ---       | ");
                    System.out.println("|                   --- Good Bye ---                   | ");
                    System.out.println("* ---------------------------------------------------- *  ");                   // Display exit message to user end -
                    System.exit(0);                                                                               // Code to terminate program
                }
                default: {                                                                                              // Default switch case is ALWAYS Executed
                    System.out.println("Invalid answer, please try again! ");                                           // Triggers if a number > 6 is entered
                    MainBuild.home();                                                                                   // Restarts the main home() menu input again
                    break;                                                                                              // ' break ' out of switch statement
                }
            }                                                                                                           // End of switch statement

        } catch(InputMismatchException e) {                                                                             // Catches error for incorrect data type input
                e.printStackTrace(System.err);
                System.out.println("Invailid charictor input, numbers only please!");                                   // Error message
                MainBuild.home();                                                                                       // Restarts the main home() menu input again
        }
    }                                                                                                                   // End of Home Method
}                                                                                                                       // End of MainBuild() Class
