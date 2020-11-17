package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class AnimalTest {

    @Test
    public void moveTest(){
        Animal animal = new Animal(new RectangularMap(4,4));

        animal.move(MoveDirection.RIGHT);
        Assert.assertEquals(animal.toString(), "E");
        Assert.assertTrue(animal.getPosition().equals(new Vector2d(2,2)));
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        Assert.assertEquals(animal.toString(), "W");
        Assert.assertTrue(animal.getPosition().equals(new Vector2d(2,2)));

        animal.move(MoveDirection.LEFT);
        Assert.assertEquals(animal.toString(), "S");
        Assert.assertTrue(animal.getPosition().equals(new Vector2d(2,2)));
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);
        Assert.assertEquals(animal.toString(), "N");
        Assert.assertTrue(animal.getPosition().equals(new Vector2d(2,2)));

        animal.move(MoveDirection.FORWARD);
        Assert.assertEquals(animal.toString(), "N");
        Assert.assertTrue(animal.getPosition().equals(new Vector2d(2,3)));
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        Assert.assertEquals(animal.toString(), "N");
        Assert.assertTrue(animal.getPosition().equals(new Vector2d(2,4)));
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.toString(), "W");
        Assert.assertTrue(animal.getPosition().equals(new Vector2d(4,4)));

        animal = new Animal(new RectangularMap(4,4));      // Północ (2,2)
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.toString(), "N");
        Assert.assertTrue(animal.getPosition().equals(new Vector2d(2,0)));
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.toString(), "E");
        Assert.assertTrue(animal.getPosition().equals(new Vector2d(0,0)));

    }
}
