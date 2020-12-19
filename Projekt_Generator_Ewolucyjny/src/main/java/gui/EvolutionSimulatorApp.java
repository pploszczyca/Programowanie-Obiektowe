package gui;

import javafx.application.Application;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class EvolutionSimulatorApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
//            FXMLLoader loader = new FXMLLoader();
            BorderPane root = FXMLLoader.load(getClass().getResource("/EvolutionSimulatorGui.fxml"));
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}