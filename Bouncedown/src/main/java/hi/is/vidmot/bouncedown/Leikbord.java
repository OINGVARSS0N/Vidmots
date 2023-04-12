package hi.is.vidmot.bouncedown;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import Vinnsla.Leikur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Leikbord extends Pane {
    private static final Random random = new Random();
    private static final ObservableList<Pallur> pallur = FXCollections.observableArrayList();
    @FXML
    private Bolti fxbolti;
    @FXML
    private Pallur fxpallur1;
    @FXML
    private Pallur fxpallur2;
    @FXML
    private Pallur fxpallur3;
    @FXML
    private Pallur fxpallur4;
    @FXML
    private Pallur fxpallur5;
    public boolean tapaleik = false;

    private final int Max_breidd = 600;
    private final int Max_haed = 600;

    public Leikbord() {
        FXML_Lestur.lesa(this, "leikbord-view.fxml");
        bolti();
        pallar();
    }


    public void bolti() {
        fxbolti.setTranslateX(20);
        fxbolti.setTranslateY(1);
    }

    public void pallar() {
        pallur.add(fxpallur1);
        pallur.add(fxpallur2);
        pallur.add(fxpallur3);
        pallur.add(fxpallur4);
        pallur.add(fxpallur5);

        fxpallur1.setX(random.nextInt(Max_breidd));
        fxpallur1.setY(random.nextInt(Max_haed));
        fxpallur2.setX(random.nextInt(Max_breidd));
        fxpallur2.setY(random.nextInt(Max_haed));
        fxpallur3.setX(random.nextInt(Max_breidd));
        fxpallur3.setY(random.nextInt(Max_haed));
        fxpallur4.setX(random.nextInt(Max_breidd));
        fxpallur4.setY(random.nextInt(Max_haed));
        fxpallur5.setX(random.nextInt(Max_breidd));
        fxpallur5.setY(random.nextInt(Max_haed));
    }

    public void setStefna(int upp) {
        fxbolti.move(upp);
    }

    public void athugaArekstur() {
        for (Pallur i : pallur) {
            if (fxbolti.erArekstur(i)) {
                fxbolti.setY(i.getY()-(fxbolti.getFitHeight()));
                break;
            }
        }
    }

    public void tapaleik() {
        if (fxbolti.getY() > 600 || fxbolti.getY() < 0) {
            tapaleik = true;
        }

    }
    public void afram(Leikur leikur){
        if(!tapaleik){
            fxbolti.move(270);
            fxbolti.afram();
            leikur.haekkastig(1);
            for (Pallur i : pallur){
                i.move(270);
            }tapaleik();
        }
    }
    public void orvatakkar() {
        fxbolti.getScene().addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {
                    switch (event.getCode()) {
                        case LEFT:
                            fxbolti.setMovingLeft(true);
                            break;
                        case RIGHT:
                            fxbolti.setMovingRight(true);
                            break;
                    }
                });
        fxbolti.getScene().addEventFilter(KeyEvent.KEY_RELEASED,
                event -> {
                    switch (event.getCode()) {
                        case LEFT:
                            fxbolti.setMovingLeft(false);
                            break;
                        case RIGHT:
                            fxbolti.setMovingRight(false);
                            break;
                    }
                });
    }
}
