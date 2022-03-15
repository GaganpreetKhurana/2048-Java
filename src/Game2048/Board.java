//package Game2048;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Board {

    private int[][] matrix; // Board matrix
    private Random randomObject;  // To generate a random number
    private int score; // To maintain score

    public Board(){
        // Constructor

        randomObject= new Random(); // initialize the random object

        matrix = new int[4][4]; // matrix initialized
        
        // For generating a random Column and Row for initialization
        Vector<Integer> positions = new Vector<Integer>();
        for(int index=0;index<4;index++){
            positions.add(index);
        }
        int randomRow = randomPosition(positions);
        int randomColumn = randomPosition(positions);
        matrix[randomRow][randomColumn]=2;
        
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
    
    private int randomPosition(Vector<Integer> positions){

        // Select a random place for the random number
        int range=positions.size();
        return positions.get(this.randomObject.nextInt(range));
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

        // generate a new random number
        int randomNumber=randomNumber();

        // positions for random number
        Vector<Integer> possiblePositions = new Vector<Integer>();
        for(int i=0;i<4;i++){
            if(matrix[i][0]==0){ // empty cells in column 0
                possiblePositions.add(i);
            }
        }
        if(possiblePositions.size()>0){
            // insert random number in empty cell in first column
            int randomPosition= randomPosition(possiblePositions);
            matrix[randomPosition][0]=randomNumber;
        }
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

        // generate a new random number
        int randomNumber=randomNumber();

        // positions for random number
        Vector<Integer> possiblePositions = new Vector<Integer>();
        for(int i=0;i<4;i++){
            if(matrix[i][3]==0){ // empty cells in column 0
                possiblePositions.add(i);
            }
        }
        if(possiblePositions.size()>0){
            // insert random number in empty cell in last column
            int randomPosition= randomPosition(possiblePositions);
            matrix[randomPosition][3]=randomNumber;
        }
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

        // generate a new random number
        int randomNumber=randomNumber();

        // positions for random number
        Vector<Integer> possiblePositions = new Vector<Integer>();
        for(int i=0;i<4;i++){
            if(matrix[3][i]==0){ // empty cells in column 0
                possiblePositions.add(i);
            }
        }
        if(possiblePositions.size()>0){
            // insert random number in empty cell in last row
            int randomPosition= randomPosition(possiblePositions);
            matrix[3][randomPosition]=randomNumber;
        }
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

        // generate a new random number
        int randomNumber=randomNumber();

        // positions for random number
        Vector<Integer> possiblePositions = new Vector<Integer>();
        for(int i=0;i<4;i++){
            if(matrix[0][i]==0){ // empty cells in column 0
                possiblePositions.add(i);
            }
        }
        if(possiblePositions.size()>0){
            // insert random number at an empty place in first row
            int randomPosition= randomPosition(possiblePositions);
            matrix[0][randomPosition]=randomNumber;
        }
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