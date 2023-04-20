package hi.is.vidmot.bouncedown;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class BouncedownApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Pane(),600,600);
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.MainMenu);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}