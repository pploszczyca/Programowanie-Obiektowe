package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class InformationWindow {
    private Stage window;
    private Label label;

    public InformationWindow(){
        window = new Stage();
        label = new Label();
    }

    public void setText(String text){
        label.setText(text);
        label.setAlignment(Pos.CENTER);
    }

    public void show(){
        window.setScene(new Scene(label));
        window.show();
    }

    public void setWindowView(Window mainWindow){
        window.setTitle("Informacje o zwierzęciu");
        window.setMinWidth(500);
        window.setMinHeight(50);

        window.setX(mainWindow.getScene().getWindow().getX()+300);
        window.setY(mainWindow.getScene().getWindow().getY()+300);

        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(mainWindow);
    }


}
