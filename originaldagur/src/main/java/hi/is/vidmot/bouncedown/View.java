package hi.is.vidmot.bouncedown;

public enum View {
    MainMenu("main-menu.fxml"),
    Leikur("bouncing-view.fxml"),
    EndScreen("EndScreen.fxml");

    private String fileName;
    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
