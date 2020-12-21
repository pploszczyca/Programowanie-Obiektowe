package model;

import java.util.Random;

public enum MapDirection{
    NORTH(new Vector2d(0,1),0),
    NORTH_EAST( new Vector2d(1,1),1),
    EAST(new Vector2d(1,0),2),
    SOUTH_EAST(new Vector2d(1,-1),3),
    SOUTH( new Vector2d(0,-1),4),
    SOUTH_WEST( new Vector2d(-1,-1),5),
    WEST(new Vector2d(-1,0),6),
    NORTH_WEST(new Vector2d(-1,1),7);


    private Vector2d unitVector;
    private int directionNumber;

    private MapDirection(Vector2d unitVector, int directionNumber){
        this.unitVector = unitVector;
        this.directionNumber = directionNumber;
    }

    public MapDirection rotate(int number){
        return getDirectionByNumber((this.directionNumber + number)%8);
    }

    private MapDirection getDirectionByNumber(int number){
        switch(number){
            case 0: return NORTH;
            case 1: return NORTH_EAST;
            case 2: return EAST;
            case 3: return SOUTH_EAST;
            case 4: return SOUTH;
            case 5: return SOUTH_WEST;
            case 6: return WEST;
            case 7: return NORTH_WEST;
        }

        throw new IllegalArgumentException();
    }

    public Vector2d toUnitVector(){
        return this.unitVector;
    }

    public MapDirection randomDirection(){
        return MapDirection.NORTH.rotate(new Random().nextInt(8));
    }
}