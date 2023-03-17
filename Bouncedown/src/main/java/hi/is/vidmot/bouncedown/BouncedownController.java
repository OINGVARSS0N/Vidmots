package hi.is.vidmot.bouncedown;

import Vinnsla.Leikur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Optional;


public class BouncedownController {

    private static final int UPP = 90;
    private static final int NIDUR = 270;
    private static final int VINSTRI = 180;
    private static final int HAEGRI = 360;
    @FXML
    Leikbord fxLeikbord;
    @FXML
    Label fxStig;

    private Timeline t;

    private static final HashMap<KeyCode, Integer> map = new HashMap<>();
    Leikur leikur;

    public void initialize(){
        leikur = new Leikur(fxStig);
    }
    public void orvatakkar() {
        map.put(KeyCode.UP, UPP);   // setjum upp beina aðganginn frá örvatökkunum og í hornið
        map.put(KeyCode.RIGHT, HAEGRI);
        map.put(KeyCode.LEFT, VINSTRI);
        fxStig.getScene().addEventFilter(KeyEvent.ANY,      //KeyEvents eru sendar á Scene
                event -> {      // lambda fall - event er parameter
                    try {
                        this.setStefna(map.get(event.getCode())); // flettum upp horninu fyrir KeyCode í map
                    } catch (NullPointerException e) {
                        event.consume();        // Ef rangur lykill er sleginn inn þá höldum við áfram
                    }
                });
    }
    public void hefjaLeik() {
        KeyFrame k = new KeyFrame(Duration.millis(50), e -> {
            fxLeikbord.afram(leikur);
            fxLeikbord.athugaArekstur();
            leiklokid();
        });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    private void leiklokid(){
        if(fxLeikbord.tapaleik){
            t.stop();
            Platform.runLater(() -> synaAlert());
        }
    }
    public void setStefna(int gradur) {
        fxLeikbord.setStefna(gradur);
    }
    private void synaAlert() {
        Alert a = new AdvorunDialog("", BouncedownApplication.skra, "Nice try kid");
        Optional<ButtonType> u = a.showAndWait();
        if (u.isPresent() && u.get().getButtonData().isCancelButton()){
            System.exit(0);
        }


    }
}