package gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import model.Simulation;

public class EvolutionSimulatorApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
//            Parent root = FXMLLoader.load(getClass().getResource("/EvolutionSimulatorGui.fxml"));
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