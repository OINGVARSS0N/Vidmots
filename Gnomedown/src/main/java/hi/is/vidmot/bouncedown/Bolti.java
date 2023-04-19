package hi.is.vidmot.bouncedown;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;



public class Bolti extends ImageView implements Leikhlutur {
    private final int hreyfing = 8;

    public Bolti () {
        FXML_Lestur.lesa(this,"bolti-view.fxml");
    }
    private boolean movingLeft = false;
    private boolean movingRight = false;
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }
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








}