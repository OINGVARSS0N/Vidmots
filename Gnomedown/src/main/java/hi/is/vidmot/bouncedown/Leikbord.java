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
    @FXML
    private Pallur fxpallur6;
    public boolean tapaleik = false;
    private final int Max_breidd = 600;
    private final int Max_haed = 600;


    public Leikbord() {
        FXML_Lestur.lesa(this, "leikbord-view.fxml");
        bolti();
        pallar();
    }

    //staðsetur boltan
    public void bolti() {
        fxbolti.setTranslateX(1);
        fxbolti.setTranslateY(1);
        fxbolti.setX(Max_breidd/2);
        fxbolti.setY(100);
        fxbolti.setFitHeight(50);
    }

    //hreinsar og bætir við pöllum.
    public void pallar() {
        pallur.clear();
        pallur.addAll(fxpallur1, fxpallur2, fxpallur3, fxpallur4, fxpallur5,fxpallur6);
        pallur.forEach(p -> {
            p.setX(random.nextInt(Max_breidd - 150));
            p.setY(random.nextInt(400) + 200);
        });
    }

    public void athugaArekstur() {
        double yHradiBolta = fxbolti.getY() + fxbolti.getFitHeight();
        boolean collided = false;
        double highestPallurY = Double.MIN_VALUE;

        for (Pallur i : pallur) {
            double yHradiPallur = i.getY();

            if (yHradiBolta >= yHradiPallur && yHradiBolta <= yHradiPallur + i.getHeight()) {
                // skoðar ef boltinn snertir pallinn frá hliðinni
                if (fxbolti.getX() + fxbolti.getFitWidth() > i.getX()
                        && fxbolti.getX() < i.getX() + i.getWidth()) {
                    collided = true;
                    if (yHradiPallur > highestPallurY) {
                        highestPallurY = yHradiPallur;
                    }
                }
            }
        }

        if (collided) {
            fxbolti.setY(highestPallurY - fxbolti.getFitHeight());
        }
    }

    //ef leikmaður fer upp í eða niður í mörkin á leikborðinu þá tapast leikurinn
    public void tapaleik() {
        if (fxbolti.getY() > 600) {
            tapaleik = true;
        }
    }
    public void afram(Leikur leikur){
        if(!tapaleik){
            fxbolti.move(270);
            fxbolti.afram();

            // Passar að boltinn fari ekki út fyrir leikinn
            if (fxbolti.getX() < 0) {
                fxbolti.setX(0);
            } else if (fxbolti.getX() + fxbolti.getFitWidth() > Max_breidd) {
                fxbolti.setX(Max_breidd - fxbolti.getFitWidth());
            }

            athugaArekstur();

            // Færir pallana
            for (Pallur i : pallur){
                i.move(270);
            }
            //hækkar stigin ef leikur er í gangi
            if(!tapaleik){
                leikur.haekkastig(1);

            }
            tapaleik();
        }
    }

    /**
     * vinstri örvatakkinn færir boltan til vinstri
     * hægri örvatakkinn færir boltan til hægri
     */
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
