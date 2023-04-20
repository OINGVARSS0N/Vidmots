package hi.is.vidmot.bouncedown;

import Vinnsla.Leikur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;


public class BouncedownController{

    @FXML
    Leikbord fxLeikbord;
    @FXML
    Label fxStig;
    private MediaPlayer mediaPlayer;
    boolean isPaused=false;

    private Timeline t;
    Leikur leikur;

    public void initialize(){
        leikur = new Leikur(fxStig);
        String lag = "src/main/java/hi/is/vidmot/bouncedown/seashanty.mp3";
        Media sound = new Media(new File(lag).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    public void orvatakkar() {
        fxLeikbord.orvatakkar();
    }
    public void hefjaLeik() {
        if (t != null) {
            t.stop();
        }
        KeyFrame k = new KeyFrame(Duration.millis(35), e -> {
            fxLeikbord.afram(leikur);
            fxLeikbord.athugaArekstur();
            try {
                leiklokid();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    /**
     * notar "m" til þess að mutea leikinn
     * þessi klasi bætir við hljóði fyrir leikinn.
     */
    public void muteaLeik(){
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

    /**
     * notar "ESCAPE" takkan til þess að pása og
     * byrja leikinn aftur.
     * þessi klasi bætir við þeim möguleika að pása leikinn.
     */
    public void pasaLeik(){
        fxStig.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                isPaused = !isPaused;

                if (isPaused) {
                    t.pause();
                } else {
                    t.play();
                }
            }
        });

    }

    /**
     * Ef leikurinn tapast þá skiptir viewswitcherinn
     * í endscreenið.
     */
    private void leiklokid() throws IOException {
        if (fxLeikbord.tapaleik) {
            t.stop();
            mediaPlayer.stop();
            leikur.saveScoreToFile();
            ViewSwitcher.switchTo(View.EndScreen);
        }
    }
}