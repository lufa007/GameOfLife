public class Simulation {

    int width;
    int height;
    int[][]board;
    public static int ALIVE=1;
    public static int DEAD=0;


    Simulation(int width, int height){
        this.width=width;
        this.height=height;
        board=new int[width][height];
    }
    public void setAlive(int x,int y){
        this.setState(x,y,1);
    }
    public void setDead(int x,int y){
        this.setState(x,y,0);
    }
    public void printBoard(){
        System.out.println("_______");
        for(int y=0;y<this.height;y++){
            System.out.print('|');
            for (int x=0;x<this.width;x++){

                if(this.board[x][y]==DEAD){
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
    public void setState(int x,int y,int state){
        if(y<0 || y>=this.height){
            return ;
        }
        if(x<0 || x>=this.width){
            return ;
        }
        this.board[x][y]=state;
    }
    public int getState(int x, int y){
        if(y<0 || y>=this.height){
            return DEAD;
        }
        if(x<0 || x>=this.width){
            return DEAD;
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
        int[][]newBoard=new int[this.width][this.height];
        for(int y=0;y<this.height;y++){
            for (int x=0;x<this.width;x++) {
                int neibors=countNeiborAlive(x,y);
                if(getState(x,y)==ALIVE){
                    if(neibors<2) newBoard[x][y]=DEAD;
                    else if(neibors<4) newBoard[x][y]=ALIVE;
                    else newBoard[x][y]=DEAD;
                }
                else {
                    if(neibors>2) newBoard[x][y]=ALIVE;
                    else newBoard[x][y]=DEAD;
                }
            }
        }
        this.board=newBoard;
    }

    public static void main(String[] args) {
        Simulation simulation =new Simulation(5,9);
        simulation.setAlive(1,2);
        simulation.setAlive(2,2);
        simulation.setAlive(3,2);
        for(int i=0;i<4;i++){
            simulation.printBoard();
            simulation.step();
        }


        //System.out.println(gameOfLife.countNeiborAlive(1,0));
    }


}