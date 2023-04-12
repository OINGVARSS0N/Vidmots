package hi.is.vidmot.bouncedown;

import Vinnsla.Leikur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashMap;
import java.util.Optional;


public class BouncedownController{

    private static final int UPP = 90;
    private static final int NIDUR = 270;
    private static final int VINSTRI = 180;
    private static final int HAEGRI = 360;
    @FXML
    Bolti fxbolti;
    @FXML
    Leikbord fxLeikbord;
    @FXML
    Label fxStig;
    boolean isPaused=false;

    private Timeline t;

    private static final HashMap<KeyCode, Integer> map = new HashMap<>();
    Leikur leikur;

    public void initialize(){
        leikur = new Leikur(fxStig);
    }
    public void orvatakkar() {
        fxLeikbord.orvatakkar();
    }
    public void hefjaLeik() {
        KeyFrame k = new KeyFrame(Duration.millis(40), e -> {
            fxLeikbord.afram(leikur);
            fxLeikbord.athugaArekstur();
            leiklokid();
        });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }
    public void muteaLeik(){
        //notar "m" til þess að mutea leikinn
        String lag = "src/main/java/hi/is/vidmot/bouncedown/seashanty.mp3";
        Media sound = new Media(new File(lag).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        Button muteButton = new Button("Mute");
        muteButton.setOnAction(event -> {
            mediaPlayer.setMute(!mediaPlayer.isMute());
            muteButton.setText(mediaPlayer.isMute() ? "Unmute" : "Mute");
        });
        fxStig.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.M) {
                mediaPlayer.setMute(!mediaPlayer.isMute());
                muteButton.setText(mediaPlayer.isMute() ? "Unmute" : "Mute");
            }
        });
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    public void pasaLeik(){
        //notar ESC til þess að pása leikinn
        fxStig.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                // Toggle pause state
                isPaused = !isPaused;

                if (isPaused) {
                    // Pause the animation
                    t.pause();
                } else {
                    // Resume the animation
                    t.play();
                }
            }
        });

    }

    private void leiklokid() {
        if (fxLeikbord.tapaleik) {
            t.stop();
            Platform.runLater(() -> synaAlert());
        }
    }
    private void synaAlert() {
        Alert a = new AdvorunDialog("", BouncedownApplication.skra, "Nice try kid");
        Optional<ButtonType> u = a.showAndWait();
        if (u.isPresent() && u.get().getButtonData().isCancelButton()){
            System.exit(0);
        }
    }
}