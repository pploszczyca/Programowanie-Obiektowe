package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FieldMapCell implements IPositionChangeObserver{
    private List<Animal> animals;

    public FieldMapCell(Animal animal){
        animals = new ArrayList<>();
        this.add(animal);
    }

    public void add(Animal animal){
        animals.add(animal);
        animal.addObserver(this);
    }

    public Animal getAnimal(Vector2d position){
        for(Animal animal: animals){
            if(animal.getPosition().equals(position)){
                return animal;
            }
        }
        return null;
    }

    public void removeWithLowEnergy(){
        Iterator<Animal> iterator = animals.iterator();
        Animal animal;

        while(iterator.hasNext()){
            animal = iterator.next();

            if(animal.getEnergy()<=0){
                animal.removeDrawn();
                iterator.remove();
            }
        }
    }

    public Animal removeAnimal(Vector2d position){
        Animal animalCopy;
        for(int i = 0; i < animals.size(); i++){
            if(animals.get(i).getPosition().equals(position)){
                animalCopy = animals.get(i);
                animals.remove(i);
                return animalCopy;
            }
        }
        return null;
    }

    public int animalsWithMaxEnergy(){
        sortList();
        int amount;
        if(isAnimalListEmpty()){
            return 0;
        }

        for(amount = 1; amount < animals.size(); amount++){
            if(animals.get(amount-1).getEnergy() != animals.get(amount).getEnergy())
                break;
        }

        return amount;
    }

    public void eatingGrass(float eatingEnergy){
        int amountConsumingAnimals = animalsWithMaxEnergy();
        float extraEnergy = eatingEnergy / amountConsumingAnimals;

        for (int i = 0; i < amountConsumingAnimals; i++){
            animals.get(i).addEnergy(extraEnergy);
        }
    }


    public int size(){
        return animals.size();
    }

    public boolean isAnimalListEmpty(){
        return animals.isEmpty();
    }

    private void sortList(){
        animals.sort(new Comparator<Animal>() {
            @Override
            public int compare(Animal animal, Animal anotherAnimal) {
                return Float.compare(anotherAnimal.getEnergy(), animal.getEnergy());
            }
        });
    }

    public AnimalPair findTwoMax(){
        sortList();

        return new AnimalPair(animals.get(0), animals.get(1));
    }

    public boolean enoughAnimalsForReproduction(){
        return animals.size() >= 2;
    }

    public String toString(){
        return animals.toString();
    }

    public List<Animal> getAnimalsList(){
        return animals;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = removeAnimal(newPosition);
        animal.removeObserver(this);
    }

    public int getAnimalsAmount(){
        return animals.size();
    }

}
