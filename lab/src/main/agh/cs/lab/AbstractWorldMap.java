package agh.cs.lab;

import java.util.LinkedHashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    final protected Map<Vector2d, Animal> animals;
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    final private MapVisualizer visualizer;

    public AbstractWorldMap() {
        animals = new LinkedHashMap<>();
        visualizer = new MapVisualizer(this);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) instanceof Animal)
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
        Animal animalsFromMap[] = animals.values().toArray(new Animal[animals.size()]);
        for (int i = 0; i < directions.length; i++) {
            animalsFromMap[i % n].move(directions[i]);
        }

    }

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition())) {
            throw new IllegalArgumentException("Position " + animal.getPosition().toString() + " is incorrect or currently occupied");
        }

        animal.addObserver(this);
        animals.put(animal.getPosition(), animal);
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    protected Vector2d[] findCorners() {
        Vector2d[] corners = new Vector2d[2];   //first element for leftCorner, second element for rightCorner
        corners[0] = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        corners[1] = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (Map.Entry<Vector2d, Animal> animal : animals.entrySet()) {
            corners[0] = corners[0].lowerLeft(animal.getValue().getPosition());
            corners[1] = corners[1].upperRight(animal.getValue().getPosition());
        }

        return corners;
    }

    public String toString() {
        Vector2d[] corners = findCorners();

        return visualizer.draw(corners[0], corners[1]);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }


}
