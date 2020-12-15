package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.Animal;

import java.util.Arrays;

public class AnimalEventHandler implements EventHandler<Event> {
    private Animal animal;

    public AnimalEventHandler(Animal animal){
        this.animal = animal;
    }

    @Override
    public void handle(Event event){
        System.out.println(Arrays.toString(animal.getGenes()));
    }
}
