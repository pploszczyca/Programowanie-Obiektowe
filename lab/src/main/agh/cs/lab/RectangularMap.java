package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    private int width;  // potrzebuje Pan tych dwóch pól?
    private int height;
    private List<Animal> animals;   // to może być finalne
    final Vector2d lowerLeft = new Vector2d(0,0);
    final Vector2d upperRight;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        upperRight = new Vector2d(width, height);   // width - 1
        animals = new ArrayList<>();
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animalOnMap: animals){
            if(animalOnMap.getPosition().equals(position))
                return true;
        }
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
    public Object objectAt(Vector2d position) { // wygląda jak isOccupied
        for(Animal animalOnMap: animals){
            if(animalOnMap.getPosition().equals(position))
                return animalOnMap;
        }

        return null;
    }

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this); // można zapamiętać visualizer

        return visualizer.draw(lowerLeft, upperRight);
    }
}
