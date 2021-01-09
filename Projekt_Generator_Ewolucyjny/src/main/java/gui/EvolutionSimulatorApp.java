package gui;    // zbiór niepowiązanych ze sobą pakietów?
// co mam oceniać? Projekt1, czy Projekt_Generator_Ewolucyjny?
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EvolutionSimulatorApp extends Application {    // lepiej trzymać tę klasę poziom wyżej, żeby łatwiej użytkownikowi było ją znaleźć

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane firstMap = loader.load(getClass().getResource("/EvolutionSimulatorGui.fxml"));

            HBox root = new HBox();
            root.getChildren().add(firstMap);

            if(checkTwoMaps()) {
                FXMLLoader otherLoader = new FXMLLoader();
                BorderPane secondMap = otherLoader.load(getClass().getResource("/EvolutionSimulatorGui.fxml"));
                root.getChildren().add(secondMap);
            }

            stage.setScene(new Scene(root));
            stage.setTitle("Symulator Ewolucyjny");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }


    private boolean checkTwoMaps(){
        boolean isTwoMap = true;

        try{
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/appSettings.json"));  // json to nie źródło (fmxl też nie bardzo), więc nie powinien być w src
            JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();
            isTwoMap = parser.get("twoMaps").getAsBoolean();

        }catch (Exception ex){  // wszystkie wyjątki do jednego worka
            ex.printStackTrace();
        }

        return isTwoMap;
    }

    public static void main(String[] args) {
        launch(args);
    }
}