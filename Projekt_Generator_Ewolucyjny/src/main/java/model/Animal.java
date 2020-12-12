package model;

import gui.MapColors;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractMapElement{
    private MapDirection direction;
    private Vector2d position;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> animalObservers;
    private float energy;
    private AnimalGenes genes;
    private final List<Animal> children;



    public Animal(IWorldMap map){
        this(map, map.getStartEnergy());
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this(map, map.getStartEnergy(), initialPosition);
    }

    public Animal(IWorldMap map, float startEnergy){
        this(map,startEnergy, new Vector2d(2,2));
    }

    public Animal(IWorldMap map, float startEnergy, Vector2d initialPosition){
        this(map, startEnergy, initialPosition, new AnimalGenes());
    }

    public Animal(IWorldMap map, float startEnergy, Vector2d initialPosition, Pane world){
        this(map, startEnergy, initialPosition, new AnimalGenes(), world);
    }

    public Animal(IWorldMap map, float startEnergy, Vector2d initialPosition, AnimalGenes genes) {
        super(initialPosition);
        this.map = map;
        direction = MapDirection.NORTH.randomDirection();
        animalObservers = new ArrayList<>();
        this.genes = genes;
        energy = startEnergy;
        children = new ArrayList<>();
        position = initialPosition;
    }

    public Animal(IWorldMap map, float startEnergy, Vector2d initialPosition, AnimalGenes genes, Pane world){
        super(initialPosition, world);
        this.map = map;
        direction = MapDirection.NORTH.randomDirection();
        animalObservers = new ArrayList<>();
        this.genes = genes;
        energy = startEnergy;
        children = new ArrayList<>();
        position = initialPosition;
        addObserver(drawing);
        drawElement();

    }



    @Override
    public String toString(){
        switch (direction){
            case NORTH: return "N";
            case NORTH_WEST: return "NW";
            case NORTH_EAST: return "NE";
            case EAST: return "E";
            case SOUTH: return "S";
            case SOUTH_EAST: return "SE";
            case SOUTH_WEST: return "SW";
            case WEST: return "W";
        }

        throw new IllegalArgumentException();
    }

    public void move(){
        energy = energy - map.getMoveEnergy();
        direction = direction.rotate(genes.drawRotation());
        Vector2d oldPosition = position;
        position = position.add(direction.toUnitVector()).wrapBy(map.getLowerLeft(), map.getUpperRight());


        positionChanged(oldPosition, position);
    }


    public float getEnergy(){
        return energy;
    }

    public void addEnergy(float extraEnergy){
        energy += extraEnergy;
    }

    public void removeEnergy(float extraEnergy){
        energy -= extraEnergy;
    }

    public void addObserver(IPositionChangeObserver observer){
        animalObservers.add(observer);
    }

    protected void removeObserver(IPositionChangeObserver observer){
        animalObservers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        List <IPositionChangeObserver> observers = new ArrayList<>(animalObservers);

        for(IPositionChangeObserver observer: observers){
            observer.positionChanged(oldPosition, newPosition);
        }

    }

    public boolean canReproduce(){
        return energy/map.getStartEnergy() >= 1/2;
    }

    public void addChild(Animal animal){
        children.add(animal);
    }

    public int[] getGenes(int startIndex, int endIndex){
        return genes.getGenesElements(startIndex, endIndex);
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;

        return false;
    }

    @Override
    public Vector2d getPosition(){
        return position;
    }

    @Override
    public MapColors getPointColor(){
        double energyRatio = energy/ map.getStartEnergy();

        if(Double.compare(energyRatio, 0.666666) >= 0){
            return MapColors.ANIMAL_MAX_ENERGY;
        }
        else if(Double.compare(energyRatio, 0.333333) >= 0){
            return MapColors.ANIMAL_MIDDLE_ENERGY;
        }
        else{
            return MapColors.ANIMAL_LOW_ENERGY;
        }
    }

}
