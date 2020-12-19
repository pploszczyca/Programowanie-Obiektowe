package model;

import gui.MapColors;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AnimalTest {

    @Test
    public void moveTest(){
        Simulation simulation = new Simulation(10,10,1,1,10,5,0,0,new Pane());
        Animal animal = new Animal(simulation.getMap(), simulation.getStartEnergy(), new Vector2d(5,5), 0);

        Assertions.assertEquals(animal.getPosition(), new Vector2d(5,5));
        Assertions.assertEquals(animal.getEnergy(), 10, 0);

        animal.move();

        Assertions.assertNotEquals(animal.getPosition(), new Vector2d(5,5));
        Assertions.assertNotEquals(animal.getEnergy(), 10, 0);
        Assertions.assertEquals(animal.getPosition().lowerLeft(new Vector2d(4,4)), new Vector2d(4,4));
        Assertions.assertEquals(animal.getPosition().upperRight(new Vector2d(6,6)), new Vector2d(6,6));
        Assertions.assertEquals(animal.getEnergy(), 5, 0);

        animal.move();

        Assertions.assertNotEquals(animal.getEnergy(), 5, 0);
        Assertions.assertEquals(animal.getPosition().lowerLeft(new Vector2d(3,3)), new Vector2d(3,3));
        Assertions.assertEquals(animal.getPosition().upperRight(new Vector2d(7,7)), new Vector2d(7,7));
        Assertions.assertEquals(animal.getEnergy(), 0, 0);
    }

    @Test
    public void getPointColorTest(){
        Simulation simulation = new Simulation(10,10,1,1,18,3,0,0,new Pane());

        FieldMap map = simulation.getMap();
        Animal animal = new Animal(map, 18, new Vector2d(4,4), new AnimalGenes(), new Pane(),0);

        map.place(animal);
        map.run();

        Assertions.assertEquals(animal.getEnergy(), 15);
        Assertions.assertEquals(animal.getPointColor(), MapColors.ANIMAL_MAX_ENERGY);

        map.run();
        map.run();
        Assertions.assertEquals(animal.getPointColor(), MapColors.ANIMAL_MIDDLE_ENERGY);

        map.run();
        Assertions.assertEquals(animal.getPointColor(), MapColors.ANIMAL_MIDDLE_ENERGY);

        map.run();
        Assertions.assertEquals(animal.getPointColor(), MapColors.ANIMAL_LOW_ENERGY);

        animal.addEnergy(6);
        Assertions.assertEquals(animal.getPointColor(), MapColors.ANIMAL_MIDDLE_ENERGY);
        animal.addEnergy(6);
        Assertions.assertEquals(animal.getPointColor(), MapColors.ANIMAL_MAX_ENERGY);

    }
}
