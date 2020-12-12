package model;

public interface IWorldMap {
    boolean place(Animal animal);
    void run();
    boolean isOccupied(Vector2d position);
    Vector2d getLowerLeft();
    Vector2d getUpperRight();
    int getMoveEnergy();
    void eat();
    void removeAnimalsWithLowEnergy();
    void multiplication();
    void putGrasses();
    Object objectAt(Vector2d position);
    int getStartEnergy();
}
