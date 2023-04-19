package hi.is.vidmot.bouncedown;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewSwitcher {

    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }
    private static final Map<View,Object> controllers = new HashMap<>();

    public static void switchTo(View view){
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }

        try {
            Parent root;
            FXMLLoader loader = null;
            loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFileName()));
            root = loader.load();
            scene.setRoot((Parent) root);
            controllers.put(view,loader.getController());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static Object lookup(View v){
        return controllers.get(v);
    }


}
