package agh.cs.lab;

public enum MapDirection{
    NORTH("Północ", new Vector2d(0,1)),
    NORTH_WEST("Północny-Wschód", new Vector2d(-1,1)),
    NORTH_EAST("Północny-Zachód", new Vector2d(1,1)),
    SOUTH("Południe", new Vector2d(0,-1)),
    SOUTH_WEST("Południowy-sWschód", new Vector2d(-1,-1)),
    SOUTH_EAST("Południowy-Zachód", new Vector2d(1,-1)),
    WEST("Zachód",new Vector2d(-1,0)),
    EAST("Wschód", new Vector2d(1,0));


    private String directionName;
    private Vector2d unitVector;

    private MapDirection(String directionName, Vector2d unitVector){
        this.unitVector = unitVector;
        this.directionName = directionName;
    }

    public String toString(){
        return this.directionName;
    }

    public MapDirection next(){
        switch (this){
            case NORTH: return EAST;
            case SOUTH: return WEST;
            case EAST: return SOUTH;
            case WEST: return NORTH;
        }

        throw new IllegalArgumentException();
    }

    public MapDirection previous(){
        switch (this){
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case EAST: return NORTH;
            case WEST: return SOUTH;
        }

        throw new IllegalArgumentException();
    }

    public Vector2d toUnitVector(){
        return this.unitVector;
    }
}