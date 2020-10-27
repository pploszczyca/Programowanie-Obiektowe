package agh.cs.lab;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public String toString(){
        return direction.toString() + " " + position.toString();
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

            if(newPosition.precedes(new Vector2d( 4,4) ) && newPosition.follows(new Vector2d(0,0)))
                position = newPosition;
        }
    }

}
