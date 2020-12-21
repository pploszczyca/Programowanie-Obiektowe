package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import model.Animal;


public class AnimalEventHandler implements EventHandler<Event> {
    private Animal animal;
    private Pane world;
    private EvolutionSimulatorController controller;

    public AnimalEventHandler(Animal animal, Pane mainWorld){
        this.animal = animal;
        this.world = mainWorld;
    }

    @Override
    public void handle(Event event){
        PopUpWindow popUpWindow = new PopUpWindow(this);
        popUpWindow.setWindowView(world.getScene().getWindow());
        popUpWindow.show();
        popUpWindow.setText(animal.toString());

        controller = animal.getWindowController();

    }

    public void setEraStop(long eraAmount){
        controller.setAnimalTracker(animal);
        controller.setStopEra(eraAmount);
    }
}
