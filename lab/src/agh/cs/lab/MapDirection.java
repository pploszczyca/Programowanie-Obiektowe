package agh.cs.lab;

public enum MapDirection{
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString(){
        switch (this){
            case NORTH: return "Północ";
            case SOUTH: return "Południe";
            case EAST: return "Wschód";
            case WEST: return "Zachód";
            default: return ""; // zwracanie prawidłowego napisu w bardzo nieprawidłowej sytuacji to zły pomysł - trudno wytropić błąd
        }
    }

    public MapDirection next(){
        switch (this){
            case NORTH: return EAST;
            case SOUTH: return WEST;
            case EAST: return SOUTH;
            case WEST: return NORTH;
            default: return NORTH;
        }
    }

    public MapDirection previous(){
        switch (this){
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case EAST: return NORTH;
            case WEST: return SOUTH;
            default: return NORTH;
        }
    }

    public Vector2d toUnitVector(){
        switch (this){
            case NORTH: return new Vector2d(0,1);
            case EAST: return new Vector2d(1,0);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
            default:    return new Vector2d(0,0);
        }
    }
}