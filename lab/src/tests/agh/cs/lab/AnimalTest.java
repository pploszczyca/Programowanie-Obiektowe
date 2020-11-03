package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class AnimalTest {

    @Test
    public void moveTest(){
        Animal animal = new Animal(new RectangularMap(4,4));

        animal.move(MoveDirection.RIGHT);
        Assert.assertEquals(animal.toString() + " " + animal.getPosition().toString(), "E (2,2)");
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        Assert.assertEquals(animal.toString() + " " + animal.getPosition().toString(), "W (2,2)");

        animal.move(MoveDirection.LEFT);
        Assert.assertEquals(animal.toString() + " " + animal.getPosition().toString(), "S (2,2)");
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);
        Assert.assertEquals(animal.toString() + " " + animal.getPosition().toString(), "N (2,2)");

        animal.move(MoveDirection.FORWARD);
        Assert.assertEquals(animal.toString() + " " + animal.getPosition().toString(), "N (2,3)");
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        Assert.assertEquals(animal.toString() + " " + animal.getPosition().toString(), "N (2,4)");
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.toString() + " " + animal.getPosition().toString(), "W (4,4)");

        animal = new Animal(new RectangularMap(4,4));      // Północ (2,2)
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.toString() + " " + animal.getPosition().toString(), "N (2,0)");
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(animal.toString() + " " + animal.getPosition().toString(), "E (0,0)");

    }
}
