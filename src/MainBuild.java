// Introduction to Programming 15/01/2025 - Inventory Management Assignment                                             // Project information & start date - For referance -
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
                + "|------------------------------------------------------| \n"
                + "|   5. EXIT PROGRAM                                    | \n"
                + "* ---------------------------------------------------- *  ");                                        // Display user message end -

        int userIn_1;                                                                                                   // Declare variable ' userIn_0 '
        System.out.print("|   Enter selection: ");                                                                      // Prompting the user for input
        userIn_1 = input.nextInt();                                                                                     // Assign user input to Variable ' userIn_0 '
        //System.out.println("** Testing ** Choice entered: " + userIn_1);                                              // Test purposes only ***

        switch (userIn_1) {
            case 1: {                                                                                                   // Directing Flow control of the program -
                CreateClass.create();                                                                                   // Jumps to Create Record Class
                break;                                                                                                  // ' break ' out of switch statement
            }
            case 2: {                                                                                                   // Directing Flow control of the program -
                ReadClass.read();                                                                                       // Jumps to Read Record Class
                break;                                                                                                  // ' break ' out of switch statement
            }
            case 3: {                                                                                                   // Directing Flow control of the program -
                UpdateClass.update();                                                                                   // Jumps to Update Record Class
                break;                                                                                                  // ' break ' out of switch statement
            }
            case 4: {                                                                                                   // Directing Flow control of the program -
                DeleteClass.Delete();                                                                                   // Jumps to Delete Record Class
                break;                                                                                                  // ' break ' out of switch statement
            }
            case 5: {                                                                                                   // Directing Flow control of the program -
                System.out.println("* ---------------------------------------------------- *  ");                       // Display exit message to user start -
                System.out.println("|              --- Thank you for using ---             | ");
                System.out.println("|        --- The Inventory Management System ---       | ");
                System.out.println("|                   --- Good Bye ---                   | ");
                System.out.println("* ---------------------------------------------------- *  ");                       // Display exit message to user end -
                System.exit(0);                                                                                   // Code to terminate program
            }
            default: {                                                                                                   // Default switch case is ALWAYS Executed
                System.out.print("<<< NEED TO ADD A ' CLEAR SCREEN FUNCTION ' HERE >>> ");
                break;                                                                                                   // ' break ' out of switch statement
            }
        }                                                                                                               // End of switch statement
    }                                                                                                                   // End of Home Method
}                                                                                                                       // End of MainBuild() Class
