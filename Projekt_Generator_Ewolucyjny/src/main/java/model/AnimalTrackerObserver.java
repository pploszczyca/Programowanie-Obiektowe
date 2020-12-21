package model;

import java.util.ArrayList;
import java.util.List;

public class AnimalTrackerObserver {
    private Animal trackedAnimal;
    private List<Animal> animalDescendants;
    private int startEra;

    public AnimalTrackerObserver(Animal animal, int startEra){
        trackedAnimal = animal;
        animalDescendants = new ArrayList<>();
        this.startEra = startEra;
        trackedAnimal.setAnimalTrackerObserver(this);
    }

    public int countChildren(){
        return countChildrenAlive(trackedAnimal.getChildrenList());
    }

    private int countChildrenAlive(List<Animal> animals){
        int counter = 0;

        for(Animal animal: animals){
            if(!animal.isDead() && animal.getBirthday() > startEra){
                counter++;
            }
        }

        return counter;
    }

    private boolean isAnimalNotInList(Animal newAnimal){
        for(Animal animal: animalDescendants){
            if(animal.equals(newAnimal)){
                return false;
            }
        }

        return true;
    }

    public void addAnimal(Animal animal){
        if(isAnimalNotInList(animal)) {
            animalDescendants.add(animal);
        }
    }

    public int countDescendants(){
        return countChildrenAlive(animalDescendants);
    }

    public String toString(){
        if(trackedAnimal.isDead()){
            return "Obserwowane zwierzę umarło w " + trackedAnimal.getDeathDay() + " epoce.";
        }
        else {
            return "Obserwowane zwierzę zyskało " + countDescendants() + " żywych potomków, w tym " + countChildren() + " żywych dzieci";
        }
    }

}
