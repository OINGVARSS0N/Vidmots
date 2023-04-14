package hi.is.vidmot.bouncedown;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;



public class Bolti extends ImageView implements Leikhlutur {
    private static int HRADI = 3;
    private int teljari =1;
    private final int hreyfing = 8;

    @FXML
    Bolti fxbolti;

    public Bolti () {
        FXML_Lestur.lesa(this,"bolti-view.fxml");
    }
    private boolean movingLeft = false;
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    private boolean movingRight = false;
    public void move(int upp){
        if(upp==360){
            setX(getX()+hreyfing);
        }else if(upp == 180){
            setX(getX()-hreyfing);
        }else if(upp == 270){
            setY(getY()+hreyfing);
        }

    }

    public void afram() {
        if (movingLeft != movingRight){
            if(movingLeft) setX(getX()-hreyfing);
            else setX(getX()+hreyfing);
        }
    }

    public boolean erArekstur(Pallur i) {
        return getBoundsInParent().intersects(i.getBoundsInParent());
    }








}