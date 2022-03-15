package Game2048;

import java.util.LinkedList;
import java.util.Random;

public class Board {

    private int[][] matrix; // Board matrix
    private Random randomObject;  // To generate a random number
    private int score; // To maintain score
    private LinkedList<Integer> emptyCells; // List of empty cells stored in the format: row * 4 + column

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

        // insert random number (2) at an empty cell;
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
        // insert a random new number at a random empty cell

        // Select a random place for the random number
        int range=emptyCells.size();
        int randomPairPosition=this.randomObject.nextInt(range);

        matrix[emptyCells.get(randomPairPosition)/4][emptyCells.get(randomPairPosition)%4]=(range==16?2:randomNumber());
        emptyCells.remove(randomPairPosition);
    }
    
    
    private boolean checkLocations(int step,int oldLocation,int newLocation){
        // to check conditions for moves
        if(step==-1){
            return oldLocation>=newLocation;
        }
        return newLocation>=oldLocation;
    }

    private void removeEmptyCell(int cell){
        // remove empty cell from the list

        for (int index=0;index<emptyCells.size();index++){
            if(emptyCells.get(index)==cell){
                emptyCells.remove(index);
                break;
            }
        }
    }
    
    
    private void moveSingleRow(int row, int newLocation, int step ){
        // perform moves for single rows
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
        // perform a right move

        for(int i=0;i<4;i++){
            // Perform a right move for each row
            moveSingleRow(i,3,-1);
        }

        // insert random number at an empty cell;
        insertRandomNumber();
    }

    public void leftMove(){
        // perform a left move

        for(int i=0;i<4;i++){
            // Perform a left move for each row
            moveSingleRow(i,0,1);
        }

        // insert random number at an empty cell;
        insertRandomNumber();
    }
    

    private void moveSingleColumn(int column, int newLocation, int step ){
        // perform column moves

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
        // To perform an up move

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
        // to check if the game has ended
        if(emptyCells.size()>0){
            // There are some empty cells
            return false;
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                // check if any pair of adjacent cells are equal
                if(i>0 && matrix[i][j]==matrix[i-1][j]){return false;}
                if(i<4 && matrix[i][j]==matrix[i+1][j]){return false;}
                if(j>0 && matrix[i][j]==matrix[i][j+1]){return false;}
                if(j<4 && matrix[i][j]==matrix[i][j+1]){return false;}
            }
        }
        return true;
    }    
};