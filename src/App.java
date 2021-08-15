import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainView mainView=new MainView();
        Scene scene=new Scene(mainView,600,600);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainView.draw();

    }

    public static void main(String[] args) {
        launch();
    }
}
