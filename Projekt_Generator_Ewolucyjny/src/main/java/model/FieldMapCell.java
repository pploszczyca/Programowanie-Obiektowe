package model;

import statistics.MapStatistics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FieldMapCell implements IPositionChangeObserver{
    private final List<Animal> animals;
    private final MapStatistics statistics;

    public FieldMapCell(Animal animal, Simulation simulation){
        animals = new ArrayList<>();
        statistics = simulation.getStatistics();
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

    public int removeWithLowEnergy(){
        Iterator<Animal> iterator = animals.iterator();
        Animal animal;
        int animalsRemoved = 0;

        while(iterator.hasNext()){
            animal = iterator.next();

            if(animal.getEnergy()<=0){
                animal.removeDrawn();
                animal.setDeathDay(statistics.getEraCounter()+1);
                statistics.addDeathAnimal(statistics.getEraCounter()-animal.getBirthday());
                iterator.remove();
                animalsRemoved++;
            }
        }

        return animalsRemoved;
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

        return new AnimalPair(animals.get(0), animals.get(1));  // a co z losowaniem w przypadku remisu?
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

    public float sumAnimalsEnergy(){
        float sum = 0;

        for(Animal animal: animals){
            sum += animal.getEnergy();
        }

        return sum;
    }

    public int getAnimalsChildrenAmount(){
        int sum = 0;

        for(Animal animal: animals){
            sum += animal.getChildrenAmount();
        }

        return sum;
    }

}
