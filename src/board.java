public class board {
    public int matrix[4][4];
    Random randomObject;
    int maxRandomNumber;
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
        int random;
        return random;
    }
}