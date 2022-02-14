import java.util.Random;
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
            return 3;
        }
        else{
            return 4;
        }
    }
    int randomPosition(Vector<int> postions){
        int range=postions.size();
        return postions[this.randomObject.nextInt(range)];
    }
}