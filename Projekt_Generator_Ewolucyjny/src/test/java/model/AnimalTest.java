package model;

import gui.MapColors;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AnimalTest {

    @Test
    public void moveTest(){
        FieldMap map = new FieldMap(10,10,new Vector2d(0,0), new Vector2d(0,0),10, 5, 0);
        Animal animal = new Animal(map, new Vector2d(5,5));

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
        FieldMap map = new FieldMap(10,10,new Vector2d(0,0), new Vector2d(0,0),18, 3, 0);
        Animal animal = new Animal(map, 18, new Vector2d(4,4), new AnimalGenes(), new Pane());

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
