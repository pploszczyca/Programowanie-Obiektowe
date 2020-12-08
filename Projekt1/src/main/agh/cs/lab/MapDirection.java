package agh.cs.lab;

import java.util.Map;
import java.util.Random;

public enum MapDirection{
    NORTH("Północ", new Vector2d(0,1),0),
    NORTH_EAST("Północny-Zachód", new Vector2d(1,1),1),
    EAST("Wschód", new Vector2d(1,0),2),
    SOUTH_EAST("Południowy-Zachód", new Vector2d(1,-1),3),
    SOUTH("Południe", new Vector2d(0,-1),4),
    SOUTH_WEST("Południowy-sWschód", new Vector2d(-1,-1),5),
    WEST("Zachód",new Vector2d(-1,0),6),
    NORTH_WEST("Północny-Wschód", new Vector2d(-1,1),7);


    private String directionName;
    private Vector2d unitVector;
    private int directionNumber;

    private MapDirection(String directionName, Vector2d unitVector, int directionNumber){
        this.unitVector = unitVector;
        this.directionName = directionName;
        this.directionNumber = directionNumber;
    }

    public String toString(){
        return this.directionName;
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