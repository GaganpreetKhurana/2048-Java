package Game2048;

import java.util.InputMismatchException;
import java.util.Scanner;

class Game{

    private static void clearConsole(){
        // to clear console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Print intro message again
        System.out.println("Welcome to 2048!!!!");
        System.out.println("Moves: L(Left) R(Right) U(Up) D(Down)\nPress any other key to end :-(\nPress ENTER key after every move");

    }
    
    public static void main(String[] args) {

        // clearConsole();

        // new Board Object
        Board newBoard= new Board();
        

        // Scanner object for input
        Scanner scan = new Scanner(System.in);


        while (!newBoard.checkGameFinish()) {
            try {
                System.out.print("Move: ");
                String input = scan.nextLine();

                // call appropriate function according to input
                switch (input){
                    case "L":
                        newBoard.leftMove();
                        break;
                    case "R":
                        newBoard.rightMove();
                        break;
                    case "U":
                        newBoard.upMove();
                        break;
                    case "D":
                        newBoard.downMove();
                        break;
                    default:
                        // exit
                        newBoard.printBoard();
                        throw new InputMismatchException();
                }

                clearConsole();
                newBoard.printBoard();
            }
            catch (InputMismatchException e) {
                // End game due to invalid input
                clearConsole();

                System.out.print("Invalid Input!!\nThank You for Playing :-)\n");
                newBoard.printBoard();

                scan.close();
                System.exit(0);
            }
        }
        // The game has finished

        clearConsole();

        System.out.print("The End!!\nThank You for Playing :-)\n");
        newBoard.printBoard();

        scan.close();
        System.exit(0);

    }

};