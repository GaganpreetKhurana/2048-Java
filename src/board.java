package Game2048;
import java.util.Random;
import java.util.Vector;

public class board {
    public int matrix[4][4];
    Random randomObject;
    board(){
        matrix = new int[4][4];
        maxRandomNumber=2;
        randomObject= new Random();

    }
    void printBoard(){
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
    }
    int randomNumber(){
        if(this.randomObject.nextBoolean()){
            return 2;
        }
        else{
            return 4;
        }
    }
    int randomPosition(Vector<int> postions){
        int range=postions.size();
        return postions[this.randomObject.nextInt(range)];
    }
    void rightMoveRow(int row){
        int j=3;
        for(int i=2;i>=0;i--){
            if(j==i){
                break;
            }
            while(matrix[row][j]!=0 && matrix[row][j]!=matrix[row][i]){
                j--;
            }
            if(matrix[row][j]==matrix[row][i]){
                matrix[row][j]*=2;
            }
            else{
                matrix[row][j]=matrix[row][i];
            }
            matrix[row][i]=0;
            j--;
        }
    }
    void rightMove(){
        for(int i=0;i<4;i++){
            rightMoveRow(i);
        }
        int randomNumber=randomNumber();
        Vector<int> possiblePositions = new Vector<int>();
        for(int i=0;i<4;i++){
            if(matrix[i][0]==0){
                possiblePositions.append(i);
            }
        }
        if(possiblePositions.size()>0){
            int randomPosition= randomPosition(possiblePositions);
            matrix[randomPosition][0]=randomNumber;
        }
    }
}