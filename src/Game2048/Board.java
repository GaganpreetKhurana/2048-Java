//package Game2048;

import java.util.LinkedList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class Board {

    private int[][] matrix; // Board matrix
    private Random randomObject;  // To generate a random number
    private int score; // To maintain score
    private LinkedList<int[]> emptyCells; 

    public Board(){
        // Constructor

        randomObject= new Random(); // initialize the random object

        matrix = new int[4][4]; // matrix initialized
        
        
        
        // add empty cells
        emptyCells = new LinkedList<int[]>();
        int [] pair = new int[2];
        for (int i=0;i<4;i++){
            pair[0]=i;
            for(int j=0;j<4;j++){
                pair[1]=j;
                emptyCells.addLast(pair);
            }
        }

        // insert random number at an empty cell;
        insertRandomNumber();

        score = 0;

        // print initial board
        printBoard();

    }
    
    public void printBoard(){
        // To print the matrix to console
        System.out.println("---- ---- ---- ----");
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(matrix[i][j]!=0) {
                    System.out.print(matrix[i][j]);
                }
                else{
                    System.out.print("____");
                }
                System.out.print("|");
            }
            System.out.println("");
            System.out.println("---- ---- ---- ----");
        }
        System.out.println();

        // print score
        System.out.print("Score: ");
        System.out.println(score);
        System.out.println();
    }
    
    private int randomNumber(){
        // To generate a random number
        if(this.randomObject.nextBoolean()){
            return 2;
        }
        else{
            return 4;
        }
    }
    
    private void insertRandomNumber(){

        // Select a random place for the random number
        int range=emptyCells.size();
        int randomPairPosition =this.randomObject.nextInt(range);
        matrix[emptyCells.get(randomPairPosition)[0]][emptyCells.get(randomPairPosition)[1]]=range==16?2:randomNumber();
        emptyCells.remove(randomPairPosition);
    }
    
    private void rightMoveRow(int row){
        int j=3; // new location
        for(int i=2;i>=0;i--){
            if(j<=i || matrix[row][i]==0){
                continue;
            }
            while(j>i && matrix[row][j]!=0 && matrix[row][j]!=matrix[row][i]){
                // to find next location
                // cell should be empty
                // cell value != current value
                j--;
            }
            if (j>i) {
                if (matrix[row][j] == matrix[row][i]) {
                    // new cell and current cell values are same
                    matrix[row][j] *= 2;
                    score+= matrix[row][j];
                } else {
                    matrix[row][j] = matrix[row][i];
                }
                matrix[row][i] = 0;
                j--;
            }


        }
    }
    public void rightMove(){
        for(int i=0;i<4;i++){
            // Perform a right move for each row
            rightMoveRow(i);
        }

        // insert random number at an empty cell;
        insertRandomNumber();
    }

    private void leftMoveRow(int row){
        int j=0; // new location
        for(int i=1;i<4;i++){
            if(j>=i || matrix[row][i]==0){
                continue;
            }
            while(j<i && matrix[row][j]!=0 && matrix[row][j]!=matrix[row][i]){
                // to find next location
                // cell should be empty
                // cell value != current value
                j++;
            }
            if (j<i) {
                if (matrix[row][j] == matrix[row][i]) {
                    // new cell and current cell values are same
                    matrix[row][j] *= 2;
                    score+= matrix[row][j];
                } else {
                    matrix[row][j] = matrix[row][i];
                }
                matrix[row][i] = 0;
                j++;
            }


        }
    }
    public void leftMove(){
        for(int i=0;i<4;i++){
            // Perform a left move for each row
            leftMoveRow(i);
        }

        // insert random number at an empty cell;
        insertRandomNumber();
    }
    
    private void upMoveColumn(int column){
        int j=0; // new location
        for(int i=1;i<4;i++){
            if(j>=i || matrix[i][column]==0){
                continue;
            }
            while(j<i && matrix[j][column]!=0 && matrix[j][column]!=matrix[i][column]){
                // to find next location
                // cell should be empty
                // cell value != current value
                j++;
            }
            if (j<i) {
                if (matrix[j][column] == matrix[i][column]) {
                    // new cell and current cell values are same
                    matrix[j][column] *= 2;
                    score+= matrix[j][column];
                } else {
                    matrix[j][column] = matrix[i][column];
                }
                matrix[i][column] = 0;
                j++;
            }


        }
    }
    public void upMove(){
        for(int i=0;i<4;i++){
            // Perform a up move for each column
            upMoveColumn(i);
        }

        // insert random number at an empty cell;
        insertRandomNumber();
    }
    
    private void downMoveColumn(int column){
        int j=3; // new location
        for(int i=2;i>=0;i--){
            if(j<=i || matrix[i][column]==0){
                continue;
            }
            while(j>i && matrix[j][column]!=0 && matrix[j][column]!=matrix[i][column]){
                // to find next location
                // cell should be empty
                // cell value != current value
                j--;
            }
            if (j>i) {
                if (matrix[j][column] == matrix[i][column]) {
                    // new cell and current cell values are same
                    matrix[j][column] *= 2;
                    score+= matrix[j][column];
                } else {
                    matrix[j][column] = matrix[i][column];
                }
                matrix[i][column] = 0;
                j--;
            }


        }
    }
    public void downMove(){
        for(int i=0;i<4;i++){
            // Perform a down move for each column
            downMoveColumn(i);
        }

        // insert random number at an empty cell;
        insertRandomNumber();
    }

    private void clearConsole(){
        // to clear console
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {

        // new Board Object
        Board newBoard= new Board();
        newBoard.clearConsole();

        // Scanner object for input
        Scanner scan = new Scanner(System.in);


        while (true) {
            try {
                
                String input = scan.nextLine();
                newBoard.clearConsole();

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
                newBoard.printBoard();
            }
            catch (InputMismatchException e) {
                newBoard.clearConsole();
                System.out.print("Thank You for Playing :-)\n");
                newBoard.printBoard();
                scan.close();
                break;
            }
        }

    }
};