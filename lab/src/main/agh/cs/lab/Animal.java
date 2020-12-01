package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement{
    private MapDirection direction;
    private Vector2d position;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> animalObservers;

    public Animal(IWorldMap map){
        this(map, new Vector2d(2,2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        direction = MapDirection.NORTH;
        position = initialPosition;
        animalObservers = new ArrayList<>();
    }

    @Override
    public String toString(){
        switch (direction){
            case NORTH: return "N";
            case EAST: return "E";
            case SOUTH: return "S";
            case WEST: return "W";
        }

        throw new IllegalArgumentException();
    }


    public void move(MoveDirection direction){
        if(direction == MoveDirection.RIGHT)
            this.direction = this.direction.next();
        else if(direction == MoveDirection.LEFT)
            this.direction = this.direction.previous();
        else if(direction == MoveDirection.FORWARD || direction == MoveDirection.BACKWARD){
            Vector2d newPosition;

            if(direction == MoveDirection.FORWARD)
                newPosition = position.add(this.direction.toUnitVector());
            else
                newPosition = position.subtract(this.direction.toUnitVector());

            if(map.canMoveTo(newPosition)) {
                Vector2d oldPosition = position;
                position = newPosition;
                positionChanged(oldPosition, newPosition);
            }
        }
    }

    public Vector2d getPosition(){
        return position;
    }

    public void addObserver(IPositionChangeObserver observer){
        animalObservers.add(observer);
    }

    protected void removeObserver(IPositionChangeObserver observer){
        animalObservers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer: animalObservers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

}
