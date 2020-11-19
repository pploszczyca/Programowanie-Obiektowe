package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap{
    final protected List<Animal> animals;
    protected Vector2d lowerLeft;   // czy to na pewno jest część wspólna obu map?
    protected Vector2d upperRight;
    MapVisualizer visualizer ;  // to pole może być finalne i prywatne

    public AbstractWorldMap(int width, int height){
        upperRight = new Vector2d(width, height);   // width - 1
        animals = new ArrayList<>();
        visualizer = new MapVisualizer(this);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(objectAt(position) instanceof Animal)
            return true;
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    public void run(MoveDirection[] directions) {
        int n = animals.size();
        for(int i = 0; i < directions.length; i++){
            animals.get(i%n).move(directions[i]);
        }
    }

    @Override
    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getPosition()))
            return false;

        animals.add(animal);
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animalOnMap: animals){
            if(animalOnMap.getPosition().equals(position))
                return animalOnMap;
        }

        return null;
    }

    public abstract String toString();  // ta metoda miała być tutaj zaimplementowana

}
