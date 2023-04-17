package hi.is.vidmot.bouncedown;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class BouncedownApplication extends Application {
    public static final String skra = "Bouncedown";
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bouncing-view.fxml"));
        Parent root = loader.load();
        BouncedownController sc = loader.getController();
        stage.setTitle("Bouncedown");
        Scene s = new Scene(root, 600, 600);
        sc.orvatakkar();
        stage.setScene(s);
        stage.show();
        sc.hefjaLeik();
        sc.pasaLeik();
        sc.muteaLeik();
    }



    public static void main(String[] args) {
        launch();
    }
}