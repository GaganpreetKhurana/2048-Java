//package Game2048;

import java.util.LinkedList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class Board {

    private int[][] matrix; // Board matrix
    private Random randomObject;  // To generate a random number
    private int score; // To maintain score
    private LinkedList<Integer> emptyCells; // List of empty cells

    public Board(){
        // Constructor

        randomObject= new Random(); // initialize the random object

        matrix = new int[4][4]; // matrix initialized
        
        
        
        // add empty cells
        emptyCells = new LinkedList<Integer>();
        for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                emptyCells.addLast(i*4+j);
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
        int randomPairPosition=this.randomObject.nextInt(range);

        matrix[emptyCells.get(randomPairPosition)/4][emptyCells.get(randomPairPosition)%4]=(range==16?2:randomNumber());
        emptyCells.remove(randomPairPosition);
    }
    
    
    private boolean checkLocations(int step,int oldLocation,int newLocation){
        if(step==-1){
            return oldLocation>=newLocation;
        }
        return newLocation>=oldLocation;
    }

    private void removeEmptyCell(int cell){
        for (int index=0;index<emptyCells.size();index++){
            if(emptyCells.get(index)==cell){
                emptyCells.remove(index);
                break;
            }
        }
    }
    
    
    private void moveSingleRow(int row, int newLocation, int step ){
        int oldLocation = newLocation+step;
        while(oldLocation< 4 && oldLocation>-1){
            if( matrix[row][oldLocation]==0){
                oldLocation+=step;
                continue;
            }
            while(!checkLocations(step,oldLocation,newLocation) && matrix[row][newLocation]!=0 && matrix[row][newLocation]!=matrix[row][oldLocation]){
                // to find next location
                // cell should be empty
                // cell value != current value
                newLocation+=step;
            }

            if(!checkLocations(step,oldLocation,newLocation)){
                if(matrix[row][newLocation]==0){
                    removeEmptyCell(row*4+newLocation);
                }
                if (matrix[row][newLocation] == matrix[row][oldLocation]) {
                    // new cell and current cell values are same
                    matrix[row][newLocation] *= 2;
                    score+= matrix[row][newLocation];
                    newLocation+=step;

                } else {
                    matrix[row][newLocation]=matrix[row][oldLocation];
                }
                matrix[row][oldLocation] = 0;
                emptyCells.add(row*4+oldLocation);
            }
            else{
                newLocation=oldLocation;
            }
            oldLocation+=step;

        }
    }
    
    public void rightMove(){
        for(int i=0;i<4;i++){
            // Perform a right move for each row
            moveSingleRow(i,3,-1);
        }

        // insert random number at an empty cell;
        insertRandomNumber();
    }

    public void leftMove(){
        for(int i=0;i<4;i++){
            // Perform a left move for each row
            moveSingleRow(i,0,1);
        }

        // insert random number at an empty cell;
        insertRandomNumber();
    }
    

    private void moveSingleColumn(int column, int newLocation, int step ){
        int oldLocation = newLocation+step;
        while(oldLocation< 4 && oldLocation>-1){
            if( matrix[oldLocation][column]==0 ){
                oldLocation+=step;
                continue;
            }
            while(!checkLocations(step,oldLocation,newLocation) && matrix[newLocation][column]!=0 && matrix[newLocation][column]!=matrix[oldLocation][column]){
                // to find next location
                // cell should be empty
                // cell value != current value
                newLocation+=step;
            }
            if(!checkLocations(step,oldLocation,newLocation)){
                if(matrix[newLocation][column]==0){
                    removeEmptyCell(newLocation*4+column);
                }
                if (matrix[newLocation][column] == matrix[oldLocation][column]) {
                    // new cell and current cell values are same
                    matrix[newLocation][column] *= 2;
                    score+= matrix[newLocation][column];
                    newLocation+=step;

                } else {
                    matrix[newLocation][column]=matrix[oldLocation][column];
                }
                matrix[oldLocation][column] = 0;
                emptyCells.add(oldLocation * 4 + column);
            }else{
                newLocation=oldLocation;
            }
            oldLocation+=step;
        }
    }
    
    public void upMove(){
        for(int i=0;i<4;i++){
            // Perform a up move for each column
            moveSingleColumn(i,0,1);
        }

        // insert random number at an empty cell;
        insertRandomNumber();
    }
    
    public void downMove(){
        for(int i=0;i<4;i++){
            // Perform a down move for each column
            moveSingleColumn(i,3,-1);
        }
        // insert random number at an empty cell;
        insertRandomNumber();
    }

    public boolean checkGameFinish(){
        return emptyCells.size()==0;
    }

    private static void clearConsole(){
        // to clear console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Welcome to 2048!!!!");
        System.out.println("Moves: L(Left) R(Right) U(Up) D(Down)\nPress any other key to end :-(\nPress ENTER key after every move");

    }



    public static void main(String[] args) {

        clearConsole();

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
                clearConsole();
                System.out.print("Invalid Input!!\nThank You for Playing :-)\n");
                newBoard.printBoard();
                scan.close();
                System.exit(0);
            }
        }
        clearConsole();
        System.out.print("The End!!\nThank You for Playing :-)\n");
        newBoard.printBoard();
        scan.close();
        System.exit(0);

    }
};