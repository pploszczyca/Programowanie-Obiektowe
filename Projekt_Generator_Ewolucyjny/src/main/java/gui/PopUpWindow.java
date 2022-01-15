package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


public class PopUpWindow {
    private VBox root;
    private final Stage window;
    private final AnimalEventHandler handler;

    public PopUpWindow(AnimalEventHandler handler){
        this.handler = handler;
        try {
            FXMLLoader loader = new FXMLLoader();
            root = loader.load(getClass().getResource("/PopUpGui.fxml"));
        } catch (Exception e){
            e.printStackTrace();
        }

        window = new Stage();
    }

    public void show(){
        window.setScene(new Scene(root));
        window.show();

        setButtonOnClickAction();
    }

    private void setButtonOnClickAction(){
        Button confirmButton = (Button) window.getScene().lookup("#confirmButton");
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                setStopEra();

            }
        });
    }

    public void setWindowView(Window mainWindow){
        window.setTitle("Informacje o zwierzęciu");
        window.setX(mainWindow.getScene().getWindow().getX()+300);
        window.setY(mainWindow.getScene().getWindow().getY()+300);

        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(mainWindow);
    }

    public void setText(String text){
        Label label = (Label) window.getScene().lookup("#genLabel");
        label.setText(text);
    }

    public void setStopEra(){
        try {
            TextField eraField = (TextField) window.getScene().lookup("#eraField");
            long eraNumber = Long.parseLong(eraField.getText(),10);
            handler.setEraStop(eraNumber);
        } catch (NumberFormatException ex){
            System.out.println("Niepoprawnie wpisana wartość dla śledzenia epok");
        }
    }


}
