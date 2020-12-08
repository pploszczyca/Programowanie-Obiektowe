package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class AnimalTest {

    @Test
    public void moveTest(){
        FieldMap map = new FieldMap(10,10,new Vector2d(0,0), new Vector2d(0,0),10, 5, 0);
        Animal animal = new Animal(map, new Vector2d(5,5));

        Assert.assertEquals(animal.getPosition(), new Vector2d(5,5));
        Assert.assertEquals(animal.getEnergy(), 10, 0);

        animal.move();

        Assert.assertNotEquals(animal.getPosition(), new Vector2d(5,5));
        Assert.assertNotEquals(animal.getEnergy(), 10, 0);
        Assert.assertEquals(animal.getPosition().lowerLeft(new Vector2d(4,4)), new Vector2d(4,4));
        Assert.assertEquals(animal.getPosition().upperRight(new Vector2d(6,6)), new Vector2d(6,6));
        Assert.assertEquals(animal.getEnergy(), 5, 0);

        animal.move();

        Assert.assertNotEquals(animal.getEnergy(), 5, 0);
        Assert.assertEquals(animal.getPosition().lowerLeft(new Vector2d(3,3)), new Vector2d(3,3));
        Assert.assertEquals(animal.getPosition().upperRight(new Vector2d(7,7)), new Vector2d(7,7));
        Assert.assertEquals(animal.getEnergy(), 0, 0);
    }
}
