package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Animal;


public class AnimalEventHandler implements EventHandler<Event> {
    private Animal animal;
    private Pane world;

    public AnimalEventHandler(Animal animal, Pane mainWorld){
        this.animal = animal;
        this.world = mainWorld;
    }

    @Override
    public void handle(Event event){
        StackPane layout = new StackPane();

        Label label = new Label("Geny: " + animal.toString());
        label.setMinHeight(100);
        label.setMinWidth(100);


        layout.getChildren().add(label);

        Scene scene = new Scene(layout, 400,100);

        Stage newWindow = new Stage();
        newWindow.setScene(scene);

        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(world.getScene().getWindow());

        newWindow.setMinHeight(100);
        newWindow.setMinWidth(400);

        newWindow.setX(world.getScene().getWindow().getX()+400);
        newWindow.setY(world.getScene().getWindow().getY()+400);

        newWindow.show();


    }
}
