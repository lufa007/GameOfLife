import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {
    private MainView mainView;

    public Toolbar(MainView mainView){
        this.mainView = mainView;

        Button draw=new Button("draw");
        draw.setOnAction(this::hadleDraw);

        Button erase=new Button("erase");
        erase.setOnAction(this::hadleErase);

        Button step=new Button("step");
        step.setOnAction(this::hadleStep);

        this.getItems().addAll(draw,erase,step);
    }

    private void hadleDraw(ActionEvent actionEvent) {
        this.mainView.setDrawMode(Simulation.ALIVE);
        System.out.println("draw");
    }

    private void hadleErase(ActionEvent actionEvent) {
        this.mainView.setDrawMode(Simulation.DEAD);
        System.out.println("erase");
    }

    private void hadleStep(ActionEvent actionEvent) {
        System.out.println("step");
        this.mainView.getSimulation().step();
        this.mainView.draw();
    }

}
