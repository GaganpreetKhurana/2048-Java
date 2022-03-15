//package Game2048;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Board {

    private int[][] matrix; // Board matrix
    private Random randomObject;  // To generate a random number


    public Board(){
        // Constructor
        matrix = new int[4][4];
        matrix[0][0]=2;
        randomObject= new Random();
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
//        System.out.println(possiblePositions.toString());
        if(possiblePositions.size()>0){
            // insert random number
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
                } else {
                    matrix[row][j] = matrix[row][i];
                }
                matrix[row][i] = 0;
                j++;
            }


        }
    }
    public void LeftMove(){
        for(int i=0;i<4;i++){
            // Perform a right move for each row
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
//        System.out.println(possiblePositions.toString());
        if(possiblePositions.size()>0){
            // insert random number
            int randomPosition= randomPosition(possiblePositions);
            matrix[randomPosition][3]=randomNumber;
        }
    }
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Board newBoard= new Board();
//        newBoard.printBoard();

        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                String input = scan.nextLine();
                switch (input){
                    case "L":
                        newBoard.LeftMove();
                        break;
                    case "R":
                        newBoard.rightMove();
                        break;
                    default:
                        newBoard.printBoard();
                        throw new InputMismatchException();
                }
                newBoard.printBoard();
            }
            catch (InputMismatchException e) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.print("Bye\n");
                newBoard.printBoard();
                scan.close();
                break;
            }
        }

    }
};