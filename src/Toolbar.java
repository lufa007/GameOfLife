import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {
    public Toolbar(){
        Button step=new Button("step");
       // step.setOnAction(this::hadleStep);

        Button draw=new Button("draw");
        //step.setOnAction(this::hadleDraw);

        Button erase=new Button("erase");
        //step.setOnAction(this::hadleErase);
        this.getItems().addAll(step,draw,erase);
    }

    private void hadleErase(ActionEvent actionEvent) {
    }

    private void hadleStep(ActionEvent actionEvent) {
        System.out.println("step");
    }

    private void hadleDraw(ActionEvent actionEvent) {
    }


}
