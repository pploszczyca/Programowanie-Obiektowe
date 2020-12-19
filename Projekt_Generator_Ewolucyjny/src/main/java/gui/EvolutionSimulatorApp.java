package gui;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class EvolutionSimulatorApp extends Application {

    @Override
    public void start(Stage stage) {
//        try {
//            BorderPane root = FXMLLoader.load(getClass().getResource("/EvolutionSimulatorGui.fxml"));
//            stage.setScene(new Scene(root));
//            stage.setTitle("Symulator Ewolucyjny");
//            stage.show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }

        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane firstMap = loader.load(getClass().getResource("/EvolutionSimulatorGui.fxml"));

            FXMLLoader otherLoader = new FXMLLoader();
            BorderPane secondMap = otherLoader.load(getClass().getResource("/EvolutionSimulatorGui.fxml"));

            HBox root = new HBox();
            root.getChildren().add(firstMap);
            root.getChildren().add(secondMap);

            stage.setScene(new Scene(root));
            stage.setTitle("Symulator Ewolucyjny");
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