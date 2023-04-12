module hi.is.vidmot.bouncedown {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;

    opens hi.is.vidmot.bouncedown to javafx.fxml;
    exports hi.is.vidmot.bouncedown;
}