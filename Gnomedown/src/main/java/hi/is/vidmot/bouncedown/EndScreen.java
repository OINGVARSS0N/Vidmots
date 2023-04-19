package hi.is.vidmot.bouncedown;

import javafx.application.Platform;

public class EndScreen {

    public void onMain() {
        ViewSwitcher.switchTo(View.MainMenu);
    }

    public void onAgain() {
        ViewSwitcher.switchTo(View.Leikur);
        BouncedownController sc = (BouncedownController) ViewSwitcher.lookup(View.Leikur);
        sc.hefjaLeik();
        sc.orvatakkar();
        sc.pasaLeik();
        sc.muteaLeik();
        sc.fxLeikbord.pallar();
    }

    public void onExit() {
        Platform.exit();

    }
}
