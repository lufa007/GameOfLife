
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;


public class MainView extends VBox {

    private Canvas canvas;
    private Simulation simulation;
    private Affine affine;
    private int drawMode=Simulation.ALIVE;
    public MainView() {
//        this.stepButton=new Button("step");
//        this.stepButton.setOnAction(actionEvent->{
//            simulation.step();
//            draw();
//        });
        this.canvas=new Canvas(400,400);
        this.canvas.setOnMousePressed(this::hadleDraw);
        this.canvas.setOnMouseDragged(this::hadleDraw);
        this.setOnKeyPressed(this::onKeyPressed);

        Toolbar toolbar=new Toolbar(this);
        this.getChildren().addAll(toolbar,this.canvas);

        this.simulation=new Simulation(10,10);

        affine=new Affine();
        affine.appendScale(400/10f,400/10f);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.D){
            this.drawMode=Simulation.ALIVE;
            System.out.println("Draw Mode:");
        }else if(keyEvent.getCode()== KeyCode.E){
            this.drawMode=Simulation.DEAD;
            System.out.println("Erase Mode:");
        }

    }

    private void hadleDraw(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();
        try {
            Point2D simCoord = this.affine.inverseTransform(mouseX, mouseY);
            //System.out.println(simCoord.getX()+","+simCoord.getY());
            int simX= (int) simCoord.getX();
            int simY= (int) simCoord.getY();
            this.simulation.setState(simX,simY,drawMode);
            draw();
        } catch (NonInvertibleTransformException e) {
            System.out.println("can't reverseTransform");
        }

        //System.out.println(mouseX+","+mouseY);
    }

    public void draw(){
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0,0,450,450);
        g.setTransform(affine);
        g.setFill(Color.BLACK);
        for(int x=0;x<this.simulation.width;x++){
            for (int y=0;y<this.simulation.height;y++){
                if(this.simulation.getState(x,y)==Simulation.ALIVE){
                    g.fillRect(x,y,1,1);
                }
            }
        }
        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for(int x=0;x<this.simulation.width+1;x++){
            g.strokeLine(x,0,x,10);
        }
        for (int y=0;y<this.simulation.height+1;y++){
            g.strokeLine(0,y,10,y);
        }

    }

    public Simulation getSimulation() {
        return this.simulation;
    }
    public void setDrawMode(int drawMode) {
        this.drawMode=drawMode;
    }
}
