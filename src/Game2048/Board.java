//package Game2048;

import java.util.Random;
import java.util.Vector;

public class Board {

    private int[][] matrix; // Board matrix
    private Random randomObject;  // To generate a random number


    public Board(){
        // Constructor
        matrix = new int[4][4];
        randomObject= new Random();

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
    private int randomPosition(Vector<Integer> postions){

        // Select a random place for the random number
        int range=postions.size();
        return postions.get(this.randomObject.nextInt(range));
    }
    private void rightMoveRow(int row){
        int j=3; // new location
        for(int i=2;i>=0;i--){
            if(j<=i){
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
            }
            j--;

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
            // insert random number
            int randomPosition= randomPosition(possiblePositions);
            matrix[randomPosition][0]=randomNumber;
        }
    }
    public static void main(String[] args) {
        Board newBoard= new Board();
        newBoard.printBoard();
        newBoard.rightMove();
        newBoard.printBoard();
    }
};