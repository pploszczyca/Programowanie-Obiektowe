package agh.cs.lab;

public class Animal {
    private MapDirection direction;
    private Vector2d position;
    private IWorldMap map;

    public Animal(IWorldMap map){
        this(map, new Vector2d(2,2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        direction = MapDirection.NORTH;
        position = initialPosition;
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

            if(map.canMoveTo(newPosition))
                position = newPosition;
        }
    }

    public Vector2d getPosition(){
        return position;
    }
}
