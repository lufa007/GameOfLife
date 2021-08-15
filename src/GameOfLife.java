public class GameOfLife{
    private int height;//对应行
    private int width;//对应列
    private int[][]board;

    GameOfLife(int height,int width){
        this.height=height;
        this.width=width;
        board=new int[height][width];
    }
    public void setAlive(int x,int y){
        this.board[x][y]=1;
    }
    public void setDead(int x,int y){
        this.board[x][y]=0;
    }
    public void printBoard(){
        System.out.println("_______");
        for(int i=0;i<this.height;i++){
            System.out.print('|');
            for (int j=0;j<this.width;j++){

                if(this.board[i][j]==0){
                    System.out.print('.');
                }
                else {
                    System.out.print('*');
                }
            }
            System.out.print("|\n");
        }
        //System.out.println("_______");
    }
    public int getState(int x, int y){
        if(x<0 || x>=this.height){
            return 0;
        }
        if(y<0 || y>=this.width){
            return 0;
        }
        return this.board[x][y];
    }
    public int countNeiborAlive(int x,int y){
        int count=0;
        count+= getState(x-1,y-1);
        count+= getState(x,y-1);
        count+= getState(x+1,y-1);

        count+= getState(x-1,y);
        count+= getState(x+1,y);

        count+= getState(x-1,y+1);
        count+= getState(x,y+1);
        count+= getState(x+1,y+1);
        return count;
    }
    public void step(){
        int[][]newBoard=new int[this.height][this.width];
        for(int i=0;i<this.height;i++){
            for (int j=0;j<this.width;j++) {
                int neibors=countNeiborAlive(i,j);
                if(getState(i,j)==1){
                    if(neibors<2) newBoard[i][j]=0;
                    else if(neibors<4) newBoard[i][j]=1;
                    else newBoard[i][j]=0;
                }
                else {
                    if(neibors>2) newBoard[i][j]=1;
                    else newBoard[i][j]=0;
                }
            }
        }
        this.board=newBoard;
    }

    public static void main(String[] args) {
        GameOfLife gameOfLife=new GameOfLife(4,9);
        gameOfLife.setAlive(1,1);
        gameOfLife.setAlive(1,2);
        gameOfLife.setAlive(1,3);
        for(int i=0;i<4;i++){
            gameOfLife.printBoard();
            gameOfLife.step();
        }


        //System.out.println(gameOfLife.countNeiborAlive(1,0));
    }
}