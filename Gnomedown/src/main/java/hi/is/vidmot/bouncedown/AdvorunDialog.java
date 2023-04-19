package hi.is.vidmot.bouncedown;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AdvorunDialog extends Alert {
    private static final String HAETTA_VID = "Hætta";
    public static final ButtonType HTYPE = new ButtonType(HAETTA_VID,
            ButtonBar.ButtonData.CANCEL_CLOSE); // ButtonType er merktur með CANCEL_CLOSE (er enum);



    public AdvorunDialog(String titill, String haus, String spurning) {
        super(AlertType.NONE, spurning, HTYPE);  // kallar á smið yfirklasans
        setTitle(titill);
        setHeaderText(haus);
    }
}