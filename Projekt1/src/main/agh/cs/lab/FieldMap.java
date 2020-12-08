package agh.cs.lab;

import java.util.*;

class FieldMap implements IWorldMap, IPositionChangeObserver{
    final private Map<Vector2d, FieldMapCell> animals;
    final private Map<Vector2d, Grass> grassFields;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final Vector2d jungleLowerLeft;
    private final Vector2d jungleUpperRight;
    private final int moveEnergy;
    final private MapVisualizer visualizer;
    final private int startEnergy;
    final private Random random;
    final private float plantEnergy;

    public FieldMap(int width, int height, Vector2d jungleLowerLeft, Vector2d jungleUpperRight, int startEnergy, int moveEnergy, float plantEnergy){
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width-1, height-1);
        this.jungleLowerLeft = jungleLowerLeft;
        this.jungleUpperRight = jungleUpperRight;
        animals = new LinkedHashMap<>();
        visualizer = new MapVisualizer(this);
        grassFields = new LinkedHashMap<>();
        random = new Random();
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;

        if(jungleLowerLeft.follows(jungleUpperRight.add(new Vector2d(1,1))) || jungleUpperRight.x >= width || jungleUpperRight.y >= height){
            throw new IllegalArgumentException("Niepoprawne wektory dla dżungli: "+ jungleLowerLeft + ";"+jungleUpperRight);
        }


    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return isOccupiedByAnimal(position) || isOccupiedByGrass(position);
    }

    private boolean isOccupiedByAnimal(Vector2d position){
        return objectAt(position) instanceof FieldMapCell;
    }

    private boolean isOccupiedByGrass(Vector2d position){
        return grassFields.get(position) instanceof Grass;
    }

    private boolean isInJungle(Vector2d position){
        return position.follows(jungleLowerLeft) && position.precedes(jungleUpperRight);
    }

    private boolean canRandGrassInJungle(){
        for(int x = jungleLowerLeft.x; x <= jungleUpperRight.x; x++){
            for(int y = jungleLowerLeft.x; y <= jungleUpperRight.y; y++){
                if(!isOccupied(new Vector2d(x,y))){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canRandGrassOutsideJungle() {
        for(int x = lowerLeft.x; x <= upperRight.x; x++){
            for(int y = lowerLeft.x; y <= upperRight.y; y++){
                if(!(isInJungle(new Vector2d(x,y))) && !isOccupied(new Vector2d(x,y))){
                    return true;
                }
            }
        }
        return false;
    }

    private void randGrassInJungle(){
        if(canRandGrassInJungle()){
            Vector2d position;

            do {
                position = randomPosition();
            } while (!isInJungle(position) || isOccupied(position));

            placeGrass(position);
        }
    }

    private void randGrassOutsideJungle(){
        if(canRandGrassOutsideJungle()) {
            Vector2d position;

            do {
                position = randomPosition();
            } while (isInJungle(position) || isOccupied(position));

            placeGrass(position);
        }
    }

    private void placeGrass(Vector2d position){
        grassFields.put(position, new Grass(position));
    }



    public void putGrasses(){
        randGrassInJungle();
        randGrassOutsideJungle();
    }

    private List<Animal> makeListOfAllAnimals(){
        List<Animal> allAnimals = new ArrayList<>();

        for(Map.Entry<Vector2d, FieldMapCell> entry: animals.entrySet()){
            allAnimals.addAll(entry.getValue().getAnimalsList());
        }

        return allAnimals;
    }

    private boolean isOccupiedAround(Vector2d center){
        Vector2d position;

        for(MapDirection direction: MapDirection.values()){
            position = center.add(direction.toUnitVector()).wrapBy(lowerLeft, upperRight);
            if(!isOccupiedByAnimal(position)){
                return false;
            }
        }

        return true;
    }

    private Vector2d findFreePosition(Vector2d center){
        Vector2d position;
        boolean isNotFree = isOccupiedAround(center);
//        System.out.println(isNotFree);

        do{
            position = center.add(MapDirection.NORTH.randomDirection().toUnitVector()).wrapBy(lowerLeft, upperRight);
        }while(!isNotFree && isOccupiedByAnimal(position));

        return position;
    }


    private Vector2d randomPosition(){
        return new Vector2d(random.nextInt(upperRight.x+1), random.nextInt(upperRight.y+1));
    }

    private Vector2d randomAnimalPosition(){
        Vector2d position;

        do{
            position = randomPosition();
        }while(isOccupiedByAnimal(position));

        return position;
    }



    @Override
    public void run() {
//        System.out.println(animals.entrySet());

        List<Animal> allAnimals = makeListOfAllAnimals();

        for(Animal animal: allAnimals){
            animal.move();
        }

        removeEmptyCells();
//        System.out.println(animals.entrySet());
//        System.out.println(animals.size());

    }

    public void randomPlace(int animalsAmount){
        if(animalsAmount > ((upperRight.x - lowerLeft.x+1)*(upperRight.y - lowerLeft.y+1)) || animalsAmount < 0){
            throw new IllegalArgumentException(animalsAmount + " to niepoprawna ilość zwierząt na początek");
        }

        Vector2d position;
        for(int i = 0; i < animalsAmount; i++){
            position = randomAnimalPosition();

            Animal animal = new Animal(this, startEnergy,position);
            animal.addObserver(this);
            animals.put(position, new FieldMapCell(animal));
        }
    }

    public void eat(){
        for(Map.Entry<Vector2d, FieldMapCell> entry: animals.entrySet()){
            if(isOccupiedByGrass(entry.getKey())) {
                entry.getValue().eatingGrass(plantEnergy);
                grassFields.remove(entry.getKey());
            }
        }
//        System.out.println(grassFields.size());
    }

    @Override
    public boolean place(Animal animal) {
        animal.addObserver(this);

        if(isOccupiedByAnimal(animal.getPosition())){
            animals.get(animal.getPosition()).add(animal);
        }
        else{
            animals.put(animal.getPosition(), new FieldMapCell(animal));
        }

        return true;
    }


    public void multiplication(){
        AnimalPair parents;
        List <Animal> newChildren = new ArrayList<>();

        for(Map.Entry<Vector2d, FieldMapCell> entry: animals.entrySet()){

            if(entry.getValue().enoughAnimalsForReproduction()) {
                parents = entry.getValue().findTwoMax();
                if (parents.canBeParents()) {
                    Vector2d childPosition = findFreePosition(parents.getPairPosition());
                    Animal childAnimal = new Animal(this, parents.getForChildEnergy(), childPosition, parents.mixParentsGenes());
                    newChildren.add(childAnimal);
                    parents.addChild(childAnimal);
//                    System.out.println("NEW CHILD " + parents.getPairPosition());
                }
            }

        }

        for(Animal children: newChildren){
            place(children);
        }

    }

    @Override
    public Object objectAt(Vector2d position) {
        Object animalObject = animals.get(position);
        if(animalObject == null){
            return grassFields.get(position);
        }
        return animals.get(position);
    }

    @Override
    public String toString(){
        return visualizer.draw(lowerLeft, upperRight);
    }

    private void removeEmptyCells(){
        Vector2d positions[] = animals.keySet().toArray(new Vector2d[animals.size()]);

        for(Vector2d position: positions){
            if(animals.get(position).isAnimalListEmpty())
                animals.remove(position);
        }
    }

    public void removeAnimalsWithLowEnergy(){
        for(Map.Entry<Vector2d, FieldMapCell> entry: animals.entrySet()){
            entry.getValue().removeWithLowEnergy();
        }

        removeEmptyCells();
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        FieldMapCell animalsCell = animals.get(oldPosition);

        Animal animal = animalsCell.getAnimal(newPosition);

        if(isOccupiedByAnimal(newPosition)) {animals.get(newPosition).add(animal);}
        else    {animals.put(newPosition, new FieldMapCell(animal));}
    }

    @Override
    public Vector2d getLowerLeft(){
        return lowerLeft;
    }

    @Override
    public Vector2d getUpperRight(){
        return upperRight;
    }

    @Override
    public int getMoveEnergy(){
        return moveEnergy;
    }

    @Override
    public int getStartEnergy() {
        return startEnergy;
    }
}
