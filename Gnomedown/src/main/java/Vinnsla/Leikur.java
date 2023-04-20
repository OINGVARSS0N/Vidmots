package Vinnsla;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

public class Leikur {
    private IntegerProperty stig = new SimpleIntegerProperty();

    public IntegerProperty getStig() {
        return stig;
    }

    public Leikur(Label fxStig) {
        fxStig.textProperty().bind(Bindings.concat("Stig: ", getStig()));
    }

    public void haekkastig(int i) {
        stig.set(stig.get() + i);
    }

    public void saveScoreToFile() {
        Platform.runLater(() -> {
            TextInputDialog dialogName = new TextInputDialog();
            dialogName.setTitle("High Score");
            dialogName.setHeaderText("Skrifaðu nafnið þitt:");
            dialogName.setContentText("Nafn:");

            Optional<String> result = dialogName.showAndWait();
            if (result.isPresent()) {
                String playerName = result.get();

                try {
                    FileWriter fileWriter = new FileWriter("highscores.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    bufferedWriter.write(playerName + "," + stig.get() + "\n");

                    bufferedWriter.close();

                    System.out.println("High Scores:");
                    try (Scanner scanner = new Scanner(new File("highscores.txt"))) {
                        while (scanner.hasNextLine()) {
                            System.out.println(scanner.nextLine());
                        }
                    }

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("High Score");
                    alert.setHeaderText(null);
                    alert.setContentText("Your high score of " + stig.getValue() + " has been saved!\n\n" +
                            "Name: " + playerName + "\n" +
                            "Score: " + stig.getValue());
                    alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

