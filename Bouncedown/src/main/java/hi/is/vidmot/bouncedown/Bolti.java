package hi.is.vidmot.bouncedown;

import javafx.scene.image.ImageView;


public class Bolti extends ImageView implements Leikhlutur {
    private static int HRADI = 5;
    private int teljari =1;
    private final int hreyfing = 8;

    public Bolti () {
        FXML_Lestur.lesa(this,"bolti-view.fxml");
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
    }

    public boolean erArekstur(Pallur i) {
        return getBoundsInParent().intersects(i.getBoundsInParent());
    }

}