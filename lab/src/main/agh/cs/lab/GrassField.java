package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap {
    private int grassAmount;
    private final List<Grass> grassFields;
    private final MapBoundary boundary;

    public GrassField(int grassAmount){
        super();
        lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        upperRight = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);
        grassFields = new ArrayList<>();
        this.grassAmount = grassAmount;
        boundary = new MapBoundary();

        placeRandomGrass();

    }

    private void placeRandomGrass(){
        Random r = new Random();
        int endPoint, x, y;

        for(int i = 0; i < grassAmount; i++) {
            endPoint = (int) Math.round(Math.sqrt(10 * grassAmount));

            do{
                x = r.nextInt(endPoint);
                y = r.nextInt(endPoint);
            }while (isOccupied(new Vector2d(x, y)));

            Grass grass = new Grass(new Vector2d(x, y));
            grassFields.add(grass);
            boundary.addMapElement(grass);
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object maybeAnimal = super.objectAt(position);
        if(maybeAnimal instanceof Animal)
            return maybeAnimal;

        for(Grass grassItem: grassFields){
            if(grassItem.getPosition().equals(position))
                return grassItem;
        }

        return null;
    }

    @Override
    public boolean place(Animal animal) {
        if(super.place(animal)){
            animal.addObserver(boundary);
            boundary.addMapElement(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight) && !super.isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(objectAt(position) instanceof Grass || objectAt(position) instanceof Animal)
            return true;
        return false;
    }

    @Override
    protected Vector2d[] findCorners(){
        return boundary.findCorners();
    }


    public Vector2d getGrassItemPosition(int index){
        return grassFields.get(index).getPosition();
    }


}
