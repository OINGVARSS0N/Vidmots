package hi.is.vidmot.bouncedown;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class mainMenu extends Application {
    public Button StartGame;
    public Button HighScore;
    public Button Exitbutton;

    public void byrjaHandler(ActionEvent actionEvent) throws IOException {
        ViewSwitcher.switchTo(View.Leikur);
        BouncedownController sc = (BouncedownController) ViewSwitcher.lookup(View.Leikur);
        sc.hefjaLeik();
        sc.orvatakkar();
        sc.pasaLeik();
        sc.muteaLeik();

    }

    public void onHighScore() {
        ViewSwitcher.switchTo(View.EndScreen);
    }

    public void onEnd() {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}





