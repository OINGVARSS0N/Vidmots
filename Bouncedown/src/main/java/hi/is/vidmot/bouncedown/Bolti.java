package hi.is.vidmot.bouncedown;

import javafx.scene.image.ImageView;


public class Bolti extends ImageView implements Leikhlutur {
    private static int HRADI = 5;
    private int teljari =1;
    private final int hreyfing = 8;

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

    }

    public boolean erArekstur(Pallur i) {
        // Get the center of the Bolti object
        double boltiCenterX = getX() + getWidth() / 2;
        double boltiCenterY = getY() + getHeight() / 2;

        // Get the center of the Pallur object
        double pallurCenterX = i.getX() + i.getWidth() / 2;
        double pallurCenterY = i.getY() + i.getHeight() / 2;

        // Calculate the distance between the centers of the objects
        double distanceX = Math.abs(boltiCenterX - pallurCenterX);
        double distanceY = Math.abs(boltiCenterY - pallurCenterY);

        // Check if the objects are colliding
        boolean collisionDetected = (distanceX <= (getWidth() / 2 + i.getWidth() / 2)) && (distanceY <= (getHeight() / 2 + i.getHeight() / 2));

        // Restrict movement to the Bouncedown direction
        if (collisionDetected && getY() + getHeight() <= i.getY()) {
            setY(i.getY() - getHeight());
        }

        return collisionDetected;
    }

    private double getHeight() {
        return getFitHeight();
    }

    private double getWidth() {
        return getFitWidth();
    }

}