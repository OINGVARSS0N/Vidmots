package hi.is.vidmot.bouncedown;

import javafx.application.Platform;

public class EndScreen {

    public void onMain() {
        ViewSwitcher.switchTo(View.MainMenu);
    }

    public void onAgain() {
        ViewSwitcher.switchTo(View.Leikur);
    }

    public void onExit() {
        Platform.exit();

    }
}
