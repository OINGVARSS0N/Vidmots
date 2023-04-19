package hi.is.vidmot.bouncedown;

import javafx.scene.shape.Rectangle;
import java.util.Random;


public class Pallur extends Rectangle implements Leikhlutur {

    private static final Random random = new Random();
    private int hradi=4;
    private int teljari=0;

    public Pallur () {
            FXML_Lestur.lesa(this,"pallur-view.fxml");
        }
    public void move(int upp){
        if(teljari++ % 100==0){
            hradi++;
        }
        if(upp==270){
            setY(getY()-hradi);
        }
        if(getY()<0){
            setY(500);
            setX(random.nextInt(550));

        }
    }
    @Override
    public void afram() {

    }
}
